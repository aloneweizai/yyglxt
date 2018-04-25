require(["../../config"], function () {
    require(["jquery.full","abc.ajaxfileupload","../abc/util/date"], function ($) {
      $(function () {
    	     
    	     var menu_tree;
          
             var setting = {
            	  check: {enable: true,chkboxType :{ "Y":"", "N":""}},
                  view: {dblClickExpand: false,showLine: false},
                  data: {simpleData: {enable: true},key: {title: "uri"}},
                  callback: {
                	  onCheck:function(event, treeId, treeNode){
                		  if(treeNode.tp=='P'){
                			  var nodes=treeNode.children;
                			  if(nodes && nodes.length>0){
                				  for(var i=0;i<nodes.length;i++){
                					  var _n=nodes[i];
                					  if(treeNode.checked==_n.checked) continue;
                					  if(treeNode.checked){
                						  menu_tree.checkNode(_n, true);
                						  var _r1='<input name="authentication'+_n.id+'" type="radio" value="true">是<input name="authentication'+_n.id+'" style="margin-left:5px"  type="radio" value="false" checked>否';
                                		  //var _r2='<input name="status'+_n.id+'" type="radio" value="true" checked>启用<input name="status'+_n.id+'" type="radio" style="margin-left:5px"  value="false">停用';
                                		  $("#apisets").append("<tr apiId='"+_n.id+"'><td>"+_n.name+"</td><td>"+_n.uri+"</td><td>"+_n.method+"</td><td><input class='apisets' data-rule='required;integer;' name='timesPerMinute"+_n.id+"' value='0'></td><td><input name='timesPerHour"+_n.id+"' data-rule='required;integer;' class='apisets' value='0'></td><td><input name='timesPerDay"+_n.id+"' data-rule='required;integer;' class='apisets' value='0'></td><td></td></tr>");
                        			  }else{
                        				  menu_tree.checkNode(_n, false);
                        				  $("#apisets").find('tr[apiId="'+_n.id+'"]').remove();
                        			  }
                				  } 
                			  }
                		  }else{
                			  if(treeNode.checked){
                				  var _r1='<input name="authentication'+treeNode.id+'" type="radio" value="true">是<input name="authentication'+treeNode.id+'" style="margin-left:5px"  type="radio" value="false" checked>否';
                        		  //var _r2='<input name="status'+treeNode.id+'" type="radio" value="true" checked>启用<input name="status'+treeNode.id+'" type="radio" style="margin-left:5px"  value="false">停用';
                        		  $("#apisets").append("<tr apiId='"+treeNode.id+"'><td>"+treeNode.name+"</td><td>"+treeNode.uri+"</td><td>"+treeNode.method+"</td><td><input class='apisets' data-rule='required;integer;' name='timesPerMinute"+treeNode.id+"' value='0'></td><td><input name='timesPerHour"+treeNode.id+"' data-rule='required;integer;' class='apisets' value='0'></td><td><input name='timesPerDay"+treeNode.id+"' data-rule='required;integer;' class='apisets' value='0'></td><td></td></tr>");
                			  }else{
                				  $("#apisets").find('tr[apiId="'+treeNode.id+'"]').remove();
                			  }
                		  }
                	  },
                	  onClick:function(event, treeId, treeNode){
                		  if(treeNode.isParent){
                			 if(treeNode.open){
                				 menu_tree.expandNode(treeNode,false);
                			 }else{
                				 menu_tree.expandNode(treeNode,true);
                			 }
                		  }else{
                			  menu_tree.checkNode(treeNode, null,null,true); 
                		  }
                	  }
                	  
                  }
              };
             
              $("#timesPerMinute").blur(function(){
            	  doval($(this));  
              });
              $("#timesPerHour").blur(function(){
            	  doval($(this));  
              });
              $("#timesPerDay").blur(function(){
            	  doval($(this));  
              });
              
              $("input[name='gender']").click(function(){
            	  var _obj=$(this);
            	  var _v=_obj.val();
            	  $("#apisets>tr>td:nth-child("+(_obj.parent().index()+1)+")").each(function(){
            		  $(this).find("input[type='radio'][value!="+_v+"]").removeAttr("checked");
            		  $(this).find("input[type='radio'][value="+_v+"]").attr("checked",true);
            	  });
              });
              function doval(_obj){
            	  var _v=_obj.val();
            	  $("#apisets>tr>td:nth-child("+(_obj.parent().index()+1)+")").each(function(){
            		  var _input=$(this).find("input");
            		  _input.val(_v).blur();
            	  });
              }
              
              var zNodes = [];
              $(".org_li").each(function(){
                  var obj = new Object();
                  obj.id= $(this).attr("data-id");
                  obj.pId= $(this).attr("data-pid");
                  obj.name= $(this).attr("data-uri");
                  obj.authentication= $(this).attr("data-authentication");
                  obj.uri= $(this).attr("data-name");
                  obj.method= $(this).attr("data-method");
                  obj.tp= $(this).attr("data-tp");
                  if($(this).attr("data-pid") =="" || $(this).attr("data-pid") =="null"){
                      obj.pId = '';
                      obj.open = false;
                      obj.isParent = true
                  }
                  obj.myAttr = $(this).attr("data-id");
                  zNodes.push(obj);
              });
              $.fn.zTree.init($("#treeDemo"), setting, zNodes);
              menu_tree = $.fn.zTree.getZTreeObj("treeDemo");
              //menu_tree.setting.check.chkboxType = { "Y":"", "N":""};
              var _datas=[];
              var appId=$("input[name='appId']").val();
              /*$.ajax({
                  type: "POST",
                  url: ctx+"/app/getApiSettings.php?appId="+appId,
                  async: false,
                  contentType: "application/json",
                  dataType: "JSON",
                  success: function (data) {
                	  _datas=data;
                	  var _tb=$("#apisets");
            		  _tb.empty();
            		  $("#maskses").hide();
                	  for(var i=0;i<data.length;i++){
                		  var _set=data[i];
                		  var node=menu_tree.getNodeByParam("id", _set.apiId, null);
                		  menu_tree.checkNode(node, true);
                		  var _r1='<input name="authentication'+_set.apiId+'" '+(_set.isValidate?"checked":"")+' type="radio" value="true">是<input name="authentication'+_set.apiId+'"  '+(_set.isValidate?"":"checked")+' style="margin-left:5px"  type="radio" value="false">否';
                		  //var _r2='<input name="status'+_set.apiId+'" '+(_set.status?"checked":"")+' type="radio" value="true">启用<input name="status'+_set.apiId+'" '+(_set.status?"":"checked")+' type="radio" style="margin-left:5px"  value="false">停用';
                		  _tb.append("<tr apiId='"+_set.apiId+"'><td>"+_set.name+"</td><td>"+_set.uri+"</td><td>"+_set.method+"</td><td><input class='apisets' data-rule='required;integer;' name='timesPerMinute"+_set.apiId+"' value='"+_set.timesPerMinute+"'></td><td><input data-rule='required;integer;' name='timesPerHour"+_set.apiId+"' class='apisets' value='"+_set.timesPerHour+"'></td><td><input data-rule='required;integer;' name='timesPerDay"+_set.apiId+"' class='apisets' value='"+_set.timesPerDay+"'></td><td></td></tr>");
                	  }
                  }
              });*/
              
              var $validatorWsysVoFrom = $("form").validator({
        		  timely: 1,
        		  focusCleanup:true,
                  theme: 'yellow_right_effect',
                  rules: {
                	  allint:[/^(-|\+)?\d+$/, '请输入整数']
                  }
              });
              $validatorWsysVoFrom.validator().trigger("showtip");
        	  
              $("#consumer_show").click(function(){
            	  abc.block();
            	  setTimeout(function(){
            		  if($validatorWsysVoFrom.isValid()){
                		  var appsetList=new Array();
                          $("#apisets").find("tr").each(function(){
                        	  var _tr=$(this);
                        	  var appset={};
                        	  appset['appId']=appId;
                        	  appset['apiId']=_tr.attr('apiid');
                        	  appset['timesPerMinute']=_tr.find("td:nth-child(4)").find("input").val();
                        	  appset['timesPerHour']=_tr.find("td:nth-child(5)").find("input").val();
                        	  appset['timesPerDay']=_tr.find("td:nth-child(6)").find("input").val();
                        	  appset['isValidate']=_tr.find("td:nth-child(7)").find("input[type='radio']:checked").val();
                        	  appset['status']=true;
                        	  appsetList.push(appset);
                          });
                          abc.unblock();
                          if(appsetList.length>100){
                        	  abc.layerAlert(false,'一次最多授权100个接口,当前选中：'+appsetList.length);
                        	  return ;
                          }
                          abc.layerAjaxConfirm("POST", ctx+'/app/batchSave.php?appId='+appId, JSON.stringify(appsetList),document.referrer);
                      } 
            	  },500);
            	  /**/
              }); 
              
              $("#guanjianzi").keyup(function(){
            	  var _val=$(this).val();
            	  if(_val.length>0){
            		  var allNode = menu_tree.getNodes();  
                	  var nodeList =menu_tree.getNodesByParamFuzzy('name', _val); 
                	  $.fn.zTree.init($("#treeDemo"), setting, nodeList);
            	  }else{
            		  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            		  for(var i=0;i<_datas.length;i++){
                		  var _set=_datas[i];
                		  var node=menu_tree.getNodeByParam("id", _set.apiId, null);
                		  menu_tree.checkNode(node, true);
                	  }
            	  }           	  
              });
         
              $("#maskses").hide();
       });
    })
});