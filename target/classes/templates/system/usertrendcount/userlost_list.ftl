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
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/usertrendcount/userlost.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test4" value="${(BaseRq.yearTime)!}" name="yearTime" style="width: 140px;height:38px;">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: inherit;">流失间隔周期</label>
                    <div class="layui-input-inline">
                        <select name="months" lay-filter="months" id="months">
                            <option <#if BaseRq.months?? && BaseRq.months == '1'>selected</#if>  value="1">1个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '2'>selected</#if> value="2">2个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '3'>selected</#if> value="3">3个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '4'>selected</#if>  value="4">4个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '5'>selected</#if>  value="5">5个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '6'>selected</#if>  value="6">6个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '7'>selected</#if>  value="7">7个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '8'>selected</#if> value="8">8个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '9'>selected</#if> value="9">9个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '10'>selected</#if>  value="10">10个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '11'>selected</#if>  value="11">11个月</option>
                            <option <#if BaseRq.months?? && BaseRq.months == '12'>selected</#if>  value="12">12个月</option>
                        </select>
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
                    <th>注册用户总数（人）</th>
                    <th>流失用户总数（人）</th>
                    <th>
                        流失率 <i class="glyphicon glyphicon-question-sign" title="流失用户总数/注册用户总数*100%"></i>
                    </th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if userLostRs??>
                    <tr>
                        <td>${(userLostRs.userCount?c)!}</td>
                        <td>${(userLostRs.lossUserCount?c)!}</td>
                        <td>${(userLostRs.rate)!}%</td>
                    </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
