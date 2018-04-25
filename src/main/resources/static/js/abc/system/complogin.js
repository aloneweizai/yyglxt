require(["../../config"], function () {
	require(["jquery.full","jqplot.full","abc.common","tagEditor"], function ($) {
		$(function () {
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				laydate.render({
					elem: '#test4'
					,theme: '#14b9d5'
				});
				laydate.render({
					elem: '#test5'
					,theme: '#14b9d5'
				});
			});

			$("#consumer_query").click(function(){
				var startTime = $("#test4").val();
				var endTime = $("#test5").val();
				if(startTime ==null || startTime==""){
					abc.layerAlert(false, "请选择查询日期起");
					return;
				}
				if(endTime ==null || endTime==""){
					abc.layerAlert(false, "请选择查询日期止");
					return;
				}
				if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
					if(CompareDate(startTime,endTime)){
						abc.layerAlert(false, "结束时间不能小于开始时间");
						return;
					}
				}
				var type = $("#type").val();
				var html = "";
				abc.block();
				$.ajax({
					type: "GET",
					url: ctx+"/taxcompstatis/complogin/listJson.php?start="+startTime+"&&end="+endTime+"&&type="+type,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						$("#registeruser").html("");
						$("#tjday").html("");
						if(type == "all"){
							html+= "<tr><td>1</td><td>"+startTime+"~"+endTime+"</td><td>电子申报</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/taxcompstatis/compdetail/userlook.php?doType=2&type=dzsb&timeInterval="+data.timeInterval+"'>"+data.dzsb +"</a></td></tr>";
							html+= "<tr><td>2</td><td>"+startTime+"~"+endTime+"</td><td>湖南国税</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/taxcompstatis/compdetail/userlook.php?doType=2&type=hngs&timeInterval="+data.timeInterval+"'>"+data.hngs +"</a></td></tr>";
							html+= "<tr><td>3</td><td>"+startTime+"~"+endTime+"</td><td>湖南地税</td><td><a class='opendialog ljc_00bcd4' href='javascript:void (0)' data-url='/taxcompstatis/compdetail/userlook.php?doType=2&type=hnds&timeInterval="+data.timeInterval+"'>"+data.hnds +"</a></td></tr>";
							var line3 = [['电子申报', parseInt(data.dzsb)], ['湖南国税', parseInt(data.hngs)], ['湖南地税', parseInt(data.hnds)]];
							$.jqplot("tjday", [line3], {
								title: "企业登录情况统计",
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
										labelRenderer: $.jqplot.CanvasAxisLabelRenderer
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
							$.each(data.compLoginStatisRs, function (i, item) {
								html+= "<tr><td width='4%'>"+(i+1)+"</td>";
								html+= "<td>"+item.month +"</td>";
								html+= "<td>"+getType(item.type) +"</td>";
								html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/taxcompstatis/compdetail/userlook.php?doType=2&type="+item.type+"&timeInterval="+item.timeInterval+"'>"+item.bindCount +"</a></td>";
								html+= '</tr>';
							});
							$.jqplot("tjday", [eval(data.tjday)], {
								title: "企业登录情况统计",
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
										labelRenderer: $.jqplot.CanvasAxisLabelRenderer
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
					},
					error: function () {
					}
				});
			});

			$("button[data-dismi]").click(function(){
				$("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
					$("#myModal3").hide();
				});
			});

			function getType(type){
				if(type == "dzsb"){
					return "电子申报";
				}
				else if(type == "hngs"){
					return "湖南国税";
				}
				else if(type == "hnds"){
					return "湖南地税";
				}
			}
			function CompareDate(d1,d2)
			{
				return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
			}
		});
	})
});