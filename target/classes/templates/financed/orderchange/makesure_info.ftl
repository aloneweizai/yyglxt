<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > td {
            text-align: left;
        }
    </style>
</head>
<body>
<form name="makesure" id="makesure" action="" enctype="multipart/form-data" method="post" next-url="">
    <div class="container-fluid m_top_30 nycol_list  sys_mod">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <table class="layui-table" lay-size="sm">
                    <caption>退/换货申请信息</caption>
                    <tr style="font-weight:bold;">
                        <td width="30%">订单号</td>
                        <td width="15%">订单金额</td>
                        <td width="15%">支付方式</td>
                        <td width="20%">下单时间</td>
                        <td width="20%">推荐人</td>
                    </tr>
					<tr style="background-color:#f6f6f6; ">
                        <td width="20%">${orders.orderNo!}</td>
                        <td width="20%">
                        <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                        ${(orders.totalPrice)!}积分
                        <#else>
                            ￥${(orders.totalPrice?string("0.00"))!}
                        </#if>
                        </td>
                        <td width="20%"><#if orders.payMethod?? && orders.payMethod="ALIPAY">支付宝<#else>积分</#if></td>
                        <td>${(orders.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						<td>${orders.recommendUser!}</td>
                    </tr>
                    <tr>
                        <td colspan="5">
                            <table class="layui-table" lay-size="sm">
                                <tr style="font-weight:bold;">
                                    <th style="text-align: left;">商品名称</th>
                                    <th style="text-align: left;">规格</th>
                                    <th style="text-align: left;">实付金额(积分)</th>
                                    <th style="text-align: left;">购买数量</th>
                                </tr>
                            <#if orders.orderProductBOList?? && ( orders.orderProductBOList?size gt 0 )>
                                <#list orders.orderProductBOList as orderProduct>
                                    <tr style="background-color:#f6f6f6; ">
                                        <td>${(orderProduct.name)!}</td>
                                        <td>
                                        ${(orderProduct.specInfo)!}
                                        </td>
                                        <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                                            <td>${(orderProduct.dealPrice)!} 积分</td>
                                        <#else>
                                            <td><#if orderProduct.dealPrice??>￥${orderProduct.dealPrice?string("0.00")!}</#if></td>
                                        </#if>
                                        <td>${(orderProduct.num)!}</td>
                                    </tr>
                                </#list>
                            </#if>
                            </table>
                        </td>
                    </tr>
					<tr style="font-weight:bold;">
                        <td width="20%" >申请时间</td>
						<td width="20%" >申请类型</td>
                        <td width="20%" >申请状态</td>
                        <td width="20%" colspan="2">退/换货原因</td>
                    </tr>
					<tr style="background-color:#f6f6f6; ">
                        <td width="20%">${(orderExchange.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						<td width="20%">
                           <#if orderExchange.type=="1"><div class="btn btn-info btn-xs ">换货</div><#else><div class="btn btn-warning btn-xs ">退货</div></#if>
						</td>
                        <td width="20%">
                            <#if exchange_status?? && ( exchange_status?size gt 0 )>
            				   <#list exchange_status as status>
                                   <#if orderExchange.status == status.fieldValue>${status.fieldKey}</#if>
            				   </#list>
            			    </#if>
						</td>
                        <td width="20%" colspan="2">
						   <#if exchange_reason?? && ( exchange_reason?size gt 0 )>
            				   <#list exchange_reason as reason>
                                   <#if orderExchange.reason == reason.fieldValue>${reason.fieldKey}</#if>
            				   </#list>
            			   </#if>
                        </td>
                    </tr>
                    <tr style="font-weight:bold;"><td colspan="5">用户申请备注</td></tr>
					<tr><td colspan="5" style="background-color:#f6f6f6;height:80px;">${orderExchange.userRemark!}</td></tr>
				</table>
            <#if orderExchange.type=="1">
                <table class="layui-table" lay-size="sm">
                    <caption>买家退货地址信息</caption>
                    <tr style="font-weight:bold;">
                        <td width="20%">收件人</td>
                        <td width="20%">联系电话</td>
                        <td width="60%">邮寄地址</td>
                    </tr>
                    <tr style="background-color:#f6f6f6; ">
                        <td width="20%">${orderExchange.consignee!}</td>
                        <td width="20%">${orderExchange.contactNumber!}</td>
                        <td width="60%">${orderExchange.shippingAddress!}</td>
                    </tr>
                </table>
            </#if>
            <#if orders.isShipping?? && ( orders.isShipping == 1 )&&orderExchange.type=="1">
                <table class="layui-table" lay-size="sm">
                    <caption>配送信息</caption>
                    <tr style="font-weight:bold;">
                        <td>物流公司</td>
                        <td>运单号</td>
                        <td>配送费用</td>
                        <td>是否保价</td>
                        <td>保价费用</td>
                    </tr>
                    <tr>
                        <td>${(orderExchange.expressComp)!}</td>
                        <td>${(orderExchange.expressNo)!}</td>
                        <td>￥${(orders.deliveryFee)!}.00</td>
                        <td>
                            <#if orders.isInsured?? && ( orders.isInsured == 1 )>是<#else>否</#if>
                        </td>
                        <td>￥${(orders.insuredFee)!}.00</td>
                    </tr>
                </table>
            </#if>
                <table class="layui-table" lay-size="sm">
				    <caption>审核员备注</caption>
					<tr >
                        <td colspan="4" style="background-color:#f6f6f6;height:80px;">${orderExchange.adminRemark!}</td>
                    </tr>
                </table>
                <table class="layui-table" lay-size="sm">
                    <caption>审核员确认备注</caption>
                    <tr >
                        <td colspan="4" style="background-color:#f6f6f6;height:80px;">${orderExchange.adminConfirmRemark!}</td>
                    </tr>
                </table>
                <table class="layui-table" lay-size="sm">
                    <tbody id="jiageTB">
<#if orders.isShipping?? && ( orders.isShipping == 1 )&&orderExchange.type=="1">
                    <tr>
                        <td>收货快递公司</td>
                        <td>
                            <input type="text"  name="expressComp" id="expressComp" style="width:250px;height:30px;"><label style='color:red;'>*</label><label id="tips" style='color:red;font-size: x-small;' hidden>不能为空</label>
                        </td>
                    </tr>
                    <tr>
                        <td>收货快递单号</td>
                        <td>
                            <input type="text"  name="expressNo" id="expressNo"  style="width:250px;height:30px;"><label style='color:red;'>*</label><label id="tips1" style='color:red;font-size: x-small;' hidden>不能为空</label>
                            <label id="tips2" style='color:red;font-size: x-small;' hidden>长度为6到32之间</label>
                        </td>
                    </tr>
</#if>
                    <tr>
                        <td>备注</td>
                        <td>
                            <input type="hidden" name="id" id="orderExchangeId" value="${orderExchange.id!}"/>
                            <input type="hidden" name="status" id="status" value="6"/>
                            <textarea id="refuseRson" name="adminConfirmRemark" rows="6" cols="50" ></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>

<#--<!--确认收货&ndash;&gt;
<#if orderExchange.status?? && orderExchange.status == "2" && orderExchange.type=="1">
<div class="main_modal container-fluid" id="myModal1" tabindex="-1"  hidden>
<div class="modal-dialog" role="document" style="top:-400px;">
<form id="makesure">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal1" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">确认收货(<span style="font-size:12px; color: #999;">温馨提示:实物退换请确认已收到货物</span>)</h4>
    </div>
    <div class="modal-body">
      <table class="table table-hover">
		<tbody id="jiageTB">
		   <tr>
            <td colspan="2"> 
			   <table class="table table-hover" style="margin-top:10px;">
                    <tr style="font-weight:bold;">
                            <th>商品名称</th>
                            <th>规格</th>
                            <th>销售价格</th>
                            <th>购买数量</th>
                    </tr>
					<#if orders.orderProductBOList?? && ( orders.orderProductBOList?size gt 0 )>
					  <#list orders.orderProductBOList as orderProduct>
					     <tr style="background-color:#f6f6f6; ">
						   <td>${(orderProduct.name)!}</td>
						   <td>
                               ${(orderProduct.specInfo)!}
                           </td>
						   <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                                <td>${(orders.totalPrice)!} 积分</td>
                                <#else>
                                <td><#if orderProduct.sellingPrice??>￥${orderProduct.sellingPrice?string("0.00")!}</#if></td>
                           </#if>
						   <td>${(orderProduct.num)!}</td>
						 </tr>
					  </#list>
					</#if>
                </table>
			</td>
		   </tr>
		   <tr>
            <td>收货快递公司</td>
			<td>
			   <input type="hidden" name="id" id="orderExchangeIdsure" value="${orderExchange.id!}"/>
			   <input type="hidden" name="status" value="6"/>
			   <input type="text"  name="expressComp" data-rule="required;" style="width:250px;height:30px;"><label style='color:red;'>*</label>
            </td>
		   </tr>
		   <tr>
            <td>收货快递单号</td>
			<td>
			   <input type="text"  name="expressNo" data-rule="required;" style="width:250px;height:30px;"><label style='color:red;'>*</label>
			</td>
		   </tr>
		   <tr>
            <td>备注</td>
			<td>
				<textarea id="refuseRson" name="adminRemark" rows="6" cols="50" ></textarea>
			</td>
		   </tr>
		</tbody>
      </table>
    </div>
    <div class="modal-footer">
	  <button type="button" class="layui-btn layui-btn-normal" data-save="modal1">确定</button>
      <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal1">关闭</button>
      
    </div>
  </div>
</form>
</div>
</div>	
</#if>-->
	
<script data-main="${ctx}/js/abc/financed/orderchange.js" src="${ctx}/js/require.js"></script>
</body>
</html>