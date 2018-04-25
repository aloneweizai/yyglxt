/**
 * Created by liuqi on 2017/5/26.
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
            $(".js-queryList-btn").click(function(){
                var username = $("[name='username']").val();//部门名称
                var nickname = $("[name='nickname']").val();//部门名称
                var phone = $("[name='phone']").val();//部门名称
                var status = $("[name='status']").val();//部门名称
                abc.block();
                $.ajax({
                    type: "GET",
                    url: ctx+"/system/operator/page.php?username="+username+"&nickname="+nickname+'&status='+status+'&phone='+phone,
                    async: false,
                    success: function (data) {
                        abc.unblock();
                        $(".js_page_div").html(data);
                        layui.use('form', function() {
                            var form = layui.form;
                            form.render();
                        });
                    }
                });
            });

            ////list页面 禁用 开启 按钮
            $('body').on('click', '.js_enable', function(){
                abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '',$(".js_currLink").val());
            });

            //list页面 批量禁用
            $('body').on('click', '.js_disable_batch', function(e){
                e.preventDefault();
                var ids = abc.getCheckBoxIds();
                if(ids){
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href")+"?id="+ids, '', $(".js_currLink").val());
                }else{
                    abc.layerAlert(false,"请勾选复选框");
                }
            });
            //list 修改按钮
            $('body').on('click', '.js_edit', function(){
                window.location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
            });

            var setting = {
                view: {dblClickExpand: false,showLine: false},
                data: {simpleData: {enable: true}},
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                        var orgId = "";
                        if(treeNode.id != -1){
                            orgId = treeNode.id;
                        }
                        $.ajax({
                            type: "GET",
                            url: ctx+"/system/operator/page.php?orgId="+orgId,
                            async: false,
                            success: function (data) {
                                $(".js_page_div").html(data);
                                layui.use('form', function() {
                                    var form = layui.form;
                                    form.render();
                                });
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
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

        });
    });
});
