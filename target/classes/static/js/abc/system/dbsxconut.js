require(["../../config"], function () {
    require(["jquery.full","abc.ajaxfileupload","../abc/util/date","highlighter.full","categoryAxisRenderer","barRenderer","pointLabels.min"], function ($) {
		$('.tj_statis_n>div').eq(0).show();
      $(function () {
		  $.ajax({
			  type: "GET",
			  url: ctx+"/dbsxgl/listJson.php",
			  async: false,
			  contentType: "application/json",
			  dataType: "JSON",
			  success: function (data) {
				  $("#tjday").html("");
				  var line3 = [['待付款订单数', parseInt(data.orderStatus2)], ['付款成功订单数', parseInt(data.orderStatus4)],
					  ['订单完成订单数', parseInt(data.orderStatus6)], ['订单作废订单数', parseInt(data.orderStatus7)], ['已退款订单数', parseInt(data.orderStatus9)]];

				  $.jqplot("tjday", [line3], {
					  title: "订单统计表",
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
    	  $("#consumer_query").click(function(){
    		  abc.block();
 			  $('#consumerListForm').submit();
     	  });
		  //当前时间
		  function getcurrentDate(){
			  // 拼接时间
			  var time1 = new Date()
			  //time1.setTime(time1.getTime() - (24 * 60 * 60 * 1000))
			  var Y1 = time1.getFullYear()
			  var M1 = ((time1.getMonth() + 1) > 10 ? (time1.getMonth() + 1) : '0' + (time1.getMonth() + 1))
			  var D1 = (time1.getDate() > 10 ? time1.getDate() : '0' + time1.getDate())
			  var timer1 = Y1 + '-' + M1 + '-' + D1 // 当前时间
			  return timer1;
		  }

    	  $(".fl").click(function(){
			  var _id = $(this).data('id');
			  var url;
			  var $parentTabNode = parent.$('#tt');
			  var exist_tab;
			  if(_id == '1'){
				 url = ctx+"/consumerManager/trueName_list.php?validStatus=1";
				 exist_tab = $parentTabNode.tabs('getTab', "实名认证审核");
				 if (exist_tab) {
					 $parentTabNode.tabs('close', "实名认证审核");
				 }
				 parent.addPanel("实名认证审核", url, "1200");
			 }
			  else if(_id == '2'){
				  url = ctx+"/financed/orderList.php?doType=1&dateType=1";
				  exist_tab = $parentTabNode.tabs('getTab', "待处理订单");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "待处理订单");
				  }
				  parent.addPanel("待处理订单", url, "1200");
			  }
			 else if(_id == '3'){
				  url = ctx+"/orderchange/applications.php";
				  exist_tab = $parentTabNode.tabs('getTab', "退换货申请");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "退换货申请");
				  }
				  parent.addPanel("退换货申请", url, "1200");
			 }
			 else if(_id == '4'){
				  url = ctx+"/orderchange/cw/applications.php";
				  exist_tab = $parentTabNode.tabs('getTab', "退款申请");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "退款申请");
				  }
				  parent.addPanel("退款申请", url, "1200");
			 }
			 else if(_id == '5'){
				  url = ctx+"/financed/invoiceList.php";
				  exist_tab = $parentTabNode.tabs('getTab', "发票寄送");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "发票寄送");
				  }
				  parent.addPanel("发票寄送", url, "1200");
			 }
			 else if(_id == '6'){
				  url = ctx+"/financed/invoiceLyList.php";
				  exist_tab = $parentTabNode.tabs('getTab', "发票领用");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "发票领用");
				  }
				  parent.addPanel("发票领用", url, "1200");
			 }
			  else if(_id == '7'){
				  url = ctx+"/bangbang/contentAudit/list.php?status=1";
				  exist_tab = $parentTabNode.tabs('getTab', "问答内容审核");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "问答内容审核");
				  }
				  parent.addPanel("问答内容审核", url, "1200");
			  }
			  else if(_id == '8'){
				  url = ctx+"/bangbang/contentAudit/list.php?initModule=tipOff&status=auditing";
				  exist_tab = $parentTabNode.tabs('getTab', "问答内容审核");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "问答内容审核");
				  }
				  parent.addPanel("问答内容审核", url, "1200");
			  }
			  else if(_id == '9'){
				  url = ctx+"/cms/feedback/list.php?isReply=false";
				  exist_tab = $parentTabNode.tabs('getTab', "用户意见反馈管理");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "用户意见反馈管理");
				  }
				  parent.addPanel("用户意见反馈管理", url, "1200");
			  }

			  else if(_id == '10'||_id == '11'){
				  url = ctx+"/cms/courseBrowseWatch/list.php?initModule=day";
				  exist_tab = $parentTabNode.tabs('getTab', "课程浏览观看数(日期)");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "课程浏览观看数(日期)");
				  }
				  parent.addPanel("课程浏览观看数(日期)", url, "1200");
			  }
			  else if(_id == '12'||_id == '13'){
				  url = ctx+"/cms/courseBrowseWatch/list.php?initModule=month";
				  exist_tab = $parentTabNode.tabs('getTab', "课程浏览观看数(月份)");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "课程浏览观看数(月份)");
				  }
				  parent.addPanel("课程浏览观看数(月份)", url, "1200");
			  }
			  else if(_id == '14'||_id == '15'){
				  var start;
				  var end;
				  var datetype;
				  var startStop = abc.getCurrentMonth();
				  if(_id=='14'){
					  datetype = 1;
					  start = getcurrentDate();
					  end = getcurrentDate();
				  }
				  else if(_id=='15'){
					  datetype = 5 ;
					  start = startStop[0];
					  end = startStop[1];
				  }
				  url = ctx+"/consumerManager/consumer/list.php?&&datetype="+datetype+"&&startDate="+start+"&&endDate="+end;
				  exist_tab = $parentTabNode.tabs('getTab', "注册用户管理");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "注册用户管理");
				  }
				  parent.addPanel("注册用户管理", url, "1200");
			  }
			  else if(_id == '18'){
				  url = ctx+"/vipgift/applylist.php?status=1";
				  exist_tab = $parentTabNode.tabs('getTab', "会员礼包申请");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "会员礼包申请");
				  }
				  parent.addPanel("会员礼包申请", url, "1200");
			  }
			  else if(_id == '19'){
				  url = ctx+"/vipgift/applylist.php?status=2";
				  exist_tab = $parentTabNode.tabs('getTab', "会员礼包申请");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "会员礼包申请");
				  }
				  parent.addPanel("会员礼包申请", url, "1200");
			  }
    	  });

		  $("a[data-type='lookdialog']").click(function(){
			  var status = $(this).attr("data-status");
			  var url ="";
			  var $parentTabNode = parent.$('#tt');
			  var exist_tab;
				  url = ctx+"/financed/orderList.php?doType=0&orderStatus="+status;
				  exist_tab = $parentTabNode.tabs('getTab', "订单查询");
				  if (exist_tab) {
					  $parentTabNode.tabs('close', "订单查询");
				  }
				  parent.addPanel("订单查询", url, "1200");
			 /* abc.block();
			  var url=$(this).attr("data-url");
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
*/
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