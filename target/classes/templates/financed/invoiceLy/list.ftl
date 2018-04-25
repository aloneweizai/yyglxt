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
<@shiro.hasPermission name="finance_invoiceReceipt:detail"><#assign canDetail=true></@shiro.hasPermission>
<@shiro.hasPermission name="finance_invoiceReceipt:add"><#assign canAdd=true></@shiro.hasPermission>
<@shiro.hasPermission name="finance_invoiceReceipt:edit"><#assign canEdit=true></@shiro.hasPermission>
<@shiro.hasPermission name="finance_invoiceReceipt:delete"><#assign canDelete=true></@shiro.hasPermission>
<@shiro.hasPermission name="finance_invoiceReceipt:sign"><#assign canSign=true></@shiro.hasPermission>
<@shiro.hasPermission name="finance_invoiceReceipt:audit"><#assign canAudit=true></@shiro.hasPermission>
<@shiro.hasPermission name="finance_invoiceReceipt:distribute"><#assign canDistribute=true></@shiro.hasPermission>


<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/financed/invoiceLyList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">申请人</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.applyUser)!}" name="applyUser" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">签发状态</label>
                    <div class="layui-input-inline">
                        <select name="issueStatus" class="cxinput" id="issueStatus">
                            <option value=""></option>
                        <#if issueStatus?? && ( issueStatus?size gt 0 )>
                            <#list issueStatus as issueSta>
                                <option
                                    <#if BaseRq.issueStatus ?? && (BaseRq.issueStatus == issueSta.fieldValue)>selected</#if>
                                    value="${issueSta.fieldValue}">${issueSta.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">审批状态</label>
                    <div class="layui-input-inline">
                        <select name="examineStatus" class="cxinput" id="examineStatus">
                            <option value=""></option>
                        <#if examineStatus?? && ( examineStatus?size gt 0 )>
                            <#list examineStatus as examineSta>
                                <option
                                    <#if BaseRq.examineStatus ?? && (BaseRq.examineStatus == examineSta.fieldValue)>selected</#if>
                                    value="${examineSta.fieldValue}">${examineSta.fieldKey}</option>
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
                <a href="${ctx}/financed/invoiceLyAdd.php"  class="layui-btn layui-btn-normal fr">新增</a>
                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">开始时间</label>
                        <div class="layui-input-inline">
                            <input type="text" id="test30" value="${(BaseRq.startTime)!}" name="startTime" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">结束时间</label>
                        <div class="layui-input-inline">
                            <input type="text" id="test31" value="${(BaseRq.endTime)!}" name="endTime" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th>序号</th>
                   <#-- <th>所属区域</th>-->
                    <th>申请发票本数</th>
                    <th>申请人</th>
                    <th>申请日期</th>
                    <th>签发状态</th>
                    <th>审核状态</th>
                    <#if canDetail?? || canAdd?? || canEdit?? || canDelete?? || canSign?? || canAudit?? || canDistribute??>
					<th>操作选项</th>
                    </#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if invoiceLyRqs?? && ( invoiceLyRqs?size gt 0 )>
					  <#list invoiceLyRqs as invoiceLy>
					  <tr>
					     <td width="30"  class="td_i">${BaseRq.offset + invoiceLy_index + 1}</td>
						 <#--<td>${(invoiceLy.issueStatus)!}</td>-->
						 <td>${(invoiceLy.applyBook)!}</td>
                          <td>${(invoiceLy.applyUserName)!}</td>
                          <td>${invoiceLy.applyTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                          <#if invoiceLy.issueStatus??>
                              <td>
                                  <#if issueStatus?? && ( issueStatus?size gt 0 )>
            				   <#list issueStatus as issueSta>
                                      <#if invoiceLy.issueStatus == issueSta.fieldValue>${issueSta.fieldKey}</#if>
                                  </#list>
            			    </#if>
                              </td>
                          <#else>
                              <td>
                              </td>
                          </#if>
                          <#if invoiceLy.examineStatus??>
                              <td>
                                  <#if examineStatus?? && ( examineStatus?size gt 0 )>
                                  <#list examineStatus as examineSta>
                                      <#if invoiceLy.examineStatus == examineSta.fieldValue>${examineSta.fieldKey}</#if>
                                  </#list>
                              </#if>
                              </td>
                          <#else>
                              <td>
                              </td>
                          </#if>
                          <#if canDetail?? || canAdd?? || canEdit?? || canDelete?? || canSign?? || canAudit?? || canDistribute??>
                          <#--<td>${(invoiceLy.issueStatus)!}</td>
                          <td>${(invoiceLy.examineStatus)!}</td>-->
                          <#--//待审核-->
                          <#if invoiceLy.examineStatus??&&invoiceLy.examineStatus == '0'>
                              <td>
                                  <#if canDetail??><a data-type="lookdialog" href="javascript:;" data-url="${ctx}/financed/invoiceLyDetail.php?id=${invoiceLy.id}">查看</a>
                                      </#if>
                                  <#if canDetail?? && canAudit??> | </#if>
                                  <#if canAudit??><a href="${ctx}/financed/invoiceLyAdd.php?doType=0&id=${invoiceLy.id}">审批</a></#if>
                              </td>
                            <#--  //审核通过   未分发-->
                          <#elseif invoiceLy.examineStatus??&&invoiceLy.examineStatus == '1'&&invoiceLy.issueStatus??&&invoiceLy.issueStatus == '0'>
                              <td>
                                  <#if canDetail??><a data-type="lookdialog" href="javascript:;" data-url="${ctx}/financed/invoiceLyDetail.php?id=${invoiceLy.id}">查看</a></#if>
                                  <#if canDetail?? && canDistribute??> | </#if>
                                  <#if canDistribute??><a href="${ctx}/financed/invoiceLyAdd.php?doType=1&id=${invoiceLy.id}">分发</a></#if>
                              </td>
                          <#--  //审核通过   已分发-->
                          <#elseif invoiceLy.examineStatus??&&invoiceLy.examineStatus == '1'&&invoiceLy.issueStatus??&&invoiceLy.issueStatus == '1'>
                              <td>
                                  <#if canDetail??><a data-type="lookdialog" href="javascript:;" data-url="${ctx}/financed/invoiceLyDetail.php?id=${invoiceLy.id}">查看</a></#if>
                                  <#if canDetail?? && canSign??> | </#if>
                                  <#if canSign??><a href="${ctx}/financed/invoiceLyAdd.php?doType=3&id=${invoiceLy.id}">签收</a></#if>
                              </td>
                              <#--//审核不通过-->
                          <#elseif invoiceLy.examineStatus??&&invoiceLy.examineStatus == '2'>
                              <td>
                                  <#if canDetail??><a data-type="lookdialog" href="javascript:;" data-url="${ctx}/financed/invoiceLyDetail.php?id=${invoiceLy.id}">查看</a></#if>
                                  <#if canDetail?? && canEdit??> | </#if>
                                  <#if canEdit??><a href="${ctx}/financed/invoiceLyAdd.php?doType=2&id=${invoiceLy.id}">修改</a></#if>
                              </td>
                             <#-- //草稿-->
                          <#elseif invoiceLy.examineStatus??&&invoiceLy.examineStatus == '3'>
                              <td>
                                  <#if canDetail??><a data-type="lookdialog" href="javascript:;" data-url="${ctx}/financed/invoiceLyDetail.php?id=${invoiceLy.id}">查看</a></#if>
                                  <#if canDetail?? && (canEdit?? || canDelete??)> | </#if>
                                  <#if canEdit??><a href="${ctx}/financed/invoiceLyAdd.php?doType=2&id=${invoiceLy.id}">修改</a></#if>
                                  <#if (canDetail?? || canEdit??) && canDelete??> | </#if>
                                  <#if canDelete??><a data-type="delSig" data-confirm="确认删除该发票领用申请?" data-okMsg="删除成功!"
                                     data-failMsg="删除失败" href="javascript:void(0);"
                                     data-href="${ctx}/financed/invoiceLydel.php?id=${invoiceLy.id}" class="pn-opt">删除</a></#if>
                              </td>
                          <#else>
                              <td>
                                  <#if canDetail??><a data-type="lookdialog" href="javascript:;" data-url="${ctx}/financed/invoiceLyDetail.php?id=${invoiceLy.id}">查看</a></#if>
                              </td>
                          </#if>
                          </#if>
					  </tr>
					  </#list>
			        </#if>
                </tbody>
            </table>
		 <table>
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
                      <input type="hidden" value="${ctx}/financed/invoiceLyList.php?applyUser=${(BaseRq.applyUser)!}&&issueStatus=${(BaseRq.issueStatus)!}&&examineStatus=${(BaseRq.examineStatus)!}&&startTime=${(BaseRq.startTime)!}&&endTime=${(BaseRq.endTime)!}" id="currLink">
                  </td>
              </tr>
              </tbody>
          </table>
        </div>		
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="lookModal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>