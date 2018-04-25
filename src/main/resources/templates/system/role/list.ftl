<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <form action="${ctx}/system/operator/list.php" method="get">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/system/role/add.php" class="layui-btn layui-btn-normal fr">创建角色</a>
            </div>
        </div>
    </form>
    <form name="form_save" action="${ctx}/system/role/save.php" method="post">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="45">序号</th>
                    <th style="text-align: center">角色名称</th>
                    <th style="text-align: center">状态</th>
                    <th style="text-align: center">操作选项</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if roles?? && (roles?size > 0) >
                    <#list roles as role>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + role_index + 1}</td>
                        <td align="center">${(role.roleName)!}</td>
                        <td align="center" class="js-div-status">
                            <#if role.status?? && role.status>
                                <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                            </#if>
                        </td>
                        <td align="center">
                            <a data-href="${ctx}/system/role/view/${role.id}.php" class="js_edit pn-opt">查看</a> |
                            <a data-href="${ctx}/system/role/edit/${role.id}.php" class="js_edit pn-opt">编辑</a> |
                            <a data-href="${ctx}/system/role/status/${role.id}/${role.status?string("false","true")}.php" class="js_enable pn-opt">
                                <#if role.status>停用<#else>启用</#if>
                            </a>
                            <#if !role.status>
                                | <a data-href="${ctx}/system/role/delete/${role.id}.php" class="js_delete pn-opt">删除</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            ${pageHtml!}
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/system/role/list" src="${ctx}/js/require.js"></script>
</body>
</html>