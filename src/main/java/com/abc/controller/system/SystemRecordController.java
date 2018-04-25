package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.SystemRecordRq;
import com.abc.soa.request.system.bo.SystemRecord;
import com.abc.soa.response.soa.AppRs;
import com.abc.soa.response.soa.bo.Api;
import com.abc.soa.response.soa.bo.App;
import com.abc.soa.response.soa.bo.AppSetting;
import com.abc.soa.response.system.SystemRecordRs;
import com.abc.soa.response.system.bo.SystemRecordBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/system/record")
public class SystemRecordController {

    protected static Logger log = LoggerFactory.getLogger(SystemRecordController.class);

    @RequestMapping("/systemRecord.php")
    public String systemRecordList(SystemRecordRq systemRecordRq, HttpServletRequest request, Model model) {
        // recordLogRq.setSize(0);
        if(StringUtils.isEmpty(systemRecordRq.getYyyyMMdd())){
            systemRecordRq.setYyyyMMdd(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        String yyyyMMdd = systemRecordRq.getYyyyMMdd();
        systemRecordRq.setYyyyMMdd(systemRecordRq.getYyyyMMdd().replace("-", ""));
        SystemRecordRs systemRecordRs = SoaConnectionFactory.get(request, ConstantsUri.SYSTEMRECORD_LIST, systemRecordRq, SystemRecordRs.class);
    if(systemRecordRs.getDataList() != null){
    for (SystemRecordBO obj: systemRecordRs.getDataList()         ) {
        if(obj.getStayLong() != null && obj.getStayLong()> 0){
            obj.setStayLong(obj.getStayLong() /1000);
        }
    }
}

        model.addAttribute("listRs", systemRecordRs.getDataList());
        systemRecordRq.setTotalItems(systemRecordRs.getTotal());
        systemRecordRq.calculate();
        systemRecordRq.setTotalPage((int) Math.ceil((double) systemRecordRq.getTotalItems() / (double) systemRecordRq.getSize()));
        systemRecordRq.setYyyyMMdd(yyyyMMdd);;
        model.addAttribute("pagination", systemRecordRq);
        model.addAttribute("BaseRq", systemRecordRq);
        return "system/record/SystemRecord_list";
    }
    @RequestMapping("/api/save.php")
    public @ResponseBody BaseResponse saveApi(  @RequestParam(required = false) String name, @RequestParam(required = false) String method,HttpServletRequest request){
        Api app = new Api();
        App appXt = null;
//        request.getHeader("Access-Token");
        AppRs appRs = SoaConnectionFactory.get(request,
                ConstantsUri.APP_LIST, null, AppRs.class);
        List<App> listApp = appRs.getDataList();
        for (App app1:listApp             ) {
            if (app1.getNickname().equals("运营管理系统"))
            {
                appXt= app1;
            }
        }
        ConstantsUri cu =  ConstantsUri.valueOf(name);
if(method==null){
    method = "GET";
}
        method= method.toUpperCase();
        app.setName(cu.describe);
String uri = cu.uri;
Integer ii =  uri.indexOf("/",2);

        app.setUri(uri.substring(ii));
        app.setStatus(true);
        app.setVersion("1");
        app.setMethod(method);
        app.setDictId("37f0f76a-3175-458d-9b9b-52cfa7a55892");
        app.setAuthentication(false);
        AppRs br1=  SoaConnectionFactory.post(request, ConstantsUri.API_ADD, app, AppRs.class);
        if(!br1.getCode().equals("2000")){
            throw new RuntimeException("错误");
        }
        AppSetting appSetting = new AppSetting();
        appSetting.setAppId(appXt.getId());
        appSetting.setApiId(br1.getData().getId());
        appSetting.setStatus(true);
        return SoaConnectionFactory.post(request, ConstantsUri.APPSET_ADD, appSetting, BaseResponse.class, appSetting.getAppId());


    }

    @RequestMapping("/systemRecordEdit.php")
    public String systemRecordEdit( @RequestParam(required = false) String id, @RequestParam(required = false) String yyyyMMdd,HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            SystemRecord systemRecord = new SystemRecord();
            if (!StringUtils.isEmpty(yyyyMMdd)) {
                systemRecord.setYyyyMMdd(yyyyMMdd.replace("-", ""));
            }
            else{
                systemRecord.setYyyyMMdd((new SimpleDateFormat("yyyy-MM-dd").format(new Date())).replace("-", ""));
            }
            SystemRecordRs systemRecordRs = SoaConnectionFactory.get(request, ConstantsUri.SYSTEMRECORD_INFO, systemRecord, SystemRecordRs.class, id);
            SystemRecordBO obj = systemRecordRs.getData();
            if(obj.getStayLong() != null && obj.getStayLong()> 0){
                obj.setStayLong(obj.getStayLong() /1000);
            }
            model.addAttribute("obj", systemRecordRs.getData());

        }
        return "system/record/SystemRecord_edit";
    }  

    @PostMapping("/systemRecordSave.php")
    public @ResponseBody   BaseResponse systemRecordSave(@RequestBody SystemRecordBO systemRecordBO, HttpServletRequest request) {
        String id = systemRecordBO.getId();
        BaseResponse returnObj = null;
        if(id==null || id.isEmpty()){
            //新增
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.SYSTEMRECORD_LIST , systemRecordBO, SystemRecordRs.class);

        }else{
              returnObj = SoaConnectionFactory.put(request, ConstantsUri.SYSTEMRECORD_INFO, systemRecordBO, SystemRecordRs.class, id);

        }

        return returnObj;

    }
      
    /**
     * 删除
     *
     */
    @RequestMapping("/systemRecordDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(@RequestParam(required = true ) String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.SYSTEMRECORD_INFO, null, BaseResponse.class, id);
    }
   
}
