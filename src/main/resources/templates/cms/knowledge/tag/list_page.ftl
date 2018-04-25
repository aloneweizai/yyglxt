<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<input type="hidden" class="js_currLink" value="${currLink}">
<table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
        <#--<th width="54"><div class="nycon_sel_btn js_selectAll">全选</div></th>-->
        <th width="50">序号</th>
        <th class="text_c">名称</th>
        <th class="text_c">描述</th>
        <th class="text_c">状态</th>
        <th class="text_c">创建时间</th>
        <th class="text_c">操作</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if tags?? && (tags?size > 0) >
        <#list tags as tag>
        <tr>
            <#--<td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${tag.id}"></td>-->
            <td class="td_i">${pagerSpec.offset + tag_index + 1}</td>
            <td align="center">${(tag.name)!}</td>
            <td align="center">${(tag.description)!}</td>
            <td align="center">
                <#if tag.status?? && tag.status>
                    <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                <#else>
                    <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                </#if>
            </td>
            <td align="center">${tag.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td align="center">
                <a data-href="${ctx}/cms/knowTag/edit.php?id=${tag.id}" class="js_edit pn-opt">编辑</a> |
                <a data-id="${tag.id}" data-href="${ctx}/cms/knowTag/delete/${tag.id}.php" class="js_delete pn-opt">删除</a> |
                <#if tag.status?? && tag.status>
                    <a data-id="${tag.id}"  data-href="${ctx}/cms/knowTag/enable/${tag.id}/false.php" class="js_enable pn-opt">停用</a>
                <#else>
                    <a data-id="${tag.id}" data-href="${ctx}/cms/knowTag/enable/${tag.id}/true.php" class="js_enable pn-opt">启用</a>
                </#if>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<#if tags?? && (tags?size > 0) >
<table class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
    <#--<input type='button' data-href="${ctx}/cms/knowTag/batch/delete.php" class="js_del_batch_btn btn btn-default btn-sm pn-opt" value='批量删除'/>-->
</#if>
