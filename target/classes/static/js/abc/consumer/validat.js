require(["../../config"], function () {
    require(["jquery.full"], function ($) {
       $(function () {
    	   $("#consumer_ok").click(function(){
               if (!isStatus())           return ;
               abc.layerAjaxConfirm("POST",ctx+'/consumerManager/trueName_save.php?userId='+$("#userId").val()+'&validStatus=2' , '', document.referrer);


    	   });
    	   function isStatus(){
               if ($("#zhengchangid").length){
                   return true;
               }else{
                   abc.layerAlert(false, '用户禁用，不能进行此操作!');
                   return false;
               }
           }
           $("#consumer_no").click(function(){
                if (!isStatus())           return ;


        	   //$("#myModal").find(".modal-dialog").css('top',abc.winscrollTop(100));
        	   $("#myModal").show();
        	   $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(100)},500);
    	   });
           $("button[data-save]").click(function(){
         	    var _rson=$("#refuseRson").val();
         	    if(!_rson==""){
         	    	abc.layerAjaxConfirm("POST",ctx+'/consumerManager/trueName_save.php?userId='+$("#userId").val()+'&validStatus=3&remark='+_rson, '', document.referrer);
         	    }else{
         	    	abc.layerAlert(false, '请填写验证失败原因!');
         	    }
           });
           $("button[data-dismiss]").click(function(){
         	    $("#myModal").find(".modal-dialog").animate({'top':'-400px'},500,function(){
         	    	$("#myModal").hide();
         	    })
           });
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
             $("#reasonlib").change(function(){
            	 var _obj=$(this);
            	 if(!_obj.val()==""){
            		 $("#refuseRson").val(_obj.val());
            	 }
             });
             
             $(".showbig").click(function(){
            	 var _this=$(this);
            	 var _shoimg=$("#shoimg");
            	 _shoimg.find('img').attr('src',_this.attr('src'));
            	 var scrol=$(document).scrollTop();
            	 _shoimg.css({width:_this.width(),height:_this.height(),top:_this.offset().top-scrol,left:_this.offset().left,marginLeft:'0px'}).show();
            	 _shoimg.animate({left:'50%',top:abc.winscrollTop(50),width:'800px',height:'600px',marginLeft:'-400px'},500);
             });
             
             $("#shoimg").click(function(){
            	 $(this).hide(500);
             });
       });
    })
});