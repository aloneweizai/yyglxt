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
    <form action="${ctx}/financed/invoiceBackList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">运单号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.userOrderNo)!}" name="userOrderNo" placeholder="运单号" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票号码</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.invoiceNo)!}" name="invoiceNo" placeholder="发票号码" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">快递单号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.expressNo)!}" name="expressNo" placeholder="快递单号" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">退单号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.sendExpressNo)!}" name="sendExpressNo" placeholder="退单号" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" >
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <th>运单号</th>
					<th>快递单号</th>
                    <th>发票类型</th>
                    <th>发票号码</th>
                    <th>发票性质</th>
                    <th>退票状态</th>
                    <th>退票单号</th>
					<th>快递公司</th>
					<th>申请时间</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if invoicebacks?? && ( invoicebacks?size gt 0 )> 
					  <#list invoicebacks as invoiceback>
					  <tr>
						 <td class="td_i">${BaseRq.offset + invoiceback_index + 1}</td>
						 <td>${invoiceback.invoiceBO.userOrderNo!}</td>
						 <td>${invoiceback.invoiceBO.expressNo!}</td>
						 <td>
							<#if invoicetype?? && ( invoicetype?size gt 0 )> 
            				   <#list invoicetype as types>
                                   <#if invoiceback.invoiceBO.type?? && invoiceback.invoiceBO.type == types.fieldValue>${types.fieldKey}</#if>
            				   </#list>
            			    </#if>
                         </td>
						 <td>${invoiceback.invoiceBO.invoiceNo!}</td>
						 <td>${invoiceback.invoiceBO.property!}</td>
						 <td>
							<#if ibackstatus?? && ( ibackstatus?size gt 0 )> 
            				   <#list ibackstatus as status>
                                   <#if invoiceback.status?? && invoiceback.status == status.fieldValue>${status.fieldKey}</#if>
            				   </#list>
            			    </#if>
                         </td>
						 <td>${(invoiceback.expressNo)!}</td>
						 <td>${(invoiceback.expressComp)!}</td>
						 <td>${invoiceback.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td><a href="${ctx}/financed/backInfo.php?id=${invoiceback.id}">查看</a></td>
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
				<input type="hidden" value="${ctx}/financed/invoiceBackList.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>