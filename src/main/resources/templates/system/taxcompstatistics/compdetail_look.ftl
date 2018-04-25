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
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .table>thead>tr>th,.table>tbody>tr>td{text-align:left;}

        .ny_tab_t>tbody>tr>td,.ny_tab_t>tr>td{padding: 3px 3px;}
        caption {
            padding-top: 8px;
            padding-bottom: 8px;
            color: #777;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/taxcompstatis/compdetail/userlook.php?doType=${BaseRq.doType!}&type=${BaseRq.type!}&timeInterval=${BaseRq.timeInterval!}&startTime=${BaseRq.startTime!}&endTime=${BaseRq.endTime!}&menu=${BaseRq.menu!}" method="post" id="consumerListForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <caption>企业详情</caption>
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>纳税人识别号</th>
                    <th>纳税人名称</th>
                    <th>主管税务机关代码</th>
                    <th>主管税务机关名称</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if taxpayerBindRs?? && ( taxpayerBindRs?size gt 0 )>
                    <#list taxpayerBindRs as taxpayerBind>
                    <tr>
                        <td class="td_i">${BaseRq.offset + taxpayerBind_index + 1}</td>
                        <td>${(taxpayerBind.nsrsbh)!}</td>
                        <td>${(taxpayerBind.nsrmc)!}</td>
                        <td>${(taxpayerBind.swjgdm)!}</td>
                        <td>${(taxpayerBind.swjgmc)!}</td>
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
