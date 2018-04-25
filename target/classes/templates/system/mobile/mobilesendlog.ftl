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
    <form action="${ctx}/mobile/msg/sendlog?doType=1" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">手机号码</label>

                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.phone)!}" name="phone" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发送状态</label>

                    <div class="layui-input-inline">
                        <select name="status" class="cxinput" id="status">
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status=="0">selected</#if> value="0">待发送</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="1">selected</#if> value="1">发送成功</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="4">selected</#if> value="4">发送失败</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发送渠道</label>

                    <div class="layui-input-inline">
                        <select name="channel" class="cxinput" id="channel">
                            <option value=""></option>
                            <option <#if BaseRq.channel?? && BaseRq.channel=="yp">selected</#if> value="yp">又拍</option>
                            <option <#if BaseRq.channel?? && BaseRq.channel=="ali">selected</#if> value="ali">阿里
                            </option>
                            <option <#if BaseRq.channel?? && BaseRq.channel=="wy">selected</#if> value="wy">网易</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">短信内容</label>

                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.sendinfo)!}" name="sendinfo" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发送时间起</label>
                    <div class="layui-input-inline"  id="startDate">
                        <input type="text" class="layui-input" id="test30" value="${(BaseRq.start)!}" name="start" >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发送时间止</label>
                    <div class="layui-input-inline" id="endDate">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.end)!}" name="end">
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
                    <th width="5%"></th>
                    <th width="10%">发送时间</th>
                    <th width="10%">发送渠道</th>
                    <th width="10%">短信类型</th>
                    <th width="10%">接收号码</th>
                    <th width="10%">发送状态</th>
                    <th width="10%">原因</th>
                    <th width="30%">短信内容</th>
                    <th nowrap="nowrap">操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if apiLogRs?? && ( apiLogRs?size gt 0 )>
                    <#list apiLogRs as apiLog>
                    <tr>
                        <td width="30" class="td_i">${BaseRq.offset + apiLog_index + 1}</td>
                        <td width="200">${(apiLog.sendtime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td width="100">
                            <#if apiLog.sendchanel?? && apiLog.sendchanel=="yp">又拍
                            <#elseif apiLog.sendchanel?? && apiLog.sendchanel=="ali">阿里
                            <#elseif apiLog.sendchanel?? && apiLog.sendchanel=="wy">网易
                            </#if>
                        </td>
                        <td width="150">${(apiLog.biztype)!}</td>
                        <td width="130">${(apiLog.phone)!}</td>
                        <td width="130">
                            <#if apiLog.sendstatus?? && apiLog.sendstatus=="0">待发送
                            <#elseif apiLog.sendstatus?? && apiLog.sendstatus=="1">发送成功
                            <#elseif apiLog.sendstatus?? && apiLog.sendstatus=="4">发送失败
                            </#if>
                        </td>
                        <td>${(apiLog.failcause)!}</td>
                        <td>${(apiLog.sendinfo)!}</td>
                        <td nowrap="nowrap">
                            <#if apiLog.sendchanel?? && apiLog.sendchanel=="ali">
                                <a data-href="${ctx}/mobile/msg/sendlog/tb?phone=${(apiLog.phone)!}&&bizId=${(apiLog.bizId)!}&&logId=${(apiLog.id)!}&&sendDate=${(apiLog.sendtime?string("yyyyMMdd"))!}"
                                   type="POST" data-type="delSig"  data-confirm="确认同步该短信发送日志?" data-okMsg='同步成功!'data-failMsg="同步失败" class="pn-opt">同步</a>
                                | <a  data-type="lookdialog" data-val="4" href="javascript:;" data-url="${ctx}/mobile/msg/query?phone=${(apiLog.phone)!}&sendDate=${(apiLog.sendtime?string("yyyyMMdd"))!}&channel=${(apiLog.sendchanel)!}">查询</a>
                                <#elseif apiLog.sendchanel?? && apiLog.sendchanel=="yp">
                                    <a  data-type="lookdialog" data-val="4" href="javascript:;" data-url="${ctx}/mobile/msg/query?phone=${(apiLog.phone)!}&sendDate=${(apiLog.sendtime?string("yyyyMMdd"))!}&channel=${(apiLog.sendchanel)!}">查询</a>
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

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"  style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>