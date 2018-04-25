
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
                <label class="layui-form-label">审核状态</label>
                <div class="layui-input-inline">
                    <select id="status">
                        <option value="">-审核状态-</option>
                        <option <#if RequestParameters["status"]?? && RequestParameters["status"] == '1'>selected</#if> value="1">待审核</option>
                        <option <#if RequestParameters["status"]?? && RequestParameters["status"] == '2'>selected</#if> value="2" >审核通过</option>
                        <option <#if RequestParameters["status"]?? && RequestParameters["status"] == '3'>selected</#if> value="3" >审核拒绝</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline" >
                    <div type="button" class="js_query_factionMember layui-btn">查询</div>
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
        <th width="54"><div class="nycon_sel_btn js_selectAll" data-check=false>全选</div></th>
        <th width="45">序号</th>
        <th style="text-align: center">帮派名称</th>
        <th style="text-align: center">成员名称</th>
        <th style="text-align: center">成员等级</th>
        <th style="text-align: center">分配积分</th>
        <th style="text-align: center">分配时间</th>
        <th style="text-align: center">审核状态</th>
        <th style="text-align: center">审核时间</th>
        <th style="text-align: center">备注</th>
        <th style="text-align: center">操作</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if list?? && (list?size > 0) >
        <#list list as item>
        <tr>
            <td>
                <#if item.status == '1'><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${item.id!}"></#if>
            </td>
            <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
            <td align="center">${(item.factionName)!}</td>
            <td align="center">${(item.memberName)!}</td>
            <td align="center">${(item.memberLevel)!}</td>
            <td align="center">${(item.integral)!}</td>
            <td align="center">${(item.createTime?string("yyyy-MM-dd"))!}</td>
            <td align="center">
                <#if item.status == '1'>待审核</#if>
                <#if item.status == '2'>审核通过</#if>
                <#if item.status == '3'>审核拒绝</#if>
            </td>
            <td align="center"><#if item.status!='1' && item.updateTime??>${(item.updateTime?string("yyyy-MM-dd"))!}</#if></td>
            <td align="center">${(item.refuseReason)!}</td>
            <td align="center">
                <#if item.status == '1'>
                    <a class="js_audit_pass pn-opt" data-status="2" data-id="${(item.id)!}">审核通过</a>
                    | <a class="js_audit_refuse pn-opt" data-status="3" data-id="${(item.id)!}">审核拒绝</a>
                </#if>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<#if list?? && (list?size > 0) >
<div class="button_caozuo_fenye">
    <button data-href="${ctx}/system/operator/enable.php" class="js_batch_pass layui-btn">批量通过</button>
    <button data-href="${ctx}/system/operator/enable.php" class="js_batch_refuse layui-btn layui-btn-primary">批量拒绝</button>
</div>
<table class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>

<div style="display: none;padding: 30px;" id="factionMemberRefuse_pop">
    <p>拒绝理由</p>
    <p style="margin-top: 20px;">
        <textarea name="refuseReason" value="" lay-verify="title" autocomplete="off" placeholder="请输入内容" class="layui-textarea"></textarea>
    </p>
    <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
        <button data-id="" class="layui-btn js_memberRefuse_submit">立即提交</button>
        <button class="layui-btn layui-btn-primary" name="close-layer-btn">关闭</button>
    </div>
</div>

<div style="display: none;padding: 30px;" id="factionMemberBatchRefuse_pop">
    <p>拒绝理由</p>
    <p style="margin-top: 20px;">
        <textarea name="batchRefuseReason" value="" lay-verify="title" autocomplete="off" placeholder="请输入内容" class="layui-textarea"></textarea>
    </p>
    <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
        <button class="layui-btn js_batchMemberRefuse_submit">立即提交</button>
        <button class="layui-btn layui-btn-primary" name="close-layer-btn">关闭</button>
    </div>
</div>
