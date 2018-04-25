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
<input name="modelId" type="hidden" value="${criteria.modelId}">
<input name="isChannel" type="hidden" value="${criteria.isChannel}">
<div class="container-fluid m_top_30 nycol_list text-right">
    <form method="post">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/cms/model/list.php"class="layui-btn layui-btn-primary fr">返回</a>
                <a href="${ctx}/cms/model/item/edit.php?modelId=${criteria.modelId}&isChannel=${criteria.isChannel}"class="layui-btn layui-btn-normal fr">创建字段</a>
            </div>
        </div>
        <div class=" nycon_list_b ">
            <table class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th width="28">全选</th>
                    <th>字段</th>
                    <th>数据类型</th>
                    <th>名称</th>
                    <th>排列顺序</th>
                    <th>显示</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody>
                    <#if modelItems.dataList?? && (modelItems.dataList?size > 0) >
                        <#list modelItems.dataList as modelItem>
                            <tr class="js_save_tr">
                                <input name="isCustom" type="hidden" value="${(modelItem.isCustom)!}">
                                <input type="hidden" name="optValue" value="${(modelItem.optValue)!}">
                                <td><input name="modelitemId" type="hidden" value="${(modelItem.modelitemId)!}">
                                    <input class="js_checkbox" type="checkbox" lay-skin="primary"  value="${modelItem.modelitemId}">
                                </td>
                                <td><input name="field" type="hidden" value="${(modelItem.field)!}">${(modelItem.field)!}</td>
                                <td><input name="dataType" type="hidden" value="${(modelItem.dataType)!}">
                                    <#if modelItem.isCustom ==0>
                                        系统字段
                                    <#else>
                                        <#list itemDataTypes as dataType>
                                            <#if dataType.code == modelItem.dataType>${dataType.description}</#if>
                                        </#list>
                                    </#if>
                                </td>
                                <td><input name="itemLabel" value="${(modelItem.itemLabel)!}" type="text" class="layui-input"></td>
                                <td><input name="priority" value="${(modelItem.priority)!}" type="text" class="layui-input"></td>
                                <td><input <#if modelItem.isCustom ==0>disabled</#if> name="isDisplay" type="checkbox" lay-skin="primary"  <#if modelItem.isDisplay?? && modelItem.isDisplay==1>checked</#if>></td>
                                <td>
                                    <a href="${ctx}/cms/model/item/edit.php?itemId=${modelItem.modelitemId}&modelId=${criteria.modelId}&isChannel=${criteria.isChannel}">编辑</a>|
                                    <a class="jd_item_del" href="javascript:void(0)" data-href="${ctx}/cms/model/item/del.php?itemId=${modelItem.modelitemId}" class="pn-opt">删除</a>
                                </td>
                            </tr>
                        </#list>
                        <tr>
                            <td colspan="8" align="left">
                                <button type="button" class="layui-btn nycon_sel_btn js_selectAll">全选</button>
                                <button type='button' data-tr-class=".js_save_tr" data-checkbox-class=".js_checkbox" data-href="${ctx}/cms/model/item/saveList.php" class="js_save_list_btn layui-btn layui-btn-normal">保存内容</button>
                                <button type='button' data-href="${ctx}/cms/model/item/delList.php" class="js_del_batch_btn layui-btn layui-btn-danger">批量删除</button>
                            </td>
                        </tr>
                    <#else>
                        <tr><td colspan="8" align="center">没有设置字段</td></tr>
                    </#if>
                    <br>
                    <#assign i=1/>
                    <#list sysModelItems.sysModelItems as sysModelItem>
                        <#if !fieldList?seq_contains(sysModelItem.field)>
                            <#if i == 1>
                                <tr><td colspan="8">可选系统预设字段:</td></tr>
                                <tr>
                                    <th width="28">全选</th>
                                    <th>字段</th>
                                    <th>数据类型</th>
                                    <th>名称</th>
                                    <th>排列顺序</th>
                                    <th>显示</th>
                                    <th></th>
                                </tr>
                            </#if>
                            <#assign i=i+1/>
                            <tr class="js_add_tr">
                                <td><input class="js_sys_checkbox" type="checkbox" lay-skin="primary"  checked></td>
                                <td><input name="field" type="hidden" value="${(sysModelItem.field)!}">${(sysModelItem.field)!}</td>
                                <td><input name="dataType" type="hidden" value="${(sysModelItem.dataType)!}">
                                    系统字段
                                    <#--<#list itemDataTypes as dataType>-->
                                        <#--<#if dataType.code == sysModelItem.dataType>${dataType.description}</#if>-->
                                    <#--</#list>-->
                                </td>
                                <td><input name="itemLabel" value="${(sysModelItem.itemLabel)!}" type="text" class="layui-input"></td>
                                <td><input name="priority" value="${(sysModelItem.priority)!}" type="text" class="layui-input"></td>
                                <td>
                                    <input disabled name="isDisplay" type="checkbox" lay-skin="primary"  <#if sysModelItem.isDisplay?? && sysModelItem.isDisplay==1>checked</#if>>
                                </td>
                                <td>
                                    <input name="isCustom" type="hidden" value="0">
                                    <input name="optValue" type="hidden" value="${(sysModelItem.optValue)!}">
                                    <input name="isRequired" type="hidden" value="${(sysModelItem.isRequired)!}">
                                </td>
                            </tr>
                        </#if>
                    </#list>
                    <#if i gt 1>
                        <tr>
                            <td colspan="7" align="left">
                                <button type="button" class="layui-btn nycon_sel_btn js_sys_selectAll" data-check="true">全选</button>
                                <button type='button' data-tr-class=".js_add_tr" data-checkbox-class=".js_sys_checkbox" data-href="${ctx}/cms/model/item/saveList.php?addSys=true" class="js_save_list_btn layui-btn layui-btn-normal">保存系统默认字段</button>
                            </td>
                        </tr>
                    </#if>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/model/modelItem" src="${ctx}/js/require.js"></script>
</body>
</html>