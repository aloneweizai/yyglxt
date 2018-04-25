package com.abc.controller.financed;


import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.InvoiceBackRq;
import com.abc.soa.request.financed.InvoiceRepoRq;
import com.abc.soa.request.financed.InvoiceRq;
import com.abc.soa.request.financed.LogisticsRq;
import com.abc.soa.response.financed.*;
import com.abc.soa.response.financed.bo.*;
import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 发票仓库
 * 
 * @author zhushuai 2017-7-13
 * 
 */
@Controller
public class InvoiceController extends BaseController{
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
	/**
	 * 发票仓库列表
	 * @param rq 查询条件
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoList.php")
	public String invoiceRepoQuery(InvoiceRepoRq rq,
			HttpServletRequest request, Model model,HttpSession session) {
		if(StringUtils.isEmpty(rq.getInvoiceNoEnd())){
			rq.setInvoiceNoEnd(rq.getInvoiceNoStart());
		}
		if(StringUtils.isEmpty(rq.getInvoiceNoStart())){
			rq.setInvoiceNoStart(rq.getInvoiceNoEnd());
		}
		if(StringUtils.isEmpty(rq.getNoEnd())){
			rq.setNoEnd(rq.getNoStart());
		}
		if(StringUtils.isEmpty(rq.getNoStart())){
			rq.setNoStart(rq.getNoEnd());
		}
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
		//查询条件丢失
		session.setAttribute("InvoiceRepoRq", rq);
		return "/financed/invoiceRepo/list";
	}

	/**
	 * 发票仓库详情列表
	 * @param rq 查询条件
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoDetailList.php")
	public String invoiceRepoDetailQuery(InvoiceRepoRq rq,
			HttpServletRequest request, Model model) {
		InvoiceRepoDetailRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICEREPO_DETIALLIST, rq, InvoiceRepoDetailRs.class);
		//发票种类
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		//库存状态
		model.addAttribute("repoStatus", getDictBOList(request, "repoStatus"));
		model.addAttribute("invoiceRepoRqs", repoRs.getDataList());
		rq.setTotalItems(repoRs.getTotal());
		rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
		rq.calculate();
		model.addAttribute("pagination", rq);
		model.addAttribute("BaseRq", rq);
		//发票状态
		model.addAttribute("invoicestatus", getDictBOList(request, "invoicestatus"));
		
		return "/financed/invoiceRepo/detailList";
	}

	/**
	 * 发票仓库单个详情 ，跳转到修改页面
	 * @param invoiceRepoId 发票仓库ID
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoDetail.php")
	public String invoiceRepoDetail(@RequestParam(required = false) String invoiceRepoId, HttpServletRequest request,Model model) {
		InvoiceRepoId rs = SoaConnectionFactory.getRestful(request, ConstantsUri.INVOICEREPO_SELECT, null, InvoiceRepoId.class, invoiceRepoId);
		InvoiceRepoRq invoiceRepoRq = (InvoiceRepoRq) request.getSession().getAttribute("InvoiceRepoRq");
		model.addAttribute("InvoiceRepoRq", invoiceRepoRq);
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		model.addAttribute("invoiceRepo", rs.getData());
		return "/financed/invoiceRepo/edit";
	}

	/**
	 * 根据发票种类代码，获取发票仓库ID
	 * @param code 发票种类代码
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoId.php")
	public @ResponseBody InvoiceRepoBo selectInvoiceRepoNum(HttpServletRequest request,@RequestParam("code") String code){
		InvoiceRepoId rs = SoaConnectionFactory.getRestful(request, ConstantsUri.INVOICEREPO_CODE, null, InvoiceRepoId.class, code);
		return rs.getData();
	}

	/**
	 * 根据发票种类代码，获取发票仓库ID
	 * @param id
	 * @param invoiceCode 发票种类代码
	 * @param invoiceNoStart 发票号码起
	 * @param invoiceNoEnd 发票号码止
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/validate.php")
	public @ResponseBody Boolean validateInvoice(HttpServletRequest request,@RequestParam(value = "id", required = false) String id,
												 @RequestParam(value = "invoiceCode", required = false) String invoiceCode,
													   @RequestParam(value = "invoiceNoStart", required = false) String invoiceNoStart,
													   @RequestParam(value = "invoiceNoEnd", required = false) String invoiceNoEnd){
		InvoiceRepoRq rq = new InvoiceRepoRq();
		rq.setId(id);
		rq.setInvoiceCode(invoiceCode);
		rq.setInvoiceNoStart(invoiceNoStart);
		rq.setInvoiceNoEnd(invoiceNoEnd);
		InvoiceRepoValidateRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICEREPO_VALIDATE, rq, InvoiceRepoValidateRs.class);
		return repoRs.getData();
	}

	/**
	 * 跳转到新增页面
	 * @param doType 0：新增 1：修改
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoAdd.php")
	public String invoiceRepoAdd(@RequestParam(required = false) String doType, HttpServletRequest request,Model model) {
		InvoiceRepoRq invoiceRepoRq = (InvoiceRepoRq) request.getSession().getAttribute("InvoiceRepoRq");
		model.addAttribute("InvoiceRepoRq", invoiceRepoRq);
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		if("0".equals(doType)){
			return "/financed/invoiceRepo/add";
		}
		return "/financed/invoiceRepo/batch_add";
	}

	/**
	 * 发票仓库入库
	 * @param invoiceRepoSaveUpdBo
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoSave.php")
	public @ResponseBody
	InvoiceRepo invoiceRepoSave(@RequestBody InvoiceRepoSaveUpdBo invoiceRepoSaveUpdBo,
			HttpServletRequest request) {
		InvoiceRepoBo invoiceRepo = new InvoiceRepoBo();
		invoiceRepo = invoiceRepoSaveUpdBo.getInvoiceRepo();
		invoiceRepo.setStatus("0");
		;
		return SoaConnectionFactory.post(request, ConstantsUri.INVOICEREPO_ADD,
				invoiceRepo, InvoiceRepo.class);
	}
	/**
	 * 发票仓库入库修改
	 * @param invoiceRepo
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepoUpdate.php")
	public @ResponseBody
	InvoiceRepo invoiceRepoUpdate(@RequestBody InvoiceRepoBo invoiceRepo,
								  HttpServletRequest request) {
		invoiceRepo.setStatus("0");;
		return SoaConnectionFactory.put(request, ConstantsUri.INVOICEREPO_EDIT,
				invoiceRepo, InvoiceRepo.class, invoiceRepo.getId());
	}
	/**
	 * 发票仓库删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceRepodel.php")
	public @ResponseBody
	BaseResponse invoiceRepodel(String id, HttpServletRequest request) {
		return SoaConnectionFactory.delete(request,
				ConstantsUri.INVOICEREPO_DEL, null, BaseResponse.class, id);
	}
	/**
	 * 发票删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceDitaildel.php")
	public @ResponseBody
	BaseResponse invoiceDitaildel(String id, HttpServletRequest request) {
		return SoaConnectionFactory.delete(request,
				ConstantsUri.INVOICEDT_DEL, null, BaseResponse.class, id);
	}
	/**
	 * 发票作废
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:invoiceRepo")
	@RequestMapping("/financed/invoiceDtDisable.php")
	public @ResponseBody
	BaseResponse invoiceDitailDisable(String id, HttpServletRequest request) {
		return SoaConnectionFactory.put(request,
				ConstantsUri.INVOICEDT_DISABLE, null, BaseResponse.class, id);
	}
	
	/**
	 * 发票列表管理
	 * @param rq 查询条件
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoiceList.php")
	public String invoiceQuery(InvoiceRq rq,
			HttpServletRequest request, Model model) {
		InvoiceRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICE_LIST, rq, InvoiceRs.class);
		model.addAttribute("invoiceRepoRqs", repoRs.getDataList());
		rq.setTotalItems(repoRs.getTotal());
		rq.calculate();
		model.addAttribute("BaseRq", rq);
		model.addAttribute("invoicecontents", getDictBOList(request, "invoicecontent"));
		model.addAttribute("invoicenames", getDictBOList(request, "invoicename"));
		model.addAttribute("invoicetypes", getDictBOList(request, "tyfp"));
		model.addAttribute("fqsqstatuss", getDictBOList(request, "fqsqstatus"));
		LogisticsRq logisticsrq= new LogisticsRq();
		LogisticsRs logisticsRs=SoaConnectionFactory.get(request, ConstantsUri.LOGISTIC_LIST, logisticsrq, LogisticsRs.class);
		model.addAttribute("logisticsRs",logisticsRs.getDataList());
		model.addAttribute("referer", request.getHeader("Referer"));
		return "/financed/invoice/list";
	}
	
	/**
	 * 发票列表详情
	 * @param id
	 * @param lookType 0 查看页面 1 审批页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoiceinfo.php")
	public String invoiceQuery(String  id,String lookType,
			HttpServletRequest request, Model model) {
		InvoiceRs repoRs = SoaConnectionFactory.getRestful(request,
				ConstantsUri.INVOICE_INFO, null, InvoiceRs.class, id);
		Invoice invoice= repoRs.getData();
		model.addAttribute("lookType", lookType);
		model.addAttribute("orderStatus", getDictBOList(request, "orderStatus")); 
		model.addAttribute("invoicecontents", getDictBOList(request, "invoicecontent"));
		model.addAttribute("invoicenames", getDictBOList(request, "invoicename"));
		model.addAttribute("invoicetypes", getDictBOList(request, "tyfp"));
		model.addAttribute("fqsqstatuss", getDictBOList(request, "fqsqstatus"));
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		model.addAttribute("invoice", invoice);
		model.addAttribute("referer", request.getHeader("Referer"));
		return "/financed/invoice/info";
	}

	/**
	 * 发票订单列表详情
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoice/orderinfo.php")
	public String invoiceQueryOrder(String  id, HttpServletRequest request, Model model) {
		InvoiceRs repoRs = SoaConnectionFactory.getRestful(request,
				ConstantsUri.INVOICE_INFO, null, InvoiceRs.class, id);
		Invoice invoice= repoRs.getData();
		List<Order> orderBOList = invoice.getOrderBOList();
		List<Order> dataList = new ArrayList<>();
		for(Order order:orderBOList){
			OrderRs orderrs = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_INFO, null, OrderRs.class, order.getOrderNo());
			dataList.add(orderrs.getData());
		}
		InvoiceRq rq = new InvoiceRq();
		model.addAttribute("BaseRq", rq);
		model.addAttribute("orders",dataList);
		model.addAttribute("orderStatus", getDictBOList(request, "orderStatus"));
		model.addAttribute("invoicenames", getDictBOList(request, "invoicename"));
		return "/financed/invoice/orderinfo";
	}
	
	/**
	 * 发票审核
	 * @param invoiceCheck
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoiceBill.php")
	public @ResponseBody
	BaseResponse invoiceBill(@RequestBody InvoiceCheck invoiceCheck, HttpServletRequest request)  {
		return SoaConnectionFactory.post(request, ConstantsUri.INVOICE_BILL, invoiceCheck, BaseResponse.class);
	}

	/**
	 * 发票审核
	 * @param invoiceInvalidBO
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoiceInvalid.php")
	public @ResponseBody
	BaseResponse invoiceInvalid(@RequestBody InvoiceInvalidBO invoiceInvalidBO, HttpServletRequest request)  {
		return SoaConnectionFactory.put(request, ConstantsUri.INVOICE_INVALID, invoiceInvalidBO, BaseResponse.class);
	}
	/**
	 * 领取单张发票
	 * @param invoiceTypeCode 发票种类代码
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:invoice")
	@GetMapping("/financed/invoiceGetOne.php") 
	public @ResponseBody InvoiceRepoDetail getOne(HttpServletRequest request, String invoiceTypeCode){
		InvoiceRepoRq repoRq=new InvoiceRepoRq();
		repoRq.setInvoiceTypeCode(invoiceTypeCode);
		return SoaConnectionFactory.get(request, ConstantsUri.INVOICE_GET,repoRq , InvoiceRepoDetailRs.class).getData();
	}
	
	/**
	 * 领取发票by no
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoiceGetByNo/{invoiceTypeCode}") 
	public @ResponseBody List<AutoComplete> getByNo(@PathVariable("invoiceTypeCode") String invoiceTypeCode,String keyword,HttpServletRequest request){
		InvoiceRepoRq repoRq=new InvoiceRepoRq();
		repoRq.setInvoiceNo(keyword);
		repoRq.setInvoiceTypeCode(invoiceTypeCode);
		List<AutoComplete> completes=new ArrayList<AutoComplete>();
		List<InvoiceRepoDetail> details= SoaConnectionFactory.get(request, ConstantsUri.INVOICE_GETBYNO,repoRq , InvoiceRepoDetailRs.class).getDataList();
		if(details!=null&&details.size()>0){
			for(InvoiceRepoDetail detail:details){
				completes.add(new AutoComplete(detail.getInvoiceNo(),detail));
			}
		}
		return completes;
	}


	/**
	 * 查询出是否有需要导出的发票打印信息
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoicekd/qexportprint.php")
	public @ResponseBody boolean qexportPrint(HttpServletRequest request,HttpServletResponse response){
		InvoiceExcelRs excelRs=SoaConnectionFactory.get(request, ConstantsUri.INVOICE_PRINT, null, InvoiceExcelRs.class);
		 List<InvoiceExcel> excels=excelRs.getDataList();
		 return (excels!=null && excels.size()>0)?true:false;
	}
	
	/**
	 * 纸质发票打印导出
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoicekd/exportprint.php")
	public void exportPrint(HttpServletRequest request,HttpServletResponse response){
		 InvoiceExcelRs excelRs=SoaConnectionFactory.get(request, ConstantsUri.INVOICE_PRINT, null, InvoiceExcelRs.class);
		 List<InvoiceExcel> excels=excelRs.getDataList();
		 exportExecl(response, excels, "发票打印信息.xlsx", "tempfiles/INVOICE_TEMP.xlsx");
	}

	/**
	 * 纸质发票打印导入
	 * @param
	 * @param
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoicekd/importprint.php")
	public @ResponseBody BaseResponse importPrint(MultipartHttpServletRequest multipartRequest){
		LOGGER.info("开始解析Excel文件.....");
		MultipartFile file =  multipartRequest.getFile("uploadFile");
		if(!(file.getOriginalFilename().endsWith("xlsx")||file.getOriginalFilename().endsWith("xls"))){
			return new BaseResponse("300", "上传文件类型错误!");
		}

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
			List<InvoiceExcel> list = new LinkedList<InvoiceExcel>();

			// 从第二行起遍历每一行
			int rowNum = sheet.getLastRowNum();
			for (int j = 1; j <= rowNum; j++) {
				InvoiceExcel invoiceExcel = new InvoiceExcel();
			    // 取得某一行
			    Row row = sheet.getRow(j);

			    Cell cell = row.getCell(0);
				try {
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    invoiceExcel.setInvoiceNo(cell.getStringCellValue());
				}
				catch (NullPointerException e){
					LOGGER.info("解析Excel文件失败:",e);
					return new BaseResponse("9999", "解析Excel文件失败!");
				}
				try {
					cell = row.getCell(18);
					cell.setCellStyle(cellStyle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if(StringUtils.isEmpty(cell.getStringCellValue())){
						return new BaseResponse("9999", "第"+(j+1)+"行数据发票代码为空，请填入后在导入!");
					}
					invoiceExcel.setInvoiceCode(cell.getStringCellValue());
				}
				catch (NullPointerException e){
					LOGGER.info("解析Excel文件失败:",e);
					return new BaseResponse("9999", "解析Excel文件失败:发票代码列不存在");
				}
				try {
			    cell = row.getCell(19);
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    invoiceExcel.setInvoiceOrderNo(cell.getStringCellValue());
			    // 保存该行的数据到该表的List中
			    list.add(invoiceExcel);
				}
				catch (NullPointerException e){
					LOGGER.info("解析Excel文件失败:",e);
					return new BaseResponse("9999", "解析Excel文件失败");
				}
			}
			System.out.println(JSON.toJSONString(list));
			return SoaConnectionFactory.post(multipartRequest, ConstantsUri.INVOICE_PRINTIMPORT, list, BaseResponse.class);
		} catch (IOException e) {
			LOGGER.info("解析Excel文件失败:",e);
			return new BaseResponse("9999", "解析Excel文件失败:"+e.getMessage());
		}
	}

	/**
	 * 查询出是否有需要导出的发票快递信息
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoicekd/qexport.php")
	public @ResponseBody boolean qexport(HttpServletRequest request,HttpServletResponse response){
		 InvoiceExpressExcelRs excelRs=SoaConnectionFactory.get(request, ConstantsUri.INVOICE_SEND, null, InvoiceExpressExcelRs.class);
		 List<InvoiceExpressExcel> excels=excelRs.getDataList();
		 return (excels!=null && excels.size()>0)?true:false;
	}
	
	/**
	 * 发票快递导出
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoicekd/export.php")
	public void exportKD(HttpServletRequest request,HttpServletResponse response,String compId){
		String contentType = "application/octet-stream";

		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		LogisticsRs logisticsRs=SoaConnectionFactory.getRestful(request, ConstantsUri.LOGISTIC_INFO, null, LogisticsRs.class, compId);
		Logistics data = logisticsRs.getData();
		 InvoiceExpressExcelRs excelRs=SoaConnectionFactory.get(request, ConstantsUri.INVOICE_SEND, null, InvoiceExpressExcelRs.class);
		 List<InvoiceExpressExcel> excels=excelRs.getDataList();
		 List<SfExportBO> sfExportBO=new ArrayList<SfExportBO>();
		 if(excels!=null && excels.size()>0){
			 for(InvoiceExpressExcel expressExcel:excels){
				 SfExportBO bo;
				 if(CommonUtils.nullOrBlank(expressExcel.getReceivingCompany())){
					 bo=new SfExportBO(expressExcel.getInvoiceOrderNo(), expressExcel.getLinkman(),
							 expressExcel.getLinkman(), expressExcel.getPhone(),  expressExcel.getPhone(),
							 expressExcel.getAddress(), expressExcel.getContent(),expressExcel.getCargoContent(), expressExcel.getCargoNum()) ;
				 }
				 else{
					 bo=new SfExportBO(expressExcel.getInvoiceOrderNo(), expressExcel.getReceivingCompany(),
							 expressExcel.getLinkman(), expressExcel.getPhone(),  expressExcel.getPhone(),
							 expressExcel.getAddress(), expressExcel.getContent(),expressExcel.getCargoContent(), expressExcel.getCargoNum()) ;
				 }
				 sfExportBO.add(bo);
			 }
		 }


		LogisticsRq rq = new LogisticsRq();
		String path = SpringCtxHolder.getProperty("abc.soa-upload-url")+SpringCtxHolder.getProperty("abc.soa-upload-context")+data.getTemplateUrl();
		String p = SpringCtxHolder.getProperty("logging.path");
		String filename = "";
		String url = data.getTemplateUrl();
		int a = url.lastIndexOf("/");
		int s = url.lastIndexOf(".");
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
		exportExeclfp(response, sfExportBO, "发票快递信息.xls", targetFile,filename);
	}

	/**
	 * 从服务器下载快递模板
	 * @param
	 * @param
	 */
	public void getFile(String downpath,String path,String filename) {
		try {
			HttpEntity httpEntity = new HttpEntity(null);
			byte[] files = SoaConnectionFactory.exchange(restTemplate,downpath,
					HttpMethod.GET, httpEntity, byte[].class);
			//String path = ClassLoader.getSystemResource("").getPath()+"tempfiles/";
			//String path = "d:/logistics/temp/";
			FileOutputStream fos = new FileOutputStream(path +"//"+ filename);
			fos.write(files);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入发票快递信息
	 * @param
	 * @param
	 */
	@RequiresPermissions("financed:invoice")
	@RequestMapping("/financed/invoicekd/importkd.php")
	public @ResponseBody BaseResponse importkd(MultipartHttpServletRequest multipartRequest) {
		LOGGER.info("开始解析Excel文件.....");
		MultipartFile file =  multipartRequest.getFile("uploadFile1");
		String compId = null;
		if (multipartRequest.getParameter("compId") != null) {
			compId = multipartRequest.getParameter("compId");
		}
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
			List<InvoiceExpressExcel> list = new LinkedList<InvoiceExpressExcel>();
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
						return new BaseResponse("9999", "Excel文件表头内容不正确！");
					}
					cell = fisrtRow.getCell(3);
					cell.setCellStyle(cellStyle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if(!"快递单号".equals(cell.getStringCellValue())){
						return new BaseResponse("9999", "Excel文件表头内容不正确！");
					}
				}
				catch (NullPointerException e){
					e.printStackTrace();
				}
			}
			// 从第二行起遍历每一行
			int rowNum = sheet.getLastRowNum();
			for (int j = 1; j <= rowNum; j++) {
				InvoiceExpressExcel invoiceExcel = new InvoiceExpressExcel();
			    // 取得某一行
			    Row row = sheet.getRow(j);
			    Cell cell = row.getCell(0);
				if(StringUtils.isEmpty(cell.getStringCellValue())){
					break;
				}
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    invoiceExcel.setInvoiceOrderNo(cell.getStringCellValue());
			    cell = row.getCell(3);
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    invoiceExcel.setWaybillNum(cell.getStringCellValue());
			    // 保存该行的数据到该表的List中
			    list.add(invoiceExcel);
			}
			System.out.println(JSON.toJSONString(list));
			return SoaConnectionFactory.post(multipartRequest, ConstantsUri.INVOICE_SENDIMPORT, list, BaseResponse.class,compId);
		} catch (IOException e) {
			LOGGER.info("解析Excel文件失败:",e);
			return new BaseResponse("9999", "解析Excel文件失败:"+e.getMessage());
		}
	}
	
	
	
	//退票start
	
	/**
	 * 申请退票列表
	 * @param request
	 * @param model
	 * @param invoicebackrq
	 * @return
	 */
	@RequestMapping("/financed/invoiceBackList.php")
	public String invoiceBackList(HttpServletRequest request, Model model,InvoiceBackRq invoicebackrq){
		InvoiceBackRs repoRs = SoaConnectionFactory.get(request,
				ConstantsUri.INVOICEBACK_LIST, invoicebackrq, InvoiceBackRs.class);
		model.addAttribute("invoicebacks", repoRs.getDataList());
		invoicebackrq.setTotalItems(repoRs.getTotal());
		invoicebackrq.calculate();
		model.addAttribute("BaseRq", invoicebackrq);
		
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		model.addAttribute("ibackstatus", getDictBOList(request, "ibackstatus"));
		
		return "/financed/invoiceBack/list";
	}

	/**
	 * 跳转到退票查看页面
	 * @param request
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping("/financed/backInfo.php")
	public String backInfo(HttpServletRequest request, Model model,String id){
		InvoiceBackRs repoRs = SoaConnectionFactory.getRestful(request,
				ConstantsUri.INVOICEBACK_INFO, null, InvoiceBackRs.class,id);
		model.addAttribute("invoiceBack", repoRs.getData());
		
		model.addAttribute("invoicecontents", getDictBOList(request, "invoicecontent"));
		model.addAttribute("invoicetype", getDictBOList(request, "invoicetype"));
		model.addAttribute("ibackreason", getDictBOList(request, "ibackreason"));
		model.addAttribute("ibackstatus", getDictBOList(request, "ibackstatus"));
		return "/financed/invoiceBack/info";
	}

	/**
	 * 发票退票
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/financed/doback.php")
	public @ResponseBody BaseResponse doback(HttpServletRequest request,
			String expressId, String id, @RequestBody InvoiceBack invoiceBack){
		return SoaConnectionFactory.post(request, ConstantsUri.INVOICEBACK_CHECK, invoiceBack, BaseResponse.class, new Object[]{expressId,id});
	}
	//退票end
}
