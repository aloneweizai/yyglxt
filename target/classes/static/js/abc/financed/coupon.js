require(["../../config"], function () {
    require(["jquery.full", "tagEditor"], function ($) {
        $(function () {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                var form = layui.form;
                laydate.render({
                    elem: '#test5'
                    , theme: '#14b9d5'
                });
                laydate.render({
                    elem: '#test6'
                    , theme: '#14b9d5'
                });
                form.on('radio(datetype)', function (data) {
                    if (data.value == "PERIOD") {
                        document.getElementById('gdsd').style.display = "block";
                        document.getElementById('gdts').style.display = "none";
                    }
                    else {
                       document.getElementById('gdsd').style.display = "none";
                       document.getElementById('gdts').style.display = "block";
                    }
                });
                form.on('select(yhmodal)', function(data){
                    if(data.value=="FIXED"){
                        document.getElementById('mjfdje').style.display = "none";
                        document.getElementById('ljfdje').style.display = "none";
                        document.getElementById("couponType").options.add(new Option("折扣","ZHEKOU"));
                        form.render("select");
                    }
                    else if(data.value=="FLOAT"){
                        document.getElementById("couponType").options.remove(2);
                        document.getElementById('mjfdje').style.display = "inline-block";
                        document.getElementById('ljfdje').style.display = "inline-block";
                        var couponType = document.getElementById('couponType').value;
                        if(couponType=="MANJIAN"){
                            document.getElementById('mj').style.display = "block";
                            document.getElementById('lj').style.display = "none";
                            document.getElementById('zk').style.display = "none";
                            document.getElementById('mjtitle').style.display = "block";
                            document.getElementById('ljtitle').style.display = "none";
                            document.getElementById('zktitle').style.display = "none";
                        }
                        else if(couponType=="LIJIAN"){
                            document.getElementById('mj').style.display = "none";
                            document.getElementById('lj').style.display = "block";
                            document.getElementById('zk').style.display = "none";
                            document.getElementById('mjtitle').style.display = "none";
                            document.getElementById('ljtitle').style.display = "block";
                            document.getElementById('zktitle').style.display = "none";
                        }
                        form.render("select");
                    }
                });
                form.on('select(yhstyle)', function(data){
                    if(data.value=="MANJIAN"){
                        document.getElementById('mj').style.display = "block";
                        document.getElementById('lj').style.display = "none";
                        document.getElementById('zk').style.display = "none";
                        document.getElementById('mjtitle').style.display = "block";
                        document.getElementById('ljtitle').style.display = "none";
                        document.getElementById('zktitle').style.display = "none";
                    }
                    else if(data.value=="LIJIAN"){
                        document.getElementById('mj').style.display = "none";
                        document.getElementById('lj').style.display = "block";
                        document.getElementById('zk').style.display = "none";
                        document.getElementById('mjtitle').style.display = "none";
                        document.getElementById('ljtitle').style.display = "block";
                        document.getElementById('zktitle').style.display = "none";
                    }
                    else if(data.value=="ZHEKOU"){
                        document.getElementById('mj').style.display = "none";
                        document.getElementById('lj').style.display = "none";
                        document.getElementById('zk').style.display = "block";
                        document.getElementById('mjtitle').style.display = "none";
                        document.getElementById('ljtitle').style.display = "none";
                        document.getElementById('zktitle').style.display = "block";
                    }
                });
                form.render("select");
            })


            $("#content").keyup(function(){
                var num =  $("#content").val().length;
                if(num>=200){
                    var char = $(this).val();
                    char = char.substr(0, 200);
                    $(this).val(char);
                    $("#num").text("200/200");
                }
                else{
                    $("#num").text(num+"/200");
                }
                });


            function indexOf (val,attr) {
                for(var i = 0; i < attr.length; i++){
                    if(attr[i] == val){return i;}
                }
                return -1;
            }
            $("button[data-dismiss]").click(function () {
                $("#myModal").find(".modal-dialog").animate({'top': '-600px'}, 500, function () {
                    $("#myModal").hide();
                })
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
                abc.block();
                $('#consumerListForm').submit();
            }

            $("a[data-type='detele']").on('click', function () {
                var url = $(this).attr("data-url");
                layer.confirm("确定删除?", {
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
                            url: url,
                            success: function (data) {
                                if (data.result.code == '2000') {
                                    layer.msg("删除成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                    setTimeout(function () {
                                        location.reload();
                                    }, 2000);
                                } else {
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            },
                            dataType: "JSON"
                        });
                    }
                );
            });

            $("a[data-type='lookdialog']").click(function () {
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

            $("a[data-type='lookdialog2']").click(function () {
                abc.block();
                var url = $(this).attr("data-url");
                var iframe = document.getElementById("look_frame");
                iframe.src = url;
                if (iframe.attachEvent) {
                    iframe.attachEvent("onload", function () {
                        //$("#myModal3").fadeIn();
                        $("#lookModal").show();
                        $("#lookModal").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
                        abc.unblock();
                    });
                } else {
                    iframe.onload = function () {
                        //$("#myModal3").fadeIn();
                        $("#lookModal").show();
                        $("#lookModal").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
                        abc.unblock();
                    };
                }

            });
            $("[id=editbtn]").click(function () {
                var status = $(this).attr("data-val");
                var _id = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponId');
                var id;
                if (_id != null) {
                    id = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponId').value;
                }
                var _stat = document.getElementById('consumer_frame').contentWindow.document.getElementById('status');
                var stat
                if (_stat != null) {
                    stat = document.getElementById('consumer_frame').contentWindow.document.getElementById('status').value;
                }
                var couponName = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponName').value;
                if (couponName == null || couponName == "") {
                    abc.layerAlert(false, "优惠券名称不能为空！");
                    return;
                }
                if (couponName.length<2||couponName.length>20) {
                    abc.layerAlert(false, "优惠券名称长度需要在2和20之间！");
                    return;
                }
                var couponMode = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponMode').value;
                var categoryIds = document.getElementById('consumer_frame').contentWindow.document.getElementById('parentId').value;
                if (categoryIds == null || categoryIds == "") {
                    abc.layerAlert(false, "优惠适用品类不能为空！");
                    return;
                }
                var couponType = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponType').value;
                var param1;
                var param2;
                var param3;
                if(couponType == "MANJIAN"){
                    param1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('mjparam1').value;
                    if (param1 == null || param1 == "") {
                        abc.layerAlert(false, "满多少金额不能为空！");
                        return;
                    }
                    if(!abc.check_zzs(param1)){
                        abc.layerAlert(false, "满多少金额参数不合法，请输入大于0的任意整数！");
                        return;
                    }
                    param2 = document.getElementById('consumer_frame').contentWindow.document.getElementById('mjparam2').value;
                    if (param2 == null || param2 == "") {
                        abc.layerAlert(false, "减多少金额不能为空！");
                        return;
                    }
                    if(param2=="0"||!abc.checkje(param2)){
                        abc.layerAlert(false, "区间金额请输入大于0且最多两位小数的任意数！");
                        return;
                    }
                    param3 = document.getElementById('consumer_frame').contentWindow.document.getElementById('mjparam3').value;
                    if(couponMode == "FLOAT"&&(param3 == null || param3 == "")){
                        abc.layerAlert(false, "区间金额不能为空！");
                        return;
                    }
                    if(couponMode == "FLOAT"&&(param3=="0"||!abc.checkje(param3))){
                        abc.layerAlert(false, "区间金额请输入大于0且最多两位小数的任意数！");
                        return;
                    }
                    if(couponMode == "FLOAT"&&parseFloat(param3)>=parseFloat(param1)){
                        abc.layerAlert(false, "满金额必须大于区间金额！");
                        return;
                    }
                    if(parseFloat(param2)>=parseFloat(param1)){
                        abc.layerAlert(false, "满金额必须大于区间金额！");
                        return;
                    }
                }
                else if(couponType == "LIJIAN"){
                    param2 = document.getElementById('consumer_frame').contentWindow.document.getElementById('ljparam2').value;
                    if (param2 == null || param2 == "") {
                        abc.layerAlert(false, "减多少金额不能为空！");
                        return;
                    }
                    if(param2=="0"||!abc.checkje(param2)){
                        abc.layerAlert(false, "减多少金额请输入大于0且最多两位小数的任意数！");
                        return;
                    }
                    param3 = document.getElementById('consumer_frame').contentWindow.document.getElementById('ljparam3').value;
                    if(couponMode == "FLOAT"&&(param3 == null || param3 == "")){
                        abc.layerAlert(false, "区间金额不能为空！");
                        return;
                    }
                    if(couponMode == "FLOAT"&&(param3=="0"||!abc.checkje(param3))){
                        abc.layerAlert(false, "区间金额请输入大于0且最多两位小数的任意数！");
                        return;
                    }
                }
                else if(couponType == "ZHEKOU"){
                    param1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('zkparam1').value;
                    if(!abc.check_ffzs(param1)){
                        abc.layerAlert(false, "满多少金额参数不合法，请输入大于等于0的任意整数！");
                        return;
                    }
                    param2 = document.getElementById('consumer_frame').contentWindow.document.getElementById('zkparam2').value;
                    if (param2 == null || param2 == "") {
                        abc.layerAlert(false, "折扣设置不能为空！");
                        return;
                    }
                    if(!abc.checkzerotoOne(param2)){
                        abc.layerAlert(false, "优惠劵折扣参数不合法，请输入0~1之间且最多两位小数的数字！");
                        return;
                    }
                }
                if(couponMode == "FLOAT"&&parseFloat(param2)>parseFloat(param3)){
                    abc.layerAlert(false, "区间金额起不能大于区间金额止！");
                    return;
                }
                var amountType = document.getElementById('consumer_frame').contentWindow.document.getElementById('amountType').value;
                var validType;
                var validStartTime;
                var validEndTime;
                var validDays;
                var validType1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('validType1').checked;
                if (validType1) {
                    validType = document.getElementById('consumer_frame').contentWindow.document.getElementById('validType1').value;
                    validStartTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test5').value;
                    if (validStartTime == null || validStartTime == "") {
                        abc.layerAlert(false, "有效期起不能为空！");
                        return;
                    }
                    validEndTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test6').value;
                    if (validEndTime == null || validEndTime == "") {
                        abc.layerAlert(false, "有效期止不能为空！");
                        return;
                    }
                    if((validStartTime !=null && validStartTime!="")&&(validEndTime !=null && validEndTime!="")){
                        if(CompareDate(validStartTime,validEndTime)){
                            abc.layerAlert(false, "有效期止不能小于有效期起");
                            return;
                        }
                    }
                }
                else {
                    validType = document.getElementById('consumer_frame').contentWindow.document.getElementById('validType2').value;
                    validDays = document.getElementById('consumer_frame').contentWindow.document.getElementById('validDays').value;
                    if (validDays == null || validDays == "") {
                        abc.layerAlert(false, "有效期固定天数不能为空！");
                        return;
                    }
                    if(!abc.check_zzs(validDays)){
                        abc.layerAlert(false, "请输入大于0的整数！");
                        return;
                    }
                }
                var description = document.getElementById('consumer_frame').contentWindow.document.getElementById('description').value;
                var coupon = {};
                coupon["id"] = id;
                coupon["couponName"] = couponName;
                coupon['couponMode'] = couponMode;
                coupon['categoryIds'] = categoryIds;
                coupon["couponType"] = couponType;
                coupon['param1'] = param1;
                coupon['param2'] = param2;
                coupon['param3'] = param3;
                coupon['amountType'] = amountType;
                coupon['startTime'] = validStartTime;
                coupon["endTime"] = validEndTime;
                coupon['validType'] = validType;
                coupon['validDays'] = validDays;
                coupon["description"] = description;
                coupon["status"] = stat;
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
                            url: ctx + "/financed/coupon/update.php?status="+status,
                            data: JSON.stringify(coupon),
                            async: true,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                abc.unblock();
                                if (data && data.code == "2000") {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                    location.reload();
                                } else {
                                    layer.alert(data.message || "操作失败", {icon: 5});
                                }
                            }
                        });
                    }
                );
            });
            function CompareDate(d1,d2)
            {
                return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
            }

            $("button[data-dismi]").click(function () {
                $("#myModal3").find(".modal-dialog").animate({'top': '-700px'}, 500, function () {
                    $("#myModal3").hide();
                });
            });
            $("button[data-dis]").click(function () {
                $("#lookModal").find(".modal-dialog").animate({'top': '-700px'}, 500, function () {
                    $("#lookModal").hide();
                });
            });

            //a删除
            $("a[data-type='delSig']").click(function(){
                //abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val());
                abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
            });

            $("#userslect").click(function () {
                abc.block();
                var userName = $("[name='userName']").val();
                var url = ctx + "/consumerManager/operate/message/consumerlook.php?userIds="+userName;
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

            $("button[data-diss]").click(function () {
                var username = $("#userNames").val();
                var names = username.split(",");
                $("#usernum").text(names.length+"/1000")
                $("#myModal3").find(".modal-dialog").animate({'top': '-700px'}, 500, function () {
                    $("#myModal3").hide();
                });
            });


            var setting = {
                check: {enable: true,chkboxType : { "Y" : "ps", "N" : "ps" }},
                data: {simpleData: {enable: true}}};
            var zNodes = [];
            var parentId=$("#parentId").val();
            var cate;
            var catename = [];
            $.ajax({
                type: "GET",
                url: ctx+"/financed/jsonDictBOs.php?dictId=goods_trading_channels",
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    $.each(data, function (i, item) {
                            var obj = new Object();
                            obj.id = item.fieldValue;
                            obj.pId = null
                            obj.name = item.fieldKey;
                            obj.open = true;
                            obj.myAttr = item.fieldValue;
                        if(parentId!=null &&parentId !="") {
                            cate= parentId.split(",");
                            if (cate.length > 0) {
                                for (var k = 0; k < cate.length; k++) {
                                        if(cate[k]==item.fieldValue){
                                            catename.push(item.fieldKey);
                                            obj.checked = true;
                                        }
                                }
                            }
                        }
                        zNodes.push(obj);
                    });
                    $("#parentName").val(catename.join(","));
                    $("#names").html(catename.join(","));
                    //doData(data);
                }
            });

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $("#parentName").click(function(){
                $(".js_pop_ztree").show();
                $(".js_pop_ztree").find(".col-sm-3").css('top','-700px').animate({'top':abc.winscrollTop(-100)},500);
            });
            $('body').on('click', '.js_close', function(){
                $(".js_pop_ztree").find(".col-sm-3").animate({'top':'-700px'},500,function(){
                    $(".js_pop_ztree").hide();
                })
            });
            var menu_tree = $.fn.zTree.getZTreeObj("treeDemo");
            $(".js_save_area_btn").click(function (e) {
                $("#parentId").val("");
                $("#parentName").val("");
                e.preventDefault();
                //设置check的menuId
                var checkedNodes = menu_tree.getCheckedNodes(true), v = [], name = [];
                for (var i = 0; i < checkedNodes.length; i++) {
                    if(checkedNodes.length>1&&checkedNodes[i].id == "ALL"){
                        abc.layerAlert(false,"全品类包含其他所有商品购买渠道!");
                        return;
                    }
                    //if (checkedNodes[i].pId != null) {
                        v.push(checkedNodes[i].id);
                        name.push(checkedNodes[i].name);
                    //}
                }
                $("#parentId").val(v.join(","));
                $("#parentName").val(name.join(","));
                $(".js_pop_ztree").find(".col-sm-3").animate({'top':'-700px'},500,function(){
                    $(".js_pop_ztree").hide();
                })
            });
        });
    });
});
