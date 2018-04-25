<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css"/>
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list character_no_span" >
    <form class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">税号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${RequestParameters["nsrsbh"]!}" name="nsrsbh" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${RequestParameters["phone"]!}" name="phone" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${RequestParameters["name"]!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">年份</label>
                    <div class="layui-input-inline">
                        <select id="yearChoose" name="date">
                        <#list year..year-10 as t>
                            <option value="${t?c}" <#if RequestParameters["date"]?? && t?c ==RequestParameters["date"]>selected</#if>>${t?c} </option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn js-query">查询</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <form name="form_save" action="" method="post" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="50">序号</th>
                    <th style="text-align: center">企业名称</th>
                    <th style="text-align: center">产品名称</th>
                    <th style="text-align: center">问题类型名称</th>
                    <th style="text-align: center">税号</th>
                    <th style="text-align: center">姓名</th>
                    <th style="text-align: center">手机号</th>
                    <th style="text-align: center">创建时间</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if serviceOrders?? && (serviceOrders?size > 0) >
                    <#list serviceOrders as order>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + order_index + 1}</td>
                        <td align="center">${(order.nsrmc)!}</td>
                        <td align="center">${(order.productName)!}</td>
                        <td align="center">${(order.type)!}</td>
                        <td align="center">${(order.nsrsbh)!}</td>
                        <td align="center">${(order.name)!}</td>
                        <td align="center">${(order.phone)!}</td>
                        <td align="center">${(order.visitDate)!}</td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        ${pageHtml!}
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/customerServiceOrder.js" src="${ctx}/js/require.js"></script>
</body>
</html>