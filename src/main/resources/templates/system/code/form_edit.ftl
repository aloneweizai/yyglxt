<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--数据字典修改-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list_edit">
    <form name="codeForm" id="codeForm" method="post">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="id" id="id" value="${dict.id}">
            <tr>
                <td width="90">字典编码</td>
                <td colspan="2">
                    <input id="dictId" class="layui-input" name="dictId" size="30" value="${dict.dictId}" type="text"
                           required>
                    <span class="color_r2">*</span><!--必填时样式-->
                </td>
            </tr>
            <tr>
            <tr>
                <td>字典名称</td>
                <td colspan="2"><input id="dictNames" class="layui-input" name="dictName" size="30" value="${dict.dictName}"
                                       type="text"></td>
            </tr>
            <!--非必填时样式-->
            <tr>
            <tr>
                <td>值名称</td>
                <td colspan="2"><input id="fieldKey" class="layui-input" name="fieldKey" size="30" value="${dict.fieldKey}"
                                       type="text"></td>
            </tr>
            <tr>
            <tr>
                <td>值编码</td>
                <td colspan="2"><input id="fieldValue" class="layui-input" name="fieldValue" size="30"
                                       value="${dict.fieldValue}" type="text">
                </td>
            </tr>
            <tr>
                <td>排序</td>
                <td colspan="2"><input id="sort" class="layui-input" name="sort" size="30"
                                       value="${dict.sort!}" type="text">
                </td>
            </tr>
            <tr>
                <td>类型</td>
                <td width="150">
                    <input name="status" type="radio" value="false"
                           <#if dict??><#if !dict.status>checked</#if><#else>checked</#if>>
                    停用
                </td>
                <td>
                    <input name="status" type="radio" value="true" <#if dict?? && dict.status>checked</#if>>
                    启用
                </td>
            <tr>
            <tr>
                <td>

                </td>
                <td colspan="2">
                    <input type="button" name="commitBtn" id="commitBtn" value="提交" class="js_save btn btn-info">
                    <input type="button" name="resetBtn" id="resetBtn" value="返回" class="layui-btn layui-btn-primary">
                </td>
            </tr>
        </table>
    </form>

</div>
<script data-main="${ctx}/js/abc/system/code" src="${ctx}/js/require.js"></script>
</body>
</html>