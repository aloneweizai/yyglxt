/**
 * Created by Administrator on 2017-06-13.
 * 评论管理
 */
require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        $('#queryBtn').on("click", function () {
            var openid = $('#openid').val();
            var nickname = $('#nickname').val();
            var startTime = $('#startTime').val();
            var endTime = $('#endTime').val();
            if (typeof(startTime) === "undefined") {
                startTime = '';
            }
            if (typeof(endTime) === "undefined") {
                endTime = '';
            }
            if (openid != '') {
                openid = encodeURI(openid);
            }
            window.location.href = ctx + "/cszjs/wxuser/list.php?openid=" + openid + "&nickname=" + nickname + "&startTime=" + startTime + "&endTime=" + endTime;
        })
        //a删除
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        $('#back').click(function () {
            window.location.href = ctx + "/cszjs/wxuser/list.php";
        });

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