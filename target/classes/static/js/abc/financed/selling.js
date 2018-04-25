require(["../../config"], function () {
    require(["jquery.full","select2.full"], function ($) {


        layui.use(['laydate'], function(){
            var laydate = layui.laydate; //获得laydate模块
            laydate.render({
                elem: '#test30',
                theme: '#14b9d5'
            });
            laydate.render({
                elem: '#test31',
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

        $("a[data-type='submitdialog']").click(function () {
            abc.block();
            var url = $(this).attr("data-url");
            var iframe = document.getElementById("consumer_frame");
            iframe.src = url;
            if (iframe.attachEvent) {
                iframe.attachEvent("onload", function () {
                    //$("#myModal3").fadeIn();
                    $("#myModal3").show();
                    $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function () {
                    //$("#myModal3").fadeIn();
                    $("#myModal3").show();
                    $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
                    abc.unblock();
                };
            }

        });


        $("[data-dismiss='modal']").click(function(){
            $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#myModal3").hide();
            });
        });

        $("[data-dismiss='myModal']").click(function(){
            location.reload();
            $("#myModal").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#myModal").hide();
            });
        });

        $("#query").click(function(){
            var phone = $("#phone").val();
            var username = $("#username").val();
            if((phone == null || phone == "")&&(username == null || username == "")){
                abc.layerAlert(false,'请输入用户名或者手机号码!');
                return;
            }
            abc.block();
            $.ajax({
                type: "GET",
                url: ctx+"/financed/queryClient.php?username="+username+"&phone="+phone,
                async: true,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    abc.unblock();
                    $('#cusername').html(data.username);
                    $('#userId').val(data.id);
                    $('#vipLevel').val(data.vipLevel);
                    $('#cphone').html(data.phone);
                    $('#nickname').html(data.nickname);
                    $('#realName').html(data.realName);
                }
            });
        });

        //规格动态
        $("#prodcutslect").change(function(){
            var vipLevel = $('#vipLevel').val();
            if(vipLevel == null || vipLevel == ""){
                //$(this).prop("selected",false);
                $(this).val("HYCZ");
                abc.layerAlert(false,'请输入需要下单的客户!');
                return;
            }
            var _val=$(this).val();
            var this_=$("#smallGoodrule");
            this_.empty().append("<option value=''>选择规格数据</option>");
            if("HYCZ" == _val){
                $.ajax({
                    type: "GET",
                    url: ctx+"/financed/queryVip.php",
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            $.each(data, function (i, item) {
                                if(item.levelCode != "VIP0"){
                                    this_.append("<option value='"+item.levelCode +"'>" +item.level  + "</option>");
                                }
                            });
                        }
                    }
                });
            }
            else if("JFCZ" == _val){
                $.ajax({
                    type: "GET",
                    url: ctx+"/util/jsonDictBOs.php?dictId=goodnote",
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            $.each(data, function (i, item) {
                                if(item.fieldValue != "0") {
                                    this_.append("<option value='" + item.fieldValue + "'>" + item.fieldKey + "元</option>");
                                }
                            });
                        }
                    }
                });
            }
            else if("CSKT" == _val){
                $.ajax({
                    type: "GET",
                    url: ctx+"/financed/queryCourse.php?vipLevel="+vipLevel,
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            $.each(data, function (i, item) {
                                    this_.append("<option value='"+item.curriculumId +"'>" +item.title + "</option>");
                            });
                            this_.select2();
                        }
                    }
                });
            }
        });
         var orcount = 0;
        var addcount = 0;
        $("#smallGoodrule").change(function(){
            var _val=$("#prodcutslect").val();
            var _this=$(this).find("option:selected").text();
            var vipLevel = $('#vipLevel').val();
            if(vipLevel == null || vipLevel == ""){
                abc.layerAlert(false,'请输入需要下单的客户!');
                return;
            }
            var gift=$("#gift");
            gift.empty();
            if("HYCZ" == _val) {
                $.ajax({
                    type: "GET",
                    url: ctx + "/financed/queryVip.php?level=" + _this,
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            $.each(data, function (i, item) {
                                $('#goodsId').val(item.id);
                                $('#marketPrice').val(item.marketPrice);
                                $('#totalPrice').val(item.salePrice);
                                $('#goodname').val(item.level);
                                $('#specInfo').val(item.levelCode);
                                $('#price').html(item.salePrice+"&nbsp;元");
                                $('#giftPoints').val(item.sendPoints);
                                $('#imageUrl').val(item.imgUrl);
                                gift.append("<input type='checkbox' checked='checked' style='width:30px;height:30px' disabled/>"+item.sendPoints+"积分");
                            });
                        }
                    }
                });
            }
            else if("JFCZ" == _val){
                $('#goodsId').val("JF0000000001");
                $('#marketPrice').val($(this).val());
                $('#totalPrice').val($(this).val());
                $('#goodname').val("积分充值");
                $('#specInfo').val("积分充值");
             /*   $('#browseUrl').val(browurl);
                $('#imageUrl').val(browurl+"/images/jfdh.jpg");*/
                $('#price').html($(this).val()+"&nbsp;元");
            }
           else if("CSKT" == _val) {
                $.ajax({
                    type: "GET",
                    url: ctx + "/financed/queryCourseOne.php?id=" + $(this).val(),
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            $('#goodsId').val(data.curriculumId);
                            $('#marketPrice').val(data.marketPrice);
                            $('#totalPrice').val(data.sellPrice);
                            $('#goodname').val(data.title);
                            $('#specInfo').val(data.title);
                            $('#price').html(data.sellPrice+"&nbsp;元");
                            //$('#giftPoints').val(data.sendPoints);
                            $('#imageUrl').val(data.cover);
                            var uvipPriceBoList = data.uvipPriceBoList;
                            $.each(uvipPriceBoList, function (i, item) {
                                if (vipLevel == item.vipGrade) {
                                    var curriculumGiftBoList = item.curriculumGiftBoList;
                                    for (var j = 0; j < curriculumGiftBoList.length; j++) {
                                        var operType = curriculumGiftBoList[j].operType;
                                        if ("COUPON" == operType) {
                                            var _coupon = $("<input name='gift' type='checkbox' style='width:25px;height:25px' operValue='"+curriculumGiftBoList[j].operValue+"' operType='"+curriculumGiftBoList[j].operType+"' giftId='"+curriculumGiftBoList[j].giftId+"' operid='"+curriculumGiftBoList[j].id+"'operSymbol='"+curriculumGiftBoList[j].operSymbol+"'>赠送优惠券："+ getCoupon(curriculumGiftBoList[j].operValue)+"&nbsp;</input>").click(function(){
                                                var operSymbol = $(this).attr("operSymbol");
                                                var operid = $(this).attr("operid");
                                                var bools = $(this).is(":checked");
                                                if(bools){
                                                    if("or" == operSymbol){
                                                        orcount++;
                                                    }
                                                    else{
                                                        addcount++;
                                                    }
                                                }
                                                else{
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount>1){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount!=0&&addcount>0){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                            });
                                            gift.append(_coupon);
                                           // gift.append("<input type='checkbox' style='width:25px;height:25px' operid='"+curriculumGiftBoList[j].id+"'operSymbol='"+curriculumGiftBoList[j].operSymbol+"'/>赠送优惠券：" + curriculumGiftBoList[j].operValue);
                                        }
                                        else if ("AMOUNT" == operType) {
                                            var _amount = $("<input  name='gift' type='checkbox' style='width:25px;height:25px' operValue='"+curriculumGiftBoList[j].operValue+"' operType='"+curriculumGiftBoList[j].operType+"' giftId='"+curriculumGiftBoList[j].giftId+"' operid='"+curriculumGiftBoList[j].id+"' operSymbol='"+curriculumGiftBoList[j].operSymbol+"'>"+ curriculumGiftBoList[j].operValue+"元会员礼包&nbsp;</input>").click(function(){
                                                var operSymbol = $(this).attr("operSymbol");
                                                var operid = $(this).attr("operid");
                                                var bools = $(this).is(":checked");
                                                if(bools){
                                                    if("or" == operSymbol){
                                                        orcount++;
                                                    }
                                                    else{
                                                        addcount++;
                                                    }
                                                }
                                                else{
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount>1){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount!=0&&addcount>0){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                            });
                                            gift.append(_amount);
                                            //gift.append("<input type='checkbox' style='width:25px;height:25px' operid='"+curriculumGiftBoList[j].id+"' operSymbol='"+curriculumGiftBoList[j].operSymbol+"'/>" + curriculumGiftBoList[j].operValue+"元会员礼包");
                                        }
                                        else if ("POINTS" == operType) {
                                            var _point = $("<input  name='gift' type='checkbox' style='width:25px;height:25px' operValue='"+curriculumGiftBoList[j].operValue+"' operType='"+curriculumGiftBoList[j].operType+"' giftId='"+curriculumGiftBoList[j].giftId+"' operid='"+curriculumGiftBoList[j].id+"' operSymbol='"+curriculumGiftBoList[j].operSymbol+"'>"+ curriculumGiftBoList[j].operValue+"积分&nbsp;</input>").click(function(){
                                                var operSymbol = $(this).attr("operSymbol");
                                                var operid = $(this).attr("operid");
                                                var bools = $(this).is(":checked");
                                                if(bools){
                                                    if("or" == operSymbol){
                                                        orcount++;
                                                    }
                                                    else{
                                                        addcount++;
                                                    }
                                                }
                                                else{
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount>1){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount!=0&&addcount>0){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                            });
                                            gift.append(_point);
                                           // gift.append("<input type='checkbox' style='width:25px;height:25px' operid='"+curriculumGiftBoList[j].id+"' operSymbol='"+curriculumGiftBoList[j].operSymbol+"'/>" + curriculumGiftBoList[j].operValue + "积分");
                                        }
                                        else if ("VIP" == operType) {
                                            var _vip = $("<input  name='gift' type='checkbox' style='width:25px;height:25px' operValue='"+curriculumGiftBoList[j].operValue+"' operType='"+curriculumGiftBoList[j].operType+"' giftId='"+curriculumGiftBoList[j].giftId+"' operid='"+curriculumGiftBoList[j].id+"' operSymbol='"+curriculumGiftBoList[j].operSymbol+"'>"+ getVipLevel(curriculumGiftBoList[j].operValue)+"&nbsp;</input>").click(function(){
                                                var operSymbol = $(this).attr("operSymbol");
                                                var operid = $(this).attr("operid");
                                                var bools = $(this).is(":checked");
                                                if(bools){
                                                    if("or" == operSymbol){
                                                        orcount++;
                                                    }
                                                    else{
                                                        addcount++;
                                                    }
                                                }
                                                else{
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount>1){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                                if(orcount!=0&&addcount>0){
                                                    layer.alert("不能叠加选择", {icon: 5});
                                                    $(this).prop("checked",false);
                                                    if("or" == operSymbol){
                                                        orcount--;
                                                    }
                                                    else{
                                                        addcount--;
                                                    }
                                                }
                                            });
                                            gift.append(_vip);
                                            //gift.append("<input type='checkbox' style='width:25px;height:25px' operid='"+curriculumGiftBoList[j].id+"' operSymbol='"+curriculumGiftBoList[j].operSymbol+"'/>" + curriculumGiftBoList[j].operValue);
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

        function getCoupon(id){
            var name = "";
            $.ajax({
                type: "GET",
                url: ctx + "/financed/queryCoupon.php?id="+id,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (data) {
                        name = data.activityName;
                    }
                }
            });
            return name;
        }

        $("#editbtn").click(function () {
            var userId = document.getElementById('consumer_frame').contentWindow.document.getElementById('userId').value;
            if (userId == null || userId == "") {
                abc.layerAlert(false, "请选择需要下单的客户！");
                return;
            }
            var vipLevel = document.getElementById('consumer_frame').contentWindow.document.getElementById('vipLevel').value;
            var username = document.getElementById('consumer_frame').contentWindow.document.getElementById('cusername').innerHTML;
            var totalPrice = document.getElementById('consumer_frame').contentWindow.document.getElementById('totalPrice').value;
            var remark = document.getElementById('consumer_frame').contentWindow.document.getElementById('remark').value;
            var goodsId = document.getElementById('consumer_frame').contentWindow.document.getElementById('goodsId').value;
            var goodsPrice = document.getElementById('consumer_frame').contentWindow.document.getElementById('marketPrice').value;
            var name = document.getElementById('consumer_frame').contentWindow.document.getElementById('goodname').value;
            var specInfo = document.getElementById('consumer_frame').contentWindow.document.getElementById('specInfo').value;
            var giftPoints = document.getElementById('consumer_frame').contentWindow.document.getElementById('giftPoints').value;
            var imgUrl = document.getElementById('consumer_frame').contentWindow.document.getElementById('imageUrl').value;
            var browseUrl = document.getElementById('consumer_frame').contentWindow.document.getElementById('browseUrl').value;
            var recommendUser = document.getElementById('consumer_frame').contentWindow.document.getElementById('recommendUser').innerHTML;
            var prodcutslect = document.getElementById('consumer_frame').contentWindow.document.getElementById('prodcutslect').value;
            var order = {};
            var orderProductBOList = [];
            var prodcut = {};
            order["userId"]=userId;
            order["nowVipLevel"]=vipLevel;
            order["username"]=username;
            order["deliveryFee"]=0;
            order["totalPrice"]=totalPrice;
            order["remark"]=remark;
            order["giftPoints"]=giftPoints;
            order["tradeMethod"]="RMB";
            order["recommendUser"]=recommendUser;
            order["isShipping"]=2;
            order["isFreeShipping"]=2;
            //order["useCouponId"]=
            prodcut["goodsPrice"] =goodsPrice;
            prodcut["num"] = 1;
            prodcut["dealPrice"] =totalPrice;
            prodcut["name"] = name;
            prodcut["goodsId"] =goodsId;
            prodcut["specInfo"] =specInfo;
            if(prodcutslect == 'JFCZ'){
                prodcut["isReturn"] =1;
                prodcut["isExchange"] =1;
            }
            else if(prodcutslect == 'HYCZ'){
                prodcut["isReturn"] =0;
                prodcut["isExchange"] =1;
            }
            prodcut["goodsType"] =5;
            if(prodcutslect != 'JFCZ'){
                prodcut["imageUrl"] =imgUrl;
            }
          prodcut["browseUrl"] =browseUrl;
            prodcut["tradingChannels"] = prodcutslect;
            orderProductBOList.push(prodcut);
            order["orderProductBOList"] = orderProductBOList;
            if(prodcutslect == 'CSKT'){
                var orderGiftBOList = [];
                var oselectall = document.getElementById('consumer_frame').contentWindow.document.getElementsByName("gift");
                for (var i = 0; i < oselectall.length; i++)
                {
                    if(oselectall[i].checked == true)
                    {
                        var gfit = {};
                        gfit["id"] =oselectall[i].getAttribute("operid");
                        gfit["giftId"] =oselectall[i].getAttribute("giftId");
                        gfit["operValue"] =oselectall[i].getAttribute("operValue");
                        gfit["operType"] =oselectall[i].getAttribute("operType");
                        gfit["operSymbol"] =oselectall[i].getAttribute("operSymbol");
                        orderGiftBOList.push(gfit);
                    }
                }
                order["orderGiftBOList"] = orderGiftBOList;
            }
            layer.confirm('确认操作？', {
                    title: '操作提示',
                    btn: ['确认', '取消'],
                    icon: 3,
                    offset: abc.winscrollTop(200),
                    closeBtn: 0,
                    zIndex: 90000
                },
                function () {
                    abc.block();
                    $.ajax({
                        type: "POST",
                        ///ctx+"/cszjs/wxactivity/update.php";
                        url: ctx + "/financed/update.php",
                        data: JSON.stringify(order),
                        async: true,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            abc.unblock();
                            if (data && data.code == "2000") {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                $("#myModal3").hide();
                                var order = data.data;
                                openPayDialog(order.orderNo);
                            } else {
                                layer.alert(data.message || "操作失败", {icon: 5});
                            }
                        }
                    });
                }
            );
        });

        function getVipLevel(level){
           var name = "";
           $.ajax({
                type: "GET",
                url: ctx + "/financed/queryVip.php",
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (data) {
                        $.each(data, function (i, item) {
                            if(level == item.levelCode){
                                name = item.level;
                            }
                        });
                    }
                }
            });
            return name;
        }
        function openPayDialog(orderNo){
            abc.block();
            var url = ctx+'/financed/payOrder.php?id='+orderNo;
            var iframe = document.getElementById("pay_frame");
            iframe.src = url;
            if (iframe.attachEvent) {
                iframe.attachEvent("onload", function () {
                    //$("#myModal3").fadeIn();
                    $("#myModal").show();
                    $("#myModal").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function () {
                    //$("#myModal3").fadeIn();
                    $("#myModal").show();
                    $("#myModal").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
                    abc.unblock();
                };
            }

        }
        $("[id='payorder']").click(function () {
            var orderNo = $(this).attr("data-no");
            openPayDialog(orderNo);
        });

        $("#paysubmit").click(function () {
            var tradeNo = document.getElementById('pay_frame').contentWindow.document.getElementById('tradeNo').value;
            var subject = document.getElementById('pay_frame').contentWindow.document.getElementById('subject').value;
            var amount = document.getElementById('pay_frame').contentWindow.document.getElementById('amount').value;
            var productId = document.getElementById('pay_frame').contentWindow.document.getElementById('productId').value;
            var checked = document.getElementById('pay_frame').contentWindow.document.getElementById('ali').checked;
            var _val;
            if(checked){
                _val = document.getElementById('pay_frame').contentWindow.document.getElementById('ali').value;
            }
            else{
                _val = document.getElementById('pay_frame').contentWindow.document.getElementById('weixin').value;
            }
            if(_val ==""||_val == null){
                abc.layerAlert(false, "请选择支付方式！");
                return;
            }
            abc.block();
            if("ali"==_val){
                $.ajax({
                    type: "GET",
                    url: ctx+"/financed/alipay.php?out_trade_no="+tradeNo+"&subject="+subject+"&total_amount="+amount,
                    async: true,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        abc.unblock();
                        $('#zhifubaoewm').attr("src","data:image/jpg;base64,"+data.qccodeStr);
                        $("#uuu").show();
                    }
                });
            }
            else if("weixin" == _val){
                $.ajax({
                    type: "GET",
                    url: ctx+"/financed/weixin/qrcode.php?out_trade_no="+tradeNo+"&body="+subject+"&total_fee="+parseFloat(amount)*100+"&product_id="+productId,
                    async: true,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        abc.unblock();
                        $('#weixinewm').attr("src","data:image/jpg;base64,"+data.code_img);
                        $("#yyy").show();
                    }
                });
            }

        });
        $("#fanhuizhifu").click(function () {
            $("#uuu").hide();
        })
        $("#fanhuizhifuw").click(function () {
            $("#yyy").hide();
        })
        $("[name='wanchengzhifu']").click(function () {
            abc.block();
            var tradeNo = document.getElementById('pay_frame').contentWindow.document.getElementById('tradeNo').value;
            var orderNo = document.getElementById('pay_frame').contentWindow.document.getElementById('orderNo').value;
            var checked = document.getElementById('pay_frame').contentWindow.document.getElementById('ali').checked;
            var _val;
            if(checked){
                _val = document.getElementById('pay_frame').contentWindow.document.getElementById('ali').value;
            }
            else{
                _val = document.getElementById('pay_frame').contentWindow.document.getElementById('weixin').value;
            }
            if("ali"==_val) {
                $.ajax({
                    type: "GET",
                    url: ctx + "/tradelog/alipay/query.php?out_trade_no=" + tradeNo,
                    async: true,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data.code == '10000') {
                            var _result = '';
                            if ('TRADE_SUCCESS' == data.trade_status) {
                                _result = '支付成功';
                            } else if ('WAIT_BUYER_PAY' == data.trade_status) {
                                _result = '等待买家付款';
                            } else if ('TRADE_CLOSED' == data.trade_status) {
                                _result = '交易超时关闭或支付完成后全额退款';
                            } else {
                                _result = '交易结束';
                            }
                            abc.unblock();
                            $("#uuu").hide();
                            $("#myModal").hide();
                            layer.msg(_result, {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                            location.reload();
                        } else {
                            abc.unblock();
                            $("#uuu").hide();
                            $("#myModal").hide();
                            //layer.msg(data.message);
                            location.reload();
                        }
                    }
                });
            }
            else if("weixin" == _val){
                $.ajax({
                    type: "GET",
                    url: ctx + "/financed/weixin/tradelog.php?out_trade_no=" + tradeNo,
                    async: true,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data.code == '2000') {
                            abc.unblock();
                            $("#uuu").hide();
                            $("#myModal").hide();
                            layer.msg("支付成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                            location.reload();
                        } else {
                            abc.unblock();
                            $("#uuu").hide();
                            $("#myModal").hide();
                            //layer.msg(data.message);
                            location.reload();
                        }
                    }
                });
            }
        })
        function closeDiv()
        {
            if(document.getElementById('loading') != null){
                document.getElementById('loading').style.visibility='hidden';
            }
        }
        closeDiv();
    })
});