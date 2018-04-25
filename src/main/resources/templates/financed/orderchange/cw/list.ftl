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
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/orderchange/cw/applications.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">订单号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.orderNo)!}" name="orderNo" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">申请类型</label>
                    <div class="layui-input-inline">
                        <select name="type" class="cxinput" id="type">
                            <option value=""></option>
                            <option <#if BaseRq.type ?? && (BaseRq.type == "1")>selected</#if> value="1">换货</option>
                            <option <#if BaseRq.type ?? && (BaseRq.type == "2")>selected</#if> value="2">退货</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
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
					<th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
				  <#if orderChangeRes?? && ( orderChangeRes?size gt 0 )> 
					  <#list orderChangeRes as orderChange>
					  <tr>
					    <td class="td_i">${BaseRq.offset + orderChange_index + 1}</td>
						<td>${orderChange.orderNo!}</td>
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
    					<td>
						     <#if orderChange.status?? && orderChange.status == "7">
                                 <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/cw/charge.php?lookType=0&&id=${orderChange.id!}"
                                    data-val="6">查看</a>&nbsp;|
                             <a href="${ctx}/orderchange/cw/charge.php?lookType=1&&id=${orderChange.id!}">退款</a>
							 <#else>
                                 <a data-type="exchangedialog" href="javascript:;" data-url="${ctx}/orderchange/cw/charge.php?lookType=0&&id=${orderChange.id!}"
                                    data-val="6">查看</a>
                             </#if>
    					</td>
                      </tr>
					 </#list>
				  </#if>
                </tbody>
            </table>
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
				<input type="hidden" value="${ctx}/orderchange/cw/applications.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
	
	
</div>

<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="look_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary"data-val="6" data-dismiss="lookModal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/financed/exhcangelist.js" src="${ctx}/js/require.js"></script>
</body>
</html>