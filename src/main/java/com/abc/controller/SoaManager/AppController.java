package com.abc.controller.SoaManager;


import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.soa.AppRq;
import com.abc.soa.request.soa.AppsetRq;
import com.abc.soa.response.soa.ApiRs;
import com.abc.soa.response.soa.AppRs;
import com.abc.soa.response.soa.AppSettingRs;
import com.abc.soa.response.soa.bo.Api;
import com.abc.soa.response.soa.bo.App;
import com.abc.soa.response.soa.bo.AppSetting;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * APP管理
 * @author 2017-11-6
 *
 */
@Controller
public class AppController extends BaseController{

	/**
	 * APP列表
	 * @param appRq  条件
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/list.php")
	public String appList(AppRq appRq, HttpServletRequest request,Model model,HttpSession session) {
		AppRs appRs = SoaConnectionFactory.get(request,
				ConstantsUri.APP_LIST, appRq, AppRs.class);
		request.setAttribute("apps", appRs.getDataList());
		appRq.setTotalItems(appRs.getTotal());
		appRq.calculate();
		request.setAttribute("BaseRq", appRq);
		session.setAttribute("AppRq",appRq);
		return "soa/app/list";
	}
	
	/**
	 * APP详细
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/info.php")
	public String queryApp(@RequestParam(value = "id") String id,
			HttpServletRequest request,Model model) {
		AppRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APP_INFO, null, AppRs.class,id);
		model.addAttribute("app", appRs.getData());
		return "soa/app/eidt";
	}
	
	/**
	 * APP查看
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/look.php")
	public String lookApp(@RequestParam(value = "id") String id,
			HttpServletRequest request,Model model) {
		AppRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APP_INFO, null, AppRs.class,id);
		model.addAttribute("app", appRs.getData());
		return "soa/app/look";
	}
	
	/**
	 * 新增APP
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/new.php")
	public String newApp() {
		return "soa/app/eidt";
	}
	
	
	/**
	 * 保存APP
	 * @param app
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("soa:app")
	@PostMapping("/app/save.php")
	public @ResponseBody BaseResponse saveApp(@RequestBody App app,HttpServletRequest request) throws Exception{	
		app.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(app.getStartT()));
		app.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(app.getEndT()));
		if(CommonUtils.nullOrBlank(app.getId())){
			//app.setPassword(MD5.md5(app.getPassword()));
			return  SoaConnectionFactory.post(request, ConstantsUri.APP_ADD, app, BaseResponse.class,app.getId());
		}else{
			if(app.getPassword().equals(app.getOldPassWord())){
				app.setPassword(null);
			}
			return  SoaConnectionFactory.put(request, ConstantsUri.APP_EDIT, app, BaseResponse.class,app.getId());
		}
		
	}
	/**
	 * APP启用停用
	 * @param app
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@PostMapping("/app/check.php")
	public @ResponseBody BaseResponse checkApp(App app,HttpServletRequest request){		
		return  SoaConnectionFactory.put(request, ConstantsUri.APP_ENABLE, app, BaseResponse.class,app.getId());
	}
	
	/**
	 * 批量授权APP
	 * @param appsetRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/batchSetting.php")
	public String batchSetting(AppsetRq appsetRq,HttpServletRequest request,Model model){
		AppRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APP_INFO, null, AppRs.class,appsetRq.getAppId());
		model.addAttribute("app", appRs.getData());
		
		
		
		/*ApiRq apiRq=new ApiRq();
		apiRq.setSize(1000);
		apiRq.setStatus(true);
		ApiRs apiRs = SoaConnectionFactory.get(request,
				ConstantsUri.API_LIST, apiRq, ApiRs.class);*/
		ApiRs apiRs=SoaConnectionFactory.getRestful(request, ConstantsUri.API_UNSET, null, ApiRs.class, appsetRq.getAppId());
		request.setAttribute("apis", apiRs.getDataList());
				
		model.addAttribute("apiSystem", getDictBOList(request, "apiSystem"));
		
