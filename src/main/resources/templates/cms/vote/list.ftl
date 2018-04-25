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
<div class="container-fluid m_top_30 nycol_list">
    <form action=" " method="post">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/cms/vote/add.php" class="layui-btn fr">创建投票</a>
            </div>
        </div>
    </form>

    <form action="" name="_topic_list_form" method="post">
        <div class=" nycon_list_b">

            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>投票标题</th>
                    <th>投票类型</th>
                    <th>投票总数</th>
                    <th>访问次数</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>截止时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">

                <#list voteList as vote>
                    <#if (vote.endTime!)!="">
                        <#assign endDate=(vote.endTime)?datetime("yyyy-MM-dd HH:mm:ss")>
                    </#if>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+vote_index+1}</td>
                        <td>${vote.name!}</td>
                        <td><#if (vote.channel!)=="radio">单选<#elseif (vote.channel!)=="check">多选</#if></td>
                        <td>${vote.nop!}</td>
                        <td>${vote.nov!}</td>
                        <td style="font-weight:bold;">
                            <#if vote.status == 0>
                                    <span style="color:#a94141;">停用</span>
                                <#elseif vote.status == 1>
                                    <span style="color:#397b3e;">启用</span>
                                <#elseif vote.status == 3>
                                    <span style="color: #bd9c30;">草稿</span>
                                <#else>
                                    <span style="color: #5bc0de;">已结束</span>
                            </#if>
                        </td>
                        <td>${vote.createTime!}</td>
                        <td>${vote.endTime!}</td>
                        <td>
                            <#if vote.status==0 || vote.status==3>
                                <a data-voteid="${vote.id!}" name="activeOrInactiveVote" href="javascript:void(0);">启用</a> |
                             <#elseif vote.status==1>
                                 <a data-voteid="${vote.id!}" name="activeOrInactiveVote" href="javascript:void(0);">停用</a> |
                            </#if>
                            <#if vote.status == 3 || vote.status ==0>
                                <a href="${ctx}/cms/vote/add.php?voteId=${vote.id!}">编辑</a> |
                            </#if>
                            <#if vote.status == 3 || vote.status ==0 || vote.status == 4>
                                <a href="javascript:void(0);" data-voteId="${vote.id!}" name="vote_list_delete" class="pn-opt">删除</a> |
                            </#if>
                            <a href="javascript:void(0);" data-voteId="${vote.id!}" name="vote_list_to_zero" class="pn-opt">清零</a> |
                            <a href="${ctx}/cms/vote/statistics.php?voteId=${vote.id!}">查看</a>
                        </td>
                    </tr>
                </#list>

                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">
</div>
</body>
<script data-main="${ctx}/js/abc/cms/vote/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</html>