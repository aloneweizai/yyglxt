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
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > td {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form>
        <div class="layui-form-top">
            <div class="layui-form-item">
                <input type="hidden" id="activityId" value="${activityId!}">
                <a href="${ctx}/consumerManager/lottery/lotteryActivity.php"  class="layui-btn layui-btn-primary fr">返回</a>
                <a href="${ctx}/consumerManager/lottery/lotteryActivityprizeEdit.php?activityId=${activityId!}" class="layui-btn fr">添加</a>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>抽奖活动</th>
                    <th>奖品</th>
                    <th>奖品图片</th>
                    <th>排序</th>
                    <th>概率%</th>
                    <th>奖项名称</th>
                    <th>总量|已发</th>
                    <th>创建时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if listRs?? && ( listRs?size gt 0 )>
                    <#list listRs as list>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+list_index+1}</td>

                        <td>${(list.activityName)!}</td>
                        <td>${(list.lotteryName)!}</td>
                        <td>
                            <#if list.lotteryImage!="">
                                <img src="${list.lotteryImage}" style="height: 80px;">
                            <#else>
                                <img src="${ctx}/images/zanwu.png" style="height: 50;">
                            </#if>
                        </td>
                        <td>${(list.sort)!}</td>
                        <td>${(list.luck)!"0"}</td>
                        <td>${(list.val1)!}</td>
                        <td>${(list.amount)!}|${(list.balance)!}</td>
                        <td>${(list.createTime?string("yyyy-MM-dd"))!}</td>
                        <td>

                            <a   href="${ctx}/consumerManager/lottery/lotteryActivityprizeEdit.php?id=${list.id}&activityId=${activityId!}">修改</a>
                            |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                      href="javascript:void(0);"
                                      data-href="${ctx}/consumerManager/lottery/lotteryActivityprizeDel.php?id=${list.id}&activityId=${activityId!}"
                                      class="pn-opt">删除</a>

                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/LotteryActivityprize.js" src="${ctx}/js/require.js"></script>
</body>
</html>
