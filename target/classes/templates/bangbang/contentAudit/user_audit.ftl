<br>
<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var ctx = "${ctx}";
</script>
<input type="hidden" class="js_currLink" value="${currLink!}">

<div class="tatle_box">
    <form class="layui-form" action="" id="forma-3">
        <div class="layui-carousel carousels">
            <#--<div class="layui-row row">-->
                <#--<div class="layui-col-lg2 layui-col-md3 lg">-->
                    <#--<div class="layui-form-item ssuo">-->
                        <#--<label class="layui-form-label">合规状态</label>-->
                        <#--<div class="layui-input-block blocks">-->
                            <#--<select name="city" lay-verify="required">-->
                                <#--<option value=""></option>-->
                                <#--<option value="0">未禁言用户</option>-->
                                <#--<option value="1">已禁言用户</option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="layui-col-lg2 layui-col-md2 lg">-->
                    <#--<div class="layui-inline ssuo">-->
                        <#--<label class="layui-form-label">时间</label>-->
                        <#--<div class="layui-input-inline inlines ">-->
                            <#--<input type="text" id="test2" class="layui-input" placeholder="yyyy-MM-dd">-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="layui-col-lg layui-col-md lg">-->
                    <#--<button class="layui-btn">查找</button>-->
                <#--</div>-->
            <#--</div>-->
        </div>
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
                <th>用户姓名</th>
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
                    <td align="center">${(item.username)!}</td>
                    <td align="center">${(item.questionDisableCnt)!}</td>
                    <td align="center">${(item.answerDisableCnt)!}</td>
                    <td align="center">${(item.commentDisableCnt)!}</td>
                    <td align="center">
                        <#if item.isDisable>
                            <a class="js_changeStatus pn-opt" data-href="${ctx}/bangbang/contentAudit/user/enable/${item.userId}.php">撤销禁言</a>
                        <#else>
                            <a data-userId="${(item.userId)!}" class="js_disableUser_pop pn-opt">禁言</a>
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
        <button data-userId="" data-href="${ctx}/bangbang/contentAudit/user/disable.php" class="layui-btn js_disableUser" lay-submit>立即提交</button>
        <button class="layui-btn layui-btn-primary" name="close-layer-btn">关闭</button>
    </div>
</div>