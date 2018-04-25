
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <script type="text/javascript"><#assign ctx=request.getContextPath()>var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/colpick.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-select.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">

</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink!}">
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <br/>
    <form name="form" action="${ctx}/cms/knowTag/save.php" method="post" >
        <input type="hidden" name="id" value="${(tag.id)!}">
        <#--<input type="hidden" name="status" value="<#if tag?? && tag.status??>${tag.status?string('true','false')}</#if>">-->
        <table class="layui-table" lay-size="sm">
            <tr>
                <td width="90">标签名称</td>
                <td colspan="3"><input type="text" value="${(tag.name)!}" name="name" style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
                <td></td>
            </tr>
            <tr>
                <td width="90">标签类型</td>
                <td colspan="3">
                    <select id="tagType" name="tagType" class="selectpicker" data-width="380px" multiple
                            data-multiple-separator=";"  data-none-selected-text="请选择">
                        <#list allTagTypeList as tt>
                            <#assign checkFlag=false>
                            <#if selectedTagTypeArray??>
                                <#list selectedTagTypeArray as opt>
                                    <#if (tt.fieldValue!) == opt>
                                        <#assign checkFlag=true>
                                        <#break >
                                    </#if>
                                </#list>
                            </#if>
                            <option <#if checkFlag>selected="selected"</#if> value="${tt.fieldValue!}">${tt.fieldKey!}</option>
                        </#list>
                    </select><label style='color:red;'>*</label>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>描述</td>
                <td colspan="3">
                    <textarea name="description" rows="3" cols="60" >${(tag.description)!}</textarea>
                </td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <input type="button" name="button" value="提交" class="js_save_btn layui-btn">
                    <a href="javascript:;" class="layui-btn layui-btn-primary js_back">返回</a>
                </td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/knowledge/tag/form.js?v=2" src="${ctx}/js/require.js"></script>
</html>