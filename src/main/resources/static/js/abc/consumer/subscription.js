require(["../../config"], function () {
    require(["jquery.full"], function ($) {
      $(function () {
          layui.use('form', function(){
              var form = layui.form;
              form.render();
              form.on('checkbox(hasWeb)', function(data){
                 if(data.elem.checked){
                     document.getElementById('webForce').style.display = "inline-block";
                  }else{
                     document.getElementById('webForce').style.display = "none";
                  }
              });
              form.on('checkbox(hasWechat)', function(data){
                  if(data.elem.checked){
                      document.getElementById('wechatForce').style.display = "inline-block";
                  }else{
                      document.getElementById('wechatForce').style.display = "none";
                  }
              });
              form.on('checkbox(hasMessage)', function(data){
                  if(data.elem.checked){
                      document.getElementById('messageForce').style.display = "inline-block";
                  }else{
                      document.getElementById('messageForce').style.display = "none";
                  }
              });
          });
	     	  
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
                  var formObj = {};
                  var _id=$("#subId").val();
                  if (_id != null) {
                      formObj['id'] = _id;
                  }
                  if($("#msgType").val() ==null || $("#msgType").val()==""){
                      abc.layerAlert(false, "请选择消息类型!");
                      return;
                  }
                  if($("#busiType").val() ==null || $("#busiType").val()==""){
                      abc.layerAlert(false, "请选择业务类型!");
                      return;
                  }
                  formObj["type"]=$("#msgType").val();
                  formObj["busiType"]=$("#busiType").val();
                  var check = document.getElementById("hasWeb").checked;
                  if(check == true) {
                      formObj["hasWeb"]=true;
                  }
                  else{
                      formObj["hasWeb"] = false;
                  }
                  check = document.getElementById("webForce1").checked;
                  if(check == true) {
                      formObj["webForce"]=true;
                  }
                  else{
                      formObj["webForce"] = false;
                  }
                   check = document.getElementById("hasWechat").checked;
                  if(check == true) {
                      formObj["hasWechat"] = true;
                  }
                  else{
                      formObj["hasWechat"] = false;
                  }
                  check = document.getElementById("wechatForce1").checked;
                  if(check == true) {
                      formObj["wechatForce"]=true;
                  }
                  else{
                      formObj["wechatForce"] = false;
                  }
                   check = document.getElementById("hasMessage").checked;
                  if(check == true) {
                      formObj["hasMessage"] = true;
                  }
                  else{
                      formObj["hasMessage"] = false;
                  }
                  check = document.getElementById("messageForce1").checked;
                  if(check == true) {
                      formObj["messageForce"]=true;
                  }
                  else{
                      formObj["messageForce"] = false;
                  }
                  if($("#remark").val().length > 200){
                      abc.layerAlert(false, "备注的最大长度为200!");
                      return;
                  }
                  formObj["remark"]=$("#remark").val();
                  abc.layerAjaxConfirm("POST", $("form").attr('action'),  JSON.stringify(formObj),document.referrer);
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