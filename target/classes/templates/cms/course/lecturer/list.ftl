<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/lecturer.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <form class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">讲师姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="keywords" value="${RequestParameters["lecturerName"]!}" placeholder="请输入讲师姓名" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button type="button" class="layui-btn js-query">查询</button>
                    </div>
                </div>
                <a href="javascript:;" class="js_add layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>
    </form>
    <form class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="5%">序号</th>
                    <th width="6%" >讲师姓名</th>
                    <th>好评度</th>
                    <th width="35%" >讲师简介</th>
                    <th>创建时间</th>
                    <th>联系电话</th>
                    <th width="15%" >所在单位</th>
                    <th width="10%">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if lecturers?? && (lecturers?size > 0) >
                    <#list lecturers as lecturer>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + lecturer_index + 1}</td>
                        <td>${(lecturer.lecturerName)!}</td>
                        <td>
                            <#if lecturer.evaluateNum?? && lecturer.evaluateNum!=0>
                                ${(lecturer.praiseNum/lecturer.evaluateNum*100)?string("###.0")}%
                                <#--${((lecturer.praiseNum/lecturer.evaluateNum)?string.percent)}-->
                            <#else>
                                0
                            </#if>
                        </td>
                        <td>${(lecturer.intro)!}</td>
                        <td>${lecturer.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>${(lecturer.phone)!}</td>
                        <td>${(lecturer.company)!}</td>
                        <td>
                            <a data-href="${ctx}/cms/course/lecturer/edit.php?id=${lecturer.lecturerId}" class="js_edit pn-opt">编辑</a> |
                            <a data-href="${ctx}/cms/course/lecturer/delete/${lecturer.lecturerId}.php" class="js_delete pn-opt">删除</a>
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
    <div class="teacher-popup" >
    </div>
<div class="bg"></div>
<#--</div>-->
<script data-main="${ctx}/js/abc/cms/course/lecturer/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>