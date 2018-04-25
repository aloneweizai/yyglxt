<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!-- 友情链接新增-->
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="lotteryTime_add_form" id="linkForm">
        <input type="hidden" id="activityId"name="activityId" value="${activityId!}">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">

                <tr>
                    <input type="hidden" name="id" id="id" value="${(lotteryTime.id)!}">
                    <td width="100">开始时间：</td>
                    <td colspan="3">
                        <input type="time" maxlength="100" name="startTime" id="startTime"  value="${(lotteryTime.startTime?string("HH:mm:ss"))!}">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="100">结束时间：</td>
                    <td colspan="3">
                        <input type="time" maxlength="100" name="endTime" id="endTime"  value="${(lotteryTime.endTime?string("HH:mm:ss"))!}">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="100">中奖概率：</td>
                    <td colspan="3">
                        <#--<input type="text" maxlength="100" name="luck" id="luck"   value="${(lotteryTime.luck)!}">-->
                        <input class="easyui-slider" value="${(lotteryTime.luck)!}"  data-options="showTip:true,rule: [0,'|',25,'|',50,'|',75,'|',100]"name="luck" id="luck" >

                        <span class="color_r2">*</span>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button" name="submit" id="submit" value="提交" class="layui-btn">
                        <input type="button" name="back" id="back" value="返回" class="layui-btn layui-btn-primary">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/lotteryTime.js" src="${ctx}/js/require.js"></script>
</body>
</html>
