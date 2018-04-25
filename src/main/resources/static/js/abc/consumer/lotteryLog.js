/**
 * Created by Administrator on 2017-06-13.
 * 评论管理
 */
require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $("#consumer_more").click(function () {
            if ($(this).hasClass('shoqilai')) {
                $(this).html('更多条件').removeClass('shoqilai');
                $(".moretjYC").each(function () {
                    $(this).removeClass('moretjYC').addClass('moretj').fadeOut(500);
                });
            } else {
                $(this).html('隐藏条件').addClass('shoqilai');
                $(".moretj").each(function () {
                    $(this).removeClass('moretj').addClass('moretjYC').fadeIn(500);
                });
            }
        });

        var hasYC = false;
        $(".ny_tab_t").find(".moretj").each(function () {
            $(this).find('select').each(function () {
                if ($(this).val() != "" || $(this).val().length > 0) {
                    hasYC = true;
                }
            });
            $(this).find('input[type="text"]').each(function () {
                if ($(this).val() != "" || $(this).val().length > 0) {
                    hasYC = true;
                }
            });
        });
        if (hasYC) {
            $("#consumer_more").click();
        }
        ;

        $('#queryBtn').on("click", function () {
            abc.block();
            $("logListForm").submit();
        })

        $('#back').click(function () {
            //window.history.back();
               window.location.href = ctx + "/consumerManager/lottery/lotterylog.php";
        });
        function fahuo(state) {
            if ($("form[name='lottery_add_form']").isValid()) {
                layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                    function (index) {
                        var data1 = JSON.parse($("#linkForm").serializeJson());
                        data1.state = state;
                        data1 = JSON.stringify(data1);
                        $.ajax({
                            type: "POST",
                            data: data1,
                            url: ctx + "/consumerManager/lottery/lotterylogSave.php",
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
        }

        $("#jjsubmit").on("click", function () {
            fahuo("拒绝发货");
        });


        var $lotteryForm = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {

            },
            fields: {
                "kuaidiGS": {
                    rule:"快递公司:required"
                },
                "kuaididanhao": {
                    rule:"快递单号:required"
                }
            }
        };
        $("#submit").on("click", function () {
            $('#linkForm').validator('destroy');
            var $linkForm = $("#linkForm").validator($lotteryForm);
            $linkForm.isValid(function(v) {
                if(v){
                    fahuo("已发货");
                }
            })
        });
    });
})