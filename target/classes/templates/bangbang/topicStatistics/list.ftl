<!DOCTYPE html>
<html style="background-color: #ecf0f5" lang="en">
<head>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
    </script>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bangbang/help.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script src="${ctx}/js/plugin/statistics/js/highcharts.js"></script>
    <script src="${ctx}/js/plugin/statistics/js/exporting.js"></script>
    <title></title>
</head>
<body>
<div id="statistics-box">
    <h3 class="h3">话题统计分析</h3>
    <div id="statistics-date">
        <form class="layui-form" id="form5" action="">
            <div class="radio-title">
                <div class="layui-form-item">
                    <label class="layui-form-label">话题分类</label>
                    <div class="layui-input-block">
                        <div class="radio-title">
                            <span><input class="on js_select_classify" data-code="0" type="button" value="全部"><img src="${ctx}/images/bangbang/ok.png" alt=""></span>
                            <#if classifyList?? && (classifyList?size > 0)>
                                <#list classifyList as classify>
                                    <span><input class="js_select_classify" type="button" data-code="${classify.classifyCode}" value="${classify.classifyName}"></span>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
            <div  class="layui-inline inline">
                <label style="width: 80px;" class="layui-form-label">统计时间</label>
                <div style="float: left" class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <input id="beginTime" type="text" class="layui-input date-item">
                        </div>
                        <div class="layui-inline zhi">至</div>
                        <div class="layui-inline">
                            <input id="endTime" type="text" class="layui-input date-item">
                        </div>
                        <div type="button" style="height: 36px;margin-top: -6px;" class="layui-btn js_query">统计</div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div style="padding: 20px; background-color: #fff" id="container_col" class="container_col"></div>
    <div class="analysis">
        <table class="layui-table">
            <colgroup>
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
            </colgroup>
            <thead>
            <tr>
                <th>分类名称</th>
                <th>回答数</th>
                <th>评论数</th>
                <th>点赞数</th>
                <th>提问数</th>
            </tr>
            </thead>
            <tbody align="center">
            <#if statisticsList?? && (statisticsList?size > 0)>
                <#list statisticsList as item>
                    <tr class="js_statistics_tr">
                        <td>${item.classifyName}</td>
                        <td>${item.answerNum}</td>
                        <td>${item.commentNum}</td>
                        <td>${item.likeNum}</td>
                        <td>${item.questionNum}</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</div>

<script data-main="${ctx}/js/abc/bangbang/topicStatistics/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>