require(["../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {


        layui.use(['laydate'], function(){
            var laydate = layui.laydate; //获得laydate模块
            var form = layui.form;
            laydate.render({
                elem: '#test30',
                theme: '#14b9d5'
            });
            laydate.render({
                elem: '#test31',
                theme: '#14b9d5'
            });
            form.on('select(goodstyle)', function(data){
                if(data.value=="HYCZ"){
                    //document.getElementById('good').style.display = "none";
                    document.getElementById('vipselect').style.display = "inline-block";
                    document.getElementById('courseselect').style.display = "none";
                }
                else if(data.value=="JFCZ"){
                    //document.getElementById('good').style.display = "none";
                    document.getElementById('vipselect').style.display = "none";
                    document.getElementById('courseselect').style.display = "none";
                }
                else if(data.value=="CSKT"){
                    //document.getElementById('good').style.display = "none";
                    document.getElementById('vipselect').style.display = "none";
                    document.getElementById('courseselect').style.display = "inline-block";
                }
                else if(data.value=="UCSC"){
                    //document.getElementById('good').style.display = "none";
                    document.getElementById('vipselect').style.display = "none";
                    document.getElementById('courseselect').style.display = "none";
                }
                else if(data.value=="ALL"){
                    //document.getElementById('good').style.display = "none";
                    document.getElementById('vipselect').style.display = "none";
                    document.getElementById('courseselect').style.display = "none";
                }
            });
            //使用模块
        });


        var consumerListForm = $("form[name='order_detail_form']");
        $('#sendSubmit').click(function () {
            var orderNo = $("#orderNo").val();
            if (orderNo == 'undefined' || orderNo == null) {
                orderNo = "";
            }
            var userId = $("#userId").val();
            if (userId == 'undefined' || userId == null) {
                userId = "";
            }
            var val=$(this).attr('data-val');
            var remark = $("#fhremark").val();
            var expressNo = $("#expressNo").val();
            var expressCompId = $("#expressCompId").val();
            if(val=="1"){
                if (expressCompId == 'undefined' || expressCompId == null ||expressCompId.trim() == "") {
                    abc.layerAlert(false, '物流公司不能为空!');
                    //layer.msg("备注信息不能为空!", {icon: 5});
                    return;
                }
                if (expressNo == 'undefined' || expressNo == null ||expressNo.trim() == "") {
                    abc.layerAlert(false, '快递单号不能为空!');
                    //layer.msg("备注信息不能为空!", {icon: 5});
                    return;
                }
            }
           else{
                if (remark == 'undefined' || remark == null ||remark.trim() == "") {
                    abc.layerAlert(false, '备注信息不能为空!');
                    //layer.msg("备注信息不能为空!", {icon: 5});
                    return;
                }
            }

            var doType = $("#doType").val();
            var fhData={};
            fhData["orderNo"]=orderNo;
            fhData['userId']=userId;
            fhData['remark']=remark;
            fhData['expressCompId']=expressCompId;
            fhData['expressNo']=expressNo;
            abc.layerAjaxConfirm("POST", ctx + "/financed/send.php", JSON.stringify(fhData), $("#link").val());
        });

        $('#sendBtn').click(function () {
            $("#fhModal").show();
        });

        $('#back').click(function () {
            var doType = $("#doType").val();
            window.location.href = ctx + "/financed/orderList.php?dateType=1&doType="+doType;
        });

        $('#closeBtn').click(function () {
            $("#fhModal").hide();
        });

        //查询
        $("#consumer_query").click(function(){
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
                $("#consumer_size").val(10);
            }
            var curtPage=parseInt($("#cupageVal").val());
            var totalPage=parseInt($("#topageVal").val());
            if((index<1||index==curtPage||index>totalPage)&&index!=-1){
                return;
            }else if(index==-1){
                index=1;
            }
            $('#cupageVal').val(index);
            var startTime = $("input[name='startTime']").val();
            var endTime = $("input[name='endTime']").val();
            if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
                if(CompareDate(startTime,endTime)){
                    abc.layerAlert(false, "结束时间不能小于开始时间");
                    return;
                }
            }
            abc.block();
            $('#consumerListForm').submit();
        }

        function CompareDate(d1,d2)
        {
            return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
        }

        var compId;
        $(".compId").change(function() {
            compId = $(this).val();
        });

        $("[id='importbtn']").click(function () {
            var doType = $("#doType").val();
            var fileNode = $("#FILE01");
            if(compId == ""||compId ==undefined){
                abc.layerAlert(false,"请选择快递公司");
                return;
            }
            if($("#FILE01").val()==''){
                abc.layerAlert(false,"请选择文件");
                return;
            }
            layer.confirm('是否导入？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                function (index) {
                    $.ajaxFileUpload({
                        url: ctx + "/financed/importExcel.php",
                        secureuri: false,
                        fileElementId: 'FILE01',//file标签的id
                        dataType: 'json',
                        data: {
                            compId: compId
                        },
                        success: function (data) {
                            //console.log(data);
                            if (data.result.code == '2000') {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                setTimeout(function () {
                                    window.location.href = ctx + "/financed/orderList.php?dateType=1&doType="+doType;
                                }, 2000);
                            } else {
                                layer.msg(data.result.message, {icon: 5});
                            }
                        }
                    });
                }
            );
        });

        $("#batch_dr").click(function(){
            $("#myModal").show();
            $(".compId").val("");
        });

        $("button[data-dismiss]").click(function(){
                $("#myModal").find(".modal-dialog").animate({'top':'-500px'},500,function(){
                    $("#myModal").hide();
                });
        });

        $(".uploadFile").change(function() {
            var _val=$(this).val();
            var types=['xls','xlsx'];
            var type=_val.substring(_val.lastIndexOf(".")+1);
            if(types.indexOf(type)<0){
                $(this).val('');
                abc.layerAlert(false,"请上传excel文件");
            }
        });

        $('#batch_dc').click(function () {
            var doType = $("#doType").val();
            layer.confirm('是否导出？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                function (index) {
                    layer.msg("导出成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                        window.location.href = ctx+"/financed/exportExcel.php";
                    });
                }
            );
        });

        $("a[data-type='opendialog']").click(function(){
            abc.block();
            var url=$(this).attr("data-url");
            var iframe=document.getElementById("look_frame");
            iframe.src=url;
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    //$("#myModal3").fadeIn();
                    $("#lookModal").show();
                    $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function(){
                    //$("#myModal3").fadeIn();
                    $("#lookModal").show();
                    $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                };
            }
        });

        $("[data-dismiss='lookModal']").click(function(){
            $("#lookModal").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#lookModal").hide();
            });
        });

        $("a[data-type='vipdialog']").click(function(){
            abc.block();
            var url=$(this).attr("data-url");
            var iframe=document.getElementById("vip_frame");
            iframe.src=url;
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    //$("#myModal3").fadeIn();
                    $("#vipModal").show();
                    $("#vipModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function(){
                    //$("#myModal3").fadeIn();
                    $("#vipModal").show();
                    $("#vipModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                };
            }
        });

        $("[data-dismiss='vipModal']").click(function(){
            $("#vipModal").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#vipModal").hide();
            });
        });
        $("#vipchoosebtn").click(function(){
            var reData={};
            var oselectall = document.getElementById('vip_frame').contentWindow.document.getElementsByName("id");
            var _id;
            for (var i = 0; i < oselectall.length; i++)
            {
                if(oselectall[i].checked == true)
                {
                    _id = oselectall[i].value;
                }
            }
            reData["orderNo"] = document.getElementById('vip_frame').contentWindow.document.getElementById('orderNo').value;
            reData['goodsId'] = document.getElementById('vip_frame').contentWindow.document.getElementById('goodsId').value;
            reData['userId']= document.getElementById('vip_frame').contentWindow.document.getElementById('userId').value;
            reData['source']= document.getElementById('vip_frame').contentWindow.document.getElementById('source').value;
            reData['vipExpireDate']=document.getElementById('vip_frame').contentWindow.document.getElementById('test-limit1').value;
            reData['level']=document.getElementById('vip_frame').contentWindow.document.getElementById('levelId').getAttribute("data-name");;
            reData['levelId']=document.getElementById('vip_frame').contentWindow.document.getElementById('levelId').value;
            if (reData['levelId'] == null || reData['levelId'] == "") {
                abc.layerAlert(false, "请选择会员等级！");
                return;
            }
            if("VIP0" != reData['levelId']){
                if (reData['vipExpireDate'] == null || reData['vipExpireDate'] == "") {
                    abc.layerAlert(false, "请选择会员到期日！");
                    return;
                }
            }
           /* if(_id == "VIP0"){
                reData['id']=_id;
                reData['levelId']="VIP0";
                reData['level']="普通会员";
            }
            else{
                reData['id']=_id;
            }*/
            layer.confirm("退单前请先确认相应的发票和发票申请记录是否已作废，发票作废请前往[发票寄送]模块处理，如发票已作废请忽略，确认是否申请退单？", {title:'操作提示',btn: ['确认','取消'], icon: 3,offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                function(){
                    abc.block();
                    $.ajax({
                        type: "POST",
                        url: ctx+"/financed/orderreturn.php",
                        data: JSON.stringify(reData),
                        async: true,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            abc.unblock();
                            if (data && data.code == "2000") {
                                layer.msg("退单成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    abc.block();
                                    window.location.reload();
                                });
                            } else {
                                abc.layerAlert(false, "退单失败"+":"+data.message);
                            }
                        }
                    });
                }
            );
        });
        $("#consumer_more").click(function(){
            if($(this).hasClass('shoqilai')){
                $(this).val('更多条件').removeClass('shoqilai');
                $(".ny_tab_t").find(".moretjYC").each(function(){
                    $(this).removeClass('moretjYC').addClass('moretj').fadeOut(500);
                });
            }else{
                $(this).val('隐藏条件').addClass('shoqilai');

                $(".ny_tab_t").find(".moretj").each(function(){
                    $(this).removeClass('moretj').addClass('moretjYC').fadeIn(500);
                });
            }
        });

        $("#closeSend").click(function(){
            var $parentTabNode = parent.$('#tt');
            $parentTabNode.tabs('close', "订单发货");
        });

        var hasYC=false;
        $(".ny_tab_t").find(".moretj").each(function(){
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


        //a删除
        $("a[data-type='delSig']").click(function(){
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
        });

        $("a[name='open_order']").off("click").on("click",function(){
            var url=$(this).attr("data-url");
            var $parentTabNode = parent.$('#tt');
            var exist_tab = $parentTabNode.tabs('getTab', "订单发货");
            if (exist_tab) {
                $parentTabNode.tabs('close', "订单发货");
            }
            parent.addPanel("订单发货", url, "1200");
        });
        function closeDiv()
        {
            if(document.getElementById('loading') != null){
                document.getElementById('loading').style.visibility='hidden';
            }
        }
        closeDiv();
    })
});