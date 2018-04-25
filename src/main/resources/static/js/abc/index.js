require(["../config"], function () {
    require(["jquery.full","abc.common"], function ($) {
        $(function () {
            var main_inav_ul_li_dl = $(".main_inav ul li dl")
            var main_inav_ul_li = $(".main_inav ul li");
            main_inav_ul_li.eq(0).find("dl").show();
            $(".main_inav ul li").first().find(" .main_inav_one").addClass("border_l_3")
            //未点击前


            //无二级导航li无class，有时class加nav_has
            main_inav_ul_li.each(function () {
                if ($(this).find("dd").length == 0) {
                    $(this).find("span").removeClass("glyphicon-menu-left glyphicon-menu-down").addClass("glyphicon-menu-right")

                } else {
                    $(this).addClass("nav_has")

                }
            });
            $(".main_inav ul li dl").click(function () {
                return false;
            })


            //nav_has下点击事件
            main_inav_ul_li.click(function () {
                $(this).siblings(".nav_has").find("span").addClass("glyphicon-menu-down")
                $(this).siblings().find(".main_inav_one").addClass("border_l_3")
                var main_inav_ul_li_dl = $(this).find("dl")
                main_inav_ul_li_dl.slideToggle("500");

                $(".main_inav_one").toggleClass('border_l_3')
                $(this).siblings().find("dl").slideUp("slow")
                if ($(this).find("dd").length !== 0) {

                    $(this).find("span").toggleClass('glyphicon-menu-down')
                }

            });

            $(".main_inav_add_t").on("click",function() {

                if ($(this).next($(".main_inav_add_d")).css("display") == 'none') {

                    $(".main_inav_add_d").slideUp("500")
                    $(this).next($(".main_inav_add_d")).slideDown("500");
                } else { $(this).next($(".main_inav_add_d")).slideUp("500"); }

            })

            //按钮
            var $main_itop_an = $("#main_itop_an");
            var $main_ileft = $("#main_ileft,#main_itop_l");
            var $main_iright_ny = $("#main_iright_ny,#main_itop_l_ny");

            if (document.body.clientWidth >= "760") {
                $main_itop_an.on("click", function () {

                    $(".tabs-header").css("width","auto");
                    $(".tabs-wrap").css("width","auto");
                    $(".tabs-panels").css("width","auto");
                    $(".panel-noscroll").css("width","auto");
                    $(".easyui-fluid").css("width","auto");
                    $(".panel-body-noborder").css("width","auto");

                    if ($main_ileft.width() >= 220) {
                        $main_ileft.animate({"width": "50px"}, 500);
                        $("#main_iright").animate({"left":"50px"}, 500);
                        $main_iright_ny.animate({"left": "50px"}, 500);
                        $("#main_ileft").addClass("main_inav_s");
                        $(".main_inav .nav_has dl").css({"display": "block"})
                        $("#main_ileft").addClass("overflow_inherit");

                    }
                    else {
                        $main_ileft.animate({"width": "230px"}, 500);
                        $("#main_iright").animate({"left":"230px"}, 500);
                        $main_iright_ny.animate({"left": "230px"}, 500);
                        $("#main_ileft").removeClass("main_inav_s");
                        $(".main_inav .nav_has dl").css({"display": "none"})
                        $("#main_ileft").removeClass("overflow_inherit");
                    }

                    setTimeout(function () {
                        $(".easyui-fluid").trigger("_resize");
                    }, 500)
                })
            } else {
                $("#main_ileft").hide();
                $main_itop_an.click(function () {
                    $("#main_ileft").toggle();

                })
            }

            //换肤
            $(".main_ileft_skin ul li").click(function () {
                var skin = $(this).attr("data-value");
                $("body").removeClass().addClass(skin);
            });

            //退出系统
            $(".js_loginout").click(function(e){
                e.preventDefault();
                layer.confirm('确认退出？', {title:'操作提示',btn: ['确认','取消'],icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                    function(){
                        window.location.href = ctx+"/login/out.php";
                    }
                );
            });

            //点击取消 隐藏对话框
            $('body').on('click', '.js_close', function(){
                $("[name='oldPw'], [name='newPw'], [name='newPwConfirm']").val("");
                cleanPasswordForm();
                $(".main_modal").hide();
            });

            //修改密码
            $(".js_alter_pw").click(function(){
                $(".js_pop_pw").show();
                $("[name='oldPw'], [name='newPw'], [name='newPwConfirm']").val("");
                cleanPasswordForm();
            });

            //修改密码提交
            $(".js_alter_pw_save").click(function(){
                if(!$validatorFrom.isValid()){
                    return;
                }
                var oldPw = $("[name='oldPw']").val();
                var newPw = $("[name='newPw']").val();
                var newPwConfirm = $("[name='newPwConfirm']").val();
                if(!(oldPw && newPw && newPwConfirm)){
                    layer.alert("输入框不能为空", {offset: '200px',icon: 2,closeBtn: 0});
                    return;
                }
                var strongRegex = new RegExp("^(?=.{8,16})(((?=.*[A-z])(?=.*[0-9]))|((?=.*[A-z])(?=.*\\W))|((?=.*\\W)(?=.*[0-9]))).*$", "g");

                if (strongRegex.test(newPw) == false) {
                    abc.layerAlert(false, '密码需要8-16位，数字、字母、特殊字符中至少两种组成');
                    return;
                }
                if(newPw==oldPw){
                	layer.alert("新密码和旧密码不能相同", {offset: '200px',icon: 2,closeBtn: 0});
                    return;
                }
                if(newPw != newPwConfirm){
                    layer.alert("2次输入新密码不相同", {offset: '200px',icon: 2,closeBtn: 0});
                    return;
                }
                if(newPw.length < 8 || newPw.length > 16){
                    layer.alert("密码长度需要在8~16位之间", {offset: '200px',icon: 2,closeBtn: 0});
                    return;
                }
                layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                    function(){
                        abc.block();
                        $.ajax({
                            type: "POST",
                            url: ctx+"/system/operator/password.php?oldPw="+oldPw+"&newPw="+newPw,
                            data: '',
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                abc.unblock();
                                if (data && data.code == "2000") {
                                	$(".js_close").show();
                                    abc.layerAlert(true, '保存成功');
                                    cleanPasswordForm();
                                    $(".main_modal").hide();
                                } else {
                                    abc.layerAlert(false, '保存失败: '+data.message);
                                }
                            }
                        });
                    }
                );
            });
            var $validatorFrom = $("form").validator({
                theme: 'yellow_top',
                timely: 1,
                fields: {
                    "oldPw": "required;length[8~16];",
                    "newPw": "required;length[8~16];",
                    "newPwConfirm":"required;length[8~16];",
                }
            });
            $validatorFrom.validator().trigger("showtip");

            $('[name="newPw"]').keyup(function () {
                var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
                var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
                var enoughRegex = new RegExp("(?=.{8,}).*", "g");
                if (false == enoughRegex.test($(this).val())) {
                    $('#pwd_Weak').removeClass('pwd_Weak_c');
                    $('#pwd_Medium').removeClass('pwd_Medium_c');
                    $('#pwd_Strong').removeClass('pwd_Strong_c');
                    //密码小于六位的时候，密码强度图片都为灰色
                }
                else if (strongRegex.test($(this).val())) {
                    $('#pwd_Weak').addClass('pwd_Weak_c');
                    $('#pwd_Medium').addClass('pwd_Medium_c');
                    $('#pwd_Strong').addClass('pwd_Strong_c');
                    //密码为八位及以上并且字母数字特殊字符三项都包括,强度最强
                }
                else if (mediumRegex.test($(this).val())) {
                    $('#pwd_Weak').addClass('pwd_Weak_c');
                    $('#pwd_Medium').addClass('pwd_Medium_c');
                    $('#pwd_Strong').removeClass('pwd_Strong_c');
                    //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等
                }
                else {
                    $('#pwd_Weak').addClass('pwd_Weak_c');
                    $('#pwd_Medium').removeClass('pwd_Medium_c');
                    $('#pwd_Strong').removeClass('pwd_Strong_c');
                    //如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的
                }
                return true;
            });


            var cleanPasswordForm = function(){
                $validatorFrom.validator("cleanUp");
                $('#pwd_Weak').removeClass('pwd_Weak_c');
                $('#pwd_Medium').removeClass('pwd_Medium_c');
                $('#pwd_Strong').removeClass('pwd_Strong_c');
            }
            
            if(isInitPassword=="true"){
            	layer.alert("系统检测到您正在使用初始密码,需要修改密码才能继续操作", {offset: abc.winscrollTop(200),icon: 7,closeBtn: 0}, function(index){
	               	 layer.close(index);
	               	 $(".js_close").hide();
	                 $(".js_alter_pw").click();
                });
            }
            
            
           
          
            
        });

    });
});