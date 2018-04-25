require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/date","qrcode","nicevalidator","nicevalidator.zh_CN","abc.common"], function ($, abc_date,qr_code) {
        $(function () {
                 //初始化题目
                 var init_subjectId_arr = [];
                 if($(".js_init_subject").length>0){
                     $(".js_init_subject").each(function(){
                         init_subjectId_arr.push($(this).attr("data-subject-id"));
                     });
                 }
//右侧的题目控件
                //单击 单选,多选  单行,多行,填空 题目控件，显示 编辑框
                $(".question_type .type_item").click(function(){
                    var curr_page_div = $(".survey_container .page:visible");
                    if(curr_page_div.find(".editor_question").length>0){
                        return;
                    }
                    var subject_number = 1;
                    var last_subject = curr_page_div.find(".question:last");
                    if(last_subject.length>0){
                        subject_number = parseInt(last_subject.attr("data-subject-number"))+1;
                    }
                    curr_page_div.append($("."+$(this).attr("data-page-div")).html());
                    curr_page_div.find(".editor_question").attr("data-subject-number",subject_number);
                    curr_page_div.find(".editor_question #question_type").find("option[value='"+$(this).attr("data-type-code")+"']").attr("selected",true);

                    var obj = $(".survey_container .page:visible").find(".question:last");
                    if(obj[0]){
                        var des =obj[0].offsetTop;
                        $("body,html").animate({scrollTop:des},"slow");
                    }
                });
            var timer = null;
            function moveSlowly(des,start,time){
                     clearInterval(timer);
                     var speedTime = time || 100;
                     var start = start || 0;
                     var distance = des - start;
                     var speed = distance/speedTime;
                     var i = 1;
                     var pos = start;

                     timer = setInterval(function(){
                             if(i == speedTime){
                                     pos = des;
                                     document.documentElement.scrollTop = document.body.scrollTop = pos;
                                     clearInterval(timer);
                                 }else{
                                     pos = pos + speed;
                                     document.documentElement.scrollTop = document.body.scrollTop = pos;
                                     i++;
                                 }

                         },1)
                 }
//初始化 题目编号
                 var initPageShowSubjectIndex = function(pid){
                     var curr_page_item = $(".pages_wrap ul .pages_item[data-pid='"+pid+"']");
                     var curr_page_div = $(".survey_container .page[data-pid='"+pid+"']");
                     var prev_page_item = curr_page_item.prev();
                     var prev_subject_number='0';
                     if(prev_page_item.length > 0){
                         prev_subject_number = $(".survey_container .page[data-pid='"+prev_page_item.attr("data-pid")+"']").find(".question_index:last").text();
                         if(!prev_subject_number){
                             prev_subject_number ='0';
                         }
                     }
                     //修改题目编号
                     curr_page_div.find(".question").each(function(i,item){
                         $(this).find(".question_index").text(parseInt(prev_subject_number)+i+1);
                     });
                 };
//点击pages_next
//             $(".pages_next").click(function(){
//                 alert($("#pages_wrap_ul").width());
//                 if($("#pages_wrap_ul").width()>700){
//                     $("#pages_wrap_ul").css("margin-left","-300px");
//                 }
//             });

//点击pager_more
             $(".js_pages_more").click(function(){
                 $(".pages_wrap .pages_item").removeClass("current");
                 var newPageId = parseInt($("li.pages_item:last").attr("data-pid"))+1;
                 var pageItemSize = $(".pages_wrap ul li").length;
                 if(pageItemSize==7){
                     abc.layerAlert(false, '问卷不能超过7页');
                     return;
                 }
                 var newPageHtml = '<li class="pages_item current" data-pid="'+newPageId+'" data-page-show="'+(pageItemSize+1)+'"><span class="pages_show">第'+(pageItemSize+1)+'页</span><a class="pages_remove" href="javascript:;">×</a></li>';
                 $("li.pages_item:last").after(newPageHtml);
                 $(".survey_container .page, .survey_title, .survey_prefix,.survey_suffix").hide();
                 $(".survey_container").append('<div class="page" data-pid='+newPageId+' style="display: block;"></div>');
             });
//点击page_item
             $('body').on('click', '.pages_wrap li.pages_item', function(){
                 $(".pages_wrap .pages_item").removeClass("current");
                 $(this).addClass("current");
                 $(".survey_container .page, .survey_suffix").hide();
                 if($(this).attr("data-page-show") =='1'){
                     $(".survey_title, .survey_prefix").show();
                 }else{
                     $(".survey_title, .survey_prefix").hide();
                 }
                 $(".survey_container .page[data-pid='"+$(this).attr("data-pid")+"']").show();//显示对应的div
                 initPageShowSubjectIndex($(this).attr("data-pid"));
             });
            $(".pages_item.pages_end").click(function(){//点击结束页
                $(".pages_wrap li.pages_item").removeClass("current");
                $(".survey_container .page, .survey_title, .survey_prefix").hide();
                $(this).addClass("current");$(".survey_suffix").show();
            });
//点击pages_remove
             $('body').on('click', '.pages_wrap .pages_remove', function(e){
                e.stopPropagation();
                if($(".pages_wrap ul li").length==1){
                    abc.layerAlert(false, '不能删去最后一页');
                    return;
                }
                 var page_item = $(this).parents(".pages_item");
                 var pid = page_item.attr("data-pid");
                 var page_div = $(".survey_container .page[data-pid='"+pid+"']");
                 var select_page_item;
                 if(page_item.next().length>0){
                     select_page_item = page_item.next();
                 }else{
                     select_page_item = page_item.prev();
                 }
                 //如果该页面没有题目
                 if(page_div.find(".question").length==0){
                     page_div.remove(); page_item.remove();//删除page的item和div
                     $(".survey_suffix").hide();
                     if(select_page_item.prev().length==0){
                         $(".survey_title, .survey_prefix").show();
                     }
                     $(".pages_wrap ul li").each(function(i,item){
                         $(this).attr("data-page-show",i+1);
                         $(this).find(".pages_show").text("第"+(i+1)+"页");
                     });
                     var showpid = select_page_item.attr("data-pid");
                     select_page_item.addClass("current");$(".survey_container .page[data-pid='"+showpid+"']").show();//显示被选中page的item和div
                     initPageShowSubjectIndex(showpid);
                     return;
                 }
                 $.ajax({
                     type: "delete",
                     url: ctx+"/cms/questionnaire/subjects/"+pid+"/"+$("[name='surveyId']").val()+".php",
                     data: '',
                     async: false,
                     contentType: "application/json",
                     dataType: "JSON",
                     success: function (data) {
                         if (data && data.success) {
                             //$(".pages_wrap .pages_item").removeClass("current");
                             page_div.remove(); page_item.remove();//删除page的item和div
                             $(".survey_suffix").hide();
                             if(select_page_item.prev().length==0){
                                 $(".survey_title, .survey_prefix").show();
                             }
                             $(".pages_wrap ul li").each(function(i,item){
                                 $(this).attr("data-page-show",i+1);
                                 $(this).find(".pages_show").text("第"+(i+1)+"页");
                             });
                             var showpid = select_page_item.attr("data-pid");
                             select_page_item.addClass("current");$(".survey_container .page[data-pid='"+showpid+"']").show();//显示被选中page的item和div
                             initPageShowSubjectIndex(showpid);
                         } else {
                             abc.layerAlert(false, "系统异常");
                         }
                     }
                 });
             });
//标题 失去焦点
            var saveTitle = function(){
                var status = $("[name='question_status']").val();
                if(status == ''){
                    status = 3;
                }
                if($.trim($(".js_title_content").text()) ==''){
                    abc.layerAlert(false, "标题不能为空");
                    return;
                }
                if($.trim($(".js_title_content").text()).length >100){
                    abc.layerAlert(false, "标题不能为空");
                    return;
                }
                $.ajax({
                    type: "post",
                    url: ctx+"/cms/questionnaire/edit.php",
                    data: JSON.stringify({id:$("[name='surveyId']").val(), title: $.trim($(".js_title_content").text()), status:status, simpleDesc:$.trim($(".js_prefix_content").html()), endDesc:$.trim($(".js_suffix_content").html())}),
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data && data.success) {
                            $("[name='surveyId']").val(data.data.id);
                            layer.msg("保存成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:500});
                        } else {
                            abc.layerAlert(false, data.message);
                        }
                    }
                });
            };
            $(".js_title_content,.js_prefix_content").blur(function(){
                saveTitle();
            });

            $(".js_suffix_content").blur(function(){
                saveTitle();
            });
//点击 复制题目按钮， 复制题目
                $('body').on('click', '.control_btn.copy', function(){
                    var subjectDiv = $(this).parents(".question");
                    var subjectId = subjectDiv.attr("data-subject-id");
                    var nextSubjects = [];
                    subjectDiv.nextAll().each(function(i){
                        var subject = {};
                        subject.id = $(this).attr("data-subject-id");
                        subject.number = parseInt($(this).attr("data-subject-number")) + 1;
                        nextSubjects.push(subject);
                    });
                    $.ajax({
                        type: "post",
                        url: ctx+"/cms/questionnaire/subject/copy/"+subjectId+".php",
                        data: JSON.stringify(nextSubjects),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (data && data.success) {
                                var copy = data.data;
                                //处理页面
                                subjectDiv.after(subjectDiv.prop("outerHTML"));
                                var addSubject = subjectDiv.next();
                                addSubject.attr("data-subject-id",copy.id);
                                addSubject.attr("data-subject-number",copy.number);
                                addSubject.find(".delete").show();
                                initPageShowSubjectIndex(subjectDiv.parents(".page").attr("data-pid"));
                                addSubject.nextAll().each(function(i){
                                    $(this).attr("data-subject-number", parseInt($(this).attr("data-subject-number")) + 1);
                                });
                            } else {
                                abc.layerAlert(false, "系统异常");
                            }
                        }
                    });
                });
