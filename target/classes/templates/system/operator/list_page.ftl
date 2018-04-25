<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<input type="hidden" class="js_currLink" value="${currLink}">
<div>
<form class="layui-form">
    <table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
        <th width="5%">序号</th>
        <th width="5%">全选</th>
        <th>用户账户(员工号)</th>
        <th>真实姓名</th>
        <th>手机号码</th>
        <th>职位</th>
        <th>所属部门</th>
        <th>账号状态</th>
        <th>创建时间</th>
        <th>最后修改时间</th>
        <th>操作选项</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if users?? && (users?size > 0) >
        <#list users as user>
        <tr>
            <td class="td_i">${pagerSpec.offset + user_index + 1}</td>
            <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${user.id}"></td>
            <td>${user.username}</td>
            <td>${user.nickname}</td>
            <td>${user.phone!}</td>
            <td>${user.job!}</td>
            <td>${user.orgName!}</td>
            <td class="js-div-status">
                <#if user.status?? && user.status>
                    <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                <#else>
                    <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                </#if>
            </td>
            <td>${user.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td>${user.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td>
                <a data-href="${ctx}/system/operator/edit.php?id=${user.id}" class="js_edit pn-opt">编辑</a> |
                <#if user.status?? && user.status>
                    <a data-href="${ctx}/system/operator/enable.php?id=${user.id}&enable=false" class="js_enable pn-opt">停用</a>
                <#else>
                    <a data-href="${ctx}/system/operator/enable.php?id=${user.id}&enable=true" class="js_enable pn-opt">启用</a>
                </#if>
            </td>
        </tr>
        </#list>
    <#else>
        <tr>
            <td colspan="11">
                <p style="text-align: center;">暂无内容</p>
            </td>
        </tr>
    </#if>
    </tbody>
</table>
</form>
</div>
<#if users?? && (users?size > 0) >
    <div class="button_caozuo_fenye">
        <div class="layui-btn nycon_sel_btn js_selectAll">全选</div>
        <div data-href="${ctx}/system/operator/enable.php" class="layui-btn layui-btn-danger js_disable_batch">批量停用</div>
    </div>
    <table class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>

</#if>