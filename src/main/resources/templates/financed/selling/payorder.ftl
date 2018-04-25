<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn" class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- 指定多核浏览器用webkit模式 -->
		<meta name="renderer" content="webkit">
		<title></title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/page_edit.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
		<script type="text/javascript">
            var ctx = "${ctx}";
        </script>
		<style>
             .nobord{ margin: 0 auto;font-size:medium;height:auto;positon:relative;}
			 .bt-label{margin:10px 10px;height:30px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 4px 10px;}
			 .nobord>tbody>tr>td{border-top:none;}
		     .nobord>tbody>tr>td:nth-child(odd){font-weight:bold;}
			 .bta{float: right;padding: 0 5px;height:25px;font-size: inherit;}
			 a{color:blue;}
        </style>
	</head>
	<body class="g_wrapper g_wrapper_full page_edit g_survey">

	<#--<table class="table  table-hover">
    <tr style="background:#f5f5f5">
      <td  height="30"><!-- &nbsp;当前位置：&nbsp;<a href="${ctx}/consumerManager/consumer/list.php"><u>用户管理</u></a> &gt;&gt; 用户标签管理 &ndash;&gt;</td>
    </tr>
    </table>-->

		<div id="container" class="g_container" >
			<div class="editor_container" style="display: block;">	 
				<!-- 主体 -->
				<div class="editor_main" style=" left: 0; top: 60px;">
					<div class="survey_wrap">
						<div class="survey_main" style="padding-top:0;">
                            <div class="survey_container">
                                <div class="page" data-pid="1" style="display: block;">
                                    <div class="toupiao-title"><h3>客户信息</h3></div>
                                    <div class="question question_radio required" style="width:94%;margin-left:3%">
                                        <table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
<#if order.user??>
                                            <tr>
                                                <td style="width: 15%">用户名：</td><td style="width: 25%">
                                                <input type="hidden" id="tradeNo"
    <#if order.tradeBOList?? && ( order.tradeBOList?size gt 0 )>
        <#list order.tradeBOList as trade>
                                                       value="${trade.tradeNo!}">
                               </#list>
            </#if>
                                            ${order.user.username!}</td>
                                                <td style="width: 15%">用户昵称:</td><td style="width: 25%">${order.user.nickname!}</td>
                                            </tr>
                                            <tr>
                                                <td>手机号:</td>
                                                <td>${order.user.phone!}</td>
                                                <td>用户姓名:</td>
                                                <td>${order.user.realName!}</td>
                                            </tr>
</#if>
                                        </table>
                                    </div>
                                    <div class="toupiao-title"><h3>订单信息</h3></div>
                                    <div class="question question_radio required" style="width:94%;margin-left:3%">
                                        <table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
<#if order.orderProductBOList?? && ( order.orderProductBOList?size gt 0 )>
    <#list order.orderProductBOList as product>
                                            <tr>
                                                <td>订单号:</td><td><input type="hidden" id="orderNo" value="${(product.orderNo)!}">${order.orderNo!}</td>
                                                <td>商品名称:</td>
                                                <td>
                                                    <input type="hidden" id="subject" value="${product.name!}">
                                                ${product.name!}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>下单时间:</td><td>${order.createTime?string("yyyy-MM-dd HH:mm:ss")!}</td>
                                                <td>购买优惠赠送:</td>
                                                <td>
        <#if order.orderGiftBOList?? && ( order.orderGiftBOList?size gt 0 )>
            <#list order.orderGiftBOList as orderGift>
                <#if orderGift.operType == "POINTS">
                ${orderGift.operValue!}积分&nbsp;&nbsp;
                </#if>
                <#if orderGift.operType == "COUPON">
                    赠送优惠券(${couponActivity!})&nbsp;&nbsp;&nbsp;&nbsp;
                </#if>
                <#if orderGift.operType == "VIP">
                            <#if levels??&&levels?size gt 0>
                        <#list levels as level>
                                <#if orderGift.operValue??&&level.levelCode == orderGift.operValue>
                                ${level.level!}&nbsp;&nbsp;&nbsp;&nbsp;
                                </#if>
                            </#list>
                    </#if>
                </#if>
                <#if orderGift.operType == "AMOUNT">
                ${orderGift.operValue!}会员礼包&nbsp;&nbsp;&nbsp;&nbsp;
                </#if>
            </#list>
        <#else>
        ${order.giftPoints!}
        </#if>

                                            </td>
                                            </tr>
                                            <tr>
                                                <td><input type="hidden" id="productId" value="${(product.goodsId)!}">产品价格:</td>
                                                <td><input type="hidden" id="amount" value="${(product.dealPrice)!}">￥${(product.dealPrice)!}</td>
                                                <td>订单备注:</td>
                                                <td>${(order.remark)!}</td>
                                            </tr>
    </#list>
</#if>
											<tr>
												<td>支付方式</td>
												<td colspan="3"><input type="radio" name="payMethod" checked="checked" id="ali" value="ali" style="width: 20px;height: 20px">支付宝
                                                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="payMethod" id="weixin"  value="weixin" style="width: 20px;height: 20px">微信</td>
											</tr>
                                        </table>
                                    </div>
                                    </br>
                                </div>
                            </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>