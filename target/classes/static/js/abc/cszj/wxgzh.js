require(["../../config"], function () {
    require(["jquery.full", "qrcode"], function ($, qrCode) {

        $('#back').click(function () {
            window.location.reload();
        });
        //edit 提交
        $("#submit").click(function () {
            var url = "update_gzhsz.php";
            var data1 = $("#linkForm").serializeJson();
            $.ajax({
                type: "POST",
                url: url,
                data: data1,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    //console.log(data);
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

        });

    });
});