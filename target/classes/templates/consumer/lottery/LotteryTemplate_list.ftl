<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form class="layui-form"  action="${ctx}/consumerManager/lottery/lotteryTemplate.php" method="get" id="templateListForm">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">模版名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name ="name" value="${(pagination.name)!}" id="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" >
                        <button id="queryBtn" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/cms/template/tplListFromDb.php"  class="layui-btn layui-btn-normal fr">管理</a>
            </div>
        </div>


        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>模版名称</th>
                    <th>模版路径</th>
                    <th>模型</th>
                    <th>状态</th>
                    <th>时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if listRs?? && ( listRs?size gt 0 )>
                    <#list listRs as list>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+list_index+1}</td>

                        <td>${(list.templateName)!}</td>
                        <td>${(list.templatePath)!}</td>
                        <td>${(list.modelName)!}</td>
                        <td>
                        <#if list.state?? && list.state == 1>
                        <div class="btn btn-success btn-xs ">启用</div>
                        <#else>
                        <div class="btn btn-danger btn-xs ">停用</div>
                        </#if>
                        </td>
                        <td>${(list.createTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <a href="${ctx}/consumerManager/lottery/lotteryTemplateDo.php?id=${list.templateId!}"
                               target="_blank">预览</a>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>	
    </form>
    <#include "../../common/pagination.ftl">
</div>
<script data-main="${ctx}/js/abc/consumer/LotteryTemplate.js" src="${ctx}/js/require.js"></script>
</body>
</html>
