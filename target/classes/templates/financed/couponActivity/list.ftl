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
    <form action="${ctx}/financed/couponActivity/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">活动名称</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value="${(BaseRq.activityName)!}" name="activityName" id="activityName">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px">优惠券活动状态</label>
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
                <a data-type='lookdialog' data-url="${ctx}/financed/couponActivity/editform.php"  class="layui-btn layui-btn-normal fr">新增优惠券活动</a>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>优惠活动名称</th>
                    <th>优惠券</th>
                    <th>优惠券数量</th>
                    <th>已领取数量</th>
                    <th>已使用数量</th>
                    <th>已产生优惠总额</th>
                    <th>有效期</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if couponActivityRs??>
                    <#list couponActivityRs as couponActivity>
                    <tr>
                        <td>${BaseRq.offset + couponActivity_index + 1}</td>
                        <td>
                            <a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/financed/couponActivity/editform.php?id=${couponActivity.id!}&lookType=0" class="pn-opt">${(couponActivity.activityName)!}</a>
                        </td>
                        <td>
                            <a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/financed/coupon/editform.php?id=${couponActivity.couponId!}&lookType=0" class="pn-opt">${(couponActivity.couponName)!}</a>
                        </td>
                        <td>
                        ${(couponActivity.couponNum?c)!}
                        </td>
                        <td>
                            <a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/financed/couponActivity/getOrUseLog.php?activityId=${(couponActivity.id)!}" class="pn-opt">
                        ${(couponActivity.collectNum?c)!}
                                </a>
                        </td>
                        <td>
                            <a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/financed/couponActivity/getOrUseLog.php?activityId=${(couponActivity.id)!}&&status=2" class="pn-opt">
                        ${(couponActivity.usedNum?c)!}
                                </a>
                        </td>
                        <td>
                            <a class="ljc_00bcd4" data-type='lookdialog2' data-url="${ctx}/financed/couponActivity/getOrUseLog.php?activityId=${(couponActivity.id)!}&&status=2" class="pn-opt">
                                <#if couponActivity.usedAmount??>${(couponActivity.usedAmount?c)!}元</#if>
                                </a>
                        </td>
                        <td>${(couponActivity.activityStartTime?string("yyyy-MM-dd"))!}&nbsp;至&nbsp;${(couponActivity.activityEndTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <#if couponStatus?? && ( couponStatus?size gt 0 )>
            				   <#list couponStatus as couponSta>
                                <#if couponActivity.status??&&couponActivity.status == couponSta.fieldValue>${couponSta.fieldKey}</#if>
                               </#list>
            			    </#if>
                        </td>
                        <td>
                            <!--0-删除 1-草稿 2-启用 3-停用-->
                            <#--<a data-type='lookdialog2' data-url="${ctx}/financed/couponActivity/editform.php?id=${couponActivity.id!}&lookType=0" class="pn-opt">查看</a>-->
                            <#if couponActivity.status??&&couponActivity.status == '1'>
                                <a data-type='lookdialog' data-url="${ctx}/financed/couponActivity/editform.php?id=${couponActivity.id!}&lookType=1" class="pn-opt">修改</a> &nbsp;|
                                <a data-href="${ctx}/financed/coupon/activity/qyorty/${(couponActivity.id)!}?status=2" type="POST"
                                   data-type="delSig"  data-confirm="确认启用优惠券活动?"data-okMsg='启用成功!'data-failMsg="启用失败"  data-okMsg='发送成功!'class="pn-opt">启用</a>&nbsp;|
                                <a data-href="${ctx}/financed/coupon/activity/detele/${(couponActivity.id)!}" type="POST"
                                   data-type="delSig"  data-confirm="确认删除该优惠券活动?" data-okMsg='删除成功!'data-failMsg="删除失败" class="pn-opt">删除</a>
                            <#elseif couponActivity.status??&&couponActivity.status == '2'>
                                <a data-type='lookdialog2' data-url="${ctx}/financed/couponActivity/editform.php?id=${couponActivity.id!}&lookType=0" class="pn-opt">查看</a>&nbsp;|
                                <a data-href="${ctx}/financed/coupon/activity/qyorty/${(couponActivity.id)!}?status=3" type="POST"
                                          data-type="delSig"  data-confirm="确认停用优惠券活动?" data-okMsg='停用成功!'data-failMsg="停用失败" data-okMsg='发送成功!'class="pn-opt">停用</a>
                            <#elseif couponActivity.status??&&couponActivity.status == '3'>
                                <a data-href="${ctx}/financed/coupon/activity/qyorty/${(couponActivity.id)!}?status=2" type="POST"
                                          data-type="delSig"  data-confirm="确认启用优惠券活动?"data-okMsg='启用成功!'data-failMsg="启用失败"  data-okMsg='发送成功!'class="pn-opt">启用</a>&nbsp;|
                                <a data-type='lookdialog' data-url="${ctx}/financed/couponActivity/editform.php?id=${couponActivity.id!}&lookType=1" class="pn-opt">修改</a> &nbsp;|
                                <a data-href="${ctx}/financed/coupon/activity/detele/${(couponActivity.id)!}" type="POST"
                                   data-type="delSig"  data-confirm="确认删除优惠券活动?" data-okMsg='删除成功!'data-failMsg="删除失败" data-okMsg='发送成功!' class="pn-opt">删除</a>
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
    <div class="modal-dialog modal-lg" role="document" style="width: 95%">
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
    <div class="modal-dialog modal-lg" role="document" style="width: 90%;top:0px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">查看详情</h4>
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
<script data-main="${ctx}/js/abc/financed/couponactivity.js" src="${ctx}/js/require.js"></script>
</body>
</html>
