require(["../../config"], function () {
    require(["jquery.full", "tagEditor"], function ($) {
        $(function () {
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                var form = layui.form;
                laydate.render({
                    elem: '#test31',
                    theme: '#14b9d5'
                });
                laydate.render({
                    elem: '#test4'
                    , type: 'datetime'
                    , theme: '#14b9d5'
                });
                laydate.render({
                    elem: '#test5'
                    , type: 'datetime'
                    , theme: '#14b9d5'
                });
                laydate.render({
                    elem: '#test6'
                    , type: 'datetime'
                    , theme: '#14b9d5'
                });
                form.on('radio(sendTime)', function (data) {
                    if (data.value == "1") {
                        document.getElementById('ljts').style.display = "block"
                        document.getElementById('dsts').style.display = "none"
                    }
                    else {
                       document.getElementById('ljts').style.display = "none"
                       document.getElementById('dsts').style.display = "block"
                    }
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
            })

            $('#tagnames').tagsInput({
                width: '330px',
                height: '45px',
                interactive: false,
                defaultText: '标签',
                onRemoveTag:function(tag){
                    num-=1;
                    $("#tagnum").text(num+"/10");
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
                width: '350px',
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
            $("#editbtn").click(function () {
                var _id = document.getElementById('consumer_frame').contentWindow.document.getElementById('taskId');
                var id;
                if (_id != null) {
                    id = document.getElementById('consumer_frame').contentWindow.document.getElementById('taskId').value;
                }
                var taskname = document.getElementById('consumer_frame').contentWindow.document.getElementById('taskname').value;
                if (taskname == null || taskname == "") {
                    abc.layerAlert(false, "任务描述不能为空！");
                    return;
                }
                var web = document.getElementById('consumer_frame').contentWindow.document.getElementById('web').checked;
                var wechat = document.getElementById('consumer_frame').contentWindow.document.getElementById('wechat').checked;
                var message = document.getElementById('consumer_frame').contentWindow.document.getElementById('message').checked;
                if (!web && !wechat && !message) {
                    abc.layerAlert(false, "请选择一种推送渠道！");
                    return;
                }
                var content = document.getElementById('consumer_frame').contentWindow.document.getElementById('content').value;
                if (content == null || content == "") {
                    abc.layerAlert(false, "消息内容不能为空！");
                    return;
                }
                //<a href='https://testuc.abc12366.com/ABCUC/member/checkIn.php'>马上签到。</a>
                var url = "";
                var urltitle = document.getElementById('consumer_frame').contentWindow.document.getElementById('urltitle').value;
                var urlhref = document.getElementById('consumer_frame').contentWindow.document.getElementById('urlhref').value;
                if (urlhref != null && urlhref != "") {
                    if(!chek_url(urlhref)){
                        abc.layerAlert(false, "网址格式不正确！");
                        return;
                    }
                    if((urltitle==null||urltitle == "")){
                        abc.layerAlert(false, "站内消息如有链接需录入链接标题！");
                        return;
                    }
                    url = "<a target='_blank' href='"+urlhref+"'>"+urltitle +"</a>";
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
                var rate;
                var rate1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('rate1').checked;
                if (rate1) {
                    rate = document.getElementById('consumer_frame').contentWindow.document.getElementById('rate1').value;
                }
                else {
                    rate = document.getElementById('consumer_frame').contentWindow.document.getElementById('rate2').value;
                }
                var sendTime;
                var startTime;
                var endTime;
                var sendTime1 = document.getElementById('consumer_frame').contentWindow.document.getElementById('sendTime1').checked;
                if (sendTime1) {
                    sendTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('sendTime1').value;
                    endTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test4').value;
                    if (endTime == null || endTime == "") {
                        abc.layerAlert(false, "推送截止时间不能为空！");
                        return;
                    }
                }
                else {
                    sendTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('sendTime2').value;
                    startTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test5').value;
                    endTime = document.getElementById('consumer_frame').contentWindow.document.getElementById('test6').value;
                    if (startTime == null || startTime == "") {
                        abc.layerAlert(false, "推送开始时间不能为空！");
                        return;
                    }
                    if (endTime == null || endTime == "") {
                        abc.layerAlert(false, "推送截止时间不能为空！");
                        return;
                    }
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
                var userIds = document.getElementById('consumer_frame').contentWindow.document.getElementById('userIds').value;
                var userNames = document.getElementById('consumer_frame').contentWindow.document.getElementById('userNames').value;
                var msgsend = {};
                msgsend["id"] = id;
                msgsend["taskname"] = taskname;
                msgsend['web'] = web;
                msgsend['wechat'] = wechat;
                msgsend["message"] = message;
                msgsend['content'] = content;
                msgsend['url'] = url;
                msgsend['urlhref'] = urlhref;
                msgsend['urltitle'] = urltitle;
                msgsend["target"] = target;
                msgsend['areaOper'] = areaOper;
                msgsend['areaIds'] = areaIds;
                msgsend["tagOper"] = tagOper;
                msgsend['tagName'] = tagIds;
                msgsend['regTimeOper'] = regTimeOper;
                msgsend["regStartTime"] = regStartTime;
                msgsend['regEndTime'] = regEndTime;
                //msgsend['userIds'] = userIds;
                msgsend['userName'] = userNames;
                msgsend['rate'] = rate;
                msgsend['sendTime'] = sendTime;
                msgsend['startTime'] = startTime;
                msgsend['endTime'] = endTime;
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
                            url: ctx + "/consumerManager/operate/message/update.php",
                            data: JSON.stringify(msgsend),
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
            $("a[data-type='delSig']").click(function () {
                var msg = $(this).attr("data-confirm");
                var url = $(this).attr("data-href");
                layer.confirm(msg, {
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
                                    layer.msg("删除数据成功!", {
                                        offset: abc.winscrollTop(200),
                                        shade: 0.3,
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function () {
                                        location.reload();
                                    }, 2000);
                                } else {
                                    bc.layerAlert(false, "删除数据失败:" + data.message);
                                }
                            },
                            dataType: "JSON"
                        });
                    }
                );
            });

            var menu_nodes = [];
            $(".menu_li").each(function () {
                var obj = {};
                obj.id = $(this).attr("data-menu-id");
                obj.pId = $(this).attr("data-menu-pid");
                obj.name = $(this).attr("data-menu-name");
                obj.open = false;
                menu_nodes.push(obj);
            });
            var setting = {check: {enable: true,chkboxType : { "Y" : "ps", "N" : "ps" }}, data: {simpleData: {enable: true}}};
            $.fn.zTree.init($("#treeDemo"), setting, menu_nodes);
            var menu_tree = $.fn.zTree.getZTreeObj("treeDemo");
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
                                if (!parent.open) {
                                    menu_tree.expandNode(parent, true, true);
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
        });
    });
});
