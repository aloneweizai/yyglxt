package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.WxuserRq;
import com.abc.soa.response.cszj.WxuserRs;
import com.abc.soa.response.cszj.bo.WxuserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/wxuser")
public class WxuserController {

    protected static Logger _log = LoggerFactory.getLogger(WxuserController.class);

    @RequestMapping("/list.php")
    public String wxuserList(WxuserRq wxuserRq, HttpServletRequest request, Model model) {
        Map<String, String> map = new HashMap<String, String>();
        if (!"".equals(wxuserRq.getOpenid()) && (wxuserRq.getOpenid()) != null)
            map.put("openid", wxuserRq.getOpenid());
        if (!"".equals(wxuserRq.getNickname()) && (wxuserRq.getNickname()) != null)
            map.put("nickname", wxuserRq.getNickname());
        if (!"".equals(wxuserRq.getStartTime()) && (wxuserRq.getStartTime()) != null)
            map.put("startTime", wxuserRq.getStartTime());
        if (!"".equals(wxuserRq.getEndTime()) && (wxuserRq.getEndTime()) != null)
            map.put("endTime", wxuserRq.getEndTime());
        map.put("page", wxuserRq.getPage() + "");
        map.put("size", wxuserRq.getSize() + "");
        WxuserRs wxuserRs = SoaConnectionFactory.get(request, ConstantsUri.WXUSER_LIST, map, WxuserRs.class);
        model.addAttribute("wxuserList", wxuserRs.getDataList());
        wxuserRq.setTotalItems(wxuserRs.getTotal());
        wxuserRq.setTotalPage((int) Math.ceil((double) wxuserRq.getTotalItems() / (double) wxuserRq.getSize()));
        model.addAttribute("pagination", wxuserRq);
        return "cszj/wxuser/list";
    }

    //同步微信用户到本地数据库
    @RequestMapping("/synchro.php")
    public
    @ResponseBody
    BaseResponse synchroUser(WxuserBO wxuserBO, HttpServletRequest request) {
        return SoaConnectionFactory.post(request, ConstantsUri.WXUSER_SYNCHRO, wxuserBO, WxuserRs.class);
    }

    /**
     * 查看单个微信用户
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/selectone.php")
    public String selectone(HttpServletRequest request, Model model, @RequestParam(value = "openid", required = false) String openid) {
        if (!StringUtils.isEmpty(openid)) {
            WxuserRs wxuserRs = SoaConnectionFactory.getRestful(request, ConstantsUri.WXUSER_INFO, null, WxuserRs.class, openid);
            model.addAttribute("wxuser", wxuserRs.getData());
        }
        return "cszj/wxuser/form_view";
    }

    //从微信服务器同步单个用户信息
    @RequestMapping("/synchroone.php")
    public
    @ResponseBody
    BaseResponse synchroOne(WxuserBO wxuserBO, HttpServletRequest request, @RequestParam(value = "openid", required = false) String openid) {
        return SoaConnectionFactory.put(request, ConstantsUri.WXUSER_SYNCHRO_ONE, null, WxuserRs.class, openid);
    }
}
