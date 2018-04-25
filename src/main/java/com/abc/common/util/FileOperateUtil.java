/**
 *
 * @author geloin
 * @date 2012-5-5 下午12:05:57
 */
package com.abc.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.BusinessException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.system.bo.UserBO;
import com.alibaba.fastjson.JSONObject;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

/**
 *
 * @author geloin
 * @date 2012-5-5 下午12:05:57
 */
public class FileOperateUtil extends BaseObject{
    private static final String FILEID="fileid";
    public static final String REALNAME = "realName";
    private static final String STORENAME = "storeName";
    private static final String SIZE = "size";
    private static final String SUFFIX = "suffix";
    private static final String CONTENTTYPE = "contentType";
    private static final String CREATETIME = "createTime";
    public static final String UPLOADDIR = "UPLOADDIR";
    public static final String STOREFILEPATH = "storeFilePath";
    public static final String PREQUALIFICATIONID = "preQualificationId";
    public static final String FILETYPE = "fileType";
    public static final String FILETPATH = "filePath";//全文件路径
	public static final String VITUALNAME = "vitualName";
	public static final String URL = "url";

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");

	public static void main(String[] args) {
		try{
			System.out.println(mkDirFile("D:\\temp1\\static\\html\\csw\\1\\a.txt"));
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	/**
	 * 根据站点根目录创建目录
	 * @param path 需要创建的目录
	 * @return
	 */
	public static boolean mkDir(String path){
		File file = new File(getConfig("STATIC_HTML_PATH")+"/"+path);
		return (file.exists() && file.isDirectory()) ? true : file.mkdirs();
	}

	/**
	 * 创建目录下的文件
	 * @param filePath 文件路径包含文件名 支持多层级创建
	 * @return
	 */
	public static boolean mkDirFile(String filePath) throws BusinessException{
		try{
			String fileDir = filePath.substring(0,filePath.lastIndexOf("\\")+1);
			File file = new File(fileDir);
			if(!file.exists()){
				file.mkdirs();
			}
			file = new File(filePath);
			if(!file.exists()) {
				return file.createNewFile();
			}
		}catch (Exception e){
			throw new BusinessException("创建文件失败");
		}
		return false;
	}


    /**
     * 将上传的文件进行重命名
     *
     * @author geloin
     * @date 2012-3-29 下午3:39:53
     * @param name
     * @return
     */
    private static String rename(String name) {

        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()));
        Long random = (long) (Math.random() * now);
        String fileName = now + "" + random;

        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }

