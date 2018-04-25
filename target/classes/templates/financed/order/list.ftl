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
    <form action="${ctx}/financed/orderList.php?doType=${(doType)!}&dateType=1" method="post" id="consumerListForm" anme="consumerListForm " class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">订单号</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.orderNo)!}" name="orderNo">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.username)!}" name="username" >
                    </div>
                </div>
            <#if doType?? && doType=="0">
                <div class="layui-inline">
                    <label class="layui-form-label">订单状态</label>
                    <div class="layui-input-inline">
                        <select name="orderStatus" lay-filter="aihao">
                            <option value="">请选择订单状态</option>
                            <#if orderStatus?? && ( orderStatus?size gt 0 )>
                                <#list orderStatus as orderSta>
                                    <option <#if BaseRq.orderStatus ?? && (BaseRq.orderStatus == orderSta.fieldValue)>selected</#if> value="${orderSta.fieldValue}">${orderSta.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">下单时间起</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test30" value="${(BaseRq.startTime)!}" name="startTime">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">下单时间止</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}" name="endTime">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">商品类型</label>
                    <div class="layui-input-inline">
                        <select name="tradingChannels" lay-filter="goodstyle">
                            <#if channels?? && ( channels?size gt 0 )>
                                <#list channels as channel>
                                    <option <#if BaseRq.tradingChannels ?? && (BaseRq.tradingChannels == channel.fieldValue)>selected</#if> value="${channel.fieldValue}">${channel.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <#--<div class="layui-inline" id="good"<#if BaseRq.tradingChannels??>style='display:none;'</#if> >
                    <label class="layui-form-label">商品</label>
                    <div class="layui-input-inline">
                        <select>
                            <option value="">请选择商品</option>
                        </select>
                    </div>
                </div>-->
                <div class="layui-inline" id="vipselect" <#if !(BaseRq.tradingChannels??&&BaseRq.tradingChannels == "HYCZ")>style='display:none;'</#if> >
                    <label class="layui-form-label">商品</label>
                    <div class="layui-input-inline">
                        <select name="levelId" lay-filter="aihao">
                            <option value="">请选择商品</option>
                            <#if vipLevels?? && ( vipLevels?size gt 0 )>
                                <#list vipLevels as vipLevel>
                                <#if 'VIP0' !=  vipLevel.levelCode>
                                    <option <#if BaseRq.goodsId ?? && (BaseRq.goodsId == vipLevel.id)>selected</#if> value="${vipLevel.id}">${vipLevel.level}</option>
                                </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline" id="courseselect" <#if !(BaseRq.tradingChannels??&&BaseRq.tradingChannels == "CSKT")>style='display:none;'</#if>>
                    <label class="layui-form-label">商品</label>
                    <div class="layui-input-inline" style="width: 230px">
                        <select name="curriculumId" lay-filter="aihao"  style="width: 230px" lay-verify="required" lay-search="">
                            <option value="">请选择商品</option>
                            <#if courseListBoRs?? && ( courseListBoRs?size gt 0 )>
                                <#list courseListBoRs as courseList>
                                    <option <#if BaseRq.goodsId ?? && (BaseRq.goodsId == courseList.curriculumId)>selected</#if> value="${courseList.curriculumId}">${courseList.title}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
            <#else>
                <div class="layui-inline">
                    <label class="layui-form-label">下单时间</label>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test30" value="${(BaseRq.startTime)!}" name="startTime" style="width: 100px">
                    </div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.endTime)!}" name="endTime" style="width: 100px">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
            </#if>

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
                    <th>商品类型</th>
                    <th>商品</th>
                    <th>用户名</th>
                    <th>金额/积分</th>
                    <th>交易方式</th>
                    <th>支付方式</th>
                    <th>订单状态</th>
                    <th>是否寄送</th>
                   <#-- <th>是否可以退货</th>-->
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
                                    <#if channels?? && ( channels?size gt 0 )>
                                        <#list channels as channel>
                                            <#if product.tradingChannels ?? && (product.tradingChannels == channel.fieldValue)>${channel.fieldKey}</#if>
                                        </#list>
                                    </#if>
                                </#list>
                            </#if>
                        </td>
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
                            <#if order.payMethod?? && order.payMethod=="ALIPAY">
                                支付宝
                            <#elseif order.payMethod?? && order.payMethod=="WEIXIN">
                                微信
                            <#elseif order.payMethod?? && order.payMethod=="POINTS">
                                积分
                            </#if>
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
                        <#--<td>
                            <#if order.orderProductBOList?? && ( order.orderProductBOList?size gt 0 )>
                                <#list order.orderProductBOList as product>
                                    <#if product.isReturn?? && ( product.isReturn == 0 )>
                                        <div class="btn btn-success btn-xs ">是</div>
                                    <#elseif product.isReturn?? && ( product.isReturn == 1 )>
                                        <div class="btn btn-danger btn-xs ">否</div>
                                    </#if>
                                </#list>
                            </#if>
                        </td>-->
                        <td>${order.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>
                            <#if doType??&&doType=="0">
                            <#if order.orderStatus == '11'>
                                <@shiro.hasPermission name="uc_order:orderlook">
                                <a data-type="opendialog" href="javascript:;"
                                   data-url="${ctx}/financed/orderInfo.php?doType=0&lookType=0&id=${order.orderNo}" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="uc_order:delete">
                                   <a data-type="delSig" data-confirm="确认删除该订单申请?" data-okMsg="删除成功!"
                                     data-failMsg="删除失败" href="javascript:void(0);"
                                     data-href="${ctx}/financed/orderdel.php?id=${order.orderNo}&&userId=${order.userId}" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                                </@shiro.hasPermission>
                            <#else>
                                <@shiro.hasPermission name="uc_order:orderlook">
                                <a id="layui_open1" data-type="opendialog" href="javascript:;"
                                   data-url="${ctx}/financed/orderInfo.php?doType=0&lookType=0&id=${order.orderNo}" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                                </@shiro.hasPermission>
                            </#if>
                            <#--  <a href="${ctx}/financed/orderInfo.php?doType=0&lookType=0&id=${order.orderNo}">查看</a>-->
                            <#elseif doType??&&doType=="1">
                                <a data-type="opendialog" href="javascript:;"
                                   data-url="${ctx}/financed/orderInfo.php?doType=1&lookType=0&id=${order.orderNo}" class="layui-btn layui-btn-normal layui-btn-mini">查看</a>
                            <#--<a href="${ctx}/financed/orderInfo.php?doType=1&lookType=0&id=${order.orderNo}">查看</a>-->
                                <a name="open_order" class="layui-btn layui-btn-mini" href="javascript:void(0)" data-url="${ctx}/financed/orderInfo.php?doType=1&lookType=1&id=${order.orderNo}">发货</a>
                                <#--<a href="${ctx}/financed/orderInfo.php?doType=1&lookType=1&id=${order.orderNo}" class="layui-btn layui-btn-mini">发货</a>-->
                            <#else>
                                <a href="${ctx}/financed/orderInfo.php?doType=2&id=${order.orderNo}" class="layui-btn layui-btn-danger layui-btn-mini">处理</a>
                            </#if>
                        <#if order.orderStatus??&&(order.orderStatus == '6')><!--已完成-->
                                <#if order.orderProductBOList?? && ( order.orderProductBOList?size gt 0 )>
                                    <#list order.orderProductBOList as product>
                                        <#if product.specInfo?? && ( product.specInfo == "VIP1"||product.specInfo == "VIP2"||product.specInfo == "VIP3"||product.specInfo == "VIP4" )>
                                            <@shiro.hasPermission name="uc_order:orderreturn">
                                                <a data-type="vipdialog" data-val="4" href="javascript:;" class="layui-btn layui-btn-warm layui-btn-mini" data-url="${ctx}/financed/vipchoose.php?orderNo=${order.orderNo}&&userId=${order.userId}&&goodsId=${product.goodsId}">退单</a>
                                            <#--<a data-type="delSig" data-confirm="确认该订单申请退单?" data-okMsg="退单成功!"
                                               data-failMsg="退单失败" href="javascript:void(0);"
                                               data-href="${ctx}/financed/orderreturn.php?id=${order.orderNo}&&userId=${order.userId}&&goodsId=${product.goodsId}" class="layui-btn layui-btn-warm layui-btn-mini">退单</a>-->
                                            </@shiro.hasPermission>
                                        </#if>
                                    </#list>
                                </#if>
                        </#if>
                            <!--|&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="" class="pn-opt">删除</a>-->
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>

        <#--<input type='button' class="btn btn-default btn-sm pn-opt" value='导出订单信息' id="batch_dc" name="batch_dc"/>
        <input type='button' class="btn btn-default btn-sm pn-opt" value='导入快递单号' id="batch_dr" name="batch_dr"/>-->
        </div>


        <div class="button_caozuo_fenye">
            <#if doType??&&doType=="1">
                <input type="hidden" style="width: 100px;" id="doType" value="${(doType)!}">
                <input type='button' class="layui-btn" value='导出订单信息' id="batch_dc" name="batch_dc"/>
                <input type='button' class="layui-btn layui-btn-normal" value='导入快递单号' id="batch_dr" name="batch_dr"/>
            </#if>
            <div class="clear"></div>
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
<#--<#include "../../common/pagination.ftl">-->
</div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="myModal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">导入快递信息</h4>
            </div>
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>

                <div>
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td>
                                快递公司：
                            </td>
                            <td>
                                <select name="compId" style="width:200px;height:30px;" id="compId" class="compId">
                                    <option value="">-- 请选择 --</option>
                                <#if logisticsRs?? && ( logisticsRs?size gt 0 )>
                                    <#list logisticsRs as logisticsRs>
                                        <#if logisticsRs.templateUrl??&&logisticsRs.templateUrl !="">
                                            <option value="${logisticsRs.id}">${logisticsRs.compName}</option>
                                        </#if>
                                    </#list>
                                </#if>
                                </select>
                                <span style="color: red;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Excel文件：
                            </td>
                            <td>
                                <input type="file" class="uploadFile" name="FILE01" id="FILE01"
                                       style="width:200px;display: inline-block;">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" value="导入" id="importbtn" name="importbtn" class="layui-btn layui-btn-normal">
                <input type="button" value="关闭" id="back" class="layui-btn layui-btn-primary">
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
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

<div class="main_modal container-fluid" id="vipModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="vipModal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择退单后的会员等级</h4>
            </div>
            <div class="modal-body">
                <iframe id="vip_frame" style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button id="vipchoosebtn" name="vipchoosebtn" class="layui-btn layui-btn-normal" data-val="2">确定</button>
                <button class="layui-btn layui-btn-primary" class="btn btn-default" data-dismiss="vipModal">关闭</button>
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