//点击 编辑题目按钮， 编辑题目
                $('body').on('click', '.control_btn.edit', function(){
                    var subjectDiv = $(this).parents(".question");
                    subjectDiv.hide();
                    var optionType = subjectDiv.attr("data-type");
                    var subjectId = subjectDiv.attr("data-subject-id");
                    //var title = subjectDiv.find(".title_text").find("p").text();
                    var title = subjectDiv.find(".title_text").html();
                    var simpleDesc = subjectDiv.find(".description_text").find("p").text();
                    var subjectNumber = subjectDiv.attr("data-subject-number");
                    var flag = false;
                    if($(".js_isSubmitPapers").val()=='true' && init_subjectId_arr.length>0 && $.inArray(subjectId, init_subjectId_arr)>= 0){
                        flag = true;
                    }
                    var editorDiv;
                    if($.inArray(optionType, ['3','4','5']) >= 0){
                        subjectDiv.after($(".js-div-text-subject").html());
                        editorDiv = $(".survey_container .page .editor_question");
                    }
                    if($.inArray(optionType, ['1','2']) >= 0){
                        subjectDiv.after($(".js-div-option-subject").html());
                        editorDiv = $(".survey_container .page .editor_question");
                    }
                    editorDiv.attr("data-subject-id",subjectId);
                    editorDiv.attr("data-subject-number",subjectNumber);
                    editorDiv.find("#question_type").find("option[value='"+optionType+"']").attr("selected",true);
                    //if(title ==''){
                    //    editorDiv.find(".editor_title").find("p").html('<br>');
                    //}else{
                    //    editorDiv.find(".editor_title").find("p").text(title);
                    //}

                    editorDiv.find(".editor_title .question_title").html(title);

                    if(simpleDesc ==''){
                        editorDiv.find(".editor_description").find("p").html('<br>');
                    }else{
                        editorDiv.find(".editor_description").find("p").text(simpleDesc);
                    }
                    if(!subjectDiv.hasClass("required")){
                        editorDiv.find('[name="question_required"]').attr("checked",false);
                    }
                    if(flag){
                        editorDiv.find("#question_type").attr("disabled",true);
                    }
                    if($.inArray(optionType, ['1','2']) >= 0){
                        editorDiv.find(".normal_options_list").find("li").remove();
                        var options = subjectDiv.find(".inputs .option_item");
                        options.each(function(i,item){
                            var p_txt = $(this).find("p").text();
                            if(p_txt ==''){
                                p_txt = '<br>';
                            }
                            var optionHtml = '<li class="option_item"><span class="handle"></span>'
                                            +'<div class="option_input_wrap"><div class="mod_editor inline_editor option_text cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true"  style="position: relative;">'
                                            +'<p>'+p_txt+'</p></div></div>';
                                            if(!flag){
                                                optionHtml = optionHtml +'<a href="javascript:;" class="btn btn_del btn_del_option">×</a>';
                                            }
                            editorDiv.find(".normal_options_list").append(optionHtml);
                        });
                    }
                });
