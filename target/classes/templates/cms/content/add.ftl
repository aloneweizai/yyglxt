<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/colpick.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/zoom.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-select.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="content_add_form" action="" enctype="multipart/form-data" method="post"
          data-validator-option="{theme:'yellow_top', timely:1}">
        <div class="nycon_list_b">

            <#include "content_model_tmpl.ftl">

                <table class="layui-table" lay-size="sm">
                <input type="hidden" name="modelId" value="${modelId!}" abc-custom="0">
                <input type="hidden" name="contentId" value="${contentId!}" abc-custom="0">
                <input type="hidden" id="view_input" value="${view!}" abc-custom="0">
                <#--<tr>-->
                    <#--<td width="150">栏目：</td>-->
                    <#--<td>-->
                        <#--<select style="width: 250px;" name="channelId" abc-custom="0">-->
                        <#--<#list columnList as column>-->
                            <#--<option value="${column.channelId!}">${column.channelName!}</option>-->
                        <#--</#list>-->
                        <#--</select>-->
                    <#--</td>-->
                <#--</tr>-->

                <#list modelItemList as modelItem>
                    <@model_tmpl_macro modelItem />
                </#list>
                <#--<tr>-->
                    <#--<td>-->
                        <#--<input type="text" name="filePath"><input type="text" name="fileName"><input type="file" name="file" id="file1" data-type="attachement"> <button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button>-->
                    <#--</td>-->
                    <#--<td>-->
                        <#--<input type="text" name="filePath"><input type="text" name="fileName"><input type="file" name="file" id="file2" data-type="attachement"> <button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button>-->
                    <#--</td>-->
                    <#--<td>-->
                        <#--<input type="text" name="filePath"><input type="text" name="fileName"><input type="file" name="file" id="file3" data-type="attachement"> <button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button>-->
                    <#--</td>-->
                <#--</tr>-->
                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button" name="content_submit_btn" value="发布" status-val="2" class="layui-btn">
                        <input type="button" name="content_submit_btn" value="保存草稿" status-val="0" class="layui-btn layui-btn-normal">
                        <#--<input type="button" name="content_reset_btn" value="重置" class="layui-btn layui-btn-primary">-->
                        <input type="button" name="content_back_btn" value="返回" class="layui-btn layui-btn-primary">
                    </td>
                </tr>
            </table>

        </div>


    </form>
</div>
</body>

<table>
<tr id="attachementInputTpl" hidden>
    <td style="padding:5px;">文件名称：<input type="text" name="fileName" data-rule="required;"></td>
    <td style="padding:5px;" hidden>文件路径：<input type="text" name="filePath" readonly></td>
    <td style="padding:5px;"><input type="file" name="file" id="file_0" data-type="attachement" abc-custom="1"></td>
    <td style="padding:5px;"><button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button></td>
    <td style="padding:5px;"><button name="_attachement_delete_btn" type="button" class="layui-btn">删除</button></td>
    <td style="padding:5px;" hidden><a name="fileUrl"></a></td>
</tr>
</table>

<script data-main="${ctx}/js/abc/cms/content/add.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
<script type="text/html" id="content_template_list_tmpl">
    <option value="">请选择</option>
    {{each list value i}}
    <option value="{{value.templatePath}}">{{value.templateName}}</option>
    {{/each}}
</script>
</html>

