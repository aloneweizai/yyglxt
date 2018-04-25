require(["../../config"], function () {
    require(["jquery.full","autocomplete"], function ($) {
      $(function () {
    	   
    	   var $validatorWsysVoFrom = $("#refuse").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
           $validatorWsysVoFrom.validator().trigger("showtip");
    	  
           var _id=$("#invoiceId").val();
		   var _userId=$("#userId").val();
		   var invoice={};
           
    	   $("#refuseInvoice").click(function(){
    		   if($validatorWsysVoFrom.isValid()){  
    			   var _remark=$("#refuseReason").val();
    			   invoice["isBilling"]=false;
    			   invoice['id']=_id;
    			   invoice['remark']=_remark;
    			   abc.layerAjaxConfirm("POST", ctx+'/financed/invoiceBill.php', JSON.stringify(invoice),document.referrer);
    		   }
    	   });
    	   
    	   $("button[datatarget='#myModal']").click(function(){
    		   $("#myModal").show();
	     	   $("#myModal").find(".modal-dialog").css('top','-500px').animate({'top':abc.winscrollTop(50)},500);
    	   });
    	   $("[data-dismiss='modal']").click(function(){
    		   $("#myModal").find(".modal-dialog").animate({'top':'-500px'},500,function(){
       	    	 $("#myModal").hide();
       	       })
    	   });
    	   
    	   $("button[datatarget='#myModal2']").click(function(){
    		   $("#myModal2").show();
	     	   $("#myModal2").find(".modal-dialog").css('top','-500px').animate({'top':abc.winscrollTop(100)},500);
    	   });
    	   $("[data-dismiss='modal2']").click(function(){
    		   $("#myModal2").find(".modal-dialog").animate({'top':'-500px'},500,function(){
       	    	 $("#myModal2").hide();
       	       })
    	   });
    	   
    	   $("button[datatarget='#myModal3']").click(function(){
    		   $("#myModal3").show();
	     	   $("#myModal3").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(20)},500);
    	   });
    	   $("[data-dismiss='modal3']").click(function(){
    		   $("#myModal3").find(".modal-dialog").animate({'top':'-600px'},500,function(){
       	    	 $("#myModal3").hide();
       	       })
    	   });
    	   
    	   $("#zidonglp").click(function(){
    		   $.ajax({
                   type: "GET",
                   url: ctx+"/financed/invoiceGetOne.php?invoiceTypeCode="+$("#invoicetype").val(),
                   async: true,
                   contentType: "application/json",
                   dataType: "JSON",
                   success: function (data) {
                	   if(data){
                		   $("#zdlqhm").val(data.invoiceNo).blur();
                		   $("#zdlqdm").val(data.invoiceCode).blur();
                		   $("#detailId").val(data.id);
                	   }else{
                		   abc.layerAlert(false,"没有可领取的发票!");
                	   }
                   }
    		   });
    	   });
    	   
    	   var kaipiaoFrom = $("#kaipiao").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
    	   kaipiaoFrom.validator().trigger("showtip");
    	   $("#kaipiaoSb").click(function(){
    		   if(kaipiaoFrom.isValid()){  
    			   invoice["isBilling"]=true;
    			   invoice['id']=_id;
    			   invoice["detailId"]=$("#detailId").val();
    			   invoice['remark']=$("#beizhu").val();
				   invoice['type']=$("#invoiceType").val();
    			   abc.layerAjaxConfirm("POST", ctx+'/financed/invoiceBill.php', JSON.stringify(invoice),document.referrer);
    		   }
    	   });
    	   
    	   $("#zdlqhm").bigAutocomplete({
    		   width:200,
    		   url:ctx+"/financed/invoiceGetByNo/"+$("#invoicetype").val(),
    		   callback:function(data){
    			   $("#zdlqhm").val(data.title).blur();
        		   $("#zdlqdm").val(data.object.invoiceCode).blur();
        		   $("#detailId").val(data.object.id);
			   }
    	   });
       });
    })
});