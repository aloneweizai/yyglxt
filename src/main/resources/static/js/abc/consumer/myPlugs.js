require(["../../config"], function () {
    require(["jquery.full","../abc/util/date"], function ($) {
      $(function () {
    	
    	  
        var myrules={};    
        
        var $validatorWsysVoFrom = $("form").validator({
          timely: 1,
          focusCleanup:true,
              theme: 'yellow_right_effect',
              rules: {
                allint:[/^(-|\+)?\d+$/, '请输入整数'],
                price:[/^\+{0,1}\d+(\.\d{1,2})?$/,'请输入正确的价格']
              }
          });
          $validatorWsysVoFrom.validator().trigger("showtip");
        
          $("#consumer_submit").click(function(){
            if($validatorWsysVoFrom.isValid()){
                  abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), document.referrer);
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


          $('input[name="isInsured"]').on('click',function (){
              if(this.checked == false){
                  $("input[name='rate']").attr("readonly","readonly");
                  $("input[name='minInsuredFee']").attr("readonly","readonly");
                  $("input[name='rate']").val("");
                  $("input[name='minInsuredFee']").val("");
              }
              else{
                  $("input[name='rate']").removeAttr("readonly");
                  $("input[name='minInsuredFee']").removeAttr("readonly");
              }
          });
          
          $("#xianshibt").click(function(){
        	  var _this=$(this);
        	  var _val=$("#passwordsss").find("input[name='password']").val();
        	  if(_this.hasClass('xianshi')){
        		  _this.removeClass('xianshi').val('隐藏');
        		  $("#passwordsss").html('<input type="text" name="password" data-rule="required;length[2~50]" style=" width:400px;" value="'+_val+'">');
        	  }else{
        		  _this.addClass('xianshi').val('显示');
        		  $("#passwordsss").html('<input type="password" name="password" data-rule="required;length[2~50]" style=" width:400px;" value="'+_val+'">');
        	  }
          });
          
          $("#shuaxin").click(function(){
        	  $("#passwordsss").find("input[name='password']").val(randomWord(false, 32, 32));
          });
          
          function randomWord(randomFlag, min, max){
        	    var str = "",range = min,
        	    arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        	    if(randomFlag){
        	        range = Math.round(Math.random() * (max-min)) + min;
        	    }
        	    for(var i=0; i<range; i++){
        	        pos = Math.round(Math.random() * (arr.length-1));
        	        str += arr[pos];
        	    }
        	    return str;
          }
          
            $("#upload_btn").click(function(){
				updat($(this));
			});
			
			function updat(obj){
				var _o=$("#uploadFile");
     	        var _val=_o.val();
     	        if(_val==""){
     	        	abc.layerAlert(false,'请选择上传图片');
     	        	return;
     	        }
     	        var size=_o[0].files[0].size/1024;
     	        if(size>200){
     	        	abc.layerAlert(false,'上传图片超过200KB!');
     	        	return;
     	        }
				
      	        obj.hide();  	        
				var types=_o.attr('data-type').split(';');
				var type=_val.substring(_val.lastIndexOf(".")+1);
      	    //var _val=obj.val();
				//var types=obj.attr('data-type').split(';');
				//var type=_val.substring(_val.lastIndexOf(".")+1);
				
				if(types.indexOf(type)<0){
					abc.layerAlert(false,'允许类型:['+_o.attr('data-type')+"]");
					obj.show();
					$("#uploadMsg").html('');
					return;
				}
				$("#uploadMsg").html('正在上传.....');
				$.ajaxFileUpload({
					url : ctx+'/util/doFileupload.php?path=viplevel',
					type : 'post',
					secureuri : false, // 一般设置为false
					fileElementId : 'uploadFile', // 上传文件的id、name属性名
					dataType : 'application/json', // 返回值类型，一般设置为json、application/json
					success : function(data, status) {
						$("#upload_btn").show();
						$("#uploadMsg").html('');
						if(data.code=='200'){
							$("input[name='imgUrl']").val(data.message).blur();
							$("#imgshow").empty().append("<img height='90' width='90' style='margin-left:10px;' src='"+imgUrl+data.message+"' />");
						}else{
							abc.layerAlert(false,data.message);
						}
					},
					error : function(data, status, e) {
						abc.layerAlert(false,e);
					}
				});
			}
          
       });
    })
});