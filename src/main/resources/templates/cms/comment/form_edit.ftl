<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <script src="${ctx}/../plugins/ckeditor/ckeditor.js"></script>
    <script src="${ctx}/../plugins/ckeditor/samples/js/sample.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script src="${ctx}/js/ny_ht.js"></script>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--评论管理修改-->
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <table class="table  table-hover">
        <tr>
            <td height="30">&nbsp;当前位置：&nbsp;<a href="${ctx}/cms/comment/list.php"><u>评论管理</u></a> &gt;&gt; 更改评论</td>
        </tr>
    </table>
    <form name="commentForm" id="commentForm">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="commentId" id="commentId" value="${comment.commentId!}">
            <tr>
                <td width="90">标题:</td>
                <td colspan="3">${comment.title!}</td>
            </tr>
            <tr>
                <td width="90">用户名:</td>
                <td>
                <#if comment.commentUserId?exists>
                        ${comment.commentUserId!}
                    </#if>
                </td>
                <td> 评论时间:</td>
                <td>${comment.createTime?string("yyyy-MM-dd HH:mm:ss")}
                </td>
            </tr>
            <tr>
                <td>审核:</td>
                <td><label><input value="1" name="isChecked" type="radio"  <#if comment.isChecked==1> disabled="disabled" </#if> <#if comment.isChecked==1>checked</#if>>
                    是
                </label>

                    <label>
                        <input value="0" name="isChecked" type="radio" <#if comment.isChecked==1> disabled="disabled" </#if>  <#if comment.isChecked==0>checked</#if>>
                        否
                    </label>
                </td>
                <td>推荐：</td>
                <td><label>
                    <input value="1" name="isRecommend" type="radio" <#if comment.isRecommend==1>checked</#if>>
                    是
                </label>
                    <label>
                        <input value="0" name="isRecommend" type="radio" <#if comment.isRecommend==0>checked</#if>>
                        否
                    </label></td>
            </tr>
            <tr>
                <td>评论：</td>
                <td colspan="3">
                    <textarea cols="70" rows="5" name="text" id="text"
                              class="color_9" readonly="readonly">${commentExt.text!}</textarea>
                </td>
            </tr>

            <tr id="huifu" style="<#if comment.isChecked==0> display: none;</#if>">
                <td>回复：</td>
                <td colspan="3">
                    <textarea cols="70" rows="5" name="reply" id="reply"
                              class="color_9"><#if commentExt.reply?exists>${commentExt.reply!}</#if></textarea>
                    <input type="hidden" name="replyCount" value="${comment.replyCount!}">
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <input type="button" name="commitBtn" id="commitBtn" value="提交" class="layui-btn">
                    <#--<input type="button" name="reset" id="reset" value="重置" class="layui-btn layui-btn-primary">-->
                </td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/comment" src="${ctx}/js/require.js"></script>
</body>
</html>