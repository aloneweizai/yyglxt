<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <title>问卷标题 - ${(question.title)!}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="/favicon.ico?v=1.1">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_survey.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_recycle_answer.css">
</head>
<body class="g_wrapper page_survey g_survey recycle_answer">
<!-- 问卷主体 -->
<div id="container" class="g_container">
    <div class="title_header">
        <div class="title">
            <h1>${(question.title)!}</h1> <span></span>
        </div>
    </div>
    <div class="g_content">
        <div class="survey_wrap">
            <div class="survey_main">
                <!-- 问卷标题 -->
                <div class="survey_title">
                    <div class="inner">
                        <div class="title_content"></div>
                    </div>
                </div>
                <div class="survey_content">
                    <div class="survey_prefix"><!-- 问卷欢迎语 -->
                        <div class="inner">
                            <div class="prefix_content"></div>
                        </div>
                    </div>
                    <div class="survey_container"><!-- 问卷内容 -->
                        <div class="answers_wrapper">
                            <ul class="slides" style="height: 794px; opacity: 1;">
                                <li class="answer_wrapper slide current" data-aid="19" data-iid="0" style="transform: translate3d(0px, 0px, 0px);">
                                    <div class="scrollbar_wrapper">
                                        <div class="one_answer_wrapper"><h2 class="inner">基本信息</h2>
                                            <div class="inner base_infor_div">
                                                <#--<span>答题ID：${answer.id} </span>-->
                                                <#if user??>
                                                    <span>用户名：${user.username} </span>
                                                    <#if user.phone??>
                                                        <span>手机号：${user.phone} </span>
                                                    </#if>
                                                <#else>
                                                    <span>用户：游客</span>
                                                </#if>
                                                <br>
                                                <span>开始时间：${answer.startTime?string("yyyy.MM.dd HH:mm:ss")}</span>
                                                <span>结束时间：${answer.endTime?string("yyyy.MM.dd HH:mm:ss")}</span>
                                            </div>
                                            <h2 class="inner">答题详情</h2>
<#if pageSubjectMap?? &&(pageSubjectMap?size>0)>
    <#assign index = 0>
    <#list pageSubjectMap?keys as key>
        <#assign subjects = pageSubjectMap[key]>
        <#if subjects?? && (subjects?size > 0)>
            <#list subjects as subject><#assign index = index+1>
                    <#assign answer_content = ''>
                    <#if answer?? && (answer.answerList)?? && ((answer.answerList)?size > 0)>
                        <#list answer.answerList as answerLog>
                            <#if answerLog.subjectsId == subject.id>
                                <#assign answer_content = answerLog.content>
                            </#if>
                        </#list>
                    </#if>
                    <div class="question question_radio" id="question_q-1-hszJ" data-type="radio" data-id="q-1-hszJ">
                        <div class="inner">
                            <div class="title">
                                <span class="title_index"><span class="question_index">${index}</span>. </span>
                                <div class="title_text">
                                    <p>${subject.title}</p><#if subject.optionType == '5'>${answer_content}</#if>
                                </div>
                                <div class="description"><div class="description_text"></div></div>
                                <div class="inputs">
                                    <#if subject.optionType == '1'>
                                        <#list subject.optionList as option>
                                            <div class="option_item" style="width: 100%;">
                                                <input class="survey_form_checkbox" type="radio" name="answer_${subject.id}"  id="${option.id}" value="<p>${option.optionString}</p>" disabled="disabled" <#if answer_content == option.optionString>checked="checked"</#if>  >
                                                <label for="${option.id}"><i class="survey_form_ui"></i>
                                                    <div class="option_text"><p>${option.optionString}</p></div>
                                                </label>
                                            </div>
                                        </#list>
                                    </#if>
                                    <#if subject.optionType == '2'>
                                        <#list subject.optionList as option>
                                            <#assign ischeck = false>
                                            <#if answer?? && (answer.answerList)?? && ((answer.answerList)?size > 0)>
                                                <#list answer.answerList as answerLog>
                                                    <#if answerLog.subjectsId == subject.id>
                                                        <#if answerLog.content == option.optionString>
                                                            <#assign ischeck = true>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                            <div class="option_item" style="width: 100%;" role="checkbox">
                                                <input class="survey_form_checkbox" type="checkbox" lay-skin="primary"  name="answer_${subject.id}"  id="${option.id}" value="<p>${option.optionString}</p>"disabled="disabled" <#if ischeck>checked="checked"</#if>>
                                                <label for="${option.id}"><i class="survey_form_ui"></i>
                                                    <div class="option_text"><p>${option.optionString}</p></div>
                                                </label>
                                            </div>
                                        </#list>
                                    </#if>
                                    <#if subject.optionType == '3'>
                                        <input class="survey_form_input" type="text" size="" maxlength=""  value="${answer_content}"  disabled="disabled">
                                    </#if>
                                    <#if subject.optionType == '4'>
                                        <textarea role="textbox" class="survey_form_input"  name="answer_q-9-ORgG" rows="5" cols="60" disabled="disabled">${answer_content}</textarea>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
            </#list>
        </#if>
    </#list>
</#if>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 问卷结束语 -->
                    <div class="survey_suffix" style="display: none;">
                        <div class="inner">
                            <div class="suffix_content"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body></html>