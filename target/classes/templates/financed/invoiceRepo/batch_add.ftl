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
    <form name="consumer_edit" action="${ctx}/financed/invoiceRepoSave.php"
          next-url="${ctx}/financed/invoiceRepoList.php?noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
          method="post">
        <table class="table">
            <tr>
                <td width="120">发票种类</td>
                <td colspan="3">
                    <select class="invoiceType" name="invoiceTypeCode" style="width:200px;height:30px;" id="invoiceTypeCode" data-rule="required;">
                        <option value="">-- 请选择 --</option>
                    <#if invoicetype?? && ( invoicetype?size gt 0 )>
                        <#list invoicetype as invoice>
                            <option value="${invoice.fieldValue}">${invoice.fieldKey}</option>
                        </#list>
                    </#if>
                    </select><label style='color:red;'>*</label>
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
                <td colspan="3"><input type="text" name="id" id="id" data-rule="length[1~20]"
                                       style="width:120px;" readonly="readonly">
                    <input type="text" name="idStart" id="idStart" data-rule="发票编号起:length[1~9]"
                           style="width:100px;">--
                    <input type="text" name="idEnd" id="idEnd" class="book" data-rule="发票编号止:required;match(gte, idStart);length[1~9];"
                           style="width:100px;"><label style='color:red;'>*</label>
                    <label id="tips" style='color:red;' hidden>发票编号止长度必须与发票编号起长度一致! </label>
                    <label id="booktips1" style='color:red;' hidden>根据发票编号起/止计算出本数与发票号码起/止计算出的本数不相等,请重新输入!</label></td>
            </tr>
            <tr>
                <td width="120">发票代码</td>
                <td colspan="3"><input type="text" name="invoiceCode" id="invoiceCode" data-rule="required;allint;length[10~12]"
                                       style=" width:200px;"><label style='color:red;'>*</label>
                    <span style="font-size:12px; color: #999;">发票代码由10-12个数字组成 </span>
                </td>
            </tr>
            <tr>
                <td width="120">发票号码起</td>
                <td colspan="3">
                    <input type="text" name="invoiceNoStart" id="invoiceNoStart" class="book" data-rule="发票开始号码:required;allint;length[8]"
                           style=" width:200px;"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120">发票号码止</td>
                <td colspan="3">
                    <input type="text" name="invoiceNoEnd" id="invoiceNoEnd" class="book"
                           data-rule="发票结束号码:required;allint;length[8];match(gte, invoiceNoStart);tests(#invoiceNoStart)" style=" width:200px;"><label
                        style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120">每本份数</td>
                <td colspan="3"><input type="text" class="book" name="share" id="share" data-rule="required;length[1~100]"
                                       style="width:200px;"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">本数</td>
                <td colspan="3"><input type="text" name="book" id="book"
                                       style="width:200px;" readonly="readonly"><label style='color:red;'>*</label>
                    <label id="booktips" style='color:red;' hidden>根据发票号码起、止及每本发票份数计算得不到完整本数,请重新输入</label>
                    </td>
            </tr>
            <tr id="tjBtn">
                <td></td>
                <td colspan="3"><input type="button" name="button" id="submit" value="提交" class="layui-btn">
                    <a href="${ctx}/financed/invoiceRepoList.php?page=${(InvoiceRepoRq.page)!}&noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
                       style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                </td>
            </tr>
        </table>
<#--<div  id="plfpmx" hidden>
        <table width="100%" class="table table-hover" align="center" border=0>
            <tr>
                <table  width="100%"  border=1 class="table table-hover">
                    <caption>发票信息</caption>
                    <tr style="font-weight:bold;">
                        <th width="4%"align="center">序号</th>
                        <th width="20%"align="center">发票编号</th>
                        <th width="20%"align="center">发票种类</th>
                        <th width="16%"align="center">发票代码</th>
                        <th width="15%"align="center">发票号码起</th>
                        <th width="15%"align="center">发票号码止</th>
                        <th width="5%"align="center">份数</th>
                        <th width="5%"align="center">本数</th>
                        <th></th>
                    </tr>
                </table>
            </tr>
            <tr>
                <td align="center" width="100%">
                    <div  style="overflow-y:auto; width:100%;height:390px">
                        <table width="100%" border=1 class="table table-hover">
                            <tbody id="fpmxbody"></tbody>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <table  width="100%"  border=1 class="table table-hover">
                    <tr style="font-weight:bold;">
                        <td></td>
                        <td colspan="3">
                            <input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
                            <a href="${ctx}/financed/invoiceRepoList.php?noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
                               style="text-decoraclass="layui-btn layui-btn-primary"btn-default">返回</a>
                        </td>
                    </tr>
                </table>
            </tr>
        </table>
</div>-->
        <div id="plfpmx" hidden>
        <table class="layui-table" lay-size="sm" >
            <caption>发票信息</caption>
            <tr style="font-weight:bold;">
                <th width="2%">序号</th>
                <th>发票编号</th>
                <th>发票种类</th>
                <th>发票代码</th>
                <th>发票号码起</th>
                <th>发票号码止</th>
                <th>份数</th>
                <th>本数</th>
            </tr>
            <tbody id="fpmxbody"></tbody>
        </table>
            <table class="yy_fanye" style="width: 100%">
                <tbody>
            <tr>
                <td align="center">
                    共&nbsp;<span id="totalItems"></span>&nbsp;条&nbsp;&nbsp;
                    每页<input maxlength="2" style="width:40px" name="size" value="10" id="consumer_size" type="text" readonly="readonly">条&nbsp;&nbsp;
                    <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                    <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                    <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                    <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                    当前&nbsp;<span id="page">1</span>/<span id="totalPage"></span>&nbsp;页&nbsp;&nbsp;转到第
                    <input style="width:50px" onfocus="this.select();" id="consumer_go" value="" type="text"> 页
                    <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                    <input type="hidden" name="page" id="cupageVal" value="1">
                    <input type="hidden" name="tpage" id="topageVal" value="">
                </td>
            </tr>
                </tbody>
        </table>
        <div class="tijiaofanhui">
                <input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
                <a href="${ctx}/financed/invoiceRepoList.php?noStart=${(InvoiceRepoRq.noStart)!}&&noEnd=${(InvoiceRepoRq.noEnd)!}&&invoiceCode=${(InvoiceRepoRq.invoiceCode)!}&&invoiceTypeCode=${(InvoiceRepoRq.invoiceTypeCode)!}&&status=${(InvoiceRepoRq.status)!}&&invoiceNoStart=${(InvoiceRepoRq.invoiceNoStart)!}&&invoiceNoEnd=${(InvoiceRepoRq.invoiceNoEnd)!}&&startTime=${(InvoiceRepoRq.startTime)!}&&endTime=${(InvoiceRepoRq.endTime)!}"
                   style=class="layui-btn layui-btn-primary" class="btn btn-default">返回</a>
        </div>
        </div>
    </form>
</div>


</body>
<script data-main="${ctx}/js/abc/financed/invoicerepo.js" src="${ctx}/js/require.js"></script>
</html>