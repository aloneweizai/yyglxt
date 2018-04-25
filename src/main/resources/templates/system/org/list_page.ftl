<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<input type="hidden" class="js_currLink" value="${currLink}">
<form class="layui-form">
    <table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
        <th width="5%">序号</th>
        <th width="5%">全选</th>
        <th>机构名称</th>
        <th>机构类型</th>
        <th>上级机构</th>
        <th>联系人</th>
        <th>联系电话</th>
        <th>联系地址</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>操作选项</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if orgs?? && (orgs?size > 0)>
        <#list orgs as org>
            <tr>
                <td class="td_i">${pagerSpec.offset + org_index + 1}</td>
                <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${org.id}"></td>
                <td>${(org.name)!}</td>
                <td>
                    <#list orgTypes as orgType>
                                        <#if orgType.code == org.type>${orgType.description}</#if>
                                    </#list>
                </td>
                <td>${(org.parentName)!}</td>
                <td>${(org.contact)!}</td>
                <td>${(org.phone)!}</td>
                <td>${(org.address)!}</td>
                <td class="js-div-status">
                    <#if org.status?? && org.status>
                        <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                    <#else>
                        <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                    </#if>
                </td>
                <td>${org.createTime?string("yyyy-MM-dd")}</td>
                <td><a data-href="${ctx}/system/org/edit.php?id=${org.id}" class="js_edit pn-opt">编辑</a> |
                    <#if org.status?? && org.status>
                        <a data-href="${ctx}/system/org/enable.php?id=${org.id}&enable=false" class="js_enable pn-opt">停用</a>
                    <#else>
                        <a data-href="${ctx}/system/org/enable.php?id=${org.id}&enable=true"  class="js_enable pn-opt">启用</a>
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
<#if orgs?? && (orgs?size > 0)>
<div class="button_caozuo_fenye">
    <div class="layui-btn nycon_sel_btn js_selectAll">全选</div>
<div data-href="${ctx}/system/org/enable.php" class="js_disable_batch layui-btn  layui-btn-danger">批量停用</div>
</div>
<table class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>