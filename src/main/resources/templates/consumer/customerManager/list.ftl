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
                <a href="${ctx}/consumerManager/customerManager/edit.php" class="layui-btn fr">创建客户经理</a>
            </div>
        </div>
    </form>
    <form name="form_save" action="" method="post">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="45">序号</th>
                    <th style="text-align: center">员工工号</th>
                    <th style="text-align: center">姓名</th>
                    <th style="text-align: center">联系电话</th>
                    <th style="text-align: center">状态</th>
                    <th style="text-align: center">创建时间</th>
                    <th style="text-align: center">操作选项</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if managers?? && (managers?size > 0) >
                    <#list managers as manager>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + manager_index + 1}</td>
                        <td align="center">${(manager.userId)!}</td>
                        <td align="center">${(manager.username)!}</td>
                        <td align="center">${(manager.phone)!}</td>
                        <td align="center">
                            <#if manager.status?? && manager.status ==1>
                                <div class="btn btn-success btn-xs btn_nocursor">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs btn_nocursor">无效</div>
                            </#if>
                        </td>
                        <td align="center">${manager.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td align="center">
                            <#if manager.status ==1>
                                <a data-href="${ctx}/consumerManager/customerManager/view.php?id=${manager.id}" class="js_view pn-opt">查看</a> |
                            <#else>
                                <a data-href="${ctx}/consumerManager/customerManager/edit.php?id=${manager.id}" class="js_edit pn-opt">编辑</a> |
                            </#if>
                            <a data-href="${ctx}/consumerManager/customerManager/status/${manager.id}/${(manager.status==1)?string("0","1")}.php" class="js_enable pn-opt">
                                <#if manager.status ==1>停用<#else>启用</#if>
                            </a>
                            <#if manager.status == 0>
                                | <a data-href="${ctx}/consumerManager/customerManager/delete/${manager.id}.php" class="js_delete pn-opt">删除</a>
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
<script data-main="${ctx}/js/abc/consumer/customerManager.js?v=1" src="${ctx}/js/require.js"></script>
</body>
</html>