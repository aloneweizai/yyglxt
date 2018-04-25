require([ "../../config" ], function() {
	require([ "jquery.full","wangEditor","abc.ajaxfileupload","bootstrap" ], function($,Editor) {
		
		var editor = new Editor("#_topic_description_area");
        editor.customConfig.uploadImgServer = ctx+'/util/wangEditorUpload.php';
        editor.customConfig.uploadImgTimeout = 60000;
        editor.customConfig.uploadImgMaxLength = 10;
        editor.create();
		
		$(function() {
			var myrules={};
		  	  
	  	    var $validatorWsysVoFrom = $("form").validator({
	  		  timely: 1,
	  		  focusCleanup:true,
	            theme: 'yellow_right_effect',
	            rules: {
	          	  allint:[/^\d+$/, '请输入整数'],
	          	  amount:[/^\+{0,1}\d+(\.\d{1,2})?$/,'请输入正确的金额']
	            }
	        });
	  	    
	        $validatorWsysVoFrom.validator().trigger("showtip");
	        
	        
	        $("#consumer_submit").click(function(){
	        	if($validatorWsysVoFrom.isValid()){
	        		var gift=JSON.parse($("form").serializeJson());
	        		gift['details']=editor.txt.html();
	        		abc.layerAjaxConfirm("POST", $("form").attr('action'),  JSON.stringify(gift), document.referrer);
	        	}
	        });
	        
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
	   	        var types=_o.attr('data-type').split(';');
				var type=_val.substring(_val.lastIndexOf(".")+1);
				obj.hide();
	   	        $("#uploadMsg").html('正在上传.....')
				
				if(types.indexOf(type)<0){
					abc.layerAlert(false,'允许类型:['+obj.attr('data-type')+"]");
					obj.show();
					$("#uploadMsg").html('');
					return;
				}
				$.ajaxFileUpload({
					url : ctx+'/util/doFileupload.php?path=vipgift',
					type : 'post',
					secureuri : false, // 一般设置为false
					fileElementId : 'uploadFile', // 上传文件的id、name属性名
					dataType : 'application/json', // 返回值类型，一般设置为json、application/json
					success : function(data, status) {
						$("#upload_btn").show();
						$("#uploadMsg").html('');
						if(data.code=='200'){
							$("#imgshow").attr('src',imgurl+data.message);
							$("#imageUrl").val(data.message).blur();
						}else{
							abc.layerAlert(false,data.message);
						}
					},
					error : function(data, status, e) {
						alert(e);
					}
				});
			}
			
		});
	});
});
