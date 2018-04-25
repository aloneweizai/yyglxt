require(["../../config"], function () {
    require(["jquery.full","../abc/consumer/page"], function ($) {
       $(function () {
    	    $("a[id='settings']").click(function(){
    	    	 var _id=$(this).attr('data-id');
				var _code = $(this).attr('data-code');
    	    	 $("#vipprivilegelevelname").html($(this).attr('data-name'));
    	    	 $("#vipprivilegelevelid").val(_id);
				$("#vipprivilegelevelcode").val(_code);
    	    	 $.ajax({
	                 url:ctx+"/consumerManager/vipprivilegelevel/"+_code,
	                 dataType: 'json',
	                 success: function (data) {
						 if(data && data.code == "2000") {
							 var data = data.dataList;
							 for (var i = 0; i < data.length; i++) {
								 var _data = data[i];
								 $("#mytb").find('tr').each(function () {
									 var _t = $(this);
									 var _tr = _t.find('#levelId');
									 var _val = _tr.val();
									 if (_val == _data.levelId) {
										 _t.find("#status").prop('checked', true);
										 _t.find("input[id^='val1']").val(_data.val1).blur().removeAttr('readonly');
										 _t.find("input[id^='val2']").val(_data.val2).blur().removeAttr('readonly');
										 _t.find("input[id^='val3']").val(_data.val3).blur().removeAttr('readonly');
										 _t.find("input[id^='val4']").val(_data.val4).blur().removeAttr('readonly');
										 _t.find("#description").val(_data.description).removeAttr('readonly');
									 }
								 });
								 //_tr.find("#levelId").val(_data.levelId);

							 }
						 }
						 else{
							 abc.layerAlert(false, data.message);
						 }
	                	$("#myModal").show();
	             	    $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(30)},300);
	                 }
	             });
    	    });
    	    
    	    $("[data-dismiss='modal']").click(function(){
         	   $("#myModal").find(".modal-dialog").animate({'top':'-500px'},300,function(){
         	    	$("#myModal").hide();
         	    	$("#mytb").find("input").each(function(){
       	    		   if($(this).attr('type')=='checkbox'){
       	    			 $(this).prop("checked",false)
       	    		   }else if($(this).attr('type')=='text'){
       	    			 $(this).val('').attr('readonly','readonly');
       	    		   }
       	    	    });
       	    	    $("#mytb").find("textarea").each(function(){
       	    		   $(this).val('').attr('readonly','readonly');
       	    	    });
         	   });
            });
    	    
    	    $("input[id='status']").click(function(){
    	    	 if(!($(this).is(':checked'))){
    	    		  $(this).parent().parent().find("input").each(function(){
         	    		   if($(this).attr('type')=='checkbox'){
         	    			 $(this).prop("checked",false)
         	    		   }else{
         	    			 $(this).val('').attr('readonly','readonly');
         	    		   }
         	    	  });
    	    		  $(this).parent().parent().find("textarea").each(function(){
         	    		 $(this).val('').attr('readonly','readonly');
         	    	  });
    	    	 }else{
    	    		 var _tr=$(this).parent().parent();
    	    		 $(this).parent().parent().find("input").each(function(){
       	    		    if($(this).attr('type')=='checkbox'){
       	    			  $(this).prop("checked",true)
       	    		    }else{
       	    			 $(this).removeAttr('readonly','readonly');
       	    		    }
       	    	     });
    	    		 $(this).parent().parent().find("textarea").each(function(){
         	    		 $(this).removeAttr('readonly');
         	    	 });
    	    	 }
    	    });
    	    
    	    var $validatorWsysVoFrom = $("#vipPrivilegeForm").validator({
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
    	    		  if(_tr.find("#status").is(':checked')){
    	    			  var p={};
    	    			  p['levelId']=_tr.find("#levelId").val();
    	    			  p['privilegeId']=$("#vipprivilegelevelcode").val();
    	    			  p['status']=true;
    	    			  p['val1']=_tr.find("input[id^='val1']").val();
    	    			  p['val2']=_tr.find("input[id^='val2']").val();
    	    			  p['val3']=_tr.find("input[id^='val3']").val();
    	    			  p['val4']=_tr.find("input[id^='val4']").val();
    	    			  p['description']=_tr.find("#description").val();
    	    			  list.push(p);
    	    		  } 
    	    	 });
    	    	 abc.layerAjaxConfirm("POST", ctx+'/consumerManager/vipprivilegelevel/save/'+$("#vipprivilegelevelcode").val(),  JSON.stringify(list), window.location.href);
    	      }
    	    });
       });
    })
});