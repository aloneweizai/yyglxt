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
    <form method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <a href="${ctx}/consumerManager/lottery/lotteryActivity.php" class="layui-btn">返回</a>
                        <a href="${ctx}/consumerManager/lottery/lotteryTimeEdit.php?activityId=${activityId!}" class="layui-btn layui-btn-normal">更多条件</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>抽奖活动</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>概率(%)</th>
                    <th>修改日期</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if lotteryTimeRs?? && ( lotteryTimeRs?size gt 0 )>
                    <#list lotteryTimeRs as lotteryTime>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+lotteryTime_index+1}</td>
                        <td>${(lotteryTime.activityName)!}</td>
                        <td>${(lotteryTime.startTime?string("HH:mm:ss"))!}</td>
                        <td>${(lotteryTime.endTime?string("HH:mm:ss"))!}</td>
                        <td>${(lotteryTime.luck)!}</td>
                        <td>${(lotteryTime.lastUpdate?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <a href="${ctx}/consumerManager/lottery/lotteryTimeEdit.php?id=${lotteryTime.id}&activityId=${activityId!}">修改</a>
                            |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                      href="javascript:void(0);"
                                      data-href="${ctx}/consumerManager/lottery/lotteryTimeDel.php?id=${lotteryTime.id}" class="pn-opt">删除</a>

                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>


<#include "../../common/pagination.ftl">
    <input type="hidden" value="${ctx}/cszjs/lotteryTime/list.php" id="currLink">
</div>
<script data-main="${ctx}/js/abc/consumer/lotteryTime.js" src="${ctx}/js/require.js"></script>
</body>
</html>