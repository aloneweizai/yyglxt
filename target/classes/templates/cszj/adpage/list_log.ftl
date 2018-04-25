<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="" method="post" id="consumerListForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>时间</th>
                    <th>广告名称</th>
                    <th>用户名</th>
                    <th>IP地址</th>

                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if adpagelogRs?? && ( adpagelogRs?size gt 0 )>
                    <#list adpagelogRs as adpagelog>
                    <tr>

                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+adpagelog_index+1}</td>
                        <td> ${(adpagelog.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td>${(adpagelog.adPageName)!}</td>
                        <td>${(adpagelog.userName)!}</td>
                        <td>${(adpagelog.ip)!}</td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">
    <input type="hidden" value="${ctx}/cszjs/adpage/list.php" id="currLink">
</div>

</body>
</html>