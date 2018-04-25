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
    <form action="${ctx}/usertrendcount/registerusercount.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">绑定日期</label>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test4" value="${(BaseRq.startTime)!}" name="startTime" style="width: 100px">
                    </div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test5" value="${(BaseRq.endTime)!}" name="endTime" style="width: 100px">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">系统类型</label>
                    <div class="layui-input-inline">
                        <select name="type" id="type">
                            <option <#if BaseRq.type?? && BaseRq.type=="all">selected</#if> value="all">全部</option>
                            <option <#if BaseRq.type?? && BaseRq.type=="dzsb">selected</#if>  value="dzsb">电子申报</option>
                            <option <#if BaseRq.type?? && !BaseRq.type=="hngs">selected</#if> value="hngs">湖南国税</option>
                            <option <#if BaseRq.type?? && !BaseRq.type=="hnds">selected</#if> value="hnds">湖南地税</option>
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
        <div class="statistics_tab " style="margin-top: 20px">
            <div class="statistics_t">
                <h3 class="modal-title" style="font-size: large"></h3>
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
                <tr>
                    <th width="30">序号</th>
                    <th>绑定时间</th>
                    <th>系统类型</th>
                    <th>企业数量</th>
                </tr>
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
<script data-main="${ctx}/js/abc/system/compbing.js" src="${ctx}/js/require.js"></script>
</body>
</html>
