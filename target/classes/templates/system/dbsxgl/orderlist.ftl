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



</head>
<body>
<div id=loading style="position:fixed; width:100%; height:100%; z-index:10000;display: block;background-color: black;opacity: 0.4">
    <img src="${ctx}/images/loading1.gif" style="position: fixed;width: 100px;height: 100px;left: 50%;top: 50%;margin-left: -50px;margin-top: -50px">
</div>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/dbsxgl/orderList.php?doType=${(doType)!}" method="post" id="consumerListForm" anme="consumerListForm " class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <caption>订单详情列表</caption>
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>订单号</th>
                    <th>商品</th>
                    <th>用户名</th>
                    <th>金额/积分</th>
                    <th>支付方式</th>
                    <th>订单状态</th>
                    <th>是否寄送</th>
                    <th>下单时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if orderrs?? && ( orderrs?size gt 0 )>
                    <#list orderrs as order>
                    <tr>
                        <td class="td_i">${BaseRq.offset + order_index + 1}</td>
                        <td>${(order.orderNo)!}</td>
                        <td>
                            <#if order.orderProductBOList?? && ( order.orderProductBOList?size gt 0 )>
                                <#list order.orderProductBOList as product>
                                    <#if order.tradeMethod?? && order.tradeMethod=="POINTS">
                                    ${(product.name)!}&nbsp;${(product.dealPrice?c)!}积分&nbsp;数量:${product.num}</br>
                                    <#elseif order.tradeMethod?? && order.tradeMethod=="RMB">
                                    ${(product.name)!}&nbsp;￥${(product.dealPrice?string("0.00"))!}
                                        &nbsp;数量:${product.num}</br>
                                    </#if>
                                </#list>
                            </#if>
                        </td>
                        <td><a class="ljc_00bcd4" data-type="opendialog" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${order.userId}">${(order.user.username)!}</a></td>
                        <td>
                            <#if order.tradeMethod=="RMB"><span class="rmb_je fb">￥${(order.totalPrice?string("0.00"))!}</span>
                            <#elseif order.tradeMethod=="POINTS"><span class="jifen_jf fb">${(order.totalPrice?c)!}积分</span></#if>
                        </td>
                        <td>
                            <#if order.tradeMethod=="RMB">
                                人民币
                            <#elseif order.tradeMethod=="POINTS">
                                积分兑换
                            </#if>
                        </td>
                        <td>
                            <#if orderStatus?? && ( orderStatus?size gt 0 )>
            				   <#list orderStatus as orderStatu>
                                <#if order.orderStatus == orderStatu.fieldValue>${orderStatu.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                        <td>
                            <#if order.isShipping?? && ( order.isShipping == 1 )>
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
                        </td>
                        <td>${order.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
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
                    <input type="hidden" value="${ctx}/financed/orderList.php?dateType=1" id="currLink">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="width: 95%">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="look_frame" style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
              <button class="layui-btn layui-btn-primary" class="btn btn-default" data-dismiss="lookModal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script data-main="${ctx}/js/abc/financed/order.js" src="${ctx}/js/require.js"></script>

<script type="text/javascript">
    var ctx = "${ctx}";
</script>
</body>
</html>