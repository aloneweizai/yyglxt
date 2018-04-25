<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
</head>
<style type="text/css">
    .tip_ico{
        display: inline-block;
        vertical-align: bottom;
        margin-left: 4px;
        cursor: pointer;
        background-image: url(${ctx}/images/sprites_ico.png?v=1.9.100);
        background-position: -242px -660px;
        width: 16px;
        height: 16px;
    }
</style>
<body>
<div class="container-fluid m_top_30 nycol_list_edit">
    <div class="layui-form-top">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">关键字</label>
                <div class="layui-input-inline">
                    <input type="text" id="keywords" value="${RequestParameters["keywords"]!}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button class="layui-btn js_query" data-href="${ctx}/cms/knowVote/ajaxList.php">搜索</button>
                </div>
            </div>
        </div>
    </div>
    <div class="qazhishiku">
        <div class="qaleft">
            <div>
                <ul id="cateTree" class="ztree"></ul>
            </div>
        </div>
        <div class="qaright">
            <div class="js_page_div"></div>
        </div>
    </div>

</div>

</body>
<script data-main="${ctx}/js/abc/cms/knowledge/vote/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</html>