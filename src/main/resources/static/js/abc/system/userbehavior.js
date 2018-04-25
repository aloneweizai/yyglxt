require(["../../config"], function () {
	require(["jquery.full","jqplot.full","abc.common","tagEditor"], function ($) {
		$(function () {
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				laydate.render({
					elem: '#test30',
					max: yesterday(),//最小日期:昨天
					theme: '#14b9d5'
				});
				laydate.render({
					elem: '#test31',
					max: yesterday(),//最小日期:昨天
					theme: '#14b9d5'
				});
			});
			var behavior;
			var html = "";
			var plot;
			$("#consumer_query").click(function(){
				var startTime = $("#test30").val();
				var endTime = $("#test31").val();
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
					if(DateDiff(startTime,endTime)>7){
						abc.layerAlert(false, "查询时间起止间隔不超过7天");
						return;
					}
				}
				abc.block();
				$.ajax({
					type: "GET",
					url: ctx+"/userimganalyze/userbehavior/listJson.php?startTime="+startTime+"&endTime="+endTime+"&menu=用户行为",
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						$("#registeruser").html("");
						$("#tjday").html("");
						if(data.userBehaviorStatisRs != "" && data.userBehaviorStatisRs != null&&(data.message == "undefined" || data.message == null)){
							html = "";
							behavior = data.userBehaviorStatisRs;
							$.each(data.userBehaviorStatisRs, function (i, item) {
								html+= "<tr><td width='4%'>"+(i+1)+"</td>";
								html+= "<td>"+item.menu +"</td>";
								html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=6&menu="+item.menu+"&startTime="+startTime+"&endTime="+endTime+"'>"+item.amount +"</a></td>";
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
							document.getElementById('userlook').style.display = "block";
							plot = $.jqplot("tjday", [eval(data.tjday)], {
								title: "软件用户行为分析",
								seriesDefaults: {
									pointLabels: { show: true },
									shadow: false,
									showMarker: true,// 是否强调显示图中的数据节点
									renderer: $.jqplot.BarRenderer,
									rendererOptions: {
										showDataLabels: true
									}
								},
								axesDefaults: {
									pad: 1.5
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
							$("#registeruser").html("");
							$("#tjday").html("");
							document.getElementById('userlook').style.display = "none";
							$("#registeruser").append("<tr><td colspan='3'>--未查到相关信息，请重新统计--</td></tr>");
						}
					},
					error: function () {
					}
				});

				if(plot){
					$("#tjday").unbind();
					$("#tjday").empty();
					$('#tjday').bind('jqplotDataClick',
						function (ev, seriesIndex, pointIndex, data) {
							//$('#info2c').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data+ ', pageX: '+ev.pageX+', pageY: '+ev.pageY);
							gettjtab(startTime,endTime,behavior[pointIndex].menu);
						}
					);
				}
				else{
					$('#tjday').bind('jqplotDataClick',
						function (ev, seriesIndex, pointIndex, data) {
							//$('#info2c').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data+ ', pageX: '+ev.pageX+', pageY: '+ev.pageY);
							gettjtab(startTime,endTime,behavior[pointIndex].menu);
						}
					);
				}

			});

			function gettjtab(startTime,endTime,menu){
				$.ajax({
					type: "GET",
					url: ctx+"/userimganalyze/userbehavior/listJson.php?startTime="+startTime+"&endTime="+endTime+"&menu="+menu,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						if(data.userBehaviorStatisRs != "" && data.userBehaviorStatisRs != null&&(data.message == "undefined" || data.message == null)) {
							$("#registeruser").html("");
							html = "";
							behavior = data.userBehaviorStatisRs;
							$.each(data.userBehaviorStatisRs, function (i, item) {
								html += "<tr><td width='4%'>" + (i + 1) + "</td>";
								html += "<td>" + item.menu + "</td>";
								html += "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=6&menu=" + item.menu + "&startTime=" + startTime + "&endTime=" + endTime + "'>" + item.amount + "</a></td>";
								html += '</tr>';
							});
							$("#registeruser").append(html);
							$("#registeruser").on('click', '.opendialog',
								function () {
									abc.block();
									var url = ctx + $(this).attr("data-url");
									var iframe = document.getElementById("consumer_frame");
									iframe.src = url;
									if (iframe.attachEvent) {
										iframe.attachEvent("onload", function () {
											//$("#myModal3").fadeIn();
											$("#myModal3").show();
											$("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
											abc.unblock();
										});
									} else {
										iframe.onload = function () {
											//$("#myModal3").fadeIn();
											$("#myModal3").show();
											$("#myModal3").find(".modal-dialog").css('top', '-700px').animate({'top': abc.winscrollTop(50)}, 500);
											abc.unblock();
										};
									}

								});
							$("#tjday").html("");
							document.getElementById('userlook').style.display = "block";
							if (plot) {
								// plot.destory();
								//$("#tjday").unbind();
								$("#tjday").empty();
								plot= null;
							}
							plot = $.jqplot("tjday", [eval(data.tjday)], {
								title: "软件用户行为分析",
								seriesDefaults: {
									pointLabels: {show: true},
									shadow: false,
									showMarker: true,// 是否强调显示图中的数据节点
									renderer: $.jqplot.BarRenderer,
									rendererOptions: {
										showDataLabels: true
									}
								},
								axesDefaults: {
									pad: 1.2
								},
								axes: {
									xaxis: {
										renderer: $.jqplot.CategoryAxisRenderer,
										labelRenderer: $.jqplot.CanvasAxisLabelRenderer
									},
									yaxis: {
										showTicks: true,
										tickOptions: {
											show: true,
											showLabel: true,
											showMark: true,
											showGridline: true
										}
									}
								}
							});
						}
						else if(data.message != "" && data.message != null){
							abc.layerAlert(false, data.message);
						}
						else{
							html = "";
							$("#registeruser").html("");
							$("#tjday").html("");
							document.getElementById('userlook').style.display = "none";
							$("#registeruser").append("<tr><td colspan='3'>--未查到相关信息，请重新统计--</td></tr>");
						}
					},
					error: function () {
					}
				});
			}
			$("button[data-dismi]").click(function(){
				//$("#myModal3").fadeOut();
				$("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
					$("#myModal3").hide();
				});
			});

			$('#back').click(function () {
				var startTime = $("#test30").val();
				var endTime = $("#test31").val();
				gettjtab(startTime,endTime,"用户行为");
			});

			function CompareDate(d1,d2)
			{
				return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
			}

			function timeForMat (count) {
				var time2 = new Date()
				time2.setTime(time2.getTime() - (24 * 60 * 60 * 1000 * count))
				var Y2 = time2.getFullYear()
				var M2 = ((time2.getMonth() + 1) > 10 ? (time2.getMonth() + 1) : '0' + (time2.getMonth() + 1))
				var D2 = (time2.getDate() > 10 ? time2.getDate() : '0' + time2.getDate())
				var timer2 = Y2 + '-' + M2 + '-' + D2 // 之前的7天或者30天
				return timer2;

			}

			function yesterday () {
				// 校验是不是选择的昨天
				var timer = timeForMat(1)
				return timer
			}

			//计算天数差的函数，通用
			function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式
				var  aDate,  oDate1,  oDate2,  iDays
				aDate  =  sDate1.split("-")
				oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
				aDate  =  sDate2.split("-")
				oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
				iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
				return  iDays
			}

		});
	})
});