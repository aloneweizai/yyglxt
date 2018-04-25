package com.abc.controller.system;


import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.TaxCalendarRq;
import com.abc.soa.response.system.TaxCalendarRs;
import com.abc.soa.response.system.bo.TaxCalendar;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 办税日历
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/taxcalendar")
public class TaxCalendarController extends BaseController{

	/**
	 * 办税日历列表
	 * 
	 * @param taxCalendarRq
	 *            查询条件
	 * @param request
	 * @return
	 */
	@RequiresPermissions("system:taxcalendar")
	@RequestMapping("/list.php")
	public String list(TaxCalendarRq taxCalendarRq, HttpServletRequest request) {

		TaxCalendarRs calendarRs = SoaConnectionFactory.get(request, ConstantsUri.BSRL_LIST, taxCalendarRq,
				TaxCalendarRs.class);
		taxCalendarRq.setTotalItems(calendarRs.getTotal());
		taxCalendarRq.calculate();
		request.setAttribute("BaseRq", taxCalendarRq);
		request.setAttribute("taxCalendars", calendarRs.getDataList());
		return "system/taxcalendar/list";
	}

	/**
	 * 办税日历保存
	 * 
	 * @param taxCalendar
	 *            办税日历
	 * @param request
	 * @return
	 */
	@RequiresPermissions("system:taxcalendar")
	@RequestMapping("/save.php")
	public @ResponseBody TaxCalendarRs eidt(@RequestBody TaxCalendar taxCalendar, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(taxCalendar.getCalId())) {
			return SoaConnectionFactory.put(request, ConstantsUri.BSRL_EDIT, taxCalendar, TaxCalendarRs.class, new Object());
		}
		return SoaConnectionFactory.post(request, ConstantsUri.BSRL_ADD, taxCalendar, TaxCalendarRs.class);
	}
	
	/**
	 * 删除办税日历
	 * @param calId
	 * @param request
	 * @return
	 */
	@RequiresPermissions("system:taxcalendar")
	@RequestMapping("/del.php")
	public @ResponseBody TaxCalendarRs delete(@RequestParam String calId,HttpServletRequest request){
		return SoaConnectionFactory.deleteRestful(request, ConstantsUri.BSRL_DEL, null, TaxCalendarRs.class,calId);
	}
	

}
