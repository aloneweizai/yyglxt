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
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/system/productspread/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">产品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.productname)!}" name="productname" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/system/productspread/edit.php" class="layui-btn layui-btn-normal fr">添加产品</a>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>产品</th>
                    <th>链接</th>
                    <th>修改时间</th>
                    <th>修改人</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if productSpreadRs?? && ( productSpreadRs?size gt 0 )>
                    <#list productSpreadRs as productSpread>
                    <tr>
                        <td class="td_i">${BaseRq.offset + productSpread_index + 1}</td>
                        <td>
                            <span title="${productSpread.name!}">
                                <#if productSpread.name?? && productSpread.name?length gt 33>
                                    ${productSpread.name?substring(0, 30)}...
                                <#else>
                                    ${productSpread.name!}
                                </#if>
                            </span>
                        </td>
                        <td>
                            <a class="ljc_00bcd4" target="_blank" href="${productSpread.url!}">
                                <#if productSpread.url?? && productSpread.url?length gt 33>
                                    ${productSpread.url?substring(0, 30)}...
                                <#else>
                                    ${productSpread.url!}
                                </#if>
                            </a>
                        </td>
                        <td>${(productSpread.lastUpdate?string("yyyy-MM-dd"))!}</td>
                        <td>${(productSpread.updateName)!}</td>
                        <td>
                            <a data-type='opendialog'
                               data-url="${ctx}/system/productspread/look.php?productSpreadId=${productSpread.id!}&lookType=0"
                               class="pn-opt">查看</a>&nbsp;|
                            <a href="${ctx}/system/productspread/edit.php?productSpreadId=${productSpread.id!}&lookType=1"
                               class="pn-opt">修改</a> &nbsp;|
                            <a data-href="${ctx}/system/productspread/del.php?productSpreadId=${(productSpread.id)!}"
                               type="POST"
                               data-type="delSig" data-confirm="确认删除该产品宣传页?" data-okMsg='删除成功!' data-failMsg="删除失败"
                               class="pn-opt">删除</a>
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
                        <input type="hidden" value="${ctx}/orderchange/applications.php" id="currLink">
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
                <iframe id="consumer_frame" style="width: 95%;height: 500px;border:0" border="0"
                        frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/system/productspread.js" src="${ctx}/js/require.js"></script>
</body>
</html>
