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
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/tagEditor/jquery.tagsinput.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
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
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="consumer_edit" action="${ctx}/financed/coupon/editform.php" next-url="${ctx}/financed/coupon/list.php"
          method="post" class="layui-form">
        <table class="layui-table">
            <!--查看页面-->
        <#if couponRs??&&lookType??&&lookType == "0">
            <input type="hidden" id="taskId" name="id" value="${couponRs.id!}">
            <tr>
                <td width="100">优惠券名称：</td>
                <td colspan="3">
                ${couponRs.couponName!}
                </td>
            </tr>
            <tr>
                <td width="100">优惠模式：</td>
                <td colspan="3">
                    <#if couponMode?? && (couponMode?size > 0) >
                        <#list couponMode as mode>
                        <#if couponRs.couponMode??&&couponRs.couponMode == mode.fieldValue>${mode.fieldKey}</#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="100">优惠适用品类：</td>
                <td colspan="3" id="names">
                <#--${couponRs.categoryIds!}-->
                    <input type="hidden" id="parentId" name="parentId" class="layui-input" style="width:330px;" value="${couponRs.categoryIds!}" >
                </td>
            </tr>
            <tr>
                <td width="100">优惠类型：</td>
                <td colspan="3">
                    <#if couponType?? && (couponType?size > 0) >
                        <#list couponType as type>
                            <#if couponRs.couponType??&&couponRs.couponType == type.fieldValue>${type.fieldKey}</#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="100">
                    <#if couponRs.couponType??&&couponRs.couponType =="MANJIAN">
                        满减设置：
                    <#elseif couponRs.couponType??&&couponRs.couponType =="LIJIAN">
                        立减设置：
                    <#elseif couponRs.couponType??&&couponRs.couponType =="ZHEKOU">
                        折扣设置：
                    </#if>
                </td>
                <td colspan="3">
                    <#if couponRs.couponType??&&couponRs.couponType =="MANJIAN">
                        满&nbsp;${couponRs.param1!}&nbsp;元&nbsp;减&nbsp;${couponRs.param2!}&nbsp;<#if couponRs.couponMode??&&couponRs.couponMode == "FLOAT">至&nbsp;${couponRs.param3!}</#if>&nbsp;元
                    <#elseif couponRs.couponType??&&couponRs.couponType =="LIJIAN">
                        减&nbsp;${couponRs.param2!}&nbsp;<#if couponRs.couponMode??&&couponRs.couponMode == "FLOAT">至&nbsp;${couponRs.param3!}</#if>&nbsp;元
                    <#elseif couponRs.couponType??&&couponRs.couponType =="ZHEKOU">
                        满&nbsp;${couponRs.param1!}&nbsp;元&nbsp;打${couponRs.param2!}&nbsp;折
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="100">计算金额类型：</td>
                <td colspan="3">
                    <#if couponRs.amountType??&&couponRs.amountType =="ORDER">
                        订单金额
                    <#elseif couponRs.amountType??&&couponRs.amountType =="POSTAGE">
                        邮费金额
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="80">有效期设置：</td>
                <td colspan="3">
                    <#if couponRs.validType??&&couponRs.validType =="PERIOD">
                        固定时间段
                    <#elseif couponRs.validType??&&couponRs.validType =="DAYS">
                        固定天数
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="100">有效期：</td>
                <td colspan="3">
                    <#if couponRs.validType??&&couponRs.validType =="PERIOD">
                    ${couponRs.validStartTime?string("yyyy-MM-dd")!}&nbsp;至&nbsp;${couponRs.validEndTime?string("yyyy-MM-dd")!}
                    <#elseif couponRs.validType??&&couponRs.validType =="DAYS">
                    ${couponRs.validDays!}&nbsp;天
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="80" style="vertical-align: middle">用券规则描述：</td>
                <td colspan="3">
                ${couponRs.description!}
                </td>
            </tr>
        <#elseif couponRs??&&lookType??&&lookType == "1">
            <input type="hidden" id="couponId" name="id" value="${couponRs.id!}">
            <input type="hidden" id="status" name="status" value="${couponRs.status!}">
            <tr>
                <td width="90">优惠券名称：</td>
                <td colspan="3">
                    <input type="text" name="couponName" id="couponName"  value="${couponRs.couponName!}"
                           style=" width:330px;" class="layui-input" ><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="80">优惠模式：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                        <select id="couponMode" name="couponMode"  lay-filter="yhmodal">
                            <#if couponMode?? && (couponMode?size > 0) >
                                <#list couponMode as mode>
                                    <option <#if couponRs.couponMode??&&couponRs.couponMode == mode.fieldValue>selected</#if> value="${mode.fieldValue}">${mode.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">优惠适用品类：</td>
                <td colspan="3">
                    <input type="text" id="parentName" name="categoryIds" readonly  class="layui-input"  style="width:330px;" ><label style='color:red;'>*</label>
                    <input type="hidden" id="parentId" name="parentId"  class="layui-input" style="width:330px;" value="${couponRs.categoryIds!}" >
                </td>
            </tr>
            <tr>
                <td width="80">优惠类型：</td>
                <td colspan="3">
                    <div style=" width:330px;">
            <#if couponRs.couponMode??&&couponRs.couponMode == "FIXED">
                        <select id="couponType" name="couponType"  lay-filter="yhstyle">
                            <#if couponType?? && (couponType?size > 0) >
                                <#list couponType as type>
                                    <option <#if couponRs.couponType??&&couponRs.couponType == type.fieldValue>selected</#if> value="${type.fieldValue}">${type.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                <#elseif couponRs.couponMode??&&couponRs.couponMode == "FLOAT">
                    <select id="couponType" name="couponType"  lay-filter="yhstyle">
                        <#if couponType?? && (couponType?size > 0) >
                            <#list couponType as type>
                            <#if type.fieldValue??&&type.fieldValue !="ZHEKOU">
                                <option <#if couponRs.couponType??&&couponRs.couponType == type.fieldValue>selected</#if> value="${type.fieldValue}">${type.fieldKey}</option>
                            </#if>
                            </#list>
                        </#if>
                    </select>
            </#if>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">
                    <div <#if couponRs.couponType??&&couponRs.couponType !="MANJIAN">hidden</#if> id="mjtitle">
                        满减设置：
                    </div>
                    <div <#if couponRs.couponType??&&couponRs.couponType !="LIJIAN">hidden</#if> id="ljtitle">
                        立减设置：
                    </div>
                    <div <#if couponRs.couponType??&&couponRs.couponType !="ZHEKOU">hidden</#if> id="zktitle">
                        折扣设置：
                    </div>
                </td>
                <td colspan="3">
                    <div <#if couponRs.couponType??&&couponRs.couponType !="MANJIAN">hidden</#if> id="mj">
                        满&nbsp;<input type="text" name="param1" id="mjparam1" value="<#if couponRs.couponType??&&couponRs.couponType == "MANJIAN">${(couponRs.param1?c)!}</#if>"
                                      style=" width:100px;" class="layui-input">&nbsp;元
                        减&nbsp;<input type="text" name="param2" id="mjparam2" value="<#if couponRs.couponType??&&couponRs.couponType == "MANJIAN">${(couponRs.param2?c)!}</#if>"
                                      style=" width:100px;" class="layui-input">
                        <span <#if couponRs.couponMode??&&couponRs.couponMode != "FLOAT">hidden</#if> id="mjfdje">至&nbsp;<input type="text" name="param3" id="mjparam3" value="<#if couponRs.couponMode??&&couponRs.couponMode == "FLOAT"&&couponRs.couponType??&&couponRs.couponType =="MANJIAN">${(couponRs.param3?c)!}</#if>"
                                                              style=" width:100px;" class="layui-input"></span>&nbsp;元<label style='color:red;'>*</label>
                    </div>
                    <div <#if couponRs.couponType??&&couponRs.couponType !="LIJIAN">hidden</#if> id="lj">
                        减&nbsp;<input type="text" name="param2" id="ljparam2" value="<#if couponRs.couponType??&&couponRs.couponType == "LIJIAN">${(couponRs.param2?c)!}</#if>"
                                      style=" width:100px;" class="layui-input">&nbsp;
                    <span id="ljfdje" <#if couponRs.couponMode??&&couponRs.couponMode != "FLOAT">hidden</#if>>至&nbsp;<input type="text" name="param3" id="ljparam3" value="<#if couponRs.couponMode??&&couponRs.couponMode == "FLOAT"&&couponRs.couponType??&&couponRs.couponType =="LIJIAN">${(couponRs.param3?c)!}</#if>"
                                                           style=" width:100px;" class="layui-input"></span>元<label style='color:red;'>*</label>
                    </div>
                    <div <#if couponRs.couponType??&&couponRs.couponType !="ZHEKOU">hidden</#if> id="zk">
                        满&nbsp;<input type="text" name="param1" id="zkparam1"value="<#if couponRs.couponType??&&couponRs.couponType == "ZHEKOU">${(couponRs.param1?c)!}</#if>"
                                      style=" width:100px;" class="layui-input">&nbsp;元
                        打&nbsp;<input type="text" name="param2" id="zkparam2" value="<#if couponRs.couponType??&&couponRs.couponType == "ZHEKOU">${(couponRs.param2)!}</#if>"
                               style=" width:100px;" class="layui-input">&nbsp;折<label style='color:red;'>*</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">计算金额类型：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                        <select name="amountType" id="amountType">
                            <option <#if couponRs.amountType??&&couponRs.amountType == "ORDER">selected</#if> value="ORDER">订单金额</option>
                            <option <#if couponRs.amountType??&&couponRs.amountType == "POSTAGE">selected</#if> value="POSTAGE">邮费金额</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">有效期设置：</td>
                <td colspan="3">
                    <input id="validType1" <#if couponRs.validType??&&couponRs.validType == "PERIOD">checked</#if> name="validType" lay-filter="datetype" type="radio" value="PERIOD" title="固定时间段"
                           checked>
                    <input id="validType2" <#if couponRs.validType??&&couponRs.validType == "DAYS">checked</#if> name="validType" lay-filter="datetype" type="radio" value="DAYS" title="固定天数">
                </td>
            </tr>
            <tr>
                <td width="80">有效期</td>
                <td colspan="3">
                    <div <#if couponRs.validType??&&couponRs.validType != "PERIOD">hidden</#if> id="gdsd">
                        <input type="text" class="layui-input" id="test5" name="validStartTime"value="<#if couponRs.validType??&&couponRs.validType == "PERIOD">${(couponRs.validStartTime?string("yyyy-MM-dd"))!}</#if>"
                               style="width: 160px">&nbsp;至&nbsp;
                        <input type="text" class="layui-input" id="test6" name="validEndTime"value="<#if couponRs.validType??&&couponRs.validType == "PERIOD">${(couponRs.validEndTime?string("yyyy-MM-dd"))!}</#if>" style="width: 160px"><label style='color:red;'>*</label>
                    </div>
                    <div <#if couponRs.validType??&&couponRs.validType != "DAYS">hidden</#if> id="gdts">
                        <input type="text" class="layui-input" name="validDays" id="validDays"value="<#if couponRs.validType??&&couponRs.validType == "DAYS">${couponRs.validDays!}</#if>"
                               style="width: 100px">&nbsp;天<label style='color:red;'>*</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80" style="vertical-align: middle">用券规则描述：</td>
                <td colspan="3">
                    <textarea class="layui-textarea" name="description" id="description"value="${couponRs.description!}">${couponRs.description!}</textarea>
                </td>
            </tr>
        <#else>
            <tr>
                <td width="90">优惠券名称：</td>
                <td colspan="3">
                    <input type="text" name="couponName" id="couponName"
                           style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="80">优惠模式：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                        <select id="couponMode" name="couponMode"  lay-filter="yhmodal">
                            <#if couponMode?? && (couponMode?size > 0) >
                                <#list couponMode as mode>
                                    <option value="${mode.fieldValue}">${mode.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">优惠适用品类：</td>
                <td colspan="3">
                    <input type="text" id="parentName" name="categoryIds" readonly  class="layui-input" style="width:330px;" ><label style='color:red;'>*</label>
                    <input type="hidden" id="parentId" name="parentId"  class="layui-input" style="width:330px;" >
                </td>
            </tr>
            <tr>
                <td width="80">优惠类型：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                        <select id="couponType" name="couponType"  lay-filter="yhstyle">
                            <#if couponType?? && (couponType?size > 0) >
                                <#list couponType as type>
                                    <option value="${type.fieldValue}">${type.fieldKey}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">
                    <div id="mjtitle">
                        满减设置：
                    </div>
                    <div id="ljtitle" hidden>
                        立减设置：
                    </div>
                    <div id="zktitle" hidden>
                        折扣设置：
                    </div>
                </td>
                <td colspan="3">
                    <div id="mj">
                        满&nbsp;<input type="text" name="param1" id="mjparam1"
                                      style=" width:100px;" class="layui-input">&nbsp;元
                        减&nbsp;<input type="text" name="param2" id="mjparam2"
                                      style=" width:100px;" class="layui-input">&nbsp;
                        <div id="mjfdje" hidden>至&nbsp;<input type="text" name="param3" id="mjparam3"
                                                              style=" width:100px;" class="layui-input"></div>&nbsp;元<label style='color:red;'>*</label>
                    </div>
                    <div id="lj" hidden>
                        减&nbsp;<input type="text" name="param2" id="ljparam2"
                                      style=" width:100px;" class="layui-input">&nbsp;
                    <span id="ljfdje" hidden>至&nbsp;<input type="text" name="param3" id="ljparam3"
                                                           style=" width:100px;" class="layui-input"></span>元<label style='color:red;'>*</label>
                    </div>
                    <div id="zk" hidden>
                        满&nbsp;<input type="text" name="param1" id="zkparam1"
                                      style=" width:100px;" class="layui-input">&nbsp;元
                        打&nbsp;<input type="text" name="param2" id="zkparam2"
                               style=" width:100px;" class="layui-input">&nbsp;折<label style='color:red;'>*</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">计算金额类型：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                    <select name="amountType" id="amountType">
                        <option value="ORDER">订单金额</option>
                        <option value="POSTAGE">邮费金额</option>
                    </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80">有效期设置：</td>
                <td colspan="3">
                    <input id="validType1" name="validType" lay-filter="datetype" type="radio" value="PERIOD" title="固定时间段"
                           checked>
                    <input id="validType2"  name="validType" lay-filter="datetype" type="radio" value="DAYS" title="固定天数">
                </td>
            </tr>
            <tr>
                <td width="80">有效期</td>
                <td colspan="3">
                    <div id="gdsd">
                        <input type="text" class="layui-input" id="test5" name="validStartTime"
                               style="width: 160px">&nbsp;至&nbsp;
                        <input type="text" class="layui-input" id="test6" name="validEndTime"  style="width: 160px"><label style='color:red;'>*</label>
                    </div>
                    <div id="gdts" hidden>
                        <input type="text" class="layui-input" name="validDays" id="validDays"
                               style="width: 100px">&nbsp;天<label style='color:red;'>*</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="80" style="vertical-align: middle">用券规则描述：</td>
                <td colspan="3">
                    <textarea class="layui-textarea" name="description" id="description"></textarea>
                </td>
            </tr>

        </#if>
        </table>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame" style="width: 100%;height: 500px;border:0" border="0"
                        frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-diss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">商品分类选择
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <button type="button" class="js_save_area_btn layui-btn">确认</button>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/financed/coupon.js" src="${ctx}/js/require.js"></script>
</html>
