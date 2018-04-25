/**
 * Created by LiuQi
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","abc.common","layui","../abc/util/page"], function ($, date) {

        var init_layUi = function(){
            layui.use('form', function(){
                var form = layui.form;
                var laydate = layui.laydate;
                laydate.render({
                    elem: '#date',type: 'month'
                });
                form.render();
            });
        };

        $.ajax({
            type: 'GET',
            url: ctx+'/bangbang/rewardPointSetting/faction/list.php',
            data: '',
            async: false,
            success: function (data) {
                $(".js_body_div").html(data);
                
                init_layUi();
            }
        });

        /* 帮派积分分配查询 */
        $('body').on('click', '.js_query_factionSetting', function(){
            $.ajax({
                type: 'GET',
                url: ctx+'/bangbang/rewardPointSetting/faction/list.php?factionName='+$("#factionName").val()
                +'&status='+$("#status").val()+'&factionNumOfMonth='+$("#factionNumOfMonth").val()+'&date='+$("#date").val(),
                data: '',
                async: false,
                success: function (data) {
                    $(".js_body_div").html(data);
                    
                    init_layUi();
                }
            });
        });
        /* 帮派成员积分分配审核查询 */
        $('body').on('click', '.js_query_factionMember', function(){
            $.ajax({
                type: 'GET',
                url: ctx+'/bangbang/rewardPointSetting/factionMember/list.php?factionName='+$("#factionName").val()
                +'&status='+$("#status").val(),
                data: '',
                async: false,
                success: function (data) {
                    $(".js_body_div").html(data);
                    
                    init_layUi();
                }
            });
        });


        //全选checkbox
        $("body").on("click",".js_selectAll", function(e){
            if($(this).attr("data-check")=='true'){
                $(this).attr("data-check",false);
                $.each($(".js_checkbox"),function (){
                    this.checked=false;
                });
            }else{
                $(this).attr("data-check",true);
                $.each($(".js_checkbox"),function (){
                    this.checked="checked";
                });
            }
        });
        abc.ajaxPage =  function(url) {
            $.ajax({
                type: 'GET',
                url: url,
                data: '',
                async: false,
                success: function (data) {
                    $(".js_body_div").html(data);
                    
                    init_layUi();
                }
            });
        };
        abc.ajaxGoPage= function(){
                var page = $('#_goPs').val();
                var url = $('#_goPage').attr("data-url");
                if (page.match("^[1-9][0-9]*$")) {
                    $.ajax({
                        type: 'GET',
                        url: url.replace("[:page]", page),
                        async: false,
                        success: function (data) {
                            $(".js_body_div").html(data);
                            
                            init_layUi();
                        }
                    });
                } else {
                    return;
                }
        };

        $(".js_tab a").click(function(){
            if($(this).hasClass("active")){
                return;
            }else{
                $(this).addClass("active").siblings().removeClass("active");
                $.ajax({
                    type: 'GET',
                    url: $(this).attr("data-href"),
                    async: false,
                    success: function (data) {
                        $(".js_body_div").html(data);
                        
                        init_layUi();
                    }
                });
            }
        });


        /*分配*/
        $('body').on('click', '.js_setting_faction', function(){
            var factionId = $(this).attr("data-factionId");
            var date = $(this).attr("data-date");
            showContentLayer('帮派奖励分配', 400, 300, 200, $("#factionSetting_pop"));
            $("[name='awardPoint']").attr("data-factionId", factionId);
            $("[name='awardPoint']").attr("data-date", date);
        });
        //open弹窗公共方法
        var showContentLayer = function (title, width, height, top, content, event, closeBtn) {
            width = width || "600";
            height = height || "300";
            if (!event || !event.success || !event.end || !event.cancel) {
                event = event || {};
                event.success = event.success || function () {
                    };
                event.end = event.end || function () {
                    };
                event.cancel = event.cancel || function () {
                    };
            }
            if (!closeBtn) {
                closeBtn = 0;
            }
            var index = layer.open({
                type: 1,
                title: title,
                skin: 'layui-layer-molv',
                closeBtn: closeBtn,
                area: [width + 'px', height + "px"],
                offset: [top],
                shadeClose: false,
                content: content,
                success: function (elem, i) {
                    var $target = $(elem);
                    $target.find("button[name='close-layer-btn']").on("click", function () {
                        layer.close(i);
                    });
                    event.success();
                },
                end: event.end,
                cancel: event.cancel
            });
            return index;
        };


        $('body').on('click', '.js_reward_submit', function(){
            var _this = $(this);
            var factionId = $("[name='awardPoint']").attr("data-factionId");
            var date = $("[name='awardPoint']").attr("data-date");
            var rewardsPoints = $("[name='awardPoint']").val();
            var formObj = {factionId:factionId, date:date,rewardsPoints:rewardsPoints};
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:2147483647},
                function() {
                    $.ajax({
                        type: 'POST',
                        url: _this.attr("data-href"),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: JSON.stringify(formObj),
                        success: function (data) {
                            if(data && data.code=='2000') {
                                layer.closeAll();
                                $.ajax({
                                    type: 'GET',
                                    url: $(".js_currLink").val(),
                                    data: '',
                                    async: false,
                                    success: function (html) {
                                        $(".js_body_div").html(html);
                                        init_layUi();
                                    }
                                });
                            }else{
                                abc.layerAlert(false, "操作失败:"+data.message);
                            }
                        }
                    });
                });
        });
        /*成员分配审核通过*/
        $('body').on('click', '.js_audit_pass', function(){
            var json = JSON.stringify([{id:$(this).attr("data-id"), status:$(this).attr("data-status")}]);
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:2147483647},
                function() {
                    $.ajax({
                        type: 'POST',
                        url: ctx+"/bangbang/rewardPointSetting/factionMember/audit.php",
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: json,
                        success: function (data) {
                            if(data && data.code=='2000') {
                                layer.closeAll();
                                $.ajax({
                                    type: 'GET',
                                    url: $(".js_currLink").val(),
                                    data: '',
                                    async: false,
                                    success: function (html) {
                                        $(".js_body_div").html(html);
                                        init_layUi();
                                    }
                                });
                            }else{
                                abc.layerAlert(false, "操作失败:"+data.message);
                            }
                        }
                    });
                });
        });
        /*成员分配审核拒绝*/
        $('body').on('click', '.js_audit_refuse', function(){
            $(".js_memberRefuse_submit").attr("data-id", $(this).attr("data-id"));
            showContentLayer('拒绝理由', 400, 300, 200, $("#factionMemberRefuse_pop"));
        });
        /*成员分配审核拒绝*/
        $('body').on('click', '.js_memberRefuse_submit', function(){
            var json = JSON.stringify([{id:$(this).attr("data-id"), status:"3",refuseReason: $("[name='refuseReason']").val()}]);
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:2147483647},
                function() {
                    $.ajax({
                        type: 'POST',
                        url: ctx+"/bangbang/rewardPointSetting/factionMember/audit.php",
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: json,
                        success: function (data) {
                            if(data && data.code=='2000') {
                                layer.closeAll();
                                $.ajax({
                                    type: 'GET',
                                    url: $(".js_currLink").val(),
                                    data: '',
                                    async: false,
                                    success: function (html) {
                                        $(".js_body_div").html(html);
                                        init_layUi();
                                    }
                                });
                            }else{
                                abc.layerAlert(false, "操作失败:"+data.message);
                            }
                        }
                    });
                });
        });

        /*批量通过*/
        $('body').on('click', '.js_batch_pass', function(){
            var ids = abc.getCheckBoxIds();
            if(!ids){
                layer.msg("请勾选复选框", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000})
                return;
            }
            var json = [];
            var idsArr = ids.split(',');
            for(var i=0;i<idsArr.length;i++)
            {
                json.push({id:idsArr[i], status:"2"});
            }
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:2147483647},
                function() {
                    $.ajax({
                        type: 'POST',
                        url: ctx+"/bangbang/rewardPointSetting/factionMember/audit.php",
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: JSON.stringify(json),
                        success: function (data) {
                            if(data && data.code=='2000') {
                                layer.closeAll();
                                $.ajax({
                                    type: 'GET',
                                    url: $(".js_currLink").val(),
                                    data: '',
                                    async: false,
                                    success: function (html) {
                                        $(".js_body_div").html(html);
                                        init_layUi();
                                    }
                                });
                            }else{
                                abc.layerAlert(false, "操作失败:"+data.message);
                            }
                        }
                    });
                });
        })

        /*成员分配批量审核拒绝*/
        $('body').on('click', '.js_batch_refuse', function(){
            var ids = abc.getCheckBoxIds();
            if(!ids){
                layer.msg("请勾选复选框", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000})
                return;
            }
            showContentLayer('拒绝理由', 400, 300, 200, $("#factionMemberBatchRefuse_pop"));
        });
        /*成员分配批量审核拒绝*/
        $('body').on('click', '.js_batchMemberRefuse_submit', function(){
            var ids = abc.getCheckBoxIds();
            if(!ids){
                layer.msg("请勾选复选框", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:1000})
                return;
            }
            var json = [];
            var idsArr = ids.split(',');
            for(var i=0;i<idsArr.length;i++)
            {
                json.push({id:idsArr[i], status:"3", refuseReason: $("[name='batchRefuseReason']").val()});
            }
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:2147483647},
                function() {
                    $.ajax({
                        type: 'POST',
                        url: ctx+"/bangbang/rewardPointSetting/factionMember/audit.php",
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        data: JSON.stringify(json),
                        success: function (data) {
                            if(data && data.code=='2000') {
                                layer.closeAll();
                                $.ajax({
                                    type: 'GET',
                                    url: $(".js_currLink").val(),
                                    data: '',
                                    async: false,
                                    success: function (html) {
                                        $(".js_body_div").html(html);
                                        init_layUi();
                                    }
                                });
                            }else{
                                abc.layerAlert(false, "操作失败:"+data.message);
                            }
                        }
                    });
                });
        });




    })
});