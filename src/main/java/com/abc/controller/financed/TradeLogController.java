package com.abc.controller.financed;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.financed.PayqueryReq;
import com.abc.soa.request.financed.TradeLogReq;
import com.abc.soa.response.financed.PayqueryRes;
import com.abc.soa.response.financed.TradeLogRes;
import com.abc.soa.response.financed.WeiXinPayqueryRes;
import com.abc.soa.response.financed.WeiXinRefundRes;
import com.abc.soa.response.financed.bo.Payquery;
import com.abc.soa.response.financed.bo.TradeBill;
import com.abc.soa.response.financed.bo.WeiXinPayquery;
import com.abc.soa.response.financed.bo.WeiXinRefund;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TradeLogController extends BaseController{
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(TradeLogController.class);

	private static final String TRADELOG_YWLX = "收费";

	/**
	 * 财务对账列表
	 * @param
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:tradelog")
	@RequestMapping("/tradelog/list.php")
	public String tradelogList(TradeLogReq req,HttpServletRequest request){
		TradeLogRes tradeLogRes=SoaConnectionFactory.get(request, ConstantsUri.TRADELOG_LIST, req, TradeLogRes.class);
		request.setAttribute("tradeLogRes",tradeLogRes.getDataList());
		req.setTotalItems(tradeLogRes.getTotal());
		req.calculate();
		request.setAttribute("BaseRq", req);
		return "financed/tradelog/list";
	}

	/**
	 * 手工对账
	 * @param
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:tradelog")
	@RequestMapping("/tradelog/dovalid.php")
	public @ResponseBody BaseResponse dovalid(String id,String type,HttpServletRequest request){
		if("valid".equals(type)){
			return SoaConnectionFactory.putRestful(request, ConstantsUri.TRADELOG_VALID, null, BaseResponse.class, id);
		}else{
			return SoaConnectionFactory.putRestful(request, ConstantsUri.TRADELOG_INVALID, null, BaseResponse.class, id);
		}
	}
	/**
	 * 批量对账单
	 * @param
	 * @param
	 * @return
	 */
	@RequiresPermissions("financed:tradelog")
	@RequestMapping("/tradelog/alipay/import.php")
	public @ResponseBody BaseResponse importExcel(MultipartHttpServletRequest multipartRequest){
		List<TradeBill> bills=new ArrayList<TradeBill>();
		MultipartFile file =  multipartRequest.getFile("uploadFile");
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream(),"GB2312"));
			String line = "";
			int i=0;
			while ((line = reader.readLine()) != null){
			   i++;
			   if(line.startsWith("#")||i<=5) continue;
               String[] temp=line.split(",");
               TradeBill bill=new TradeBill();
				//业务类型为收费是支付宝收取商户的单笔业务的费用，不需要对账
				if(!TRADELOG_YWLX.equals(temp[10].trim())){
					bill.setAliTrandeNo(temp[1].trim());
					bill.setTradeNo(temp[2].trim());
					bill.setAmount(Double.valueOf(temp[6].trim())+Double.valueOf(temp[7].trim()));
					bills.add(bill);
				}
            }
			LOGGER.info("导入支付宝对账单数据:"+JSON.toJSONString(bills));
			return SoaConnectionFactory.put(multipartRequest, ConstantsUri.TRADELOG_BILL, bills, BaseResponse.class, new Object());
		} catch (Exception e) {
			LOGGER.error("导入支付宝对账单数据失败:",e);
			return new BaseResponse("9999", "导入失败："+e.getMessage());
		}
		
	}

	/**
	 * 支付宝交易结果查询
	 * @param
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:tradelog")
	@RequestMapping("/tradelog/alipay/query.php")
	public @ResponseBody BaseResponse aliPayResult(PayqueryReq payqueryReq,HttpServletRequest request){
		PayqueryRes rs= SoaConnectionFactory.get(request, ConstantsUri.ALIPAY_Q, payqueryReq, PayqueryRes.class);
		if("2000".equals(rs.getCode())){
			return rs.getData();
		}else{
			return rs;
		}
	}

	/**
	 * 积分支付交易结果查询
	 * @param
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:tradelog")
	@RequestMapping("/tradelog/points/query.php")
	public @ResponseBody BaseResponse pointsResult(PayqueryReq payqueryReq,HttpServletRequest request){
		PayqueryRes rs= SoaConnectionFactory.get(request, ConstantsUri.POINTS_Q, payqueryReq, PayqueryRes.class);
		if("2000".equals(rs.getCode())){
			Payquery pay = rs.getData();
			if(!StringUtils.isEmpty(pay)){
				pay.setCode("2000");
			}
			return pay;
		}else{
			return rs;
		}
	}

	/**
	 * 微信支付交易结果查询
	 * @param
	 * @param request
	 * @return
	 */
	@RequiresPermissions("financed:tradelog")
	@RequestMapping("/tradelog/weixin/query.php")
	public @ResponseBody BaseResponse weixinResult(PayqueryReq payqueryReq,@RequestParam(value = "tradeType", required = false) String tradeType,HttpServletRequest request){
		if("1".equals(tradeType)){
			WeiXinPayqueryRes rs = new WeiXinPayqueryRes();
			rs= SoaConnectionFactory.get(request, ConstantsUri.WEIXIN_PAYQ, payqueryReq, WeiXinPayqueryRes.class);
			if("2000".equals(rs.getCode())){
				WeiXinPayquery pay = rs.getData();
				Double fee =Double.parseDouble(pay.getCash_fee())/100;
				pay.setCash_fee(fee.toString());
				pay.setCode("2000");;
				return pay;
			}else{
				return rs;
			}
		}
		else{
			WeiXinRefundRes rs = new WeiXinRefundRes();
			PayqueryReq rq = new PayqueryReq();
			rq.setRefund_id(payqueryReq.getTransaction_id());
			rs= SoaConnectionFactory.get(request, ConstantsUri.WEIXIN_REFUNDQ, rq, WeiXinRefundRes.class);
			if("2000".equals(rs.getCode())){
				WeiXinRefund pay = rs.getData();
				Double fee =Double.parseDouble(pay.getCash_fee())/100;
				pay.setCash_fee(fee.toString());
				pay.setCode("2000");;
				return pay;
			}else{
				return rs;
			}
		}
	}

}
