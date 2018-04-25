<html>
<head>
    <meta charset="utf-8">
    <title>调查问卷</title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list">
    <form method="post">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/cms/questionnaire/add.php" class="layui-btn fr">创建问卷</a>
            </div>
        </div>
    </form>
    <form method="post">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>问卷</th>
                    <th>状态</th>
                    <th>回收量</th>
                    <th>创建人</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody js_model_list">
                <#if questions?? && (questions?size > 0) >
                    <#list questions as question>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + question_index + 1}</td>
                        <td>${(question.title)!}</td>
                        <td>
                            <#if question.status == '0'>
                                <div class="btn btn-success btn-xs ">已发布</div>
                            <#elseif question.status == '1'>
                                <div class="btn btn-danger btn-xs ">已撤销</div>
                            <#elseif question.status == '2'>
                                <div class="btn btn-info btn-xs ">已结束</div>
                            <#else>
                                <div class="btn btn-warning btn-xs ">草稿</div>
                            </#if>
                        </td>
                        <td>${(question.recoveryRate)!}</td>
                        <td>${(question.createUser)!}</td>
                        <td>${(question.updateTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <#if question.status == '0'><!-- 已发布状态 -->
                                <a class="js_recycle_question" href="javascript:void(0)" data-href="${ctx}/cms/questionnaire/recycle/${(question.id)!}.php?status=1">撤销</a> |
                            </#if>
                            <#if question.status == '1' || question.status == '3'><!-- 已撤销状态 -->
                                <a class="js_recycle_question" href="javascript:void(0)" data-href="${ctx}/cms/questionnaire/recycle/${(question.id)!}.php?status=0">发布</a> |
                                <a class="js_edit" href="javascript:void(0)" data-href="${ctx}/cms/questionnaire/edit/${(question.id)!}.php">编辑</a> |
                                <a class="js_del_question" href="javascript:void(0)" data-href="${ctx}/cms/questionnaire/del/${(question.id)!}.php">删除</a> |
                            </#if>
                            <#if question.status == '2'><!-- 已结束状态 -->
                                <a class="js_del_question" href="javascript:void(0)" data-href="${ctx}/cms/questionnaire/del/${(question.id)!}.php">删除</a> |
                            </#if>
                            <a href="${ctx}/cms/questionnaire/statistics/${(question.id)!}.php">统计</a> |
                            <a href="${ctx}/cms/questionnaire/preview/${question.id}.php" target="_blank">预览</a>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            ${pageHtml!}
        </div>
    </form>
</div>
<div id="share_div" style="display: none;">
    <div style="opacity: 0.7; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; z-index: 1000000; background: rgb(0, 0, 0);" class="ui-popup-backdrop"></div>
    <div class="ui-popup ui-popup-modal ui-popup-show ui-popup-focus" role="alertdialog" style="position: absolute; outline: 0px; left: 50%; top: 50%; z-index: 1000000; width: 660px; height: 500px; margin: -250px 0 0 -330px;">
        <div i="dialog" class="ui-dialog ui-dialog-large ui-dialog_share closed">
            <div class="ui-dialog-arrow-a"></div>
            <div class="ui-dialog-arrow-b"></div>
            <table class="ui-dialog-grid">
                <tbody>
                <tr>
                    <td i="header" class="ui-dialog-header"><button i="close" class="ui-dialog-close" title="cancel">×</button>
                        <div i="title" class="ui-dialog-title">问卷分享</div>
                    </td>
                </tr>
                <tr>
                    <td i="body" class="ui-dialog-body">
                        <div i="content" class="ui-dialog-content" style="width: 600px; height: 400px;">
                            <h3>分享预览链接</h3>
                            <div class="wrap">
                                <input id="share_link" type="text" class="link" value="">
                                <button class="btn btn_small btn_white copy_link">复制</button>
                                <a target="_blank" href="#" class="btn btn_small btn_white open_link">打开</a>
                            </div>
                            <h3>二维码</h3>
                            <div class="wrap" id="qrcode"></div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td i="footer" class="ui-dialog-footer">
                        <div i="button" class="ui-dialog-button"><button type="button" i-id="ok" autofocus="" class="ui-dialog-autofocus">关闭</button></div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cms/questionnaire/list.js?v=1" src="${ctx}/js/require.js"></script>
</body>
</html>
