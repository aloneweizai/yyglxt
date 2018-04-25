package com.abc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.common.exception.BusinessException;
import com.abc.common.util.BaseObject;
import com.abc.common.util.FileType;
import com.abc.common.util.SFTPUtil;
import com.abc.dto.cms.FileDto;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Encoder;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.FileOperateUtil;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.SysTaskRq;
import com.abc.soa.response.consumer.SysTaskRs;
import com.abc.soa.response.system.bo.DictBO;
/**
 * 公用Controller
 * @author zhushuai 2017-6-28
 *
 */
@Controller
public class SystemUtilController extends BaseController{

	protected static Logger logger = LoggerFactory.getLogger(SystemUtilController.class);

	/**
	 * ajax单文件上传
	 * @param filename
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	 @SuppressWarnings("unchecked")
	 @RequestMapping("/util/doFileupload.php")
     public @ResponseBody BaseResponse uploadFile(String path,MultipartHttpServletRequest multipartRequest){
        try {
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			MultipartFile file = null;
			for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, MultipartFile> entry = it.next();
				file = entry.getValue();
			}
			  CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
			  FjDto fjDto = new FjDto();
			  fjDto.setFileName(file.getOriginalFilename());
			  try {
				fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
				cmsFileUploadDto.setDirectory(path.replaceAll("!", "/"));
				cmsFileUploadDto.getFjBo().add(fjDto);
			  } catch (Exception e) {
				e.printStackTrace();
			  }
			  FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
			  if(fileListDto.getDataList().size()>0){
				  return new BaseResponse("200", "/images" + fileListDto.getDataList().get(0).getFilePath());
			  }else{
				  return new BaseResponse("300", "上传失败");
			  }
		} catch (Exception e) {
			return new BaseResponse("300", "上传失败:程序异常");
		}
    	
     }
	
	 /**
	  * 页面富文本编辑器上传
	  * @param multipartRequest
	  * @return
	  */
	@RequestMapping("/util/wangEditorUpload.php")
	@SuppressWarnings("unchecked")
	public @ResponseBody Map<String, Object> wangEditorUpload(MultipartHttpServletRequest multipartRequest){
		 Map<String, Object> result = new HashMap<>();
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
		 cmsFileUploadDto.setDirectory("/goods");
		 if(fileMap != null){
				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, MultipartFile> entry = it.next();
					MultipartFile file = entry.getValue();
					FjDto fjDto = new FjDto();
			        fjDto.setFileName(file.getOriginalFilename());
			        try {
						//fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
			        	fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
			            cmsFileUploadDto.getFjBo().add(fjDto);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		 }
		 FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
		 if(fileListDto.getDataList().size()>0){
			 result.put("errno", "0");
			 List<String> data = new ArrayList<String>();
			 for(int i=0;i<fileListDto.getDataList().size();i++){
				 data.add(SpringCtxHolder.getProperty("abc.soa-upload-url") + "/images" + fileListDto.getDataList().get(i).getFilePath());
			 }
			 result.put("data", data);
         }else{
        	 result.put("errno", "-1");
         }
		 return result;
	 }

	/**
	 * 页面富文本编辑器上传自定义地址
	 * @param multipartRequest
	 * @param directory
	 * @return
	 */
	@PostMapping("/util/wangEditorUpload/{directory}.php")
	public @ResponseBody Map<String, Object> editorUpload(MultipartHttpServletRequest multipartRequest, @PathVariable String directory){
		Map<String, Object> result = new HashMap<>();
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
		cmsFileUploadDto.setDirectory(directory);
		if(fileMap != null){
			try {
				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, MultipartFile> entry = it.next();
					MultipartFile file = entry.getValue();
					FjDto fjDto = new FjDto();
					fjDto.setFileName(file.getOriginalFilename());
					//fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
					fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
					cmsFileUploadDto.getFjBo().add(fjDto);
				}
			} catch (Exception e) {
				logger.error("SystemUtilController.editorUpload: error:"+e.getMessage(), e);
				result.put("errno", "-1");
				return result;
			}
		}
		FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
		if(fileListDto.getDataList()!= null && !fileListDto.getDataList().isEmpty()){
			List<String> data = new ArrayList<>();
			for(FileDto file: fileListDto.getDataList()){
				data.add(SpringCtxHolder.getProperty("abc.soa-upload-url") + "/images" + file.getFilePath());
			}
			result.put("errno","0");
			result.put("data", data);
		}else{
			result.put("errno", "-1");
		}
		return result;
	}
	
	 
	   /**
		 * 数据字典获取
		 * @param request
		 * @param criteria
		 * @return
		 */
		@RequestMapping("/util/jsonDictBOs.php")
		public @ResponseBody List<DictBO> getDictBOs(HttpServletRequest request,@RequestParam("dictId") String dictId){
			return getDictBOList(request,dictId);
		}
		
