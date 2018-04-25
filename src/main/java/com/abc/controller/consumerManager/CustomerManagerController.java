package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.PagerSpec;
import com.abc.common.util.PagerUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.consumer.CustomerManagerListRs;
import com.abc.soa.response.consumer.CustomerManagerRs;
import com.abc.soa.response.consumer.bo.CustomerManagerBo;
import com.abc.soa.response.system.bo.OrgListBO;
import com.abc.soa.response.system.bo.UserBO;
import com.abc.soa.response.system.bo.UserListBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author liuQi
 * @Date 2017/10/9 14:30
 * 客户经理管理
 */
@Controller
public class CustomerManagerController extends BaseController{

    /* 查询客户经理 */
    @RequiresPermissions("consumerManager:customerManager")
    @GetMapping("/consumerManager/customerManager/list.php")
    public String list(PagerSpec pagerSpec, HttpServletRequest request,Model model){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page",String.valueOf(pagerSpec.getCurrentPage()));
        map.put("size",String.valueOf(pagerSpec.getPerPageNum()));
        CustomerManagerListRs managers = SoaConnectionFactory.get(request, ConstantsUri.CUSTOMER_MANAGER_LIST, map, CustomerManagerListRs.class);
        model.addAttribute("managers",managers.getDataList());
        pagerSpec.setTotalItems(managers.getTotal());
        PagerUtil.calculatePagerSpec(pagerSpec);
        model.addAttribute("pagerSpec", pagerSpec);
        model.addAttribute("pageHtml", PagerUtil.pager(pagerSpec));
        model.addAttribute("currLink", pagerSpec.getLink().replace("[:page]",String.valueOf(pagerSpec.getCurrentPage())));
        return "consumer/customerManager/list";
    }

    /* 新增 GET */
    @RequiresPermissions("consumerManager:customerManager")
    @GetMapping("/consumerManager/customerManager/edit.php")
    public String add(HttpServletRequest request,Model model, @RequestParam(value = "id", defaultValue = "")String id,
                      @RequestParam(value = "currLink", defaultValue = "")String currLink){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/consumerManager/customerManager/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));
        if(!CommonUtils.nullOrBlank(id)){
            CustomerManagerRs managers = SoaConnectionFactory.get(request, ConstantsUri.CUSTOMER_MANAGER_VIEW, null, CustomerManagerRs.class, id);
            model.addAttribute("manager", managers.getData());
        }
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        OrgListBO orgAll = SoaConnectionFactory.get(request, ConstantsUri.ORG, map, OrgListBO.class);
        model.addAttribute("orgAll",orgAll.getDataList());
        UserListBO users = SoaConnectionFactory.get(request, ConstantsUri.OPERATOR, map, UserListBO.class);
        model.addAttribute("users",users.getDataList());
        return "consumer/customerManager/form";
    }

    /* 新增 GET */
    @RequiresPermissions("consumerManager:customerManager")
    @GetMapping("/consumerManager/customerManager/view.php")
    public String view(HttpServletRequest request,Model model, @RequestParam(value = "id") String id){
        model.addAttribute("imgUrl", SpringCtxHolder.getProperty("picdomain"));

        CustomerManagerRs managers = SoaConnectionFactory.get(request, ConstantsUri.CUSTOMER_MANAGER_VIEW, null, CustomerManagerRs.class, id);
        model.addAttribute("manager", managers.getData());

        return "consumer/customerManager/view";
    }


    /* 新增修改客户经理 */
    @RequiresPermissions("consumerManager:customerManager")
    @PostMapping(value = "/consumerManager/customerManager/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, UserBO user, @RequestBody CustomerManagerBo manger) throws IOException {
        BaseResponse rs = null;
        if(CommonUtils.nullOrBlank(manger.getId())){
            manger.setCreateUserId(user.getId());
            rs = SoaConnectionFactory.post(request, ConstantsUri.CUSTOMER_MANAGER_ADD, manger, CustomerManagerRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.CUSTOMER_MANAGER_MODIFY, manger, BaseResponse.class, manger.getId());
        }
        return rs;
    }

    /* 删除客户经理 */
    @RequiresPermissions("consumerManager:customerManager")
    @PostMapping("/consumerManager/customerManager/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id, HttpServletRequest request){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.CUSTOMER_MANAGER_DELETE, null, BaseResponse.class, id);
    }

    /* 停用启用 */
    @RequiresPermissions("consumerManager:customerManager")
    @PostMapping("/consumerManager/customerManager/status/{id}/{status}.php")
    public @ResponseBody BaseResponse status(@PathVariable(value = "id") String id,
                                             @PathVariable(value = "status") String status, HttpServletRequest request){
        CustomerManagerBo manger = new CustomerManagerBo();
        manger.setId(id);
        manger.setStatus(Integer.valueOf(status));
        return SoaConnectionFactory.put(request, ConstantsUri.CUSTOMER_MANAGER_MODIFY, manger, BaseResponse.class, id);
    }

}
