/**
 * Created by liuqi on 2017/5/24.
 */
require(["../../../config"], function () {
    require(["jquery.full","abc.common"], function ($) {
        $(function () {
            //点击返回
            $('body').on('click', '.js_back', function(){
                window.location.href = $(".js_currLink").val();
            });

            var zNodes = [];
            $(".menu_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-menu-id");
                obj.pId= $(this).attr("data-menu-pid");
                obj.name= $(this).attr("data-menu-name");
                obj.open = true;
                zNodes.push(obj);
            });
            var setting = {
                data: {simpleData: {enable: true}}
            };
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);


//组织-员工树--------
            var org_operator_nodes = [];
            $(".org_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-org-id");
                obj.pId= $(this).attr("data-org-pid");
                obj.name= $(this).attr("data-org-name");
                obj.open = true;
                org_operator_nodes.push(obj);
            });
            var user_nodes = [];
            $(".operator_li").each(function(){
                var obj = {};
                    obj.id= $(this).attr("data-user-id");
                    obj.pId= $(this).attr("data-user-orgId");
                    obj.name= $(this).attr("data-user-name");
                    obj.open = true;
                user_nodes.push(obj);
            });

            var clone_user_nodes = [];
            $(".js_select_userId").each(function(){
                for (var i = 0; i < user_nodes.length; i++) {
                    if (user_nodes[i].id == $(this).attr("data-select-userId")) {
                        clone_user_nodes.push(user_nodes[i]);
                    }
                }
            });
            for (var i = 0; i < clone_user_nodes.length; i++) {
                org_operator_nodes.push(clone_user_nodes[i]);
            }

            var org_operator_setting = {
                view: {dblClickExpand: false},//屏蔽掉双击事件
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function (e,treeId, treeNode) {
                        org_operator_tree.expandNode(treeNode);
                    }
                }
            };
            $.fn.zTree.init($("#org_operator_tree"), org_operator_setting, org_operator_nodes);
            var org_operator_tree = $.fn.zTree.getZTreeObj("org_operator_tree");
        });
    })
});