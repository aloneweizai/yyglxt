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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/statistics.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .survey_wrap{padding:30px 0;}
        .toupiao-input{margin: 0px 0 0 22px;height: 28px;line-height: 28px;width: 70%;padding: 5px;box-sizing:border-box;}
        .xuanxiang{width:80%;position:relative;margin:auto;}
    </style>
</head>

<body class="g_wrapper g_wrapper_full page_stat_overview page_stat page_stat_chart page_stat_recycle">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item" href="javascript:void(0);" data-voteid="${voteId!}" id="vote-result">结果与日志</a>
                <a class="nav_item current" href="javascript:void(0);" data-voteid="${voteId!}" id="vote-url">投票地址</a>
                <a class="nav_item" href="javascript:void(0);" id="vote-index">返回</a>
            </div>
        </div>
    </div>
</div>

<div style="padding-top:120px;">
<#include "./url.ftl">
</div>
</body>
<script data-main="${ctx}/js/abc/cms/vote/view" src="${ctx}/js/require.js"></script>
</html>