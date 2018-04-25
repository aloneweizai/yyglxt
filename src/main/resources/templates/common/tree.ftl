<!doctype html>
<html>
<head>
    <title></title>
    <script type="text/javascript"><#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
</head>
<body>
    <div name="column_tree_layer" class="nycon_list_b">
        <div>
            <ul id="abc_tree" class="ztree"></ul>
        </div>
        <input value="确认" id="okSub" class="js_close btn btn-info">
    </div>
</body>
</html>