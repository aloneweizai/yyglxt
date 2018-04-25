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
<div class="container-fluid m_top_30 nycon_list">
    <form id="model_form" action="${ctx}/cms/model/save.php" method="post">
        <input type="hidden" name="modelId" value="${(model.modelId)!}">
        <input type="hidden" name="isDisabled" value="<#if model??>${model.isDisabled}<#else>0</#if>">
        <input type="hidden" name="isDef" value="<#if model??>${model.isDef}<#else>0</#if>">
        <table class="layui-table" lay-size="sm" style="margin-top: 30px;">
            <tr>
                <#--<td width="120">模型ID：</td>-->
                <#--<td>${(model.modelId)!}</td>-->
                <#--<td width="120">全站模型：</td>-->
                <#--<td>-->
                    <#--<label><input value="1" name="isGlobal" type="radio" <#if model??><#if model.isGlobal==1>checked</#if><#else>checked</#if>>是</label>-->
                    <#--<label><input value="0" name="isGlobal" type="radio" <#if model?? && model.isGlobal==0>checked</#if>>否</label>-->
                    <#--<span class="color_r2">*</span>-->
                <#--</td>-->
                <td style="width: 150px;">模型名称</td>
                <td colspan="3"><input name="modelName"  value="${(model.modelName)!}" style="width:150px" type="text" class="layui-input" required><span class="color_r2">*</span></td>
            </tr>
            <tr hidden>
                <td>模型路径</td>
                <td colspan="3"><input name="modelPath" value="${(model.modelPath)!}" style="width:150px" type="text" class="layui-input" required><span class="color_r2">*</span></td>
            </tr>
            <tr hidden>
                <td>栏目模板前缀</td>
                <td><input name="tplChannelPrefix" value="${(model.tplChannelPrefix)!}" style="width:150px" type="text" class="layui-input"></td>
                <td>内容模板前缀</td>
                <td><input name="tplContentPrefix" value="${(model.tplContentPrefix)!}" style="width:150px" type="text" class="layui-input"></td>
            </tr>
            <tr>
                <td>栏目标题图</td>
                <td>
                    宽：<input name="titleImgWidth" value="${(model.titleImgWidth?c)!}" style="width:60px" type="text" class="layui-input"><span class="color_r2">*</span>
                    高：<input name="titleImgHeight" value="${(model.titleImgHeight?c)!}" style="width:60px" type="text" class="layui-input"><span class="color_r2">*</span></td>
                </td>
                <td>栏目内容图</td>
                <td>
                    宽：<input name="contentImgWidth" value="${(model.contentImgWidth?c)!}" style="width:60px" type="text" class="layui-input"><span class="color_r2">*</span>
                    高：<input name="contentImgHeight" value="${(model.contentImgHeight?c)!}" style="width:60px" type="text" class="layui-input"><span class="color_r2">*</span>
                </td>
            </tr>
            <tr>
                <td>排列顺序</td>
                <td colspan="3"><input name="priority" value="${(model.priority?c)!}" style="width:60px" type="text" class="layui-input" required><span class="color_r2">*</span></td>
                <#--<td>是否有内容：</td>-->
                <#--<td>-->
                    <#--<label><input value="1" name="hasContent" type="radio" <#if model??><#if model.hasContent==1>checked</#if><#else>checked</#if>>是</label>-->
                    <#--<label><input value="0" name="hasContent" type="radio" <#if model?? && model.hasContent==0>checked</#if>>否</label>-->
                <#--</td>-->
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                    <input  name="button" value="提交" class="layui-btn js_save_btn">
                    <a href="${ctx}/cms/model/list.php" class="layui-btn layui-btn-primary">返回</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/model/model" src="${ctx}/js/require.js"></script>
</body>
</html>