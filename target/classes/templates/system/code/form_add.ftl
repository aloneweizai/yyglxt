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
    <!--数据字典新增-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list_edit">
    <form name="codeForm" id="codeForm" method="post" class="layui-form">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="id" id="id">
            <tr>
                <td width="90">字典编码</td>
                <td colspan="2">
                    <input id="dictId" class="layui-input" name="dictId" size="30" type="text" style="width:300px;" required>
                    <span class="color_r2">*</span><!--必填时样式-->
                </td>
            </tr>
            <tr>
            <tr>
                <td>字典名称</td>
                <td colspan="2">
                    <input id="dictNames" class="layui-input" name="dictName" size="30" style="width:300px;" type="text">
                    <span class="color_r2">*</span><!--必填时样式-->
                </td>
            </tr>
            <!--非必填时样式-->
            <tr>
            <tr>
                <td>值名称</td>
                <td colspan="2">
                    <input id="fieldKey" class="layui-input" name="fieldKey" size="30" style="width:300px;" type="text">
                    <span class="color_r2">*</span><!--必填时样式-->
                </td>
            </tr>
            <tr>
                <td>值编码</td>
                <td colspan="2">
                    <input id="fieldValue" class="layui-input" name="fieldValue" size="30" type="text" style="width:300px;" >
                    <span class="color_r2">*</span><!--必填时样式-->
                </td>
            </tr>
            <tr>
                <td>排序</td>
                <td colspan="2">
                    <input id="sort" class= "layui-input iptxt" name="sort" size="30" type="text" style="width:300px;" >

                </td>
            </tr>
            <tr>
                <td>类型</td>
                <td colspan="2">
                    <input name="status" type="radio" value="false" title="停用">
                    <input name="status" type="radio" value="true" title="启用" checked>

                </td>
            <tr>
            <tr>
                <td>

                </td>
                <td colspan="2">
                    <input type="button" name="commitBtn" id="commitBtn" value="提交" class="js_save layui-btn">
                    <input type="button" name="resetBtn" id="resetBtn" value="返回" class="layui-btn layui-btn-primary">
                </td>
            </tr>
        </table>
    </form>

</div>
<script data-main="${ctx}/js/abc/system/code" src="${ctx}/js/require.js"></script>
</body>
</html>