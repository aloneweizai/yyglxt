package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.ConsumerRq;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.system.*;
import com.abc.soa.response.consumer.ConsumerRs;
import com.abc.soa.response.consumer.VipLevelRs;
import com.abc.soa.response.system.*;
import com.abc.soa.response.system.bo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 接口访问日志
 * @author zhushuai 2017-10-18
 *
 */
@Controller
@RequestMapping(value = "/usertrendcount")
public class UserTrendCountController  extends BaseController {
	/**
	 * 注册用户统计分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerusercount/list.php")
	public String selectlist(RegisterUserCountRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/registerusercount";
	}
	 /**
	  * 注册用户统计分析
	  * @param
	  * @param request
	  * @param model
	  * @return
	  */
	 @RequestMapping("/registerusercount.php")
     public String counts(RegisterUserCountRq rq,HttpServletRequest request, Model model){
		 model.addAttribute("BaseRq", rq);
		 return "system/usertrendcount/registerusercount";
     }

	@RequestMapping("/registerusercount/listJson.php")
	public ModelAndView listJson(RegisterUserCountRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserTrendCountRq userRq = new UserTrendCountRq();
		String type = rq.getType();
		List<UserTrendCountBO> list = new ArrayList<>();
		if(StringUtils.isEmpty(type)){
			type = "0";
		}
		if("0".equals(type)||"1".equals(type)||"2".equals(type)||"3".equals(type)||"4".equals(type)||"5".equals(type)){//时间段
			userRq.setStartTime(rq.getTimed().substring(0,10));
			userRq.setEndTime(rq.getTimed().substring(12,23));
		}
		else if("6".equals(type)){//对比时间
			userRq.setStartTime(rq.getTimed().substring(0, 10));
			userRq.setEndTime(rq.getTimed().substring(12, 23));
			UserTrendCountRs userTrendCountb = SoaConnectionFactory.get(request, ConstantsUri.REGISTERUSER_COUNT, userRq, UserTrendCountRs.class);
			List<UserTrendCountBO> userTrendCount = userTrendCountb.getDataList();
			if(!StringUtils.isEmpty(userTrendCount)){
				String[] tjday = new String[userTrendCount.size()];
				for (int i = 0; i < userTrendCount.size(); i++) {
					tjday[i] = userTrendCount.get(i).getCount().toString();
				}
				mav.addObject("tjday1", Arrays.toString(tjday));
			}
			for(UserTrendCountBO user:userTrendCount){
				list.add(user);
			}
			userRq.setStartTime(rq.getTimeb().substring(0, 10));
			userRq.setEndTime(rq.getTimeb().substring(12, 23));
		}
		UserTrendCountRs userTrendCountRs = SoaConnectionFactory.get(request, ConstantsUri.REGISTERUSER_COUNT, userRq, UserTrendCountRs.class);
		List<UserTrendCountBO> userTrend = userTrendCountRs.getDataList();
		for(UserTrendCountBO user:userTrend){
			list.add(user);
		}
		if("6".equals(type)){
			if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[userTrend.size()];
				for (int i = 0; i < userTrend.size(); i++) {
					tjday[i] = userTrend.get(i).getCount().toString();
				}
				mav.addObject("tjday", Arrays.toString(tjday));
				mav.addObject("day", userTrend.size());
			}
			else{
				String[] tjday = new String[0];
				mav.addObject("tjday", Arrays.toString(tjday));
			}
		}
		else{
			if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String[] arr = new String[2];
					arr[0] = "'"+list.get(i).getDays()+"'";
					arr[1] = list.get(i).getCount().toString();
					tjday[i] = Arrays.toString(arr);

				}
				mav.addObject("tjday", Arrays.toString(tjday));
			}
			else{
				String[] tjday = new String[0];
				mav.addObject("tjday", Arrays.toString(tjday));
			}
		}
		mav.addObject("userTrendCountRs", list);
		return mav;
	}

	/**
	 * 注册用户信息
	 *
	 * @param rq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerusercount/userlook.php")
	public String expLog(RegisterUserCountRq rq,
						 HttpServletRequest request, Model model) {
		ConsumerRq consumerRq = new ConsumerRq();
		if(!StringUtils.isEmpty(rq.getDays())){
			if(rq.getDays().length()>9){
				consumerRq.setStartDate(rq.getDays());
				consumerRq.setEndDate(rq.getDays());
			}
			else{
				consumerRq.setStartDate(rq.getDays()+"-01");
				consumerRq.setEndDate(rq.getDays()+"-"+DateUtil.getLastMonthDay(rq.getDays().substring(0,4),rq.getDays().substring(5,7)));
			}
		}
		ConsumerRs consumerRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_LIST, consumerRq, ConsumerRs.class);
		model.addAttribute("consumers", consumerRs.getDataList());
		rq.setTotalItems(consumerRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);

		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());
		return "system/usertrendcount/registeruser_look";
	}

	/**
	 * 用户流失率统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userlost/list.php")
	public String selectlist(UserLostRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userlost_list";
	}

	/**
	 * 用户流失率统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userlost.php")
	public String userlost(UserLostRq rq,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(rq.getYearTime())){
			rq.setYearTime(DateUtil.getYear() + "-" + DateUtil.getMonth());
		}
		if(StringUtils.isEmpty(rq.getMonths())){
			rq.setMonths("1");
		}
		UserLostRs userLostRs = SoaConnectionFactory.get(request, ConstantsUri.USERLOST_RATE, rq, UserLostRs.class);
		model.addAttribute("userLostRs", userLostRs.getData());
		rq.setTotalItems(userLostRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userlost_list";
	}

	/**
	 * 用户留存率统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userretention/list.php")
	public String query(UserTrendCountRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userretention_list";
	}

	/**
	 * 用户留存率统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userretention.php")
	public String userretention(UserTrendCountRq rq,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(rq.getStartTime())){
			rq.setStartTime(DateUtil.getYear() + "-" + DateUtil.getMonth());
		}
		if(StringUtils.isEmpty(rq.getEndTime())){
			rq.setEndTime(DateUtil.getYear() + "-" + DateUtil.getMonth());
		}
		UserRetentionCountRs userRetentionCountRs = SoaConnectionFactory.get(request, ConstantsUri.USERRETEN_RATE, rq, UserRetentionCountRs.class);
		List<UserRetainedRateListBO> dataList = userRetentionCountRs.getDataList();
		List<UserRetentionBO> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0;i<dataList.size();i++)  {
			UserRetainedRateListBO rate = dataList.get(i);
			UserRetentionBO bo = new UserRetentionBO();
			bo.setStartTime(DateUtil.getFirstDayOfMonth(rate.getDate(), "yyyy-MM-dd"));
			bo.setEndTime(sdf.format(rate.getDate()));
			List<UserRetainedRateBO> userRetainedRateBOList = rate.getUserRetainedRateBOList();
			bo.setUserCount(userRetainedRateBOList.get(0).getUserCount());
			if(0 != bo.getUserCount()){
				bo.setRate1(Double.parseDouble(userRetainedRateBOList.get(0).getRate()));
				bo.setRate2(Double.parseDouble(userRetainedRateBOList.get(1).getRate()));
				bo.setRate3(Double.parseDouble(userRetainedRateBOList.get(2).getRate()));
				bo.setRate4(Double.parseDouble(userRetainedRateBOList.get(3).getRate()));
				bo.setRate5(Double.parseDouble(userRetainedRateBOList.get(4).getRate()));
				bo.setRate6(Double.parseDouble(userRetainedRateBOList.get(5).getRate()));
				bo.setRate7(Double.parseDouble(userRetainedRateBOList.get(6).getRate()));
			}
			list.add(bo);
		}
		model.addAttribute("userretentionRs", list);
		rq.setTotalItems(userRetentionCountRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userretention_list";
	}

	/**
	 * 用户活跃度统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/useractive/list.php")
	public String selectlist(UserActiveCountRq rq,HttpServletRequest request, Model model){
		rq.setType("day");
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/useractive_list";
	}

	/**
	 * 用户活跃度统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/useractive.php")
	public String useractive(UserActiveCountRq rq,HttpServletRequest request, Model model){
		UserActiveRs userActiveRs = SoaConnectionFactory.get(request, ConstantsUri.USERACTIVE_RATE, null, UserActiveRs.class);
		UserActiveRq userActiveRq = new UserActiveRq();
		List<UserActiveDetailBO> dataList = new ArrayList<>();
		if(StringUtils.isEmpty(rq.getType())){
			userActiveRq.setType("day");
			userActiveRq.setStart(DateUtil.getCurrentTime("yyyy-MM-dd"));
			userActiveRq.setEnd(DateUtil.getCurrentTime("yyyy-MM-dd"));
			rq.setStartDay(DateUtil.getCurrentTime("yyyy-MM-dd"));
			rq.setEndDay(DateUtil.getCurrentTime("yyyy-MM-dd"));
			rq.setType("day");
			UserActiveDetailRs userActiveDetailRs = SoaConnectionFactory.get(request, ConstantsUri.USERACTIVEDETAIL_RATE, userActiveRq, UserActiveDetailRs.class);
			if(userActiveDetailRs.getDataList().size()>0){
				for(UserActiveDetailBO bo:userActiveDetailRs.getDataList()){
					bo.setDateStr(bo.getDate().substring(0,10));
					dataList.add(bo);
				}
			}
		}
		else if("day".equals(rq.getType())){
			userActiveRq.setType(rq.getType());
			userActiveRq.setStart(rq.getStartDay());
			userActiveRq.setEnd(rq.getEndDay());
			UserActiveDetailRs userActiveDetailRs = SoaConnectionFactory.get(request, ConstantsUri.USERACTIVEDETAIL_RATE, userActiveRq, UserActiveDetailRs.class);
			if(userActiveDetailRs.getDataList().size()>0){
				for(UserActiveDetailBO bo:userActiveDetailRs.getDataList()){
					bo.setDateStr(bo.getDate().substring(0,10));
					dataList.add(bo);
				}
			}
		}
		else if("month".equals(rq.getType())){
			userActiveRq.setType(rq.getType());
			userActiveRq.setStart(rq.getStartMon());
			userActiveRq.setEnd(rq.getEndMon());
			UserActiveDetailRs userActiveDetailRs = SoaConnectionFactory.get(request, ConstantsUri.USERACTIVEDETAIL_RATE, userActiveRq, UserActiveDetailRs.class);
			if(userActiveDetailRs.getDataList().size()>0){
				for(UserActiveDetailBO bo:userActiveDetailRs.getDataList()){
					bo.setDateStr(bo.getDate().substring(0,7));
					dataList.add(bo);
				}
			}
		}
		else if("year".equals(rq.getType())){
			userActiveRq.setType(rq.getType());
			userActiveRq.setStart(rq.getStartYear());
			userActiveRq.setEnd(rq.getEndYear());
			UserActiveDetailRs userActiveDetailRs = SoaConnectionFactory.get(request, ConstantsUri.USERACTIVEDETAIL_RATE, userActiveRq, UserActiveDetailRs.class);
			if(userActiveDetailRs.getDataList().size()>0){
				for(UserActiveDetailBO bo:userActiveDetailRs.getDataList()){
					bo.setDateStr(bo.getDate().substring(0,4));
					dataList.add(bo);
				}
			}
		}

		model.addAttribute("useractiveRs", userActiveRs.getDataList());
		model.addAttribute("userActiveDetailRs", dataList);
		rq.setTotalItems(userActiveRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/useractive_list";
	}

	/**
	 * 用户活跃度统计用户信息
	 *
	 * @param rq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/useractive/look.php")
	public String useractive(RegisterUserCountRq rq,
						 HttpServletRequest request, Model model) {
		ConsumerRq consumerRq = new ConsumerRq();
		if(!StringUtils.isEmpty(rq.getDays())){
			if(rq.getDays().length()>9){
				consumerRq.setStartDate(rq.getDays());
				consumerRq.setEndDate(rq.getDays());
			}
			else if(rq.getDays().length()>6&&rq.getDays().length()<9){
				consumerRq.setStartDate(rq.getDays()+"-01");
				consumerRq.setEndDate(rq.getDays()+"-"+DateUtil.getLastMonthDay(rq.getDays().substring(0,4),rq.getDays().substring(5,7)));
			}
			else{
				consumerRq.setStartDate(rq.getDays()+"-01-01");
				consumerRq.setEndDate(rq.getDays()+"-12-31");
			}

		}
		ConsumerRs consumerRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_LIST, consumerRq, ConsumerRs.class);
		model.addAttribute("consumers", consumerRs.getDataList());
		rq.setTotalItems(consumerRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);

		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());
		return "system/usertrendcount/useractive_look";
	}

	/**
	 * 用户活跃度统计用户信息
	 *
	 * @param rq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/useractive/info.php")
	public String useractive_info(UserActiveInfoRq rq,
							 HttpServletRequest request, Model model) {
		ConsumerRs consumerRs = SoaConnectionFactory.get(request,
				ConstantsUri.USERACTIVE_INFO, rq, ConsumerRs.class);
		model.addAttribute("consumers", consumerRs.getDataList());
		rq.setTotalItems(consumerRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());
		return "system/usertrendcount/useractive_detail";
	}
	/**
	 * 用户经验值等级统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userexplevel/list.php")
	public String selectlist(UserTrendCountRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userexplevel_list";
	}
	/**
	 * 用户经验值等级统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userexplevel.php")
	public String userexplevel(UserTrendCountRq rq,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(rq.getYear())) {
			rq.setYear(DateUtil.getYear());
		}
		UserExplevelRateRs userExplevelRateRs = SoaConnectionFactory.get(request, ConstantsUri.USEREXPLEVEL_RATE, rq, UserExplevelRateRs.class);
		List<UserExplevelRateBO> dataList = userExplevelRateRs.getDataList();
		if(!StringUtils.isEmpty(dataList)&&dataList.size()>0){
			for(UserExplevelRateBO user:dataList){
				if(user.getMinValue()>0){
					user.setIncreasePercent(String.valueOf((user.getLevelCount() - user.getMinValue()) / user.getMinValue()));
				}
				else{
					user.setIncreasePercent("100");
				}
			}
		}
		model.addAttribute("userexplevelRs", dataList);
		rq.setTotalItems(userExplevelRateRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userexplevel_list";
	}

	/**
	 * 用户会员等级统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userviplevel/list.php")
	public String list(UserTrendCountRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userviplevel_list";
	}
	/**
	 * 用户会员等级统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userviplevel.php")
	public String userviplevel(UserTrendCountRq rq,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(rq.getYear())){
			rq.setYear(DateUtil.getYear());
		}
		UserExplevelRateRs userExplevelRateRs = SoaConnectionFactory.get(request, ConstantsUri.USERVIPLEVEL_RATE, rq, UserExplevelRateRs.class);
		List<UserExplevelRateBO> dataList = userExplevelRateRs.getDataList();
		/*for(UserExplevelRateBO user:dataList){
			user.setIncrease(user.getIncrease()+user.getLastYearAll());
		}*/
		model.addAttribute("userviplevelRs", dataList);
		rq.setTotalItems(userExplevelRateRs.getDataList().size());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/userviplevel_list";
	}

	/**
	 * 用户活跃度统计用户信息
	 *
	 * @param rq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userviplevel/look.php")
	public String userviplevel(UserTrendCountRq rq,String doType,
							 HttpServletRequest request, Model model) {
		ConsumerRs consumerRs = new ConsumerRs();
		if("0".equals(doType)){//新增用户
			 consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERVIPLEVEL_INCREASE, rq, ConsumerRs.class);
		}
		else if("1".equals(doType)){//全部用户
			 consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERVIPLEVEL_ALLINFO, rq, ConsumerRs.class);
		}
		model.addAttribute("consumers", consumerRs.getDataList());
		rq.setTotalItems(consumerRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		model.addAttribute("doType", doType);
		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());
		return "system/usertrendcount/userviplevel_look";
	}

	/**
	 * 用户消费能力分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumption/list.php")
	public String list(UserConsumptionRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/consumption_list";
	}
	/**
	 * 用户消费能力分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumption.php")
	public String consumption(UserConsumptionRq rq,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(rq.getStartTime())){
			rq.setStartTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		};
		if(StringUtils.isEmpty(rq.getEndTime())){
			rq.setEndTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		};
		if(StringUtils.isEmpty(rq.getTradeMethod())){
			rq.setTradeMethod("RMB");
		};
		UserConsumptionRs userConsumptionRs = SoaConnectionFactory.get(request, ConstantsUri.CONSUME_RFM, rq, UserConsumptionRs.class);
		model.addAttribute("consumptionRs", userConsumptionRs.getData());
		rq.setTotalItems(userConsumptionRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/consumption_list";
	}
	/**
	 * 用户消费能力分析用户信息
	 *
	 * @param rq
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/consumption/listJson.php")
	public ModelAndView userlist(UserConsumptionListRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserTrendExcelRs userConsumptionRs = SoaConnectionFactory.get(request, ConstantsUri.CONSUME_RATE, rq, UserTrendExcelRs.class);
		mav.addObject("total", userConsumptionRs.getTotal());
		return mav;
	}

	/**
	 * 用户消费能力分析用户信息
	 *
	 * @param rq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumption/look.php")
	public String consumption(UserConsumptionListRq rq,
							 HttpServletRequest request, Model model) {
		UserTrendExcelRs userConsumptionRs = SoaConnectionFactory.get(request, ConstantsUri.CONSUME_RATE, rq, UserTrendExcelRs.class);
		model.addAttribute("userConsumptionRs", userConsumptionRs.getDataList());
		rq.setTotalItems(userConsumptionRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/consumption_look";
	}


	/**
	 * 查询出是否有需要导出的用户消费能力分析用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/qexportuser.php")
	public @ResponseBody boolean qexportPrint(UserConsumptionListRq rq,HttpServletRequest request,HttpServletResponse response){
		UserTrendExcelRs userConsumptionRs = SoaConnectionFactory.get(request, ConstantsUri.CONSUME_RATE, rq, UserTrendExcelRs.class);
		List<UserTrendExcelBO> excels=userConsumptionRs.getDataList();
		return (excels!=null && excels.size()>0)?true:false;
	}

	/**
	 * 用户消费能力分析用户信息导出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/consumption/exportExcel.php")
	public void exportPrint(UserConsumptionListRq rq,HttpServletRequest request,HttpServletResponse response){
		UserTrendExcelRs userConsumptionRs = SoaConnectionFactory.get(request, ConstantsUri.CONSUME_RATE, rq, UserTrendExcelRs.class);
		List<UserTrendExcelBO> excels=userConsumptionRs.getDataList();
		exportExecl(response, excels, "用户消费能力分析-用户详情.xlsx", "tempfiles/USERTREND_TEMP.xlsx");
	}

	/**
	 * 注册用户统计分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/usersign/list.php")
	public String usersign_counts(RegisterUserCountRq rq,HttpServletRequest request, Model model){
		UserSignRs userSignRs = new UserSignRs();
		//UserSignRs userSignRs = SoaConnectionFactory.get(request, ConstantsUri.USERLOST_RATE, rq, UserSignRs.class);
		model.addAttribute("userSignRs", userSignRs.getData());
		rq.setTotalItems(userSignRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/usersign_list";
	}

	@RequestMapping("/usersign/listJson.php")
	public ModelAndView usersign_listJson(RegisterUserCountRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserTrendCountRq userRq = new UserTrendCountRq();
		String type = rq.getType();
		List<UserTrendCountBO> list = new ArrayList<>();
		if(StringUtils.isEmpty(type)){
			type = "0";
		}
		if("0".equals(type)||"1".equals(type)||"2".equals(type)||"3".equals(type)||"4".equals(type)||"5".equals(type)){//时间段
			userRq.setStartTime(rq.getTimed().substring(0,10));
			userRq.setEndTime(rq.getTimed().substring(12,23));
		}
		else if("6".equals(type)){//对比时间
			userRq.setStartTime(rq.getTimed().substring(0, 10));
			userRq.setEndTime(rq.getTimed().substring(12, 23));
			UserTrendCountRs userTrendCountb = SoaConnectionFactory.get(request, ConstantsUri.REGISTERUSER_COUNT, userRq, UserTrendCountRs.class);
			List<UserTrendCountBO> userTrendCount = userTrendCountb.getDataList();
			if(!StringUtils.isEmpty(userTrendCount)){
				String[] tjday = new String[userTrendCount.size()];
				for (int i = 0; i < userTrendCount.size(); i++) {
					tjday[i] = userTrendCount.get(i).getCount().toString();
				}
				mav.addObject("tjday1", Arrays.toString(tjday));
			}
			for(UserTrendCountBO user:userTrendCount){
				list.add(user);
			}
			userRq.setStartTime(rq.getTimeb().substring(0, 10));
			userRq.setEndTime(rq.getTimeb().substring(12, 23));
		}
		UserTrendCountRs userTrendCountRs = SoaConnectionFactory.get(request, ConstantsUri.REGISTERUSER_COUNT, userRq, UserTrendCountRs.class);
		List<UserTrendCountBO> userTrend = userTrendCountRs.getDataList();
		for(UserTrendCountBO user:userTrend){
			list.add(user);
		}
		if("6".equals(type)){
			if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[userTrend.size()];
				for (int i = 0; i < userTrend.size(); i++) {
					tjday[i] = userTrend.get(i).getCount().toString();
				}
				mav.addObject("tjday", Arrays.toString(tjday));
				mav.addObject("day", userTrend.size());
			}
			else{
				String[] tjday = new String[0];
				mav.addObject("tjday", Arrays.toString(tjday));
			}
		}
		else{
			if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String[] arr = new String[2];
					arr[0] = "'"+list.get(i).getDays()+"'";
					arr[1] = list.get(i).getCount().toString();
					tjday[i] = Arrays.toString(arr);

				}
				mav.addObject("tjday", Arrays.toString(tjday));
			}
			else{
				String[] tjday = new String[0];
				mav.addObject("tjday", Arrays.toString(tjday));
			}
		}
		mav.addObject("userTrendCountRs", list);
		return mav;
	}

	/**
	 * 注册用户统计分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/usersigntop/list.php")
	public String usersigntop(RegisterUserCountRq rq,HttpServletRequest request, Model model){
		UserSignTopRs userSignTopRs = new UserSignTopRs();
		//UserSignRs userSignRs = SoaConnectionFactory.get(request, ConstantsUri.USERLOST_RATE, rq, UserSignRs.class);
		model.addAttribute("userSignTopRs", userSignTopRs.getData());
		rq.setTotalItems(userSignTopRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("BaseRq", rq);
		return "system/usertrendcount/usersign_top";
	}
}
