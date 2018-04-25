/**
 * Created by liuqi on 2017/5/24.
 */
require(["../../../config"], function () {
    require(["jquery.full","../abc/util/region","abc.common"], function ($) {

        $(function () {

            //点击返回
            $('body').on('click', '.js_back', function(){
                window.location.href = $(".js_currLink").val();
            });

            //点击取消 隐藏对话框
            $('body').on('click', '.js_close', function(){
                $(".main_modal").hide();
            });
            //form表单
            $(".js_save_btn").click(function (e) {
                e.preventDefault();
                if($validatorFrom.isValid()){
                    abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), $(".js_currLink").val());
                }
            });
            var setting = {
                view: {dblClickExpand: false,showLine: false},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                        zTree.expandNode(treeNode);
                        // 选择上级机构
                        if(treeNode.id == -1){
                            $("#parentName").text('');
                            $("[name='parentId']").val('');
                        }else{
                            $("#parentName").text(treeNode.name);
                            $("[name='parentId']").val(treeNode.id);
                        }
                    }
                }
            };
            var zNodes = [{ id:-1, pId: -2, name:"运营机构信息", open:true}];
            $(".org_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-id");
                obj.pId= $(this).attr("data-pid");
                obj.name= $(this).attr("data-name");
                if(obj.pId =="" || obj.pId =="null"){
                    obj.pId = -1;
                }
                obj.open = true;
                obj.myAttr = obj.id;
                zNodes.push(obj);
            });
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            //点击组织选择按钮 弹出树形结构层
            $(".js_choose_btn").click(function(){
                $(".js_pop_ztree").show();
            });
            //数据校验
            var $validatorFrom = $("form").validator({
                theme: 'yellow_right',
                timely: 1,
                fields: {
                    "type": "required",
                    "name": "required",
                    "code": "required",
                    "contact": "required",
                    "phone":"required;mobile",
                    "address": "required",
                    "province" :"required",
                    "city" :"required",
                    "area" :"required",
                }
            });
            $validatorFrom.validator().trigger("showtip");
        });
    });
});
