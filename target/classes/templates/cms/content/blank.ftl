<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<input type="hidden" id="preUrl" value="${pre!}">
</body>
<script data-main="${ctx}/js/abc/cms/content/blank.js" src="${ctx}/js/require.js"></script>
</html>