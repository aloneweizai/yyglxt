<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/zoom.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body>
<div class="container-fluid m_top_30 nycol_list_edit">
    <form id="site_manage_add_form" name="site_manage_add_form" action="${ctx}/content/site/insertOrUpdate.php"
          data-validator-option="{theme:'yellow_right', timely:1}" enctype="multipart/form-data" method="POST">

        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="siteId" value="${cmsSite.siteId!}">
            <tr>
                <td width="180">域名</td>
                <td>
                    <input  class="layui-input" name="domain" maxlength="50" size="30" data-rule="required;domain" value="${cmsSite.domain!}" type="text" style="width: 300px; float: left;"><span
                        class="color_r2">*</span>
                    <button style="height:26px;line-height:13px;" class="layui-btn" type="button" id="_site_checkurl">打开网页</button>
                </td>
            </tr>
            <!--非必填时样式-->
            <tr>
                <td>站点路径</td>
                <td>
                    <input class="layui-input" name="sitePath" maxlength="20" size="30" data-rule="required;length[0~20]"
                           value="${cmsSite.sitePath!}" <#if (cmsSite.siteId!)!="">readonly="readonly"</#if> type="text" style="width: 300px; float: left;"><span class="color_r2">*</span>
                </td>
            </tr>
            <tr>
                <td>站点名称</td>
                <td><input  class="layui-input" name="siteName" size="30" maxlength="100" data-rule="required;length[1~100]" value="${cmsSite.siteName!}" type="text" style="width: 300px; float: left;"><span
                        class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>站点本地化</td>
                <td><div style="width: 300px;">
                    <#--<input  class="layui-input" name="localeFront" size="30" maxlength="10" data-rule="length[0~10]" value="${cmsSite.localeFront!}" type="text">-->
                    <select style="width:218px;" class="layui-input" name="localeFront">
                        <option value="">请选择</option>
                        <option <#if (cmsSite.localeFront!)=="zh-CN">selected="selected"</#if> value="zh-CN">zh-CN</option>
                        <option <#if (cmsSite.localeFront!)=="en-US">selected="selected"</#if> value="en-US">en-US</option>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td>站点关键字</td>
                <td><textarea class="layui-textarea" name="keywords" data-rule="length[0~255]" maxlength="255" type="text">${cmsSite.keywords!}</textarea></td>
            </tr>
            <tr>
                <td>站点描述</td>
                <td><textarea class="layui-textarea" name="description" data-rule="length[0~255]" maxlength="255" type="text">${cmsSite.description!}</textarea></td>
            </tr>
            <tr>
                <td>站点Logo</td>
                <td>
                    <img data-action="zoom" src="<#if (cmsSite.siteLogo!)=="">${ctx}/images/default.jpg<#else>${"http://"+ (cmsSite.domain!) + (cmsSite.siteLogo!)}</#if>" height="80" style="margin-right:20px;">
                    <input class="layui-input" style="width: 250px;display:inline-block;margin-right:10px;" name="siteLogo_ipt" id="siteLogo_ipt" size="" value="" type="file">
                    <button style="height:26px;line-height:13px;" id="_file_upload_btn" type="button" class="layui-btn">上传</button>
                    <input type="hidden" name="siteLogo" value="${cmsSite.siteLogo!}">
                </td>
            </tr>
            <tr>
                <td>版权说明</td>
                <td><input  class="layui-input" name="copyrightExplain" size="30" maxlength="100" data-rule="length[0~100]" value="${cmsSite.copyrightExplain!}" type="text" style="width: 300px;"></td>
            </tr>
            <tr>
                <td>备案号</td>
                <td><input  class="layui-input" name="recordNum" size="30" value="${cmsSite.recordNum!}" maxlength="30" data-rule="length[0~30]" type="text" style="width: 300px;"></td>
            </tr>
            <tr>
                <td>资源是否自动同步</td>
                <td><div style="width: 300px;">
                    <select style="width:218px;" name="resourceSync" class="layui-input" >
                        <option value="0" <#if cmsSite.resourceSync?exists && cmsSite.resourceSync=="0">selected="selected"</#if> >否</option>
                        <option value="1" <#if cmsSite.resourceSync?exists && cmsSite.resourceSync=="1">selected="selected"</#if>>是</option>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td>静态页是否自动同步</td>
                <td><div style="width: 300px;">
                    <select class="layui-input" name="staticSync">
                        <option value="0" <#if cmsSite.resourceSync?exists && cmsSite.staticSync=="0">selected="selected"</#if>>否</option>
                        <option value="1" <#if cmsSite.resourceSync?exists && cmsSite.staticSync=="1">selected="selected"</#if>>是</option>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td>静态发布</td>
                <td><div style="width: 300px;">
                    <select class="layui-input" name="staticIssue">
                        <option value="0" <#if cmsSite.resourceSync?exists && cmsSite.staticIssue=="0">selected="selected"</#if>>否</option>
                        <option value="1" <#if cmsSite.resourceSync?exists && cmsSite.staticIssue=="1">selected="selected"</#if>>是</option>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td>站点状态</td>
                <td><div style="width: 300px;">
                    <select class="layui-input" name="siteStatus">
                        <option value="0" <#if cmsSite.resourceSync?exists && cmsSite.siteStatus=="0">selected="selected"</#if>>停用</option>
                        <option value="1" <#if cmsSite.resourceSync?exists && cmsSite.siteStatus=="1">selected="selected"</#if>>启用</option>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td>第三方分享代码</td>
                <td>
                    <textarea name="shareCode" maxlength="500" class="layui-textarea">${cmsSite.shareCode!}</textarea>
                </td>
            </tr>
            <tr>
                <td>站点统计代码</td>
                <td>
                    <textarea name="statisticsCode"  class="layui-textarea" maxlength="500">${cmsSite.statisticsCode!}</textarea>
                </td>
            </tr>
            <tr>
                <td>站点声明</td>
                <td>
                    <textarea name="statement"  class="layui-textarea" maxlength="500">${cmsSite.statement!}</textarea>
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td><input type="button" id="submit_btn" value="提交" class="layui-btn">
                    <input type="button" id="back_btn" value="返回" class="layui-btn layui-btn-primary">
                </td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/siteManage/add" src="${ctx}/js/require.js"></script>
</body>
</html>