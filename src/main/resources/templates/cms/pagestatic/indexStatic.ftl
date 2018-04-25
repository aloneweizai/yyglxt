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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/colpick.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list">
    <div name="content_static"  class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button name="staticIndex" type="button"  class="layui-btn">
                            生成站点首页静态页
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/pagestatic/columnstatic.js" src="${ctx}/js/require.js"></script>
</html>