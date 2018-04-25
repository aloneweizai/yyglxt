<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--数据字典列表-->
</head>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <form name="queryForm" id="queryForm" method="post" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">字典名称</label>
                    <div class="layui-input-inline">
                        <select name="dictName" id="dictName"  lay-verify="required" lay-search="">
                            <option value="">直接选择或搜索选择</option>
                            <#if dictst.dataList?? && (dictst.dataList?size > 0) >
                                <#list dictst.dataList as dict>
                                    <option value="${dict.dictName}" <#if dict.dictName??&&dictName?? && dict.dictName==dictName>selected</#if>>${dict.dictName}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">字典状态</label>
                    <div class="layui-input-inline">
                        <select name="status" id="status" class="cxinput">
                            <option value=""> </option>
                            <option value="true" <#if status?? && status=="true">selected</#if>>-启用-</option>
                            <option value="false" <#if status?? && status=="false">selected</#if>>-停用-</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="queryBtn" class="layui-btn">查询</button>
                    </div>
                </div>
                <a id="consumer_more" class="layui-btn layui-btn-normal fr" href="${ctx}/system/code/add.php">创建数据字典</a>
            </div>
        </div>
    </form>

    <div class=" nycon_list_b layui-form">

        <table class="layui-table" lay-size="sm">
            <thead class="pn-lthead">
            <tr>
                <th width="28">序号</th>
                <th width="28">
                    <div>全选</div>
                </th>
                <th>字典名称</th>
                <th>字典编码</th>
                <th>值名称</th>
                <th>值编码</th>
                <th>排序</th>
                <th>状态</th>
                <th>最后修改时间</th>
                <th>操作选项</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <#if dicts.dataList?? && (dicts.dataList?size > 0) >
                <#list dicts.dataList as dict>
                <tr>
                    <td>${dict_index+1+(pagination.page-1)*pagination.size}</td>
                    <td>
                        <input name="ids" type="checkbox" lay-skin="primary"  value="${dict.id}" status="<#if dict.status?? && dict.status>1<#else>0</#if>">
                    </td>
                    <td>${dict.dictName}</td>
                    <td>${dict.dictId}</td>
                    <td>${dict.fieldKey}</td>
                    <td>${dict.fieldValue}</td>
                    <td>${dict.sort!}</td>
                    <td>
                        <#if dict.status?? && dict.status>
                            <div class="btn btn-success btn-xs ">启用</div>
                        <#else>
                            <div class="btn btn-danger btn-xs ">停用</div>
                        </#if>
                    </td>
                    <td>
                            ${(dict.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}
                    </td>
                    <td>
                        <#if dict.status?? && dict.status>
                            <a data-url="${ctx}/system/code/${dict.id}?status=false" type="PATCH" abc-type="是否停用?" class="pn-opt">停用</a>
                        <#else>
                            <a href="${ctx}/system/code/${dict.id}" type="get">修改</a> |
                            <a data-url="${ctx}/system/code/${dict.id}" type="POST"  abc-type="是否删除?" class="pn-opt">删除</a> |
                            <a data-url="${ctx}/system/code/${dict.id}?status=true" type="PATCH" abc-type="是否启用?"  class="pn-opt">启用</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            <#else>
            </#if>
            </tbody>
        </table>
        <div class="button_caozuo_fenye">
            <div class="layui-btn nycon_sel_btn">全选</div>
            <div id="batch_del" class="layui-btn layui-btn-danger">批量删除</div>
        </div>
    <#--${pagination}-->
    <#include "../../common/pagination.ftl">
    </div>
</div>
<script data-main="${ctx}/js/abc/system/code" src="${ctx}/js/require.js"></script>
</body>
</html>