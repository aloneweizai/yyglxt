require(["../../config"], function () {
    require(["jquery.full","jqplot.full","abc.common","tagEditor"], function ($) {
      $(function () {
		  layui.use('laydate', function() {
			  var laydate = layui.laydate;
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
		  });

    	  $("#consumer_query").click(function(){
			  var startTime = $("#test4").val();
			  var endTime = $("#test5").val();
			  if(startTime ==null || startTime==""){
				  abc.layerAlert(false, "请选择注册时间起");
				  return;
			  }
			  if(endTime ==null || endTime==""){
				  abc.layerAlert(false, "请选择注册时间止");
				  return;
			  }
			  if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
				  if(CompareDate(startTime,endTime)){
					  abc.layerAlert(false, "注册时间止不能小于注册时间起");
					  return;
				  }
			  }
			  var html = "";
			  abc.block();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/userimganalyze/userservicecomp/listJson.php?startTime="+startTime+"&&endTime="+endTime,
				  async: true,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  var data = data.userServiceCompRs;
					  abc.unblock();
					  $("#registeruser").html("");
					  if(data != "" && data != null){
					  html+= "<tr><td>1</td><td>1-5户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=1&endNum=5&startTime="+startTime+"&endTime="+endTime+"'>"+data.oneToFive +"</a></td></tr>";
					  html+= "<tr><td>2</td><td>6-10户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=6&endNum=10&startTime="+startTime+"&endTime="+endTime+"'>"+data.sixToTen +"</a></td></tr>";
					  html+= "<tr><td>3</td><td>11-20户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=11&endNum=20&startTime="+startTime+"&endTime="+endTime+"'>"+data.elevenToTwenty +"</a></td></tr>";
					  html+= "<tr><td>4</td><td>21-30户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=21&endNum=30&startTime="+startTime+"&endTime="+endTime+"'>"+data.twentyOneToThirty +"</a></td></tr>";
					  html+= "<tr><td>5</td><td>31-40户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=31&endNum=40&startTime="+startTime+"&endTime="+endTime+"'>"+data.thirtyOneToForty +"</a></td></tr>";
					  html+= "<tr><td>6</td><td>41-50户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=41&endNum=50&startTime="+startTime+"&endTime="+endTime+"'>"+data.fortyOneToFifty +"</a></td></tr>";
					  html+= "<tr><td>7</td><td>51-100户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=51&endNum=100&startTime="+startTime+"&endTime="+endTime+"'>"+data.fiftyOneToHundred +"</a></td></tr>";
					  html+= "<tr><td>8</td><td>101-150户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=101&endNum=150&startTime="+startTime+"&endTime="+endTime+"'>"+data.hundredOneToHundredFifty +"</a></td></tr>";
					  html+= "<tr><td>9</td><td>151-200户</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=151&endNum=200&startTime="+startTime+"&endTime="+endTime+"'>"+data.hundredFiftyOneToTwoHundred +"</a></td></tr>";
					  html+= "<tr><td>10</td><td>200户以上</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/userimganalyze/userdetail/userlook.php?doType=4&startNum=200&startTime="+startTime+"&endTime="+endTime+"'>"+data.twoHundredAbove +"</a></td></tr>";
					  $("#registeruser").append(html);
					  $("#registeruser").on('click', '.opendialog',
						  function() {
							  abc.block();
							  var url=ctx+$(this).attr("data-url");
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
					  $("#tjday").html("");
						  document.getElementById('tjday').style.display = "block";
					  var line3 = [['1-5户', parseInt(data.oneToFive)], ['6-10户', parseInt(data.sixToTen)], ['11-20户', parseInt(data.elevenToTwenty)], ['21-30户', parseInt(data.twentyOneToThirty)],
						  ['31-40户', parseInt(data.thirtyOneToForty)],['41-50户', parseInt(data.fortyOneToFifty)],['51-100户', parseInt(data.fiftyOneToHundred)],['101-150户', parseInt(data.hundredOneToHundredFifty)],
						  ['151-200户', parseInt(data.hundredFiftyOneToTwoHundred)],['200户以上', parseInt(data.twoHundredAbove)]];
					  document.getElementById('userlook').style.display = "block";
					  $.jqplot("tjday", [line3], {
							  title: "用户服务企业情况统计",
							  seriesDefaults: {
								  pointLabels: { show: true },
								  shadow: false,
								  showMarker: true,// 是否强调显示图中的数据节点
								  renderer: $.jqplot.BarRenderer,
								  rendererOptions: {
									  showDataLabels: true
								  }
							  },
							  axes: {
								  xaxis: {
									  renderer: $.jqplot.CategoryAxisRenderer,
									  labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
									  tickRenderer: $.jqplot.CanvasAxisTickRenderer
								  },
								  yaxis: {
									  showTicks: true,
									  tickOptions:{
										  show: true,
										  showLabel: true,
										  showMark: true,
										  showGridline: true
									  }
								  }
							  }
					  });
					  }
					  else{
						  document.getElementById('tjday').style.display = "none";
						  $("#registeruser").append("<tr><td colspan='3'>--未查到相关信息，请重新统计--</td></tr>");
					  }
				  },
				  error: function () {
				  }
			  });
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