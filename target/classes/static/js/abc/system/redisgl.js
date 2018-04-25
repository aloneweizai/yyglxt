require(["../../config"], function () {
    require(["jquery.full","clipboard"], function ($,Clipboard) {
      $(function () {
		  //window.ZeroClipboard = zeroClipboard;
         //查询
    	 $("#consumer_query").click(function(){
			 var pattern = $("#pattern").val();
			 if(pattern ==null || pattern==""){
				 abc.layerAlert(false, "请输入正则表达式！");
				 return;
			 }
			 abc.block();
			 $('#consumerListForm').submit();
    	 });

		  $("a[data-type='detele']").on('click',function (){
			  var url=$(this).attr("data-url");
			  var key = $(this).attr("data-id");
			  var msg;
			  $.ajax({
				  type: "GET",
				  url: ctx+"/system/redis/selectone/"+key,
				  async: true,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  msg = "该redis键值对，key:"+key+",value:"+data.redisGlQueryRs+",是否确认删除？<span id='fzredis' data-toggle='tooltip' data-placement='right' title='已复制'class='fzredis' style='color: blue'>复制</span>",
					  layer.confirm(msg, {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
						  function(){
							  abc.block();
							  $.ajax({
								  type:"POST",
								  url: url ,
								  success: function (data){
									  if(data.result.code=='2000'){
										  layer.msg("删除成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
										  setTimeout(function () {
											  location.reload();
										  }, 2000);
									  }else{
										  layer.msg(data.result.message, {icon: 5});
									  }
								  } ,
								  dataType: "JSON"
							  });
						  }
					  );

					  $(".fzredis").click(
						  function() {
							  var clipboard = new Clipboard('.fzredis', {
								  text: function() {
									  return data.redisxx;
								  }
							  });
							  clipboard.on('success', function(e) {
								  $('[data-toggle="tooltip"]').tooltip();
							  });

							  clipboard.on('error', function(e) {
								  alert("异常错误： " + JSON.stringify(e.message));
								  console.log(e);
							  });
						  });
				  },
				  error: function () {
				  }
			  });
		  });

		  //a删除
		  $("a[data-type='delSig']").click(function(){
			  var msg = $(this).attr("data-confirm");
			  var url = $(this).attr("data-href");
			  layer.confirm(msg, {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
				  function(){
					  abc.block();
					  $.ajax({
						  type:"POST",
						  url: url ,
						  success: function (data){
							  if(data.result.code=='2000'){
								  layer.msg("清空redis数据库成功!", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
								  setTimeout(function () {
									  location.reload();
								  }, 2000);
							  }else{
								  bc.layerAlert(false, "清空redis数据库失败:"+data.message);
							  }
						  } ,
						  dataType: "JSON"
					  });
				  }
			  );
		  });

	  });
    })
});
