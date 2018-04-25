package com.abc.controller.financed;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.financed.ProductRq;
import com.abc.soa.request.financed.ProductrepoRq;
import com.abc.soa.response.consumer.VipLevelRs;
import com.abc.soa.response.financed.ProductBORs;
import com.abc.soa.response.financed.ProductRs;
import com.abc.soa.response.financed.ProductrepoRs;
import com.abc.soa.response.financed.UvipPriceRs;
import com.abc.soa.response.financed.bo.Good;
import com.abc.soa.response.financed.bo.ProductBO;
import com.abc.soa.response.financed.bo.Productrepo;
import com.abc.soa.response.financed.bo.UvipPrice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 商品及库存
 * 
 * @author zhushuai 2017-6-27
 * 
 */
@Controller
public class ProductController extends BaseController {

	/**
	 * 商品列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodList.php")
	public String goodList(ProductRq productRq, HttpServletRequest request,
			Model model) {
		ProductRs productRs = SoaConnectionFactory.get(request,
				ConstantsUri.GOODS_DETAILLIST, productRq, ProductRs.class);
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		
		model.addAttribute("productRs", productRs.getDataList());
		productRq.setTotalItems(productRs.getTotal());
		productRq.calculate();
		model.addAttribute("BaseRq", productRq);
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("recommendType", getDictBOList(request, "recommendType"));
		return "financed/good/list";
	}

	/**
	 * 产品管理列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/financed/ajaxGoodList.php")
	public @ResponseBody BaseResponse ajaxGoodList(ProductRq productRq, HttpServletRequest request) {
		return SoaConnectionFactory.get(request,ConstantsUri.GOODS_DETAILLIST, productRq, ProductRs.class);
	}

	/**
	 * 跳转到商品新增页面
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodAdd.php")
	public String goodAdd(HttpServletRequest request, Model model) {
		
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));

		// 查询出所有会员等级信息
		VipLevelRq levelRq = new VipLevelRq();
		levelRq.setSize(100);
		levelRq.setStatus(true);
		VipLevelRs levelRs = SoaConnectionFactory.get(request,
				ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levelRs", levelRs.getDataList());

		// 查出所有商品推荐类型
		model.addAttribute("recommendType",
				getDictBOList(request, "recommendType"));
		// 商品规格大类
		model.addAttribute("goodrule", getDictBOList(request, "goodrule"));
		model.addAttribute("goodsType", getDictBOList(request, "goodsType"));
		model.addAttribute("invoicecontent", getDictBOList(request, "invoicecontent"));
		model.addAttribute("referer", request.getHeader("Referer"));
		return "financed/good/add";
	}

	/**
	 * 跳转到商品修改页面
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodEdit.php")
	public String goodEdit(String id,String look,HttpServletRequest request, Model model) {
		
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		model.addAttribute("look",look);
		model.addAttribute("referer", request.getHeader("Referer"));
		// 查询出所有会员等级信息
		VipLevelRq levelRq = new VipLevelRq();
		levelRq.setSize(100);
		levelRq.setStatus(true);
		VipLevelRs levelRs = SoaConnectionFactory.get(request,
				ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
		model.addAttribute("levelRs", levelRs.getDataList());

		// 查出所有商品推荐类型
		model.addAttribute("recommendType",
				getDictBOList(request, "recommendType"));
		// 商品规格大类
		model.addAttribute("goodrule", getDictBOList(request, "goodrule"));
		model.addAttribute("goodsType", getDictBOList(request, "goodsType"));
		model.addAttribute("invoicecontent", getDictBOList(request, "invoicecontent"));
		//查询出商品信息
		ProductRs productRs = SoaConnectionFactory.getRestful(request,
				ConstantsUri.GOODS_INFO, null, ProductRs.class,id);
		
		model.addAttribute("good", productRs.getData());
	    
		return "financed/good/edit";
	}

	/**
	 * 产品参数查询
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodProduct.php")
	public @ResponseBody
	List<ProductBO> goodProducts(String  goodsId, HttpServletRequest request,
			Model model) {
		ProductBORs boRs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PRODUCT, null, ProductBORs.class, goodsId);
		return boRs.getDataList();
	}

	/**
	 * 会员价格列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/financed/productPrice.php")
	public @ResponseBody
	List<UvipPrice> productPrice(String productId, HttpServletRequest request,
			Model model) {
		UvipPriceRs rs= SoaConnectionFactory.getRestful(request, ConstantsUri.GOODS_PROPRICE, null, UvipPriceRs.class, productId);
		return rs.getDataList();
	}

	/**
	 * 产品审核
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodCheck.php")
	public @ResponseBody
	BaseResponse goodEnable(ProductRq productRq, HttpServletRequest request,
			Model model) {
		return SoaConnectionFactory.put(request, ConstantsUri.GOODS_CHECK,
				productRq, BaseResponse.class);
	}


	/**
	 * 商品新增
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodsave.php")
	public @ResponseBody
	BaseResponse goodsave(@RequestBody Good good, HttpServletRequest request) {
		return SoaConnectionFactory.post(request, ConstantsUri.GOODS_ADD, good,
				BaseResponse.class);
	}

	/**
	 * 商品修改
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/goodeditsave.php")
	public @ResponseBody
	BaseResponse goodeditsave(@RequestBody Good good, HttpServletRequest request) {
		return SoaConnectionFactory.put(request, ConstantsUri.GOODS_EDIT, good,
				BaseResponse.class,good.getId());
	}

	/**
	 * 商品删除
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:product")
	@RequestMapping("/financed/delgood.php")
	public @ResponseBody BaseResponse delGood(String goodId,HttpServletRequest request){		
		return SoaConnectionFactory.delete(request, ConstantsUri.GOODS_EDIT, null, BaseResponse.class,goodId);		
	}


	/**
	 * 商品库存列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:productrepo")
	@RequestMapping("/financed/productrepoList.php")
	public String productrepoList(ProductrepoRq productRq, HttpServletRequest request,
			Model model,HttpSession session) {
		ProductBORs productRs = SoaConnectionFactory.get(request,
				ConstantsUri.PRODUCTREPO_LIST, productRq, ProductBORs.class);
		model.addAttribute("productRs", productRs.getDataList());
		productRq.setTotalItems(productRs.getTotal());
		productRq.calculate();
		model.addAttribute("BaseRq", productRq);
		model.addAttribute("referer", request.getHeader("Referer"));
		session.setAttribute("ProductrepoRq", productRq);
		return "financed/productrepo/list";
	}

	/**
	 * 商品库存详情页面
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:productrepo")
	@RequestMapping("/financed/productrepoInfo.php")
	public String productrepoInfo(ProductrepoRq productRq,HttpServletRequest request,
			Model model){
		ProductrepoRs productRs = SoaConnectionFactory.get(request,
				ConstantsUri.PRODUCTREPO_INFO, productRq, ProductrepoRs.class);
		ProductrepoRq rq = (ProductrepoRq)request.getSession().getAttribute("ProductrepoRq");
		model.addAttribute("productRs", productRs.getDataList());
		productRq.setTotalItems(productRs.getTotal());
		productRq.calculate();
		model.addAttribute("BaseRq", productRq);
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("ProductrepoRq", rq);
		return "financed/productrepo/info";
	}

	/**
	 * 商品库存出库  入库
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:productrepo")
	@RequestMapping("/financed/productrepoEdit.php")
	public @ResponseBody BaseResponse productrepoEdit(String type,@RequestBody Productrepo productrepo,HttpServletRequest request,Model model){
		if("outcome".equals(type)){
			return SoaConnectionFactory.post(request, ConstantsUri.PRODUCTREPO_MINUS, productrepo, BaseResponse.class);
		}else{
			return SoaConnectionFactory.post(request, ConstantsUri.PRODUCTREPO_ADD, productrepo, BaseResponse.class);
		}
	}
}
