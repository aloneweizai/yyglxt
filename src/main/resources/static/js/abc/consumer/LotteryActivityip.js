require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $('#queryBtn').on("click", function () {

            window.location.href = ctx + "/consumerManager/lottery/lotteryActivityip.php?ip=" + $("#ip").val();
        })

        $('#back').click(function () {
            window.location.href = ctx + "/consumerManager/lottery/lotteryActivityip.php?activityId="+ $("#activityId").val();

        });
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', '', $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });
        $("#submit").on("click", function () {
            if ($("#linkForm").isValid()) {
                layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                    function (index) {
                        var data1 = JSON.parse($("#linkForm").serializeJson());
                           
 //
                        data1=JSON.stringify(data1);
                        $.ajax({
                            type: "POST",
                            data:  data1,
                            url: ctx + "/consumerManager/lottery/lotteryActivityipSave.php",
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data.code == '2000') {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
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
        });
    });
})
