package com.abc.controller.financed;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.course.CourseCriteria;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.consumer.VipLogRq;
import com.abc.soa.request.financed.CouponUserRq;
import com.abc.soa.request.financed.LogisticsRq;
import com.abc.soa.request.financed.OrderRq;
import com.abc.soa.response.cms.course.CourseListBoRs;
import com.abc.soa.response.consumer.VipLevelRs;
import com.abc.soa.response.consumer.VipLogRs;
import com.abc.soa.response.consumer.bo.VipLog;
import com.abc.soa.response.financed.*;
import com.abc.soa.response.financed.bo.*;
import com.abc.soa.response.system.bo.DictBO;
import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 订单管理
 *
 * @author zhushuai 2017-6-29
 */
@Controller
public class OrderController extends BaseController {
    /**
     * 订单列表
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:query")
    @RequestMapping("/financed/orderList.php")
    public String getOrderList(OrderRq re, String doType, String lookType,@RequestParam(value = "dateType", required = false) String dateType,HttpServletRequest request, Model model,HttpSession session) {
        OrderRs orderrs = new OrderRs();
        List<Order> lsit = new ArrayList<Order>();
        if ("1".equals(doType)) {//待处理订单
            re.setOrderStatus("4");
        }
        if ("2".equals(doType)) {//退单申请
            orderrs=SoaConnectionFactory.get(request, ConstantsUri.ORDER_BACK_LIST, re, OrderRs.class);;
            lsit=JSON.parseArray(orderrs.getDataList(),Order.class);

        } else {
            if(!"1".equals(dateType)){
                re.setStartTime(DateUtil.getYear()+"-"+DateUtil.getMonth()+"-01");
                re.setEndTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
            }
            String channel = re.getTradingChannels();
            if("JFCZ".equals(channel)){
                re.setGoodsId("JF0000000001");
            }
            else if("ALL".equals(channel)){
                re.setTradingChannels(null);
            }
            else if("HYCZ".equals(channel)){
                re.setGoodsId(re.getLevelId());
            }
            else if("CSKT".equals(channel)){
                re.setGoodsId(re.getCurriculumId());
            }
            orderrs = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LIST, re, OrderRs.class);
            lsit=JSON.parseArray(orderrs.getDataList(), Order.class);
            model.addAttribute("channels", getDictBOList(request, "goods_trading_channels"));
                VipLevelRq levelRq = new VipLevelRq();
                levelRq.setPage(0);
                levelRq.setSize(0);
                levelRq.setStatus(Boolean.TRUE);
                VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
                model.addAttribute("vipLevels", levelRs.getDataList());
                CourseCriteria criteria = new CourseCriteria();
                criteria.setPage(1);
                criteria.setSize(10000000);
                CourseListBoRs rs = SoaConnectionFactory.get(request, ConstantsUri.COURSE_LIST, criteria, CourseListBoRs.class);
                model.addAttribute("courseListBoRs", rs.getDataList());
                OrderRq rqq = re;
                rqq.setTradeMethod("RMB");
                MyOrderMoneyRs rss = SoaConnectionFactory.get(request, ConstantsUri.MYORDERMONEY_LIST, rqq, MyOrderMoneyRs.class);
                model.addAttribute("RMB", rss.getData());
                OrderRq rq = re;
                rq.setTradeMethod("POINTS");
                MyOrderMoneyRs rs1 = SoaConnectionFactory.get(request, ConstantsUri.MYORDERMONEY_LIST, rq, MyOrderMoneyRs.class);
                model.addAttribute("POINTS", rs1.getData());
            re.setTradingChannels(channel);

        }
        model.addAttribute("orderrs", lsit);
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
        //查询条件丢失
        session.setAttribute("OrderRq", re);
        return "/financed/order/list";
    }

    /**
     * 订单详情
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:query")
    @RequestMapping("/financed/orderInfo.php")
    public String getOrderInfo(String id, String doType,String lookType, HttpServletRequest request, Model model) {
        OrderRs orderrs = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_INFO, null, OrderRs.class, id);
        LogisticsRq logisticsrq= new LogisticsRq();
        LogisticsRs logisticsRs=SoaConnectionFactory.get(request, ConstantsUri.LOGISTIC_LIST, logisticsrq, LogisticsRs.class);
        model.addAttribute("logisticsRs", logisticsRs.getDataList());
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
        model.addAttribute("doType", doType);
        model.addAttribute("lookType", lookType);
        model.addAttribute("orderStatus", getDictBOList(request, "orderStatus"));
        model.addAttribute("invoicenames", getDictBOList(request, "invoicename"));
        OrderRq orderRq = (OrderRq) request.getSession().getAttribute("OrderRq");
        model.addAttribute("OrderRq", orderRq);
        CouponUserRq couponUserRq = new CouponUserRq();
        couponUserRq.setOrderNo(id);
        CouponUserRs couponUserRs = SoaConnectionFactory.get(request,
                ConstantsUri.COUPONUSER_ORDER, couponUserRq, CouponUserRs.class);
        List<CouponUserListBO> dataList = couponUserRs.getDataList();
        List<CouponUserListBO> list = new ArrayList<>();
        List<DictBO> dict = getDictBOList(request, "goods_trading_channels");
        String category ="";
            for(CouponUserListBO coupon : dataList){
                String categoryIds = coupon.getCategoryIds();
                if(!StringUtils.isEmpty(categoryIds)){
                    String [] ids = categoryIds.split(",");
                    if(ids.length>0){
                        for(DictBO bo:dict){
                        for(String caid : ids){
                                if(caid.equals(bo.getFieldValue())){
                                    if(!StringUtils.isEmpty(category)){
                                        category = bo.getFieldKey()+ ","+category;
                                    }
                                    else{
                                        category = bo.getFieldKey();
                                    }
                                }
                        }
                    }
                }
                coupon.setCategory(category);
                list.add(coupon);
            }
        }
        model.addAttribute("couponUserRs", list);
        model.addAttribute("couponMode", getDictBOList(request, "couponMode"));
        model.addAttribute("couponType", getDictBOList(request, "couponType"));
        VipLevelRq levelRq = new VipLevelRq();
        levelRq.setPage(0);
        levelRq.setSize(0);
        levelRq.setStatus(Boolean.TRUE);
        VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        model.addAttribute("vipLevels", levelRs.getDataList());
        return "/financed/order/info";
    }

    /**
     *导出订单信息
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:handle")
    @RequestMapping("/financed/exportExcel.php")
    public void exportOrderExecl(HttpServletRequest request, HttpServletResponse response) {
        Workbook workBook = null;
        List list = new ArrayList<>();
        try {
            OrderRq orderRq = new OrderRq();
            OrderExportRs orderRs = SoaConnectionFactory.get(request, ConstantsUri.ORDER_EXPORT, orderRq, OrderExportRs.class);
            list = orderRs.getDataList();
            List<OrderExport> excel=new ArrayList<OrderExport>();
            if(list!=null && list.size()>0){
                for(int i = 0;i<list.size();i++){
                    OrderExport bo = (OrderExport) list.get(i);
                    if(CommonUtils.nullOrBlank(bo.getPayMethod())){
                        bo.setPayMethod("RMB");
                    }
                    excel.add(bo);
                }
            }
            exportExecl(response, excel, "订单详情.xlsx", "tempfiles/ORDER_DETAIL.xlsx");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     *单个订单发货
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:handle")
    @PostMapping(value = "/financed/send.php")
    public @ResponseBody BaseResponse sendOrder(@RequestBody Order order,HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        return SoaConnectionFactory.post(request, ConstantsUri.ORDER_SEND, order, OrderRs.class);
    }

    /**
     *查询是否有需要导出的订单信息
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:handle")
    @RequestMapping(value = "/financed/queryExp.php")
    public ModelAndView queryExp(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        OrderRq orderRq = new OrderRq();
        OrderExportRs orderRs = SoaConnectionFactory.get(request, ConstantsUri.ORDER_EXPORT, orderRq, OrderExportRs.class);
        mav.addObject("result", orderRs);
        return mav;
    }

    /**
     *导入订单快递信息
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:handle")
    @RequestMapping(value = "/financed/importExcel.php", method = RequestMethod.POST)
    public ModelAndView importOrderExecl(MultipartHttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        List<Order> dataList = new ArrayList<>();
        String compId = null;
        if (request.getParameter("compId") != null) {
            compId = request.getParameter("compId");
        }
        //图片上传开始
        MultipartFile file = request.getFile("FILE01");
        if(!(file.getOriginalFilename().endsWith("xlsx")||file.getOriginalFilename().endsWith("xls"))){
            BaseResponse br = new BaseResponse("300", "上传文件类型错误!");
            mav.addObject("result", br);
            return mav;
        }
        if (file != null && file.getSize() > 0) {
            try {
                System.out.println("excel2json方法执行....");
                // Excel列的样式，主要是为了解决Excel数字科学计数的问题
                CellStyle cellStyle;
                // 根据Excel构成的对象
                Workbook wb;
                // 如果是2007及以上版本，则使用想要的Workbook以及CellStyle
                if (file.getOriginalFilename().endsWith("xlsx")) {
                    System.out.println("是2007及以上版本  xlsx");
                    wb = new XSSFWorkbook(file.getInputStream());
                    XSSFDataFormat dataFormat = (XSSFDataFormat) wb.createDataFormat();
                    cellStyle = wb.createCellStyle();
                    // 设置Excel列的样式为文本
                    cellStyle.setDataFormat(dataFormat.getFormat("@"));
                } else {
                    System.out.println("是2007以下版本  xls");
                    POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
                    wb = new HSSFWorkbook(fs);
                    HSSFDataFormat dataFormat = (HSSFDataFormat) wb.createDataFormat();
                    cellStyle = wb.createCellStyle();
                    // 设置Excel列的样式为文本
                    cellStyle.setDataFormat(dataFormat.getFormat("@"));
                }
                // 遍历每一个sheet
                Sheet sheet = wb.getSheetAt(0);
                // 一个sheet表对于一个List
                List list = new LinkedList();
                // 将第一行的列值作为正个json的key
                String[] cellNames;
                // 取第一行列的值作为key
                Row fisrtRow = sheet.getRow(0);
                // 得到第一行有多少列
                int curCellNum = fisrtRow.getLastCellNum();
                System.out.println("第一行的列数：" + curCellNum);
                // 根据第一行的列数来生成列头数组
                cellNames = new String[curCellNum];
                // 单独处理第一行，取出第一行的每个列值放在数组中，就得到了整张表的JSON的key
                for (int m = 0; m < curCellNum; m++) {
                    Cell cell = fisrtRow.getCell(0);
                    try {
                        // 设置该列的样式是字符串
                        cell.setCellStyle(cellStyle);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        // 取得该列的字符串值
                        cellNames[m] = cell.getStringCellValue();
                        if(!"订单编号".equals(cell.getStringCellValue())){
                            mav.addObject("result", new BaseResponse("9999", "Excel文件表头内容不正确！"));
                            return mav;
                        }
                        cell = fisrtRow.getCell(3);
                        cell.setCellStyle(cellStyle);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if(!"快递单号".equals(cell.getStringCellValue())){
                            mav.addObject("result", new BaseResponse("9999", "Excel文件表头内容不正确！"));
                            return mav;
                        }
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                System.out.println();
                // 从第二行起遍历每一行
                int rowNum = sheet.getLastRowNum();
                System.out.println("总共有 " + rowNum + " 行");
                for (int j = 1; j <= rowNum; j++) {
                    Order order = new Order();
                    // 取得某一行
                    Row row = sheet.getRow(j);
                    Cell cell = row.getCell(0);
                    if(StringUtils.isEmpty(cell.getStringCellValue())){
                        break;
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    order.setOrderNo(cell.getStringCellValue());
                    cell = row.getCell(3);
                    cell.setCellStyle(cellStyle);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    order.setExpressNo(cell.getStringCellValue());
                    // 保存该行的数据到该表的List中
                    dataList.add(order);
                }
                System.out.println("excel2json方法结束....");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OrderRs result = SoaConnectionFactory.post(request, ConstantsUri.ORDER_IMPORT, dataList, OrderRs.class, compId);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 订单删除操作
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:query")
    @RequestMapping("/financed/orderdel.php")
    public @ResponseBody
    BaseResponse orderdel(String id,String userId, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request,
                ConstantsUri.ORDER_DEL, null, BaseResponse.class,userId, id);
    }

    /**
     * 订单删除操作
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("order:query")
    @RequestMapping("/financed/orderreturn.php")
    public @ResponseBody
    BaseResponse orderreturn(@RequestBody OrderReturnBO bo, HttpServletRequest request) {
        VipLog log = new VipLog();
        log.setUserId(bo.getUserId());
        log.setLevel(bo.getLevel());
        log.setLevelId(bo.getLevelId());
        log.setVipExpireDate(DateUtil.strToDate(bo.getVipExpireDate(), "yyyy-MM-dd HH:mm:ss"));
        log.setSource(bo.getSource());
        /*if(bo.getId().equals("VIP0")){
            log.setLevel(bo.getLevel());
            log.setLevelId(bo.getLevelId());
        }
        VipLogRq vipLogRq = new VipLogRq();
        vipLogRq.setUserId(bo.getUserId());
        VipLogRs consumerInfoRs = SoaConnectionFactory.get(request,
                ConstantsUri.CONSUMER_VIPLOG, vipLogRq, VipLogRs.class);
        List<VipLog> dataList = consumerInfoRs.getDataList();
        for(VipLog logs : dataList){
           if(logs.getId().equals(bo.getId())){
               log = logs;
           }
        }*/
        return SoaConnectionFactory.put(request,
                ConstantsUri.ORDER_RETURN, log, BaseResponse.class, bo.getOrderNo(), bo.getGoodsId());
    }

    /**
     * Vip日志查询
     *
     * @param
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("order:query")
    @RequestMapping("/financed/vipchoose.php")
    public String vipLog(String orderNo,String goodsId,VipLogRq vipLogRq, HttpServletRequest request,
                         Model model) {
        vipLogRq.setSize(10000);
        model.addAttribute("referer", request.getHeader("Referer"));
        VipLogRs consumerInfoRs = SoaConnectionFactory.get(request,
                ConstantsUri.CONSUMER_VIPLOG_ORDER, vipLogRq, VipLogRs.class);
        List<VipLog> dataList = consumerInfoRs.getDataList();
        List<VipLog> list = new ArrayList<>();
        Date date = new Date();
        for(VipLog log : dataList){
            log.setOutdated(DateUtil.compareTimeBig(date, log.getVipExpireDate()));
           if(orderNo.equals(log.getSource())){
                log.setOutdated(true);
            }
            if(!"6".equals(log.getOrderStatus())){
                log.setOutdated(true);
            }
            list.add(log);
        }
        for (VipLog log:list) {
            if(!log.getOutdated()){
               log.setIsChecked(true);
                break;
            }
        }
        model.addAttribute("vipLogs", list);
        vipLogRq.setTotalItems(consumerInfoRs.getTotal());
        vipLogRq.calculate();
        model.addAttribute("BaseRq", vipLogRq);
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("goodsId", goodsId);
        model.addAttribute("orderStatus", getDictBOList(request, "orderStatus"));
        VipLevelRq levelRq = new VipLevelRq();
        levelRq.setPage(0);
        levelRq.setSize(0);
        levelRq.setStatus(Boolean.TRUE);
        VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        model.addAttribute("vipLevels", levelRs.getDataList());
        return "/financed/order/vipchoose";
    }
}
