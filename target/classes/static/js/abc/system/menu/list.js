/**
 * Created by liuqi on 2017/5/22.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","ztree","abc.common","layui","../abc/util/page"], function ($) {
        $(function () {

            $.ajax({
                type: "GET",
                url: initPageLink,
                async: false,
                success: function (data) {
                    $(".js_page_div").html(data);
                    layui.use('form', function() {
                        var form = layui.form;form.render();
                    });
                }
            });

            //list页面 禁用开启
            $('body').on('click', '.js_enable,.js_delete', function(){
                var _this = $(this);
                abc.layerAjaxConfirm("POST", _this.attr("data-href"), '', $(".js_currLink").val());
            });

            //list页面批量禁用
            $('body').on('click', '.js_disable_batch', function(){
                var ids = abc.getCheckBoxIds();
                if(ids){
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href")+"?id="+ids, '',$(".js_currLink").val());
                }else{
                    abc.layerAlert(false,"请勾选复选框");
                }
            });

            //list页面 修改按钮
            $('body').on('click', '.js_edit', function(){
                location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
            });

            var setting = {
                view: {dblClickExpand: false,showLine: false},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                        var parentId = "";
                        if(treeNode.id != -1){
                            parentId = treeNode.id;
                        }
                        $.ajax({
                            type: "GET",
                            url: ctx+"/system/menu/page.php?parentId="+parentId,
                            async: false,
                            success: function (data) {
                                $(".js_page_div").html(data);
                                layui.use('form', function() {
                                    var form = layui.form;form.render();
                                });
                            }
                        });
                    }
                }
            };
            var zNodes = [{ id:-1, pId:-2, name:"系统模块菜单", open:true}];
            $(".menu_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-menu-id");
                obj.pId= $(this).attr("data-menu-pid");
                obj.name= $(this).attr("data-menu-name");
                if(obj.pId =="" || obj.pId =="null"){
                    obj.pId = -1;
                }
                obj.myAttr = $(this).attr("data-menu-id");
                zNodes.push(obj);
            });
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

        });
    });
});
