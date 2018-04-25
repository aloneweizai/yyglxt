<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style type="text/css">
        #_content_list_form th, #_content_list_form td {
            text-align: center;
        }

        .ny_tab_t > tbody > tr > td, .ny_tab_t > tr > td {
            padding: 3px 3px;
        }

        .cxtd1 {
            text-align: right;
            width: 80px;
        }

        .cxtd2 {
            text-align: left;
            width: 150px;
        }

        .cxinput {
            width: 140px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form class="layui-form" action="${ctx}/consumerManager/lottery/lotterylog.php" method="get" id="logListForm">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">是否中奖</label>
                    <div class="layui-input-inline">
                        <select name="isluck" class="cxinput" id="isluck">
                            <option value=""></option>
                            <option value="0" <#if pagination.isluck?? && (pagination.isluck == 0)>selected </#if>>否</option>
                            <option value="1"  <#if pagination.isluck?? && (pagination.isluck == 1)>selected </#if>>是</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(pagination.userName)!}" name="userName" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">活动</label>
                    <div class="layui-input-inline">
                        <select name="activityName" id="activityName"class="cxinput">
                            <option value=""></option>
                        <#list lotteryActivityRs as lotteryActivity>
                            <option  value="${(lotteryActivity.name)!}" <#if pagination.activityName??&&lotteryActivity.name??&&pagination.activityName==lotteryActivity.name>selected</#if> > ${(lotteryActivity.name)!}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline js_query" style="width:180px;">
                        <button id="queryBtn" class="layui-btn">查询</button>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>
                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">开始时间</label>
                        <div class="layui-input-inline">
                            <input type="text" id="startTime" data-type="datebox" name="startTime" value="${(pagination.startTime)!}" class="layui-input">
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" id="endTime" data-type="datebox" name="endTime" value="${(pagination.endTime)!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">领奖状态</label>
                        <div class="layui-input-inline">
                            <select name ="state" id="staterq"class="cxinput">
                                <option value=""></option>
                                <#if lottery_fhzt?? && ( lottery_fhzt?size gt 0 )>
                                    <#list lottery_fhzt as typeObj>
                                        <option value="${(typeObj.fieldKey)!}"<#if pagination??&&pagination.state??&&typeObj.fieldKey??&&pagination.state==typeObj.fieldKey>selected</#if>> ${(typeObj.fieldKey)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>抽奖时间</th>
                    <th>奖品</th>
                    <th>活动</th>
                    <th>用户名称</th>
                    <th>IP地址</th>
                    <th>是否中奖</th>
                    <th>消耗积分</th>
                    <th>领取状态</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if lotteryLogRs?? && ( lotteryLogRs?size gt 0 )>
                    <#list lotteryLogRs as lotteryLog>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+lotteryLog_index+1}</td>

                        <td>${lotteryLog.createTime?string("yyyy-MM-dd HH:mm:ss")!}</td>
                        <td>${(lotteryLog.lotteryName)!}</td>
                        <td>${(lotteryLog.activityName)!}</td>
                        <td>${(lotteryLog.userName)!}</td>
                        <td>${(lotteryLog.ip)!}</td>
                        <td>
                            <#if lotteryLog.isluck?? && lotteryLog.isluck == 1>
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
                        </td>
                        <td>${(lotteryLog.upoint)!}</td>
                        <td>${(lotteryLog.state)!}</td>
                        <td>${(lotteryLog.remake)!}</td>
                        <td>
                            <a href="${ctx}/consumerManager/lottery/lotteryLogDetail.php?id=${lotteryLog.id}">查看</a>
                        <#if lotteryLog.state?? && lotteryLog.state == '发货中'>
                           | <a href="${ctx}/consumerManager/lottery/lotteryLogEdit.php?id=${lotteryLog.id}">处理</a>
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
    <input type="hidden" value="${ctx}/cszjs/lotteryLog/list.php" id="currLink">
</div>
<script data-main="${ctx}/js/abc/consumer/lotteryLog.js" src="${ctx}/js/require.js"></script>
</body>
</html>