require(["../../config"], function () {
    require(["jquery.full"], function ($) {
      $(function () {
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
			  abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
		  });


		  var goPage= function(index){
			  var start = $('input[name="startRepo"]').val();
			  var end = $('input[name="endRepo"]').val();
			  if(parseInt(start)>parseInt(end)){
				  layer.msg("库存范围起应小于或等于库存范围止,请重新输入! ", {
					  offset: abc.winscrollTop(200),
					  shade: 0.3,
					  icon: 5,
					  time: 2000
				  });
				  return;
			  }
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


		  $("input[data-type='date']").datebox({
    	   editable:false,
      	   formatter:function(date){  
      		   var y = date.getFullYear();  
      		   var m = date.getMonth()+1;  
      		   var d = date.getDate();  
      		   var h = date.getHours();  
      		   var M = date.getMinutes();  
      		   var s = date.getSeconds();  
      		   function formatNumber(value){  
      		     return (value < 10 ? '0' : '') + value;  
      		   }    
      		   return y+'-'+formatNumber(m)+'-'+formatNumber(d);  
      		},  
      		parser:function(s){  
      		   var t = Date.parse(s);  
      		   if (!isNaN(t)){  
      		    return new Date(t);  
      		   } else {  
      		    return new Date();  
      		   }  
      		} 
         });
    	 
    	 $("#showmyModal").click(function(){
    		  $("#myModal").show();
    		  $("#myModal").find(".modal-dialog").css('top','-600px').animate({'top':abc.winscrollTop(100)},500);
    	 });
    	 
    	 $("button[data-dismiss]").click(function(){
    		 $("#myModal").find(".modal-dialog").animate({'top':'-600px'},500,function(){
     	    	$("#myModal").hide();
     	     })
         });
    	 
    	 var $validatorWsysVoFrom = $("form[data-type]").validator({
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
    			 var _type=$("#type").val();
           	     var _num=$("#goodsNum").val();
           	     var _remark=$("#remark").val();
           	     var productrepo={};
           	     productrepo['remark']=_remark;
           	     productrepo['goodsId']=$("#goodsId").val();
                 productrepo['productId']=$("#productId").val();
           	     if(_type=='income'){
           	    	productrepo['income']=_num;
           	     }else{
           	    	productrepo['outcome']=_num;
           	     }
           	     abc.layerAjaxConfirm("POST", ctx+'/financed/productrepoEdit.php?type='+_type, JSON.stringify(productrepo), ctx+'/financed/productrepoInfo.php?productId='+$("#productId").val()+'&goodsId='+$("#goodsId").val()+'&goodsName='+$("#goodsName").val()+'&guige='+$("#guige").val()+'&page='+$("#page").val());
    		 } 
         });
       });
    })
});