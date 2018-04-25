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
<!--清空-->
<@shiro.hasPermission name="sys_redisgl:flush">
    <#assign canFlush=true>
</@shiro.hasPermission>
<!--删除-->
<@shiro.hasPermission name="sys_redisgl:delete">
    <#assign canDelete=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/system/redis/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">正则表达式</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.pattern)!}" name="pattern" id="pattern">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
<#if canFlush??>
                <a data-type="delSig" data-confirm="确定清空redis数据库?" data-okMsg="清空redis数据库成功!"
                   data-failMsg="清空redis数据库失败" href="javascript:void(0);"
                   data-href="${ctx}/system/redis/flushdb.php" class="layui-btn  layui-btn-danger fr">清空redis数据库</a>
</#if>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>redis键</th>
<#if canDelete??>
                    <th>操作</th>
</#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if redisGlRs??>
                    <#list redisGlRs as redisGl>
                    <tr>
                        <td>${BaseRq.offset + redisGl_index + 1}</td>
                        <td>${(redisGl)!}</td>
                        <#if canDelete??>
                        <td> <a data-url="${ctx}/system/redis/${(redisGl)!}" type="POST"
                                data-id="${(redisGl)!}" data-type="detele"  abc-type="是否删除?" class="pn-opt">删除</a></td>
                        </#if>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/system/redisgl" src="${ctx}/js/require.js"></script>
</body>
</html>
