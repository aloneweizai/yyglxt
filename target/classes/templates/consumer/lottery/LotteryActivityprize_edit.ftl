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
    <style>
        input[type="text"] {
            width: 350px;
        }
        select{
            width: 350px;
        }
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form id="linkForm" class="layui-form">
        <div class="nycon_list_b">
        <#if obj??><input type="hidden" name="id" id="id" value="${obj.id!}"></#if>
            <input type="hidden" id="activityId"name="activityId" value="${activityId!}">
            <table class="layui-table" lay-size="sm">
                <#if obj??>
                    <tr>
                        <td width="130px">奖品名称：<span class="color_r2">*</span></td>
                        <td colspan="3">

                        ${(obj.lotteryName)!}

                            <#if obj?? && obj.lotteryImage!="">
                                <img id="imgid" src="${(obj.lotteryImage)!}" style="height: 80px;" alt="">
                            <#else>
                                <img id="imgid" src="${ctx}/images/default.jpg" style="height: 80px;" alt=""/>
                            </#if>
                        </td>
                    </tr>
                <#else >
                    <tr>
                        <td width="100px">奖品名称：<span class="color_r2">*</span></td>
                        <td colspan="3">

                            <select name="lotteryId" id="lotteryId" required="required">
                                <option value="">请选择</option>
                                <#list lotteryRs as lottery>
                                    <option  value="${(lottery.id)!}" datanoluck="${(lottery.noluck?string('true', 'false'))!}" dataurl="${(lottery.image)!}" <#if obj??&&obj.lotteryId??&&lottery.id??&&obj.lotteryId==lottery.id>selected</#if> > ${(lottery.name)!}</option>
                                </#list>
                            </select>&nbsp;
                            <#if obj?? && obj.lotteryImage!="">
                                <img id="imgid" src="${(obj.lotteryImage)!}" style="height: 80px;" alt="">
                            <#else>
                                <img id="imgid" src="${ctx}/images/default.jpg" style="height: 80px;" alt=""/>
                            </#if>
                        </td>
                    </tr>
                </#if>

                <tr class="gailvId">
                    <td width="130px">奖品总数：<span class="color_r2">*</span></td>
                    <td colspan="3">
                        <input type="text" name="amount" id="amount" value="${(obj.amount)!}" class="layui-input">
                    </td>
                </tr>
                <tr class="gailvId">
                    <td width="130px">中奖概率：<span class="color_r2">*</span></td>
                    <td colspan="3">
                        <input type="text" name="luck" id="luck" <#if obj??>value="#{(obj.luck)! ;m0M4}"</#if>  maxlength="6" onkeyup="value=this.value.replace(/[^\d.]/g, '')" class="layui-input">
                    </td>
                </tr>
                <tr>
                    <td width="100px">奖品排序：<span class="color_r2">*</span></td>
                    <td colspan="3">
                        <input type="text" name="sort" id="sort" value="${(obj.sort)!}" onkeyup="value=this.value.replace(/\D+/g,'')" class="layui-input">
                    </td>
                </tr>
                <#--<tr>-->
                    <#--<td width="100px">已发数量：</td>-->
                    <#--<td colspan="3">-->
                        <#--<input type="text" name="balance" id="balance" value="${(obj.balance)!}">-->
                    <#--</td>-->
                <#--</tr>-->
                <tr class="gailvId">
                    <td width="130px">奖项名称：<span class="color_r2">*</span></td>
                    <td colspan="3">
                       <input type="text" name="val1" value="${(obj.val1)!}" class="layui-input">
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
<script data-main="${ctx}/js/abc/consumer/LotteryActivityprize.js" src="${ctx}/js/require.js"></script>
</body>
</html>
