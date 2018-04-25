package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.ExpRuleRq;
import com.abc.soa.request.consumer.PointRuleRq;
import com.abc.soa.request.consumer.SysTaskRq;
import com.abc.soa.response.consumer.ExpRuleRs;
import com.abc.soa.response.consumer.PointRuleRs;
import com.abc.soa.response.consumer.SysTaskRs;
import com.abc.soa.response.consumer.bo.ExpRule;
import com.abc.soa.response.consumer.bo.PointRule;
import com.abc.soa.response.consumer.bo.SysTask;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * 系统任务
 * @author zhushuai 2017-6-20
 *
 */
@Controller
public class SysTasksController extends BaseController{
	/**
	 * 任务列表
	 * @param sysTaskRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:systask")
	@RequestMapping("/consumerManager/systask/list.php")
    public String list(SysTaskRq sysTaskRq , HttpServletRequest request,Model model){
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));
        SysTaskRs sysTaskRs=SoaConnectionFactory.get(request, ConstantsUri.SYSTASK_LIST, sysTaskRq, SysTaskRs.class);
        model.addAttribute("sysTasks",sysTaskRs.getDataList());
        sysTaskRq.setTotalItems(sysTaskRs.getTotal());
        sysTaskRq.calculate();
        model.addAttribute("BaseRq", sysTaskRq);              
        model.addAttribute("DictBOs",getDictBOList(request, "tasktype"));       
        return "consumer/systask/list";
    }
	
	/**
	 * 任务新增，修改
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("consumer:systask")
	@RequestMapping("/consumerManager/systask/edit.php")
	public String editSysTask(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("abc.soa-upload-url"));
		PointRuleRq pointRuleRq=new PointRuleRq();
		pointRuleRq.setSize(0);
		pointRuleRq.setPage(0);
		List<PointRule> points=SoaConnectionFactory.get(request, ConstantsUri.POINTRULE_LIST, pointRuleRq, PointRuleRs.class).getDataList();
		model.addAttribute("points", points);
		
		ExpRuleRq expRuleRq=new ExpRuleRq();
		expRuleRq.setPage(0);
		expRuleRq.setSize(0);
		List<ExpRule> exps=SoaConnectionFactory.get(request, ConstantsUri.EXPRULE_LIST, pointRuleRq, ExpRuleRs.class).getDataList();
		model.addAttribute("exps", exps);
		
		if("2".equals(dpType)){
			SysTaskRs pointRuleRs=SoaConnectionFactory.getRestful(request, ConstantsUri.SYSTASK_INFO, null, SysTaskRs.class,id);
			model.addAttribute("sysTask", pointRuleRs.getData());
		}
		model.addAttribute("dpType", dpType);
		return "consumer/systask/edit";
	}
	
	/**
	 * 任务新增，修改保存
	 * @param sysTask
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequiresPermissions("consumer:systask")
	@PostMapping("/consumerManager/sysTask/save.php")
	public @ResponseBody BaseResponse saveSysTask(@RequestBody SysTask sysTask,HttpServletRequest request) throws ParseException{
		BaseResponse rs = null;
		if(!StringUtils.isEmpty(sysTask.getStartT())){
			sysTask.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sysTask.getStartT()));
		}
        if(!StringUtils.isEmpty(sysTask.getEndT())){
        	sysTask.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sysTask.getEndT()));
		}
		if(CommonUtils.nullOrBlank(sysTask.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.SYSTASK_ADD, sysTask, PointRule.class);
        }else{
            rs = SoaConnectionFactory.put(request, ConstantsUri.SYSTASK_EDIT, sysTask, PointRule.class,sysTask.getId());
        }
		return rs;
	}
	
	/**
	 * task删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("consumer:systask")
	@PostMapping("/consumerManager/sysTask/del.php")
	public @ResponseBody BaseResponse delSysTask(String id,HttpServletRequest request){
		return SoaConnectionFactory.delete(request, ConstantsUri.SYSTASK_DEL, null, BaseResponse.class,id);
	}
	
}