		/**
		 * excel导出
		 * @param request
		 * @param response
		 * @param excelType
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping("/util/exportExcel.php")
		public void exportExecl(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(required=true,value="excelType")String excelType){
			Workbook workBook = null;
			List list=new ArrayList<>();
			try {
				Map map=new HashMap();
				//File file = ResourceUtils.getFile("classpath:tempfiles/INVOICE_TEMP.xlsx");
				
				SysTaskRq sysTaskRq=new SysTaskRq();
				SysTaskRs sysTaskRs=SoaConnectionFactory.get(request, ConstantsUri.SYSTASK_LIST, sysTaskRq, SysTaskRs.class);
				list=sysTaskRs.getDataList();
				
				map.put("table", list);
				XLSTransformer transformer = new XLSTransformer();
				InputStream input= new ClassPathResource("tempfiles/INVOICE_TEMP.xlsx").getInputStream();
				workBook =transformer.transformXLS(input, map);
				this.writeExlcel(request,response,"纸质发票打印列表.xlsx",workBook);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		/**
		 * excel导出
		 * @param request
		 * @param response
		 * @param fileName 文件名
		 * @param workbook Excel内容
		 * @throws Exception
		 */
		private void writeExlcel(HttpServletRequest request, HttpServletResponse response,String fileName,Workbook workbook)throws Exception{
			
			fileName = new String(fileName.getBytes("GBK"),"ISO8859_1");      
			response.setHeader("content-disposition", "attachment; filename="+fileName);  
		    response.setContentType("application/msexcel");  
			OutputStream stream= response.getOutputStream();
			workbook.write(stream);
			stream.flush();
			stream.close();
		}

	/**
	 * 上传多媒体
	 * @param mediaName  文件名
	 * @param path 路径
	 * @param multipartRequest
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/util/mediaUpload.php")
	public @ResponseBody Map uploadMedia(@RequestParam(value = "mediaName")String mediaName,
										 @RequestParam(value = "path")String path,
										 MultipartHttpServletRequest multipartRequest) throws IOException {
		MultipartFile file = multipartRequest.getFile(mediaName);
		String fileSize = String.valueOf(file.getSize()/(1024*1024));
		long time = System.currentTimeMillis();
		Map<String, String> map = SFTPUtil.uploadMedia(path, file);
		logger.info(" 上传文件花费秒： "+(System.currentTimeMillis() - time)/1000);

//		map.put("fileSize", fileSize);
		map.put("fileName", file.getOriginalFilename());
		return map;
	}


	/**
	 * 初始化编辑器
	 * @param request
	 * @return
	 */
	@GetMapping("/util/initUeditorFileUpload.php")
	@ResponseBody
	public Map<String, Object> initUeditorFileUpload(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		result.put("imageUrl", "/util/ueditorFileUpload.php");
		result.put("imagePath", "/ueditor/php/");
		result.put("imageFieldName", "upfile");
		result.put("imageMaxSize", "10240");
		result.put("imageActionName","uploadimage");
		result.put("imageUrlPrefix",BaseObject.getConfig("cswdomain"));
		return result;
	}

	/**
	 * 初始化编辑器
	 * @param request
	 * @return
	 */
	@GetMapping("/util/initSoaUeditorFileUpload.php")
	@ResponseBody
	public Map<String, Object> initSoaUeditorFileUpload(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		result.put("imageUrl", "/util/ueditorSoaFileUpload.php");
		result.put("imagePath", "/ueditor/php/");
		result.put("imageFieldName", "upfile");
		result.put("imageMaxSize", "10240");
		result.put("imageActionName","uploadimage");
		result.put("imageUrlPrefix",BaseObject.getConfig("picdomain"));
		return result;
	}

