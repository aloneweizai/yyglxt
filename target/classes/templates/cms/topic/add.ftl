<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/zoom.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="_topic_add_form" action="" enctype="multipart/form-data" method="post"
          data-validator-option="{theme:'yellow_bottom', timely:1}">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
            <#if !(topicDto??)>
                <#assign topicDto={}>
            </#if>
                <input id="topicId" name="topicId" value="${topicDto.topicId!}" type="hidden">
                <input name="modelId" value="" type="hidden">
                <#--<input name="siteDomain" value="${siteDomain!}" type="hidden">-->
                <tr>
                    <#--<td width="90"> 包含栏目:</td>-->
                    <#--<td>-->
                        <#--<input type="hidden" name="channelId" value="${topicDto.channelId!}"-->
                               <#--style="width:250px;margin-right:10px;">-->
                        <#--<input type="text" style="width:250px;margin-right:10px;" data-rule="required" name="text_topic_channelId"-->
                               <#--readonly="readonly" value="${channelName!}" placeholder="请选择栏目">-->
                        <#--<button style="height:26px;line-height:13px;" name="_channel_btn" type="button"-->
                                <#--<#if (topicDto.topicId!)!="">disabled="disabled"</#if> class="layui-btn">选择-->
                        <#--</button>-->
                        <#--<span class="color_r2">*</span>-->
                    <#--</td>-->
                    <td width="90">名称：</td>
                    <td colspan="3"><input class="required" maxlength="100" value="${topicDto.topicName!}" style="width:50%;"
                               data-rule="required;length[1~50]" name="topicName" placeholder="请输入专题名称" type="text"
                               required><span
                            class="color_r2">*</span></td>
                </tr>
                <tr>
                    <td width="90">所属站点：</td>
                    <td>
                        <select id="siteId" name="siteId" style="width:240px" data-rule="required">
                        <#if (topicDto.siteId!)=="">
                            <option value="">请选择站点</option>
                        </#if>

                        <#list siteList as site>
                            <#if (site.siteStatus!)=="1">
                                <#if (topicDto.siteId!)=="">
                                    <option <#if (topicDto.siteId!)==(site.siteId!)>selected="selected"</#if>
                                            data-sitepath="${site.sitePath!}" data-domain="${site.domain!}"
                                            value="${site.siteId!}">${site.siteName!}</option>
                                <#elseif (topicDto.siteId!)!="" && (topicDto.siteId!)==(site.siteId!)>
                                    <option <#if (topicDto.siteId!)==(site.siteId!)>selected="selected"</#if>
                                            data-sitepath="${site.sitePath!}" data-domain="${site.domain!}"
                                            value="${site.siteId!}">${site.siteName!}</option>
                                </#if>
                            </#if>
                        </#list>
                        </select>
                        <span class="color_r2">*</span>
                    </td>
                    <td>简称：</td>
                    <td><input class="required" maxlength="100" value="${topicDto.shortName!}" name="shortName"
                               placeholder="请输入专题简称" type="text"></td>
                </tr>
                <tr>
                    <td>描述:</td>
                    <td colspan="3">
                        <div id="_topic_description_area" name="description">${topicDto.description!}</div>
                    </td>
                </tr>
                <tr>
                    <td>推荐：</td>
                    <td>
                        <label><input value="1" <#if ((topicDto.isRecommend!)=="1")>checked="checked"</#if>
                                      name="isRecommend" type="radio">&nbsp;是</label>&nbsp;&nbsp;
                        <label><input value="0"
                                      <#if ((topicDto.isRecommend!)=="0" || !topicDto.isRecommend??)>checked="checked"</#if>
                                      name="isRecommend" type="radio">&nbsp;否</label></td>
                    <td>排序：</td>
                    <td>
                        <input class="reuqired digits" maxlength="255" data-rule="required"
                               value="${topicDto.priority???string((topicDto.priority!),"10")}" name="priority"
                               size="10" type="text">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td>模板：</td>
                    <td>
                        <select name="tplContent" style="width:240px" data-rule="required">
                            <option value="">请选择模版</option>
                        <#list fileTplList as tmpl>
                            <option <#if (topicDto.tplContent!)==(tmpl.templatePath!)>selected="selected"</#if>
                                    value="${tmpl.templatePath!}">${tmpl.templateName!}</option>
                        </#list>
                        </select>
                        <span class="color_r2">*</span>
                    </td>
                    <td>关键字:</td>
                    <td><input maxlength="255" name="keywords" size="35" value="${topicDto.keywords!}" type="text"
                               placeholder="请输入专题关键字"></td>
                </tr>
                <tr>
                    <td>标题图:</td>
                    <td colspan="3">
                        <input type="hidden" name="titleImg" value="${topicDto.titleImg!}">
                        <img data-action="zoom"
                             src="${((topicDto.titleImg!)=="")?string("",(siteDomain!))}${((topicDto.titleImg!)=="")?string(ctx+"/images/default.jpg",(topicDto.titleImg!))}"
                             width="80" height="80" style="margin-right:20px;">
                        <input id="upload_title_img" name="img_titleImg" class="layui-input" data-type="img"
                               style="width: 250px;display:inline-block;margin-right:10px;"
                               value="${topicDto.titleImg!}"
                               type="file">
                        <button style="height:26px;line-height:13px;" name="_img_upload_btn" type="button" class="layui-btn">上传
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>内容图:</td>
                    <td colspan="3">
                        <input type="hidden" name="contentImg" value="${topicDto.contentImg!}">
                        <img data-action="zoom"
                             src="${((topicDto.titleImg!)=="")?string("",(siteDomain!))}${((topicDto.contentImg!)=="")?string(ctx+"/images/default.jpg",(topicDto.contentImg!))}"
                             width="80" height="80" style="margin-right:20px;">
                        <input id="upload_content_img" name="img_contentImg" class="layui-input"
                               style="width: 250px;display:inline-block;margin-right:10px;"
                               value="${topicDto.contentImg!}"
                               type="file">
                        <button style="height:26px;line-height:13px;" name="_img_upload_btn" type="button"
                                class="layui-btn">上传
                        </button>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button" name="submit_btn" value="提交" class="layui-btn">
                        <input type="button" name="back_btn" value="返回" class="layui-btn">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/topic/add.js" src="${ctx}/js/require.js"></script>
<script type="text/html" id="topic_template_list_tmpl">
    <option value="">请选择</option>
    {{each list value i}}
    <option value="{{value.templatePath}}">{{value.templateName}}</option>
    {{/each}}
</script>
</html>