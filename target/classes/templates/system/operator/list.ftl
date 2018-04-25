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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
</head>

<body>
<div class="container-fluid m_top_30 nycol_list operator">
    <div class="row">
        <form action="${ctx}/system/operator/list.php" method="get" class="layui-form">
            <div class="layui-form-top">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户账户</label>
                        <div class="layui-input-inline">
                            <input type="text"  name="username" value="${RequestParameters["username"]!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">用户姓名</label>
                        <div class="layui-input-inline">
                            <input type="text"  name="nickname" value="${RequestParameters["nickname"]!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机号码</label>
                        <div class="layui-input-inline">
                            <input type="text"  name="phone" value="${RequestParameters["phone"]!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">用户状态</label>
                        <div class="layui-input-inline">
                            <select name="status">
                                <option value="">-请选择-</option>
                                <option value="true"  <#if RequestParameters["status"]?? && RequestParameters["status"] == 'true'>selected</#if> >启用</option>
                                <option value="false" <#if RequestParameters["status"]?? && RequestParameters["status"] == 'false' >selected</#if> >停用</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <#--<div class="layui-input-inline">-->
                            <button class="js-queryList-btn layui-btn">查询</button>
                        <#--</div>-->
                    </div>
                            <a href="${ctx}/system/operator/edit.php" class="layui-btn layui-btn-normal fr">创建用户</a>
                </div>
            </div>
        </form>
    </div>
    <div class="depart_l panel">
        <div id="treeDemo" class="ztree"></div>
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

<script data-main="${ctx}/js/abc/system/operator/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>