<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<!--查看-->
<@shiro.hasPermission name="cszj_wxactivity:look">
    <#assign canLook=true>
</@shiro.hasPermission>
<!--明细-->
<@shiro.hasPermission name="cszj_wxactivity:detail">
    <#assign canDetail=true>
</@shiro.hasPermission>
<!--停用-->
<@shiro.hasPermission name="cszj_wxactivity:stop">
    <#assign canStop=true>
</@shiro.hasPermission>
<!--启用-->
<@shiro.hasPermission name="cszj_wxactivity:start">
    <#assign canStart=true>
</@shiro.hasPermission>
<!--修改-->
<@shiro.hasPermission name="cszj_wxactivity:update">
    <#assign canUpdate=true>
</@shiro.hasPermission>
<!--删除-->
<@shiro.hasPermission name="cszj_wxactivity:delete">
    <#assign canDelete=true>
</@shiro.hasPermission>
<!--新增-->
<@shiro.hasPermission name="cszj_wxactivity:add">
    <#assign canDdd=true>
</@shiro.hasPermission>

<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/cszjs/wxactivity/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">活动名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(rq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <#if canDdd??>
                <a href="${ctx}/cszjs/wxactivity/editform.php"  class="layui-btn layui-btn-normal fr">添加</a>
                </#if>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>活动名称</th>
                    <th>活动描述</th>
                    <th>是否有效</th>
                    <th>活动状态</th>
                    <th>金额类型</th>
                    <th>红包金额(元)</th>
                    <th>已发送人数</th>
                    <th>已发送金额</th>
                    <th>已领取人数</th>
                    <th>已领取金额</th>
                    <th>参与次数</th>
                    <th>排序</th>
<#if canLook?? || canDetail?? || canStop?? || canStart?? || canUpdate?? || canDelete??>
                    <th>操作选项</th>
</#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if wxActivityRs?? && ( wxActivityRs?size gt 0 )>
                    <#list wxActivityRs as wxActivity>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+wxActivity_index+1}</td>
                        <td>${(wxActivity.name)!}</td>
                        <td>${(wxActivity.description)!}</td>
                        <td>
                            <#if wxActivity.outdated??&&wxActivity.outdated>
                                <div class="btn btn-danger btn-xs ">否</div>
                            <#else >
                                <div class="btn btn-success btn-xs ">是</div>
                            </#if>
                        </td>
                       <#-- <td>${wxActivity.startTime?string("yyyy-MM-dd HH:mm:ss")}&nbsp;--&nbsp;${wxActivity.endTime?string("yyyy-MM-dd HH:mm:ss")}</td>-->
                        <td>
                            <#if wxActivity.status??&&wxActivity.status>
                            <div class="btn btn-success btn-xs">启动</div>
                                <#else >
                                <div class="btn btn-danger btn-xs">停止</div>
                            </#if>
                        </td>
                        <td>
                            <#if wxActivity.amountType??&&wxActivity.amountType == '1' >
                                固定金额
                            <#else>
                                随机金额
                            </#if>
                        </td>
                        <td>
                            <#if wxActivity.amountType??&&wxActivity.amountType == '2'&& wxActivity.minAmount??>
                            ${(wxActivity.minAmount?c)!}&nbsp;-&nbsp;
                            </#if>
                        ${(wxActivity.amount?c)!}</td>
                        <td>${(wxActivity.sent)!}</td>
                        <td>${(wxActivity.sentAmount)!}</td>
                        <td>${(wxActivity.received)!}</td>
                        <td>${(wxActivity.receivedAmount)!}</td>
                        <td>${(wxActivity.nop)!}</td>
                        <td>${(wxActivity.sort)!}</td>
                        <#if canLook?? || canDetail?? || canStop?? || canStart?? || canUpdate?? || canDelete??>
                        <td>
                            <#if canLook??>
						<a data-type="opendialog" href="javascript:;" data-url="${ctx}/cszjs/wxactivity/look.php?id=${wxActivity.id}">查看</a>&nbsp;
                            </#if>
                        <#if wxActivity.status??&&wxActivity.status>
                            <#if canDetail??>
                            | <a href="${ctx}/cszjs/wxactivity/detailList.php?activityId=${wxActivity.id}">明细</a>&nbsp;
                            </#if>
                        <#if canStop??>
                            | <a data-type="delSig" data-confirm='确认停用?' data-okMsg='停用成功!' data-failMsg='停用失败' href="javascript:void(0);" data-href="${ctx}/cszjs/wxactivity/check.php?id=${wxActivity.id}&status=false" class="pn-opt">停用</a>
                        </#if>
                            <#else>
                                <#if canDetail??>
                                    | <a href="${ctx}/cszjs/wxactivity/detailList.php?activityId=${wxActivity.id}">明细</a>&nbsp;
                                </#if>
                            <#if canStart??>
                                | <a data-type="delSig" data-confirm='确认启用?' data-okMsg='启用成功!' data-failMsg='启用失败'
                                   href="javascript:void(0);" data-href="${ctx}/cszjs/wxactivity/check.php?id=${wxActivity.id}&status=true" class="pn-opt">启用</a>&nbsp;
                            </#if>
                            <#if canUpdate??>
                                | <a href="${ctx}/cszjs/wxactivity/editform.php?id=${wxActivity.id!}">修改</a> &nbsp;
                            </#if>
                                <#if canDelete??>
                                    | <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                          href="javascript:void(0);"
                                          data-href="${ctx}/cszjs/wxactivity/del.php?id=${wxActivity.id}" class="pn-opt">删除</a>&nbsp;
                                </#if>
                            </#if>
                        </td>
                        </#if>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"  style="width: 100%;height: 650px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</div>
<script data-main="${ctx}/js/abc/cszj/wxactivity.js" src="${ctx}/js/require.js"></script>
</body>
</html>