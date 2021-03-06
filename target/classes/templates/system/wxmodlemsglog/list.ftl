<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/system/wxmodlemsglog/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.username)!}" name="username" >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">昵称</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.nickname)!}" name="nickname" >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发送时间</label>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test30" value="${(BaseRq.beginTime)!}" name="beginTime" style="width: 100px">
                    </div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}" name="endTime" style="width: 100px">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>



        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>模板消息</th>
                    <th>发送结果</th>
                    <th>发送时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if wxModleMsglogRs??>
                    <#list wxModleMsglogRs as wxModleMsglog>
                    <tr>
                        <td>${BaseRq.offset + wxModleMsglog_index + 1}</td>
                        <td>${(wxModleMsglog.username)!}</td>
                        <td>${(wxModleMsglog.nickname)!}</td>
                        <td title='${wxModleMsglog.templateval!}'>${(wxModleMsglog.templatevalstr)!}</td>
                        <#--<td title="${(wxModleMsglog.templateval)!}">${(wxModleMsglog.templatevalsstr)!}</td>-->
                        <td>${(wxModleMsglog.returnmsg)!}</td>
                        <td>${(wxModleMsglog.createdate?string("yyyy-MM-dd"))!}</td>
                       <#-- <td>${(wxModleMsglog.synchronizeTime?string("yyyy-MM-dd"))!}</td>-->
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
                        <input type="hidden" value="${ctx}/orderchange/applications.php" id="currLink">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
