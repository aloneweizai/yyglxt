require(["../../config"], function () {
    require(["jquery.full","wangEditor", "tagEditor"], function ($,Editor) {
        if(document.getElementById("_topic_description_area")!=null){
            editor = new Editor("#_topic_description_area");
            editor.customConfig.uploadImgServer = ctx + '/util/wangEditorUpload.php';
            /*editor.customConfig.uploadImgTimeout = 60000;
             editor.customConfig.uploadImgMaxLength = 10;*/
            editor.create();
        }
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
                laydate.render({
                    elem: '#test31'
                    , theme: '#14b9d5'
                });
                form.on('radio(target)', function (data) {
                    if (data.value == "1") {
                        document.getElementById('bfyh').style.display = "none"
                        document.getElementById('tdyh').style.display = "none"
                    }
                    else if (data.value == "2") {
                        document.getElementById('bfyh').style.display = "block"
                        document.getElementById('tdyh').style.display = "none"
                    }
                    else if (data.value == "3") {
                        document.getElementById('bfyh').style.display = "none"
                        document.getElementById('tdyh').style.display = "block"
                    }
                });
                form.on('select(couponId)', function(data){
                    if(data.elem[data.elem.selectedIndex].getAttribute("data-type") == "PERIOD"){
                        $("#test5").val(data.elem[data.elem.selectedIndex].getAttribute("data-start"));
                        $("#start").val(data.elem[data.elem.selectedIndex].getAttribute("data-start"));
                        $("#test6").val(data.elem[data.elem.selectedIndex].getAttribute("data-end"));
                        $("#end").val(data.elem[data.elem.selectedIndex].getAttribute("data-end"));
                        $("#validType").val("PERIOD");
                    }
                    else if(data.elem[data.elem.selectedIndex].getAttribute("data-type") == "DAYS"){
                        $("#start").val("");
                        $("#end").val("");
                        $("#validType").val("DAYS");
                    }
                });
                form.on('radio(limit)', function (data) {
                    if (data.value == "true") {//限制
                        $("input[name='limitNum']").removeAttr("readonly");
                    }
                    else if (data.value == "false") {//不限制
                        $("input[name='limitNum']").attr("readonly",true);
                    }
                });
            })

            $('#tagnames').tagsInput({
                width: '390px',
                height: '45px',
                interactive: false,
                defaultText: '标签',
                onRemoveTag:function(tag){
                    num-=1;
                    $("#tagnum").text(num+"/10");
                    $('#'+tag).hide();
                }
            });
            $('#levels').tagsInput({
                width: '510px',
                height: '45px',
                interactive: false,
                defaultText: '用户类型',
                onRemoveTag:function(tag){
                    $('#'+tag).hide();
                }
            });

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

            $("#tagslect").click(function () {
                var tagNameStr = $("[name='tagName']").val();
                if(tagNameStr!=null &&tagNameStr !=""){
                    var tagNameArr = tagNameStr.split(",");
                    for(var k =0 ; k<tagNameArr.length;k++){
                        $('#'+tagNameArr[k]).show();
                    }
                    num = tagNameArr.length;
                }
                $("#myModal").show();
                $("#myModal").find(".modal-dialog").css('top', '-600px').animate({'top': abc.winscrollTop(50)}, 500);
            });

            $("#tagdelall").click(function () {
                $('#tagnames').clearALL();
            });
            var num = parseInt($('#tagnum').attr("data-num"));
            $("a[data-type='unselect']").click(function () {
                var _name = $(this).attr('data-id');
                if (!$('#tagnames').tagExist(_name)) {
                    num +=1;
                    $('#tagnames').addTag(_name);
                    $("#tagnum").text(num+"/10");
                    $('#'+_name).show();
                }
                else{
                    $('#tagnames').removeTag(_name);
                    $('#'+_name).hide();
                }
            });

            $("#levelslect").click(function () {
                var levelStr = $("[name='level']").val();
                if(levelStr!=null &&levelStr !=""){
                    var levelArr = levelStr.split(",");
                    for(var k =0 ; k<levelArr.length;k++){
                        $('#'+levelArr[k]).show();
                    }
                }
                $("#levelModal").show();
                $("#levelModal").find(".modal-dialog").css('top', '-600px').animate({'top': abc.winscrollTop(50)}, 500);
            });

            $("a[data-type='levelselect']").click(function () {
                var _name = $(this).attr('data-id');
                if (!$('#levels').tagExist(_name)) {
                    $('#levels').addTag(_name);
                    $('#'+_name).show();
                }
                else{
                    $('#levels').removeTag(_name);
                    $('#'+_name).hide();
                }
            });
            $("#seletablib").change(function () {
                var _v = $(this).val();
                if (_v == "") {
                    $(".itemWraper1").css('display', 'block');
                } else {
                    $(".itemWraper1").css('display', 'none');
                    $("#tag_" + _v).css('display', 'block');
                }
            });

            var userNames = [];
            $('#userName').tagsInput({
                width: '480px',
                height: '45px',
                interactive: false,
                defaultText: '标签',
                onRemoveTag:function(tag){
                    var userNames = $('#userNames').val();
                    var names = userNames.split(",");
                    names.splice(indexOf(tag,names),1);
                    $('#userNames').val(names.join(","));
                    $('#userName').importTags(names.join(","));
                    $("#usernum").text(names.length+"/1000")
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
            $("button[data-close]").click(function () {
                $("#levelModal").find(".modal-dialog").animate({'top': '-600px'}, 500, function () {
                    $("#levelModal").hide();
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

            $("a[data-type='nuse']").on('click', function () {
                var url = $(this).attr("data-url");
                layer.confirm("确定撤销?", {
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
                                    layer.msg("撤销成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
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

            $("a[data-type='reuse']").on('click', function () {
                var url = $(this).attr("data-url");
                layer.confirm("确定复用?", {
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
                                    layer.msg("复用成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
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
                        $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    });
                } else {
                    iframe.onload = function () {
                        //$("#myModal3").fadeIn();
                        $("#myModal3").show();
                        $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
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
                        $("#lookModal").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    });
                } else {
                    iframe.onload = function () {
                        //$("#myModal3").fadeIn();
                        $("#lookModal").show();
                        $("#lookModal").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    };
                }

            });
            function CompareDate(d1,d2)
            {
                return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
            }

            $("[id=editbtn]").click(function () {
                var status = $(this).attr("data-val");
                var _id = document.getElementById('consumer_frame').contentWindow.document.getElementById('activityId');
                var id;
                if (_id != null) {
                    id = document.getElementById('consumer_frame').contentWindow.document.getElementById('activityId').value;
                }
                var _stat = document.getElementById('consumer_frame').contentWindow.document.getElementById('status');
                var stat
                if (_stat != null) {
                    stat = document.getElementById('consumer_frame').contentWindow.document.getElementById('status').value;
                }
                var activityName = document.getElementById('consumer_frame').contentWindow.document.getElementById('activityName').value;
                if (activityName == null || activityName == "") {
                    abc.layerAlert(false, "优惠券活动名称不能为空！");
                    return;
                }
                if (activityName.length<2||activityName.length>30) {
                    abc.layerAlert(false, "优惠券活动名称长度需要在2和30之间！");
                    return;
                }
                var couponId = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponId').value;
                if (couponId == null || couponId == "") {
                    abc.layerAlert(false, "优惠券不能为空！");
                    return;
                }
                var startTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test5').value;
                var start = document.getElementById('consumer_frame').contentWindow.document.getElementById('start').value;
                var validType = document.getElementById('consumer_frame').contentWindow.document.getElementById('validType').value;
                if (startTime == null || startTime == "") {
                    abc.layerAlert(false, "活动有效期起不能为空！");
                    return;
                }
                if(start != null && start != ""&&validType=="PERIOD"){
                    if(CompareDate(startTime,start)){
                        abc.layerAlert(false, "优惠券的有效期开始时间，必须在活动时间之间");
                        return;
                    }
                }
                var endTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test6').value;
                var end = document.getElementById('consumer_frame').contentWindow.document.getElementById('end').value;
                if (endTime == null || endTime == "") {
                    abc.layerAlert(false, "活动有效期止不能为空！");
                    return;
                }
                if((endTime !=null && endTime!="")&&(startTime !=null && startTime!="")){
                    if(CompareDate(startTime,endTime)){
                        abc.layerAlert(false, "活动有效期结束时间不能小于活动有效期开始时间");
                        return;
                    }
                }
                if(end != null && end != ""&&validType=="PERIOD"){
                    if(CompareDate(endTime,end)){
                        abc.layerAlert(false, "优惠券活动有效期结束时间不能大于优惠券的有效期结束时间");
                        return;
                    }
                }
                var activityLink = document.getElementById('consumer_frame').contentWindow.document.getElementById('activityLink').value;
               if (activityLink != null && activityLink != "") {
                   if(!abc.check_url(activityLink)){
                       abc.layerAlert(false, "请输入正确的链接地址！");
                       return;
                   }
                }
                var target;
                var target1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('target1').checked;
                if (target1) {
                    target = document.getElementById('consumer_frame').contentWindow.document.getElementById('target1').value;
                }
                var target2 = document.getElementById('consumer_frame').contentWindow.document.getElementById('target2').checked;
                if (target2) {
                    target = document.getElementById('consumer_frame').contentWindow.document.getElementById('target2').value;
                }
                var target3 = document.getElementById('consumer_frame').contentWindow.document.getElementById('target3').checked;
                if (target3) {
                    target = document.getElementById('consumer_frame').contentWindow.document.getElementById('target3').value;
                }
                var couponNum = document.getElementById('consumer_frame').contentWindow.document.getElementById('couponNum').value;
                if (couponNum == null || couponNum == "") {
                    abc.layerAlert(false, "优惠券发放数量不能为空！");
                    return;
                }
                if(!abc.check_zzs(couponNum)){
                    abc.layerAlert(false, "请输入大于0的整数！");
                    return;
                }
                var limit1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('limit1').checked;
                var limit;
                var limitNum;
                if (limit1) {
                    limit = false;
                }
                else {
                    limit = true;
                    limitNum = document.getElementById('consumer_frame').contentWindow.document.getElementById('limitNum').value;
                    if (limitNum == null || limitNum == "") {
                        abc.layerAlert(false, "每人限领数量不能为空！");
                        return;
                    }
                    if(!abc.check_zzs(limitNum)){
                        abc.layerAlert(false, "请输入大于0的整数！");
                        return;
                    }
                    if(parseInt(limitNum)>parseInt(couponNum)){
                        abc.layerAlert(false, "每人限领张数不得超过优惠券发放数量！");
                        return;
                    }
                }
                var getType1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('getType1').checked;
                var getType;
                if (getType1) {
                    getType = document.getElementById('consumer_frame').contentWindow.document.getElementById('getType1').value;
                }
                else {
                    getType = document.getElementById('consumer_frame').contentWindow.document.getElementById('getType2').value;
                }
                var areaOper = document.getElementById('consumer_frame').contentWindow.document.getElementById('areaOper').value;
                var areaIds = document.getElementById('consumer_frame').contentWindow.document.getElementById('areaIds').value;
                if ((areaOper == null || areaOper == "") && (areaIds != null && areaIds != "")) {
                    abc.layerAlert(false, "已输入区域，请选择相应的区域操作选项！");
                    return;
                }
                if ((areaIds == null || areaIds == "") && (areaOper != null && areaOper != "")) {
                    abc.layerAlert(false, "已选择区域操作选项，请输入区域！");
                    return;
                }
                var tagOper = document.getElementById('consumer_frame').contentWindow.document.getElementById('tagOper').value;
                var tagIds = document.getElementById('consumer_frame').contentWindow.document.getElementById('tagnames').value;
                var tag = tagIds.split(",");
                if(tag.length>10){
                    abc.layerAlert(false, "最多选十个标签！");
                    return;
                }
                if ((tagOper == null || tagOper == "") && (tagIds != null && tagIds != "")) {
                    abc.layerAlert(false, "已输入标签，请选择相应的标签操作选项！");
                    return;
                }
                if ((tagIds == null || tagIds == "") && (tagOper != null && tagOper != "")) {
                    abc.layerAlert(false, "已选择标签操作选项，请输入标签！");
                    return;
                }
                var regTimeOper = document.getElementById('consumer_frame').contentWindow.document.getElementById('regTimeOper').value;
                var regStartTime;
                var regEndTime;
                if (regTimeOper == "gte") {
                    regStartTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test31').value;
                    if (regStartTime == null || regStartTime == "") {
                        abc.layerAlert(false, "注册时间不能为空！");
                        return;
                    }
                }
                else if (regTimeOper == "lte") {
                    regEndTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test31').value;
                    if (regEndTime == null || regEndTime == "") {
                        abc.layerAlert(false, "注册时间不能为空！");
                        return;
                    }
                }
                else {
                    var time = document.getElementById('consumer_frame').contentWindow.document.getElementById('test31').value;
                    if (time != null && time != "") {
                        abc.layerAlert(false, "已输入注册时间，请选择相应的注册时间操作选项！");
                        return;
                    }
                }
                var imageUrl = document.getElementById('consumer_frame').contentWindow.document.getElementById('imageUrl').value;
               if (imageUrl == null || imageUrl == "") {
                    abc.layerAlert(false, "活动图片不能为空！");
                    return;
                }
                var vips = document.getElementById('consumer_frame').contentWindow.document.getElementById('levels').value;
                var userIds = document.getElementById('consumer_frame').contentWindow.document.getElementById('userIds').value;
                var userNames = document.getElementById('consumer_frame').contentWindow.document.getElementById('userNames').value;
                var valid = document.getElementById('consumer_frame').contentWindow.document.getElementById('valid').checked;
                var validApi;
                if(valid){
                    validApi = document.getElementById('consumer_frame').contentWindow.document.getElementById('validApi').value;
                    if (validApi == null || validApi == "") {
                        abc.layerAlert(false, "领取按钮外部校验接口路径不能为空！");
                        return;
                    }
                }
                else{
                    valid = false;
                }
                var activity = {};
                activity["id"] = id;
                activity["status"] = stat;
                activity["activityName"] = activityName;
                activity['couponId'] = couponId;
                activity['activityLink'] = activityLink;
                activity["limit"] = limit;
                activity['couponNum'] = couponNum;
                activity['limitNum'] = limitNum;
                activity['getType'] = getType;
                activity['imageUrl'] = imageUrl;
                activity["target"] = target;
                activity['areaOper'] = areaOper;
                activity['areaIds'] = areaIds;
                activity["tagOper"] = tagOper;
                activity['tagName'] = tagIds;
                activity['regTimeOper'] = regTimeOper;
                activity["regStart"] = regStartTime;
                activity['regEnd'] = regEndTime;
                activity['levels'] = vips;
                activity['userName'] = userNames;
                /*activity['sendTime'] = sendTime;*/
                activity['startTime'] = startTime;
                activity['endTime'] = endTime;
                activity['validApi'] = validApi;
                activity['valid'] = valid;
                var description = document.getElementById('consumer_frame').contentWindow.editor.txt.html();
                if(description.length>1000){
                    abc.layerAlert(false, "活动规则描述长度需要在0和1000之间！");
                    return;
                }
                activity['description']=description;
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
                            url: ctx + "/financed/coupon/activity/update.php?status="+status,
                            data: JSON.stringify(activity),
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

            //用户名校验
            function chek_url(val){
                return ((/^(https?|ftp):\/\/[^\s]+$/i.test(val)));
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

            var menu_nodes = [];
            $(".menu_li").each(function () {
                var obj = {};
                if($(this).attr("data-menu-pid") == "430000" || $(this).attr("data-menu-id") == "430000"){
                    obj.open = true;
                }
                else{
                    obj.open = false;
                }
                obj.id = $(this).attr("data-menu-id");
                obj.pId = $(this).attr("data-menu-pid");
                obj.name = $(this).attr("data-menu-name");
                //obj.open = false;
                menu_nodes.push(obj);
            });
            var setting = {check: {enable: true,chkboxType : { "Y" : "ps", "N" : "ps" }}, data: {simpleData: {enable: true}}};
            $.fn.zTree.init($("#treeDemo"), setting, menu_nodes);
            var menu_tree = $.fn.zTree.getZTreeObj("treeDemo");
            var p;
            // menu_tree.setting.check.chkboxType = {"Y": "ps", "N": "ps"};
            var menuIdsStr = $("[name='areaIds']").val();
            if(menuIdsStr!=null &&menuIdsStr !=""){
                var menuIdsArr = menuIdsStr.split(",");
                var allNode = menu_tree.transformToArray(menu_tree.getNodes());
                var name = [];
                for (var k = 0; k < menuIdsArr.length; k++) {
                    if (menuIdsArr[k] && menuIdsArr[k] != 'null') {
                        for (var i = 0; i < allNode.length; i++) {
                            if (allNode[i].id == menuIdsArr[k] && !allNode[i].isParent) {
                                allNode[i].checked = true;
                                name.push(allNode[i].name);
                                menu_tree.updateNode(allNode[i]);
                                var parent = allNode[i].getParentNode();
                                var p1 = allNode[1].getParentNode();
                                p = p1.tId;
                                if (!parent.open) {
                                    menu_tree.expandNode(parent,true,true,true,true);
                                }
                            }
                        }
                        $("[name='areaName']").val(name.join(","));
                        $("[id='areaNames']").html(name.join(","));
                        $("[id='areanum']").text(name.length+"/20");
                    }
                }
            }else{
                $("[id='areanum']").text("0/20");
            }

            //点击上级菜单弹出树形结构层
            $("#areaslect").click(function () {
                var area = document.getElementById("areadw");
                if(p!=null&&p!=""){
                    area.setAttribute("href","#"+p);
                }
                else{
                    area.setAttribute("href","#treeDemo_203");
                }
                $(".js_pop_menu").show();
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
                        $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    });
                } else {
                    iframe.onload = function () {
                        //$("#myModal3").fadeIn();
                        $("#myModal3").show();
                        $("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(0)}, 500);
                        abc.unblock();
                    };
                }

            });

            $("button[data-diss]").click(function () {
                var username = $("#userNames").val();
                if(username!=null&&username!=""){
                    var names = username.split(",");
                    $("#usernum").text(names.length+"/1000")
                }
                else{
                    $("#usernum").text("0/1000")
                }
                $("#myModal3").find(".modal-dialog").animate({'top': '-700px'}, 500, function () {
                    $("#myModal3").hide();
                });
            });

            $(".js_save_area_btn").click(function (e) {
                $("[name='areaName']").val("");
                $("[name='areaIds']").val("");
                e.preventDefault();
                //设置check的menuId
                var checkedNodes = menu_tree.getCheckedNodes(true), v = [], name = [];
                for (var i = 0; i < checkedNodes.length; i++) {
                    if (checkedNodes[i].pId != null) {
                        v.push(checkedNodes[i].id);
                        name.push(checkedNodes[i].name);
                    }
                }
                if(checkedNodes.length > 0){
                    p = checkedNodes[1].getParentNode().tId;
                }
                if(v.length>20){
                    abc.layerAlert(false, "最多选二十个区域！");
                    return;
                }
                $("[name='areaIds']").val(v.join(","));
                $("[name='areaName']").val(name.join(","));
                $("#areanum").text(v.length+"/20");
                $(".main_modal").hide();
            });

            //点击取消 隐藏对话框
            $('.js_close').click(function (e) {
                $(".main_modal").hide();
            });


            $("#upload_btn").click(function(){
                updat($(this));
            });

            function updat(obj){
                var _o=$("#uploadFile");
                var _val=_o.val();
                if(_val==""){
                    abc.layerAlert(false,'请选择上传图片');
                    return;
                }
                var size=_o[0].files[0].size/1024;
                if(size>1024){
                    abc.layerAlert(false,'上传图片超过1MB!');
                    return;
                }
                var types=_o.attr('data-type').split(';');
                var type=_val.substring(_val.lastIndexOf(".")+1);
                obj.hide();
                $("#uploadMsg").html('正在上传.....')

                if(types.indexOf(type)<0){
                    abc.layerAlert(false,'允许类型:['+_o.attr('data-type')+"]");
                    obj.show();
                    $("#uploadMsg").html('');
                    return;
                }
                $.ajaxFileUpload({
                    url : ctx+'/util/doFileupload.php?path=vipgift',
                    type : 'post',
                    secureuri : false, // 一般设置为false
                    fileElementId : 'uploadFile', // 上传文件的id、name属性名
                    dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                    success : function(data, status) {
                        $("#upload_btn").show();
                        $("#uploadMsg").html('');
                        if(data.code=='200'){
                            $("#imgshow").attr('src',imgurl+data.message);
                            $("#imageUrl").val(data.message).blur();
                        }else{
                            abc.layerAlert(false,data.message);
                        }
                    },
                    error : function(data, status, e) {
                        alert(e);
                    }
                });
            }
        });
    });
});
