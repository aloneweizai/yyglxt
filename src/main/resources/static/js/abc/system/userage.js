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
					url: ctx+"/userimganalyze/userage/listJson.php?startTime="+startTime+"&&endTime="+endTime,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						$("#registeruser").html("");
						$.each(data.userAgeStatisRs, function (i, item) {
							if("未知" != item.ageGroup){
								var startNum = getNum(item.ageGroup)[0];
								var endNum = getNum(item.ageGroup)[1];
							}
							else{
								var startNum = "";
								var endNum = "";
							}
							html+= "<tr><td width='4%'>"+(i+1)+"</td>";
							html+= "<td>"+getage(item.ageGroup) +"</td>";
							html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=2&startNum="+startNum+"&endNum="+endNum+"&startTime="+startTime+"&endTime="+endTime+"'>"+item.count +"</a></td>";
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
						document.getElementById('userlook').style.display = "block";
						$.jqplot("tjday", [eval(data.tjday)], {
							title: "用户年龄分布统计",
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
						abc.unblock();
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

			function getNum(num){
					var strSeparator = "-"; //日期分隔符
					var startStop= num.split(strSeparator);
					return startStop ;
			}

			function getage(age){
				if("未知"!= age){
					return age+"周岁";
				}
				else{
					return age;
				}
			}

			function CompareDate(d1,d2)
			{
				return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
			}
		});
	})
});