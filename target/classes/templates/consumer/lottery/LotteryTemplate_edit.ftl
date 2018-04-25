<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input[type="text"]{
            width: 350px;
        }

    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form  id="linkForm">
        <div class="nycon_list_b">
                    <#if obj??>
                   <input type="hidden" name="id" id="id" value="${obj.id!}">
             
               </#if>
                        <table class="layui-table" lay-size="sm">
<tr>
<td width="100px">名称：</td>
<td colspan="3">
<input type="text" name="name" id="name"   value="${(obj.name)!}">
</td>
</tr>
<tr>
<td width="100px">模版路径：</td>
<td colspan="3">
<input type="text" name="url" id="url"   value="${(obj.url)!}">
</td>
</tr>
<tr>
<td width="100px">模版类型：</td>
<td colspan="3">
<input type="text" name="types" id="types"   value="${(obj.types)!}">
</td>
</tr>
<tr>
<td width="100px">状态：</td>
<td colspan="3">
<label><input type="radio" name="status" id="status" checked value="true">启用</label>
    <label><input type="radio" name="status" id="status" <#if obj?? && !obj.status>checked</#if> value="false">停用</label>

</td>
</tr>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button" name="submit" id="submit" value="提交" class="layui-btn">
                        <input type="button" name="back" id="back" value="返回" class="layui-btn layui-btn-primary">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/LotteryTemplate.js" src="${ctx}/js/require.js"></script>
</body>
</html>
