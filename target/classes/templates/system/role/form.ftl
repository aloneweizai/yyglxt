<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <br>
    <form name="form_add"  method="post">
        <div class="character">
            <div class="character_r_top">角色名称:
                <input name="roleName" type="text" value="${(role.roleName)!}" class="layui-input" style="width: 250px">
                <input name="menuIds" type="hidden" value="${(role.menuIds)!}">
                <input type="hidden" name="id" value="${(role.id)!}">
            </div>
            <div class="character_add" style="float: left">
                <div class="character_r_b">系统权限
                    <ul id="treeDemo" class="ztree" style="width: 400;height: 660"></ul>
                </div>
                <div class="character_r_add_btn" style="margin-top: 15px;">
                    <button name="button"  class="js_save_menu_btn layui-btn" date-href="${ctx}/system/role/menu/save.php">提交</button>
                </div>
            </div>
            <div class="character_add" style="margin-left: 450px">
                <div class="character_r_b">机构用户
                    <ul id="org_operator_tree" class="ztree" style="width: 440;height: 660"></ul>
                </div>
                <div class="character_r_add_btn" style="margin-top: 15px;">
                    <button name="button"  class="js_save_operator_btn layui-btn" date-href="${ctx}/system/role/operator/save.php">提交</button>
                    <a href="javascript:;" class="layui-btn layui-btn-primary js_back">返回</a>
                </div>
            </div>
        </div>
    </form>
</div>
<#if menus?? && (menus?size > 0)>
<div hidden>
    <ul>
        <#list menus as menu>
            <li class="menu_li" data-menu-id="${menu.menuId!}" data-menu-pid="${menu.parentId!}" data-menu-name="${menu.menuName!}"></li>
        </#list>
    </ul>
    <ul>
        <#list orgAll as org>
            <li class="org_li" data-org-id="${org.id!}" data-org-pid="${org.parentId!}" data-org-name="${org.name!}"></li>
        </#list>
    </ul>
    <ul>
        <#list users as user>
            <li class="operator_li" data-user-id="${user.id!}" data-user-orgId="${user.orgId!}" data-user-name="${user.nickname!}"></li>
        </#list>
    </ul>
    <ul>
        <#if roleUser?? && roleUser.adminList?? && (roleUser.adminList?size>0)>
            <#list roleUser.adminList as user>
                <li class="js_select_userId" data-select-userId="${user.id!}"></li>
            </#list>
        </#if>
    </ul>
</div>
</#if>
<script data-main="${ctx}/js/abc/system/role/form.js?v=2" src="${ctx}/js/require.js"></script>
</body>
</html>