//点击 删去题目按钮， 删去题目
            $('body').on('click', '.control_btn.delete', function(){
                var subjectDiv = $(this).parents(".question");
                var subjectId = subjectDiv.attr("data-subject-id");
                var pid = subjectDiv.parents(".page").attr("data-pid");
                $.ajax({
                    type: "delete",
                    url: ctx+"/cms/questionnaire/subjects/del/"+subjectId+"/"+$("[name='surveyId']").val()+".php",
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data && data.success) {
                            subjectDiv.remove();
                            initPageShowSubjectIndex(pid);
                        } else {
                            abc.layerAlert(false, "系统异常");
                        }
                    }
                });
            });
//点击 题目编辑框的确认按钮
            $('body').on('click', '.page #editor_confirm_btn', function() {
                try {
                    //获取题目，备注， 题目类型
                    var editor_div = $(this).parents(".editor_question");
                    //var title = editor_div.find(".editor_title").find("p").text();
                    var title = $.trim(editor_div.find(".editor_title .question_title").html());
                    if (title == '<p><br></p>') {
                        throw("题目不能为空");
                    }
                    if(title.substr(title.length-4) == '<br>'){
                        title = title.substr(0, title.length-4);
                    }
                    var simpleDesc = editor_div.find(".editor_description").find("p").text();
                    var optionType = editor_div.find("#question_type").val();
                    var isRequired = editor_div.find("input[name='question_required']").is(':checked');
                    var subject = {};
                    subject.id = editor_div.attr("data-subject-id");
                    subject.questionId = $("[name='surveyId']").val();
                    if (subject.questionId == "") {
                        saveTitle();
                        subject.questionId = $("[name='surveyId']").val();
                    }
                    subject.title = $.trim(title);
                    subject.simpleDesc = simpleDesc;
                    subject.optionType = optionType;
                    subject.isQuestion = '0';
                    if (isRequired) {
                        subject.isRequired = 1;
                    } else {
                        subject.isRequired = 0;
                    }
                    subject.pages = editor_div.parents(".page").attr("data-pid");
                    subject.number = editor_div.attr("data-subject-number");
                    //获取 选项
                    var li_options = editor_div.find(".normal_options_list li");
                    //如果是单选和多选，至少需要2个选项
                    if($.inArray(optionType, ['1','2']) >= 0){
                        if(li_options.length<=1){
                            throw("选择题至少需要2个选项");
                        }
                    }
                    var subject_options = [];
                    li_options.each(function (index, item) {
                        var option = {};
                        //option.optionString = $(this).find("p").text();
                        option.optionString = $.trim($(this).find(".option_input_wrap div").text());
                        if (option.optionString == '') {
                            throw("选项值不能为空");
                        }
                        option.status = true;
                        option.sequence = index+1;
                        subject_options.push(option);
                    });
                    subject.optionList = subject_options;
                    $.ajax({
                        type: "post",
                        url: ctx + "/cms/questionnaire/subjects/edit.php",
                        data: JSON.stringify(subject),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (data && data.success) {
                                if (editor_div.attr("data-subject-id") != '') {
                                    var subject_div = editor_div.prev();
                                    if (subject_div.is(":hidden")) {
                                        subject_div.remove();
                                    }
                                }
                                var rs_subject = data.data;
                                var subjectId = rs_subject.id;
                                var subjectNumber = rs_subject.number;
                                var optionType = rs_subject.optionType;
                                var isRequiredClass = "";
                                if (isRequired) {
                                    isRequiredClass = "required";
                                }
                                var html = '<div class="question question_radio ' + isRequiredClass + '" data-subject-id="'
                                    + subjectId + '" data-subject-number="' + subjectNumber + '" data-type="' + optionType + '">'
                                    + '<div class="inner"><div class="title"><span class="title_index"><span class="question_index"></span>.</span>'
                                    //+ '<h3 class="title_text"><p>' + title + '</p></h3></div>'
                                    + '<h3 class="title_text">' + title + '</h3></div>'
                                    + '<div class="description"><div class="description_text"><p>'
                                    + simpleDesc + '</p></div></div>';
                                var inputs = '';
                                if (optionType == "1") {
                                    inputs = '<div class="inputs">';
                                    $(subject_options).each(function (i, item) {
                                        inputs = inputs + '<div class="option_item" style="width: 100%;" role="radio">'
                                            + '<input class="survey_form_checkbox" type="radio" value=""><label><i class="survey_form_ui"></i>'
                                            + '<div class="option_text"><p>' + item.optionString + '</p></div></label></div>';
                                    });
                                    inputs = inputs + '</div>';
                                }
                                if (optionType == "2") {
                                    inputs = '<div class="inputs">';
                                    $(subject_options).each(function (i, item) {
                                        inputs = inputs + '<div class="option_item" style="width: 100%;" role="checkbox">'
                                            + '<input class="survey_form_checkbox" type="checkbox" lay-skin="primary" value=""><label><i class="survey_form_ui"></i>'
                                            + '<div class="option_text"><p>' + item.optionString + '</p></div></label></div>';
                                    });
                                    inputs = inputs + '</div>';
                                }
                                if (optionType == "3") {
                                    inputs = '<div class="inputs"><input class="survey_form_input" type="text" size=""  value=""></div>';
                                }
                                if (optionType == "4") {
                                    inputs = '<div class="inputs"><textarea role="textbox" class="survey_form_input" rows="5" cols="60"></textarea></div>';
                                }
                                html = html + inputs + '</div><div class="control_mask"></div><div class="control"><ul><li class="control_btn edit"><b title="编辑"><i></i></b></li><li class="control_btn copy"><b title="复制"><i></i></b></li>';
                                if ($(".js_isSubmitPapers").val() == 'true' && $.inArray(subjectId, init_subjectId_arr) >= 0) {
                                    html = html + '</ul></div>';
                                } else {
                                    html = html + '<li class="control_btn delete"><b title="删除"><i></i></b></li></ul></div>';
                                }
                                //$(".survey_container .page").append(html);
                                editor_div.after(html);
                                editor_div.remove();//删去编辑框
                                initPageShowSubjectIndex(subject.pages);
                            } else {
                                abc.layerAlert(false, "系统异常");
                            }
                        }
                    });
                }catch(e){
                    abc.layerAlert(false, e);
                    return false;
                }
            });
            //单击取消按钮，删去 编辑框
            $('body').on('click', '#editor_cancel_btn', function(){
                $(this).parents(".editor_question").prev().show();
                $(this).parents(".editor_question").remove();
            });


            //点击新建选项+
            $('body').on('click', '.page .add_option', function(){
                var option = '<li class="option_item" data-id="o-101-EFGH" data-display="" data-goto=""> <span class="handle"></span>'
                    + '<div class="option_input_wrap">'
                    +    '<div class="mod_editor inline_editor option_text cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true" data-editor="editor58" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_1607" style="position: relative;">'
                    +    '<p>选项</p>'
                    +    '</div>'
                    +    '</div>'
                    +    '<a href="javascript:;" class="btn btn_del btn_del_option">×</a>'
                    +   '<div class="additional_setting"> <input class="no_random" type="checkbox" lay-skin="primary" style="display: none;"> <input class="correct_answer" type="checkbox" style="display: none;"> </div>'
                    +    '</li>';
                var options = $(this).parents(".options_list").find(".normal_options_list");
                options.append(option);
                options.find("p:last").focus();
            });
            //点击删去选项×
            $('body').on('click', '.page .btn_del_option', function() {
                $(this).parents("li").remove();
            });
            //optionType onChange触发
            $('body').on('change', '.js-question-type', function() {
                var optionType = $(this).val();
                var editor_options = $(this).parents(".editor_question").find(".editor_options");
                if(optionType =='1'|| optionType =='2'){
                    if(editor_options.length==0){
var editor_options_html = '<div class="row editor_options" style="">'
                        +'<ul class="options_list">'
                        +'<ul class="normal_options_list">';

var option_item_html = '<li class="option_item" data-id=""><span class="handle"></span>'
                        +'<div class="option_input_wrap"><div class="mod_editor inline_editor option_text cke_editable cke_editable_inline cke_contents_ltr cke_show_borders" contenteditable="true" data-editor="editor57" tabindex="0" spellcheck="false" role="textbox" aria-label="false" aria-describedby="cke_1580" style="position: relative;">'
                        +'<p>选项</p></div></div>'
                        +'<a href="javascript:;" class="btn btn_del btn_del_option">×</a>'
                        +'</li>';
    editor_options_html = editor_options_html +option_item_html +option_item_html
                        +'</ul><li class="option_item option_create"><div class="option_input_wrap"><span class="add_option">新建选项</span></div></li></ul></div>';
                        $(this).parents(".editor_type").after(editor_options_html);
                    }
                }else{
                    if(editor_options.length>0){
                        editor_options.remove();
                    }
                }
            });
