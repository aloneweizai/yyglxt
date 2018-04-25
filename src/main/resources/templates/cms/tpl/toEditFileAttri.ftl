<form name="eidtFileAttriForm">
    <div style="width: 300px; margin:20px 0 0 20px;">
        <input type="hidden" name="parentPath">
        <input type="hidden" name="templateId" value="${tplBo.templateId!}">



    <#--<div class="form-group">-->
    <#--<label>站点</label>-->
    <#--<select name="siteId" id="siteId" class="form-control">-->
    <#--<option value="">--请选择--</option>-->
    <#--<#if (cmsSiteList)?? && (cmsSiteList?size > 0)>-->
    <#--<#list cmsSiteList as item>-->
    <#--<option value="${item.siteId}" <#if item.siteId== tplBo.siteId> selected="selected"</#if>>${item.siteName}</option>-->
    <#--</#list>-->
    <#--</#if>-->
    <#--</select>-->
    <#--</div>-->
    <#--站点：<input type="text" name="siteId">-->

        <div class="form-group">
            <label>模型名称</label><label style='color:red;'>*</label>
            <select name="templateProperty" class="form-control">
                <option value="">--请选择--</option>
                <#if (models)?? && (models?size > 0)>
                    <#list models as item>
                        <option value="${item.modelId}" <#if item.modelId== tplBo.templateProperty> selected="selected"</#if> >${item.modelName}</option>
                    </#list>
                </#if>
            </select>
        </div>

    <#--模板属性：<input type="text" name="templateProperty">-->

        <div class="form-group" style="display:display">
            <label>模板页面类型</label>
            <select name="templateType" class="form-control">
                <option value="">--请选择--</option>
            <#if (tplType)?? && (tplType?size > 0)>
                <#list tplType as item>
                    <option value="${item.fieldValue}" <#if item.fieldValue== tplBo.templateType!> selected="selected"</#if> >${item.fieldKey}</option>
                </#list>
            </#if>
            </select>
        </div>

        <div class="form-group">
            <label>模板中文名称</label><label style='color:red;'>*</label>
            <input type="text" class="form-control" name="templateName" value="${tplBo.templateName!}">
        </div>

        <div class="form-group" style="display: none">
            <label>序号</label>
            <input type="text" name="priority" class="form-control" value="${tplBo.priority!}">
        </div>

        <div class="form-group">
            <input type="button" id="tplFile_edit_btn" value="提交" class="layui-btn">
            <input type="button" class="layui-btn layui-btn-primary" id="backToFileListBtn" value="返回">
        </div>
    </div>
</form>