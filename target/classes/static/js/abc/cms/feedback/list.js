/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/date","abc.common","bootstrap","layui","../abc/util/page"], function ($,date) {

        layui.use('form', function() {
            var form = layui.form;
            form.render();
        });

        $("[data-toggle='popover']").popover();


        var queryList = function(){
            var feedbackType = $.trim($("[name='feedbackType']").val());//反馈类型
            var sourceType = $.trim($("[name='sourceType']").val());//来源途径
            var isReply = $.trim($("[name='isReply']").val());//是否回复
            location.href = ctx+'/cms/feedback/list.php?feedbackType='+feedbackType+"&sourceType="+sourceType+"&isReply="+isReply;
        };
        $('body').on('click', '.js-query', function(e){
            e.preventDefault();
            queryList();
        });

        $("body").on("click", ".js_open_userInfo", function(e){
            abc.block();
            var url=$(this).attr("data-url");
            var iframe=document.getElementById("consumer_frame");
            iframe.src=url;
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    $("#myModal3").show();
                    $("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function(){
                    $("#myModal3").show();
                    $("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                };
            }
        });
        $("body").on("click", "button[data-dismi]", function(e){
            $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#myModal3").hide();
            });
        });



        //$("[name='feedbackType'],[name='sourceType']").change(function(e){
        //    e.preventDefault();
        //    queryList();
        //});

        //list页面 删去
        $('body').on('click', '.js_delete', function(){
            abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '', $(".js_currLink").val());
        });

        //list批量删除
        $(".js_del_batch_btn").click(function(){
            var ids =[];
            $(".js_checkbox:checked").each(function(i){
                ids.push($(this).val());
            });
            if(ids.length==0){
                abc.layerAlert(false,"请勾选要操作的字段");
            }else{
                abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), JSON.stringify(ids), $(".js_currLink").val());
            }
        });

        //list页面 回复
        $('body').on('click', '.js_answer', function(){
            var feedbackId = $(this).attr("data-id");
            var feedbackUserId = $(this).attr("data-feedbackUserId");
            var feedbackTime = $(this).parent().siblings(".js_feedbackTime").html();
            var opinionDesc = $(this).parent().siblings(".js_opinionDesc").html();
            var year = feedbackTime.substring(0,4);
            var month = feedbackTime.substring(5,7);
            var day = feedbackTime.substring(8,10);
            var time = year+'-'+month+'-'+day;
            var html = '<textarea style="resize: none;width: 467px;height: 176px;max-width: 497px;max-height: 206px; margin:15px; overflow-x: hidden; overflow-y: auto;" id="boxContent"></textarea>' +
                '<div style="text-align: center">' +
                '<input type="button" value="添加" id="boxAddBtn" class="layui-btn layui-btn-primary"/>'+
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
                        var content = $.trim($("#boxContent").val());
                        if(!content){
                            layer.alert("回复内容不能为空", {icon: 5});
                        }else{
                            content = '您'+year+'年'+month+'月'+day+'日反馈的问题，小艾客服回复如下：'+content+'，感谢您一直以来对我们的支持！！！！！';
                        }
                        $.ajax({
                            url : ctx+"/cms/businessMsg/v2/send.php",
                            type : "POST",
                            async: false,
                            contentType: "application/json",
                            dataType : 'JSON',
                            data : JSON.stringify({
                            	userIds : [feedbackUserId],
                                businessId:feedbackId,
                                type:"2",
                                busiType :'BANGBANG_YJFK',
                                templateid:'SU4_nNvBrnx8fBhSrT5S36yhUrWLSRqkQAJMsye0YrY',
                                phoneMsg : content,
                                webMsg :content,
                                dataList:{first:'您的反馈与投诉已由客服处理',keyword1:time,keyword2:opinionDesc,keyword3:date.format(new Date(),'yyyy-MM-dd'),remark:'详情请登录系统查看'}}),
                            success : function(data){
                                if(data && data.code=='2000') {
                                    layer.alert("回复成功", {icon: 1});
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



    });
});