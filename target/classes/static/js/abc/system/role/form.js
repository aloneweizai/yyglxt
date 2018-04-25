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

            //form表单弹出框
            $(".js_save_menu_btn").click(function (e) {
                e.preventDefault();
                if($validatorFrom.isValid()){
                    //设置check的menuId
                    var checkedNodes=menu_tree.getCheckedNodes(true),v=[];
                    for(var i=0;i<checkedNodes.length;i++){
                        v.push(checkedNodes[i].id);
                    }
                    $("[name='menuIds']").val(v.join(","));
                    var href = $(this).attr('date-href');
                    layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                        function(){
                            abc.block();
                            $.ajax({
                                type: "POST",
                                url: href,
                                data: $("form").serializeJson(),
                                async: false,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    $.unblockUI();
                                    if (data && data.code == "2000") {
                                        $('[name="id"]').val(data.data.id);
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    } else {
                                        abc.layerAlert(false, '操作失败: '+data.message);
                                    }
                                }
                            });
                        }
                    );
                }
            });

            $(".js_save_operator_btn").click(function(){
                if($validatorFrom.isValid()) {
                    //设置check的menuId
                    var checkedNodes=org_operator_tree.getCheckedNodes(true),v=[];
                    for(var i=0;i<checkedNodes.length;i++){
                        v.push(checkedNodes[i].id);
                    }
                    var selectUserIds = v.join(",");
                    var obj = {};
                    obj.roleId = $("[name='id']").val();
                    obj.userId = selectUserIds;
                    if(obj.roleId == ''){
                        var role ={};
                        role.roleName = $("[name='roleName']").val();
                        obj.role = role;
                    }
                    var href = $(this).attr('date-href');
                    layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                        function(){
                            abc.block();
                            $.ajax({
                                type: "POST",
                                url: href,
                                data: JSON.stringify(obj),
                                async: false,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    $.unblockUI();
                                    if (data && data.code == "2000") {
                                        if(data.data.roleId){
                                            $('[name="id"]').val(data.data.roleId);
                                        }
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    } else {
                                        abc.layerAlert(false, '操作失败: '+data.message);
                                    }
                                }
                            });
                        }
                    );
                }
            });
//菜单树--------
            var menu_nodes = [];
            $(".menu_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-menu-id");
                obj.pId= $(this).attr("data-menu-pid");
                obj.name= $(this).attr("data-menu-name");
                obj.open = true;
                menu_nodes.push(obj);
            });
            var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
            $.fn.zTree.init($("#treeDemo"), setting, menu_nodes);
            var menu_tree = $.fn.zTree.getZTreeObj("treeDemo");
            menu_tree.setting.check.chkboxType = { "Y":"ps", "N":"ps"};

            var menuIdsStr = $("[name='menuIds']").val();
            var menuIdsArr = menuIdsStr.split(",");
            var allNode = menu_tree.transformToArray(menu_tree.getNodes());
            for (var k = 0; k < menuIdsArr.length; k++) {
                if(menuIdsArr[k] && menuIdsArr[k] != 'null'){
                    for (var i = 0; i < allNode.length; i++) {
                        if (allNode[i].id == menuIdsArr[k]) {
                            allNode[i].checked = true;
                            menu_tree.updateNode(allNode[i]);
                        }
                    }
                }
            }
//组织-员工树--------
            var org_operator_nodes = [];
            $(".org_li").each(function(){
                var obj = {};
                obj.id= $(this).attr("data-org-id");
                obj.pId= $(this).attr("data-org-pid");
                obj.name= $(this).attr("data-org-name");
                obj.open = true;
                obj.nocheck = true;
                org_operator_nodes.push(obj);
            });
            $(".operator_li").each(function(){
                var obj = {};
                if($(this).attr("data-user-orgId") !=""){
                    obj.id= $(this).attr("data-user-id");
                    obj.pId= $(this).attr("data-user-orgId");
                    obj.name= $(this).attr("data-user-name");
                    obj.open = true;
                    org_operator_nodes.push(obj);
                }
            });
            var org_operator_setting = {
                view: {dblClickExpand: false},//屏蔽掉双击事件
                check: {enable: true},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function (e,treeId, treeNode) {
                        if(treeNode.nocheck == true){
                            org_operator_tree.expandNode(treeNode);
                        }else{
                            org_operator_tree.checkNode(treeNode, !treeNode.checked, true);
                        }
                    }
                }
            };
            $.fn.zTree.init($("#org_operator_tree"), org_operator_setting, org_operator_nodes);
            var org_operator_tree = $.fn.zTree.getZTreeObj("org_operator_tree");
            org_operator_tree.setting.check.chkboxType = { "Y":"ps", "N":"ps"};

            //初始化 角色-用户关联
            var allOperatorNode = org_operator_tree.transformToArray(org_operator_tree.getNodes());
            var select_userIds = [];
            $(".js_select_userId").each(function(){
                select_userIds.push($(this).attr("data-select-userId"));
            });
            for (var k = 0; k < select_userIds.length; k++) {
                for (var i = 0; i < allOperatorNode.length; i++) {
                    if (allOperatorNode[i].nocheck == false && allOperatorNode[i].id == select_userIds[k]) {
                        allOperatorNode[i].checked = true;
                        org_operator_tree.updateNode(allOperatorNode[i]);
                    }
                }
            }

            //数据校验
            var $validatorFrom = $("form").validator({
                theme: 'yellow_top',
                timely: 1,
                fields: {
                    "roleName": "required"
                }
            });
            $validatorFrom.validator().trigger("showtip");
        });
    })
});