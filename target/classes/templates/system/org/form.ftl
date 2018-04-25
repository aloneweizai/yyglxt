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
<div class="container-fluid m_top_30 nycol_list_edit">
    <form name="form_save" action="${ctx}/system/org/save.php" method="post">
        <input type="hidden" name="status" value="true">
        <#if org??>
            <input type="hidden" id="initRegion" data-province-id="${(org.province)!}" data-city-id="${(org.city)!}" data-county-id="${(org.area)!}">
            <input name="id" type="hidden" value="${(org.id)!}">
        </#if>
        <table class="layui-table" lay-size="sm">
            <br>
            <tr>
                <td width="90">机构类型</td>
                <td>
                    <select name="type" style="height: 30px; border:1px solid #ddd">
                        <option value="" selected="selected">--请选择--</option>
                        <#list orgTypes as orgType>
                            <option value="${orgType.code}"<#if orgType.code == (org.type)!>selected</#if>>${orgType.description}</option>
                        </#list>
                    </select><span class="color_r2">*</span>
                </td>
            </tr>
            <tr>
                <td>上级机构</td>
                <td>
                    <#if org??>
                        <input type="hidden" name="parentId" value="${(org.parentId)!}">
                        <span id="parentName">${(org.parentName)!}</span>
                    <#else>
                        <input type="hidden" name="parentId" value="${parentId!}">
                        <span id="parentName">${parentName!}</span>
                    </#if>
                    <a href="javascript:void(0)" class="js_choose_btn layui-btn">选择</a>
                </td>
            </tr>
            <tr>
                <td>所属地区</td>
                <td>
                    <select id="s_province" name="province" style="height: 30px; border:1px solid #ddd; margin-right: 20px;"><option value="">请选择</option></select>
                    <select id="s_city" name="city" style="height: 30px; border:1px solid #ddd; margin-right: 20px;"><option value="">请选择</option></select>  
                    <select id="s_county" name="area" style="height: 30px; border:1px solid #ddd; margin-right: 20px;"><option value="">请选择</option></select>
                    <span class="color_r2">*</span>
                </td>
            </tr>
            <tr>
                <td>机构名称</td>
                <td><input name="name" value="${(org.name)!}" size="30" required="" type="text" class="layui-input" style="width: 330px;"><span class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>机构代码</td>
                <td><input name="code"  value="${(org.code)!}" size="30" required="" type="text" class="layui-input" style="width: 330px;"><span class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>联系人</td>
                <td><input name="contact" value="${(org.contact)!}" size="30" required="" type="text" class="layui-input" style="width: 330px;"><span class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input name="phone" value="${(org.phone)!}" size="30" required="" type="text" class="layui-input" style="width: 330px;"><span class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>联系地址</td>
                <td><input name="address" value="${(org.address)!}" size="60" required="" type="text" class="layui-input" style="width: 330px;"><span class="color_r2">*</span></td>
            </tr>
            <tr>
                <td>备注</td>
                <td><textarea name="remark"  cols="57" rows="4" class="layui-textarea" style="width: 330px;">${(org.remark)!}</textarea></td>
            </tr>
            <tr>
                <td></td>
                <td>
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
             <li class="org_li" data-id="${org.id!}" data-pid="${org.parentId!}" data-name="${org.name!}"></li>
        </#list>
    </ul>
</div>
</#if>
<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">请选择上级机构
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <div>
            <input value="确认" class="js_close btn btn-info">
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/system/org/form" src="${ctx}/js/require.js"></script>
</body>
</html>