<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var imgUrl="${imgUrl!}";var ctx = "${ctx}";
</script>

<form id="expert_form" action="${ctx}/bangbang/questionExpert/save.php">
    <input type="hidden" id="form_username" value="${(expert.username)!}"/>
    <input type="hidden" name="userId" value="${(expert.userId)!}"/>
    <input type="hidden" name="status" <#if expert??>value ="${(expert.status)}"<#else>value="1"</#if>/>
    <input type="hidden" name="id" value="${(expert.id)!}"/>
    <input type="hidden" name="userImage" value="${(expert.userImage)!}"/>

    <div class="layui-container container">
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>用户名</span>
            </div>
            <div class="layui-col-md8 md">
                <input type="text" name="username" value="${(expert.username)!}" style="height: 30px">&nbsp;&nbsp;
                <button type="button" class="layui-btn layui-btn-normal js_valid_username">验证</button>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md md">
                <span>基本信息</span>
            </div>
            <div class="layui-col-md3  md">
                <ul  style="float: left">
                    <li>
                        用户名称：<span class="js_nickname">${(expert.nickname)!}</span>
                    </li>
                    <li>
                        真实姓名：<span class="js_realName">${(expert.realName)!}</span>
                    </li>
                    <li>
                        联系电话：<span class="js_phone">${(expert.phone)!}</span>
                    </li>
                </ul>
            </div>
            <div class="layui-col-md2 md md">
                <div class="Image">
                    <img class="img" id="userImage" <#if expert?? && expert.userImage??>src="${imgUrl}/${expert.userImage}"<#else>src="${ctx}/images/default.png"</#if>>
                </div>
            </div>
            <div class="layui-col-md3  md">
                <input name="uploadFile" data-type="jpg;jpeg;png;gif;bmp" id="uploadFile" type="file"><label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                <button type="button" class="layui-btn layui-btn-danger" id="upload_img_btn"><i class="layui-icon"></i>上传图片</button>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>专家类型</span>
            </div>
            <div class="layui-col-md3 md lg">
                <div style="margin: 0; width: 300px;" class="layui-input-block">
                    <select name="type" lay-verify="required" class="required" style="width: 140px;height: 30px">
                        <option value="">请选择</option>
                        <#list expertTypes as expertType>
                            <option value="${expertType.fieldValue}" <#if expert?? && expert.type == expertType.fieldValue!>selected</#if>>${expertType.fieldKey}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-col-md2 md">
                <span>从业时间(年)</span>
            </div>
            <div class="layui-col-md3 md lg">
                <input type="text" name="yearWork" value="${(expert.yearWork?c)!}" style="height: 30px">
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>擅长领域</span>
            </div>
            <div class="layui-col-md8 md">
                <textarea name="goodField" class="layui-textarea">${(expert.goodField)!}</textarea>
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>专家介绍</span>
            </div>
            <div class="layui-col-md8 md jd_editor">${(expert.intro)!}</div>
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