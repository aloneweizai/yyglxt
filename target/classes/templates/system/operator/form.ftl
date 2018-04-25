<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="form_save" action="${ctx}/system/operator/save.php" method="post" class="layui-form">
        <input type="hidden" name="id" value="${(user.id)!}">
        <input type="hidden" name="status" value="<#if user??>${(user.status?string("true","false"))!}<#else>true</#if>">
        <table class="layui-table" lay-size="sm">
            <br>
            <tr>
                <td width="90">用户账户</td>
                <td colspan="3">
                    <input name="username" value="${(user.username)!}" type="text" style=" width:330px;" class="layui-input"><span class="color_r2">*</span>
                    <#if user?? && resetPassword??>
                        <a data-href="${ctx}/system/operator/resetPassword/${(user.id)!}.php" class="js_reset_password layui-btn">重置密码</a>
                    </#if>
                </td>
            </tr>
            <#if !user??>
                <tr>
                    <td>密码</td>
                    <td colspan="3">
                        <input name="password" type="password" style=" width:330px;" value="" class="layui-input"><span class="color_r2">*</span>
                        <div style="">
                            <div id="pwd_Weak" class="pwd_cs pwd_c "></div>
                            <div id="pwd_Medium" class="pwd_cs pwd_c "></div>
                            <div id="pwd_Strong" class="pwd_cs pwd_c pwd_c_r "></div>
                            <div class="clear"></div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>再次确认</td>
                    <td colspan="3">
                        <input name="confirm_password" type="password" class="layui-input" style="width:330px;" value="">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
            </#if>
            <tr>
                <td>真实姓名</td>
                <td colspan="3"><input name="nickname" value="${(user.nickname)!}" type="text" style=" width:330px;" class="layui-input"><span class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>手机号码</td>
                <td colspan="3"><input name="phone" value="${(user.phone)!}" type="text" style=" width:330px;" class="layui-input"></td>
            </tr>
            <tr>
                <td>所属部门</td>
                <td colspan="3">
                    <input name="orgName" value="${(user.orgName)!}" type="text" style=" width:330px;"  class="js_choose_btn layui-input" readonly><span class="color_r2">*</span>
                    <input name="orgId" value="${(user.orgId)!}" type="hidden" style=" width:330px;" >
                </td>
            </tr>
            <tr>
                <td>职位</td>
                <td colspan="3"><input name="job" value="${(user.job)!}" type="text" style=" width:330px;" class="layui-input"></td>
            </tr>
            <tr class="operator_checkbox">
                <td>角色</td>
                <td colspan="3">
                    <#if allRole?? && (allRole?size > 0) >
                        <#list allRole as role>
                            <input name="roleIds_arr" type="checkbox" lay-skin="primary"  value="${role.id}" <#if user?? && user.roleIds?index_of(role.id+',')!=-1>checked</#if> title="${role.roleName}">
                        </#list>
                    </#if><input name="roleIds" type="hidden">
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="4">
                    <button name="button" class="js_save_btn layui-btn">提交</button>
                    <a href="javascript:;" class="layui-btn layui-btn-primary js_back">返回</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<#if orgAll?? && (orgAll?size > 0) >
<div >
    <ul>
        <#list orgAll as org>
            <#if org.status == true>
                <li class="org_li" data-id="${org.id!}" data-pid="${org.parentId!}" data-name="${org.name!}" data-type="${org.type!}"></li>
            </#if>
        </#list>
    </ul>
</div>
</#if>
<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">请选择所属部门
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <input value="确认" class="js_close btn btn-info">
    </div>
</div>
<script data-main="${ctx}/js/abc/system/operator/form.js?v=2" src="${ctx}/js/require.js"></script>
</body>
</html>