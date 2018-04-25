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
    <form id="consumerListForm" class="layui-form" method="post" action="${ctx}/system/record/systemRecord.php"  class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">日期</label>
                    <div class="layui-input-inline">
                        <input type="text" id="test30" value="${(BaseRq.yyyyMMdd)!}" name="yyyyMMdd" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">使用系统</label>

                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.appName)!}" name="appName" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">访问地点</label>

                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.location)!}" name="location" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>



                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">用户名</label>

                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">操作功能</label>

                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.feature)!}" name="feature" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>用户</th>
                    <th>纳税人识别号</th>
                    <th>时间</th>
                    <th>页面标题</th>
                    <th>停留时长(秒)</th>
                    <th>操作功能</th>
                    <th>使用系统</th>
                    <th>访问地点</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if listRs?? && ( listRs?size gt 0 )>
                    <#list listRs as list>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+list_index+1}</td>
                        <td>${(list.username)!}</td>
                        <td>${(list.nsrsbh)!}</td>
                        <td>${(list.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td>
                            <a class="ljc_00bcd4"  href="javascript:void(0);" data-id="${(list.id)!}" name="a1">${(list.pageTitle)!}</a>
                        </td>
                        <td>${(list.stayLong)!}</td>
                        <td>${(list.feature)!}</td>
                        <td>${(list.appName)!}</td>
                        <td>${(list.location)!}</td>
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
<!-- Modal(模态框) -->
<div class="modal fade" id="modal-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">详细页面</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary ok"id="model-ok">确定</button>
            </div>
        </div>
    </div>
</div><!-- modal -->

<script data-main="${ctx}/js/abc/system/SystemRecord.js" src="${ctx}/js/require.js"></script>
</body>
</html>
