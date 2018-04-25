/**
 * Created by Administrator on 2017-06-13.
 * 二维码
 */
require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $('#queryBtn').on("click", function () {
            var name = $('#name').val();

            window.location.href = $("form").attr("action") + "?name=" + name;
        })

        $("#submit").click(function () {

            var url = "rqcode_save.php";
            var data1 = JSON.parse($("form").serializeJson());
            if(data1.name.length<1){
                layer.msg("错误，名称不能为空", {icon: 5});
                return false;
            }
            //data1.type = "0";
            data1=JSON.stringify(data1);

            $.ajax({
                type: "POST",
                url: url,
                data: data1,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (data.code == '2000') {
                        layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                        setTimeout(function () {
                        	window.location.href=document.referrer;
                        }, 1000);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            });

        });
        //a删除
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', "", $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        /*$('#back').click(function () {
            window.location.href =$("form").attr("action");
        });*/

        $("select[data-type='ajax']").each(function () {
            var this_ = $(this);
            var rule = this_.attr('data-rules').split(":");
            var urls = this_.attr('data-url');
            var val = this_.attr('data-val');
            $.ajax({
                type: "GET",
                url: urls,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (data) {
                        $.each(data, function (i, item) {
                            this_.append("<option " + (eval("item." + rule[0]) == val ? "selected" : "") + " value='" + eval("item." + rule[0]) + "'>" + eval("item." + rule[1]) + "</option>");
                        });
                    } else {
                    }
                }
            });
        });
    });
})