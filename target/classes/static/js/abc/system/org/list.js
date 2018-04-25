/**
 * Created by liuqi on 2017/5/24.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","ztree","nicevalidator","nicevalidator.zh_CN","abc.common","layui","../abc/util/page"], function ($) {
        $(function () {
            $.ajax({
                type: "GET",
                url: initPageLink,
                async: false,
                success: function (data) {
                    $(".js_page_div").html(data);
                    layui.use('form', function() {
                        var form = layui.form;
                        form.render();
                    });
                }
            });

            //list页面 根据条件 查询list
            $(".js-queryList-btn").click(function(e){
                e.preventDefault();
                var orgName = $("[name='orgName']").val();//部门名称
                var orgType = $("[name='orgType']").val(); //部门类型
                abc.block();
                $.ajax({
                    type: 'GET',
                    url: ctx+'/system/org/page.php?name='+orgName+'&type='+orgType,
                    async: false,
                    success: function (data) {
                        abc.unblock();
                        $(".js_page_div").html(data);
                        layui.use('form', function() {
                            var form = layui.form;form.render();
                        });
                    }
                });
            });

            //list页面 禁用开启
            $('body').on('click', '.js_enable', function(){
                var _this = $(this);
                abc.layerAjaxConfirm("POST", _this.attr("data-href"), '', $(".js_currLink").val());
            });

            //list页面 批量禁用
            $(".js_disable_batch").click(function(e){
                e.preventDefault();
                var ids = abc.getCheckBoxIds();
                if(ids){
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href")+"?id="+ids, '', $(".js_currLink").val());
                }else{
                    abc.layerAlert(false,"请勾选复选框");
                }
            });

            //list页面 修改按钮
            $('body').on('click', '.js_edit', function(){
                location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
            });

            //list 页面 新增机构
            $('body').on('click', '.js_add_org', function(){
                var parentId = $(".js_select_parent").attr("data-parentId");
                var parentName = $(".js_select_parent").attr("data-parentName");
                location.href = $(this).attr("data-href")+"?parentId="+parentId+"&parentName="+parentName;
            });

            var setting = {
                view: {dblClickExpand: false,showLine: false},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj("tree_org_demo");
                        //zTree.expandNode(treeNode);
                        var parentId = "",parentName="";
                        if(treeNode.id != -1){
                            parentId = treeNode.id;
                            parentName = treeNode.name;
                        }
                        $.ajax({
                            type: "GET",
                            url: ctx+"/system/org/page.php?parentId="+parentId,
                            async: false,
                            success: function (data) {
                                if (data) {
                                    $(".js_page_div").html(data);
                                    layui.use('form', function() {
                                        var form = layui.form;form.render();
                                    });
                                    if(parentId ==""){
                                        $(".js_select_parent").attr({"data-parentId":"","data-parentName":""});
                                    }else{
                                        $(".js_select_parent").attr({"data-parentId":parentId,"data-parentName":parentName});
                                    }
                                } else {
                                    abc.layerAlert(false, '操作失败: '+data.message);
                                }
                            }
                        });
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
                //obj.open = true;
                obj.myAttr = obj.id;
                zNodes.push(obj);
            });
            $.fn.zTree.init($("#tree_org_demo"), setting, zNodes);
        });
    });
});
