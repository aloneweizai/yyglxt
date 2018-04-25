<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bangbang/help.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <style>
        a{
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
            <li data-href="${ctx}/cms/courseBrowseWatch/list_day.php" <#if initPageLink?contains("day")>class="noe"</#if>>课程浏览观看数(日期)</li>
            <li data-href="${ctx}/cms/courseBrowseWatch/list_month.php" <#if initPageLink?contains("month")>class="noe"</#if>>课程浏览观看数(月份)</li>
        </ul>
    </div>
</div>
<div class="js_body_div js_page_div nycon_list_b" style="padding-left: 15px;padding-right: 15px"></div>

<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="look_frame" style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button class="layui-btn layui-btn-primary lookModal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cms/courseBrowseWatch/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>


