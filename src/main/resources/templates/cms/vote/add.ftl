<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 指定多核浏览器用webkit模式 -->
    <meta name="renderer" content="webkit">
    <title>投票</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/vote/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .toupiao-title h3{padding:10px 0}
    </style>
</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader" style="position:absolute;">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item current" href="javascript:void(0);" id="vote_baseinfo" data-voteid="${voteDto.id!}" test="r1">基本设置</a>
                <a class="nav_item" href="javascript:void(0);" id="vote_publish" data-voteid="${voteDto.id!}" test="r2">发布设置</a>
                <a class="nav_item " href="javascript:void(0);" id="vote_generate" data-voteid="${voteDto.id!}" test="r3">生成地址</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">

    <div class="survey_background_wrap" style="height: 872px; background-position: 50% 50%;"></div>

    <div class="editor_container" style="display: block;">
        <!-- 主体 -->
        <div class="editor_main" style=" left: 0; top: 60px;">
            <div class="survey_wrap">
                <div class="survey_main" style="padding-top:0;">
                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block;">
                            <div class="toupiao-title"><h3>创建投票</h3><i class="glyphicon glyphicon-menu-up"></i></div>
                            <div class="question question_radio required">
                                <form id="vote_base_info" name="vote_base_info" data-validator-option="{theme:'yellow_right', timely:1}">

                                    <input type="hidden" id="id" name="id" value="${voteDto.id!}">

                                    <table class="table-zy">
                                    <tr>
                                        <th>投票标题</th>
                                        <td>
                                            <input type="text" name="name" value="${voteDto.name!}" data-rule="required" class="toupiao-input"/><span style="color:#F56954;">*</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="20px"></td>
                                    </tr>
                                    <tr>
                                        <th>投票类型</th>
                                        <td>
                                            <div style="margin-left:22px;">
                                                <div class="radio" style="margin-top:0px;">
                                                    <label>
                                                        <input type="radio" name="channel" <#if (voteDto.channel!)=="radio" || !voteDto.channel?? >checked="checked"</#if>
                                                               value="radio"><span
                                                            style="font-weight: 600;">单选</span>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="channel" <#if (voteDto.channel!)=="check">checked="checked"</#if>
                                                               value="check"><span
                                                            style="font-weight: 600;">多选</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="10px"></td>
                                    </tr>
                                    <tr>
                                        <th>隐私投票</th>
                                        <td>
                                            <div style="margin-left:22px;">
                                                <div class="radio" style="margin-top:0px;">
                                                    <label>
                                                        <input type="radio" name="privacyVote" <#if (voteDto.privacyVote!)=="all" || !voteDto.privacyVote??>checked="checked"</#if>
                                                               value="all"><span
                                                            style="font-weight: 600;">任何人可投票</span>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="privacyVote" <#if (voteDto.privacyVote!)=="password">checked="checked"</#if>
                                                               value="password"><span style="font-weight: 600;">仅登陆后可投票</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="10px"></td>
                                    </tr>
                                    <tr>
                                        <th>结果查看</th>
                                        <td>
                                            <div style="margin-left:22px;">
                                                <div class="radio" style="margin-top:0px;">
                                                    <label>
                                                        <input type="radio" name="showResult" <#if voteDto.showResult==true || !voteDto.showResult??>checked="checked"</#if>
                                                               value="true"><span
                                                            style="padding-right:8px;font-weight: 600;">仅投票后查看</span><span>只能在提交投票后才可看到投票选项分布情况</span>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="showResult" <#if voteDto.showResult==false>checked="checked"</#if>
                                                               value="false"><span
                                                            style="padding-right:8px;font-weight: 600;">自由查看</span><span>不投票即可看到当前的投票选项分布情况</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="20px"></td>
                                    </tr>
                                    <tr>
                                        <th>投票截止时间</th>
                                        <td>
                                            <div style="margin-left:22px;">
                                                <input abc-type="datetimebox" class="toupiao-input" style="width: 150px;" name="endTime" value="${voteDto.endTime!}"
                                                       type="text">
                                                <i class="ico ico_date"></i>
                                                <span style="color:#F56954;">*</span>
                                                <input type="hidden" name="endTime-vote-proxy" data-rule="required;curentDateTime" value="${voteDto.endTime!}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                    </tr>

                                </table>
                                </form>
                            </div>


                            <div class="toupiao-title"><h3>投票选项</h3><i class="glyphicon glyphicon-menu-up"></i></div>
                            <div class="question question_radio required">
                                <form id="vote_item_info" name="vote_item_info" data-validator-option="{theme:'yellow_bottom', timely:1}">
                                    <!-- 普通投票 -->
                                    <div id="vote_item_normal_group">
                                        <input id="vote_subjectId" name="subjectId" type="hidden" value="${subjectId!}">
                                    <#if subjectItemList?size==0>
                                        <div class="xuanxiang">
                                            <div class="xuanxiang-input">
                                                <span name="order">1.</span>
                                                <input type="text" vote-field="vote_item_name" name="vote_item_name_1" data-rule="required;length[1~50]" maxlength="50"
                                                       class="toupiao-input"/>
                                                <#--<span><a name="vote_readonly_normal_item" href="javascript:void(0);"-->
                                                         <#--style="background: #28A4C9;">保存</a></span>-->
                                                <span><a name="vote_add_normal_item" href="javascript:void(0);"
                                                         style="background: #5cb85c;">新增</a></span>
                                                <#--<input type="hidden" name="vote_intro_html">-->
                                                <div name="vote_intro_html" style="display:none;"></div>
                                                <input type="hidden" name="vote_subject_item_id" value="">
                                                <input type="hidden" name="vote_subject_item_sort" value="0">
                                                <input type="hidden" name="vote_subject_item_nop" value="0">
                                                <input type="hidden" name="vote_subject_item_status" value="1">
                                            </div>
                                            <div style="height:80px;display:table;width:100%;">
                                                <input type="hidden" name="vote_subject_item_img">
                                                <div style="display:table-cell;vertical-align:middle;text-align: center;"
                                                     name="vote_normal_item_pic"></div>
                                            </div>
                                            <div class="xuanxiang-a">
                                                <#--<a name="vote_editable_normal_item" href="javascript:void(0);">修改文字</a>-->
                                                <a name="vote_uploadimg_normal_item" href="javascript:void(0);">添加图片</a>
                                                <a name="vote_intro_normal_item" href="javascript:void(0);">添加查看页</a>
                                            </div>
                                        </div>
                                    <#elseif subjectItemList?size gt 0>
                                        <#list subjectItemList as subjectItem>
                                            <div class="xuanxiang">
                                                <div class="xuanxiang-input">
                                                    <span name="order">${subjectItem_index+1}.</span>
                                                    <input type="text" vote-field="vote_item_name" name="vote_item_name_${subjectItem_index+1}" data-rule="required;length[1~50]" maxlength="50"
                                                           class="toupiao-input"
                                                           <#if (subjectItem.status!)=="0">readonly="readonly"</#if>
                                                           value="${subjectItem.item!}"/>
                                                    <#--<span><a name="vote_readonly_normal_item" href="javascript:void(0);"-->
                                                             <#--style="background: #28A4C9;">保存</a></span>-->
                                                    <#if subjectItem_index==0>
                                                        <span><a name="vote_add_normal_item" href="javascript:void(0);"
                                                                 style="background: #5cb85c;">新增</a></span>
                                                    <#else>
                                                        <span><a name="vote_delete_normal_item"
                                                                 href="javascript:void(0);">删除</a></span>
                                                    </#if>
                                                    <#--<input type="hidden" name="vote_intro_html"-->
                                                           <#--value="${subjectItem.detail!}">-->
                                                    <div name="vote_intro_html" style="display:none;">${subjectItem.detail!}</div>
                                                    <input type="hidden" name="vote_subject_item_id"
                                                           value="${subjectItem.id!}">
                                                    <input type="hidden" name="vote_subject_item_sort"
                                                           value="${subjectItem.sort!}">
                                                    <input type="hidden" name="vote_subject_item_nop"
                                                           value="${subjectItem.nop!}">
                                                    <input type="hidden" name="vote_subject_item_status"
                                                           value="${subjectItem.status!}">
                                                </div>
                                                <div style="height:80px;display:table;width:100%;">
                                                    <input type="hidden" name="vote_subject_item_img"
                                                           value="${subjectItem.image!}">
                                                    <div style="display:table-cell;vertical-align:middle;text-align: center;"
                                                         name="vote_normal_item_pic">
                                                        <#if subjectItem.image?? && (subjectItem.image)!="" >
                                                            <img src="${hostUrl}${subjectItem.image!}" height="60">
                                                        </#if>
                                                    </div>
                                                </div>
                                                <div class="xuanxiang-a">
                                                    <#--<a name="vote_editable_normal_item"-->
                                                       <#--href="javascript:void(0);">修改文字</a>-->
                                                    <a name="vote_uploadimg_normal_item"
                                                       href="javascript:void(0);">添加图片</a>
                                                    <a name="vote_intro_normal_item"
                                                       href="javascript:void(0);">添加查看页</a>
                                                </div>
                                            </div>
                                        </#list>
                                    </#if>
                                    </div>
                                </form>
                            </div>
                            <div class="toupiao-title"><h3>添加活动介绍</h3><i class="glyphicon glyphicon-menu-down"></i></div>
                            <div class="question question_radio required" style="display: none;">
                                <form id="vote_intro_form">
                                    <div style="padding:15px;">
                                        <textarea id="_vote_subject_intro">${voteDto.startIntro!}</textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="toupiao-title"><h3>投票后显示的内容</h3><i
                                    class="glyphicon glyphicon-menu-down"></i>
                            </div>
                            <div class="question question_radio required" style="display: none;">
                                <form id="vote_after_form">
                                <div style="padding:15px;">
                                    <textarea id="_voted_words">${voteDto.endIntro!}</textarea>
                                </div>
                                </form>
                            </div>

                            <#--<div class="toupiao-title"><h3>收集用户信息(选中为必填项)<span style="color:red;">(请选择右边属性)</span></h3><i class="glyphicon glyphicon-menu-down"></i>-->
                            <#--</div>-->
                            <#--<div class="question question_radio required" style="display: none;">-->
                                <#--<table class="table-zy" style="float: left; width:50%">-->

                                    <#--<tr>-->
                                        <#--<th>请选择收集信息</th>-->
                                        <#--<td width="20%" id="vote_collect_list">-->
                                            <#--<#list collectionList as voteAddition>-->
                                                <#--<div name="vote_collect_item" style="margin-left:22px;" data-id="${voteAddition.id!}" data-selectedval="${voteAddition.dictId!}">-->
                                                    <#--<div class="checkbox" style="margin-top:0px;">-->
                                                        <#--<label>-->
                                                            <#--<input name="vote_collect_item_checked" type="checkbox" lay-skin="primary"-->
                                                                   <#--<#if voteAddition.required?? && voteAddition.required==true>checked</#if>-->
                                                                   <#--value=""><span-->
                                                                <#--name="vote_collect_name">${voteAddition.content!}</span>-->
                                                        <#--</label>-->
                                                        <#--<a style="margin-left:20px;" name="vote_remove_label" href="javascript:void(0);">-->
                                                            <#--<div style="width:15px;height:15px;display:inline-block;background:url('${ctx}/images/tabs_icons.png') no-repeat center right;"></div>-->
                                                        <#--</a>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</#list>-->
                                        <#--</td>-->
                                    <#--</tr>-->
                                <#--</table>-->
                                <#--<div class="shoujixinxi">-->
                                <#--<#list cmsVoteCollectionList as voteCollection>-->
                                    <#--<a href="javascript:void(0);" name="vote_collection_btn"-->
                                       <#--data-value="${voteCollection.fieldValue!}">${voteCollection.fieldKey!}</a>-->
                                <#--</#list>-->
                                <#--</div>-->

                            <#--</div>-->

                            <div class="inner" style="text-align:center; margin-top:30px">
                                <div class="row editor_control">
                                    <a href="javascript:void(0);" id="vote_editor_confirm_btn"
                                       class="btn btn_small btn_blue btn_confirm">提交</a>
                                    <a href="javascript:void(0);" id="vote_editor_back_btn" style="background:#e9e9e9;"
                                       class="btn btn_small btn_default">返回</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script data-main="${ctx}/js/abc/cms/vote/add.js?v=${.now}" src="${ctx}/js/require.js"></script>
