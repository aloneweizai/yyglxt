<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/course.css">
    <style>.js-body-tr a{color: #337ab7;}</style>
</head>
<body>
<div class="room-nav" style="position: static">
    <div class="room-subnav js_tab">
        <a href="javascript:;" data-href="${ctx}/bangbang/rewardPointSetting/faction/list.php" class="active">帮派奖励</a>
        <a href="javascript:;" data-href="${ctx}/bangbang/rewardPointSetting/factionMember/list.php?status=1">帮派成员奖励</a>
    </div>
</div>
<div class="js_body_div nycon_list_b" style="padding-left: 15px;padding-right: 15px"></div>
<script data-main="${ctx}/js/abc/bangbang/rewardsPointSetting/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>


