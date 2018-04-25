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
            <li class="active">
                <a href="#home" data-toggle="tab">订单信息</a>
            </li>
            <!--<li><a href="#ios" data-toggle="tab">收退款记录</a></li>-->
            <li class="dropdown"><a href="#jmeter" data-toggle="tab">订单日志</a></li>
            <li><a href="#profile" data-toggle="tab">支付交易记录</a></li>
            <li><a href="#send" data-toggle="tab">优惠赠送</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <table class="layui-table" lay-size="sm">
                    <caption>商品信息</caption>
                    <tr style="font-weight:bold;">
                        <td>商品名称</td>
                        <td>规格</td>
                        <td>会员等级</td>
                        <td>销售价格</td>
                    <#--<td>折扣</td>-->
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
                            <#--<td>${(product.dealPrice/product.goodsPrice*100)!}%</td>-->
                                <td><#if order.orderStatus??&&order.orderStatus != "2">${(order.totalPrice)!} 积分</#if></td>
                                <td>${product.num!}</td>
                                <td><#if order.orderStatus??&&order.orderStatus != "2">${(product.dealPrice)!} 积分</#if></td>
                            <#else>
                                <td>￥${(product.goodsPrice?string("0.00"))!}</td>
                            <#-- <td>${(product.dealPrice/product.goodsPrice*100)!}%</td>-->
                                <td><#if order.orderStatus??&&order.orderStatus != "2">￥${(order.totalPrice?string("0.00"))!}</#if></td>
                                <td>${product.num!}</td>
                                <td><#if order.orderStatus??&&order.orderStatus != "2">￥${(product.dealPrice)!}</#if></td>
                            </#if>
                            <td>${order.giftPoints!}</td>
                        </tr>
                    </#list>
                </#if>
                </table>
                <table class="layui-table" lay-size="sm">
                    <caption>优惠券信息</caption>
                    <tr style="font-weight:bold;">
                        <td>优惠券名称</td>
                        <td>优惠适用品类</td>
                        <td>优惠模式</td>
                        <td>优惠类型</td>
                        <td>计算金额类型</td>
                        <td>使用有效期</td>
                        <td>优惠金额</td>
                    </tr>
                <#if couponUserRs?? && ( couponUserRs?size gt 0 )>
                    <#list couponUserRs as couponUser>
                       <tr>
                            <td>${couponUser.couponName!}</td>
                            <td>
                            ${couponUser.category!}
                            </td>
                           <td>
                               <#if couponMode?? && (couponMode?size > 0) >
                        <#list couponMode as mode>
                                   <#if couponUser.couponMode??&&couponUser.couponMode == mode.fieldValue>${mode.fieldKey}</#if>
                               </#list>
                    </#if>
                           </td>
                            <td>
                                <#if couponType?? && (couponType?size > 0) >
                                    <#list couponType as type>
                                        <#if couponUser.couponType??&&couponUser.couponType == type.fieldValue>${type.fieldKey}</#if>
                                    </#list>
                                </#if>
                                <#if couponUser.couponType??&&couponUser.couponType =="MANJIAN">
                                    满&nbsp;${couponUser.param1!}&nbsp;元&nbsp;减&nbsp;${couponUser.param2!}&nbsp;元
                                <#elseif couponUser.couponType??&&couponUser.couponType =="LIJIAN">
                                    减&nbsp;${couponUser.param2!}&nbsp;元
                                <#elseif couponUser.couponType??&&couponUser.couponType =="ZHEKOU">
                                    满&nbsp;${couponUser.param1!}&nbsp;元&nbsp;打${couponUser.param2!}&nbsp;折
                                </#if>
                           </td>
                            <td>
                                <#if couponUser.amountType??&&couponUser.amountType =="ORDER">
                                    订单金额
                                <#elseif couponUser.amountType??&&couponUser.amountType =="POSTAGE">
                                    邮费金额
                                </#if>
                            </td>
                           <td>${couponUser.validStartTime?string("yyyy-MM-dd")!}&nbsp;至&nbsp;${couponUser.validEndTime?string("yyyy-MM-dd")!}</td>
                           <td><#if couponUser.amountAfter??>${couponUser.amountAfter!}元</#if></td>
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
                <#--  <#if order.userAddressBO??>-->
                    <tr style="background-color:#f6f6f6; ">
                        <td width="20%">${order.consignee!}</td>
                        <td width="20%">${order.contactNumber!}</td>
                        <td width="20%">${order.shippingAddress!}</td>
                    <#--<td width="60%">${order.userAddressBO.provinceName!}${order.userAddressBO.cityName!}${order.userAddressBO.areaName!}${order.userAddressBO.detail!}</td>-->
                    </tr>
                <#-- </#if>-->
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
                    <#--  <td>发票名称</td>-->
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
                        <#--<td>
                            <#if invoicetypes?? && ( invoicetypes?size gt 0 )>
       <#list invoicetypes as invoicetype>
                                <#if order.invoiceBO.type??&&order.invoiceBO.type == invoicetype.fieldValue>${invoicetype.fieldKey}</#if>
                            </#list>
    </#if>
                        </td>-->
                        </tr>
                    </#if>
                </table>
            </#if>
            </div>
            <div class="tab-pane fade" id="profile">
                <table class="layui-table" lay-size="sm">
                    <caption>支付交易记录</caption>
                    <tr>
                        <td>订单号</td>
                        <td>金额(积分)</td>
                        <td>类型</td>
                        <td>支付方式</td>
                        <td>支付状态</td>
                        <td>完成时间</td>
                    </tr>

                <#if order.tradeBOList?? && (order.tradeBOList?size gt 0 )>
                    <#list order.tradeBOList as tradeBO>
                        <#if tradeBO.tradeLogBOList?? && (tradeBO.tradeLogBOList?size gt 0 )>
                            <#list tradeBO.tradeLogBOList as tradeLog>
                                <#if order.tradeMethod?? && ( order.tradeMethod == "POINTS" )>
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
            <div class="tab-pane fade" id="jmeter">
                <table class="layui-table" lay-size="sm">
                    <caption>订单日志</caption>
                    <tr style="font-weight:bold;">
                        <td>时间</td>
                        <td>操作人</td>
                        <td>动作</td>
                        <td>备注</td>
                    </tr>
                <#if order.orderLogBOList?? && ( order.orderLogBOList?size gt 0 )>
                    <#list order.orderLogBOList as orderLog>
                        <tr>
                            <td>${(orderLog.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            <td>${(orderLog.createUserName)!}</td>
                            <td>${(orderLog.action)!}</td>
                            <td>${orderLog.remark!}</td>
                        </tr>
                    </#list>
                </#if>
                </table>
            </div>
            <div class="tab-pane fade" id="send">
                <table class="layui-table" lay-size="sm">
                    <caption>优惠赠送</caption>
                <#if order.orderGiftBOList?? && ( order.orderGiftBOList?size gt 0 )>
                    <#list order.orderGiftBOList as orderGift>
                        <#if orderGift.operType == "POINTS">
                            <tr style="font-weight:bold;">
                                <td width="50%">赠送积分</td>
                                <td>${orderGift.operValue!}</td>
                            </tr>
                        </#if>
                        <#if orderGift.operType == "COUPON">
                            <tr style="font-weight:bold;">
                                <td>赠送优惠券</td>
                                <td>${couponActivity!}</td>
                            </tr>
                        </#if>
                        <#if orderGift.operType == "VIP">
                            <tr style="font-weight:bold;">
                                <td>赠送会员</td>
                                <td>
                                    <#if vipLevels??&&vipLevels?size gt 0>
                        <#list vipLevels as level>
                                        <#if orderGift.operValue??&&level.levelCode == orderGift.operValue>
                                        ${level.name!}
                                        </#if>
                                    </#list>
                    </#if>
                                </td>
                            </tr>
                        </#if>
                        <#if orderGift.operType == "AMOUNT">
                            <tr style="font-weight:bold;">
                                <td>赠送礼包金额</td>
                                <td>${orderGift.operValue!}</td>
                            </tr>
                        </#if>
                    </#list>
                    <tr style="font-weight:bold;">
                        <td>赠送时间</td>
                        <td>${order.lastUpdate?string("yyyy-MM-dd HH:mm:ss")!}</td>
                    </tr>
                <#else>
                    <tr style="font-weight:bold;">
                        <td width="50%">赠送积分</td>
                        <td></td>
                    </tr>
                    <tr style="font-weight:bold;">
                        <td>赠送优惠券</td>
                        <td></td>
                    </tr>
                    <tr style="font-weight:bold;">
                        <td>赠送会员</td>
                        <td></td>
                    </tr>
                    <tr style="font-weight:bold;">
                        <td>赠送礼包金额</td>
                        <td></td>
                    </tr>
                    <tr style="font-weight:bold;">
                        <td>赠送时间</td>
                        <td></td>
                    </tr>
                </#if>
                </table>
            </div>
        </div>
        <table>
            <tr>
                <td style="width: 400px;"></td>
            <#--<td colspan="3" style='text-align:right;padding-right:10%;'>
              <button type="button" class="layui-btn layui-btn-normal" data-toggle="modal" data-target="#myModal">发货</button>
              <button type="button" class="layui-btn layui-btn-normal" data-toggle="modal" data-target="#myModal3">退货</button>
              <input type="reset" name="button2" value="作废" class="layui-btn layui-btn-danger">
            </td>-->
                <td>
                    <input type="hidden" value="${(doType)!}" id="doType">
                    <input type="hidden" value="${(order.orderNo)!}" name="orderNo" id="orderNo">
                    <input type="hidden" value="${(order.userId)!}" name="userId" id="userId">
                <#--<input type="hidden" value="${(order.remark)!}" name="remark" id="remark">-->
                <#if order.orderStatus?? &&( order.orderStatus == '4' ) &&(lookType??&&lookType=="1")>
                    <input type="button" name="sendBtn" id="sendBtn" value="发货" class="layui-btn layui-btn-normal">
                    <button type="button" class="layui-btn layui-btn-primary"  id="closeSend">关闭</button>
                <input id="link" value="${ctx}/financed/orderList.php?dateType=1&doType=${(doType)!}&page=${(OrderRq.page)!}&orderNo=${(OrderRq.orderNo)!}&&username=${(OrderRq.username)!}&&orderStatus=${(OrderRq.orderStatus)!}&&startTime=${(OrderRq.startTime)!}&&endTime=${(OrderRq.endTime)!}" hidden>
                <#--<button type="button" class="layui-btn layui-btn-normal" data-toggle="modal" data-target="#myModal3">退货</button>-->
                <#-- <input type="reset" name="button2" value="作废" class="layui-btn layui-btn-danger">-->
                   <#-- <a href="${ctx}/financed/orderList.php?doType=${(doType)!}&page=${(OrderRq.page)!}&orderNo=${(OrderRq.orderNo)!}&&username=${(OrderRq.username)!}&&orderStatus=${(OrderRq.orderStatus)!}&&startTime=${(OrderRq.startTime)!}&&endTime=${(OrderRq.endTime)!}"
                       style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>-->
                <#--<input type="button" name="back" id="back"class="layui-btn layui-btn-primary"btn-default">-->
                <#else>
                <#--  <a href="${referer}" style=class="layui-btn layui-btn-primary" class="btn btn-default">返回</a>-->
                <#--<input type="buttoclass="layui-btn layui-btn-primary"" value="返回" class="btn btn-default">-->
                </#if>
                </td>
            </tr>
        </table>
    </div>
</form>
<div class="main_modal container-fluid" id="fhModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">发货信息</h4>
                </div>
                <div class="modal-body">
                    <table class="layui-table" lay-size="sm">
                        <caption>商品信息</caption>
                        <tr>
                            <th>商品名称</th>
                            <th>规格</th>
                            <th>实付金额(积分)</th>
                            <th>购买数量</th>
                        <#-- <th>选择发货</th>-->
                        </tr>
                    <#if order.orderProductBOList?? && ( order.orderProductBOList?size gt 0 )>
                        <#list order.orderProductBOList as product>
                            <tr>
                                <td>${(product.name)!}</td>
                                <td>
                                ${product.specInfo!}
                                </td>
                                <#if order.tradeMethod?? && ( order.tradeMethod == "POINTS" )>
                                    <td>${(order.totalPrice)!} 积分</td>
                                <#else>
                                    <td><#if product.sellingPrice??>￥${product.sellingPrice?string(".00")}</#if></td>
                                </#if>
                                <td>${product.num}</td>
                            </tr>
                        </#list>
                    </#if>
                    </table>
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td>订单号</td>
                            <td><input type="hidden" value="${(doType)!}" id="doType">
                                <input type="hidden" value="${(order.orderNo)!}" name="orderNo" id="orderNo">
                                <input type="hidden" value="${(order.userId)!}" name="userId"
                                       id="userId">${order.orderNo!}</td>
                            <td>是否需要配送</td>
                            <td><#if order.isShipping?? && ( order.isShipping == 1 )>是<#else>否</#if></td>
                        </tr>
                    <#if order.isShipping?? && ( order.isShipping == 1 )>
                        <tr>
                            <td>配送方式：</td>
                            <td>
                                <input type="radio" name="expressMethod" checked="checked"> 快递
                            <#-- <input type="radio" name="expressMethod"> 自取-->
                            </td>
                            <td></td>
                            <td></td>
                        <#--<td>是否保价</td>
                        <td>否</td>-->
                        </tr>
                        <#if order.isShipping?? && ( order.isShipping == 1 )>
                            <tr>
                                <td>收件人：</td>
                                <td>
                                ${order.consignee!}
                                </td>
                                <td>手机号：</td>
                                <td>
                                ${order.contactNumber!}
                                </td>
                            </tr>
                            <tr>
                                <td>配送地址：</td>
                                <td colspan="3" align="left">
                                ${order.shippingAddress!}
                                <#-- <input type="text"style="width:145px; height: 32px; float: left;"  value="${order.userAddressBO.provinceName!}" name="province" readonly="readonly">
                                 <input type="text"style="width:145px; height: 32px; float: left;"  value="${order.userAddressBO.cityName!}" name="city" readonly="readonly">
                                 <input type="text"style="width:145px; height: 32px; float: left;"  value="${order.userAddressBO.areaName!}" name="area" readonly="readonly">
                            --> </td>
                            </tr>
                        <#--<tr>
                            <td>详细地址</td>
                            <td colspan="3">
                            ${order.userAddressBO.detail!}
                            </td>
                        </tr>-->
                        <#else>
                            <tr>
                                <td>收件人：</td>
                                <td>
                                </td>
                                <td>手机号：</td>
                                <td>
                                </td>
                            </tr>
                            <tr>
                                <td>配送地址：</td>
                                <td colspan="3">
                                </td>
                            </tr>
                            <tr>
                                <td>详细地址</td>
                                <td colspan="3">
                                </td>
                            </tr>
                        </#if>
                        <tr>
                            <td>物流公司：</td>
                            <td>
                                <select name="compId" style="width:200px;height:30px;" id="expressCompId"
                                        class="expressCompId">
                                    <#if logisticsRs?? && ( logisticsRs?size gt 0 )>
                                        <#list logisticsRs as logisticsRs>
                                            <#if logisticsRs.templateUrl??&&logisticsRs.templateUrl !="">
                                                <option value="${logisticsRs.id}">${logisticsRs.compName}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                                <label style='color:red;'>*</label>
                            </td>
                            <td>快递单号：</td>
                            <td>
                                <input type="text" value="" name="expressNo" id="expressNo"><label style='color:red;'>*</label>
                            </td>
                        </tr>
                    </#if>
                        <tr>
                            <td>备注</td>
                            <td colspan="3">
                                <textarea style="width: 500px; height: 100px;" placeholder="请填写物品发货信息"
                                          id="remark"></textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer" class="layui-btn layui-btn-primary">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal" id="closeBtn">关闭</button>
                    <!--<button type="button" class="layui-btn layui-btn-normal">打印配货单</button>-->
                <button type="button" class="layui-btn layui-btn-normal" id="sendSubmit" data-val="${(order.isShipping)!}">确认</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/financed/order.js" src="${ctx}/js/require.js"></script>
</body>
</html>