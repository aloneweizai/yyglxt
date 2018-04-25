<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <title>调查问卷</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_recycle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_overview.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_stat_chart.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <style>input[type="button"]{margin: 2px;}</style>
</head>
<body class="g_wrapper g_wrapper_full page_stat_overview page_stat page_stat_chart page_stat_recycle">
<input type="hidden" value="${question.id}" name="questionId">
<div id=loading style="position:fixed; left:50px; top:20; width:100%; height:100%; z-index:10000;display: block;background-color: black;opacity: 0.4">
    <img src="${ctx}/images/loading1.gif" style="position: fixed;width: 100px;height: 100px;left: 50%;top: 50%;margin-left: -50px;margin-top: -50px">
</div>
<div class="g_subheader">
    <div class="g_content">
        <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item" href="${ctx}/cms/questionnaire/list.php">问卷列表</a>
                <#if "1,3"?contains(question.status)>
                    <a class="nav_item" href="${ctx}/cms/questionnaire/edit/${question.id}.php">编辑问卷</a>
                </#if>
                <a class="nav_item current" href="javascript:;">统计分析</a>
            </div>
        </div>
    </div>
</div>
<div id="container" class="g_container">
    <div class="container_wrapper">
        <div class="container_inner">
            <div class="stat_sidebar">
                <div class="stat_sidebar">
                    <ul>
                        <li class="sidebar_item  current" test="n1">
                            <a class="overview_ico iconLink " href="#">
                                <div class="icon"><i class="needle"></i> </div>
                                <div class="wording">回收概况</div>
                            </a>
                        </li>
                        <li class="sidebar_item" test="n2">
                            <a class="recycle_ico iconLink current" href="#">
                                <div class="icon"> <i class="recycle_inner_line recycle_inner_line_1"></i> <i class="recycle_inner_line recycle_inner_line_2"></i> <i class="recycle_inner_line recycle_inner_line_3"></i> </div>
                                <div class="wording">样本数据</div>
                            </a>
                        </li>
                        <li class="sidebar_item" test="n3">
                            <a class="chart_ico iconLink current" href="#">
                                <div class="icon"> <i class="left_fan recycle_fan"></i> <i class="right_fan recycle_fan"></i> <i class="bg_fan recycle_fan"></i>
                                    <div class="ie8Compacity"></div>
                                </div>
                                <div class="wording">统计图表</div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="n1" class="js_sidebar_div">
                <div id="overview">
                    <div class="overview_wrapper">
                        <div class="simple_modules clearfix" style="margin-bottom:5px;">
                            <div class="simple_module">
                                <div class="simple_module_inner" href="javascript:;">
                                    <h4 class="module_title">回收率</h4>
                                    <p class="module_data"><span class="number">${recovery!}</span></p>
                                    <p class="adding_word">回收率 = 回收量/访问量</p>
                                </div>
                            </div>
                            <div class="simple_module even">
                                <div class="simple_module_inner" href="javascript:;">
                                    <h4 class="module_title">平均完成时间</h4>
                                    <p class="module_data"><span class="number">${avgMinute!}</span><span>分</span><span class="number">${avgSecond!}</span><span>秒</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="recycle_module">
                            <div class="col-md-12 ny_statis">
                                <div>
                                    <input id="access_statistics_starttime" type="text" data-type="datebox" style=" width:130px;">
                                    <span>至</span>
                                        <input id="access_statistics_endtime" type="text" data-type="datebox" style=" width:130px;">
                                    <span>
                                        <a href="javascript:;" class="btn btn_small btn_blue btn_confirm js_btn_access_statistics_query">查询</a>
                                    </span>
                                </div>
                                <div id="access_statistics" class="container_line_wjtongji">
                                </div>
                            </div>
                        </div>

                        <div class="recycle_module">
                            <div class="col-md-12 ny_statis">
                                <div>
                                    <input id="answer_statistics_starttime" type="text" data-type="datebox" style=" width:130px;">
                                    <span>至</span>
                                    <input id="answer_statistics_endtime" type="text" data-type="datebox" style=" width:130px;">
                                    <span>
                                        <a href="javascript:;" class="btn btn_small btn_blue btn_confirm js_btn_answer_statistics_query">查询</a>
                                    </span>
                                </div>
                                <div id="answer_statistics" class="container_line_wjtongji">

                                </div>
                            </div>
                        </div>
                        <div class="js_access_statistics_div" hidden>

                                <input type="hidden" name="accessCounts"  <#if accessStatistics??> value="${accessStatistics.llcnts}"<#else>value="0"</#if>    >

                            <#if accessStatistics?? && accessStatistics.list?? && (accessStatistics.list?size>0) >
                                <#list accessStatistics.list as access>
                                    <input type="hidden" class="js_access_statistics" data-days="${access.tj}" data-counts="${access.cnt}">
                                </#list>
                            </#if>
                            <#if accessStatistics?? && accessStatistics.pclist?? && (accessStatistics.pclist?size>0) >
                                <#list accessStatistics.pclist as access>
                                    <input type="hidden" class="js_access_pc_statistics" data-days="${access.tj}" data-counts="${access.cnt}">
                                </#list>
                            </#if>
                            <#if accessStatistics?? && accessStatistics.mobileWeblist?? && (accessStatistics.mobileWeblist?size>0) >
                                <#list accessStatistics.mobileWeblist as access>
                                    <input type="hidden" class="js_access_mobile_statistics" data-days="${access.tj}" data-counts="${access.cnt}">
                                </#list>
                            </#if>
                        </div>
                        <div class="js_answer_statistics_div" hidden>
                            <input type="hidden" name="answerCounts"  <#if answerStatistics??> value="${answerStatistics.llcnts}"<#else>value="0"</#if>    >
                            <#if answerStatistics?? && answerStatistics.list?? && (answerStatistics.list?size>0) >
                                <#list answerStatistics.list as answer>
                                    <input type="hidden" class="js_answer_statistics" data-days="${answer.tj}" data-counts="${answer.cnt}">
                                </#list>
                            </#if>
                            <#if answerStatistics?? && answerStatistics.pclist?? && (answerStatistics.pclist?size>0) >
                                <#list answerStatistics.pclist as answer>
                                    <input type="hidden" class="js_answer_pc_statistics" data-days="${answer.tj}" data-counts="${answer.cnt}">
                                </#list>
                            </#if>
                            <#if answerStatistics?? && answerStatistics.mobileWeblist?? && (answerStatistics.mobileWeblist?size>0) >
                                <#list answerStatistics.mobileWeblist as answer>
                                    <input type="hidden" class="js_answer_mobile_statistics" data-days="${answer.tj}" data-counts="${answer.cnt}">
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
            <div id="n2" class="js_sidebar_div" style="display:none; background: #fff; border:1px solid #ddd;">
                <div id="recycle">
                    <div class="recycle_filter">
                        <div class="legend">
                            <span class="title">财税问卷调查</span>
                        </div>
                        <div class="info">
                            <#--<a class="info_icon info_btn delete_answer_result_btn btn js_del_answer" href="javascript:;"><i class="del_icon"></i></a>-->
                            <a href="javascript:;" class="download_btn info_btn btn js_btn_export_answer" data-href="${ctx}/cms/questionnaire/statistics/answer/export/${question.id}.php">导出答题记录</a>
                        </div>
                        <div class="query">
                            <div class="time">
                                <span class="label">选择时间</span>
                                <div class="date_wrap"><input id="answer_start_time" type="text" data-type="datebox" style="width:130px;"></div>
                                <span>至</span>
                                <div class="date_wrap"><input id="answer_end_time" type="text" data-type="datebox" style="width:130px;"></div>
                                <span><a href="javascript:;" class="btn btn_small btn_blue btn_confirm js_btn_query_answer">查询</a></span>
                            </div>
                            <div class="filters">
                                <div class="select_list_num">
                                    每页显示
                                    <select class="js_change_perpagenum" style="width: auto">
                                        <option value="15" selected="selected">15 条</option>
                                        <option value="20">20 条</option>
                                        <option value="50">50 条</option>
                                        <option value="100">100 条</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="table" style="display: block;">
                        <div class="data_table_wrapper" id="data_table_wrapper">
                            <div class="table_wrapper">
                                <table id="recycle_table">
                                    <thead>
                                        <tr>
                                            <#--<th class="input_th"><input class="survey_form_checkbox js_selectAll" type="checkbox" lay-skin="primary"  name="delete_checkbox_all" id="delete_checkbox_all"> <label for="delete_checkbox_all" class="del_input_label"> <i class="survey_form_ui"></i> </label> </th>-->
                                            <th>查看</th>
                                            <th>编号</th>
                                            <th>开始答题时间<span class="icos"> <span class="ico_up_arrow"></span> <span class="ico_down_arrow"></span> </span></th>
                                            <th>结束答题时间<span class="icos"> <span class="ico_up_arrow"></span> <span class="ico_down_arrow"></span> </span></th>
                                            <#if subjects?? && (subjects?size>0) >
                                                <#list subjects as subject>
                                                    <th>
                                                        <span>
                                                        ${subject_index + 1} .${subject.title?replace('<([^>]*)>',"",'r')}</span> <span class="icos"> <span class="ico_up_arrow"></span> <span class="ico_down_arrow"></span> </span>
                                                    </th>
                                                </#list>
                                            </#if>
                                        </tr>
                                    </thead>
                                    <tbody id="list" class="js-body-tr">
                                        <#if answerRecords?? && (answerRecords?size>0) >
                                            <#list answerRecords as record>
                                                <tr>
                                                    <#--<td class="input_td"><input class="survey_form_checkbox js_checkbox" type="checkbox" lay-skin="primary"  value="${record.id}" name="delete_checkbox" id="${record.id}"><label for="${record.id}" class="del_input_label"> <i class="survey_form_ui"></i></label></td>-->
                                                    <td ><a href="${ctx}/cms/questionnaire/statistics/answer/${question.id}/${record.id}.php" target="_blank"><span class="recycle_eye"></span></a></td>
                                                    <td >${record_index+1}</td>
                                                    <td >${record.startTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                                    <td >${record.endTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                                    <#if record.answerList?? && (record.answerList?size>0) >
                                                        <#if subjects?? && (subjects?size>0)>
                                                            <#list subjects as subject>
                                                                <td >
                                                                    <#list record.answerList as answer>
                                                                        <#if answer.subjectsId == subject.id>${answer.content!} </#if>
                                                                    </#list>
                                                                </td>
                                                            </#list>
                                                        </#if>
                                                    </#if>
                                                </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tableLoading" style="display: none;">
                            <img src="./css_files/loading-ffff.gif" alt="正在加载……">
                        </div>
                    </div>
                    <div id="infor" style="margin-bottom: 70px;">
                        <table width="100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
                    </div>
                </div>
            </div>
            <div id="n3" class="js_sidebar_div" style="display:none; background: #fff; border:1px solid #ddd;">
                <div class="chart_filter">
                    <div class="legend"><span class="title">答题统计</span></div>
                    <#--<div class="info">-->
                        <#--<a href="javascript:;" target="blank" class="download_btn">导出统计结果</a>-->
                    <#--</div>-->
                    <#--<div class="query">-->
                        <#--<div class="time">-->
                            <#--<span class="label">选择时间</span>-->
                            <#--<div class="date_wrap"><input id="start_time" type="text" data-type="datebox" style="width:130px;"></div><span>至</span>-->
                            <#--<div class="date_wrap"><input id="end_time" type="text" data-type="datebox" style="width:130px;"></div>-->
                            <#--<span><a href="javascript:;" class="btn btn_small btn_blue btn_confirm">查询</a></span>-->
                        <#--</div>-->
                    <#--</div>-->
                </div>
                <div id="chart">
                    <div class="pages_tab">
                        <a class="page_preview" href="javascript:;"><i></i></a>
                        <div class="pages_wrap">
                            <ul>
                                <#if pageSubjectMap?? &&(pageSubjectMap?size>0)>
                                    <#list pageSubjectMap?keys as key>
                                        <li <#if key_index==0>class="page_tab active"<#else>class="page_tab"</#if>  data-pid="${key}">第${key_index+1}页</li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                        <a class="page_next" href="javascript:;"><i></i></a>
                    </div>
                    <div class="page_panels">
                    <#assign subjectIndex = 0>
                        <#if pageSubjectMap?? &&(pageSubjectMap?size>0)>
                            <#list pageSubjectMap?keys as key>
                                <div <#if key_index==0> class="page_panel active"<#else>class="page_panel"</#if> data-pid="${key}">
                                    <#assign subjects = pageSubjectMap[key]>
                                    <#if subjects?? && (subjects?size > 0)>
                                        <#list subjects as subject>
                                            <#assign subjectIndex = subjectIndex + 1>
                                            <div class="question question_radio" data-id="" data-type="radio">
                                                <#if subject.optionType == '3' || subject.optionType == '4'|| subject.optionType == '5'><a href="${ctx}/cms/questionnaire/statistics/subjectAnswer/export/${subject.id}.php" target="_blank" class="exportDataButton">导出分析数据</a></#if>
                                                <div class="title"> ${subjectIndex}.${subject.title?replace('<([^>]*)>',"",'r')}</div>
                                                <#if subject.optionType == '1' || subject.optionType == '2'>
                                                    <div class="table_wrap">
                                                        <div  class="dataTables_wrapper no-footer">
                                                            <table class="dataTable no-footer" role="grid">
                                                                <thead>
                                                                <tr role="row">
                                                                    <th class="table_title sorting" tabindex="0" rowspan="1" colspan="1" style="width: 658px;">选项</th>
                                                                    <th class="table_value sorting" tabindex="0"  rowspan="1" colspan="1"  style="width: 200px;">小计</th>
                                                                    <th class="table_value sorting" tabindex="0"  rowspan="1" colspan="1"  style="width: 200px;">百分比</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                        <#if subjectsdtxxtjBos?? && (subjectsdtxxtjBos?size>0)>
                                                                            <#list subjectsdtxxtjBos as subjectdtxxtj>
                                                                                <#if subjectdtxxtj.id == subject.id>
                                                                                    <#assign subjectdtxxtjCnt = subjectdtxxtj.cnt>
                                                                                    <#assign dtlist = subjectdtxxtj.dtlist><#break>
                                                                                </#if>
                                                                            </#list>
                                                                        </#if>
                                                                        <#list subject.optionList as option>
                                                                            <#if subjectsdtxxtjBos?? && dtlist?? && (dtlist?size>0)>
                                                                                <#list dtlist as dt>
                                                                                    <#if dt.content == option.optionString>
                                                                                        <#assign cnt = dt.cnt><#break>
                                                                                    </#if>
                                                                                </#list>
                                                                            </#if>
                                                                            <tr role="row">
                                                                                <td class="table_title">${option.optionString}</td>
                                                                                <td class="table_value">${cnt!0}</td>
                                                                                <td class="table_value">
                                                                                    <#if cnt?? && subjectdtxxtjCnt?? && subjectdtxxtjCnt!=0>
                                                                                        ${cnt/subjectdtxxtjCnt*100}
                                                                                    <#else>0</#if>
                                                                                </td>
                                                                            </tr>
                                                                            <#assign cnt = 0>
                                                                        </#list>
                                                                    <#assign subjectdtxxtjCnt = 0>
                                                                    <#assign cnt = 0>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </#if>
                                            </div>
                                        </#list>
                                    </#if>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/cms/questionnaire/statistics.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</html>