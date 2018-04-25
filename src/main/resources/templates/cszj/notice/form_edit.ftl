<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--评论管理修改-->
</head>

<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
<#--<table class="table  table-hover">
    <tr>
        <td height="30">&nbsp;当前位置：&nbsp;<a href="${ctx}/cszjs/notice/list.php">
                <u>通知公告</u>
            </a> &gt;&gt; 新增通告</td>
    </tr>
</table>-->
    <form name="notice_add_form" action="" enctype="multipart/form-data" method="post"
          next-url="${ctx}/cszjs/notice/list.php">
        <table class="layui-table" lay-size="sm">
        <#if noticeDto??>
            <input type="hidden" id='noticeId' name="noticeId" value="${noticeDto.id!}"/>
            <tr>
                <td width="90">通告标题</td>
                <td colspan="3"><input type="text" id="title" name="title" class="layui-input" style="width:330px;"
                                       data-rule="required;length[1~64]" value="${noticeDto.title!}"></td>
                <td></td>
            </tr>
            <tr>
                <td width="90">通告来源</td>
                <td colspan="3"><input type="text" id="comefrom" name="comefrom" class="layui-input" style="width:330px;"value="${noticeDto.comefrom!}"></td>
            </tr>
            <tr>
                <td width="90">作者</td>
                <td colspan="3"><input type="text" name="author" id="author" class="layui-input" style=" width:330px;" data-rule="required;length[1~64]" value="${noticeDto.author!}"></td>
            </tr>
            <tr>
                <td>内容:</td>
                <td colspan="3">
                    <div id="_topic_description_area" name="content" id="content">${noticeDto.content!}</div>
                </td>
            </tr>
        <#else>
            <tr>
                <td width="90">通告标题</td>
                <td colspan="3"><input type="text" id="title" name="title" class="layui-input" style="width:330px;"
                                       data-rule="required;length[1~64]"></td>
            </tr>
            <tr>
                <td width="90">通告来源</td>
                <td colspan="3"><input type="text" id="comefrom" name="comefrom" class="layui-input" style="width:330px;"></td>
            </tr>
            <tr>
                <td width="90">作者</td>
                <td colspan="3"><input type="text" name="author" id="author" class="layui-input" style=" width:330px;" data-rule="required;length[1~64]"></td>
            </tr>
            <tr>
                <td>内容:</td>
                <td colspan="3">
                    <div id="_topic_description_area" name="content" id="content"></div>
                </td>
            </tr>
        </#if>
            <tr>
                <td></td>
                <td colspan="3">
                    <input type="hidden" style="width: 100px;" id="noticeTitle" value="${(NoticeRq.title)!}">
                    <input type="hidden" style="width: 100px;" id="status" value="${(NoticeRq.status)!}">
                    <input type="hidden" style="width: 100px;" id="page" value="${(NoticeRq.page)!}">
                    <input type="hidden" style="width: 100px;" id="size" value="${(NoticeRq.size)!}">
                    <input type="submit" name="notice_submit_btn" id="notice_submit_cg" status-val="0" value="草稿" disabled
                           class="layui-btn layui-btn-disabled">
                    <input type="submit" name="notice_submit_btn" id="notice_submit_fb" status-val="1" value="发布" disabled
                           class="layui-btn layui-btn-normal layui-btn-disabled">
                <#--<input type="reset" name="content_reset_btn"id="content_reset_btn" value="重置" class="layui-btn layui-btn-primary">-->
                    <a style="text-decoration:none;" id="back" class="layui-btn layui-btn-primary layui-btn-disabled" disabled="disabled">返回</a></td>
                    <#--<input type="button" name="back" id="back" value="返回" class="layui-btn layui-btn-primary">-->
                </td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/cszj/noticeedit.js" src="${ctx}/js/require.js"></script>
</body>
</html>