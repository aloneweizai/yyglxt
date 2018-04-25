<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <form method="post" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                 <a href="${ctx}/cms/model/edit.php" class="layui-btn fr">创建模型</a>
            </div>
        </div>
    </form>
    <form method="post" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="28">序号</th>
                    <th width="28">
                        全选
                    </th>
                    <th>模型名称</th>
                    <th>排列顺序</th>
                    <th>状态</th>
                    <th style="width: 20%">操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody js_model_list">
                <#if models?? && models.dataList?? && (models.dataList?size > 0) >
                    <#list models.dataList as model>
                    <tr>
                        <input name="modelId" type="hidden" value="${(model.modelId)!}">
                        <td class="td_i">${model_index + 1}</td>
                        <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${(model.modelId)!}">

                        <td id="modelName_td">${(model.modelName)!}</td>
                        <td><input name="priority" class="w_55" type="text" value="${(model.priority?c)!}"></td>
                        <td class="js-div-status">
                            <#if model.isDisabled?? && model.isDisabled==0>
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                        </td>
                        <td>
                            <a href="${ctx}/cms/model/edit.php?id=${model.modelId}">编辑</a> |
                            <a href="${ctx}/cms/model/item/list.php?isChannel=1&modelId=${model.modelId}">栏目模型</a> |
                            <a href="${ctx}/cms/model/item/list.php?isChannel=0&modelId=${model.modelId}">内容模型</a> |
                            <a data-href="${ctx}/cms/model/del.php?id=${model.modelId}" href="javascript:void(0)" class="js_del_btn pn-opt">删除</a> |
                            <#if model.isDisabled?? && model.isDisabled==0>
                                <a data-href="${ctx}/cms/model/enable/${model.modelId}.php?isDisabled=1" class="js_enable pn-opt">停用</a>
                            <#else>
                                <a data-href="${ctx}/cms/model/enable/${model.modelId}.php?isDisabled=0"  class="js_enable pn-opt">启用</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <#if models?? && models.dataList?? && (models.dataList?size > 0) >
            <div class="button_caozuo_fenye">
                <div  class="layui-btn js_selectAll" data-check=false>全选</div>
                <div  data-href="${ctx}/cms/model/delList.php"  class="layui-btn layui-btn-danger js_del_batch_btn">批量删除</div>
                <div  data-href="${ctx}/cms/model/saveList.php" class="layui-btn js_save_list_btn">保存排列顺序</div>
            </div>
            </#if>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/model/model" src="${ctx}/js/require.js"></script>
</body>
</html>