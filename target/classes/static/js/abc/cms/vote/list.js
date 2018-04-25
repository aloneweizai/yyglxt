require(["../../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {

        $("a[name='vote_list_delete']").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.alert("请选择需要删除的投票", {icon: 5});
                return ;
            }

            layer.confirm("是否确认删除该投票？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/cms/vote/delete.php",
                    type : "post",
                    data : {
                        voteId : voteId
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg("操作成功", {icon: 1},function(){
                                layer.closeAll();
                                //location.href=ctx+"/cms/vote/list.php";
                                window.location.reload();
                            });
                        }else{
                            layer.alert(result.message||"删除投票失败", {icon: 5},function(){
                                layer.closeAll();
                            });
                        }
                    },
                    error : function(){
                        layer.alert("删除投票失败", {icon: 5},function(){
                            layer.closeAll();
                        });
                    }
                });
            });
        });

        $("a[name='vote_list_to_zero']").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.alert("请选择需要清零的投票", {icon: 5});
                return ;
            }

            layer.confirm("是否确认清零该投票？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/cms/vote/toZero.php",
                    type : "post",
                    data : {
                        voteId : voteId
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg("操作成功", {icon: 1},function(){
                                layer.closeAll();
                                //location.href=ctx+"/cms/vote/list.php";
                                window.location.reload();
                            });
                        }else{
                            layer.alert(result.message||"清零投票失败", {icon: 5},function(){
                                layer.closeAll();
                            });
                        }
                    },
                    error : function(){
                        layer.alert("清零投票失败", {icon: 5},function(){
                            layer.closeAll();
                        });
                    }
                });
            });

        });

        $("a[name='activeOrInactiveVote']").on("click",function(){

            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.alert("请选择投票", {icon: 5});
                return ;
            }

            var index = layer.confirm("确认操作？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/cms/vote/activeOrInactiveVote.php?voteId=" + voteId,
                    type : "get",
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg("操作成功", {icon: 1},function(){
                                layer.closeAll();
                                //location.reload();
                                window.location.reload();
                            });
                        }else{
                            layer.alert(result.message||"操作失败", {icon: 5},function(){
                                layer.closeAll();
                            });
                        }
                    },
                    error : function(){
                        layer.alert("操作失败", {icon: 5},function(){
                            layer.closeAll();
                        });
                    }
                });
            });
        });

    })
});