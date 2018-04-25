package com.abc.controller.system;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.ProductSpreadRq;
import com.abc.soa.response.system.ProductSpreadRs;
import com.abc.soa.response.system.bo.ProductSpreadBO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/system/productspread")
public class ProductSpreadController extends BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(ProductSpreadController.class);

    /**
     * 微信模版消息日志列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping("/list.php")
    public String selectList(ProductSpreadRq rq, HttpServletRequest request, Model model,HttpSession session) {
        ProductSpreadRs productSpreadRs = SoaConnectionFactory.get(request, ConstantsUri.PRODUCTSPREAD_LIST, rq, ProductSpreadRs.class);
        model.addAttribute("productSpreadRs", productSpreadRs.getDataList());
        rq.setTotalItems(productSpreadRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);
        session.setAttribute("ProductSpreadRq", rq);
        return "system/productspread/list";
    }

    /**
     * 微信模版消息日志列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping("/download.php")
    public String dowloadList(ProductSpreadRq rq, HttpServletRequest request, Model model) {
        ProductSpreadRs productSpreadRs = SoaConnectionFactory.get(request, ConstantsUri.PRODUCTSPREAD_LIST, rq, ProductSpreadRs.class);
        model.addAttribute("productSpreadRs", productSpreadRs.getDataList());
        model.addAttribute("BaseRq", rq);
        request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        return "system/productspread/download";
    }

    /**
     * 微信模版消息日志列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping("/downloadproduct.php")
    public @ResponseBody ProductSpreadBO  downloadproduct(@RequestParam(required = false) String productSpreadId,@RequestParam(required = false) String url, HttpServletRequest request, Model model) {
        ProductSpreadRq rq = new ProductSpreadRq();
        ProductSpreadRs productSpreadRs = SoaConnectionFactory.get(request, ConstantsUri.PRODUCTSPREAD_LIST, rq, ProductSpreadRs.class);
        model.addAttribute("productSpreadRs", productSpreadRs.getDataList());
        model.addAttribute("BaseRq", rq);
        ProductSpreadRs SpreadRs = SoaConnectionFactory.getRestful(request, ConstantsUri.PRODUCTSPREAD_ONE, null, ProductSpreadRs.class,
                productSpreadId);
        request.setAttribute("SpreadRs", SpreadRs.getData());
        model.addAttribute("productSpreadId", productSpreadId);
        model.addAttribute("url", url);
        request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        return SpreadRs.getData();
    }

    /**
     * 会员礼物添加、编辑
     *
     * @param productSpreadId
     *            礼物ID
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping(value = "/edit.php")
    public String giftEdit(@RequestParam(required = false) String productSpreadId, HttpServletRequest request,Model model) {
        if (StringUtils.isNotEmpty(productSpreadId)) {
            ProductSpreadRs productSpreadRs = SoaConnectionFactory.getRestful(request, ConstantsUri.PRODUCTSPREAD_ONE, null, ProductSpreadRs.class,
                    productSpreadId);
            request.setAttribute("productSpreadRs", productSpreadRs.getData());
        }
        ProductSpreadRq productSpreadRq = (ProductSpreadRq) request.getSession().getAttribute("ProductSpreadRq");
        model.addAttribute("ProductSpreadRq", productSpreadRq);
        request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        return "system/productspread/form_edit";
    }

    /**
     * 会员礼物添加、编辑
     *
     * @param productSpreadId
     *            礼物ID
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping(value = "/look.php")
    public String look(@RequestParam(required = false) String productSpreadId, HttpServletRequest request,Model model) {
        if (StringUtils.isNotEmpty(productSpreadId)) {
            ProductSpreadRs productSpreadRs = SoaConnectionFactory.getRestful(request, ConstantsUri.PRODUCTSPREAD_ONE, null, ProductSpreadRs.class,
                    productSpreadId);
            request.setAttribute("productSpreadRs", productSpreadRs.getData());
        }
        ProductSpreadRq productSpreadRq = (ProductSpreadRq) request.getSession().getAttribute("ProductSpreadRq");
        model.addAttribute("ProductSpreadRq", productSpreadRq);
        request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        return "system/productspread/look";
    }

    /**
     * 会员礼物保存
     *
     * @param productSpread
     *            礼物内容
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping(value = "/save.php")
    public @ResponseBody ProductSpreadRs giftSave(@RequestBody ProductSpreadBO productSpread, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(productSpread.getId())) {
            return SoaConnectionFactory.putRestful(request, ConstantsUri.PRODUCTSPREAD_LIST, productSpread, ProductSpreadRs.class);
        } else {
            return SoaConnectionFactory.post(request, ConstantsUri.PRODUCTSPREAD_LIST, productSpread, ProductSpreadRs.class);
        }
    }

    /**
     * 会员礼包删除
     *
     * @param productSpreadId
     *            礼物ID
     * @param request
     * @return
     */
    @RequiresPermissions("system:productspread")
    @RequestMapping(value = "/del.php")
    public @ResponseBody
    ProductSpreadRs Del(@RequestParam String productSpreadId, HttpServletRequest request) {
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.PRODUCTSPREAD_ONE, null, ProductSpreadRs.class, productSpreadId);
    }


}
