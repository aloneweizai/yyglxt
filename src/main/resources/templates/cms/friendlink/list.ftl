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
    <!-- 友情链接列表-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list">
    <form action=" " method="post" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/cms/friendlink/add.php" class="layui-btn fr">创建友情链接</a>
            </div>
        </div>


    <div class=" nycon_list_b">
        <table class="layui-table" lay-size="sm">
            <thead class="pn-lthead">
            <tr>
                <th width="28">序号</th>
                <th width="28">全选</th>
                <th>网站名称</th>
                <th>网站LOGO</th>
                <th>点击次数</th>
                <th>排列顺序</th>
                <th>显示</th>
                <th width="128">操作选项</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <#if links.dataList?? && (links.dataList?size > 0) >
                <#list links.dataList as link>
                <tr>
                    <td>${link_index+1+(pagination.page-1)*pagination.size}</td>
                    <td>
                        <input name="ids" type="checkbox" lay-skin="primary"  value="${link.friendlinkId}">
                    </td>
                    <td>
                        <#if link.siteName?exists>
                            ${link.siteName}
                        </#if>
                    </td>
                    <td>
                        <#if link.logoPath!="">
                            <img src="${link.logoPath}" style="height: 80px;">
                        <#else>
                            <img src="${ctx}/images/zanwu.png" style="height: 50;">
                        </#if>
                    </td>
                    <td>
                        <#if link.views?exists>
                            ${link.views}
                        </#if></td>
                    <td>
                        <#if link.priority?exists>
                            ${link.priority}
                        </#if></td>
                    <td>
                        <#if link.isEnabled?? && link.isEnabled=='1'>
                            <div class="btn btn-success btn-xs ">是</div>
                        <#else>
                            <div class="btn btn-danger btn-xs ">否</div>
                        </#if>
                    </td>
                    <td>
                        <a href="${ctx}/cms/friendlink/${link.friendlinkId}">编辑</a> |
                        <a data-url="${ctx}/cms/friendlink/${link.friendlinkId}" type="POST" abc-type="delete"  class="pn-opt">删除</a>
                    </td>
                </tr>
                </#list>
            <#else>
            </#if>
            </tbody>
        </table>
        <div class="button_caozuo_fenye">
            <button type="button" class="nycon_sel_btn layui-btn">全选</button>
            <button id="batch_del"  class="layui-btn layui-btn-danger">批量删除</button>
        <!-- <input type='button' class="btn btn-default btn-sm pn-opt" value='保存排列顺序'/>-->
        </div>
    <#include "../../common/pagination.ftl">
    </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/friendlink" src="${ctx}/js/require.js"></script>
</body>
</html>