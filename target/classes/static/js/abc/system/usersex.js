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
					url: ctx+"/userimganalyze/usersex/listJson.php?startTime="+startTime+"&&endTime="+endTime,
					async: true,
					contentType: "application/json",
					dataType: "JSON",
					success: function (data) {
						abc.unblock();
						$("#registeruser").html("");
						if(data.userSexStatisRs != "" && data.userSexStatisRs != null){
						document.getElementById('sextable').style.width = 500;
						document.getElementById('sextable').style.height = 200;
						document.getElementById('sextable').style.marginLeft = 80;
						$.each(data.userSexStatisRs, function (i, item) {
							html+= "<tr><td width='4%'>"+(i+1)+"</td>";
							html+= "<td>"+getSex(item.sexGroup) +"</td>";
							if("0" == item.sexGroup ||"1" == item.sexGroup){
								html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=3&sex="+item.sexGroup+"&startTime="+startTime+"&endTime="+endTime+"'>"+item.count +"</a></td>";
							}
							else{
								html+= "<td><a class='opendialog ljc_00bcd4' href='javascript:void(0)' data-url='/userimganalyze/userdetail/userlook.php?doType=3&&startTime="+startTime+"&endTime="+endTime+"'>"+item.count +"</a></td>";
							}
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
						document.getElementById('tjday').style.display = "block";
						var line3 = [['女', parseInt(data.wNum)], ['男', parseInt(data.mNum)], ['未知', parseInt(data.uNum)]];
						$.jqplot('tjday', [line3], {
							title:{         // 标题属性
								text:'<div class="chart-title">用户性别分布统计<div>',// 标题文本
								show:true,              // 是否阴影
								fontFamily:'微软雅黑',  // 标题字体
								fontSize:14,            // 标题字体大小
								textAlign:'center',       // 标题对齐方式
								textColor:'#515151',    // 标题颜色（也能够写作属性color）
								escapeHtml:false        // 是否转义HTML字符。值为false时。能够在text属性中使用HTML代码
							},
							seriesDefaults:{
								renderer:$.jqplot.PieRenderer,
								rendererOptions:{
									diameter: 200,
									padding: 20,
									showDataLabels:true
								},
								pointLabels: {  // 显示数据点。依赖于jqplot.pointLabels.min.js文件
									show: true
								}
							},
							grid:{
								drawBorder:false,
								shadow:false,
								background:'transparent'
							},
							legend:{        // 图例属性
								show:true,
								placement: 'outside', // 设置图例位于图表外部
								xoffset: 12, // 分类名称框距图表区域上边框的距离（单位px）
								yoffset: 12 // 分类名称框距图表区域左边框的距离(单位px)
							}
						});
						}
						else{
							document.getElementById('sextable').style.width = 1200;
							document.getElementById('sextable').style.height = 80;
							document.getElementById('sextable').style.marginLeft = 0;
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

			function getSex(sex){
				if("0" == sex){
					return "女"
				}
				else if("1" == sex){
					return "男"
				}
				else{
					return "未知"
				}
			}

			function CompareDate(d1,d2)
			{
				return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
			}

		});
	})
});