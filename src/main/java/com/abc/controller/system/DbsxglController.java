package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.OrderRq;
import com.abc.soa.response.financed.OrderRs;
import com.abc.soa.response.financed.bo.Order;
import com.abc.soa.response.system.DbsxRs;
import com.abc.soa.response.system.OrderCountRs;
import com.abc.soa.response.system.bo.OrderCountBO;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 接口访问日志
 * @author zhushuai 2017-10-18
 *
 */
@Controller
public class DbsxglController  extends BaseController {
	 /**
	  * APP接口访问总数
	  * @param
	  * @param request
	  * @param model
	  * @return
	  */
	 @RequestMapping("/dbsxgl/dbsxCounts.php")
     public String counts(HttpServletRequest request, Model model){
		 DbsxRs dbsxRs = SoaConnectionFactory.get(request, ConstantsUri.DBSX_TOTAL, null, DbsxRs.class);
		 OrderCountRs orderCountRs = SoaConnectionFactory.get(request, ConstantsUri.ORDERCOUNT_TOTAL, null, OrderCountRs.class);
         model.addAttribute("dbsxRs", dbsxRs.getDataList());
		 model.addAttribute("orderCountRs", orderCountRs.getDataList());

		 DbsxRs dbsxRs1 = SoaConnectionFactory.getRestful(request, ConstantsUri.BANGBANG_TODOLIST, null, DbsxRs.class);
		 model.addAttribute("dbsxRs1", dbsxRs1.getDataList());

		 return "system/dbsxgl/dbsxCounts";
     }

	@RequestMapping("/dbsxgl/listJson.php")
	public @ResponseBody OrderCountBO listJson(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		OrderCountRs orderCountRs = SoaConnectionFactory.get(request, ConstantsUri.ORDERCOUNT_TOTAL, null, OrderCountRs.class);
		String[] tjday = new String[5];
		String[] name = {"付款中订单数","付款成功订单数","订单完成订单数","订单作废订单数","已退款订单数"};
		for (int i = 0; i < 5; i++) {
			boolean isContain = false;
			if(orderCountRs.getDataList()!=null){
				String[] arr = new String[2];
				arr[0] = name[i];
				if(i==0){
					arr[1] = orderCountRs.getDataList().getorderStatus3();
				}
				else if(i==1){
					arr[1] = orderCountRs.getDataList().getorderStatus4();
				}
				else if(i==2){
					arr[1] = orderCountRs.getDataList().getorderStatus6();
				}
				else if(i==3){
					arr[1] = orderCountRs.getDataList().getorderStatus7();
				}
				else if(i==4){
					arr[1] = orderCountRs.getDataList().getorderStatus9();
				}
				tjday[i] = Arrays.toString(arr);
				isContain = true;
			}

			if (!isContain) {
				String[] arr = new String[2];
				arr[0] = name[i];
				arr[1] = "0";
				tjday[i] = Arrays.toString(arr);
			}
		}
		mav.addObject("tjday", Arrays.toString(tjday));
		mav.addObject("orderStatus", orderCountRs.getDataList().getorderStatus());
		return  orderCountRs.getDataList();
	}

	/**
	 * 订单列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/dbsxgl/orderList.php")
	public String getOrderList(OrderRq re, String doType,HttpServletRequest request, Model model) {
		OrderRs orderrs = new OrderRs();
		List<Order> lsit = new ArrayList<Order>();
		if (!"0".equals(doType)) {//待处理订单
			re.setOrderStatus(doType);
		}
		re.setStartTime(DateUtil.getYear()+"-"+DateUtil.getMonth()+"-01");
		re.setEndTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		orderrs = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LIST, re, OrderRs.class);
		lsit=JSON.parseArray(orderrs.getDataList(),Order.class);
		model.addAttribute("orderrs", lsit);
		re.setTotalItems(orderrs.getTotal());
		re.setTotalPage((int) Math.ceil((double) re.getTotalItems() / (double) re.getSize()));
		re.calculate();
		model.addAttribute("BaseRq", re);
		model.addAttribute("orderStatus", getDictBOList(request, "orderStatus"));
		model.addAttribute("doType", doType);
		return "system/dbsxgl/orderlist";
	}
}
