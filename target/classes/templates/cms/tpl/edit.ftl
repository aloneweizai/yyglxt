<#--文件名称：<input type="text" id="tplPathName_mod" value="${filePathName}" readonly="readonly"/><br>-->
<div style="padding:5px; border-bottom:1px solid #ddd;">
    <span>文件名称：</span><span type="text">${root}/${filePathName}</span>
    <input type="hidden" id="tplPathName_mod" value="${filePathName}"/>
    <input type="hidden" id="siteId_mod"/>

    <#--<div style="float: right; margin-top:-20px">-->
        <#--<a href="javascript:void(0)" name="selectTagBtn" id="selectTagBtn" class="btn btn-success">选择标签</a>-->
    <#--</div>-->

</div>
<#if fullText??>
    <textarea id="editor" style="width:800px;height:600px;"> ${fullText}</textarea>
<#else>
    <div style="text-align:center;font-size:16px;margin-top:6px;">没找到该文件</div>
</#if>
<div style="text-align:right; width: 800px;">
    <#if fullText??>
        <input type="button" class="layui-btn layui-btn-normal" id="updateTplBtn" value="保存模板"">
        <input type="button" class="layui-btn" id="recordVersionTplBtn" value="保存并记录版本">
    </#if>
    <input type="button" class="layui-btn layui-btn-primary" id="backToFileListBtn" value="返回">
</div>

