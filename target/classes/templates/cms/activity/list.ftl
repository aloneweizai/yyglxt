<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .ny_tab_t span{ display: inline-block; text-align: right; margin-bottom: 10px;;}
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycol_list">
    <form action=" " method="post" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">活动标题</label>
                    <div class="layui-input-inline">
                        <input name="title" id="title" value="${title!}" type="text" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">活动状态</label>
                    <div class="layui-input-inline">
                        <select  class="cxinput" id="status" name="status">
                            <option value=""></option>
                        <#if hdzt??&&(hdzt?size>0)>
                            <#list hdzt as dict>
                                <option value="${dict.fieldValue}" <#if dict.fieldValue??&&status??&&dict.fieldValue==status>selected</#if>>${dict.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">活动类别</label>
                    <div class="layui-input-inline">
                        <select class="cxinput" id="category" name="category">
                            <option value=""class="cxinput" ></option>
                        <#if dict?? && (dict?size > 0) >
                            <#list dict as dict>
                                <option value="${dict.fieldValue}" <#if dict.fieldValue??&&category??&&dict.fieldValue==category>selected</#if>>${dict.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="query" class="layui-btn" type="button">查询</button>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>
                <a href="${ctx}/cms/activity/add.php" type="button" class="layui-btn layui-btn-normal fr">创建活动</a>
                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">活动时间起</label>
                        <div class="layui-input-inline">
                            <input type="text" id="begintime" name="begintime" value="${(begintime)!}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">活动时间止</label>
                        <div class="layui-input-inline">
                            <input type="text" id="endtime" name="endtime"  value="${(endtime)!}" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <#--<form action=" " method="post">-->
        <div class=" nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>活动标题</th>
                    <th>活动地点</th>
                    <th>活动时间</th>
                    <th>活动分类</th>
                    <th>活动形式</th>
                    <th>报名人数</th>
                    <th>活动状态</th>
                    <th>发布时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if event.dataList?? && (event.dataList?size > 0) >
                    <#list event.dataList as event>
                <tr>
                    <td>${event_index+1+(pagination.page-1)*pagination.size}</td>
                    <td>${event.title!}</td>
                    <td>${event.address!}</td>
                    <td>${(event.begintime?string("yyyy-MM-dd HH:mm:ss"))!}
                        <#if event.begintime??&&event.endtime??>
                            到
                        </#if>
                    ${(event.endtime?string("yyyy-MM-dd HH:mm:ss"))!}</td>

                    <td>
                        <#if event.category??>
                            <#list event.category?split(",") as category>
                                <#if dict?? && (dict?size > 0) >
                                    <#list dict as dict>
                                        <#if dict.fieldValue==category>
                                            ${dict.fieldKey!}
                                        </#if>
                                    </#list>
                                </#if>
                            </#list>
                        </#if>
                    <#--${event.category!}-->

                    </td>
                    <td>
                        <#if event.shape??>
                            <#list event.shape?split(",") as shape>
                                <#if dictxs?? && (dictxs?size > 0) >
                                    <#list dictxs as dict>
                                        <#if dict.fieldValue==shape>
                                            ${dict.fieldKey!}
                                        </#if>
                                    </#list>
                                </#if>
                            </#list>
                        </#if>
                    </td>
                    <td>${event.peoppleNum!}</td>
                    <td>
                        <#if event.status??&& event.status=='1'>

                            <div class="btn btn-warning btn-xs  ">草稿</div>
                        </#if>
                        <#if event.status??&&event.status=='2'>
                            <div class="btn btn-success btn-xs ">已发布</div>
                        </#if>
                        <#if event.status??&&event.status=='3'>

                            <div  class="btn btn-info btn-xs ">已结束</div>
                        </#if>
                        <#if event.status??&&event.status=='4'>

                            <div class="btn btn-danger btn-xs ">已撤销</div>
                        </#if>
                    </td>
                    <td>${(event.updatetime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    <td>
                        <#if event.status??&&event.status=='1'>
                            <a href="${ctx}/cms/activity/hd_md_list.php?id=${event.eventId!}"  >名单</a> | <a href="${ctx}/cms/activity/${event.eventId!}">编辑</a> | <a href="javascript:;" class="pn-opt" abc-type="delete"  delid="${event.eventId!}">删除</a>
                        </#if>
                        <#if event.status??&&event.status=='2'>
                            <a href="${ctx}/cms/activity/hd_md_list.php?id=${event.eventId!}" >名单</a> | <a href="${ctx}/cms/activity/tj/${event.eventId!}">统计</a> | <a href="javascript:;" class="pn-opt" abc-type="revoke" delid="${event.eventId!}">撤销</a>
                        </#if>
                        <#if event.status??&&event.status=='3'>
                            <a href="${ctx}/cms/activity/tj/${event.eventId!}">统计</a>  | <a href="javascript:;" class="pn-opt" abc-type="delete" delid="${event.eventId!}">删除</a>
                        </#if>
                        <#if event.status??&&event.status=='4'>
                            <a href="${ctx}/cms/activity/hd_md_list.php?id=${event.eventId!}" >名单</a> | <a href="${ctx}/cms/activity/${event.eventId!}">编辑</a> | <a href="javascript:;" class="pn-opt" abc-type="delete" delid="${event.eventId!}">删除</a>
                        </#if>
                    </td>
                </tr>
                    </#list>
                    </#if>
                </tbody>
            </table>
            <#--${pagination}-->
        <#include "../../common/pagination.ftl">
        </div>


    <#--</form>-->




</div>
<script data-main="${ctx}/js/abc/cms/activity/activitylist" src="${ctx}/js/require.js"></script>
</body>
</html>