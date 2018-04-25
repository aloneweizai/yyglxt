<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var initStatus = "${status!}";
        var initModule = "${initModule!}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bangbang/help.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <style>
        a{
            color: #337ab7;
            text-decoration: none;
            cursor: pointer;
        }
        a:hover,a:focus {
            color: #23527c;
        }
    </style>
</head>
<body>
<div id="problem-box"  class="room-nav" style="position: static">
    <div  class=" title1 room-subnav js_tab">
        <ul>
            <li data-href="${ctx}/bangbang/contentAudit/system/list.php" <#if initModule =='system'>class="noe"</#if> >内容合规审查</li>
            <li  data-href="${ctx}/bangbang/contentAudit/tipOff/list.php" <#if initModule =='tipOff'>class="noe"</#if>>举报内容审查</li>
            <li  data-href="${ctx}/bangbang/contentAudit/user/list.php">用户合规审查</li>
            <li  data-href="${ctx}/bangbang/contentAudit/ip/list.php">IP 合规审查</li>
        </ul>
    </div>
</div>
<div class="js_body_div js_page_div nycon_list_b" style="padding-left: 15px;padding-right: 15px"></div>
<script data-main="${ctx}/js/abc/bangbang/contentAudit/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>


