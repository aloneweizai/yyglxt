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
    <form action="${ctx}/mobile/msg/modle/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">模板状态</label>
                    <div class="layui-input-inline">
                        <select name="status" lay-filter="aihao">
                            <option value="">请选择模板状态</option>
                        <#if templateStatus?? && ( templateStatus?size gt 0 )>
                            <#list templateStatus as templateSta>
                                <option <#if BaseRq.status ?? && (BaseRq.status == templateSta.fieldValue)>selected</#if> value="${templateSta.fieldValue}">${templateSta.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a data-type="delSig" data-confirm="确定同步短信模板到本地?" data-okMsg="同步短信模板成功!"
                   data-failMsg="同步短信模板失败" href="javascript:void(0);"
                   data-href="${ctx}/mobile/msg/modle/synchro.php" class="layui-btn layui-btn-normal fr">同步短信模板</a>
            </div>
        </div>



        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>模板ID</th>
                    <th>模板标题</th>
                    <th>模板签名</th>
                    <th>短信类型</th>
                    <th>短信模板内容</th>
                    <th>模板状态</th>
                    <th>已发送行业短信条数</th>
                    <th>已发送营销短信条数</th>
                    <th>原因</th>
                    <th>创建者昵称</th>
                    <th>创建时间</th>
                    <th>同步时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if mobileModleMsgRs??>
                    <#list mobileModleMsgRs as mobileModleMsg>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+mobileModleMsg_index+1}</td>
                        <td>${(mobileModleMsg.templateId)!}</td>
                        <td>${(mobileModleMsg.title)!}</td>
                        <td>${(mobileModleMsg.sign)!}</td>
                        <td>
                            <#if mobileModleMsg.type??&&mobileModleMsg.type == "industry">
                                行业
                            <#elseif mobileModleMsg.type??&&mobileModleMsg.type == "marketing" >
                                营销
                            </#if>
                        </td>
                        <td title='${mobileModleMsg.content!}'>${(mobileModleMsg.contentstr)!}</td>
                        <td>
                            <#if templateStatus?? && ( templateStatus?size gt 0 )>
                                <#list templateStatus as templateSta>
                                    <#if mobileModleMsg.status ?? && (mobileModleMsg.status == templateSta.fieldValue)>${templateSta.fieldKey}</#if>
                                </#list>
                            </#if>
                           <#-- ${(mobileModleMsg.status)!}-->
                        </td>
                        <td>${(mobileModleMsg.ownerIndustry)!}</td>
                        <td>${(mobileModleMsg.ownerMarketing)!}</td>
                        <td>${(mobileModleMsg.reason)!}</td>
                        <td>${(mobileModleMsg.ownerName)!}</td>
                        <td>${(mobileModleMsg.createTime)!}</td>
                        <td>${(mobileModleMsg.synchronizeTime?string("yyyy-MM-dd"))!}</td>
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
