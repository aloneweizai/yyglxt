<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<!-- saved from url=(0039)https://wj.qq.com/edit.html?sid=1422807 -->
<html lang="zh-cn"
      class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>用户消息查询</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item current" href="${ctx}/system/usermsg/usermsg_sx.php">私信消息</a>
                <a class="nav_item" href="${ctx}/system/usermsg/usermsg_yw.php">业务消息</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">

    <div class="editor_container" style="display: block;text-align: center">
        <!-- 主体 -->
        <div class="editor_main" >

            <div class="survey_wrap" style="max-width: none;width: 99%">

                <div class="survey_main" style="padding-top:0;">

                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block;">
                            <form action="${ctx}/system/usermsg/usermsg_sx.php" method="post" id="consumerListForm" class="layui-form">
                                <div class="layui-form-top">
                                    <div class="layui-form-item">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">发送人</label>
                                            <div class="layui-input-inline">
                                                <input name="fromNickname" value="${(BaseRq.fromNickname)!}" class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
                                            <label class="layui-form-label">接收人</label>
                                            <div class="layui-input-inline">
                                                <input  name="toNickname" value="${(BaseRq.toNickname)!}" class="layui-input">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
                                            <label class="layui-form-label">起止时间</label>
                                            <div class="layui-input-inline" style="width: 100px">
                                                <input type="text" class="layui-input" id="test30" value="${(BaseRq.startDate)!}" name="startDate" style="width: 100px">
                                            </div>
                                            <div class="layui-input-inline" style="width: 100px">
                                                <input type="text" class="layui-input" id="test31" value="${(BaseRq.endDate)!}" name="endDate" style="width: 100px">
                                            </div>
                                        </div>
                                        <div class="layui-inline">
                                            <div class="layui-input-inline" style="width:180px;">
                                                <div id="querysxbtn" class="layui-btn">查询</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="nycon_list_b">
                                        <table class="layui-table" lay-size="sm">
                                            <tr>
                                                <th>序号</th>
                                                <th>发送人</th>
                                                <th>接收人</th>
                                                <th >私信类型</th>
                                                <th>私信内容</th>
                                                <th>私信状态</th>
                                                <th>创建时间</th>
                                                <th>修改时间</th>
                                            </tr>

                                        <#if userMessageRs?? && ( userMessageRs?size gt 0 )>
                                            <#list userMessageRs as userMessage>
                                                <tr>
                                                    <td>${BaseRq.offset + userMessage_index + 1}</td>
                                                    <td>
                                                    <#--   <#if userMessage.fromUserPic??>
                                                               <img height="32" width="32" src="${userMessage.fromUserPic!}">&nbsp;&nbsp;${userMessage.fromuser!}
                                                           <#else>-->
                                        ${userMessage.fromNickname!}
                                    <#--</#if>-->
                                                    </td>
                                                    <td>
                                                    <#-- <#if userMessage.toUserPic??>
                                                             <img height="32" width="32" src="${userMessage.toUserPic!}">&nbsp;&nbsp;${userMessage.touser!}
                                                         <#else>-->
                                        ${userMessage.toNickname!}
                                   <#-- </#if>-->
                                                    </td>
                                                    <td>
                                                        <#if userMessage.type??&&userMessage.type == "1">
                                                            私信
                                                        </#if>
                                                    </td>
                                                    <td title="${userMessage.content!}">${userMessage.contentstr!}</td>
                                                    <td>
                                                        <#if userMessage.status??&&userMessage.status == "0">
                                                            删除
                                                        <#elseif userMessage.status??&&userMessage.status == "1" >
                                                            未读
                                                        <#elseif userMessage.status??&&userMessage.status == "2" >
                                                            已读
                                                        </#if>
                                                    </td>
                                                    <td>${userMessage.createTime!}</td>
                                                    <td>${userMessage.lastUpdate!}</td>
                                                </tr>
                                            </#list>
                                        </#if>
                                        </table>
                                        <table class="yy_fanye" style="width: 100%">
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
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/system/usermsg.js" src="${ctx}/js/require.js"></script>
</body>
</html>