<div>
<form name="_pagination_form"  method="GET">
    <table name="_pagination_table" class="yy_fanye" style="width: 100%">
        <tbody>
        <tr>
            <#if (pagination.page <= 0) >
                <#assign page=1>
            <#else>
                <#assign page=pagination.page>
            </#if>
            <td align="center">
                <input type="hidden" name="totalItems" value="${(pagination.totalItems?c)!}">
                <input type="hidden" name="size" value="${pagination.size}">
                <input type="hidden" name="page" value="${page}">
                <input type="hidden" name="totalPage" value="${(pagination.totalPage?c)!}">

                共&nbsp;<span name="_pagi_total_items">${(pagination.totalItems?c)!}</span>&nbsp;条&nbsp;&nbsp;每页&nbsp;<span
                    name="_pagi_size">${pagination.size}</span>&nbsp;条
                <input class=" btn btn-default btn-xs" value="首 页" name="_pagi_first_btn" type="button">
                <input class=" btn btn-default btn-xs" value="上一页" name="_pagi_pre_btn" type="button">
                <input class=" btn btn-default btn-xs" value="下一页" name="_pagi_next_btn" type="button">
                <input class=" btn btn-default btn-xs" value="尾 页" name="_pagi_last_btn" type="button">

                当前&nbsp;<span name="_pagi_page">${page}</span>/<span
                    name="_pagi_total_page">${(pagination.totalPage?c)!}</span>&nbsp;页&nbsp;&nbsp;转到第&nbsp;
                <input style="width:50px" name="_pagi_goto_text" type="text"   onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                &nbsp;页&nbsp;<input class=" btn btn-default btn-xs" name="_pagi_goto_btn" value="转" type="button">
            </td>
        </tr>
        </tbody>
    </table>
</form>
</div>