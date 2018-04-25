package com.abc.controller.vipGift;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.vipGift.GiftCheckBO;
import com.abc.soa.request.vipGift.GiftSendBO;
import com.abc.soa.request.vipGift.UgiftApplyRq;
import com.abc.soa.request.vipGift.VipGiftRq;
import com.abc.soa.response.vipGift.UgiftApplyRs;
import com.abc.soa.response.vipGift.UgiftLogRs;
import com.abc.soa.response.vipGift.VipGiftRs;
import com.abc.soa.response.vipGift.bo.Gift;
import com.abc.soa.response.vipGift.bo.UgiftApplyBO;
import com.abc.soa.response.vipGift.bo.UgiftLog;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 会员礼物管理类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/vipgift")
public class VipgiftController extends BaseController{
	/**
	 * 会员礼物列表
	 * 
	 * @param giftRq
	 *            参数
	 * @param request
	 *            上下文
	 * @return
	 */
	@RequiresPermissions("vipgift:manage")
	@RequestMapping(value = "/list.php")
	public String giftList(VipGiftRq giftRq, HttpServletRequest request) {
		VipGiftRs giftRs = SoaConnectionFactory.get(request, ConstantsUri.GIFT_LIST, giftRq, VipGiftRs.class);
		request.setAttribute("gifts", giftRs.getDataList());
		giftRq.setTotalItems(giftRs.getTotal());
		giftRq.calculate();
		request.setAttribute("BaseRq", giftRq);
		request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		return "vipgift/list";
	}

	/**
	 * 礼物详情查看
	 * 
	 * @param giftId
	 *            礼物ID
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:manage")
	@RequestMapping(value = "/giftlook.php")
	public String giftLook(@RequestParam String giftId, HttpServletRequest request) {
		VipGiftRs giftRs = SoaConnectionFactory.getRestful(request, ConstantsUri.GIFT_INFO, null, VipGiftRs.class,
				giftId);
		request.setAttribute("vipgift", giftRs.getData());
		request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		return "vipgift/look";
	}

	/**
	 * 会员礼物添加、编辑
	 * 
	 * @param giftId
	 *            礼物ID
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:manage")
	@RequestMapping(value = "/giftedit.php")
	public String giftEdit(@RequestParam(required = false) String giftId, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(giftId)) {
			VipGiftRs giftRs = SoaConnectionFactory.getRestful(request, ConstantsUri.GIFT_INFO, null, VipGiftRs.class,
					giftId);
			request.setAttribute("vipgift", giftRs.getData());
		}
		request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		return "vipgift/edit";
	}

	/**
	 * 会员礼物保存
	 * 
	 * @param gift
	 *            礼物内容
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:manage")
	@RequestMapping(value = "/giftsave.php")
	public @ResponseBody VipGiftRs giftSave(@RequestBody Gift gift, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(gift.getId())) {
			return SoaConnectionFactory.putRestful(request, ConstantsUri.GIFT_INFO, gift, VipGiftRs.class,
					gift.getId());
		} else {
			return SoaConnectionFactory.post(request, ConstantsUri.GIFT_LIST, gift, VipGiftRs.class);
		}
	}

	/**
	 * 会员礼包删除
	 * 
	 * @param giftId
	 *            礼物ID
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:manage")
	@RequestMapping(value = "/giftdel.php")
	public @ResponseBody VipGiftRs giftDel(@RequestParam String giftId, HttpServletRequest request) {
		return SoaConnectionFactory.deleteRestful(request, ConstantsUri.GIFT_INFO, null, VipGiftRs.class, giftId);
	}

	/**
	 * 会员礼包上下架
	 *
	 * @param giftId
	 *            礼物ID
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:manage")
	@RequestMapping(value = "/giftsxj.php")
	public @ResponseBody VipGiftRs gift(@RequestParam String giftId, @RequestParam String status, HttpServletRequest request) {
		VipGiftRs giftRs = SoaConnectionFactory.getRestful(request, ConstantsUri.GIFT_INFO, null, VipGiftRs.class,
				giftId);
		Gift gift = giftRs.getData();
		gift.setStatus(status);
		return SoaConnectionFactory.putRestful(request, ConstantsUri.GIFT_INFO, gift, VipGiftRs.class, gift.getId());
	}
	/**
	 * 会员礼物申请列表
	 * 
	 * @param applyRq
	 *            参数
	 * @param request
	 *            上下文
	 * @return
	 */
	@RequiresPermissions("vipgift:apply")
	@RequestMapping(value = "/applylist.php")
	public String applyList(UgiftApplyRq applyRq, HttpServletRequest request) {
		UgiftApplyRs giftRs = SoaConnectionFactory.get(request, ConstantsUri.GIFTAPPLY_INFO, applyRq,
				UgiftApplyRs.class);
		request.setAttribute("applylists", giftRs.getDataList());
		applyRq.setTotalItems(giftRs.getTotal());
		applyRq.calculate();
		request.setAttribute("BaseRq", applyRq);
		request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		return "vipgift/applylist";
	}

	/**
	 * 会员礼包申请审核
	 * 
	 * @param checkBO
	 *            审核信息
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:apply")
	@RequestMapping(value = "/applycheck.php")
	public @ResponseBody UgiftApplyRs applycheck(@RequestBody GiftCheckBO checkBO, HttpServletRequest request) {
		return SoaConnectionFactory.put(request, ConstantsUri.GIFTAPPLY_CHECK, checkBO, UgiftApplyRs.class);
	}

	/**
	 * 会员礼包发货
	 * 
	 * @param sendBO
	 *            发货信息
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:apply")
	@RequestMapping(value = "/applysend.php")
	public @ResponseBody UgiftApplyRs applysend(@RequestBody GiftSendBO sendBO, HttpServletRequest request) {
		return SoaConnectionFactory.put(request, ConstantsUri.GIFTAPPLY_SEND, sendBO, UgiftApplyRs.class);
	}

	/**
	 * 申请日志查询
	 * 
	 * @param request
	 * @param applyId
	 *            申请ID
	 * @return
	 */
	@RequiresPermissions("vipgift:apply")
	@RequestMapping(value = "/applylog.php")
	public @ResponseBody List<UgiftLog> applylog(HttpServletRequest request, @RequestParam String applyId) {
		UgiftLogRs logRs = SoaConnectionFactory.getRestful(request, ConstantsUri.GIFTAPPLY_LOG, null, UgiftLogRs.class,
				applyId);
		return logRs.getDataList();
	}

	/**
	 * 单个申请信息查看
	 * 
	 * @param applyId
	 *            申请ID
	 * @param request
	 * @return
	 */
	@RequiresPermissions("vipgift:apply")
	@RequestMapping(value = "/applyinfo.php")
	public @ResponseBody UgiftApplyBO applyinfo(HttpServletRequest request, @RequestParam String applyId) {
		UgiftApplyBO applyBO = SoaConnectionFactory
				.getRestful(request, ConstantsUri.GIFTAPPLY_LIST, null, UgiftApplyRs.class, applyId).getData();
		return applyBO;
	}

}
