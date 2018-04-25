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
    <form id="msgauto_edit_form" action="${ctx}/cszjs/msgauto/addPagepost.php"   method="post" class="layui-form">
        <table class="layui-table" lay-size="sm">

            <input type="hidden" id='id' name="id" value="${(msgautoDto.id)!}"/>
            <tr>
                <td width="90">关键字：</td>
                <td colspan="3"><input type="text" id="keyString" name="keyString" class="layui-input" style="width:330px;"data-rule="required;length[1~64]" value="${(msgautoDto.keyString)!}">
                <span style=" font-size:12px; color: #999;">提示：多个关键词可以使用英文逗号","分隔,拒绝中文逗号"，"任意匹配项请将关键词设为"*"。</span>
                </td>
            </tr>
            <tr>
                <td width="90">匹配排序号：</td>
                <td colspan="3"><input type="text" name="sort" id="sort" style=" width:330px;"  class="layui-input" onkeyup="value=this.value.replace(/\D+/g,'')"
                                       data-rule="required;length[1~64]" value="${(msgautoDto.sort?c)!}"></td>
            </tr>
            <tr>
                <td width="90">匹配类型：</td>
                <td colspan="3">
                    <div style="width: 330px; float: left;">
                    <select name="searchTp" class="mingdan-input" id="searchTp">
                    <option value="ALL">全匹配</option>
                    <option value="PART" ${((msgautoDto.searchTp)! == "PART")?string('selected="selected"','')}>模糊匹配</option>
                </select></div></td>
            </tr>
            <tr>
                <td width="90">自动回复类型：</td>
                <td colspan="3"><div style="width: 330px; float: left;"><select name="msgType" class="mingdan-input" id="msgType">
                    <option value="text">文本</option>
                    <option value="image" ${((msgautoDto.msgType)! == "image")?string('selected="selected"','')}>图片</option>
                    <option value="news"${((msgautoDto.msgType)! == "news")?string('selected="selected"','')}>图文</option>
                </select></div><span style=" font-size:12px; color: #999; margin-left: 5px;">提示：全匹配是对话完全一致才触发，模糊匹配只要包含就触发，两种匹配均区分大小写。</span></td>
            </tr>
            <tr>
                <td>自动回复内容：</td>
                <td colspan="3">
                    <textarea type="text" style="height: 100px;" class="toupiao-textarea layui-textarea" id="content" name="content"/>${(msgautoDto.content)!}</textarea>
                </td>
            </tr>

            <tr>
                <td></td>
                <td colspan="3">
                    <button type="button" name="msgauto_submit_btn" id="save_btn" status-val="1" class="layui-btn">保存</button>
                    <button type="button" name="back" id="back" class="layui-btn layui-btn-primary">返回</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/cszj/msggjc.js" src="${ctx}/js/require.js"></script>
</body>
</html>