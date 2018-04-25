<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--定时任务修改-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list_edit">
    <form name="codeForm" id="codeForm" method="post" class="layui-form">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="id" id="id" value="1">
            <tr>
                <td>任务名称</td>
                <td colspan="2">
                    <input id="jobName" class="layui-input" style="width:330px;" name="jobName" size="30"
                           type="text" value="${taskDataBo.jobName!}" readonly="readonly"> <span class="color_r2">*</span>
                    <span style="font-size:12px; color: #999;">温馨提示：填写需要包含全路径名称,例如，com.abc12366.uc.job.MinuteJob</span><!--必填时样式-->
                </td>
            </tr>
            <tr>
                <td width="90">任务分组</td>
                <td colspan="2">
                    <div style="width: 330px; float: left">
                        <select style="height:30px;margin-left:30px;" id="jobGroup" name ="jobGroup">
                        <#if jobGroups?? && ( jobGroups?size gt 0 )>
                            <#list jobGroups as jobGroup>
                                <option <#if taskDataBo.jobGroup ?? && (taskDataBo.jobGroup == jobGroup.fieldValue)>selected</#if> value="${jobGroup.fieldValue}">${jobGroup.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    <#--<select  name="jobGroup" style="height:30px;"  data-type="ajax" data-val="${taskDataBo.jobGroup!}" data-url="${ctx}/util/jsonDictBOs.php?dictId=jobGroup"  data-rules="fieldValue:fieldKey" >
                    </select>--></div>
                    <span class="color_r2">*</span>
                </td>
            </tr>
            <tr>
                <td>任务表达式</td>
                <td colspan="2">
                    <input id="cronExpression" class="layui-input" style="width:330px;" name="cronExpression" size="30"
                           type="text" value="${taskDataBo.cronExpression!}"> <#--<span class="color_r2">*</span><!--必填时样式&ndash;&gt;-->
                  <span class="color_r2">
                    <a href="#" data-toggle="tooltip" data-html="true" data-placement="right" id="tips"
                       title="<p align='left'>&nbsp;&nbsp;
                    温馨提示：表达式支持到七个域(秒分时日月周年），秒分时日月周必须填写，例如最简单的：* * * ? * *这个表达会每秒钟(每分种的、每小时的、每天的)激发一个部署的job，
                       秒域和分域允许值0-59，时域允许值0-23，日域允许值0-31，月域允许值1-12或 JAN-DEC，周域允许值1-7 或 SUN-SAT，年域允许值1970-2099，每个域都可以使用特殊符号 ,
                       - * / 日域还可以使用特殊符号 ? L W C，周域还可以使用特殊符号 ? L C #。
                      <br/>&nbsp;&nbsp;1.逗号 (,) 是用来在给某个域上指定一个值列表的，例如：0 0,15,30,45 * * * ? 使用值 0,15,30,45 在秒域上意味着每15秒触发一个 trigger。
                        <br/>&nbsp;&nbsp;2.中划线 (-) 用于指定一个范围，例如：0 45 3-8 ? * * 在上午的3点至上午的8点的45分时触发 trigger。
                       <br/>&nbsp;&nbsp;3.星号(*) 指示着你想在这个域上包含所有合法的值，例如：0 * 17 * * ? 每天从下午5点到下午5:59中的每分钟激发一次 trigger。
                        <br/>&nbsp;&nbsp;4.斜杠 (/) 是用于时间表的递增的，例如：0/15 0/30 * * * ? 在整点和半点时每15秒触发 trigger。
                        <br/>&nbsp;&nbsp;5.问号（?） 号只能用在日和周域上，但是不能在这两个域上同时使用，例如：0 10,44 14 ? 3 WEB 在三月中的每个星期三的下午 2:10 和 下午 2:44 被触发。
                       <br/>&nbsp;&nbsp;6.L 字母用在日域上，表示的是在月域上指定的月份的最后一天，例如：0 0 8 L * ? 在每个月最后一天的上午 8:00 触发 trigger。
                       <br/>&nbsp;&nbsp;7.L 字母用于周域上，指示着周的最后一天，就是星期六，例如：0 59 23 ? * L 在每个月的最后一个星期六下午的 11:59 触发 trigger。
                       <br/>&nbsp;&nbsp;8.W 字符代表着平日 (Mon-Fri)，并且仅能用于日域中。它用来指定离指定日的最近的一个平日。
                     <br/>&nbsp;&nbsp;9.# 字符仅能用于周域中。它用于指定月份中的第几周的哪一天，例如，周域的值为 6#3，它意思是某月的第三个周五 。</p>">?</a></span><!--必填时样式-->
                </td>
            </tr>
            <tr>
                <td>任务描述</td>
                <td colspan="2">
                    <textarea style="width: 500px; height: 100px;" id="jobDescription" name="jobDescription">${taskDataBo.jobDescription!}</textarea>
                </td>
            </tr>

            <tr>
                <td>

                </td>
                <td colspan="2">
                    <input type="button" name="commitBtn" id="commitBtn" value="提交" class="js_save layui-btn">
                    <input type="button" name="resetBtn" id="resetBtn" value="返回" class="layui-btn layui-btn-primary">
                </td>
            </tr>
        </table>
    </form>

</div>
<script data-main="${ctx}/js/abc/cms/time/time" src="${ctx}/js/require.js"></script>
</body>
</html>