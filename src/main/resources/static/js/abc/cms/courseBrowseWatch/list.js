/**
 * Created by LiuQi
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0", "../abc/util/date", "abc.common", "layui"], function ($, date) {

        var init_layUi = function(){
            layui.use('form', function(){
                var form = layui.form;
                form.render();
                var laydate = layui.laydate;
                lay('.date-item').each(function () {
                    laydate.render({
                        elem: this
                    })
                });
                laydate.render({
                    elem: '.date-month',type: 'month'
                })
            });
        };

        $.ajax({
            type: "GET",
            url: initPageLink,
            data: '',
            async: false,
            success: function (data) {
                $(".js_page_div").html(data);
                init_layUi();
            }
        });

        $(".js_tab li").click(function(){
            if($(this).hasClass("noe")){
                return;
            }else{
                $(this).addClass("noe").siblings().removeClass("noe");
                $.ajax({
                    type: 'GET',
                    url: $(this).attr("data-href"),
                    async: false,
                    success: function (data) {
                        $(".js_body_div").html(data);
                        init_layUi();
                    }
                });
            }
        });

        $("body").on("click",".js_query_day",function(e){
            e.preventDefault();
            abc.block();
            $.ajax({
                type: "GET",
                url: ctx+"/cms/courseBrowseWatch/list_day.php?date="+$("#date").val(),
                async: false,
                success: function (data) {
                    abc.unblock();
                    $(".js_page_div").html(data);
                    init_layUi();
                }
            });
        });

        $("body").on("click",".js_query_month",function(){
            abc.block();
            $.ajax({
                type: "GET",
                url: ctx+"/cms/courseBrowseWatch/list_month.php?month="+$("#month").val(),
                async: false,
                success: function (data) {
                    abc.unblock();
                    $(".js_page_div").html(data);
                    init_layUi();
                }
            });
        });


        $("body").on("click",".js_page_location", function(){
            if($(this).attr("data-ajax") == "true"){
                $.ajax({
                    type: 'GET',
                    url: $(this).attr("data-href"),
                    async: false,
                    success: function (data) {
                        $(".js_page_div").html(data);
                        init_layUi();
                        layui.use('form', function() {
                            var form = layui.form;
                            form.render();
                        });
                    }
                });
            }else{
                location.href = $(this).attr("data-href");
            }
        });

        $("body").on("keypress","#go_page", function(e){
            if(e.keyCode==13){
                $(".js_go_page").click();
                return false;
            }
        });

        $("body").on("click",".js_go_page", function(e){
            var page = $('#go_page').val();
            var url = $(this).attr("data-href");
            if($(this).attr("data-ajax") == "true"){
                if (page.match("^[1-9][0-9]*$")) {
                    $.ajax({
                        type: 'GET',
                        url: url.replace("[:page]", page),
                        async: false,
                        success: function (data) {
                            $(".js_page_div").html(data);
                            init_layUi();
                            layui.use('form', function() {
                                var form = layui.form;
                                form.render();
                            });
                        }
                    });
                } else {
                    return;
                }
            }else{
                if (page.match("^[1-9][0-9]*$")) {
                    location.href = url.replace("[:page]", page);
                }
            }
        });

        $('body').on('click', '.opendialog', function(e){
            abc.block();
            var url=ctx + $(this).attr("data-url");
            var iframe=document.getElementById("look_frame");
            iframe.src=url;
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    //$("#myModal3").fadeIn();
                    $("#lookModal").show();
                    $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(0)},500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function(){
                    //$("#myModal3").fadeIn();
                    $("#lookModal").show();
                    $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(0)},500);
                    abc.unblock();
                };
            }
        });

        $('body').on('click', '.lookModal', function(e){
            $("#lookModal").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#lookModal").hide();
            });
        });

    })
});