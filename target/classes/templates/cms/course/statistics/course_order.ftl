<table class="table">
    <tr>
        <td>课程标题：${(situation.title)!}</td>
        <td>授课方式：
        <#list teachMethodType as type>
            <#if type.name() == situation.teachingMethod>${type.description}</#if>
        </#list>
        </td>
        <td>人数限制：${(situation.peopleLimit)!}</td>
        <td>订购次数：${(situation.orderNum)!}</td>
        <td>累计收入：${(situation.generalIncome)!}</td>
        <td>累计积分：${(situation.aggregateScore)!}</td>
    </tr>
    <tr>
        <td colspan="2">报名时间：${(situation.applyTimeBegin?string("yyyy-MM-dd"))!}</td>
        <td colspan="2">签到时间：${(situation.signTimeBegin?string("yyyy-MM-dd"))!}</td>
        <td colspan="2"></td>
    </tr>
</table>
<div class="nycol_list">
    <table class="ny_tab_t">
        <tr>
            <td>
                用户名:
                <input type="text" id="keywords" value="${RequestParameters["username"]!}"  style="width:200px; margin:0px; ">
                订购时间:
                <input id="study_starttime" value="${RequestParameters["startTime"]!}"  type="text" data-type="datebox" style="width:130px;">
                <span>至</span>
                <input id="study_endtime" value="${RequestParameters["endTime"]!}"  type="text" data-type="datebox" style="width:130px;">
            </td>
            <td>
                <div class="nycon_l_t_btn text-right">
                    <input data-name="order" data-goodsId="${goodsId!}" type="button" value="查询" class="btn btn-sm query_btn js_query_order">
                </div>
            </td>
        </tr>
    </table>
</div>
<table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
    <tr>
        <th width="45">序号</th>
        <th style="text-align: center">用户名</th>
        <th style="text-align: center">会员等级</th>
        <th style="text-align: center">订购价格</th>
        <th style="text-align: center">订单状态</th>
        <th style="text-align: center">订购时间</th>
        <th style="text-align: center">联系电话</th>
        <th style="text-align: center">订单详情</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if list?? && (list?size > 0) >
        <#list list as item>
            <tr>
                <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
                <td align="center"><a class="ljc_00bcd4 opendialog"  data-val="4" href="javascript:;" data-url="/consumerManager/consumer/info.php?id=${item.user.id}">${(item.user.username)!}</a></td>
                <td align="center">${(item.nowVipLevelName)!}</td>
                <td align="center">
                    ${(item.totalPrice)!}<#if item.tradeMethod =='POINTS'>积分<#else>${(item.tradeMethod)!}</#if>
                </td>
                <td align="center">
                    <#if orderStatus?? && ( orderStatus?size gt 0 )>
        				<#list orderStatus as orderStatu>
                            <#if item.orderStatus == orderStatu.fieldValue>${orderStatu.fieldKey}</#if>
                        </#list>
        			</#if>
                </td>
                <td align="center">${(item.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                <td align="center">${(item.user.phone)!}</td>
                <td align="center">
                    <a  href="javascript:;" data-url="/financed/orderInfo.php?doType=1&lookType=0&id=${item.orderNo}" class="ljc_00bcd4 opendialog">查看</a></td>
            </tr>
        </#list>
    </#if>
    </tbody>
</table>
<#if list?? && (list?size > 0) >
<table class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>