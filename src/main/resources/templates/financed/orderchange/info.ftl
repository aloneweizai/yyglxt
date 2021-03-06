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
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
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
            <li class="active">
                <a href="#home" data-toggle="tab">订单信息</a>
            </li>
            <!--<li><a href="#ios" data-toggle="tab">收退款记录</a></li>-->
            <li class="dropdown"><a href="#jmeter" data-toggle="tab">订单日志</a></li>
            <li><a href="#profile" data-toggle="tab">支付交易记录</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <div>
                    <table class="layui-table" lay-size="sm">
                        <caption>商品信息</caption>
                        <tr style="font-weight:bold;">
                            <td>商品名称</td>
                            <td>规格</td>
                            <td>会员等级</td>
                            <td>实付金额(积分)</td>
                            <td>商品数量</td>
                            <td>小计</td>
                            <td>赠送积分</td>
                        </tr>
                    <#if orders.orderProductBOList?? && ( orders.orderProductBOList?size gt 0 )>
                        <#list orders.orderProductBOList as orderProduct>
                            <tr style="background-color:#f6f6f6; ">
                                <td>${(orderProduct.name)!}</td>
                                <td>
                                ${orderProduct.specInfo!}
                                </td>
                                <td>${orders.nowVipLevelName!}</td>
                                <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                                    <td>${(orderProduct.dealPrice)!} 积分</td>
                                    <td>${orderProduct.num!}</td>
                                    <td>${(orders.totalPrice)!} 积分</td>
                                <#else>
                                    <td>￥${(orderProduct.dealPrice?string("0.00"))!}</td>
                                    <td>${(orderProduct.num)!}</td>
                                    <td>￥${(orders.totalPrice?string("0.00"))!}</td>
                                </#if>
                                <td>${(orders.giftPoints)!}</td>
                            </tr>
                        </#list>
                    </#if>
                    </table>
                    <table class="layui-table" lay-size="sm">
                        <caption>订单信息</caption>
                        <tr style="font-weight:bold;">
                            <td width="20%">订单号</td>
                            <td width="20%">订单金额</td>
                            <td width="20%">支付方式</td>
                            <td>下单时间</td>
                            <td>推荐人</td>
                        </tr>
                        <tr style="background-color:#f6f6f6; ">
                            <td width="20%">${orders.orderNo!}</td>
                        <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                            <td width="20%">${(orders.totalPrice)!}积分</td>
                        <#else>
                            <td width="20%">￥${(orders.totalPrice?string("0.00"))!}</td>
                        </#if>
                            <td width="20%"><#if orders.payMethod?? && orders.payMethod="ALIPAY">支付宝<#else>积分</#if></td>
                            <td>${(orders.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            <td>${orders.recommendUser!}</td>
                        </tr>
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
                    <#if orders.user??>
                        <tr style="background-color:#f6f6f6; ">
                            <td width="20%">${orders.user.username!}</td>
                            <td width="20%">${orders.user.nickname!}</td>
                            <td width="20%">${orders.user.realName!}</td>
                            <td width="20%">${orders.user.phone!}</td>
                            <td>${orders.user.vipLevelName!}</td>
                        </tr>
                    </#if>
                    </table>
                <#if orderExchange.type=="1">
                    <table class="layui-table" lay-size="sm">
                        <caption>买家收货地址信息</caption>
                        <tr style="font-weight:bold;">
                            <td width="20%">收件人</td>
                            <td width="20%">联系电话</td>
                            <td width="60%">邮寄地址</td>
                        </tr>
                        <tr style="background-color:#f6f6f6; ">
                            <td width="20%">${orders.consignee!}</td>
                            <td width="20%">${orders.contactNumber!}</td>
                            <td width="20%">${orders.shippingAddress!}</td>
                        <#--<td width="60%">${order.userAddressBO.provinceName!}${order.userAddressBO.cityName!}${order.userAddressBO.areaName!}${order.userAddressBO.detail!}</td>-->
                        </tr>
                    </table>
                </#if>
                <#if orders.isShipping?? && ( orders.isShipping == 1 )&&orderExchange.type=="1">
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
                            <td><#if orders.expressComp??>${(orders.expressComp.compName)!}</#if></td>
                            <td>${(orders.expressNo)!}</td>
                            <td>￥${(orders.deliveryFee)!}.00</td>
                            <td>
                                <#if orders.isInsured?? && ( orders.isInsured == 1 )>是<#else>否</#if>
                            </td>
                            <td>￥${(orders.insuredFee)!}.00</td>
                        </tr>
                    </table>
                </#if>
                <#if orders.invoiceBO??>
                    <table class="layui-table" lay-size="sm">
                        <caption>发票信息</caption>
                        <tr style="font-weight:bold;">
                            <td width="20%">发票代码</td>
                            <td width="20%">发票号码</td>
                            <td width="20%">发票性质</td>
                            <td>发票金额</td>
                        </tr>
                        <tr style="background-color:#f6f6f6; ">
                            <td width="20%">${orders.invoiceBO.invoiceCode!}</td>
                            <td width="20%">${orders.invoiceBO.invoiceNo!}</td>
                            <td width="20%"><#if orders.invoiceBO.property=="1">纸质发票<#else>电子发票</#if></td>
                            <td>￥${(orders.invoiceBO.amount?string("0.00"))!}</td>
                        </tr>
                    </table>
                </#if>
                </div>
            <#--<table class="table table-hover">
                <caption>订单信息及退/换货申请信息</caption>
                <tr style="font-weight:bold;">
                    <td width="20%">订单号</td>
                    <td width="20%">订单金额</td>
                    <td width="20%">支付方式</td>
                    <td>下单时间</td>
                    <td>推荐人</td>
                </tr>
                <tr style="background-color:#f6f6f6; ">
                    <td width="20%">${orders.orderNo!}</td>
                    <td width="20%">￥${(orders.totalPrice?string("0.00"))!}</td>
                    <td width="20%"><#if orders.payMethod?? && orders.payMethod="ALIPAY">支付宝<#else>微信</#if></td>
                    <td>${(orders.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    <td>${orders.recommendUser!}</td>
                </tr>
                <tr style="font-weight:bold;">
                    <td width="20%" >申请时间</td>
                    <td width="20%" >申请类型</td>
                    <td width="20%" >申请状态</td>
                    <td width="20%" colspan="2">退/换货原因</td>
                </tr>
                <tr style="background-color:#f6f6f6; ">
                    <td width="20%">${(orderExchange.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                    <td width="20%">
                       <#if orderExchange.type=="1"><div class="layui-btn btn-xs ">换货</div><#else><div class="btn btn-warning btn-xs ">退货</div></#if>
                    </td>
                    <td width="20%">
                        <#if exchange_status?? && ( exchange_status?size gt 0 )>
                           <#list exchange_status as status>
                               <#if orderExchange.status == status.fieldValue>${status.fieldKey}</#if>
                           </#list>
                        </#if>
                    </td>
                    <td width="20%" colspan="2">
                       <#if exchange_reason?? && ( exchange_reason?size gt 0 )>
                           <#list exchange_reason as reason>
                               <#if orderExchange.reason == reason.fieldValue>${reason.fieldKey}</#if>
                           </#list>
                       </#if>
                    </td>
                </tr>
                <tr style="font-weight:bold;"><td colspan="4">用户申请备注</td></tr>
                <tr><td colspan="4" style="background-color:#f6f6f6;height:80px;">${orderExchange.userRemark!}</td></tr>
            </table>-->
            <#--<#if orders.isShipping?? && ( orders.isShipping == 1 )&&orderExchange.type=="1">
                <table class="table table-hover">
                    <caption>配送信息</caption>
                    <tr style="font-weight:bold;">
                        <td>物流公司</td>
                        <td>运单号</td>
                        <td>配送费用</td>
                        <td>是否保价</td>
                        <td>保价费用</td>
                    </tr>
                    <tr>
                        <td><#if orders.expressComp??>${(orders.expressComp.compName)!}</#if></td>
                        <td>${(orders.expressNo)!}</td>
                        <td>￥${(orders.deliveryFee)!}.00</td>
                        <td>
                            <#if orders.isInsured?? && ( orders.isInsured == 1 )>是<#else>否</#if>
                        </td>
                        <td>￥${(orders.insuredFee)!}.00</td>
                    </tr>
                </table>
            </#if>-->
            <#--<table class="table table-hover">
                <caption>审核员备注</caption>
                <tr >
                    <td colspan="4" style="background-color:#f6f6f6;height:80px;">${orderExchange.adminRemark!}</td>
                </tr>
            </table>
            <table class="table table-hover">
                <caption>审核员确认备注</caption>
                <tr >
                    <td colspan="4" style="background-color:#f6f6f6;height:80px;">${orderExchange.adminConfirmRemark!}</td>
                </tr>
            </table>-->
            </div>
            <div class="tab-pane fade" id="jmeter">
                <table class="table table-hover" style="margin-top:10px;">
                    <caption>订单日志</caption>
                    <tr style="font-weight:bold;">
                        <td>时间</td>
                        <td>操作人</td>
                        <td>动作</td>

                        <td>备注</td>
                    </tr>
                <#if orders.orderLogBOList?? && ( orders.orderLogBOList?size gt 0 )>
                    <#list orders.orderLogBOList as orderLog>
                        <tr style="background-color:#f6f6f6; ">
                            <td>${(orderLog.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            <td width="20%">${orderLog.createUserName!}</td>
                            <td width="20%">${orderLog.action!}</td>
                            <td>${orderLog.remark!}</td>
                        </tr>
                    </#list>
                </#if>
                </table>
            </div>
            <div class="tab-pane fade" id="profile">
                <table class="table table-hover">
                    <caption>支付交易记录</caption>
                    <tr>
                        <td>订单号</td>
                        <td>收款金额</td>
                        <td>类型</td>
                        <td>支付方式</td>
                        <td>支付状态</td>
                        <td>完成时间</td>
                    </tr>
                <#if orders.tradeBOList?? && (orders.tradeBOList?size gt 0 )>
                    <#list orders.tradeBOList as tradeBO>
                <#if tradeBO.tradeLogBOList?? && (tradeBO.tradeLogBOList?size gt 0 )>
                    <#list tradeBO.tradeLogBOList as tradeLog>
                        <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                            <tr>
                                <td>${tradeBO.orderNo!}</td>
                                <td>
                                    <#if tradeLog.amount??>
                                    ${tradeLog.amount!}积分
                                        </#if>
                                </td>
                                <td>
                                    <#if tradeLog.tradeType?? && tradeLog.tradeType="1">付款
                                    <#elseif tradeLog.tradeType?? && tradeLog.tradeType="2">退款
                                    <#else>未付款
                                    </#if>
                                </td>
                                <td>积分兑换</td>
                            <#--交易状态：1.交易中 2.交易成功 3.交易失败 4.取消交易-->
                                <td>
                                    <#if tradeLog.tradeStatus?? && tradeLog.tradeStatus="1">交易成功
                                    <#elseif tradeLog.tradeStatus?? && tradeLog.tradeStatus="2">交易成功
                                    <#elseif tradeLog.tradeStatus?? && tradeLog.tradeStatus="3">交易失败
                                    <#elseif tradeLog.tradeStatus?? && tradeLog.tradeStatus="4">取消交易
                                    <#else>交易中
                                    </#if>
                                </td>
                                <td>${(tradeLog.tradeTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            </tr>
                        <#else>
                            <tr>
                                <td>${tradeBO.orderNo!}</td>
                                <td>
                                    <#if tradeLog.amount??>
                                        ￥${(tradeLog.amount?string("0.00"))!}
                                    </#if>
                                    </td>
                                <td>
                                    <#if tradeLog.tradeType?? && tradeLog.tradeType="1">付款
                                    <#elseif tradeLog.tradeType?? && tradeLog.tradeType="2">退款
                                    <#else>未付款
                                    </#if>
                                </td>
                                <td>支付宝</td>
                                <td>
                                    <#if tradeLog.tradeStatus?? && tradeLog.tradeStatus="1">交易成功
                                    <#elseif tradeLog.tradeStatus?? && tradeLog.tradeStatus="2">交易成功
                                    <#elseif tradeLog.tradeStatus?? && tradeLog.tradeStatus="3">交易失败
                                    <#elseif tradeLog.tradeStatus?? && tradeLog.tradeStatus="4">取消交易
                                    <#else>交易中
                                    </#if>
                                </td>
                                <td>${(tradeLog.tradeTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            </tr>
                        </#if>
                    </#list>
                </#if>
                    </#list>
</#if>
                </table>
            </div>
        </div>
    <#--<table>
        <tr>
          <td colspan="2" style="text-align:right;padding-right:150px;">
             <#if orderExchange.status?? && orderExchange.status == "1">
                <input type="button" name="button" id="application_ok" value="审核" class="layui-btn">
             <#elseif orderExchange.status?? && orderExchange.status == "2">
                <#if orderExchange.type=="1">
                  <input type="button" name="button" id="application_sh" value="确认收货" class="layui-btn">
                <#else>
                  <input type="button" name="button" id="application_td" value="确认退单" class="layui-btn">
                </#if>
             </#if>
             <a href="${ctx}/orderchange/applications.php" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
          </td>
        </tr>
    </table>-->
    </div>
</form>

<!--退/换货审批-->
<#if orderExchange.status?? && orderExchange.status == "1">
<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document" style="top:-400px;">
        <form id="orderbackCharge">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">退/换货审核</h4>
                </div>
                <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <tbody id="jiageTB">
                        <tr>
                            <td>审核</td>
                            <td>
                                <input type="hidden" name="id" id="orderExchangeId" value="${orderExchange.id!}"/>
                                <input type="radio" name="status" value="2" id="isOk" checked>审核通过
                                <input type="radio" name="status" value="5" id="isNO" style="margin-left:60px;">审核拒绝
                            </td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td>
                                <textarea id="refuseRson" name="adminRemark" placeholder="审核拒绝时请填写拒绝原因" rows="6"
                                          cols="50"></textarea><label style='color:red;'>*</label>
                                <label id="tips" style='color:red;font-size: x-small;' hidden>不能为空</label></br><span
                                    style="font-size:12px; color: #999;"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn" data-save="modal">确定</button>
                    <button tyclass="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </form>
    </div>
</div>
</#if>
<!--确认收货-->
<#if orderExchange.status?? && orderExchange.status == "2" && orderExchange.type=="1">
<div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
    <div class="modal-dialog" role="document" style="top:-400px;">
        <form id="makesure">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal1" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">确认收货(<span style="font-size:12px; color: #999;">温馨提示:实物退换请确认已收到货物</span>)
                    </h4>
                </div>
                <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <tbody id="jiageTB">
                        <tr>
                            <td colspan="2">
                                <table class="table table-hover" style="margin-top:10px;">
                                    <tr style="font-weight:bold;">
                                        <th>商品名称</th>
                                        <th>规格</th>
                                        <th>销售价格</th>
                                        <th>购买数量</th>
                                    </tr>
                                    <#if orders.orderProductBOList?? && ( orders.orderProductBOList?size gt 0 )>
                                        <#list orders.orderProductBOList as orderProduct>
                                            <tr style="background-color:#f6f6f6; ">
                                                <td>${(orderProduct.name)!}</td>
                                                <td>
                                                ${(orderProduct.specInfo)!}
                                                </td>
                                                <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                                                    <td>${(orders.goodsPrice)!} 积分</td>
                                                <#else>
                                                    <td><#if orderProduct.goodsPrice??>
                                                        ￥${orderProduct.goodsPrice?string("0.00")!}</#if></td>
                                                </#if>
                                                <td>${(orderProduct.num)!}</td>
                                            </tr>
                                        </#list>
                                    </#if>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>收货快递公司</td>
                            <td>
                                <input type="hidden" name="id" id="orderExchangeIdsure" value="${orderExchange.id!}"/>
                                <input type="hidden" name="status" value="6"/>
                                <input type="text" name="expressComp" data-rule="required;"
                                       style="width:250px;height:30px;"><label style='color:red;'>*</label>
                            </td>
                        </tr>
                        <tr>
                            <td>收货快递单号</td>
                            <td>
                                <input type="text" name="expressNo" data-rule="required;"
                                       style="width:250px;height:30px;"><label style='color:red;'>*</label>
                            </td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td>
                                <textarea id="refuseRson" name="adminRemark" rows="6" cols="50"></textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn" data-save="modal1">确定</button>
                    <button class="layui-btn layui-btn-primary" data-dismiss="modal1">关闭</button>

                </div>
            </div>
        </form>
    </div>
</div>
</#if>

<!-- 确认退单 -->
<#if orderExchange.status?? && orderExchange.status == "2" && orderExchange.type=="2">
<div class="main_modal container-fluid" id="myModal2" tabindex="-1" hidden>
    <div class="modal-dialog" role="document" style="top:-400px;">
        <form id="orderback">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal2" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">确认退单</h4>
                </div>
                <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <tbody id="jiageTB">
                        <tr>
                            <td colspan="2">
                                <table class="table table-hover" style="margin-top:10px;">
                                    <tr style="font-weight:bold;">
                                        <th>商品名称</th>
                                        <th>规格</th>
                                        <th>销售价格</th>
                                        <th>购买数量</th>
                                    </tr>
                                    <#if orders.orderProductBOList?? && ( orders.orderProductBOList?size gt 0 )>
                                        <#list orders.orderProductBOList as orderProduct>
                                            <tr style="background-color:#f6f6f6; ">
                                                <td>${(orderProduct.name)!}</td>
                                                <td>
                                                ${(orderProduct.specInfo)!}
                                                </td>
                                                <#if orders.tradeMethod?? && ( orders.tradeMethod == "POINTS" )>
                                                    <td>${(orders.goodsPrice)!} 积分</td>
                                                <#else>
                                                    <td><#if orderProduct.goodsPrice??>
                                                        ￥${orderProduct.goodsPrice?string("0.00")!}</#if></td>
                                                </#if>
                                                <td>${(orderProduct.num)!}</td>
                                            </tr>
                                        </#list>
                                    </#if>
                                </table>
                            </td>
                        </tr>
                            <#if orders.invoiceBO??>
                            <tr>
                                <td colspan="2">
                                    <table class="layui-table" lay-size="sm">
                                        <tr style="font-weight:bold;">
                                            <td width="20%">发票代码</td>
                                            <td width="20%">发票号码</td>
                                            <td width="20%">发票性质</td>
                                            <td>发票金额</td>
                                        </tr>
                                        <tr style="background-color:#f6f6f6; ">
                                            <td width="20%">${orders.invoiceBO.invoiceCode!}</td>
                                            <td width="20%">${orders.invoiceBO.invoiceNo!}</td>
                                            <td width="20%"><#if orders.invoiceBO.property=="1">纸质发票<#else>
                                                电子发票</#if></td>
                                            <td>￥${(orders.invoiceBO.amount?string("0.00"))!}</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            </#if>
                        <!--实物和纸质发票的时候需要填写说活快递公司和单号-->
                            <#if (orderExchange.goodsType??&&orderExchange.goodsType=="1") || (orders.invoiceBO?? && orders.invoiceBO.property=="1")>
                            <tr>
                                <td>收货快递公司</td>
                                <td>
                                    <input type="text" name="expressComp" data-rule="required;"
                                           style="width:250px;height:30px;"><label style='color:red;'>*</label>
                                </td>
                            </tr>
                            <tr>
                                <td>收货快递单号</td>
                                <td>
                                    <input type="text" name="expressNo" data-rule="required;"
                                           style="width:250px;height:30px;"><label style='color:red;'>*</label>
                                </td>
                            </tr>
                            </#if>
                        <tr>
                            <td>备注</td>
                            <td>
                                <input type="hidden" name="id" id="orderExchangeIdback" value="${orderExchange.id!}"/>
				<textarea id="refuseRson" placeholder="实物退单请确认已收到货物;如已开纸质发票请确认是否已收到发票" name="adminConfirmRemark"
                          rows="6" cols="50" data-rule="required;">
                </textarea><label style='color:red;'>*</label>
                                <br/>
                            <#--<span style="font-size:12px; color: #999;">温馨提示:实物退单请确认已收到货物;如已开纸质发票请确认是否已收到发票</span>-->
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn" data-save="modal2">确定</button>
                    <button class="layui-btn layui-btn-primary"ype="button" data-dismiss="modal2">关闭</button>

                </div>
            </div>
        </form>
    </div>
</div>
</#if>


<#if orderExchange.status?? && orderExchange.status == "7">
<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog" role="document" style="top:-400px;">
        <form id="orderrefund">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal3" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">确认退款</h4>
                </div>
                <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <tbody id="jiageTB">
                        <tr>
                            <td>订单金额:</td>
                            <td>
                                ￥${(orders.totalPrice?string("0.00"))!}
                            </td>
                        </tr>
                        <tr>
                            <td>退款金额:</td>
                            <td>
                                <input type="text" data-rule="required;range(0~);" name="amount" id="amount"
                                       value='${(orders.totalPrice?string("0.00"))!}'/>
                            </td>
                        </tr>
                        <tr>
                            <td>退款渠道:</td>
                            <td>
                                <input type="radio" name="refundType" value="1" id="refundType" checked>支付宝
                            </td>
                        </tr>
                        <tr>
                            <td>备注:</td>
                            <td>
                                <input type="hidden" name="id" id="orderExchangeIdback" value="${orderExchange.id!}"/>
                                <textarea id="refuseRson" name="adminRemark" rows="6" cols="50"></textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn" data-save="modal3">确定</button>
                    <button class="layui-btn layui-btn-primary" type="button" data-dismiss="modal3">关闭</button>

                </div>
            </div>
        </form>
    </div>
</div>
</#if>

<script data-main="${ctx}/js/abc/financed/orderchange.js" src="${ctx}/js/require.js"></script>
</body>
</html>