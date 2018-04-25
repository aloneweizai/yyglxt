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
    <form action="${ctx}/cszjs/wx/msg/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">模板标题</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.title)!}" name="title">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">一级行业</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.primary_industry)!}" name="primary_industry">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">二级行业</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.deputy_industry)!}" name="deputy_industry">
                    </div>
                </div>
                <a data-type="delSig" data-confirm="确认微信模板到本地?" data-okMsg="同步微信模板成功!"
                   data-failMsg="同步微信模板失败" href="javascript:void(0);"
                   data-href="${ctx}/cszjs/wx/msg/synchro.php" class="btn btn-md btn-info layui-btn fr">同步微信模板</a>
                <input style="margin-top: 0"  type="button" value="查询" id="consumer_query" class="layui-btn fr">
            </div>
        </div>



        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>模板ID</th>
                    <th>模板标题</th>
                    <th>一级行业</th>
                    <th>二级行业</th>
                    <th>模板内容</th>
                    <th>示例</th>
                    <th>同步时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if wxTemplateRs??>
                    <#list wxTemplateRs as wxTemplate>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+wxTemplate_index+1}</td>
                        <td>${(wxTemplate.template_id)!}</td>
                        <td>${(wxTemplate.title)!}</td>
                        <td>${(wxTemplate.primary_industry)!}</td>
                        <td>${(wxTemplate.deputy_industry)!}</td>
                        <td title='${wxTemplate.content!}'>${(wxTemplate.contentstr)!}</td>
                        <td title='${wxTemplate.example!}'>${(wxTemplate.examplestr)!}</td>
                        <td>${(wxTemplate.lastupdate?string("yyyy-MM-dd"))!}</td>
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
