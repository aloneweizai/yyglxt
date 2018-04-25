<!DOCTYPE html>
<html>
<head>
    <title>调查问卷</title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <style>
        /*#end_time_display span{width:220px; display: inline-block; text-align: right; margin-bottom: 10px;;}*/
    </style>
</head>
<body class="g_wrapper g_wrapper_full page_edit g_survey">
<input name="surveyId" type="hidden" value="${(question.id)!}">
<input name="question_status" type="hidden" value="${(question.status)!}">
<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item" href="${currLink!}">问卷列表</a>
                <a class="nav_item current" href="javascript:;" id="sidebar_normal" data-sidebar-id="r1">编辑问卷</a>
                <a class="nav_item" href="javascript:;" id="sidebar_theme" data-sidebar-id="r2">选择皮肤</a>
                <a class="nav_item" href="javascript:;" id="survey_setting">设置</a>
                <#if question??>
                    <a class="nav_item" href="javascript:;" id="analyse_link">统计分析</a>
                </#if>
            </div>
            <div class="survey_options published" style="display: block;">
                <a href="javascript:;"  id="preview_survey"><i class="ico ico_preview"></i>预览</a>
                <a href="javascript:;" id="publish_survey" class="btn btn_middle btn_blue btn_start"><i></i>发布</a>
            </div>
        </div>
    </div>
</div>

