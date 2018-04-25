<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/colpick.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/zoom.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list_edit">

    <#--<table class="table  table-hover">-->
        <#--<tr>-->
            <#--<td height="30">&nbsp;当前位置：&nbsp;<a href=""><u>栏目</u></a> &gt;&gt; 添加栏目</td>-->
        <#--</tr>-->
    <#--</table>-->
    <form name="model_add_form" action="" enctype="multipart/form-data" method="post"
          data-validator-option="{theme:'yellow_right', timely:1}">
        <input type="hidden" name="channelId" value="${channelId!}" abc-custom="0">
        <input type="hidden" name="operateType" value="${(channelId??)?string("1","0")}" abc-custom="0">
        <input type="hidden" name="tmplType" value="${tmplType!}">
    <#include "model_tmpl.ftl">

        <table class="layui-table" lay-size="sm">
        <#list modelItemList as modelItem>
            <@model_tmpl_macro modelItem />
        </#list>

            <tr>
                <td>

                </td>
                <td><input type="button" name="model_submit_btn" value="提交" class="layui-btn">
                    <input type="button" name="model_reset_btn" value="返回" class="layui-btn layui-btn-primary">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/column/add.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>

<script type="text/html" id="column_template_list_tmpl">
    <option value="">请选择</option>
    {{each list value i}}
    <option value="{{value.templatePath}}">{{value.templateName}}</option>
    {{/each}}
</script>
</html>