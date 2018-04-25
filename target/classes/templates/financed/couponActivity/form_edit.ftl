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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/page_edit.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
        var imgurl="${imgPth!}";
        var editor;
    </script>
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }
        a{color:blue;}
        ul, ol {margin-top: 0;margin-bottom: 10px;}
        .tag-list__itemheader{ margin: 0; line-height: 32px; font-weight: bold; font-size: 14px; color: #333;  border-bottom: 1px solid #EEE;}
        .tag-list__itembody {padding: 20px 0;margin-bottom: 0;}
        .taglist--inline{list-style: none;padding: 0;font-size: 0;margin-top: 2}
        .tag {display: inline-block;padding: 0 8px;color: #017E66;background-color: rgba(1,126,102,0.08);line-height: 24px;font-weight: normal;font-size: 16px;text-align: center;}
        a, a:hover, a:active, a:focus {outline: 0;}
        .tag-list__itemWraper {margin-bottom: 5px;float:left;margin-right:2%;height:auto;width: 160px}
        .tag:hover, .tag:focus {background-color: #017E66;color: #fff;text-decoration: none;}
        .taglist--inline.multi>li { margin-bottom: 5px;}.taglist--inline>li {display: inline-block;margin-right: 3px;}
        .taglist--inline li, .taglist--block li {padding: 0;font-size: 13px;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="consumer_edit" action="${ctx}/cszjs/yyMsgSend/save.php" next-url="${ctx}/cszjs/yyMsgSend/list.php"
          method="post" class="layui-form">
        <table class="layui-table">
            <!--查看页面-->
        <#if couponActivityRs??&&lookType??&&lookType == "0">
            <input type="hidden" id="taskId" name="id" value="${couponActivityRs.id!}">
            <tr>
                <td width="120px">优惠券活动名称：</td>
                <td colspan="3">
                ${couponActivityRs.activityName!}
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券：</td>
                <td colspan="3">
                ${couponActivityRs.couponName!}
                </td>
            </tr>
            <tr>
                <td width="120px">活动有效期：</td>
                <td colspan="3">
                ${couponActivityRs.activityStartTime?string("yyyy-MM-dd")!}&nbsp;至&nbsp;${couponActivityRs.activityEndTime?string("yyyy-MM-dd")!}
                </td>
            </tr>
            <tr>
                <td width="120px">发放目标人群：</td>
                <td colspan="3">
                    <#if couponActivityRs.target??&&couponActivityRs.target =="1">
                        全部用户
                    <#elseif couponActivityRs.target??&&couponActivityRs.target =="2">
                        部分用户
                    <#elseif couponActivityRs.target??&&couponActivityRs.target =="3">
                        特定用户
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr <#if couponActivityRs.target??&&couponActivityRs.target != "2">hidden</#if> id="bfyh">
                            <td style="width: 760px">
                                <table style="width: 100%">
                                    <caption>部分用户筛选条件</caption>
                                    <tr>
                                        <td width="60" align="right">区域：</td>
                                        <td>
                                            <div class="layui-inline" style="width: 90px">
                                                <#if couponActivityRs.areaOper??&&couponActivityRs.areaOper == "equals">
                                                    等于
                                                <#elseif couponActivityRs.areaOper??&&couponActivityRs.areaOper == "ne">
                                                    不等于
                                                </#if>
                                            </div>
                                            <div class="layui-inline">
                                                <div id="areaNames"></div>
                                                <input id="areaIds" name="areaIds" style=" width:400px;" type="hidden"
                                                       value="${couponActivityRs.areaIds!}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">标签：</td>
                                        <td>
                                            <span id="tagnum" data-num="0" hidden></span>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <#if couponActivityRs.tagOper??&&couponActivityRs.tagOper == "equals">
                                                    等于
                                                <#elseif couponActivityRs.tagOper??&&couponActivityRs.tagOper == "ne">
                                                    不等于
                                                </#if>
                                            </div>
                                            <div class="layui-inline">
                                            ${(couponActivityRs.tagName)!}
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">注册时间：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <#if couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "lte">
                                                    小于等于
                                                <#elseif couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "gte">
                                                    大于等于
                                                </#if>
                                            </div>
                                            <div class="layui-inline">
                                                <#if couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "lte">
                                                ${(couponActivityRs.regEndTime?string("yyyy-MM-dd"))!}
                                                <#elseif couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "gte">
                                                ${(couponActivityRs.regStartTime?string("yyyy-MM-dd"))!}
                                                </#if>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">用户类型：</td>
                                        <td>
                                        ${(couponActivityRs.levels)!}
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="tdyh" <#if couponActivityRs.target??&&couponActivityRs.target != "3">hidden</#if>>
                            <td style="width: 760px">
                                <table style="width: 100%">
                                    <caption>特定用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">用户ID：</td>
                                        <td>
                                            <div class="layui-inline">
                                            ${couponActivityRs.userName!}
                                            </div>

                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券活动链接：</td>
                <td colspan="3">
                ${couponActivityRs.activityLink!}
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券发放数量：</td>
                <td colspan="3">
                ${(couponActivityRs.couponNum?c)!}&nbsp;张
                </td>
            </tr>
            <tr>
                <td width="120px">领取数量限制：</td>
                <td colspan="3">
                    <#if couponActivityRs.limit??&&couponActivityRs.limit>
                        每人限领&nbsp;${(couponActivityRs.limitNum?c)!}&nbsp;张
                    <#else>
                        不限制
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="120px">领取方式：</td>
                <td colspan="3">
                    <#if couponActivityRs.getType??&&couponActivityRs.getType =="SYSTEM">
                        系统派发
                    <#elseif couponActivityRs.getType??&&couponActivityRs.getType =="USER">
                        用户手动领取
                    </#if>
                </td>
            </tr>
            <tr>
                <td width="120px">领取按钮外部校验接口：</td>
                <td colspan="3">
                    <#if couponActivityRs.valid??&&couponActivityRs.valid>
                        校验外部业务领取条件
                    <#else>
                        不校验外部业务领取条件
                    </#if>
                    &nbsp;${couponActivityRs.validApi!}
                </td>
            </tr>
            <tr>
                <td width="120px">活动图片：</td>
                <td colspan="3">
            <#if couponActivityRs.imageUrl??&&couponActivityRs.imageUrl!="">
                    <img height='126' width='206' style='margin-left:10px;max-width:206px' src='${imgPth!}${(couponActivityRs.imageUrl)!}' />
            </#if>
                </td>
            </tr>
            <tr>
                <td>活动规则描述:</td>
                <td colspan="3">
                ${couponActivityRs.description!}
                </td>
            </tr>
        <#elseif couponActivityRs??&&lookType??&&lookType == "1">
            <input type="hidden" id="activityId" name="id" value="${couponActivityRs.id!}">
            <input type="hidden" id="status" name="status" value="${couponActivityRs.status!}">
            <tr>
                <td width="120px">优惠券活动名称：</td>
                <td colspan="3">
                    <input type="text" name="activityName" id="activityName"  value="${couponActivityRs.activityName!}"
                           style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120px">选择优惠券：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                        <select name="couponId" id="couponId" lay-filter="couponId"lay-search="">
                            <option value="">直接选择或搜索选择</option>
                            <#if couponRs?? && ( couponRs?size gt 0 )>
                                <#list couponRs as coupon>
                                    <option
                                        <#if couponActivityRs.couponId??&&couponActivityRs.couponId == coupon.id > selected</#if>
                                   value="${coupon.id}" data-type="${(coupon.validType)!}" data-start="${(coupon.validStartTime?string("yyyy-MM-dd"))!}" data-end="${(coupon.validEndTime?string("yyyy-MM-dd"))!}">${coupon.couponName}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="120px">活动有效期：</td>
                <td colspan="3">
                    <input id="start" name="start" style=" width:330px;" type="hidden" value="${(couponActivityRs.start)!}">
                    <input id="end" name="end" style=" width:330px;" type="hidden"  value="${(couponActivityRs.end)!}">
                    <input id="validType" name="validType" style=" width:330px;" type="hidden"  value="${(couponActivityRs.validType)!}">
                    <input type="text" class="layui-input" id="test5"  name="startTime" value="${(couponActivityRs.activityStartTime?string("yyyy-MM-dd"))!}"
                           style="width: 160px">&nbsp;至&nbsp;
                    <input type="text" class="layui-input" id="test6"  name="endTime" value="${(couponActivityRs.activityEndTime?string("yyyy-MM-dd"))!}" style="width: 160px"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="80">发放目标人群：</td>
                <td colspan="3">
                    <input id="target1" <#if couponActivityRs.target??&&couponActivityRs.target == "1">checked</#if> name="target"
                           type="radio" lay-filter="target" value="1" title="全部用户">
                    <input id="target2" lay-filter="target"
                           <#if couponActivityRs.target??&&couponActivityRs.target == "2">checked</#if> name="target"
                           type="radio" value="2" title="部分用户">
                    <input id="target3" lay-filter="target"
                           <#if couponActivityRs.target??&&couponActivityRs.target == "3">checked</#if> name="target"
                           type="radio" value="3" title="特定用户">
                </td>
            </tr>
            <tr>
                <td width="80"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr id="bfyh" <#if couponActivityRs.target??&&couponActivityRs.target != "2">hidden</#if> >
                            <td style="width: 760px">
                                <table style="width: 100%">
                                    <caption>部分用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">区域：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="areaOper" id="areaOper" lay-filter="areaOper">
                                                    <option value=""></option>
                                                    <option
                                                        <#if couponActivityRs.areaOper??&&couponActivityRs.areaOper == "equals">selected</#if>
                                                        value="equals">等于
                                                    </option>
                                                    <option
                                                        <#if couponActivityRs.areaOper??&&couponActivityRs.areaOper == "ne">selected</#if>
                                                        value="ne">不等于
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                <textarea id="areaName" name="areaName" type="text" style=" width:400px;height: 45px"
                                          readonly="readonly"></textarea>
                                                <input id="areaIds" name="areaIds" style=" width:330px;" type="hidden"
                                                       value="${couponActivityRs.areaIds!}">
                                                <span id="areanum" style="font-size: smaller">0/20</span>
                                            </div>
                                            <#if lookType??&&lookType=="1">
                                            <a id="areadw" href=""><div id="areaslect" class="layui-btn">选择</div></a>
                                            </#if>
                                        <#--<input type="text" value="${yyMsgSendRs.areaIds!}" id="areaIds" name="areaIds" data-rule="length[2~50]" style=" width:330px;"  class="layui-input">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">标签：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="tagOper" id="tagOper" lay-filter="tagOper">
                                                    <option value=""></option>
                                                    <option
                                                        <#if couponActivityRs.tagOper??&&couponActivityRs.tagOper == "equals">selected</#if>
                                                        value="equals">等于
                                                    </option>
                                                    <option
                                                        <#if couponActivityRs.tagOper??&&couponActivityRs.tagOper == "ne">selected</#if>
                                                        value="ne">不等于
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                                <input type="text" name="tagName" id="tagnames"
                                                       value="${(couponActivityRs.tagName)!}"
                                                       placeholder="请搜索选择标签" class="layui-input">
                                            </div>
                                            <#if lookType??&&lookType=="1">
                                                <span id="tagnum" data-num="${couponActivityRs.tagnum!}" style="font-size: smaller"><#if couponActivityRs.tagnum??>${couponActivityRs.tagnum!}/10<#else>0/10</#if></span>
                                                <div id="tagslect" class="layui-btn">选择</div>
                                            </#if>
                                        <#-- <div id="tagdelall" class="layui-btn layui-btn-danger">清空</div>-->
                                        <#--<input type="text" id="tagIds" value="${yyMsgSendRs.tagIds!}" name="tagIds" data-rule="length[2~50]" style=" width:330px;"  class="layui-input">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">注册时间：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="regTimeOper" id="regTimeOper" lay-filter="regTimeOper"
                                                        value="${couponActivityRs.regTimeOper!}">
                                                    <option value=""></option>
                                                    <option
                                                        <#if couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "lte">selected</#if>
                                                        value="lte">小于等于
                                                    </option>
                                                    <option
                                                        <#if couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "gte">selected</#if>
                                                        value="gte">大于等于
                                                    </option>
                                                </select>
                                            </div>
                                            <input type="text" class="layui-input" id="test31"
                                                   <#if couponActivityRs.regTimeOper??&&couponActivityRs.regTimeOper == "lte">value="${couponActivityRs.regEndTime?string("yyyy-MM-dd")!}"
                                                   <#else>value="${(couponActivityRs.regStartTime?string("yyyy-MM-dd"))!}" </#if>
                                                   name="regStartTime" style=" width:400px;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">用户类型：</td>
                                        <td>
                                            <div class="layui-inline">
                                                <input type="text" name="level" id="levels"value="${(couponActivityRs.levels)!}"
                                                       placeholder="请搜索选择用户类型" class="layui-input">
                                                <input id="vips" name="vips" style=" width:330px;" value="${(couponActivityRs.vips)!}" type="hidden">
                                            </div>
                                            <div id="levelslect" class="layui-btn">选择</div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="tdyh" <#if couponActivityRs.target??&&couponActivityRs.target != "3">hidden</#if>>
                            <td style="width: 760px">
                                <table style="width: 100%">
                                    <caption>特定用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">用户ID：</td>
                                        <td>
                                            <div class="layui-inline">
                                            <#--<textarea id="userName" name="userName"
                                                      data-url="${ctx}/consumerManager/operate/message/consumerlook.php?userIds=${yyMsgSendRs.userName!}"
                                                      style=" width:420px;height: 45px" class="layui-input" readonly="readonly"
                                                      value="${yyMsgSendRs.userName!}">${yyMsgSendRs.userName!}</textarea>-->
                                                <input type="text" id="userName" name="userName"
                                                       data-url="${ctx}/consumerManager/operate/message/consumerlook.php?userIds=${couponActivityRs.userName!}"
                                                       style=" width:450px;height: 50px" class="layui-input" value="${couponActivityRs.userName!}"
                                                       readonly="readonly"></input>
                                                <input id="userIds" name="userIds" style=" width:330px;" type="hidden"
                                                       value="${couponActivityRs.userIds!}">
                                                <input id="userNames" name="userNames" style=" width:330px;" type="hidden"
                                                       value="${couponActivityRs.userName!}">
                                                <span id="usernum" style="font-size: smaller"><#if couponActivityRs.usernum??>${couponActivityRs.usernum!}/1000<#else>0/1000</#if></span>
                                            </div>
                                            <#if lookType??&&lookType=="1">
                                                <div id="userslect" class="layui-btn" value="${couponActivityRs.userIds!}">选择
                                                </div>
                                            </#if>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券活动链接：</td>
                <td colspan="3">
                <#--  <input type="text" id="urltitle" name="urltitle" placeholder="链接标题" data-rule="length[2~50]"
                         style=" width:100px;" class="layui-input">-->
                    <input type="text" id="activityLink" name="activityLink" value="${couponActivityRs.activityLink!}"
                           placeholder="活动入口页面地址，必须是http://开头" data-rule="url;" style=" width:330px;"
                           class="layui-input">
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券发放数量：</td>
                <td colspan="3">
                    <input type="text" id="couponNum" name="couponNum"value="${(couponActivityRs.couponNum?c)!}"data-rule="required;integer(+);"
                           style=" width:160px;" class="layui-input">&nbsp;张<label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120px">领取数量限制：</td>
                <td colspan="3">
                    <input id="limit1" name="limit" type="radio" <#if couponActivityRs.limit??&&!couponActivityRs.limit>checked</#if> lay-filter="limit" value="false" title="不限制">
                    <input id="limit2" name="limit" type="radio" <#if couponActivityRs.limit??&&couponActivityRs.limit>checked</#if> lay-filter="limit" value="true" title="每人限领">
                    <input type="text" name="limitNum" id="limitNum" value="<#if couponActivityRs.limit??&&couponActivityRs.limit>${couponActivityRs.limitNum!}</#if>"
                           style=" width:50px;" <#if couponActivityRs.limit??&&!couponActivityRs.limit>readonly</#if> class="layui-input"data-rule="integer(+);">&nbsp;张
                </td>
            </tr>
            <tr>
                <td width="120px">领取方式：</td>
                <td colspan="3">
                    <input id="getType1" name="getType" type="radio"<#if couponActivityRs.getType??&&couponActivityRs.getType == 'SYSTEM'>checked</#if> lay-filter="getType" value="SYSTEM" title="系统派发">
                    <input id="getType2" name="getType" type="radio" <#if couponActivityRs.getType??&&couponActivityRs.getType == 'USER'>checked</#if> lay-filter="getType" value="USER" title="用户手动领取">
                </td>
            </tr>
            <tr>
                <td width="120px">领取按钮外部校验接口：</td>
                <td colspan="3">
                    <input id="valid" name="valid" type="checkbox" lay-filter="valid" <#if couponActivityRs.valid??&&couponActivityRs.valid>checked</#if> lay-skin="primary"  title="是否校验外部业务领取条件">
                    <input type="text" name="validApi" id="validApi" value="<#if couponActivityRs.valid??&&couponActivityRs.valid>${couponActivityRs.validApi!}</#if>" style=" width:330px;" class="layui-input">
                </td>
            </tr>
            <tr>
                <td width="120px">活动图片：</td>
                <td colspan="3">
                    <img id="imgshow" height='126' width='206' style='margin-left:10px;max-width:206px' onerror="javascript:this.src='${ctx}/images/default.jpg';" src='${imgPth!}${(couponActivityRs.imageUrl)!}' />
                    <input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><#--<label style='color:red;'>*</label>-->
                    <button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
                    <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                    <input type="hidden" id="imageUrl" name="imageUrl"  value='${(couponActivityRs.imageUrl)!}'>
                    <div style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出1MB（jpg、png、bmp）</div>
                </td>
            </tr>
            <tr>
                <td>活动规则描述:</td>
                <td colspan="3">
                    <div id="_topic_description_area" name="description"  id="description">${couponActivityRs.description!}</div>
                </td>
            </tr>
        <#else>
            <tr>
                <td width="120px">优惠券活动名称：</td>
                <td colspan="3">
                    <input type="text" name="activityName" id="activityName"
                           style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
            </tr>
            <tr>
                <td width="120px">选择优惠券：</td>
                <td colspan="3">
                    <div style=" width:330px;">
                        <select name="couponId" id="couponId" lay-filter="couponId"lay-search="">
                            <option value="">直接选择或搜索选择</option>
                            <#if couponRs?? && ( couponRs?size gt 0 )>
                                <#list couponRs as coupon>
                                    <option value="${coupon.id}" data-type="${(coupon.validType)!}" data-start="${(coupon.validStartTime?string("yyyy-MM-dd"))!}" data-end="${(coupon.validEndTime?string("yyyy-MM-dd"))!}">${coupon.couponName}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="120px">活动有效期：</td>
                <td colspan="3">
                    <input id="start" name="start" style=" width:330px;" type="hidden">
                    <input id="end" name="end" style=" width:330px;" type="hidden">
                    <input id="validType" name="validType" style=" width:330px;" type="hidden">
                    <input type="text" class="layui-input" id="test5" name="startTime"
                           style="width: 160px">&nbsp;至&nbsp;
                    <input type="text" class="layui-input" id="test6" name="endTime" style="width: 160px"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120px">发放目标人群：</td>
                <td colspan="3">
                    <input id="target1" name="target" type="radio" lay-filter="target" value="1" title="全部用户" checked>
                    <input id="target2" name="target" type="radio" lay-filter="target" value="2" title="部分用户">
                    <input id="target3" name="target" type="radio" lay-filter="target" value="3" title="特定用户">
                </td>
            </tr>
            <tr>
                <td width="120px"></td>
                <td colspan="3">
                    <table style="width: 100%">
                        <tr id="bfyh" hidden>
                            <td style="width: 760px">
                                <table style="width: 100%">
                                    <caption>部分用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">区域：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="areaOper" id="areaOper" lay-filter="areaOper">
                                                    <option value=""></option>
                                                    <option value="equals">等于</option>
                                                    <option value="ne">不等于</option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                            <textarea id="areaName" name="areaName" type="text"
                                                      style=" width:400px;height: 45px"
                                                      readonly="readonly"></textarea>
                                                <input id="areaIds" name="areaIds" style=" width:330px;" type="hidden">
                                                <span id="areanum" style="font-size: smaller">0/20</span>
                                            </div>
                                            <a id="areadw" href=""><div id="areaslect" class="layui-btn">选择</div></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">标签：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="tagOper" id="tagOper" lay-filter="tagOper">
                                                    <option value=""></option>
                                                    <option value="equals">等于</option>
                                                    <option value="ne">不等于</option>
                                                </select>
                                            </div>
                                            <div class="layui-inline">
                                                <input type="text" name="tagName" id="tagnames"
                                                       value="${(BaseRq.tagName)!}"
                                                       placeholder="请搜索选择标签" class="layui-input">
                                            </div>
                                            <span id="tagnum" data-num="0" style="font-size: smaller">0/10</span>
                                            <div id="tagslect" class="layui-btn">选择</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">注册时间：</td>
                                        <td>
                                            <div class="layui-input-inline" style="width: 90px">
                                                <select name="regTimeOper" id="regTimeOper" lay-filter="regTimeOper">
                                                    <option value=""></option>
                                                    <option value="lte">小于等于</option>
                                                    <option value="gte">大于等于</option>
                                                </select>
                                            </div>
                                            <input type="text" class="layui-input" id="test31" name="regStartTime"
                                                   style=" width:400px;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="80" align="right">用户类型：</td>
                                        <td>
                                            <div class="layui-inline">
                                                <input type="text" name="level" id="levels"
                                                       placeholder="请搜索选择用户类型" class="layui-input">
                                                <input id="vips" name="vips" style=" width:330px;" type="hidden">
                                            </div>
                                            <div id="levelslect" class="layui-btn">选择</div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="tdyh" hidden>
                            <td style="width: 760px">
                                <table style="width: 100%">
                                    <caption>特定用户筛选条件</caption>
                                    <tr>
                                        <td width="80" align="right">用户ID：</td>
                                        <td>
                                            <div class="layui-inline">
                                                <input type="text" id="userName" name="userName"
                                                       data-url="${ctx}/consumerManager/operate/message/consumerlook.php"
                                                       style=" width:450px;height: 50px" class="layui-input"
                                                       readonly="readonly"></input>
                                                <input id="userIds" name="userIds" style=" width:330px;" type="hidden">
                                                <input id="userNames" name="userNames" style=" width:330px;" type="hidden">
                                                <span id="usernum" style="font-size: smaller">0/1000</span>
                                            </div>
                                            <div id="userslect" class="layui-btn">选择</div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券活动链接：</td>
                <td colspan="3">
                  <#--  <input type="text" id="urltitle" name="urltitle" placeholder="链接标题" data-rule="length[2~50]"
                           style=" width:100px;" class="layui-input">-->
                    <input type="text" id="activityLink" name="activityLink"
                           placeholder="活动入口页面地址，必须是http://开头" data-rule="url;" style=" width:330px;"
                           class="layui-input">
                </td>
            </tr>
            <tr>
                <td width="120px">优惠券发放数量：</td>
                <td colspan="3">
                    <input type="text" id="couponNum" name="couponNum" data-rule="required;integer(+);"
                           style=" width:160px;" class="layui-input">&nbsp;张<label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="120px">领取数量限制：</td>
                <td colspan="3">
                    <input id="limit1" name="limit" type="radio" lay-filter="limit" value="false" title="不限制" checked>
                    <input id="limit2" name="limit" type="radio" lay-filter="limit" value="true" title="每人限领">
                    <input type="text" name="limitNum" id="limitNum"data-rule="integer(+);"
                           style=" width:50px;" class="layui-input" readonly="readonly">&nbsp;张
                </td>
            </tr>
            <tr>
                <td width="120px">领取方式：</td>
                <td colspan="3">
                    <input id="getType1" name="getType" type="radio" lay-filter="getType" value="SYSTEM" title="系统派发" checked>
                    <input id="getType2" name="getType" type="radio" lay-filter="getType" value="USER" title="用户手动领取">
                </td>
            </tr>
            <tr>
                <td width="120px">领取按钮外部校验接口：</td>
                <td colspan="3">
                    <input id="valid" name="valid" type="checkbox" lay-filter="valid"  lay-skin="primary"  title="是否校验外部业务领取条件">
                    <input type="text" name="validApi" id="validApi" style=" width:330px;" class="layui-input">

                </td>
            </tr>
            <tr>
                <td width="120px">活动图片：</td>
                <td colspan="3">
                    <img id="imgshow" height='126' width='206' style='margin-left:10px;max-width:206px' onerror="javascript:this.src='${ctx}/images/default.jpg';" src='<#--${imgPth!}${(vipgift.imageUrl)!}-->' />
                    <input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><#--<label style='color:red;'>*</label>-->
                    <button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
                    <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                    <input type="hidden" id="imageUrl" name="imageUrl"  value='${(vipgift.imageUrl)!}'>
                    <div style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出1MB（jpg、png、bmp）</div>
                </td>
            </tr>
            <tr>
                <td>活动规则描述:</td>
                <td colspan="3">
                    <div id="_topic_description_area" name="description" id="description"></div>
                </td>
            </tr>
        </#if>
        </table>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <label><h3>标签</h3></label>
                <select style="height:30px;margin-left:30px;" id="seletablib">
                    <option value="">所有标签类型</option>
                <#if taglib?? && ( taglib?size gt 0 )>
                    <#list taglib as lib>
                        <option value="${lib.fieldValue}">${lib.fieldKey}</option>
                    </#list>
                </#if>
                </select>
            </div>
            <div class="modal-body">
            <#if taglib?? && ( taglib?size gt 0 )>
                <#list taglib as lib>
                    <div class="tag-list__itemWraper itemWraper1" id="tag_${lib.fieldValue}">
                        <h3 class="h5 tag-list__itemheader">${lib.fieldKey}</h3>
                        <ul class="tag-list__itembody taglist--inline multi">
                            <#if tags?? && ( tags?size gt 0 )>
                                <#list tags as tag>
                                    <#if lib.fieldValue == tag.category  && tag.status>
                                        <li><a id="taglib" class="btn tag" data-type="unselect"
                                               data-id="${tag.tagName!}">${tag.tagName!}</a><i id="${tag.tagName!}" style="font-size: 0.01px;" class="glyphicon glyphicon-ok" hidden></i></li>
                                    </#if>
                                </#list>
                            </#if>
                        </ul>
                    </div>

                    <#if (lib_index+1)%3==0>
                        <div style="clear: both;height:0px;"></div>
                    </#if>
                </#list>
            </#if>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid" id="levelModal" tabindex="-1"
     style=" width:100%; height:100%; position:fixed; top:0; left:0;z-index: 100000;" hidden>
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <label><h3>用户等级</h3></label>
            </div>
            <div class="modal-body">
                    <div class="tag-list__itemWraper itemWraper1" id="tag">
                        <ul class="tag-list__itembody taglist--inline multi">
                            <#if levelRs?? && ( levelRs?size gt 0 )>
                                <#list levelRs as level>
                                        <li><a id="level" class="btn tag" data-type="levelselect"
                                               data-id="${level.level!}">${level.level!}&nbsp;${level.levelCode!}</a><i id="${level.level!}" style="font-size: 0.01px;" class="glyphicon glyphicon-ok" hidden></i></li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-close="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid row js_pop_menu" hidden>
    <div class="main_modal_tc col-sm-4">
        <div class="main_modal_t">请选择区域
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
        <#-- <div class="character_r_b">全国-->
            <ul id="treeDemo" class="ztree" style="width: 250;height: 300"></ul>
        <#--  </div>-->
        </div>
        <div>
            <input value="确认" class="js_save_area_btn btn btn-info">
        </div>
    </div>
</div>

<#if menus?? && (menus?size > 0) >
<div hidden>
    <ul>
        <#list menus as menu>
            <li class="menu_li" data-menu-id="${menu.regionId!}" data-menu-pid="${menu.pId!}"
                data-menu-name="${menu.regionName!}"></li>
        </#list>
    </ul>
</div>
</#if>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="width: 90%;top:0px">
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
</body>
<script data-main="${ctx}/js/abc/financed/couponactivity.js" src="${ctx}/js/require.js"></script>
</html>
