package com.abc.controller.consumerManager;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.NsqyglRq;
import com.abc.soa.response.consumer.*;
import com.abc.soa.response.consumer.bo.*;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理 Created by zhushuai on 2017/6/14.
 */
@Controller
@RequestMapping(value = "/consumerManager/consumer/nsqygl")
public class NsqyglController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 税种核定信息
	 * 
	 * @return
	 */
	@RequiresPermissions("nsqygl:szhdxx")
	@RequestMapping("/szhdxx/list.php")
	public String szhdxxQuery(NsqyglRq nsqyglRq,
			HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			SzhdxxRs rs = SoaConnectionFactory.post(request, ConstantsUri.SZHDXX_LIST, getEncryptBody(json, request), SzhdxxRs.class);
			model.addAttribute("szhdxxRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/szhdxx/list";
	}

	/**
	 * 出口退税备案
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:cktsba")
	@RequestMapping("/cktsba/list.php")
	public String cktsbaQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			CktsbaRs rs = SoaConnectionFactory.post(request, ConstantsUri.CKTSBA_LIST, getEncryptBody(json, request), CktsbaRs.class);
			model.addAttribute("cktsbaRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/cktsba/list";
	}

	/**
	 * 财务报表备案
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:cwbbba")
	@RequestMapping("/cwbbba/list.php")
	public String cwbbbaQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			CwbbbaRs rs = SoaConnectionFactory.post(request, ConstantsUri.CWBBBA_LIST, getEncryptBody(json, request), CwbbbaRs.class);
			model.addAttribute("cwbbbaRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/cwbbba/list";
	}

	/**
	 * 简易征收备案
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:jyzsba")
	@RequestMapping("/jyzsba/list.php")
	public String jyzsbaQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			JyzsbaRs rs = SoaConnectionFactory.post(request, ConstantsUri.JYZSBA_LIST, getEncryptBody(json, request), JyzsbaRs.class);
			model.addAttribute("jyzsbaRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/jyzsba/list";
	}

	/**
	 * 即征即退资格
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:jzjtzg")
	@RequestMapping("/jzjtzg/list.php")
	public String jzjtzgQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			JzjtzgRs rs = SoaConnectionFactory.post(request, ConstantsUri.JZJTZG_LIST, getEncryptBody(json, request), JzjtzgRs.class);
			model.addAttribute("jzjtzgRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/jzjtzg/list";
	}

	/**
	 * 纳税人资格类型
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:nsrzglx")
	@RequestMapping("/nsrzglx/list.php")
	public String nsrzglxQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			NsrzglxRs rs = SoaConnectionFactory.post(request, ConstantsUri.NSRZGLX_LIST, getEncryptBody(json, request), NsrzglxRs.class);
			model.addAttribute("nsrzglxRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/nsrzglx/list";
	}

	/**
	 * 文化事业建设费登记
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:whsyjsfdj")
	@RequestMapping("/whsyjsfdj/list.php")
	public String whsyjsfdjQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			WhsyjsfdjRs rs = SoaConnectionFactory.post(request, ConstantsUri.WHSYJSFDJ_LIST, getEncryptBody(json, request), WhsyjsfdjRs.class);
			model.addAttribute("whsyjsfdjRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/whsyjsfdj/list";
	}

	/**
	 * 逾期未认定纳税人
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:yqwrdnsr")
	@RequestMapping("/yqwrdnsr/list.php")
	public String yqwrdnsrQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			YqwrdnsrRs rs = SoaConnectionFactory.post(request, ConstantsUri.YQWRDNSR_LIST, getEncryptBody(json, request), YqwrdnsrRs.class);
			model.addAttribute("yqwrdnsrRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/yqwrdnsr/list";
	}

	/**
	 * 增值税农产品备案
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:zzsbzbab")
	@RequestMapping("/zzsbzbab/list.php")
	public String zzsbzbabQuery(NsqyglRq nsqyglRq,
								HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			ZzsncpbaRs rs = SoaConnectionFactory.post(request, ConstantsUri.ZZSNCPBA_LIST, getEncryptBody(json, request), ZzsncpbaRs.class);
			model.addAttribute("zzsbzbabRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/zzsbzbab/list";
	}

	/**
	 * 增值税总分机构
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:zzszfjg")
	@RequestMapping("/zzszfjg/list.php")
	public String zzszfjgQuery(NsqyglRq nsqyglRq,
								HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			ZzszfjgRs rs = SoaConnectionFactory.post(request, ConstantsUri.ZZSZFJG_LIST, getEncryptBody(json, request), ZzszfjgRs.class);
			model.addAttribute("zzszfjgRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/zzszfjg/list";
	}

	/**
	 * 增值税总分机构
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:sdsdjhz")
	@RequestMapping("/sdsdjhz/list.php")
	public String sdsdjhzQuery(NsqyglRq nsqyglRq,
							   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			SdsdjhzRs rs = SoaConnectionFactory.post(request, ConstantsUri.SDSDJHZ_LIST, getEncryptBody(json, request), SdsdjhzRs.class);
			model.addAttribute("sdsdjhzRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/sdsdjhz/list";
	}

	/**
	 * 增值税总分机构
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:cwbbyjbhd")
	@RequestMapping("/cwbbyjbhd/list.php")
	public String cwbbyjbhdQuery(NsqyglRq nsqyglRq,
							   HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())) {
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>();
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			CwbbyjbhdRs rs = SoaConnectionFactory.post(request, ConstantsUri.CWBBYJBHD_LIST, getEncryptBody(json, request), CwbbyjbhdRs.class);
			model.addAttribute("cwbbyjbhdRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/cwbbyjbhd/list";
	}

	/**
	 * 税种核定信息
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:nsrjbxx")
	@RequestMapping("/nsrjbxx/list.php")
	public String nsrjbxxQuery(NsqyglRq nsqyglRq,
							  HttpServletRequest request, Model model) {
		model.addAttribute("referer", request.getHeader("Referer"));
		NsqyglBO bo = new NsqyglBO();
		if(!StringUtils.isEmpty(nsqyglRq.getNsrsbh())){
			bo.setNsrsbh(nsqyglRq.getNsrsbh());
			Map<String, String> body = new HashMap<>() ;
			body.put("nsrsbh", nsqyglRq.getNsrsbh());
			String json = JSON.toJSONString(body);
			SzhdxxRs rs = SoaConnectionFactory.post(request, ConstantsUri.SZHDXX_LIST, getEncryptBody(json, request), SzhdxxRs.class);
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
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);
		}
		return "consumer/nsqygl/nsrjbxx/list";
	}


	/**
	 * 税种核定信息
	 *
	 * @return
	 */
	@RequiresPermissions("nsqygl:smrz")
	@RequestMapping("/smrz/list.php")
	public String smrzQuery(NsqyglRq nsqyglRq,
							HttpSession session,HttpServletRequest request, Model model) {
		String nsrsbh=nsqyglRq.getNsrsbh();
		if(!StringUtils.isEmpty(nsrsbh)){
			nsrsbh = rsaSmrz("nsrsbh|"+nsrsbh);
			nsqyglRq.setApi(Base64.getEncoder().encodeToString(("/smrz/getgxrlist?p=" + nsrsbh).getBytes()));
			SmrzxxRs rs = SoaConnectionFactory.get(request, ConstantsUri.SMRZXX_LIST, nsqyglRq, SmrzxxRs.class);
			List<SmrzxxBO> dataList = rs.getDataList();
			if(!StringUtils.isEmpty(dataList)&&dataList.size()>0){
				for(SmrzxxBO smrz : dataList){
					if (!StringUtils.isEmpty(smrz.getSfzjhm())&&smrz.getSfzjhm().length()>=8){
						smrz.setSfzjhm(CommonUtils.replaceWithSpecialChar(smrz.getSfzjhm(), 4, 4, "*"));
					}
					if (!StringUtils.isEmpty(smrz.getYddh())
							&& smrz.getYddh().length() >= 8) {
						smrz.setYddh(CommonUtils.getPhoneBluring(smrz.getYddh()));
					}
					if (!StringUtils.isEmpty(smrz.getGddh())
							&& smrz.getGddh().length() > 8) {
						smrz.setGddh(CommonUtils.getPhoneBluring(smrz.getGddh()));
					}
				}
			}
			model.addAttribute("smrzxxRs", rs);
			nsqyglRq.setTotalItems(rs.getTotal());
			nsqyglRq.calculate();
			model.addAttribute("BaseRq", nsqyglRq);

		}
		return "consumer/nsqygl/smrzxx/list";
	}
}
