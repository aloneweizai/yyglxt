<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<input type="hidden" class="js_currLink" value="${currLink}">
<form class="layui-form">
    <table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
        <th width="5%">序号</th>
        <th width="5%">全选</th>
        <th>菜单名称</th>
        <th>上级菜单</th>
        <th>菜单URL</th>
        <th>授权标识</th>
        <th>类型</th>
        <th>状态</th>
        <th>排序号</th>
        <th>操作选项</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if menus?? && (menus?size > 0) >
        <#list menus as menu>
        <tr>
            <td class="td_i">${pagerSpec.offset + menu_index + 1}</td>
            <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${menu.menuId}"></td>
            <td>${(menu.menuName)!}</td>
            <td>
                <#if (menu.parentId)! =="">--<#else>${(menu.parentName)!}</#if>
            </td>
            <td><#if (menu.type)! == "2" || (menu.type)! == "3">${(menu.menuUrl)!}<#else>--</#if></td>
            <td><#if (menu.type)! == "2" || (menu.type)! == "3">${(menu.perms)!}<#else>--</#if></td>
            <td>
                <#if (menu.type)! == "1">
                    <div class="layui-btn btn-xs btn_nocursor">目录</div>
                <#elseif (menu.type)! == "2">
                    <div class="btn btn-success btn-xs btn_nocursor">菜单</div>
                <#elseif (menu.type)! == "3">
                    <div class="btn btn-warning btn-xs btn_nocursor">按钮</div>
                </#if>
            </td>
            <td class="js-div-status">
                <#if menu.status?? && menu.status>
                    <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                <#else>
                    <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                </#if>
            </td>
            <td>${(menu.sort)!}</td>
            <td>
                <a data-href="${ctx}/system/menu/edit.php?id=${menu.menuId}" class="js_edit pn-opt">编辑</a> |
                <#if menu.status?? && menu.status>
                    <a data-href="${ctx}/system/menu/enable.php?id=${menu.menuId}&enable=false" class="js_enable pn-opt">停用</a>
                <#else>
                    <a data-href="${ctx}/system/menu/enable.php?id=${menu.menuId}&enable=true" class="js_enable pn-opt">启用</a> |
                    <a data-href="${ctx}/system/menu/delete/${menu.menuId}.php" class="js_delete pn-opt">删除</a>
                </#if>
            </td>
        </tr>
        </#list>
    <#else>
    <tr>
        <td colspan="10">
            <p style="text-align: center;">暂无内容</p>
        </td>
    </tr>
    </#if>
    </tbody>
</table>
</form>
<#if menus?? && (menus?size > 0) >
    <div class="button_caozuo_fenye">
        <div class="layui-btn nycon_sel_btn js_selectAll" data-check=false>全选</div>
        <div  data-href="${ctx}/system/menu/enable.php" class="js_disable_batch layui-btn layui-btn-danger">批量停用</div>
    </div>
    <table class="yy_fanye" style="width: 100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>
