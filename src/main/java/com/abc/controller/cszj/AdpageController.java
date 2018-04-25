package com.abc.controller.cszj;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.AdpageRq;
import com.abc.soa.response.cszj.AdpageRs;
import com.abc.soa.response.cszj.bo.AdpageBO;
import com.abc.soa.response.system.Friendlink;
import com.abc.soa.response.system.bo.UserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/adpage")
public class AdpageController {

    protected static Logger _log = LoggerFactory.getLogger(AdpageController.class);
    /**
     * 广告页列表接口
     *
     * @param adpageRq
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/list.php")
    public String adpageList(AdpageRq adpageRq, HttpServletRequest request, Model model,HttpSession session) {
        AdpageRs adpageRs = SoaConnectionFactory.get(request, ConstantsUri.ADPAGE_LIST, adpageRq, AdpageRs.class);
        for (int i = 0; i < adpageRs.getDataList().size(); i++) {
            String url = adpageRs.getDataList().get(i).getUrl();
            if (url != null && !"".equals(url)) {
                adpageRs.getDataList().get(i).setUrl(SpringCtxHolder.getProperty("picdomain") + "/images" + url);
            } else {
                adpageRs.getDataList().get(i).setUrl("");
            }
        }
        model.addAttribute("adpageRs", adpageRs.getDataList());
        adpageRq.setTotalItems(adpageRs.getTotal());
        adpageRq.calculate();
        model.addAttribute("BaseRq", adpageRq);
        model.addAttribute("pagination", adpageRq);
        model.addAttribute("referer", request.getHeader("Referer"));
        session.setAttribute("adpageRq", adpageRq);
        return "cszj/adpage/list";
    }

    /**
     * 跳转到广告页新增，修改页面
     *
     * @param
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/adpageEdit.php")
    public String editPointrule(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        model.addAttribute("referer", request.getHeader("Referer"));
        AdpageRq rq = (AdpageRq) request.getSession().getAttribute("adpageRq");
        model.addAttribute("adpageRq", rq);
        if (!StringUtils.isEmpty(id)) {
            AdpageRs adpageRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ADPAGE_INFO, null, AdpageRs.class, id);
            String url = adpageRs.getData().getUrl();
            if (url != null && !"".equals(url)) {
                adpageRs.getData().setUrl(SpringCtxHolder.getProperty("picdomain") + "/images" + url);
            } else {
                adpageRs.getData().setUrl("");
            }
            model.addAttribute("adpage", adpageRs.getData());
            return "cszj/adpage/form_edit";
        } else {
            return "cszj/adpage/form_add";
        }
    }

    /**
     * 跳转到广告页预览页面
     *
     * @param id
     * @param request
     * @param
     * @return
     */
    @GetMapping("/addlook.php")
    public String addlook(HttpServletRequest request, @RequestParam String id){
        AdpageRs adpageRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ADPAGE_INFO, null, AdpageRs.class, id);
        AdpageBO data = adpageRs.getData();
        data.setUrl(SpringCtxHolder.getProperty("picdomain") + "/images" + data.getUrl());
        request.setAttribute("adpage", data);
        return "cszj/adpage/addlook";
    }
    /**
     * 广告页新增，修改
     *
     * @param
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/adpageSave.php", method = RequestMethod.POST)
    public ModelAndView add(MultipartHttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        AdpageBO adpageBO = new AdpageBO();
        if (request.getParameter("adpageId") != null) {
            adpageBO.setId(request.getParameter("adpageId"));
        }
        adpageBO.setName(request.getParameter("name"));
        adpageBO.setLink(request.getParameter("link"));
        adpageBO.setStyle(request.getParameter("views"));

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟

        try {
            if(!StringUtils.isEmpty(request.getParameter("startTime")))
            adpageBO.setStartTime( sdf.parse(request.getParameter("startTime")) );
            if(!StringUtils.isEmpty(request.getParameter("endTime")))
            adpageBO.setEndTime(sdf.parse(request.getParameter("endTime")));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        adpageBO.setSort(Integer.parseInt(request.getParameter("sort")));
        if ("1".equals(request.getParameter("showName"))) {
            adpageBO.setShowName(true);
        } else {
            adpageBO.setShowName(false);
        }
        if ("1".equals(request.getParameter("status"))) {
            adpageBO.setStatus(true);
        } else {
            adpageBO.setStatus(false);
        }
        BaseResponse rs = null;
        //图片上传开始
        MultipartFile file = request.getFile("FILE01");
        Friendlink result = null;
        if (file != null && file.getSize() > 0) {
            FjDto fjDto = new FjDto();
            fjDto.setFileName(file.getOriginalFilename());
            CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
            try {
                byte[] img = file.getBytes();
                if (file.getSize() > 1024 * 1024) {
                    BaseResponse br = new BaseResponse("300", "文件大小不能大于1024k");
                    mav.addObject("result", br);
                    return mav;
                }
                if (getFileTypeByStream(img) == null) {
                    BaseResponse br = new BaseResponse("300", "上传文件类型错误!");
                    mav.addObject("result", br);
                    return mav;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
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
                    adpageBO.setUrl(fileListDto.getDataList().get(0).getFilePath());
                }
            }
        }
        if (CommonUtils.nullOrBlank(adpageBO.getId())) {
            rs = SoaConnectionFactory.post(request, ConstantsUri.ADPAGE_ADD, adpageBO, AdpageRs.class);
        } else {
            rs = SoaConnectionFactory.put(request, ConstantsUri.ADPAGE_EDIT, adpageBO, AdpageRs.class, adpageBO.getId());
        }
        mav.addObject("result", rs);
        return mav;
    }

    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    /**
     * 获得文件流
     *
     * @param
     * @param
     * @return
     */
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

    /**
     * 图片类型
     *
     * @param
     * @param
     * @return
     */
    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp", "424D");
    }

    /**
     * 广告页删除
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/adpageDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.ADPAGE_DEL, null, BaseResponse.class, id);
    }

    /**
     * 广告页启用
     *
     * @param adpage
     * @param request
     * @return
     */
    @RequestMapping("/adpageEnable.php")
    public
    @ResponseBody
    BaseResponse adpageEnable(AdpageBO adpage, HttpServletRequest request, Model model) {
        AdpageRs adpageRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ADPAGE_INFO, null, AdpageRs.class, adpage.getId());
        adpage.setStatus(true);
        adpage.setShowName(adpageRs.getData().getShowName());
        return SoaConnectionFactory.put(request, ConstantsUri.ADPAGE_EDIT, adpage, BaseResponse.class, adpage.getId());
    }

    /**
     * 停用
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/adpageDisable.php")
    public
    @ResponseBody
    BaseResponse adpageDisable(AdpageBO adpage, HttpServletRequest request, Model model) {
        AdpageRs adpageRs = SoaConnectionFactory.getRestful(request, ConstantsUri.ADPAGE_INFO, null, AdpageRs.class, adpage.getId());
        adpage.setStatus(false);
        adpage.setShowName(adpageRs.getData().getShowName());
        return SoaConnectionFactory.put(request, ConstantsUri.ADPAGE_EDIT, adpage, BaseResponse.class, adpage.getId());
    }
}
