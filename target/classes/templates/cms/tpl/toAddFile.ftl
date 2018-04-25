<form name="addFileForm">
    <div style="width: 300px; margin:20px 0 0 20px;">
        <input type="hidden" name="parentPath">
        <div class="form-group">
            <label>上传文件</label><label style='color:red;'>*</label><br>
            <input type="file" name="tplFile" id="tplFile" data-rule="required">
        <#--<p class="help-block">请选择需要上传的模板文件.</p>-->
        </div>
        <input type="hidden" name="siteId" id="siteId" value="${siteId!}"/>
    <#--<div class="form-group">-->
    <#--<label>站点</label>-->
    <#--<select name="siteId" id="siteId" class="form-control">-->
    <#--<option value="">--请选择--</option>-->
    <#--<#if (cmsSiteList)?? && (cmsSiteList?size > 0)>-->
    <#--<#list cmsSiteList as item>-->
    <#--<option value="${item.siteId}">${item.siteName}</option>-->
    <#--</#list>-->
    <#--</#if>-->
    <#--</select>-->
    <#--</div>-->

        <div class="form-group">
            <label>模型名称</label><label style='color:red;'>*</label>
            <select name="templateProperty" class="form-control">
                <option value="">--请选择--</option>
            <#if (models)?? && (models?size > 0)>
                <#list models as item>
                    <option value="${item.modelId}">${item.modelName}</option>
                </#list>
            </#if>
            </select>
        </div>

        <div class="form-group">
            <label>模板页面类型</label>
            <select name="templateType" class="form-control">
                <option value="">--请选择--</option>
            <#if (tplType)?? && (tplType?size > 0)>
                <#list tplType as item>
                    <option value="${item.fieldValue}">${item.fieldKey}</option>
                </#list>
            </#if>
            </select>
        </div>

        <div class="form-group">
            <label>模板中文名称</label><label style='color:red;'>*</label>
            <input type="text" class="form-control" name="templateName" data-rule="required;chinese">
        </div>


        <div class="form-group" style="display: none">
            <label>序号</label>
            <input type="text" name="priority" class="form-control" data-rule="integer">
        </div>
        <div class="form-group">
            <input type="button" name="tplFile_submit_btn" value="提交" class="layui-btn">
            <input type="button" class="layui-btn layui-btn-primary" id="backToFileListBtn" value="返回">
        </div>
    </div>
</form>