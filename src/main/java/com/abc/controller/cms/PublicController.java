package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.dto.cms.pub.AskBOList;
import com.abc.dto.cms.pub.HotspotAskBOList;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import com.abc.soa.response.system.bo.DictListBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 前端公开接口 页面可以直接访问
 * Created by Administrator on 2017/7/26 0026.
 */
@Controller
//@RequestMapping("/cms/pub")
public class PublicController extends BaseController {

    /**
     * 热门文章
     * @param request
     * @return
     */
    @GetMapping("/cms/pub/hotContentList")
    public ModelAndView hotContentList(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers:", "x-requested-with,content-type");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("size", "4");
        parameter.put("page", "0");
        PublishSiteColumnContentListResponse contentListResponse = SoaConnectionFactory.get(request, ConstantsUri.CMS_CONTENT_HOT, parameter, PublishSiteColumnContentListResponse.class);
        mav.addObject("hotContentList", contentListResponse.getDataList());
        return mav;
    }

    /**
     * 热词
     * @param request
     * @return
     */
    @GetMapping("/cms/pub/hotLabel")
    public ModelAndView hotLabel(HttpServletRequest request, HttpServletResponse response   ){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers:","x-requested-with,content-type");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        DictListBO contentLabelList = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, "cms_content_label");
        mav.addObject("contentLabelList", contentLabelList.getDataList());
        return mav;
    }

    /**
     * 热点问题
     * @param request
     * @return
     */
    @GetMapping("/cms/pub/hotQuestion")
    public ModelAndView hotQuestion(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers:","x-requested-with,content-type");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        HotspotAskBOList hotspotAskBOList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTQUESTION, null, HotspotAskBOList.class);
        mav.addObject("hotspotAskBOList", hotspotAskBOList.getDataList());
        return mav;
    }


    /**
     * 帮帮热议
     * @param request
     * @return
     */
    @GetMapping("/cms/pub/hotDiscussion")
    public ModelAndView hotDiscussion(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers:","x-requested-with,content-type");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        AskBOList askBOList = SoaConnectionFactory.get(request, ConstantsUri.PUB_HOTDISCUSSION, null, AskBOList.class);
        mav.addObject("askBOList", askBOList.getDataList());
        return mav;
    }

}
