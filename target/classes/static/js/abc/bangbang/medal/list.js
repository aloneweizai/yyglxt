/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","abc.common","abc.ajaxfileupload","layui","../abc/util/page"], function ($, Editor) {

        //初始化富文本框
        var editor;
        $('body').on('click', '.js-query', function(){
            var name = $.trim($("#name").val());//
            location.href = ctx+'/bangbang/questionMedal/list.php?name='+name;
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
                url : ctx+"/bangbang/questionMedal/edit.php",
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
            if(!$.trim($("[name='image']").val())){
                layer.msg("请上传勋章图片", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='name']").val())){
                layer.msg("请输入勋章名称", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='type']").val())){
                layer.msg("请选择勋章类别", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='acquireCondition']").val())){
                layer.msg("请输入获取条件", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(!$.trim($("[name='description']").val())){
                layer.msg("请输入描述", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var formObj = JSON.parse($("form").serializeJson());
            abc.layerAjaxConfirm("POST", $("#medal_form").attr('action'), JSON.stringify(formObj), $(".js_currLink").val());
        });


        //上传图片
        $('body').on('click', '#upload_img_btn', function(){
            var obj = $(this);
            var _val=$("#uploadFile").val();
            if(_val==""){
                abc.layerAlert(false,'请选择上传图片');
                return;
            }
            if($("#uploadFile")[0].files[0].size>204800){
                $("#uploadFile").val('');
                abc.layerAlert(false,'图片大小不能超过200KB');
                return;
            }
            //读取图片数据
            var reader = new FileReader();
            reader.readAsDataURL($("#uploadFile")[0].files[0]);
            reader.onload = function (e) {
                var data = e.target.result;
                //加载图片获取图片真实宽度和高度
                var image = new Image();
                image.onload=function(){
                    var width = image.width;
                    var height = image.height;
                    if(width<270 || height<270){
                        $("#uploadFile").val('');
                        abc.layerAlert(false,'图片尺寸至少为270px*270px(像素)');
                        return;
                    }else{
                        var types=$("#uploadFile").attr('data-type').split(';');
                        var type=_val.substring(_val.lastIndexOf(".")+1);
                        obj.hide();
                        $("#uploadMsg").html('正在上传.....');
                        if(types.indexOf(type)<0){
                            abc.layerAlert(false,'允许类型:['+obj.attr('data-type')+"]");
                            obj.show();
                            $("#uploadMsg").html('');
                            return;
                        }
                        $.ajaxFileUpload({
                            url : ctx+'/util/doFileupload.php?path=medal',
                            type : 'post',
                            secureuri : false, // 一般设置为false
                            fileElementId : 'uploadFile', // 上传文件的id、name属性名
                            dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                            success : function(data, status) {
                                obj.show();
                                $("#uploadMsg").html('');
                                if(data.code=='200'){
                                    $("#medalImage").attr("src", imgUrl+data.message);
                                    $("[name='image']").val(data.message);
                                }
                            },
                            error : function(data, status, e) {
                                obj.show();
                            }
                        });
                    }
                };
                image.src= data;
            };
        });

    });
});