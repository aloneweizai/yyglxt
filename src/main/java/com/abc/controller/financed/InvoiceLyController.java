package com.abc.controller.financed;


import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.InvoiceLyRq;
import com.abc.soa.request.financed.InvoiceRepoRq;
import com.abc.soa.response.financed.InvoiceLyRs;
import com.abc.soa.response.financed.InvoiceRepoRs;
import com.abc.soa.response.financed.InvoiceUseDetailRs;
import com.abc.soa.response.financed.bo.InvoiceLy;
import com.abc.soa.response.financed.bo.InvoiceRepo;
import com.abc.soa.response.financed.bo.InvoiceUseDetailBO;
import com.abc.soa.response.financed.bo.InvoiceUseSaveUpdBo;
import com.abc.soa.response.system.bo.DictBO;
import com.abc.soa.response.system.bo.UserBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


/**
 * 发票仓库
 * 
 * @author zhushuai 2017-7-13
 * 
 */
@Controller
public class InvoiceLyController extends BaseController{
	/**
	 * 发票领用列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/invoiceLyList.php")
	public String invoiceLylist(InvoiceLyRq rq,
			HttpServletRequest request, Model model,HttpSession session) {
		InvoiceLyRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICELY_LIST, rq, InvoiceLyRs.class);
		model.addAttribute("invoiceLyRqs", repoRs.getDataList());
		List<InvoiceLy> list = repoRs.getDataList();
		for(int i=0;i<list.size();i++){
			List<InvoiceUseDetailBO> invoiceUseDetailBOList = list.get(i).getInvoiceUseDetailBOList();
			list.get(i).setNum(invoiceUseDetailBOList.size());
		}
		rq.setTotalItems(repoRs.getTotal());
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		rq.calculate();
		model.addAttribute("pagination", rq);
		model.addAttribute("BaseRq", rq);
		model.addAttribute("issueStatus", getDictBOList(request, "issueStatus"));
		model.addAttribute("examineStatus", getDictBOList(request, "examineStatus"));
		session.setAttribute("InvoiceLyRq",rq);
		return "/financed/invoiceLy/list";
	}


	/**
	 * 跳转到发票领用查看页面
	 *
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/invoiceLyDetail.php")
	public String editPointrule(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
		InvoiceLyRq rq = new InvoiceLyRq();
		InvoiceLyRs repoRs = SoaConnectionFactory.getRestful(request, ConstantsUri.INVOICELY_INFO, null, InvoiceLyRs.class, id);
		InvoiceLyRq invoiceLyRq = (InvoiceLyRq) request.getSession().getAttribute("InvoiceLyRq");
		model.addAttribute("InvoiceLyRq", invoiceLyRq);
		model.addAttribute("invoiceLyRs", repoRs.getData());
		model.addAttribute("issueStatus", getDictBOList(request, "issueStatus"));
		model.addAttribute("examineStatus", getDictBOList(request, "examineStatus"));
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		    model.addAttribute("BaseRq", rq);
			return "/financed/invoiceLy/info";

	}

	/**
	 * 跳转到发票领用审核，分发，签收，新增页面
	 *
	 * @param
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/invoiceLyAdd.php")
	public String invoiceRepoAdd(@RequestParam(required = false) String id,@RequestParam(required = false) String doType,HttpServletRequest request, Model model) {
		UserBO userBO = (UserBO) request.getSession().getAttribute("currentUser");
		model.addAttribute("user", userBO);
		InvoiceLyRq invoiceLyRq = (InvoiceLyRq) request.getSession().getAttribute("InvoiceLyRq");
		model.addAttribute("InvoiceLyRq", invoiceLyRq);
		model.addAttribute("issueStatus", getDictBOList(request, "issueStatus"));
		model.addAttribute("examineStatus", getDictBOList(request, "examineStatus"));
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		if (!StringUtils.isEmpty(id)) {
			InvoiceLyRs invoiceLyRs = SoaConnectionFactory.getRestful(request, ConstantsUri.INVOICELY_INFO, null, InvoiceLyRs.class, id);
			model.addAttribute("invoiceLyRs", invoiceLyRs.getData());
		}
		//审批
		if("0".equals(doType)){
			return "/financed/invoiceLy/form_check";
		}
		//分发
		else if("1".equals(doType)){
			return "/financed/invoiceLy/form_dist";
		}
		//签收
		else if("3".equals(doType)){
			return "/financed/invoiceLy/form_sign";
		}
		else{
			return "/financed/invoiceLy/add";
		}

	}

	/**
	 * 数据字典获取
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/financed/jsonDictBOs.php")
	public @ResponseBody List<DictBO> getDictBOs(HttpServletRequest request,@RequestParam("dictId") String dictId){
		List<DictBO> dictBo = getDictBOList(request, dictId);
		return dictBo;
	}

	/**
	 * 发票领用新增修改保存操作
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping(value = "/financed/save.php",method = RequestMethod.POST)
	public ModelAndView add( @RequestBody InvoiceUseSaveUpdBo invoiceUseSaveUpdBo,HttpServletRequest request, HttpServletResponse response,@RequestParam("type") String type) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		InvoiceLy invoiceLy = new InvoiceLy();
		InvoiceLyRs result = new InvoiceLyRs();
		invoiceLy.setInvoiceUseDetailBOList(invoiceUseSaveUpdBo.getInvoiceUseDetailBO());
		invoiceLy.setApplyUser(invoiceUseSaveUpdBo.getApplyUser());
		invoiceLy.setRemark(invoiceUseSaveUpdBo.getRemark());
		invoiceLy.setIssueStatus("0");
		invoiceLy.setExamineStatus(type);
		if(!StringUtils.isEmpty(invoiceUseSaveUpdBo.getId())){
			invoiceLy.setId(invoiceUseSaveUpdBo.getId());
			result =  SoaConnectionFactory.put(request, ConstantsUri.INVOICELY_EDIT, invoiceLy, InvoiceLyRs.class);
		}
		else{
			result =  SoaConnectionFactory.post(request, ConstantsUri.INVOICELY_ADD, invoiceLy, InvoiceLyRs.class);
		}
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 发票领用审核操作
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping(value = "/financed/invoiceLyCheck.php",method = RequestMethod.POST)
	public ModelAndView invoiceLyCheck( @RequestBody InvoiceUseSaveUpdBo invoiceUseSaveUpdBo,HttpServletRequest request, HttpServletResponse response,@RequestParam("type") String type) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		InvoiceLy invoiceLy = new InvoiceLy();
		invoiceLy.setInvoiceUseDetailBOList(invoiceUseSaveUpdBo.getInvoiceUseCheckBO());
		invoiceLy.setApplyUser(invoiceUseSaveUpdBo.getApplyUser());
		invoiceLy.setCheckOpinion(invoiceUseSaveUpdBo.getCheckOpinion());
		invoiceLy.setIssueStatus("0");
		invoiceLy.setExamineStatus(type);
		invoiceLy.setId(invoiceUseSaveUpdBo.getId());
		InvoiceLyRs result =  SoaConnectionFactory.post(request, ConstantsUri.INVOICELY_CHECK, invoiceLy, InvoiceLyRs.class,invoiceUseSaveUpdBo.getId());
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 发票领用删除操作
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/invoiceLydel.php")
	public @ResponseBody
	BaseResponse invoiceLydel(String id, HttpServletRequest request) {
		return SoaConnectionFactory.delete(request,
				ConstantsUri.INVOICELY_DEL, null, BaseResponse.class, id);
	}

	/**
	 * 发票领用分发操作
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping(value = "/financed/invoiceLydist.php",method = RequestMethod.POST)
	public ModelAndView invoiceLydist( @RequestBody InvoiceUseSaveUpdBo invoiceUseSaveUpdBo,HttpServletRequest request, HttpServletResponse response,@RequestParam("type") String type) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		InvoiceLy invoiceLy = new InvoiceLy();
		invoiceLy.setInvoiceUseDetailBOList(invoiceUseSaveUpdBo.getInvoiceUseDistBO());
		invoiceLy.setIssueStatus("1");
		invoiceLy.setExamineStatus("1");
		invoiceLy.setId(invoiceUseSaveUpdBo.getId());
		InvoiceLyRs result =  SoaConnectionFactory.post(request, ConstantsUri.INVOICELY_DIST, invoiceLy, InvoiceLyRs.class);
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 发票领用签收操作
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping(value = "/financed/invoiceLysign.php",method = RequestMethod.POST)
	public ModelAndView invoiceLysign( @RequestBody InvoiceUseSaveUpdBo invoiceUseSaveUpdBo,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
		InvoiceLyRs result =  SoaConnectionFactory.post(request, ConstantsUri.INVOICELY_SIGN, null, InvoiceLyRs.class,invoiceUseSaveUpdBo.getId());
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 查询可以分发的发票库存列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/invoiceLyRepoList.php")
	public @ResponseBody HashMap<String, Object> invoiceLyRepoQuery(HttpServletRequest request, Model model,
							    @RequestParam(required = false,value="invoicetype") String invoicetype,@RequestParam(required = false,value="status") String status,
															  @RequestParam(required = false,value="invoiceCode") String invoiceCode,@RequestParam(required = false,value="noStart") String noStart,
															  @RequestParam(required = false,value="noEnd") String noEnd,@RequestParam(required = false,value="invoiceNoStart") String invoiceNoStart,
															  @RequestParam(required = false,value="invoiceNoEnd") String invoiceNoEnd,@RequestParam(required = false,value="page") Integer page
																	,@RequestParam(required = false,value="size") Integer size) {
		InvoiceRepoRq rq = new InvoiceRepoRq();
		rq.setInvoiceTypeCode(invoicetype);
		rq.setStatus(status);
		rq.setInvoiceCode(invoiceCode);
		rq.setNoStart(noStart);
		rq.setNoEnd(noEnd);
		rq.setInvoiceNoStart(invoiceNoStart);
		rq.setInvoiceNoEnd(invoiceNoEnd);
		if(!StringUtils.isEmpty(page)){
			rq.setPage(page);;
		}
		if(!StringUtils.isEmpty(size)){
			rq.setSize(size);
		}
		//(required = false)
		InvoiceRepoRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICEREPO_LIST, rq, InvoiceRepoRs.class);
		List<InvoiceRepo> dataList = repoRs.getDataList();
		StringBuilder body = new StringBuilder();
		HashMap rs = new HashMap();
		if(dataList != null && !dataList.isEmpty()){
			for (InvoiceRepo repo :dataList){
				body.append("<tr><td class='mdtablethtd'></td>")
						.append("<td class='mdtablethtd'>&nbsp;&nbsp;<input class=\"js_checkbox\" name=\"ids\" type=\"checkbox\" value=\"").append(repo.getId()).append("\"></td>")
						.append("<td class=\"mdtablethtd\">").append(repo.getId()).append("</td>")
						.append("<td class=\"mdtablethtd\">").append(repo.getInvoiceTypeName()).append("</td>")
						.append("<td class=\"mdtablethtd\">").append(repo.getInvoiceCode()).append("</td>")
						.append("<td class=\"mdtablethtd\">").append(repo.getInvoiceNoStart()).append("</td>")
						.append("<td class=\"mdtablethtd\">").append(repo.getInvoiceNoEnd()).append("</td>")
						.append("<td class=\"mdtablethtd\">").append(repo.getShare()).append("</td>")
				.append("</tr>");
			}

			int totalPage = (int)Math.ceil((double)repoRs.getTotal()/(double)15);
			body.append("<tr><td align='center' colspan=\"8\">共&nbsp;&nbsp;").append(repoRs.getTotal()).append("条&nbsp;&nbsp;")
					.append("每页<input maxlength='2' style='width:40px' name='size' value='15'  id='consumer_size' type='text'>条&nbsp;&nbsp;")
					.append("<input class='btn btn-default btn-xs'  value='首 页' id='consumer_first' type='button' >")
					.append("<input class=\" btn btn-default btn-xs\" value=\"上一页\" id=\"consumer_up\"  type=\"button\">")
					.append("<input class=\" btn btn-default btn-xs\" value=\"下一页\" id=\"consumer_down\" type=\"button\">")
					.append("<input class=\" btn btn-default btn-xs\" value=\"尾 页\" id=\"consumer_last\" type=\"button\">&nbsp;&nbsp;")
					.append("当前&nbsp;<span id=\"page\">1</span>/<span id=\"totalPage\">").append(totalPage).append("</span>&nbsp;页")
					.append("<input type=\"hidden\" name=\"page\" id=\"cupageVal\" value=\"1\">")
					.append("<input type=\"hidden\" name=\"tpage\" id=\"topageVal\" value=\"").append(totalPage).append("\">")
					.append("</td></tr>");
		}
		rs.put("bodyHtml",body);
		return rs;
	}


	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/RepoList.php")
	public String invoiceLyRepo(HttpServletRequest request, Model model,InvoiceRepoRq rq) {
		if (request.getParameter("invoicetype") != null) {
			rq.setInvoiceTypeCode(request.getParameter("invoicetype"));
		}
		rq.setStatus("0");
		if (request.getParameter("invoiceCode") != null) {
			rq.setInvoiceCode(request.getParameter("invoiceCode"));
		}
		if (request.getParameter("noStart") != null) {
			rq.setNoStart(request.getParameter("noStart"));
		}
		if (request.getParameter("noEnd") != null) {
			rq.setNoEnd(request.getParameter("noEnd"));
		}
		if (request.getParameter("invoiceNoStart") != null) {
			rq.setInvoiceNoStart(request.getParameter("invoiceNoStart"));
		}
		if (request.getParameter("invoiceNoEnd") != null) {
			rq.setInvoiceNoEnd(request.getParameter("invoiceNoEnd"));
		}
		if (request.getParameter("page") != null) {
			rq.setPage(Integer.parseInt(request.getParameter("page")));
		}
		if (request.getParameter("size") != null) {
			rq.setSize(Integer.parseInt(request.getParameter("size")));
		}
		//(required = false)
		InvoiceRepoRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICEREPO_LIST, rq, InvoiceRepoRs.class);
		//发票种类
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		//库存状态
		model.addAttribute("repoStatus", getDictBOList(request, "repoStatus"));
		model.addAttribute("invoiceRepoRqs", repoRs.getDataList());
		rq.setTotalItems(repoRs.getTotal());
		rq.calculate();
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		model.addAttribute("pagination", rq);
		model.addAttribute("BaseRq", rq);
		return "/financed/invoiceLy/dist_list";
	}
	/**
	 * 根据发票种类代码查发票库存数
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:invoiceLy")
	@RequestMapping("/financed/invoiceRepoNum.php")
	public @ResponseBody InvoiceUseDetailBO selectInvoiceRepoNum(HttpServletRequest request,@RequestParam("invoiceTypeCode") String invoiceTypeCode){
		InvoiceUseDetailRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.INVOICELY_NUM, null, InvoiceUseDetailRs.class, invoiceTypeCode);
		return rs.getData();
	}

}
