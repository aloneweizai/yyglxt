package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.*;
import com.abc.soa.response.consumer.*;
import com.abc.soa.response.consumer.bo.*;
import com.abc.soa.response.system.bo.DictBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * 用户参数管理
 * Created by zhushuai on 2017/6/16.
 */
@Controller
public class ParametersController extends BaseController{
	
	
	
	/**
	 * 积分规则
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/pointrule/list.php")
    public String pointruleList(PointRuleRq pointRuleRq , HttpServletRequest request,Model model){
		PointRuleRs pointRuleRs=SoaConnectionFactory.get(request, ConstantsUri.POINTRULE_LIST, pointRuleRq, PointRuleRs.class);
		model.addAttribute("pointRules",pointRuleRs.getDataList());
		pointRuleRq.setTotalItems(pointRuleRs.getTotal());
		pointRuleRq.calculate();
        model.addAttribute("BaseRq", pointRuleRq);
        return "consumer/parameterMag/point/pointrule_List";
    }
	/**
	 * 积分规则新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/pointrule/edit.php")
	public String editPointrule(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		if("2".equals(dpType)){
			PointRuleRs pointRuleRs=SoaConnectionFactory.getRestful(request, ConstantsUri.POINTRULE_INFO, null, PointRuleRs.class,id);
			model.addAttribute("pointRule", pointRuleRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/point/pointrule_edit";
	}
	/**
	 * 积分规则新增，修改保存
	 * @param pointRule
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/pointrule/save.php")
	public @ResponseBody BaseResponse savePointrule(@RequestBody PointRule pointRule,HttpServletRequest request){
		BaseResponse rs = null;
		pointRule.setCode("P-"+pointRule.getCode());
		if(CommonUtils.nullOrBlank(pointRule.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.POINTRULE_ADD, pointRule, PointRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.POINTRULE_EDIT, pointRule, PointRule.class,pointRule.getId());
        }
		return rs;
	}
	
	
	/**
	 * 经验值规则
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/exprule/list.php")
    public String expruleList(ExpRuleRq pointRuleRq , HttpServletRequest request,Model model){
		ExpRuleRs expRuleRs=SoaConnectionFactory.get(request, ConstantsUri.EXPRULE_LIST, pointRuleRq, ExpRuleRs.class);
		model.addAttribute("pointRules",expRuleRs.getDataList());
		pointRuleRq.setTotalItems(expRuleRs.getTotal());
		pointRuleRq.calculate();
        model.addAttribute("BaseRq", pointRuleRq);
        
        model.addAttribute("clienttype", getDictBOList(request, "clienttype"));
        
        return "consumer/parameterMag/exp/exprule_List";
    }
	
	@RequestMapping("/consumerManager/expcodex/{expruleid}")
	public @ResponseBody List<ExpCodex> getExpCodexs(HttpServletRequest request,@PathVariable("expruleid") String expruleid){
		ExpCodexRs rs=SoaConnectionFactory.getRestful(request, ConstantsUri.EXP_CODEXQ, null, ExpCodexRs.class, expruleid);
		return rs.getDataList();
	}
	
	@PostMapping("/consumerManager/expcodex/save/{expruleid}")
	public @ResponseBody BaseResponse expCodexsSave(@PathVariable("expruleid") String expruleid,
			             @RequestBody List<ExpCodex> codexs,HttpServletRequest request){
		return SoaConnectionFactory.post(request, ConstantsUri.EXP_CODEXA, codexs, BaseResponse.class, expruleid);
	}
	
	
	
	/**
	 * 积分规则新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/exprule/edit.php")
	public String editExprule(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		if("2".equals(dpType)){
			ExpRuleRs expRuleRs=SoaConnectionFactory.getRestful(request, ConstantsUri.EXPRULE_INFO, null, ExpRuleRs.class,id);
			model.addAttribute("pointRule", expRuleRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/exp/exprule_edit";
	}
	
	/**
	 * 经验值规则新增，修改保存
	 * @param
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/exprule/save.php")
	public @ResponseBody BaseResponse saveExprule(@RequestBody ExpRule expRule,HttpServletRequest request){
		BaseResponse rs = null;
		expRule.setCode("E-"+expRule.getCode());
		if(CommonUtils.nullOrBlank(expRule.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.EXPRULE_ADD, expRule, ExpRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.EXPRULE_EDIT, expRule, ExpRule.class,expRule.getId());
        }
		return rs;
	}
	
	
	/**
	 * VIPLEVEL
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/viplevel/list.php")
    public String viplevelList(VipLevelRq levelRq , HttpServletRequest request,Model model){
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));
		model.addAttribute("referer", request.getHeader("Referer"));
		VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("pointRules",levelRs.getDataList());
		levelRq.setTotalItems(levelRs.getTotal());
		levelRq.calculate();
        model.addAttribute("BaseRq", levelRq);
        
        VipPrivilegeRq privilege=new VipPrivilegeRq();
        privilege.setStatus(true);
        privilege.setPage(0);
        privilege.setSize(0);
        VipPrivilegeRs vipPrivilege=SoaConnectionFactory.get(request, ConstantsUri.VIPPRIVILEGE_LIST, privilege, VipPrivilegeRs.class);
		model.addAttribute("vipPrivileges",vipPrivilege.getDataList());
        
        return "consumer/parameterMag/viplevel/list";
    }
	
	/**
	 * VIPLEVEL json
	 * @param levelRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/viplevel/jsonlist.php")
    public @ResponseBody List<VipLevel> viplevelJsonList(VipLevelRq levelRq , HttpServletRequest request,Model model){
		VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        return levelRs.getDataList();
    }
	
	/**
	 * VIPLEVEL新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/viplevel/edit.php")
	public String editViplevel(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));
		model.addAttribute("referer", request.getHeader("Referer"));
		if("2".equals(dpType)){
			VipLevelRs levelRs=SoaConnectionFactory.getRestful(request, ConstantsUri.VIPLEVEL_INFO, null, VipLevelRs.class,id);
			model.addAttribute("pointRule", levelRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/viplevel/edit";
	}
	/**
	 * VIPLEVEL新增，修改保存
	 * @param
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/viplevel/save.php")
	public @ResponseBody BaseResponse saveViplevel(@RequestBody VipLevel vipLevel,HttpServletRequest request){
		String levelCode=vipLevel.getLevelCode();
		if(!levelCode.matches("^VIP\\d{1,}$")){
			return new BaseResponse("9999", "会员等级代码请以VIP开头，并且只能接整数!");
		}
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(vipLevel.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.VIPLEVEL_ADD, vipLevel, ExpRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.VIPLEVEL_EDIT, vipLevel, ExpRule.class,vipLevel.getId());
        }
		return rs;
	}
	
	
	/**
	 * vipPrivilege
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/vipPrivilege/list.php")
    public String vipPrivilegeList(VipPrivilegeRq privilege , HttpServletRequest request,Model model){
		VipPrivilegeRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPPRIVILEGE_LIST, privilege, VipPrivilegeRs.class);
		model.addAttribute("pointRules",levelRs.getDataList());
		privilege.setTotalItems(levelRs.getTotal());
		privilege.calculate();
		
		VipLevelRq levelRq=new VipLevelRq();
		levelRq.setStatus(true);
		levelRq.setPage(0);
		levelRq.setSize(0);
		VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levels", level.getDataList());
		
        model.addAttribute("BaseRq", privilege);
        return "consumer/parameterMag/vipPrivilege/list";
    }
	/**
	 * vipPrivilege新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/vipPrivilege/edit.php")
	public String editVipPrivilege(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		if("2".equals(dpType)){
			VipPrivilegeRs levelRs=SoaConnectionFactory.getRestful(request, ConstantsUri.VIPPRIVILEGE_INFO, null, VipPrivilegeRs.class,id);
			model.addAttribute("pointRule", levelRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/vipPrivilege/edit";
	}
	/**
	 * vipPrivilege新增，修改保存
	 * @param
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/vipPrivilege/save.php")
	public @ResponseBody BaseResponse saveVipPrivilege(@RequestBody VipPrivilege privilege,HttpServletRequest request){
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(privilege.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.VIPPRIVILEGE_ADD, privilege, ExpRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.VIPPRIVILEGE_EDIT, privilege, ExpRule.class,privilege.getId());
        }
		return rs;
	}
	
	/**
	 * 删除
	 * @param delType 1.pointRule 2.expRule 3.vipLevel 4.vipPrivilege 5.Tag 6 explevel
	 * @param request
	 * @return
	 */
	@RequestMapping("/consumerManager/del.php")
	public @ResponseBody BaseResponse delVipPrivilege(int delType,String id,HttpServletRequest request){
		switch (delType){
		  case 1:
			  return SoaConnectionFactory.delete(request, ConstantsUri.POINTRULE_EDIT, null, BaseResponse.class,id);
		  case 2:
			  return SoaConnectionFactory.delete(request, ConstantsUri.EXPRULE_EDIT, null, BaseResponse.class,id);
		  case 3:
			  return SoaConnectionFactory.delete(request, ConstantsUri.VIPLEVEL_INFO, null, BaseResponse.class,id);
		  case 4:
			  return SoaConnectionFactory.delete(request, ConstantsUri.VIPPRIVILEGE_DEL, null, BaseResponse.class,id);
		  case 5:
			  return SoaConnectionFactory.delete(request, ConstantsUri.TAG_DEL, null, BaseResponse.class,id);
			case 7:
				return SoaConnectionFactory.delete(request, ConstantsUri.SUBSCRIPTION_DEL, null, BaseResponse.class, id);
		  default:
			  return SoaConnectionFactory.delete(request, ConstantsUri.UEXPLEVEL_DEL, null, BaseResponse.class,id);
		}
		
	}
	
