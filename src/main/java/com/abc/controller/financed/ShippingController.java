package com.abc.controller.financed;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.ShippingRq;
import com.abc.soa.response.financed.ShippingRs;
import com.abc.soa.response.financed.bo.Shipping;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 配送方式
 * @author zhushuai 2017-6-23
 *
 */
@Controller
@RequestMapping("/financed")
public class ShippingController extends BaseController{
	/**
	 * 配送方式列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:shipping")
	@RequestMapping("/shippingList.php")
    public String ShippingList(ShippingRq shippingRq, HttpServletRequest request,Model model,HttpSession session){
		ShippingRs shippingRs=SoaConnectionFactory.get(request, ConstantsUri.SHIPPING_LIST, shippingRq, ShippingRs.class);
        model.addAttribute("shippingRs",shippingRs.getDataList());
        shippingRq.setTotalItems(shippingRs.getTotal());
        shippingRq.calculate();
        model.addAttribute("BaseRq", shippingRq);
		session.setAttribute("shippingRq", shippingRq);
		return "financed/shipping/list";  	
    }
	
	/**
	 * 配送方式新增，修改页面
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:shipping")
	@RequestMapping("/shippingEdit.php")
	public String editPointrule(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		if("2".equals(dpType)){
			ShippingRs shippingRs=SoaConnectionFactory.getRestful(request, ConstantsUri.SHIPPING_INFO, null, ShippingRs.class,id);
			model.addAttribute("pointRule", shippingRs.getData());
		}
		ShippingRq shippingRq = (ShippingRq) request.getSession().getAttribute("shippingRq");
		model.addAttribute("shippingRq", shippingRq);
		model.addAttribute("dpType", dpType);
		return "financed/shipping/edit";
	}
	
	
	/**
	 * 配送方式新增，修改保存
	 * @param shipping
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:shipping")
	@PostMapping("/shippingSave.php")
	public @ResponseBody BaseResponse logisticsSave(@RequestBody Shipping shipping,HttpServletRequest request){
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(shipping.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.SHIPPING_ADD, shipping, ShippingRs.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.SHIPPING_EDIT, shipping, ShippingRs.class,shipping.getId());
        }
		return rs;
	}
	
	/**
	 * 配送方式删除
	 * @param 
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:shipping")
	@RequestMapping("/shippingDel.php")
	public @ResponseBody BaseResponse delVipPrivilege(String id,HttpServletRequest request){		
		return SoaConnectionFactory.delete(request, ConstantsUri.SHIPPING_DEL, null, BaseResponse.class,id);		
	}

	/**
	 * 配送方式禁用
	 * @param
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:shipping")
	@RequestMapping("/shippingEnable.php")
    public @ResponseBody BaseResponse shippingEnable(ShippingRq shippingRq, HttpServletRequest request,Model model){
		return SoaConnectionFactory.put(request, ConstantsUri.SHIPPING_ENABLE, shippingRq, BaseResponse.class);	
    }
}
