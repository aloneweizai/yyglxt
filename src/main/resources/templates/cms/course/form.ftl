<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <script type="text/javascript">
        var imgUrl = "${imgUrl!}";
        var readonly = "${readonly?c}";
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
<#--<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layer/skin/default/layer.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/course.css">
    <style>
        input[data-type='datetimebox'] {
            width: 170px;
        }

        .head span {
            width: 245px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        .course_table {
            width: 96%;
        }

        tbody.edui-default {
            display: table-row-group !important;
            vertical-align: middle !important;
            border-color: inherit !important;
        }

        tr.edui-default {
            display: table-row !important;
            vertical-align: inherit !important;
            border-color: inherit !important;
        }


    </style>
</head>
<body class="newclassroom-body">
<input type="hidden" class="js_currLink" value="${currLink!}">
<input type="hidden" class="js_course_isFree" value="${(course.isFree)!}">
<#assign isFree = false>
<#if course?? && course.isFree==1><#assign isFree = true></#if>
<#assign isRecording = false>
<#if course?? && course.teachingMethod=='recording'><#assign isRecording = true></#if>

<form id="course_form">
    <fieldset <#if readonly>disabled</#if>>
        <input type="hidden" name="curriculumId" value="${(course.curriculumId)!}">

        <div class="room-nav">
            <div class="room-subnav">
                <a <#if formName == "course">class="active"</#if> >课程内容</a>
                <a <#if formName == "courseware">class="active"</#if>>课时信息</a>
            </div>
        </div>
        <table class="course_table newclassroom-table layui-form" <#if formName != "course">style="display:none;"</#if>>
            <tbody class="first">
            <tr class="title">
                <td><span class="color_r2">*</span>课程标题：</td>
                <td><input type="text" name="title" style="width: 565px" class="layui-input" value="${(course.title)!}">
                </td>
            </tr>
            <tr class="select">
                <td><span class="color_r2">*</span>选择分类：</td>
                <td>
                    <input id="selectCateTree" class="layui-input" value="${(course.classifyName)!}" readonly>
                    <input name="classify" value="${(course.classify)!}" hidden>
                </td>
            </tr>
            <tr>
                <td><span class="color_r2">*</span>课程推荐：</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="recommend" value="" title="普通课程"
                               <#if !course?? || !(course.recommend??) || course.recommend==''>checked</#if>>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="recommend" value="recommend" title="推荐课程"
                               <#if course?? && course.recommend?? && course.recommend=='recommend'>checked</#if>>
                    </label>
                <#--<label class="radio-inline">-->
                <#--<input type="radio" name="recommend" value="hot" <#if course?? && course.recommend?? && course.recommend=='hot'>checked</#if>>热门课程-->
                <#--</label>-->
                </td>
            </tr>
            <tr class="charge">
                <td><span class="color_r2">*</span>课程收费：</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="isFree" value="1" title="免费" class="charge-one"
                               <#if isFree>checked</#if>>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isFree" value="0" title="收费" class="charge-two"
                               <#if !isFree>checked</#if>>
                    </label>
                </td>
            </tr>
            <div>
                <tr class="goods" <#if isFree>style="display: none"</#if>>
                    <td>
                        <span style="margin-left:50px;">成本价格：</span>
                        <input class="js_keyup_number layui-input" name="costPrice" type="text"
                               value="${((course.costPrice)?c)!}" style=" width: 100px">
                    </td>
                <#--<td>-->
                <#--<span style="margin-left:20px;">销售价格：</span>-->
                <#--<input name="sellPrice" type="text" value="${((course.sellPrice)?c)!}" style="height: 25px;width: 100px" >-->
                <#--</td>-->
                    <td>
                        <span style="margin-left:20px;">市场价格：</span>
                        <input class="js_keyup_number layui-input" name="marketPrice" type="text"
                               value="${((course.marketPrice)?c)!}" style=" width: 100px">
                    </td>
                </tr>
                <tr class="member" <#if isFree>style="display: none"</#if>>
                    <td><span style="margin-left:50px;">免费会员：</span></td>
                    <td>
                    <#if vipLevels?? && (vipLevels?size > 0)>
                        <#list vipLevels as vipLevel>
                            <#if vipLevel.levelCode != 'VIP0'>
                                <input type="checkbox" lay-filter="level" value="${vipLevel.levelCode!}"
                                       title="${vipLevel.level!}" class="js_free_member"
                                       <#if !isFree && course?? && (course.memberGradeIds)?seq_contains(vipLevel.levelCode)>checked</#if>
                                       lay-skin="primary">
                            </#if>
                        </#list>
                    </#if>
                    </td>
                </tr>
                <tr class="goods" <#if isFree>style="display: none"</#if>>
                    <td width="100">
                        <span style="margin-left:50px;font-weight: 800;white-space: nowrap">会员名称</span>
                    </td>
                    <td width="110" style="text-align: center">
                        <span style="font-weight: 800">销售价格</span>
                    </td>
                    <td width="100" style="text-align: center">
                        <span style="font-weight: 800">销售积分价格</span>
                    </td>
                    <td width="160" style="text-align: center" colspan="3">
                        <span style="font-weight: 800">赠送积分</span>
                    </td>
                    <td width="210" style="text-align: center" colspan="3">
                        <span style="font-weight: 800">赠送优惠券</span>
                    </td>
                    <td width="220" style="text-align: center" colspan="3">
                        <span style="font-weight: 800">赠送会员</span>
                    </td>
                    <td width="170" style="text-align: center" colspan="3">
                        <span style="font-weight: 800">赠送礼包金额</span>
                    </td>
                </tr>
            <#if vipLevels?? && (vipLevels?size > 0)>
                <#list vipLevels as vipLevel>
                    <tr data-vip-code="${vipLevel.levelCode!}" class="goods js_vip_price"
                        <#if isFree>style="display: none"</#if>
                        <#if !isFree && course?? && (course.memberGradeIds)?seq_contains(vipLevel.levelCode)>style="display: none"</#if>>
                        <td width="100">
                                <span style="margin-left:50px;white-space: nowrap">
                                    <#--<#if vipLevel.levelCode == 'VIP0'>普通会员<#else>-->
                                    ${vipLevel.level!}
                                    <#--</#if>-->
                                        ：</span>
                        </td>
                        <#assign vipPrice="", vipIntegral="",giveIntegral="" >
                        <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                            <#list course.uvipPriceBoList as uvipPrice>
                                <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                <#assign vipPrice=uvipPrice.vipPrice?c>
                                <#assign vipIntegral=uvipPrice.vipIntegral?c>
                                <#break>
                                </#if>
                            </#list>
                        </#if>
                                    <td width="100">
                                        <input class="js_keyup_number layui-input" type="text" name="vipPrice"
                                               value="${vipPrice}" style=" width: 80px">
                                    </td>
                                    <td width="100">
                                        <input class="js_keyup_number layui-input" type="text" name="vipIntegral"
                                               value="${vipIntegral}" style=" width: 80px">
                                    </td>
                                    <td width="60">
                                        <select style="width: 60px" name="points">
                                            <option value="">无</option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'POINTS'&&gift.operSymbol =="and">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="and">和
                                            </option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'POINTS'&&gift.operSymbol =="or">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="or">或
                                            </option>
                                        </select>
                                    </td>
                                    <td width="80">
                                        <input class="js_keyup_number layui-input" type="text" name="giveIntegral"
                                            <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                <#list course.uvipPriceBoList as uvipPrice>
                                                    <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                        <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                            <#list uvipPrice.curriculumGiftBoList as gift>
                                                                <#if gift.operType??&&gift.operType == 'POINTS'>
                                               value="${gift.operValue!}"
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                            style=" width: 80px" >
                                    </td>
                                    <td width="20"></td>
                                    <td width="60">
                                        <select style="width: 60px" name="coupon">
                                            <option value="">无</option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'COUPON'&&gift.operSymbol =="and">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="and">和
                                            </option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'COUPON'&&gift.operSymbol =="or">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="or">或
                                            </option>
                                        </select>
                                    </td>
                                    <td width="140">
                                        <select name="couponId" id="couponId" lay-filter="couponId" lay-search="">
                                            <option value="">直接选择或搜索选择</option>
                                            <#if couponActivityRs?? && ( couponActivityRs?size gt 0 )>
                                                <#list couponActivityRs as couponActivity>
                                                    <option
                                                        <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                            <#list course.uvipPriceBoList as uvipPrice>
                                                                <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                                    <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                        <#list uvipPrice.curriculumGiftBoList as gift>
                                                                            <#if gift.operType??&&gift.operType == 'COUPON'&&gift.operValue == couponActivity.id>
                                                                                    selected
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                                        value="${couponActivity.id}">${couponActivity.activityName}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </td>
                                    <td width="20"></td>
                                    <td width="60">
                                        <select style="width: 60px" name="vip">
                                            <option value="">无</option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'VIP'&&gift.operSymbol =="and">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="and">和
                                            </option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'VIP'&&gift.operSymbol =="or">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="or">或
                                            </option>
                                        </select>
                                    </td>
                                    <td width="140">
                                        <select name="givelevel" id="givelevel" lay-filter="levelId">
                                            <option value="">-请选择-</option>
                                            <#if vipLevels?? && (vipLevels?size > 0)>
                                                <#list vipLevels as vip>
                                                    <#if vip.levelCode != "VIP0">
                                                        <option
                                                            <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                                <#list course.uvipPriceBoList as uvipPrice>
                                                                    <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                                        <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                            <#list uvipPrice.curriculumGiftBoList as gift>
                                                                                <#if gift.operType??&&gift.operType == 'VIP'&&gift.operValue == vip.levelCode>
                                                                                        selected
                                                                                </#if>
                                                                            </#list>
                                                                        </#if>
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                                            value="${vip.levelCode!}">${vip.level!}</option>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </select>
                                    </td>
                                    <td width="20"></td>
                                    <td width="60">
                                        <select style="width: 60px" name="amount">
                                            <option value="">无</option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'AMOUNT'&&gift.operSymbol =="and">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="and">和
                                            </option>
                                            <option
                                                <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                    <#list course.uvipPriceBoList as uvipPrice>
                                                        <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                            <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                                <#list uvipPrice.curriculumGiftBoList as gift>
                                                                    <#if gift.operType??&&gift.operType == 'AMOUNT'&&gift.operSymbol =="or">
                                                                            selected
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                                value="or">或
                                            </option>
                                        </select>
                                    </td>
                                    <td width="80">
                                        <input class="js_keyup_number layui-input" type="text" name="giveamount"
                                            <#if !isFree && course?? && (course.uvipPriceBoList??) && (course.uvipPriceBoList?size>0)>
                                                <#list course.uvipPriceBoList as uvipPrice>
                                                    <#if uvipPrice.vipGrade == vipLevel.levelCode>
                                                        <#if uvipPrice??&&(uvipPrice.curriculumGiftBoList??) && (uvipPrice.curriculumGiftBoList?size>0)>
                                                            <#list uvipPrice.curriculumGiftBoList as gift>
                                                                <#if gift.operType??&&gift.operType == 'AMOUNT'>
                                               value="${gift.operValue!}"
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        <#--value="${giveIntegral}"--> style=" width: 80px">
                                    </td>
                                    <td width="15">元</td>
                    </tr>
                </#list>
            </#if>
            </div>


            <tr>
                <td><span class="color_r2">*</span>授课方式：</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="teachingMethod" value="recording" title="录播"
                               <#if isRecording>checked</#if>>
                    </label>
                    <label class="radio-inline" style="margin-left:29px;">
                        <input type="radio" name="teachingMethod" value="live" title="直播"
                               <#if course?? && course.teachingMethod! =='live'>checked</#if>>
                    </label>
                    <label class="radio-inline" style="margin-left:29px;">
                        <input type="radio" name="teachingMethod" value="face" title="面授"
                               <#if !course?? || (course.teachingMethod! =='face')>checked</#if> >
                    </label>
                </td>
            </tr>
            <tr class="js_train_tr" <#if isRecording>style="display: none"</#if>>
                <td><span class="color_r2">*</span>培训时间：</td>
                <td>
                    <label for="one"><input id="trainTimeBegin" type="text" data-type="datetimebox" id="one"
                                            <#if course?? && !isRecording>value="${(course.trainTimeBegin?string("yyyy-MM-dd HH:mm:ss"))!}"</#if>></label>
                    至
                    <label for="two"><input id="trainTimeEnd" type="text" data-type="datetimebox" id="two"
                                            <#if course?? && !isRecording>value="${(course.trainTimeEnd?string("yyyy-MM-dd HH:mm:ss"))!}"</#if>></label>
                </td>
            </tr>
            <tr class="js_train_tr" <#if isRecording>style="display: none"</#if>>
                <td><span class="color_r2">*</span>是否需要报名：</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="isApply" value="0" title="否"
                               <#if (course?? && course.isApply?? && course.isApply==0)>checked</#if> >
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isApply" value="1" title="是"
                               <#if !course?? || !course.isApply?? || (course?? && course.isApply?? && course.isApply==1)>checked</#if>>
                    </label>
                </td>
                <td id="applyTime_td"
                    <#if (course?? && course.isApply?? && course.isApply==0)>style="display: none" </#if>>
                    <span style="margin-left:20px;">报名时间：</span>
                    <label for="one">
                        <input id="applyTimeBegin" type="text" data-type="datetimebox" id="one"
                               <#if course?? && !isRecording && course.applyTimeBegin??>value="${(course.applyTimeBegin?string("yyyy-MM-dd HH:mm:ss"))!}"</#if>>
                    </label>
                    至
                    <label for="two">
                        <input id="applyTimeEnd" type="text" data-type="datetimebox" id="two"
                               <#if course?? && !isRecording && course.applyTimeEnd??>value="${(course.applyTimeEnd?string("yyyy-MM-dd HH:mm:ss"))!}"</#if>>
                    </label>
                </td>
            </tr>
            <tr class="js_train_tr js_sign_fee_tr"
                <#if isRecording || isFree || (course?? && course.isApply?? && course.isApply==0)>style="display: none"</#if>>
                <td><span class="color_r2">*</span>报名时交费：&nbsp;&nbsp;&nbsp;</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="applyPay" value="0" title="否"
                               <#if (course?? && course.applyPay?? && course.applyPay==0)>checked</#if>>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="applyPay" value="1" title="是"
                               <#if !course?? || !course.applyPay?? || (course?? && course.applyPay?? && course.applyPay==1)>checked</#if>>
                    </label>
                </td>
            </tr>
            <tr class="js_train_tr" <#if isRecording>style="display: none"</#if>>
                <td><span class="color_r2">*</span>是否需要签到：</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="isSign" value="0" title="否"
                               <#if (course?? && course.isSign?? && course.isSign==0)>checked</#if>>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isSign" value="1" title="是"
                               <#if !course?? || !course.isSign?? || (course?? && course.isSign?? && course.isSign==1)>checked</#if>>
                    </label>
                </td>
                <td id="signTime_td" <#if (course?? && course.isSign?? && course.isSign==0)>style="display: none"</#if>>
                    <span style="margin-left:20px;">签到时间：</span>
                    <label for="one">
                        <input id="signTimeBegin" type="text" data-type="datetimebox" id="one"
                               <#if course?? && !isRecording && course.signTimeBegin??>value="${(course.signTimeBegin?string("yyyy-MM-dd HH:mm:ss"))!}"</#if>>
                    </label>
                    至
                    <label for="two">
                        <input id="signTimeEnd" type="text" data-type="datetimebox" id="two"
                               <#if course?? && !isRecording && course.signTimeEnd??>value="${(course.signTimeEnd?string("yyyy-MM-dd HH:mm:ss"))!}"</#if>>
                    </label>
                </td>
            </tr>
            <tr class="js_train_tr" <#if isRecording>style="display: none"</#if>>
                <td><span class="color_r2">*</span>人数限制：</td>
                <td class="number">
                    <input type="text" class=" layui-input" name="peopleLimit"
                           <#if course?? && course.peopleLimit??>value="${course.peopleLimit}"<#else>value="0"</#if> >
                    <span>提示：人数为0表示不限人数</span>
                </td>
            </tr>
            <tr class="training js_train_tr" <#if isRecording>style="display: none"</#if>>
                <td><span class="color_r2">*</span>培训地点：</td>
                <td>
                    <input type="text" class=" layui-input" name="trainSite" value="${(course.trainSite)!}">
                </td>
            </tr>
            <tr class="cover">
                <td><span class="color_r2">*</span>课程封面：</td>
                <td id="imgshow">
                    <img <#if course?? && course.cover??>height='90' width='90' style='margin-left:10px;'
                         src="${imgUrl}/${course.cover}"<#else>src="${ctx}/images/default.png"</#if> alt="">
                </td>
                <td>
                    <p>课程封面图片支持jpg,jpeg,png,gif,bmp格式,图片尺寸至少为480px*270px(像素),<br>文件大小不能超过200K</p>
                    <input type="file" id="uploadFile" name="uploadFile" data-type="jpg;jpeg;png;gif;bmp">
                    <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
                    <button id="upload_img_btn" type="button" class="layui-btn"  data-type="jpg;jpeg;png;gif;bmp">上传</button>
                    <input type="hidden" name="cover" value="${(course.cover)!}">
                </td>
            </tr>
            <tr class="labels">
                <td>&nbsp;相关标签：</td>
                <td>
                    <div class="btn-group" role="group" style="float: left;" id="labelDiv">
                    <#if course?? && course.labelList?? && (course.labelList?size>0)>
                        <#list course.labelList as lable>
                            <span name='labelSpan' onclick='$(this).remove();'>
                                    <input name='tagIds' type='hidden' value='${lable.labelId}'/>
                                    <button type='button' class='btn btn-default'>${lable.labelName}<i
                                            class='glyphicon glyphicon-remove'></i></button>
                                </span>
                        </#list>
                    </#if>
                    </div>
                    <button onkeydown="return false" data-type="xthf_course"
                            class="btn btn-default btn-sm pn-opt js_select_courseTag"><span
                            class="glyphicon glyphicon-plus"></span></button>
                </td>
            </tr>
            <tr>
                <td><span class="color_r2">*</span>发布范围：</td>
                <td>
                    <label class="radio-inline">
                        <input type="radio" name="issueScope" value="intra-company" title="仅公司内部"
                               <#if course?? && course.issueScope?? && course.issueScope=='intra-company'>checked</#if>>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="issueScope" value="open" title="对外公开"
                               <#if !course?? || (course.issueScope?? && course.issueScope=='open')>checked</#if>>
                    </label>
                </td>
            </tr>
            <tr class="labels">
                <td><span class="color_r2">*</span>授课讲师：</td>
                <td>
                    <div style="float: left;" id="lecturer_arr_div">
                    <#if course?? && course.lecturerGxList?? && (course.lecturerGxList?size>0)>
                        <#list course.lecturerGxList as lecturer>
                            <span onclick='$(this).remove();'>
                                        <input name='lecturerId' type='hidden' value='${(lecturer.lecturerId)!}'/>
                                        <button type='button' class='btn btn-default'>${(lecturer.lecturerName)!}<i
                                                class='glyphicon glyphicon-remove '></i></button>
                                    </span>
                        </#list>
                    </#if>
                    </div>
                    <button class="btn btn-default btn-sm pn-opt js_select_lecturer"><span
                            class="glyphicon glyphicon-plus"><span></button>
                </td>
            </tr>
            <tr>
                <td style="display: inline-block">&nbsp;课程简介:</td>
                <td style="display: inline-block; width: 90%;">
                <#--<div class="jd_editor">${(course.curriculumidIntro)!}</div>-->
                    <script id="editor_curriculumidIntro" type="text/plain">
                        ${(course.curriculumidIntro)!}

                    </script>
                </td>
            </tr>
            </tbody>
            <tfoot class="foot">
            <tr>
                <td></td>
                <td>
                    <a href="${currLink!}" style="color:#333">返回课程列表</a>
                    <button <#if !course??>data-status="0"<#else>data-status="${course.status}"</#if>
                            class="js_save_course  btn btn-default  pn-opt">保存
                    </button>
                <#if course??>
                    <button data-status="1" class="js_save_course btn btn-default  pn-opt">保存并发布</button></#if>
                </td>
            </tr>
            </tfoot>
        </table>
    </fieldset>
</form>
<!-- 引用课时页面 -->
<#include "courseware/form.ftl">
<div class="bg"></div>
<script data-main="${ctx}/js/abc/cms/course/form.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>