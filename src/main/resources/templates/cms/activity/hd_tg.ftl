<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<!-- saved from url=(0039)https://wj.qq.com/edit.html?sid=1422807 -->
<html lang="zh-cn" class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>活动管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link href="${ctx}/css/datetime/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/css/datetime/daterangepicker-bs3.css" />
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>

    <style>
        h3{display: block;
            font-size: 1.17em;
            -webkit-margin-before: 1em;
            -webkit-margin-after: 1em;
            -webkit-margin-start: 0px;
            -webkit-margin-end: 0px;
            font-weight: bold;}
        .table-zy{
            width:94%;
            margin: 0 auto;
        }
        .table-zy th{width:15%; text-align: right; padding-top:2px;}

        .page_edit .subtitle_item input[type=radio], .page_edit .option_item input[type=radio], .page_edit .subtitle_item input[type=checkbox], .page_edit .option_item input[type=checkbox] {margin-left: 0;}

        .toupiao-title{border-bottom:1px solid #ddd; width: 94%; margin: 0 auto; position: relative;}
        .toupiao-title i{ position:absolute; top:10px; right:20px; cursor: pointer;}
        .toupiao-input{margin: 0px 0 0 22px; height: 28px; line-height: 28px; width: 70%; padding: 5px;}
        .toupiao-textarea{margin: 0px 0 0 22px; height: 60px; line-height: 30px; width: 70%; padding: 5px;}
        .tuiguangfuzhi{display:inline-block; padding:0 5px; background: #00BCD4; vertical-align: top; color: #fff;}
        .survey_form_ui{margin-top:5px !important;}

        .option_text{margin-top:0px !important;}

        .beizhutishi{margin-left: 5px; color: #999;}

        .xuanxiang{width:80%; margin: 0 auto;}
        .xuanxiang-input{margin-top:10px;}
        .xuanxiang-input a{display: inline-block; width:50px; height: 26px;  text-align: center; background: #F0614E; color: #fff;;}
        .xuanxiang-a{padding-left:35px;}
        .xuanxiang-a a{padding: 0 5px;}

        .adjoined-bottom{width:90%; margin: 0 auto;}

        .shoujixinxi{width:40%; float: left; border: 1px solid #ddd; padding:10px; margin-left:20px;}
        .shoujixinxi a{width:47%; padding:2px 3%; margin: 1% ;border: 1px solid #ddd;  display: inline-block; text-align: center; background: #fff;}
        .shoujixinxi a:hover{color:#fff; background: #28A4C9;}


        .FancyForm{display:inline-block; padding:0px 4px; text-align: center; font-size:16px; border: 3px solid #ccc; margin-left:22px;}
        .mycard-plus{margin:5px 22px; border:1px solid #ddd; padding:5px 0 0 5px;}
        .mycard-plus a{ background:#DEEDFA; padding:2px 5px; margin:0 2px 5px 0; display: inline-block;}
        .plus-tag{display: inline-block; margin-left:10px;}
        .plus-tag a{ background:#DEEDFA; padding:4px 5px; margin:0; display: inline-block;}
        .plus-tag a span{display: inline-block;}
        .plus-tag a em{background:url(${ctx}/images/tagbg.png) no-repeat;}
        .plus-tag a em{display: inline-block; margin:5px 0 0 8px;width:13px;height:13px;overflow:hidden;background-position:-165px -99px;cursor:pointer;}
        .plus-tag a:hover em{background-position:-168px -63px;}

        .mdtable{border: 0; width:90%; margin:0 auto; border: 1px solid #ddd;margin-top:20px;}
        .mdtable th,td{ padding: 4px 8px;}
        .mdtable th{background: #f2f2f2;}
        .mdtable td{text-align: left;}
        .mdtable tr:hover{ background: #f7f7f7;}


    </style>


</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item " href="${ctx}/cms/activity/${id!}">基本信息</a>
                <a class="nav_item " href="${ctx}/cms/activity/hd_md_list.php?id=${id!}">活动名单</a>
                <a class="nav_item" href="${ctx}/cms/activity/tj/${id!}">活动统计</a>
                <a class="nav_item current" href="${ctx}/cms/activity/tg/${id!}">活动推广</a>
            </div>
            <div class="survey_options published" style="display: block;">
                <a  href="javascript:;" id="publish_survey"  class="btn btn_middle btn_blue btn_start"><i></i>返回</a>
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
                            <div class="toupiao-title"><h3>活动推广</h3></div>
                            <div class="question question_radio required" >
                                <table class="table-zy">
                                    <tr>
                                        <th>微信查看原文链接</th>
                                        <td>
                                            <input type="text" class="toupiao-input" id="wx_url" value="${url!}"/>
                                            <span>
													<a class="tuiguangfuzhi" href="javascript:;" fzid="wx_url" name="fz">复制</a>
												</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>嵌入报名链接</th>
                                        <td>
                                            <p style="margin-left:22px">样式效果：<a href="" style="">立即报名</a></p>
                                            <input type="text" class="toupiao-input" id="lijibaoming" value="${bmurl}"/>
                                            <span>
													<a class="tuiguangfuzhi" href="javascript:;" fzid="lijibaoming" name="fz">复制</a>
												</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>嵌入报名按钮</th>
                                        <td>
                                            <p style="margin:0 0 5px 22px">样式效果：<a href="" style="display:inline-block; padding:0px 5px; background: #009900; color: #fff;">立即报名</a></p>
                                            <textarea type="text" class="toupiao-textarea" id="stylexg"/>${bman}</textarea>
                                            <span>
													<a class="tuiguangfuzhi" href="javascript:;" fzid="stylexg" name="fz">复制</a>
												</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>嵌入报名表单</th>
                                        <td>
                                            <textarea type="text" class="toupiao-textarea" id="qrform"/>${bmform}</textarea>
                                            <span>
													<a class="tuiguangfuzhi" href="javascript:;" fzid="qrform" name="fz">复制</a>
												</span>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <div class="page" data-pid="1" style="display: block;">
                                <div class="toupiao-title"><h3>活动页二维码</h3></div>
                                <table style="width:90%; margin: 20px auto;">
                                    <tr>
                                        <td width="30%"><div style="width: 100%;" id="event_erweima"></div></td>
                                        <td width="70%" valign="top">
                                            <h4>二维码分享</h4>
                                            <p style="color:#999">这是活动的专属二维码，赶紧扫描分享给朋友们吧</p>
                                            <textarea type="text" class="toupiao-textarea" style="margin-left:0; width:100%"/></textarea>
                                            <p>
                                                <a class="tuiguangfuzhi" href="javascript:;" id="xiazaierweima">下载二维码</a>
                                                <a href="javascript:;" id="xiazai" style="display: none;"></a>
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script data-main="${ctx}/js/abc/cms/activity/activitytg" src="${ctx}/js/require.js"></script>
</body>
</html>