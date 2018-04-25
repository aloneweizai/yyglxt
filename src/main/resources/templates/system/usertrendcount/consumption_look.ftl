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
    <form action="${ctx}/usertrendcount/consumption/look.php" method="get" id="consumerListForm" class="layui-form">
        <input type="hidden" name="startTime" id="startTime" value="${BaseRq.startTime!}">
        <input type="hidden" name="endTime" id="endTime" value="${BaseRq.endTime!}">
        <input type="hidden" name="tradeMethod" id="tradeMethod" value="${BaseRq.tradeMethod!}">
        <input type="hidden" name="startDay" id="startDay" value="${BaseRq.startDay!}">
        <input type="hidden" name="endDay" id="endDay" value="${BaseRq.endDay!}">
        <input type="hidden" name="startCount" id="startCount" value="${BaseRq.startCount!}">
        <input type="hidden" name="endCount" id="endCount" value="${BaseRq.endCount!}">
        <input type="hidden" name="startPrice" id="startPrice" value="${BaseRq.startPrice!}">
        <input type="hidden" name="endPrice" id="endPrice" value="${BaseRq.endPrice!}">

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <caption>用户详情列表</caption>
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>用户昵称</th>
                    <th>手机号码</th>
                    <th>真实姓名</th>
                    <th>所在（地区）</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if userConsumptionRs?? && ( userConsumptionRs?size gt 0 )>
                    <#list userConsumptionRs as userConsumption>
                    <tr>
                        <td class="td_i">${BaseRq.offset + userConsumption_index + 1}</td>
                        <td>${(userConsumption.nickName)!}</td>
                        <td>${(userConsumption.phone)!}</td>
                        <td>${(userConsumption.realName)!}</td>
                        <td>${(userConsumption.area)!}</td>
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="5">--未查到用户详情信息--</td>
                </tr>
                </#if>
                </tbody>
            </table>
            <div class="button_caozuo_fenye">
                <div id='tjdownLoad' class="layui-btn"
                       data-qurl="${ctx}/usertrendcount/qexportuser.php?startTime=${BaseRq.startTime!}&endTime=${BaseRq.endTime!}&tradeMethod=${BaseRq.tradeMethod!}&startDay=${BaseRq.startDay!}&endDay=${BaseRq.endDay!}&startCount=${BaseRq.startCount!}&endCount=${BaseRq.endCount!}&startPrice=${BaseRq.startPrice!}&endPrice=${BaseRq.endPrice!}"
                       data-durl="${ctx}/usertrendcount/consumption/exportExcel.php?startTime=${BaseRq.startTime!}&endTime=${BaseRq.endTime!}&tradeMethod=${BaseRq.tradeMethod!}&startDay=${BaseRq.startDay!}&endDay=${BaseRq.endDay!}&startCount=${BaseRq.startCount!}&endCount=${BaseRq.endCount!}&startPrice=${BaseRq.startPrice!}&endPrice=${BaseRq.endPrice!}"
                       data-noMsg="当前条件下无可导出的用户详情信息，请重新输入条件统计" />导出用户详情</div>
            </div>
            <table class="yy_fanye">
                <tbody>
                <tr>
                    <td align="center">
                        共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                        每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                        <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                        <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                        <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                        <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                        当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                        <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                        <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                        <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                        <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
