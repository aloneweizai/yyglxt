require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $('#queryBtn').on("click", function () {

            window.location.href = ctx + "/consumerManager/lottery/lotteryActivityprize.php?activityId="+ $("#activityId").val();
        });
        $("#lotteryId").change(function(){
            // var objS = $(this);
            // var url = objS.options[objS.selectedIndex].data-url;
            var objS = document.getElementById("lotteryId");
            var grade = objS.options[objS.selectedIndex];
            var url = ($(grade).attr("dataurl"));
            $("#imgid").attr("src",url);

            var noluck = $(grade).attr("datanoluck");

            if(noluck == "true"){
                $(".gailvId").css("display","none");
                $("#luck").val("");
            }else{
                $(".gailvId").css("display","");
            }
          //  console.log(url);
        });


        $('#back').click(function () {

            window.location.href = ctx + "/consumerManager/lottery/lotteryActivityprize.php?activityId="+ $("#activityId").val();
        });
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', '', $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        var $lotteryForm = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {

            },
            fields: {
                "lotteryId":{
                    rule:"奖品名称:required"
                },
                "amount": {
                    rule:"奖品总数:required"
                },
                "luck":{
                    rule:"中奖概率:required"
                },
                "userFreeCount": {
                    rule:"每天免费次数:required"
                },
                "sort": {
                    rule:"奖品自然排序:required;"
                },
                "val1": {
                    rule:"奖项名称:required;"
                }
            }
        };

        $("#submit").on("click", function () {
            $('#linkForm').validator('destroy');
            var $linkForm = $("#linkForm").validator($lotteryForm);
            $linkForm.isValid(function(v) {
                if (v) {
                    layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                        function (index) {
                            var data1 = JSON.parse($("#linkForm").serializeJson());
                            data1 = JSON.stringify(data1);
                            $.ajax({
                                type: "POST",
                                data: data1,
                                url: ctx + "/consumerManager/lottery/lotteryActivityprizeSave.php",
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
