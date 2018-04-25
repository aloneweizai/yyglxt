require(["../../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {

        var contentListForm = $("form[name='_content_list_form']");
        $("#consumer_more").click(function(){
            if($(this).hasClass('shoqilai')){
                $(this).val('更多条件').removeClass('shoqilai');
                $(".moretjYC").each(function(){
                    $(this).removeClass('moretjYC').addClass('moretj').fadeOut(500);
                });
            }else{
                $(this).val('隐藏条件').addClass('shoqilai');

                $(".moretj").each(function(){
                    $(this).removeClass('moretj').addClass('moretjYC').fadeIn(500);
                });
            }
        });

        var hasYC=false;
        $(".moretj").each(function(){
            $(this).find('select').each(function(){
                if($(this).val()!="" || $(this).val().length>0){
                    hasYC=true;
                }
            });
            $(this).find('input[type="text"]').each(function(){
                if($(this).val()!="" || $(this).val().length>0){
                    hasYC=true;
                }
            });
            $(this).find('input[type="checkbox"]').each(function(){
                if($(this).is(":checked")){
                    hasYC=true;
                }
            });
        });
        if(hasYC){$("#consumer_more").click();};
        contentListForm.find("a[name='content_del_btn']").on("click",function(){
            var contentId = $(this).data("contentid");
            if(!contentId){
                layer.msg("请选择内容", {icon: 5,offset: abc.winscrollTop(200)});
                return;
            }

            layer.confirm('是否确定删除该内容项？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function() {
                $.ajax({
                    url:ctx+"/content/content/delete/"+contentId,
                    type:"get",
                    success:function(result){
                        if(result.code=='2000'){
                            layer.msg(result.msg||"操作成功", {icon: 1,offset: abc.winscrollTop(200)});
                            setTimeout(function(){
                                //location.href=ctx+"/content/content/list.php";
                                window.location.reload();
                            },1000);
                        }else{
                            layer.msg(result.msg||"操作失败", {icon: 5,offset: abc.winscrollTop(200)});
                        }
                    },
                    error:function(){
                        layer.msg("操作失败", {icon: 5,offset: abc.winscrollTop(200)});
                    }
                });
            });

        });


        contentListForm.find("a[name='content_issue_btn']").on("click",function(){
            var contentId = $(this).data("contentid");

            layer.confirm('是否确定发布该内容项？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function() {
                $.ajax({
                    url:ctx+"/content/content/issue/"+contentId,
                    type:"get",
                    success:function(result){
                        if(result.code=='2000'){
                            var ids = [];
                            ids.push(contentId);
                            $.ajax({
                                url : ctx + "/content/sysmaintain/static_some_content.php",
                                type : "get",
                                data : {
                                    "ids[]" : ids
                                },
                                success : function (result1) {
                                    if(result1.code=="2000"){
                                        layer.msg(result1.message||"操作成功", {icon: 1,offset: abc.winscrollTop(200)});
                                        setTimeout(function(){
                                            //location.href=ctx+"/content/content/list.php";
                                            window.location.reload();
                                        },1000);
                                    }else{
                                        layer.msg(result1.message||"操作失败", {icon: 5,offset: abc.winscrollTop(200)});
                                        window.location.reload();
                                    }
                                },

                                error : function () {
                                    layer.msg(result1.message||"操作失败", {icon: 5,offset: abc.winscrollTop(200)});
                                    window.location.reload();
                                }
                            });


                        }else{
                            layer.msg(result.msg||"操作失败", {icon: 5,offset: abc.winscrollTop(200)});
                        }
                    },
                    error:function(){
                        layer.msg("操作失败", {icon: 5,offset: abc.winscrollTop(200)});
                    }
                });
            });

        });


        $("input[name='content_query_staus_btn']").off("click").on("click",function(){
            location.href=ctx+"/content/content/list.php?status="+$(this).data("status");
        });

        $("#btn-select-all").on("click",function(){
            var selectall = $(this).data("selectall")||"0";
            if(selectall==="1"){
                $(this).data("selectall","0");
                $("input[name='ids']").removeAttr("checked");
                $("input[name='ids']").siblings("div").removeClass("layui-form-checked");
            }else{
                $(this).data("selectall","1");
                $("input[name='ids']").attr("checked","checked");
                layui.form.render("checkbox");
            }
        });

        layui.form.on("checkbox(ids-filter)",function (data) {
            if(data.elem.checked){
                $(data.elem).attr("checked","checked");
            }else{
                $(data.elem).removeAttr("checked");
            }
        });

        $("#content_delete_batch").on("click",function(){
            var reqList = [],generated,status,msg;
            $("input[name='ids']:checked").each(function(){

                generated = $(this).data("generated");
                status = $(this).data("status");

                if(status!=2 || generated!=1){
                    reqList.push($(this).val());
                }else{
                    msg = "请先执行撤销发布再删除该内容项";
                }
            });

            if(msg){
                layer.alert(msg, {icon: 5});
                return ;
            }

            if(!reqList || reqList.length<=0){
                layer.alert("请选择删除内容项", {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            layer.confirm('是否确认删除选中的内容项？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function() {
                $.ajax({
                    url : ctx+"/content/content/deleteBatch.php",
                    type : "get",
                    data : {
                        'ids[]' : reqList
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg(result.msg||"删除内容成功", {icon: 1,offset: abc.winscrollTop(200)});
                            setTimeout(function(){
                                //location.href=ctx+"/content/content/list.php";
                                window.location.reload();
                            },1000);
                        }else{
                            layer.alert(result.msg||"删除内容失败", {icon: 5,offset: abc.winscrollTop(200)});
                        }
                    },
                    error : function(){
                        layer.alert("删除内容失败", {icon: 5,offset: abc.winscrollTop(200)});
                    }
                });
            });
        });

        $("input[name='list_topLevel']").on("change",function(){
            var value = 0;
            try{
                value = parseInt($(this).val());
                if(value<0){
                    value = 0;
                }
            }catch(e){
                value = 0;
            }
            $(this).val(value);
        });

        $("#content_set_toplevel").on("click",function(){
            var reqStr = "";
            var topLevel = "";
            $("input[name='ids']:checked").each(function(){

                if($(this).parents("tr").find("input[name='list_topLevel']").prop("checked")){
                    topLevel = "1";
                }else{
                    topLevel = "0";
                }
                if(reqStr){
                    reqStr += ";"+ $(this).val() + ":" + topLevel
                }else{
                    reqStr += $(this).val() + ":" + topLevel;
                }
            });
            if(!reqStr){
                layer.alert("请选择需要固顶的文章", {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            layer.confirm('是否确认固顶文章？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function(){
                $.ajax({
                    url : ctx+"/content/content/updateBatch.php",
                    type : "get",
                    data : {
                        keyValList : reqStr
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg(result.msg||"操作成功", {icon: 1,offset: abc.winscrollTop(200)});
                            setTimeout(function(){
                                //location.href=ctx+"/content/content/list.php";
                                window.location.reload();
                            },1000);
                        }else{
                            layer.alert(result.msg||"固顶失败", {icon: 5,offset: abc.winscrollTop(200)});
                        }
                    },
                    error : function(){
                        layer.alert("固顶失败", {icon: 5,offset: abc.winscrollTop(200)});
                    }
                });
            });
        });

        $("#content_send_back").on("click",function(){
            var reqList = [];
            var errorMsg = "";
            $("input[name='ids']:checked").each(function(){
                if($(this).data("status")=="0"){
                    // layer.alert("未发布的文章不能撤销发布", {icon: 5});
                    errorMsg = "未发布的文章不能撤销发布";
                    return false;
                }else if($(this).data("status")=="3"){
                    errorMsg = "已撤销的内容不能再撤销";
                    return false;
                }else{
                    reqList.push($(this).val());
                }
            });

            if(errorMsg){
                layer.alert(errorMsg, {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }
            if(!reqList || reqList.length<=0){
                layer.alert("请选择撤销内容项", {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            layer.confirm('是否确认撤销选中的内容项？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function(){
                $.ajax({
                    url : ctx+"/content/content/sendBackBatch.php",
                    type : "get",
                    data : {
                        'ids[]' : reqList
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg(result.msg||"操作成功", {icon: 1,offset: abc.winscrollTop(200)});
                            setTimeout(function(){
                                //location.href=ctx+"/content/content/list.php";
                                window.location.reload();
                            },1000);
                        }else{
                            layer.alert(result.msg||"撤销内容失败", {icon: 5,offset: abc.winscrollTop(200)});
                        }
                    },
                    error : function(){
                        layer.alert("撤销内容失败", {icon: 5,offset: abc.winscrollTop(200)});
                    }
                });
            });
        });

        $("input[name='content_set_topic']").on("click",function(){

            var reqList = [];
            var siteId = "";
            var generated = "";
            $("input[name='ids']:checked").each(function(){
                reqList.push($(this).val());
                siteId = $(this).data("siteid");
                generated = $(this).data("generated");
            });

            if(!reqList || reqList.length<=0){
                layer.alert("请选择需要推送专题的内容项", {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            if(reqList.length!=1){
                layer.alert("只能选择一个专题进行推送", {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            if(generated!="1"){
                layer.alert("只能推送已生成的文章至专题", {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            var confirmIndex = layer.confirm('是否确认推送选中的内容项至专题？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function() {
                layer.close(confirmIndex);
                $.ajax({
                    type: "GET",
                    url: ctx + "/content/content/topicList.php?siteId=" + siteId,
                    success: function (result) {
                        layer.open({
                            area: ['600px', '700px'],
                            type: 1,
                            content: result,
                            success: function () {
                                $("table[name='_content_topic_table']").find("a[name='_content_a_topic']").on("click", function () {
                                    var topicId = $(this).data("topicid");
                                    if (!topicId) {
                                        layer.alert("请选择需要推送内容的专题项", {icon: 5});
                                        return;
                                    }
                                    $.ajax({
                                        url: ctx + "/content/content/pushTopic.php",
                                        type: "POST",
                                        data: {
                                            "ids[]": reqList,
                                            "topicId": topicId
                                        },
                                        success: function (pushResult) {
                                            if (pushResult.code == "2000") {
                                                layer.msg(pushResult.message || "推送内容成功", {icon: 1,offset: abc.winscrollTop(200)});
                                                setTimeout(function () {
                                                    //location.href = location.href;
                                                    window.location.reload();
                                                }, 1000);
                                            } else {
                                                layer.alert(pushResult.message || "推送内容失败", {icon: 5,offset: abc.winscrollTop(200)});
                                            }
                                        },
                                        error: function () {
                                            layer.alert("推送内容失败", {icon: 5,offset: abc.winscrollTop(200)});
                                        }
                                    });
                                });
                            }
                        });
                    }
                });
            },function(){
                layer.close(confirmIndex);
            });
        });

        $("#generate_static_site").on("click",function(){
            var reqList = [];
            var flag = true;
            $("input[name='ids']:checked").each(function(){
                reqList.push($(this).val());
                if($(this).data("status")==2 && $(this).data("generated")==0){
                    if($(this).data("sitestatus")=="0"){
                        flag = "站点已停用，不能生成静态页";
                    }
                }else{
                    flag = "请选择已发布状态且未生成静态页的内容";
                    return;
                }
            });
            if(flag!=true){
                layer.alert(flag, {icon: 5});
                return ;
            }

            if(!reqList || reqList.length<=0){
                layer.alert("请选择需要生成静态页的内容项", {icon: 5});
                return ;
            }

            layer.confirm('是否确认生成选中的内容项的静态页？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url: ctx + "/content/sysmaintain/static_some_content.php",
                    type: "GET",
                    data: {
                        "ids[]": reqList
                    },
                    success: function (result) {
                        if (result.code == "2000") {
                            layer.msg(result.message || "生成静态页成功", {icon: 1});
                            setTimeout(function () {
                                //location.href = location.href;
                                window.location.reload();
                            }, 1000);
                        } else {
                            layer.alert(result.message || "生成静态页失败", {icon: 5});
                        }
                    },
                    error: function () {
                        layer.alert("生成静态页失败", {icon: 5});
                    }
                });
            });
        })

        $("a[name='content_show_site'],a[name='content_list_item']").on("click",function(){
            var generated = $(this).data("generated");
            if(generated=="0"){
                layer.alert("该内容未生成静态页，不能查看", {icon: 5});
            }
            var url = $(this).data("url");
            window.parent.open(url);
        });

        $("#input_channelId").on("click",function(){

            var targetNode = $(this);
            $.ajax({
                type: "GET",
                url: ctx + "/content/column/ajaxColTree.php?channelId=",
                success: function (result) {
                    layer.open({
                        area: ['600px', '700px'],
                        type: 1,
                        title: "请选择栏目",
                        content: result,
                        success: function(){
                            $("div[name='column_tree_layer'] a[name='column_tree_name']").off("click").on("click",function(){
                                targetNode.siblings("input[name='channelId']").val($(this).data("channelid"));
                                targetNode.val($(this).data("channelname"));
                                layer.closeAll();
                            });
                        }
                    });
                },
                error: function () {
                    layer.msg('查询栏目信息失败', {icon: 5});
                }
            });

        });

        $("#content_create_btn").on("click",function(){
            var channelId = $("#content_query_condition_form input[name='channelId']").val();
            if(!channelId){
                var srcLocation = location.href;
                location.href = ctx + "/content/content/preAddPage.php?pre=" + srcLocation;
            }else{
                location.href = ctx + "/content/content/addPage.php?channelId=" + channelId;
            }
        });

        // $("#content_clean_field").on("click",function(){
        //     $("#content_query_condition_form").find("input,select").val("").prop("checked",false);
        // });

        // $("#content_create_in_channel").on("click",function(){
        //
        //     var channelId = $("#content_query_condition_form input[name='channelId']").val();
        //     if(!channelId){
        //         layer.alert("请选择一个栏目", {icon: 5});
        //         return ;
        //     }
        //
        //     location.href = ctx + "/content/content/addPage.php?channelId=" + channelId;
        //
        // });

    });
});