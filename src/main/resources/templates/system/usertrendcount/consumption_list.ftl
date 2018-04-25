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
        caption {
            padding-top: 8px;
            padding-bottom: 8px;
            color: #777;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/usertrendcount/consumption.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询日期</label>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test30" value="${(BaseRq.startTime)!}" name="startTime" style="width: 100px">
                    </div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}" name="endTime" style="width: 100px">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">交易方式</label>
                    <div class="layui-input-inline">
                        <select name="tradeMethod"  id="tradeMethod" lay-filter="tradeMethod">
                            <option <#if BaseRq.tradeMethod?? && BaseRq.tradeMethod == 'RMB'>selected</#if>  value="RMB">人民币</option>
                            <option <#if BaseRq.tradeMethod?? && BaseRq.tradeMethod == 'POINTS'>selected</#if> value="POINTS">积分</option>
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
                <caption>多维度统计</caption>
                <thead class="pn-lthead">
                <tr>
                    <th>统计维度</th>
                    <th>最小值到平均值</th>
                    <th>平均值到最大值</th>
                    <th>操作
                    </th>
                    <th>统计数（笔）</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if consumptionRs??>
                <tr>
                    <td>R：最近一次消费间隔统计时间段截止日期的天数</td>
                    <td>
                        <input type="text" class="layui-input" id="minTime" value="${(consumptionRs.minTime?c)!}" style="display: none">
                        <input type="text" class="layui-input" id="avgTime" value="${(consumptionRs.avgTime?c)!}" style="display: none">
                        <input type="text" class="layui-input" id="maxTime" value="${(consumptionRs.maxTime?c)!}"style="display: none">
                        <input id="time1" name="time" checked type="radio" value="1" title="${(consumptionRs.minTime?c)!} - ${(consumptionRs.avgTime?c)!}">
                    </td>
                    <td>
                        <input id="time2" name="time" type="radio" value="2" title="${(consumptionRs.avgTime?c)!} - ${(consumptionRs.maxTime?c)!}">
                    </td>
                    <td rowspan="3"><a date-href="${ctx}/usertrendcount/consumption/userlist.php" data-type="comsumtj" class="layui-btn layui-btn-mini">统计</a></td>
                    <td rowspan="3"><a class="ljc_00bcd4" data-type="opendialog"><span id="tjs"></span></a></td>
                </tr>
                <tr>
                    <td>F：统计时间段内消费次数</td>
                    <td>
                        <input type="text" class="layui-input" id="minConsum" value="${(consumptionRs.minConsum?c)!}" style="display: none">
                        <input type="text" class="layui-input" id="avgConsum" value="${(consumptionRs.avgConsum?c)!}" style="display: none">
                        <input type="text" class="layui-input" id="maxConsum" value="${(consumptionRs.maxConsum?c)!}"style="display: none">
                        <input id="consum1" name="consum" checked type="radio" value="1" title="${(consumptionRs.minConsum?c)!} - ${(consumptionRs.avgConsum?c)!}" >
                    </td>
                    <td>
                        <input id="consum2" name="consum" type="radio" value="2" title="${(consumptionRs.avgConsum?c)!} - ${(consumptionRs.maxConsum?c)!}">
                    </td>
                </tr>
                <tr>
                    <td>M：统计时间段内容消费总金额</td>
                    <td>
                        <input type="text" class="layui-input" id="minPrice" value="${(consumptionRs.minPrice?c)!}" style="display: none">
                        <input type="text" class="layui-input" id="avgPrice" value="${(consumptionRs.avgPrice?c)!}" style="display: none">
                        <input type="text" class="layui-input" id="maxPrice" value="${(consumptionRs.maxPrice?c)!}"style="display: none">
                        <input id="price1" name="price" checked type="radio" value="1" title="${(consumptionRs.minPrice*100/100)!} - ${(consumptionRs.avgPrice*100/100)!}">
                    </td>
                    <td>
                        <input id="price2" name="price" type="radio" value="2" title="${(consumptionRs.avgPrice*100/100)!} - ${(consumptionRs.maxPrice*100/100)!}">

                    </td>
                </tr>
                <#else>
                <tr>
                    <td>R：最近一次消费间隔统计时间段截止日期的天数</td>
                    <td rowspan="3" colspan="4">--无消费记录,请重新查询--</td>
                </tr>
                <tr>
                    <td>F：统计时间段内消费次数</td>
                </tr>
                <tr>
                    <td>M：统计时间段内容消费总金额</td>
                </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/system/consumption.js" src="${ctx}/js/require.js"></script>
</body>
</html>
