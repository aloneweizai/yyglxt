/**
 * Created by LiuQi
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/date","qrcode","layui"], function ($, abc_date,qr_code) {
        $(function () {

            $(".js_blank_text").each(
                function(i,item){
                    var text = $.trim($(this).text());
                    var  title = text.replace(/\_+/g, function(word){
                        var html = '<input class="fill_blank needsclick" role="textbox"  style="width: '+word.length*10+'px;" >';
                        return html;
                    });
                    $(this).html(title);
                    if($(this).parents(".question_radio").hasClass("required")){
                        $(this).addClass("change");
                    }
                }
            );



            var checkRequired =  function(){
                var curr_page_div = $(".survey_page:visible");//当前显示的页面
                curr_page_div.find(".question").each(function(i,item){
                    if($(this).hasClass("required")){
                        var errorHtml = '<span class="tips_error" tabindex="0">这道题必须回答哦</span>';
                        var optionType = $(this).attr("data-subject-optionType");
                        if(optionType == '1'|| optionType == '2'){
                            if($(this).find(".survey_form_checkbox:checked").length == 0){
                                $(this).find(".tips").html(errorHtml);
                            }
                        }
                        if(optionType == '3' || optionType == '4'){
                            var inputVal = $.trim($(this).find(".survey_form_input").val());
                            if(inputVal ==''){
                                $(this).find(".tips").html(errorHtml);
                            }
                        }
                        if(optionType == '5'){
                            var _this = $(this);
                            _this.find(".fill_blank").each(function(i,item){
                                if($.trim($(this).val()) == ''){
                                    _this.find(".tips").html(errorHtml);
                                    return false;
                                }
                            });
                        }
                    }
                });
                if(curr_page_div.find(".tips_error").length > 0){
                    var obj = curr_page_div.find(".tips_error:first");
                    var des =obj[0].offsetTop;
                    $("body,html").animate({scrollTop:des},"slow");
                    return false;
                }else{
                    return true;
                }
            }

            /* 点击上一页 */
            $('body').on('click', '.survey_control .survey_prevpage', function(){
                var curr_page_div = $(".survey_page:visible");//当前显示的页面
                var prev_page = curr_page_div.prev();
                prev_page.show().siblings().hide();//显示下一页，隐藏其他页
                $(".survey_btn.survey_nextpage").show();
                $(".survey_btn.survey_submit").hide();
                if(prev_page.prev().length > 0){
                    $(".survey_btn.survey_prevpage").show();
                }else{
                    $(".survey_btn.survey_prevpage").hide();
                }
            });

            /* 点击下一页 */
            $('body').on('click', '.survey_control .survey_nextpage', function(){
                if(!checkRequired()){
                    return;
                }
                var curr_page_div = $(".survey_page:visible");//当前显示的页面
                var next_page = curr_page_div.next();
                next_page.show().siblings().hide();//显示下一页，隐藏其他页
                //上一页，下一页，提交按钮
                $(".survey_btn.survey_prevpage").show();
                if(next_page.next().length > 0){
                    $(".survey_btn.survey_nextpage").show();
                    $(".survey_btn.survey_submit").hide();
                }else{//显示上一页 和 提交页面
                    $(".survey_btn.survey_nextpage").hide();
                    $(".survey_btn.survey_submit").show();
                }
            });

            /* 点击提交 */
            $('body').on('click', '.survey_control .survey_submit', function(){
                if(!checkRequired()){
                    return;
                }
                $(".survey_control,.survey_container,.survey_prefix").hide();
                $(".survey_suffix").show();
                if($(".js_answer_result").length>0){
                    $(".js_answer_result").show();
                }
                return;
                //提交数据库
                var answerlog={};
                answerlog.questionId = $("[name='questionId']").val();
                answerlog.startTime = abc_date.getDate($("[name='startTime']").val());
                answerlog.endTime = new Date();
                answerlog.accessTerminal = 'pc';
                var answerList = [];
                //循环题目
                $(".question").each(function(){
                    var _this = $(this);
                    var answer = {};
                    answer.subjectsId = _this.attr("data-id");
                    var option_data_type = _this.attr("data-subject-optionType");
                    if(option_data_type == '1'){
                        answer.content = _this.find(".survey_form_checkbox:checked").parents(".option_item").find(".option_text p").text();
                        answerList.push(answer);
                    }
                    if(option_data_type == '2'){
                        _this.find(".survey_form_checkbox:checked").each(function(i, item){
                            var content = $(this).parents(".option_item").find(".option_text p").text();
                            var answer = {};
                            answer.subjectsId = _this.attr("data-id");
                            answer.content = content;
                            answerList.push(answer);
                        });
                    }
                    if(option_data_type == '3' || option_data_type == '4'){
                        answer.content = _this.find(".survey_form_input").val();
                        answerList.push(answer);
                    }
                    if(option_data_type == '5'){
                        answer.content = _this.find(".fill_blank").val();
                        answerList.push(answer);
                    }
                });
                answerlog.answerList = answerList;
                $.ajax({
                    type: "post",
                    url: ctx+"/cms/questionnaire/preview/submit.php",
                    data: JSON.stringify(answerlog),
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            var answerId = data.data.id;
                            var questionId = data.data.questionId;
                            if($(".js_answer_result").length>0){
                                $(".js_answer_result").attr("href",ctx+"/cms/questionnaire/statistics/answer/"+questionId+"/"+answerId+".php");
                                $(".js_answer_result").show();
                            }
                            $(".survey_control,.survey_container,.survey_prefix").hide();
                            $(".survey_suffix").show();
                        } else {
                            //TODO
                        }
                    }
                });
            });

            /* 绑定radio选中事件 */
            $('body').on('click', '.option_item input:radio', function(){
                $(this).parents(".question").find(".tips").empty();
            });

            /* 绑定checkbox选中事件 */
            $('body').on('click', '.option_item input:checkbox', function(){
                var subjectDiv = $(this).parents(".question");
                if(subjectDiv.hasClass("required") && subjectDiv.find(".survey_form_checkbox:checked").length == 0){
                    subjectDiv.find(".tips").html('<span class="tips_error" tabindex="0">这道题必须回答哦</span>');
                }else{
                    subjectDiv.find(".tips").empty();
                }
            });

            /*绑定 文本框失焦事件*/
            $('body').on('blur', '.survey_form_input', function(){
                var subjectDiv = $(this).parents(".question");
                var inputVal = $.trim($(this).val());
                if(subjectDiv.hasClass("required") && inputVal ==''){
                    subjectDiv.find(".tips").html('<span class="tips_error" tabindex="0">这道题必须回答哦</span>');
                }else{
                    subjectDiv.find(".tips").empty();
                }
            });
            $('body').on('blur', '.fill_blank', function(){
                var subjectDiv = $(this).parents(".question");
                var tipsError = false;
                if(subjectDiv.hasClass("required")){
                    subjectDiv.find(".fill_blank").each(function(i,item){
                        if($.trim($(this).val()) == ''){
                            tipsError = true;
                            return false;
                        }
                    });
                    if(tipsError){
                        subjectDiv.find(".tips").html('<span class="tips_error" tabindex="0">这道题必须回答哦</span>');
                    }else{
                        subjectDiv.find(".tips").empty();
                    }
                }
                else{
                    subjectDiv.find(".tips").empty();
                }
            });

            /* 分享 */
            $(".btn_share_box").click(function() {
                $("#share_div").show();
            });
            $(".ui-dialog-close, .ui-dialog-autofocus").click(function() {
                $("#share_div").hide();
            });
            $('.copy_link').on('click',function (){
                $("#share_link").select(); // 选择对象
                document.execCommand("Copy"); // 执行浏览器复制命令
                layer.alert("已复制好，可贴粘。", {icon: 1,closeBtn: 0});
            });

            var qrcode = new qr_code(document.getElementById("qrcode"), {
                width : 180,//设置宽高
                height : 180
            });
            var share_url = $("#share_link").val();
            //share_url = share_url.replace("testsns.abc12366.com","118.118.116.105:8888");
            var wx_url = $("#wxUrl").val() + encodeURIComponent(share_url) + "#wechat_redirect";
            qrcode.makeCode(wx_url);

            /* 打印 */
            $(".js_print").click(function(){
                var oldBody = window.preview_pc_iframe.document.body.innerHTML;
                console.log(oldBody);
                var container = window.preview_pc_iframe.document.getElementsByClassName("survey_wrap");
                var $container = $(container);
                $container.css("border","none");
                $container.find(".survey_page").show();
                $container.find(".survey_control").hide();
                window.preview_pc_iframe.print();
                window.preview_pc_iframe.document.body.innerHTML = oldBody;
            });
        });
    });
});