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
        <td>浏览人数：${(situation.browseNum)!}</td>
        <td>学习次数：${(situation.watchNum)!}</td>
    </tr>
</table>
<div class="nycol_list">
        <table class="ny_tab_t">
            <tr>
                <td>
                    用户名:
                    <input type="text" id="keywords" value="${RequestParameters["username"]!}"  style="width:200px; margin:0px;">
                    学习时间:
                    <input id="study_starttime" value="${RequestParameters["begintime"]!}"  type="text" data-type="datebox" style="width:130px;">
                    <span>至</span>
                    <input id="study_endtime" value="${RequestParameters["endtime"]!}"  type="text" data-type="datebox" style="width:130px;">
                </td>
                <td>
                    <div class="nycon_l_t_btn text-right">
                        <input  data-name="study" type="button" value="查询" class="btn btn-sm query_btn js_query_study">
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
                <th style="text-align: center">学习课件</th>
                <th style="text-align: center">学习时间</th>
                <th style="text-align: center">学习进度</th>
                <th style="text-align: center">访问IP</th>
                <th style="text-align: center">访问地点</th>
            </tr>
        </thead>
        <tbody class="js-body-tr pn-ltbody">
            <#if list?? && (list?size > 0) >
                <#list list as item>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
                        <td align="center">
                    <#if canQuery??>
                            <a class="ljc_00bcd4 opendialog"  data-val="4" href="javascript:;" data-url="/consumerManager/consumer/info.php?id=${item.userId}">${(item.username)!}</a>
                        <#else>
                        ${(item.username)!}
                    </#if>
                    </td>
                        <td align="center">${(item.memberGrade)!}</td>
                        <td align="center">${(item.coursewareName)!}</td>
                        <td align="center">${(item.studyTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td align="center">${(item.studyDuration)!}</td>
                        <td align="center">${(item.visitIP)!}</td>
                        <td align="center">${(item.visitSite)!}</td>
                    </tr>
                </#list>
            </#if>
        </tbody>
    </table>
<#if list?? && (list?size > 0) >
    <table class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>