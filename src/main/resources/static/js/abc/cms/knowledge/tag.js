/**
 * Created by LiuQi
 * 标签管理
 */
define(['jquery-3.1.0','layui'],function(){

    $("body").on("click","[name='tagBtn']",selectLabelClick);

    $(".js_add_tag").on('click',function(e){
        e.preventDefault();
        var tagType = $(this).attr("data-type");
        $.ajax({
            type: "GET",
            url: ctx + "/cms/knowTag/label.php?tagType="+tagType,
            async: false,
            success: function(html){
                layer.open({
                    title:"选择标签",
                    type: 1,
                    area: ['1000px','380px'],
                    fixed: true, //不固定
                    content: html,
                    success:function(){
                        /* 点击标签 */
                        $("#tag_query").on('click', function(){
                            var tagName = $.trim($("#tag_input").val());
                            $.ajax({
                                url: ctx + "/cms/knowTag/ajaxList.php?tagName="+tagName+"&tagType="+tagType,
                                type: "get",
                                success: function (result) {
                                    if(result.code == '2000'){
                                        var html='';
                                        $.each(result.dataList, function (i, item) {
                                            html = html+ '<span><button type="button" class="layui-btn layui-btn-primary" name="tagBtn" value="'+item.id+'">'+item.name+'</button></span>&nbsp;&nbsp;'
                                        });
                                        $("#labelDIV_").html(html);
                                    }
                                },
                                error: function (err) {
                                    layer.msg("操作失败", {icon: 5},function () {
                                    });
                                }
                            });
                        });


                        $("button[name='tagBtn']").on('click', function(){
                            var flag = true, _this = $(this);
                            $("#labelDiv input").each(function(e, i){
                                if($(this).val() == _this.val()){
                                    flag = false;
                                    return false;
                                }
                            });
                            if(flag){
                                $("#labelDiv").append("<span name='labelSpan' onclick='$(this).remove();'><input name='tagIds' type='hidden' value='"+$(this).val()+"'/><button type='button' class='btn btn-default'>"+$(this).text()+"<i class='glyphicon glyphicon-remove'></i></button></span>");
                            }
                        });
                        $("#add_label_btn").on('click', function(){
                            var labelStr = $.trim($("#label").val());
                            if(!labelStr){
                                layer.alert('请输入标签', {icon: 5});
                                return;
                            }
                            $.ajax({
                                url: ctx + "/cms/knowTag/addByOtherChannel.php?tagType="+tagType,
                                type: "POST",
                                data: {"labelStr":labelStr},
                                success: function (result) {
                                    if (result.code == '2000') {
                                        layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                                            $("#label").val("");
                                            var labelList = result.dataList;
                                            if(labelList!=null && labelList.length>0){
                                                for(var i=0;i<labelList.length;i++){
                                                    $("#labelDIV_").append("<span><button type='button' class='btn btn-default' name='tagBtn' value='"+labelList[i].id+"'>"+labelList[i].name+"</button></span>&nbsp;&nbsp;");
                                                }
                                            }
                                        });
                                    }else{
                                        layer.alert(result.message ||"操作失败", {icon: 1,closeBtn:0,title:""});
                                    }
                                },
                                error: function (err) {
                                    layer.msg("操作失败", {icon: 5},function () {
                                    });
                                }
                            });
                        });
                    }
                });
            },
            error: function(){
                layer.msg('获取标签信息失败', {icon: 5});
            }
        });
    });

    //弹出的标签框 点击标签事件
    function selectLabelClick(){
        var flag = true, _this = $(this);
        $("#labelDiv input").each(function(e, i){
            if($(this).val() == _this.val()){
                flag = false;
                return false;
            }
        });
        if(flag){
            $("#labelDiv").append("<span name='labelSpan' onclick='$(this).remove();'><input name='tagIds' type='hidden' value='"+$(this).val()+"'/><button type='button' class='layui-btn'>"+$(this).text()+"<i class='glyphicon glyphicon-remove'></i></button></span>");
        }
    }


});
