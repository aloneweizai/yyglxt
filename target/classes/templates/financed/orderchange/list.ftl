<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
	<script type="text/javascript">
            var ctx = "${ctx}";
        </script>
</head>
<!--查看-->
<@shiro.hasPermission name="finance_orderchange:detail">
    <#assign canDetail=true>
</@shiro.hasPermission>
<!--审核-->
<@shiro.hasPermission name="finance_orderchange:audit">
    <#assign canAudit=true>
</@shiro.hasPermission>
<!--确认收货-->
<@shiro.hasPermission name="finance_orderchange:makesure">
    <#assign canMakesure=true>
</@shiro.hasPermission>
<!--确认退单-->
<@shiro.hasPermission name="finance_orderchange:back">
    <#assign canBack=true>
</@shiro.hasPermission>
<!--导入快递单号-->
<@shiro.hasPermission name="finance_orderchange:drkddh">
    <#assign canDrkddh=true>
</@shiro.hasPermission>
<!--导出待发货信息-->
<@shiro.hasPermission name="finance_orderchange:dcdfhxx">
    <#assign canDcdfhxx=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/orderchange/applications.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">订单号</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.orderNo)!}" name="orderNo">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.username)!}" name="username">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">申请类型</label>
                    <div class="layui-input-inline">
                        <select name="type" lay-filter="aihao">
                            <option value="">请选择申请类型</option>
                            <option <#if BaseRq.type ?? && (BaseRq.type == "1")>selected</#if> value="1">换货</option>
                            <option <#if BaseRq.type ?? && (BaseRq.type == "2")>selected</#if> value="2">退货</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">订单状态</label>
                    <div class="layui-input-inline">
                        <select name="status" lay-filter="aihao"  id="orderStatus">
                            <option value="">请选择订单状态</option>
                            <#if exchange_status?? && ( exchange_status?size gt 0 )>
                                <#list exchange_status as status>
                                    <option
                                        <#if BaseRq.status ?? && (BaseRq.status == status.fieldValue)>selected</#if>
                                        value="${status.fieldValue}">${status.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button  id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
            </div>
        </div>



      <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th>序号</th>
                    <th>订单号</th>
					<th>用户名</th>
					<th>商品名称</th>
					<th>申请类型</th>
                    <th>原因</th>
					<th>申请时间</th>
					<th>状态</th>
<#if canAudit?? || canDetail?? || canMakesure?? || canBack??>
					<th>操作</th>
</#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
				  <#if orderChangeRes?? && ( orderChangeRes?size gt 0 )> 
					  <#list orderChangeRes as orderChange>
					  <tr>
					    <td class="td_i">${BaseRq.offset + orderChange_index + 1}</td>
                          <td><a  class="ljc_00bcd4" id="pointsLog" data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}"
                                 data-val="1"> ${orderChange.orderNo!}</a>
                          </td>
						<#--<td>${invoiceRepo.orderNo!}</td>-->
						<#--<td>${invoiceRepo.username!}</td>
						<td><img height="35" width="50" src="${imgPth}${invoiceRepo.imageUrl!}">&nbsp;&nbsp;${invoiceRepo.name!}</td>-->
						<#--<td>${orderChange.orderNo!}</td>-->
						<td>${orderChange.username!}</td>
						<td>
                            <#if orderChange.imageUrl??>
                                <img height="32" width="32" src="${imgPth}${orderChange.imageUrl!}">&nbsp;&nbsp;${orderChange.name!}
                            <#else>
                                &nbsp;&nbsp;${orderChange.name!}
                            </#if>
                        </td>
						<td>
						   <#if orderChange.type == "1"><div class="btn btn-info btn-xs ">换货</div><#else><div class="btn btn-warning btn-xs ">退货</div></#if>
                        </td>
                        <td>
						   <#if exchange_reason?? && ( exchange_reason?size gt 0 )> 
            				   <#list exchange_reason as reason>
                                   <#if orderChange.reason == reason.fieldValue>${reason.fieldKey}</#if>
            				   </#list>
            			   </#if>
                        </td>
                        <td>${(orderChange.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td>
							<#if exchange_status?? && ( exchange_status?size gt 0 )> 
            				   <#list exchange_status as status>
                                   <#if orderChange.status == status.fieldValue>${status.fieldKey}</#if>
            				   </#list>
            			    </#if>
                        </td>
                          <#if canAudit?? || canDetail?? || canMakesure?? || canBack??>
    					<td>
						     <#if orderChange.status?? && orderChange.status == "1">
                                 <#if canDetail??>
                                 <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=0"
                                    data-val="5" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                                 </#if>
                                 <#if canAudit??>
                                  <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=1"
                                     data-val="2" class="layui-btn layui-btn-mini">审核</a>
                                 </#if>
							 <#elseif orderChange.status?? && orderChange.status == "2">
            					<#if orderChange.type=="1">
                                    <#if canDetail??>
                                        <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=0"
                                           data-val="5" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                                    </#if>
                                    <#if canMakesure??> <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=2&type=1"
                                       data-val="3" class="layui-btn layui-btn-mini">确认收货</a>
                                    </#if>
            				    <#else>
                                    <#if canDetail??>
                                        <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=0"
                                           data-val="5" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                                    </#if>
                                    <#if canBack??>
                                     <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=2&type=2"
                                       data-val="4" class="layui-btn layui-btn-danger layui-btn-mini">确认退单</a>
                                    </#if>
                                </#if>
							 <#else>
                                 <#if canDetail??>
                                     <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/charge.php?id=${orderChange.id!}&status=0"
                                        data-val="5" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
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
                <#if canDcdfhxx??>
                          <input type='button' <#--data-type='downLoad'--> id="consumer_export" data-qurl="${ctx}/orderchange/qexport.php" data-durl="${ctx}/orderchange/export.php" data-noMsg="当前无待发货退换货信息，请稍后再试"  class="layui-btn" value='导出待发货信息' />
                </#if>
                <#if canDrkddh??>
                          <input type="button" value="导入快递单号" id="consumer_import" class="layui-btn layui-btn-normal">
                </#if>
              <div class="clear"></div>
          </div>
		 <table class="yy_fanye">
          <tbody>
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
				<input type="hidden" value="${ctx}/orderchange/applications.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
	
<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
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
                                快递单Excel：
                            </td>
                            <td>
                                <input type="file" name="uploadFile" id="uploadFile" style="width:200px;display: inline-block;">
                            </td>
                        </tr>
                    </table>
                </div>
				<#--<div>
                                快递单Excel：<input type="file" name="uploadFile" id="uploadFile" style="width:200px;display: inline-block;">
                </div>-->
            </div>
            <div class="modal-footer">
                <button type="button" id="importbtn" name="importbtn" class="layui-btn layui-btn-normal">导入</button>
                <button type="button" id="back" class="layui-btn layui-btn-primary">关闭</button>
            </div>
        </div>
    </div>
</div>

    <div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
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
                    <button  id='downLoad' id="exportbtn" name="exportbtn" class="layui-btn layui-btn-normal" data-qurl="${ctx}/orderchange/qexport.php" data-durl="${ctx}/orderchange/export.php" data-noMsg="当前无待发货退换货信息，请稍后再试">导出</button>
                    <button  value="关闭" id="back" class="layui-btn layui-btn-primary">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--退/换货审批-->
<div class="main_modal container-fluid" id="myModal2" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_charge" name="consumer_charge"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn" data-save="modal">确定</button>
                <button class="layui-btn layui-btn-primary" data-dismiss="modal" data-val="2">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--确认收货-->
<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_sure"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn" data-save="modal1">确定</button>
                <button class="layui-btn layui-btn-primary"ype="button" data-dismiss="modal" data-val="3">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--确认退单-->
<div class="main_modal container-fluid" id="myModal4" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_back"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn" data-save="modal2">确定</button>   <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal" data-val="4">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal5" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal" data-val="5">关闭</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/financed/exhcangelist.js" src="${ctx}/js/require.js"></script>
</body>
</html>
