<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form id="consumerListForm"
          action="${ctx}/consumerManager/operate/message/look.php" method="get" class="layui-form">
        <table class="layui-table">
            <input type="hidden" name="id" value="${yyMsgSend.id!}">
            <caption style="text-align: center">任务描述${(yyMsgSend.taskname)!}</caption>
            <tr>
                <td width="120">推送渠道：</td>
                <td colspan="3">
                <#if yyMsgSend.web??&&yyMsgSend.web>
                    站内消息
                </#if>
                <#if yyMsgSend.wechat??&&yyMsgSend.wechat>
                    微信消息
                </#if>
                <#if yyMsgSend.message??&&yyMsgSend.message>
                    短信消息
                </#if>
                </td>
                <td width="120">目标人群：</td>
                <td colspan="3">
                <#if yyMsgSend.target??&&yyMsgSend.target == '1'>
                    全部用户
                <#elseif yyMsgSend.target??&&yyMsgSend.target == '2'>
                    部分用户
                <#elseif yyMsgSend.target??&&yyMsgSend.target == '3'>
                    特定用户
                </#if>
                </td>
            </tr>
            <tr>
                <td width="120">消息链接：</td>
                <td colspan="3">
                ${(yyMsgSend.url)!}
                </td>
                <td width="120">推送时间：</td>
                <td colspan="3">
                ${(yyMsgSend.startTime)!}~${(yyMsgSend.endTime)!}
                </td>
            </tr>
            <tr>
                <td width="120">消息内容：</td>
                <td colspan="7">
                ${(yyMsgSend.content)!}
                </td>
            </tr>
            <tr>
                <td width="120">接收情况：</td>
                <td colspan="7">
                    <div class="layui-inline">
                        <label style="font-weight: inherit;">用户昵称</label>

                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" value="${(BaseRq.nickName)!}" name="nickName">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label style="font-weight: inherit;padding: 2px 10px;">用户ID</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" value="${(BaseRq.userId)!}" name="userId"
                                   id="userId">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <div id="consumer_query" class="layui-btn">查询</div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="120"></td>
                <td colspan="7">
                    <table style="width: 100%">
                        <thead class="pn-lthead">
                        <tr>
                            <th width="30">序号</th>
                            <th>用户ID</th>
                            <th>用户昵称</th>
                            <th>接收时间</th>
                            <th>接收渠道</th>
                        </tr>
                        </thead>
                        <tbody class="pn-ltbody">
                        <#if msgSendDetailRs??>
                            <#list msgSendDetailRs as msgSendDetail>
                        <tr>
                            <td>${BaseRq.offset + msgSendDetail_index + 1}</td>
                            <td>${(msgSendDetail.userId)!}</td>
                            <td>${(msgSendDetail.nickName)!}</td>
                            <td>${(msgSendDetail.createTime?string("yyyy-MM-dd"))!}</td>
                            <td>
                                <#if msgSendDetail.type??&&msgSendDetail.type == 'web'>
                                    站内消息
                                <#elseif msgSendDetail.type??&&msgSendDetail.type == 'wechat'>
                                    微信消息
                                <#elseif msgSendDetail.type??&&msgSendDetail.type == 'message'>
                                    短信消息
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </#if>
                        </tbody>
                    </table>
                    <table class="yy_fanye" style="width: 100%">
                        <tbody>
                        <tr>
                            <td align="center">
                                共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                                每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                                <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                                <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                                <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                                <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                                当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                                <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                                <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                                <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                                <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
<script data-main="${ctx}/js/abc/consumer/yymsgsend.js" src="${ctx}/js/require.js"></script>
</html>
