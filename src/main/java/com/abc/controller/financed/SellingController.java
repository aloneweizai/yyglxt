package com.abc.controller.financed;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.CourseCriteria;
import com.abc.soa.request.consumer.ConsumerRq;
import com.abc.soa.request.consumer.MyConsumerRq;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.financed.*;
import com.abc.soa.request.system.ProductSpreadRq;
import com.abc.soa.response.cms.course.Course;
import com.abc.soa.response.cms.course.CourseListBo;
import com.abc.soa.response.cms.course.CourseListBoRs;
import com.abc.soa.response.cms.course.CourseRs;
import com.abc.soa.response.consumer.ConsumerRs;
import com.abc.soa.response.consumer.VipLevelRs;
import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.consumer.bo.VipLevel;
import com.abc.soa.response.financed.*;
import com.abc.soa.response.financed.bo.*;
import com.abc.soa.response.system.MySpreadUrlRs;
import com.abc.soa.response.system.ProductSpreadRs;
import com.abc.soa.response.system.bo.UserBO;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 *
 * @author zhushuai 2017-6-29
 */
@Controller
public class SellingController extends BaseController {
    /**
     * 我的客户列表
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("financed:myclient")
    @RequestMapping("/financed/clientList.php")
    public String getOrderList(MyConsumerRq consumerRq,HttpServletRequest request, Model model) {
        String date = "";
        String datee = "";
        if(!StringUtils.isEmpty(consumerRq.getCreateTimeB())){
            date = consumerRq.getCreateTimeB();
            consumerRq.setCreateTimeB(consumerRq.getCreateTimeB().replace("-",""));
        }
        if(!StringUtils.isEmpty(consumerRq.getCreateTimeE())){
            datee = consumerRq.getCreateTimeE();
            consumerRq.setCreateTimeE(consumerRq.getCreateTimeE().replace("-", ""));
        }
        UserBO userBO = (UserBO)request.getSession().getAttribute("currentUser");
        consumerRq.setRecommend(userBO.getUsername());
        consumerRq.setRecommendPhone(userBO.getPhone());
        ConsumerRs consumerRs = SoaConnectionFactory.get(request,
                ConstantsUri.MYCONSTOMERS_LIST, consumerRq, ConsumerRs.class);
        model.addAttribute("consumers", consumerRs.getDataList());
        consumerRq.setTotalItems(consumerRs.getTotal());
        consumerRq.calculate();
        consumerRq.setCreateTimeB(date);
        consumerRq.setCreateTimeE(datee);
        model.addAttribute("BaseRq", consumerRq);
        model.addAttribute("username",userBO.getUsername());
        return "/financed/selling/client_list";
    }


    /**
     * 我的销售订单列表
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/sellingOrderList.php")
    public String getSellingOrderList(OrderRq re, @RequestParam(value = "doType", required = false) String doType,String lookType,HttpServletRequest request, Model model,HttpSession session) {
        MyOrderRs orderrs = new MyOrderRs();
        UserBO userBO = (UserBO)request.getSession().getAttribute("currentUser");
        re.setRecommendUser(userBO.getUsername());
        re.setRecommendPhone(userBO.getPhone());
        if(!"1".equals(doType)){
            re.setStartTime(DateUtil.getYear() + "-" + DateUtil.getMonth() + "-01");
            re.setEndTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
        }
        orderrs = SoaConnectionFactory.get(request, ConstantsUri.MYORDER_LIST, re, MyOrderRs.class);
        re.setTradeMethod("RMB");
        MyOrderMoneyRs rs = SoaConnectionFactory.get(request, ConstantsUri.MYORDERMONEY_LIST, re, MyOrderMoneyRs.class);
        model.addAttribute("RMB", rs.getData());
        OrderRq rq = re;
        rq.setTradeMethod("POINTS");
        MyOrderMoneyRs rs1 = SoaConnectionFactory.get(request, ConstantsUri.MYORDERMONEY_LIST, rq, MyOrderMoneyRs.class);
        model.addAttribute("POINTS", rs1.getData());
        model.addAttribute("orderrs", orderrs.getDataList());
        re.setTotalItems(orderrs.getTotal());
        re.setTotalPage((int) Math.ceil((double) re.getTotalItems() / (double) re.getSize()));
        re.calculate();
        model.addAttribute("pagination", re);
        model.addAttribute("BaseRq", re);
        model.addAttribute("doType", doType);
        model.addAttribute("lookType", lookType);
        model.addAttribute("orderStatus", getDictBOList(request, "orderStatus"));
        LogisticsRq logisticsrq= new LogisticsRq();
        LogisticsRs logisticsRs=SoaConnectionFactory.get(request, ConstantsUri.LOGISTIC_LIST, logisticsrq, LogisticsRs.class);
        model.addAttribute("logisticsRs", logisticsRs.getDataList());
        model.addAttribute("referer", request.getHeader("Referer"));
        model.addAttribute("channels",getDictBOList(request, "goods_trading_channels"));
        model.addAttribute("username",userBO.getUsername());
        //查询条件丢失
        session.setAttribute("OrderRq", re);
        return "/financed/selling/list";
    }

    /**
     * 跳转到我的推广码页面
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myspreadurl")
    @RequestMapping("/financed/myspreadurl.php")
    public String dowloadList(MytgmRq rq, HttpServletRequest request, Model model) {
        ProductSpreadRq rq1 = new ProductSpreadRq();
        ProductSpreadRs productSpreadRs = SoaConnectionFactory.get(request, ConstantsUri.PRODUCTSPREAD_LIST, rq1, ProductSpreadRs.class);
        model.addAttribute("productSpreadRs", productSpreadRs.getDataList());
        model.addAttribute("BaseRq", rq);
        request.setAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        UserBO userBO = (UserBO)request.getSession().getAttribute("currentUser");
        model.addAttribute("username", userBO.getUsername());
        return "/financed/selling/tgm";
    }

    /**
     * 生成我的推广码
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myspreadurl")
    @RequestMapping("/financed/myspread.php")
    public @ResponseBody MySpreadUrlRs downloadproduct(MytgmRq rq, HttpServletRequest request, Model model) {
        Map map = new HashMap<>();
        map.put("url", rq.getUrl());
        MySpreadUrlRs rs = SoaConnectionFactory.post(request, ConstantsUri.MYSPREAD_QRCODE, map, MySpreadUrlRs.class);
        return rs;
    }

    /**
     * 查询需要下单的客户
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/queryClient.php")
    public @ResponseBody
    Consumer queryClinet(ConsumerRq rq, HttpServletRequest request, Model model) {
        ConsumerRs consumerRs = SoaConnectionFactory.get(request,
                ConstantsUri.CONSUMER_LIST, rq, ConsumerRs.class);
        if(!StringUtils.isEmpty(consumerRs.getDataList())&&consumerRs.getDataList().size()>0){
            return consumerRs.getDataList().get(0);
        }
       else{
            return new Consumer();
        }
    }

    /**
     * 查询会员等级列表
     *
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/queryVip.php")
    public @ResponseBody
    List<VipLevel> queryvip(VipLevelRq rq ,HttpServletRequest request, Model model) {
        rq.setStatus(true);
        rq.setPage(0);
        rq.setSize(0);
        VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, rq, VipLevelRs.class);
        if(!StringUtils.isEmpty(level.getDataList())&&level.getDataList().size()>0){
            return level.getDataList();
        }
        else{
            return new ArrayList<>();
        }
    }

    /**
     * 查询客户可以下单的课程列表
     *
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/queryCourse.php")
    public @ResponseBody
    List<CourseListBo> queryCourse(CourseCriteria criteria,@RequestParam(required = false,value = "username") String username,HttpServletRequest request, Model model) {
        criteria.setIsFree("0");
        criteria.setStatus("1");
        criteria.setPage(1);
        criteria.setSize(10000000);
        CourseListBoRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_QUERYByVip_LIST, criteria, CourseListBoRs.class);
        if(!StringUtils.isEmpty(rs.getDataList())&&rs.getDataList().size()>0){
            List<CourseListBo> courses = rs.getDataList();
            List<CourseListBo> courseList = new ArrayList<>();
            OrderRq re = new OrderRq();
            re.setOrderStatus("6");;
            re.setTradingChannels("CSKT");
            re.setUsername(username);
            re.setPage(1);
            re.setSize(9999);
            OrderRs orderrs = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LIST, re, OrderRs.class);
            List<Order> lsit = new ArrayList<Order>();
            lsit= JSON.parseArray(orderrs.getDataList(), Order.class);
            if(!StringUtils.isEmpty(lsit)&&lsit.size()>0){
             for(Order order:lsit){
                 List<OrderProduct> products = order.getOrderProductBOList();
                 if(!StringUtils.isEmpty(products)&&products.size()>0){
                     for(OrderProduct product:products){
                         for(CourseListBo course:courses){
                             if(!product.getGoodsId().equals(course.getCurriculumId())){
                                 courseList.add(course);
                             }
                         }
                     }
                 }
             }
            }
            return courseList;
        }
        else{
            return new ArrayList<>();
        }
    }
    /**
     * 查询单个课程
     *
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/queryCourseOne.php")
    public @ResponseBody
    Course queryCourseOne(@RequestParam(required = false,value = "id") String id,HttpServletRequest request, Model model) {
        Course course = new Course();
        if(!CommonUtils.nullOrBlank(id)) {
            CourseRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_VIEW, null, CourseRs.class, id);
            course = rs.getData();
        }
        return  course;
    }

    /**
     * 查询课程赠送的优惠券
     *
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/queryCoupon.php")
    public @ResponseBody
    CouponActivity queryCouponOne(@RequestParam(required = false,value = "id") String id,HttpServletRequest request, Model model) {
        CouponActivity data = new CouponActivity();
        if(!CommonUtils.nullOrBlank(id)) {
            CouponActivityRs couponActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPONACTIVITY_ONE, null, CouponActivityRs.class, id);
           data = couponActivityRs.getData();
        }
        return  data;
    }
    /**
     * 跳转到代客下单页面
     *
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/createOrder.php")
    public String editform(HttpServletRequest request, Model model) {
        UserBO userBO = (UserBO)request.getSession().getAttribute("currentUser");
        model.addAttribute("username", userBO.getUsername());
        model.addAttribute("channels",getDictBOList(request, "goods_trading_channels"));
        VipLevelRq levelRq=new VipLevelRq();
        levelRq.setStatus(true);
        levelRq.setPage(0);
        levelRq.setSize(0);
        VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        model.addAttribute("levels", level.getDataList());
        return "/financed/selling/createorder";
    }

    /**
     * 跳转到代客下单支付页面
     *
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/payOrder.php")
    public String payorder(String id,HttpServletRequest request, Model model) {
        UserBO userBO = (UserBO)request.getSession().getAttribute("currentUser");
        model.addAttribute("username", userBO.getUsername());
        model.addAttribute("channels",getDictBOList(request, "goods_trading_channels"));
        VipLevelRq levelRq=new VipLevelRq();
        levelRq.setStatus(true);
        levelRq.setPage(0);
        levelRq.setSize(0);
        VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        model.addAttribute("levels", level.getDataList());
        OrderRs orderrs = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_INFO, null, OrderRs.class, id);
        model.addAttribute("order", orderrs.getData());
        List<OrderGiftBO> gift = orderrs.getData().getOrderGiftBOList();
        if(!StringUtils.isEmpty(gift)){
            for(OrderGiftBO bo :gift){
                if("COUPON".equals(bo.getOperType())){
                    CouponActivityRs couponActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPONACTIVITY_ONE, null,
                            CouponActivityRs.class, bo.getOperValue());
                    CouponActivity data = couponActivityRs.getData();
                    model.addAttribute("couponActivity", data.getActivityName());
                }
            }
        }
        return "/financed/selling/payorder";
    }

    /**
     * 代客下单
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/update.php")
    public @ResponseBody
    OrderRs update(@RequestBody OrderSubmitBO update,HttpServletRequest request){
            return SoaConnectionFactory.post(request, ConstantsUri.CREATEORDER_ADD, update, OrderRs.class,update.getUserId());
    }

    /**
     * 支付宝支付二维码
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/alipay.php")
    public @ResponseBody
    AliCode alierweima(AlipayRq rq, HttpServletRequest request, Model model) {
        rq.setNotify_url(BaseObject.getConfig("ucdomain")+"/callback.html");
        AliCodeRs rs = SoaConnectionFactory.post(request, ConstantsUri.ALIPAY_QRCODE, rq, AliCodeRs.class);
        return rs.getData();
    }

    /**
     * 微信支付二维码
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/weixin/qrcode.php")
    public @ResponseBody
    BaseResponse weixinerweima(WxPayOrder rq, HttpServletRequest request, Model model) {
        rq.setSpbill_create_ip("127.0.0.1");
        rq.setTrade_type("NATIVE");
        rq.setNotify_url(BaseObject.getConfig("ucdomain")+"/callback.html");
        WeiXinCodeRs rs = SoaConnectionFactory.post(request, ConstantsUri.WEIXIN_QRCODE, rq, WeiXinCodeRs.class);
        if("2000".equals(rs.getCode())){
            WeiXinCode data = rs.getData();
            data.setCode("2000");
            return data;
        }
        else{
            return rs;
        }
    }

    /**
     * 支付宝交易结果查询
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/ali/tradelog.php")
    public @ResponseBody BaseResponse aliPayResult(PayqueryReq payqueryReq,HttpServletRequest request){
        PayqueryRes rs= SoaConnectionFactory.get(request, ConstantsUri.ALIPAY_Q, payqueryReq, PayqueryRes.class);
        if("2000".equals(rs.getCode())){
            return rs.getData();
        }else{
            return rs;
        }
    }
    /**
     * 微信支付完成支付后回调
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:myselling")
    @RequestMapping("/financed/weixin/tradelog.php")
    public @ResponseBody BaseResponse weixinResult(PayqueryReq payqueryReq,HttpServletRequest request){
        TradeLogReq req = new TradeLogReq();
        req.setOrderNo(payqueryReq.getOut_trade_no());
        TradeLogRes tradeLogRes=SoaConnectionFactory.get(request, ConstantsUri.TRADELOG_LIST, req, TradeLogRes.class);
       if(!StringUtils.isEmpty(tradeLogRes.getDataList())&&tradeLogRes.getDataList().size()>0){
           List<TradeLog> dataList = tradeLogRes.getDataList();
           payqueryReq.setTransaction_id(dataList.get(0).getAliTrandeNo());
       }
            WeiXinPayqueryRes rs = new WeiXinPayqueryRes();
            rs= SoaConnectionFactory.get(request, ConstantsUri.WEIXIN_PAYQ, payqueryReq, WeiXinPayqueryRes.class);
            if("2000".equals(rs.getCode())){
                WeiXinPayquery pay = rs.getData();
                if(!StringUtils.isEmpty(pay.getCash_fee())){
                    Double fee =Double.parseDouble(pay.getCash_fee())/100;
                    pay.setCash_fee(fee.toString());
                    pay.setCode("2000");;
                }
                return pay;
            }else{
                return rs;
            }
    }

}
