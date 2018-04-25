package com.abc.controller.SoaManager;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.soa.ApiRq;
import com.abc.soa.response.soa.ApiRs;
import com.abc.soa.response.soa.bo.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * API管理接口
 * @author  2017-11-6
 *
 */
@Controller
public class ApiController extends BaseController{
	/**
	 * API列表
	 * @param apiRq 条件
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("soa:api")
	@RequestMapping("/api/list.php")
	public String appList(ApiRq apiRq, HttpServletRequest request,Model model) throws IOException {
		if(apiRq.getUrl()!=null){
			apiRq.setUrl(new BASE64Encoder().encode(apiRq.getUrl().getBytes()));
		}
		ApiRs appRs = SoaConnectionFactory.get(request,
				ConstantsUri.API_LIST, apiRq, ApiRs.class);
		
		request.setAttribute("apis", appRs.getDataList());
		apiRq.setTotalItems(appRs.getTotal());
		apiRq.calculate();
		if(apiRq.getUrl()!=null){
			apiRq.setUrl(new String(new BASE64Decoder().decodeBuffer(apiRq.getUrl())));
		}
		request.setAttribute("BaseRq", apiRq);
		model.addAttribute("apps", getDictBOList(request, "apiSystem"));
		return "soa/api/list";
	}
	
	/**
	 * API新增编辑
	 * @param dpType 操作类型
	 * @param id 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("soa:api")
	@RequestMapping("/api/edit.php")
	public String editApi(String dpType,@RequestParam(required=false) String id,
			HttpServletRequest request,Model model) {
		if("2".equals(dpType)){
			ApiRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.API_INFO, null, ApiRs.class,id);
			model.addAttribute("api", appRs.getData());
		}
		
		model.addAttribute("apps", getDictBOList(request, "apiSystem"));
		model.addAttribute("dpType", dpType);
		return "soa/api/edit";
	}
	
	/**
	 * API保存
	 * @param app
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:api")
	@PostMapping("/api/save.php")
	public @ResponseBody BaseResponse saveApi(@RequestBody Api app,HttpServletRequest request){	
		if(CommonUtils.nullOrBlank(app.getId())){
			return  SoaConnectionFactory.post(request, ConstantsUri.API_ADD, app, BaseResponse.class);
		}else{
			return  SoaConnectionFactory.put(request, ConstantsUri.API_EDIT, app, BaseResponse.class,app.getId());
		}	
	}
	
	/**
	 * API停用启用
	 * @param id
	 * @param status
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:api")
	@PostMapping("/api/enableApi.php")
	public @ResponseBody BaseResponse enableApi(String id ,boolean status,HttpServletRequest request){
		ApiRs appRs=SoaConnectionFactory.getRestful(request, ConstantsUri.API_INFO, null, ApiRs.class,id);
		Api app=appRs.getData();
		app.setStatus(status);
		return  SoaConnectionFactory.put(request, ConstantsUri.API_EDIT, app, BaseResponse.class,app.getId());
	}
	
	/**
	 * API删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("soa:api")
	@PostMapping("/api/del.php")
	public @ResponseBody BaseResponse delApi(@RequestParam(value = "id") String id,HttpServletRequest request){		
		return  SoaConnectionFactory.delete(request, ConstantsUri.API_DEL, null, BaseResponse.class,id);
	}
}
