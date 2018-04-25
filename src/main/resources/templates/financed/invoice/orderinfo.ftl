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
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > td {
            text-align: left;
        }
    </style>
</head>
<body>
<form name="order_detail_form" action="" enctype="multipart/form-data" method="post" next-url="">
    <div class="container-fluid m_top_30 nycol_list  sys_mod">
        <ul id="myTab" class="nav nav-tabs">
        <#if orders?? && ( orders?size gt 0 )>
            <#list orders as order>
                <li <#if BaseRq.offset + order_index == 0 >class="active" </#if>>
                    <a href="#home${BaseRq.offset + order_index + 1}" data-toggle="tab">订单信息<#if (BaseRq.offset + order_index)??&&(BaseRq.offset + order_index)  gt 0>${BaseRq.offset + order_index}</#if></a>
                </li>
            </#list>
        </#if>
        <#--<!--<li><a href="#ios"  class="active" data-toggle="tab">收退款记录</a></li>&ndash;&gt;
        <li class="dropdown"><a href="#jmeter" data-toggle="tab">订单日志</a></li>
        <li><a href="#profile" data-toggle="tab">支付交易记录</a></li>-->
        </ul>
        <div id="myTabContent" class="tab-content">
        <#if orders?? && ( orders?size gt 0 )>
            <#list orders as order>
                <div <#if BaseRq.offset + order_index == 0 >class="tab-pane fade in active"<#else>class="tab-pane fade" </#if>id="home${BaseRq.offset + order_index + 1}">
                    <table class="layui-table" lay-size="sm">
                        <caption>商品信息</caption>
                        <tr style="font-weight:bold;">
                            <td>商品名称</td>
                            <td>规格</td>
                            <td>会员等级</td>
                            <td>销售价格</td>
                            <td>实付金额(积分)</td>
                            <td>商品数量</td>
                            <td>小计</td>
                            <td>赠送积分</td>
                        </tr>
                        <#if order.orderProductBOList?? && ( order.orderProductBOList?size gt 0 )>
                            <#list order.orderProductBOList as product>
                                <tr>
                                    <td>${product.name!}</td>
                                    <td>
                                    ${product.specInfo!}
                                    </td>
                                    <td>${order.nowVipLevelName!}</td>
                                    <#if order.tradeMethod?? && ( order.tradeMethod == "POINTS" )>
                                        <td>${(product.goodsPrice)!} 积分</td>
                                        <td><#if order.orderStatus??&&order.orderStatus != "2">${(order.totalPrice)!}
                                            积分</#if></td>
                                        <td>${product.num!}</td>
                                        <td><#if order.orderStatus??&&order.orderStatus != "2">${(product.dealPrice)!}
                                            积分</#if></td>
                                    <#else>
                                        <td>￥${(product.goodsPrice?string("0.00"))!}</td>
                                        <td><#if order.orderStatus??&&order.orderStatus != "2">
                                            ￥${(order.totalPrice?string("0.00"))!}</#if></td>
                                        <td>${product.num!}</td>
                                        <td><#if order.orderStatus??&&order.orderStatus != "2">
                                            ￥${(product.dealPrice)!}</#if></td>
                                    </#if>
                                    <td>${order.giftPoints!}</td>
                                </tr>
                            </#list>
                        </#if>
                    </table>
                    <table class="layui-table" lay-size="sm">
                        <caption>买家信息</caption>
                        <tr style="font-weight:bold;">
                            <td width="20%">用户名</td>
                            <td width="20%">昵称</td>
                            <td width="20%">真实姓名</td>
                            <td width="20%">手机号</td>
                            <td>当前会员等级</td>
                        </tr>
                        <#if order.user??>
                            <tr>
                                <td>${order.user.username!}</td>
                                <td>${order.user.nickname!}</td>
                                <td>${order.user.realName!}</td>
                                <td>${order.user.phone!}</td>
                                <td>${order.user.vipLevelName!}</td>
                            </tr>
                        </#if>
                    </table>
                    <#if order.isShipping?? && ( order.isShipping == 1 )>
                        <table class="layui-table" lay-size="sm">
                            <caption>买家收货地址信息</caption>
                            <tr style="font-weight:bold;">
                                <td width="20%">收件人</td>
                                <td width="20%">联系电话</td>
                                <td width="60%">邮寄地址</td>
                            </tr>
                            <tr style="background-color:#f6f6f6; ">
                                <td width="20%">${order.consignee!}</td>
                                <td width="20%">${order.contactNumber!}</td>
                                <td width="20%">${order.shippingAddress!}</td>
                            </tr>
                        </table>
                    </#if>
                    <table class="layui-table" lay-size="sm">
                        <caption>订单信息</caption>
                        <tr style="font-weight:bold;">
                            <td width="20%">订单号</td>
                            <td width="15%">订单状态</td>
                            <td width="15%">支付方式</td>
                            <td width="20%">是否需要寄送</td>
                            <td width="20%">下单时间</td>
                            <td width="10%">推荐人</td>
                        </tr>
                        <tr>
                            <td>${order.orderNo!}</td>
                            <td>
                                <#if orderStatus?? && ( orderStatus?size gt 0 )>
				   <#list orderStatus as orderStatu>
                                    <#if order.orderStatus == orderStatu.fieldValue>${orderStatu.fieldKey}</#if>
                                </#list>
			    </#if>
                            </td>
                            <td><#if order.tradeMethod?? && ( order.tradeMethod == "POINTS" )>积分<#else>人民币</#if></td>
                            <td><#if order.isShipping?? && ( order.isShipping == 1 )>是<#else>否</#if></td>
                            <td>${order.createTime?string("yyyy-MM-dd HH:mm:ss")!}</td>
                            <td>${order.recommendUser!}</td>
                        </tr>
                    </table>
                    <#if order.isShipping?? && ( order.isShipping == 1 )>
                        <table class="layui-table" lay-size="sm">
                            <caption>配送信息</caption>
                            <tr style="font-weight:bold;">
                                <td>物流公司</td>
                                <td>运单号</td>
                                <td>配送费用</td>
                                <td>是否保价</td>
                                <td>保价费用</td>
                            </tr>
                            <tr>
                                <td><#if order.expressComp??>${(order.expressComp.compName)!}</#if></td>
                                <td>${(order.expressNo)!}</td>
                                <td>￥${(order.deliveryFee)!}.00</td>
                                <td>
                                    <#if order.isInsured?? && ( order.isInsured == 1 )>是<#else>否</#if>
                                </td>
                                <td>￥${(order.insuredFee)!}.00</td>
                            </tr>
                        </table>
                    </#if>
                    <#if order.remark??>
                        <table class="layui-table" lay-size="sm">
                            <tr>
                                <td>备注:</td>
                                <td colspan="3">
                            <textarea style="width: 500px; height: 100px;" readonly
                                      id="remark">${order.remark!}</textarea>
                                </td>
                            </tr>
                        </table>
                    </#if>
                    <#if order.tradeMethod?? && ( order.tradeMethod != 'POINTS' )>
                        <table class="layui-table" lay-size="sm" id="fptable">
                            <caption>发票信息</caption>
                            <tr style="font-weight:bold;">
                                <td>发票抬头</td>
                                <td>公司名称</td>
                                <td>发票号码</td>
                                <td>发票代码</td>
                            </tr>
                            <#if order.invoiceBO??>
                                <tr>
                                    <td>
                                        <#if invoicenames?? && ( invoicenames?size gt 0 )>
			   <#list invoicenames as invoicename>
                                            <#if order.invoiceBO.name == invoicename.fieldValue>${invoicename.fieldKey}</#if>
                                        </#list>
		    </#if>
                                    </td>
                                    <td>${order.invoiceBO.nsrmc!}</td>
                                    <td>${order.invoiceBO.invoiceNo!}</td>
                                    <td>${order.invoiceBO.invoiceCode!}</td>
                                </tr>
                            </#if>
                        </table>
                    </#if>
                </div>
            </#list>
        </#if>
        </div>
    </div>
</form>

<script data-main="${ctx}/js/abc/financed/order.js" src="${ctx}/js/require.js"></script>
</body>
</html>