<form id="form3" class="layui-form" action="">
    <div class="layui-container container">
        <div class="layui-row row">
            <div class="layui-col-md2">标题：</div>
            <div class="layui-col-md10">${data.title!}</div>
        </div>
        <#if data.describ??>
            <div class="layui-row row">
                <div class="layui-col-md2">描述：</div>
                <div class="layui-col-md10">${data.describ!}</div>
            </div>
        </#if>
        <div class="layui-row row">
            <div class="layui-col-md2">内容：</div>
            <div class="layui-col-md10">${data.detail!}</div>
        </div>
    <#if data.cheatsImage??&&data.cheatsImage != "">
        <div class="layui-row row">
            <div class="layui-col-md2">图片：</div>
            <div class="layui-col-md10"><img height='126' width='206' style='margin-left:10px;max-width:206px' src='${imgPth!}${(data.cheatsImage)!}' /></div>
        </div>
    </#if>
    </div>
</form>