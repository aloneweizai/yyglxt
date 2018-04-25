<br>
<script type="text/javascript">
    <#assign ctx=request.getContextPath()>var ctx = "${ctx}";
</script>
<input type="hidden" class="js_currLink" value="${currLink!}">
<div class="nycol_list">
    <form class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">帮派名称</label>
                    <div class="layui-input-inline">
                        <input type="text" id="factionName" value="${RequestParameters["factionName"]!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">分配状态</label>
                    <div class="layui-input-inline">
                        <select id="status">
                            <option value="">-分配状态-</option>
                            <option <#if RequestParameters["status"]?? && RequestParameters["status"] == 'true'>selected</#if> value="true">已分配</option>
                            <option <#if RequestParameters["status"]?? && RequestParameters["status"] == 'false'>selected</#if> value="false" >未分配</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">月份</label>
                    <div class="layui-input-inline">
                        <input id="date"  value="${RequestParameters["date"]!}" type="text" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">帮派数量</label>
                    <div class="layui-input-inline" style="width: 180px">
                        <input  type="text" id="factionNumOfMonth" value="${RequestParameters["factionNumOfMonth"]!}"  placeholder="每月荣誉值排行帮派数" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" >
                        <div type="button" class="js_query_factionSetting layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
    <tr>
        <th width="45">序号</th>
        <th style="text-align: center">月份</th>
        <th style="text-align: center">帮派名称</th>
        <th style="text-align: center">帮主</th>
        <th style="text-align: center">所属分类</th>
        <th style="text-align: center">荣誉值</th>
        <th style="text-align: center">帮派人数</th>
        <th style="text-align: center">剩余积分</th>
        <th style="text-align: center">分配积分</th>
        <th style="text-align: center">分配状态</th>
        <th style="text-align: center">分配时间</th>
        <th style="text-align: center">操作</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if list?? && (list?size > 0) >
        <#list list as item>
        <tr>
            <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
            <td align="center">${(item.date)!}</td>
            <td align="center">${(item.factionName)!}</td>
            <td align="center">${(item.leaderName)!}</td>
            <td align="center">${(item.classifyName)!}</td>
            <td align="center">${((item.honor?number)/10)!}</td>
            <td align="center">${(item.memberCnt)!}</td>
            <td align="center">${(item.totalPoints)!}</td>
            <td align="center">${(item.rewardsPoints)!}</td>
            <td align="center"><#if item.status?c == 'true'>已分配<#else>未分配</#if></td>
            <td align="center"><#if item.rewardDate??>${(item.rewardDate?string("yyyy-MM-dd"))!}</#if></td>
            <td align="center">
                <#if item.status?c  == 'false'>
                    <a class="js_setting_faction pn-opt" data-date="${(item.date)!}" data-factionId="${(item.factionId)!}">分配</a>
                </#if>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<#if list?? && (list?size > 0) >
<table class="yy_fanye" width="100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>
<div style="display: none;padding: 30px;" id="factionSetting_pop">
    <p>请输入积分</p>
    <p style="margin-top: 20px;">
        <input type="text" name="awardPoint" data-factionId="" data-date="" value="" lay-verify="title" autocomplete="off" placeholder="请输入积分" class="layui-input">
    </p>
    <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
        <button data-href="${ctx}/bangbang/rewardPointSetting/faction.php" class="layui-btn js_reward_submit">立即提交</button>
        <button class="layui-btn layui-btn-primary" name="close-layer-btn">关闭</button>
    </div>
</div>
