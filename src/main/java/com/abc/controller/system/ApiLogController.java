package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.DateUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.ApiLogRq;
import com.abc.soa.request.system.MessageSendLogRq;
import com.abc.soa.request.system.bo.MessageSendLogBtAlRq;
import com.abc.soa.response.soa.AppRs;
import com.abc.soa.response.system.ApiLogRs;
import com.abc.soa.response.system.MessageQueryLogRs;
import com.abc.soa.response.system.MessageSendLogRs;
import com.abc.soa.response.system.bo.ApiLogBO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 接口访问日志
 * @author zhushuai 2017-10-18
 *
 */
@Controller
public class ApiLogController {
	 /**
	  * APP接口访问总数
	  * @param apiLogRq
	  * @param request
	  * @param model
	  * @return
	  */
	 @RequestMapping("/apilog/appCounts")
     public String appCounts(ApiLogRq apiLogRq, HttpServletRequest request, Model model){
		 if(StringUtils.isEmpty(apiLogRq.getStartTime())){
			 apiLogRq.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		 }
		 apiLogRq.setPage(0);
		 apiLogRq.setSize(0);
    	 ApiLogRs apiLogRs = SoaConnectionFactory.get(request, ConstantsUri.APP_TOTAL, apiLogRq, ApiLogRs.class);
         model.addAttribute("apiLogRs", JSONObject.parseArray(apiLogRs.getDataList(), ApiLogBO.class));
         apiLogRq.setTotalItems(apiLogRs.getTotal());
         apiLogRq.calculate();
         model.addAttribute("BaseRq", apiLogRq);
    	 return "system/apilog/appCounts";
     }

	/**
	 * APP接口访问总数
	 * @param startTime
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/apilog/listJson.php")
	public  @ResponseBody List<ApiLogBO> appCountsBar(@RequestParam(required=false) String startTime,HttpServletRequest request, Model model){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		ApiLogRq apiLogRq = new ApiLogRq();
		if(StringUtils.isEmpty(startTime)){
			apiLogRq.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
		else{
			apiLogRq.setStartTime(startTime);
		}
		apiLogRq.setPage(0);
		apiLogRq.setSize(0);
		ApiLogRs apiLogRs = SoaConnectionFactory.get(request, ConstantsUri.APP_TOTAL, apiLogRq, ApiLogRs.class);
		List<ApiLogBO> list=JSONObject.parseArray(apiLogRs.getDataList(), ApiLogBO.class);
		if(!StringUtils.isEmpty(list)){
			String[] tjday = new String[list.size()];
			for(int i=0;i<list.size();i++){
				String[] arr = new String[2];
				arr[0] = list.get(i).getNickname();
				arr[1] = list.get(i).getAppAccessCount();
				tjday[i] = Arrays.toString(arr);
			}
			mav.addObject("tjday", Arrays.toString(tjday));
		}
		return list;
	}
	 
	 /**
	  * APP接口访问总数明细
	  * @param apiLogRq
	  * @param request
	  * @param model
	  * @return
	  */
	 @RequestMapping("/apilog/appApiCounts")
     public String appApiCounts(ApiLogRq apiLogRq, HttpServletRequest request, Model model){
		 if(StringUtils.isEmpty(apiLogRq.getStartTime())){
			 apiLogRq.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		 }
		 AppRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APP_INFO, null, AppRs.class,apiLogRq.getAppId());
		 model.addAttribute("app", appRs.getData());
		 
    	 ApiLogRs apiLogRs = SoaConnectionFactory.get(request, ConstantsUri.APP_API_TOTAL, apiLogRq, ApiLogRs.class);
    	 List<ApiLogBO> list=JSONObject.parseArray(apiLogRs.getDataList(), ApiLogBO.class);
    	 for(ApiLogBO apiLogBO:list){
    		 apiLogBO.setBaseuri(new BASE64Encoder().encode(apiLogBO.getUri().getBytes()));
    	 }
         model.addAttribute("apiApiLogRs", list);
         apiLogRq.setTotalItems(apiLogRs.getTotal());
         apiLogRq.calculate();
         model.addAttribute("BaseRq", apiLogRq);
    	 return "system/apilog/appApiCounts";
     }
	 
	 /**
	  * APP某接口访问明细
	  * @param apiLogRq
	  * @param request
	  * @param model
	  * @return
	  */
	 @RequestMapping("/apilog/appApiinfo")
     public String appApiinfo(ApiLogRq apiLogRq, HttpServletRequest request, Model model){
		 if(StringUtils.isEmpty(apiLogRq.getStartTime())){
			 apiLogRq.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		 }
		 AppRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APP_INFO, null, AppRs.class,apiLogRq.getAppId());
		 model.addAttribute("app", appRs.getData());
		 
    	 ApiLogRs apiLogRs = SoaConnectionFactory.get(request, ConstantsUri.APP_API_LIST, apiLogRq, ApiLogRs.class);
    	 List<ApiLogBO> list=JSONObject.parseArray(apiLogRs.getDataList(), ApiLogBO.class);
    	 for(ApiLogBO apiLogBO:list){
    		 apiLogBO.setBgtime(new Date(apiLogBO.getInTime()));
    	 }
         model.addAttribute("appApiinfoRs", list);
         apiLogRq.setTotalItems(apiLogRs.getTotal());
         apiLogRq.calculate();
         model.addAttribute("BaseRq", apiLogRq);
    	 return "system/apilog/appApiinfo";
     }
	 
	 /**
	  * 短信发送日志
	  * @param messageSendLogRq
	  * @param doType 0 第一次进入页面 1 查询
	  * @param request
	  * @return
	  */
	 @RequestMapping("/mobile/msg/sendlog")
	 public String mobileMsgsendlog(MessageSendLogRq messageSendLogRq,String doType,
			 HttpServletRequest request){
		 if("0".equals(doType)){
			 messageSendLogRq.setStart(DateUtil.getStrDate());
			 messageSendLogRq.setEnd(DateUtil.getStrDate());
		 }
		 MessageSendLogRs apiLogRs = SoaConnectionFactory.get(request, ConstantsUri.MOBILEMSG_LOG, messageSendLogRq, MessageSendLogRs.class);
		 request.setAttribute("apiLogRs", apiLogRs.getDataList());
		 messageSendLogRq.setTotalItems(apiLogRs.getTotal());
		 messageSendLogRq.calculate();
		 request.setAttribute("BaseRq", messageSendLogRq);
		 return "system/mobile/mobilesendlog";
	 }

	/**
	 * 短信发送日志
	 * @param messageSendLogRq
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobile/msg/query")
	public String mobileMsgQuery(MessageSendLogRq messageSendLogRq,
								   HttpServletRequest request){
		MessageQueryLogRs messageQueryLogRs = SoaConnectionFactory.get(request, ConstantsUri.MOBILEMSG_QUERY, messageSendLogRq, MessageQueryLogRs.class);
		request.setAttribute("messageQueryLogRs", messageQueryLogRs);
		messageSendLogRq.setTotalItems(messageQueryLogRs.getTotal());
		messageSendLogRq.calculate();
		request.setAttribute("BaseRq", messageSendLogRq);
		return "system/mobile/mobilemsgquery";
	}
	/**
	 * 微信红包活动删除
	 *
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobile/msg/sendlog/tb")
	public
	@ResponseBody
	BaseResponse tb(MessageSendLogBtAlRq messageSendLogRq,HttpServletRequest request) {
		return  SoaConnectionFactory.get(request, ConstantsUri.MOBILEMSG_LOG_TB, messageSendLogRq, BaseResponse.class);
	}
}
