require(["../../../config"], function () {
    require(["jquery.full"], function ($) {
        var divNode = $('div[name="content_static"]');
        divNode.find("button[name='_channel_btn']").on("click",function(){
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
                                divNode.find("input[type='hidden']").val($(this).data("channelid"));
                                divNode.find("input[type='text']").val($(this).data("channelname"));
                                layer.closeAll();
                            });
                        }
                    });
                },
                error: function () {
                    layer.closeAll();
                    layer.msg('查询栏目信息失败', {icon: 5});
                }
            });
        });

        $(".nycon_list").find("[name='staticColumn']").bind("click",function(){

                    var $validatorFrom = $("div[name='content_static']").find("div[name='columnSel']").validator({
                        timely: 1,
                        theme: 'yellow_top',
                        fields: {
                            "columnName": "required;",
                        }
                    });


                    $validatorFrom.validator().trigger("showtip");
                    if ($validatorFrom.isValid()) {
                        layer.confirm('是否生成栏目首页和列表静态页？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},function(index) {
                            layer.close(index);
                            layer.load(1, {shade: [0.6, "#393D49"]});
                            $.ajax({
                                type: 'GET',
                                url: ctx + '/content/sysmaintain/static_column.php/' + $('#columnId').val() + '?random=' + Math.random(),
                                //async: false,
                                dataType: "JSON",
                                success: function (data) {
                                    layer.closeAll();
                                    layer.msg(data.result.message, {icon: 1});
                                    //alert(data.result.message);
                                },
                                error: function () {
                                    layer.closeAll();
                                    layer.msg("操作失败", {icon: 2});
                                }

                            });
                        });
                    }
        });

        $(".nycon_list").find("[name='staticIndex']").bind("click",function(){
            layer.confirm('是否生成站点首页静态页？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},function(index) {
                layer.close(index);
                layer.load(1, {shade: [0.6, "#393D49"]});

                $.ajax({
                    type: 'GET',
                    url: ctx + '/content/sysmaintain/staticIndex.php',
                    //async: false,
                    dataType: "JSON",
                    success: function (data) {
                        layer.closeAll();
                        layer.msg(data.result.message, {icon: 1});
                    },
                    error: function () {
                        layer.closeAll();
                        layer.msg("操作失败", {icon: 2});
                    }
                });

            });
        })
    });
});