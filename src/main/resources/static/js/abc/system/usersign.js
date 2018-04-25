require(["../../config"], function () {
    require(["jquery.full","jqplot.full","abc.common"], function ($) {
      $(function () {
		  layui.use('laydate', function() {
			  var laydate = layui.laydate;
			  //日期范围
			  laydate.render({
				  elem: '#test6'
				  ,range: true
				  ,theme: '#14b9d5'
				  ,done: function(value, date){
					  type = 5;//时间段
					  $("#sjd").removeClass("layui-btn-primary");
					  $("#lastweek").addClass("layui-btn-primary");
					  $("#thismon").addClass("layui-btn-primary");
					  $("#thisweek").addClass("layui-btn-primary");
					  $("#lastmon").addClass("layui-btn-primary");
				  }
			  });
		  })
		  $("#test6").val(abc.getCurrentWeek()[0]+" - "+abc.getCurrentWeek()[1]);
		  $("#thisweek").click(function(){
			  type = 0;//本周
			  $(this).removeClass("layui-btn-primary");
			  $("#lastweek").addClass("layui-btn-primary");
			  $("#thismon").addClass("layui-btn-primary");
			  $("#lastmon").addClass("layui-btn-primary");
			  $("#sjd").addClass("layui-btn-primary");
			  var startStop = abc.getCurrentWeek();
			  $("#test6").val(startStop[0]+" - "+startStop[1]);
		  });
		  $("#lastweek").click(function(){
			  type = 1;//上周
			  $(this).removeClass("layui-btn-primary");
			  $("#thisweek").addClass("layui-btn-primary");
			  $("#thismon").addClass("layui-btn-primary");
			  $("#lastmon").addClass("layui-btn-primary");
			  $("#sjd").addClass("layui-btn-primary");
			  var startStop = abc.getPreviousWeek();
			  $("#test6").val(startStop[0]+" - "+startStop[1]);
		  });
		  $("#thismon").click(function(){
			  type = 2;//本月
			  $(this).removeClass("layui-btn-primary");
			  $("#lastweek").addClass("layui-btn-primary");
			  $("#thisweek").addClass("layui-btn-primary");
			  $("#lastmon").addClass("layui-btn-primary");
			  $("#sjd").addClass("layui-btn-primary");
			  var startStop = abc.getCurrentMonth();
			  $("#test6").val(startStop[0]+" - "+startStop[1]);
		  });
		  $("#lastmon").click(function(){
			  type = 3;//上月
			  $(this).removeClass("layui-btn-primary");
			  $("#lastweek").addClass("layui-btn-primary");
			  $("#thismon").addClass("layui-btn-primary");
			  $("#thisweek").addClass("layui-btn-primary");
			  $("#sjd").addClass("layui-btn-primary");
			  var startStop = abc.getPreviousMonth();
			  $("#test6").val(startStop[0]+" - "+startStop[1]);
		  });
		  $("#sjd").click(function(){
			  type = 5;//时间段
			  $(this).removeClass("layui-btn-primary");
			  $("#lastweek").addClass("layui-btn-primary");
			  $("#thismon").addClass("layui-btn-primary");
			  $("#thisweek").addClass("layui-btn-primary");
			  $("#lastmon").addClass("layui-btn-primary");
		  });
		  var type = 0;
		  var timed = $("#test6").val();
		  var plot;
		  /*$.ajax({
			  type: "GET",
			  url: ctx+"/taxcompstatis/registercompcount/listJson.php?type="+type+"&&timed="+timed,
			  async: true,
			  contentType: "application/json",
			  dataType: "JSON",
			  success: function (data) {
				  $("#registeruser").html("");
				  var html = "<tr><th width='30'>序号</th><th>日期</th><th>企业税务登记注册数（个）</th>";
				  $.each(data.userTrendCountRs, function (i, item) {
					  html+= "<tr><td width='4%'>"+(i+1)+"</td>";
					  html+= "<td>"+item.djrq +"</td>";
					  html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/taxcompstatis/registercompcount/detail.php?days="+item.djrq+"'>"+item.total +"</a></td>";
					  html+= '</tr>';
				  });
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
				  $.jqplot("tjday", [eval(data.tjday)], {
					  title: "企业税务登记注册情况统计",
					  seriesDefaults: {
						  pointLabels: { show: true },
						  shadow: false,
						  showMarker: true,// 是否强调显示图中的数据节点
						  renderer: $.jqplot.BarRenderer,
						  rendererOptions: {
							  varyBarColor: true,
							  showDataLabels: true
						  }
					  },
					  axes: {
						  xaxis: {
							  renderer: $.jqplot.CategoryAxisRenderer,
							  labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
							  tickRenderer: $.jqplot.CanvasAxisTickRenderer,
							  tickOptions:{
								  angle: -30
							  }
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
			  },
			  error: function () {
			  }
		  });*/

    	  $("#consumer_query").click(function(){
			  /*var timed = $("#test6").val();
			  var ticks = [];
			  var html = "<tr><th width='30'>序号</th><th>日期</th><th>企业税务登记注册数（个）</th>";
			  abc.block();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/taxcompstatis/registercompcount/listJson.php?type="+type+"&&timed="+timed,
				  async: true,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  abc.unblock();
					  $("#registeruser").html("");
					  $.each(data.userTrendCountRs, function (i, item) {
						  html+= "<tr><td width='4%'>"+(i+1)+"</td>";
						  html+= "<td>"+item.djrq +"</td>";
						  html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/taxcompstatis/registercompcount/detail.php?days="+item.djrq+"'>"+item.total +"</a></td>";
						  html+= '</tr>';
					  });
					  var day = data.day;
					  if(day_d>30){
						  for(var i=0;i<day;i++){
							  ticks[i] = "第"+(i+1)+"月";
						  }
					  }
					  else{
						  for(var i=0;i<day;i++){
							  ticks[i] = "第"+(i+1)+"天";
						  }
					  }
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
						  $.jqplot("tjday", [eval(data.tjday)], {
							  title: "企业税务登记注册情况统计",
							  seriesDefaults: {
								  pointLabels: { show: true },
								  shadow: false,
								  showMarker: true,// 是否强调显示图中的数据节点
								  renderer: $.jqplot.BarRenderer,
								  rendererOptions: {
									  //varyBarColor: true,
									  showDataLabels: true
								  }
							  },
							  axes: {
								  xaxis: {
									  renderer: $.jqplot.CategoryAxisRenderer,
									  labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
									  tickRenderer: $.jqplot.CanvasAxisTickRenderer,
									  tickOptions:{
										  angle: -30
									  }
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
					  },
				  error: function () {
				  }
			  });*/
			  abc.block();
			  $('#consumerListForm').submit();
     	  });

		  $("button[data-dismi]").click(function(){
			  //$("#myModal3").fadeOut();
			  $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
				  $("#myModal3").hide();
			  });
		  });

		  function getDays(strDateStart,strDateEnd){
			  var strSeparator = "-"; //日期分隔符
			  var oDate1;
			  var oDate2;
			  var iDays;
			  oDate1= strDateStart.split(strSeparator);
			  oDate2= strDateEnd.split(strSeparator);
			  var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
			  var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
			  iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数
			  return iDays ;
		  }

	  });
    })
});