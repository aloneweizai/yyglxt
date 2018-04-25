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
<form name="invoiceLy_detail_form" action="" enctype="multipart/form-data" method="post"
      next-url="">
    <div class="container-fluid m_top_30 nycol_list  sys_mod">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#home" data-toggle="tab">发票领用申请信息</a>
            </li>
<#if invoiceLyRs.examineStatus??&&invoiceLyRs.examineStatus != "3">
        <li><a href="#ios" data-toggle="tab">审批记录</a></li>
</#if>
            <#if invoiceLyRs.issueStatus??&&invoiceLyRs.issueStatus != "0">
            <li class="dropdown"><a href="#jmeter" data-toggle="tab">发票分发信息</a></li>
            </#if>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <table class="layui-table" lay-size="sm">
                    <caption>发票领用申请信息</caption>
                    <#if invoiceLyRs??>
                    <tr>
                        <td style="font-weight:bold;">申请人：</td>
                        <td>${(invoiceLyRs.applyUserName)!}</td>
                        <td style="font-weight:bold;">申请日期：</td>
                        <td>${invoiceLyRs.applyTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    </tr>
                    <tr>
                        <td style="font-weight:bold;">签发状态：</td>
                        <#if invoiceLyRs.issueStatus??>
                            <td>
                                <#if issueStatus?? && ( issueStatus?size gt 0 )>
            				   <#list issueStatus as issueSta>
                                    <#if invoiceLyRs.issueStatus == issueSta.fieldValue>${issueSta.fieldKey}</#if>
                                </#list>
            			    </#if>
                            </td>
                        <#else>
                            <td>
                            </td>
                        </#if>
                        <td style="font-weight:bold;">审批状态：</td>
                        <#if invoiceLyRs.examineStatus??>
                            <td>
                                <#if examineStatus?? && ( examineStatus?size gt 0 )>
                                  <#list examineStatus as examineSta>
                                    <#if invoiceLyRs.examineStatus == examineSta.fieldValue>${examineSta.fieldKey}</#if>
                                </#list>
                              </#if>
                            </td>
                        <#else>
                            <td>
                            </td>
                        </#if>
                    </tr>
                    <#if invoiceLyRs.issueStatus??&&invoiceLyRs.issueStatus != "0">
                        <tr>
                            <td style="font-weight:bold;">分发人：</td>
                            <td>${(invoiceLyRs.distributeUserName)!}</td>
                            <td style="font-weight:bold;">分发时间：</td>
                            <td>
                        <#if invoiceLyRs.distributeTime??>
                            ${invoiceLyRs.distributeTime?string("yyyy-MM-dd HH:mm:ss")}
                        </#if>
                            </td>
                        </tr>
                    </#if>
                        <#if invoiceLyRs.issueStatus??&&invoiceLyRs.issueStatus == "2">
                            <tr>
                                <td style="font-weight:bold;">签收人：</td>
                                <td>${(invoiceLyRs.signUserName)!}</td>
                                <td style="font-weight:bold;">签收时间：</td>
                                <td>
                                    <#if invoiceLyRs.signTime??>
                                        ${invoiceLyRs.signTime?string("yyyy-MM-dd HH:mm:ss")}
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                    <tr>
                        <td style="font-weight:bold;">备注：</td>
                        <td colspan="3">${(invoiceLyRs.remark)!}</td>
                    </tr>
                    </#if>
                </table>
                <table class="layui-table" lay-size="sm">
                    <caption>发票领用明细(单位:本)</caption>
                    <tr style="font-weight:bold;">
                        <td width="20%">发票种类</td>
                        <td width="20%">申请数量</td>
                        <td width="20%">审批数量</td>
                        <td width="20%">实发数量</td>
                        <td>备注</td>
                    </tr>
               <#if invoiceLyRs.invoiceUseDetailBOList?? && ( invoiceLyRs.invoiceUseDetailBOList?size gt 0 )>
                <#list invoiceLyRs.invoiceUseDetailBOList as invoiceUseDetail>
                    <tr>
                        <td class="mdtablethtd">
                            <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                <#list invoicetype as invoice>
                                    <#if invoiceUseDetail.invoiceTypeCode ?? && (invoiceUseDetail.invoiceTypeCode == invoice.fieldValue)>${invoice.fieldKey}</#if>
                                </#list>
                            </#if>
                        </td>
                        <td class="mdtablethtd">${(invoiceUseDetail.applyNum?c)!}</td>
                        <td class="mdtablethtd">
                    <#if invoiceLyRs.examineStatus??&&invoiceLyRs.examineStatus == '2'>
                    ${(invoiceUseDetail.applyNum?c)!}
                    <#else>
                    ${(invoiceUseDetail.checkBook?c)!}
                    </#if>
                        </td>
                        <td class="mdtablethtd">
                            <#if invoiceLyRs.examineStatus??&&invoiceLyRs.examineStatus == '2'>
                          0
                                <#else>
                            ${(invoiceUseDetail.realNum?c)!}
                            </#if>
                        </td>
                        <td class="mdtablethtd">${(invoiceUseDetail.remark)!}</td>
                    </tr>
                     </#list>
                 </#if>
                </table>
            </div>

   <div class="tab-pane fade" id="ios">
       <table class="layui-table" lay-size="sm">
       <caption>审批记录</caption>
           <tr style="font-weight:bold;">
               <td>执行顺序</td>
               <td>审批结果</td>
               <td>审批意见</td>
               <td>审批人及时间</td>
           </tr>
       <#if invoiceLyRs.invoiceApprovalLogList?? && ( invoiceLyRs.invoiceApprovalLogList?size gt 0 )>
           <#list invoiceLyRs.invoiceApprovalLogList as invoiceApprovalLog>
               <tr>
                   <td width="30"  class="td_i">第${BaseRq.offset + invoiceApprovalLog_index + 1}步</td>
                   <td class="mdtablethtd">${(invoiceApprovalLog.approvalResult)!}</td>
                   <td class="mdtablethtd">${(invoiceApprovalLog.approvalOpinions)!}</td>
                   <td class="mdtablethtd"><span>提交人：${(invoiceApprovalLog.approver)!}</span>
                       <br/>
                       <span>提交时间：<#if invoiceApprovalLog.approverTime??>${invoiceApprovalLog.approverTime?string("yyyy-MM-dd HH:mm:ss")}</#if></span></td>
               </tr>
           </#list>
       </#if>
     </table>
   </div>

            <div class="tab-pane fade" id="jmeter">
                <table class="layui-table" lay-size="sm">
                    <caption>发票分发信息</caption>
                    <tr style="font-weight:bold;">
                        <td>序号</td>
                        <td>发票编号</td>
                        <td>发票种类</td>
                        <td>发票代码</td>
                        <td>发票号码起</td>
                        <td>发票号码止</td>
                        <td>发票份数</td>
                        <td>签发状态</td>
                        <td>备注</td>
                    </tr>
                <#if invoiceLyRs.invoiceDistributeBOList?? && ( invoiceLyRs.invoiceDistributeBOList?size gt 0 )>
                    <#list invoiceLyRs.invoiceDistributeBOList as invoiceDistribute>
                        <tr>
                            <td width="30"  class="td_i">${BaseRq.offset + invoiceDistribute_index + 1}</td>
                            <td class="mdtablethtd">${(invoiceDistribute.invoiceRepoId)!}</td>
                            <td class="mdtablethtd">
                                <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                    <#list invoicetype as invoice>
                                        <#if invoiceDistribute.invoiceTypeCode ?? && (invoiceDistribute.invoiceTypeCode == invoice.fieldValue)>${invoice.fieldKey}</#if>
                                    </#list>
                                </#if>
                            </td>
                            <td class="mdtablethtd">${(invoiceDistribute.invoiceCode)!}</td>
                            <td class="mdtablethtd">${(invoiceDistribute.invoiceNoStart)!}</td>
                            <td class="mdtablethtd">${(invoiceDistribute.invoiceNoEnd)!}</td>
                            <td class="mdtablethtd">${(invoiceDistribute.share)!}</td>
                            <td class="mdtablethtd">
                                <#if invoiceDistribute.status??&&invoiceDistribute.status == '0'>
                                    未分发
                                <#elseif invoiceDistribute.status??&&invoiceDistribute.status == '1'>
                                    已分发
                                <#elseif invoiceDistribute.status??&&invoiceDistribute.status == '2'>
                                    已签收
                                <#elseif invoiceDistribute.status??&&invoiceDistribute.status == '3'>
                                    已缴销
                                </#if>
                            </td>
                            <td class="mdtablethtd">${(invoiceDistribute.remark)!}</td>
                        </tr>
                    </#list>
                </#if>
                </table>
            </div>
        </div>
        <#--<table>
            <tr>
                <td>
                  &lt;#&ndash;  <input type="button" name="sendBtn" id="checkBtn" value="审核" class="layui-btn layui-btn-normal">
                    <input type="button" name="sendBtn" id="distBtn" value="分发" class="layui-btn layui-btn-normal">&ndash;&gt;
                      <a href="${ctx}/financed/invoiceLyList.php?page=${(InvoiceLyRq.page)!}&applyUser=${(InvoiceLyRq.applyUser)!}&&issueStatus=${(InvoiceLyRq.issueStatus)!}&&examineStatus=${(InvoiceLyRq.examineStatus)!}&&startTime=${(InvoiceLyRq.startTime)!}&&endTime=${(InvoiceLyRq.endTime)!}"
                         style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                </td>
            </tr>
        </table>-->
    </div>
</form>
<script data-main="${ctx}/js/abc/financed/invoiceLy.js" src="${ctx}/js/require.js"></script>
</body>
</html>