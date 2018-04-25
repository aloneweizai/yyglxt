/**
 * Created by LiuQi
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0", "../abc/util/date", "abc.common", "../abc/util/page", "layui"], function ($, date) {

        var init_layUi = function () {
            layui.use('form', function () {
                var form = layui.form;
                form.render();
                var laydate = layui.laydate;
                lay('.date-item').each(function () {
                    laydate.render({
                        elem: this
                    })
                });
            });
        };
        var initUrl = "";
        if (initModule == 'tipOff') {
            initUrl = ctx + '/bangbang/contentAudit/tipOff/list.php';
        } else {
            initUrl = ctx + '/bangbang/contentAudit/system/list.php';
        }
        if (initStatus) {
            initUrl = initUrl + "?status=" + initStatus;
        }
        $.ajax({
            type: 'GET',
            url: initUrl,
            data: '',
            async: false,
            success: function (data) {
                $(".js_body_div").html(data);
                date.init();
                init_layUi();
            }
        });

        /* 系统内容审核查询 */
        $('body').on('click', '.js_query_sysAudit', function (e) {
            e.preventDefault();
            $.ajax({
                type: 'GET',
                url: ctx + '/bangbang/contentAudit/system/list.php?content=' + $("#content").val() + '&status=' + $("#status").val()
                + '&beginTime=' + $("#beginTime").val() + '&endTime=' + $("#endTime").val(),
                data: '',
                async: false,
                success: function (data) {
                    $(".js_body_div").html(data);
                    date.init();
                    init_layUi();
                }
            });
        });

        /* 系统内容审核查询 */
        $('body').on('click', '.js_query_tipOff', function (e) {
            e.preventDefault();
            $.ajax({
                type: 'GET',
                url: ctx + '/bangbang/contentAudit/tipOff/list.php?status=' + $("#status").val(),
                data: '',
                async: false,
                success: function (data) {
                    $(".js_body_div").html(data);
                    date.init();
                    init_layUi();
                }
            });
        });

        $('body').on('click', '.js_view', function (e) {
            e.preventDefault();
            $.ajax({
                type: "GET",
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    var index = layer.open({
                        type: 1,
                        title: "详情",
                        skin: 'layui-layer-molv',
                        closeBtn: 2,
                        area: ['680px', "500px"],
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
                }
            });
        });


        $('body').on('click', '.js_changeStatus', function () {
            var _this = $(this);
            layer.confirm('确认操作？', {
                    title: '操作提示',
                    btn: ['确认', '取消'],
                    icon: 3,
                    offset: abc.winscrollTop(200),
                    closeBtn: 0,
                    zIndex: 90000
                },
                function () {
                    abc.block();
                    $.ajax({
                        type: "POST",
                        url: _this.attr("data-href"),
                        data: '',
                        async: true,
                        success: function (data) {
                            abc.unblock();
                            if (data && data.code == "2000") {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                $.ajax({
                                    type: 'GET',
                                    url: $(".js_currLink").val(),
                                    data: '',
                                    async: false,
                                    success: function (data) {
                                        $(".js_body_div").html(data);
                                        date.init();
                                        init_layUi();
                                    }
                                });
                            } else {
                                abc.layerAlert(false, '操作失败: ' + data.message);
                            }
                        }
                    });
                }
            );
        });

        $('body').on('click', '.openLink', function () {
            var type = $(this).attr("data-s");
            var href = $(this).attr("data-href");
            var id = $(this).attr("data-id");
            if (type == "question" || type == "cheats") {
                window.open(href + id);
            } else {
                getQuestionId(href, type, id);
            }
        });

        function getQuestionId(href, type, id) {
            $.ajax({
                type: "GET",
                url: ctx + '/bangbang/contentAudit/tipOff/getview.php?type='+type+"&id="+id,
                async: true,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    abc.unblock();
                    window.open(href + data.id);
                },
                error: function (data) {
                    abc.unblock();
                    layer.msg("操作失败", {icon: 5,title: "", closeBtn:0, offset: abc.winscrollTop(200)});
                    //abc.layerAlert(false, data.message);
                }
            });
        }

        $(".js_tab li").click(function () {
            if ($(this).hasClass("noe")) {
                return;
            } else {
                $(this).addClass("noe").siblings().removeClass("noe");
                $.ajax({
                    type: 'GET',
                    url: $(this).attr("data-href"),
                    async: false,
                    success: function (data) {
                        $(".js_body_div").html(data);
                        date.init();
                        init_layUi();
                    }
                });
            }
        });
        abc.ajaxPage = function (url) {
            $.ajax({
                type: 'GET',
                url: url,
                data: '',
                async: false,
                success: function (data) {
                    $(".js_body_div").html(data);
                    date.init();
                    init_layUi();
                }
            });
        };
        abc.ajaxGoPage = function () {
            var page = $('#_goPs').val();
            var url = $('#_goPage').attr("data-url");
            if (page.match("^[1-9][0-9]*$")) {
                $.ajax({
                    type: 'GET',
                    url: url.replace("[:page]", page),
                    async: false,
                    success: function (data) {
                        $(".js_body_div").html(data);
                        date.init();
                        init_layUi();
                    }
                });
            } else {
                return;
            }
        };


        /*举报奖励积分*/
        $('body').on('click', '.js_tipoff_approved', function (e) {
            e.preventDefault();
            $(".js_submit_tipoff_rewardsPoints").attr("data-id", $(this).attr("data-id"));
            var index = showContentLayer('请输入奖励积分', 400, 450, 200, $("#tipoff_rewardsPoints_pop"));
            init_layUi();
            $("button[name='close-rewardsPoints-btn'],.layui-layer-close").on("click", function (e) {
                e.preventDefault();
                layer.close(index);
                $("#tipoff_rewardsPoints_pop").hide();
            });
        });
        /*举报详情*/
        $('body').on('click', '.js_tipoff_view', function (e) {
            e.preventDefault();
            $.ajax({
                type: "GET",
                url: $(this).attr("data-href"),
                async: false,
                success: function (data) {
                    $("#tipoff_refuseReason").val("");
                    var index = layer.open({
                        type: 1,
                        title: "举报详情",
                        skin: 'layui-layer-molv',
                        closeBtn: 2,
                        area: ['680px', "500px"],
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
                }
            });
        });
        /* 举报通过奖励积分提交 */
        $('body').on('click', '.js_submit_tipoff_rewardsPoints', function (e) {
            e.preventDefault();
            var _this = $(this);
            var id = _this.attr("data-id");
            var rewardsPoints = $.trim($("#tipoff_rewardsPoints").val());
            if (rewardsPoints == '') {
                layer.msg("输入内容不能为空", {offset: abc.winscrollTop(200), shade: 0.3, icon: 5, time: 1000});
                return;
            }
            if (rewardsPoints < 0) {
                layer.msg("请输入正整数", {offset: abc.winscrollTop(200), shade: 0.3, icon: 5, time: 1000});
                return;
            }
            //if(!(/^[1-9]\d*$/.test(rewardsPoints))){
            //    layer.msg("请输入正整数", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000});
            //    return;
            //}
            layer.confirm('确认操作？', {
                    title: '操作提示',
                    btn: ['确认', '取消'],
                    icon: 3,
                    offset: abc.winscrollTop(200),
                    closeBtn: 0,
                    zIndex: 2147483647
                },
                function () {
                    $.ajax({
                        type: 'POST',
                        url: ctx + '/bangbang/contentAudit/tipOff/modifyStatus.php',
                        data: JSON.stringify({
                            id: id,
                            status: 'approved',
                            rewardsPoints: rewardsPoints,
                            refuseReason: "审批通过"
                        }),
                        contentType: "application/json",
                        dataType: "JSON",
                        async: false,
                        success: function (data) {
                            layer.closeAll();
                            $.ajax({
                                type: 'GET',
                                url: $(".js_currLink").val(),
                                data: '',
                                async: false,
                                success: function (data) {
                                    $(".js_body_div").html(data);
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                    init_layUi();
                                }
                            });
                        }
                    });
                });
        });
        /*举报拒绝弹出*/
        $('body').on('click', '.js_tipoff_refuse', function (e) {
            e.preventDefault();
            $(".js_submit_tipoff_refuse").attr("data-id", $(this).attr("data-id"));
            var index = showContentLayer('请输入拒绝理由', 400, 350, 200, $("#tipoff_refuse_pop"));
            $(".layui-layer-close").on("click", function (e) {
                e.preventDefault();
                layer.close(index);
                $("#tipoff_refuse_pop").hide();
            });
        });
        /* 举报拒绝提交 */
        $('body').on('click', '.js_submit_tipoff_refuse', function (e) {
            e.preventDefault();
            var _this = $(this);
            var id = _this.attr("data-id");
            var refuseReason = $.trim($("#tipoff_refuseReason").val());
            if (refuseReason == '' || refuseReason == '') {
                layer.msg("拒绝理由不能为空", {offset: abc.winscrollTop(200), shade: 0.3, icon: 5, time: 1000});
                return;
            }
            layer.confirm('确认操作？', {
                    title: '操作提示',
                    btn: ['确认', '取消'],
                    icon: 3,
                    offset: abc.winscrollTop(200),
                    closeBtn: 0,
                    zIndex: 2147483647
                },
                function () {
                    $.ajax({
                        type: 'POST',
                        url: ctx + '/bangbang/contentAudit/tipOff/modifyStatus.php',
                        data: JSON.stringify({id: id, status: 'refuse', refuseReason: refuseReason}),
                        contentType: "application/json",
                        dataType: "JSON",
                        async: false,
                        success: function (data) {
                            layer.closeAll();
                            $.ajax({
                                type: 'GET',
                                url: $(".js_currLink").val(),
                                data: '',
                                async: false,
                                success: function (data) {
                                    $(".js_body_div").html(data);
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                    init_layUi();
                                }
                            });
                        }
                    });
                });
        });


        /*禁言*/
        $('body').on('click', '.js_disableUser_pop', function () {
            $(".js_disableUser").attr("data-userId", $(this).attr("data-userId"));
            showContentLayer('用户合格审查设置禁言天数', 400, 400, 200, $("#disable_pop"));
            $(".layui-layer-close").on("click", function (e) {
                e.preventDefault();
                $("#disable_pop").hide();
            });
        });
        /*禁言*/
        $('body').on('click', '.js_disableIP_pop', function () {
            $(".js_disableIP").attr("data-ip", $(this).attr("data-ip"));
            showContentLayer('IP合格审查设置禁言天数', 400, 400, 200, $("#disable_pop"));
            $(".layui-layer-close").on("click", function (e) {
                e.preventDefault();
                $("#disable_pop").hide();
            });
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
                closeBtn = 2;
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
                        layer.close(i);
                        if ($("#disable_pop")) {
                            $("#disable_pop").hide();
                        }
                    });
                    event.success();
                },
                end: event.end,
                cancel: event.cancel
            });
            return index;
        };
        /* 禁言提交 */
        $('body').on('click', '.js_disableUser', function (e) {
            var _this = $(this);
            var disableTime = $.trim($("[name='disable_time']").val());
            var disableReason = $.trim($("[name='disable_reason']").val());
            if (disableTime == '' || disableReason == '') {
                layer.msg("输入内容不能为空", {offset: abc.winscrollTop(200), shade: 0.3, icon: 5, time: 1000});
                return;
            }
            layer.confirm('确认操作？', {
                    title: '操作提示',
                    btn: ['确认', '取消'],
                    icon: 3,
                    offset: abc.winscrollTop(200),
                    closeBtn: 0,
                    zIndex: 2147483647
                },
                function () {
                    var date = new Date(new Date().getTime() + (disableTime * 24 * 60 * 60 * 1000));
                    $.ajax({
                        type: 'POST',
                        url: _this.attr("data-href"),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: JSON.stringify({
                            reason: disableReason,
                            userId: _this.attr("data-userId"),
                            activeTime: date
                        }),
                        success: function (data) {
                            layer.closeAll();
                            $.ajax({
                                type: 'GET',
                                url: $(".js_currLink").val(),
                                data: '',
                                async: false,
                                success: function (data) {
                                    $(".js_body_div").html(data);
                                    init_layUi();
                                }
                            });
                        }
                    });
                });
        });

        /* 禁言提交 */
        $('body').on('click', '.js_disableIP', function (e) {
            var _this = $(this);
            var disableTime = $.trim($("[name='disable_time']").val());
            var disableReason = $.trim($("[name='disable_reason']").val());
            if (disableTime == '' || disableReason == '') {
                layer.msg("输入内容不能为空", {offset: abc.winscrollTop(200), shade: 0.3, icon: 5, time: 1000});
                return;
            }
            layer.confirm('确认操作？', {
                    title: '操作提示',
                    btn: ['确认', '取消'],
                    icon: 3,
                    offset: abc.winscrollTop(200),
                    closeBtn: 0,
                    zIndex: 2147483647
                },
                function () {
                    var date = new Date(new Date().getTime() + (disableTime * 24 * 60 * 60 * 1000));
                    $.ajax({
                        type: 'POST',
                        url: _this.attr("data-href"),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: JSON.stringify({reason: disableReason, ip: _this.attr("data-ip"), activeTime: date}),
                        success: function (data) {
                            layer.closeAll();
                            $.ajax({
                                type: 'GET',
                                url: $(".js_currLink").val(),
                                data: '',
                                async: false,
                                success: function (data) {
                                    $(".js_body_div").html(data);
                                    init_layUi();
                                }
                            });
                        }
                    });
                });
        });

    })
});