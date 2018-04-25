require(["../../config"], function () {
    require(["jquery.full","abc.ajaxfileupload","../abc/util/date"], function ($) {
      $(function () {
    	  var myrules={};
    	  
    	    function parseDate(str) {
    	        return Date.parse(str.replace(/\.|\-/g, '/'));
    	    }
    	          	  
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
        		  if($("#renwutype").val()=='3' && ($("#startTime").val()=='' || $("#endTime").val()=='')){
        			  abc.layerAlert(false,'特殊任务开始时间或者结束时间不能为空!');
        			  return;
        		  }
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
          
         
          
             var setting = {
                  view: {dblClickExpand: false,showLine: false},
                  data: {simpleData: {enable: true}},
                  callback: {
                      onClick: function(e,treeId, treeNode) {
                          var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                          zTree.expandNode(treeNode);
                          if(treeNode.id=='1'||treeNode.id=='2')return;
                          $("input[name='ruleName']").val(treeNode.text).blur();
                          $("input[name='ruleCode']").val(treeNode.id).blur();
                          $("input[name='award']").val(treeNode.val).blur();
                          $("input[name='awardType']").val(treeNode.type);
                          $("input[name='ruleId']").val(treeNode.rid);
                      }
                  }
              };

              var zNodes = [];
              $(".org_li").each(function(){
                  var obj = new Object();
                  obj.id= $(this).attr("data-id");
                  obj.pId= $(this).attr("data-pid");
                  obj.name= $(this).attr("data-name")+($(this).attr("data-val")?("("+$(this).attr("data-val")+")"):"");
                  obj.text= $(this).attr("data-name");
                  obj.val=$(this).attr("data-val");
                  obj.type=$(this).attr("data-type");
                  obj.rid=$(this).attr("data-rid");
                  if($(this).attr("data-pid") =="" || $(this).attr("data-pid") =="null"){
                      obj.pId = '';
                      //obj.open = true;
                  }
                  obj.myAttr = $(this).attr("data-id");
                  zNodes.push(obj);
              });
              $.fn.zTree.init($("#treeDemo"), setting, zNodes);
          
          $("input[clickDIV]").click(function(){
        	  $(".js_pop_ztree").show();
     		  $(".js_pop_ztree").find(".col-sm-3").css('top','-700px').animate({'top':abc.winscrollTop(-100)},500);
          });
          $('body').on('click', '.js_close', function(){
        	  $(".js_pop_ztree").find(".col-sm-3").animate({'top':'-700px'},500,function(){
	       	    	 $(".js_pop_ztree").hide();
	       	  })
          });
          
           /* $("#uploadFile").change(function() {
				updat($(this));
			})*/
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
					url : ctx+'/util/doFileupload.php?path=sys_task',
					type : 'post',
					secureuri : false, // 一般设置为false
					fileElementId : 'uploadFile', // 上传文件的id、name属性名
					dataType : 'application/json', // 返回值类型，一般设置为json、application/json
					success : function(data, status) {
						$("#upload_btn").show();
						$("#uploadMsg").html('');
						//$("#uploadFile").bind('change',function(){updat($(this))});
						if(data.code=='200'){
							$("input[name='imageUrl']").val(data.message).blur();
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