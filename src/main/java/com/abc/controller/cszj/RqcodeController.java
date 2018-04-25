package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.RqcodeRq;
import com.abc.soa.response.cszj.RqcodeRs;
import com.abc.soa.response.cszj.bo.RqcodeBO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/wxqrcode")
public class RqcodeController {

    protected static Logger LOGGER = LoggerFactory.getLogger(RqcodeController.class);

    @RequestMapping("/list.php")
    public String rqcodeList(RqcodeRq rqcodeRq, HttpServletRequest request, Model model) {
        RqcodeRs rqcodeRs = SoaConnectionFactory.get(request, ConstantsUri.QRCODE_LIST, rqcodeRq, RqcodeRs.class);
        model.addAttribute("rq",rqcodeRq);
        model.addAttribute("rqcodeRs", rqcodeRs.getDataList());
        rqcodeRq.setTotalItems(rqcodeRs.getTotal());
        rqcodeRq.calculate();
        model.addAttribute("pagination", rqcodeRq);
        return "cszj/rqcode/list";
    }
 
    /**
     * 积分规则新增，修改
     *
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/rqcodeEdit.php")
    public String rqcodeEdit( @RequestParam(required = false) String id, HttpServletRequest request, Model model) {

        if (!StringUtils.isEmpty(id)) {
            RqcodeRs rqcodeRs = SoaConnectionFactory.getRestful(request, ConstantsUri.QRCODE_INFO, null, RqcodeRs.class, id);

            model.addAttribute("rqcode", rqcodeRs.getData());

        }

        return "cszj/rqcode/form_edit";
    }



    /**
     * 修改和添加
     */
    @PostMapping("/rqcode_save.php")
    public @ResponseBody  BaseResponse  rqcodeSave(@RequestBody RqcodeBO obj, HttpServletRequest request) {
        LOGGER.info("rqcodeSave  {}", obj.toString());
        String id = obj.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.QRCODE_LIST, obj, RqcodeRs.class);

        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.QRCODE_INFO, obj, RqcodeRs.class, id);
        }
        return returnObj;
    }

    /**
     * 删除
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/rqcodeDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.QRCODE_INFO, null, BaseResponse.class, id);
    }

}
