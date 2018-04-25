<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
	<script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
		input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
		.alertTb>tbody>tr>td{padding:10px 5px;text-align:center;}
		.alertTb>tbody>tr>td:HOVER{border: 1px solid #c0c0c0;}
		.alertTb>tbody>tr>td>img{cursor:pointer;}
	</style>
</head>
<body>
  <div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="consumer_edit" action="${ctx}/consumerManager/subscription/save.php" next-url="${ctx}/consumerManager/subscription/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if subscriptionRs??>
		<input type="hidden" id="subId" name="id" value="${subscriptionRs.id!}">
            <tr>
                <td width="150">消息类型</td>
                <td colspan="3" style="height: 45px;">
                    <select style="height:30px;margin-left:30px;" id="msgType" name="msgType">
                        <option value="">--请选择--</option>
                        <#if msgType?? && ( msgType?size gt 0 )>
                            <#list msgType as msg>
                                <option <#if subscriptionRs.type??&&subscriptionRs.type==msg.fieldValue>selected</#if> value="${msg.fieldValue}">${msg.fieldKey}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="150">业务类型</td>
                <td colspan="3" style="height: 45px;">
                    <select style="height:30px;margin-left:30px;" id="busiType" name="busiType">
                        <option value="">--请选择--</option>
                        <#if busiType?? && ( busiType?size gt 0 )>
                            <#list busiType as busi>
                                <option <#if subscriptionRs.busiType??&&subscriptionRs.busiType==busi.fieldValue>selected</#if> value="${busi.fieldValue}">${busi.fieldKey}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="150">站内消息：</td>
                <td colspan="3" style="height: 45px;">
                    <input id="hasWeb" name="hasWeb" type="checkbox" lay-filter="hasWeb"
                           <#if subscriptionRs.hasWeb??&&subscriptionRs.hasWeb>checked</#if>
                           lay-skin="primary" >
                    <span <#if !(subscriptionRs.hasWeb??&&subscriptionRs.hasWeb)>hidden</#if> id="webForce"  style="margin-left: 30px">&nbsp;&nbsp;&nbsp;强制订阅：
                        <input id="webForce1" name="webForce" lay-filter="webForce"
                               type="radio" value="true"
                               <#if subscriptionRs.webForce??&&subscriptionRs.webForce>checked</#if>
                               title="是">
                    <input id="webForce2" name="webForce" lay-filter="webForce"
                           <#if subscriptionRs.webForce??&&!subscriptionRs.webForce>checked</#if>
                           type="radio" value="false"
                           title="否">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="150">微信消息：</td>
                <td colspan="3" style="height: 45px;">
                    <input id="hasWechat" name="hasWechat" type="checkbox"lay-filter="hasWechat"
                           <#if subscriptionRs.hasWechat??&&subscriptionRs.hasWechat>checked</#if>
                           lay-skin="primary" >
                    <span <#if !(subscriptionRs.hasWechat??&&subscriptionRs.hasWechat)>hidden</#if> id="wechatForce"  style="margin-left: 30px">&nbsp;&nbsp;&nbsp;强制订阅：
                        <input id="wechatForce1" name="wechatForce" lay-filter="wechatForce"
                               <#if subscriptionRs.wechatForce??&&subscriptionRs.wechatForce>checked</#if>
                               type="radio" value="true"
                               title="是">
                    <input id="wechatForce2" name="wechatForce" lay-filter="wechatForce"
                           <#if subscriptionRs.wechatForce??&&!subscriptionRs.wechatForce>checked</#if>
                           type="radio" value="false"
                           title="否">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="150">短信消息：</td>
                <td colspan="3" style="height: 45px;">
                    <input id="hasMessage" name="hasMessage" type="checkbox"lay-filter="hasMessage"
                           <#if subscriptionRs.hasMessage??&&subscriptionRs.hasMessage>checked</#if>
                           lay-skin="primary" >
                    <span <#if !(subscriptionRs.hasMessage??&&subscriptionRs.hasMessage)>hidden</#if> id="messageForce"  style="margin-left: 30px">&nbsp;&nbsp;&nbsp;强制订阅：
                        <input id="messageForce1" name="messageForce" lay-filter="messageForce"
                               <#if subscriptionRs.messageForce??&&subscriptionRs.messageForce>checked</#if>
                               type="radio" value="true"
                               title="是">
                    <input id="messageForce2" name="messageForce" lay-filter="messageForce"
                           type="radio" value="false"
                           <#if subscriptionRs.messageForce??&&!subscriptionRs.messageForce>checked</#if>
                           title="否">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="150">备注</td>
                <td colspan="3"><textarea type="text" maxlength="200" name="remark" id="remark" class="layui-input" value="${subscriptionRs.remark!}"style="height: 50px">${subscriptionRs.remark!}</textarea></td>
            </tr>
		<#else>
            <tr>
                <td width="150">消息类型</td>
                <td colspan="3" style="height: 45px;">
                    <select style="height:30px;margin-left:30px;" id="msgType" name="msgType">
                        <option value="">--请选择--</option>
                        <#if msgType?? && ( msgType?size gt 0 )>
                            <#list msgType as msg>
                                <option value="${msg.fieldValue}">${msg.fieldKey}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="150">业务类型</td>
                <td colspan="3" style="height: 45px;">
                    <select style="height:30px;margin-left:30px;" id="busiType" name="busiType">
                        <option value="">--请选择--</option>
                        <#if busiType?? && ( busiType?size gt 0 )>
                            <#list busiType as busi>
                                <option value="${busi.fieldValue}">${busi.fieldKey}</option>
                            </#list>
                        </#if>
                    </select>
            </td>
            </tr>
            <tr>
                <td width="150">站内消息：</td>
                <td colspan="3" style="height: 45px;">
                    <input id="hasWeb" name="hasWeb" type="checkbox" lay-filter="hasWeb" checked
                           lay-skin="primary" >
                    <span id="webForce"  style="margin-left: 30px">&nbsp;&nbsp;&nbsp;强制订阅：
                        <input id="webForce1" name="webForce" lay-filter="webForce"
                                        type="radio" value="true" checked
                                        title="是">
                    <input id="webForce2" name="webForce" lay-filter="webForce"
                           type="radio" value="false"
                           title="否">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="150">微信消息：</td>
                <td colspan="3" style="height: 45px;">
                    <input id="hasWechat" name="hasWechat" type="checkbox"lay-filter="hasWechat"
                           lay-skin="primary" >
                    <span hidden id="wechatForce" style="margin-left: 30px">&nbsp;&nbsp;&nbsp;强制订阅：
                        <input id="wechatForce1" name="wechatForce" lay-filter="wechatForce" checked
                               type="radio" value="true"
                               title="是">
                    <input id="wechatForce2" name="wechatForce" lay-filter="wechatForce"
                           type="radio" value="false"
                           title="否">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="150">短信消息：</td>
                <td colspan="3"  style="height: 45px;">
                    <input id="hasMessage" name="hasMessage" type="checkbox"lay-filter="hasMessage"
                           lay-skin="primary" >
                    <span hidden id="messageForce"  style="margin-left: 30px">&nbsp;&nbsp;&nbsp;强制订阅：
                        <input id="messageForce1" name="messageForce" lay-filter="messageForce" checked
                               type="radio" value="true"
                               title="是">
                    <input id="messageForce2" name="messageForce" lay-filter="messageForce"
                           type="radio" value="false"
                           title="否">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="150">备注</td>
                <td colspan="3"><textarea type="text" name="remark" id="remark" class="layui-input" style="height: 50px"></textarea></td>
            </tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3"><input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
            <a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a>
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/subscription.js" src="${ctx}/js/require.js"></script>
</html>