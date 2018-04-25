require(["../../config"], function () {
    require(["jquery.full", "qrcode"], function ($, qrCode) {

        $("#consumer_submit").click(function () {

            var url = "update_btj.php";
            var data1 ={
                "id":$("#keyId").val(),
                "msgType":$("input[name='msgType']:checked").val()

            };
            if(data1.msgType == "news"){
                data1.content = $("#articleDiv2 textarea").val().trim();
            }else   if(data1.msgType == "text"){
                data1.content = $("#articleDiv1 textarea").val().trim();
            }else {
                data1.content = $("#articleDiv0 textarea").val().trim();
            }
            data1 = JSON.stringify(data1);

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
                            window.location.reload();//刷新当前页面.
                        }, 2000);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            });

        });

        var status = $("input:radio[name='type']:checked").val();
        var ui = document.getElementById("articleDiv"+status);
        //ui.style.display="";
        $("#querybtn").on('click', function () {
            var keyString = $('#keyString').val();
            window.location.href = ctx + "/cszjs/msgauto/msgauto_gjc.php?keyString=" + keyString;
        })

        //a删除
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        $('#back').click(function () {
            window.location.href = ctx + "/cszjs/msgauto/msgauto_gjc.php";
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

        $('#status0').on('click',function (){
            $('#noDiv').attr("class", "noDiv_0");
        })
        $('#status1').on('click',function (){
            $('#noDiv').attr("class", "noDiv_text");

        })
        $('#status2').on('click',function (){
            $('#noDiv').attr("class", "noDiv_news");
        })
    });
});