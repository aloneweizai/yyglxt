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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ifont/iconfont.css">

</head>
<!--个人产品代客下单付款-->
<@shiro.hasPermission name="uc_myorder:createorder">
    <#assign canCreateorder=true>
</@shiro.hasPermission>
<!--我的销售订单查看-->
<@shiro.hasPermission name="uc_myorder:vieworder">
    <#assign canView=true>
</@shiro.hasPermission>
<body>
<div id=loading
     style="position:fixed; width:100%; height:100%; z-index:10000;display: block;background-color: black;opacity: 0.4">
    <img src="${ctx}/images/loading1.gif"
         style="position: fixed;width: 100px;height: 100px;left: 50%;top: 50%;margin-left: -50px;margin-top: -50px">
</div>
<div  id="uuu" style="display: none">
<div style="z-index: 99999; border: none; margin: 0px; padding: 0px; width: 100%; height: 100%; top: 0px; left:0px;background-color: rgb(0, 0, 0); opacity: 0.6; cursor: wait; position: fixed;">
</div>
<div style="z-index: 100010; position: fixed; background:white;padding: 0px; margin: -145px 0px 0px -125px; width: 250px; top: 50%; left: 50%; text-align: center; color: rgb(0, 0, 0); cursor: auto; height: 330px;">
    <div class="zf_zhifubao" test="zhifu" id="alipay_div" style="display:block;padding: 20px;">
        <h4>
            <div class="zf_logo"><i class="iconfont" style="color:#009fe8;font-size: 40px;">&#xe750;</i></div>
        </h4>
        <div><span>
             <img src="" id="zhifubaoewm">
              </span>
            <p id="paysaoma">请使用支付宝扫一扫支付</p>
        </div>
        <button id="fanhuizhifu" class="layui-btn layui-btn-normal" button-type="1">取消支付</button>
        <button name="wanchengzhifu" button-type="2" class="layui-btn layui-btn-normal">完成支付</button>
    </div>
</div>
</div>

<div  id="yyy" style="display: none">
    <div style="z-index: 99999; border: none; margin: 0px; padding: 0px; width: 100%; height: 100%; top: 0px; left:0px;background-color: rgb(0, 0, 0); opacity: 0.6; cursor: wait; position: fixed;">
    </div>
    <div style="z-index: 100010; position: fixed; background:white;padding: 0px; margin: -145px 0px 0px -125px; width: 250px; top: 50%; left: 50%; text-align: center; color: rgb(0, 0, 0); cursor: auto; height: 330px;">
        <div class="zf_zhifubao" test="zhifu" id="alipay_div" style="display:block;padding: 20px;">
            <h4>
                <div class="zf_logo"><i class="iconfont" style="color:#09bb07;font-size: 40px;">&#xe751;</i></div>
            </h4>
            <div><span>
             <img src="" id="weixinewm">
              </span>
                <p id="paysaoma">请使用微信扫一扫支付</p>
            </div>
            <div id="fanhuizhifuw" class="layui-btn layui-btn-normal" button-type="1">取消支付</div>
            <button name="wanchengzhifu" button-type="2" class="layui-btn layui-btn-normal">完成支付</button>
        </div>
    </div>
