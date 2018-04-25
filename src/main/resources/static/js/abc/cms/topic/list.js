require(["../../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {

        var topicListForm = $("form[name='_topic_list_form']");

        topicListForm.find("input[name='_topic_delete_batch']").on("click",function(){
            var reqList = [];
            $("input[name='ids']:checked").each(function(){
                reqList.push($(this).val());
            });

            if(!reqList || reqList.length<=0){
                layer.alert("请选择删除专题项", {icon: 5});
                return ;
            }

            layer.confirm("是否确认删除选中的专题项？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/cms/topic/deleteBatch.php",
                    type : "get",
                    data : {
                        'ids[]' : reqList
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.alert(result.msg||"删除内容成功", {icon: 1});
                            setTimeout(function(){
                                location.href=ctx+"/cms/topic/list.php";
                            },1000);
                        }else{
                            layer.alert(result.msg||"删除专题项失败", {icon: 5});
                        }
                    },
                    error : function(){
                        layer.alert("删除专题项失败", {icon: 5});
                    }
                });
            });
        });

        $("input[name='_list_toplevel']").on("change",function(){
            var value = 0;
            try{
                value = parseInt($(this).val());
                if(value<0 || isNaN(value)){
                    value = 0;
                }
            }catch(e){
                value = 0;
            }
            $(this).val(value);
        });

        topicListForm.find("input[name='_topic_update_priority']").on("click",function(){

            var reqStr = "";
            var topLevel = "";
            var flag = true;
            $("input[name='ids']:checked").each(function(){
                topLevel = $(this).parents("tr").find("input[name='_list_toplevel']").val();
                if(parseInt(topLevel) < 0 || parseInt(topLevel) > 1000){
                    flag = false;
                }
                if(reqStr){
                    reqStr += ";"+ $(this).val() + ":" + topLevel
                }else{
                    reqStr += $(this).val() + ":" + topLevel;
                }
            });
            if(!flag){
                layer.alert("排列顺序范围需在[1-1000]", {icon: 5});
                return;
            }
            if(!reqStr){
                layer.alert("请选择需要修改排列顺序的专题项", {icon: 5});
                return ;
            }

            layer.confirm("是否确认修改选中的专题项？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/cms/topic/updateBatch.php",
                    type : "get",
                    data : {
                        keyValList : reqStr
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.alert(result.msg||"修改专题项成功", {icon: 1});
                            setTimeout(function(){
                                location.href=ctx+"/cms/topic/list.php";
                            },1000);
                        }else{
                            layer.alert(result.msg||"修改专题项失败", {icon: 5});
                        }
                    },
                    error : function(){
                        layer.alert("修改专题项失败", {icon: 5});
                    }
                });
            });
        });

        topicListForm.find("a[name='_topic_list_delete']").on("click",function(){

            var topicId = $(this).data("topicid");
            if(!topicId){
                layer.alert("请选择需要删除的专题项", {icon: 5});
                return ;
            }

            layer.confirm("是否确认删除该专题项？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/cms/topic/delete.php",
                    type : "get",
                    data : {
                        topicId : topicId
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            location.href=ctx+"/cms/topic/list.php";
                        }else{
                            layer.alert(result.msg||"删除专题项失败", {icon: 5});
                        }
                    },
                    error : function(){
                        layer.alert("删除专题项失败", {icon: 5});
                    }
                });
            });
        });

        topicListForm.find("div[name='nycon_sel_btn']").on("click",function(){
            $("input[name='ids']").attr("checked",!$("input[name='ids']").attr("checked"));
        });

    });
});