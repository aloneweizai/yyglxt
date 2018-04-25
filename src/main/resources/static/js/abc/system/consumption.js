require(["../../config"], function () {
    require(["jquery.full"], function ($) {
      $(function () {
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
			  form.on('select(tradeMethod)', function(data){
				  if(data.value=="RMB"){
					  tradeMethod = data.value;
				  }else if(data.value=="POINTS"){
					  tradeMethod = data.value;
				  }
			  });
		  })
		  $("button[data-dismi]").click(function(){
			  //$("#myModal3").fadeOut();
			  $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
				  $("#myModal3").hide();
			  });
		  });

		  $("a[data-type='opendialog']").click(function(){
			  var startTime = $('input[name="startTime"]').val();
			  var endTime = $('input[name="endTime"]').val();
			  var minTime = $("#minTime").val();
			  var avgTime = $("#avgTime").val();
			  var maxTime = $("#maxTime").val();
			  var minConsum = $("#minConsum").val();
			  var avgConsum = $("#avgConsum").val();
			  var maxConsum = $("#maxConsum").val();
			  var minPrice = $("#minPrice").val();
			  var avgPrice = $("#avgPrice").val();
			  var maxPrice = $("#maxPrice").val();
			  var startCount;
			  var endCount;
			  var startPrice;
			  var endPrice;
			  var startDay;
			  var endDay;
			  var time = $("input:radio[name='time']:checked").val();
			  if(time == '1'){
				  startDay = minTime;
				  endDay = avgTime;
			  }
			  else{
				  startDay = avgTime;
				  endDay = maxTime;
			  }
			  var consum = $("input:radio[name='consum']:checked").val();
			  if(consum == '1'){
				  startCount = minConsum;
				  endCount = avgConsum;
			  }
			  else{
				  startCount = avgConsum;
				  endCount = maxConsum;
			  }
			  var price = $("input:radio[name='price']:checked").val();
			  if(price == '1'){
				  startPrice = minPrice;
				  endPrice = avgPrice;
			  }
			  else{
				  startPrice = avgPrice;
				  endPrice = maxPrice;
			  }
			  abc.block();
			  var url=ctx+"/usertrendcount/consumption/look.php?startDay="+startDay+"&endDay="+endDay+"&tradeMethod="+tradeMethod+"&startCount="+startCount+"&endCount="+endCount+"&startTime="+startTime
				  +"&endTime="+endTime+"&startPrice="+startPrice+"&endPrice="+endPrice;
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
         //查询
    	 $("#consumer_query").click(function(){
			 var startTime = $("input[name='startTime']").val();
			 if(startTime ==null || startTime==""){
					 abc.layerAlert(false, "请选择开始时间");
					 return;
			 }
			 var endTime = $("input[name='endTime']").val();
			 if(endTime ==null && endTime==""){
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
		  var tradeMethod = "RMB";
    	 //统计
         $("a[data-type='comsumtj']").click(function(){
			 var startTime = $('input[name="startTime"]').val();
			 var endTime = $('input[name="endTime"]').val();
			 var minTime = $("#minTime").val();
			 var avgTime = $("#avgTime").val();
			 var maxTime = $("#maxTime").val();
			 var minConsum = $("#minConsum").val();
			 var avgConsum = $("#avgConsum").val();
			 var maxConsum = $("#maxConsum").val();
			 var minPrice = $("#minPrice").val();
			 var avgPrice = $("#avgPrice").val();
			 var maxPrice = $("#maxPrice").val();
			 tradeMethod = $("#tradeMethod").val();
			 var startCount;
			 var endCount;
			 var startPrice;
			 var endPrice;
			 var startDay;
			 var endDay;
			 var time = $("input:radio[name='time']:checked").val();
			 if(time == '1'){
				 startDay = minTime;
				 endDay = avgTime;
			 }
			 else{
				 startDay = avgTime;
				 endDay = maxTime;
			 }
			 var consum = $("input:radio[name='consum']:checked").val();
			 if(consum == '1'){
				 startCount = minConsum;
				 endCount = avgConsum;
			 }
			 else{
				 startCount = avgConsum;
				 endCount = maxConsum;
			 }
			 var price = $("input:radio[name='price']:checked").val();
			 if(price == '1'){
				 startPrice = minPrice;
				 endPrice = avgPrice;
			 }
			 else{
				 startPrice = avgPrice;
				 endPrice = maxPrice;
			 }
			 $.ajax({
				 type: "GET",
				 url: ctx+"/usertrendcount/consumption/listJson.php?startDay="+startDay+"&endDay="+endDay+"&tradeMethod="+tradeMethod+"&startCount="+startCount+"&endCount="+endCount+"&startTime="+startTime
				 +"&endTime="+endTime+"&startPrice="+startPrice+"&endPrice="+endPrice,
				 async: true,
				 contentType: "application/json",
				 dataType: "JSON",
				 success: function (data) {
					// $("#tjday").html("");
					 $("#tjs").html(data.total);
				 },
				 error: function () {
				 }
			 });
         });

		  function CompareDate(d1,d2)
		  {
			  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  }

       });
    })
});
