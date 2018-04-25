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
	<#--
	<table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/financed/invoiceRepoList.php">
            <u>发票仓库管理</u>
          </a> &gt;&gt; 发票仓库明细</td>
      </tr>
    </table>
	-->
    <form action="${ctx}/financed/invoiceRepoDetailList.php" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">发票号码</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.invoiceNo)!}" name="invoiceNo"  class="layui-input" >
                        <input type="text" value="${(BaseRq.invoiceRepoId)!}" name="invoiceRepoId"  hidden>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width:220px;">请选择发票库存状态</label>
                    <div class="layui-input-inline">
                        <select name="status" style="width:200px;height:30px;">
                            <option value=""></option>
                            <#if invoicestatus?? && ( invoicestatus?size gt 0 )>
                                <#list invoicestatus as invoicestas>
                                    <option <#if BaseRq.status ?? && (BaseRq.status == invoicestas.fieldValue)>selected</#if> value="${invoicestas.fieldValue}">${invoicestas.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/financed/invoiceRepoList.php?page=${(InvoiceRepoRq.page)!}&noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
                   class="layui-btn layui-btn-primary fr" style="text-decoration:none;color:black">返回</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th>序号</th>
					<th>发票编号</th>
                    <th>发票代码</th>
                    <th>发票号码</th>
                    <th>发票性质</th>
                    <th>状态</th>
                   <#-- <th>录入人</th>-->
                    <th>入库时间</th>
                    <th>备注</th>
                   <#-- <th>修改时间</th>-->
					<#--<th>操作选项</th>-->
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if invoiceRepoRqs?? && ( invoiceRepoRqs?size gt 0 )> 
					  <#list invoiceRepoRqs as invoiceRepo>
					  <tr>
					     <td width="30" class="td_i">${BaseRq.offset + invoiceRepo_index + 1}</td>
						 <td>${(invoiceRepo.invoiceRepoId)!}</td>
						 <td>${(invoiceRepo.invoiceCode)!}</td>
                          <td>${(invoiceRepo.invoiceNo)!}</td>
						 <td>
						   <#if invoiceRepo.property?? && invoiceRepo.property=="1">纸质发票
						   <#else>电子发票
						   </#if>
						 </td>
						 <td>
							<#if invoicestatus?? && ( invoicestatus?size gt 0 )> 
            					  <#list invoicestatus as invoicestas>
                                    <#if invoiceRepo.status ?? && (invoiceRepo.status == invoicestas.fieldValue)>${invoicestas.fieldKey}</#if>
            					  </#list>
            			    </#if>
                         </td>
                          <#--<td>${(invoiceRepo.createUser)!}</td>-->
                          <#if invoiceRepo.createTime??>
                              <td>${invoiceRepo.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                          <#else>
                              <td></td>
                          </#if>
                          <td>${(invoiceRepo.remark)!}</td>
                         <#-- <#if invoiceRepo.lastUpdate??>
                              <td>${invoiceRepo.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
                          <#else>
                              <td></td>
                          </#if>-->
						 <#--<td>
							<#if invoiceRepo.status?? && invoiceRepo.status!="3" && invoiceRepo.status!="1">
							<a data-type="delSig" data-confirm="确认作废该发票?" data-okMsg="发票作废成功!" data-failMsg="发票作废失败" href="javascript:void(0);" data-href="${ctx}/financed/invoiceDtDisable.php?id=${(invoiceRepo.id)!}" class="pn-opt">作废</a>
							</#if>
							<#if invoiceRepo.status?? && invoiceRepo.status=="0">
							|&nbsp;<a data-type="delSig" data-confirm="确认删除该发票?" data-okMsg="发票删除成功!" data-failMsg="发票删除失败" href="javascript:void(0);" data-href="${ctx}/financed/invoiceDitaildel.php?id=${(invoiceRepo.id)!}" class="pn-opt">删除</a>
							</#if>
						 </td>-->
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