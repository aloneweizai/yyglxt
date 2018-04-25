<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<!-- saved from url=(0039)https://wj.qq.com/edit.html?sid=1422807 -->
<html lang="zh-cn"
      class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>活动管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link href="${ctx}/css/datetime/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/css/datetime/daterangepicker-bs3.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
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

        .mingdan-input {
            height: 28px;
            line-height: 28px;
            width: 60%;
            padding: 5px;
        }

        .mingdan-button {
            background: #3ABB1C;
            color: #FFF;
            display: block;
            text-align: center;
            height: 28px;
            line-height: 28px;;
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


    </style>


</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item" href="${ctx}/cszjs/msgauto/msgauto_btj.php">被添加自动回复</a>
                <a class="nav_item" href="${ctx}/cszjs/msgauto/msgauto_msg.php?setting=1">消息自动回复</a>
                <a class="nav_item current" href="${ctx}/cszjs/msgauto/msgauto_gjc.php">关键词自动回复</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">

    <div class="editor_container" style="display: block;text-align: center">
        <!-- 主体 -->
        <div class="editor_main" >

            <div class="survey_wrap" style="max-width: none;width: 99%">

                <div class="survey_main" style="padding-top:0;">

                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block; padding: 0 15px;" class="layui-form">
                            <div class="layui-form-top">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">关键字</label>
                                        <div class="layui-input-inline">
                                            <input id="keyString" value="${(pagination.keyString)!}" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">匹配类型</label>
                                        <div class="layui-input-inline">
                                            <select name="" id="searchTp" style="width: 150px;height: 30px;" >
                                                <option value="">全部</option>
                                                <option value="ALL"<#if (pagination.searchTp)! == "ALL">selected="selected"</#if> >全匹配</option>
                                                <option value="PART" <#if (pagination.searchTp)! == "PART">selected="selected"</#if> >模糊匹配</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <div class="layui-input-inline" >
                                            <button id="querybtn" class="layui-btn">查询</button>

                                        </div>
                                    </div>
                                    <a href="${ctx}/cszjs/msgauto/addPage.php" class="layui-btn layui-btn-normal" style="float: right">新增</a>
                                </div>
                            </div>

                            <table class="layui-table" lay-size="sm">
                                <tr>
                                    <th>序号</th>
                                    <th>关键词</th>
                                    <th>排序</th>
                                    <th >匹配类型</th>
                                    <th>回复类型</th>
                                    <th style="width: 500px">回复内容</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>

                            <#if gjcList?? && ( gjcList?size gt 0 )>
                                <#list gjcList as gjcBo>
                                    <tr>
                                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+gjcBo_index+1}</td>
                                        <td>${gjcBo.keyString!}</td>
                                        <td>${gjcBo.sort!}</td>
                                        <td>
                                            <#if (gjcBo.searchTp)! == "ALL">全匹配</#if>
                                            <#if (gjcBo.searchTp)! == "PART">模糊匹配</#if>
                                        </td>
                                        <td>
                                            <#if gjcBo.msgType??>
                                                <#if gjcBo.msgType == "text">
                                                文本
                                                <#elseif gjcBo.msgType == "image">
                                                图片
                                                <#elseif gjcBo.msgType == "news">
                                                图文
                                                </#if>
                                            </#if>
                                        </td>
                                        <td>
                                            <#if gjcBo.msgType == 'image'&& gjcBo.imgurl! !="">
                                                <img src="${gjcBo.imgurl}" style="height: 80px;">
                                            <#elseif gjcBo.msgType == 'image'>
                                                <img src="${ctx}/images/zanwu.png" style="height: 50;">
                                            <#elseif gjcBo.msgType == 'text'>
                                            ${gjcBo.content!}
                                            </#if>
                                        </td>
                                    <#-- <td>${gjcBo.content}</td>-->
                                        <td>${(gjcBo.lastUpdate?string("yyyy-MM-dd"))!}</td>
                                        <td>
                                            <a href="${ctx}/cszjs/msgauto/addPage.php?msgId=${gjcBo.id}">修改</a>
                                            |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!"
                                                      data-failMsg="删除失败" href="javascript:void(0);"
                                                      data-href="${ctx}/cszjs/msgauto/delete.php?id=${gjcBo.id}"
                                                      class="pn-opt">删除</a>&nbsp;

                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </table>
                            <#include "../../common/pagination.ftl">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cszj/msggjc.js" src="${ctx}/js/require.js"></script>
</body>
</html>