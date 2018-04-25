package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.businessMsg.Message;
import com.abc.soa.response.cms.businessMsg.MessageSendBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liuqi
 * @Date 2017/8/14 16:40
 * 业务消息
 */
@Controller
public class BusinessMsgController {

    /* 新增业务消息 */
    @PostMapping("/cms/businessMsg/add.php")
    public @ResponseBody BaseResponse add(HttpServletRequest request, @RequestBody Message msg){
        return SoaConnectionFactory.post(request, ConstantsUri.BUSINESS_MSG, msg, BaseResponse.class);
    }

    /* 新增业务消息 */
    @PostMapping("/cms/businessMsg/send.php")
    public @ResponseBody BaseResponse send(HttpServletRequest request, @RequestBody MessageSendBo msg){
        return SoaConnectionFactory.post(request, ConstantsUri.BUSINESS_MSG_SEND, msg, BaseResponse.class);
    }

    /* 新增业务消息 */
    @PostMapping("/cms/businessMsg/v2/send.php")
    public @ResponseBody BaseResponse sendV2(HttpServletRequest request, @RequestBody MessageSendBo msg){
        return SoaConnectionFactory.post(request, ConstantsUri.BUSINESS_MSG_SENDV2, msg, BaseResponse.class);
    }

}
