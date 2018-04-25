require(["../../config"], function () {
    require(["jquery.full"], function ($) {
       $(function () {
    	   
    	  /* $(window.parent).scroll(function(){
    		   //
    		   $(".modal-dialog").css('top',abc.winscrollTop(50));
    	   });*/
    	   
    	   //退换货审批
    	   $("#application_ok").click(function(){
    		   $("#myModal").show();
        	   $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(50)},500);
    	   });
    	   
    	   $("button[data-dismiss='modal']").click(function(){
        	   $("#myModal").find(".modal-dialog").animate({'top':'-400px'},500,function(){
        	    	$("#myModal").hide();
        	   });
           });
    	   var $validatorWsysVoFrom1 = $("#orderbackCharge").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
           $validatorWsysVoFrom1.validator().trigger("showtip");
           
    	   $("button[data-save='modal']").click(function(){
    		   if($validatorWsysVoFrom1.isValid()){
				   var _val =  $("input:radio[name='status']:checked").val();
				   if(_val == '5'&& !$('#refuseRson').val()){
					   $("#tips").show();
					   return;
				   }
				   else{
					   $("#tips").hide();
				   }
    			   abc.layerAjaxConfirm("POST", ctx+"/orderchange/docharge.php",  $("#orderbackCharge").serializeJson(), document.referrer);
    		   }
    	   });
    	   
    	   //换货收货
    	   $("#application_sh").click(function(){
    		   $("#myModal1").show();
        	   $("#myModal1").find(".modal-dialog").animate({'top':abc.winscrollTop(50)},500);
    	   });
    	   $("button[data-dismiss='modal1']").click(function(){
        	   $("#myModal1").find(".modal-dialog").animate({'top':'-400px'},500,function(){
        	    	$("#myModal1").hide();
        	   });
           });
    	   
    	   var $validatorWsysVoFrom2 = $("#makesure").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
           $validatorWsysVoFrom2.validator().trigger("showtip");
           
    	   $("button[data-save='modal1']").click(function(){
    		   if($validatorWsysVoFrom2.isValid()){  
    			   abc.layerAjaxConfirm("POST", ctx+"/orderchange/docharge.php", $("#makesure").serializeJson(), document.referrer);
    		   }
    	   });
    	   
    	   //确认退单
    	   $("#application_td").click(function(){
    		   $("#myModal2").show();
        	   $("#myModal2").find(".modal-dialog").animate({'top':abc.winscrollTop(10)},500);
    	   });
    	   $("button[data-dismiss='modal2']").click(function(){
        	   $("#myModal2").find(".modal-dialog").animate({'top':'-400px'},500,function(){
        	    	$("#myModal2").hide();
        	   });
           });
    	   var $validatorWsysVoFrom3 = $("#orderback").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
           $validatorWsysVoFrom3.validator().trigger("showtip");
           
    	   $("button[data-save='modal2']").click(function(){
    		   if($validatorWsysVoFrom3.isValid()){  
    			   abc.layerAjaxConfirm("POST", ctx+"/orderchange/orderback.php", $("#orderback").serializeJson(), document.referrer);
    		   }
    	   });
		   $("#amount").bind('blur',function(){
			   var val=$(this).attr('data-val');
			   var amount = $("#amount").val();
			   if(parseFloat(amount)>parseFloat(val)){
				   $(this).css('border-color','red').focus();
			   }
			   else{
				   $(this).css('border-color','#ccc');
			   }
		   });
    	   //确认退款
    	   $("#application_tk").click(function(){
    		   $("#myModal3").show();
        	   $("#myModal3").find(".modal-dialog").animate({'top':abc.winscrollTop(20)},500);
    	   });
    	   $("button[data-dismiss='modal3']").click(function(){
        	   $("#myModal3").find(".modal-dialog").animate({'top':'-400px'},500,function(){
        	    	$("#myModal3").hide();
        	   });
           });
    	   
    	   var $validatorWsysVoFrom4 = $("#orderrefund").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
           $validatorWsysVoFrom4.validator().trigger("showtip");
           
    	   $("button[data-save='modal3']").click(function(){
    		   if($validatorWsysVoFrom4.isValid()){
				   var money = $("#moneny").val();
				   var amout = $('input[name="amount"]').val();
				   if(parseFloat(amout)>parseFloat(money)){
					   abc.layerAlert(false, '退款金额小于等于订单金额!');
					   return;
				   }
				   if(parseFloat(amout)<=0){
					   abc.layerAlert(false, '退款金额应大于0');
					   return;
				   }
    			   abc.layerAjaxConfirm("POST", ctx+"/orderchange/refund.php", $("#orderrefund").serializeJson(), document.referrer);
    		   }
    	   });

		   /*$('input[name="status"]').on('click',function (){
			   var isChecked=$(this).val();
			   if(isChecked=='5'){
				   document.getElementById('refuseRson').innerHTML="";
				   document.getElementById('refuseRson').setAttribute("data-rule",'required;');
			   }
			   else if(isChecked=='2'){
				   document.getElementById('refuseRson').setAttribute("data-rule",'');
			   }
		   });*/
       });
    })
});