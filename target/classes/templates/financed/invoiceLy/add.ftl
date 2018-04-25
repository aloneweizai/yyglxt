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
<form name="invoiceLy" id="invoiceLy" action="" enctype="multipart/form-data" method="post"
      next-url="">
    <div class="container-fluid m_top_30 nycon_list sys_mod_add">
        <table class="table table-bordered">
            <caption>填写申请信息</caption>
        <#if invoiceLyRs??>
            <tr>
                <td width="120">申请人</td>
                <td colspan="3">
                    <input type="hidden" style="width: 100px;" name="id" value="${(invoiceLyRs.id)!}">
                    <input type="text" value="${(invoiceLyRs.applyUserName)!}" id="applyUserName"
                           data-rule="required;length[1~100]" class="layui-input" style="width:330px;" readonly="readonly">
                </td>
            </tr>
            <tr>
                <td width="120">备注</td>
                <td colspan="3">
                    <textarea style="width: 500px; height: 100px;" value="${(invoiceLyRs.remark)!}" id="remark"
                              name="remark">${(invoiceLyRs.remark)!}</textarea>
                </td>
            </tr>
        <#else>
            <tr>
                <td width="120">申请人</td>
                <td colspan="3">
                    <input type="text" name="applyUser" id="applyUser" value="${(user.nickname)!}"
                           data-rule="required;length[1~100]" class="layui-input" style="width:330px;" readonly="readonly">
                </td>
            </tr>
            <tr>
                <td width="120">备注</td>
                <td colspan="3">
                    <textarea style="width: 500px; height: 100px;" id="remark" name="remark"></textarea>
                </td>
            </tr>
        </#if>
        </table>
        <table class="layui-table mdtable" lay-size="sm" id="fptable">
            <caption>发票申请明细(按种类和本数填写)</caption>
            <tr>
                <th width="16%" class="mdtablethtd">发票种类<span style="color: red;">*</span></th>
                <th width="16%" class="mdtablethtd">库存数量(本)</th>
                <th width="17%" class="mdtablethtd">可用份数</th>
                <th width="17%" class="mdtablethtd">申请数量(本)<span style="color: red;">*</span></th>
                <th width="20%" class="mdtablethtd">申请备注</th>
                <th width="8%" class="mdtablethtd">操作</th>
            </tr>
        <#if invoiceLyRs??&&invoiceLyRs.invoiceUseDetailBOList?? && (invoiceLyRs.invoiceUseDetailBOList?size > 0) >
            <#list invoiceLyRs.invoiceUseDetailBOList as invoiceUseDetail>
                <tr did="${invoiceUseDetail_index}">
                    <td class="mdtablethtd">
                        <div style="color: red;" id="name_name"></div>
                        <#if invoiceLyRs??&&invoiceLyRs.examineStatus??&&invoiceLyRs.examineStatus == '2'>
                            <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                <#list invoicetype as invoice>
                                    <#if invoiceUseDetail.invoiceTypeCode ?? && (invoiceUseDetail.invoiceTypeCode == invoice.fieldValue)>
                                    ${invoice.fieldKey}<input type="hidden"  id="invoiceTypeCode${invoiceUseDetail_index}"
                                               style="width: 200px;" class="invoiceTypeCode" name="invoiceTypeCode"
                                               value="${invoice.fieldValue}">
                                    </#if>
                                </#list>
                            </#if>
                        <#else>
                            <select class="invoiceType" name="invoiceTypeCode" style="width:200px;height:30px;"
                                    id="invoiceTypeCode${invoiceUseDetail_index}" data-rule="required;" readonly="readonly">
                                <option value="">-- 请选择 --</option>
                                <#if invoicetype?? && ( invoicetype?size gt 0 )>
                                    <#list invoicetype as invoice>
                                        <option
                                            <#if invoiceUseDetail.invoiceTypeCode ?? && (invoiceUseDetail.invoiceTypeCode == invoice.fieldValue)>selected</#if>
                                            value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                                    </#list>
                                </#if>
                            </select>
                        </#if>
                    </td>
                    <td class="mdtablethtd">
                        <input type="text" readonly="readonly" id="repoNum${invoiceUseDetail_index}" class="layui-input repoNum"
                               name="repoNum" value="${(invoiceUseDetail.repoNum?c)!}"></td>
                    <td class="mdtablethtd">
                    <input type="text" readonly="readonly" id="usableShare0" name="usableShare" class="layui-input" <#if invoiceUseDetail.usableShare??> value="${(invoiceUseDetail.usableShare?c)!}"</#if>>
                    <input type="hidden" style="width: 100px;" name="usableShare" class="fpsize" value="${invoiceLyRs.invoiceUseDetailBOList?size}"></td>
                    <td class="mdtablethtd">
                        <input type="text" id="applyNum${invoiceUseDetail_index}" name="applyNum" class="layui-input applyNum"
                               data-rule="required;integer(+);length[0~4];" value="${(invoiceUseDetail.applyNum?c)!}"
                    </td>
                    <td class="mdtablethtd">
                        <input type="text" id="fpremark" class="layui-input" name="fpremark"
                            <#if invoiceUseDetail.remark??> value="${(invoiceUseDetail.remark)!}"
                            <#else>
                               value=" "
                            </#if>>
                    <#--<textarea  id="remark0" name="fpremark" value="${(invoiceUseDetail.remark)!}">${(invoiceUseDetail.remark)!}</textarea>-->
                        <input type="hidden" style="width: 100px;" name="fpremark" value="">
                        <input type="hidden" style="width: 100px;" name="invoiceTypeCode" value="-1">
                        <input type="hidden" style="width: 100px;" name="fpmxId" value="${(invoiceUseDetail.id)!}">
                        <input type="hidden" style="width: 100px;" name="fpmxId" value=" ">
                    </td>
                    <td class="mdtablethtd">
                        <input type="hidden" style="width: 100px;" name="repoNum" value="">
                        <input type="hidden" style="width: 100px;" name="applyNum" value="1">
                        <span name="jia" class="jia" value="${invoiceLyRs.invoiceUseDetailBOList?size}">+</span>
                        <span class="jian" name="jian" delid="">-</span>
                    </td>
                </tr>
            </#list>
        <#else>
            <tr did="0">
                <td class="mdtablethtd">
                    <select class="invoiceType" name="invoiceTypeCode" style="width:200px;height:30px;"
                            id="invoiceTypeCode0" data-rule="required;">
                        <option value="">-- 请选择 --</option>
                        <#if invoicetype?? && ( invoicetype?size gt 0 )>
                            <#list invoicetype as invoice>
                                <option value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
                <td class="mdtablethtd">
                    <input type="text" readonly="readonly" id="repoNum0" class="repoNum layui-input" style="width: 100px;"
                           name="repoNum" value="">
                </td>
                <td class="mdtablethtd">
                    <input type="text" readonly="readonly" id="usableShare0" style="width: 100px;" name="usableShare" class="layui-input">
                    <input type="hidden" style="width: 100px;" name="usableShare"class="fpsize" value="0"></td>
                <td class="mdtablethtd">
                    <input type="text" id="applyNum0" style="width: 100px;" value="" name="applyNum" class="applyNum layui-input"
                           data-rule="required;integer(+);length[0~4];">
                </td>
                <td class="mdtablethtd">
                    <input type="text" id="fpremark" name="fpremark" value=" " class="layui-input">
                <#--<textarea  id="remark0" name="fpremark" value=" "> </textarea>-->
                    <input type="hidden" style="width: 100px;" name="fpremark" value="">
                    <input type="hidden" style="width: 100px;" name="invoiceTypeCode" value="-1">
                    <input type="hidden" style="width: 100px;" name="fpmxId" value=" ">
                    <input type="hidden" style="width: 100px;" name="fpmxId" value=" ">
                </td>
                <td class="mdtablethtd">
                    <input type="hidden" style="width: 100px;" name="repoNum" value="">
                    <input type="hidden" style="width: 100px;" name="applyNum" value="1">
                    <span name="jia" class="jia" value="0">+</span>
                    <span class="jian" name="jian" delid="">-</span>
                </td>
            </tr>
        </#if>
        </table>

        <table width="100%">
            <tr>
                <td></td>
                <td colspan="3">
                    <input type="hidden" style="width: 100px;" name="user" value="${(InvoiceLyRq.applyUser)!}">
                    <input type="hidden" style="width: 100px;" name="issueStatus" value="${(InvoiceLyRq.issueStatus)!}">
                    <input type="hidden" style="width: 100px;" name="examineStatus"
                           value="${(InvoiceLyRq.examineStatus)!}">
                    <input type="hidden" style="width: 100px;" name="startTime" value="${(InvoiceLyRq.startTime)!}">
                    <input type="hidden" style="width: 100px;" name="endTime" value="${(InvoiceLyRq.endTime)!}">
                    <input type="hidden" style="width: 100px;" name="page" value="${(InvoiceLyRq.page)!}">
                <#if invoiceLyRs??&&invoiceLyRs.examineStatus??&&invoiceLyRs.examineStatus == '2'>
                    <input type="button"  name="commitBtn" id="commitBtn" value="提交审批" class="layui-btn commitBtn">
                    <a href="${ctx}/financed/invoiceLyList.php?page=${(InvoiceLyRq.page)!}&&applyUser=${(InvoiceLyRq.applyUser)!}&&issueStatus=${(InvoiceLyRq.issueStatus)!}&&examineStatus=${(InvoiceLyRq.examineStatus)!}&&startTime=${(InvoiceLyRq.startTime)!}&&endTime=${(InvoiceLyRq.endTime)!}"
                       style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                <#else>
                    <input type="button" name="editBtn" id="editBtn" value="保存草稿" class="layui-btn">
                    <input type="button"  name="commitBtn" id="commitBtn" value="提交审批" class="layui-btn commitBtn">
                    <a href="${ctx}/financed/invoiceLyList.php?page=${(InvoiceLyRq.page)!}&&applyUser=${(InvoiceLyRq.applyUser)!}&&issueStatus=${(InvoiceLyRq.issueStatus)!}&&examineStatus=${(InvoiceLyRq.examineStatus)!}&&startTime=${(InvoiceLyRq.startTime)!}&&endTime=${(InvoiceLyRq.endTime)!}"
                       style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                </#if>
                </td>
            </tr>
        </table>
    </div>
</form>

<script data-main="${ctx}/js/abc/financed/invoiceLy.js" src="${ctx}/js/require.js"></script>
</body>
</html>