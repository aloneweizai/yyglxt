<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
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
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
<#--
<table class="table  table-hover">
  <tr style="background:#f5f5f5">
    <td height="30">&nbsp;当前位置：&nbsp;
      <a href="${ctx}/financed/invoiceRepoList.php">
        <u>发票仓库管理</u>
      </a> &gt;&gt; 添加发票仓库</td>
  </tr>
</table>
-->
    <form name="consumer_edit" action="${ctx}/financed/invoiceRepoSave.php"
          next-url="${ctx}/financed/invoiceRepoList.php?noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
          method="post">
        <table class="table">
            <tr>
                <td width="120">发票种类</td>
                <td colspan="3">
                    <select class="invoiceType" name="invoiceTypeCode" style="width:200px;height:30px;"
                            data-rule="required;">
                        <option value="">-- 请选择 --</option>
                    <#if invoicetype?? && ( invoicetype?size gt 0 )>
                        <#list invoicetype as invoice>
                            <option value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                        </#list>
                    </#if>
                    </select><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td>发票性质：</td>
                <td colspan="3">
                    <span><input name="property" type="radio" value="1" checked="checked">纸质发票</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span><input name="property" type="radio" value="2">电子发票</span>
                </td>
            </tr>
            <tr>
                <td width="120">发票编号</td>
                <td colspan="3"><input type="text" name="id" id="id" data-rule="length[1~100]" style="width:120px;"
                                       readonly="readonly">
                    <input type="text" name="idStart" id="idStart" data-rule="required;length[1~9]" style="width:100px;"
                          ><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">发票代码</td>
                <td colspan="3"><input type="text" name="invoiceCode" id="invoiceCode"
                                       data-rule="required;allint;length[10~12]" style=" width:200px;"><label
                        style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">发票号码起</td>
                <td colspan="3">
                    <input type="text" name="invoiceNoStart" class="share" id="invoiceNoStart"
                           data-rule="发票开始号码:required;allint;length[8];" style=" width:200px;"><label
                        style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120">发票号码止</td>
                <td colspan="3">
                    <input type="text" class="share" name="invoiceNoEnd" id="invoiceNoEnd"
                           data-rule="发票结束号码:required;allint;length[8];match(gte, invoiceNoStart);tests(#invoiceNoStart)"
                           style=" width:200px;"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120">每本份数</td>
                <td colspan="3"><input type="text" name="share" id="share" data-rule="required;length[1~100]"
                                       style="width:200px;" readonly="readonly"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">本数</td>
                <td colspan="3"><input type="text" name="book" id="book" readonly="readonly" style="width:200px;"
                                       value="1"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">备注</td>
                <td colspan="3">
                    <textarea style="width: 500px; height: 100px;" value="" id="remark" name="remark"></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <input type="hidden" style="width: 100px;" name="noStart" value="${(InvoiceRepoRq.noStart)!}">
                    <input type="hidden" style="width: 100px;" name="noEnd" value="${(InvoiceRepoRq.noEnd)!}">
                    <input type="hidden" style="width: 100px;" name="code" value="${(InvoiceRepoRq.invoiceCode)!}">
                    <input type="hidden" style="width: 100px;" name="typeCode" value="${(InvoiceRepoRq.invoiceTypeCode)!}">
                    <input type="hidden" style="width: 100px;" name="status" value="${(InvoiceRepoRq.status)!}">
                    <input type="hidden" style="width: 100px;" name="invoiceStart" value="${(InvoiceLyRq.invoiceNoStart)!}">
                    <input type="hidden" style="width: 100px;" name="invoiceEnd" value="${(InvoiceLyRq.invoiceNoEnd)!}">
                    <input type="hidden" style="width: 100px;" name="startTime" value="${(InvoiceLyRq.startTime)!}">
                    <input type="hidden" style="width: 100px;" name="endTime" value="${(InvoiceLyRq.endTime)!}">
                    <input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
                    <a href="${ctx}/financed/invoiceRepoList.php?page=${(InvoiceRepoRq.page)!}&noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
                       style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                </td>
            </tr>
        </table>
    </form>
</div>


</body>
<script data-main="${ctx}/js/abc/financed/invoicerepo.js" src="${ctx}/js/require.js"></script>
</html>