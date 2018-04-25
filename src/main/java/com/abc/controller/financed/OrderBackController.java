package com.abc.controller.financed;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.OrderbackRq;
import com.abc.soa.response.financed.OrderbackRes;
/**
 * 退单（暂时不用）
 * @author zhushuai 2017-11-6
 *
 */
@Controller
public class OrderBackController extends BaseController {
	
	/**
	 * 退单列表
	 * @param backRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderback/applications.php")
	public String orderBackList(OrderbackRq backRq,HttpServletRequest request, Model model){
		
		
		OrderbackRes orderbackRes=SoaConnectionFactory.get(request, ConstantsUri.ORDER_BACK_LIST, backRq, OrderbackRes.class);
		model.addAttribute("invoiceRepoRqs", orderbackRes.getDataList());
		backRq.setTotalItems(orderbackRes.getTotal());
		backRq.calculate();
		
		model.addAttribute("obackstatus", getDictBOList(request, "obackstatus"));
		model.addAttribute("obackreason", getDictBOList(request, "obackreason"));
		
		model.addAttribute("BaseRq", backRq);
		return "/financed/orderback/list";
	}
	
	/**
	 * 退单审核
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderback/charge.php")
	public String charge(@RequestParam("id") String id,HttpServletRequest request, Model model){
		
		return "/financed/orderback/info";
	}

}
