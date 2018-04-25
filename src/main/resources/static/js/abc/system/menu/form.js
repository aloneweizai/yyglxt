/**
 * Created by liuqi on 2017/5/22.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","ztree","nicevalidator","nicevalidator.zh_CN","abc.common","layui"], function ($) {

        $(function () {
            //点击返回
            $('body').on('click', '.js_back', function(){
               window.location.href = $(".js_currLink").val();
            });

            //点击取消 隐藏对话框
            $('body').on('click', '.js_close', function(){
                $(".main_modal").hide();
            });
            //form表单提交弹出框
            $(".js_save_btn").click(function (e) {
                if($validatorFrom.isValid()){
                    var radioVal = $("input[type='radio']:checked").val();
                    var parentType = $("[name='parentType']").val();
                    if(radioVal == '1'){
                        if(parentType == '2' || parentType == '3'){
                            abc.layerAlert(false, '目录需选目录为上一级');
                            return;
                        }
                    }
                    if(radioVal == '2'){
                        if(parentType=='2' || parentType=='3'){
                            abc.layerAlert(false, '菜单需选目录为上一级');
                            return;
                        }
                    }
                    if(radioVal == '3' && parentType!='2'){
                        abc.layerAlert(false, '按钮需选菜单为上一级');
                        return;
                    }
                    abc.layerAjaxConfirm("POST", $("form").attr('action'), $("form").serializeJson(), $(".js_currLink").val());
                }
            });

            /**
              form页面  目录：菜单名称，上级菜单，排序号，图标；
              菜单：菜单名称，上级菜单，菜单URL，授权标识，排序号
              按钮: 菜单名称	,上级菜单, 菜单URL, 授权标识
            */
             var initRadio = function(radioVal){
                if(radioVal == '1') {
                    $("#td_menu_name").show();
                    $("#td_parent_menu").show();
                    $("#td_menu_url").hide();
                    $("#td_perms").hide();
                    $("#td_sort").show();
                    $("#td_icon").show();
                }
                if(radioVal == '2') {
                    $("#td_menu_name").show();
                    $("#td_parent_menu").show();
                    $("#td_menu_url").show();
                    $("#td_perms").show();
                    $("#td_sort").show();
                    $("#td_icon").hide();
                }
                if(radioVal == '3') {
                    $("#td_menu_name").show();
                    $("#td_parent_menu").show();
                    $("#td_menu_url").show();
                    $("#td_perms").show();
                    $("#td_sort").hide();
                    $("#td_icon").hide();
                }
            };
            //initRadio($("input[type='radio']:checked").val());
            $(":radio").click(function () {
                initRadio($(this).val());
            });

            var setting = {
                view: {dblClickExpand: false,showLine: false},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                        zTree.expandNode(treeNode);
                        //点击给上级菜单栏位赋值；
                        if(treeNode.id == -1){
                            $("#parentName").val('');
                            $("[name='parentId']").val('');
                            $("[name='parentType']").val('');
                        }else{
                            $("#parentName").val(treeNode.name);
                            $("[name='parentId']").val(treeNode.id);
                            $("[name='parentType']").val(treeNode.type);
                        }
                    }
                }
            };

            var zNodes = [{ id:-1, pId:-2, name:"系统模块菜单", open:true}];

            var parentId = $("[name='parentId']").val();
            $(".menu_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-menu-id");
                obj.pId= $(this).attr("data-menu-pid");
                obj.name= $(this).attr("data-menu-name");
                obj.type= $(this).attr("data-menu-type");
                if(obj.pId =="" || obj.pId =="null"){
                    obj.pId = -1;
                }
                obj.myAttr = $(this).attr("data-menu-id");
                zNodes.push(obj);
                //初始化为parentType栏位赋值
                if(parentId == obj.id){
                    $("[name='parentType']").val(obj.type);
                }
            });
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            //点击上级菜单弹出树形结构层
            $("#parentName").click(function(){
                $(".js_pop_menu").show();
            });
            //数据校验
            var $validatorFrom = $("form").validator({
                theme: 'yellow_top',
                timely: 1,
                fields: {
                    "menuName": "required;length[2~32];",
                    "menuUrl": "length[~128];",
                    "perms": "length[~500];",
                    "icon": "length[~64];",
                    "sort":"integer;range[0~1000];"
                }
            });
            $validatorFrom.validator().trigger("showtip");
        });
    });
});
