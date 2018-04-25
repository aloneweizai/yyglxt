/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","abc.common","layui","../abc/util/page"], function ($, Editor) {

        //初始化富文本框
        $('body').on('click', '.js-query', function(){
            var keywords = $.trim($("#keywords").val());
            location.href = ctx+'/bangbang/sensitiveWords/list.php?keywords='+keywords;
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

        ////list批量删除
        //$(".js_del_batch_btn").click(function(){
        //    var ids =[];
        //    $(".js_checkbox:checked").each(function(i){
        //        ids.push($(this).val());
        //    });
        //    if(ids.length==0){
        //        abc.layerAlert(false,"请勾选要复选框");
        //    }else{
        //        abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), JSON.stringify(ids), $(".js_currLink").val());
        //    }
        //});

        //新增
        $(".js_add").click(function(){
            $.ajax({
                url : ctx+"/bangbang/sensitiveWords/edit.php",
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
            var formObj = JSON.parse($("form").serializeJson());
            if(!$.trim(formObj.keywords)){
                abc.layerAlert(false,"请输入敏感词汇");
            }else{
                abc.layerAjaxConfirm("POST", $("#sensitiveWords_form").attr('action'), JSON.stringify(formObj), $(".js_currLink").val());
            }
        });

    });
});