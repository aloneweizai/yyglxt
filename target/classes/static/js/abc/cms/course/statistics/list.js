/**
 * Created by LiuQi
 */
require(["../../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/date","layui","abc.common","../abc/util/page"], function ($, date) {
        $.ajax({
            type: 'GET',
            url: ctx+'/cms/course/statistics/study/'+curriculumId+'.php',
            data: '',
            async: false,
            success: function (data) {
                $(".js_body_div").html(data);
                date.init();
            }
        });

        $(".js_tab a").click(function(){
            if($(this).hasClass("active")){
                return;
            }else{
                abc.block();
                $(this).addClass("active").siblings().removeClass("active");
                $.ajax({
                    type: 'GET',
                    url: $(this).attr("data-href"),
                    async: true,
                    success: function (data) {
                        $(".js_body_div").html(data);
                        date.init();
                        abc.unblock();
                    }
                });
            }
        });

        $('body').on('click', '.js_query_study, .js_query_sign', function(e){
            abc.block();
            e.preventDefault();
            var name = $(this).attr("data-name");
            $.ajax({
                type: 'GET',
                url: ctx+'/cms/course/statistics/'+name+'/'+curriculumId+'.php?username='+$("#keywords").val()+'&begintime='+$("#study_starttime").val()+'&endtime='+$("#study_endtime").val(),
                async: true,
                success: function (data) {
                    $(".js_body_div").html(data);
                    date.init();
                    abc.unblock();
                }
            });
        });
        $('body').on('click', '.js_query_order', function(e){
            abc.block();
            var goodsId = $(this).attr("data-goodsId");
            $.ajax({
                type: 'GET',
                url: ctx+'/cms/course/statistics/order/'+curriculumId+'.php?username='
                +$("#keywords").val()+'&startTime='+$("#study_starttime").val()+'&endTime='+$("#study_endtime").val(),
                async: true,
                success: function (data) {
                    $(".js_body_div").html(data);
                    date.init();
                    abc.unblock();
                }
            });
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
                    $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(-400)},500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function(){
                    //$("#myModal3").fadeIn();
                    $("#lookModal").show();
                    $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(-400)},500);
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