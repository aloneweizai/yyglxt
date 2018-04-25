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
    <form action="${ctx}/mobile/msg/query" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <input type="hidden" name="channel" id="channel" value="${BaseRq.channel}" class="layui-input">
            <input type="hidden" name="phone" id="phone" value="${BaseRq.channel}" class="layui-input">
            <input type="hidden" name="sendDate" id="sendDate" value="${BaseRq.channel}" class="layui-input">
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="5%"></th>
                    <th width="10%">发送时间</th>
                    <th width="10%">接收时间</th>
                    <th width="10%">发送渠道</th>
                    <th width="10%">接收号码</th>
                    <th width="10%">发送状态</th>
                    <th width="30%">短信内容</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
<#if messageQueryLogRs??&&messageQueryLogRs.dataList?? && ( messageQueryLogRs.dataList?size gt 0 )>
                    <#list messageQueryLogRs.dataList as messageQueryLog>
                    <tr>
                        <td width="30" class="td_i">${BaseRq.offset + messageQueryLog_index + 1}</td>
                        <td width="200">${(messageQueryLog.sendDate)!}</td>
                        <td width="200">${(messageQueryLog.receiveDate)!}</td>
                        <td width="100">
                            <#if messageQueryLog.channel?? && messageQueryLog.channel=="yp">又拍
                            <#elseif messageQueryLog.channel?? && messageQueryLog.channel=="ali">阿里
                            <#elseif messageQueryLog.channel?? && messageQueryLog.channel=="wy">网易
                            </#if>
                        </td>
                        <td width="150">${(messageQueryLog.phoneNum)!}</td>
                        <td width="130">
                            <#if messageQueryLog.sendStatus?? && messageQueryLog.sendStatus=="1">正在发送
                            <#elseif messageQueryLog.sendStatus?? && messageQueryLog.sendStatus=="2">发送失败
                            <#elseif messageQueryLog.sendStatus?? && messageQueryLog.sendStatus=="3">发送成功
                            </#if>
                        </td>
                        <td>${(messageQueryLog.content)!}</td>
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="7" style="font-size:15px; color: #999;text-align: center">--${(messageQueryLogRs.message)!}--</td>
                </tr>
                </#if>
                </tbody>
            </table>
            <table class="yy_fanye">
                <tbody>
                <tr>
                    <td align="center">
                        共&nbsp;${(BaseRq.totalItems?c)!}&nbsp;条&nbsp;&nbsp;
                        每页<input maxlength="2" style="width:40px" name="size" value="${(BaseRq.size?c)!}"
                                 id="consumer_size" type="text">条&nbsp;&nbsp;
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
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/taxpayerBind.js" src="${ctx}/js/require.js"></script>
</body>
</html>