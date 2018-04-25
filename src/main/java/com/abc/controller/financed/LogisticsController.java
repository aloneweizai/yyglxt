package com.abc.controller.financed;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.LogisticsRq;
import com.abc.soa.response.financed.LogisticsRs;
import com.abc.soa.response.financed.bo.Logistics;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 物流
 * @author zhushuai 2017-6-23
 *
 */
@Controller
@RequestMapping("/financed")
public class LogisticsController {
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 物流公司列表
	 *
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:logistics")
	@RequestMapping("/logisticsList.php")
    public String LogisticsList(LogisticsRq logisticsrq, HttpServletRequest request,Model model,HttpSession session){
		LogisticsRs logisticsRs=SoaConnectionFactory.get(request, ConstantsUri.LOGISTIC_LIST, logisticsrq, LogisticsRs.class);
        model.addAttribute("logisticsRs",logisticsRs.getDataList());
        logisticsrq.setTotalItems(logisticsRs.getTotal());
        logisticsrq.calculate();
        model.addAttribute("BaseRq", logisticsrq);
		session.removeAttribute("InvoiceRepoRq");
		session.removeAttribute("InvoiceLyRq");
		session.setAttribute("LogisticsRq", logisticsrq);
		return "financed/logistics/list";  	
    }
	
	/**
	 * 跳转到新增，修改页面
	 * @param dpType 1：新增 2：修改
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:logistics")
	@RequestMapping("/logisticsEdit.php")
	public String editPointrule(String dpType,@RequestParam(required=false) String id,HttpServletRequest request,Model model){
		if("2".equals(dpType)){
			LogisticsRs logisticsRs=SoaConnectionFactory.getRestful(request, ConstantsUri.LOGISTIC_INFO, null, LogisticsRs.class,id);
			if (!CommonUtils.nullOrBlank(logisticsRs.getData().getTemplateUrl())) {
				String path = SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context")+logisticsRs.getData().getTemplateUrl();
				model.addAttribute("path", path);
			}
			model.addAttribute("pointRule", logisticsRs.getData());
		}
		LogisticsRq logisticsRq = (LogisticsRq) request.getSession().getAttribute("LogisticsRq");
		model.addAttribute("LogisticsRq", logisticsRq);
		model.addAttribute("dpType", dpType);
		return "financed/logistics/edit";
	}

	/**
	 * 物流公司新增，修改保存
	 *
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:logistics")
	@RequestMapping(value = "/logisticsSave.php", method = RequestMethod.POST)
	public ModelAndView add(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		Logistics logistics = new Logistics();
		if (!CommonUtils.nullOrBlank(request.getParameter("id"))) {
			logistics.setId(request.getParameter("id"));
		}
		logistics.setCompName(request.getParameter("compName"));
		logistics.setCompCode(request.getParameter("compCode"));
		logistics.setCompUrl(request.getParameter("compUrl"));
		logistics.setSort(Integer.parseInt(request.getParameter("sort")));
		BaseResponse rs = null;
		//文件上传开始
		MultipartFile file = request.getFile("FILE01");
		if (file != null && file.getSize() > 0) {FjDto fjDto = new FjDto();
			fjDto.setFileName(file.getOriginalFilename());
			CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();

			try {
                fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
				cmsFileUploadDto.setDirectory("goods");
				cmsFileUploadDto.getFjBo().add(fjDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			FileListDto fileListDto = SoaConnectionFactory.post(request, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
			if (fileListDto != null) {
				if (fileListDto.getDataList().size() > 0) {
					logistics.setTemplateUrl(fileListDto.getDataList().get(0).getFilePath());
				}
			}
		}
		if(CommonUtils.nullOrBlank(logistics.getId())){
			rs = SoaConnectionFactory.post(request, ConstantsUri.LOGISTIC_ADD, logistics, LogisticsRs.class);
		}else{
			rs = SoaConnectionFactory.put(request, ConstantsUri.LOGISTIC_EDIT, logistics, LogisticsRs.class,logistics.getId());
		}
		mav.addObject("result", rs);
		return mav;
	}
	/**
	 * 物流公司删除
	 * @param 
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:logistics")
	@RequestMapping("/logisticsDel.php")
	public @ResponseBody BaseResponse delVipPrivilege(String id,HttpServletRequest request){		
		return SoaConnectionFactory.delete(request, ConstantsUri.LOGISTIC_DEL, null, BaseResponse.class,id);		
	}

}
