/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","abc.common","layui","../abc/util/page"], function ($, Editor) {

        //初始化富文本框
        var editor;
        $('body').on('click', '.js-query', function(){
            var name = $.trim($("#name").val());//
            location.href = ctx+'/bangbang/factionLevel/list.php?name='+name;
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
                url : ctx+"/bangbang/factionLevel/edit.php",
                type : "GET",
                async: false,
                success : function(data){
                    $(".teacher-popup").html(data);
                    $(".teacher-popup").show().css({zIndex:100});
                    $("div.bg").show();
                    layui.use('form', function(){
                        var form = layui.form;
                        form.render();
                    });
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
                    layui.use('form', function(){
                        var form = layui.form;
                        form.render();
                    });
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

            if(!$.trim($("[name='name']").val())){
                layer.msg("请输入帮派名称", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='code']").val())){
                layer.msg("请输入帮派等级", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='honorValue']").val())){
                layer.msg("请输入荣誉值", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='peopleLimit']").val())){
                layer.msg("请输入人数限制", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var reg=/(^[1-9]\d{0,9}$)/;
            if(!reg.test($("[name='honorValue']").val())){
                layer.msg("荣誉值需为{1-9}位正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!reg.test($("[name='peopleLimit']").val())){
                layer.msg("人数限制需为{1-9}位正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }

            abc.layerAjaxConfirm("POST", $("#factionLevel_form").attr('action'), $("form").serializeJson(), $(".js_currLink").val());
        });
    });
});