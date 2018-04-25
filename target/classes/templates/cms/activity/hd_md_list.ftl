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
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script src="${ctx}/js/ny_ht.css"></script>
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
        .table-zy th{width:15%; text-align: right; vertical-align: top; padding-top:2px;}

        .page_edit .subtitle_item input[type=radio], .page_edit .option_item input[type=radio], .page_edit .subtitle_item input[type=checkbox], .page_edit .option_item input[type=checkbox] {margin-left: 0;}

        .toupiao-title{border-bottom:1px solid #ddd; width: 94%; margin: 0 auto; position: relative;}
        .toupiao-title i{ position:absolute; top:10px; right:20px; cursor: pointer;}
        .toupiao-input{margin: 0px 0 0 22px; height: 28px; line-height: 28px; width: 70%; padding: 5px;}
        .mingdan-input{ height: 28px; line-height: 28px; width: 100%; padding: 5px;}
        .mingdan-button{background: #3ABB1C; color:#FFF; display: block; text-align: center; height: 28px; line-height: 28px;;}
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
                <a class="nav_item current" href="${ctx}/cms/activity/hd_md_list.php?id=${id!}">活动名单</a>
                <a class="nav_item" href="${ctx}/cms/activity/tj/${id!}">活动统计</a>
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
                            <div class="toupiao-title"><h3>报名名单</h3></div>
                            <table class="table-zy">
                                <tr>
                                    <td><input class="mingdan-input" id="name" value="${name!""}"></td>
                                    <td><select name="" class="mingdan-input" id="status">
                                        <option value="">请选择</option>
                                        <option value="0" <#if status??&&status=='0'>selected = "selected"</#if>>待审核</option>
                                        <option value="1" <#if status??&&status=='1'>selected = "selected"</#if>>已审核</option>
                                        <option value="1" <#if status??&&status=='2'>selected = "selected"</#if>>拒绝审核</option>
                                    </select></td>
                                    <input type="hidden" id="mdid_" value="${id!}">
                                    <td><a href="javascript:void(0);" abc-id="${id!}" class="mingdan-button" id="querymd">查询</a></td>
                                    <td width="10%"></td>
                                    <td><a href="javascript:void(0);" class="mingdan-button" id="mdsh" abc-id="${id!}">审核</a></td>
                                    <td><a href="javascript:void(0);" class="mingdan-button" style="background:#D6655C" id="jjmdsh" abc-id="${id!}">拒绝审核</a></td>
                                    <td><a href="javascript:void(0);" class="mingdan-button" style="background:#D6655C" id="mddel" abc-id="${id!}">删除</a></td>
                                    <td><a href="javascript:;" class="mingdan-button" id="mdmsg" abc-id="${id!}">提醒</a></td>
                                    <td><a href="javascript:void(0);" class="mingdan-button" abc-id="${id!}" id="mddc">导出</a></td>
                                </tr>
                            </table>

                            <table class="mdtable" cellspacing="1" cellpadding="5" border="1">
                                <tr>
                                    <th><input type="checkbox" id="quanxuanmd"></th>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>手机</th>
                                    <th>邮箱</th>
                                    <th>状态</th>
                                    <th>报名时间</th>
                                </tr>

<#if eventApplyItemBo.dataList?? && (eventApplyItemBo.dataList?size > 0) >
    <#list eventApplyItemBo.dataList as eventApplyItemBo>
                                <tr>
                                    <td><input type="checkbox"   name="ids" value="${eventApplyItemBo.applyId!""}" userid="${eventApplyItemBo.userid!""}" status="${eventApplyItemBo.status}"></td>
                                    <td>${eventApplyItemBo_index+1}</td>
                                    <td>${eventApplyItemBo.name!""}</td>
                                    <td>${eventApplyItemBo.tel!""}</td>
                                    <td>${eventApplyItemBo.email!""}</td>
                                    <td><#if eventApplyItemBo.status=='1'>
                                        已审核
                                    </#if><#if eventApplyItemBo.status=='0'>
                                        待审核
                                    </#if><#if eventApplyItemBo.status=='2'>
                                        拒绝审核
                                    </#if></td>
                                    <td>${(eventApplyItemBo.applytime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                </tr>
    </#list>
</#if>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script data-main="${ctx}/js/abc/cms/activity/activitymd" src="${ctx}/js/require.js"></script>
</body>
</html>