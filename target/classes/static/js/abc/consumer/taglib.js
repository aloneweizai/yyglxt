require(["../../config"], function () {
    require(["jquery.full"], function ($) {
      $(function () {
    	  $('a[data-type]').click(function(){
    		  var _this=$(this);
    		  var _userId=$("#userId").val();
    		  var type=_this.attr('data-type');
    		  //abc.layerAjaxConfirm("POST",ctx+"/consumerManager/consumer/edituserTags.php?id="+_userId+"&doType="+type+"&tagId="+_this.attr('data-id'), "", ctx+"/consumerManager/consumer/userTags.php?id="+_userId);
    		  abc.ajaxConfirm("POST", ctx+"/consumerManager/consumer/edituserTags.php?id="+_userId+"&doType="+type+"&tagId="+_this.attr('data-id'), "", ctx+"/consumerManager/consumer/userTags.php?id="+_userId,$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"),1000);
    	  });
    	  
    	  $("#seletablib").change(function(){
    		  var _v=$(this).val();
    		  if(_v==""){
    			  $(".tag-list__itemWraper").css('display','block');
    		  }else{
    			  $(".tag-list__itemWraper").css('display','none');
    			  $("#tag_"+_v).css('display','block');
    		  }
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
       });
    })
});