<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var curriculumId = "${curriculumId}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/course.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
</head>
<body>
<div class="room-nav" style="position: static">
    <div class="room-subnav js_tab">
        <a href="javascript:;" data-href="${ctx}/cms/course/statistics/study/${curriculumId!}.php" class="active">课程学习情况</a>
        <a href="javascript:;" data-href="${ctx}/cms/course/statistics/sign/${curriculumId!}.php">报名签到情况</a>
        <a href="javascript:;" data-href="${ctx}/cms/course/statistics/order/${curriculumId!}.php">课程订购情况</a>
        <span style="float: right;margin-right: 10px">
            <a href="${RequestParameters["currLink"]!}"class="btn btn_middle btn_blue btn_start"><i></i>返回课程列表</a>
        </span>
    </div>
</div>
<div class="js_body_div js_page_div nycon_list_b" style="padding-left: 15px;padding-right: 15px">
</div>

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
<script data-main="${ctx}/js/abc/cms/course/statistics/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>


