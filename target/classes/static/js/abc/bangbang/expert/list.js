/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","wangEditor","abc.common","layui","abc.ajaxfileupload","../abc/util/page"], function ($, Editor) {

        //初始化富文本框
        var editor;
        $('body').on('click', '.js-query', function(){
            var username = $.trim($("#username").val());
            var realName = $.trim($("#realName").val());
            var phone = $.trim($("#phone").val());
            var type = $.trim($("#type").val());
            location.href = ctx+'/bangbang/questionExpert/list.php?username='+username+'&realName='+realName+'&phone='+phone+'&type='+type;
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
                url : ctx+"/bangbang/questionExpert/edit.php",
                type : "GET",
                async: false,
                success : function(data){
                    $(".teacher-popup").html(data);
                    $(".teacher-popup").show().css({zIndex:100});
                    $("div.bg").show();

                    editor = new Editor(".jd_editor");///
                    editor.customConfig.uploadImgServer = ctx+'/util/wangEditorUpload/courseLecturer.php';
                    editor.customConfig.menus =['head', 'bold', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'image', 'table', 'video', 'code', 'undo', 'redo'];
                    editor.create();
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

                    editor = new Editor(".jd_editor");///courseLecturer
                    editor.customConfig.uploadImgServer = ctx+'/util/wangEditorUpload/courseLecturer.php';
                    editor.customConfig.menus =['head', 'bold', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'image', 'table', 'video', 'code', 'undo', 'redo'];
                    editor.create();
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
            if($("[name='username']").val() ==''){
                layer.msg("请输入用户名", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if($("#form_username").val() =='' || $("#form_username").val() != $("[name='username']").val()){
                layer.msg("请先验证用户名", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if($("[name='type']").val() ==''){
                layer.msg("请选择专家类型", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if($("[name='yearWork']").val() ==''){
                layer.msg("请输入从业时间", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var reg=/^[1-9]\d*$/;
            if(!reg.test($("[name='yearWork']").val())){
                layer.msg("从业时间需为正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if($("[name='yearWork']").val() ==''){
                layer.msg("请输入从业时间", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if($("[name='goodField']").val() ==''){
                layer.msg("请输入擅长领域", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            if(editor.txt.text() ==''){
                layer.msg("请输入专家介绍", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var formObj = JSON.parse($("form").serializeJson());
            formObj.intro = editor.txt.html();
            var username = $("[name='username']").val();
            $.ajax({
                url : ctx+"/consumerManager/consumer/info/"+username+".php",
                type : "GET",
                async: false,
                success : function(data){
                    if(data && data.code=='2000' && data.data) {
                        abc.layerAjaxConfirm("POST", $("#expert_form").attr('action'), JSON.stringify(formObj), $(".js_currLink").val());
                    }else{
                        layer.msg("用户名不存在", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                    }
                },
                error : function(){
                    abc.layerAlert(false, '操作失败: 用户名不存在');
                }
            });
        });

        //验证 用户名是否存在
        $('body').on('click', '.js_valid_username', function(e){
            e.preventDefault();
            var username = $("[name='username']").val();
            if(username){
                $.ajax({
                        url : ctx+"/bangbang/questionExpert/validUsername/"+username+".php",
                        type : "GET",
                        async: false,
                        success : function(data){
                            if(data && data.code=='2000') {
                                var user = data.user;
                                var userExt = data.user_extend;
                                $("[name='userId']").val(user.id);
                                $("#form_username").val(username);
                                $(".js_phone").text(user.phone);
                                $(".js_nickname").text(user.nickname);
                                $(".js_realName").text(userExt.realName);
                                if(user.userPicturePath){
                                    $("#userImage").attr("src",imgUrl+user.userPicturePath);
                                    $("[name='userImage']").val(user.userPicturePath);
                                }
                                layer.alert("验证成功", {icon: 1});
                            }else{
                                layer.alert("验证失败", {icon: 5});
                            }
                        },
                        error : function(){
                            layer.alert("验证失败", {icon: 5});
                        }
                    });
            }else{
                layer.alert("验证失败", {icon: 5});
            }
        });



        //上传用户图片
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
                    if(width<480 || height<270){
                        $("#uploadFile").val('');
                        abc.layerAlert(false,'图片尺寸至少为480px*270px(像素)');
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
                            url : ctx+'/util/doFileupload.php?path=expert',
                            type : 'post',
                            secureuri : false, // 一般设置为false
                            fileElementId : 'uploadFile', // 上传文件的id、name属性名
                            dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                            success : function(data, status) {
                                obj.show();
                                $("#uploadMsg").html('');
                                if(data.code=='200'){
                                    $("#userImage").attr("src", imgUrl+data.message);
                                    $("[name='userImage']").val(data.message);
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