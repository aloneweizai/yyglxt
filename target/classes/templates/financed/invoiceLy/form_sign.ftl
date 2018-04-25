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
<form name="invoiceLy_sign" id="invoiceLy_sign" action="" enctype="multipart/form-data" method="post"
      next-url="">
    <div class="container-fluid m_top_30 nycol_list  sys_mod">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <table class="layui-table" lay-size="sm">
                    <caption>发票领用申请信息</caption>
                    <#if invoiceLyRs??>
                    <tr style="font-weight:bold;">
                        <td>申请人：</td>
                        <td>
                            <input type="hidden" style="width: 100px;" name="id" value="${(invoiceLyRs.id)!}">
                        ${(invoiceLyRs.applyUserName)!}</td>
                        <td>申请日期：</td>
                        <td>${invoiceLyRs.applyTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    </tr>
                    <tr>
                        <td>签发状态：</td>
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
                        <td>审批状态：</td>
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
                    <tr>
                        <td>备注：</td>
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
                            <input type="hidden" style="width: 100px;" name="invoiceTypeCode" value="${(invoiceUseDetail.invoiceTypeCode)!}">
                            <input type="hidden" style="width: 100px;" name="invoiceTypeCode" value=" ">
                            <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                <#list invoicetype as invoice>
                                    <#if invoiceUseDetail.invoiceTypeCode ?? && (invoiceUseDetail.invoiceTypeCode == invoice.fieldValue)>${invoice.fieldKey}</#if>
                                </#list>
                            </#if>
                        </td>
                        <td class="mdtablethtd">${(invoiceUseDetail.applyNum?c)!}</td>
                        <td class="mdtablethtd">${(invoiceUseDetail.checkBook?c)!}</td>
                        <td class="mdtablethtd">${(invoiceUseDetail.realNum?c)!}</td>
                        <td class="mdtablethtd">${(invoiceUseDetail.remark)!}</td>
                    </tr>
                     </#list>
                 </#if>
                </table>
            </div>
        </div>
        <table>
            <tr>
                <td>
                    <input type="hidden" style="width: 100px;" name="user" value="${(InvoiceLyRq.applyUser)!}">
                    <input type="hidden" style="width: 100px;" name="isStatus" value="${(InvoiceLyRq.issueStatus)!}">
                    <input type="hidden" style="width: 100px;" name="exStatus" value="${(InvoiceLyRq.examineStatus)!}">
                    <input type="hidden" style="width: 100px;" name="startTime" value="${(InvoiceLyRq.startTime)!}">
                    <input type="hidden" style="width: 100px;" name="endTime" value="${(InvoiceLyRq.endTime)!}">
                    <input type="hidden" style="width: 100px;" name="page" value="${(InvoiceLyRq.page)!}">
                      <input type="button" name="signBtn" id="signBtn" value="签收" class="layui-btn">
                    <a href="${ctx}/financed/invoiceLyList.php?page=${(InvoiceLyRq.page)!}&applyUser=${(InvoiceLyRq.applyUser)!}&&issueStatus=${(InvoiceLyRq.issueStatus)!}&&examineStatus=${(InvoiceLyRq.examineStatus)!}&&startTime=${(InvoiceLyRq.startTime)!}&&endTime=${(InvoiceLyRq.endTime)!}"
                       style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                </td>
            </tr>
        </table>
    </div>
</form>
<script data-main="${ctx}/js/abc/financed/invoiceLy.js" src="${ctx}/js/require.js"></script>
</body>
</html>