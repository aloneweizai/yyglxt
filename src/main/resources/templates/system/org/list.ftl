<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css" >
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
</head>
<body>

<input type="hidden" class="js_select_parent" data-parentId="" data-parentName="">
<div class="container-fluid m_top_30 nycol_list">
    <div class="row">
        <form method="post" class="layui-form">
            <div class="layui-form-top">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">机构名称</label>
                        <div class="layui-input-inline">
                            <input name="orgName" type="text" value="${RequestParameters["name"]!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">机构类型</label>
                        <div class="layui-input-inline">
                            <select name="orgType" class="cxinput">
                                <option value=""></option>
                            <#list orgTypes as orgType>
                                <option value="${orgType.code}" <#if RequestParameters["type"]?? && RequestParameters["type"] == orgType.code>selected</#if> >${orgType.description}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline" style="width:180px;">
                            <button class="js-queryList-btn layui-btn">查询</button>
                        </div>
                    </div>
                    <a href="javascript:void(0);" data-href="${ctx}/system/org/edit.php" class="js_add_org layui-btn layui-btn-normal fr">创建机构</a>
                </div>
             </div>
        </form>
    </div>
    <div class="depart_l panel ">
        <div id="tree_org_demo" class="ztree"></div>
    </div>
    <form action="" method="get">
        <div class="nycon_list_b depart_r js_page_div">
        </div>
    </form>
</div>
<div class="clear"></div>

<#if orgAll?? && (orgAll?size > 0) >
    <div hidden>
        <ul>
            <#list orgAll as org>
                <li class="org_li" data-id="${org.id!}" data-pid="${org.parentId!}" data-name="${org.name!}"></li>
            </#list>
        </ul>
    </div>
</#if>
<script data-main="${ctx}/js/abc/system/org/list.js?${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>