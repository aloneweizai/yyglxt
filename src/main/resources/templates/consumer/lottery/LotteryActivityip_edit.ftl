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
        input[type="text"] {
            width: 350px;
        }
    </style>
</head>

<body>

<div class="container-fluid m_top_30 nycon_list">
    <form id="linkForm">
        <input type="hidden" id="activityId" name="activityId" value="${activityId!}">
        <div class="nycon_list_b">
        <#if obj??>
            <input type="hidden" name="id" id="id" value="${obj.id!}">

        </#if>
            <table class="layui-table" lay-size="sm">
                <tr>
                    <td width="100px">ip地址：</td>
                    <td colspan="3">
                        <input type="text" name="ip" id="ip" value="${(obj.ip)!}" class="layui-input">
                    </td>
                </tr>
                <tr>
                    <td width="100px">描述：</td>
                    <td colspan="3">
                        <input type="text" name="description" id="description" value="${(obj.description)!}" class="layui-input">
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
<script data-main="${ctx}/js/abc/consumer/LotteryActivityip.js" src="${ctx}/js/require.js"></script>
</body>
</html>
