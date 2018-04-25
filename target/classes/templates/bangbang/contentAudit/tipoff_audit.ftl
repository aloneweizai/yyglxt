<br>
<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var ctx = "${ctx}";
</script>
<input type="hidden" class="js_currLink" value="${currLink!}">

<div class="tatle_box">
    <form class="layui-form" action="" id="form1">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select class="layui-select" id="status" >
                        <option value="">--状态--</option>
                        <option value="approved" <#if RequestParameters["status"]?? && RequestParameters["status"] == 'approved'>selected</#if>>审核通过</option>
                        <option value="auditing" <#if RequestParameters["status"]?? && RequestParameters["status"] == 'auditing'>selected</#if>>待审核</option>
                        <option value="refuse" <#if RequestParameters["status"]?? && RequestParameters["status"] == 'refuse'>selected</#if>>审核拒绝</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button  class="layui-btn js_query_tipOff">查询</button>
                <#--<div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>-->
                </div>
            </div>
        </div>

        <table class="layui-table tables">
            <colgroup>
                <col width="3%">
                <col width="21%">
                <col width="21%">
                <col width="6%">
                <col width="8%">
                <col width="10%">
                <col width="6%">
                <col width="10%">
            </colgroup>
            <thead>
            <tr>
                <th style="text-align: center;">序号</th>
                <th style="text-align: center;">举报内容</th>
                <th style="text-align: center;">举报原因</th>
                <th style="text-align: center;">举报人</th>
                <th style="text-align: center;">举报人IP</th>
                <th style="text-align: center;">举报时间</th>
                <th style="text-align: center;">状态</th>
                <th style="text-align: center;">操作</th>
            </tr>
            </thead>
            <tbody class="js-body-tr">
                <#if list?? && (list?size > 0) >
                    <#list list as item>
                        <tr>
                            <td>${pagerSpec.offset + item_index + 1}</td>
                            <td><p class="webkit">
                                <a class="ljc_00bcd4"
                                    <#list systemAuditLinkType as link>
                                   <#if item.sourceType == link.name()>href="${SNSURL}${link.description}${item.questionId}"</#if>
                                    </#list>
                                   target="_blank">
                            ${item.content!?replace('<([^>]*)>',"",'r')}
                            </a>
                            </p></td>
                            <td><p class="webkit">${item.reason?replace('<([^>]*)>',"",'r')}</p></td>
                            <td>${(item.createUsername)!}</td>
                            <td>${(item.createIP)!}</td>
                            <td>${(item.createTime?string("yyyy-MM-dd"))!}</td>
                            <td>
                                <#list tipOffAuditStatus as status>
                                    <#if item.status?? && item.status == status.name()>${status.description}</#if>
                                </#list>
                            </td>
                            <td style="text-align: center">
                                <a class="js_tipoff_view pn-opt" data-href="${ctx}/bangbang/contentAudit/tipOff/view/${(item.id)!}.php">查看</a>
                                <#if item.status == 'auditing'>
                                    <span class="btn-span">｜</span>
                                    <a class="js_tipoff_view pn-opt" data-href="${ctx}/bangbang/contentAudit/tipOff/view/${(item.id)!}.php">审批</a>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
        <#if list?? && (list?size > 0) >
            <table  width="100%" class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
        </#if>

        <div style="display: none;padding: 30px;" id="tipoff_rewardsPoints_pop">
            <p>请输入奖励积分</p>
            <#--<p style="margin-top: 20px;">-->
                <#--<input type="text" id="tipoff_rewardsPoints" value="" placeholder="请输入奖励积分" class="layui-input">-->
            <#--</p>-->
            <select id="tipoff_rewardsPoints" class="layui-select">
                <option value="">请选择奖励积分</option>
                <option value="0">0分</option>
                <option value="10">10分</option>
                <option value="20">20分</option>
                <option value="30">30分</option>
                <option value="40">40分</option>
                <option value="50">50分</option>
                <option value="60">60分</option>
                <option value="70">70分</option>
                <option value="80">80分</option>
                <option value="90">90分</option>
                <option value="100">100分</option>
            </select>

            <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
                <button data-id="" class="layui-btn js_submit_tipoff_rewardsPoints">立即提交</button>
                <button class="layui-btn layui-btn-primary" name="close-rewardsPoints-btn">关闭</button>
            </div>
        </div>

        <div style="display:none;padding: 30px;" id="tipoff_refuse_pop">
            <p>拒绝理由</p>
            <textarea id="tipoff_refuseReason" placeholder="请输入内容" class="layui-textarea"></textarea>
            <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
                <button data-id="" class="layui-btn js_submit_tipoff_refuse">立即提交</button>
            </div>
        </div>
    </form>
</div>
