<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .table>thead>tr>th,.table>tbody>tr>td{text-align:left;}

        .ny_tab_t>tbody>tr>td,.ny_tab_t>tr>td{padding: 3px 3px;}
        .cxtd1{text-align:right;width:80px;}
        .cxtd2{text-align:left;width:150px;}
        .cxinput{width:140px;height:30px;}
        #consumer_more{background-color: #1fd84f;border:none;}
        #consumer_more:hover{background-color: #2eb751;border:none;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/usertrendcount/userretention.php?days=${BaseRq.days!}" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询日期</label>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test5" value="${(BaseRq.startTime)!}" name="startTime" style="width: 100px">
                    </div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test4" value="${(BaseRq.endTime)!}" name="endTime" style="width: 100px">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <#--<th width="30">序号</th>-->
                    <th rowspan="2">时间</th>
                    <th rowspan="2">新增注册用户总数（人）</th>
                   <#-- <th>新增用户在不同月份至少登录一次的总用户数</th>-->
                    <th colspan="7">
                        留存率 <i class="glyphicon glyphicon-question-sign" title="新增用户在间隔月份登录一次的用户总数/新增注册用户总数*100%"></i>
                    </th>
                </tr>
                <tr>
                    <td>当月</td>
                    <td>1个月</td>
                    <td>2个月</td>
                    <td>3个月</td>
                    <td>4个月</td>
                    <td>6个月</td>
                    <td>12个月</td>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
               <#if userretentionRs?? && ( userretentionRs?size gt 0 )>
                    <#list userretentionRs as userretention>
                    <tr>
                        <td>${(userretention.startTime)!}~${(userretention.endTime)!}</td>
                        <td>${(userretention.userCount?c)!}</td>
                        <td style="<#if userretention.rate1??&&userretention.rate1 gt -1&&userretention.rate1 lt 0.2>background-color: #bafff8;
                    <#elseif userretention.rate1??&&userretention.rate1 gt 0.2&&userretention.rate1 lt 0.5>background-color: #82fff8;
                    <#elseif userretention.rate1??&&userretention.rate1 gt 0.5&&userretention.rate1 lt 1.5>background-color: #4fddff;
                    </#if>"><#if userretention.rate1??>${(userretention.rate1*100)!}%</#if></td>
                        <td style="<#if userretention.rate2??&&userretention.rate2 gt -1&&userretention.rate2 lt 0.2>background-color: #bafff8;
                    <#elseif userretention.rate2??&&userretention.rate2 gt 0.2&&userretention.rate2 lt 0.5>background-color: #82fff8;
                    <#elseif userretention.rate2??&&userretention.rate2 gt 0.5&&userretention.rate2 lt 1.5>background-color: #4fddff;
                    </#if>"><#if userretention.rate2??>${(userretention.rate2*100)!}%</#if></td>
                        <td style="<#if userretention.rate3??&&userretention.rate3 gt -1&&userretention.rate3 lt 0.2>background-color: #bafff8;
                        <#elseif userretention.rate3??&&userretention.rate3 gt 0.2&&userretention.rate3 lt 0.5>background-color: #82fff8;
                        <#elseif userretention.rate3??&&userretention.rate3 gt 0.5&&userretention.rate3 lt 1.5>background-color: #4fddff;
                        </#if>"><#if userretention.rate3??>${(userretention.rate3*100)!}%</#if></td>
                        <td style="<#if userretention.rate4??&&userretention.rate4 gt -1&&userretention.rate4 lt 0.2>background-color: #bafff8;
                        <#elseif userretention.rate4??&&userretention.rate4 gt 0.2&&userretention.rate4 lt 0.5>background-color: #82fff8;
                        <#elseif userretention.rate4??&&userretention.rate4 gt 0.5&&userretention.rate4 lt 1.5>background-color: #4fddff;
                        </#if>"><#if userretention.rate4??>${(userretention.rate4*100)!}%</#if></td>
                        <td style="<#if userretention.rate5??&&userretention.rate5 gt -1&&userretention.rate5 lt 0.2>background-color: #bafff8;
                        <#elseif userretention.rate5??&&userretention.rate5 gt 0.2&&userretention.rate5 lt 0.5>background-color: #82fff8;
                        <#elseif userretention.rate5??&&userretention.rate5 gt 0.5&&userretention.rate5 lt 1.5>background-color: #4fddff;
                        </#if>"><#if userretention.rate5??>${(userretention.rate5*100)!}%</#if></td>
                        <td style="<#if userretention.rate6??&&userretention.rate6 gt -1&&userretention.rate6 lt 0.2>background-color: #bafff8;
                        <#elseif userretention.rate6??&&userretention.rate6 gt 0.2&&userretention.rate6 lt 0.5>background-color: #82fff8;
                        <#elseif userretention.rate6??&&userretention.rate6 gt 0.5&&userretention.rate6 lt 1.5>background-color: #4fddff;
                        </#if>"><#if userretention.rate6??>${(userretention.rate6*100)!}%</#if></td>
                        <td style="<#if userretention.rate7??&&userretention.rate7 gt -1&&userretention.rate7 lt 0.2>background-color: #bafff8;
                        <#elseif userretention.rate7??&&userretention.rate7 gt 0.2&&userretention.rate7 lt 0.5>background-color: #82fff8;
                        <#elseif userretention.rate7??&&userretention.rate7 gt 0.5&&userretention.rate7 lt 1.5>background-color: #4fddff;
                        </#if>"><#if userretention.rate7??>${(userretention.rate7*100)!}%</#if></td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/system/userretention.js" src="${ctx}/js/require.js"></script>
</body>
</html>
