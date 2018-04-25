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
                  abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), document.referrer);
              }
          }); 
          
          
             var setting = {
                  view: {dblClickExpand: false,showLine: false},
                  data: {simpleData: {enable: true}},
                  callback: {
                      onClick: function(e,treeId, treeNode) {
                          var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                          zTree.expandNode(treeNode);
                          if($("#categoryId")&&$("#categoryId").val()==treeNode.id){
                        	  abc.layerAlert(false,"不能选择自己作为上级分类!");
                        	  return;
                          }
                          $("input[name='parentName']").val(treeNode.name);
                          $("input[name='parentId']").val(treeNode.id).blur();
                      }
                  }
              };

              var zNodes = [];
              var parentId=$("#parentId").val();
              $.ajax({
                  type: "GET",
                  url: ctx+"/goodscategory/listJson.php",
                  async: false,
                  contentType: "application/json",
                  dataType: "JSON",
                  success: function (data) {
                	  doData(data);
                  }
              });
              function doData(data){
            	  if(parentId==data.id){
            		  $("#parentName").val(data.category);
            	  }
                  var categoryId = $("#categoryId").val();
                  if(categoryId != data.id) {
                      var obj = new Object();
                      obj.id = data.id;
                      obj.pId = data.parentId
                      obj.name = data.category
                      obj.open = true;
                      obj.myAttr = data.id;
                      zNodes.push(obj);
                      if (data.nodes && data.nodes.length > 0) {
                          doChildren(data.nodes);
                      }
                  }
              }
              function doChildren(nodes){
            	  for(var i=0;i<nodes.length;i++){
            		  doData(nodes[i]);
            	  }
              }
              
              /*$(".org_li").each(function(){
                  var obj = new Object();
                  obj.id= $(this).attr("data-id");
                  obj.pId= $(this).attr("data-pid");
                  obj.name= $(this).attr("data-name");
                  obj.open = true;
                  obj.myAttr = $(this).attr("data-id");
                  zNodes.push(obj);
              });*/
              $.fn.zTree.init($("#treeDemo"), setting, zNodes);   
                           
	          $("#parentName").click(function(){
	        	  $(".js_pop_ztree").show();
	     		  $(".js_pop_ztree").find(".col-sm-3").css('top','-700px').animate({'top':abc.winscrollTop(-100)},500);
	          });
	          $('body').on('click', '.js_close', function(){
	        	  $(".js_pop_ztree").find(".col-sm-3").animate({'top':'-700px'},500,function(){
	       	    	 $(".js_pop_ztree").hide();
	       	      })
	          });
       });
    })
});