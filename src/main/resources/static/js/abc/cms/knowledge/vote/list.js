/**
 * Created by LiuQi
 */
require(["../../../../config"], function () {

    require(["jquery-3.1.0","layui"], function ($) {
        $.ajax({
            type: "GET",
            url: initPageLink,
            async: false,
            success: function (data) {
                $(".popover").remove();
                $(".js_page_div").html(data);
                layui.use('form', function() {
                    var form = layui.form;
                    form.render();
                });
                $("[data-toggle='popover']").popover();
            }
        });
    });


    require(["jquery-3.1.0","../abc/cms/knowledge/category","abc.common","layui","bootstrap"], function ($,category) {



        zTreeObj.setting.callback.onClick = function(){
            ajaxListByCategory();
        };
        $(".js_query").click(function(){
            ajaxListByCategory();
        });

        var ajaxListByCategory = function(){
            var categoryCode = "";
            var keywords = $.trim($("#keywords").val());//关键字
            //分类
            var nodes = zTreeObj.getSelectedNodes();
            if(nodes.length == 1){
                categoryCode = nodes[0].id;
            }
            if(categoryCode == "0"){
                categoryCode = "";
            }
            //知识类型
            abc.block();
            $.ajax({
                type: 'GET',
                url: ctx+'/cms/knowVote/page.php?keywords='+keywords+'&categoryCode='+categoryCode,
                async: false,
                success: function (data) {
                    $(".popover").remove();
                    $(".js_page_div").html(data);
                    layui.use('form', function() {
                        var form = layui.form;
                        form.render();
                    });
                    $("[data-toggle='popover']").popover();
                    abc.unblock();
                }
            });
        };

        //list页面 删去
        $('body').on('click', '.js_delete', function(){
            abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '', $(".js_currLink").val());
        });

        //list批量删除
        $('body').on('click', '.js_del_batch_btn', function(e){
            e.preventDefault();
            var ids =[];
            $(".js_checkbox:checked").each(function(i){
                ids.push($(this).val());
            });
            if(ids.length==0){
                abc.layerAlert(false,"请勾选要操作的字段");
            }else{
                abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), JSON.stringify(ids) ,$(".js_currLink").val());
            }
        });

        //list页面 回复
        $('body').on('click', '.js_answer', function(){
            var voteId = $(this).attr("data-id");
            var voteUserId = $(this).attr("data-userId");

            var html = '<textarea style="resize: none;width: 497px;height: 206px;max-width: 497px;max-height: 206px;" id="boxContent"></textarea>' +
                '<div style="text-align: center">' +
                '<input type="button" value="添加" id="boxAddBtn" class="layui-btn"/>'+
                '&nbsp;&nbsp;&nbsp;&nbsp;'+
                '<input type="button" value="关闭" id="boxCancelBtn" class="layui-btn layui-btn-primary"/>' +
                '</div>';
            var openBox = layer.open({
                title:"回复",
                type: 1,
                offset: '100px',
                area: ['500px', '300px'],
                fixed: false,
                content: html,
                success:function(){
                    //点击添加按钮
                    $("#boxAddBtn").on('click', function(){
                        abc.block();
                        var content = $("#boxContent").val();
                        $.ajax({
                            url : ctx+"/cms/businessMsg/add.php",
                            type : "POST",
                            async: false,
                            contentType: "application/json",
                            dataType : 'JSON',
                            data : JSON.stringify({userId : voteUserId,businessId : voteId,content :content, type:'1', busiType:'KNOWLEDGE_VOTE'}),
                            success : function(data){
                                abc.unblock();
                                if(data.code=='2000') {
                                    layer.close(openBox);
                                    location.href = $(".js_currLink").val();
                                }else{
                                    layer.alert("回复失败", {icon: 5});
                                }
                            },
                            error : function(){
                                layer.alert("回复失败", {icon: 5});
                            }
                        });
                        layer.close(openBox);
                    });
                    //点击取消按钮事件
                    $("#boxCancelBtn").on('click', function(){
                        layer.close(openBox);
                    });
                },
                error: function(){
                    layer.msg('失败', {icon: 5});
                }
            });
        });





        $("body").on("click",".js_page_location", function(){
            $.ajax({
                type: 'GET',
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    $(".popover").remove();
                    $(".js_page_div").html(data);
                    layui.use('form', function() {
                        var form = layui.form;
                        form.render();
                    });
                    $("[data-toggle='popover']").popover();
                }
            });
        });
        $("body").on("keypress","#go_page", function(e){
            if(e.keyCode==13){
                $(".js_go_page").click();
                return false;
            }
        });
        $("body").on("click",".js_go_page", function(e){
            var page = $('#go_page').val();
            var url = $(this).attr("data-href");
            if (page.match("^[1-9][0-9]*$")) {
                $.ajax({
                    type: 'GET',
                    url: url.replace("[:page]", page),
                    async: false,
                    success: function (data) {
                        $(".popover").remove();
                        $(".js_page_div").html(data);
                        layui.use('form', function() {
                            var form = layui.form;
                            form.render();
                        });
                        $("[data-toggle='popover']").popover();
                    }
                });
            } else {
                return;
            }
        });

    });
});