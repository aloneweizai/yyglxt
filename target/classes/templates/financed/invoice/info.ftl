<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">	
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/autocomplete/jquery.bigautocomplete.css">
	<script type="text/javascript">
            var ctx = "${ctx}";
    </script>
    <style>
		input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
		.table>thead>tr>th,.table>tbody>tr>td{text-align:left;}
	</style>
</head>
<body>
<#assign neirong="">
<#assign property="">
<#if invoicecontents?? && ( invoicecontents?size gt 0 )> 
   <#list invoicecontents as invoicecontent>
      <#if invoice.content == invoicecontent.fieldValue><#assign neirong=invoicecontent.fieldKey></#if>
   </#list>
</#if>
<#if invoice.property?? && invoice.property=="1"><#assign property="纸质发票"><#else><#assign property="电子发票"></#if>
<input type="hidden" value='${invoice.id!}' id="invoiceId"/>
<input type="hidden" value='${currentUser.id!}' id="userId"/>
<input type="hidden" value='${invoice.type!}' id="invoicetype"/>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
        <table class="table table-bordered">
            <caption>申请信息</caption>
            <#--<tr>
                <td width="15%">发票类型:</td>
                <td colspan="3">
                  <#if invoicetypes?? && invoice.type?? && ( invoicetypes?size gt 0 )> 
				   <#list invoicetypes as invoicetype>
                       <#if invoice.type == invoicetype.fieldValue>${invoicetype.fieldKey}</#if>
				   </#list>
			    </#if>
				</td>
            </tr>-->
            <tr>
                <td>发票抬头:</td>
                <td>
                  <#if invoicenames?? && ( invoicenames?size gt 0 )> 
				   <#list invoicenames as invoicename>
                       <#if invoice.name == invoicename.fieldValue>${invoicename.fieldKey}</#if>
				   </#list>
			      </#if>
				</td>
                <td>名称:</td>
                <td><#if invoice.name=="1">${invoice.username!}<#else>${invoice.nsrmc!}</#if></td>
            </tr>
			<#if invoice.name=="2">
            <tr>
				<td>纳税人识别号:</td>
                <td>${invoice.nsrsbh!}</td>
                
                <td>注册地址:</td>
                <td>${invoice.address!}</td>
            </tr>
            <tr>
                <td>注册电话:</td>
                <td>${invoice.phone!}</td>
                <td>开户银行:</td>
                <td>${invoice.bank!}</td>
            </tr>
			</#if>
            <tr>
                <td>发票内容:</td>
                <td>
                  ${neirong!}
				</td>
                <td>发票性质:</td>
                <td>
                  ${property!}
				</td>
            </tr>
<#if invoice.property??&&invoice.property=="2">
<tr>
    <td>邮箱地址：</td>
    <td colspan="3">${(invoice.email)!}</td>
