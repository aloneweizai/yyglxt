package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.GeneralTree;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.*;
import com.abc.soa.response.consumer.TaxpayerBindRs;
import com.abc.soa.response.system.*;
import com.abc.soa.response.system.bo.CompBingStatisBO;
import com.abc.soa.response.system.bo.CompLoginStatisBO;
import com.abc.soa.response.system.bo.CompRegisterCountBO;
import com.abc.soa.response.system.bo.CompUsingStatisBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(value = "/taxcompstatis")
public class TaxCompStatisticsController extends BaseController {
	/**
	 * 企业绑定情况统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/compbing/list.php")
	public String selectlist(CompBingStatisRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/taxcompstatistics/compbing_list";
	}

	/**
	 * 企业绑定情况统计
	 * @param
	 * @param request
	 * @param rq
	 * @return
	 */
	@RequestMapping("/compbing/listJson.php")
	public ModelAndView listJson(CompBingStatisRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		CompBingStatisRs compBingStatisRs = SoaConnectionFactory.get(request, ConstantsUri.COMPBING_RATE, rq, CompBingStatisRs.class);
		List<CompBingStatisBO> list = compBingStatisRs.getData();
		if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					if("all".equals(rq.getType())){
						if("dzsb".equals(list.get(i).getType())){
							mav.addObject("dzsb", list.get(i).getBindCount().toString());
						}
						else if("hngs".equals(list.get(i).getType())){
							mav.addObject("hngs", list.get(i).getBindCount().toString());
						}
						else if("hnds".equals(list.get(i).getType())){
							mav.addObject("hnds", list.get(i).getBindCount().toString());
						}
						mav.addObject("timeInterval", list.get(i).getTimeInterval());
					}
					else{
						String[] arr = new String[2];
						arr[0] = "'"+list.get(i).getMonth()+"'";
						arr[1] = list.get(i).getBindCount().toString();
						tjday[i] = Arrays.toString(arr);
						mav.addObject("tjday", Arrays.toString(tjday));
				}
			}
		}
		else{
			String[] tjday = new String[0];
			mav.addObject("tjday", Arrays.toString(tjday));
		}

		mav.addObject("compBingStatisRs", list);
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
	@RequestMapping("/compdetail/userlook.php")
	public String userlook(CompDetailLookRq rq,
						   HttpServletRequest request, Model model) {
		TaxpayerBindRs taxpayerBindRs = new TaxpayerBindRs();
		//doType:1 用户标签分析统计 2 用户年龄分布统计 3 用户性别分布统计
		if("1".equals(rq.getDoType())){
			taxpayerBindRs = SoaConnectionFactory.get(request,
					ConstantsUri.COMPBING_INFO, rq, TaxpayerBindRs.class);
		}
		else if("2".equals(rq.getDoType())){
			taxpayerBindRs = SoaConnectionFactory.get(request,
					ConstantsUri.COMPLOGIN_INFO, rq, TaxpayerBindRs.class);
		}
		else if("3".equals(rq.getDoType())){
			CompUsingStatisRq compUsingStatisRq = new CompUsingStatisRq();
			compUsingStatisRq.setStartTime(rq.getStartTime());
			compUsingStatisRq.setEndTime(rq.getEndTime());
			compUsingStatisRq.setMenu(rq.getMenu());
			compUsingStatisRq.setPage(rq.getPage());
			compUsingStatisRq.setSize(rq.getSize());
			taxpayerBindRs = SoaConnectionFactory.get(request,
					ConstantsUri.COMPUSING_INFO, compUsingStatisRq, TaxpayerBindRs.class);
		}
		model.addAttribute("taxpayerBindRs", taxpayerBindRs.getDataList());
		rq.setTotalItems(taxpayerBindRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		return "system/taxcompstatistics/compdetail_look";
	}

	/**
	 * 企业登录情况统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/complogin/list.php")
	public String selectlist(ComploginStatisRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/taxcompstatistics/complogin_list";
	}

	/**
	 * 企业登录情况统计
	 * @param
	 * @param request
	 * @param rq
	 * @return
	 */
	@RequestMapping("/complogin/listJson.php")
	public ModelAndView list(ComploginStatisRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		CompLoginStatisRs compLoginStatisRs = SoaConnectionFactory.get(request, ConstantsUri.COMPLOGIN_RATE, rq, CompLoginStatisRs.class);
		List<CompLoginStatisBO> list = compLoginStatisRs.getData();
		if(!StringUtils.isEmpty(list)){
			String[] tjday = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				if("all".equals(rq.getType())){
					if("dzsb".equals(list.get(i).getType())){
						mav.addObject("dzsb", list.get(i).getBindCount().toString());
					}
					else if("hngs".equals(list.get(i).getType())){
						mav.addObject("hngs", list.get(i).getBindCount().toString());
					}
					else if("hnds".equals(list.get(i).getType())){
						mav.addObject("hnds", list.get(i).getBindCount().toString());
					}
					mav.addObject("timeInterval", list.get(i).getTimeInterval());
				}
				else{
					String[] arr = new String[2];
					arr[0] = "'"+list.get(i).getMonth()+"'";
					arr[1] = list.get(i).getBindCount().toString();
					tjday[i] = Arrays.toString(arr);
					mav.addObject("tjday", Arrays.toString(tjday));
				}
			}
		}
		else{
			String[] tjday = new String[0];
			mav.addObject("tjday", Arrays.toString(tjday));
		}

		mav.addObject("compLoginStatisRs", list);
		return mav;
	}

	/**
	 * 企业使用情况统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/compusing/list.php")
	public String query(CompUsingStatisRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/taxcompstatistics/compusing_list";
	}

	/**
	 * 企业使用情况统计
	 * @param
	 * @param request
	 * @param rq
	 * @return
	 */
	@RequestMapping("/compusing/listJson.php")
	public ModelAndView compusinglist(CompUsingStatisRq rq,HttpServletRequest request){
		rq.setMenuList(GeneralTree.t.getChild((String) rq.getMenu()));
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		CompUsingStatisRs compUsingStatisRs = SoaConnectionFactory.get(request, ConstantsUri.COMPUSING_RATE, rq, CompUsingStatisRs.class);
		List<CompUsingStatisBO> list = compUsingStatisRs.getDataList();
		if(!StringUtils.isEmpty(list)){
			String[] tjday = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String[] arr = new String[2];
				arr[0] = "'"+list.get(i).getMenu()+"'";
				arr[1] = list.get(i).getTotal().toString();
				tjday[i] = Arrays.toString(arr);

			}
			mav.addObject("tjday", Arrays.toString(tjday));
		}
		else{
			String[] tjday = new String[0];
			mav.addObject("tjday", Arrays.toString(tjday));
		}

		mav.addObject("compUsingStatisRs", list);
		return mav;
	}

	/**
	 * 注册用户统计分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/registercompcount/list.php")
	public String counts(RegisterUserCountRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/taxcompstatistics/registercompcount";
	}

	@RequestMapping("/registercompcount/listJson.php")
	public ModelAndView listJson(RegisterUserCountRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		RegisterCompCountRq userRq = new RegisterCompCountRq();
		String type = rq.getType();
		List<CompRegisterCountBO> list = new ArrayList<>();
		if(StringUtils.isEmpty(type)){
			type = "0";
		}
		if("0".equals(type)||"1".equals(type)||"2".equals(type)||"3".equals(type)||"4".equals(type)||"5".equals(type)){//时间段
			userRq.setBeginDate(rq.getTimed().substring(0,10));
			userRq.setEndDate(rq.getTimed().substring(13, 23));
		}
		else if("6".equals(type)){//对比时间
			userRq.setBeginDate(rq.getTimed().substring(0, 10));
			userRq.setEndDate(rq.getTimed().substring(13, 23));
			CompRegisterCountRs userTrendCountb = SoaConnectionFactory.get(request, ConstantsUri.REGISTERCOMP_COUNT, userRq, CompRegisterCountRs.class);
			List<CompRegisterCountBO> userTrendCount = userTrendCountb.getDataList();
			if(!StringUtils.isEmpty(userTrendCount)){
				String[] tjday = new String[userTrendCount.size()];
				for (int i = 0; i < userTrendCount.size(); i++) {
					tjday[i] = userTrendCount.get(i).getTotal().toString();
				}
				mav.addObject("tjday1", Arrays.toString(tjday));
			}
			for(CompRegisterCountBO user:userTrendCount){
				list.add(user);
			}
			userRq.setBeginDate(rq.getTimeb().substring(0, 10));
			userRq.setEndDate(rq.getTimeb().substring(13, 23));
		}
		CompRegisterCountRs userTrendCountRs = SoaConnectionFactory.get(request, ConstantsUri.REGISTERCOMP_COUNT, userRq, CompRegisterCountRs.class);
		List<CompRegisterCountBO> userTrend = userTrendCountRs.getDataList();
		for(CompRegisterCountBO user:userTrend){
			list.add(user);
		}
		if("6".equals(type)){
			if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[userTrend.size()];
				for (int i = 0; i < userTrend.size(); i++) {
					tjday[i] = userTrend.get(i).getTotal().toString();
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
					arr[0] = "'"+list.get(i).getDjrq()+"'";
					arr[1] = list.get(i).getTotal().toString();
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
	@RequestMapping("/registercompcount/detail.php")
	public String expLog(RegisterUserCountRq rq,
						 HttpServletRequest request, Model model) {
		RegisterCompDetailRs consumerRs = SoaConnectionFactory.get(request,
				ConstantsUri.REGISTERCOMP_DETAIL, rq, RegisterCompDetailRs.class,rq.getDays());
		model.addAttribute("taxpayerBindRs", consumerRs.getDataList());
		rq.setTotalItems(consumerRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		return "system/taxcompstatistics/registercomp_look";
	}
}
