require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $('#queryBtn').on("click", function () {
            abc.block();
            $("actListForm").submit();
        })

        $('#back').click(function () {
           // window.history.back();
            window.location.href = ctx + "/consumerManager/lottery/lotteryActivity.php";
        });
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', '', $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });
        //查看
        $("a[data-type='edit']").click(function () {
            var id = $(this).attr("data-id");
            modleShow("lotteryActivityEdit.php",id);
        });

        function modleShow(url,id) {

            //在模态框的.moday-body位置异步加载url
            $(".modal-body").load(url + "?chakan=1&id=" + id , function() {//加载完成执行此

                $("#model-ok").hide();
                $("#modal-dialog").modal("show");
            })

        }

        var $actForm = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {

            },
            fields: {
                "name": {
                    rule:"活动名称:required"
                },
                "luck": {
                    rule:"模版名称:required"
                },
                "getlotteyDay": {
                    rule:"领奖有效天数:required;"
                },
                "userLotteryMaxDay": {
                    rule:"用户每天中奖次数:required;"
                },
                "userLevel":{
                    rule:"用户等级:required;"
                },
                "userFreeCount":{
                    rule:"每天免费次数:required;"
                },
                "timeStock": {
                    rule:"每天派奖总数:required"
                },
                "timeCount": {
                    rule:"当天已派奖数:required"
                },
                "startTime": {
                    rule:"开始时间:required;"
                },
                "endTime": {
                    rule:"结束时间:required;"
                }
            }
        };

        $("#submit").on("click", function () {
            $('#linkForm').validator('destroy');
            var $linkForm = $("#linkForm").validator($actForm);
            $linkForm.isValid(function(v) {
                if (v) {
                    layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                        function (index) {
                            var data1 = JSON.parse($("#linkForm").serializeJson());

                            //
                            data1 = JSON.stringify(data1);
                            $.ajax({
                                type: "POST",
                                data: data1,
                                url: ctx + "/consumerManager/lottery/lotteryActivitySave.php",
                                async: false,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    if (data.code == '2000') {
                                        layer.msg("操作成功", {
                                            offset: abc.winscrollTop(200),
                                            shade: 0.3,
                                            icon: 1,
                                            time: 1000
                                        });
                                        setTimeout(function () {
                                            $('#back').click();
                                        }, 2000);
                                    } else {
                                        layer.msg(data.message, {icon: 5});
                                    }

                                }
                            });
                        }
                    );
                }
            })
        });
    });
})
