require(["../../config"], function () {
    require(["jquery.full"], function ($) {
    	var index=0;
        function getData(){
        	abc.block();
        	$.ajax({
                type: "GET",
                url: ctx+"/goodscategory/listJson.php?category="+$("input[name='category']").val(),
                async: true,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                   if(data.id==null){
					   abc.unblock();
					   return;
				   }
                   index=0;
                   $("#treeDataTb tbody").empty();
					if(data.parentId==null){
						doDatas(data,data.parentName,0,"");
					}
					else{
						doDatas(data,data.parentName,1,"");
					}
                   $("a[data-type='delSig']").click(function(){
					   var hasChild = $(this).attr("data-child");
					   if(hasChild == 'true'){
						   abc.layerAlert(false,"该商品分类有子分类不能删除!");
						   return;
					   }
                  	  //abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val());
                	   abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(),$(this).attr("data-confirm"),$(this).attr("data-okMsg"),$(this).attr("data-failMsg"));
                   });
                   $("#treeDataTb>tbody>tr>td:not(:last-child)").click(function(){
                	   var _tr=$(this).parent();
                	   var _haschild=_tr.attr('data-child');
                	   var _open=_tr.attr('data-open');
                	   if(_haschild=='true'){
                		   var id=_tr.attr('id');
                		   if(_open=='yes'){
                			   _tr.attr('data-open','no');
                			   _tr.find("img").attr('src',ctx+'/images/open.gif');
                			   $("."+id+"d").css('display','none');
                		   }else{
                			   _tr.attr('data-open','yes');
                			   _tr.find("img").attr('src',ctx+'/images/close.gif');
                			   $("."+id+"d").css('display','table-row');
                		   }
                	   }
                   })
                   abc.unblock();
                }
        	    
            });
        }
        function doDatas(data,parentName,deep,father){
        	var hasChild=(data.nodes.length>0);
        	index+=1;
        	var _cls=father+"d "+data.id;
			if(data.parentName == null){
				var val="<tr data-open='yes' id='"+data.id+"' class='"+_cls+"' data-child='"+hasChild+"'><td width='30'>"+index+"</td><td width='500' id='"+data.id+"'style='cursor:pointer;padding-left:"+12*parseInt(deep)+"px;'>"
					+"<img src='"+ctx+"/images/close.gif'/>"
					+data.category
					+"</td><td></td><td>"+data.sort+"</td>"
					+"<td>"+formatter(new Date(data.lastUpdate))
					+(deep==0?"":"</td><td><a href='"+ctx+"/goodscategory/edit.php?id="+data.id+"&dpType=2'>编辑</a>&nbsp;" +
					"|&nbsp;<a data-type='delSig' data-confirm='确认删除?' data-okMsg='删除成功!' data-failMsg='删除失败' href='javascript:void(0);' data-href='"+ctx+"/goodscategory/del.php?id="+data.id+"' class='pn-opt' data-child='"+hasChild+"'>删除</a></td></tr>");
			}
			else{
				var val="<tr data-open='yes' id='"+data.id+"' class='"+_cls+"' data-child='"+hasChild+"'><td width='30'>"+index+"</td><td width='500' id='"+data.id+"'style='cursor:pointer;padding-left:"+12*parseInt(deep)+"px;'>"
					+"<img src='"+ctx+"/images/close.gif'/>"
					+data.category
					+"</td><td>"+data.parentName+"</td><td>"+data.sort+"</td>"
					+"<td>"+formatter(new Date(data.lastUpdate))
					+(deep==0?"":"</td><td><a href='"+ctx+"/goodscategory/edit.php?id="+data.id+"&dpType=2'>编辑</a>&nbsp;" +
					"|&nbsp;<a data-type='delSig' data-confirm='确认删除?' data-okMsg='删除成功!' data-failMsg='删除失败' href='javascript:void(0);' data-href='"+ctx+"/goodscategory/del.php?id="+data.id+"' class='pn-opt' data-child='"+hasChild+"'>删除</a></td></tr>");
			}
        	$("#treeDataTb tbody").append(val);
        	if(hasChild){
        		for(var i=0;i<data.nodes.length;i++){
        			doDatas(data.nodes[i],data.category,deep+1,_cls);
          	    }
            }
        }
        
       $("#consumer_query").click(function(){
        	$("#treeDataTb tbody").empty();
        	getData();
        	
        });
        function formatter(date){  
 		   var y = date.getFullYear();  
		   var m = date.getMonth()+1;  
		   var d = date.getDate();  
		   var h = date.getHours();  
		   var M = date.getMinutes();  
		   var s = date.getSeconds();  
		   function formatNumber(value){  
		     return (value < 10 ? '0' : '') + value;  
		   }    
		   return y+'-'+formatNumber(m)+'-'+formatNumber(d)+' '+formatNumber(h)+':'+formatNumber(M)+':'+formatNumber(s);  
		}
        
        getData();
        
    })
});