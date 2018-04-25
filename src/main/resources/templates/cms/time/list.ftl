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
    <!--定时任务列表-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list">
    <form name="queryForm" id="queryForm" method="post" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/cms/time/add.php" class="layui-btn layui-btn-normal fr">创建定时任务</a>
            </div>
        </div>
    </form>

    <div class=" nycon_list_b">

        <table class="layui-table" lay-size="sm">
            <thead class="pn-lthead">
            <tr>
                <th>序号</th>
                <th>任务名称</th>
                <th>任务分组</th>
                <th>任务描述</th>
                <th>任务状态</th>
                <th>任务表达式</th>
                <th>创建时间</th>
                <th>操作选项</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
<#if taskDataListBo.dataList?? && (taskDataListBo.dataList?size > 0) >
    <#list taskDataListBo.dataList as taskDataListBo>
                <tr>
                    <td>${taskDataListBo_index+1+(pagination.page-1)*pagination.size}</td>
                    <td>${taskDataListBo.jobName!}</td>
                    <#if taskDataListBo.jobGroup??>
                        <td>
                            <#if jobGroup?? && ( jobGroup?size gt 0 )>
            				   <#list jobGroup as group>
                                <#if taskDataListBo.jobGroup == group.fieldValue>${group.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                    <#else>
                        <td>
                        </td>
                    </#if>
                    <td>${taskDataListBo.jobDescription!}</td>
                    <#if taskDataListBo.jobStatus??>
                        <td>
                            <#if jobStatus?? && ( jobStatus?size gt 0 )>
            				   <#list jobStatus as status>
                                <#if taskDataListBo.jobStatus == status.fieldValue>${status.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                    <#else>
                        <td>
                        </td>
                    </#if>
                    <td>${taskDataListBo.cronExpression!}</td>
                    <td>${taskDataListBo.createTime!}</td>
                    <#--<td>${(taskDataListBo.isEnable??&&taskDataListBo.isEnable=='1')?string('<div class="btn btn-success btn-xs ">启用</div>','<div class="btn btn-danger btn-xs ">停用</div>')}</td>-->
                    <td>
                       <#-- <#if taskDataListBo.jobStatus??&&taskDataListBo.jobStatus=='1'>
                            <a data-url="${ctx}/cms/time/ting/${taskDataListBo.taskId!}" href="javascript:;" type="POST" abc-type="ting">暂停</a>
                        <#else >-->
                           <#if taskDataListBo.jobStatus??&&taskDataListBo.jobStatus == 'NORMAL'>
                               <a href="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}">编辑</a> |
                               <a data-url="${ctx}/cms/time/ting/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" href="javascript:;" type="POST" abc-type="ting">暂停</a> |
                               <a href="javascript:;" data-url="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" type="POST" abc-type="delete" class="pn-opt"  >删除</a>

                           <#elseif taskDataListBo.jobStatus??&&(taskDataListBo.jobStatus == 'PAUSED' || taskDataListBo.jobStatus == 'ERROR')>
                               <a href="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}">编辑</a> |
                               <a data-url="${ctx}/cms/time/qi/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" href="javascript:;" type="POST" abc-type="qi">激活</a> |
                               <a href="javascript:;" data-url="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" type="POST" abc-type="delete" class="pn-opt"  >删除</a>

                           <#elseif taskDataListBo.jobStatus??&&taskDataListBo.jobStatus == 'COMPLETE'>
                               <a href="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}">编辑</a> |
                               <a href="javascript:;" data-url="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" type="POST" abc-type="delete" class="pn-opt"  >删除</a>
                           <#elseif taskDataListBo.jobStatus??&&(taskDataListBo.jobStatus == 'BLOCKED'||taskDataListBo.jobStatus == 'NONE')>
                               <a href="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}">编辑</a> |
                               <a data-url="${ctx}/cms/time/qi/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" href="javascript:;" type="POST" abc-type="qi">激活</a>｜
                               <a data-url="${ctx}/cms/time/ting/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" href="javascript:;" type="POST" abc-type="ting">暂停</a> |
                               <a href="javascript:;" data-url="${ctx}/cms/time/${taskDataListBo.jobName!}/${taskDataListBo.jobGroup!}" type="POST" abc-type="delete" class="pn-opt"  >删除</a>
                           </#if>

                    </td>
                </tr>
    </#list>
</#if>
            </tbody>
        </table>
    </div>
</div>
<script data-main="${ctx}/js/abc/cms/time/time" src="${ctx}/js/require.js"></script>
</body>
</html>