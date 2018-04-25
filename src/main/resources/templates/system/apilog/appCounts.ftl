<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/tagEditor/jquery.tagsinput.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .ny_tab_t>tbody>tr>td,.ny_tab_t>tr>td{padding: 3px 3px;}
        .cxtd1{text-align:right;width:80px;}
        .cxtd2{text-align:left;width:150px;}
        .cxinput{width:160px;height:40px;}
        #consumer_more{background-color: #1fd84f;border:none;}
        #consumer_more:hover{background-color: #2eb751;border:none;}
        @-webkit-keyframes shake {
            0%, 100% {-webkit-transform: translate3d(0, 0, 0);transform: translate3d(0, 0, 0);}
            10%, 30%, 50%, 70%, 90% {-webkit-transform: translate3d(-10px, 0, 0);transform: translate3d(-10px, 0, 0);}
            20%, 40%, 60%, 80% { -webkit-transform: translate3d(10px, 0, 0); transform: translate3d(10px, 0, 0); }
        }
        @keyframes shake {
            0%, 100% {-webkit-transform: translate3d(0, 0, 0);-ms-transform: translate3d(0, 0, 0);transform: translate3d(0, 0, 0);}
            10%, 30%, 50%, 70%, 90% {-webkit-transform: translate3d(-10px, 0, 0);-ms-transform: translate3d(-10px, 0, 0);transform: translate3d(-10px, 0, 0);}
            20%, 40%, 60%, 80% {-webkit-transform: translate3d(10px, 0, 0);-ms-transform: translate3d(10px, 0, 0);transform: translate3d(10px, 0, 0);}
        }
        .fl{width:23.75%;float:left;margin-top:30px;margin-left:1%;cursor:pointer;display:table;}
        /*.fl:hover{transform:scale(1.05);-webkit-animation-name: /!*shake;animation-name: shake;-webkit-animation-duration: 1s;animation-duration: 1s;-webkit-animation-fill-mode: both;animation-fill-mode: both;*!/}*/
        .col-xs-4{width:55%;font-size: 20px;font-weight:bold;margin-top:0;line-height: 80px;height:80px;white-space: nowrap;overflow:hidden;text-overflow:ellipsis;}
        .col-xs-8{width:45%;text-align: center;background: #fff;overflow: hidden;}
        .ccd{line-height: 80px;font-size: 25px;margin-top:0;font-weight:bold; background:#f2f2f2;}
        .btn-bj1 {background: #ff7372;}
        .color_1{color: #ff7372;}
        .btn-bj2 {background: #ff9800;}
        .color_2{color: #ff9800;}
        .btn-bj3 {background: #70C846;}
        .color_3{color: #70C846;}
        .btn-bj4 {background: #00bcd4}
        .color_4{color: #00bcd4}
        .btn-bj5 {background: #ff9800;}
        .color_5{color: #ff9800;}
        .btn-bj6 {background: #70C846}
        .color_6{color: #70C846}
        .btn-bj7 {background: #00bcd4}
        .color_7{color: #00bcd4;}
        .btn-bj8 {background: #ff7372;}
        .color_8{color: #ff7372;}
        .btn-bj9 {background: #ff9800}
        .color_9{color: #ff9800}
        .btn-bj10 {background: #70C846}
        .color_10{color: #70C846}
        .sta_icon2 ul li {
            margin: 0 1%
        }
        .sta_icon2 .sta_icon_l{ font-size: 15px; }

    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <div class=" container-fluid">
    <form action="${ctx}/apilog/appCounts" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">查询日期</label>
                    <div class="layui-input-inline">
                        <input id="startTime" type="text" value="${(BaseRq.startTime)!}" name="startTime" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="sta_icon2"  style="border-top: 1px solid #ddd;width: 100%;margin: 15px 0 10px; padding-top: 20px;">
        <ul style="width: 100%;">
        <#if apiLogRs?? && ( apiLogRs?size gt 0 )>
            <#list apiLogRs as apiLog>
                <li class="fl" <#if apiLogRs?? && ( apiLogRs?size gt 5 )> style="width: 14%;"<#else>style="width: 17%;"</#if> title='${apiLog.nickname!}' data-id='${apiLog.appId!}' >
                    <div class="col-xs-4 btn-bj${apiLog_index + 1} sta_icon_l">${apiLog.nickname!}</div>
                    <div class="col-xs-8"><h3 id="days" class="color_${apiLog_index + 1} ccd">${apiLog.appAccessCount!}</h3></div>
                </li>
            </#list>
        </#if>
            <div class="clear"></div>
        </ul>
    </div>

    <div class="statistics_tab " style="margin-top: 20px">
        <div class="statistics_t">
            <h4 class="modal-title">接口访问日志统计</h4>
        </div>
        <div class="statistics_tb">
        </div>
        <div class="tj_statis_w">
            <div class="tj_statis_n hover">
                <div id="tjday" style="width: 100%"></div>
            </div>
        </div>
    </div>
</div>
    </div>
<script data-main="${ctx}/js/abc/system/appconut" src="${ctx}/js/require.js"></script>
</body>
</html>