<div id="container" class="g_container">
    <div class="survey_background_wrap" style="height: 1050px; background-position: 50% 50%; background-image: url('${(question.skinUrl)!}')"></div>
    <div class="editor_container" style="display: block;">
        <!-- 左边栏 -->
        <div class="editor_sidebar" id="r1">
            <div class="tab">
                <a class="tab_item current" href="javascript:;" data-tab="question_type">题目控件</a>
                <a class="tab_item" href="javascript:;" data-tab="survey_outline">问卷大纲</a>
            </div>
            <div id="menu" class="tab_content question_type" style="display: block;">
                <ul>
                    <li class="type_item radio" data-type-code="1" data-type="radio" data-page-div="js-div-option-subject">
                        <a href="javascript:;"><span class="ico"><i></i></span>单选题</a>
                    </li>
                    <li class="type_item checkbox " data-type-code="2" data-type="checkbox" data-page-div="js-div-option-subject">
                        <a href="javascript:;"><span class="ico"><i></i></span>多选题</a>
                    </li>
                    <li class="type_item text " data-type-code="3" data-type="text"  data-page-div="js-div-text-subject">
                        <a href="javascript:;"><span class="ico"><i></i></span>单行文本题</a>
                    </li>
                    <li class="type_item textarea " data-type-code="4" data-type="textarea"  data-page-div="js-div-text-subject">
                        <a href="javascript:;"><span class="ico"><i></i></span>多行文本题</a>
                    </li>
                    <li class="type_item text_multiple " data-type-code="5" data-type="text_multiple"  data-page-div="js-div-text-multipe-subject">
                        <a href="javascript:;"><span class="ico"><i></i></span>填空题</a>
                    </li>
                </ul>
            </div>
            <div id="outline" class="tab_content survey_outline" style="display: none;">
                <div class="page_container"></div>
            </div>
            <div id="sidebar_adjust_btn"></div>
        </div>
        <div class="editor_sidebar" id="r2" style="display: none;">
            <div class="tab">
                <a class="tab_item current" href="javascript:;" data-tab="themes_list">选择皮肤</a>
            </div>
            <div class="tab_content themes_list">
                <ul>
                    <#if themeImgs?? &&(themeImgs?size>0)>
                        <#list themeImgs as themeImg>
                            <li>
                                <a href="javascript:;" class="item current" data-theme="default" test="1"> <img src="${themeImg.imgUrl}" alt="${themeImg.imgUrl}"> <span>${themeImg.name}</span> </a>
                            </li>
                        </#list>
                    </#if>
                </ul>
            </div>
                <#--<div class="row"> 设置背景图片 </div>-->
                <#--<div class="row">-->
                    <#--<a id="upload_survey_background-a" href="javascript:;" class="btn">上传图片 <input type="file" name="src" id="upload_survey_background-file"> </a>-->
                    <#--<a id="clear_survey_background" href="javascript:;" class="btn" style="display: none;">清除</a>-->
                <#--</div>-->
                <#--<div class="row tips"> 支持小于1M的PNG,JPG,GIF图片 </div>-->
            </div>
            <div id="sidebar_adjust_btn"></div>
        </div>
        <!-- 主体 -->
        <div class="editor_main2">
            <div class="survey_wrap">
                <div class="survey_pages_tab ">
                    <a class="pages_preview" href="javascript:;"><i></i></a>
                    <div class="pages_wrap"><!-- 滚动容器 -->
                        <ul id="pages_wrap_ul"><!-- 页码容器 -->
                            <#if subjectMap?? &&(subjectMap?size>0)>
                                <#list subjectMap?keys as key>
                                    <li <#if key_index==0>class="pages_item current"<#else>class="pages_item"</#if>  data-pid="${key}" data-page-show="${key_index+1}"><span class="pages_show">第${key_index+1}页</span>
                                        <a class="pages_remove" href="javascript:;">×</a>
                                    </li>
                                </#list>
                            <#else>
                                <li class="pages_item current"  data-pid="1" data-page-show="1"><span class="pages_show">第1页</span>
                                    <a class="pages_remove" href="javascript:;">×</a>
                                </li>
                            </#if>
                        </ul>
                        <!-- 结束页码 -->
                        <a class="pages_item pages_end">
                            <span class="pages_show">结束页</span>
                        </a>
                    </div>
                    <a class="pages_next" href="javascript:;"><i></i></a>
                    <a class="pages_more js_pages_more" href="javascript:;"><i></i></a>
                </div>
                <div class="survey_main">
                    <div class="survey_title" style="display: block;">
                        <div class="inner">
                            <h1 class="js_title_content title_content cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true" data-editor="editor1" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_35" style="position: relative;">
                                <p><#if question??>${question.title}<#else>财税问卷调查</#if></p>
                            </h1>
                        </div>
                    </div>
                    <div class="survey_prefix" style="display: block;">
                        <div class="inner">
                            <h1 class="js_prefix_content prefix_content cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true" data-editor="editor2" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_58" style="position: relative;">
                                <p><#if question??>${question.simpleDesc}<#else>为了给您提供更好的服务，希望您能抽出几分钟时间，将您的感受和建议告诉我们，我们非常重视每位用户的宝贵意见，期待您的参与！现在我们就马上开始吧！</#if></p>
                            </h1>
                        </div>
                    </div>
                    <div class="survey_container">
                    <#if subjectMap?? &&(subjectMap?size>0)>
                        <#assign subjectIndex = 0>
                        <#list subjectMap?keys as key>
                            <div class="page" data-pid="${key}" <#if key_index==0>style="display: block;"<#else>style="display: none;"</#if> >
                             <#assign subjects = subjectMap[key]>
                                <#if subjects?? && (subjects?size > 0)>
                                    <#list subjects as subject>
                                        <#assign subjectIndex = (subjectIndex+1)>
                                        <div class="question question_radio <#if subject.isRequired == 1>required</#if> " data-subject-id="${subject.id}" data-subject-number="${subject.number}" data-type="${subject.optionType}">
                                            <div class="inner">
                                                <div class="title">
                                                    <span class="title_index"><span class="question_index">${subjectIndex}</span>.</span>
                                                    <h3 class="title_text">
                                                        <#--<p>${subject.title}</p>-->
                                                        ${subject.title}
                                                    </h3>
                                                </div>
                                                <div class="description">
                                                    <div class="description_text">
                                                        <p>${subject.simpleDesc}</p>
                                                    </div>
                                                </div>
                                                <#if subject.optionType == '1'>
                                                    <div class="inputs">
                                                        <#list subject.optionList as option>
                                                            <div class="option_item" style="width: 100%;" role="radio" tabindex="-1" aria-checked="false">
                                                                <input class="survey_form_checkbox" type="radio" value="">
                                                                <label>
                                                                    <i class="survey_form_ui"></i>
                                                                    <div class="option_text"><p>${option.optionString}</p></div>
                                                                </label>
                                                            </div>
                                                        </#list>
                                                    </div>
                                                </#if>
                                                <#if subject.optionType == '2'>
                                                    <div class="inputs">
                                                        <#list subject.optionList as option>
                                                            <div class="option_item" style="width: 100%;" role="checkbox" tabindex="-1" aria-checked="false">
                                                                <input class="survey_form_checkbox" type="checkbox" value="">
                                                                <label>
                                                                    <i class="survey_form_ui"></i>
                                                                    <div class="option_text"><p>${option.optionString}</p></div>
                                                                </label>
                                                            </div>
                                                        </#list>
                                                    </div>
                                                </#if>
                                                <#if subject.optionType == '3'>
                                                    <div class="inputs">
                                                        <input  class="survey_form_input" type="text" size=""  value="">
                                                    </div>
                                                </#if>
                                                <#if subject.optionType == '4'>
                                                    <div class="inputs">
                                                        <textarea role="textbox" class="survey_form_input" rows="5" cols="60"></textarea>
                                                    </div>
                                                </#if>
                                            </div>
                                            <div class="control_mask"></div>
                                            <div class="control">
                                                <ul>
                                                    <li class="control_btn edit"><b title="编辑"><i></i></b></li>
                                                    <li class="control_btn copy"><b title="复制"><i></i></b></li>
                                                    <li <#if isSubmitPapers?? && isSubmitPapers>hidden</#if> class="control_btn delete"><b title="删除"><i></i></b></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                        </#list>
                    <#else>
                        <div class="page" data-pid="1" style="display: block;"></div>
                    </#if>
                        <div class="survey_suffix" style="display: none;">
                            <div class="inner">
                                <div class="js_suffix_content suffix_content cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true"  data-editor="editor8" tabindex="0" spellcheck="false" role="textbox"  aria-label="false" aria-describedby="cke_225" style="position: relative;">
