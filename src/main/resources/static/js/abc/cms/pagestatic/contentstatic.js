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
                                $("td[name='constantSel']").find("input[type='hidden']").val($(this).data("channelid"));
                                $("td[name='constantSel']").find("input[type='text']").val($(this).data("channelname"));
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

        $(".nycon_list").find("[abc-type='datebox']").each(function(){
            var targetNode = $(this);
            targetNode.datebox({
                editable: false,
                formatter: function (date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d);
                },
                parser: function (str) {
                    if (!str) {
                        return new Date();
                    }
                    var ss = str.split("-");
                    var y = parseInt(ss[0], 10);
                    var m = parseInt(ss[1], 10);
                    var d = parseInt(ss[2], 10);
                    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                        return new Date(y, m - 1, d);
                    } else {
                        return new Date();
                    }
                }
            });
        });

        $(".nycon_list").find("[name='staticContent']").bind("click",function(){

                    var $validatorFrom = $("div[name='content_static']").find("td[name='constantSel']").validator({
                        timely: 1,
                        theme: 'yellow_top',
                        fields: {
                            "columnName": "required;",
                        }
                    });

                    $validatorFrom.validator().trigger("showtip");
                    if ($validatorFrom.isValid()) {
                        layer.confirm('是否生成该栏目下内容静态页？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},function(index) {
                            layer.close(index);
                            layer.load(1, {shade: [0.6, "#393D49"]});
                            $.ajax({
                                type: 'GET',
                                url: ctx + '/content/sysmaintain/static_content.php/' + $('#columnId').val() + '?random=' + Math.random(),
                                data: {
                                    'startTime': $('#startTime').val()
                                },
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
        })
    });
});