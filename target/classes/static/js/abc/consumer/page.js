require(["../../config"], function () {
    require(["jquery.full","../abc/util/date"], function ($) {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			laydate.render({
				elem: '#test30',
				theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test31',
				theme: '#14b9d5'
			});
			//日期范围
			laydate.render({
				elem: '#test6'
				,range: true
				,theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test2'
				,type: 'year'
				,theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test3'
				,type: 'year'
				,theme: '#14b9d5'
			});
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
//日期时间选择器
			laydate.render({
				elem: '#test-limit1'
				,type: 'datetime'
				,min: getcurrentDate()
			});

		})
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
      $(function () {
          $("a[name='a1']").on("click", function () {
              var id =($(this).attr("data-id"));
              id += "&type=" + ($(this).attr("data-type"));
              modleShow(                  ctx +"/consumerManager/consumer/taxpayerBind_info.php" ,id);
          });
          function modleShow(url,id) {

              //在模态框的.moday-body位置异步加载url
              $(".modal-body").load(url + "?id=" + id, function() {//加载完成执行此

                  $("#model-ok").hide();
                  $("#modal-dialog").modal("show");
              })

          }
		  $("#consumer_more").click(function(){
			  if($(this).hasClass('shoqilai')){
				  $(this).val('更多条件').removeClass('shoqilai');
				  $(".moretjYC").each(function(){
					  $(this).removeClass('moretjYC').addClass('moretj').fadeOut(500);
				  });
			  }else{
				  $(this).val('隐藏条件').addClass('shoqilai');

				  $(".moretj").each(function(){
					  $(this).removeClass('moretj').addClass('moretjYC').fadeIn(500);
				  });
			  }
		  });
		  var hasYC=false;
		  $(".moretj").each(function(){
			  $(this).find('select').each(function(){
				  if($(this).val()!="" || $(this).val().length>0){
					  hasYC=true;
				  }
			  });
			  $(this).find('input[type="text"]').each(function(){
				  if($(this).val()!="" || $(this).val().length>0){
					  hasYC=true;
				  }
			  });
		  });
		  if(hasYC){$("#consumer_more").click();};

         //查询
    	 $("#consumer_query").click(function(){
    		 goPage(-1);
    	 }); 
    	 //每页大小
    	 $("#consumer_size").keypress(function(e) {
    		 if(e.which == 13) {
    			 goPage(-1);
    		 }
    	 });
    	 //首页
    	 $("#consumer_first").click(function(){
    		 goPage(1);
    	 });
    	 //前一页
    	 $("#consumer_up").click(function(){
    		 goPage(parseInt($("#cupageVal").val())-1);
    	 });
    	 //下一页
    	 $("#consumer_down").click(function(){
    		 goPage(parseInt($("#cupageVal").val())+1);
    	 });
    	 //最后一页
    	 $("#consumer_last").click(function(){
    		 goPage(parseInt($("#topageVal").val()));
    	 });
    	 //跳转
    	 $("#consumer_go").keypress(function(e) {
    		 if(e.which == 13) {
    			 goPage(isNaN($("#consumer_go").val())?1:$("#consumer_go").val());
    		 }
    	 });
    	 //跳转匡
    	 $("#consumer_gogo").click(function(e) {
    		goPage(parseInt(isNaN($("#consumer_go").val())?1:$("#consumer_go").val()));
    	 });


    	 //a删除
         $("a[data-type='delSig']").click(function(){
        	 //abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val());
        	 abc.ajaxConfirm("POST", encodeURI($(this).attr("data-href")), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
         });

		  $("[data-dismiss='lookModal']").click(function(){
			  $("#myModal3").fadeOut();
		  });

    	 var goPage= function(index){
    		 if(isNaN($("#consumer_size").val())
    				 ||$("#consumer_size").val()==''){
    			 $("#consumer_size").val(10);
    		 }
    		 var curtPage=parseInt($("#cupageVal").val());
    		 var totalPage=parseInt($("#topageVal").val());
			 if((index<1||index==curtPage||index>totalPage)&&index!=-1){
			    return;
			 }else if(index==-1){
			    index=1;
			 }
			 $('#cupageVal').val(index);
			 var beginTime = $("input[name='beginTime']").val();
			 var startTime = $("input[name='startTime']").val();
			 var endTime = $("input[name='endTime']").val();
			 var year = $("input[name='year']").val();
			 /*if(year ==null || year==""){
				 abc.layerAlert(false, "请选择查询日期");
				 return;
			 }*/
			 if((beginTime !=null && beginTime!="")&&(endTime !=null && endTime!="")){
				 if(CompareDate(beginTime,endTime)){
					 abc.layerAlert(false, "结束时间不能小于开始时间");
					 return;
				 }
			 }
			 if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
				 if(CompareDate(startTime,endTime)){
					 abc.layerAlert(false, "结束时间不能小于开始时间");
					 return;
				 }
			 }
			 var startDate = $("input[name='startDate']").val();
			 var endDate = $("input[name='endDate']").val();
			 if((startDate !=null && startDate!="")&&(endDate !=null && endDate!="")){
				 if(CompareDate(startDate,endDate)){
					 abc.layerAlert(false, "结束时间不能小于开始时间");
					 return;
				 }
			 }
			 var start = $("input[name='start']").val();
			 var end = $("input[name='end']").val();
			 if((start !=null && start!="")&&(end !=null && end!="")){
				 if(CompareDate(start,end)){
					 abc.layerAlert(false, "发送时间止不能小于发送时间起");
					 return;
				 }
			 }
			 abc.block();
			 $('#consumerListForm').submit();
		 }

    	 $("select[data-type='ajax']").each(function(){
       	  var this_=$(this);
       	  var rule=this_.attr('data-rules').split(":");
       	  var urls=this_.attr('data-url');
       	  var val=this_.attr('data-val');
       	  $.ajax({
                 type: "GET",
                 url: urls,
                 async: false,
                 contentType: "application/json",
                 dataType: "JSON",
                 success: function (data) {
                     if (data) {
                         $.each(data, function (i, item) {
                       	  this_.append("<option "+ (eval("item."+rule[0])==val?"selected":"") +" value='"+ eval("item."+rule[0])+"'>" + eval("item."+rule[1]) + "</option>");
                         });
                     } else {
                     }
                 }
             });
         });
		  $("a[data-type='opendialog']").click(function(){
				  $("#myModal1").fadeIn();
		  });
		  $("[data-dismiss='modal']").click(function(){
			  $("#myModal1").fadeOut();
		  });

		  $("a[data-type='lookdialog']").click(function(){
         	abc.block();
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
 			
 		});
         
         $("button[data-dismi]").click(function(){
 			 //$("#myModal3").fadeOut();
 			 $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
 	      	    	$("#myModal3").hide();
 	      	 });
 		});
		  $("input[data-type='downLoad']").click(function(){
			  var noMsg=$(this).attr('data-noMsg');
			  var qurl=$(this).attr('data-qurl');
			  var durl=$(this).attr('data-durl');
			  layer.confirm('是否导出？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
				  function (index) {
					  $.ajax({
						  url: qurl,
						  dataType: 'json',
						  success: function (data) {
							  if (data) {
								  layer.msg("导出成功", {
									  offset: abc.winscrollTop(200),
									  shade: 0.3,
									  icon: 1,
									  time: 1000
								  }, function () {
									  window.location.href = durl;
								  });
							  } else {
								  layer.msg(noMsg, {
									  offset: abc.winscrollTop(200),
									  shade: 0.3,
									  icon: 5,
									  time: 2000
								  });
							  }
						  }
					  });
				  }
			  );
		  });

		  $("#tjdownLoad").click(function(){
			  var noMsg=$(this).attr('data-noMsg');
			  var qurl=$(this).attr('data-qurl');
			  var durl=$(this).attr('data-durl');
			  var size = $("#consumer_size").val();
			  var page = $("#cupageVal").val();
			  layer.confirm('是否导出？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
				  function (index) {
					  $.ajax({
						  url: qurl+"&page="+page+"&size="+size,
						  dataType: 'json',
						  success: function (data) {
							  if (data) {
								  layer.msg("导出成功", {
									  offset: abc.winscrollTop(200),
									  shade: 0.3,
									  icon: 1,
									  time: 1000
								  }, function () {
									  window.location.href = durl+"&page="+page+"&size="+size;
								  });
							  } else {
								  layer.msg(noMsg, {
									  offset: abc.winscrollTop(200),
									  shade: 0.3,
									  icon: 5,
									  time: 2000
								  });
							  }
						  }
					  });
				  }
			  );
		  });
		  function CompareDate(d1,d2)
		  {
			  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  }

		  var $validatorWsysVoFrom = $("form").validator({
			  timely: 1,
			  focusCleanup:true,
			  theme: 'yellow_right_effect',
			  rules: {
				  allint:[/^(-|\+)?\d+$/, '请输入整数']
			  }
		  });
		  $validatorWsysVoFrom.validator().trigger("showtip");
		  $("#consumer_submit").click(function(){
			  if($validatorWsysVoFrom.isValid()){
				  abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), document.referrer);
			  }
		  });
	  });
    })
});