//点击设置
            $("#survey_setting").click(function(){
                $("#set_up").show();
                if($("#dialog_setting_end_time").is(':checked')){
                    $("#end_time_display").show();
                    //$("#end_time_date").css("float","left").show();
                }
            });
            $("#setup_cancel").click(function(){ $("#set_up").hide(); });
            $("#setup_submit").click(function(){
                if($("[name='surveyId']").val()==''){
                    saveTitle();
                }
                var setup = {};
                setup.isDisplayNumber=0,setup.isBackspace=0,setup.isDisplayResults=0,
                setup.isLoginAuth=0,setup.isAnswerOnce=0,setup.isWxOnce=0,setup.isUserRange=0,
                setup.isAnswerRemind=0,setup.isPrize=0,setup.isSettingEndTime=0;
                $(".setting_content .btn_switch").each(function(){
                    if($(this).is(':checked')){
                        setup[$(this).attr("data-name")]=1;
                    }
                });
                if(setup.isSettingEndTime == 1){
                    var endTimeStr = $("[name='end_time_date']").val();

                    if(!endTimeStr){
                        abc.layerAlert(false, "请选择回收结束时间");
                        return;
                    }
                    setup.recoveryEndTime = abc_date.getDate(endTimeStr);
                    var now = new Date();
                    if(now.getTime() > setup.recoveryEndTime.getTime()){
                        abc.layerAlert(false, "回收结束时间不能小于当前时间");
                        return;
                    }
                }
                if(setup.isAnswerOnce == 1 && setup.isLoginAuth!=1){
                    abc.layerAlert(false, "每个用户只能回答一次 需要同时勾选 答题需要登录验证");
                    return;
                }
                $.ajax({
                    type: "post",
                    url: ctx+"/cms/questionnaire/setup/"+$("[name='surveyId']").val()+".php",
                    data: JSON.stringify(setup),
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data && data.success) {
                            $("#set_up").hide();
                            abc.layerAlert(true, "保存成功");
                        } else {
                            abc.layerAlert(false, "系统异常");
                        }
                    }
                });
            });

            $(".ico_checkbox.js_setting_end_time").click(function(){
                //$("#end_time_date").hide();
                if(!$("#dialog_setting_end_time").is(':checked')){
                    $("#end_time_display").show();
                }else{
                    $("#end_time_display").hide();
                }
            });

            $(".ico_checkbox.js_setting_answer_count").click(function(){
                if(!$("#dialog_setting_answer_count").is(':checked')){
                    $("#dialog_setting_login_check").attr("checked",true);
                }else{
                    $("#dialog_setting_login_check").removeAttr("checked");
                }
            });

