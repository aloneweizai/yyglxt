/**
 * Created by liuqi on 2017/5/26.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","ztree","nicevalidator","nicevalidator.zh_CN","abc.common","layui"], function ($) {
        $(function () {
            //点击返回
            $('body').on('click', '.js_back', function(){
                window.location.href = $(".js_currLink").val();
            });

            //点击重置密码
            $('body').on('click', '.js_reset_password', function(){
                abc.ajaxConfirm("POST", $(this).attr("data-href"), '','','重置密码为:abc@12366','操作成功','操作失败');
            });

            //点击取消 隐藏对话框
            $('body').on('click', '.js_close', function(){
                $(".main_modal").hide();
            });

            //form表单弹出框
            $(".js_save_btn").click(function (e) {
                e.preventDefault();
                if($validatorFrom.isValid()){
                    var strongRegex = new RegExp("^(?=.{8,16})(((?=.*[A-z])(?=.*[0-9]))|((?=.*[A-z])(?=.*\\W))|((?=.*\\W)(?=.*[0-9]))).*$", "g");
                    if ($("input[name='id']").val()=='' &&  strongRegex.test($("[name='password']").val())==false) {
                        abc.layerAlert(false, '密码需要8-16位，数字、字母、特殊字符中至少两种组成');
                        return;
                    }
                    if($("[name='password']").val() != $("[name='confirm_password']").val()){
                        abc.layerAlert(false, '密码与密码确认不一致');
                        return;
                    }
                    var roleIds_str = "";
                    $("input[name='roleIds_arr']:checked").each(function(){
                        roleIds_str = roleIds_str + $(this).val() + ",";
                    });
                    roleIds_str = roleIds_str.substring(0,roleIds_str.length-1);
                    $("input[name='roleIds']").val(roleIds_str);
                    abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), $(".js_currLink").val());
                }
            });
            var setting = {
                view: {dblClickExpand: false,showLine: false},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        //只有部门下面可以添加员工
                        if(treeNode.type == '2'){
                            $("[name='orgName']").val(treeNode.name);
                            $("[name='orgId']").val(treeNode.id);
                        }else{
                            $("[name='orgName']").val("");
                            $("[name='orgId']").val("");
                        }
                    }
                }
            };

            var zNodes = [];
            $(".org_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-id");
                obj.pId= $(this).attr("data-pid");
                obj.name= $(this).attr("data-name");
                obj.type= $(this).attr("data-type");
                if($(this).attr("data-pid") =="" || $(this).attr("data-pid") =="null"){
                    obj.pId = '';
                }
                //obj.open = true;
                obj.myAttr = $(this).attr("data-id");
                zNodes.push(obj);
            });
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            //点击组织选择按钮 弹出树形结构层
            $(".js_choose_btn").click(function(){
                $(".js_pop_ztree").show();
            });
            //数据校验
            var $validatorFrom = $("form").validator({
                theme: 'yellow_top',
                timely: 1,
                fields: {
                    "username": "required;length[6~32];",
                    "password": "required;length[8~16];",
                    "confirm_password":"required;length[8~16];",
                    "nickname": "required",
                    "phone": "mobile",
                    "orgName": "required",
                }
            });
            $validatorFrom.validator().trigger("showtip");

            $('[name="password"]').keyup(function () {
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


        });
    });
});
