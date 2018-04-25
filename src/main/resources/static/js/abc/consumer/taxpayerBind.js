require(["../../config"], function () {
    require(["jquery.full","../abc/util/date"], function ($) {
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
			 var username = $("input[name='username']");
			 var nsrsbh = $("input[name='nsrsbh']");
			 if(username != null && nsrsbh != null){
				 if((username.val() == null||username.val() == "")&&(nsrsbh.val() == null||nsrsbh.val() == "")){
					 abc.layerAlert(false, "请输入用户名或者纳税人识别号任意一个！");
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
