<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>投票</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_recycle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_overview.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_chart.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/statistics.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>

</head>

<body class="g_wrapper g_wrapper_full page_stat_overview page_stat page_stat_chart page_stat_recycle">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item current" href="javascript:void(0);" data-voteid="${voteId!}" id="vote-result">结果与日志</a>
                <a class="nav_item" href="javascript:void(0);" data-voteid="${voteId!}" id="vote-url">投票地址</a>
                <a class="nav_item" href="javascript:void(0);" id="vote-index">返回</a>
            </div>
        </div>
    </div>
</div>
<div id="container" class="g_container">
    <div class="container_wrapper">
        <div class="container_inner">
            <div class="stat_sidebar">
                <div class="stat_sidebar">
                    <ul>
                        <li class="sidebar_item current" test="n1">
                            <a class="chart_ico iconLink current" href="javascript:void(0);">
                                <div class="icon"><i class="left_fan recycle_fan"></i> <i
                                        class="right_fan recycle_fan"></i> <i class="bg_fan recycle_fan"></i>

                                    <div class="ie8Compacity"></div>
                                </div>
                                <div class="wording">日志统计</div>
                            </a>
                        </li>
                        <li class="sidebar_item" test="n2">
                            <a class="recycle_ico iconLink current" href="javascript:void(0);">
                                <div class="icon"><i class="recycle_inner_line recycle_inner_line_1"></i> <i
                                        class="recycle_inner_line recycle_inner_line_2"></i> <i
                                        class="recycle_inner_line recycle_inner_line_3"></i></div>
                                <div class="wording">投票结果</div>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
            <div id="n1">
                <div style="background:#fff;padding:15px 0 5px;">
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                            <input type="hidden" id="voteId" name="voteId" value="${voteId!}">
                            <div class="layui-inline">
                                <label class="layui-form-label">起始日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="startDate" id="startDate" value="${startDate!}" lay-verify="required" placeholder="请选择" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">终止日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="endDate" id="endDate" value="${endDate!}" lay-verify="required" placeholder="请选择" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">统计单位</label>
                                <div class="layui-input-inline">
                                    <select name="statisticsScope" lay-filter="statisticsScope">
                                        <option value="day" selected>日</option>
                                        <option value="month">月</option>
                                        <option value="year">年</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button class="layui-btn" id="search-btn" lay-submit lay-filter="do-statistics-form">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div style="height: 20px;"></div>
                <div class="col-md-12 ny_statis" style="padding: 10px; background: #fff;">
                    <div id="container_line_tptongji" class="container_line_tptongji"></div>
                </div>
                <div style="height: 20px;"></div>

                <div class="col-md-12 ny_statis" style="padding: 10px; background: #fff;">
                    <div id="container_col_tptongji" class="container_col_tptongji"></div>
                </div>
            </div>
            <div id="n2" style="background: #fff; border:1px solid #ddd; display: none;">

                <div id="recycle">
                    <div class="recycle_filter">
                        <div class="legend">
                            <table id="vote_item_list_title" class="table-zy">
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/vote/statistics.js" src="${ctx}/js/require.js"></script>
<script type="text/html" id="vote_item_list_tmpl">

    <tr>
        <td colspan="2" style="padding:0;"><h2>品相投票</h2></td>
    </tr>
    <tr >
        <td colspan="2">投票总数{{totalVote}}票 &nbsp; 浏览{{totalView}}次</td>
    </tr>

    {{each list value i}}
    <tr>
        <td width="20%">
            {{i+1}}、{{value.item}}<span>第{{i+1}}名</span>
        </td>
        <td>
            <div class="skillbar clearfix " data-percent="{{value.percent}}%">
                <div class="skillbar-bar" style="background: #28A4C9;"></div>
                <div class="skill-bar-percent">{{value.percent}}%</div>
            </div>
            <#--<p><a name="vote_update_sub_item" href="javascript:void(0);" data-id="{{value.id}}" data-curbz="{{value.bz}}">{{if value.bz=='1'}}暂停此项{{else}}启用此项{{/if}}</a></p>-->
        </td>
    </tr>
    {{/each}}

</script>
</html>