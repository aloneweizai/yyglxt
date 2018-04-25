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
    <form action="" method="post">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/content/site/addPage.php" class="layui-btn fr">创建站点</a>
            </div>
        </div>
    </form>

    <form name="_site_form" action="" method="post">
        <div class=" nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>站点名称</th>
                    <th>站点路径</th>
                    <th>版权说明</th>
                    <th>备案号</th>
                    <th>静态发布</th>
                    <th>站点本地化</th>
                    <th>站点状态</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#list cmsSiteList as cmsSite>
                    <tr>
                        <td>${cmsSite_index+1}</td>
                        <td>${cmsSite.siteName!}</td>
                        <td>${cmsSite.sitePath!}</td>
                        <td>${cmsSite.copyrightExplain!}</td>
                        <td>${cmsSite.recordNum!}</td>
                        <td>${(cmsSite.staticIssue=='1')?string('是','否')}</td>
                        <td>${cmsSite.localeFront!}</td>
                        <#--<td style="color:${(cmsSite.siteStatus=='1')?string('#397b3e','#a94141')}">${(cmsSite.siteStatus=='1')?string('启用','停用')}</td>-->
                        <td>
                            <#if (cmsSite.siteStatus!)=='1'>
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                        </td>



                        <td>
                            <a data-siteid="${cmsSite.siteId!}" name="activeOrInactiveSite" href="javascript:void(0);">${(cmsSite.siteStatus=='1')?string('停用','启用')}</a> |
                            <a href="${ctx}/content/site/addPage.php?siteId=${cmsSite.siteId!}">编辑</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </form>

    <#include "../../common/pagination.ftl">
</div>
<script type="text/javascript" data-main="${ctx}/js/abc/cms/siteManage/list.js" src="${ctx}/js/require.js"></script>
</body>
</html>