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
<div class="container-fluid m_top_30 nycol_list">
    <form action=" " method="post">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="nycon_l_t_btn text-right">
                    <button type="button" class="layui-btn layui-btn-normal fr">创建栏目</button>
                    <div class="nycon_btn_b" style="border: 1px solid #ddd">
                        <ul>
                            <#list modelList as model>
                                <li><a href="${ctx}/content/column/addPage.php?modelId=${model.modelId!}"> <span
                                        class="glyphicon glyphicon-menu-right"></span>${model.modelName!} </a></li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <#include "list_tmpl.ftl">

    </form>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/column/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</html>