	/**
	 * 编辑器上传
	 * @param request
	 * @return
	 */
	@PostMapping("/util/ueditorFileUpload.php")
	@ResponseBody
	public Map<String, Object> ueditorFileUpload(MultipartHttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		FileType tarFileType = null;

		String subsystem = request.getParameter("subsystem");
		String fileTypeTag = request.getParameter("fileTypeTag");


		if(StringUtils.isEmpty(subsystem)||StringUtils.isEmpty(fileTypeTag)){//为空
			_log.error("subsystem或fileTypeTag为空");
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}

		for(FileType fileType: FileType.values()){
			if(fileType.tag.equals(fileTypeTag)){
				tarFileType = fileType;
			}
		}

		if(StringUtils.isEmpty(subsystem)||tarFileType==null){//为空
			_log.error("subsystem或tarFileType为空");
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}


		List<Map<String,Object>> fileResult = null;
		try {
			fileResult = FileOperateUtil.uploadFileInContent(request,subsystem,tarFileType);
		}catch (BusinessException e){
			_log.error(e.getMessage());
			result.put("state", e.getMessage());
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}catch (Exception e){
			e.printStackTrace();
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}

		if (fileResult!=null && fileResult.size() > 0) {//数量大于0


			List<String> data = new ArrayList<>();
			for (int i = 0; i < fileResult.size(); i++) {
				data.add((String) fileResult.get(i).get(FileOperateUtil.FILETPATH) + "/" + (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
			}
			result.put("state", "SUCCESS");
			result.put("url",  (String) fileResult.get(0).get(FileOperateUtil.FILETPATH) + "/" + (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
			result.put("title", (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
			result.put("original", (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
		}else{
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
		}
		return result;
	}





	/**
	 * 编辑器上传
	 * @param request
	 * @return
	 */
	@PostMapping("/util/ueditorSoaFileUpload.php")
	@ResponseBody
	public Map<String, Object> ueditorSoaFileUpload(MultipartHttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		FileType tarFileType = null;

		String subsystem = request.getParameter("subsystem");
		String fileTypeTag = request.getParameter("fileTypeTag");


		if(StringUtils.isEmpty(subsystem)||StringUtils.isEmpty(fileTypeTag)){//为空
			_log.error("subsystem或fileTypeTag为空");
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}

		for(FileType fileType: FileType.values()){
			if(fileType.tag.equals(fileTypeTag)){
				tarFileType = fileType;
			}
		}

		if(StringUtils.isEmpty(subsystem)||tarFileType==null){//为空
			_log.error("subsystem或tarFileType为空");
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}


		List<Map<String,Object>> fileResult = null;
		try {
			fileResult = FileOperateUtil.uploadSoaFileInContent(request,subsystem,tarFileType);
		}catch (BusinessException e){
			_log.error(e.getMessage());
			result.put("state", e.getMessage());
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}catch (Exception e){
			e.printStackTrace();
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			return result;
		}

		if (fileResult!=null && fileResult.size() > 0) {//数量大于0


			/*List<String> data = new ArrayList<>();
			for (int i = 0; i < fileResult.size(); i++) {
				data.add((String) fileResult.get(i).get(FileOperateUtil.FILETPATH) + "/" + (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
			}*/
			result.put("state", "SUCCESS");
			result.put("url",  fileResult.get(0).get(FileOperateUtil.STOREFILEPATH));
			result.put("title", (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
			result.put("original", (String)fileResult.get(0).get(FileOperateUtil.VITUALNAME));
		}else{
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
		}
		return result;
	}


	/**
	 * 上传m3u8格式zip文件
	 * @param mediaName  文件名
	 * @param path 路径
	 * @param multipartRequest
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/util/uploadM3u8Zip.php")
	public @ResponseBody Map uploadM3u8Zip(@RequestParam(value = "mediaName")String mediaName,
										 @RequestParam(value = "path")String path,
										 MultipartHttpServletRequest multipartRequest) throws Exception {
		Map<String, String> map = new HashMap<>();
		try {
			MultipartFile file = multipartRequest.getFile(mediaName);

			int fileSize = (int) (file.getSize()/(1024*1024));
			if(fileSize > 500){
				map.put("error", "压缩问卷大小不能超过500MB");
				return map;
			}

			List<String> list = SFTPUtil.readM3u8(file);
			if(list.isEmpty()){
				map.put("error", "压缩文件里需要有m3u8格式的文件");
				return map;
			}
			if(list.size()>1){
				map.put("error", "压缩文件只需要一个m3u8格式的文件");
				return map;
			}
			String m3u8Name = list.get(0);


			long time = System.currentTimeMillis();
			map = SFTPUtil.uploadMedia(path, file);
			logger.info(" 上传文件花费秒： " + (System.currentTimeMillis() - time) / 1000);

			map.put("fileName", file.getOriginalFilename());
			/*解压ZIP*/
			String zipDirectory = map.get("storeName").replace(".zip","");
			SFTPUtil.unzip(zipDirectory, path);
			//返回m3u8格式文件的路径
			String filePath = "/images/"+path+"/"+zipDirectory+"/"+m3u8Name;
			map.put("filePath", filePath);
			return map;
		}catch (Exception e){
			map.put("error", "系统异常");
			return map;
		}
	}


}
