<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<!-- saved from url=(0039)https://wj.qq.com/edit.html?sid=1422807 -->
<html lang="zh-cn" class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>活动管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link href="${ctx}/css/datetime/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>

    <style>
        h3{display: block;
            font-size: 1.17em;
            -webkit-margin-before: 1em;
            -webkit-margin-after: 1em;
            -webkit-margin-start: 0px;
            -webkit-margin-end: 0px;
            font-weight: bold;}
        .table-zy{
            width:94%;
            margin: 0 auto;
        }
        .table-zy th{width:15%; text-align: right; vertical-align: top; padding-top:2px;}

        .page_edit .subtitle_item input[type=radio], .page_edit .option_item input[type=radio], .page_edit .subtitle_item input[type=checkbox], .page_edit .option_item input[type=checkbox] {margin-left: 0;}

        .toupiao-title{border-bottom:1px solid #ddd; width: 94%; margin: 0 auto; position: relative;}
        .toupiao-title i{ position:absolute; top:10px; right:20px; cursor: pointer;}
        .toupiao-input{margin: 0px 0 0 22px; height: 28px; line-height: 28px; width: 70%; padding: 5px;}

        .survey_form_ui{margin-top:8px !important;}

        .option_text{margin-top:0px !important;}

        .beizhutishi{margin-left: 5px; color: #999;}

        .xuanxiang{width:80%; margin: 0 auto;}
        .xuanxiang-input{margin-top:10px;}
        .xuanxiang-input a{display: inline-block; width:50px; height: 26px;  text-align: center; background: #F0614E; color: #fff;;}
        .xuanxiang-a{padding-left:35px;}
        .xuanxiang-a a{padding: 0 5px;}

        .adjoined-bottom{width:90%; margin: 0 auto;}

        .shoujixinxi{width:40%; float: left; border: 1px solid #ddd; padding:10px; margin-left:20px;}
        .shoujixinxi a{width:47%; padding:2px 3%; margin: 1% ;border: 1px solid #ddd;  display: inline-block; text-align: center; background: #fff;}
        .shoujixinxi a:hover{color:#fff; background: #28A4C9;}


        .FancyForm{display:inline-block; padding:0px 4px; text-align: center; font-size:16px; border: 3px solid #ccc; margin-left:22px;}
        .mycard-plus{margin:5px 22px; border:1px solid #ddd; padding:5px 0 0 5px;}
        .mycard-plus a{ background:#DEEDFA; padding:2px 5px; margin:0 2px 5px 0; display: inline-block;}
        .plus-tag{display: inline-block; margin-left:10px;}
        .plus-tag a{ background:#DEEDFA; padding:4px 5px; margin:0; display: inline-block;}
        .plus-tag a span{display: inline-block;}
        .plus-tag a em{background:url(${ctx}/images/tagbg.png) no-repeat;}
        .plus-tag a em{display: inline-block; margin:5px 0 0 8px;width:13px;height:13px;overflow:hidden;background-position:-165px -99px;cursor:pointer;}
        .plus-tag a:hover em{background-position:-168px -63px;}
        .option_item em{background:url(${ctx}/images/tagbg.png) no-repeat;}
        .option_item em{display: inline-block; margin:8px 0 0 0px;width:13px;height:13px;overflow:hidden;background-position:-165px -99px;cursor:pointer;}
        .mdtable{width:90%; margin:0 auto; border:#ddd 1px solid;}
        .mdtablethtd{ border-bottom: 1px solid #ddd; padding:5px;}
        .mdtablethtd input{
            border:0;}
        .mdtablethtd .jia{background:#2e9ad0; color:#fff; width:22px; line-height:22px;
            margin-right:5px;
            text-align: center;
            display: inline-block;
            cursor: pointer}
        .mdtablethtd .jian{background:#d87271; color:#fff; width:22px; line-height:22px;
            margin-right:5px;
            text-align: center;
            display: inline-block;
            cursor: pointer}

    </style>

</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item current" href="${ctx}/cms/activity/add.php">基本信息</a>
                <a class="nav_item " href="javascript:;" id="hdmd_list">活动名单</a>
                <a class="nav_item " href="javascript:;" id="tongji">活动统计</a>
                <a class="nav_item " href="javascript:;" id="tuiguang">活动推广</a>
            </div>
            <div class="survey_options published" style="display: block;">
                <a href="${ctx}/cms/activity/list.php" id="huodong_fanhui" class="btn btn_middle btn_blue btn_start"><i></i>返回</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">

    <div class="survey_background_wrap" style="height: 872px; background-position: 50% 50%;"></div>

    <div class="editor_container" style="display: block;">

    <form id="hd" >
        <!-- 主体 -->
        <div class="editor_main" style=" left: 0; top: 60px;">

            <div class="survey_wrap">

                <div class="survey_main" style="padding-top:0;">

                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block;">
                            <div class="toupiao-title"><h3>填写基本信息</h3><i class="glyphicon glyphicon-menu-up"></i></div>
                            <div class="question question_radio required" >

                                <table class="table-zy">
                                    <tr>
                                        <th>活动标题</th>
                                        <td>
                                            <input type="text" class="toupiao-input" name="title"/><span style="color: red;">*</span>
                                            <input type="hidden" class="toupiao-input" name="eventId" id="eventId"/>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>活动地点</th>
                                        <td>
                                            <select class="toupiao-input" style="width:145px; height: 32px; float: left;" id="province" name="province">
                                                <option value="">请选择</option>
                                            <#if province.dataList?? && (province.dataList?size > 0) >
                                            <#list province.dataList as province>
                                                <option value="${province.provinceId}">${province.province}</option>
                                            </#list>
                                            </#if>
                                            </select>
                                            <select class="toupiao-input" style="width:190px;height: 32px; float: left;" id="city" name="city">
                                                <option value="">请选择</option>
                                            </select>
                                            <input type="text" class="toupiao-input" style="width:300px; float:left;" name="address"/>
                                            <span style="color: red;">*</span>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>活动时间</th>
                                        <td style="padding-left: 20px;">
                                            <#--<input type="text" style="width: 320px" name="time" id="reservationtime" class="toupiao-input" value="2017/01/01 1:00 PM - 2017/01/01 1:30 PM"  class="span4"/>-->
                                            <input type="text"   data-options="editable:false" data-target="#startMsg" data-type="datetimebox" id="begintime" name="begintime" style=" width:150px;" >
                                                -<input type="text"  data-options="editable:false" data-type="datetimebox" data-target="#startMsg" name="endtime" id="endtime" style=" width:150px;" ><span style="color: red;">*</span><span class="msg-box" id="startMsg"></span>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>报名截止时间</th>
                                        <td style="padding-left: 20px;">
                                            <input type="text"   data-options="editable:false" data-type="datetimebox" data-target="#startMsgs" id="bmendtime" name="bmendtime" style=" width:150px; " >
                                            <span style="color: red;">*</span><span class="msg-box" id="startMsgs"></span>
                                        </td>
                                    </tr>
                                    <tr><td height="10px"></td></tr>
                                    <tr>
                                        <th>活动海报</th>
                                        <td>
                                            <div style="margin-left:22px;" class="filediv">
                                                <img id="filepath" src="${ctx}/images/default.jpg" style="width:100px; height: 100px;"><input type="file" class="filesz" id="filep" name="filep"  accept="image/x-png,image/jpeg,image/bmp">
                                                <a href="javascript:;" type="button" id="shangchuan" class="layui-btn" style="">上传</a>
                                                <p><span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200K（jpg、png、bmp），尺寸不能小于1000*600px</span></p>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>活动人数</th>
                                        <td>
                                            <input type="text" class="toupiao-input" name="peoppleNum"/><span style="color: red;">*</span>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>活动分类</th>
                                        <td>
                                            <div class="plus-tag-add">
                                                <a href="javascript:void(0);" class="FancyForm" type="hdfl"><i name="tubiao" class="glyphicon glyphicon-minus"></i></a><span style="color: red;">*</span>
                                                <span class="plus-tag" type="hdfl">
															</span>
                                                <input type="hidden" name="category" id="category">
                                            </div><!--plus-tag-add end-->
                                            <div class="mycard-plus" type="hdfl">
                                            <#if dict?? && (dict?size > 0) >
                                            <#list dict as dict>
                                                <a value="${dict.fieldValue}" title="${dict.fieldKey}" href="javascript:void(0);" type="hdfl"><span>${dict.fieldKey}</span><em></em></a>
                                            </#list>
                                            </#if>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>活动形式</th>
                                        <td>
                                            <div class="plus-tag-add">
                                                <a href="javascript:void(0);" class="FancyForm" type="hdxs"><i name="tubiao" class="glyphicon glyphicon-minus"></i></a><span style="color: red;">*</span>
                                                <span class="plus-tag" type="hdxs">
															</span>
                                                <input type="hidden" name="shape" id="shape">
                                            </div><!--plus-tag-add end-->
                                            <div class="mycard-plus" type="hdxs">
                                            <#if dictxs?? && (dictxs?size > 0) >
                                                <#list dictxs as dict>
                                                    <a value="${dict.fieldValue}" title="${dict.fieldKey}" href="javascript:void(0);" type="hdxs"><span>${dict.fieldKey}</span><em></em></a>
                                                </#list>
                                            </#if>

                                            </div>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>添加标签</th>
                                        <td>
                                            <div class="plus-tag-add" >
                                                <a href="javascript:void(0);" class="FancyForm" type="tjbq"><i name="tubiao" class="glyphicon glyphicon-minus"></i></a>
                                                <span class="plus-tag" type="tjbq">
															</span>
                                                <input type="hidden" name="tag" id="tag">
                                            </div><!--plus-tag-add end-->
                                            <div class="mycard-plus" type="tjbq">
                                            <#if dictbq?? && (dictbq?size > 0) >
                                                <#list dictbq as dict>
                                                    <a value="${dict.fieldValue}" title="${dict.fieldKey}" href="javascript:void(0);" type="tjbq"><span>${dict.fieldKey}</span><em></em></a>
                                                </#list>
                                            </#if>

                                            </div>
                                        </td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>活动摘要</th>
                                        <td><textarea style="width:91%; height: 120px; margin-left:22px;" name="summary" id="summary"></textarea></td>
                                    </tr>
                                    <tr><td height="20px"></td></tr>
                                    <tr>
                                        <th>详细内容</th>
                                        <td>
                                            <div id="_topic_description_area" style="margin-left: 20px;"></div>
                                            <input type="hidden" name="description" id="description">
                                            <script>
                                            </script>
                                        </td>
                                    </tr>

                                </table>



                            </div>


                            <div class="toupiao-title"><h3>表单设置</h3><i class="glyphicon glyphicon-menu-down"></i></div>
                            <div class="question question_radio required" style="display: none;">
                                    <table class="table-zy" id="table-zy" style="float: left; width:50%">
                                        <tr>
                                            <th>报名用户填写资料(选中为必填)</th>
                                            <td width="20%" style="color:red">
                                                &nbsp; （请选择右边属性）
                                            </td>
                                        </tr>
                                    </table>
                                <input type="hidden" name="field" id="field">
                                <div class="shoujixinxi">
                                <#if dictbm?? && (dictbm?size > 0) >
                                    <#list dictbm as dict>
                                    <a href="javascript:void(0);" value="${dict.fieldValue}" title="${dict.fieldKey}">${dict.fieldKey}</a>
                                    </#list>
                                </#if>
                                </div>
                            </div>
                            <input type="hidden" name="fieldsf" id="fieldsf">
                            <div class="toupiao-title"><h3>活动设置</h3><i id="huodongsztb" class="glyphicon glyphicon-menu-up"></i></div>
                            <div class="question question_radio required" id="huodongsz" style="">
                                <h4 style="color:#999; margin-left:30px;">基本信息设置</h4></td>
                                <table class="table-zy" >
                                    <tr>
                                        <th>参与人等级限制</th>
                                        <td width="30%">
                                            <div class="option_item" style="width: 100%; margin: 0;"  >
                                                <input class="survey_form_checkbox" type="checkbox" lay-skin="primary"  name="test1" data-oid="option_q-2-Tdr0_o-100-ABC112" data-exclusive="0" id="option_q-2-Tdr0_o-100-ABCe" value="">
                                                <label for="option_q-2-Tdr0_o-100-ABCe" style="min-height: 0; height: 26px;">
                                                    <i class="survey_form_ui"></i>
                                                    <div class="option_text">
                                                        <p style="color:#999;">限制参与此活动用户的等级</p>
                                                    </div>
                                                </label>
                                                <input type="hidden" name="isUserGrade" id="isUserGrade">
                                            </div>
                                        </td>
                                        <th>
                                            用户等级
                                        </th>
                                        <td>
                                            <select class="toupiao-input" style="width:80px;" name="userGrade">

                                            <#if expleve.dataList?? && (expleve.dataList?size > 0) >
                                                <#list expleve.dataList as expleve>
                                                    <option value="${expleve.name}">${expleve.name}</option>
                                                </#list>
                                            </#if>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>是否审核</th>
                                        <td colspan="3">
                                            <div class="option_item" style="width: 100%; margin: 0;"  >
                                                <input class="survey_form_checkbox" type="checkbox" lay-skin="primary"  name="test" data-oid="o-100-ABCD" data-exclusive="0" id="option_q-2-Tdr0_o-100-ABCf" value="">
                                                <label for="option_q-2-Tdr0_o-100-ABCf" style="min-height: 0; height: 26px;">
                                                    <i class="survey_form_ui"></i>
                                                    <div class="option_text">
                                                        <p style="color:#999;">凡报名此次活动者需要经过审批</p>
                                                    </div>
                                                </label>
                                                <input type="hidden" name="isCheck" id="isCheck">
                                            </div>
                                        </td>

                                    </tr>
                                </table>
                                <h4 style="color:#999; margin-left:30px;">主办方信息<span style="color: red;">*</span></h4>
                                <table class="mdtable" cellspacing="1" cellpadding="5">
                                    <tr>
                                        <th width="4%" class="mdtablethtd">&nbsp;</th>
                                        <th width="16%" class="mdtablethtd">主办单位<span style="color: red;">*</span></th>
                                        <th width="16%" class="mdtablethtd">联系人<span style="color: red;">*</span></th>
                                        <th width="17%" class="mdtablethtd">手机号码<span style="color: red;">*</span></th>
                                        <th width="17%" class="mdtablethtd">邮箱地址<span style="color: red;">*</span></th>
                                        <th width="20%" class="mdtablethtd">主办方简介</th>
                                        <th width="8%" class="mdtablethtd">操作</th>
                                    </tr>
                                <#if eventSponsor.dataList?? && (eventSponsor.dataList?size > 0) >
                                    <#list eventSponsor.dataList as eventSponsor>
                                        <tr>
                                            <td class="mdtablethtd">
                                                <#--<input class="survey_form_checkbox" type="checkbox" name="zbfids" data-oid="o-100-ABCD" data-exclusive="0" id="${eventSponsor.sponsorId}" value="${eventSponsor.sponsorId}">-->
                                                <#--<label for="${eventSponsor.sponsorId}" style="margin: 2px;">-->
                                                    <#--<i class="survey_form_ui"></i>-->
                                                <#--</label>-->
                                                <input type="radio" name="zbfids" id="${eventSponsor.sponsorId}" value="${eventSponsor.sponsorId}">
                                            </td>
                                            <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorName" value="${eventSponsor.sponsorName}"abc-id="name_${eventSponsor_index}" abc-type="name">
                                                <div style="color: red;" id="name_${eventSponsor_index}"></div>
                                                <input type="hidden" style="width: 100px;" name="sponsorName" value=""><input type="hidden" style="width: 100px;" name="sponsorId" value="${eventSponsor.sponsorId}"><input type="hidden" style="width: 100px;" name="sponsorId" value=""></td>
                                            <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorLxr" value="${eventSponsor.sponsorLxr}" abc-type="lxr" abc-id="lxr_${eventSponsor_index}">
                                                <div style="color: red;" id="lxr_${eventSponsor_index}"></div>
                                                <input type="hidden" style="width: 100px;" name="sponsorLxr" value=""></td>
                                            <td class="mdtablethtd"><input type="text" style="width: 100px;" abc-type="tel" name="sponsorTel" value="${eventSponsor.sponsorTel}" abc-id="tel_${eventSponsor_index}">
                                                <div style="color: red;" id="tel_${eventSponsor_index}"></div>
                                                <input type="hidden" style="width: 100px;" name="sponsorTel" value=""></td>
                                            <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorEmail" value="${eventSponsor.sponsorEmail}" abc-type="email" abc-id="email_${eventSponsor_index}">
                                                <div style="color: red;" id="email_${eventSponsor_index}"></div>
                                                <input type="hidden" style="width: 100px;" name="sponsorEmail" value=""></td>
                                            <td class="mdtablethtd"><textarea name="sponsorIntro">${eventSponsor.sponsorIntro} </textarea><textarea name="sponsorIntro" style="display: none">&nbsp;</textarea></td>
                                            <td class="mdtablethtd">
                                                <span name="jia" class="jia">+</span>
                                                <span class="jian" name="jian" delid="${eventSponsor.sponsorId}">-</span>
                                            </td>
                                        </tr>
                                    </#list>
                                <#else>
                                    <tr>
                                        <td class="mdtablethtd">
                                            <#--<input class="survey_form_checkbox" type="checkbox" name="zbfids" data-oid="o-100-ABCD" data-exclusive="0" id="option_q-2-Tdr0_o-100-ABCD0" value="">-->
                                            <#--<label for="option_q-2-Tdr0_o-100-ABCD0" style="margin: 2px;">-->
                                                <#--<i class="survey_form_ui"></i>-->
                                            <#--</label>-->
                                            <input type="radio" name="zbfids" id="option_q-2-Tdr0_o-100-ABCD0" value="">
                                        </td>
                                        <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorName" value="" abc-type="name" abc-id="name_name">
                                            <div style="color: red;" id="name_name"></div>
                                            <input type="hidden" style="width: 100px;" name="sponsorName" value=""><input type="hidden" style="width: 100px;" name="sponsorId" value=" "><input type="hidden" style="width: 100px;" name="sponsorId" value=" "></td>
                                        <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorLxr" value="" abc-type="lxr" abc-id="lxr_lxr">
                                            <div style="color: red;" id="lxr_lxr"></div>
                                            <input type="hidden" style="width: 100px;" name="sponsorLxr" value=""></td>
                                        <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorTel" value="" abc-type="tel" abc-id="tel_tel">
                                            <div style="color: red;" id="tel_tel"></div>
                                            <input type="hidden" style="width: 100px;" name="sponsorTel" value=""></td>
                                        <td class="mdtablethtd"><input type="text" style="width: 100px;" name="sponsorEmail" value="" abc-type="email" abc-id="email_email">
                                            <div style="color: red;" id="email_email"></div>
                                            <input type="hidden" style="width: 100px;" name="sponsorEmail" value=" "></td>
                                        <td class="mdtablethtd"><textarea name="sponsorIntro"> </textarea><textarea name="sponsorIntro" style="display: none"> </textarea></td>
                                        <td class="mdtablethtd">
                                            <span name="jia" class="jia">+</span>
                                            <span class="jian" name="jian" delid="">-</span>
                                        </td>
                                    </tr>
                                </#if>
                                    <input type="hidden" name="zbfindex" id="zfbindex">
                                    <input type="hidden" name="status" id="status">
                                </table>
                            </div>

                            <div class="inner" style="text-align:center; margin-top:30px">


                                <div class="row editor_control">
                                    <#--<a href="javascript:;" id="editor_cancel_btn_yl" class="btn btn_small btn_white btn_cancel">预览</a>-->
                                    <a href="javascript:;" id="editor_cancel_btn_bc" class="btn btn_small btn_white btn_cancel">保存</a>
                                    <a href="javascript:;" id="editor_confirm_btn_fb" class="btn btn_small btn_blue btn_confirm">发布活动</a>
                                        <input type="button" name="reset" id="reset" value="返回" class="layui-btn layui-btn-primary">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
        <input type="hidden" name="picture" id="picture">
    </form>

    </div>

</div>
<script data-main="${ctx}/js/abc/cms/activity/activity" src="${ctx}/js/require.js"></script>
</body>

</html>