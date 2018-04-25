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
    <form action="${ctx}/usertrendcount/registerusercount.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-btn-group">
                    <div class="layui-btn layui-btn-sm" id="thisweek">本周</div>
                    <div class="layui-btn layui-btn-primary layui-btn-sm" id="lastweek">上周</div>
                    <div class="layui-btn layui-btn-primary layui-btn-sm" id="thismon">本月</div>
                    <div class="layui-btn layui-btn-primary layui-btn-sm" id="lastmon">上月</div>
                    <div class="layui-btn layui-btn-primary layui-btn-sm" id="thisyear">今年</div>
                    <div class="layui-btn layui-btn-primary layui-btn-sm" id="sjd">时间段</div>
                    <div class="clear"></div>
                </div>
                <div class="layui-inline">
                  <#--  <label class="layui-form-label">时间段</label>-->
                    <div class="layui-input-inline" style="width:200px;">
                        <input type="text" class="layui-input" id="test6" >
                    </div>
                    <div class="layui-input-inline" style="width:80px; height: 30px; padding-left:10px; margin-right: 0;">
                        <input type="checkbox" id="dbbox" lay-skin="primary" title="对比">
                    </div>
                    <div class="layui-input-inline" style="width:200px;">
                        <input type="text" class="layui-input" id="test7" >
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;margin-left: 30px">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
               <#-- <input type="button" value="查询" id="consumer_query" class="layui-btn fr">-->
            </div>
        </div>
        <div class="statistics_tab " style="margin-top: 20px">
            <div class="statistics_t">
                <h3 class="modal-title" style="font-size: large">企业税务登记注册情况统计</h3>
            </div>
            <div class="statistics_tb">
            </div>
            <div class="tj_statis_w">
                <div class="tj_statis_n hover">
                    <div id="tjday" style="width: 100%"></div>
                </div>
            </div>
            <div class="tj_statis_w" <#if !(BaseRq.type?? && BaseRq.type == '6')>style='display:none;'</#if>>
                <div class="tj_statis_n hover">
                    <div id="tjday1" style="width: 100%"></div>
                </div>
            </div>
        </div>

        <div class="nycon_list_b" style="margin-top: 30px">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                </thead>
                <tbody class="pn-ltbody" id="registeruser">
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
<script data-main="${ctx}/js/abc/system/registercomp.js" src="${ctx}/js/require.js"></script>
</body>
</html>
