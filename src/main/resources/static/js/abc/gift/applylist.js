require(["../../config"], function () {
	require(["jquery.full","tagEditor"], function ($) {
		layui.use('laydate', function() {
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

			//a删除
			$("a[data-type='delSig']").click(function(){
				abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', location.href);
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
			
			$("a[data-do='applay_detail']").click(function(){
				 var _id=$(this).attr('data-info');
				 $("#sqrzcx").empty();
				 $.ajax({
					  type: "GET",
					  url: ctx+"/vipgift/applyinfo.php?applyId="+_id,
					  async: false,
					  contentType: "application/json",
					  dataType: "JSON",
					  success: function (data) {
						  $("#ap_sqid").text(_id);
						  $("#ap_sqr").text(data.name);
						  $("#ap_dhhm").text(data.phone);
						  $("#ap_sjdz").text(data.address);
						  $("#ap_sqsj").text(dateTimeFormat(data.createTime));
						  var str='';
						  if(data.giftApplyBOList.length>0){
							  for(var i=0;i<data.giftApplyBOList.length;i++){
								  str+= data.giftApplyBOList[i].giftName+"&nbsp;&nbsp;￥"+data.giftApplyBOList[i].giftAmount+"&nbsp;&nbsp;数量："+data.giftApplyBOList[i].giftNum+'</br>';
							  }
						  }
						  $("#ap_lwmc").html(str);
						  $("#ap_kdgs").text(data.expressComp);
						  $("#ap_kdhm").text(data.expressNo);
						  $("#ap_remark").text(data.remark);
						  if(data.status=='0'){
							  $("#ap_zt").html("<label style='color: rgba(255, 29, 0, 0.74);'>已拒绝</label>");
							  $("#remarktr2").show();
							  $("button[data-save]").hide();
						  }else if(data.status=='1'){
							  $("#ap_zt").html("<label style='color: #ff9000;'>待处理</label>");
							  $("#shenhetr").show();
							  $("#remarktr").show();
							  $("button[data-save]").attr('data-id',data.applyId).text('审核').show();
						  }else if(data.status=='2'){
							  $("#ap_zt").html("<label style='color: #ff9000;'>已审批</label>");
							  $("#kuaiditr1").show();
							  $("#kuaidinotr1").show();
							  $("#remarktr").show();
							  $("button[data-save]").attr('data-id',data.applyId).text('发货').show();
						  }else if(data.status=='3'){
							  $("#ap_zt").html("<label style='color: #ff9000;'>已发货</label>");
							  $("#kuaiditr").show();
							  $("#kuaidinotr").show();
							  $("button[data-save]").hide();
							  $("#remarktr2").show();
						  }else{
							  $("#ap_zt").html("<label style='color:green;'>已完成</label>");
							  $("#kuaiditr").show();
							  $("#kuaidinotr").show();
							  $("button[data-save]").hide();
							  $("#remarktr2").show();
						  }
						  if(data.ugiftLogBOList.length>0){
							  for(var i=0;i<data.ugiftLogBOList.length;i++){
								  $("#sqrzcx").append('<tr><td>'+dateTimeFormat(data.ugiftLogBOList[i].createTime)+'</td><td>'+data.ugiftLogBOList[i].action+'</td><td>'+data.ugiftLogBOList[i].adminName+'</td><td>'+getStr(data.ugiftLogBOList[i].remark)+'</td></tr>')
							  }
						  }
						  $("#myModal").show();
			     		  $("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(50)},500);
					  }
				  });
				  
				  
	   	    });
			function getStr(obj){
				if(obj !=null && obj != 'null'){
					return obj;
				}
				else{
					return "";
				}
			}
			function dateTimeFormat(longTypeDate){
				   var date = new Date();  
				   date.setTime(longTypeDate);
				   return (date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date)+" "+getHours(date)+":"+getMinutes(date)+":"+getSeconds(date));
			}
			function getMonth(date){  
		  	    var month = "";  
		  	    month = date.getMonth() + 1; //getMonth()得到的月份是0-11  
		  	    if(month<10){  
		  	        month = "0" + month;  
		  	    }  
		  	    return month;  
		  	};  
		  	function getDay(date){  
		  	    var day = "";  
		  	    day = date.getDate();  
		  	    if(day<10){  
		  	        day = "0" + day;  
		  	    }  
		  	    return day;  
		  	};

		  	function getHours(date){
		  	  var hours = "";
		  	  hours = date.getHours();
		  	  if(hours<10){ 
		  	    hours = "0" + hours; 
		  	  } 
		  	  return hours; 
		  	}

		  	function getMinutes(date){
		  	  var minute = "";
		  	  minute = date.getMinutes();
		  	  if(minute<10){ 
		  	    minute = "0" + minute; 
		  	  } 
		  	  return minute; 
		  	}

		  	function getSeconds(date){
		  	  var second = "";
		  	  second = date.getSeconds();
		  	  if(second<10){ 
		  	    second = "0" + second; 
		  	  } 
		  	  return second; 
		  	}
			
			$("button[data-dismiss]").click(function(){
	    		  $("#myModal").find(".modal-dialog").animate({'top':'-600px'},500,function(){
	        	    	$("#myModal").hide();
	        	    	$("tr[data-hide]").hide();
	        	  })
	        });
			
			$("button[data-save]").click(function(){
				var remark=$("#remark").val();
				var id=$(this).attr('data-id');
				var url='';
				var obj={applyId:id};
				if($(this).text()=='审核'){
					var status=$("input[name='status']:checked").val();
					obj['status']=status;
					obj['remark']=remark;
					if(status=='0'&& remark==''){
						abc.layerAlert(false,'审核拒绝时请填写拒绝理由！');
						return;
					}
					if(remark.length>150){
						abc.layerAlert(false,'备注信息的长度不能超过150位！');
						return;
					}
					url=ctx+'/vipgift/applycheck.php';
				}else{
					var expressComp=$("input[name='expressComp']").val();
					var expressNo=$("input[name='expressNo']").val();
					if(expressComp=='' || expressNo==''){
						abc.layerAlert(false,'快递单号或快递公司不能为空！');
						return;
					}
					if(expressComp.length>50){
						abc.layerAlert(false,'快递公司名称不能超过50位！');
						return;
					}
					if(expressNo.length>32){
						abc.layerAlert(false,'快递单号不能超过32位！');
						return;
					}
					if(remark.length>150){
						abc.layerAlert(false,'备注信息的长度不能超过150位！');
						return;
					}
					obj['expressComp']=expressComp;
					obj['expressNo']=expressNo;
					obj['remark']=remark;
					url=ctx+'/vipgift/applysend.php';
				}
				
				abc.layerAjaxConfirm("POST", url, JSON.stringify(obj), location.href);
				
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

			$("button[data-dismi]").click(function(){
				//$("#myModal3").fadeOut();
				$("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
					$("#myModal3").hide();
				});
			});

		});
	})
});
