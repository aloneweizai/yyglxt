<form id="form3" class="layui-form" action="">
    <div class="layui-container container">
        <div class="layui-row row">
            <div class="layui-col-md2">标题：</div>
            <div class="layui-col-md10">${(data.title)!""}${(data.questionTitle)!""}${(data.cheatsTitle)!""}</div>
        </div>
        <#if data.describ??>
            <div class="layui-row row">
                <div class="layui-col-md2">描述：</div>
                <div class="layui-col-md10">${data.describ!}</div>
            </div>
        </#if>
        <#if data.detail??>
            <div class="layui-row row">
                <div class="layui-col-md2">详情：</div>
                <div class="layui-col-md10">${data.detail!}</div>
            </div>
        </#if>
        <#if data.answer??>
            <div class="layui-row row">
                <div class="layui-col-md2">回答内容：</div>
                <div class="layui-col-md10">${data.answer!}</div>
            </div>
        </#if>
        <#if data.commentTxt??>
            <div class="layui-row row">
                <div class="layui-col-md2">评论内容：</div>
                <div class="layui-col-md10">${data.commentTxt!}</div>
            </div>
        </#if>
    </div>
</form>