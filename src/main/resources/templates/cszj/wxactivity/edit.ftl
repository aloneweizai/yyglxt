<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
    </style>
</head>
<body>
    <form name="redpack_edit" id="redpack_edit" <#--action="${ctx}/cszjs/repack/edit.php" next-url="${ctx}/cszjs/repackRs/list.php"--> method="post">
        <div class="container-fluid m_top_30 sys_mod_add">
        <table class="table">
            <#if repackRs??>
            <input type="hidden" id="id" name="id" value="${repackRs.id!}">
            <tr>
                <td width="200">创建时间：</td>
                <td colspan="3">
                    <input type="text" data-rule="required;" id="createTime" data-type="datetimebox" name="createTime" style=" width:150px;"
                           value="${repackRs.createTime!}">
                 </td>
                <td></td>
            </tr>
            <tr>
                <td width="200">用户OPENID：</td>
                <td colspan="3">
                    <input type="text" name="openId" id="openId" data-rule="required;" value="${repackRs.openId!}"  style=" width:330px;" ></td>
                </td>
                <td></td>
            </tr>
                <#else>
                <tr>
                    <td align="center">${message!}</td>
                </tr>
            </#if>
        </table>
        </div>
    </form>
</body>
<script data-main="${ctx}/js/abc/cszj/wxactivity.js" src="${ctx}/js/require.js"></script>
</html>