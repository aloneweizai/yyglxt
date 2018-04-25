<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <style type="text/css">.ztree{border: none}</style>
</head>
<body>
<input type="hidden" class="js_select_tagType" value="${RequestParameters["tagType"]!}">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <form method="get" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input placeholder="请输入名称" type="text" id="keywords" value="${RequestParameters["keywords"]!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn js-query">查询</button>
                    </div>
                </div>
                <a href="${ctx}/cms/knowTag/edit.php" class="layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>
        <div class="depart_l panel">
            <div id="treeDemo" class="ztree"></div>
        </div>
    </form>

    <form>
        <div class="nycon_list_b nycon_list_b depart_r js_page_div">
        </div>
    </form>
</div>
<#if allTagTypeList?? && (allTagTypeList?size > 0) >
    <div hidden>
        <ul>
            <#list allTagTypeList as tagType>
                <li class="tagType_li" data-tagType="${tagType.fieldValue!}"  data-name="${tagType.fieldKey!}"></li>
            </#list>
        </ul>
    </div>
</#if>
<script data-main="${ctx}/js/abc/cms/knowledge/tag/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>