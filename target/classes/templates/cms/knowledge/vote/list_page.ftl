<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<input type="hidden" class="js_currLink" value="${currLink}">
<form class="layui-form">
        <table class="layui-table" lay-size="sm">
            <thead>
            <th width="5%"><div class="nycon_sel_btn js_selectAll" data-check=false>全选</div></th>
            <th width="5%">序号</th>
            <th>投票人</th>
            <th width="8%">投票结果</th>
            <th>对应知识</th>
            <th>投票时间</th>
            <th width="10%">操作</th>
            </thead>
            <tbody class="js-body-tr pn-ltbody">
            <#if (knowVotes?? && knowVotes.dataList?? && knowVotes.dataList?size > 0)>
                <#list knowVotes.dataList as vote>
                <tr>
                    <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${(vote.id)!}">
                    <td class="td_i">${pagerSpec.offset + vote_index + 1}</td>
                    <td><#if vote.userName??>${vote.userName!}<#else>游客</#if></td>
                    <td><#if vote.isUseFull>有用<#else>没用</#if></td>
                    <td>${vote.knowledgeSubject}</td>
                    <td>${(vote.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    <td>
                        <a data-href="${ctx}/cms/knowVote/delete/${vote.id}.php" href="javascript:void(0)" class="js_delete pn-opt">删除</a>
                        <#if vote.userId?? && !vote.replyMsg??>
                            | <a data-id="${(vote.id)!}" data-userId="${(vote.userId)!}" class="js_answer pn-opt">回复</a>
                        </#if>
                        <#if vote.userId?? && vote.replyMsg??>
                            <a title="回复信息" data-container="body" data-toggle="popover" data-placement="left" data-content="${vote.replyMsg}">
                                <i class="tip_ico"></i>
                            </a>
                        </#if>
                    </td>
                </tr>
                </#list>
            <#else>
            <tr>
                <td colspan="8">
                    <p style="text-align: center;">暂无内容</p>
                </td>
            </tr>
            </#if>
            </tbody>
        </table>
<#if (knowVotes?? && knowVotes.dataList?? && knowVotes.dataList?size > 0)>
        <div class="button_caozuo_fenye">
            <input type='button' data-href="${ctx}/cms/knowVote/delete.php" class="js_del_batch_btn layui-btn layui-btn-danger" value='批量删除'/>
        </div>
            <table class="yy_fanye" style="width: 100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>
</form>