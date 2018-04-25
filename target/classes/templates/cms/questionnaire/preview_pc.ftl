<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="format-detection" content="telephone=no">
    <title>问卷标题 - ${(question.title)!}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_survey.css">
    <style>
        .change:after{
            content: '*';
            font-weight: 700;
            color: #f00;
            margin-left: 5px;}
    </style>
</head>
<body class="g_wrapper page_survey g_survey">
<input type="hidden" name="questionId" value="${(question.id)!}">
<input type="hidden" name="startTime" value="${startTime?string("yyyy-MM-dd HH:mm:ss")}">
<!-- 问卷主体 -->
<div id="container" class="g_container">
    <div class="survey_background_wrap" style="background-image: url('${(question.skinUrl)!}'); background-size: cover;background-position: center center;height: 1000px"></div>
    <div class="g_content">
        <div class="survey_wrap" style="margin: auto">
            <div class="survey_main"><!-- 问卷标题 -->
                <h1 class="survey_title" style="display: block;">
                    <div class="inner"><div class="title_content"><p>${(question.title)!}</p></div></div>
                </h1>
                <div class="survey_content"><!-- 问卷欢迎语 -->
                    <div class="survey_prefix" style="display: block;">
                        <div class="inner"><h2 class="prefix_content"><p>${(question.simpleDesc)!}</p></h2></div>
                    </div>
                    <div class="survey_container"><!-- 问卷内容 -->
                        <#if pageSubjectMap?? &&(pageSubjectMap?size>0)>
                            <#assign subjectIndex = 0>
                            <#list pageSubjectMap?keys as key>
                            <div class="survey_page" data-pid="${key}" <#if key_index!=0>style="display: none;"</#if>>
                                <#assign subjects = pageSubjectMap[key]>
                                <#if subjects?? && (subjects?size > 0)>
                                    <#list subjects as subject>
                                        <#assign subjectIndex = (subjectIndex+1)>
                                        <#--<#if subject.optionType == '1' || subject.optionType == '2'>-->
                                            <div class="question question_radio <#if subject.isRequired == 1>required</#if>" data-id="${subject.id}" role="group" data-subject-optionType="${subject.optionType}" style="display: block;">
                                                <div class="inner">
                                                    <div class="title">
                                                        <div role="presetation">
                                                            <span class="title_index"><span class="question_index">${subjectIndex}</span>. </span>
                                                            <h3 <#if subject.optionType == '5'> class="js_blank_text title_text"<#else>class="title_text" </#if> >
                                                                 ${subject.title}
                                                            </h3>
                                                        </div>
                                                        <span class="tips" role="alert"></span>
                                                    </div>
                                                    <div class="description"><div class="description_text">${subject.simpleDesc}</div></div>
                                                    <div class="inputs">
                                                        <#if subject.optionType == '1'>
                                                            <#list subject.optionList as option>
                                                                <div class="option_item" style="width: 100%;" role="radio" >
                                                                    <input class="survey_form_checkbox" type="radio" name="answer_${subject.id}" id="${option.id}" value="<p>${option.optionString}</p>">
                                                                    <label for="${option.id}"> <i class="survey_form_ui"></i>
                                                                        <div class="option_text"><p>${option.optionString}</p></div>
                                                                    </label>
                                                                </div>
                                                            </#list>
                                                        </#if>
                                                        <#if subject.optionType == '2'>
                                                            <#list subject.optionList as option>
                                                                <div class="option_item" style="width: 100%;" role="checkbox" aria-checked="false">
                                                                    <input class="survey_form_checkbox" type="checkbox" lay-skin="primary"  name="answer_${subject.id}"  id="${option.id}" value="<p>${option.optionString}</p>">
                                                                    <label for="${option.id}">
                                                                        <i class="survey_form_ui"></i>
                                                                        <div class="option_text"><p>${option.optionString}</p></div>
                                                                    </label>
                                                                </div>
                                                            </#list>
                                                        </#if>
                                                        <#if subject.optionType == '3'>
                                                            <input class="survey_form_input" type="text" size="" maxlength="" id="text_q-8-c0aq"  value="">
                                                        </#if>
                                                        <#if subject.optionType == '4'>
                                                            <textarea role="textbox" class="survey_form_input" id="textarea_q-9-ORgG" name="answer_q-9-ORgG" rows="5" cols="60"></textarea>
                                                        </#if>
                                                    </div>
                                                </div>
                                            </div>
                                        <#--</#if>-->
                                    </#list>
                                </#if>
                            </div>
                            </#list>
                        </#if>
                    </div>
                    <!-- 问卷结束语 -->
                    <div class="survey_suffix" style="display: none;">
                        <div class="inner">
                            <div class="suffix_content"><p><img alt="问卷已经100%完成" src="${ctx}/images/cms/end.png"></p>
                                <p>&nbsp;</p>
                                <p>问卷到此结束，感谢您的参与！</p>
                            </div>
                        </div>
                    </div>
                    <!-- 问卷操作区域 -->
                    <div class="survey_control">
                        <div class="inner">
                            <a href="javascript:;" title="上一页" aria-label="上一页" role="button" class="survey_btn survey_prevpage" style="display: none;">上一页</a>
                            <#if pageSubjectMap?? &&(pageSubjectMap?size>1)>
                                <a href="javascript:;" title="下一页" aria-label="下一页" role="button" class="survey_btn survey_nextpage" style="display: inline-block;">下一页</a>
                                <a href="javascript:;" title="提交" aria-label="提交 submit" role="button" class="survey_btn survey_submit" style="display: none;">提交</a>
                            <#else>
                                <a href="javascript:;" title="提交" aria-label="提交 submit" role="button" class="survey_btn survey_submit" style="display: inline-block;">提交</a>
                            </#if>
                            <#if question.questionnaireParam?? && question.questionnaireParam.isDisplayResults == 1>
                            <a target="_blank" href="javascript:;" class="survey_btn survey_show_stat js_answer_result" style="display: none;">答题结果</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/questionnaire/preview.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</html>