//点击tab_item(题目控件 和 问卷大纲)
            $(".tab_item").click(function(){
                $(this).addClass("current").siblings().removeClass("current");
                $(this).parents(".editor_sidebar").find(".tab_content").hide();
                $(".tab_content."+$(this).attr("data-tab")).show();
                //如果点击的是：问卷大纲
                if($(this).attr("data-tab") == 'survey_outline'){
                    $("#menu").hide();$("#outline").show();
                    $("#outline .page_container").empty();
                    $(".survey_container .page").each(function(i,item){
                        var dl = '<dl class="outline_page" data-pid="'+$(this).attr("data-pid")+'"> <dt class="outline_page_title">第<span>'+(i+1)+'</span>页</dt><div class="question_container">'
                        var dds="";
                        $(this).find(".question").each(function(){
                            var _this = $(this);
                            var optionType;
                            var subject_title =_this.find(".title_text p").text();
                            var subject_data_type = _this.attr("data-type");
                            var type_map={ '1':'radio', '2':'checkbox','3':'text','4':'textarea','5':'text_multiple'};
                            optionType = type_map[subject_data_type];
                            var dd = '<dd class="outline_question '+optionType+'"> <span class="ico"><i></i></span><span class="question_index"></span>'+subject_title+'</dd>';
                            dds = dds + dd;
                        });
                        dl = dl+ dds+ "</div></dl>";
                        $("#outline .page_container").append(dl);
                    });
                    $("#outline .question_index").each(function(i){
                        $(this).text(i+1+".");
                    })
                }
            });
            $(".nav_item").click(function(){
                $(this).siblings().removeClass("current");
                $(this).addClass("current");
            });
            //点击选择皮肤
            $("#sidebar_theme, #sidebar_normal").click(function(){
                $(".editor_sidebar").hide();
                $("#"+$(this).attr("data-sidebar-id")).show();
            });
            //统计分析
            $("#analyse_link").click(function(){
                window.location.href = ctx+"/cms/questionnaire/statistics/"+$("[name='surveyId']").val()+".php";
            });
