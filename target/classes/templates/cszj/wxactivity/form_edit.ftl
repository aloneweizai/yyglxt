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
    <form name="consumer_edit" action="${ctx}/cszjs/wxactivity/save.php" next-url="${ctx}/cszjs/wxactivity/list.php" method="post" class="layui-form">
        <table class="layui-table" lay-size="sm">
        <#if WxActivity??>
            <input type="hidden" name="id" value="${WxActivity.id!}">
            <input type="hidden" name="outdated" value="">
            <tr>
                <td width="120"><label for="id">活动编号：</label></td>
                <td colspan="3"><span>${WxActivity.id!}</span></td>
            </tr>
            <tr>
                <td width="120">活动名称：</td>
                <td colspan="3"><input type="text" name="name" value="${WxActivity.name!}" data-rule="required;length[2~16]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">活动描述：</td>
                <td colspan="3">
                    <textarea name="description" data-rule="length[~500]" rows="3" cols="60" value="${WxActivity.description!}"  class="layui-textarea">${WxActivity.description!}</textarea>
                </td>
            </tr>

            <tr>
                <td width="120">规则类型：</td>
                <td colspan="3">
					<input type="hidden" name="ruleType" value="${WxActivity.ruleType!}">
                    <input id="ruleType1"  name="ruleType2" <#if WxActivity.ruleType??&&WxActivity.ruleType =='1'>checked</#if> type="radio" value="1" title="随机口令" disabled>
                    <input id="ruleType2"  name="ruleType2" <#if WxActivity.ruleType??&&WxActivity.ruleType =='2'>checked</#if> type="radio" value="2" title="关键字口令" disabled>
                    <label style="font-size:12px; color: #999;">温馨提示：随机口令示例：系统会自动生成大写字母加数字的随机口令，如CHB4AJ78;如果【口令规则】为HB，则生成HBCHB4AJ78。关键字口令规则示例：艾博克#财税平台#爱我中华，只要匹配其中一个词即可抽奖。</label>
                    <#--<i class="glyphicon glyphicon-question-sign" title="口令规则示例：系统会自动生成8位数字加大写字母的口令，如：ABCDEF12。"></i>-->
                </td>
            </tr>
            <tr>
                <td width="120">规则定义：</td>
                <td colspan="3"><input type="text" name="rule" value="${WxActivity.rule!}" data-rule="length[2~50]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">金额类型：</td>
                <td colspan="3">
                    <input id="amountType1"name="amountType" lay-filter="amountType" <#if WxActivity.amountType??&&WxActivity.amountType == '1'>checked</#if> type="radio" value="1" title="固定金额">
                    <input id="amountType2" name="amountType" lay-filter="amountType"<#if WxActivity.amountType??&&WxActivity.amountType == '2'>checked</#if> type="radio" value="2" title="随机金额">
                </td>
            </tr>
            <tr>
                <td width="120">金额(元)：</td>
                <td colspan="3">
                    <div <#if WxActivity.amountType??&&WxActivity.amountType == '2'>style="display: inline-block"<#else>hidden</#if> id="minAmount">
                     <input type="text" name="minAmount"  data-rule="je;"  style=" width:160px;" value="${WxActivity.minAmount!}" placeholder="最小为1元" class="layui-input"/> -</div>
                    <input type="text" name="amount" data-rule="required;je;"value="${(WxActivity.amount?c)!}" style=" width:160px;"  placeholder="最小为1元" class="layui-input">
                    <br/>
                    <label style="font-size:12px; color: #999;">温馨提示：如果输入的金额为整数，发送的红包金额为整数；金额包含小数，发送的红包金额会包含'角、分'。如果选择'随机金额'时，'金额区间中最大金额'将作为随机金额的最大值。</label>
                    <#--<i class="glyphicon glyphicon-question-sign" title="如果输入的金额为整数，发送的红包金额为整数；金额包含小数，发送的红包金额会包含'角、分'。如果选择'随机金额'时，'金额'将作为随机金额的最大值。"></i>-->
                </td>
            </tr>
            <tr>
                <td width="120">中奖概率：</td>
                <td colspan="3">
                    <input type="text" name="probability" value="${WxActivity.probability!}" data-rule="required;"
                           style=" width:330px;" placeholder="中奖概率为百分比" class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">红包数量：</td>
                <td colspan="3"><input type="text" name="num" data-rule="required;integer(+);"value="${(WxActivity.num?c)!}"  style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">抽奖次数限制：</td>
                <td colspan="3"><input type="text" name="times" data-rule="required;integer(+);"value="${(WxActivity.times?c)!}"  style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">红包祝福语：</td>
                <td colspan="3"><input type="text" name="wishing" value="${WxActivity.wishing!}" data-rule="required;length[~16]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">红包备注：</td>
                <td colspan="3">
                    <input type="text" name="remark" value="${WxActivity.remark!}" data-rule="required;length[~16]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">活动起止时间：</td>
                <td colspan="3">
                    <label>
                        <input type="text" data-rule="required;"id="startTime" data-type="datetimebox" name="startTime" style=" width:150px;"
                               value="${(WxActivity.startTime)!}" class="layui-input">至
                        <input type="text" data-rule="required;"id="endTime" data-type="datetimebox" name="endTime" style=" width:150px;"
                               value="${(WxActivity.endTime)!}" class="layui-input">
                    </label>
                </td>
            </tr>
            <tr>
                <td width="120">排序：</td>
                <td colspan="3">
                    <input type="text" name="sort" value="${WxActivity.sort!}" data-rule="required;integer(+);length[~256]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td>是否赠送积分</td>
                <td colspan="3">
                    <input id="giftPoints1" name="giftPoints" type="radio" <#if WxActivity.giftPoints??&&WxActivity.giftPoints>checked</#if> value="true" title="是" lay-filter="giftPoints">
                    <input id="giftPoints2" name="giftPoints" type="radio"<#if WxActivity.giftPoints??&&!WxActivity.giftPoints>checked</#if> value="false" title="否"lay-filter="giftPoints">
                    <div id="points" <#if WxActivity.giftPoints??&&WxActivity.giftPoints>style="width:420px;display: inline-block"<#else>hidden</#if>>
                        赠送积分：
                        <input type="text" name="points" data-rule="integer(+);length[~256]" value="${(WxActivity.points?c)!}" style="width:260px;" class="layui-input"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>是否赠送优惠券</td>
                <td colspan="3">
                    <input id="giftCoupon1" name="giftCoupon" type="radio" <#if WxActivity.giftCoupon??&&WxActivity.giftCoupon>checked</#if> value="true" title="是" lay-filter="giftCoupon">
                    <input id="giftCoupon2" name="giftCoupon" type="radio" <#if WxActivity.giftCoupon??&&!WxActivity.giftCoupon>checked</#if> value="false" title="否" lay-filter="giftCoupon">
                    <div id="couponActivity"<#if WxActivity.giftCoupon??&&WxActivity.giftCoupon>style="width:420px;display: inline-block"<#else>hidden</#if>>
                        优惠券活动：
                        <input type="text" id="activityName" value="${activityName!}" style="width:260px;" class="layui-input" readonly="readonly"/>
                        <input type="text" id="activityId" name="activityId"  style="width:260px;" value="${WxActivity.activityId!}" class="layui-input" hidden/>
                        <div id="couponActivitySlect" class="layui-btn">选择</div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td colspan="3">
                    <input id="status1"name="status" <#if WxActivity.status>checked</#if> type="radio" value="true" title="启用">
                    <input id="status2"name="status" <#if !WxActivity.status>checked</#if> type="radio" value="false" title="停用">
                </td>
            </tr>
        <#else>
            <tr>
                <td width="120">活动名称：</td>
                <td colspan="3">
                    <input type="hidden" name="outdated" value="">
                    <input type="text" name="name"  data-rule="required;length[2~16]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120">活动描述：</td>
                <td colspan="3">
                    <textarea name="description" data-rule="length[~500]" rows="3" cols="60"  class="layui-textarea"></textarea>
                </td>
            </tr>
            <tr>
                <td width="120">规则类型：</td>
                <td colspan="3">
                    <input id="ruleType1" name="ruleType" type="radio" value="1" title="随机口令" checked>
                    <input id="ruleType2" name="ruleType" type="radio" value="2" title="关键字口令">
                    <label style="font-size:12px; color: #999;">温馨提示：随机口令示例：系统会自动生成大写字母加数字的随机口令，如CHB4AJ78;如果【口令规则】为HB，则生成HBCHB4AJ78。关键字口令规则示例：艾博克#财税平台#爱我中华，只要匹配其中一个词即可抽奖。</label>
                    <#--<i class="glyphicon glyphicon-question-sign" title="随机口令规则示例：系统会自动生成8位数字加大写字母的口令，如：ABCDEF12。"></i>-->
                </td>
            </tr>
            <tr>
                <td width="120">口令规则：</td>
                <td colspan="3"><input type="text" name="rule" data-rule="length[2~50]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">金额类型：</td>
                <td colspan="3">
                    <input  id="amountType1" name="amountType" type="radio" value="1" title="固定金额"  lay-filter="amountType" checked>
                    <label for="amountType1" style="width: 100px"></label>
                    <input id="amountType2" name="amountType" type="radio" value="2" title="随机金额"  lay-filter="amountType">
                    <label for="amountType2" style="width: 100px"></label>
                </td>
            </tr>
            <tr>
                <td width="120">金额(元)：</td>
                <td colspan="3">
                    <div id="minAmount" hidden><input type="text" name="minAmount"  data-rule="je;"  style=" width:160px;" placeholder="最小为1元" class="layui-input"> -</div>
                    <input type="text" name="amount"  data-rule="required;je;"  style=" width:160px;" placeholder="最小为1元" class="layui-input">
                    <label style="font-size:12px; color: #999;">温馨提示：如果输入的金额为整数，发送的红包金额为整数；金额包含小数，发送的红包金额会包含'角、分'。如果选择'随机金额'时，'金额区间中最大金额'将作为随机金额的最大值。</label>
                    <#--<i class="glyphicon glyphicon-question-sign"
                       title="如果输入的金额为整数，发送的红包金额为整数；金额包含小数，发送的红包金额会包含'角、分'。如果选择'随机金额'时，'金额'将作为随机金额的最大值。"></i>
                </td>-->
            </tr>
            <tr>
                <td width="120">中奖概率：</td>
                <td colspan="3"><input type="text" name="probability"  data-rule="required;" style=" width:330px;"
                                       placeholder="中奖概率为百分比" class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">红包数量：</td>
                <td colspan="3"><input type="text"data-rule="required;integer(+)" name="num"  style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">抽奖次数限制：</td>
                <td colspan="3"><input type="text" data-rule="required;integer(+);"name="times"  style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">红包祝福语：</td>
                <td colspan="3"><input type="text" name="wishing" data-rule="required;length[~16]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">红包备注：</td>
                <td colspan="3">
                    <input type="text" name="remark" data-rule="required;length[~16]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td width="120">活动起止时间：</td>
                <td colspan="3">
                    <label>
                        <input type="text" id="startTime"data-rule="required;" data-type="datetimebox" name="startTime" style=" width:150px;" class="layui-input">至
                        <input type="text" id="endTime" data-rule="required;"data-type="datetimebox" name="endTime" style=" width:150px;" class="layui-input">
                    </label>
                </td>
            </tr>
            <tr>
                <td width="120">排序：</td>
                <td colspan="3">
                    <input type="text" name="sort"  data-rule="required;integer(+);length[~256]" style=" width:330px;"  class="layui-input"></td>
            </tr>
            <tr>
                <td>是否赠送积分</td>
                <td colspan="3">
                    <input id="giftPoints1" name="giftPoints" type="radio" value="true" title="是" lay-filter="giftPoints">
                    <input id="giftPoints2" name="giftPoints" type="radio" value="false" title="否" lay-filter="giftPoints" checked>
                    <div id="points" style="width:420px;" hidden>
                        赠送积分：
                        <input type="text" name="points" data-rule="integer(+);length[~256]" style="width:260px;" class="layui-input"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>是否赠送优惠券</td>
                <td colspan="3">
                    <input id="giftCoupon1" name="giftCoupon" type="radio" value="true" title="是" lay-filter="giftCoupon">
                    <input id="giftCoupon2" name="giftCoupon" type="radio" value="false" title="否" lay-filter="giftCoupon" checked>
                    <div id="couponActivity" style="width:420px;" hidden>
                        优惠券活动：
                        <input type="text" id="activityName" style="width:260px;" class="layui-input" readonly="readonly"/>
                        <input type="text" id="activityId" name="activityId" class="layui-input" hidden/>
                        <div id="couponActivitySlect" class="layui-btn">选择</div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td colspan="3">
                    <input id="status1" name="status" type="radio" value="true" title="启用" checked>
                    <input id="status2" name="status" type="radio" value="false" title="停用">
                </td>
            </tr>
        </#if>
            <tr>
                <td></td>
                <td colspan="3"><button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
                    <a href="javascript:location.href=document.referrer" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
            </tr>
        </table>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="width: 90%;top:0px">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame" style="width: 100%;height: 500px;border:0" border="0"
                        frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-diss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cszj/wxactivity.js" src="${ctx}/js/require.js"></script>
</html>
