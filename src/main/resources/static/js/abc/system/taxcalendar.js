require(["../../config"], function () {
	require(["jquery.full","tagEditor"], function ($) {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			
			laydate.render({
				elem: '#sbnftime'
				,type: 'month'
			});
			laydate.render({
				elem: '#queryY'
				,type: 'year'
			});
			laydate.render({
				elem: '#queryM'
				,type: 'month'
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

				
				abc.block();
				$('#cupageVal').val(index);
				$('#consumerListForm').submit();
			}

			//a删除
			$("a[data-type='delSig']").click(function(){
				abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', location.href);
			});

			$("button[data-dismiss]").click(function(){
	    		  $("#myModal").find(".modal-dialog").animate({'top':'-600px'},500,function(){
	        	    	$("#myModal").hide();
	        	    	$("tr[data-hide]").hide();
	        	  })
	        });
			
			$("a[data-do='applay_detail']").click(function(){
				  var infos=$(this).attr('data-info').split('|');
				  $('[name="sbyfA"]').val(infos[1]);
				  $('[name="sbrqA"]').val(infos[2]);
				  $('[name="remark"]').val(infos[3]);
				  $('[name="calId"]').val(infos[0]);
				  $("#myModal").show();
	     		  $("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(50)},500);
	   	    });
			
			
			$("#addbsrl").click(function(){
				 $('[name="sbyfA"]').val('');
				 $('[name="sbrqA"]').val('');
				 $('[name="remark"]').val('');
				 $('[name="calId"]').val('');
				 $("#myModal").show();
	     		 $("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(50)},500);
			});
			
			$('[data-save="modal"]').click(function(){
				  var sbyf=$('[name="sbyfA"]').val();
				  if(sbyf==''){
					abc.layerAlert(false,'请填写申报年月');
					return;
				  }
				  var sbrq=$('[name="sbrqA"]').val();
				  if(sbrq==''){
					abc.layerAlert(false,'请填写日期范围');
					return; 
				  }
				  var remark=$('[name="remark"]').val();
				  var calId=$('[name="calId"]').val();
				  var obj={calId:calId,sbyf:sbyf,description:remark,sbrq:sbrq};
				  abc.layerAjaxConfirm("POST", ctx+'/taxcalendar/save.php', JSON.stringify(obj), location.href);
			});
			
			$("a[data-type='delSig']").click(function(){
				abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', location.href);
			});

		});
	})
});
