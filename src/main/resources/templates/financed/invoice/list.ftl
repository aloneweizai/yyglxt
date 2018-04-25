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
	<script type="text/javascript">
            var ctx = "${ctx}";
        </script>
</head>
<!--审批发票寄送-->
<@shiro.hasPermission name="finance_invoiceDelivery:audit">
    <#assign canAudit=true>
</@shiro.hasPermission>
<!--详情发票寄送-->
<@shiro.hasPermission name="finance_invoiceDelivery:detail">
    <#assign canDetail=true>
</@shiro.hasPermission>
<!--导入发票-->
<@shiro.hasPermission name="finance_invoiceDelivery:impInvoice">
    <#assign canImpInvoice=true>
</@shiro.hasPermission>
<!--导出发票-->
<@shiro.hasPermission name="finance_invoiceDelivery:expInvoice">
    <#assign canExpInvoice=true>
</@shiro.hasPermission>
<!--导入快递-->
<@shiro.hasPermission name="finance_invoiceDelivery:impkd">
    <#assign canImpkd=true>
</@shiro.hasPermission>
<!--导出快递-->
<@shiro.hasPermission name="finance_invoiceDelivery:expkd">
    <#assign canExpkd=true>
</@shiro.hasPermission>
<!--发票作废-->
<@shiro.hasPermission name="finance_invoiceDelivery:invalid">
    <#assign canInvalid=true>
