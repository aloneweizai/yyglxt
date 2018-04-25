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
    <input type="hidden" id="activityId" value="${activityId!}">
    <form class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">IP地址</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(pagination.ip)!}" name="ip" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button type="button" id="queryBtn"  class="layui-btn">查询</button>
                </div>
                <a href="${ctx}/consumerManager/lottery/lotteryActivity.php" class="layui-btn layui-btn-primary fr">返回</a>
                <a href="lotteryActivityipEdit.php?activityId=${activityId!}" class="layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
<th>活动名称</th>
<th>ip地址</th>
<th>描述</th>
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
<td>${(list.ip)!}</td>
<td>${(list.description)!}</td>
<td>${(list.createTime?string("yyyy-MM-dd"))!}</td>

                        <td>
                        <#if list.status??>
                        
                        <#else>
                         <a href="lotteryActivityipEdit.php?id=${list.id}&activityId=${activityId!}">修改</a>|
                         &nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                      href="javascript:void(0);"
                                      data-href="lotteryActivityipDel.php?id=${list.id}&activityId=${activityId!}" class="pn-opt">删除</a>
                       </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>	
    </form>
<#include "../../common/pagination.ftl">
</div>
<script data-main="${ctx}/js/abc/consumer/LotteryActivityip.js" src="${ctx}/js/require.js"></script>
</body>
</html>
