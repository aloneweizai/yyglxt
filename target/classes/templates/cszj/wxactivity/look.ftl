<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="consumer_edit" action="${ctx}/cszjs/wxactivity/save.php" next-url="${ctx}/cszjs/wxactivity/list.php" method="post">
        <table class="table">
            <input type="hidden" name="id" value="${WxActivity.id!}">
            <tr>
                <td width="200">活动编号：</td>
                <td colspan="3"><span>${WxActivity.id!}</span></td>
                <td></td>
            </tr>
            <tr>
                <td width="200">活动名称：</td>
                <td colspan="3">${WxActivity.name!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">活动描述：</td>
                <td colspan="3">
                    ${WxActivity.description!}
                </td>
            </tr>

            <tr>
                <td width="200">规则类型：</td>
                <td width="150">
                   <#if WxActivity.ruleType??&&WxActivity.ruleType =='1'>随机口令
                    <i class="glyphicon glyphicon-question-sign" title="口令规则示例：系统会自动生成8位数字加大写字母的口令，如：ABCDEF12。"></i>
                   <#else>
                                   关键字口令<i class="glyphicon glyphicon-question-sign" title="关键字规则示例：艾博克#财税平台#爱我中华，只要匹配其中一个词即可抽奖。"></i>
				   </#if>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td width="200">规则定义：</td>
                <td colspan="3">${WxActivity.rule!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">金额类型：</td>
                <td >
					<#if WxActivity.amountType??&&WxActivity.amountType == '1'>固定金额
                    <#else>随机金额</#if>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td width="200">金额(元)：</td>
                <td colspan="3">
<#if WxActivity.amountType??&&WxActivity.amountType == '2'&&WxActivity.minAmount??>
${WxActivity.minAmount!} 元 -
    </#if>
                ${WxActivity.amount!}元
                    <i class="glyphicon glyphicon-question-sign" title="如果输入的金额为整数，发送的红包金额为整数；金额包含小数，发送的红包金额会包含'角、分'。如果选择'随机金额'时，'金额'将作为随机金额的最大值。"></i>
                </td>
                <td></td>
            </tr>
            <tr>
                <td width="200">中奖概率：</td>
                <td colspan="3">
                    ${WxActivity.probability!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">红包数量：</td>
                <td colspan="3">${WxActivity.num!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">每人每天抽奖次数限制：</td>
                <td colspan="3">${WxActivity.times!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">红包祝福语：</td>
                <td colspan="3">${WxActivity.wishing!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">红包备注：</td>
                <td colspan="3">${WxActivity.remark!}</td>
                <td></td>
            </tr>
            <tr>
                <td width="200">活动起止时间：</td>
                <td colspan="3">
                        ${(WxActivity.startTime)!} 至  ${(WxActivity.endTime)!}
                </td>
                <td></td>
            </tr>
            <tr>
                <td>是否赠送积分</td>
                <td width="200"><#if WxActivity.giftPoints??&&WxActivity.giftPoints>
                    是&nbsp;&nbsp;赠送积分：${(WxActivity.points?c)!}
                <#else>否</#if>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>是否赠送优惠券</td>
                <td>
                <#if WxActivity.giftCoupon??&&WxActivity.giftCoupon>
                    是&nbsp;&nbsp;优惠券活动：${activityName!}
                <#else>
                    否
                </#if>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>状态</td>
                <td width="200"><#if WxActivity.status??&&WxActivity.status>启用<#else>停用</#if>
                </td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>