package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.FileOperateUtil;
import com.abc.dto.cms.site.SiteManageDto;
import com.abc.dto.cms.site.SiteManageListDto;
import com.abc.dto.cms.site.SiteManageQueryWrapper;
import com.abc.service.TplManager;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by relic5 on 2017/5/25.
 */
@Controller
public class SiteManageController {

    protected static Logger _log = LoggerFactory.getLogger(SiteManageController.class);

    @Autowired
    private TplManager tplManager;

    @GetMapping("/content/site/sitelist.php")
    public String getListPage(HttpServletRequest request,HttpSession session,Model model,BasePaginationCriteria basePaginationCriteria){

        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, basePaginationCriteria, SiteManageListDto.class);

        basePaginationCriteria.setTotalItems(Long.parseLong(siteManageListDto.getTotal()));

        basePaginationCriteria.setTotalPage((int) Math.ceil((double) basePaginationCriteria.getTotalItems() / (double) basePaginationCriteria.getSize()));

        model.addAttribute("cmsSiteList", siteManageListDto.getDataList());
        model.addAttribute("pagination",basePaginationCriteria);
        return "cms/site/siteManage";
    }

    @GetMapping("/content/site/addPage.php")
    public String addPage(String siteId,HttpServletRequest request, Model model){

        if(!StringUtils.isEmpty(siteId)){
            SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);
            SiteManageDto siteManageDto = siteManageQueryWrapper.getData();
            model.addAttribute("cmsSite",siteManageDto);
        }else{
            model.addAttribute("cmsSite",new SiteManageDto());
        }

        return "cms/site/siteManageAdd";
    }

    @PostMapping("/content/site/insertOrUpdate.php")
    @ResponseBody
    public SiteManageQueryWrapper insertOrUpdate(HttpServletRequest request,SiteManageDto siteManageDto){

//        MultipartFile multipartFile = request.getFile("siteLogo_ipt");
//
//        try {
//            siteManageDto.setContent(FileOperateUtil.fileBytesToList(multipartFile.getBytes()));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        SiteManageDto addResp = null;

        SiteManageQueryWrapper siteResponse = null;

        if(StringUtils.isEmpty(siteManageDto.getSiteId())){
            siteResponse = SoaConnectionFactory.post(request, ConstantsUri.CMS_SITE, siteManageDto, SiteManageQueryWrapper.class);
            if(siteResponse.isSuccess()) {
                String sitePath = siteManageDto.getSitePath();
                String siteId = siteResponse.getData().getSiteId();
                String siteName = siteManageDto.getSiteName();
                tplManager.createSiteTplDir(request, siteId, sitePath, siteName);
            }
        }else{
            siteResponse = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_SITE_RESTFUL, siteManageDto, SiteManageQueryWrapper.class, siteManageDto.getSiteId());
            String siteId = siteManageDto.getSiteId();
            String siteName = siteManageDto.getSiteName();
            tplManager.updateSiteTplDirName(request, siteId,  siteName);
        }
        FileOperateUtil.mkDir(siteManageDto.getSitePath()+"/template"); //创建站点目录

        return siteResponse;
//        return "redirect:/content/site/sitelist.php";
    }

    @GetMapping("/content/site/activeOrInactiveVote.php")
    @ResponseBody
    public BaseResponse activeOrInactiveVote(HttpServletRequest request,String siteId){

        BaseResponse result = null;

        if(StringUtils.isEmpty(siteId)){
            result = new BaseResponse("-1","请选择启用/停用项目");
            return result;
        }

        SiteManageQueryWrapper siteManageQueryWrapper = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_SITE_RESTFUL, null, SiteManageQueryWrapper.class, siteId);

        String status = siteManageQueryWrapper.getData().getSiteStatus();
        if("1".equals(status)){
            siteManageQueryWrapper.getData().setSiteStatus("0");
        }else{
            siteManageQueryWrapper.getData().setSiteStatus("1");
        }

        result = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_SITE_RESTFUL, siteManageQueryWrapper.getData(), BaseResponse.class, siteId);

        return result;
    }

}
