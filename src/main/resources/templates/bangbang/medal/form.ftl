<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var imgUrl="${imgUrl!}";var ctx = "${ctx}";
</script>

<form id="medal_form" action="${ctx}/bangbang/questionMedal/save.php">
    <input type="hidden" name="status" <#if medal?? && medal.status>value ="${(medal.status?c)}"<#else>value="true"</#if>/>
    <input type="hidden" name="image" value="${(medal.image)!}"/>
    <input type="hidden" name="id" value="${(medal.id)!}"/>
    <div class="layui-container container">
        <div class="layui-row row">
            <div class="layui-col-md2 md md">
                <span>勋章图片</span>
            </div>
            <div class="layui-col-md2 md md">
                <div class="Image">
                    <img class="img" id="medalImage" <#if medal?? && medal.image??>src="${imgUrl}/${medal.image}"<#else>src="${ctx}/images/default.png"</#if>>
                </div>
            </div>
            <div class="layui-col-md3  md">
                <input name="uploadFile" data-type="jpg;jpeg;png;gif;bmp" id="uploadFile" type="file"><label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                <button type="button" class="layui-btn layui-btn-danger" id="upload_img_btn"><i class="layui-icon"></i>上传图片</button>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>勋章名称</span>
            </div>
            <div class="layui-col-md8 md">
                <input type="text" name="name" value="${(medal.name)!}" lay-verify="title" autocomplete="off" placeholder="请输入勋章名称" class="layui-input">
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>勋章类别</span>
            </div>
            <div class="layui-col-md8 md">
                <select name="type" lay-verify="required" class="required layui-select">
                    <option value="">请选择</option>
                    <option value="personal" <#if medal?? && medal.type == "personal">selected</#if>>个人勋章</option>
                    <option value="faction" <#if medal?? && medal.type == "faction">selected</#if>>帮派勋章</option>
                </select>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>获取条件</span>
            </div>
            <div class="layui-col-md8 md">
                <textarea name="acquireCondition" placeholder="请输入获取条件" class="layui-textarea">${(medal.acquireCondition)!}</textarea>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>描述</span>
            </div>
            <div class="layui-col-md8 md">
                <textarea name="description" placeholder="请输入描述" class="layui-textarea">${(medal.description)!}</textarea>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md8 md">
                <div class="btn">
                    <button type="button" class="layui-btn js_save">保存</button>
                    <button type="button" class="layui-btn layui-btn-primary js_close">取消</button>
                </div>
            </div>
        </div>
    </div>
</form>