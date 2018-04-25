package com.abc.controller;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.*;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
import com.abc.soa.request.system.FriendlinkCriteria;
import com.abc.soa.response.cms.file.FjBo;
import com.abc.soa.response.cms.file.FjListBo;
import com.abc.soa.response.system.Friendlink;
import com.abc.soa.response.system.bo.FriendlinkDetail;
import com.abc.soa.response.system.bo.FriendlinkListBo;
import com.abc.soa.response.system.bo.UserBO;
import com.abc.soa.response.system.bo.commentBo.CommentFileListBo;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017-06-06.
 */
@Controller
@RequestMapping(value = "/cms/friendlink")
public class FriendlinkController extends BaseController {

    /**
     * 友情链接列表查询
     *
     * @param pagerSpec
     * @param request
     * @return
     */
    @RequestMapping(value = "/list.php", method = RequestMethod.GET)
    public ModelAndView list(BasePaginationCriteria basePaginationCriteria, PagerSpec pagerSpec, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/friendlink/list");
        FriendlinkListBo links = SoaConnectionFactory.get(request, ConstantsUri.FRIENDLINK, basePaginationCriteria, FriendlinkListBo.class);
        for (int i = 0; i < links.getDataList().size(); i++) {
            String logoPath = links.getDataList().get(i).getLogoPath();
            if(logoPath!=null&&!"".equals(logoPath)){
                links.getDataList().get(i).setLogoPath(SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context") + logoPath);
            }else{
                links.getDataList().get(i).setLogoPath("");
            }
        }
        basePaginationCriteria.setTotalItems((long)links.getTotal());
        basePaginationCriteria.setTotalPage((int) Math.ceil((double) basePaginationCriteria.getTotalItems() / (double) basePaginationCriteria.getSize()));
        mav.addObject("links", links);
        mav.addObject("pagination", basePaginationCriteria);
        return mav;
    }

    /**
     * 友情链接种类新增页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAddSortPage.php", method = RequestMethod.GET)
    public ModelAndView toAddSortPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/friendlink/form_sort");
        return mav;
    }

    /**
     * 友情链接新增页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add.php", method = RequestMethod.GET)
    public ModelAndView toAddPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/friendlink/form_add");
        return mav;
    }

    /**
     * 友情链接修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/friendlink/form_edit");
        FriendlinkDetail result = SoaConnectionFactory.getRestful(request, ConstantsUri.FRIENDLINK_ONE, null, FriendlinkDetail.class, id);
        String logoPath = result.getData().getLogoPath();
        if(logoPath!=null&&!"".equals(logoPath)){
            result.getData().setLogoPath(SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context") + logoPath);
        }else{
            result.getData().setLogoPath("");
        }
        mav.addObject("link", result.getData());
        return mav;
    }

    /**
     * 友情链接保存操作
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/save.php", method = RequestMethod.POST)
    public ModelAndView add(MultipartHttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        Friendlink friendlink = new Friendlink();
        if (request.getParameter("friendlinkId") != null) {
            friendlink.setFriendlinkId(request.getParameter("friendlinkId"));
        }
        friendlink.setSiteName(request.getParameter("siteName"));
        friendlink.setDomain(request.getParameter("domain"));
        friendlink.setEmail(request.getParameter("email"));
        friendlink.setDescription(request.getParameter("description"));
        if (!CommonUtils.nullOrBlank(request.getParameter("priority"))) {
            friendlink.setPriority(Integer.parseInt(request.getParameter("priority")));
        }
        if (!CommonUtils.nullOrBlank(request.getParameter("views"))) {
            friendlink.setViews(Integer.parseInt(request.getParameter("views")));
        }
        friendlink.setIsEnabled(request.getParameter("isEnabled"));
        friendlink.setFriendlinkctgId(request.getParameter("friendlinkctgId"));
        //图片上传开始
        MultipartFile file=request.getFile("FILE01");
        Friendlink result = null;
        if(file!=null&&file.getSize()>0){
            FjDto fjDto = new FjDto();
            fjDto.setFileName(file.getOriginalFilename());
            CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
            try {
                byte[] img = file.getBytes();
                if(file.getSize()>1024*200){
                    BaseResponse br = new BaseResponse("300", "文件大小不能大于200k");
                    mav.addObject("result",br);
                    return mav;
                }
                if (getFileTypeByStream(img) == null) {
                    BaseResponse br = new BaseResponse("300", "上传文件类型错误!");
                    mav.addObject("result",br);
                    return mav;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
//                fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
                fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
                UserBO userBO = (UserBO) request.getSession().getAttribute("currentUser");
                cmsFileUploadDto.setDirectory(userBO.getId());
                cmsFileUploadDto.getFjBo().add(fjDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileListDto fileListDto = SoaConnectionFactory.post(request, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
            if (fileListDto != null) {
                if (fileListDto.getDataList().size() > 0) {
                    friendlink.setLogoPath(fileListDto.getDataList().get(0).getFilePath());
                }
            }
        }
        if (CommonUtils.nullOrBlank(friendlink.getFriendlinkId())) {
            result = SoaConnectionFactory.post(request, ConstantsUri.FRIENDLINK, friendlink, Friendlink.class);
        } else {
            result = SoaConnectionFactory.put(request, ConstantsUri.FRIENDLINK_ONE, friendlink, Friendlink.class, friendlink.getFriendlinkId());
        }
        mav.addObject("result", result);
        return mav;
    }

    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    public String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        getPicFileType();
        System.out.println(filetypeHex);
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP
                .entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue().toUpperCase();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp","424D");
    }

    /**
     * 友情链接删除操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView del(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        result=SoaConnectionFactory.delete(request, ConstantsUri.FRIENDLINK_ONE, null, BaseResponse.class, id);
        mav.addObject("result", result);
        return mav;
    }


    /**
     * 友情链接批量删除操作
     *
     * @param ids 多个id编号用","隔开
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchDel.php", method = RequestMethod.POST)
    public ModelAndView batchDel(@RequestParam(value = "ids") String ids, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            if(id.length>0){
                for(String idcode : id){
                    if(idcode!=null&&!"".equals(idcode))
                        result=SoaConnectionFactory.delete(request, ConstantsUri.FRIENDLINK_ONE, null, BaseResponse.class, idcode);
                }
            }
        }
        mav.addObject("result", result);
        return mav;
    }

}
