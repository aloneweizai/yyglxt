require(["../../config"], function () {
    require(["jquery.full"], function ($) {
      $(function () {
    	  var myrules={};
	     	  
    	  var $validatorWsysVoFrom = $("form").validator({
    		  timely: 1,
    		  focusCleanup:true,
              theme: 'yellow_right_effect',
              rules: {
            	  allint:[/^(-|\+)?\d+$/, '请输入整数']
              }
          });
          $validatorWsysVoFrom.validator().trigger("showtip");
    	  
          $("#consumer_submit").click(function(){
        	  if($validatorWsysVoFrom.isValid()){
                  abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(),document.referrer);
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
          
          $("input[data-type='date']").datebox({  
        	   required:true,
        	   formatter:$(this).attr('data-fmt'),
        	   formatter: function (date) {  
                   var y = date.getFullYear();  
                   var m = date.getMonth() + 1;  
                   var d = date.getDate();  
                   return y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d);  
               }
          });
          $("#see").click(function(){
        	  $(".js_pop_ztree").show();
          });
          
          $("img[title]").click(function(){
        	  $("input[name='medalIcon']").val($(this).attr('title'));
        	  $("#see").attr('src',ctx+'/images/medals/'+$(this).attr('title')+'.png');
          });
          
          $('body').on('click', '.js_close', function(){
              $(".main_modal").hide();
          });
       });
    })
});