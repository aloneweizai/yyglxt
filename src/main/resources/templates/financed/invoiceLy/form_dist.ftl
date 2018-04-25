<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/autocomplete/jquery.bigautocomplete.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .table>thead>tr>th,.table>tbody>tr>td{text-align:left;}
        .mdtablethtd{ border-bottom: 1px solid #ddd; padding:5px;}
        .mdtablethtd input{
            border:1;}
        .mdtablethtd .jia{background:#2e9ad0; color:#fff; width:22px; line-height:22px;
            margin-right:5px;
            text-align: center;
            display: inline-block;
            cursor: pointer}
        .mdtablethtd .jian{background:#d87271; color:#fff; width:22px; line-height:22px;
            margin-right:5px;
            text-align: center;
            display: inline-block;
            cursor: pointer}
    </style>
</head>
<body>
<form id="invoice_dist">
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <table class="table table-bordered">
        <caption>填写申请信息</caption>
    <#if invoiceLyRs??>
        <tr>
            <td width="120">申请人：</td>
            <td>
                <input type="hidden" style="width: 100px;" name="id" value="${(invoiceLyRs.id)!}">
        ${(invoiceLyRs.applyUserName)!}
            </td>
            <td width="120">申请日期：</td>
            <td>
            ${invoiceLyRs.applyTime?string("yyyy-MM-dd HH:mm:ss")}
            </td>
        </tr>
        <tr>
            <td width="120">签发状态：</td>
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
            <td width="120">审批状态：</td>
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
            <td width="120">备注</td>
            <td colspan="3">
                <textarea style="width: 500px; height: 100px;" value="${(invoiceLyRs.remark)!}" id="remark"
                          name="remark" readonly="readonly">${(invoiceLyRs.remark)!}</textarea>
            </td>
        </tr>
    </#if>
    </table>
    <table class="table-hover" style="margin-top:10px;width:70%">
        <caption>发票申请明细(按种类和本数填写) </caption>
        <tr>
            <th width="20%" class="mdtablethtd">发票种类</th>
            <th width="10%" class="mdtablethtd">库存数量(本)</th>
            <th width="10%" class="mdtablethtd">申请数量(本)</th>
            <th width="15%" class="mdtablethtd">申请备注</th>
            <th width="10%" class="mdtablethtd">审批数量</th>
            <th width="10%" class="mdtablethtd">实发数量<span style="color: red;">*</span></th>
            <th width="10%" class="mdtablethtd">操作</th>
            <th width="15%" class="mdtablethtd">分发备注</th>
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
                <td class="mdtablethtd">${(invoiceUseDetail.repoNum?c)!}</td>
                <td class="mdtablethtd">${(invoiceUseDetail.applyNum?c)!}</td>
                <td class="mdtablethtd">${(invoiceUseDetail.remark)!}</td>
                <td class="mdtablethtd">${(invoiceUseDetail.checkBook?c)!}</td>
                <td class="mdtablethtd">
                    <input type="hidden" style="width: 100px;" name="checkBook" id="checkBook" value="${(invoiceUseDetail.checkBook)!}">
                    <input type="hidden" style="width: 100px;" name="checkBook" value=" ">
                    <input type="hidden" style="width: 100px;" name="useId" value="${(invoiceUseDetail.useId)!}">
                    <input type="hidden" style="width: 100px;" name="useId" value=" ">
                    <input type="text" style="width: 100px;"  class="layui-input realNum" name="realNum" value="" readonly="readonly">
                    <input type="hidden" style="width: 100px;" name="realNum" value="-1"></td>
                <td class="mdtablethtd">
                    <input type="hidden" style="width: 100px;" name="fpmxId" value="${(invoiceUseDetail.id)!}">
                    <input type="hidden" style="width: 100px;" name="fpmxId" value=" ">
                    <input type="hidden" style="width: 100px;" class="invoiceRepoIds" name="invoiceRepoIds" value="">
                    <input type="hidden" style="width: 100px;" name="invoiceRepoIds" value=" ">
                    <a href="javascript:void(0);" name="distBtn" id="distBtn"
                       data-value="${invoiceUseDetail.invoiceTypeCode!}">分发</a>
                <td class="mdtablethtd">
                    <input type="text" style="width: 100px;" id="ffremark" name="ffremark" value=" " class="layui-input">
                    <input type="hidden" style="width: 100px;" id="ffremark" name="ffremark" value=" "></td>
            </tr>
        </#list>
    </#if>
    </table>
    <table width="100%">
        <tr>
            <td></td>
            <td colspan="3">
                <input type="button" name="distSubmit" id="distSubmit" value="提交" class="layui-btn">
                <#--<a href="${ctx}/financed/invoiceLyList.php" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>-->
                <a href="${ctx}/financed/invoiceLyList.php?page=${(InvoiceLyRq.page)!}&applyUser=${(InvoiceLyRq.applyUser)!}&&issueStatus=${(InvoiceLyRq.issueStatus)!}&&examineStatus=${(InvoiceLyRq.examineStatus)!}&&startTime=${(InvoiceLyRq.startTime)!}&&endTime=${(InvoiceLyRq.endTime)!}"
                   style="text-decoration: none"class="layui-btn layui-btn-primary">返回</a>
            </td>
        </tr>
    </table>
</div>
</form>

<div class="main_modal container-fluid" id="modal-dialog" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">选择分发发票</h4>
            </div>
            <div class="modal-body">
                <iframe id="distlist"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <input type="hidden" style="width: 100px;" name="user" value="${(InvoiceLyRq.applyUser)!}">
                <input type="hidden" style="width: 100px;" name="isStatus" value="${(InvoiceLyRq.issueStatus)!}">
                <input type="hidden" style="width: 100px;" name="exStatus" value="${(InvoiceLyRq.examineStatus)!}">
                <input type="hidden" style="width: 100px;" name="startTime" value="${(InvoiceLyRq.startTime)!}">
                <input type="hidden" style="width: 100px;" name="endTime" value="${(InvoiceLyRq.endTime)!}">
                <input type="hidden" style="width: 100px;" name="page" value="${(InvoiceLyRq.page)!}">
                <input type="button" name="fpdistBtn" id="fpdistBtn" value="确定" class="layui-btn">
                <button class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/financed/invoiceLy.js" src="${ctx}/js/require.js"></script>
</body>
</html>