<#if question?? && question.endDesc??>${question.endDesc}<#else>
                                    <p><img alt="问卷已经100%完成" src="https://wj.qq.com/image/end.png"></p>
                                    <p>&nbsp;</p>
                                    <p>问卷到此结束，感谢您的参与！</p>
</#if>
                                </div>
                            </div>
                        </div>
            </div>
        </div>
    </div>
</div>

<!-- 编辑页面初始化原来的题目，根据是否有用户提交试卷来判断 该题目是否可以编辑 -->
<input type="hidden" class="js_isSubmitPapers" value="${(isSubmitPapers?c)!}">
<#if subjectMap?? &&(subjectMap?size>0)>
<ul hidden>
    <#list subjectMap?keys as key>
        <#assign subjects = subjectMap[key]>
        <#if subjects?? && (subjects?size > 0)>
            <#list subjects as subject>
                <li class="js_init_subject" data-subject-id="${subject.id}">
            </#list>
        </#if>
    </#list>
</ul>
</#if>

<!-- 设置 -->
<div id="set_up" style="display: none">
    <div style="opacity: 0.7; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; z-index: 1026; background: rgb(0, 0, 0);" class="ui-popup-backdrop"></div>
    <div style="position: absolute; outline: 0px; left:50%; width: 900px; top: 100px; height: 470px; margin: 100px 0 0 -450px;z-index: 1026;"
         aria-labelledby="title:settingDialog" aria-describedby="content:settingDialog" class="ui-popup ui-popup-modal ui-popup-show ui-popup-focus" role="alertdialog">
        <div i="dialog" class="ui-dialog g_dialog_v2 setting_dialog">
            <div class="ui-dialog-arrow-a"></div>
            <div class="ui-dialog-arrow-b"></div>
            <table class="ui-dialog-grid">
                <tbody>
                <tr>
                    <td i="header" class="ui-dialog-header"><button i="close" class="ui-dialog-close" title="cancel" style="display: none;">×</button>
                        <div i="title" class="ui-dialog-title" id="title:settingDialog">问卷设置</div>
                    </td>
                </tr>
                <tr>
                    <td i="body" class="ui-dialog-body">
                        <#if question?? && question.questionnaireParam??>
                            <#assign qParam=question.questionnaireParam>
                        </#if>
                        <div i="content" class="ui-dialog-content" id="content:settingDialog" style="width: 830px; height: 300px;">
                            <ul class="index_panel">
                                <li class="current" data-index="0">显示设置</li>
                                <li data-index="1">回收设置</li>
                            </ul>
                            <div class="setting_content">
                                <div class="setting_category" data-index="0">
                                    <div class="category_label">显示设置</div>
                                    <div class="category_content">
                                        <div class="setting_item">
                                            <input name="show_stat" data-name="isDisplayResults" type="checkbox" lay-skin="primary"  class="btn_switch" id="dialog_setting_show_stat" <#if qParam?? && qParam.isDisplayResults?? && qParam.isDisplayResults==1>checked</#if>>
                                            <label class="ico_checkbox" for="dialog_setting_show_stat"></label> 答题完成后显示结果
                                        </div>
                                    </div>
                                </div>
                                <div class="setting_category" data-index="1">
                                    <div class="category_label">回收设置</div>
                                    <div class="category_content">
                                        <div class="setting_item">
                                            <input data-name="isSettingEndTime" type="checkbox" lay-skin="primary"  class="btn_switch" id="dialog_setting_end_time" <#if qParam?? && qParam.recoveryEndTime??>checked</#if>>
                                            <label class="ico_checkbox js_setting_end_time" for="dialog_setting_end_time"></label> 设定回收结束时间
                                            <div id="end_time_display" <#if !(qParam?? && qParam.recoveryEndTime??)>style="display: none;"</#if>>
                                                <input style="width:180px;display:none!important;" type="text"
                                                       data-type="datetimebox" name="end_time_date"
                                                    <#if qParam?? && qParam.recoveryEndTime??>value="${qParam.recoveryEndTimeText}"</#if>>
                                            </div>
                                        </div>
                                        <div class="setting_item">
                                            <input data-name="isLoginAuth" name="login_check" type="checkbox" lay-skin="primary"  class="btn_switch" id="dialog_setting_login_check" <#if qParam?? && qParam.isLoginAuth?? && qParam.isLoginAuth==1>checked</#if>>
                                            <label class="ico_checkbox" for="dialog_setting_login_check"></label> 答题需要登录验证
                                        </div>
                                        <div class="setting_item">
                                            <input data-name="isAnswerOnce" name="answer_count" type="checkbox" lay-skin="primary"  class="btn_switch" id="dialog_setting_answer_count" <#if qParam?? && qParam.isAnswerOnce?? && qParam.isAnswerOnce==1>checked</#if>>
                                            <label class="ico_checkbox js_setting_answer_count" for="dialog_setting_answer_count"></label> 每个用户只能回答一次
                                        </div>
                                        <div class="setting_item">
                                            <input data-name="isWxOnce" name="wx_count" type="checkbox" lay-skin="primary"  class="btn_switch" id="dialog_setting_wx_count" <#if qParam?? && qParam.isWxOnce?? && qParam.isWxOnce==1>checked</#if>>
                                            <label class="ico_checkbox js_setting_wx_count" for="dialog_setting_wx_count"></label> 每个微信号只能回答一次
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td i="footer" class="ui-dialog-footer">
                        <div i="statusbar" class="ui-dialog-statusbar"></div>
                        <div i="button" class="ui-dialog-button">
                            <button id="setup_submit" type="button" i-id="保存" autofocus="" class="ui-dialog-autofocus">保存</button>
                            <button id="setup_cancel" type="button" i-id="取消">取消</button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


