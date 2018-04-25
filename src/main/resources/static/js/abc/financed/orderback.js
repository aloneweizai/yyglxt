require(["../../config"], function () {
    require(["jquery.full"], function ($) {
       $(function () {
    	   $("#application_ok").click(function(){
    		   $("#myModal").show();
        	   $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(100)},500);
               $("#refuseRson").focus();
    	   });
    	   
    	   $("button[data-dismiss='modal']").click(function(){
        	   $("#myModal").find(".modal-dialog").animate({'top':'-400px'},500,function(){
        	    	$("#myModal").hide();
        	   });
           });
    	   var $validatorWsysVoFrom = $("#orderbackCharge").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
           $validatorWsysVoFrom.validator().trigger("showtip");
           
    	   $("button[data-save='modal']").click(function(){
    		   if($validatorWsysVoFrom.isValid()){  
    			   
    		   }
    	   });
       });
    })
});