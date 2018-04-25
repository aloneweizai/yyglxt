/**
 * Created by LiuQi
 */

require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/bangbang/questionClassify/category","abc.common","layui","../abc/util/page"], function ($, category) {

        category.initUrl(ctx + "/bangbang/questionClassify/ajaxList.php",ctx +"/bangbang/questionClassify/save.php",
            ctx +"/bangbang/questionClassify/delete/{id}.php",ctx +"/bangbang/questionClassify/save.php");

        zTreeObj.setting.callback.onClick = function(){
            ajaxListByCategory();
        };

        var ajaxListByCategory = function(){
            var nodes = zTreeObj.getSelectedNodes();
            var categoryCode = nodes[0].id;
            var categoryName = nodes[0].name;
            var vategoryPCode = nodes[0].pId;
            $("#categoryName").text("分类名称："+categoryName);
            $("#categoryName").attr("data-categoryId",categoryCode);
            $("#categoryName").attr("data-categoryName",categoryName);

            if(categoryCode == 0 || vategoryPCode==0 ){
                $(".js_tag_div").hide();
            }else{
                $.ajax({
                    url: ctx + "/bangbang/questionClassify/cateTag/ajaxList.php?classifyCode="+categoryCode,
                    type: "get",
                    success: function (result) {
                        if(result.code == '2000'){
                            $(".selectTag span").not(".selectTag_title").remove();
                            $("[name='tagIds']").each(function(){
                                $(this)[0].checked = false;
                            });
                            $.each(result.dataList, function (i, item) {
                                $(".selectTag").append('<span>'+item.tagName+'</span>');
                                if($("[name='tagIds'][data-id='"+item.tagId+"']")[0]){
                                    $("[name='tagIds'][data-id='"+item.tagId+"']")[0].checked = true;
                                }
                            });
                            $(".js_tag_div").show();
                            layui.use('form', function(){
                                var form = layui.form;
                                form.render();
                                form.on('checkbox(tag)', function(data){
                                    var tagName = data.elem.getAttribute("data-name");
                                    if(data.elem.checked){
                                        $(".selectTag").append('<span>'+tagName+'</span>');
                                    }else{
                                        $(".selectTag span").each(function(){
                                            if($(this).text() == tagName){
                                                $(this).remove();
                                            }
                                        })
                                    }
                                });
                            });
                        }
                    },
                    error: function (err) {
                        layer.msg("操作失败", {icon: 5},function () {
                        });
                    }
                });
            }
        };

        /*查询按钮*/
        $('body').on('click', '.js_select_tag', function(e) {
            e.preventDefault();
            var keywords = $.trim($("#keywords").val());
            $("div.layui-form-checkbox span").each(function(i,item){
                    $(this).removeAttr("style");
            });
            if(!keywords){
                layer.msg("请输入标签名", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
            var hasTag = false;
            $("div.layui-form-checkbox span").each(function(i,item){
                if($(this).text().indexOf(keywords)!=-1){
                    hasTag = true;
                    $(this).attr("style","color:#FF5722");
                }
            });
            if(!hasTag){
                layer.msg("标签名不存在", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                return;
            }
        });


        $('body').on('click', '.js_save_categoryTag_btn', function(e){
            e.preventDefault();
            var categoryCode = $("#categoryName").attr("data-categoryId");//分类code
            var categoryName = $("#categoryName").attr("data-categoryName");//分类name
            var tagList = [];
            $("[name='tagIds']").each(function(){
                if($(this)[0].checked){
                    var tag = {};
                    tag.tagId=$(this).attr('data-id');
                    //tag.tagName=$(this).attr('data-name');
                    tagList.push(tag);
                }
            });
            var obj={classifyCode:categoryCode, tagList:tagList};
            $.ajax({
                type: "post",
                url: ctx + "/bangbang/questionClassify/cateTag/modify.php",
                data: JSON.stringify(obj),
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (result) {
                    layer.msg("操作成功", {icon: 1},function () {});
                },
                error: function (err) {
                    layer.msg("操作失败", {icon: 5},function () {
                    });
                }
            });
        });


        /**/
        $('body').on('click', '.js_add_tag', function(e){
            e.preventDefault();
            showContentLayer('添加标签', 400, 300, 200, $("#addTag_pop"));
        });
        //open弹窗公共方法
        var showContentLayer = function (title, width, height, top, content, event, closeBtn) {
            width = width || "600";
            height = height || "300";
            if (!event || !event.success || !event.end || !event.cancel) {
                event = event || {};
                event.success = event.success || function () {
                    };
                event.end = event.end || function () {
                    };
                event.cancel = event.cancel || function () {
                    };
            }
            if (!closeBtn) {
                closeBtn = 0;
            }
            var index = layer.open({
                type: 1,
                title: title,
                skin: 'layui-layer-molv',
                closeBtn: closeBtn,
                area: [width + 'px', height + "px"],
                offset: [top],
                shadeClose: false,
                content: content,
                success: function (elem, i) {
                    var $target = $(elem);
                    $target.find("button[name='close-layer-btn']").on("click", function () {
                        $("#addTag_pop").hide();
                        layer.close(i);
                    });
                    event.success();
                },
                end: event.end,
                cancel: event.cancel
            });
            return index;
        };


        $('body').on('click', '.js_submit_tag', function(e){
            e.preventDefault();
            var labelStr = $.trim($("#label").val());
            if(!labelStr){
                layer.alert('请输入标签', {icon: 5});
                return;
            }
            $.ajax({
                url: ctx + "/cms/knowTag/addByOtherChannel.php?tagType=xthf_bb",
                type: "POST",
                data: {"labelStr":labelStr},
                success: function (result) {
                    if (result.code == '2000') {
                        layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                            $("#addTag_pop").hide();
                            layer.closeAll();
                            var labelList = result.dataList;
                            if(labelList!=null && labelList.length>0){
                                for(var i=0;i<labelList.length;i++){
                                    $(".js_tag_checkbox").append('<input name="tagIds" lay-filter="tag" data-id="'+labelList[i].id+'" data-name="'+labelList[i].name+'" type="checkbox" title="'+labelList[i].name+'" lay-skin="primary">');
                                }
                                var form = layui.form;
                                form.render();
                                form.on('checkbox(tag)', function(data){
                                    var tagName = data.elem.getAttribute("data-name");
                                    if(data.elem.checked){
                                        $(".selectTag").append('<span>'+tagName+'</span>');
                                    }else{
                                        $(".selectTag span").each(function(){
                                            if($(this).text() == tagName){
                                                $(this).remove();
                                            }
                                        })
                                    }
                                });
                            }
                        });
                    }else{
                        layer.alert(result.message ||"操作失败", {icon: 5,closeBtn:0,title:""});
                    }
                },
                error: function (err) {
                    layer.msg("操作失败", {icon: 5});
                }
            });
        });

    });
});