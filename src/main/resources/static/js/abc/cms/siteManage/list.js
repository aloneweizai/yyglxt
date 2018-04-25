require(["../../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {

        $("a[name='activeOrInactiveSite']").on("click",function(){

            var siteId = $(this).data("siteid");
            if(!siteId){
                layer.msg("请选择站点", {icon: 5});
                return ;
            }

            layer.confirm("确认操作？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    url : ctx+"/content/site/activeOrInactiveVote.php?siteId=" + siteId,
                    type : "get",
                    success : function(result){
                        if(result.code=='2000') {
                            layer.msg(result.message||"操作成功", {icon: 1}, function(){
                                location.href=location.href;
                            });

                        }else{
                            layer.alert(result.message||"操作失败", {icon: 5});
                        }
                    },
                    error : function(){
                        layer.alert("操作失败", {icon: 5});
                    }
                });
            });
        });

    });
});