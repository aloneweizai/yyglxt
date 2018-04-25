require(["../../config"], function () {
    require(["jquery.full","../abc/consumer/page"], function ($) {
       $(function () {
    	    
    	   $("a[id='settings']").click(function(){
 	    	    var _id=$(this).attr('data-id');
 	    	    $("#expruleName").html($(this).attr('data-name'));
 	    	    $("#expruleId").val(_id);
 	    	    $.ajax({
	                 url:ctx+"/consumerManager/expcodex/"+_id,
	                 dataType: 'json',
	                 success: function (data) {
	                	for(var i=0;i<data.length;i++){
	                		var _data=data[i];
	                    	var _tr=$("#mytb").find('#'+_data.clientType);
	                    	_tr.find("input[id^='status']").prop('checked',true);
	                    	_tr.find("input[id^='uexp']").val(_data.uexp).removeAttr('readonly');
	                    	_tr.find("select[id^='period']").val(_data.period).removeAttr('disabled');
	                    	_tr.find("input[id^='degree']").val(_data.degree).removeAttr('readonly');
	                    	_tr.find("textarea").val(_data.remark).removeAttr('readonly');
	                    }
	                	$("#myModal").show();
	             	    $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(30)},300);
	                 }
	             });
 	    	   $("#myModal").show();
        	   $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(30)},300);
	  	    });
    	   
    	   
    	    $("[data-dismiss='modal']").click(function(){
	       	   $("#myModal").find(".modal-dialog").animate({'top':'-500px'},300,function(){
	       	    	$("#myModal").hide();
	       	    	$("#mytb").find("input").each(function(){
	     	    		if($(this).attr('type')=='checkbox'){
	     	    			 $(this).prop("checked",false)
	     	    		}else if($(this).attr('type')=='text'){
	     	    			 $(this).val('').attr('readonly','readonly').blur();
	     	    		}
	     	    	});
	     	    	$("#mytb").find("textarea").each(function(){
	     	    		 $(this).val('').attr('readonly','readonly').blur();
	     	    	});
	     	    	$("#mytb").find("select").each(function(){
	      	    		 $(this).val('A').attr('disabled','disabled');
	      	    	});
	       	    });
	        });
    	    
    	    $("input[id^='status']").click(function(){
	 	    	 if(!($(this).is(':checked'))){
	 	    		  $(this).parent().parent().find("input").each(function(){
	      	    		   if($(this).attr('type')=='checkbox'){
	      	    			 $(this).prop("checked",false)
	      	    		   }else{
	      	    			 $(this).val('').attr('readonly','readonly').blur();
	      	    		   }
	      	    	  });
	 	    		  $(this).parent().parent().find("textarea").each(function(){
	      	    		 $(this).val('').attr('readonly','readonly').blur();
	      	    	  });
	 	    		  $(this).parent().parent().find("select").each(function(){
	      	    		 $(this).val('A').attr('disabled','disabled');
	      	    	  });
	 	    	 }else{
	 	    		 var _tr=$(this).parent().parent();
	 	    		_tr.find("input").each(function(){
      	    		    if($(this).attr('type')=='checkbox'){
      	    			  $(this).prop("checked",true)
      	    		    }else{
      	    			 $(this).removeAttr('readonly');
      	    		    }
      	    	     });
	 	    		_tr.find("textarea").each(function(){
        	    		 $(this).removeAttr('readonly');
        	    	 });
	 	    		_tr.find("select").each(function(){
	      	    		 $(this).removeAttr('disabled');
	      	    	 });
	 	    	 }
	 	    });
    	    
    	    var $validatorWsysVoFrom = $("#ExpCodexForm").validator({
	      		  timely: 1,
	      		  focusCleanup:true,
	                theme: 'yellow_bottom',
	                rules: {
	              	  no_CN:[/^[^\u4e00-\u9fa5]{0,}$/, '输入非法字符']
	                }
	        });
	        $validatorWsysVoFrom.validator().trigger("showtip"); 
	        $("[data-dismiss='save']").click(function(){
			  	 if($validatorWsysVoFrom.isValid()){
			  		 var _tb=$("#mytb");
		   	    	 var list=[];
		   	    	 _tb.find('tr').each(function(){
		   	    		  var _tr=$(this);
		   	    		  if(_tr.find("input[id^='status']").is(':checked')){
		   	    			 var p={uexpruleId:$("#expruleId").val(),clientType:_tr.attr('id')};
		   	    			 p['uexp']=_tr.find("input[id^='uexp']").val();
		   	    			 p['period']=_tr.find("select[id^='period']").val();
		   	    			 p['degree']=_tr.find("input[id^='degree']").val();
		   	    			 p['remark']=_tr.find("textarea").val();
		   	    			 list.push(p);
		   	    		  } 
		   	    	 });
		   	    	abc.layerAjaxConfirm("POST", ctx+'/consumerManager/expcodex/save/'+$("#expruleId").val(),  JSON.stringify(list), ctx+'/consumerManager/exprule/list.php');
		   	     }
			}); 
    	   
       });
    })
});