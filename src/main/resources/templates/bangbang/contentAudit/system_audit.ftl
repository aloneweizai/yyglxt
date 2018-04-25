<br>
<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var ctx = "${ctx}";
</script>
<input type="hidden" class="js_currLink" value="${currLink!}">

<div id="form1">
    <div style="display: block" class="tatle_box">
        <form class="layui-form" >
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select class="layui-select" id="status" >
                            <option value="">--请选择--</option>
                            <option value="0" <#if RequestParameters["status"]?? && RequestParameters["status"] == '0'>selected</#if>>审核通过</option>
                            <option value="1" <#if RequestParameters["status"]?? && RequestParameters["status"] == '1'>selected</#if>>待审核</option>
                            <option value="2" <#if RequestParameters["status"]?? && RequestParameters["status"] == '2'>selected</#if>>拉黑</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" id="content" value="${RequestParameters["content"]!}" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">开始时间</label>
                    <div class="layui-input-inline">
                        <input id="beginTime" value="${RequestParameters["beginTime"]!}" type="text" class="layui-input date-item">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">结束时间</label>
                    <div class="layui-input-inline">
                        <input id="endTime" value="${RequestParameters["endTime"]!}" type="text" class="layui-input date-item">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button class="layui-btn js_query_sysAudit">查询</button>
                    <#--<div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>-->
                    </div>
                </div>
            </div>
            <table class="layui-table tables">
                <colgroup>
                    <col width="5%">
                    <col width="30%">
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                    <col width="15%">
                </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>内容</th>
                    <th>类别</th>
                    <th>时间</th>
                    <th>用户名称</th>
                    <th>所属分类</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody align="center" class="js-body-tr">
                    <#if list?? && (list?size > 0) >
                        <#list list as item>
                        <tr>
                            <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
                            <td align="center">
                                <p class="webkit">
                                    <a class="ljc_00bcd4"
                                        <#list systemAuditLinkType as link>
                                       <#--<#if item.sourceType == link.name()> data-s="${item.sourceType}" data-type="${item.sourceType}" data-id="${item.sourceId}" data-href="${SNSURL}${link.description}"</#if>-->
                                       <#if item.sourceType == link.name()>href="${SNSURL}${link.description}${item.linkId}"</#if>
                                        </#list>
                                       target="_blank">
                                    ${item.content!?replace('<([^>]*)>',"",'r')}
                                    </a>
                            </p></td>
                            <td align="center">
                                <#list systemAuditType as type>
                                    <#if item.sourceType == type.name()>${type.description}</#if>
                                </#list>
                            </td>
                            <td align="center">${(item.createTime?string("yyyy-MM-dd"))!}</td>
                            <td align="center">${(item.username)!}</td>
                            <td align="center">${(item.classifyName)!}</td>
                            <td align="center">
                            <#list contentAuditStatus as status>
                                <#if item.status == status.code>${status.description}</#if>
                            </#list>
                            </td>
                            <td align="center">
                                <a class="js_view" data-href="${ctx}/bangbang/contentAudit/system/view/${item.sourceType}/${item.sourceId}.php">查看</a>
                                <#if item.status == '1'> |
                                    <a class="js_changeStatus pn-opt" data-href="${ctx}/bangbang/contentAudit/system/${(item.id)!}/0.php">审核通过</a> |
                                    <a class="js_changeStatus pn-opt" data-href="${ctx}/bangbang/contentAudit/system/${(item.id)!}/2.php">拉黑</a>
                                </#if>
                                <#if item.status == '2'>
                                    | <a class="js_changeStatus pn-opt" data-href="${ctx}/bangbang/contentAudit/system/${(item.id)!}/1.php">撤销拉黑</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </form>
    </div>
<#if list?? && (list?size > 0) >
    <table width="100%" class="yy_fanye table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>

