require(["../../config"], function () {
    require(["jquery.full", "../abc/consumer/page"], function ($, qrCode) {

        $("#queryywbtn").on('click', function () {
            var startTime = $("input[name='startDate']").val();
            var endTime = $("input[name='endDate']").val();
            if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
                if(CompareDate(startTime,endTime)){
                    abc.layerAlert(false, "结束时间不能小于开始时间");
                    return;
                }
                if(checkMonth(startTime,endTime)){
                    abc.layerAlert(false, "开始时间和结束时间必须在同一个月份中");
                    return;
                }
            }
            abc.block();
            $('#consumerListForm').submit();
        })

        $("#querysxbtn").on('click', function () {
            var startTime = $("input[name='startDate']").val();
            var endTime = $("input[name='endDate']").val();
            if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
                if(CompareDate(startTime,endTime)){
                    abc.layerAlert(false, "结束时间不能小于开始时间");
                    return;
                }
            }
            abc.block();
            $('#consumerListForm').submit();
        })

        function CompareDate(d1,d2)
        {
            return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
        }

        function checkMonth(d1,d2)
        {
            var year1 = d1.substr(0,4);
            var year2 = d2.substr(0,4);
            var mon1 = d1.substr(5,2);
            var mon2 = d2.substr(5,2);
            if(year1 != year2){
                return true;
            }
            else{
                if(mon1 != mon2){
                    return true;
                }
            }
        }

        $("#keyString").on('change', function(){
            var tmpStr = $(this).val( );
            var douhao = tmpStr.indexOf(",");
            if(douhao > 0){
                $("#searchTp").val('PART');
            }
        });

        //edit 提交
        $("#save_btn").click(function () {
            var url = $("#msgauto_edit_form").attr("action");
            var data1 = $("#msgauto_edit_form").serializeJson();
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
        //a删除
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', "", $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        $('#back').click(function () {
            window.location.href = document.referrer;
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
});