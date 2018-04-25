<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var imgUrl="${imgUrl!}";var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink!}">
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="form_save" action="${ctx}/consumerManager/customerManager/save.php" method="post" class="layui-form">
        <input type="hidden" name="id" value="${(manager.id)!}">
        <input type="hidden" name="status" value="<#if manager??>${(manager.status)!}<#else>1</#if>">
        <table class="layui-table" lay-size="sm">
            <br>
            <tr>
                <td>姓名</td>
                <td style="width: 150px;">
                    <input name="username" value="${(manager.username)!}" type="text" class="layui-input" readonly>
                </td>
                <td colspan="3"><a href="javascript:void(0)" class="js_choose_btn m_left_10 layui-btn">选择</a></td>
            </tr>
            <tr>
                <td width="90">员工工号</td>
                <td colspan="4">
                    <input name="userId" value="${(manager.userId)!}" type="text" style="display: none">
                    <span id="userId">${(manager.userId)!}</span>
                </td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td colspan="4">
                    <input name="phone" value="${(manager.phone)!}" type="text" style="display: none">
                    <span id="phone">${(manager.phone)!}</span>
                </td>
            </tr>
            <tr>
                <td>形象照片</td>
                <td id="imgshow">
                    <img <#if manager?? && manager.userPicturePath?? && manager.userPicturePath !=''>src="${imgUrl}/${manager.userPicturePath}"<#else>src="${ctx}/images/default.png"</#if>  style='margin-left:10px;' height='90' alt="">
                </td>
                <td colspan="3">
                    <#--<p>课程封面图片支持jpg,gif,png格式,图片尺寸至少为480px*270px(像素),<br>文件大小不能超过200K</p>-->
                    <input type="file" id="uploadFile" name="uploadFile" data-type="jpg;jpeg;png;gif;bmp">
                    <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                    <button style="height:26px;line-height:13px;" id="upload_img_btn" type="button" class="layui-btn">上传</button>
                    <input type="hidden" name="userPicturePath" value="${(manager.userPicturePath)!}">
                </td>
            </tr>
            <tr>
                <td>个人介绍</td>
                <td colspan="4">
                    <textarea name="intro" rows="3" cols="60" class="n-valid layui-textarea" style="width: 500px;">${(manager.intro)!}</textarea>
                    <span class="msg-box n-right" for="description"></span>
                    <span class="color_r2">*</span>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="4">
                    <button type="button" class="js_save_btn layui-btn">提交</button>
                    <a href="${currLink!}" class="layui-btn layui-btn-primary js_back">返回</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<#if orgAll?? && (orgAll?size > 0) >
<div hidden>
    <ul>
        <#list orgAll as org>
            <li class="org_li" data-org-id="${org.id!}" data-org-pid="${org.parentId!}" data-org-name="${org.name!}"></li>
        </#list>
    </ul>
    <ul>
        <#list users as user>
            <li class="operator_li" data-user-id="${user.id!}" data-user-orgId="${user.orgId!}"
                data-user-name="${user.nickname!}" data-user-empId="${user.username!}"
                data-user-phone="${user.phone!}">
            </li>
        </#list>
    </ul>
</div>
</#if>
<div  class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-4">
        <div class="main_modal_t">请选择运营人员
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div style="overflow-y:scroll;  height:400px;" id="org_operator_tree" class="ztree"></div>
        </div>
        <input value="确认" class="js_close btn btn-info">
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/customerManager.js?v=1" src="${ctx}/js/require.js"></script>
</body>
</html>