</tr>
</#if>
        <#if invoice.property??&&invoice.property=="1">
            <tr>
                <td>收件人：</td>
                <td>${(invoice.consignee)!}</td>
                <td>联系电话：</td>
                <td>${(invoice.contactNumber)!}</td>
            </tr>
            <tr>
                <td>邮寄地址：</td>
                <td>${(invoice.shippingAddress)!}</td>
				<td>邮箱地址：</td>
                <td>${(invoice.email)!}</td>
            </tr>
			<tr>
				<td width="15%">物流公司：</td>
                <td width="35%"><#if invoice.waybillNum??>顺丰</#if></td>
				<td width="15%">运单号：</td>
                <td width="35%">${invoice.waybillNum!}</td>
			</tr>
			</#if>
            <tr>
                <td>备注：</td>
                <td colspan="3">${(invoice.remark)!}</td>
            </tr>
        </table>
    <table class="layui-table" lay-size="sm">
            <thead class="pn-lthead">
                <tr style="font-weight:bold;">
                    <th>订单号</th>
                    <th>用户名</th>
                    <th>总金额</th>
                    <!--<th>支付方式</th>-->
                    <th>订单状态</th>
                    <th>下单时间</th>
                </tr>
            </thead>
            <tbody class="pn-ltbody">
				<#if invoice.orderBOList?? && ( invoice.orderBOList?size gt 0 )> 
				<#list invoice.orderBOList as order>
                <tr>
                    <td>${order.orderNo!}</td>
                    <td>${order.username!}</td>
                    <td><span class="text-warning">¥${(order.totalPrice?string("0.00"))!}</span></td>
                    <!--<td>${order.payMethod!}</td>-->
                    <td>
						<#if orderStatus?? && ( orderStatus?size gt 0 )> 
        				   <#list orderStatus as orderStatu>
                               <#if order.orderStatus == orderStatu.fieldValue>${orderStatu.fieldKey}</#if>
        				   </#list>
        			    </#if>
					</td>
                    <td>${(order.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                </tr>
                </#list>
			    </#if>
        </table>
		<#if invoice.status!="1" && invoice.status!="3">
            <table class="layui-table" lay-size="sm">
		  <caption>发票信息</caption>
          <tr style="font-weight:bold;">
            <td>名称</td>
              <td>发票种类</td>
            <td>发票号码</td>
            <td>发票代码</td>
            <td>发票性质</td>
            <td>发票内容</td>
            <td>开票金额</td>
          </tr>
		  <tr>
            <td><#if invoice.name=="1">个人<#else>${invoice.nsrmc!}</#if></td>
              <td>
                  <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                <#list invoicetype as invoicety>
                      <#if invoice.type ?? && (invoice.type == invoicety.fieldValue)>${invoicety.fieldKey}</#if>
                  </#list>
                            </#if>
              </td>
            <td>${invoice.invoiceNo!}</td>
            <td>${invoice.invoiceCode!}</td>
            <td>${property}</td>
            <td>${neirong}</td>
            <td>￥${(invoice.amount?string("0.00"))!}</td>
          </tr>
		</table>
		</#if>
		
        <table width="100%">
            <tr>
                <td colspan="3" align="right" style="text-align:right;padding-right:10px">
					
					<#if invoice.status?? && invoice.status=="1"&&(lookType??&&lookType=="1")>
                    <button type="button" class="layui-btn layui-btn-normal" data-toggle="modal" datatarget="#myModal">审核通过</button>
                    <button type="button" class="layui-btn layui-btn-danger" data-toggle="modal" datatarget="#myModal2">审核拒绝</button>
                        <a href="${referer}" class="layui-btn layui-btn-primary">返回</a>
                    <#--<#else>
                        <a href="class="layui-btn layui-btn-primary"btn-default">返回</a>-->
					</#if>
                </td>
            </tr>
        </table>
    </div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1"  hidden>
	<form id="kaipiao">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">开票信息
					(<span style="font-size:12px; color: #999;">温馨提示:电子发票审核后自动开具;纸质发票审核后管理员导出统一开具.</span>)
			    </h4>
            </div>
            <div class="modal-body">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <td>名称：<input type="hidden" id="detailId" value=''/></td>
                        <td><#if invoice.name=="1">个人<#else>${invoice.nsrmc!}</#if></td>
                    </tr>
					<#if invoice.name=="2">
					<tr>
                        <td>纳税人识别号：</td>
                        <td>${invoice.nsrsbh!}</td>
                    </tr>
					</#if>
					<tr>
                        <td>发票性质：</td>
                        <td>${property}<!--<input type="button" id="zidonglp" class="layui-btn layui-btn-normal" value="自动领票">--></td>
                    </tr>
                    <tr>
                        <td>发票种类：</td>
                        <td>
                            <select class="invoiceType" id="invoiceType" name="type" style="width:200px;height:30px;"
                                    data-rule="required;">
                            <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                <#if invoice.property??&&invoice.property=="2">
                                 <#list invoicetype as invoice>
                                    <#if invoice.fieldValue == "DZZZSPTFP-">
                                    <option value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                                    </#if>
                                </#list>
                                <#else>
                                    <#list invoicetype as invoice>
                                        <#if invoice.fieldValue != "DZZZSPTFP-">
                                            <option <#if "TYJDFP-" == invoice.fieldValue>selected</#if> value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                                        </#if>
                                    </#list>
                                </#if>

                            </#if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>发票内容：</td>
                        <td><input type="text" readonly name="content" value='${neirong!}'></td>
                    </tr>
                    <tr>
                        <td>开票金额：</td>
                        <td><input type="text" readonly name="jine" value='${(invoice.amount?string("0.00"))!}' ></td>
                    </tr>
                    <tr>
                        <td>备注：</td>
                        <td>
                            <textarea rows="4" id="beizhu" name="beizhu" cols="50"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn" id="kaipiaoSb">确认</button>
                <button class="layui-btn layui-btn-primary" id="kaipiaoX" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
	</form>
</div>
	
<div class="main_modal container-fluid" id="myModal2" tabindex="-1"  hidden>
	<form id="refuse">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal2" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">拒绝原因</h4>
                </div>
                <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td><textarea style="width:80%;" name='refuseReason' rows="4" cols="50" data-rule="required;" id="refuseReason"></textarea><label style='color:red;'>*</label></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn" id="refuseInvoice">确认</button>
                    <button class="layui-btn layui-btn-primary"ype="button" id="refuseInvoiceX" data-dismiss="modal2">关闭</button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1"  hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal3" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">快递信息</h4>
            </div>
            <div class="modal-body">
                <table class="layui-table" lay-size="sm">
                    <caption>快递内容</caption>
                    <tr>
                        <th>名称</th>
                        <th>发票号码</th>
                        <th>发票代码</th>
                        <th>发票性质</th>
                        <th>发票内容</th>
                        <th>开票金额</th>
                    </tr>
                    <tr>
                        <td><#if invoice.name=="1">${invoice.username!}<#else>${invoice.nsrmc!}</#if></td>
                        <td>${invoice.invoiceNo!}</td>
                        <td>${invoice.invoiceCode!}</td>
                        <td>${property}</td>
                        <td>${neirong}</td>
                        <td>￥${(invoice.amount?string("0.00"))!}</td>
                    </tr>
                </table>
                <table class="layui-table" lay-size="sm">
                    <tr style='display:none;'>
                        <td>运单号：</td>
                        <td>${invoice.userOrderNo!}</td>
                        <td>是否寄送</td>
                        <td>是</td>
                    </tr>
                    <tr style='display:none;'>
                        <td>配送方式:</td>
                        <td>
                            <input type="radio" name="expressMethod" checked> 快递
                            <input type="radio" name="expressMethod"> 自取
                        </td>
                        <td>是否保价:</td>
                        <td>
                            <input type="radio" name="sfbj" checked> 是
                            <input type="radio" name="sfbj"> 否
                        </td>
                    </tr>
                    <tr>
                        <td>收件人：</td>
                        <td>
                            <input type="text" value="${(invoice.userAddressBO.name)!}">
                        </td>
                        <td>手机号:</td>
                        <td>
                            <input type="text" value="${(invoice.userAddressBO.phone)!}">
                        </td>
                    </tr>
                    <tr>
                        <td>物流公司：</td>
                        <td>
                            <select style="height:30px">
                                <option value="sf">顺丰速递</option>
                            </select>
                        </td>
                        <td>快递单号:</td>
                        <td>
                            <input type="text" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>配送地址：</td>
                        <td colspan="3">
                            ${(invoice.userAddressBO.provinceName)!}${(invoice.userAddressBO.cityName)!}${(invoice.userAddressBO.areaName)!}
                        </td>
                    </tr>
                    <tr>
                        <td>详细地址:</td>
                        <td colspan="3">
                            <input type="text" value="${(invoice.userAddressBO.detail)!}" style="width:350px">
                        </td>
                    </tr>
                    <tr>
                        <td>备注:</td>
                        <td colspan="3">
                            <textarea rows="3" cols="50"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn layui-btn layui-btn-primary"></button>
                <button type="button" class="btn btn-default" id="kuaidiX" data-dismiss="modal3">关闭</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/financed/invoice.js" src="${ctx}/js/require.js"></script>
</body>
</html>