<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
</head>
<body>
<#if item??>
    <#if item.isCustom==1><#assign isCustom = true/><#else><#assign isSys = true/></#if>
</#if>
<div class="container-fluid m_top_30 nycon_list">
    <br>
    <form name="item_form" action="${ctx}/cms/model/item/save.php"  method="post">
        <input type="hidden" name="modelitemId" value="${(item.modelitemId)!}">
        <input type="hidden" name="modelId" value="${modelId!}">
        <input type="hidden" name="isChannel" value="${isChannel!}">
        <input type="hidden" name="isCustom" value="<#if item??>${(item.isCustom)!}<#else>1</#if>">
        <input type="hidden" name="isDisplay" value="<#if item??>${(item.isDisplay)!}<#else>1</#if>">
        <input type="hidden" name="isSingle" value="<#if item??>${(item.isSingle)!}<#else>1</#if>">
        <input type="hidden" name="help" value="${(item.help)!}">
        <input type="hidden" name="helpPosition" value="${(item.helpPosition)!}">
        <input type="hidden" name="areaRows" value="${(item.areaRows)!}">
        <input type="hidden" name="areaCols" value="${(item.areaCols)!}">
        <input name="textSize" type="hidden" value="${(item.textSize)!}">
        <table class="layui-table" lay-size="sm">
            <tr>
                <td width="120">字段:</td>
                <td>
                    <#if item??>
                        ${(item.field)!}<input name="field" value="${(item.field)!}" type="hidden">
                    <#else>
                        <input name="field" value="${(item.field)!}" type="text"><span class="color_r2">*</span>
                    </#if>
                </td>
                <td width="120">名称:</td>
                <td><input name="itemLabel" value="${(item.itemLabel)!}" type="text"><span class="color_r2">*</span></td>
            </tr>

<!-- 如果是编辑系统字段 -->
<#if item?? && isSys??>
    <input name="dataType" type="hidden" value="${(item.dataType)!}">
    <input name="defValue" type="hidden" value="${(item.defValue)!}">
    <input name="optValue" type="hidden" value="${(item.optValue)!}">
    <input name="checkRule" type="hidden" value="${(item.checkRule)!}">
    <tr>
        <td>必填项:</td>
        <td colspan="3">
            <label><input name="isRequired" value="1" type="radio" <#if item.isRequired==1>checked<#else>checked</#if>>是</label>
            <label><input name="isRequired" value="0" type="radio" <#if item.isRequired==0>checked</#if>>否</label>
        </td>
    </tr>
<#else>
<!-- 如果是新增和编辑自定义字段-->
    <tr>
        <td width="120">数据类型:</td>
        <td>
            <select name="dataType">
                <#list modelItemDataTypes as dataType>
                    <option value="${dataType.code}" <#if item?? && dataType.code == item.dataType>selected</#if>>${dataType.description}</option>
                </#list>
            </select>
            <span class="color_r2">*</span>
        </td>
        <td width="120">排列顺序:</td>
        <td><input name="priority" value="${(item.priority)!}"  type="text"><span class="color_r2">*</span></td>
    </tr>
    <tr <#if !item?? || "1,2,3"?contains(item.dataType)><#else>hidden</#if> class="js_defValue_tr">
        <td>默认值:</td>
        <td colspan="3"><input name="defValue" type="text" value="${(item.defValue)!}"></td>
    </tr>
    <tr <#if item?? && "6,7,8"?contains(item.dataType)><#else>hidden</#if> class="js_optValue_tr">
        <td>可选项：</td>
        <td colspan="3">
            <input value="${(item.optValue)!}" name="optValue" type="text" style=" width:600px;" ><span class="color_r2">*</span><span class="color_9">例子 0:香蕉;1:苹果;2:橙子</span>
        </td>
    </tr>
    <tr class="js_img_set_tr" <#if item?? && item.dataType=='10'><#else>hidden</#if>>
        <td>图片设置:</td>
        <td colspan="3">
            图片宽度<input name="imageWidth" value="${(item.imageWidth?c)!}" type="text">
            图片高度<input name="imageHeight" value="${(item.imageHeight?c)!}" type="text">
        </td>
    </tr>
    <tr>
        <td>必填项:</td>
        <td>
            <label><input name="isRequired" value="1" type="radio" <#if item??><#if item.isRequired==1>checked</#if><#else>checked</#if>>是</label>
            <label><input name="isRequired" value="0" type="radio" <#if item?? && item.isRequired==0>checked</#if>>否</label>
        </td><td colspan="2"></td>
    </tr>
    <#if checkRules??>
        <tr class="js_checkRule_tr" <#if !"1,2,3"?contains((item.dataType)!"1")>hidden</#if>>
                <td>校验规则:</td>
                <td>
                    <select name="checkRule">
                        <option value="">请选择</option>
                        <#list checkRules as checkRule>
                            <option value="${checkRule.fieldValue}" <#if item?? && checkRule.fieldValue == item.checkRule!>selected</#if>>${checkRule.fieldKey}</option>
                        </#list>
                    </select>
                </td><td colspan="2"></td>
        </tr>
    <#else>
        <input name="checkRule" type="hidden" value="${(item.checkRule)!}">
    </#if>
</#if>
            <tr>
                <td></td>
                <td><input value="提交" class="js_save_btn btn btn-info btn-sm w_55">
                    <#if isChannel=='1'>
                        <a href="${ctx}/cms/model/item/list.php?modelId=${modelId}&isChannel=1" class="layui-btn layui-btn-primary">返回</a>
                    <#else>
                        <a href="${ctx}/cms/model/item/list.php?modelId=${modelId}&isChannel=0" class="layui-btn layui-btn-primary">返回</a>
                    </#if>
                <td></td><td></td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/model/modelItem" src="${ctx}/js/require.js"></script>
</body>
</html>