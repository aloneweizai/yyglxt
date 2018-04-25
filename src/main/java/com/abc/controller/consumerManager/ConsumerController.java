package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.*;
import com.abc.soa.response.consumer.*;
import com.abc.soa.response.consumer.bo.*;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户管理 Created by zhushuai on 2017/6/14.
 */
@Controller
public class ConsumerController extends BaseController {
	/**
	 * 用户列表
	 *
	 * @param consumerRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/consumer/list.php")
	public String list(ConsumerRq consumerRq, HttpServletRequest request,
					   Model model) {
		TagsRq tagsRq = new TagsRq();
		tagsRq.setPage(0);
		tagsRq.setSize(0);
		tagsRq.setType("user");
		TagsRs tagsRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
		List<Tag> tags = tagsRs.getDataList();
		model.addAttribute("tags", tags);
		String tagName = consumerRq.getTagName();
		String tagIds = "";
		if(!StringUtils.isEmpty(tagName)){
			String [] names = tagName.split(",");
			if(names.length>0){
				for(String name : names){
					for(int i =0;i<tags.size();i++){
						if(name.equals(tags.get(i).getTagName())){
							if(!StringUtils.isEmpty(tagIds)){
								tagIds = tags.get(i).getId()+ ","+tagIds;
							}
							else{
								tagIds = tags.get(i).getId();
							}
						}
					}
				}
			}
		}
		consumerRq.setTagId(tagIds);
		/*if(StringUtils.isEmpty(consumerRq.getDatetype())){
			consumerRq.setDatetype("4");
		}*/
		String type = consumerRq.getDatetype();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		if("1".equals(type)){//今天
			consumerRq.setStartDate(sdf.format(currentDate));
			consumerRq.setEndDate(sdf.format(currentDate));
		}
		else if("2".equals(type)){//昨天
			consumerRq.setStartDate(DateUtil.getYesterday());
			consumerRq.setEndDate(DateUtil.getYesterday());
		}
		else if("3".equals(type)){//最近7天
			consumerRq.setStartDate(DateUtil.getPastDate(7));
			consumerRq.setEndDate(sdf.format(currentDate));
		}
		else if("4".equals(type)){//最近30天
			consumerRq.setStartDate(DateUtil.getPastDate(30));
			consumerRq.setEndDate(sdf.format(currentDate));
		}
		if(StringUtils.isEmpty(consumerRq.getExpOper())&&!(StringUtils.isEmpty(consumerRq.getExp()))){
			consumerRq.setExpOper("equals");
		}
		if(StringUtils.isEmpty(consumerRq.getPointsOper())&&!(StringUtils.isEmpty(consumerRq.getPoints()))){
			consumerRq.setPointsOper("equals");
		}
		ConsumerRs consumerRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_LIST, consumerRq, ConsumerRs.class);
		model.addAttribute("consumers", consumerRs.getDataList());
		consumerRq.setTotalItems(consumerRs.getTotal());
		consumerRq.calculate();
		model.addAttribute("BaseRq", consumerRq);

		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());

		model.addAttribute("taglib", getDictBOList(request, "taglib"));

		return "consumer/consumer_list";
	}

	/**
	 * 用户信息查询列表
	 *
	 * @param consumerRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/toquery.php")
	public String toquery(ConsumerRq consumerRq, HttpServletRequest request,
						Model model) {
		TagsRq tagsRq = new TagsRq();
		tagsRq.setPage(0);
		tagsRq.setSize(0);
		tagsRq.setType("user");
		TagsRs tagsRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
		List<Tag> tags = tagsRs.getDataList();
		model.addAttribute("tags", tags);
		ConsumerRs consumerRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_LIST, consumerRq, ConsumerRs.class);
		model.addAttribute("consumers", consumerRs.getDataList());
		consumerRq.setTotalItems(consumerRs.getTotal());
		consumerRq.calculate();
		model.addAttribute("BaseRq", consumerRq);

		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());

		model.addAttribute("taglib", getDictBOList(request, "taglib"));

		return "consumer/consumer_query";
	}
	/**
	 * 用户信息查询列表
	 *
	 * @param consumerRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/query.php")
	public String query(ConsumerRq consumerRq, HttpServletRequest request,
						Model model) {
		model.addAttribute("BaseRq", consumerRq);
		return "consumer/consumer_query";
	}

	/**
	 * 用户详细信息
	 *
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/info.php")
	public String info(@RequestParam(value = "id") String id,
					   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));


		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));

		ConsumerInfoRs consumerInfoRs = SoaConnectionFactory.getRestful(
				request, ConstantsUri.CONSUMER_INFO, null,
				ConsumerInfoRs.class, id);
		model.addAttribute("consumerInfoRs", consumerInfoRs);


		model.addAttribute("shhy", getDictBOList(request, "industry"));

		model.addAttribute("scly", getDictBOList(request, "goodat"));



		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());

		if(consumerInfoRs.getUser_extend()!=null){
			String[] address=getAddres(request, new String[]{
					consumerInfoRs.getUser_extend().getProvince(),
					consumerInfoRs.getUser_extend().getCity(),
					consumerInfoRs.getUser_extend().getArea()});

			consumerInfoRs.getUser_extend().setProvince(address[0]);
			consumerInfoRs.getUser_extend().setCity(address[1]);
			consumerInfoRs.getUser_extend().setArea(address[2]);
		}


		TagsRs tagsRs = SoaConnectionFactory.getRestful(request,
				ConstantsUri.CONSUMER_TAGS, null, TagsRs.class, id);
		model.addAttribute("usertags", tagsRs.getDataList());

		return "consumer/moreInfo";
	}

	/**
	 * 用户积分日志
	 *
	 * @param pointsOrExpLogRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/pointsLog.php")
	public String pointsLog(PointsOrExpLogRq pointsOrExpLogRq,
							HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		PointsOrExpLogRs consumerInfoRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_POINTLOG, pointsOrExpLogRq,
				PointsOrExpLogRs.class);
		model.addAttribute("pointsLogs", consumerInfoRs.getDataList());
		pointsOrExpLogRq.setTotalItems(consumerInfoRs.getTotal());
		pointsOrExpLogRq.calculate();
		model.addAttribute("BaseRq", pointsOrExpLogRq);
		return "consumer/pointsLog";
	}

	/**
	 * 经验日志查询
	 *
	 * @param pointsOrExpLogRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/expLog.php")
	public String expLog(PointsOrExpLogRq pointsOrExpLogRq,
						 HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		PointsOrExpLogRs consumerInfoRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_EXPLOG, pointsOrExpLogRq,
				PointsOrExpLogRs.class);
		model.addAttribute("expLogs", consumerInfoRs.getDataList());
		pointsOrExpLogRq.setTotalItems(consumerInfoRs.getTotal());
		pointsOrExpLogRq.calculate();
		model.addAttribute("BaseRq", pointsOrExpLogRq);
		return "consumer/expLog";
	}

	/**
	 * 经验日志查询
	 *
	 * @param pointsOrExpLogRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/amountLog.php")
	public String amountLog(PointsOrExpLogRq pointsOrExpLogRq,
						 HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		PointsOrExpLogRs consumerInfoRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_AMOUNT, pointsOrExpLogRq,
				PointsOrExpLogRs.class);
		model.addAttribute("amountLogs", consumerInfoRs.getDataList());
		pointsOrExpLogRq.setTotalItems(consumerInfoRs.getTotal());
		pointsOrExpLogRq.calculate();
		model.addAttribute("BaseRq", pointsOrExpLogRq);
		return "consumer/amountLog";
	}
	/**
	 * 经验日志查询
	 *
	 * @param pointsOrExpLogRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/consumer/sjLog.php")
	public String sjLog(PointsOrExpLogRq pointsOrExpLogRq,
						 HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		UpdatePhoneLogRs updatePhoneLogRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_SJLOG, pointsOrExpLogRq,
				UpdatePhoneLogRs.class);
		model.addAttribute("sjLogs", updatePhoneLogRs.getDataList());
		pointsOrExpLogRq.setTotalItems(updatePhoneLogRs.getTotal());
		pointsOrExpLogRq.calculate();
		model.addAttribute("BaseRq", pointsOrExpLogRq);
		return "consumer/sjLog";
	}
	/**
	 * 数据字典管理启用和禁用
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("consumer:manage")
	@RequestMapping(path ="/consumerManager/consumer/{id}",  method = RequestMethod.PATCH)
	public ModelAndView updateStatus(@PathVariable(value = "id") String id, @RequestParam(value = "status") String status, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);

		BaseResponse result = null;
		result = SoaConnectionFactory.put(request, ConstantsUri.CONSUMER_STATUS, null, BaseResponse.class, id,status);
		mav.addObject("result", result);
		return mav;
	}
	/**
	 * Vip日志查询
	 *
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:query")
	@RequestMapping("/consumerManager/consumer/vipLog.php")
	public String vipLog(VipLogRq vipLogRq, HttpServletRequest request,
						 Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		VipLogRs consumerInfoRs = SoaConnectionFactory.get(request,
				ConstantsUri.CONSUMER_VIPLOG, vipLogRq, VipLogRs.class);
		model.addAttribute("vipLogs", consumerInfoRs.getDataList());
		vipLogRq.setTotalItems(consumerInfoRs.getTotal());
		vipLogRq.calculate();
		model.addAttribute("BaseRq", vipLogRq);
		return "consumer/vipLog";
	}

	/**
	 * 纳税人绑定查询
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:bdxxcx")
	@RequestMapping("/consumerManager/consumer/taxpayerBind_list.php")
	public String taxpayerBindQuery(TaxpayerBindRq taxpayerBindRq,
									HttpServletRequest request, Model model) {
		if(StringUtils.isEmpty(taxpayerBindRq.getType())){
			taxpayerBindRq.setType("dzsb");
		}
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("BaseRq", taxpayerBindRq);
		return "consumer/taxpayerBind_list";
	}
	/**
	 * 纳税人绑定查询
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:bdxxcx")
	@RequestMapping("/consumerManager/consumer/taxpayerBindQuery.php")
	public String taxpayerBindQuery_list(TaxpayerBindRq taxpayerBindRq,
									HttpServletRequest request, Model model) {
		if(StringUtils.isEmpty(taxpayerBindRq.getType())){
			taxpayerBindRq.setType("dzsb");
		}
		model.addAttribute("referer", request.getHeader("Referer"));
		TaxpayerBindRs taxpayerBindRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAXPAYERBIND_LIST, taxpayerBindRq,
				TaxpayerBindRs.class);
		model.addAttribute("taxpayerBindRs", taxpayerBindRs.getDataList());
		taxpayerBindRq.setTotalItems(taxpayerBindRs.getTotal());
		taxpayerBindRq.calculate();
		model.addAttribute("BaseRq", taxpayerBindRq);
		return "consumer/taxpayerBind_list";
	}

	@RequiresPermissions("nsqygl:bdxxcx")
	@RequestMapping("/consumerManager/consumer/taxpayerBind_info.php")
	public String taxpayerBindInfo(@RequestParam(value = "id") String id,
								   @RequestParam(value = "type") String type,
								   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		if ("dzsb".equals(type)) {
			UserDzsbRs userDzsb = SoaConnectionFactory.getRestful(request,
					ConstantsUri.TAXPAYERBIND_DZSB, null, UserDzsbRs.class, id);
			model.addAttribute("userDzsb", userDzsb.getData());
		}
		if ("hnds".equals(type)) {
			UserHndsRs userHnds = SoaConnectionFactory.getRestful(request,
					ConstantsUri.TAXPAYERBIND_HNDS, null, UserHndsRs.class, id);
			model.addAttribute("userHnds", userHnds.getData());
		}
		if ("hngs".equals(type)) {
			UserHngsRs userHngs = SoaConnectionFactory.getRestful(request,
					ConstantsUri.TAXPAYERBIND_HNGS, null, UserHngsRs.class, id);
			model.addAttribute("userHngs", userHngs.getData());
		}
		return "consumer/taxpayerBind_info";
	}

	@RequiresPermissions("nsqygl:bdxxcx")
	@RequestMapping("/consumerManager/consumer/taxpayerBind_pwdlog.php")
	public String taxpayerBindpwdlog(TaxpayerBindRq taxpayerBindRq,
								   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		TaxpayerBindPwdLogRs taxpayerBindPwdLogRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAXPAYERBIND_PWDLOG, taxpayerBindRq,
				TaxpayerBindPwdLogRs.class);
		model.addAttribute("taxpayerBindRs", taxpayerBindPwdLogRs.getDataList());
		taxpayerBindRq.setTotalItems(taxpayerBindPwdLogRs.getTotal());
		taxpayerBindRq.calculate();
		model.addAttribute("BaseRq", taxpayerBindRq);
		return "consumer/taxpayerBind_pwdlog";
	}

	@RequiresPermissions("nsqygl:bdxxcx")
	@RequestMapping("/consumerManager/consumer/taxpayerBind_nsrxx.php")
	public String taxpayerBind_nsr(@RequestParam(value = "id") String id,
								   @RequestParam(value = "type") String type,
								   @RequestParam(value = "nsrsbh") String nsrsbh,
								   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		if ("dzsb".equals(type)) {
			UserDzsbRs userDzsb = SoaConnectionFactory.getRestful(request,
					ConstantsUri.TAXPAYERBIND_DZSB, null, UserDzsbRs.class, id);
			model.addAttribute("userDzsb", userDzsb.getData());
		}
		if ("hnds".equals(type)) {
			UserHndsRs userHnds = SoaConnectionFactory.getRestful(request,
					ConstantsUri.TAXPAYERBIND_HNDS, null, UserHndsRs.class, id);
			model.addAttribute("userHnds", userHnds.getData());
		}
		if ("hngs".equals(type)) {
			UserHngsRs userHngs = SoaConnectionFactory.getRestful(request,
					ConstantsUri.TAXPAYERBIND_HNGS, null, UserHngsRs.class, id);
			model.addAttribute("userHngs", userHngs.getData());
		}
		NsqyglBO bo = new NsqyglBO();
		bo.setNsrsbh(nsrsbh);
		Map<String, String> body = new HashMap<>() ;
		body.put("nsrsbh",nsrsbh);
		String json = JSON.toJSONString(body);
		SzhdxxRs rs = SoaConnectionFactory.post(request, ConstantsUri.SZHDXX_LIST, getEncryptBody(json,request), SzhdxxRs.class);
		SzhdxxBO nsrjcxxvo = rs.getNsrjcxxvo();
		if(!StringUtils.isEmpty(nsrjcxxvo)){
			if (!StringUtils.isEmpty(nsrjcxxvo.getDhhm())
					&& nsrjcxxvo.getDhhm().length() >= 8) {
				nsrjcxxvo.setDhhm(CommonUtils.getPhoneBluring(nsrjcxxvo.getDhhm()));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getFrYddhhm())
					&& nsrjcxxvo.getFrYddhhm().length() >= 8) {
				nsrjcxxvo.setFrYddhhm(CommonUtils.getPhoneBluring(nsrjcxxvo.getFrYddhhm()));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getCwfzrYddhhm())
					&& nsrjcxxvo.getCwfzrYddhhm().length() >= 8) {
				nsrjcxxvo.setCwfzrYddhhm(CommonUtils.getPhoneBluring(nsrjcxxvo.getCwfzrYddhhm()));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getBsrDhhm())
					&& nsrjcxxvo.getBsrDhhm().length() >= 8) {
				nsrjcxxvo.setBsrDhhm(CommonUtils.getPhoneBluring(nsrjcxxvo.getBsrDhhm()));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getBsrYddhhm())
					&& nsrjcxxvo.getBsrYddhhm().length() >= 8) {
				nsrjcxxvo.setBsrYddhhm(CommonUtils.getPhoneBluring(nsrjcxxvo.getBsrYddhhm()));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getZjhm())
					&& nsrjcxxvo.getZjhm().length() >= 8) {
				nsrjcxxvo.setZjhm(CommonUtils.replaceWithSpecialChar(nsrjcxxvo.getZjhm(), 4, 4, "*"));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getBsrZjhm())
					&& nsrjcxxvo.getBsrZjhm().length() >= 8) {
				nsrjcxxvo.setBsrZjhm(CommonUtils.replaceWithSpecialChar(nsrjcxxvo.getBsrZjhm(), 4, 4, "*"));
			}
			if (!StringUtils.isEmpty(nsrjcxxvo.getCwfzrZjhm())
					&& nsrjcxxvo.getCwfzrZjhm().length() >= 8) {
				nsrjcxxvo.setCwfzrZjhm(CommonUtils.replaceWithSpecialChar(nsrjcxxvo.getCwfzrZjhm(), 4,4, "*"));
			}

			List<tzfBO> tzfList = nsrjcxxvo.getTzfList();
			if(!StringUtils.isEmpty(tzfList)&&tzfList.size()>0){
				for(tzfBO tzf:tzfList){
					if (!StringUtils.isEmpty(tzf.getZjhm())
							&& tzf.getZjhm().length() >= 8) {
						tzf.setZjhm(CommonUtils.replaceWithSpecialChar(tzf.getZjhm(), 4,4, "*"));
					}
				}
			}
			List<kkyhBO> kkyhList = nsrjcxxvo.getKkyhList();
			if(!StringUtils.isEmpty(kkyhList)&&kkyhList.size()>0){
				for(kkyhBO kkyh : kkyhList){
					if (!StringUtils.isEmpty(kkyh.getKkyhzh())&&kkyh.getKkyhzh().length()>=8){
						kkyh.setKkyhzh(CommonUtils.replaceWithSpecialChar(kkyh.getKkyhzh(), 4, 4, "*"));
					}
				}
			}
		}
		model.addAttribute("szhdxxRs", rs);
		return "consumer/taxpayerBind_nsrxx";
	}

	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/consumer/userTags.php")
	public String userTags(@RequestParam(value = "id") String id,
						   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));

		ConsumerInfoRs consumerInfoRs = SoaConnectionFactory.getRestful(
				request, ConstantsUri.CONSUMER_INFO, null,
				ConsumerInfoRs.class, id);
		model.addAttribute("consumerInfoRs", consumerInfoRs);

		TagsRs usertagsRs = SoaConnectionFactory.getRestful(request,
				ConstantsUri.CONSUMER_TAGS, null, TagsRs.class, id);
		List<Tag> usertags = usertagsRs.getDataList();
		model.addAttribute("usertags", usertags);

		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());

		TagsRq tagsRq = new TagsRq();
		tagsRq.setPage(0);
		tagsRq.setSize(0);
		tagsRq.setType("user");
		TagsRs tagsRs = SoaConnectionFactory.get(request,
				ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
		List<Tag> tags = tagsRs.getDataList();

		List<Tag> untags = new ArrayList<Tag>();

		for (Tag tag : tags) {
			if(!tag.isStatus()){continue;}
			Boolean has = false;
			for (Tag tag1 : usertags) {
				if (tag1.getId().equals(tag.getId())) {
					has = true;
					break;
				}
			}
			if (!has) {
				untags.add(tag);
			}
		}
		model.addAttribute("tags", untags);

		model.addAttribute("taglib", getDictBOList(request, "taglib"));

		return "consumer/uerTags";
	}

	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/consumer/edituserTags.php")
	public @ResponseBody
	TagsRs edituserTags(@RequestParam(value = "id") String id,
						@RequestParam(value = "tagId") String tagId,
						@RequestParam(value = "doType") String doType,
						HttpServletRequest request) {

		if ("selected".equals(doType)) {
			return SoaConnectionFactory.deleteRestful(request,
					ConstantsUri.CONSUMER_TAGSDEL, null, TagsRs.class,
					new Object[] { id, tagId });
		} else {
			return SoaConnectionFactory.post(request,
					ConstantsUri.CONSUMER_TAGADD, null, TagsRs.class,
					new Object[] { id, tagId });
		}
	}

	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/consumer/batchTags.php")
	public @ResponseBody
	BaseResponse batchTags(@RequestBody BatchTagInsertRq batchTagInsertRq,
						   HttpServletRequest request) {
		return SoaConnectionFactory.post(request,
				ConstantsUri.CONSUMERS_TAGBATCH, batchTagInsertRq,
				BaseResponse.class, "user");
	}

	/**
	 * 实名列表信息查询
	 *
	 * @return
	 */
	@RequiresPermissions("consumer:truename")
	@RequestMapping("/consumerManager/trueName_list.php")
	public String trueNameListQuery(RealNameValidaRq nameValidaRq,
									HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		RealNameValidaRs rs = SoaConnectionFactory.get(request,
				ConstantsUri.REALNAMEVALIDA_LIST, nameValidaRq,
				RealNameValidaRs.class);
		model.addAttribute("consumers", rs.getDataList());
		nameValidaRq.setTotalItems(rs.getTotal());
		nameValidaRq.calculate();
		model.addAttribute("BaseRq", nameValidaRq);
		return "consumer/trueName/list";
	}

	/**
	 * 实名信息查询
	 *
	 * @return
	 */
	@RequiresPermissions("consumer:truename")
	@RequestMapping("/consumerManager/trueName_query.php")
	public String trueNameQuery(String userId, HttpServletRequest request,
								Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));

		ConsumerInfoRs consumerInfoRs = SoaConnectionFactory.getRestful(
				request, ConstantsUri.CONSUMER_INFO, null,
				ConsumerInfoRs.class, userId);
		model.addAttribute("consumerInfoRs", consumerInfoRs);

		return "consumer/trueName/info";
	}

	/**
	 * 实名认证
	 *
	 * @return
	 */
	@RequiresPermissions("consumer:truename")
	@RequestMapping("/consumerManager/trueName_save.php")
	public @ResponseBody
	BaseResponse trueNameSave(String userId, String validStatus,String remark,
							  HttpServletRequest request, Model model) {
		ConsumerExtend extend=new ConsumerExtend();
		extend.setRemark(remark);
		return SoaConnectionFactory.put(request,
				ConstantsUri.REALNAMEVALIDA_VALIDA, extend, BaseResponse.class,
				new Object[] { userId, validStatus });
	}


	/* 通过用户名或者手机号查询用户 */
	@RequiresPermissions("consumer:query")
	@GetMapping("/consumerManager/consumer/info/{usernameOrPhone}.php")
	public @ResponseBody BaseResponse viewByNameOrPhone(HttpServletRequest request, @PathVariable String usernameOrPhone){
		ConsumerInfoRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.CONSUMER_INFO_BY_NAME_PHONE, null , ConsumerInfoRs.class, usernameOrPhone);
		return rs;
	}

	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/editphone.php")
	public @ResponseBody
	BaseResponse editphone(String userId, String phone,String reason,String username,
						   HttpServletRequest request, Model model) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("phone", StringUtils.isEmpty(phone)?null:phone);
		map.put("reason", StringUtils.isEmpty(reason)?null:reason);
		map.put("username", StringUtils.isEmpty(username)?null:username);
		return SoaConnectionFactory.put(request,
				ConstantsUri.USER_EDITPHONE, map, BaseResponse.class,
				new Object[] { userId });
	}

	@RequiresPermissions("consumer:manage")
	@RequestMapping("/consumerManager/editpwd.php")
	public @ResponseBody
	BaseResponse editpwd(String userId, String reason,
						   HttpServletRequest request, Model model) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("userId", StringUtils.isEmpty(userId)?null:userId);
		map.put("reason", StringUtils.isEmpty(reason)?null:reason);
		return SoaConnectionFactory.put(request,
				ConstantsUri.USER_RESETPWD, map, BaseResponse.class);
	}
}
