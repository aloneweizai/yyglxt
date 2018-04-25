require(["../../config"], function () {
    require(["jquery.full","../abc/consumer/page"], function ($) {
       $(function () {
		   var compId;
		   $(".compId").change(function() {
			   compId = $(this).val();
		   });

    	   $("[id='downLoad']").click(function(){
      		 var qurl=$(this).attr('data-qurl');
      		 //var durl=$(this).attr('data-durl');
      		 var noMsg=$(this).attr('data-noMsg');
			   if(compId == ""||compId ==undefined){
				   abc.layerAlert(false,"请选择快递公司");
				   return;
			   }
			   var durl = ctx+"/orderchange/export.php?compId="+compId;
      		 layer.confirm('是否导出？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
      	          function (index) {
  	    			 $.ajax({
  	                     url:qurl,
  	                     dataType: 'json',
  	                     success: function (data) {
  	                         if(data){
  	                        	 layer.msg("导出成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    window.location.href =durl;
									 $("#myModal1").hide();
                                 });
  	                         }else{
  	                          layer.msg(noMsg, {offset: abc.winscrollTop(200), shade: 0.3, icon: 5, time: 2000});
  	                         }
  	                     }
  	                 });       
      	          }
      	     );
           });

    	   $("#consumer_import").click(function(){
    		   $("#myModal").show();
        	   $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(100)},300);
			   $(".compId").val("");
			   $("#uploadFile").val("");
    	   });

		   $("#consumer_export").click(function(){
			   $("#myModal1").show();
			   $("#myModal1").find(".modal-dialog").animate({'top':abc.winscrollTop(100)},300);
			   $(".compId").val("");
		   });
    	   $("[id='back']").click(function(){
				   $("#myModal").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
					   $("#myModal").hide();
				   });
				   $("#myModal1").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
					   $("#myModal1").hide();
				   });
           });

		   /*$("a[data-type='opendialog']").click(function(){
			   var url=$(this).attr("data-url");
			   $("#myModal3").fadeIn();
			   document.getElementById("consumer_frame").src=url;
		   });
*/
		   $("button[data-dismi]").click(function(){
			   $("#myModal3").fadeOut();
		   });

		   $("a[data-type='exchangedialog']").click(function(){
			   var url=$(this).attr("data-url");
			   var val=$(this).attr("data-val");
			   if(val=='1'||val=='5'){
				   $("#myModal5").fadeIn();
				   document.getElementById("consumer_frame").src=url;
			   }
			   else if(val=='2'){
				   $("#myModal2").fadeIn();
				   document.getElementById("consumer_charge").src=url;
			   }
			   else if(val=='3'){
				   $("#myModal3").fadeIn();
				   document.getElementById("consumer_sure").src=url;
			   }
			   else if(val=='4'){
				   $("#myModal4").fadeIn();
				   document.getElementById("consumer_back").src=url;
			   }
			   else if(val=='6'){
				   $("#lookModal").fadeIn();
				   document.getElementById("look_frame").src=url;
			   }
		   });

		   $("button[data-dismiss]").click(function(){
			   var val=$(this).attr("data-val");
			   if(val=='5'){
				   $("#myModal5").fadeOut();
			   }
			   else if(val=='2'){
				   $("#myModal2").fadeOut();
			   }
			   else if(val=='3'){
				   $("#myModal3").fadeOut();
			   }
			   else if(val=='4'){
				   $("#myModal4").fadeOut();
			   }
			   else if(val=='6'){
				   $("#lookModal").fadeOut();
			   }

		   });

		   var $validatorWsysVoFrom1 = $("#orderbackCharge").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
		   $validatorWsysVoFrom1.validator().trigger("showtip");

		   $("button[data-save='modal']").click(function(){
			   if($validatorWsysVoFrom1.isValid()){
				   var isOk = document.getElementById('consumer_charge').contentWindow.document.getElementById('isOk').checked;
				   var _val;
				   if(isOk){
					   _val = document.getElementById('consumer_charge').contentWindow.document.getElementById('isOk').value;
				   }
				   else{
					   _val = document.getElementById('consumer_charge').contentWindow.document.getElementById('isNO').value;
				   }
				   var refuseRson = document.getElementById('consumer_charge').contentWindow.document.getElementById('refuseRson').value;
				   var _id = document.getElementById('consumer_charge').contentWindow.document.getElementById('orderExchangeId').value;
				   if(_val == '5'&& !refuseRson){
					   document.getElementById('consumer_charge').contentWindow.document.getElementById('tips').style.display = "block"
					   return;
				   }
				   else{
					   document.getElementById('consumer_charge').contentWindow.document.getElementById('tips').style.display = "none"
				   }
				   if(refuseRson.length>500){
					   document.getElementById('consumer_charge').contentWindow.document.getElementById('tips1').style.display = "block"
					   return;
				   }
				   else{
					   document.getElementById('consumer_charge').contentWindow.document.getElementById('tips1').style.display = "none"
				   }
				   var orderbackCharge={};
				   orderbackCharge["status"]=_val;
				   orderbackCharge['id']=_id;
				   orderbackCharge['adminRemark']=refuseRson;
				   layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
					   function(){
						   abc.block();
						   $.ajax({
							   type: "POST",
							   url: ctx+"/orderchange/docharge.php",
							   data: JSON.stringify(orderbackCharge),
							   async: true,
							   contentType: "application/json",
							   dataType: "JSON",
							   success: function (data) {
								   abc.unblock();
								   if (data && data.code == "2000") {
									   layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
									   location.reload();
								   } else {
									   layer.alert(result.message||"操作失败", {icon: 5});
								   }
							   }
						   });
					   }
				   );
				   //abc.layerAjaxConfirm("POST", ctx+"/orderchange/docharge.php", JSON.stringify(orderbackCharge), document.referrer);
			   }
		   });

		   var $validatorWsysVoFrom2 = $("#makesure").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
		   $validatorWsysVoFrom2.validator().trigger("showtip");

		   $("button[data-save='modal1']").click(function(){
			   if($validatorWsysVoFrom2.isValid()){
				   var refuseRson = document.getElementById('consumer_sure').contentWindow.document.getElementById('refuseRson').value;
				   var status = document.getElementById('consumer_sure').contentWindow.document.getElementById('status').value;
				   var _id = document.getElementById('consumer_sure').contentWindow.document.getElementById('orderExchangeId').value;
				   var expressComp;
				   var comp = document.getElementById('consumer_sure').contentWindow.document.getElementById('expressComp');
				   if(comp!=null && comp!=""){
					   expressComp = comp.value;
					   if(!expressComp){
						   comp.focus();
						   document.getElementById('consumer_sure').contentWindow.document.getElementById('tips').style.display = "block"
						   return;
					   }
					   else{
						   document.getElementById('consumer_sure').contentWindow.document.getElementById('tips').style.display = "none"
					   }
				   }
				   var expressNo;
				   var _no = document.getElementById('consumer_sure').contentWindow.document.getElementById('expressNo');
				   if(_no!=null && _no!=""){
					   expressNo = _no.value;
					   if(!expressNo){
						   _no.focus();
						   document.getElementById('consumer_sure').contentWindow.document.getElementById('tips1').style.display = "block"
						   return;
					   }
					   else{
						   document.getElementById('consumer_sure').contentWindow.document.getElementById('tips1').style.display = "none"
						   if(expressNo.length<6||expressNo.length>32){
							   _no.focus();
							   document.getElementById('consumer_sure').contentWindow.document.getElementById('tips2').style.display = "block"
							   return;
						   }
						   else{
							   document.getElementById('consumer_sure').contentWindow.document.getElementById('tips2').style.display = "none"
						   }
					   }
				   }
				   var makesure={};
				   makesure["status"]=status;
				   makesure['id']=_id;
				   makesure['adminConfirmRemark']=refuseRson;
				   makesure['expressComp']=expressComp;
				   makesure['expressNo']=expressNo;
				   layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
					   function(){
						   abc.block();
						   $.ajax({
							   type: "POST",
							   url: ctx+"/orderchange/docharge.php",
							   data: JSON.stringify(makesure),
							   async: true,
							   contentType: "application/json",
							   dataType: "JSON",
							   success: function (data) {
								   abc.unblock();
								   if (data && data.code == "2000") {
									   layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
									   location.reload();
								   } else {
									   layer.alert(result.message||"操作失败", {icon: 5});
								   }
							   }
						   });
					   }
				   );
				  // abc.layerAjaxConfirm("POST", ctx+"/orderchange/docharge.php", JSON.stringify(makesure), document.referrer);
			   }
		   });

		   var $validatorWsysVoFrom3 = $("#orderback").validator({timely: 1,focusCleanup:true,theme: 'yellow_right_effect'});
		   $validatorWsysVoFrom3.validator().trigger("showtip");

		   $("button[data-save='modal2']").click(function(){
			   if($validatorWsysVoFrom3.isValid()){
				   var refuseRson = document.getElementById('consumer_back').contentWindow.document.getElementById('refuseRson').value;
				   var _id = document.getElementById('consumer_back').contentWindow.document.getElementById('orderExchangeIdback').value;
				   var expressComp;
				   var comp = document.getElementById('consumer_back').contentWindow.document.getElementById('expressComp');
				   if(comp!=null && comp!=""){
					   expressComp = comp.value;
					   if(!expressComp){
						   comp.focus();
						   document.getElementById('consumer_back').contentWindow.document.getElementById('tips').style.display = "block";
						   return;
					   }
					   else{
						   document.getElementById('consumer_back').contentWindow.document.getElementById('tips').style.display = "none";
					   }
				   }
				   var expressNo;
				   var _no = document.getElementById('consumer_back').contentWindow.document.getElementById('expressNo');
				   if(_no!=null && _no!=""){
					   expressNo = _no.value;
					   if(!expressNo){
						   _no.focus();
						   document.getElementById('consumer_back').contentWindow.document.getElementById('tips1').style.display = "block";
						   return;
					   }
					   else{
						   document.getElementById('consumer_back').contentWindow.document.getElementById('tips1').style.display = "none";
						   if(expressNo.length<6||expressNo.length>32){
							   _no.focus();
							   document.getElementById('consumer_back').contentWindow.document.getElementById('tips2').style.display = "block"
							   return;
						   }
						   else{
							   document.getElementById('consumer_back').contentWindow.document.getElementById('tips2').style.display = "none"
						   }
					   }
				   }
				   var orderback={};
				   orderback['id']=_id;
				   orderback['adminConfirmRemark']=refuseRson;
				   orderback['expressComp']=expressComp;
				   orderback['expressNo']=expressNo;
				   layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
					   function(){
						   abc.block();
						   $.ajax({
							   type: "POST",
							   url: ctx+"/orderchange/orderback.php",
							   data: JSON.stringify(orderback),
							   async: true,
							   contentType: "application/json",
							   dataType: "JSON",
							   success: function (data) {
								   abc.unblock();
								   if (data && data.code == "2000") {
									   layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
									   location.reload();
								   } else {
									   layer.alert(result.message||"操作失败", {icon: 5});
								   }
							   }
						   });
					   }
				   );
				   //abc.layerAjaxConfirm("POST", ctx+"/orderchange/orderback.php", JSON.stringify(orderback), document.referrer);
			   }
		   });
    	   $("#uploadFile").change(function() {
    		   var _val=$(this).val();
    		   var types=['xls','xlsx'];
    		   var type=_val.substring(_val.lastIndexOf(".")+1);
    		   if(types.indexOf(type)<0){
    			   $(this).val('');
    			   abc.layerAlert(false,"请上传excel文件");
			   }
		   });
    	   
    	   $("#importbtn").click(function(){
			   if(compId == ""||compId ==undefined){
				   abc.layerAlert(false,"请选择快递公司");
				   return;
			   }
    		   if($("#uploadFile").val()==''){
    			   abc.layerAlert(false,"请选择文件");
    			   return;
    		   }
    		   layer.confirm('确认导入？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                   function(){
               	     abc.block();
               	     $.ajaxFileUpload({
    					url : ctx+'/orderchange/import.php',
    					type : 'post',
    					secureuri : false, // 一般设置为false
    					fileElementId : 'uploadFile', // 上传文件的id、name属性名
    					dataType : 'application/json', // 返回值类型，一般设置为json、application/json
						 data: {
							 compId: compId
						 },
    					success : function(data, status) {
    						abc.unblock();
    						if(data.code=='2000'){
    							layer.msg("导入成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    window.location.href = ctx+'/orderchange/applications.php';
                                });
    						}else{
    							abc.layerAlert(false,data.message);
    						}
    					},
    					error : function(data, status, e) {
    						abc.layerAlert(false,e);
    					}
    				 });    
                   }
               );
    		   
    	   });


	   });
    })
});