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
			$('#tagnames').tagsInput({
				width:'400px',
				height:'45px',
				interactive:false,
				defaultText:'标签'
			});

			$("#tagslect").click(function(){
				$("#myModal").show();
				$("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(50)},500);
			});

			$("#tagdelall").click(function(){
				$('#tagnames').clearALL();
			});

			$("a[data-type='unselect']").click(function(){
				var _name=$(this).attr('data-id');
				if (!$('#tagnames').tagExist(_name)){
					$('#tagnames').addTag(_name);
				}
			});

			$("#seletablib").change(function(){
				var _v=$(this).val();
				if(_v==""){
					$(".itemWraper1").css('display','block');
				}else{
					$(".itemWraper1").css('display','none');
					$("#tag_"+_v).css('display','block');
				}
			});

			$("button[data-dismiss]").click(function(){
				$("#myModal").find(".modal-dialog").animate({'top':'-600px'},500,function(){
					$("#myModal").hide();
				})
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
				var type = $("#type").val();
				var areatype = "";
				var tagName = $('#tagnames').val();
				if(type == "country"){
					areatype = "province";
				}
				else{
					areatype = "city";
				}
				var html = "";
				abc.block();
				$.ajax({
					type: "GET",
					url: ctx+"/userimganalyze/userarea/listJson.php?start="+startTime+"&&end="+endTime+"&&type="+type+"&&tagName="+tagName,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						$("#registeruser").html("");
						if(data.userAreaStatisRs != "" && data.userAreaStatisRs != null){
							$.each(data.userAreaStatisRs, function (i, item) {
								html+= "<tr><td width='4%'>"+(i+1)+"</td>";
								html+= "<td>"+item.regionName +"</td>";
								html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=5&type="+areatype+"&startTime="+item.timeInterval+"&province="+item.regionName+"&tagName="+item.tagName+"'>"+item.allCount +"</a></td>";
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
							document.getElementById('tjday').style.display = "block";
							$("#tjday").html("");
							if(data.tjday != "" && data.tjday != null){
								$.jqplot("tjday", [eval(data.tjday)], {
									title: "用户区域分布统计",
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
							}
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