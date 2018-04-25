package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.AdminLogRq;
import com.abc.soa.response.system.AdminLogRs;
import com.abc.soa.response.system.bo.AdminLogBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 2017-05-24.
 * 数据字典管理1
 */
@Controller
@RequestMapping(value = "/system/adminlog")
public class AdminLogController extends BaseController {

    /**
     * 跳转至数据字典管理列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list.php")
    public String ShippingList(AdminLogRq adminLogRq, HttpServletRequest request, Model model) {
        AdminLogRs adminLogRs = SoaConnectionFactory.get(request, ConstantsUri.ADMINLOG_LIST, adminLogRq, AdminLogRs.class);
        List<AdminLogBO> list = new ArrayList<>();
        List<AdminLogBO> dataList = adminLogRs.getDataList();
        if(dataList.size()>0){
            for(int i =0;i<dataList.size();i++){
                AdminLogBO adlog = dataList.get(i);
                if(adlog.getBusinessUri().length()>40){
                    adlog.setBusinessUriStr(adlog.getBusinessUri().substring(0, 40) + "...");
                }
                else {
                    adlog.setBusinessUriStr(adlog.getBusinessUri());
                }
                if(adlog.getBusinessData().length()>100){
                    adlog.setBusinessDataStr(adlog.getBusinessData().substring(0, 100) + "...");
                }
                else {
                    adlog.setBusinessDataStr(adlog.getBusinessData());
                }
                list.add(adlog);
            }
        }
        model.addAttribute("adminLogRs", list);
        adminLogRq.setTotalItems(adminLogRs.getTotal());
        adminLogRq.calculate();
        adminLogRq.setTotalPage((int) Math.ceil((double) adminLogRq.getTotalItems() / (double) adminLogRq.getSize()));
        model.addAttribute("pagination", adminLogRq);
        model.addAttribute("BaseRq", adminLogRq);
        return "system/adminlog/list";
    }

}