<div hidden class="js-div-option-subject">
    <div class="editor_question" data-subject-id="" data-subject-number="" style="position: relative; left: 0px;">
        <div class="inner radio">
            <div class="row editor_title"><label class="row_title" >题目</label>
                <div class="row_content">
                    <div contenteditable="true" class="question_title mod_editor inline_editor cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" data-editor="editor61" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_1688" style="position: relative;"><p><br></p></div>
                </div>
            </div>
            <div class="row editor_description"> <label class="row_title" >备注</label>
                <div class="row_content">
                    <div placeholder="(可选填)" contenteditable="true" class="question_description mod_editor inline_editor cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" data-editor="editor59" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_1634" style="position: relative;"><p><br></p></div>
                </div>
            </div>
            <div class="row editor_type">
                <div class="row_content">
                    <select id="question_type" class="js-question-type">
                    <#list subjectTypes as subjectType>
                            <option value="${subjectType.code}">${subjectType.description}</option>
                    </#list>
                    </select><label><input name="question_required" style="margin-right: 0;" type="checkbox" lay-skin="primary"  checked="">必填</label>
                </div>
            </div>
            <div class="row editor_options" style="">
                <ul class="options_list">
                    <div class="additional_setting_title"></div>
                    <ul class="normal_options_list">
                        <li class="option_item" > <span class="handle"></span>
                            <div class="option_input_wrap">
                                <div class="mod_editor inline_editor option_text cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true" data-editor="editor57" tabindex="0" spellcheck="false" role="textbox" aria-label="false" style="position: relative;">
                                    <p>选项</p>
                                </div>
                            </div>
                            <a href="javascript:;" class="btn btn_del btn_del_option">×</a>
                        </li>
                        <li class="option_item" > <span class="handle"></span>
                            <div class="option_input_wrap"><div class="mod_editor inline_editor option_text cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true" style="position: relative;">
                                    <p>选项</p>
                            </div></div>
                            <a href="javascript:;"  class="btn btn_del btn_del_option">×</a>
                        </li>
                    </ul>
                    <li class="option_item option_create">
                        <div class="option_input_wrap"><span class="add_option">新建选项</span></div>
                    </li>
                </ul>
            </div>
            <div class="row editor_control">
                <a href="javascript:;" id="editor_confirm_btn" class="btn btn_small btn_blue btn_confirm">确定</a>
                <a href="javascript:;" id="editor_cancel_btn" class="btn btn_small btn_white btn_cancel">取消</a>
            </div>
        </div>
    </div>
