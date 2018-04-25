require(["../../config"], function () {
    require(["jquery.full",  "../abc/util/date"], function ($) {
        layui.use(['laydate'], function(){
            var laydate = layui.laydate; //获得laydate模块
            laydate.render({
                elem: '#test30',
                theme: '#14b9d5'
            });
            //使用模块
        });


        $("#consumer_more").click(function(){
            if($(this).hasClass('shoqilai')){
                $(this).val('更多条件').removeClass('shoqilai');
                $(".moretjYC").each(function(){
                    $(this).removeClass('moretjYC').addClass('moretj').fadeOut(500);
                });
            }else{
                $(this).val('隐藏条件').addClass('shoqilai');

                $(".moretj").each(function(){
                    $(this).removeClass('moretj').addClass('moretjYC').fadeIn(500);
                });
            }
        });
        var hasYC=false;
        $(".moretj").each(function(){
            $(this).find('select').each(function(){
                if($(this).val()!="" || $(this).val().length>0){
                    hasYC=true;
                }
            });
            $(this).find('input[type="text"]').each(function(){
                if($(this).val()!="" || $(this).val().length>0){
                    hasYC=true;
                }
            });
        });
        if(hasYC){$("#consumer_more").click();};

        $("a[name='a1']").on("click", function () {
            var id =($(this).attr("data-id"));
            modleShow("systemRecordEdit.php",id);
        });
        function modleShow(url,id) {
            var yyyyMMdd=$("input[name='yyyyMMdd']").val();
            //在模态框的.moday-body位置异步加载url
            $(".modal-body").load(url + "?id=" + id+"&yyyyMMdd="+yyyyMMdd, function() {//加载完成执行此

                $("#model-ok").hide();
                $("#modal-dialog").modal("show");
            })

        }
        //查询
        $("#queryBtn").click(function(){
            goPage(-1);
        });
        //每页大小
        $("#consumer_size").keypress(function(e) {
            if(e.which == 13) {
                goPage(-1);
            }
        });
        //首页
        $("#consumer_first").click(function(){
            goPage(1);
        });
        //前一页
        $("#consumer_up").click(function(){
            goPage(parseInt($("#cupageVal").val())-1);
        });
        //下一页
        $("#consumer_down").click(function(){
            goPage(parseInt($("#cupageVal").val())+1);
        });
        //最后一页
        $("#consumer_last").click(function(){
            goPage(parseInt($("#topageVal").val()));
        });
        //跳转
        $("#consumer_go").keypress(function(e) {
            if(e.which == 13) {
                goPage(isNaN($("#consumer_go").val())?1:$("#consumer_go").val());
            }
        });
        //跳转匡
        $("#consumer_gogo").click(function(e) {
            goPage(parseInt(isNaN($("#consumer_go").val())?1:$("#consumer_go").val()));
        });

        var goPage= function(index){
            if(isNaN($("#consumer_size").val())
                ||$("#consumer_size").val()==''){
                $("#consumer_size").val(15);
            }
            var curtPage=parseInt($("#cupageVal").val());
            var totalPage=parseInt($("#topageVal").val());
            if((index<1||index==curtPage||index>totalPage)&&index!=-1){
                return;
            }else if(index==-1){
                index=1;
            }
            $('#cupageVal').val(index);
            abc.block();
            $('#consumerListForm').submit();
        };


        $('#back').click(function () {
            window.location.href = ctx + "/consumerManager/lottery/systemRecord.php";
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
                            url: ctx + "/consumerManager/lottery/systemRecordSave.php",
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
