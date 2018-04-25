require(["../../../config"], function () {
    require(["jquery.full"], function ($) {

        $.ajax({
            type: "GET",
            url: ctx + "/content/column/ajaxColTree.php?channelId=",
            success: function (result) {
                layer.open({
                    area: ['600px', '700px'],
                    type: 1,
                    content: result,
                    title: "请选择栏目",
                    closeBtn: 1,
                    success: function(){
                        $("div[name='column_tree_layer'] tr[name='column_tree_row']").off("click").on("click",function(){
                            window.history.replaceState(null,"title",$("#preUrl").val());

                            location.href = ctx + "/content/content/addPage.php?channelId=" + $(this).data("channelid");
                        });
                    },
                    cancel: function () {
                        history.back();
                    }
                });
            },
            error: function () {
                layer.msg('查询栏目信息失败', {icon: 5});
            }
        });
    });
});