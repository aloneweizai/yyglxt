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
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/subscription/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="${ctx}/consumerManager/subscription/edit.php?dpType=1" class="layui-btn layui-btn-normal fr">添加消息订阅</a>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>消息类型</th>
                    <th>站内消息</th>
                    <th>微信消息</th>
                    <th>短信消息</th>
                    <th>修改时间</th>
                    <th>相关操作</th>
                </tr>
                </thead>
            <tbody class="pn-ltbody">
            <#if msgType?? && ( msgType?size gt 0 )>
                <#list msgType as msg>
                <#if msg.flag??&&msg.flag>
                    <thead class="pn-lthead">
                    <tr style="background-color: rgba(0,0,0,.075)">
                        <td colspan="7" style="text-align: left">
                        ${msg.fieldKey!}
                        </td>
                    </tr>
                    </thead>
                </#if>
                    <#if subscriptionRs?? && ( subscriptionRs?size gt 0 )>
                        <#list subscriptionRs as subscription>
                            <#if msg.fieldValue == subscription.type>
                                <tr>
                                    <td>
                                        <#if busiType?? && ( busiType?size gt 0 )>
                                 <#list busiType as busi>
                                            <#if busi.fieldValue == subscription.busiType>${busi.fieldKey!}</#if>
                                        </#list>
                             </#if>
                                    </td>
                                    <td>
                                        <#if subscription.hasWeb?? && subscription.hasWeb&&subscription.webForce?? && subscription.webForce>
                                            有/是
                                        <#elseif subscription.hasWeb?? && subscription.hasWeb&&subscription.webForce?? &&  !subscription.webForce>
                                            有/否
                                        <#elseif subscription.hasWeb?? && !subscription.hasWeb>
                                            无/否
                                        <#else>
                                            -/-
                                        </#if>
                                    </td>
                                    <td>
                                        <#if subscription.hasWechat?? && subscription.hasWechat&&subscription.wechatForce?? && subscription.wechatForce>
                                            有/是
                                        <#elseif subscription.hasWechat?? && subscription.hasWechat&&subscription.wechatForce?? &&  !subscription.wechatForce>
                                            有/否
                                        <#elseif subscription.hasWechat?? && !subscription.hasWechat>
                                            无/否
                                        <#else>
                                            -/-
                                        </#if>
                                    </td>
                                    <td>
                                        <#if subscription.hasMessage?? && subscription.hasMessage&&subscription.messageForce?? && subscription.messageForce>
                                            有/是
                                        <#elseif subscription.hasMessage?? && subscription.hasMessage&&subscription.messageForce?? &&  !subscription.messageForce>
                                            有/否
                                        <#elseif subscription.hasMessage?? && !subscription.hasMessage>
                                            无/否
                                        <#else>
                                            -/-
                                        </#if>
                                    </td>
                                    <td>${subscription.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <a href="${ctx}/consumerManager/subscription/edit.php?dpType=2&id=${subscription.id}">编辑</a>
                                        |
                                        <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!"
                                           data-failMsg="删除失败" href="javascript:void(0);"
                                           data-href="${ctx}/consumerManager/del.php?delType=7&id=${subscription.id}"
                                           class="pn-opt">删除</a>
                                    </td>
                                </tr>
                            </#if>
                        </#list>
                    </#if>
                </#list>
            </#if>
                </tbody>
            </table>
            <table class="yy_fanye">
                <tbody>
                <tr>
                    <td align="center">
                        共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                        每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size"
                                 type="text">条&nbsp;&nbsp;
                        <input class="btn btn-default btn-xs" value="首 页" id="consumer_first" type="button">
                        <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up" type="button">
                        <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                        <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                        当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                        <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}"
                               type="text"> 页
                        <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                        <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                        <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                        <input type="hidden" value="${ctx}/consumerManager/subscription/list.php" id="currLink">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/page.js" src="${ctx}/js/require.js"></script>
</body>
</html>