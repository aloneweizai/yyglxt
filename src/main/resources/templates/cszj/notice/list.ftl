<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > td {
            text-align: left;
        }
    </style>
    <!--评论管理-->
</head>
<style type="text/css">
    #_content_list_form th, #_content_list_form td {
        text-align: center;
    }

    .ny_tab_t > tbody > tr > td, .ny_tab_t > tr > td {
        padding: 3px 3px;
    }

    .cxtd1 {
        text-align: right;
        width: 80px;
    }

    .cxtd2 {
        text-align: left;
        width: 150px;
    }

    .cxinput {
        width: 140px;
        height: 30px;
    }
</style>
<body>
<div class="container-fluid m_top_30 nycol_list operator">
    <form action="${ctx}/cszjs/notice/list.php" method="post" id="noticeListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">通告标题</label>
                    <div class="layui-input-inline">
                        <input type="text" id="query_title" name="title" value="${(BaseRq.title)!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发布状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="cxinput" >
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status=="0">selected</#if> value="0">草稿</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="1">selected</#if> value="1">已发布</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="queryBtn" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/cszjs/notice/addPage.php"  class="layui-btn layui-btn-normal fr">新增</a>
            </div>
        </div>
    </form>
    <form action="" name="_notice_list_form" method="post">
        <div class=" nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>通告标题</th>
                    <th>通告来源</th>
                    <th>作者</th>
                    <th>状态</th>
                    <th>发布时间</th>
                    <th>修改时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if noticeList??>
                <#list noticeList as notice>
                <tr>
                    <td>${(pagination.size!)?number*((pagination.page!)?number-1)+notice_index+1}</td>
                    <td style='width:400px;'>${notice.title!}</td>
                    <td>${notice.comefrom!}</td>
                    <td>${notice.author!}</td>
                    <td>
                        <#if notice.status?? && notice.status == '1'>
                            已发布
                        <#else>
                            草稿
                        </#if>
                    </td>
                    <td>${notice.releaseTime!}</td>
                    <td>${notice.lastUpdate!}</td>
                    <td>
						<a data-type="opendialog" href="javascript:;" data-url="${ctx}/cszjs/notice/addlook.php?noticeId=${notice.id}">预览</a>|
                        <#if notice.status?? && notice.status == '1'>
                            <a data-type="delSig" data-confirm="确认撤销该通知公告?" data-okMsg="撤销成功!" data-failMsg="撤销失败"
                               href="javascript:void(0);"
                               data-href="${ctx}/cszjs/notice/noticecx.php?id=${notice.id}&status=0"
                               class="pn-opt">撤销</a>
                        <#else>
                            <a href="${ctx}/cszjs/notice/addPage.php?noticeId=${notice.id!}">修改</a>
                            |&nbsp;<a data-type="delSig" data-confirm="确认删除该通知公告?" data-okMsg="删除成功!"
                                      data-failMsg="删除失败" href="javascript:void(0);"
                                      data-href="${ctx}/cszjs/notice/delete.php?id=${notice.id}" class="pn-opt">删除</a>&nbsp;
                        </#if>
                    </td>
                </tr>
                </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">
    <input type="hidden" value="${ctx}/cszjs/notice/list.php?title=${(BaseRq.title)!}&&status=${(BaseRq.status)!}" id="currLink">
		
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"  style="width: 100%;height: 650px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/cszj/notice.js" src="${ctx}/js/require.js"></script>
</body>
</html>