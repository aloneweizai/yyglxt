<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>调查问卷</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body class="g_wrapper g_wrapper_full page_mine">
<input type="hidden" id="wxUrl" value="${WEIXIN_URL}">
<div class="mod_preview" style="display: block;">
    <div class="header">
        <div class="nav">
            <ul>
                <li id="preview_pc" class="preview_type btn_preview_pc active"> <i class="ico ico_preview_pc"></i> 电脑预览 </li>
            </ul>
            <div class="nav_ext">
                <a href="javascript:;" class="btn btn_middle btn_white btn_share_box not_hide" id="fx">分享</a>
                <a href="javascript:;" class="btn btn_middle btn_white not_hide js_print">打印</a>
            </div>
        </div>
    </div>
    <div class="body">
        <#--<div id="preview_mobile_container" class="preview_content"><iframe class="preview_iframe" id="preview_mobile_iframe"  frameborder="0"></iframe> </div>-->
        <div id="preview_pc_container" class="preview_content active">
            <iframe class="preview_iframe" id="preview_pc_iframe" name="preview_pc_iframe" src="${ctx}/cms/questionnaire/preview/pc/${questionId}.php" frameborder="0" style="height: 650px;"></iframe>
        </div>
    </div>
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
                                <input id="share_link" type="text" class="link" value="${SNSURL}/pub/questionnaire/view/pc/${questionId}.php">
                                <button class="btn btn_small btn_white copy_link">复制</button>
                                <a target="_blank" href="${SNSURL}/pub/questionnaire/view/pc/${questionId}.php" class="btn btn_small btn_white open_link">打开</a>
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
</body>
<script data-main="${ctx}/js/abc/cms/questionnaire/preview.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</html>