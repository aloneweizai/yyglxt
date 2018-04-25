package com.abc.controller.financed;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.LogisticsRq;
import com.abc.soa.request.financed.OrderChangeRq;
import com.abc.soa.response.financed.LogisticsRs;
import com.abc.soa.response.financed.OrderChangeRes;
import com.abc.soa.response.financed.OrderRs;
import com.abc.soa.response.financed.SfExportRs;
import com.abc.soa.response.financed.bo.*;
import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/**
 * 换货申请
 * @author zhushuai 2017-8-10
 *
 */
@Controller
public class OrderChangeController extends BaseController{
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderChangeController.class);

	/**
	 * 退换货列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/applications.php")
	public String orderchangeList(OrderChangeRq backRq,HttpServletRequest request, Model model){
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		
		OrderChangeRes orderChangeRes=SoaConnectionFactory.get(request, ConstantsUri.EXCHANGE_LIST, backRq, OrderChangeRes.class);
		model.addAttribute("orderChangeRes", orderChangeRes.getDataList());
		backRq.setTotalItems(orderChangeRes.getTotal());
		backRq.calculate();
		
		model.addAttribute("exchange_status", getDictBOList(request, "exchange_status"));
		model.addAttribute("exchange_reason", getDictBOList(request, "exchange_reason"));
		model.addAttribute("BaseRq", backRq);
		LogisticsRq logisticsrq= new LogisticsRq();
		LogisticsRs logisticsRs=SoaConnectionFactory.get(request, ConstantsUri.LOGISTIC_LIST, logisticsrq, LogisticsRs.class);
		model.addAttribute("logisticsRs",logisticsRs.getDataList());
		return "/financed/orderchange/list";
	}

	/**
	 * 退款列表
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:refund")
	@RequestMapping("/orderchange/cw/applications.php")
	public String orderchangeListCW(OrderChangeRq backRq,HttpServletRequest request, Model model){
		model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
		
		OrderChangeRes orderChangeRes=SoaConnectionFactory.get(request, ConstantsUri.EXCHANGE_LISTF, backRq, OrderChangeRes.class);
		model.addAttribute("orderChangeRes", orderChangeRes.getDataList());
		backRq.setTotalItems(orderChangeRes.getTotal());
		backRq.calculate();
		
		model.addAttribute("exchange_status", getDictBOList(request, "exchange_status"));
		model.addAttribute("exchange_reason", getDictBOList(request, "exchange_reason"));
		model.addAttribute("BaseRq", backRq);
		model.addAttribute("referer", request.getHeader("Referer"));
		return "/financed/orderchange/cw/list";
	}

	/**
	 * 跳转到退款详情页面
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:refund")
	@RequestMapping("/orderchange/cw/charge.php")
	public String chargecw(@RequestParam("id") String id,String lookType,HttpServletRequest request, Model model){
		model.addAttribute("exchange_status", getDictBOList(request, "exchange_status"));
		model.addAttribute("exchange_reason", getDictBOList(request, "exchange_reason"));
		
		OrderChangeRes orderbackRes=SoaConnectionFactory.getRestful(request, ConstantsUri.EXCHANGE_INFO, null, OrderChangeRes.class,id );
		OrderExchange orderExchange=orderbackRes.getData();
		
		OrderRs orders = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_INFO, null, OrderRs.class, orderExchange.getOrderNo());
		model.addAttribute("orderExchange", orderExchange);
		model.addAttribute("orders", orders.getData());
		model.addAttribute("referer", request.getHeader("Referer"));
		model.addAttribute("lookType", lookType);
		return "/financed/orderchange/cw/info";
	}

	/**
	 * 跳转到退换货审核，确认收货，确认退单页面，退换货详情页面，退换货订单详情页面
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/charge.php")
	public String charge(@RequestParam(value = "id", required = false) String id,
						 @RequestParam(value = "status", required = false) String status,
						 @RequestParam(value = "type", required = false) String type,HttpServletRequest request, Model model){
		model.addAttribute("exchange_status", getDictBOList(request, "exchange_status"));
		model.addAttribute("exchange_reason", getDictBOList(request, "exchange_reason"));
		
		OrderChangeRes orderbackRes=SoaConnectionFactory.getRestful(request, ConstantsUri.EXCHANGE_INFO, null, OrderChangeRes.class,id );
		OrderExchange orderExchange=orderbackRes.getData();
		
		OrderRs orders = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_INFO, null, OrderRs.class, orderExchange.getOrderNo());
		model.addAttribute("orderExchange", orderExchange);
		model.addAttribute("orders", orders.getData());
		if ("1".equals(status)) {//审核页面
			return "/financed/orderchange/charge_info";
		}
		else if ("2".equals(status)){
			if("1".equals(type)){//确认收货
				return "/financed/orderchange/makesure_info";
			}
			else{//确认退单
				return "/financed/orderchange/orderback_info";
			}
		}
		else if ("0".equals(status)){//退换货信息页面
			return "/financed/orderchange/edit_info";
		}
		else{//订单详情查看页面
			return "/financed/orderchange/info";
		}

	}

	/**
	 * 退换货审核，退换货确认收货
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/docharge.php")
	public @ResponseBody BaseResponse docharge(@RequestBody OrderExchange exchange,HttpServletRequest request){
		if("2".equals(exchange.getStatus())){//审核通过
			return SoaConnectionFactory.putRestful(request, ConstantsUri.EXCHANGE_AGREE, exchange, BaseResponse.class, exchange.getId());
		}else if("5".equals(exchange.getStatus())){//审核决绝
			return SoaConnectionFactory.putRestful(request, ConstantsUri.EXCHANGE_DISAGREE, exchange, BaseResponse.class, exchange.getId());
		}else {//换货确认收货
			return SoaConnectionFactory.putRestful(request, ConstantsUri.EXCHANGE_CONFIIRM, exchange, BaseResponse.class, exchange.getId());
		}
		
	}

	/**
	 * 退换货确认退单
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/orderback.php")
	public @ResponseBody BaseResponse orderback(@RequestBody ExchangeBack exchange,HttpServletRequest request){
		return SoaConnectionFactory.putRestful(request, ConstantsUri.EXCHANGE_BACK, exchange, BaseResponse.class, exchange.getId());
	}

	/**
	 * 退款操作
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:refund")
	@RequestMapping("/orderchange/refund.php")
	public @ResponseBody BaseResponse refund(@RequestBody ExchangeRefund exchange,HttpServletRequest request){
		return SoaConnectionFactory.putRestful(request, ConstantsUri.EXCHANGE_REFUND, exchange, BaseResponse.class, exchange.getId());
	}

	/**
	 * 查询出是否有需要导出的发货信息
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/qexport.php")
	public @ResponseBody boolean qexport(HttpServletRequest request,HttpServletResponse response){
		 SfExportRs sfExportRs = SoaConnectionFactory.get(request, ConstantsUri.EXCHANGE_EXPORT, null, SfExportRs.class);
		 List<SfExportBO> sfExportBOs=sfExportRs.getDataList();
		 return (sfExportBOs!=null && sfExportBOs.size()>0)?true:false;
	}

	/**
	 * 导出发货信息
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/export.php")
	public void export(HttpServletRequest request,HttpServletResponse response,String compId){
		LogisticsRs logisticsRs=SoaConnectionFactory.getRestful(request, ConstantsUri.LOGISTIC_INFO, null, LogisticsRs.class, compId);
		Logistics data = logisticsRs.getData();
		 SfExportRs sfExportRs=SoaConnectionFactory.get(request, ConstantsUri.EXCHANGE_EXPORT, null, SfExportRs.class);
		 List<SfExportBO> sfExportBOs=sfExportRs.getDataList();
		String path = SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context")+data.getTemplateUrl();
		String p = SpringCtxHolder.getProperty("logging.path");
		String filename = "";
		String url = data.getTemplateUrl();
		int a = url.lastIndexOf("/");
		filename = url.substring(a + 1, url.length());
		File targetFile = new File(p, filename);
		try {
			if (!targetFile.exists()) {
				targetFile.createNewFile();
				getFile(path,p, filename);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		exportExeclfp(response, sfExportBOs, "退换货快递信息.xls", targetFile,filename);
		 //exportExecl(response, sfExportBOs, "退换货快递信息.xls", "tempfiles/EXCHANGE_DATA.xls");
	}
	public void getFile(String downpath,String path,String filename) {
		try {
			HttpEntity httpEntity = new HttpEntity(null);
			byte[] files = SoaConnectionFactory.exchange(restTemplate,downpath,
					HttpMethod.GET, httpEntity, byte[].class);
			FileOutputStream fos = new FileOutputStream(path +"//"+ filename);
			fos.write(files);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入发货快递信息
	 *
	 * @param
	 * @param
	 * @return
	 */
	@RequiresPermissions("order:orderchange")
	@RequestMapping("/orderchange/import.php")
	public @ResponseBody BaseResponse importExcel(MultipartHttpServletRequest multipartRequest){
		String compId = null;
		if (multipartRequest.getParameter("compId") != null) {
			compId = multipartRequest.getParameter("compId");
		}
		LOGGER.info("开始解析Excel文件.....");
		MultipartFile file =  multipartRequest.getFile("uploadFile");
		CellStyle cellStyle;
        // 根据Excel构成的对象
        Workbook wb;
        // 如果是2007及以上版本，则使用想要的Workbook以及CellStyle
        try {
			if (file.getOriginalFilename().endsWith("xlsx")) {
			    wb = new XSSFWorkbook(file.getInputStream());
			    XSSFDataFormat dataFormat = (XSSFDataFormat) wb.createDataFormat();
			    cellStyle = wb.createCellStyle();
			    // 设置Excel列的样式为文本
			    cellStyle.setDataFormat(dataFormat.getFormat("@"));
			} else {
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
			List<SfImportBO> list = new LinkedList<SfImportBO>();
			
			// 从第二行起遍历每一行
			int rowNum = sheet.getLastRowNum();
			for (int j = 1; j <= rowNum; j++) {
				SfImportBO importBO = new SfImportBO();
			    // 取得某一行
			    Row row = sheet.getRow(j);
			    Cell cell = row.getCell(0);
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    importBO.setOrderNo(cell.getStringCellValue());
			    cell = row.getCell(1);
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    importBO.setExpressNo(cell.getStringCellValue());
				importBO.setExpressComp(compId);
			    // 保存该行的数据到该表的List中
			    list.add(importBO);
			}
			System.out.println(JSON.toJSONString(list));
			return SoaConnectionFactory.put(multipartRequest, ConstantsUri.EXCHANGE_IMPORT, list, BaseResponse.class, new Object());
		} catch (IOException e) {
			LOGGER.info("解析Excel文件失败:",e);
			return new BaseResponse("9999", "解析Excel文件失败:"+e.getMessage());
		}
	}
	
}