</@shiro.hasPermission>
<!--查看用户信息-->
<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/financed/invoiceList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">发票订单号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.id)!}" name="id" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票号码</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.invoiceNo)!}" name="invoiceNo" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>
                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">运单号</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.waybillNum)!}" name="waybillNum" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">开始时间</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test30" value="${(BaseRq.startTime)!}" name="startTime">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">结束时间</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}" name="endTime">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">收件人</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.consignee)!}" name="consignee" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <!--<th>发票类型</th>-->
                    <th>发票订单号</th>
                    <th>发票抬头</th>
                    <th>用户名</th>
                    <th>收件人</th>
                    <th>发票号码</th>
					<th>发票内容</th>
					<th>发票性质</th>
					<th>发票金额</th>
					<th>状态</th>
					<th>申请时间</th>
					<th>运单号</th>
                    <#if canAudit?? || canDetail??>
					<th>操作选项</th>
                    </#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if invoiceRepoRqs?? && ( invoiceRepoRqs?size gt 0 )> 
					  <#list invoiceRepoRqs as invoiceRepo>
					  
					  <tr>
					     <td class="td_i">${BaseRq.offset + invoiceRepo_index + 1}</td>
                         <td><a class="ljc_00bcd4" data-type="lookModal" data-val="4" href="javascript:;" data-url="${ctx}/financed/invoice/orderinfo.php?id=${invoiceRepo.id}">${(invoiceRepo.id)!}</a></td>
						 <td>
							<#if invoicenames?? && ( invoicenames?size gt 0 )> 
            				   <#list invoicenames as invoicename>
                                   <#if invoiceRepo.name == invoicename.fieldValue>${invoicename.fieldKey}</#if>
            				   </#list>
            			    </#if>
						 </td>
						 <td>
                          <#if canQuery??>
                             <a class="ljc_00bcd4" data-type="lookModal" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${invoiceRepo.userId}">${(invoiceRepo.username)!}</a>
                              <#else>
                              ${(invoiceRepo.username)!}
                          </#if>
                          </td>
						 <td>${(invoiceRepo.consignee)!}</td>
						 <td><a class="ljc_00bcd4" href="javascript:;" name="fpxq" data-type="${(invoiceRepo.type)!}" data-code="${(invoiceRepo.invoiceCode)!}" data-pro="${(invoiceRepo.property)!}" data-no="${(invoiceRepo.invoiceNo)!}">${(invoiceRepo.invoiceNo)!}</a></td>
						 <td>
							<#if invoicecontents?? && ( invoicecontents?size gt 0 )> 
            				   <#list invoicecontents as invoicecontent>
                                   <#if invoiceRepo.content == invoicecontent.fieldValue>${invoicecontent.fieldKey}</#if>
            				   </#list>
            			    </#if>
						 </td>
						 <td><#if invoiceRepo.property?? && invoiceRepo.property=="1">纸质发票<#else>电子发票</#if></td>
						 <td>￥${(invoiceRepo.amount?string("0.00"))!}</td>
						 <td>
							<#if fqsqstatuss?? && ( fqsqstatuss?size gt 0 )> 
            				   <#list fqsqstatuss as fqsqstatus>
                                   <#if invoiceRepo.status == fqsqstatus.fieldValue>${fqsqstatus.fieldKey}</#if>
            				   </#list>
            			    </#if>
						 </td>
						 <td>${invoiceRepo.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td>${(invoiceRepo.waybillNum)!}</td>
                         <#if canAudit?? || canDetail??>
                             <td>
                                <#if invoiceRepo.status == "1">
                                    <#if canAudit??>
                                        <a data-type="lookModal" href="javascript:;" data-url="${ctx}/financed/invoiceinfo.php?lookType=0&&id=${invoiceRepo.id}">查看</a>|
                                        <a href="${ctx}/financed/invoiceinfo.php?lookType=1&&id=${invoiceRepo.id}">审批</a>
                                    </#if>
                                <#elseif invoiceRepo.status != "1"&&invoiceRepo.status != "3"&&invoiceRepo.status != "8">
                                    <a data-type="lookModal" href="javascript:;" data-url="${ctx}/financed/invoiceinfo.php?lookType=0&id=${invoiceRepo.id}">查看</a>
                                <#if canInvalid??>
                                    | <a data-url='${ctx}/financed/invoiceInvalid.php' data-id="${invoiceRepo.id}" data-val="${invoiceRepo.property}" id="invoice_invalid" >作废</a>
                                </#if>
                                <#else>
                                    <#if canDetail??>
                                        <a data-type="lookModal" href="javascript:;" data-url="${ctx}/financed/invoiceinfo.php?lookType=0&id=${invoiceRepo.id}">查看</a>
                                       <#-- <a href="${ctx}/financed/invoiceinfo.php?id=${invoiceRepo.id}">查看</a>-->
                                    </#if>
                                </#if>
                             </td>
                         </#if>
					  </tr>
					  </#list>
			        </#if>
                </tbody>
            </table>
          <div class="button_caozuo_fenye">
    <#if canExpInvoice??>
              <div  id='downLoad'data-val="1" data-qurl="${ctx}/financed/invoicekd/qexportprint.php" data-durl="${ctx}/financed/invoicekd/exportprint.php" data-noMsg="当前无待打印纸质发票信息，请稍后再试" class="layui-btn">导出发票信息</div>
    </#if>
<#if canImpInvoice??>
              <div  data-url='/financed/invoicekd/importprint.php' data-val="1"  id="consumer_import" class="layui-btn layui-btn-normal">导入发票信息</div>
</#if>
<#if canExpkd??>
              <div  id='downLoad'data-val="2" data-qurl="${ctx}/financed/invoicekd/qexport.php" data-durl="${ctx}/financed/invoicekd/export.php" data-noMsg="当前无待发货发票信息，请稍后再试" class="layui-btn">导出发货信息</div>
</#if>
<#if canImpkd??>
              <div  data-url='/financed/invoicekd/importkd.php'data-val="2" id="consumer_import" class="layui-btn layui-btn-normal">导入快递信息</div>
</#if>
                  <div class="clear"></div>
            </div>
          <table>
          <tbody class="yy_fanye">
            <tr>
              <td align="center">
                             共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
				每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp; 
				 当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
				<input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
			    <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
				<input type="hidden" value="${ctx}/financed/invoiceList.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>
				<div>
                    Excel文件：<input type="file" class="uploadFile" name="uploadFile" id="uploadFile"  style="width:200px;display: inline-block;">
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" value="导入" id="importbtn" name="importbtn" class="layui-btn layui-btn-normal" data-val="1">
                <input type="button" value="关闭" id="back" class="layui-btn layui-btn-primary"data-val="1">
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal1" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">导入快递信息</h4>
            </div>
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>
                <div>
                    <table class="layui-table" lay-size="sm">
                       <tr>
						   <td>
                               快递公司：
						   </td>
						   <td>
                               <select name="compId" style="width:200px;height:30px;" id="compId" class="compId">
                                   <option value="">-- 请选择 --</option>
							   <#if logisticsRs?? && ( logisticsRs?size gt 0 )>
								   <#list logisticsRs as logisticsRs>
                                       <#if logisticsRs.templateUrl??&&logisticsRs.templateUrl !="">
                                       <option value="${logisticsRs.id}">${logisticsRs.compName}</option>
                                       </#if>
								   </#list>
							   </#if>
                               </select>
                               <span style="color: red;">*</span>
						   </td>
					   </tr>
                        <tr>
                            <td>
                                Excel文件：
                            </td>
                            <td>
                                <input type="file" class="uploadFile" name="uploadFile1" id="uploadFile1" style="width:200px;display: inline-block;">
                            </td>
                        </tr>
                    </table>
					</div>
            </div>
            <div class="modal-footer">
                <input type="button" value="导入" id="importbtn" name="importbtn" class="layui-btn layui-btn-normal" data-val="2">
                <input type="button" value="关闭" id="back" class="layui-btn layui-btn-primary" data-val="2">
            </div>
        </div>
    </div>
</div>


<div class="main_modal container-fluid" id="myModal2" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>
                <div style="font-size: medium">
                    请选择快递公司：
                    <select name="compId" style="width:200px;height:30px;" id="compId"class="compId">
                        <option value="">-- 请选择 --</option>
					<#if logisticsRs?? && ( logisticsRs?size gt 0 )>
						<#list logisticsRs as logisticsRs>
                        <#if logisticsRs.templateUrl??&&logisticsRs.templateUrl !="">
                            <option value="${logisticsRs.id}">${logisticsRs.compName}</option>
                        </#if>
						</#list>
					</#if>
                    </select>
                    <span style="color: red;">*</span>
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" value="导出" id="exportbtn" name="exportbtn" class="layui-btn layui-btn-normal" data-qurl="${ctx}/financed/invoicekd/qexport.php" data-durl="${ctx}/financed/invoicekd/export.php" data-noMsg="当前无待发货发票信息，请稍后再试">
                <input type="button" class="layui-btn layui-btn-primary" id="back" value="关闭" data-val="3">
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="look_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button class="layui-btn layui-btn-primary"ype="button"  data-dismiss="lookModal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">发票详情</h4>
            </div>
            <div class="modal-body">
                <table class="table tablea">
                    <tr>
                        <td>发票种类</td>
                        <td id="type"></td>
                    </tr>
                    <tr>
                        <td>发票性质</td>
                        <td id="property"></td>
                    </tr>
                    <tr>
                        <td>发票代码</td>
                        <td id="invoiceCode"></td>
                    </tr>
                    <tr>
                        <td>发票号码</td>
                        <td id="invoiceNo"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal4" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal1" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择作废类型</h4>
            </div>
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>
                <div>
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td>
                                作废类型：
                                <input type="hidden" name="invoiceId" id="invoiceId" value="">
                            </td>
                            <td>
                                <select name="type" style="width:200px;height:30px;" id="type" class="type">
                                    <option value="">-- 请选择 --</option>
                                    <option value="0">发票申请</option>
                                    <option value="1">发票申请和发票</option>
                                </select>
                                <span style="color: red;">*</span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" value="确定" id="invalidbtn" name="invalidbtn" class="layui-btn layui-btn-normal" data-val="2">
                <input type="button" value="关闭" id="back" class="layui-btn layui-btn-primary" data-val="4">
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/financed/invoicelist" src="${ctx}/js/require.js"></script>
</body>
</html>