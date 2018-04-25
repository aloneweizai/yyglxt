<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/operate/message/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="status" id="status">
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status == '1'>selected</#if>  value="1">进行中</option>
                            <option <#if BaseRq.status?? && BaseRq.status == '0'>selected</#if> value="0">已撤销</option>
                            <option <#if BaseRq.status?? && BaseRq.status == '2'>selected</#if> value="2">已完成</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.createTime)!}" name="createTime">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">任务描述</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="${(BaseRq.name)!}" name="name" id="name">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
                <a data-type='lookdialog' data-url="${ctx}/consumerManager/operate/message/editform.php"  class="layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>任务描述</th>
                    <th>推送渠道</th>
                    <th>目标人群</th>
                    <th>推送时间</th>
                    <th>创建时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if yyMsgSendRs??>
                    <#list yyMsgSendRs as yyMsgSend>
                    <tr>
                        <td>${BaseRq.offset + yyMsgSend_index + 1}</td>
                        <td><a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/consumerManager/operate/message/look.php?id=${(yyMsgSend.id)!}" class="pn-opt">${(yyMsgSend.taskname)!}</a></td>
                        <td>
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
                        <td>
                        <#if yyMsgSend.target??&&yyMsgSend.target == '1'>
                            全部用户
                        <#elseif yyMsgSend.target??&&yyMsgSend.target == '2'>
                            部分用户
                        <#elseif yyMsgSend.target??&&yyMsgSend.target == '3'>
                            特定用户
                        </#if>
                        </td>
                        <td>
                            <#if yyMsgSend.sendTime??&&yyMsgSend.sendTime == '1'>
                                立即推送
                            <#elseif yyMsgSend.sendTime??&&yyMsgSend.sendTime == '2'>
                                定时推送
                            </#if>
                        </td>
                        <td>${(yyMsgSend.createTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <#if yyMsgSend.status??&&yyMsgSend.status == '0'>
                                已撤销
                             <#elseif yyMsgSend.status??&&yyMsgSend.status == '1'>
                                 进行中
                            <#elseif yyMsgSend.status??&&yyMsgSend.status == '2'>
                                已完成
                            </#if>
                        </td>
                        <td>
                            <a data-type='lookdialog2' data-url="${ctx}/consumerManager/operate/message/editform.php?id=${yyMsgSend.id!}&lookType=0" class="pn-opt">查看</a> &nbsp;|
                            <#if yyMsgSend.status??&&yyMsgSend.status == '0'>
                                <a data-type='lookdialog' data-url="${ctx}/consumerManager/operate/message/editform.php?id=${yyMsgSend.id!}&lookType=1" class="pn-opt">修改</a> &nbsp;|
                                <a data-url="${ctx}/consumerManager/operate/message/${(yyMsgSend.id)!}" type="POST"
                                    data-type="detele"  abc-type="是否删除?" class="pn-opt">删除</a>
                            <#elseif yyMsgSend.status??&&yyMsgSend.status == '1'>
                                <a data-url="${ctx}/consumerManager/operate/message/nuse/${(yyMsgSend.id)!}" type="POST"
                                    data-type="nuse"  abc-type="是否撤销?" class="pn-opt">撤销</a>
                            <#elseif yyMsgSend.status??&&yyMsgSend.status == '2'>
                                <a data-url="${ctx}/consumerManager/operate/message/reuse/${(yyMsgSend.id)!}" type="POST"
                                    data-type="reuse"  abc-type="是否复用?" class="pn-opt">复用</a>
                            </#if>
                            </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <table class="yy_fanye">
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
        </div>
    </form>
</div>


<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document"style="width: 95%;top: 0px">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-normal" id="editbtn" data-tj="modal">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog" style="width: 1000px;" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="look_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dis="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/yymsgsend.js" src="${ctx}/js/require.js"></script>
</body>
</html>
