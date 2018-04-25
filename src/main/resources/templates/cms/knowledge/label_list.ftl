<form class="layui-form" style="padding-left: 10px;padding-top: 10px;padding-right: 10px;">
    <style>
        .layui-form-checkbox{
            margin-bottom:10px;}
        .layui-form-checkbox span{
            color: #000000;
        }
    </style>
    <#if tagList??&&(tagList?size>0)>
        <#list tagList as list>
            <input type="checkbox" name="updatelabel" title="${list.name!""}" value="${list.id!""}" >
        </#list>
    </#if>
    <div style="position:fixed;bottom:0; width: 100%; height: 40px; text-align: center; font-size: 16px; ">
        <button class="layui-btn" type="button" id="bqsave">保存</button>
        <button class="layui-btn" type="button" id="quxiao_">取消</button>
    </div>
</form>