        return fileName;
    }

    /**
     * 压缩后的文件名
     *
     * @author geloin
     * @date 2012-3-29 下午6:21:32
     * @param name
     * @return
     */
    private static String zipName(String name) {
        String prefix = "";
        if (name.indexOf(".") != -1) {
            prefix = name.substring(0, name.lastIndexOf("."));
        } else {
            prefix = name;
        }
        return prefix + ".zip";
    }

    /**
     * 静态资源上传文件
     *
     * @author husl
     * @date 2015-8-15 下午14:25:47
     * @param request
     * @param fileType  子目录
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> upload(HttpServletRequest request,String subsystemType,FileType fileType) throws BusinessException,Exception {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	String filedir = null;
		String fileSuffix = null;
		String uploadedFileName = null;

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		filedir = !CommonUtils.nullOrBlank(BaseObject.getConfig("STATIC_FILE_DIR_PATH")) ? BaseObject.getConfig("STATIC_FILE_DIR_PATH") : "/home/html";

		filedir += "/"+subsystemType + fileType.dir;

		File file = new File(filedir);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = null;
		int i = 0;
		if (fileMap != null) {
			for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {
				String storeDirPath = "/"+subsystemType + fileType.dir;
				Map.Entry<String, MultipartFile> entry = it.next();
				Map<String, Object> map = new HashMap<String, Object>();

				MultipartFile mFile = entry.getValue();

				fileName = mFile.getOriginalFilename();
				fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				uploadedFileName = sdf.format(new Date()) + "." + fileSuffix;
				if(fileType.tag.equals("image")) {//图片类型校验
					if (mFile.getSize() > 1024 * Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE"))) {
						throw new BusinessException("-3","图片不能超过" + SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE") + "K");
					}
					BufferedImage bi = Thumbnails.of(mFile.getInputStream()).scale(1f).asBufferedImage();
					if (bi.getWidth() < Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_WIDTH"))) {
						throw new BusinessException("-3","图片宽度不能小于" + SpringCtxHolder.getProperty("IMG_UPLOAD_WIDTH") + "px");
					}else if (bi.getHeight() < Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_HEIGHT"))) {
						throw new BusinessException("-3","图片高度不能小于" + SpringCtxHolder.getProperty("IMG_UPLOAD_HEIGHT") + "px");
					}
					String require = SpringCtxHolder.getProperty("IMG_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "图片格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("file")){//文件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("FILE_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "文件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("media")){//多媒体类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("MEDIA_UPLOAD_SIZE"))) {
						throw new BusinessException("-3","多媒体文件不能超过" + SpringCtxHolder.getProperty("MEDIA_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("MEDIA_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "多媒体文件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("attachement")){//多附件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "附件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("ATTACHMENT_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "附件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else{//其他类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
				}

				String filePath = filedir + "/" + uploadedFileName;

				try {
					_log.debug("上传文件FilePath："+filePath);
					FileOutputStream outputStream = new FileOutputStream(filePath);
					FileCopyUtils.copy(mFile.getInputStream(), outputStream);
				} catch (Exception e) {
					_log.error(e.getMessage());
					e.printStackTrace();
					throw new Exception("上传文件异常");
				}

				map.put(FileOperateUtil.VITUALNAME, uploadedFileName);
				map.put(FileOperateUtil.REALNAME, fileName);
				map.put(FileOperateUtil.SIZE, new File(filePath).length());
				map.put(FileOperateUtil.SUFFIX, fileSuffix);
				map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
				map.put(FileOperateUtil.CREATETIME, new Date());
				map.put(FileOperateUtil.UPLOADDIR, filedir);
				map.put(FileOperateUtil.FILETPATH, fileType.dir);
				map.put(FileOperateUtil.STOREFILEPATH, storeDirPath + uploadedFileName);
				result.add(map);
			}
		}
		return result;
    }

	/**
	 * 内容编辑框中上传文件
	 *
	 * @author husl
	 * @date 2015-8-15 下午14:25:47
	 * @param request
	 * @param fileType  子目录
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> uploadFileInContent(HttpServletRequest request,String subsystemType,FileType fileType) throws BusinessException,Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		String filedir = null;
		String fileSuffix = null;
		String uploadedFileName = null;

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		filedir = !CommonUtils.nullOrBlank(BaseObject.getConfig("STATIC_FILE_DIR_PATH")) ? BaseObject.getConfig("STATIC_FILE_DIR_PATH") : "/home/html";

		filedir += "/"+subsystemType + fileType.dir;

		File file = new File(filedir);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = null;
		int i = 0;
		if (fileMap != null) {
			for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {
				String storeDirPath = "/"+subsystemType + fileType.dir;
				Map.Entry<String, MultipartFile> entry = it.next();
				Map<String, Object> map = new HashMap<String, Object>();

				MultipartFile mFile = entry.getValue();

				fileName = mFile.getOriginalFilename();
				fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				uploadedFileName = sdf.format(new Date()) + "." + fileSuffix;
				if(fileType.tag.equals("image")) {//图片类型校验
					if (mFile.getSize() > 1024 * Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE"))) {
						throw new BusinessException("-3","图片不能超过" + SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE") + "K");
					}
					BufferedImage bi = Thumbnails.of(mFile.getInputStream()).scale(1f).asBufferedImage();

					String require = SpringCtxHolder.getProperty("IMG_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "图片格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("file")){//文件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("FILE_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "文件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("media")){//多媒体类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("MEDIA_UPLOAD_SIZE"))) {
						throw new BusinessException("-3","多媒体文件不能超过" + SpringCtxHolder.getProperty("MEDIA_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("MEDIA_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "多媒体文件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("attachement")){//多附件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "附件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("ATTACHMENT_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "附件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else{//其他类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
				}

				String filePath = filedir + "/" + uploadedFileName;

				try {
					_log.debug("上传文件FilePath："+filePath);
					FileOutputStream outputStream = new FileOutputStream(filePath);
					FileCopyUtils.copy(mFile.getInputStream(), outputStream);
				} catch (Exception e) {
					_log.error(e.getMessage());
					e.printStackTrace();
					throw new Exception("上传文件异常");
				}

				map.put(FileOperateUtil.VITUALNAME, uploadedFileName);
				map.put(FileOperateUtil.REALNAME, fileName);
				map.put(FileOperateUtil.SIZE, new File(filePath).length());
				map.put(FileOperateUtil.SUFFIX, fileSuffix);
				map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
				map.put(FileOperateUtil.CREATETIME, new Date());
				map.put(FileOperateUtil.UPLOADDIR, filedir);
				map.put(FileOperateUtil.FILETPATH, fileType.dir);
				map.put(FileOperateUtil.STOREFILEPATH, storeDirPath + uploadedFileName);
				result.add(map);
			}
		}
		return result;
	}


	/**
	 * 内容编辑框中上传文件
	 *
	 * @author husl
	 * @date 2015-8-15 下午14:25:47
	 * @param request
	 * @param fileType  子目录
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> uploadSoaFileInContent(HttpServletRequest request,String subsystemType,FileType fileType) throws BusinessException,Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		String filedir = null;
		String fileSuffix = null;
		String uploadedFileName = null;

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		filedir = !CommonUtils.nullOrBlank(BaseObject.getConfig("STATIC_FILE_DIR_PATH")) ? BaseObject.getConfig("STATIC_FILE_DIR_PATH") : "/home/html";

		filedir += "/"+subsystemType + fileType.dir;

		File file = new File(filedir);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = null;
		int i = 0;
		if (fileMap != null) {
			for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {
				String storeDirPath = "/"+subsystemType + fileType.dir;
				Map.Entry<String, MultipartFile> entry = it.next();
				Map<String, Object> map = new HashMap<String, Object>();

				MultipartFile mFile = entry.getValue();

				fileName = mFile.getOriginalFilename();
				fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				uploadedFileName = sdf.format(new Date()) + "." + fileSuffix;
				if(fileType.tag.equals("image")) {//图片类型校验
					if (mFile.getSize() > 1024 * Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE"))) {
						throw new BusinessException("-3","图片不能超过" + SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE") + "K");
					}
					BufferedImage bi = Thumbnails.of(mFile.getInputStream()).scale(1f).asBufferedImage();

					String require = SpringCtxHolder.getProperty("IMG_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "图片格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("file")){//文件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("FILE_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "文件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("media")){//多媒体类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("MEDIA_UPLOAD_SIZE"))) {
						throw new BusinessException("-3","多媒体文件不能超过" + SpringCtxHolder.getProperty("MEDIA_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("MEDIA_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "多媒体文件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else if(fileType.tag.equals("attachement")){//多附件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "附件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
					String require = SpringCtxHolder.getProperty("ATTACHMENT_UPLOAD_SUFFIX");
					if (StringUtils.isNotEmpty(require)) {
						if(!require.contains(fileSuffix)) {
							throw new BusinessException("-3", "附件格式只能为" + require.replaceAll("\\.", "").replaceAll(";", ",") + "中的一种");
						}
					}
				}else{//其他类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE"))) {
						throw new BusinessException("-3", "文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
				}

				String filePath = filedir + "/" + uploadedFileName;

				try {
					_log.debug("上传文件FilePath："+filePath);
//					FileOutputStream outputStream = new FileOutputStream(filePath);
//					FileCopyUtils.copy(mFile.getInputStream(), outputStream);
					byte[] b = mFile.getBytes();
					String str = new BASE64Encoder().encodeBuffer(b);
					FjDto fjDto = new FjDto();
					fjDto.setFileName(fileName);
					CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
					try {
//                fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
						fjDto.setFileContent(str);
						UserBO userBO = (UserBO) request.getSession().getAttribute("currentUser");
						cmsFileUploadDto.setDirectory(userBO.getId());
						cmsFileUploadDto.getFjBo().add(fjDto);
					} catch (Exception e) {
						e.printStackTrace();
					}
					FileListDto fileListDto=SoaConnectionFactory.post(request,ConstantsUri.FILEUPBASE64,cmsFileUploadDto,FileListDto.class);
					map.put(FileOperateUtil.VITUALNAME, uploadedFileName);
					map.put(FileOperateUtil.REALNAME, fileName);
					map.put(FileOperateUtil.SIZE, mFile.getSize());
					map.put(FileOperateUtil.SUFFIX, fileSuffix);
					map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
					map.put(FileOperateUtil.CREATETIME, new Date());
					map.put(FileOperateUtil.UPLOADDIR, filedir);
					map.put(FileOperateUtil.FILETPATH, fileType.dir);
					map.put(FileOperateUtil.STOREFILEPATH, "/images"+fileListDto.getDataList().get(0).getFilePath());
					result.add(map);
					_log.info("文件上传日志{}",JSONObject.toJSONString(result));
				} catch (Exception e) {
					_log.error(e.getMessage());
					e.printStackTrace();
					throw new Exception("上传文件异常");
				}
			}
		}
		return result;
	}






    public static FileListDto voteImgUpload(MultipartHttpServletRequest request){
		String directory = "_voteUploadedImg";
		String fileName = null;
		List<Byte> content = null;
		FjDto fjDto = null;
		List<FjDto> fjDtoList = new ArrayList<>();
		FileListDto fileListDto = null;
		try{

			Map<String, MultipartFile> fileMap = request.getFileMap();
			if(fileMap != null){
				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {

					fjDto = new FjDto();
					Map.Entry<String, MultipartFile> entry = it.next();
					Map<String, Object> map = new HashMap<String, Object>();

					MultipartFile mFile = entry.getValue();
					fileName = mFile.getOriginalFilename();
//					content = fileBytesToList(mFile.getBytes());
					fjDto.setFileName(fileName);
//					fjDto.setContent(content);
                    fjDto.setFileContent(new BASE64Encoder().encode(mFile.getBytes()));
					fjDtoList.add(fjDto);
//					param.put("directory",directory);
//					param.put("fjBo",fjDto);
//					result.add(SoaConnectionFactory.post(request, ConstantsUri.SYSFILEUP, param, FileListDto.class));
				}

				CmsFileUploadDto param = new CmsFileUploadDto();
				param.setDirectory(directory);
				param.setFjBo(fjDtoList);
				fileListDto = SoaConnectionFactory.post(request, ConstantsUri.FILEUPBASE64, param, FileListDto.class);

			}

		}catch (Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
		}

		if(fileListDto.getDataList()==null){
			fileListDto.setDataList(new ArrayList<>());
		}

//		FjDto


//		String classPath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
//
//		String relativeImgDir = "static/images/voteUploaded/";
//
//		String filedir = classPath + relativeImgDir;
//		String fileSuffix = null;
//		String uploadedFileName = null;
//
//		try {
//			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
//			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
//
//			File file = new File(filedir);
//
//			if (!file.exists()) {
//				file.mkdirs();
//			}
//
//			String fileName = null;
//			if(fileMap != null){
//				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
//
//					Map.Entry<String, MultipartFile> entry = it.next();
//					Map<String, Object> map = new HashMap<String, Object>();
//
//					MultipartFile mFile = entry.getValue();
//					fileName = mFile.getOriginalFilename();
//
//					fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//					uploadedFileName = sdf.format(new Date()) + "." + fileSuffix;
//
//					String filePath = filedir + uploadedFileName;
//
//					FileOutputStream outputStream = new FileOutputStream(filePath);
//					FileCopyUtils.copy(mFile.getInputStream(), outputStream);
//
//					map.put(FileOperateUtil.VITUALNAME, uploadedFileName);
//					map.put(FileOperateUtil.REALNAME, fileName);
//					map.put(FileOperateUtil.SIZE, new File(filePath).length());
//					map.put(FileOperateUtil.SUFFIX, fileSuffix);
//					map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
//					map.put(FileOperateUtil.CREATETIME, new Date());
//					map.put(FileOperateUtil.UPLOADDIR, filedir);
//					map.put(FileOperateUtil.FILETPATH, filePath);
//					map.put(FileOperateUtil.URL, request.getContextPath()+"/images/voteUploaded/"+uploadedFileName);
//					result.add(map);
//				}
//			}
//		} catch(Exception e) {
//			_log.error(e.getMessage());
//			e.printStackTrace();
//		}
		return fileListDto;
	}
    
    public static String checkFile(HttpServletRequest request) throws Exception {
    	try {
    		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
    		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
    		String maxFileSize = !CommonUtils.nullOrBlank(BaseObject.getConfig("SYS_FILE_MAX_SIZE")) ? BaseObject.getConfig("SYS_FILE_MAX_SIZE") : "5";
    		if(fileMap != null){
				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, MultipartFile> entry = it.next();
					MultipartFile mFile = entry.getValue();
					String[] idInfo = entry.getKey().split("_");
					String fileTypeName = idInfo[0];
					String fileName = mFile.getOriginalFilename();
					String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					ArrayList<String> yszlType = new ArrayList<String>(){{add(".bmp"); add(".jpg"); add(".pdf");}};
					if (mFile.getSize() < 1) {
						return "100000010130";
					}
					if ((fileTypeName.indexOf("zxsqs") > -1 && suffix.indexOf(".doc") < 0) ||
							(fileTypeName.indexOf("yszl") > -1 && !yszlType.contains(suffix))) {
						return "100000010129";
					}
					if (Math.ceil(mFile.getSize() / 1024 / 1024) > Integer.parseInt(maxFileSize)) {
						return "100000010128";
					}
				}
			}
    	} catch(Exception e) {
			_log.error(e.getMessage());
    		e.printStackTrace();
    		throw new Exception("验证上传文件大小异常");
    	}
		return "";
    }

    /**
     * 下载
     *
     */
    public static void download(HttpServletRequest request,
                                HttpServletResponse response, String filePath, String fileName, String realFileName) throws Exception {
		String contentType = "application/octet-stream";

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String downLoadPath = filePath + fileName;

		long fileLength = new File(downLoadPath).length();
		if (fileLength > 0) {
			
			String agent = request.getHeader("User-Agent");
			boolean isMSIE = ((agent != null && agent.indexOf("MSIE") != -1) || agent.indexOf("rv:11") != -1);
			if (isMSIE) {
				realFileName = URLEncoder.encode(realFileName, "UTF-8");
				realFileName = realFileName.replace("+", "%20");
			} else {
				realFileName = new String(realFileName.getBytes("UTF-8"), "ISO-8859-1");
			}

			response.setContentType(contentType);
			response.setHeader("Content-disposition", "attachment; filename=" + realFileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			try {
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
//				bis.close();
//				bos.close();
			}catch(Exception e){
				_log.error(e.getMessage());
				e.printStackTrace();
			}finally {
				if(bis!=null) {
					bis.close();
				}
				if(bos!=null) {
					bos.flush();
					bos.close();
				}
			}
		}
    }
    
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    public static void delteFile(String filePath) {
    	File file = new File(filePath);
    	file.delete();
    }

	/**
	 * 返回SOA_URL
	 * @return
	 */
	public static String getSoaURL(){
		String soa_url = BaseObject.getConfig("SOA_DOWN_URL");
		return soa_url;
	}

	/**
	 * 文件内容转换为byteList
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static List fileBytesToList(byte[] bytes) throws Exception {
		Byte[] newBytes = new Byte[bytes.length];
		List list = null;
		for (int i = 0; i < bytes.length; i++) {
			newBytes[i] = bytes[i];
		}
		list = Arrays.asList(newBytes);
		return list;
	}

}