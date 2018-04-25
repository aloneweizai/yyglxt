package com.abc.controller.cszj;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cszj.RepackRq;
import com.abc.soa.request.cszj.WxActivityRq;
import com.abc.soa.request.financed.CouponActivityRq;
import com.abc.soa.response.cszj.RepackRs;
import com.abc.soa.response.cszj.WxActivityRs;
import com.abc.soa.response.cszj.bo.*;
import com.abc.soa.response.financed.CouponActivityRs;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/cszjs/wxactivity")
public class WxActivityController extends BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(WxActivityController.class);

    /**
     * 微信红包活动列表查询
     *
     * @param wxActivityRq
     * @param request
     * @return
     */
    @RequestMapping("/list.php")
    public String selectList(WxActivityRq wxActivityRq, HttpServletRequest request, Model model,HttpSession session) {
        WxActivityRs wxActivityRs = SoaConnectionFactory.get(request, ConstantsUri.WXACTIVITY_LIST, wxActivityRq, WxActivityRs.class);
        model.addAttribute("rq",wxActivityRq);
        List<WxActivityBO> dataList = wxActivityRs.getDataList();
        List<WxActivityBO> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<dataList.size();i++){
            WxActivityBO bo = dataList.get(i);
            bo.setIsChecked(DateUtil.compareTimeBig(date, DateUtil.strToDate(bo.getEndTime(), "yyyy-MM-dd HH:mm:ss")));
            list.add(bo);
        }
        model.addAttribute("wxActivityRs", list);
        wxActivityRq.setTotalItems(wxActivityRs.getTotal());
        wxActivityRq.calculate();
        model.addAttribute("pagination", wxActivityRq);
        //查询条件丢失
        session.setAttribute("wxActivityRq", wxActivityRq);
        return "cszj/wxactivity/list";
    }

    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/editform.php")
    public String editform( @RequestParam(required = false) String id, HttpServletRequest request, Model model) {

        if (!StringUtils.isEmpty(id)) {
            WxActivityRs wxActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.WXACTIVITY_INFO, null, WxActivityRs.class, id);
            model.addAttribute("WxActivity", wxActivityRs.getData());
            if(!StringUtils.isEmpty(wxActivityRs.getData().getActivityId())){
                CouponActivityRs couponActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPONACTIVITY_ONE, null, CouponActivityRs.class, wxActivityRs.getData().getActivityId());
                model.addAttribute("activityName", couponActivityRs.getData().getActivityName());
            }
        }

        return "cszj/wxactivity/form_edit";
    }

    /**
     * 微信红包活动预览
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/look.php")
    public String look( @RequestParam String id, HttpServletRequest request, Model model) {
            WxActivityRs wxActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.WXACTIVITY_INFO, null, WxActivityRs.class, id);
        if(!StringUtils.isEmpty(wxActivityRs.getData().getActivityId())){
            CouponActivityRs couponActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPONACTIVITY_ONE, null, CouponActivityRs.class, wxActivityRs.getData().getActivityId());
            model.addAttribute("activityName", couponActivityRs.getData().getActivityName());
        }
        model.addAttribute("WxActivity", wxActivityRs.getData());
       return "cszj/wxactivity/look";
    }


    /**
     * 微信红包活动预览
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/edithbkl.php")
    public String edithbkl( @RequestParam String id, HttpServletRequest request, Model model) {
        RepackBO obj = new RepackBO();
        RepackRs repackRs = SoaConnectionFactory.getRestful(request, ConstantsUri.REDPACK_SECRET, obj, RepackRs.class, id);
        if (!StringUtils.isEmpty(repackRs.getData())) {
            model.addAttribute("repackRs", repackRs.getData());
        }
        else{
            model.addAttribute("message", repackRs.getMessage());
        }
        return "cszj/wxactivity/edit";
    }

    /**
     * 微信红包口令编辑
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/update.php")
    public @ResponseBody BaseResponse update(@RequestBody WxRedEnvelopUpdateBO update,HttpServletRequest request){
        WxRedEnvelopBO bo = new WxRedEnvelopBO();
        bo.setId(update.getId());
        bo.setOpenId(update.getOpenId());
        try {
            bo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(update.getCreateTimes()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return SoaConnectionFactory.put(request, ConstantsUri.REDPACK_EDIT, bo, BaseResponse.class, update.getId());
    }

    /**
     * 优惠券列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequestMapping("/couponActivity/list.php")
    public String selectActivityList(CouponActivityRq rq, @RequestParam(required = false) String activityid,HttpServletRequest request, Model model) {
        rq.setStatus("2");
        rq.setIsOverdue("1");
        CouponActivityRs couponActivityRs = SoaConnectionFactory.get(request, ConstantsUri.COUPONACTIVITY_LIST, rq, CouponActivityRs.class);
        model.addAttribute("couponActivityRs", couponActivityRs.getDataList());
        rq.setTotalItems(couponActivityRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);
        model.addAttribute("couponStatus", getDictBOList(request, "couponStatus"));
        model.addAttribute("activityid", activityid);
        return "cszj/wxactivity/couponActivityList";
    }
    /**
     * 微信红包活动新增修改
     *
     * @param obj
     * @param request
     * @return
     */
    @PostMapping("/save.php")
    public @ResponseBody  BaseResponse  save(@RequestBody WxActivityBO obj, HttpServletRequest request) {
        LOGGER.info("WxActivitySave  {}", obj.toString());
        if(!obj.getGiftCoupon()){
            obj.setActivityId(null);
        }
        String id = obj.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.WXACTIVITY_LIST, obj, WxActivityRs.class);

        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.WXACTIVITY_INFO, obj, WxActivityRs.class, id);
        }
        return returnObj;
    }

    /**
     * 微信红包活动删除
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/del.php")
    public
    @ResponseBody
    BaseResponse del(String id, HttpServletRequest request) {
        return  SoaConnectionFactory.delete(request, ConstantsUri.WXACTIVITY_INFO, null, WxActivityRs.class, id);
    }

    /**
     * 微信红包活动明细列表
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/detailList.php")
    public String detailList(RepackRq rq,
    		@RequestParam(required=false)String isFirst,
    		@RequestParam(required=false)String backUrl,
    		  HttpServletRequest request, Model model) {
    	if(StringUtils.isEmpty(isFirst)){
    		request.setAttribute("backUrl", request.getHeader("Referer"));
    	}else{
    		request.setAttribute("backUrl", backUrl);
    	}
    	RepackRs repackRs = SoaConnectionFactory.get(request,
                ConstantsUri.REDPACK_LIST, rq, RepackRs.class);
        model.addAttribute("repackRs", repackRs.getDataList());
        rq.setTotalItems(repackRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("pagination", rq);
        model.addAttribute("BaseRq", rq);
        model.addAttribute("receiveStatus", getDictBOList(request, "receiveStatus"));
        model.addAttribute("sendStatus", getDictBOList(request, "sendStatus"));
        WxActivityRq wxActivityRq = (WxActivityRq) request.getSession().getAttribute("wxActivityRq");
        model.addAttribute("wxActivityRq", wxActivityRq);
        return "cszj/wxactivity/detailList";
    }

    /**
     * 微信红包活动停启用接口
     *
     * @param obj
     * @param
     * @return
     */
    @PostMapping("/check.php")
    public @ResponseBody BaseResponse check(WxActivityBO obj,HttpServletRequest request){
        WxActivityRs rs = SoaConnectionFactory.getRestful(request, ConstantsUri.WXACTIVITY_INFO, null, WxActivityRs.class, obj.getId());
        WxActivityBO data = rs.getData();
        data.setStatus(obj.getStatus());
        return  SoaConnectionFactory.put(request, ConstantsUri.WXACTIVITY_INFO, data, BaseResponse.class, obj.getId());
    }

    /**
     * 微信红包活动---同步红包明细信息
     *
     * @param obj
     * @param
     * @return
     */
    @PostMapping("/queryinfo.php")
    public @ResponseBody BaseResponse queryredpack(RepackBO obj,HttpServletRequest request){
        return  SoaConnectionFactory.getRestful(request, ConstantsUri.REDPACK_SECRET, obj, BaseResponse.class, obj.getId());
    }

    /**
     * 微信红包活动--重新发送红包
     *
     * @param obj
     * @param request
     * @return
     */
    @PostMapping("/resend.php")
    public @ResponseBody BaseResponse resend(RepackBO obj,HttpServletRequest request){
        return  SoaConnectionFactory.put(request, ConstantsUri.REDPACK_RESEND, obj, BaseResponse.class, obj.getId());
    }

    /**
     * 微信红包活动--导入红包口令
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/import.php", method = RequestMethod.POST)
    public ModelAndView importExecl(MultipartHttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        List<RepackBO> dataList = new ArrayList<>();
        //图片上传开始
        MultipartFile file = request.getFile("uploadFile");
        if(!(file.getOriginalFilename().endsWith("xlsx")||file.getOriginalFilename().endsWith("xls"))){
            BaseResponse br = new BaseResponse("300", "上传文件类型错误!");
            mav.addObject("result", br);
            return mav;
        }
        if (file != null && file.getSize() > 0) {
            try {
                dataList = excel2json(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        RepackRs result = SoaConnectionFactory.post(request, ConstantsUri.REDPACK_IMPORT, dataList, RepackRs.class);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 微信红包活动---解析导入文件
     *
     * @param file
     * @param
     * @return
     */
    public static List excel2json(MultipartFile file) throws IOException, ParseException {
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
            Cell cell = fisrtRow.getCell(m);
            try {
                // 设置该列的样式是字符串
                cell.setCellStyle(cellStyle);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                // 取得该列的字符串值
                cellNames[m] = cell.getStringCellValue();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        System.out.println();
        // 从第二行起遍历每一行
        int rowNum = sheet.getLastRowNum();
        System.out.println("总共有 " + rowNum + " 行");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int j = 1; j <= rowNum; j++) {
            RepackBO repackBO = new RepackBO();
            // 取得某一行
            Row row = sheet.getRow(j);
            Cell cell = row.getCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            repackBO.setSecret(cell.getStringCellValue());
            cell = row.getCell(1);
                cell.setCellStyle(cellStyle);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            repackBO.setActivityId(cell.getStringCellValue().trim());
            // 保存该行的数据到该表的List中
            list.add(repackBO);

        }
        System.out.println("excel2json方法结束....");
        return list;

    }

    /**
     * 微信红包活动---下载导入红包口令模板
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("/downloadExcel.php")
    public void exportOrderExecl(HttpServletRequest request, HttpServletResponse response) {
        List list = new ArrayList<>();
        try {
            exportExecl(response, list, "红包口令模板.xlsx", "tempfiles/HBML.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* 批量删除 */
    @DeleteMapping("/batchDel.php")
    public @ResponseBody BaseResponse batchDelete(HttpServletRequest request, @RequestBody List<String> ids){
        List<IdBo> idss = new ArrayList<>();
        for(int i =0;i<ids.size();i++){
            IdBo id= new IdBo();
            id.setId(ids.get(i));
            idss.add(id);
        }
        return SoaConnectionFactory.post(request, ConstantsUri.REDPACK_BATCH, idss, BaseResponse.class);
    }
    /**
     * 微信红包口令删除操作（未抽过奖的）
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView delmx(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.REDPACK_DEL, null, BaseResponse.class, id);
        System.out.print(result);
        mav.addObject("result", result);
        return mav;
    }
}
