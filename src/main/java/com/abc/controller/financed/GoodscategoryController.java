package com.abc.controller.financed;


import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.GoodscategoryRq;
import com.abc.soa.response.financed.GoodscategoryRs;
import com.abc.soa.response.financed.bo.Goodscategory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 商品分类
 * @author zhushuai 2017-6-26
 *
 */
@Controller
@RequestMapping("/goodscategory")
public class GoodscategoryController extends BaseController{
	/**
	 * 商品分类列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:goodscategory")
	@RequestMapping("/list.php")
    public String list(GoodscategoryRq rq, HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		return "financed/goodscategory/list";  	
    }

	/**
	 * 商品分类树
	 *
	 * @param rq 查询条件
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:goodscategory")
	@RequestMapping("/listJson.php")
    public @ResponseBody Goodscategory listJson(GoodscategoryRq rq, HttpServletRequest request,Model model){
		GoodscategoryRs logisticsRs=SoaConnectionFactory.get(request, ConstantsUri.GOODSCATEGORY_LIST, rq, GoodscategoryRs.class);
        return  logisticsRs.getData();
    }
	
	/**
	 * 跳转到新增，修改页面
	 * @param dpType 1：新增 2：修改
	 * @param  id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:goodscategory")
	@RequestMapping("/edit.php")
	public String edit(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		if("2".equals(dpType)){
			GoodscategoryRs logisticsRs=SoaConnectionFactory.getRestful(request, ConstantsUri.GOODSCATEGORY_INFO, null, GoodscategoryRs.class,id);
			model.addAttribute("pointRule", logisticsRs.getData());
		}
		model.addAttribute("dpType", dpType);
		model.addAttribute("referer", request.getHeader("Referer"));
		return "financed/goodscategory/edit";
	}
	
	
	/**
	 * 商品分类新增，修改保存
	 * @param logistics
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:goodscategory")
	@PostMapping("/save.php")
	public @ResponseBody BaseResponse save(@RequestBody Goodscategory logistics,HttpServletRequest request){
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(logistics.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.GOODSCATEGORY_ADD, logistics, GoodscategoryRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.GOODSCATEGORY_EDIT, logistics, GoodscategoryRs.class,logistics.getId());
        }
		return rs;
	}
	
	/**
	 * 商品分类删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:goodscategory")
	@RequestMapping("/del.php")
	public @ResponseBody BaseResponse delVipPrivilege(String id,HttpServletRequest request){		
		return SoaConnectionFactory.delete(request, ConstantsUri.GOODSCATEGORY_DEL, null, BaseResponse.class,id);		
	}
}
