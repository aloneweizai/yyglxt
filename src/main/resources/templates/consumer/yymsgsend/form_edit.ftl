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
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/tagEditor/jquery.tagsinput.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
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
    <form name="consumer_edit" action="${ctx}/cszjs/yyMsgSend/save.php" next-url="${ctx}/cszjs/yyMsgSend/list.php"
          method="post" class="layui-form">
        <table class="layui-table">
            <!--查看页面-->
        <#if yyMsgSendRs??&&lookType??&&lookType == "0">
            <input type="hidden" id="taskId" name="id" value="${yyMsgSendRs.id!}">
            <tr>
                <td width="80">任务描述：</td>
                <td colspan="3">${yyMsgSendRs.taskname!}</td>
            </tr>
            <tr>
                <td width="80">推送渠道：</td>
                <td colspan="3">
                    <#if yyMsgSendRs.web??&&yyMsgSendRs.web>
                        站内消息
                    </#if>
                    <#if yyMsgSendRs.wechat??&&yyMsgSendRs.wechat>
                        微信消息
                    </#if>
                    <#if yyMsgSendRs.message??&&yyMsgSendRs.message>
                        短信消息
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="80">消息内容：</td>
                <td colspan="3">
                ${yyMsgSendRs.content!}
                </td>
            </tr>
            <tr>
                <td width="80">消息链接：</td>
                <td colspan="3">
                ${(yyMsgSendRs.url)!}
                </td>
            </tr>
            <tr>
                <td width="80">目标人群：</td>
                <td colspan="3">
                    <#if yyMsgSendRs.target??&&yyMsgSendRs.target == '1'>
                        全部用户
                    <#elseif yyMsgSendRs.target??&&yyMsgSendRs.target == '2'>
                        部分用户
                    <#elseif yyMsgSendRs.target??&&yyMsgSendRs.target == '3'>
                        特定用户
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr <#if yyMsgSendRs.target??&&yyMsgSendRs.target != "2">hidden</#if> id="bfyh">
                            <td style="width: 700px">
                                <table style="width: 100%">
                                    <caption>部分用户筛选条件</caption>
                                    <tr>
                                        <td width="60" align="right">区域：</td>
                                        <td>
                                            <div class="layui-inline" style="width: 90px">
                                                <#if yyMsgSendRs.areaOper??&&yyMsgSendRs.areaOper == "equals">
                                                    等于
                                                <#elseif yyMsgSendRs.areaOper??&&yyMsgSendRs.areaOper == "ne">
                                                    不等于
                                                </#if>
                                            </div>
                                            <div class="layui-inline"style="width: 500px">
                                                 <div id="areaNames"></div>
                                                <input id="areaIds" name="areaIds" style=" width:330px;" type="hidden"
                                                       value="${yyMsgSendRs.areaIds!}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">标签：</td>
                                        <td>
                                            <span id="tagnum" data-num="0" hidden></span>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <#if yyMsgSendRs.tagOper??&&yyMsgSendRs.tagOper == "equals">
                                                    等于
                                                <#elseif yyMsgSendRs.tagOper??&&yyMsgSendRs.tagOper == "ne">
                                                    不等于
                                                </#if>
                                            </div>
                                          <div class="layui-inline">
                                            ${(yyMsgSendRs.tagName)!}
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">注册时间：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <#if yyMsgSendRs.regTimeOper??&&yyMsgSendRs.regTimeOper == "lte">
                                                    小于等于
                                                <#elseif yyMsgSendRs.regTimeOper??&&yyMsgSendRs.regTimeOper == "gte">
                                                    大于等于
                                                </#if>
                                            </div>
                                            <div class="layui-inline">
                                            ${yyMsgSendRs.regEndTime!}
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="tdyh" <#if yyMsgSendRs.target??&&yyMsgSendRs.target != "3">hidden</#if>>
                            <td style="width: 700px">
                                <table style="width: 100%">
                                    <caption>特定用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">用户ID：</td>
                                        <td>
                                            <div class="layui-inline">
                                            ${yyMsgSendRs.userName!}
                                            </div>

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="80">频率：</td>
                <td colspan="3">
                    <#if yyMsgSendRs.rate??&&yyMsgSendRs.rate == 'O'>
                        一次性
                    <#elseif yyMsgSendRs.rate??&&yyMsgSendRs.rate == 'D'>
                        每天一次
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="80">推送时间：</td>
                <td colspan="3">
                    <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == '1'>
                        立即推送 &nbsp;&nbsp;&nbsp;${yyMsgSendRs.endTime!}之前用户登录可接收到消息
                    <#elseif yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == '2'>
                        定时推送 &nbsp;&nbsp;&nbsp;${yyMsgSendRs.startTime!}至${yyMsgSendRs.endTime!}内用户登录可接收到消息
                    </#if>
                </td>
            </tr>
        <#elseif yyMsgSendRs??&&lookType??&&lookType == "1">
            <input type="hidden" id="taskId" name="id" value="${yyMsgSendRs.id!}">
            <tr>
                <td width="80">任务描述：</td>
                <td colspan="3">
                    <input type="text" value="${yyMsgSendRs.taskname!}" name="taskname" id="taskname"
                           data-rule="required;length[2~16]" style=" width:330px;"
                           class="layui-input"><label
                        style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="80">推送渠道：</td>
                <td colspan="3">
                    <input id="web" <#if yyMsgSendRs.web??&&yyMsgSendRs.web>checked</#if> name="web" type="checkbox"
                           lay-skin="primary"  title="站内消息">
                    <input id="wechat" <#if yyMsgSendRs.wechat??&&yyMsgSendRs.wechat>checked</#if> name="wechat"
                           type="checkbox"  lay-skin="primary"
                           title="微信消息">
                    <input id="message" <#if yyMsgSendRs.message??&&yyMsgSendRs.message>checked</#if> name="message"
                           type="checkbox"  lay-skin="primary"
                           title="短信消息">
                </td>
            </tr>
            <tr>
                <td width="80">消息内容：</td>
                <td colspan="3">
                    <textarea name="content" value="${yyMsgSendRs.content!}" id="content"
                              placeholder="站内消息和微信消息,短信消息是最多200字。" style="width: 600px"
                              data-rule="required;length[~200]" rows="3"
                              class="layui-textarea">${yyMsgSendRs.content!}</textarea><label
                        style='color:red;'>*</label><span id="num">${yyMsgSendRs.num!}/200</span>
                </td>
            </tr>
            <tr>
                <td width="80">消息链接：</td>
                <td colspan="3">
                    <input type="text" id="urltitle" value="${yyMsgSendRs.urltitle!}" name="urltitle" placeholder="链接标题"
                           data-rule="length[2~50]" style=" width:100px;" class="layui-input">
                    <input type="text" id="urlhref" value="${yyMsgSendRs.urlhref!}" data-rule="url;" name="url"
                           placeholder="链接地址，必须是http://开头" data-rule="length[2~50]" style=" width:330px;"
                           class="layui-input">
                </td>
            </tr>
            <tr>
                <td width="80">目标人群：</td>
                <td colspan="3">
                    <input id="target1" <#if yyMsgSendRs.target??&&yyMsgSendRs.target == "1">checked</#if> name="target"
                           type="radio" lay-filter="target" value="1" title="全部用户">
                    <input id="target2" lay-filter="target"
                           <#if yyMsgSendRs.target??&&yyMsgSendRs.target == "2">checked</#if> name="target"
                           type="radio" value="2" title="部分用户">
                    <input id="target3" lay-filter="target"
                           <#if yyMsgSendRs.target??&&yyMsgSendRs.target == "3">checked</#if> name="target"
                           type="radio" value="3" title="特定用户">
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr id="bfyh" <#if yyMsgSendRs.target??&&yyMsgSendRs.target != "2">hidden</#if> >
                            <td style="width: 700px">
                                <table style="width: 100%">
                                    <caption>部分用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">区域：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="areaOper" id="areaOper" lay-filter="areaOper">
                                                    <option value=""></option>
                                                    <option
                                                        <#if yyMsgSendRs.areaOper??&&yyMsgSendRs.areaOper == "equals">selected</#if>
                                                        value="equals">等于
                                                    </option>
                                                    <option
                                                        <#if yyMsgSendRs.areaOper??&&yyMsgSendRs.areaOper == "ne">selected</#if>
                                                        value="ne">不等于
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                <textarea id="areaName" name="areaName" type="text" style=" width:330px;height: 45px"
                                          readonly="readonly"></textarea>
                                                <input id="areaIds" name="areaIds" style=" width:330px;" type="hidden"
                                                       value="${yyMsgSendRs.areaIds!}">
                                                <span id="areanum" style="font-size: smaller">0/20</span>
                                            </div>
                                            <#if lookType??&&lookType=="1">
                                                <div id="areaslect" class="layui-btn">选择</div>
                                            </#if>
                                        <#--<input type="text" value="${yyMsgSendRs.areaIds!}" id="areaIds" name="areaIds" data-rule="length[2~50]" style=" width:330px;"  class="layui-input">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">标签：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="tagOper" id="tagOper" lay-filter="tagOper">
                                                    <option value=""></option>
                                                    <option
                                                        <#if yyMsgSendRs.tagOper??&&yyMsgSendRs.tagOper == "equals">selected</#if>
                                                        value="equals">等于
                                                    </option>
                                                    <option
                                                        <#if yyMsgSendRs.tagOper??&&yyMsgSendRs.tagOper == "ne">selected</#if>
                                                        value="ne">不等于
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                                <input type="text" name="tagName" id="tagnames"
                                                       value="${(yyMsgSendRs.tagName)!}"
                                                       placeholder="请搜索选择标签" class="layui-input">
                                            </div>
                                            <#if lookType??&&lookType=="1">
                                                <span id="tagnum" data-num="${yyMsgSendRs.tagnum!}" style="font-size: smaller"><#if yyMsgSendRs.tagnum??>${yyMsgSendRs.tagnum!}/10<#else>0/10</#if></span>
                                                <div id="tagslect" class="layui-btn">选择</div>
                                            </#if>
                                        <#-- <div id="tagdelall" class="layui-btn layui-btn-danger">清空</div>-->
                                        <#--<input type="text" id="tagIds" value="${yyMsgSendRs.tagIds!}" name="tagIds" data-rule="length[2~50]" style=" width:330px;"  class="layui-input">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">注册时间：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="regTimeOper" id="regTimeOper" lay-filter="regTimeOper"
                                                        value="${yyMsgSendRs.regTimeOper!}">
                                                    <option value=""></option>
                                                    <option
                                                        <#if yyMsgSendRs.regTimeOper??&&yyMsgSendRs.regTimeOper == "lte">selected</#if>
                                                        value="lte">小于等于
                                                    </option>
                                                    <option
                                                        <#if yyMsgSendRs.regTimeOper??&&yyMsgSendRs.regTimeOper == "gte">selected</#if>
                                                        value="gte">大于等于
                                                    </option>
                                                </select>
                                            </div>
                                            <input type="text" class="layui-input" id="test31"
                                                   <#if yyMsgSendRs.regTimeOper??&&yyMsgSendRs.regTimeOper == "lte">value="${yyMsgSendRs.regEndTime!}"
                                                   <#else>value="${yyMsgSendRs.regStartTime!}" </#if>
                                                   name="regStartTime" style=" width:350px;">
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="tdyh" <#if yyMsgSendRs.target??&&yyMsgSendRs.target != "3">hidden</#if>>
                            <td style="width: 700px">
                                <table style="width: 100%">
                                    <caption>特定用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">用户ID：</td>
                                        <td>
                                            <div class="layui-inline">
                                 <#--<textarea id="userName" name="userName"
                                           data-url="${ctx}/consumerManager/operate/message/consumerlook.php?userIds=${yyMsgSendRs.userName!}"
                                           style=" width:420px;height: 45px" class="layui-input" readonly="readonly"
                                           value="${yyMsgSendRs.userName!}">${yyMsgSendRs.userName!}</textarea>-->
                                                <input type="text" id="userName" name="userName"
                                                       data-url="${ctx}/consumerManager/operate/message/consumerlook.php?userIds=${yyMsgSendRs.userName!}"
                                                       style=" width:450px;height: 50px" class="layui-input" value="${yyMsgSendRs.userName!}"
                                                       readonly="readonly"></input>
                                                <input id="userIds" name="userIds" style=" width:330px;" type="hidden"
                                                       value="${yyMsgSendRs.userIds!}">
                                                <input id="userNames" name="userNames" style=" width:330px;" type="hidden"
                                                       value="${yyMsgSendRs.userName!}">
                                                <span id="usernum" style="font-size: smaller"><#if yyMsgSendRs.usernum??>${yyMsgSendRs.usernum!}/1000<#else>0/1000</#if></span>
                                            </div>
                                            <#if lookType??&&lookType=="1">
                                                <div id="userslect" class="layui-btn" value="${yyMsgSendRs.userIds!}">选择
                                                </div>
                                            </#if>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="80">频率：</td>
                <td colspan="3">
                    <input id="rate1" name="rate" type="radio"
                           <#if yyMsgSendRs.rate??&&yyMsgSendRs.rate == "O">checked</#if> value="O" title="一次性">
                    <input id="rate2" name="rate" type="radio"
                           <#if yyMsgSendRs.rate??&&yyMsgSendRs.rate == "D">checked</#if> value="D" title="每天一次">
                </td>
            </tr>
            <tr>
                <td width="80">推送时间：</td>
                <td colspan="3">
                    <input id="sendTime1" name="sendTime" lay-filter="sendTime"
                           <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == "1">checked</#if> type="radio" value="1"
                           title="立即推送">
                    <input id="sendTime2" name="sendTime" lay-filter="sendTime"
                           <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == "2">checked</#if> type="radio" value="2"
                           title="定时推送">
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr>
                            <td id="ljts" <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime != "1">hidden</#if>>
                                <input type="text" class="layui-input" id="test4"
                                       <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == "1">value="${yyMsgSendRs.endTime!}" <#else>
                                       disabled="disabled"</#if>  name="endTime" style="width: 160px">之前用户登录可接收到消息
                            </td>
                        </tr>
                        <tr>
                            <td id="dsts" <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime != "2">hidden</#if>>
                                <input type="text" class="layui-input" id="test5"
                                       <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == "2">value="${yyMsgSendRs.startTime!}" <#else>
                                       disabled="disabled"</#if> name="startTime" style="width: 160px">至
                                <input type="text" class="layui-input" id="test6"
                                       <#if yyMsgSendRs.sendTime??&&yyMsgSendRs.sendTime == "2">value="${yyMsgSendRs.endTime!}" <#else>
                                       disabled="disabled"</#if> name="endTime" style="width: 160px">内用户登录可接收到消息
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        <#else>
            <tr>
                <td width="80">任务描述：</td>
                <td colspan="3">
                    <input type="text" name="taskname" id="taskname" data-rule="required;length[2~16]"
                           style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="80">推送渠道：</td>
                <td colspan="3">
                    <input id="web" name="web" type="checkbox" lay-skin="primary" title="站内消息">
                    <input id="wechat" name="wechat" type="checkbox" lay-skin="primary" title="微信消息">
                    <input id="message" name="message" type="checkbox" lay-skin="primary" title="短信消息">
                </td>
            </tr>
            <tr>
                <td width="80">消息内容：</td>
                <td colspan="3">
                    <textarea name="content" id="content" placeholder="站内消息和微信消息,短信消息是最多200字。"
                              style="width: 600px" data-rule="required;length[~200]" rows="3"
                              class="layui-textarea"></textarea><label style='color:red;'>*</label>
                    <span id="num">0/200</span>
                </td>
            </tr>
            <tr>
                <td width="80">消息链接：</td>
                <td colspan="3">
                    <input type="text" id="urltitle" name="urltitle" placeholder="链接标题" data-rule="length[2~50]"
                           style=" width:100px;" class="layui-input">
                    <input type="text" id="urlhref" name="urlhref" <#--value="http://" -->data-rule="url;"
                           placeholder="链接地址，必须是http://开头" data-rule="length[2~50]" style=" width:330px;"
                           class="layui-input">
                </td>
            </tr>
            <tr>
                <td width="80">目标人群：</td>
                <td colspan="3">
                    <input id="target1" name="target" type="radio" lay-filter="target" value="1" title="全部用户" checked>
                    <input id="target2" name="target" type="radio" lay-filter="target" value="2" title="部分用户">
                    <input id="target3" name="target" type="radio" lay-filter="target" value="3" title="特定用户">
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr id="bfyh" hidden>
                            <td style="width: 700px">
                                <table style="width: 100%">
                                    <caption>部分用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">区域：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="areaOper" id="areaOper" lay-filter="areaOper">
                                                    <option value=""></option>
                                                    <option value="equals">等于</option>
                                                    <option value="ne">不等于</option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                            <textarea id="areaName" name="areaName" type="text"
                                                      style=" width:340px;height: 45px"
                                                      readonly="readonly"></textarea>
                                                <input id="areaIds" name="areaIds" style=" width:330px;" type="hidden">
                                                <span id="areanum" style="font-size: smaller">0/20</span>
                                            </div>
                                            <div id="areaslect" class="layui-btn">选择</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">标签：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="tagOper" id="tagOper" lay-filter="tagOper">
                                                    <option value=""></option>
                                                    <option value="equals">等于</option>
                                                    <option value="ne">不等于</option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                                <input type="text" name="tagName" id="tagnames"
                                                       value="${(BaseRq.tagName)!}"
                                                       placeholder="请搜索选择标签" class="layui-input">
                                            </div>
                                            <span id="tagnum" data-num="0" style="font-size: smaller">0/10</span>
                                            <div id="tagslect" class="layui-btn">选择</div>
                                        <#-- <div id="tagdelall" class="layui-btn layui-btn-danger">清空</div>-->
                                        <#-- <input type="text" id="tagIds" name="tagIds" data-rule="length[2~50]" style=" width:330px;"  class="layui-input">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">注册时间：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="regTimeOper" id="regTimeOper" lay-filter="regTimeOper">
                                                    <option value=""></option>
                                                    <option value="lte">小于等于</option>
                                                    <option value="gte">大于等于</option>
                                                </select>
                                            </div>
                                            <input type="text" class="layui-input" id="test31" name="regStartTime"
                                                   style=" width:350px;">
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="tdyh" hidden>
                            <td style="width: 700px">
                                <table style="width: 100%">
                                    <caption>特定用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">用户ID：</td>
                                        <td>
                                            <div class="layui-inline">
                                           <#-- <textarea id="userName" name="userName"
                                                      data-url="${ctx}/consumerManager/operate/message/consumerlook.php"
                                                      style=" width:420px;height: 50px" class="layui-input"
                                                      readonly="readonly"></textarea>-->
                                             <input type="text" id="userName" name="userName"
                                                       data-url="${ctx}/consumerManager/operate/message/consumerlook.php"
                                                       style=" width:450px;height: 50px" class="layui-input"
                                                       readonly="readonly"></input>
                                                <input id="userIds" name="userIds" style=" width:330px;" type="hidden">
                                                <input id="userNames" name="userNames" style=" width:330px;" type="hidden">
                                                <span id="usernum" style="font-size: smaller">0/1000</span>
                                            </div>
                                            <div id="userslect" class="layui-btn">选择</div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="80">频率：</td>
                <td colspan="3">
                    <input id="rate1" name="rate" type="radio" value="O" title="一次性" checked>
                    <input id="rate2" name="rate" type="radio" value="D" title="每天一次">
                </td>
            </tr>
            <tr>
                <td width="80">推送时间：</td>
                <td colspan="3">
                    <input id="sendTime1" name="sendTime" lay-filter="sendTime" type="radio" value="1" title="立即推送"
                           checked>
                    <input id="sendTime2" name="sendTime" lay-filter="sendTime" type="radio" value="2" title="定时推送">
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr>
                            <td id="ljts"><input type="text" class="layui-input" id="test4" name="endTime"
                                                 style="width: 160px">之前用户登录可接收到消息
                            </td>
                        </tr>
                        <tr>
                            <td id="dsts" hidden><input type="text" class="layui-input" id="test5" name="startTime"
                                                        style="width: 160px">至
                                <input type="text" class="layui-input" id="test6" name="endTime" style="width: 160px">内用户登录可接收到消息
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </#if>
        <#--  <tr>
              <td></td>
              <td colspan="3"><button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
                  <a href="javascript:location.href=document.referrer" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
          </tr>-->
        </table>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <label><h3>标签</h3></label>
                <select style="height:30px;margin-left:30px;" id="seletablib">
                    <option value="">所有标签类型</option>
                <#if taglib?? && ( taglib?size gt 0 )>
                    <#list taglib as lib>
                        <option value="${lib.fieldValue}">${lib.fieldKey}</option>
                    </#list>
                </#if>
                </select>
            </div>
            <div class="modal-body">
            <#if taglib?? && ( taglib?size gt 0 )>
                <#list taglib as lib>
                    <div class="tag-list__itemWraper itemWraper1" id="tag_${lib.fieldValue}">
                        <h3 class="h5 tag-list__itemheader">${lib.fieldKey}</h3>
                        <ul class="tag-list__itembody taglist--inline multi">
                            <#if tags?? && ( tags?size gt 0 )>
                                <#list tags as tag>
                                    <#if lib.fieldValue == tag.category  && tag.status>
                                        <li><a id="taglib" class="btn tag1" data-type="unselect"
                                               data-id="${tag.tagName!}">${tag.tagName!}</a></li>
                                    </#if>
                                </#list>
                            </#if>
                        </ul>
                    </div>

                    <#if (lib_index+1)%3==0>
                        <div style="clear: both;height:0px;"></div>
                    </#if>
                </#list>
            </#if>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid row js_pop_menu" hidden>
    <div class="main_modal_tc col-sm-4">
        <div class="main_modal_t">请选择区域
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
        <#-- <div class="character_r_b">全国-->
            <ul id="treeDemo" class="ztree" style="width: 250;height: 300"></ul>
        <#--  </div>-->
        </div>
        <div>
            <input value="确认" class="js_save_area_btn btn btn-info">
        </div>
    </div>
</div>

<#if menus?? && (menus?size > 0) >
<div hidden>
    <ul>
        <#list menus as menu>
            <li class="menu_li" data-menu-id="${menu.regionId!}" data-menu-pid="${menu.pId!}"
                data-menu-name="${menu.regionName!}"></li>
        </#list>
    </ul>
</div>
</#if>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="width:90%;">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame" style="width: 100%;height: 500px;border:0" border="0"
                        frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-diss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/consumer/yymsgsend.js" src="${ctx}/js/require.js"></script>
</html>
