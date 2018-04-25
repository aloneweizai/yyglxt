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
    <form action="${ctx}/usertrendcount/useractive.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">统计类型</label>
                    <div class="layui-input-inline">
                        <select name="type"  id="type" lay-filter="type">
                            <option <#if BaseRq.type?? && BaseRq.type == 'day'>selected</#if>  value="day">按日</option>
                            <option <#if BaseRq.type?? && BaseRq.type == 'month'>selected</#if> value="month">按月</option>
                            <option <#if BaseRq.type?? && BaseRq.type == 'year'>selected</#if> value="year">按年</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div id="day" <#if !(BaseRq.type?? && BaseRq.type == 'day')>style="display: none"</#if>>
                        <label class="layui-form-label">查询日期</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test30" value="${(BaseRq.startDay)!}" name="startDay">
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test31" value="${(BaseRq.endDay)!}" name="endDay">
                        </div>
                    </div>
                    <div id="month" <#if !(BaseRq.type?? && BaseRq.type == 'month')>style="display: none"</#if>>
                        <label class="layui-form-label">查询日期</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test4" value="${(BaseRq.startMon)!}" name="startMon">
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test5" value="${(BaseRq.endMon)!}" name="endMon">
                        </div>
                    </div>
                    <div id="year" <#if !(BaseRq.type?? && BaseRq.type == 'year')>style="display: none"</#if>>
                        <label class="layui-form-label">查询日期</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test2" value="${(BaseRq.startYear)!}" name="startYear">
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="test3" value="${(BaseRq.endYear)!}" name="endYear">
                        </div>
                    </div>

                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <caption>活跃用户概况</caption>
                <thead class="pn-lthead">
                <tr>
                    <th>昨日活跃用户数</th>
                    <th>过去7天活跃用户数</th>
                    <#--<th>昨日活跃/过去7天活跃</th>-->
                    <th>过去30天活跃用户数</th>
                    <th>本月活跃用户数</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <tr>
                <#if useractiveRs??>
                    <td>${(useractiveRs.yesterday)!}</td>
                    <td>${(useractiveRs.lastweek)!}</td>
                    <#--<td>${(useractiveRs.lastweekDevidedbyLastweek)!}</td>-->
                    <td>${(useractiveRs.last30Days)!}</td>
                    <td>${(useractiveRs.thismonth)!}</td>
                <#else>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </#if>
                </tr>
                </tbody>
            </table>
            <table class="layui-table" lay-size="sm">
                <caption>活跃用户明细</caption>
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>日期</th>
                    <th>注册用户数（人）</th>
                    <th>活跃用户数（人）</th>
                    <th>注册用户总数（人）</th>
                    <th>
                        用户活跃率 <i class="glyphicon glyphicon-question-sign" title="活跃用户数/注册用户总数*100%"></i>
                    </th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if userActiveDetailRs?? && ( userActiveDetailRs?size gt 0 )>
                    <#list userActiveDetailRs as userActiveDetail>
                    <tr>
                        <td class="td_i">${BaseRq.offset + userActiveDetail_index + 1}</td>
                        <td>${(userActiveDetail.dateStr)!}</td>
                        <td>
                            <a class="ljc_00bcd4" title="查看用户清单" data-type="lookdialog" data-val="2" href="javascript:;"
                               data-url="${ctx}/usertrendcount/useractive/look.php?days=${(userActiveDetail.dateStr)!}">${(userActiveDetail.register?c)!}</a>
                        </td>
                        <td><a class="ljc_00bcd4" title="查看用户清单" data-type="lookdialog" data-val="2" href="javascript:;"
                               data-url="${ctx}/usertrendcount/useractive/info.php?timeInterval=${(userActiveDetail.date)!}">${(userActiveDetail.liveUsers?c)!}</a></td>
                        <td>
                           ${(userActiveDetail.allRegister?c)!}
                        </td>
                        <td>${(userActiveDetail.liveUserPercent)!}</td>
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="6">--未查到相关记录--</td>
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
<script data-main="${ctx}/js/abc/system/useractive.js" src="${ctx}/js/require.js"></script>
</body>
</html>