		return "soa/app/batchSetting";
	}
	
	/**
	 * APP批量授权保存
	 * @param appId 主键
	 * @param appSettings 批量设置
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/batchSave.php")
	public @ResponseBody BaseResponse batchSave(String appId,@RequestBody List<AppSetting> appSettings,HttpServletRequest request){ 
		return SoaConnectionFactory.post(request, ConstantsUri.APPSET_BATCH, appSettings, BaseResponse.class, appId);
	}
	
	/**
	 * 获取APP批量设置
	 * @param appsetRq
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/getApiSettings.php")
	public @ResponseBody List<AppSetting> getApiSettings(AppsetRq appsetRq,HttpServletRequest request,Model model){
		appsetRq.setSize(1000);
		AppSettingRs appSettingRs = SoaConnectionFactory.get(request,
				ConstantsUri.APPSET_LIST, appsetRq, AppSettingRs.class);
		return appSettingRs.getDataList();
	}
	
	/**
	 * APP批量设置列表
	 * @param appsetRq
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/settinglist.php")
	public String settinglist(AppsetRq appsetRq,HttpServletRequest request,Model model) throws IOException{
		if(appsetRq.getUri()!=null){
			appsetRq.setUri(new BASE64Encoder().encode(appsetRq.getUri().getBytes()));
		}
		AppRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APP_INFO, null, AppRs.class,appsetRq.getAppId());
		model.addAttribute("app", appRs.getData());
		
		AppSettingRs appSettingRs = SoaConnectionFactory.get(request,
				ConstantsUri.APPSET_LIST, appsetRq, AppSettingRs.class);
		request.setAttribute("appsets", appSettingRs.getDataList());
		appsetRq.setTotalItems(appSettingRs.getTotal());
		appsetRq.calculate();
		if(appsetRq.getUri()!=null){
			appsetRq.setUri(new String(new BASE64Decoder().decodeBuffer(appsetRq.getUri())));
		}
		model.addAttribute("apiSystem", getDictBOList(request, "apiSystem"));
		AppRq appRq = (AppRq) request.getSession().getAttribute("AppRq");
		model.addAttribute("AppRq", appRq);
		request.setAttribute("BaseRq", appsetRq);
		return "soa/app/settingList";
	}
	
	/**
	 * APP授权详情
	 * @param appSetting
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/settingInfo.php")
	public String settingInfo(AppSetting appSetting,HttpServletRequest request){
		ApiRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.API_INFO, null, ApiRs.class,appSetting.getApiId());
		request.setAttribute("api", appRs.getData());
		
		AppSettingRs appSettingRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APPSET_INFO, 
				null, AppSettingRs.class, new Object[]{appSetting.getAppId(),appSetting.getId()});
		request.setAttribute("appset", appSettingRs.getData());
		return "soa/app/settingInfo";
	}
	
	/**
	 * APP未授权API列表
	 * @param appId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/unsetlist.php")
	public @ResponseBody List<Api> getUnset(String appId,HttpServletRequest request,Model model){
		ApiRs apiRs=SoaConnectionFactory.getRestful(request, ConstantsUri.API_UNSET, null, ApiRs.class, appId);
		return apiRs.getDataList();
	}
	
	/**
	 * APP设置
	 * @param appSetting
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/setting.php")
	public @ResponseBody BaseResponse setting(@RequestBody AppSetting appSetting,HttpServletRequest request){
		return SoaConnectionFactory.post(request, ConstantsUri.APPSET_ADD, appSetting, BaseResponse.class, appSetting.getAppId());
	}
	
	/**
	 * APP设置停启用
	 * @param appId 主键
	 * @param id 授权ID
	 * @param status
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/settingEnable.php")
	public @ResponseBody BaseResponse settingEnable(String appId,String id,boolean status,HttpServletRequest request){
		AppSettingRs appSettingRs=SoaConnectionFactory.getRestful(request, ConstantsUri.APPSET_INFO, 
				null, AppSettingRs.class, new Object[]{appId,id});
		AppSetting appSetting=appSettingRs.getData();
		appSetting.setStatus(status);
		return SoaConnectionFactory.putRestful(request, ConstantsUri.APPSET_EDIT, appSetting, BaseResponse.class, new Object[]{appId,id});
	}
	
	/**
	 * APP授权编辑
	 * @param appSetting
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/settingEdit.php")
	public @ResponseBody BaseResponse settingEdit(@RequestBody AppSetting appSetting,HttpServletRequest request){
		return SoaConnectionFactory.putRestful(request, ConstantsUri.APPSET_EDIT, appSetting, BaseResponse.class, new Object[]{appSetting.getAppId(),appSetting.getId()});
	}
	
	/**
	 * APP授权删除
	 * @param appSetting
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:app")
	@RequestMapping("/app/settingDel.php")
	public @ResponseBody BaseResponse settingDel(AppSetting appSetting,HttpServletRequest request){
		return SoaConnectionFactory.deleteRestful(request, ConstantsUri.APPSET_DEL, null, BaseResponse.class, new Object[]{appSetting.getAppId(),appSetting.getId()});
	}
}
