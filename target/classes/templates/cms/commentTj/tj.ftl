<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8" />
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/commentTj.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--评论统计查询-->
</head>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <div class=" container-fluid">
        <div class="row sta_icon2" style="margin-top: 15px;">
            <ul>
                <li class=" fl">
                    <div class="col-xs-4 btn-bj-red2 sta_icon_l">TODAY</div>
                    <div class="col-xs-8 sta_icon_r"><h3 id="days" class=" color_r2"></h3>今日</div>
                </li>
                <li class=" fl">
                    <div class=" col-xs-4 btn-bj-yellow sta_icon_l">WEEK</div>
                    <div class="col-xs-8 sta_icon_r"><h3 id="weeks" class=" color_y"></h3>本周</div>
                </li>
                <li class=" fl">
                    <div class="col-xs-4 btn-bj-blue sta_icon_l">MONTH</div>
                    <div class="col-xs-8 sta_icon_r "><h3 id="months" class=" color_b"></h3>本月</div>
                </li>
                <li class=" fl">
                    <div class="col-xs-4 btn-bj-green sta_icon_l">YEAR</div>
                    <div class="col-xs-8 sta_icon_r"><h3 id="years" class=" color_g"></h3>今年</div>
                </li>
                <li class=" fl">
                    <div class="col-xs-4 btn-bj-yellow sta_icon_l">TOTAL</div>
                    <div class="col-xs-8 sta_icon_r"><h3 id="cnts" class="  color_y"></h3>评论总数</div>
                </li>
            </ul>
        </div>
        <div class="statistics_tab ">
            <div class="statistics_t">
                <ul>
                    <li class="hover">日统计</li>
                    <li>月统计</li>
                    <li>年统计</li>
                    <div class="clear"></div>
                </ul>
            </div>
            <div class="statistics_tb">
                <ul>
                    <li class="hover">全部评论</li>
                    <li>已回复</li>
                    <li>未回复</li>
                    <div class="clear"></div>
                </ul>
            </div>
            <div class="tj_statis_w">
                <div class="tj_statis_n hover">
                    <div id="tjday" style="width: 100%"></div>
                    <div id="tjday0" style="width: 100%"></div>
                    <div id="tjday1" style="width: 100%"></div>
                </div>
                <div class="tj_statis_n">
                    <div id="tjmonth" style="width: 100%"></div>
                    <div id="tjmonth0" style="width: 100%"></div>
                    <div id="tjmonth1" style="width: 100%"></div>
                </div>
                <div class="tj_statis_n">
                    <div id="tjyear" style="width: 100%"></div>
                    <div id="tjyear0" style="width: 100%"></div>
                    <div id="tjyear1" style="width: 100%"></div>
                </div>
            </div>
        </div>
    </div>
    <script data-main="${ctx}/js/abc/cms/commentTj" src="${ctx}/js/require.js"></script>
</body>
</html>