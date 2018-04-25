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
    <form action="${ctx}/financed/RepoList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">发票编号起</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.noStart)!}" name="noStart" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票编号止</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.noEnd)!}" name="noEnd" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票代码</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.invoiceCode)!}" name="invoiceCode" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票号码起</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.invoiceNoStart)!}" name="invoiceNoStart" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票号码止</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.invoiceNoEnd)!}" name="invoiceNoEnd" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票种类</label>
                    <div class="layui-input-inline">
                        <#if invoicetype?? && ( invoicetype?size gt 0 )>
                            <#list invoicetype as invoice>
                                <#if BaseRq.invoiceTypeCode ?? && (BaseRq.invoiceTypeCode == invoice.fieldValue)>
                                    <input type="text" hidden value="${(BaseRq.invoiceTypeCode)!}" name="invoiceTypeCode" id="invoiceTypeCode">
                                    <input type="text" id="invoiceTypename" value=" ${invoice.fieldKey}"  class="layui-input" readonly="readonly" ></span>
                                </#if>
                            </#list>
                        </#if>
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
                    <th>
                        <div class="nycon_sel_btn js_selectAll" data-check=false>全选</div>
                    </th>
                    <th>发票编号</th>
                    <th>发票种类</th>
                    <th>发票代码</th>
                    <th>发票号码起</th>
                    <th>发票号码止</th>
                    <th>可用份数</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if invoiceRepoRqs?? && ( invoiceRepoRqs?size gt 0 )> 
					  <#list invoiceRepoRqs as invoiceRepo>
					  <tr>
					     <td width="30"  class="td_i">${BaseRq.offset + invoiceRepo_index + 1}</td>
                          <td>
                              <div class="layui-input-inline">
                                  <input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${invoiceRepo.id}">
                              </div>
                          </td>
						 <td>${(invoiceRepo.id)!}</td>
						 <td>${(invoiceRepo.invoiceTypeName)!}</td>
                          <td>${(invoiceRepo.invoiceCode)!}</td>
                          <td>${(invoiceRepo.invoiceNoStart)!}</td>
                          <td>${(invoiceRepo.invoiceNoEnd)!}</td>
                          <td>${(invoiceRepo.share)!}</td>
                         <#-- <#if invoiceRepo.status??>
                              <td>
                                  <#if repoStatus?? && ( repoStatus?size gt 0 )>
            				   <#list repoStatus as repoSta>
                                      <#if invoiceRepo.status == repoSta.fieldValue>${repoSta.fieldKey}</#if>
                                  </#list>
            			    </#if>
                              </td>
                          <#else>
                              <td>
                              </td>
                          </#if>-->
						 <#--<td>${invoiceRepo.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                          <td>${invoiceRepo.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>-->
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
                      <input type="hidden" value="${ctx}/financed/invoiceRepoList.php?noStart=${(BaseRq.noStart)!}&&noEnd=${(BaseRq.noEnd)!}&&invoiceCode=${(BaseRq.invoiceCode)!}&&invoiceTypeCode=${(BaseRq.invoiceTypeCode)!}&&status=${(BaseRq.status)!}&&invoiceNoStart=${(BaseRq.invoiceNoStart)!}&&invoiceNoEnd=${(BaseRq.invoiceNoEnd)!}&&startTime=${(BaseRq.startTime)!}&&endTime=${(BaseRq.endTime)!}"
                             id="currLink">
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