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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
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
                <a class="nav_item current" href="${ctx}/cms/activity/tj/${id!}">活动统计</a>
                <a class="nav_item " href="${ctx}/cms/activity/tg/${id!}">活动推广</a>
            </div>
            <div class="survey_options published" style="display: block;">
                <a href="javascript:;" id="publish_survey"  class="btn btn_middle btn_blue btn_start"><i></i>返回</a>
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
                            <div class="toupiao-title"><h3>浏览统计</h3></div>
                            <div class="question question_radio required" >
                                <table class="table-zy">
                                    <tr>
                                        <th>统计时间</th>
                                        <td>
                                            <input type="text" data-type="date" name="begintime" id="begintime" data-rule="required;" style=" width:130px;" >-<input type="text" data-type="date" name="endtime" id="endtime" data-rule="required;" style=" width:130px;" >&nbsp;<select id="type">
                                            <option value="1">日</option>
                                            <option value="2">月</option>
                                            <option value="3">年</option>
                                        </select>
                                        </td>
                                        <td>
                                            <a href="javascript:;" id="editor_confirm_btn" abc-id="${id!}" class="btn btn_small btn_blue btn_confirm">查询</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-12 ny_statis">
                                <div id="tjday" style="width: 100%"></div>
                            </div>
                            <div class="toupiao-title"><h3>浏览统计</h3></div>
                            <div class="col-md-12 ny_statis">
                                <div id="tjday1" style="width: 100%"></div>
                            </div>
                            <input type="hidden" id="id" value="${id!}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cms/activity/activitytj" src="${ctx}/js/require.js"></script>
</body>
</html>