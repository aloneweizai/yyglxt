
require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $('body').on("click", ".js-query",function () {
            abc.block();
            $("#lotteryListForm").submit();
        })

        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', '', $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });
        $("#types").change(function(){
            var types =$("#types").val();
            if(types=="实物"){
                $("input:radio[value="+ true + "]").attr('checked',false);
                $("input:radio[value="+ false + "]").attr('checked',false);
                $("input:radio[value="+ true + "]").attr('checked',true);
            }else if(types=="虚拟物品"){
                $("input:radio[value="+ true + "]").attr('checked',false);
                $("input:radio[value="+ false + "]").attr('checked',false);
                $("input:radio[value="+ false + "]").attr('checked',true);
            }
        });

        var $lotteryForm = {
            theme: 'simple_right',
            stopOnError:true,
            timely: 1,
            rules: {

            },
            fields: {
                "name": {
                    rule:"奖品名称:required"
                },
                "FILE01": {
                    rule:"图片:required"
                },
                "cost": {
                    rule:"成本价:required;"
                },
                "types":{
                    rule:"奖品类别;required"
                },
                "activityId":{
                    rule:"所属活动;required"
                 }
            }
        };

        $("#submit").on("click", function () {
            $('#linkForm').validator('destroy');
            var $linkForm = $("#linkForm").validator($lotteryForm);
            $linkForm.isValid(function(v){
                if(v){
                    layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                        function (index) {
                            var data1 = JSON.parse($("#linkForm").serializeJson());
                            $.ajaxFileUpload({
                                url: ctx + "/consumerManager/lottery/lotterySave.php",
                                secureuri: false,
                                fileElementId: 'FILE01',//file标签的id
                                dataType: 'json',
                                data: data1  ,
                                success: function (data) {
                                    //console.log(data);
                                    if (data.result.code == '2000') {
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                        setTimeout(function () {
                                            $('#back').click();
                                        }, 2000);
                                    } else {
                                        layer.msg(data.result.message, {icon: 5});
                                    }
                                }
                            });
                        }
                    );
                }
            })
        })

        $('#back').click(function () {
           // window.history.back();
            window.location.href = ctx + "/consumerManager/lottery/lottery.php";
        });
    });
})