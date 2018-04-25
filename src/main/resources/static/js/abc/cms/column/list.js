require(["../../../config"], function () {
    require(["jquery.full"], function ($) {

        $("table[name='column_tree_table'] a[name='btn_moveChannel']").off("click").on("click",function(){
            var channelId = $(this).data("channelid")||"";
            $.ajax({
                type: "GET",
                url: ctx+"/content/column/ajaxColTree.php?channelId="+channelId,
                success: function(result){
                    layer.open({
                        area: ['600px', '700px'],
                        type: 1,
                        title: "请选择栏目",
                        content: result,
                        success: function(){
                            $("div[name='column_tree_layer'] a[name='column_tree_name']").off("click").on("click",function(){
                                var targetChannelId = $(this).data("channelid");
                                if(!targetChannelId){
                                    layer.msg('请选择目标栏目');
                                    return;
                                }
                                layer.msg("确定将节点移动至该节点下吗？", {
                                    time: 15000, //20s后自动关闭
                                    btn: ['确定', '取消'],
                                    yes: function(){
                                        $.ajax({
                                            type: "GET",
                                            url: ctx+"/content/column/move.php?srcChannelId="+channelId+"&targetChannelId="+targetChannelId,
                                            success: function(moveResult){
                                                if(moveResult.code=='000'){
                                                    layer.alert(moveResult.message, {icon: 1});
                                                    setTimeout(function(){
                                                        location.reload();
                                                    },1000);
                                                }else{
                                                    layer.msg(moveResult.message, {icon: 5});
                                                }
                                            },
                                            error: function(){
                                                layer.msg("移动栏目节点失败", {icon: 5});
                                            }
                                        });
                                    }
                                });
                            });
                        }
                    });
                },
                error: function(){
                    layer.msg('查询栏目信息失败', {icon: 5});
                }
            });
        });

        $("table[name='column_tree_table'] a[name='btn_activeChannel']").off("click").on("click",function(){

            var channelId = $(this).data("channelid");

            var txt = $(this).text();
            if(txt=="启用"){
                txt = "停用";
            }else{
                txt = "启用";
            }

            if(!channelId){
                layer.alert("请选择需要" + txt + "的栏目项", {icon: 5});
                return ;
            }

            layer.confirm('是否确认'+txt+'该栏目？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                location.href= ctx+"/content/column/activeOrInactive.php?channelId=" + channelId;
            });
        });

        $("table[name='column_tree_table'] a[name='btn_deleteChannel']").off("click").on("click",function(){
            var columnId = $(this).data("columnid");
            if(!columnId){
                layer.alert("请选择需要删除的栏目项", {icon: 5});
                return ;
            }


            layer.confirm('是否确认删除该栏目项？', {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function(){
                $.ajax({
                    url : ctx+"/content/column/delete.php",
                    type : "get",
                    data : {
                        columnId : columnId
                    },
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg("操作成功", {icon: 1},function(){
                                location.href=ctx+"/content/column/list.php";
                            });

                        }else{
                            layer.alert(result.message||"删除栏目项失败", {icon: 5});
                        }
                    },
                    error : function(){
                        layer.alert("删除栏目项失败", {icon: 5});
                    }
                });
            });
        });

        $("table[name='column_tree_table'] a[name='column_tree_open_content']").off("click").on("click",function(){
            var channelId = $(this).data("channelid");
            var siteId = $(this).data("siteid");
            var url = ctx + "/content/content/list.php?channelId=" + channelId + "&siteId=" + siteId;

            var $parentTabNode = parent.$('#tt');
            var exist_tab = $parentTabNode.tabs('getTab', "文章管理");
            if (exist_tab) {
                $parentTabNode.tabs('close', "文章管理");
            }
            parent.addPanel("文章管理", url, "1200");
        });
    });
});