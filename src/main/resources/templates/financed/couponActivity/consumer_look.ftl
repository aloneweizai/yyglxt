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
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/financed/couponActivity/getOrUseLog.php" method="get" id="consumerListForm" class="layui-form">
        <input type="hidden" name="activityId" id="activityId" value="${BaseRq.activityId!}">
        <input type="hidden" name="status" id="status" value="${BaseRq.status!}">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>优惠劵名称</th>
                    <th>用户名</th>
                    <th>优惠劵模式</th>
                    <th>优惠类型</th>
                    <th>计算金额类型</th>
                    <th>优惠金额</th>
                    <th>有效期</th>
                    <th>状态</th>
                    <th>订单号</th>
                    <th>更新时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if couponUserRs??>
                    <#list couponUserRs as couponUser>
                    <tr>
                        <td>${BaseRq.offset + couponUser_index + 1}</td>
                        <td>
                        ${(couponUser.couponName)!}
                        </td>
                        <td>
                        ${(couponUser.username)!}
                        </td>
                        <td>
                            <#if couponMode?? && ( couponMode?size gt 0 )>
            				   <#list couponMode as mode>
                                <#if couponUser.couponMode??&&couponUser.couponMode == mode.fieldValue>${mode.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                        <td>
                            <#if couponType?? && ( couponType?size gt 0 )>
            				   <#list couponType as type>
                                <#if couponUser.couponType??&&couponUser.couponType == type.fieldValue>${type.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                        <#--<td>
                            <#if couponUser.validType?? && couponUser.validType == "PERIOD" >
                            ${(coupon.validStartTime?string("yyyy-MM-dd"))!}至${(coupon.validEndTime?string("yyyy-MM-dd"))!}
                            <#elseif couponUser.validType?? && couponUser.validType == "DAYS">
                            ${(couponUser.validDays)!}&nbsp;天
                            </#if>
                        </td>-->
                        <td>
                            <#if couponUser.amountType?? && couponUser.amountType == "ORDER" >
                                订单金额
                            <#elseif couponUser.amountType?? && couponUser.amountType == "POSTAGE">
                                邮费金额
                            </#if>
                        </td>
                        <td>
                            <#if couponUser.amountAfter??>${(couponUser.amountAfter)!}元</#if>
                        </td>
                        <td>${(couponUser.validStartTime?string("yyyy-MM-dd"))!}&nbsp;至&nbsp;${(couponUser.validEndTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <#if couponUseStatus?? && ( couponUseStatus?size gt 0 )>
            				   <#list couponUseStatus as couponUseStat>
                                <#if couponUser.status??&&couponUser.status == couponUseStat.fieldValue>${couponUseStat.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                        <td>
                            <a data-type="lookdialog" href="javascript:;"
                               data-url="${ctx}/financed/orderInfo.php?doType=0&lookType=0&id=${couponUser.orderNo!}">${(couponUser.orderNo)!}</a>
                        </td>
                        <td>${(couponUser.lastUpdate?string("yyyy-MM-dd"))!}</td>
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
                        每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                        <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                        <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                        <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                        <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                        当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                        <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
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


<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/financed/couponactivity.js" src="${ctx}/js/require.js"></script>
</body>
</html>
