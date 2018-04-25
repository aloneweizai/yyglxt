require(["../../config"], function () {
    require(["jquery.full","select2.full"], function ($) {
      $(function () {
    	  $("#consumer_show").click(function(){
			  var _appId=$("#appId").val();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/app/unsetlist.php?appId="+_appId,
				  async: false,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  $("#apiName").empty();
					  allApi={};
					  for(var i=0;i<data.length;i++){
						  if(!(allApi[data[i].dictId])){
							  allApi[data[i].dictId]=new Array();
						  }
						  allApi[data[i].dictId].push(data[i]);
						  $("#apiName").append("<option data-uri='"+data[i].uri+"' data-method='"+data[i].method+"' value=\""+data[i].id+"\">"+data[i].name+"("+data[i].uri+")"+"</option>");
					  }
					  $("#apiName").select2();
					  var _op=$("#apiName").find("option:selected");
					  $("#appuri").html(_op.attr('data-uri'));
					  $("#appmethod").html(_op.attr('data-method'));
				  }
			  });
			  $("#myModal").show();
     		  $("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(50)},500);
   	      });
          
    	  $("button[data-dismiss]").click(function(){
    		  $("#myModal").find(".modal-dialog").animate({'top':'-600px'},500,function(){
        	    	$("#myModal").hide();
        	  })
          });
    	  
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
         	 abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
          });
     	 
     	 
     	 var goPage= function(index){
     		 if(isNaN($("#consumer_size").val())){
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
 			 $('#consumerListForm').submit();
 		 }
    	  
    	  $("a[data-type='delSig']").click(function(){
        	 //abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val());
        	 abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
          });
    	  
    	  var allApi={};

		  $("#dictId").change(function(){
			  $("#apiName").empty();
			  var _val=$(this).val();
			  if(_val!=""){
				  var _a=allApi[_val];
				  if(_a !== undefined){
					  for(var i=0;i<_a.length;i++){
						  $("#apiName").append("<option data-uri='"+_a[i].uri+"' data-method='"+_a[i].method+"' value=\""+_a[i].id+"\">"+_a[i].name+"("+_a[i].uri+")"+"</option>");
					  }
				  }
				  else{
					  $("#apiName").append("<option></option>");
				  }

			  }else{
				  for(var _p in allApi){
					  for(var i=0;i<allApi[_p].length;i++){
						  $("#apiName").append("<option data-uri='"+(allApi[_p])[i].uri+"' data-method='"+(allApi[_p])[i].method+"' value=\""+(allApi[_p])[i].id+"\">"+(allApi[_p])[i].name+"("+(allApi[_p])[i].uri+")"+"</option>");
					  }
				  }
			  }
		  });
		  $("#apiName").change(function(){
			  var _val=$(this).val();
			  if(_val!=""){
				  var _op=$(this).find("option:selected");
				  $("#appuri").html(_op.attr('data-uri'));
				  $("#appmethod").html(_op.attr('data-method'));
			  }else{
				  $("#appuri").empty();
				  $("#appmethod").empty();
			  }
		  });

    	  $(document).ready(function(){
    		   var _appId=$("#appId").val();
    		   /*$.ajax({
                   type: "GET",
                   url: ctx+"/app/unsetlist.php?appId="+_appId,
                   async: false,
                   contentType: "application/json",
                   dataType: "JSON",
                   success: function (data) {
					   $("#apiName").empty();
					   allApi={};
                      for(var i=0;i<data.length;i++){
                    	  if(!(allApi[data[i].dictId])){
                    		  allApi[data[i].dictId]=new Array();
                    	  }
                    	  allApi[data[i].dictId].push(data[i]);
                    	  $("#apiName").append("<option data-uri='"+data[i].uri+"' data-method='"+data[i].method+"' value=\""+data[i].id+"\">"+data[i].name+"</option>");
                      }
                      $("#apiName").select2();
                      var _op=$("#apiName").find("option:selected");
   				      $("#appuri").html(_op.attr('data-uri'));
   				      $("#appmethod").html(_op.attr('data-method'));
                   }
               }); */
    		   
    		   /*$("#dictId").change(function(){
				   $("#apiName").length;
				   alert(1);
    			    $("#apiName").empty();
    	    	    var _val=$(this).val();
    	    		if(_val!=""){
    	    			var _a=allApi[_val];
    	    			for(var i=0;i<_a.length;i++){
                      	  $("#apiName").append("<option data-uri='"+_a[i].uri+"' data-method='"+_a[i].method+"' value=\""+_a[i].id+"\">"+_a[i].name+"</option>");
                        }
    	    		}else{
    	    			for(var _p in allApi){
    	    				for(var i=0;i<allApi[_p].length;i++){
    	                      	  $("#apiName").append("<option data-uri='"+(allApi[_p])[i].uri+"' data-method='"+(allApi[_p])[i].method+"' value=\""+(allApi[_p])[i].id+"\">"+(allApi[_p])[i].name+"</option>");
    	                    }
    	    			}
    	    		}
    	       });
    		   $("#apiName").change(function(){
    			   var _val=$(this).val();
    			   if(_val!=""){
    				   var _op=$(this).find("option:selected");
    				   $("#appuri").html(_op.attr('data-uri'));
    				   $("#appmethod").html(_op.attr('data-method'));
    			   }else{
    				   $("#appuri").empty();
    				   $("#appmethod").empty();
    			   }
    		   });*/
    		   
    		   var $validatorWsysVoFrom = $("form[data-setting]").validator({
 	    		  timely: 1,
 	    		  focusCleanup:true,
 	              theme: 'yellow_right_effect',
 	              rules: {
 	            	  allint:[/^(-|\+)?\d+$/, '请输入整数']
 	              }
 	           });
 	           $validatorWsysVoFrom.validator().trigger("showtip");
    		   
    		   $("button[data-save]").click(function(){
    			   if($validatorWsysVoFrom.isValid()){
    				   abc.layerAjaxConfirm("POST", $("form[data-setting]").attr('action'), $("form[data-setting]").serializeJson(), $("form[data-setting]").attr('next-url'));
    			   }
    		   });
    	  }); 
    	  
       });
    })
});