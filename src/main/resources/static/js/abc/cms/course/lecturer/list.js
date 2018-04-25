/**
 * Created by LiuQi
 */

require(["../../../../config"], function () {
    require(["jquery-3.1.0","wangEditor","abc.common","layui"], function ($, Editor) {

        //初始化富文本框
        var editor;
        $('body').on('click', '.js-query', function(e){
            e.preventDefault();
            var keywords = $.trim($("#keywords").val());//讲师姓名
            location.href = ctx+'/cms/course/lecturer/list.php?lecturerName='+keywords;
        });

        //list页面 删去
        $('body').on('click', '.js_delete', function(){
            abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '', $(".js_currLink").val());
        });

        //list批量删除
        $(".js_del_batch_btn").click(function(){
            var ids =[];
            $(".js_checkbox:checked").each(function(i){
                ids.push($(this).val());
            });
            if(ids.length==0){
                abc.layerAlert(false,"请勾选要复选框");
            }else{
                abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), JSON.stringify(ids), $(".js_currLink").val());
            }
        });

        //新增
        $(".js_add").click(function(e){
            e.preventDefault();
            $.ajax({
                url : ctx+"/cms/course/lecturer/edit.php",
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

        $('body').on('click', '#boxCancelBtn,.teacher-close', function(){
            $(".teacher-popup").hide();
            $("div.bg").hide();
        });

        //新增，修改 讲师
        $('body').on('click', '.js_save', function(){
            var formObj = JSON.parse($("form").serializeJson());
            //formObj.intro = editor.txt.html();
            var lecturerName = $("[name='lecturerName']").val();
            var username = $("[name='username']").val();
            var phone = $("[name='phone']").val();
            var lecturerQQ = $("[name='lecturerQQ']").val();
            var lecturerEmail = $("[name='lecturerEmail']").val();
            if(lecturerName==''){
                abc.layerAlert(false, '操作失败: 讲师姓名不能为空');
                return false;
            }
            if(lecturerName.length>15){
                abc.layerAlert(false, '操作失败: 讲师姓名长度需为[1-15]');
                return false;
            }
            if(username==''){
                abc.layerAlert(false, '操作失败: 讲师用户名不能为空');
                return false;
            }
            if(formObj.intro==''){
                abc.layerAlert(false, '操作失败: 讲师简介不能为空');
                return false;
            }
            if(formObj.intro.length >200){
                abc.layerAlert(false, '操作失败: 讲师简介需控制在200字以内');
                return false;
            }
            $.ajax({
                url : ctx+"/consumerManager/consumer/info/"+username+".php",
                type : "GET",
                async: false,
                success : function(data){
                    if(data && data.code=='2000' && data.data) {
                        abc.layerAjaxConfirm("POST", $("#form").attr('action'), JSON.stringify(formObj), $(".js_currLink").val());
                    }else{
                        abc.layerAlert(false, '操作失败: 讲师用户名不存在');
                    }
                },
                error : function(){
                    abc.layerAlert(false, '操作失败: 讲师用户名不存在');
                }
            });
        });

        //验证 用户名是否存在
        $('body').on('click', '.js_valid_username', function(){
            var username = $("[name='username']").val();
            if(username){
                $.ajax({
                        url : ctx+"/cms/course/validUsername/"+username+".php",
                        type : "GET",
                        async: false,
                        success : function(data){
                            if(data && data.code=='2000') {
                                var user = data.user;
                                var userExt = data.user_extend;
                                $("[name='userId']").val(user.id);
                                $("[name='lecturerEmail']").val('');
                                $("[name='phone']").val('');
                                $("[name='lecturerQQ']").val('');
                                $("[name='lecturerName']").val('');
                                if(user.regMail){
                                    $("[name='lecturerEmail']").val(user.regMail);
                                }
                                if(user.phone){
                                    $("[name='phone']").val(user.phone);
                                }
                                if(userExt.qq){
                                    $("[name='lecturerQQ']").val(userExt.qq);
                                }
                                if($.trim($("[name='lecturerName']").val())==''){
                                    $("[name='lecturerName']").val(userExt.realName);
                                }
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



    });
});