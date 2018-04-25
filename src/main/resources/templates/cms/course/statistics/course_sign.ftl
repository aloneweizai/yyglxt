<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<table class="table">
    <tr>
        <td>课程标题：${(situation.title)!}</td>
        <td>授课方式：
        <#list teachMethodType as type>
            <#if type.name() == situation.teachingMethod>${type.description}</#if>
        </#list>
        </td>
        <td>人数限制：${(situation.peopleLimit)!}</td>
        <td>报名总数：${(situation.applyNum)!}</td>
        <td>签到总人数：${(situation.signNum)!}</td>
    </tr>
    <tr>
        <td colspan="2">报名时间：${(situation.applyTimeBegin?string("yyyy-MM-dd"))!}</td>
        <td colspan="2">签到时间：${(situation.signTimeBegin?string("yyyy-MM-dd"))!}</td>
        <td></td>
    </tr>
</table>
<div class="nycol_list">
    <table class="ny_tab_t">
        <tr>
            <td>
                用户名:
                <input type="text" id="keywords" value="${RequestParameters["username"]!}"  style="width:200px; margin:0px; ">
                报名时间:
                <input id="study_starttime" value="${RequestParameters["begintime"]!}"  type="text" data-type="datebox" style="width:130px;">
                <span>至</span>
                <input id="study_endtime" value="${RequestParameters["endtime"]!}"  type="text" data-type="datebox" style="width:130px;">
            </td>
            <td>
                <div class="nycon_l_t_btn text-right">
                    <input data-name="sign" type="button" value="查询" class="btn btn-sm query_btn js_query_sign">
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
        <th style="text-align: center">用户昵称</th>
        <th style="text-align: center">用户名</th>
        <th style="text-align: center">报名时间</th>
        <th style="text-align: center">联系电话</th>
        <th style="text-align: center">签到时间</th>
        <th style="text-align: center">签到IP</th>
        <th style="text-align: center">签到地点</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if list?? && (list?size > 0) >
        <#list list as item>
        <tr>
            <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
            <td align="center">${(item.nickname)!}</td>
            <td align="center">
            <#if canQuery??>
                <a class="ljc_00bcd4 opendialog"  data-val="4" href="javascript:;" data-url="/consumerManager/consumer/info.php?id=${item.userId!}">${(item.username)!}</a>
                <#else>
                ${(item.username)!}
            </#if>
            </td>
            <td align="center">${(item.applyTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
            <td align="center">${(item.phone)!}</td>
            <td align="center">${(item.signTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
            <td align="center">${(item.signIP)!}</td>
            <td align="center">${(item.signSite)!}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<#if list?? && (list?size > 0) >
<table class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>