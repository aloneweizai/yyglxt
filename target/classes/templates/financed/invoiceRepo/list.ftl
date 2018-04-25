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
    <form action="${ctx}/financed/invoiceRepoList.php" method="get" id="consumerListForm" class="layui-form">
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
                    <label class="layui-form-label">库存状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="cxinput" id="status">
                            <option value=""></option>
                        <#if repoStatus?? && ( repoStatus?size gt 0 )>
                            <#list repoStatus as repoSta>
                                <option
                                    <#if BaseRq.status ?? && (BaseRq.status == repoSta.fieldValue)>selected</#if>
                                    value="${repoSta.fieldValue}">${repoSta.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>
                <a href="${ctx}/financed/invoiceRepoAdd.php?doType=0" class="layui-btn">发票入库</a>
                <a href="${ctx}/financed/invoiceRepoAdd.php?doType=1" class="layui-btn layui-btn-normal">批量发票入库</a>



                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">发票种类</label>
                        <div class="layui-input-inline">
                            <select name="invoiceTypeCode"  style="width:205px;height:30px;" id="invoiceTypeCode">
                                <option value=""></option>
                            <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                <#list invoicetype as invoice>
                                    <option
                                        <#if BaseRq.invoiceTypeCode ?? && (BaseRq.invoiceTypeCode == invoice.fieldValue)>selected</#if>
                                        value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                                </#list>
                            </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">发票代码</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.invoiceCode)!}" name="invoiceCode" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">入库时间</label>
                        <div class="layui-input-inline" style="width: 100px">
                            <input type="text" class="layui-input" id="test30" value="${(BaseRq.startTime)!}" name="startTime" style="width: 100px">
                        </div>
                        <div class="layui-input-inline" style="width: 100px">
                            <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}" name="endTime" style="width: 100px">
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

                </div>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <th>发票编号</th>
                    <th>发票种类</th>
                    <th>发票代码</th>
                    <th>发票号码起</th>
                    <th>发票号码止</th>
                    <th>可用份数</th>
                    <th>库存状态</th>
                    <th>入库时间</th>
                    <th>修改时间</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if invoiceRepoRqs?? && ( invoiceRepoRqs?size gt 0 )> 
					  <#list invoiceRepoRqs as invoiceRepo>
					  <tr>
					     <td width="30"  class="td_i">${BaseRq.offset + invoiceRepo_index + 1}</td>
						 <td>${(invoiceRepo.id)!}</td>
						 <td>${(invoiceRepo.invoiceTypeName)!}</td>
                          <td>${(invoiceRepo.invoiceCode)!}</td>
                          <td>${(invoiceRepo.invoiceNoStart)!}</td>
                          <td>${(invoiceRepo.invoiceNoEnd)!}</td>
                          <td>${(invoiceRepo.share?c)!}</td>
                          <#if invoiceRepo.status??>
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
                          </#if>
						 <td>${invoiceRepo.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                          <td>${invoiceRepo.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
                         <#-- 发票库存状态，0：未出库，1：已出库，2：重新入库-->
                          <#if invoiceRepo.status??>
                          <#if invoiceRepo.status == "0">
						 <td><a href="${ctx}/financed/invoiceRepoDetailList.php?invoiceRepoId=${invoiceRepo.id}">明细</a>&nbsp;|
                             <a href="${ctx}/financed/invoiceRepoDetail.php?invoiceRepoId=${invoiceRepo.id}">修改</a>
							|&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/financed/invoiceRepodel.php?id=${(invoiceRepo.id)!}" class="pn-opt">删除</a>
						 </td>
                          <#elseif invoiceRepo.status == "1">
                              <td><a href="${ctx}/financed/invoiceRepoDetailList.php?invoiceRepoId=${invoiceRepo.id}">明细</a>
                                  <#--|&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/financed/invoiceRepodel.php?id=${(invoiceRepo.id)!}" class="pn-opt">删除</a>-->
                              </td>
                          <#elseif invoiceRepo.status == "2">
                              <td><a href="${ctx}/financed/invoiceRepoDetailList.php?invoiceRepoId=${invoiceRepo.id}">明细</a>
                                  <#--|&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/financed/invoiceRepodel.php?id=${(invoiceRepo.id)!}" class="pn-opt">删除</a>-->
                              </td>
                          </#if>
                          <#else>
                              <td><a href="${ctx}/financed/invoiceRepoDetailList.php?invoiceRepoId=${invoiceRepo.id}">明细</a>
                                  <#--|&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/financed/invoiceRepodel.php?id=${(invoiceRepo.id)!}" class="pn-opt">删除</a>-->
                              </td>
                          </#if>
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