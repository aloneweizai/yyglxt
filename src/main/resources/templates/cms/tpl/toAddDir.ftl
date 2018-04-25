<form name="addDirForm">
    <div style="width: 300px; margin:20px 0 0 20px;">
        <input type="hidden" name="parentPath">
        <input type="hidden" name="parentSiteId">

    <#--<div class="form-group">-->
    <#--<label>站点</label>-->
    <#--<select name="siteId" id="siteId" class="form-control" data-rule="required">-->
    <#--<option value="">--请选择--</option>-->
    <#--<#if (cmsSiteList)?? && (cmsSiteList?size > 0)>-->
    <#--<#list cmsSiteList as item>-->
    <#--<option value="${item.siteId}">${item.siteName}</option>-->
    <#--</#list>-->
    <#--</#if>-->
    <#--</select>-->
    <#--</div>-->

        <div class="form-group">
            <label>文件夹英文名称（例如：content）</label><label style='color:red;'>*</label>
            <input type="text" class="form-control" name="tplDirName" data-rule="required;szzm">
        </div>

        <div class="form-group">
            <label>文件夹中文名称（例如：内容）</label><label style='color:red;'>*</label>
            <input type="text" class="form-control" name="tplDirChineseName" data-rule="required;chinese">
        </div>

        <div class="form-group">
            <input type="button" name="dir_submit_btn" value="提交" class="layui-btn">
            <input type="button" class="layui-btn layui-btn-primary" id="backToFileListBtn" value="返回">
        </div>
    </div>
</form>