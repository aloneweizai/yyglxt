package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.DateUtil;
import com.abc.common.util.GeneralTree;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.TagsRq;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.system.*;
import com.abc.soa.response.consumer.ConsumerRs;
import com.abc.soa.response.consumer.TagsRs;
import com.abc.soa.response.consumer.VipLevelRs;
import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.consumer.bo.Tag;
import com.abc.soa.response.consumer.bo.VipLevel;
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
@RequestMapping(value = "/userimganalyze")
public class UserImgAnalyzeController extends BaseController {
	/**
	 * 注册用户统计分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/usertag/list.php")
	public String selectlist(UserTagStatisRq rq,HttpServletRequest request, Model model){
		TagsRq tagsRq = new TagsRq();
		tagsRq.setPage(0);
		tagsRq.setSize(0);
		tagsRq.setType("user");
		TagsRs tagsRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
		List<Tag> tags = tagsRs.getDataList();
		model.addAttribute("tags", tags);
		model.addAttribute("taglib", getDictBOList(request, "taglib"));
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/usertag_list";
	}

	@RequestMapping("/usertag/listJson.php")
	public ModelAndView listJson(UserTagStatisRq rq,HttpServletRequest request){
		rq.setType("day");
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserTagStatisRs userTagStatisRs = SoaConnectionFactory.get(request, ConstantsUri.USERTAG_RATE, rq, UserTagStatisRs.class);
		List<UserTagStatisBO> list = userTagStatisRs.getDataList();
			if(!StringUtils.isEmpty(list)){
				String[] tjday = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String[] arr = new String[2];
					arr[0] = "'"+list.get(i).getTagName()+"'";
					arr[1] = list.get(i).getCount().toString();
					tjday[i] = Arrays.toString(arr);

				}
				mav.addObject("tjday", Arrays.toString(tjday));
			}
			else{
				String[] tjday = new String[0];
				mav.addObject("tjday", Arrays.toString(tjday));
			}

		mav.addObject("userTagStatisRs", list);
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
	@RequestMapping("/userdetail/userlook.php")
	public String userlook(UserDetailLookRq rq,
						 HttpServletRequest request, Model model) {
		ConsumerRs consumerRs = new ConsumerRs();
		//doType:1 用户标签分析统计 2 用户年龄分布统计 3 用户性别分布统计 4 用户服务企业情况统计 5 用户区域分布统计 6  软件用户行为分析
		if("1".equals(rq.getDoType())){
			UserTagStatisRq userTagStatisRq = new UserTagStatisRq();
			userTagStatisRq.setType("day");
			userTagStatisRq.setStart(rq.getStartTime());
			userTagStatisRq.setEnd(rq.getEndTime());
			userTagStatisRq.setTagName(rq.getTagName());
			userTagStatisRq.setPage(rq.getPage());
			userTagStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERTAG_INFO, userTagStatisRq, ConsumerRs.class);
			model.addAttribute("consumers", consumerRs.getDataList());
		}
		else if("2".equals(rq.getDoType())){
			UserAgeStatisRq userAgeStatisRq = new UserAgeStatisRq();
			userAgeStatisRq.setStartTime(rq.getStartTime() + "-01");
			userAgeStatisRq.setEndTime(rq.getEndTime() + "-01");
			userAgeStatisRq.setStartNum(rq.getStartNum());
			userAgeStatisRq.setEndNum(rq.getEndNum());
			userAgeStatisRq.setPage(rq.getPage());
			userAgeStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERAGE_INFO, userAgeStatisRq, ConsumerRs.class);
			model.addAttribute("consumers", consumerRs.getDataList());
		}
		else if("3".equals(rq.getDoType())){
			UserSexStatisRq userSexStatisRq = new UserSexStatisRq();
			userSexStatisRq.setStartTime(rq.getStartTime() + "-01");
			userSexStatisRq.setEndTime(rq.getEndTime() + "-"+ DateUtil.getLastMonthDay(rq.getEndTime().substring(0, 4), rq.getEndTime().substring(5, 7)));
			userSexStatisRq.setSex(rq.getSex());
			userSexStatisRq.setPage(rq.getPage());
			userSexStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERSEX_INFO, userSexStatisRq, ConsumerRs.class);
			model.addAttribute("consumers", consumerRs.getDataList());
		}
		else if("4".equals(rq.getDoType())){
			UserBindStatisRq userBindStatisRq = new UserBindStatisRq();
			userBindStatisRq.setStartTime(rq.getStartTime()+"-01");
			userBindStatisRq.setEndTime(rq.getEndTime()+"-01");
			userBindStatisRq.setStartNum(rq.getStartNum());
			userBindStatisRq.setEndNum(rq.getEndNum());
			userBindStatisRq.setPage(rq.getPage());
			userBindStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERSERVICECOMP_INFO, userBindStatisRq, ConsumerRs.class);
			model.addAttribute("consumers", consumerRs.getDataList());
		}
		else if("5".equals(rq.getDoType())){
			UserAreaStatisRq userAreaStatisRq = new UserAreaStatisRq();
			userAreaStatisRq.setProvince(rq.getProvince());
			userAreaStatisRq.setType(rq.getType());
			userAreaStatisRq.setTimeInterval(rq.getStartTime());
			userAreaStatisRq.setTagName(rq.getTagName());
			userAreaStatisRq.setPage(rq.getPage());
			userAreaStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERAREA_INFO, userAreaStatisRq, ConsumerRs.class);
			model.addAttribute("consumers", consumerRs.getDataList());
		}
		else if("6".equals(rq.getDoType())){
			UserBehaviorStatisRq userBehaviorStatisRq = new UserBehaviorStatisRq();
			int index =0;
			//int index = GeneralTree.t.getIndex((String) rq.getMenu());
			CodeCriteria codeCriteria=new CodeCriteria();
			codeCriteria.setDictId("UserBehavior");
			DictListBO d = SoaConnectionFactory.get(request, ConstantsUri.SYS_CODE_TREE, codeCriteria, DictListBO.class);
			DictBO oo = d.getData();
			if(!StringUtils.isEmpty(oo)){
				List<DictBO> nodes = oo.getNodes();
				if(!StringUtils.isEmpty(nodes)&&nodes.size()>0){
					for(DictBO first:nodes){
						if(first.getFieldKey().equals(rq.getMenu())){
							index = 1;
							break;
						}
						else{
							List<DictBO> nodess = first.getNodes();
							if(!StringUtils.isEmpty(nodess)&&nodess.size()>0){
								for(DictBO second:nodess){
									if(second.getFieldKey().equals(rq.getMenu())){
										index = 2;
										break;
									}
									else{
										List<DictBO> nodesss = second.getNodes();
										if(!StringUtils.isEmpty(nodesss)&&nodesss.size()>0){
											for(DictBO three:nodesss){
												if(three.getFieldKey().equals(rq.getMenu())){
													index = 3;
													break;
												}
												else{

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(1 == index){
				userBehaviorStatisRq.setMenua(rq.getMenu());
			}
			else if(2 == index){
				userBehaviorStatisRq.setMenub(rq.getMenu());
			}
			else if(3 == index){
				userBehaviorStatisRq.setMenuc(rq.getMenu());
			}
			userBehaviorStatisRq.setStartTime(rq.getStartTime());
			userBehaviorStatisRq.setEndTime(rq.getEndTime());
			userBehaviorStatisRq.setPage(rq.getPage());
			userBehaviorStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERBEHAVIOR_INFO, userBehaviorStatisRq, ConsumerRs.class);
			model.addAttribute("consumers", consumerRs.getDataList());
		}
		rq.setTotalItems(consumerRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());
		return "system/userimganalyze/userdetail_look";
	}

	/**
	 * 用户年龄分布统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userage/list.php")
	public String selectlist(UserServiceCompRq rq, HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/userage_list";
	}

	/**
	 * 用户年龄分布统计
	 * @param
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/userage/listJson.php")
	public ModelAndView useragelist(UserServiceCompRq rq,HttpServletRequest request){
		rq.setStartTime(rq.getStartTime() + "-01");
		rq.setEndTime(rq.getEndTime() + "-01");
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserAgeStatisRs userAgeStatisRs = SoaConnectionFactory.get(request, ConstantsUri.USERAGE_RATE, rq, UserAgeStatisRs.class);
		List<UserAgeStatisBO> list = userAgeStatisRs.getDataList();
		if(!StringUtils.isEmpty(list)){
			String[] tjday = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String[] arr = new String[2];
				if("未知".equals(list.get(i).getAgeGroup())){
					arr[0] = "'"+list.get(i).getAgeGroup()+"'";
				}
				else{
					arr[0] = "'"+list.get(i).getAgeGroup()+"周岁'";
				}
				arr[1] = list.get(i).getCount().toString();
				tjday[i] = Arrays.toString(arr);

			}
			mav.addObject("tjday", Arrays.toString(tjday));
		}
		else{
			String[] tjday = new String[0];
			mav.addObject("tjday", Arrays.toString(tjday));
		}

		mav.addObject("userAgeStatisRs", list);
		return mav;
	}


	/**
	 * 用户留存率统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/usersex/list.php")
	public String query(UserServiceCompRq rq, HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/usersex_list";
	}

	/**
	 * 用户留存率统计
	 * @param
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/usersex/listJson.php")
	public ModelAndView usersexlist(UserServiceCompRq rq, HttpServletRequest request){
		rq.setStartTime(rq.getStartTime() + "-01");
		rq.setEndTime(rq.getEndTime() + "-"+ DateUtil.getLastMonthDay(rq.getEndTime().substring(0, 4), rq.getEndTime().substring(5, 7)));
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserSexStatisRs userSexStatisRs = SoaConnectionFactory.get(request, ConstantsUri.USERSEX_RATE, rq, UserSexStatisRs.class);
		List<UserSexStatisBO> list = userSexStatisRs.getDataList();
		mav.addObject("uNum", 0);
		if(!StringUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				if ("0".equals(list.get(i).getSexGroup())) {
					mav.addObject("wNum", list.get(i).getCount());
				} else if ("1".equals(list.get(i).getSexGroup())) {
					mav.addObject("mNum", list.get(i).getCount());
				} else {
					mav.addObject("uNum", list.get(i).getCount());
				}
			}
		}
		mav.addObject("userSexStatisRs", list);
		return mav;
	}


	/**
	 * 用户区域分布统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userarea/list.php")
	public String selectlist(UserAreaStatisRq rq,HttpServletRequest request, Model model){
		TagsRq tagsRq = new TagsRq();
		tagsRq.setPage(0);
		tagsRq.setSize(0);
		tagsRq.setType("user");
		TagsRs tagsRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
		List<Tag> tags = tagsRs.getDataList();
		model.addAttribute("tags", tags);
		model.addAttribute("taglib", getDictBOList(request, "taglib"));
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/userarea_list";
	}

	/**
	 * 用户活跃度统计
	 * @param
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/userarea/listJson.php")
	public ModelAndView userarealist(UserAreaStatisRq rq,HttpServletRequest request){
		if("province".equals(rq.getType())){
			rq.setProvince("湖南省");
		}
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserAreaStatisRs userAreaStatisRs = SoaConnectionFactory.get(request, ConstantsUri.USERAREA_RATE, rq, UserAreaStatisRs.class);
		List<UserAreaStatisBO> list = userAreaStatisRs.getDataList();
		if(list.size()>0){
			String[] tjday = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String[] arr = new String[2];
				arr[0] = "'"+list.get(i).getRegionName()+"'";
				arr[1] = list.get(i).getAllCount().toString();
				tjday[i] = Arrays.toString(arr);

			}
			mav.addObject("tjday", Arrays.toString(tjday));
		}
		else{
			String[] tjday = new String[0];
			mav.addObject("tjday", Arrays.toString(tjday));
		}

		mav.addObject("userAreaStatisRs", list);
		return mav;
	}

	/**
	 * 用户经验值等级统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userproperty/list.php")
	public String selectlist(UserTrendCountRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/userproperty_list";
	}
	/**
	 * 用户经验值等级统计
	 * @param
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/userproperty/listJson.php")
	public ModelAndView userpropertylist(UserTagStatisRq rq,HttpServletRequest request){
		rq.setType("day");
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserTagStatisRs userTagStatisRs = SoaConnectionFactory.get(request, ConstantsUri.USERTAG_RATE, rq, UserTagStatisRs.class);
		List<UserTagStatisBO> list = userTagStatisRs.getDataList();
		if(!StringUtils.isEmpty(list)){
			String[] tjday = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String[] arr = new String[2];
				arr[0] = "'"+list.get(i).getTagName()+"'";
				arr[1] = list.get(i).getCount().toString();
				tjday[i] = Arrays.toString(arr);

			}
			mav.addObject("tjday", Arrays.toString(tjday));
		}
		else{
			String[] tjday = new String[0];
			mav.addObject("tjday", Arrays.toString(tjday));
		}

		mav.addObject("userTagStatisRs", list);
		return mav;
	}

	/**
	 * 用户会员等级统计
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userbehavior/list.php")
	public String list(UserBehaviorStatisRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/userbehavior_list";
	}
	/**
	 * 用户会员等级统计
	 * @param
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/userbehavior/listJson.php")
	public ModelAndView userbehaviorlist(UserBehaviorStatisRq rq,HttpServletRequest request){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		CodeCriteria codeCriteria=new CodeCriteria();
		codeCriteria.setDictName(rq.getMenu());
		DictListBO dicts = SoaConnectionFactory.get(request, ConstantsUri.SYS_CODE, codeCriteria, DictListBO.class);
		String menus = "";
		if(!StringUtils.isEmpty(dicts.getDataList())){
			List<DictBO> dataList = dicts.getDataList();
			if(dataList.size()>0){
				 for(DictBO dict:dataList){
					 if(dict.getDictName().equals(rq.getMenu())){
						 menus +=dict.getFieldKey()+",";
					 }
				 }
				rq.setMenuList(menus);
			}
		}
		if(!StringUtils.isEmpty(rq.getMenuList())){
			UserBehaviorStatisRs userBehaviorStatisRs = SoaConnectionFactory.get(request, ConstantsUri.USERBEHAVIOR_RATE, rq, UserBehaviorStatisRs.class);
			List<UserBehaviorStatisBO> list = userBehaviorStatisRs.getDataList();
			if (!StringUtils.isEmpty(list)) {
				String[] tjday = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					String[] arr = new String[2];
					arr[0] = "'" + list.get(i).getMenu() + "'";
					arr[1] = list.get(i).getAmount().toString();
					tjday[i] = Arrays.toString(arr);

				}
				mav.addObject("tjday", Arrays.toString(tjday));
			} else {
				String[] tjday = new String[0];
				mav.addObject("tjday", Arrays.toString(tjday));
			}

			mav.addObject("userBehaviorStatisRs", list);
			return mav;
		}
		else {
			mav.addObject("message", "已经统计的是最后一层功能点");
			return mav;
		}
	}

	/**
	 * 用户消费能力分析
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/userservicecomp/list.php")
	public String list(UserServiceCompRq rq,HttpServletRequest request, Model model){
		model.addAttribute("BaseRq", rq);
		return "system/userimganalyze/userservicecomp_list";
	}
	/**
	 * 用户消费能力分析
	 * @param
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/userservicecomp/listJson.php")
	public ModelAndView userservicecomplist(UserServiceCompRq rq,HttpServletRequest request){
		rq.setStartTime(rq.getStartTime() + "-01");
		rq.setEndTime(rq.getEndTime() + "-01");
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		UserServiceCompRs userServiceCompRs = SoaConnectionFactory.get(request, ConstantsUri.USERSERVICECOMP_RATE, rq, UserServiceCompRs.class);
		mav.addObject("userServiceCompRs", userServiceCompRs.getData());
		return mav;
	}

	/**
	 * 查询出是否有需要导出的用户消费能力分析用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/qexportuser.php")
	public @ResponseBody boolean qexportPrint(UserDetailLookRq rq,HttpServletRequest request,HttpServletResponse response){
		ConsumerRs consumerRs = new ConsumerRs();
		//doType:1 用户标签分析统计 2 用户年龄分布统计 3 用户性别分布统计 4 用户服务企业情况统计 5 用户区域分布统计 6  软件用户行为分析
		if("2".equals(rq.getDoType())){
			UserAgeStatisRq userAgeStatisRq = new UserAgeStatisRq();
			userAgeStatisRq.setStartTime(rq.getStartTime() + "-01");
			userAgeStatisRq.setEndTime(rq.getEndTime() + "-01");
			userAgeStatisRq.setStartNum(rq.getStartNum());
			userAgeStatisRq.setEndNum(rq.getEndNum());
			userAgeStatisRq.setPage(rq.getPage());
			userAgeStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERAGE_INFO, userAgeStatisRq, ConsumerRs.class);
		}
		else if("3".equals(rq.getDoType())){
			UserSexStatisRq userSexStatisRq = new UserSexStatisRq();
			userSexStatisRq.setStartTime(rq.getStartTime() + "-01");
			userSexStatisRq.setEndTime(rq.getEndTime() + "-"+ DateUtil.getLastMonthDay(rq.getEndTime().substring(0, 4), rq.getEndTime().substring(5, 7)));
			userSexStatisRq.setSex(rq.getSex());
			userSexStatisRq.setPage(rq.getPage());
			userSexStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERSEX_INFO, userSexStatisRq, ConsumerRs.class);
		}
		else if("4".equals(rq.getDoType())){
			UserBindStatisRq userBindStatisRq = new UserBindStatisRq();
			userBindStatisRq.setStartTime(rq.getStartTime()+"-01");
			userBindStatisRq.setEndTime(rq.getEndTime()+"-01");
			userBindStatisRq.setStartNum(rq.getStartNum());
			userBindStatisRq.setEndNum(rq.getEndNum());
			userBindStatisRq.setPage(rq.getPage());
			userBindStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERSERVICECOMP_INFO, userBindStatisRq, ConsumerRs.class);
		}
		else if("5".equals(rq.getDoType())){
			UserAreaStatisRq userAreaStatisRq = new UserAreaStatisRq();
			userAreaStatisRq.setProvince(rq.getProvince());
			userAreaStatisRq.setType(rq.getType());
			userAreaStatisRq.setTimeInterval(rq.getStartTime());
			userAreaStatisRq.setTagName(rq.getTagName());
			userAreaStatisRq.setPage(rq.getPage());
			userAreaStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERAREA_INFO, userAreaStatisRq, ConsumerRs.class);
		}
		else if("6".equals(rq.getDoType())){
			UserBehaviorStatisRq userBehaviorStatisRq = new UserBehaviorStatisRq();
			int index = 0;
			CodeCriteria codeCriteria=new CodeCriteria();
			codeCriteria.setDictId("UserBehavior");
			DictListBO d = SoaConnectionFactory.get(request, ConstantsUri.SYS_CODE_TREE, codeCriteria, DictListBO.class);
			DictBO oo = d.getData();
			if(!StringUtils.isEmpty(oo)){
				List<DictBO> nodes = oo.getNodes();
				if(!StringUtils.isEmpty(nodes)&&nodes.size()>0){
					for(DictBO first:nodes){
						if(first.getFieldKey().equals(rq.getMenu())){
							index = 1;
							break;
						}
						else{
							List<DictBO> nodess = first.getNodes();
							if(!StringUtils.isEmpty(nodess)&&nodess.size()>0){
								for(DictBO second:nodess){
									if(second.getFieldKey().equals(rq.getMenu())){
										index = 2;
										break;
									}
									else{
										List<DictBO> nodesss = second.getNodes();
										if(!StringUtils.isEmpty(nodesss)&&nodesss.size()>0){
											for(DictBO three:nodesss){
												if(three.getFieldKey().equals(rq.getMenu())){
													index = 3;
													break;
												}
												else{

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(1 == index){
				userBehaviorStatisRq.setMenua(rq.getMenu());
			}
			else if(2 == index){
				userBehaviorStatisRq.setMenub(rq.getMenu());
			}
			else if(3 == index){
				userBehaviorStatisRq.setMenuc(rq.getMenu());
			}
			userBehaviorStatisRq.setStartTime(rq.getStartTime());
			userBehaviorStatisRq.setEndTime(rq.getEndTime());
			userBehaviorStatisRq.setPage(rq.getPage());
			userBehaviorStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERBEHAVIOR_INFO, userBehaviorStatisRq, ConsumerRs.class);
		}
		List<Consumer> dataList = consumerRs.getDataList();
		return (dataList!=null && dataList.size()>0)?true:false;
	}

	/**
	 * 用户消费能力分析用户信息导出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/consumption/exportExcel.php")
	public void exportPrint(UserDetailLookRq rq,HttpServletRequest request,HttpServletResponse response){
		ConsumerRs consumerRs = new ConsumerRs();
		String fileName = "";
		//doType:1 用户标签分析统计 2 用户年龄分布统计 3 用户性别分布统计 4 用户服务企业情况统计 5 用户区域分布统计 6  软件用户行为分析
		if("2".equals(rq.getDoType())){
			UserAgeStatisRq userAgeStatisRq = new UserAgeStatisRq();
			userAgeStatisRq.setStartTime(rq.getStartTime() + "-01");
			userAgeStatisRq.setEndTime(rq.getEndTime() + "-01");
			userAgeStatisRq.setStartNum(rq.getStartNum());
			userAgeStatisRq.setEndNum(rq.getEndNum());
			userAgeStatisRq.setPage(rq.getPage());
			userAgeStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERAGE_INFO, userAgeStatisRq, ConsumerRs.class);
			fileName = "用户年龄分布统计-用户详情.xlsx";
		}
		else if("3".equals(rq.getDoType())){
			UserSexStatisRq userSexStatisRq = new UserSexStatisRq();
			userSexStatisRq.setStartTime(rq.getStartTime() + "-01");
			userSexStatisRq.setEndTime(rq.getEndTime() + "-"+ DateUtil.getLastMonthDay(rq.getEndTime().substring(0, 4), rq.getEndTime().substring(5, 7)));
			userSexStatisRq.setSex(rq.getSex());
			userSexStatisRq.setPage(rq.getPage());
			userSexStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERSEX_INFO, userSexStatisRq, ConsumerRs.class);

			fileName = "用户性别分布统计-用户详情.xlsx";
		}
		else if("4".equals(rq.getDoType())){
			UserBindStatisRq userBindStatisRq = new UserBindStatisRq();
			userBindStatisRq.setStartTime(rq.getStartTime()+"-01");
			userBindStatisRq.setEndTime(rq.getEndTime() + "-01");
			userBindStatisRq.setStartNum(rq.getStartNum());
			userBindStatisRq.setEndNum(rq.getEndNum());
			userBindStatisRq.setPage(rq.getPage());
			userBindStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERSERVICECOMP_INFO, userBindStatisRq, ConsumerRs.class);
			fileName = "用户服务企业情况统计-用户详情.xlsx";
		}
		else if("5".equals(rq.getDoType())){
			UserAreaStatisRq userAreaStatisRq = new UserAreaStatisRq();
			userAreaStatisRq.setProvince(rq.getProvince());
			userAreaStatisRq.setType(rq.getType());
			userAreaStatisRq.setTimeInterval(rq.getStartTime());
			userAreaStatisRq.setTagName(rq.getTagName());
			userAreaStatisRq.setPage(rq.getPage());
			userAreaStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERAREA_INFO, userAreaStatisRq, ConsumerRs.class);
			fileName = "用户区域分布统计-用户详情.xlsx";
		}
		else if("6".equals(rq.getDoType())){
			UserBehaviorStatisRq userBehaviorStatisRq = new UserBehaviorStatisRq();
			int index = 0;
			CodeCriteria codeCriteria=new CodeCriteria();
			codeCriteria.setDictId("UserBehavior");
			DictListBO d = SoaConnectionFactory.get(request, ConstantsUri.SYS_CODE_TREE, codeCriteria, DictListBO.class);
			DictBO oo = d.getData();
			if(!StringUtils.isEmpty(oo)){
				List<DictBO> nodes = oo.getNodes();
				if(!StringUtils.isEmpty(nodes)&&nodes.size()>0){
					for(DictBO first:nodes){
						if(first.getFieldKey().equals(rq.getMenu())){
							index = 1;
							break;
						}
						else{
							List<DictBO> nodess = first.getNodes();
							if(!StringUtils.isEmpty(nodess)&&nodess.size()>0){
								for(DictBO second:nodess){
									if(second.getFieldKey().equals(rq.getMenu())){
										index = 2;
										break;
									}
									else{
										List<DictBO> nodesss = second.getNodes();
										if(!StringUtils.isEmpty(nodesss)&&nodesss.size()>0){
											for(DictBO three:nodesss){
												if(three.getFieldKey().equals(rq.getMenu())){
													index = 3;
													break;
												}
												else{

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(1 == index){
				userBehaviorStatisRq.setMenua(rq.getMenu());
			}
			else if(2 == index){
				userBehaviorStatisRq.setMenub(rq.getMenu());
			}
			else if(3 == index){
				userBehaviorStatisRq.setMenuc(rq.getMenu());
			}
			userBehaviorStatisRq.setStartTime(rq.getStartTime());
			userBehaviorStatisRq.setEndTime(rq.getEndTime());
			userBehaviorStatisRq.setPage(rq.getPage());
			userBehaviorStatisRq.setSize(rq.getSize());
			consumerRs = SoaConnectionFactory.get(request,
					ConstantsUri.USERBEHAVIOR_INFO, userBehaviorStatisRq, ConsumerRs.class);
			fileName = "软件用户行为分析-用户详情.xlsx";
		}
		List<Consumer> dataList = consumerRs.getDataList();
		List<Consumer> excels = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		List<VipLevel> levellist = level.getDataList();
		for(int i = 0;i<dataList.size();i++){
			Consumer user = dataList.get(i);
			user.setVipLevelName(user.getVipLevel());
			for(int j=0;j<levellist.size();j++){
				if(user.getVipLevel().equals(levellist.get(j).getLevelCode())){
					user.setVipLevelName(levellist.get(j).getLevel());
				}
			}
			if(user.getStatus()){
				user.setStatusStr("启用");
			}
			else{
				user.setStatusStr("停用");
			}
			user.setCreateTimeStr(sdf.format(user.getCreateTime()));
			excels.add(user);
		}
		exportExecl(response, excels, fileName, "tempfiles/USERIMGSTATIS_TEMP.xlsx");
	}
}
