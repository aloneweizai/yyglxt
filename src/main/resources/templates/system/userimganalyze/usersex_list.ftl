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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.css">
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
<div class="container-fluid m_top_3 sys_mod">
    <form action="${ctx}/usertrendcount/registerusercount.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间起</label>

                    <div class="layui-input-inline" id="startDate">
                        <input type="text" class="layui-input" id="test4" value="${(BaseRq.startTime)!}"
                               name="startTime">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间止</label>

                    <div class="layui-input-inline" id="endDate">
                        <input type="text" class="layui-input" id="test5" value="${(BaseRq.endTime)!}" name="endTime">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="statistics_tab " style="margin-top: 20px">
            <div class="statistics_t">
                <h3 class="modal-title" style="font-size: large"></h3>
            </div>
            <div class="statistics_tb">
            </div>
           <#-- <div class="tj_statis_w">
                <div class="tj_statis_n hover">
                    <div id="tjday" style="width: 100%"></div>
                </div>
            </div>-->

            <div class="layui-form-item">
                <div class="layui-inline">
                    <#--<div class="layui-input-inline">
                        <div id="tjday" style="width: 100%"></div>
                    </div>-->
                        <div id="tjday" style="width: 100%"></div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <table class="layui-table" lay-size="sm" id="sextable" style="width: 1200px">
                            <thead class="pn-lthead">
                            <tr>
                                <th width="30">序号</th>
                                <th>性别</th>
                                <th>用户数（人）</th>
                            </tr>
                            </thead>
                            <tbody class="pn-ltbody" id="registeruser">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b" style="margin-top: 30px">
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
<script data-main="${ctx}/js/abc/system/usersex.js" src="${ctx}/js/require.js"></script>
</body>
</html>