<script type="text/html" id="vote_item_tmpl">
        <div class="xuanxiang" style="border-top:1px solid #bababa;margin-top:20px;">
            <div class="xuanxiang-input">
                <span name="order">1.</span>
                <input type="text" vote-field="vote_item_name" name="" class="toupiao-input" data-rule="required;length[1~50]" maxlength="50"/>
                <#--<span><a name="vote_readonly_normal_item" href="javascript:void(0);" style="background: #28A4C9;">保存</a></span>-->
                <span><a name="vote_delete_normal_item" href="javascript:void(0);">删除</a></span>
                <#--<input type="hidden" name="vote_intro_html">-->
                <div name="vote_intro_html" style="display:none;"></div>
                <input type="hidden" name="vote_subject_item_id">
                <input type="hidden" name="vote_subject_item_sort" value="0">
                <input type="hidden" name="vote_subject_item_nop" value="0">
                <input type="hidden" name="vote_subject_item_status" value="1">
            </div>
            <div style="height:80px;display:table;width:100%;">
                <input type="hidden" name="vote_subject_item_img">
                <div style="display:table-cell;vertical-align:middle;text-align: center;" name="vote_normal_item_pic"></div>
            </div>
            <div class="xuanxiang-a">
                <#--<a name="vote_editable_normal_item" href="javascript:void(0);">修改文字</a>-->
                <a name="vote_uploadimg_normal_item" href="javascript:void(0);">添加图片</a>
                <a name="vote_intro_normal_item" href="javascript:void(0);">添加查看页</a>
            </div>
        </div>

</script>
<script type="text/html" id="vote_collect_item_tmpl">
    <div name="vote_collect_item" style="margin-left:22px;" data-selectedval="{{value}}">
        <div class="checkbox" style="margin-top:0px;">
            <label>
                <input name="vote_collect_item_checked" type="checkbox" lay-skin="primary"  value=""><span
                    name="vote_collect_name">{{text}}</span>
            </label>
            <a style="margin-left:20px;" name="vote_remove_label" href="javascript:void(0);">
                <div style="width:15px;height:15px;display:inline-block;background:url('${ctx}/images/tabs_icons.png') no-repeat center right;"></div>
            </a>
        </div>
    </div>
</script>
</html>