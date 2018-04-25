require(["../../config"], function () {
	require(["jquery.full","abc.common"], function ($) {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			var form = layui.form;
			laydate.render({
				elem: '#test30',
				theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test31',
				theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test2'
				,type: 'year'
				,theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test3'
				,type: 'year'
				,theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test4'
				,type: 'month'
				,theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test5'
				,type: 'month'
				,theme: '#14b9d5'
			});
			form.on('select(type)', function(data){
				if(data.value=="day"){
					document.getElementById('day').style.display = "block";
					document.getElementById('month').style.display = "none";
					document.getElementById('year').style.display = "none";
				}else if(data.value=="month"){
					document.getElementById('day').style.display = "none";
					document.getElementById('month').style.display = "block";
					document.getElementById('year').style.display = "none";
				}
				else if(data.value=="year"){
					document.getElementById('day').style.display = "none";
					document.getElementById('month').style.display = "none";
					document.getElementById('year').style.display = "block";
				}
			});

		})
		$(function () {
			//查询
			$("#consumer_query").click(function(){
				var type = $("#type").val();
				var startTime;
				var endTime;
				if(type=="day"){
					startTime = $("input[name='startDay']").val();
					endTime = $("input[name='endDay']").val();
					document.getElementById('day').style.display = "block";
					document.getElementById('month').style.display = "none";
					document.getElementById('year').style.display = "none";
				}else if(type=="month"){
					startTime = $("input[name='startMon']").val();
					endTime = $("input[name='endMon']").val();
					document.getElementById('day').style.display = "none";
					document.getElementById('month').style.display = "block";
					document.getElementById('year').style.display = "none";
				}
				else if(type=="year"){
					startTime = $("input[name='startYear']").val();
					endTime = $("input[name='endYear']").val();
					document.getElementById('day').style.display = "none";
					document.getElementById('month').style.display = "none";
					document.getElementById('year').style.display = "block";
				}
				if(startTime ==null || startTime==""){
					abc.layerAlert(false, "请选择开始时间");
					return;
				}
				if(endTime ==null || endTime==""){
					abc.layerAlert(false, "请选择结束时间");
					return;
				}
				if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
					if(CompareDate(startTime,endTime)){
						abc.layerAlert(false, "结束时间不能小于开始时间");
						return;
					}
				}
				abc.block();
				$('#consumerListForm').submit();
			});

			$("[data-dismiss='lookModal']").click(function(){
				$("#myModal3").fadeOut();
			});

			$("a[data-type='lookdialog']").click(function(){
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

			function CompareDate(d1,d2)
			{
				return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
			}
		});
	})
});
