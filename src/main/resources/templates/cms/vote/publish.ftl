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
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
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
                <a class="nav_item" href="javascript:void(0);" id="vote_baseinfo" data-voteid="${voteDto.id!}" test="r1">基本设置</a>
                <a class="nav_item current" href="javascript:void(0);" id="vote_publish" data-voteid="${voteDto.id!}" test="r2">发布设置</a>
                <a class="nav_item " href="javascript:void(0);" id="vote_generate" data-voteid="${voteDto.id!}" test="r3">生成地址</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">

    <div class="survey_background_wrap" style="height: 872px; background-position: 50% 50%;"></div>

    <div class="editor_container" style="display: block;">

        <!-- 主体 -->
        <div class="editor_main" style=" left: 0; top: 60px;">
            <div class="survey_wrap">
                <div class="survey_main" style="padding-top:0;">
                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block;">
                            <form id="vote_publish_form" data-validator-option="{theme:'yellow_right', timely:1}">
                                <input type="hidden" name="id" value="${voteId!}">
                                <div class="toupiao-title"><h3>发布设置</h3></div>
                                <div class="question question_radio required"  style="display: none;">
                                    <input type="hidden" name="status" value="1">
                                    <#--<table class="table-zy">-->
                                        <#--<tr>-->
                                            <#--<th>启用投票</th>-->
                                            <#--<td>-->
                                                <#--<div style="margin-left:22px;">-->
                                                   <#--&lt;#&ndash; <div class="radio" style="margin-top:0px;">-->
                                                        <#--<label>-->
                                                            <#--<input type="radio" name="status"-->
                                                                   <#--value="true" <#if (voteDto.status)>checked="checked"</#if>><span-->
                                                                <#--style="padding-right:8px;font-weight: 600;">启用</span>-->
                                                        <#--</label>-->
                                                    <#--</div>&ndash;&gt;-->
                                                    <#--<div class="radio">-->
                                                        <#--<label>-->
                                                            <#--<input type="radio" name="status" checked="checked" value="false"><span-->
                                                                <#--style="padding-right:8px;font-weight: 600;">停用</span>-->
                                                        <#--</label>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</td>-->
                                        <#--</tr>-->
                                    <#--</table>-->
                                </div>
                                <div class="question question_radio required">
                                    <table class="table-zy">
                                        <tr>
                                            <th>页眉图片</th>
                                            <td>
                                                <div style="margin-left:20px;">
                                                    <input type="hidden" name="header" value="${voteDto.header!}">
                                                    <img src="${((voteDto.header!)!="")?string((hostUrl!)+(voteDto.header!),"")}" name="vote_publish_header" height="100" width="500"><br>
                                                    <button type="button" id="vote_publish_upload_btn">上传图片</button>
                                                    <input type="file" id="vote_publish_header" name="vote_publish_header" style="display:none;">
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="question question_radio required">
                                    <table class="table-zy">
                                        <tr>
                                            <th>页脚文字</th>
                                            <td>
                                                <div style="margin-left:20px;">
                                                    <textarea name="footer" style="width: 500px; height: 100px;" maxlength="100">${voteDto.footer!}</textarea><br>
                                                    <p>最多可以输入100个字符</p>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="toupiao-title"><h3>高级设置</h3></div>
                                <div class="question question_radio required">
                                    <table class="table-zy">
                                        <tr>
                                            <th>显示验证码(仅限PC显示)</th>
                                            <td>

                                                <div style="margin-left:22px;">
                                                    <div class="radio" style="margin-top:0px;">
                                                        <label>
                                                            <input type="radio" name="validateCode" <#if (voteDto.validateCode)>checked="checked"</#if>
                                                                   value="true"><span
                                                                style="padding-right:8px;font-weight: 600;">是</span>
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" name="validateCode" <#if !(voteDto.validateCode)>checked="checked"</#if>
                                                                   value="false"><span
                                                                style="padding-right:8px;font-weight: 600;">否</span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <#--<tr>-->
                                            <#--<td height="10px"></td>-->
                                        <#--</tr>-->
                                        <#--<tr>-->
                                            <#--<th>隐藏投票结果</th>-->
                                            <#--<td>-->

                                                <#--<div style="margin-left:22px;">-->
                                                    <#--<div class="radio" style="margin-top:0px;">-->
                                                        <#--<label>-->
                                                            <#--<input type="radio" name="hiddenResult" <#if (voteDto.hiddenResult)>checked="checked"</#if>-->
                                                                   <#--value="true"><span-->
                                                                <#--style="padding-right:8px;font-weight: 600;">是</span>-->
                                                        <#--</label>-->
                                                    <#--</div>-->
                                                    <#--<div class="radio">-->
                                                        <#--<label>-->
                                                            <#--<input type="radio" name="hiddenResult" <#if !(voteDto.hiddenResult)>checked="checked"</#if>-->
                                                                   <#--value="false"><span-->
                                                                <#--style="padding-right:8px;font-weight: 600;">否</span>-->
                                                        <#--</label>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</td>-->
                                        <#--</tr>-->
                                    </table>
                                </div>
                            </form>
                            <div class="inner" style="text-align:center; margin-top:30px">
                                <div class="row editor_control">
                                    <a href="javascript:void(0);" id="vote_publish_confirm_btn"
                                       class="btn btn_small btn_blue btn_confirm">保存设置</a>
                                    <a href="javascript:void(0);" id="vote_publish_back_btn" style="background:#e9e9e9;"
                                       class="btn btn_small btn_default">返回</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/vote/publish.js" src="${ctx}/js/require.js"></script>
</html>