	/**
	 * 启用禁用
	 * @param delType
	 * @param id
	 * @param status
	 * @param request
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("/consumerManager/enable.php")
	public @ResponseBody BaseResponse enable(int delType,String id,boolean status,HttpServletRequest request){
		switch (delType){
		  case 1:
			  return SoaConnectionFactory.putRestful(request, ConstantsUri.POINTRULE_ENABLE, null, BaseResponse.class,new Object[]{id,status});
		  case 2:
			  return SoaConnectionFactory.putRestful(request, ConstantsUri.EXPRULE_ENABLE, null, BaseResponse.class,new Object[]{id,status});
		  case 3:
			  return SoaConnectionFactory.putRestful(request, ConstantsUri.VIPLEVEL_ENABLE, null, BaseResponse.class,new Object[]{id,status});
		  case 4:
			  return SoaConnectionFactory.putRestful(request, ConstantsUri.VIPPRIVILEGE_ENABLE, null, BaseResponse.class,id);
		  case 5:
			  return SoaConnectionFactory.putRestful(request, ConstantsUri.TAG_ENABLE, null, BaseResponse.class,new Object[]{id,status});
		  default:
			  return SoaConnectionFactory.putRestful(request, ConstantsUri.UEXPLEVEL_ENABLE, null, BaseResponse.class,new Object[]{id,status});
		}
		
	}
	
	
	/**
	 * tag
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/tag/list.php")
    public String tagList(TagsRq tagsRq , HttpServletRequest request,Model model){
		TagsRs tagsRs=SoaConnectionFactory.get(request, ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
		model.addAttribute("pointRules",tagsRs.getDataList());
		tagsRq.setTotalItems(tagsRs.getTotal());
		tagsRq.calculate();
        model.addAttribute("BaseRq", tagsRq);
        
        model.addAttribute("DictBOs", getDictBOList(request, "taglib"));
        model.addAttribute("tagtype", getDictBOList(request, "tagtype"));
        
        return "consumer/parameterMag/tag/list";
    }
	
	/**
	 * tag新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/tag/edit.php")
	public String editTag(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		if("2".equals(dpType)){
			TagsRs levelRs=SoaConnectionFactory.getRestful(request, ConstantsUri.TAG_INFO, null, TagsRs.class,id);
			model.addAttribute("pointRule", levelRs.getData());
		}
		model.addAttribute("taglib", getDictBOList(request, "taglib"));
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/tag/edit";
	}
	/**
	 * tag新增，修改保存
	 * @param
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/tag/save.php")
	public @ResponseBody BaseResponse saveTag(@RequestBody Tag privilege,HttpServletRequest request){
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(privilege.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.TAG_ADD, privilege, ExpRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.TAG_EDIT, privilege, ExpRule.class,privilege.getId());
        }
		return rs;
	}
	
	
	
	/**
	 * honour
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/honour/list.php")
    public String honourList(ExpLevelRq expLevelRq, HttpServletRequest request,Model model){
		ExpLevelRs expLevelRs=SoaConnectionFactory.get(request, ConstantsUri.UEXPLEVEL_LIST, expLevelRq, ExpLevelRs.class);
		model.addAttribute("pointRules", expLevelRs.getDataList());
		expLevelRq.setTotalItems(expLevelRs.getTotal());
		expLevelRq.calculate();
		model.addAttribute("BaseRq", expLevelRq);
        return "consumer/parameterMag/honour/list";
    }
	
	
	/**
	 * honour新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/honour/edit.php")
	public String editHonour(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		if("2".equals(dpType)){
			ExpLevelRs levelRs=SoaConnectionFactory.getRestful(request, ConstantsUri.UEXPLEVEL_INFO, null, ExpLevelRs.class,id);
			model.addAttribute("pointRule", levelRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/honour/edit";
	}
	/**
	 * honour新增，修改保存
	 * @param
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/honour/save.php")
	public @ResponseBody BaseResponse saveHonour(@RequestBody ExpLevel expLevel,HttpServletRequest request){
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(expLevel.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.UEXPLEVEL_ADD, expLevel, ExpRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.UEXPLEVEL_EDIT, expLevel, ExpRule.class,expLevel.getId());
        } 
		return rs;
	}
	
	
	@GetMapping("/consumerManager/vipprivilegelevel/{id}")
	public @ResponseBody VipprivilegelevelRs getByVipPrivilege(@PathVariable("id") String id,
			HttpServletRequest request){
		VipprivilegelevelReq req=new VipprivilegelevelReq();
		req.setPrivilege(id);
		req.setPage(0);
		req.setSize(0);
		return SoaConnectionFactory.get(request, ConstantsUri.PRIVLEGE_BYID, req, VipprivilegelevelRs.class);
	}
	
	@GetMapping("/consumerManager/vip/{id}")
	public @ResponseBody VipprivilegelevelRs getByVip(@PathVariable("id") String id,
			HttpServletRequest request){
		VipprivilegelevelReq req=new VipprivilegelevelReq();
		req.setLevel(id);
		req.setPage(0);
		req.setSize(0);
		return SoaConnectionFactory.get(request, ConstantsUri.PRIVLEGE_BYLEVEL, req, VipprivilegelevelRs.class);
	}
	
	@PostMapping("/consumerManager/vipprivilegelevel/save/{id}")
	public @ResponseBody BaseResponse save(@PathVariable("id") String id,
			             @RequestBody List<VipPrivilegeLevelUpdateBO> levelUpdateBOs,HttpServletRequest request){
		return SoaConnectionFactory.post(request, ConstantsUri.PRIVLEGE_PADD, levelUpdateBOs, BaseResponse.class, id);
	}
	
	@PostMapping("/consumerManager/leve/save/{id}")
	public @ResponseBody BaseResponse leveSave(@PathVariable("id") String id,
			             @RequestBody List<VipPrivilegeLevelUpdateBO> levelUpdateBOs,HttpServletRequest request){
		return SoaConnectionFactory.post(request, ConstantsUri.PRIVLEGE_LADD, levelUpdateBOs, BaseResponse.class, id);
	}


	/**
	 * subscription
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/subscription/list.php")
	public String subscriptionList(SubscriptionRq subscriptionRq, HttpServletRequest request,Model model){
		SubscriptionRs subscriptionRs=SoaConnectionFactory.get(request, ConstantsUri.SUBSCRIPTION_LIST, subscriptionRq, SubscriptionRs.class);
		model.addAttribute("subscriptionRs", subscriptionRs.getDataList());
		subscriptionRq.setTotalItems(subscriptionRs.getTotal());
		subscriptionRq.calculate();
		model.addAttribute("BaseRq", subscriptionRq);
		model.addAttribute("busiType", getDictBOList(request, "busiType"));
		List<DictBO> dictBOs = getDictBOList(request, "msgType");
		List<Subscription> dataList = subscriptionRs.getDataList();
		for(DictBO dict:dictBOs){
			for(Subscription sub:dataList){
				if(sub.getType().equals(dict.getFieldValue())){
					dict.setFlag(true);
				}
			}
		}
		model.addAttribute("msgType", dictBOs);
		return "consumer/parameterMag/subscription/list";
	}


	/**
	 * subscription新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/consumerManager/subscription/edit.php")
	public String editSubscription(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("busiType", getDictBOList(request, "busiType"));
		model.addAttribute("msgType", getDictBOList(request, "msgType"));
		if("2".equals(dpType)){
			SubscriptionRs subscriptionRs=SoaConnectionFactory.getRestful(request, ConstantsUri.SUBSCRIPTION_INFO, null, SubscriptionRs.class,id);
			model.addAttribute("subscriptionRs", subscriptionRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/parameterMag/subscription/edit";
	}
	/**
	 * subscription新增，修改保存
	 * @param
	 * @param request
	 * @return
	 */
	@PostMapping("/consumerManager/subscription/save.php")
	public @ResponseBody BaseResponse saveSubscription(@RequestBody Subscription subscription,HttpServletRequest request){
		BaseResponse rs = null;
		if(CommonUtils.nullOrBlank(subscription.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.SUBSCRIPTION_ADD, subscription, ExpRule.class);
		}else{
			rs = SoaConnectionFactory.put(request, ConstantsUri.SUBSCRIPTION_ADD, subscription, ExpRule.class,subscription.getId());
		}
		return rs;
	}
}
