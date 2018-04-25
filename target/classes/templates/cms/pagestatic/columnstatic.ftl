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
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list">
    <div name="content_static" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">栏目</label>
                    <div class="layui-input-inline" style="width: 400px;" name="columnSel">
                        <input type="hidden"  name="columnId" id="columnId" value="">
                        <input type="text" readonly="readonly" name="columnName" placeholder="请选择栏目"  data-rule="required;" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button name="_channel_btn" type="button"  class="layui-btn">
                            选择
                        </button>
                    </div>
                </div>
                <button type="button" name="staticColumn"  class="layui-btn layui-btn-normal fr">生成栏目静态页</button>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/pagestatic/columnstatic.js" src="${ctx}/js/require.js"></script>
</html>