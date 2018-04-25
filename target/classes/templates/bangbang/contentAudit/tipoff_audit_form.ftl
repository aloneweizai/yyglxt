<form id="form3" class="layui-form" action="">
<div class="layui-container container">
    <div class="layui-row row">
        <div class="layui-col-md2">举报内容：</div>
        <div class="layui-col-md10">${data.content!}</div>
    </div>
    <div class="layui-row row">
        <div class="layui-col-md2">举报原因：</div>
        <div class="layui-col-md10">${data.reason!}</div>
    </div>
    <div class="layui-row row">
        <div class="layui-col-md2">举报人：</div>
        <div class="layui-col-md10">${data.createUsername!}</div>
    </div>
    <div class="layui-row row">
        <div class="layui-col-md2">举报时间：</div>
        <div class="layui-col-md10">${(data.createTime?string("yyyy-MM-dd"))!}</div>
    </div>
    <#if data.status == 'approved'>
        <div class="layui-row row">
            <div class="layui-col-md2">积分奖励：</div>
            <div class="layui-col-md10">${data.rewardsPoints!}</div>
        </div>
    </#if>
    <#if data.status == 'refuse'>
        <div class="layui-row row">
            <div class="layui-col-md2">拒绝原因：</div>
            <div class="layui-col-md10">${data.refuseReason!}</div>
        </div>
    </#if>
    <#if data.status == 'auditing'>
        <div class="layui-row row">
            <div class="layui-col-md2">操作：</div>
            <div class="layui-col-md10">
                <button data-id="${data.id}" class="layui-btn js_tipoff_approved">通过</button>
                <button data-id="${data.id}" class="layui-btn layui-btn-primary js_tipoff_refuse">拒绝</button>
            </div>
        </div>
    </#if>
</div>
</form>