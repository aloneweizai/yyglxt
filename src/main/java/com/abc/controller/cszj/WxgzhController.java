package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.FileOperateUtil;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.AdpageRq;
import com.abc.soa.response.cszj.GzhInfoRs;
import com.abc.soa.response.cszj.bo.GzhInfo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/gzhsz")
public class WxgzhController {

    protected static Logger LOGGER = LoggerFactory.getLogger(WxgzhController.class);

    @RequestMapping("/list.php")
    public String adpageList(  HttpServletRequest request, Model model) {
        GzhInfoRs gzhInfoRs = SoaConnectionFactory.get(request, ConstantsUri.GZH_LIST, null, GzhInfoRs.class);
        List<GzhInfo> list = gzhInfoRs.getDataList();
        if(list != null && list.size()>0){
            model.addAttribute("gzhInfo", list.get(0));
        }

        return "cszj/wxgzh/wxgzh";
    }

    /**
     * 修改
     */
    @PostMapping("/update_gzhsz.php")
    public @ResponseBody  BaseResponse  updateGzhsz(@RequestBody GzhInfo gzhInfo, HttpServletRequest request) {
        LOGGER.info("updateGzhsz  {}", gzhInfo.toString());
        String id = gzhInfo.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            LOGGER.error("updateGzhsz id错误");
            throw( new RuntimeException("updateGzhsz id错误"));
        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.MSGAUTO_GZH_update, gzhInfo, GzhInfoRs.class, id);
        }
        return returnObj;
    }
}
