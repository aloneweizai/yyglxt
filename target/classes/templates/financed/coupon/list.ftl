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
    <form action="${ctx}/financed/coupon/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">优惠模式</label>
                    <div class="layui-input-inline">
                        <select name="couponMode" id="couponMode">
                            <option value=""></option>
                        <#if couponMode?? && ( couponMode?size gt 0 )>
                            <#list couponMode as mode>
                                <option
                                    <#if BaseRq.couponMode ?? && (BaseRq.couponMode == mode.fieldValue)>selected</#if>
                                    value="${mode.fieldValue}">${mode.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">优惠类型</label>
                    <div class="layui-input-inline">
                        <select name="couponType" id="couponType">
                            <option value=""></option>
                        <#if couponType?? && ( couponType?size gt 0 )>
                            <#list couponType as type>
                                <option
                                    <#if BaseRq.couponType ?? && (BaseRq.couponType == type.fieldValue)>selected</#if>
                                    value="${type.fieldValue}">${type.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">优惠券状态</label>
                    <div class="layui-input-inline">
                        <select name="status" id="status">
                            <option value=""></option>
                        <#if couponStatus?? && ( couponStatus?size gt 0 )>
                            <#list couponStatus as couponSta>
                            <#if couponSta.fieldValue??&&couponSta.fieldValue != "0">
                                <option
                                    <#if BaseRq.status ?? && (BaseRq.status == couponSta.fieldValue)>selected</#if>
                                    value="${couponSta.fieldValue}">${couponSta.fieldKey}</option>
                            </#if>
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
                <a data-type='lookdialog' data-url="${ctx}/financed/coupon/editform.php"  class="layui-btn layui-btn-normal fr">新增优惠券</a>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <#--<th>优惠券ID</th>-->
                    <th>优惠券名称</th>
                    <th>优惠方式</th>
                    <th>优惠类型</th>
                    <th>有效期</th>
                    <th>计算金额类型</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if couponRs??>
                    <#list couponRs as coupon>
                    <tr>
                        <td>${BaseRq.offset + coupon_index + 1}</td>
                        <#--<td>${(coupon.id)!}</td>-->
                        <td>
                            <a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/financed/coupon/editform.php?id=${coupon.id!}&lookType=0" class="pn-opt">${(coupon.couponName)!}</a>
                        </td>
                        <td>
                            <#if couponMode?? && ( couponMode?size gt 0 )>
            				   <#list couponMode as mode>
                                    <#if coupon.couponMode??&&coupon.couponMode == mode.fieldValue>${mode.fieldKey}</#if>
                                </#list>
            			    </#if>
                        </td>
                        <td>
                            <#if couponType?? && ( couponType?size gt 0 )>
            				   <#list couponType as type>
                                <#if coupon.couponType??&&coupon.couponType == type.fieldValue>${type.fieldKey}</#if>
                               </#list>
            			    </#if>
                        </td>
                        <td>
                        <#if coupon.validType?? && coupon.validType == "PERIOD" >
                        ${(coupon.validStartTime?string("yyyy-MM-dd"))!}&nbsp;至&nbsp;${(coupon.validEndTime?string("yyyy-MM-dd"))!}
                        <#elseif coupon.validType?? && coupon.validType == "DAYS">
                        ${(coupon.validDays)!}&nbsp;天
                        </#if>
                        </td>
                        <td>
                            <#if coupon.amountType?? && coupon.amountType == "ORDER" >
                                订单金额
                            <#elseif coupon.amountType?? && coupon.amountType == "POSTAGE">
                                邮费金额
                            </#if>
                        </td>
                        <td>
                            <#if couponStatus?? && ( couponStatus?size gt 0 )>
            				   <#list couponStatus as couponSta>
                                <#if coupon.status??&&coupon.status == couponSta.fieldValue>${couponSta.fieldKey}</#if>
                            </#list>
            			    </#if>
                        </td>
                        <td>
                            <!--0-删除 1-草稿 2-启用 3-停用-->
                            <#--<a data-type='lookdialog2' data-url="${ctx}/financed/coupon/editform.php?id=${coupon.id!}&lookType=0" class="pn-opt">查看</a>-->
                            <#if coupon.status??&&coupon.status == '1'>
                               <a data-type='lookdialog' data-url="${ctx}/financed/coupon/editform.php?id=${coupon.id!}&lookType=1" class="pn-opt">修改</a> &nbsp;|
                                <a data-href="${ctx}/financed/coupon/qyorty/${(coupon.id)!}?status=2" type="POST"
                                   data-type="delSig"  data-confirm="确认启用优惠券?"data-okMsg='启用成功!'data-failMsg="启用失败"  data-okMsg='发送成功!'class="pn-opt">启用</a>&nbsp;|
                                <a data-href="${ctx}/financed/coupon/detele/${(coupon.id)!}" type="POST"
                                    data-type="delSig"  data-confirm="确认删除该优惠券?" data-okMsg='删除成功!'data-failMsg="删除失败" class="pn-opt">删除</a>
                            <#elseif coupon.status??&&coupon.status == '2'>
                                <a data-type='lookdialog2' data-url="${ctx}/financed/coupon/editform.php?id=${coupon.id!}&lookType=0" class="pn-opt">查看</a>&nbsp;|
                                <a data-href="${ctx}/financed/coupon/qyorty/${(coupon.id)!}?status=3" type="POST"
                                    data-type="delSig"  data-confirm="确认停用优惠券?" data-okMsg='停用成功!'data-failMsg="停用失败" data-okMsg='发送成功!'class="pn-opt">停用</a>
                            <#elseif coupon.status??&&coupon.status == '3'>
                                <a data-href="${ctx}/financed/coupon/qyorty/${(coupon.id)!}?status=2" type="POST"
                                    data-type="delSig"  data-confirm="确认启用优惠券?"data-okMsg='启用成功!'data-failMsg="启用失败"  data-okMsg='发送成功!'class="pn-opt">启用</a>&nbsp;|
                                <a data-type='lookdialog' data-url="${ctx}/financed/coupon/editform.php?id=${coupon.id!}&lookType=1" class="pn-opt">修改</a> &nbsp;|
                                <a data-href="${ctx}/financed/coupon/detele/${(coupon.id)!}" type="POST"
                                   data-type="delSig"  data-confirm="确认删除优惠券?" data-okMsg='删除成功!'data-failMsg="删除失败" data-okMsg='发送成功!' class="pn-opt">删除</a>
                            </#if>
                            </td>
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
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-normal" id="editbtn" data-val="1" data-tj="modal">保存</button>
                <button type="button" class="layui-btn layui-btn-normal" id="editbtn" data-val="2" data-tj="modal">保存并启用</button>
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="main_modal container-fluid" id="lookModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">优惠券详情</h4>
            </div>
            <div class="modal-body">
                <iframe id="look_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dis="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/financed/coupon.js" src="${ctx}/js/require.js"></script>
</body>
</html>
