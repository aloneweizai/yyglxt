require(["../../config"], function () {
	require(["jquery.full","jqplot.full","abc.common","tagEditor"], function ($) {
		$(function () {
			layui.use('laydate', function() {
				var laydate = layui.laydate;
				laydate.render({
					elem: '#test6'
					,range: true
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
				var _val = $("#test6").val();
				var start = _val.substr(0,10);
				var end = _val.substr(12,23);
				var tagName = $('#tagnames').val();
				if(_val ==null || _val==""){
					abc.layerAlert(false, "请选择注册时间");
					return;
				}
				if(tagName ==null || tagName==""){
					abc.layerAlert(false, "请选择标签");
					return;
				}
				var html = "";
				abc.block();
				$.ajax({
					type: "GET",
					url: ctx+"/userimganalyze/usertag/listJson.php?start="+start+"&end="+end+"&tagName="+tagName,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						$("#registeruser").html("");
						$.each(data.userTagStatisRs, function (i, item) {
						 html+= "<tr><td width='4%'>"+(i+1)+"</td>";
						 html+= "<td>"+item.tagName +"</td>";
						 html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=1&tagName="+item.tagName+"&startTime="+start+"&endTime="+end+"'>"+item.count +"</a></td>";
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
						document.getElementById('usertag').style.display = "block";
						$.jqplot("tjday", [eval(data.tjday)], {
							title: "用户标签分析统计",
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

		});
	})
});