</div>
    <div class="container-fluid m_top_30 nycol_list  sys_mod">
        <form action="${ctx}/financed/sellingOrderList.php?doType=1" method="post" id="consumerListForm"
              anme="consumerListForm " class="layui-form">
            <div class="layui-form-top" style="margin-top: -10px;">
                <table width="100%" style="border-bottom:1px solid #ccc;height: 30px">
                    <tr>
                        <td style="width: 50%;text-align: left">我的销售订单</td>
                        <td style="width: 50%;text-align: right">我的推荐人代码:${(username)!}</td>
                    </tr>
                </table>
            </div>
            <div class="layui-form-top">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户名</label>

                        <div class="layui-input-inline">
                            <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input"
                                   value="${(BaseRq.username)!}" name="username">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">用户手机</label>

                        <div class="layui-input-inline">
                            <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input"
                                   value="${(BaseRq.phone)!}" name="phone">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">订单状态</label>

                        <div class="layui-input-inline">
                            <select name="orderStatus" lay-filter="aihao">
                                <option value="">请选择订单状态</option>
                            <#if orderStatus?? && ( orderStatus?size gt 0 )>
                                <#list orderStatus as orderSta>
                                    <option
                                        <#if BaseRq.orderStatus ?? && (BaseRq.orderStatus == orderSta.fieldValue)>selected</#if>
                                        value="${orderSta.fieldValue}">${orderSta.fieldKey}</option>
                                </#list>
                            </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline" style="width:180px;">
                            <div id="consumer_query" class="layui-btn">查询</div>
                            <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                        </div>
                    </div>
                <#if canCreateorder??>
                    <a data-type='submitdialog' data-url="${ctx}/financed/createOrder.php"
                       class="layui-btn layui-btn-normal fr">个人产品代客下单</a>
                </#if>
                    <div class='moretj' style='display:none;'>
                        <div class="layui-inline">
                            <label class="layui-form-label">下单时间起</label>

                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="test30" value="${(BaseRq.startTime)!}"
                                       name="startTime">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">下单时间止</label>

                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}"
                                       name="endTime">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">商品类型</label>

                            <div class="layui-input-inline">
                                <select name="tradingChannels" lay-filter="tradingChannels">
                                    <option value="">全品类</option>
                                <#if channels?? && ( channels?size gt 0 )>
                                    <#list channels as channel>
                                        <#if channel.fieldValue != 'ALL'>
                                            <option
                                                <#if BaseRq.tradingChannels ?? && (BaseRq.tradingChannels == channel.fieldValue)>selected</#if>
                                                value="${channel.fieldValue}">${channel.fieldKey}</option>
                                        </#if>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-top  fr" style="margin-top: -10px;margin-left: 700px">
                <div>金额合计：
                <#if RMB??>
                ${(RMB?c)!}
                <#else>
                    0
                </#if>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    积分合计：
                <#if POINTS??>
                ${(POINTS?c)!}
                <#else>
                    0
                </#if>
                </div>
            </div>
            <div class="nycon_list_b">
                <table class="layui-table" lay-size="sm">
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
                        <th>操作</th>
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
                            <td><a class="ljc_00bcd4" data-type="opendialog" data-val="4" href="javascript:;"
                                   data-url="${ctx}/consumerManager/consumer/info.php?id=${order.userId}">${(order.user.username)!}</a>
                            </td>
                            <td>
                                <#if order.tradeMethod=="RMB"><span
                                        class="rmb_je fb">￥${(order.totalPrice?string("0.00"))!}</span>
                                <#elseif order.tradeMethod=="POINTS"><span class="jifen_jf fb">${(order.totalPrice?c)!}
                                    积分</span></#if>
                            </td>
                            <td>
                                <#if order.tradeMethod=="RMB">
                                <#--
                                 <#if order.payMethod?? && order.payMethod=="ALIPAY">
                                 支付宝
                                 <#elseif order.payMethod?? && order.payMethod=="WEIXIN">
                                 微信
                                 </#if>
                                 -->
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
                            <td>
                            <#if canView??>
                                <a data-type="opendialog" href="javascript:;"
                                   data-url="${ctx}/financed/orderInfo.php?doType=0&lookType=0&id=${order.orderNo}"
                                   class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                                </#if>
                                <#if order.orderStatus??&&order.orderStatus == '2'>
                                    <#if canCreateorder??>
                                |
                                <a id="payorder" data-no="${order.orderNo}" href="javascript:;"
                                   class="layui-btn layui-btn-normal layui-btn-mini">付款</a>
                                    </#if>
                                </#if>
                            </td>
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
                        共&nbsp;${BaseRq.totalItems?c}&nbsp;条&nbsp;&nbsp;
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
                        <input type="hidden" value="${ctx}/financed/sellingOrderList.php?doType=1" id="currLink">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    <#--<#include "../../common/pagination.ftl">-->
    </div>

    <div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <iframe id="look_frame" style="width: 100%;height: 500px;border:0" border="0"
                            frameboder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button class="layui-btn layui-btn-primary" class="btn btn-default" data-dismiss="lookModal">关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <iframe id="consumer_frame" style="width: 100%;height: 500px;border:0" border="0"
                            frameboder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn layui-btn-normal" id="editbtn" data-tj="modal">提交订单</button>
                    <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>


    <div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <iframe id="pay_frame" style="width: 100%;height: 500px;border:0" border="0"
                            frameboder="0"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="layui-btn layui-btn-normal" id="paysubmit" data-dismiss="pay">立即支付
                    </button>
                    <button type="button" class="layui-btn layui-btn-primary" data-dismiss="myModal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <script data-main="${ctx}/js/abc/financed/selling.js" src="${ctx}/js/require.js"></script>

    <script type="text/javascript">
        var ctx = "${ctx}";


    </script>
</body>
</html>