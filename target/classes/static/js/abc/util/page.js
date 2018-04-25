/**
 * Created by LiuQi
 */
define(['jquery-3.1.0','layui'],function(){

    $("body").on("click",".js_page_location", function(){
        if($(this).attr("data-ajax") == "true"){
            $.ajax({
                type: 'GET',
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    $(".js_page_div").html(data);
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

    ////全选checkbox
    //$("body").on("click",".js_selectAll", function(e){
    //    if($(this).attr("data-check")=='true'){
    //        $(this).attr("data-check",false);
    //        $.each($(".js_checkbox"),function (){
    //            this.checked=false;
    //            $(this).next().removeClass("layui-form-checked");
    //        });
    //    }else{
    //        $(this).attr("data-check",true);
    //        $.each($(".js_checkbox"),function (){
    //            this.checked="checked";
    //            $(this).next().addClass("layui-form-checked");
    //        });
    //    }
    //});


});
