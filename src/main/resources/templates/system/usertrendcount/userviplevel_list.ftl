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
    <form action="${ctx}/usertrendcount/userviplevel.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test3" value="${(BaseRq.year)!}" name="year" style="width: 140px;height:38px;">
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
                    <th width="30">序号</th>
                    <th>时间</th>
                    <th>会员等级</th>
                    <th>新增用户数</th>
                    <th>用户总数（人）</th>
                    <th>
                        会员等级增长率 <i class="glyphicon glyphicon-question-sign" title="统计年度该等级比上一年度新增会员数/上一年度该等级会员总数*100%"></i>
                    </th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if userviplevelRs?? && ( userviplevelRs?size gt 0 )>
                    <#list userviplevelRs as userviplevel>
                    <tr>
                        <td class="td_i">${BaseRq.offset + userviplevel_index + 1}</td>
                        <td>${(BaseRq.year)!}</td>
                        <td>${(userviplevel.levelName)!}</td>
                        <td><a class="ljc_00bcd4" title="查看用户清单" data-type="lookdialog" data-val="2" href="javascript:;"
                               data-url="${ctx}/usertrendcount/userviplevel/look.php?year=${(BaseRq.year)!}&&vipCode=${(userviplevel.levelCode)!}&&doType=0">${(userviplevel.increase)!}</a></td>
                        <td><a class="ljc_00bcd4" title="查看用户清单" data-type="lookdialog" data-val="2" href="javascript:;"
                               data-url="${ctx}/usertrendcount/userviplevel/look.php?year=${(BaseRq.year)!}&&vipCode=${(userviplevel.levelCode)!}&&doType=1">${(userviplevel.all)!}</a></td>
                        <td>${(userviplevel.increasePercent)!}</td>
                    </tr>
                    </#list>
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
<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
