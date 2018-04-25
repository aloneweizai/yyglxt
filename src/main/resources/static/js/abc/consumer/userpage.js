require(["../../config"], function () {
	require(["jquery.full","tagEditor"], function ($) {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			var form = layui.form;
			//年月选择器
			laydate.render({
				elem: '#test3'
				,type: 'month'
			});
			laydate.render({
				elem: '#test30',
				theme: '#14b9d5'
				,done: function(value, date){
					layuiSelected("datetype","时间段");
					//alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
				}
			});
			laydate.render({
				elem: '#test31',
				theme: '#14b9d5'
				,done: function(value, date){
					layuiSelected("datetype","时间段");
					//alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
				}
			});
			form.on('select(datetype)', function(data){
				if(data.value=="1"){
					$("input[name='startDate']").val(getcurrentDate());
					$("input[name='endDate']").val(getcurrentDate());
				}
				else if(data.value=="2"){
					$("input[name='startDate']").val(yesterday());
					$("input[name='endDate']").val(yesterday());
				}
				else if(data.value=="3"){
					$("input[name='startDate']").val(sevenDays());
					$("input[name='endDate']").val(getcurrentDate());
				}
				else if(data.value=="4"){
					$("input[name='startDate']").val(thirtyDays());
					$("input[name='endDate']").val(getcurrentDate());
				}
				else if(data.value==""){
					$("input[name='startDate']").val("");
					$("input[name='endDate']").val("");
				}
			});

			form.on('select(datechange)', function(data){
				if(data.value=="1"){
					$("input[name='createTimeB']").val(getcurrentDate());
					$("input[name='createTimeE']").val(getcurrentDate());
				}
				else if(data.value=="2"){
					$("input[name='createTimeB']").val(yesterday());
					$("input[name='createTimeE']").val(yesterday());
				}
				else if(data.value=="3"){
					$("input[name='createTimeB']").val(sevenDays());
					$("input[name='createTimeE']").val(getcurrentDate());
				}
				else if(data.value=="4"){
					$("input[name='createTimeB']").val(thirtyDays());
					$("input[name='createTimeE']").val(getcurrentDate());
				}
				else if(data.value==""){
					$("input[name='createTimeB']").val("");
					$("input[name='createTimeE']").val("");
				}
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

			$('#tagnames').tagsInput({
				width:'440px',
				height:'45px',
				interactive:false,
				defaultText:'标签'
			});

			$("a[data-type='unselect']").click(function(){
				var _name=$(this).attr('data-id');
				if (!$('#tagnames').tagExist(_name)){
					$('#tagnames').addTag(_name);
				}
			});

			$("#seletablib").change(function(){
				var _v=$(this).val();
				if(_v==""){
					$(".itemWraper1").css('display','block');
				}else{
					$(".itemWraper1").css('display','none');
					$("#tag_"+_v).css('display','block');
				}
			});

			$("#tagslect").click(function(){
				$("#myModal").show();
				$("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(50)},500);
			});
			$("button[data-dismiss]").click(function(){
				$("#myModal").find(".modal-dialog").animate({'top':'-600px'},500,function(){
					$("#myModal").hide();
				})
			});
			$("#tagdelall").click(function(){
				$('#tagnames').clearALL();
			});

			$("a").on('click',function (){
				var data=$(this).attr("data");
				var url=$(this).attr("data-url");
				var type=$(this).attr('type');
				var abc_type=$(this).attr('abc-type');
				if('get'==type||'GET'==type){
					window.location.href=url;
				}else{
					if(typeof(abc_type)!="undefined"){
						layer.confirm(abc_type, {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
							function(index){
								$.ajax({
									type:type,
									url: url ,
									data:data,
									success: function (data){
										//alert(data.result.code);
										if(data.result.code=='2000'){
											layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000}
												,function(){goPage(parseInt($("#cupageVal").val()));});
										}else{
											layer.msg(data.result.message, {icon: 5});
										}
									} ,
									dataType: "JSON"
								});
							}
						);
					}else{
						//console.log("err");
					}
				}
			});

           var doType = 0;
			//查询
			$("#consumer_query").click(function(){
				doType = $(this).attr("data-val");
				goPage(-1);
			});
			//每页大小
			$("#consumer_size").keypress(function(e) {
				doType = $(this).attr("data-val");
				if(e.which == 13) {
					goPage(-1);
				}
			});
			//首页
			$("#consumer_first").click(function(){
				doType = $(this).attr("data-val");
				goPage(1);
			});
			//前一页
			$("#consumer_up").click(function(){
				doType = $(this).attr("data-val");
				goPage(parseInt($("#cupageVal").val())-1);
			});
			//下一页
			$("#consumer_down").click(function(){
				doType = $(this).attr("data-val");
				goPage(parseInt($("#cupageVal").val())+1);
			});
			//最后一页
			$("#consumer_last").click(function(){
				doType = $(this).attr("data-val");
				goPage(parseInt($("#topageVal").val()));
			});
			//跳转
			$("#consumer_go").keypress(function(e) {
				doType = $(this).attr("data-val");
				if(e.which == 13) {
					goPage(isNaN($("#consumer_go").val())?1:$("#consumer_go").val());
				}
			});
			//跳转匡
			$("#consumer_gogo").click(function(e) {
				doType = $(this).attr("data-val");
				goPage(parseInt(isNaN($("#consumer_go").val())?1:$("#consumer_go").val()));
			});

			//a删除
			$("a[data-type='delSig']").click(function(){
				abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', location.href);
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

			$("[id='sjlog']").click(function(){
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

			$("button[data-dismi]").click(function(){
				//$("#myModal3").fadeOut();
				$("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
					$("#myModal3").hide();
				});
			});

			/*$("a[data-type='opendialog']").click(function(){
			 var url=$(this).attr("data-url");
			 $("#myModal3").fadeIn();
			 document.getElementById("consumer_frame").src=url;
			 });

			 $("button[data-dismi]").click(function(){
			 $("#myModal3").fadeOut();
			 });*/

			//批量打标签
			var _slect=[];
			var _slectTag={};
			$('#taged-s').tagsInput({
				width:'500px',
				height:'60px',
				interactive:false,
				onRemoveTag:function(tag){
					delete  _slectTag[tag]
				},
				defaultText:''
			});
			//保存按钮
			$("button[data-yes]").click(function(){
				var _slectTags=[];
				for(var p in _slectTag){
					_slectTags.push(_slectTag[p]);
				}
				if(_slectTags.join(",")==''){
					abc.layerAlert(false, '请添加需要打的标签!');
					return;
				}
				var batchTagInsertRq={};
				batchTagInsertRq["subjectIds"]=_slect.join(',');
				batchTagInsertRq["tagIds"]=_slectTags.join(',');
				layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
					function(){
						abc.block();
						$.ajax({
							type: "POST",
							url: ctx+'/consumerManager/consumer/batchTags.php',
							data: JSON.stringify(batchTagInsertRq),
							async: true,
							contentType: "application/json",
							dataType: "JSON",
							success: function (data) {
								abc.unblock();
								if (data && data.code == "2000") {
									layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
									$("#myModal2").find(".modal-dialog").animate({'top':'-600px'},500,function(){
										$("#myModal2").hide();
									})
								} else {
									abc.layerAlert(false, '操作失败: '+data.message);
								}
							}
						});
					}
				);
			});
			$("button[data-del]").click(function(){
				$('#taged-s').clearALL();
			});



			$(".xiugaishouji").click(function(){
				$("#xghmnc").val($(this).attr('data-nickname'));
				$("#xghmyhm").html(($(this).attr('data-ylphone')=='')?"-":$(this).attr('data-ylphone'));
				$("#xghmyuserid").val($(this).attr('data-userid'));
				$("#myModal4").show();
				$("#myModal4").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(10)},500);
			});

			$("a[data-type='resetpwd']").click(function(){
				$("#userid").val($(this).attr('data-userid'));
				layer.confirm('是否重置密码,默认密码是abc12366？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					function(index) {
						layer.close(index);
						$("#myModal5").show();
						$("#myModal5").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(10)},500);
					}
				);
			});

			$("button[data-resetpwd]").click(function(){
				$("#myModal5").find(".modal-dialog").animate({'top':'-600px'},500,function(){
					$("#myModal5").hide();
				})
			});

			$("button[data-reset]").click(function(){
				var _id = $("#userid").val();
				var _reason = $("#pwdreason").val();
				if(_reason ==null || _reason==""){
					abc.layerAlert(false, "请填写重置原因!");
					return;
				}
				_reason = "重置密码-"+ _reason;
				abc.layerAjaxConfirm("POST", ctx + '/consumerManager/editpwd.php?userId=' + _id  + '&reason=' + _reason, '', document.location.href);
			});

			$("[data-disxgsj]").click(function(){
				$("#myModal4").find(".modal-dialog").animate({'top':'-600px'},500,function(){
					$("#myModal4").hide();
				})
			});

			$("[data-xgsj]").click(function(){
					var _id = $("#xghmyuserid").val();
					var _phone = $("#newphone").val();
					var _reason = $("#reason").val();
				var username = $("#xghmnc").val();
				if(username ==null || username==""){
					abc.layerAlert(false, "用户名不能为空!");
					return;
				}
				if(username !=null && username!=""&&!chek_username(username)){
					abc.layerAlert(false, "请输入2-20位的用户名！");
					return;
				}
					if(_reason ==null || _reason==""){
						abc.layerAlert(false, "请填写修改备注!");
						return;
					}
				_reason = "修改用户名和手机号码-"+ _reason;
				//^1[3|4|5|8|7][0-9]\d{8}$
					if (_phone !== '' && !/^1[0-9]{10}$/.test(_phone)) {
						abc.layerAlert(false, '请输入正确的手机号码!');
					} else {
						abc.layerAjaxConfirm("POST", ctx + '/consumerManager/editphone.php?userId=' + _id + '&phone=' + _phone + '&reason=' + _reason + '&username=' + username, '', document.location.href);
					}
			});



			$("#batchTags").click(function(){
				_slect = [];
				$.each($(".js_checkbox"),function (){
					if(this.checked){
						_slect.push(this.value);
					}
				});
				if(_slect.length==0){
					abc.layerAlert(false, '请选择需要打标签的用户!');
					return;
				}
				$("#myModal2").show();
				$("#myModal2").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(10)},500);

			});

			$("button[data-dis]").click(function(){
				$("#myModal2").find(".modal-dialog").animate({'top':'-600px'},500,function(){
					$("#myModal2").hide();
				})
				//$("#myModal2").hide();
			});

			$("#seletablib2").change(function(){
				var _v=$(this).val();
				if(_v==""){
					$(".itemWraper2").css('display','block');
				}else{
					$(".itemWraper2").css('display','none');
					$("#tag2_"+_v).css('display','block');
				}
			});
			//点击标签事件
			$("a[data-taged]").click(function(){


				var _lib=$(this).attr('data-lib');
				var list1 = $(this).parent().parent();
				var abq = list1.find("a");
				//得到同等级的a标签
				for(var  i =0;i<abq.length;i++){
					var _name=$(abq[i]).attr('data-name');
					if ($('#taged-s').tagExist(_name)){
						//console.log("重复");
						return;
					}
				}



				var _name=$(this).attr('data-name');
				var _id=$(this).attr('data-id');

				$('#taged-s').addTag(_name);
				_slectTag[_name]=_id;

			});

			var goPage= function(index){
				if(isNaN($("#consumer_size").val())){
					$("#consumer_size").val(10);
				}
				var curtPage=parseInt($("#cupageVal").val());
				var totalPage=parseInt($("#topageVal").val());
				if((index<1||index>totalPage)&&index!=-1){
					return;
				}else if(index==-1){
					index=1;
				}
				if(doType == 1){
					var username = $("input[name='username']").val();
					var realName = $("input[name='realName']").val();
					var nickname = $("input[name='nickname']").val();
					var startDate = $("input[name='startDate']").val();
					var endDate = $("input[name='endDate']").val();
					var phone = $("input[name='phone']").val();
					if((username ==null || username == "")&&(realName ==null || realName == "") &&(nickname ==null || nickname == "")
						&&(startDate ==null || startDate == "")&&(endDate ==null || endDate == "")&&(phone ==null || phone == "")){
						abc.layerAlert(false, "请输入至少一个查询条件");
						return;
					}
				}
				var pointsOper = $("#pointsOper").val();
				var points = $("input[name='points']").val();
				if((pointsOper ==null || pointsOper == "")&&(points !=null && points!="")){
						abc.layerAlert(false, "已输入积分，请输入相应的积分查询条件！");
						return;
				}
				if(points !=null && points!=""&&!chek(points)){
					abc.layerAlert(false, "请输入整数！");
					return;
				}
				var expOper = $("#expOper").val();
				var exp = $("input[name='exp']").val();
				if((expOper ==null || expOper == "")&&(exp !=null && exp!="")){
					abc.layerAlert(false, "已输入经验值，请输入相应的经验值查询条件！");
					return;
				}
				if(exp !=null && exp!=""&&!chek(exp)){
					abc.layerAlert(false, "请输入整数！");
					return;
				}
				abc.block();
				$('#cupageVal').val(index);
				$('#consumerListForm').submit();
			}

            //匹配整数
			function chek(val){
				return ((/^-?[1-9]\d*$/.test(val)));
			}
			//用户名校验
			function chek_username(val){
				if(val.length<2||val.length>20){
					return false;
				}
				else{
					return true;
				}
				//return ((/^[0-9a-zA-Z]{3,15}$/.test(val)));
			}
			$("select[data-type='ajax']").each(function(){
				var this_=$(this);
				var rule=this_.attr('data-rules').split(":");
				var urls=this_.attr('data-url');
				var val=this_.attr('data-val');
				$.ajax({
					type: "GET",
					url: urls,
					async: false,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						if (data) {
							$.each(data, function (i, item) {
								this_.append("<option "+ (eval("item."+rule[0])==val?"selected":"") +" value='"+ eval("item."+rule[0])+"'>" + eval("item."+rule[1]) + "</option>");
							});
						} else {
						}
					}
				});
			});

			function timeForMat (count) {
				var time2 = new Date()
				   time2.setTime(time2.getTime() - (24 * 60 * 60 * 1000 * count))
				var Y2 = time2.getFullYear()
				var M2 = ((time2.getMonth() + 1) > 10 ? (time2.getMonth() + 1) : '0' + (time2.getMonth() + 1))
				var D2 = (time2.getDate() > 10 ? time2.getDate() : '0' + time2.getDate())
				var timer2 = Y2 + '-' + M2 + '-' + D2 // 之前的7天或者30天
				   return timer2;

			}
			//当前时间
			function getcurrentDate(){
				// 拼接时间
				var time1 = new Date()
				//time1.setTime(time1.getTime() - (24 * 60 * 60 * 1000))
				var Y1 = time1.getFullYear()
				var M1 = ((time1.getMonth() + 1) > 10 ? (time1.getMonth() + 1) : '0' + (time1.getMonth() + 1))
				var D1 = (time1.getDate() > 10 ? time1.getDate() : '0' + time1.getDate())
				var timer1 = Y1 + '-' + M1 + '-' + D1 // 当前时间
				return timer1;
			}

		 function yesterday () {
			   // 校验是不是选择的昨天
			 var timer = timeForMat(1)
			   return timer
			 }

		 function sevenDays () {
			   // 获取最近7天
			 var timer = timeForMat(7)
			   return timer
			 }

		 function thirtyDays () {
			   // 获取最近30天
			 var timer = timeForMat(30)
			   return timer
			 }

			/**
			 * layui：select插件,默认选中
			 * ps：单个下拉框
			 * @param 下拉框的id
			 * @param 想要让选中的值：str
			 */
			function layuiSelected(id,str){
				//0、设置select的值
				$("#"+id).attr("value",str);
				//0.1把select下的option的selected换成现在的
				$("#"+id).children("option").each(function(){
					if ($(this).text() == str) {
						$(this).attr("selected","selected");
					}else{
						if ($(this).attr("selected") == "selected") {
							$(this).removeAttr("selected");
						}
					}
				});
				//1、首先设置输框
				$("#"+id).siblings("div[class='layui-unselect layui-form-select']").children("div[class='layui-select-title']").children("input").val(str);
				//2、其次，设置dl下的dd
				$("#"+id).siblings("div[class='layui-unselect layui-form-select']").children("dl").children("dd").each(function(){
					if ($(this).text() == str){
						if (!$(this).hasClass("layui-this")) {
							$(this).addClass("layui-this");
							$(this).click();
						}
						return true;
					}else{
						if ($(this).hasClass("layui-this")) {
							$(this).removeClass("layui-this");
						}
					}
				});
			}

		});
	})
});
