<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<!-- saved from url=(0039)https://wj.qq.com/edit.html?sid=1422807 -->
<html lang="zh-cn"
      class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>消息自动回复</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link href="${ctx}/css/datetime/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/css/datetime/daterangepicker-bs3.css"/>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>

    <style>
        h3 {
            display: block;
            font-size: 1.17em;
            -webkit-margin-before: 1em;
            -webkit-margin-after: 1em;
            -webkit-margin-start: 0px;
            -webkit-margin-end: 0px;
            font-weight: bold;
        }

        .table-zy {
            width: 94%;
            margin: 0 auto;
        }

        .table-zy th {
            width: 15%;
            text-align: right;
            padding-top: 2px;
        }

        .page_edit .subtitle_item input[type=radio], .page_edit .option_item input[type=radio], .page_edit .subtitle_item input[type=checkbox], .page_edit .option_item input[type=checkbox] {
            margin-left: 0;
        }

        .toupiao-title {
            border-bottom: 1px solid #ddd;
            width: 94%;
            margin: 0 auto;
            position: relative;
        }

        .toupiao-title i {
            position: absolute;
            top: 10px;
            right: 20px;
            cursor: pointer;
        }

        .toupiao-input {
            margin: 0px 0 0 22px;
            height: 28px;
            line-height: 28px;
            width: 70%;
            padding: 5px;
        }

        .toupiao-textarea {
            margin: 0px 0 0 22px;
            height: 60px;
            line-height: 30px;
            width: 70%;
            padding: 5px;
        }

        .tuiguangfuzhi {
            display: inline-block;
            padding: 0 5px;
            background: #00BCD4;
            vertical-align: top;
            color: #fff;
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

        .shoujixinxi {
            width: 40%;
            float: left;
            border: 1px solid #ddd;
            padding: 10px;
            margin-left: 20px;
        }

        .shoujixinxi a {
            width: 47%;
            padding: 2px 3%;
            margin: 1%;
            border: 1px solid #ddd;
            display: inline-block;
            text-align: center;
            background: #fff;
        }

        .shoujixinxi a:hover {
            color: #fff;
            background: #28A4C9;
        }

        .FancyForm {
            display: inline-block;
            padding: 0px 4px;
            text-align: center;
            font-size: 16px;
            border: 3px solid #ccc;
            margin-left: 22px;
        }

        .mycard-plus {
            margin: 5px 22px;
            border: 1px solid #ddd;
            padding: 5px 0 0 5px;
        }

        .mycard-plus a {
            background: #DEEDFA;
            padding: 2px 5px;
            margin: 0 2px 5px 0;
            display: inline-block;
        }

        .plus-tag {
            display: inline-block;
            margin-left: 10px;
        }

        .plus-tag a {
            background: #DEEDFA;
            padding: 4px 5px;
            margin: 0;
            display: inline-block;
        }

        .plus-tag a span {
            display: inline-block;
        }

        .plus-tag a em {
            background: url(${ctx}/images/tagbg.png) no-repeat;
        }

        .plus-tag a em {
            display: inline-block;
            margin: 5px 0 0 8px;
            width: 13px;
            height: 13px;
            overflow: hidden;
            background-position: -165px -99px;
            cursor: pointer;
        }

        .plus-tag a:hover em {
            background-position: -168px -63px;
        }

        .mdtable {
            border: 0;
            width: 90%;
            margin: 0 auto;
            border: 1px solid #ddd;
            margin-top: 20px;
        }

        .mdtable th, td {
            padding: 4px 8px;
        }

        .mdtable th {
            background: #f2f2f2;
        }

        .mdtable td {
            text-align: left;
        }

        .mdtable tr:hover {
            background: #f7f7f7;
        }

        .noDiv_0 #articleDiv1{
            display: none;
        }
        .noDiv_0 #articleDiv2{
            display: none;
        }
        .noDiv_text #articleDiv0{
            display: none;
        }
        .noDiv_text #articleDiv2{
            display: none;
        }
        .noDiv_news #articleDiv0{
            display: none;
        }
        .noDiv_news #articleDiv1{
            display: none;
        }

    </style>


</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item" href="${ctx}/cszjs/msgauto/msgauto_btj.php">被添加自动回复</a>
                <a class="nav_item current" href="${ctx}/cszjs/msgauto/msgauto_msg.php?setting=1">消息自动回复</a>
                <a class="nav_item" href="${ctx}/cszjs/msgauto/msgauto_gjc.php">关键词自动回复</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">

    <div class="editor_container" style="display: block;">
        <!-- 主体 -->
        <div class="editor_main" >

            <div class="survey_wrap" style="max-width: none;width: 99%">

                <div class="survey_main" style="padding-top:0;">

                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block;">
                            <div class="question question_radio required">
                                <form name="consumer_edit" action="${ctx}/cszjs/msgauto/msgsave.php"
                                      next-url="${ctx}/cszjs/msgauto/list.php" method="post" class="layui-form">
                                    <table class="table">
                                        <tr>
                                            <td>
                                                <div >
                                                    <input type="hidden"id="keyId" value="${(msgautoDto.id)!}"/>
                                               <div  >
                                                        &nbsp;&nbsp;&nbsp;
                                                        <input type="radio" name="msgType" id="status0" ${((msgautoDto.msgType! == "0") ? string('checked="checked"',""))!}  value="0" title="禁用">
                                                        &nbsp;<input type="radio" name="msgType" id="status1"  value="text" ${((msgautoDto.msgType! == "text") ? string('checked="checked"',""))!} title="文本">
                                                        &nbsp;<input type="radio" name="msgType" id="status2" value="news" ${((msgautoDto.msgType! == "news") ? string('checked="checked"',""))!} title="图文">
                                                    </div>
                                                    <div id = "noDiv" class = "noDiv_${msgautoDto.msgType}">
                                                        <div id="articleDiv0"  >
                                                            <textarea style="width:91%; height: 120px; margin-left:22px;" name="summary" class="layui-textarea">［消息自动回复］已禁用，要开启请选择其他选项！</textarea>
                                                        </div>
                                                        <div id="articleDiv1" >
                                                            <textarea style="width:91%; height: 120px; margin-left:22px;" name="summary" class="layui-textarea"><#if msgautoDto.msgType! == "text">${msgautoDto.content}</#if></textarea></textarea>
                                                        </div>
                                                        <div id="articleDiv2"  >
                                                            <textarea style="width:91%; height: 120px; margin-left:22px;" name="summary" class="layui-textarea"><#if msgautoDto.msgType! == "news">${msgautoDto.content}</#if></textarea></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3"><button type="button" name="button" id="save_btn" class="layui-btn">提交</button></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script data-main="${ctx}/js/abc/cszj/msgauto.js" src="${ctx}/js/require.js"></script>
</body>
</html>