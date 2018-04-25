<br>
<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var ctx = "${ctx}";
</script>
<input type="hidden" class="js_currLink" value="${currLink!}">

<div class="tatle_box">
    <form class="layui-form" action="" id="forma-4">
        <table class="layui-table tables">
            <colgroup>
                <col width="5%">
                <col width="15%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
                <col width="20%">
            </colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>IP</th>
                <th>提问拉黑数</th>
                <th>回答拉黑数</th>
                <th>评论拉黑数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody align="center" class="js-body-tr">
                <#if list?? && (list?size > 0) >
                    <#list list as item>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
                        <td align="center">${(item.ip)!}</td>
                        <td align="center">${(item.questionDisableCnt)!}</td>
                        <td align="center">${(item.answerDisableCnt)!}</td>
                        <td align="center">${(item.commentDisableCnt)!}</td>
                        <td align="center">
                            <#if item.isDisable>
                                <a class="js_changeStatus pn-opt" data-href="${ctx}/bangbang/contentAudit/ip/enable/${(item.ip)!}.php">撤销禁言</a>
                            <#else>
                                <a data-ip="${(item.ip)!}" class="js_disableIP_pop pn-opt">禁言</a>
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
<table width="100%" class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>
<div style="display: none;padding: 30px;" id="disable_pop">
    <p>选择禁言天数</p>
    <select name="disable_time" lay-verify="required" style="width: 340px;height: 30px">
        <option value="">请选择禁言天数</option>
        <option value="10">10天</option>
        <option value="30">1个月</option>
        <option value="180">6个月</option>
        <option value="365">1年</option>
    </select>
    <p style="margin-top: 20px;">禁言理由</p>
    <textarea name="disable_reason" placeholder="请输入内容" class="layui-textarea"></textarea>
    <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
        <button data-ip="" data-href="${ctx}/bangbang/contentAudit/ip/disable.php" class="layui-btn js_disableIP" lay-submit>立即提交</button>
        <button class="layui-btn layui-btn-primary" name="close-layer-btn">关闭</button>
    </div>
</div>