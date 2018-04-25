require(["../../config"], function () {
    require(["jquery.full","abc.ajaxfileupload","highlighter.full","categoryAxisRenderer","barRenderer","pointLabels.min"], function ($) {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			laydate.render({
				elem: '#startTime',
				theme: '#14b9d5'
			});
		})
      $(function () {
		  var startTime = $("#startTime").val();
		  $.ajax({
			  type: "GET",
			  url: ctx+"/apilog/listJson.php?startTime="+startTime,
			  async: false,
			  contentType: "application/json",
			  dataType: "JSON",
			  success: function (data) {
				  var params  = new Array();
				  for(var i=0;i<data.length;i++){
					  var _datas=data[i];
					  params[i] = new Array();
					  params[i][0] = _datas.nickname;
					  params[i][1] = parseInt(_datas.appAccessCount);
				  }
				  $("#tjday").html("");
				  $.jqplot("tjday", [params], {
					  title: "接口访问日志统计",
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
							  tickOptions: {
								  fontFamily: 'Georgia',
								  fontSize: '10pt',
								  labelPosition: 'start'
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
		  });

    	  $("#consumer_query").click(function(){
			  var time = $("#startTime").val();
    		  abc.block();
 			  $('#consumerListForm').submit();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/apilog/listJson.php?startTime="+time,
				  async: false,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  var params  = new Array();
					  for(var i=0;i<data.length;i++){
						  var _datas=data[i];
						  params[i] = new Array();
						  params[i][0] = _datas.nickname;
						  params[i][1] = parseInt(_datas.appAccessCount);
					  }
					  $("#tjday").html("");
					  $.jqplot("tjday", [params], {
						  title: "接口访问日志统计",
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
								  tickOptions: {
									  fontFamily: 'Georgia',
									  fontSize: '10pt',
									  labelPosition: 'start'
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
			  });
     	  }); 
    	  
    	  $(".fl").click(function(){
    		  abc.block();
    		  window.location.href=ctx+"/apilog/appApiCounts?appId="+$(this).data('id')+'&startTime='+$("#startTime").val();
    	  });
       });
    })
});