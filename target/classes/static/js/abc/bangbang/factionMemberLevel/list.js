/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","abc.common","layui","../abc/util/page"], function ($, Editor) {

        //初始化富文本框
        var editor;
        $('body').on('click', '.js-query', function(){
            var name = $.trim($("#name").val());//
            location.href = ctx+'/bangbang/factionMemberLevel/list.php?name='+name;
        });

        $('body').on('click', '.js_status', function(){
            abc.layerAjaxConfirm("POST", $(this).attr("data-href"),
                JSON.stringify({id:$(this).attr("data-id"), status:$(this).attr("data-status")}),
                $(".js_currLink").val());
        });

        //list页面 删去
        $('body').on('click', '.js_delete', function(){
            abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '', $(".js_currLink").val());
        });

        //新增
        $(".js_add").click(function(){
            $.ajax({
                url : ctx+"/bangbang/factionMemberLevel/edit.php",
                type : "GET",
                async: false,
                success : function(data){
                    $(".teacher-popup").html(data);
                    $(".teacher-popup").show().css({zIndex:100});
                    $("div.bg").show();

                }
            });
        });
        //编辑
        $(".js_edit").click(function(){
            $.ajax({
                url : $(this).attr("data-href"),
                type : "GET",
                async: false,
                success : function(data){
                    $(".teacher-popup").html(data);
                    $(".teacher-popup").show().css({zIndex:100});
                    $("div.bg").show();

                }
            });
        });
        $('body').on('click', '.js_close,.teacher-close', function(e){
            e.preventDefault();
            $(".teacher-popup").hide();
            $("div.bg").hide();
        });

        //新增，修改
        $('body').on('click', '.js_save', function(e){
            e.preventDefault();

            if(!$.trim($("[name='code']").val())){
                layer.msg("请输入帮手等级", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='name']").val())){
                layer.msg("请输入帮手名称", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='answers']").val())){
                layer.msg("请输入回答数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='discussions']").val())){
                layer.msg("请输入讨论数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='adoptions']").val())){
                layer.msg("请输入采纳数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var reg=/(^(0|[1-9]\d{0,9})$)/;
            if(!reg.test($("[name='answers']").val())){
                layer.msg("回答数需为0或者{1-9}位正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!reg.test($("[name='discussions']").val())){
                layer.msg("讨论数需为0或者{1-9}位正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!reg.test($("[name='adoptions']").val())){
                layer.msg("采纳数需为0或者{1-9}位正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var formObj = JSON.parse($("form").serializeJson());
            abc.layerAjaxConfirm("POST", $("#factionMemberLevel_form").attr('action'), JSON.stringify(formObj), $(".js_currLink").val());
        });
    });
});