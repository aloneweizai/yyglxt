<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>投票</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/vote/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <style>
        .table-zy {
            width: 94%;
            margin: 0 auto;
        }

        .table-zy th {
            width: 15%;
            text-align: right;
            vertical-align: top;
            padding-top: 2px;
        }

        .page_edit .subtitle_item input[type=radio], .page_edit .option_item input[type=radio], .page_edit .subtitle_item input[type=checkbox], .page_edit .option_item input[type=checkbox] {
            margin-left: 0;
        }

        .toupiao-title {
            border-bottom: 1px solid #ddd;
            width: 94%;
            margin: 0 auto;
        }

        .toupiao-input {
            margin: 0px 0 0 22px;
            height: 28px;
            line-height: 28px;
            width: 70%;
            padding: 5px;
        }

        .survey_form_ui {
            margin-top: 5px !important;
        }

        .option_text {
            margin-top: 0px !important;
        }

        .beizhutishi {
            margin-left: 5px;
            color: #999;
        }

        .xuanxiang {
            width: 80%;
            margin: 0 auto;
        }

        .xuanxiang-input {
            margin-top: 10px;
        }

        .xuanxiang-input a {
            display: inline-block;
            width: 50px;
            height: 26px;
            text-align: center;
            background: #F0614E;
            color: #fff;;
        }

        .xuanxiang-a {
            padding-left: 35px;
        }

        .xuanxiang-a a {
            padding: 0 5px;
        }

        .adjoined-bottom {
            width: 90%;
            margin: 0 auto;
        }
    </style>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item" href="javascript:void(0);" id="vote_baseinfo" data-voteid="${voteId!}" test="r1">基本设置</a>
                <a class="nav_item" href="javascript:void(0);" id="vote_publish" data-voteid="${voteId!}" test="r2">发布设置</a>
                <a class="nav_item current" href="javascript:void(0);" id="vote_generate" data-voteid="${voteId!}" test="r3">生成地址</a>
            </div>
        </div>
    </div>
</div>

<#include "./url.ftl">
<#--<div id="container" class="g_container">-->
    <#--<div class="survey_background_wrap" style="height: 872px; background-position: 50% 50%;"></div>-->
    <#--<div class="editor_container" style="display: block;">-->
        <#--<!-- 主体 &ndash;&gt;-->
        <#--<div class="editor_main" style=" left: 0; top: 60px;">-->
            <#--<div class="survey_wrap">-->
                <#--<div class="survey_main" style="padding-top:0;">-->
                    <#--<div class="survey_container">-->
                        <#--<div class="page" data-pid="1" style="display: block;">-->


                            <#--<div class="toupiao-title"><h3>投票页链接地址</h3></div>-->
                            <#--<div class="question question_radio required">-->
                                <#--<div class="xuanxiang" style="width:90%;">-->
                                    <#--<div class="xuanxiang-input">-->
                                        <#--<input type="text" id="generated_url" class="toupiao-input" value="${url!}"/>-->
                                        <#--<span>-->
                                            <#--<a href="${url}" target="_blank" style="width:100px; background:#178FE5;color:#FFF;">打开链接</a>-->
                                        <#--</span>-->
                                    <#--</div>-->
                                    <#--<div class="xuanxiang-a">-->
                                        <#--此链接可以放到自定义菜单或微信文章的阅读原文-->
                                    <#--</div>-->
                                <#--</div>-->

                            <#--</div>-->
                            <#--<div class="toupiao-title"><h3>二维码</h3></div>-->
                            <#--<div class="question question_radio required">-->
                                <#--<div class="xuanxiang">-->
                                    <#--<div style="width: 300px;height:300px;border:1px solid gray;" id="vote_qrcode_container"></div>-->
                                    <#--<p>用微信扫描二维码</p>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="inner" style="text-align:center; margin-top:30px">-->
                                <#--<div class="row editor_control">-->
                                    <#--<a href="${ctx}/cms/vote/list.php" style="background:#e9e9e9;"-->
                                       <#--class="btn btn_small btn_default">返回首页</a>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

</body>
<script data-main="${ctx}/js/abc/cms/vote/generate.js" src="${ctx}/js/require.js"></script>
</html>