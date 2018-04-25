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
				var status = $("#status").val();
				if(status ==null || status==""){
					abc.layerAlert(false, "请选择业务类型");
					return;
				}
				var html = "";
				abc.block();
				$.ajax({
					type: "GET",
					url: ctx+"/taxcompstatis/compusing/listJson.php?startTime="+startTime+"&&endTime="+endTime+"&&menu="+status,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						$("#registeruser").html("");
						$("#tjday").html("");
						if(data.compUsingStatisRs != "" && data.compUsingStatisRs != null) {
							document.getElementById('userlook').style.display = "block";
							$.each(data.compUsingStatisRs, function (i, item) {
								html += "<tr><td width='4%'>" + (i + 1) + "</td>";
								html += "<td>" + startTime+"~"+endTime + "</td>";
								html += "<td>" + item.menu + "</td>";
								html += "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/taxcompstatis/compdetail/userlook.php?doType=3&menu=" + item.menu + "&startTime=" + startTime + "&endTime=" + endTime + "'>" + item.total + "</a></td>";
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
							$.jqplot("tjday", [eval(data.tjday)], {
								title: "企业使用情况统计",
								seriesDefaults: {
									pointLabels: {show: true},
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
						else{
							document.getElementById('userlook').style.display = "none";
							$("#registeruser").append("<tr><td colspan='4'>--未查到相关信息，请重新统计--</td></tr>");
						}
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

			function CompareDate(d1,d2)
			{
				return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
			}
		});
	})
});