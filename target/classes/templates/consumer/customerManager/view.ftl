<script type="text/javascript"> <#assign ctx=request.getContextPath()>var imgUrl="${imgUrl!}"; </script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
<br>
<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 120px">姓名</label>
        <div class="layui-form-label">
            <span>${(manager.username)!}</span>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 120px">员工工号</label>
        <div class="layui-form-label">
            <span>${(manager.userId)!}</span>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 120px">联系电话</label>
        <div class="layui-form-label">
            <span>${(manager.phone)!}</span>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 120px">形象照片</label>
        <div class="layui-input-label">
            <img <#if manager?? && manager.userPicturePath?? && manager.userPicturePath!=''>src="${imgUrl}/${manager.userPicturePath}"<#else>src="${ctx}/images/default.png"</#if>  style='margin-left:10px;' height='90' width='90' alt="">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label" style="width: 120px">个人介绍</label>
        <div class="layui-form-label">
            ${(manager.intro)!}
        </div>
    </div>
</form>