require(["../../../config"], function () {
    require(["jquery-3.1.0","jquery.easyui.min","abc.common","layui","../abc/util/page"], function ($) {

        /* 单个详情 */
        $('body').on('click', '.js_view', function() {
            var html = '';
            $.ajax({
                type: "GET",
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    var index=layer.open({
                        type: 1,
                        title: "掌门人详情",
                        skin: 'layui-layer-molv',
                        closeBtn: 2,
                        area: ['640px',"600px"],
                        offset: [200],
                        shadeClose: false,
                        content: data,
                        success: function (elem, i) {
                            var $target = $(elem);
                            $target.find("button[name='close-layer-btn']").on("click", function () {
                                layer.close(i);
                            });
                        },
                    });
                },
                error: function(){
                    layer.msg('获取掌门人信息失败', {icon: 5});
                }
            });
        });


        /* 单个详情 */
        $('body').on('click', '.js_status', function() {
            var html = '';
            $.ajax({
                type: "GET",
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    var index=layer.open({
                        type: 1,
                        title: "掌门人详情",
                        skin: 'layui-layer-molv',
                        closeBtn: 2,
                        area: ['640px',"700px"],
                        offset: [200],
                        shadeClose: false,
                        content: data,
                        success: function (elem, i) {
                            var $target = $(elem);
                            $target.find("button[name='close-layer-btn']").on("click", function () {
                                layer.close(i);
                            });
                            $('body').on('click', '.js_change_status', function(e){
                                e.preventDefault();
                                var remark = $.trim($("#headman_remark").val());
                                if($(this).attr("data-status") == 'refuse'){
                                    if(!remark){
                                        layer.msg('请输入拒绝原因', {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:2000});
                                        return;
                                    }
                                }
                                $.ajax({
                                    type: "post",
                                    url:  ctx+ $(this).attr("data-href"),
                                    data: JSON.stringify({id:$(this).attr("data-id"),status:$(this).attr("data-status"),remark:remark }),
                                    contentType: "application/json",
                                    dataType: "JSON",
                                    async: false,
                                    success: function(data){
                                        layer.msg('操作成功', {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        location.href = $(".js_currLink").val();
                                    },
                                    error: function(){
                                        layer.msg('操作失败', {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
                                    }
                                });
                            });
                        },
                    });
                },
                error: function(){
                    layer.msg('获取掌门人信息失败', {icon: 5});
                }
            });
        });
    })
});
