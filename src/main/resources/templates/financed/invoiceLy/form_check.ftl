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
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > td {
            text-align: left;
        }

        .mdtablethtd {
            border-bottom: 1px solid #ddd;
            padding: 5px;
        }

        .mdtablethtd input {
            border: 1;
        }

        .mdtablethtd .jia {
            background: #2e9ad0;
            color: #fff;
            width: 22px;
            line-height: 22px;
            margin-right: 5px;
            text-align: center;
            display: inline-block;
            cursor: pointer
        }

        .mdtablethtd .jian {
            background: #d87271;
            color: #fff;
            width: 22px;
            line-height: 22px;
            margin-right: 5px;
            text-align: center;
            display: inline-block;
            cursor: pointer
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <div>
    <form id="invoiceLy_check">
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
                <textarea style="width: 500px; height: 100px;" value="${(invoiceLyRs.remark)!}" readonly="readonly"
                          name="remark">${(invoiceLyRs.remark)!}</textarea>
            </td>
        </tr>
    </#if>
    </table>
        <table class="layui-table" lay-size="sm">
        <caption>发票申请明细(按种类和本数填写)</caption>
        <tr>
            <th width="16%" class="mdtablethtd">发票种类<span style="color: red;">*</span></th>
            <th width="16%" class="mdtablethtd">库存数量(本) <span style="color: red;">*</span></th>
            <th width="17%" class="mdtablethtd">可用份数 <span style="color: red;">*</span></th>
            <th width="17%" class="mdtablethtd">申请数量(本)<span style="color: red;">*</span></th>
            <th width="17%" class="mdtablethtd">审批数量(本)<span style="color: red;">*</span></th>
            <th width="20%" class="mdtablethtd">申请备注</th>
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
                <td class="mdtablethtd">${(invoiceUseDetail.usableShare?c)!}</td>
                <td class="mdtablethtd">
                    ${(invoiceUseDetail.applyNum?c)!}
                </td>
                <td class="mdtablethtd">
                    <input type="hidden" style="width: 100px;" name="fpmxId" value="${(invoiceUseDetail.id)!}">
                    <input type="hidden" style="width: 100px;" name="fpmxId" value=" ">
                    <input type="hidden" style="width: 100px;" name="useId" value="${(invoiceUseDetail.useId)!}">
                <input type="hidden" style="width: 100px;" name="useId" value=" ">
                    <input type="text" style="width: 100px;" name="checkBook" value="${(invoiceUseDetail.applyNum?c)!}" readonly="readonly">
                    <input type="hidden" style="width: 100px;" name="checkBook" value=" "></td>
                <td class="mdtablethtd">${(invoiceUseDetail.remark)!}</td>
            </tr>
        </#list>
    </#if>
    </table>
    <table class="table table-bordered">
        <caption>发票领用申请审批</caption>
        <tr>
            <td>审批结果：</td>
            <td colspan="2"><span><input name="examineStatus" type="radio" value="1" checked="checked">审批通过</span>
                <span style="width:20px"></span>
                <span><input name="examineStatus" type="radio" value="2">审批不通过</span>
            </td>
        </tr>
        <tr>
            <td>审批意见：</td>
            <td colspan="2">
                <textarea style="width: 500px; height: 100px;" id="remark" name="checkOpinion">同意</textarea>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td></td>
            <td colspan="3">
                <input type="hidden" style="width: 100px;" name="user" value="${(InvoiceLyRq.applyUser)!}">
                <input type="hidden" style="width: 100px;" name="isStatus" value="${(InvoiceLyRq.issueStatus)!}">
                <input type="hidden" style="width: 100px;" name="exStatus" value="${(InvoiceLyRq.examineStatus)!}">
                <input type="hidden" style="width: 100px;" name="startTime" value="${(InvoiceLyRq.startTime)!}">
                <input type="hidden" style="width: 100px;" name="endTime" value="${(InvoiceLyRq.endTime)!}">
                <input type="hidden" style="width: 100px;" name="page" value="${(InvoiceLyRq.page)!}">
                <input type="button" name="checkSubmit" id="checkSubmit" value="提交审批" class="layui-btn">
                <a href="${ctx}/financed/invoiceLyList.php?page=${(InvoiceLyRq.page)!}&applyUser=${(InvoiceLyRq.applyUser)!}&&issueStatus=${(InvoiceLyRq.issueStatus)!}&&examineStatus=${(InvoiceLyRq.examineStatus)!}&&startTime=${(InvoiceLyRq.startTime)!}&&endTime=${(InvoiceLyRq.endTime)!}"
                   style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
            </td>
        </tr>
    </table>
    </form>
    </div>
</div>

<script data-main="${ctx}/js/abc/financed/invoiceLy.js" src="${ctx}/js/require.js"></script>
</body>
</html>