</div>

<div hidden class="js-div-text-subject">
    <div class="editor_question" data-subject-id="" data-subject-number="">
        <div class="inner text">
            <div class="row editor_title"><label class="row_title">题目</label>
                <div class="row_content">
                    <div contenteditable="true" class="question_title mod_editor inline_editor cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" role="textbox"  style="position: relative;"><p><br></p></div>
                </div>
            </div>
            <div class="row editor_description"><label class="row_title">备注</label>
                <div class="row_content">
                    <div placeholder="(可选填)" contenteditable="true" class="question_description mod_editor inline_editor cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" data-editor="editor135" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_3776" style="position: relative;"><p><br></p></div>
                </div>
            </div>
            <div class="row editor_type">
                <div class="row_content">
                    <select id="question_type" class="js-question-type">
                    <#list subjectTypes as subjectType>
                        <option value="${subjectType.code}">${subjectType.description}</option>
                    </#list>
                    </select><label><input name="question_required" style="margin-right: 0;" type="checkbox" lay-skin="primary"  checked="">必填</label>
                </div>
            </div>
            <div class="row editor_control">
                <a href="javascript:;" id="editor_confirm_btn" class="btn btn_small btn_blue btn_confirm">确定</a>
                <a href="javascript:;" id="editor_cancel_btn" class="btn btn_small btn_white btn_cancel">取消</a>
            </div>
        </div>
    </div>
</div>

<div hidden class="js-div-text-multipe-subject">
    <div class="editor_question" data-subject-id="" data-subject-number="">
        <div class="inner text_multipe">
            <div class="row editor_title"><label class="row_title">题目</label>
                <div class="row_content">
                    <div contenteditable="true" class="question_title mod_editor inline_editor cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" role="textbox"  style="position: relative;">
                        <p>题目: <span class="mod_fillblank" data-nostyle="true">________</span></p>
                    </div>
                </div>
            </div>
            <div class="row editor_description"><label class="row_title">备注</label>
                <div class="row_content">
                    <div placeholder="(可选填)" contenteditable="true" class="question_description mod_editor inline_editor cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" style="position: relative;"><p><br></p></div>
                </div>
            </div>
            <div class="row editor_type">
                <div class="row_content">
                    <select id="question_type" class="js-question-type">
                    <#list subjectTypes as subjectType>
                        <option value="${subjectType.code}">${subjectType.description}</option>
                    </#list>
                    </select><label><input name="question_required" type="checkbox" lay-skin="primary"  checked="">必填</label>
                </div>
            </div>
            <div class="row editor_control">
                <a href="javascript:;" id="editor_confirm_btn" class="btn btn_small btn_blue btn_confirm">确定</a>
                <a href="javascript:;" id="editor_cancel_btn" class="btn btn_small btn_white btn_cancel">取消</a>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/questionnaire/form.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</html>