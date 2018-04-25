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
         textarea
         {
             width: 350px;
             height:100px;
             font-size: 12px;
         }
        .td111{
            width: 140px;
            text-align: right;
        }
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form id="linkForm" class="layui-form">
        <div class="nycon_list_b">
        <#if obj??>
            <input  type="hidden" name="id" id="id" value="${obj.id!}">

        </#if>
            <table class="layui-table" lay-size="sm">

                <tr>
                    <td class="td111">活动名称：</td>
                    <td >${(obj.name)!}</td>
                </tr>
                <tr>
                    <td class="td111">模版名称：</td>
                    <td >
                    <#if lotteryTemplateRs??>
                        <#list lotteryTemplateRs as lotteryTemplate>
                            <#if obj??&&obj.templateId??&&lotteryTemplate.templateId??&&obj.templateId==lotteryTemplate.templateId>
                                 ${(lotteryTemplate.templateName)!}
                            </#if>
                        </#list>
                    </#if>
                    </td>
                </tr>

                <tr>
                    <td class="td111">描述：</td>
                    <td >${(obj.description)!}</td>
                </tr>


                <tr>
                    <td class="td111">用户等级：</td>
                    <td colspan="3">
                    <#if userLevelRs??>
                        <#list userLevelRs as userLevel>
                             <#if obj??&&obj.userLevel?? &&obj.userLevel==userLevel>LV${(userLevel)!}</#if>
                        </#list>
                    </#if>
                        <span>，参与活动用户最低等级限制</span>
                    </td>
                </tr>
                <tr>
                    <td class="td111">每天免费次数：</td>
                    <td >${(obj.userFreeCount)!}</td>
                </tr>
                <tr>
                    <td class="td111">领奖有效天数：</td>
                    <td >${(obj.getlotteyDay)!}</td>
                </tr>
                <#--<tr>-->
                    <#--<td   class="td111">用户中奖次数限制：</td>-->
                    <#--<td colspan="3">-->
                        <#--<input type="text" name="userLotteryMax" id="userLotteryMax" value="${(obj.userLotteryMax)!}"onkeyup="value=this.value.replace(/\D+/g,'')">-->

                    <#--</td>-->

                <#--</tr>-->
                <tr>
                    <td   class="td111">用户每天中奖次数</td>
                    <td >${(obj.userLotteryMaxDay)!}</td>

                </tr>
                <tr>
                    <td width="100px" class="td111">每天派奖总数：</td>
                    <td >${(obj.timeStock)!}</td>
                </tr>
                <tr>
                    <td width="100px" class="td111">当天已派奖数：</td>
                    <td >${(obj.timeCount)!}</td>
                </tr>
                <tr>
                    <td   class="td111">开始时间：</td>
                    <td>${(obj.startTime?string("yyyy-MM-dd"))!}</td>
                </tr>
                <tr>
                    <td   class="td111">结束时间：</td>
                    <td>${(obj.endTime?string("yyyy-MM-dd"))!}</td>
                </tr>
                <tr>
                    <td   class="td111">状态：</td>
                    <td>
                    <#if obj?? && !obj.status>
                        停用
                    <#else>
                        启用
                    </#if></td>
                </tr>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/LotteryActivity.js" src="${ctx}/js/require.js"></script>
</body>
</html>