//发布，撤销
            $("#publish_survey").click(function(){
                if($("[name='surveyId']").val()==''){
                    saveTitle();
                }
                var questionId = $("[name='surveyId']").val();
                if(questionId !=''){
                    abc.layerAjaxConfirm("POST", ctx+"/cms/questionnaire/recycle/"+questionId+".php?status=0" , '',ctx+'/cms/questionnaire/list.php');
                }else{
                    abc.layerAlert(false, "系统异常");
                }
            });
//预览
            $("#preview_survey").click(function(){
                if($("[name='surveyId']").val()==''){
                    saveTitle();
                }
                var questionId = $("[name='surveyId']").val();
                if(questionId !=''){
                    window.open(ctx+"/cms/questionnaire/preview/"+questionId+'.php');
                }else{
                    abc.layerAlert(false, "系统异常");
                }
            });
//点击皮肤图片
            $(".themes_list .item").click(function(){
                if($("[name='surveyId']").val()==''){
                    saveTitle();
                }
                var questionId = $("[name='surveyId']").val();
                if(questionId !=''){
                    var imgSrc =  $(this).find("img").attr("src");
                    $.ajax({
                        type: "post",
                        url: ctx+"/cms/questionnaire/ajaxSkin/"+$("[name='surveyId']").val()+".php?imageUrl="+imgSrc,
                        data: '',
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (data && data.success) {
                                $(".survey_background_wrap").css("background-image","url("+imgSrc+")");
                            } else {
                                abc.layerAlert(false, "系统异常");
                            }
                        }
                    });
                }
            });
        })
    })
});