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
        select,  input[type="text"]{
            width: 350px;
        }
        textarea{
            width: 350px;
            height: 80px;
        }
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="lotteryLog_add_form" id="linkForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <tr>
                    <input type="hidden" name="id"  value="${lotteryLog.id!}">
                    <td width="100">用户名称：</td>
                    <td colspan="3">

                        <span class="color_r2">${lotteryLog.userName!}</span>
                    </td>
                </tr>
                <tr>

                    <td width="100">奖品名称：</td>
                    <td colspan="3">

                        <span class="color_r2">${lotteryLog.lotteryName!}</span>
                    </td>
                </tr>
                <tr>
                    <td width="100">消耗积分：</td>
                    <td colspan="3">

                        <span class="color_r2">${lotteryLog.upoint!}</span>
                    </td>
                </tr>
            <#if lotteryLog.lotterySend?? && lotteryLog.lotterySend>
                <tr>
                    <td width="100">收货地址：</td>
                    <td colspan="3">
                        <span class="color_r2"> ${lotteryLog.address!""}</span>
                    </td>
                </tr>
                <tr>
                    <td width="100">收件人：</td>
                    <td colspan="3">

                        <span class="color_r2">${lotteryLog.sendName!""}</span>
                    </td>
                </tr>
                <tr>
                    <td width="100">联系电话：</td>
                    <td colspan="3">

                        <span class="color_r2">${(lotteryLog.phone)!}</span>
                    </td>
                </tr>
                <#--<tr>-->
                    <#--<td width="100">发货状态：</td>-->
                    <#--<td colspan="3">-->

                        <#--<select name="state">-->
                            <#--<option>请选择</option>-->
                            <#--<#if lottery_fhzt?? && ( lottery_fhzt?size gt 0 )>-->
                                <#--<#list lottery_fhzt as typeObj>-->
                                    <#--<option value="${(typeObj.fieldKey)!}"-->
                                            <#--<#if lotteryLog??&&lotteryLog.state??&&typeObj.fieldKey??&&lotteryLog.state==typeObj.fieldKey>selected</#if>> ${(typeObj.fieldKey)!}</option>-->
                                <#--</#list>-->
                            <#--</#if>-->
                        <#--</select>-->
                    <#--</td>-->
                <#--</tr>-->


                <tr>
                    <td width="100">快递公司：</td>
                    <td colspan="3">
                        <div style="width: 350px;">
                        <select name="kuaidiGS" required="required">
                            <option value="">请选择</option>
                            <#if kdgs?? && ( kdgs?size gt 0 )>
                                <#list kdgs as typeObj>
                                    <option value="${(typeObj.fieldKey)!}"
                                            <#if lotteryLog??&&lotteryLog.kuaidiGS??&&typeObj.fieldKey??&&lotteryLog.kuaidiGS==typeObj.fieldKey>selected</#if>> ${(typeObj.fieldKey)!}</option>
                                </#list>
                            </#if>
                        </select></div>
                    </td>
                </tr>
                <tr>
                    <td width="100">快递单号：</td>
                    <td colspan="3">
                        <input type="text" name="kuaididanhao" value="${lotteryLog.kuaididanhao!}" style="width: 350px;" class="layui-input">
                    </td>
                </tr>
            <#else>
                <tr>
                    <td width="100">提示消息：</td>
                    <td colspan="3">

                        <textarea  name="sendRemake">${lotteryLog.sendRemake!}</textarea>
                        <span class="color_r2">提示用户的内容</span>
                    </td>
                </tr>

            </#if>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button"   id="submit" value="发货" class="layui-btn">
                        <#--<input type="button"   id="back" value="返回" class="layui-btn layui-btn-primary">-->
                        <a href="${referer}" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                        &nbsp;
                        <input type="button"   id="jjsubmit" value="拒绝发货" class="layui-btn layui-btn-danger">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/lotteryLog.js" src="${ctx}/js/require.js"></script>
</body>
</html>
