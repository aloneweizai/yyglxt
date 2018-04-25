/**
 * Created by Administrator on 2017-06-13.
 * 二维码
 */
require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
        layui.use('laydate', function () {
            var form = layui.form;
            form.on('radio(amountType)', function (data) {
                if (data.value == "1") {//固定金额
                    document.getElementById('minAmount').style.display = "none";
                }
                else {//随机金额
                    document.getElementById('minAmount').style.display = "inline-block";
                }
            });
            form.on('radio(giftCoupon)', function (data) {
                if (data.value  == 'true') {//是赠送优惠券
                    document.getElementById('couponActivity').style.display = "inline-block";
                }
                else if(data.value  == 'false') {//不赠送优惠券
                    document.getElementById('couponActivity').style.display = "none";
                }
            });

            form.on('radio(giftPoints)', function (data) {
                if (data.value  == 'true') {//是赠送积分
                    document.getElementById('points').style.display = "inline-block";
                }
                else if(data.value  == 'false') {//不赠送积分
                    document.getElementById('points').style.display = "none";
                }
            });
        })
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

        $("#couponActivitySlect").click(function () {
            abc.block();
            var activityid = $('#activityId').val();
            var url = ctx + "/cszjs/wxactivity/couponActivity/list.php?activityid="+activityid;
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
            var oselectall = document.getElementById('consumer_frame').contentWindow.document.getElementsByName("ids");
            for (var i = 0; i < oselectall.length; i++)
            {
                if(oselectall[i].checked == true)
                {
                    $('#activityName').val(oselectall[i].getAttribute("data-name"));
                    $('#activityId').val(oselectall[i].value);
                }

            }
            $("#myModal3").find(".modal-dialog").animate({'top': '-700px'}, 500, function () {
                $("#myModal3").hide();
            });
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


        /* $('#consumer_query').on("click", function () {
         abc.block();
         $('#consumerListForm').submit();
         });*/
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

        $("#submit").click(function () {

            var url = "rqcode_save.php";
            var data1 = JSON.parse($("form").serializeJson());
            if(data1.name.length<1){
                layer.msg("错误，名称不能为空", {icon: 5});
                return false;
            }
            //data1.type = "0";
            data1=JSON.stringify(data1);

            $.ajax({
                type: "POST",
                url: url,
                data: data1,
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

        });

        var $validatorWsysVoFrom = $("form").validator({
            timely: 1,
            focusCleanup:true,
            theme: 'yellow_right',
            rules: {
                allint:[/^(-|\+)?\d+$/, '请输入整数']
            }
        });
        $validatorWsysVoFrom.validator().trigger("showtip");

        $("#consumer_submit").click(function(){
            if($validatorWsysVoFrom.isValid()){
                var checkType = $("input:radio[name='ruleType']:checked").val();
                var giftCoupon = $("input:radio[name='giftCoupon']:checked").val();
                var giftPoints = $("input:radio[name='giftPoints']:checked").val();
                var rule = $("input[name='rule']").val();
                var probability = $("input[name='probability']").val();
                var amount = $("input[name='amount']").val();
                var activityId = $("input[name='activityId']").val();
                var points = $("input[name='points']").val();
                if(parseFloat(amount)< parseFloat(1)){
                    abc.layerAlert(false,'最小为1元!');
                    return ;
                }
                if(!abc.checkje14(amount)){
                    abc.layerAlert(false, "金额应为14位数字两位小数！");
                    return;
                }
                var minAmount = $("input[name='minAmount']").val();
                var amountType = $("input:radio[name='amountType']:checked").val();
                if(amountType == '2'){
                    if(minAmount==null||minAmount == ""){
                        abc.layerAlert(false, '最小金额不能为空!');
                        return;
                    }
                    if(parseFloat(minAmount)>=parseFloat(amount)){
                        abc.layerAlert(false, '最小金额不能大于等于最大金额!');
                        return;
                    }
                    if(parseFloat(minAmount)< parseFloat(1)){
                        abc.layerAlert(false,'最小为1元!');
                        return ;
                    }
                    if(!abc.checkje14(minAmount)){
                        abc.layerAlert(false, "金额应为14位数字两位小数！");
                        return;
                    }
                }
                if(checkType=='2'){
                    if(!rule) {
                        abc.layerAlert(false, '规则定义不能为空!');
                        return;
                    }
                    if(!chek(rule)){
                        abc.layerAlert(false,'请先设置正确的规则定义!');
                        return ;
                    }
                }
                else{
                    if(rule){
                        if(!chek(rule)){
                            abc.layerAlert(false,'请先设置正确的规则定义!');
                            return ;
                        }
                    }
                }
                if(giftCoupon=='true'){
                    if(activityId==null||activityId == ""){
                        abc.layerAlert(false, '请选择赠送优惠券对应的优惠券活动!');
                        return;
                    }
                }
                if(giftPoints=='true'){
                    if(points==null||points == ""){
                        abc.layerAlert(false, '请填写赠送多少积分!');
                        return;
                    }
                }
                if(probability){
                    if(!((/^([1-9]\d?|100)[%]{1}$/.test(probability)))){
                        abc.layerAlert(false,'中奖概率为1%到100%的百分比!');
                        return ;
                    }
                }
                if($("#startTime").val() >= $("#endTime").val()){
                    abc.layerAlert(false, '操作失败: 活动截止时间需大于开始时间');
                    return false;
                }
                var sort = $("input[name='sort']").val();
                if(sort==null||sort == ""){
                    abc.layerAlert(false, '排序不能为空!');
                    return;
                }
                abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), document.referrer);
            }
        });

        function chek(val){
            var checkType = $("input:radio[name='ruleType']:checked").val();
            if(checkType=='1'){
                return ((/^[A-Za-z0-9]+$/.test(val)));
            }
            else{
                return ((/^([0-9a-zA-Z\u4e00-\u9fa5]+[#]?)+$/.test(val)));
            }
        }

        $("input:radio[name='ruleType']").click(function(){
            if($(this).val()=='2'){
                $("input[name='times']").val(1);
                $("input[name='times']").attr("readonly",true);
            }else{
                $("input[name='times']").removeAttr("readonly");
            }
        })

        $("#consumer_import").click(function(){
            $("#myModal").show();
            $("#uploadFile").val("");
        });

        $("[id='importbtn']").click(function(){
            if($("#uploadFile").val()==''){
                abc.layerAlert(false,"请选择文件");
                return;
            }
            layer.confirm('是否导入？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                function (index) {
                    abc.block();
                    $.ajaxFileUpload({
                        url: ctx + "/cszjs/wxactivity/import.php",
                        secureuri: false,
                        fileElementId: 'uploadFile',//file标签的id
                        dataType: 'json',
                        success: function (data) {
                            //console.log(data);
                            abc.unblock();
                            if (data.result.code == '2000') {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                setTimeout(function () {
                                   location.reload();
                                }, 2000);
                            } else {
                                layer.msg(data.result.message, {icon: 5});
                            }
                        }
                    });
                }
            );
        });

        //a删除
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        $('#back').click(function () {
            $("#myModal").hide();
            $("#uploadFile").val("");
           // window.location.href =$("form").attr("action");
        });

        $("a[data-type='download']").click(function () {
            window.location.href = ctx+"/cszjs/wxactivity/downloadExcel.php";
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
        
        $("a[data-type='opendialog']").click(function(){
        	abc.block();
			var url=$(this).attr("data-url");
			var iframe=document.getElementById("consumer_frame");
			iframe.src=url;
			if (iframe.attachEvent){ 
				iframe.attachEvent("onload", function(){ 
					//$("#myModal3").fadeIn();
					$("#myModal3").show();
		    		$("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
					abc.unblock();
				}); 
			} else { 
				iframe.onload = function(){ 
					//$("#myModal3").fadeIn(); 
					$("#myModal3").show();
		    		$("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
					abc.unblock();
				}; 
			} 
			
		});

        $("#editbtn").click(function(){
            var _id = document.getElementById('hbkl_frame').contentWindow.document.getElementById('id').value;
            var openId = document.getElementById('hbkl_frame').contentWindow.document.getElementById('openId').value;
            var createTimes = document.getElementById('hbkl_frame').contentWindow.document.getElementById('createTime').value;
            var repack={};
            repack["openId"]=openId;
            repack['id']=_id;
            repack['createTimes']=createTimes;
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                function(){
                    abc.block();
                    $.ajax({
                        type: "POST",
                        ///ctx+"/cszjs/wxactivity/update.php";
                        url:ctx+"/cszjs/wxactivity/update.php",
                        data: JSON.stringify(repack),
                        async: true,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            abc.unblock();
                            if (data && data.code == "2000") {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                location.reload();
                            } else {
                                layer.alert(result.message||"操作失败", {icon: 5});
                            }
                        }
                    });
                }
            );
             //   abc.layerAjaxConfirm("POST", ctx+"/cszjs/wxactivity/update.php", $("#redpack_edit").serializeJson(), document.referrer);
        });
        $("a[data-type='editdialog']").click(function(){
            abc.block();
            var url=$(this).attr("data-url");
            var iframe=document.getElementById("hbkl_frame");
            iframe.src=url;
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    //$("#myModal3").fadeIn();
                    $("#myModal1").show();
                    $("#myModal1").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                });
            } else {
                iframe.onload = function(){
                    //$("#myModal3").fadeIn();
                    $("#myModal1").show();
                    $("#myModal1").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
                    abc.unblock();
                };
            }

        });
        
        $("button[data-dismi]").click(function(){
			 //$("#myModal3").fadeOut();
			 $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
	      	    	$("#myModal3").hide();
	      	 });
		});

        $("button[data-dism]").click(function(){
            //$("#myModal3").fadeOut();
            $("#myModal1").find(".modal-dialog").animate({'top':'-700px'},500,function(){
                $("#myModal1").hide();
            });
        });
        $('.nycon_sel_btn').on('click',function (){
            var oselectall = $('input[name="ids"]');
            var bool=false;
            oselectall.each(function() {
                if (this.checked == false) {
                    bool=true;
                }
            })
            if(bool){
                oselectall.each(function() {
                    this.checked = true;
                    $(this).next().addClass("layui-form-checked");
                })
            }else{
                oselectall.each(function() {
                    this.checked = false;
                    $(this).next().removeClass("layui-form-checked");
                })
            }
        });

        $('#batch_del').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids='';
            var idss =[];
            var bool=true;
            oselectall.each(function() {
                if (this.checked == true) {
                    var status=$(this).attr('status');
                    ids+=this.value+',';
                    idss.push(this.value);
                    if(status==1){
                        bool=false;
                    }
                }
            })

            if(ids==''){
                layer.msg("请选择需要批量删除的项!", {icon: 5});
            }else{
                if(!bool){
                    layer.msg("选择的记录中有抽过奖的记录，删除失败!", {icon: 5});
                    return;
                }
                layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                    function(){
                        abc.block();
                        $.ajax({
                            type: "DELETE",
                            url: ctx+"/cszjs/wxactivity/batchDel.php",
                            data: JSON.stringify(idss),
                            async: true,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                abc.unblock();
                                if (data && data.code == "2000") {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        location.reload();
                                    }, 2000);
                                } else {
                                    abc.layerAlert(false, '操作失败: '+data.message);
                                }
                            }
                        });
                    }
                );
            }
        });

        $("a[data-type='detele']").on('click',function (){
            var url=$(this).attr("data-url");
            layer.confirm('警告：删除未使用的口令可能会导致用户无法参与本次活动，请谨慎操作！是否确认删除？？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                function(){
                    abc.block();
                    $.ajax({
                        type:"POST",
                        url: url ,
                        success: function (data){
                            if(data.result.code=='2000'){
                                layer.msg("删除成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                setTimeout(function () {
                                    location.reload();
                                }, 2000);
                            }else{
                                layer.msg(data.result.message, {icon: 5});
                            }
                        } ,
                        dataType: "JSON"
                    });
                }
            );

        });
    });
})