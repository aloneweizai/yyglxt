/**
 * Created by Administrator on 2017-06-06.
 * 友情链接
 */
require(["../../config"], function () {
    require(["jquery.full", "swfupload.full","../abc/util/pagination"], function ($) {
        $(function () {

            //数据校验
            var $validatorWsysVoFrom = $("form").validator({
                theme: 'simple_right',
                timely: 1
            });

            $('#reset').click(function () {
                window.location.href = ctx + "/financed/logisticsList.php";
            });

            $("a[data-type='download']").click(function () {
                var val=$(this).attr('data-val');
                window.location.href=val;
                //abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
            });

            $("#consumer_submit").on("click", function () {
                if ($validatorWsysVoFrom.isValid()) {
                    var comp = $("#comp").val();
                    var page = $("#page").val();
                    layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                        function (index) {
                            $.ajaxFileUpload({
                                url: ctx + "/financed/logisticsSave.php",
                                secureuri: false,
                                fileElementId: 'FILE01',//file标签的id
                                dataType: 'json',
                                data: {
                                    compName: $("#compName").val(),
                                    compCode: $("#compCode").val(),
                                    compUrl: $("#compUrl").val(),
                                    id: $("#compNameId").val(),
                                    sort: $("#sort").val()
                                },
                                success: function (data) {
                                    //console.log(data);
                                    if (data.result.code == '2000') {
                                        layer.msg("操作成功", {
                                            offset: abc.winscrollTop(200),
                                            shade: 0.3,
                                            icon: 1,
                                            time: 1000
                                        }, function () {
                                                window.location.href = ctx + "/financed/logisticsList.php?compName="+comp+"&page="+page;
                                            });
                                        setTimeout(function () {
                                            window.location.reload();//刷新当前页面.
                                            //window.location.href = ctx + "/financed/logisticsList.php";
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

        });

    })
})
