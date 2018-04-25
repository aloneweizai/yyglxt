require([ "../../config" ], function() {
	require([ "jquery.full","wangEditor","ztree","abc.ajaxfileupload","bootstrap" ], function($,Editor) {
		
		var editor = new Editor("#_topic_description_area");
        editor.customConfig.uploadImgServer = ctx+'/util/wangEditorUpload.php';
        editor.customConfig.uploadImgTimeout = 60000;
        editor.customConfig.uploadImgMaxLength = 10;
        editor.create();
        
		$(function() {

			//文件上传
			/*$("#uploadFile").change(function() {
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
       	        var types=_o.attr('data-type').split(';');
				var type=_val.substring(_val.lastIndexOf(".")+1);
				obj.hide();
       	        $("#uploadMsg").html('正在上传.....')
				/*var _val=obj.val();
				var types=obj.attr('data-type').split(';');
				var type=_val.substring(_val.lastIndexOf(".")+1);*/
				
				if(types.indexOf(type)<0){
					abc.layerAlert(false,'允许类型:['+obj.attr('data-type')+"]");
					obj.show();
					$("#uploadMsg").html('');
					return;
				}
				$.ajaxFileUpload({
					url : ctx+'/util/doFileupload.php?path=goods',
					type : 'post',
					secureuri : false, // 一般设置为false
					fileElementId : 'uploadFile', // 上传文件的id、name属性名
					dataType : 'application/json', // 返回值类型，一般设置为json、application/json
					success : function(data, status) {
						$("#upload_btn").show();
						$("#uploadMsg").html('');
						//$("#uploadFile").bind('change',function(){updat($(this))});
						if(data.code=='200'){
							$("#imgshow").html("<img height='90' width='90' style='margin-left:10px;' src='"+imgurl+data.message+"' />");
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
			
			//商品分类树
			var setting = {
	                  view: {dblClickExpand: false,showLine: false},
	                  data: {simpleData: {enable: true}},
	                  callback: {
	                      onClick: function(e,treeId, treeNode) {
	                          var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	                          zTree.expandNode(treeNode);
	                          if(treeNode.id=='1'||treeNode.id=='2')return;
	                          $("input[name='categoryName']").val(treeNode.name);
	                          $("input[name='categoryId']").val(treeNode.id).blur();
	                      }
	                  }
	        };

			var zNodes = [];
            
            $.ajax({
                type: "GET",
                url: ctx+"/goodscategory/listJson.php?",
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
              	  doData(data);
                }
            });
            function doData(data){
          	  var obj = new Object();
          	  obj.id=data.id;	
          	  obj.pId=data.parentId
          	  obj.name=data.category
          	  obj.open = true;
                obj.myAttr=data.id;
                zNodes.push(obj);
                if(data.nodes&&data.nodes.length>0){
              	  doChildren(data.nodes);
                }
            }
            function doChildren(nodes){
          	  for(var i=0;i<nodes.length;i++){
          		  doData(nodes[i]);
          	  }
            }
            
           
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	          
              $("input[name='categoryName']").click(function(){
	              $(".js_pop_ztree").show();
	     		  $(".js_pop_ztree").find(".col-sm-3").css('top','-700px').animate({'top':abc.winscrollTop(-100)},500);
	          });
	          $('body').on('click', '.js_close', function(){
	        	  $(".js_pop_ztree").find(".col-sm-3").animate({'top':'-700px'},500,function(){
        	    	 $(".js_pop_ztree").hide();
        	      })
	          });
	          
	          //规格动态
	          $("#bigGoodrule").change(function(){
	        	  var _val=$(this).val();
	        	  var this_=$("#smallGoodrule");
	        	  this_.empty().append("<option value=''>选择规格数据</option>");
	        	  $.ajax({
	                  type: "GET",
	                  url: ctx+"/util/jsonDictBOs.php?dictId="+_val,
	                  async: false,
	                  contentType: "application/json",
	                  dataType: "JSON",
	                  success: function (data) {
	                      if (data) {
	                          $.each(data, function (i, item) {
	                        	  this_.append("<option value='"+item.id +"'>" +item.fieldKey  + "</option>");
	                          });
	                      } 
	                  }
	              });
	          });
	          
	          //产品规格列表
	          var proudcts=[{'marketPrice':0,'sellingPrice':0,'costPrice':0,'stock':0,'weight':0,'uvipPriceList':[]}];
	          //规则
	          var selectBigRules={};
	          //选中产品规格价格
	          var selectXsjg=0;
	          //选中规格位置
	          var selectIndex=0;
	          
	          //select改变事件
	          $("#smallGoodrule").change(function(){
	        	  var _cando=true;
	        	  var _sml=$(this);
	        	  if(_sml.val()=="")return;
		    	  var _big=$("#bigGoodrule");
		    	  if(typeof(selectBigRules[_big.val()+"!"+_big.find("option:selected").text()])=="undefined"){
	        		  selectBigRules[_big.val()+"!"+_big.find("option:selected").text()]=new Array();
	        		  selectBigRules[_big.val()+"!"+_big.find("option:selected").text()].push(_sml.val());
	        		  for(var i=0;i<proudcts.length;i++){
	        			  var _p=proudcts[i];
	        			  _p[_big.val()]=_sml.val();
	        			  _p[_big.val()+"_tx"]=_sml.find("option:selected").text();
	        		  }
	        		  _cando=false;
	        	  }else if(typeof(selectBigRules[_big.val()+"!"+_big.find("option:selected").text()])!="undefined"
	        		  &&selectBigRules[_big.val()+"!"+_big.find("option:selected").text()].indexOf(_sml.val())<0){
	        		  selectBigRules[_big.val()+"!"+_big.find("option:selected").text()].push(_sml.val());
	        		  var len=proudcts.length;
	        		  for(var i=0;i<len;i++){
	        			  var _p=proudcts[i];
	        			  var _newObj = JSON.parse(JSON.stringify(_p)); 
	        			  _newObj[_big.val()]=_sml.val();
	        			  _newObj[_big.val()+"_tx"]=_sml.find("option:selected").text();
	        			  proudcts.push(_newObj);
	        		  }
	        	  }else{
	        		  return;
	        	  }
		    	  delSame(_cando); 
	          });
	          
	          //商品列表去重
	          function delSame(_cando){
	        	  if(_cando){
	        		  var temp=new Array();
		    		  for(var k=0;k<proudcts.length;k++){
		    			  var _pp=proudcts[k];
		    			  if(temp.length==0){
		    				  temp.push(_pp);
		    			  }else if(!($.isEmptyObject(selectBigRules))){
		    				  var cf=false;
		    				  for(var j=0;j<temp.length;j++){
		    					  var _same=true;
		    					  for(var rule in selectBigRules){
		    						  var _bg=(rule.split("!"))[0];
		    						  if(_pp[_bg]!=(temp[j])[_bg]){
		    							  _same=false;
		    							  break;
		    						  }
		    					  }
		    					  if(_same){cf=true;break;}
		    				  }
		    				  if(!cf){temp.push(_pp);}
		    			  }
		    		  }
		    		  proudcts=temp;
	        	  }
	    		  showTable();
	          }
	          //展示列表
	          function showTable(){
	        	  var _tbody=$("#guigeTY");
		    	  var _thead=$("#guigeTH");
		    	  _thead.empty();
		    	  for(var rule in selectBigRules){
		    		  var _td=$("<td>"+(rule.split("!"))[1]+"</td>");
		    		  var _a=$("<a href='javascript:void(0);' data-id='"+rule+"' class='pn-opt' style='margin-left:10px'>删</a>");
		    		  
		    		  _a.one('click',function(){
		    			  var _ru=$(this).attr('data-id');
		    			  var _big=(_ru.split("!"))[0];
		    			  //删除属性
			    		  delete selectBigRules[_ru];
			    		  for(var i=0;i<proudcts.length;i++){
			    			  delete (proudcts[i])[_big];
			    			  delete (proudcts[i])[_big+'_tx'];
			    		  }
			    		  delSame(true);
			    	  });
		  
		    		  _td.append(_a);
		    		  _thead.append(_td);
		    	  }
		    	  var checkType = $("input:radio[name='tradeMethod']:checked").val();
		    	  if(checkType=='RMB'){
		    		  _thead.append('<td width="250">成本价格(<label data-id="danwei" style="color:red">元</label>)</td><td width="250">市场价格(<label data-id="danwei" style="color:red">元</label>)</td><td width="250">销售价格(<label data-id="danwei" style="color:red">元</label>)</td><td width="100">操作</td><td style="display:none;" width="100">库存</td><td style="display:none;" width="100">重量</td>');    
		    	  }else{
		    		  _thead.append('<td width="250">成本价格(<label data-id="danwei" style="color:red">积分</label>)</td><td width="250">市场价格(<label data-id="danwei" style="color:red">积分</label>)</td><td width="250">销售价格(<label data-id="danwei" style="color:red">积分</label>)</td><td width="100">操作</td><td style="display:none;" width="100">库存</td><td style="display:none;" width="100">重量</td>'); 
		    	  }
		    	  
		    	  _tbody.empty();
		    	  for(var i=0;i<proudcts.length;i++){
		    		  var _pt=proudcts[i];
		    		  var _tr=$("<tr trindex='"+i+"'></tr>");
		    		  for(var rule in selectBigRules){
			    		  var _td=$("<td>"+_pt[(rule.split("!"))[0]+"_tx"]+"</td>");
			    		  _tr.append(_td);
			    	  }
		    		  _tr.append('<td trindex="'+i+'" width="250"><input type="text" data-did id="costPrice" style="width:90px;" value="'+_pt["costPrice"]+'"> </td>');
		    		  _tr.append('<td trindex="'+i+'" width="250"><input type="text" data-did id="marketPrice" style="width:90px;" value="'+_pt["marketPrice"]+'"> </td>');
		    		  _tr.append('<td trindex="'+i+'" width="250"><input type="text" data-di id="sellingPrice" style="width:90px;" data-rule="required;allint;" value="'+_pt["sellingPrice"]+'"></td><td><a class="btn btn-primary btn-sm" data-alert="alertDiv"><i class="glyphicon glyphicon-plus">会员组价格</i></td>');
		    		  _tr.append('<td style="display:none;" trindex="'+i+'" width="100"><input type="text"  id="stock" style="width:90px;" value="'+_pt["stock"]+'"> </td>');
		    		  _tr.append('<td style="display:none;" trindex="'+i+'" width="100"><input type="text"  id="weight" style="width:90px;" value="'+_pt["weight"]+'"> </td>');
		    		  _tbody.append(_tr);
		    	  }
		    	  
		    	  $("a[data-alert]").bind('click',function(){
		    		  vipPrice($(this));
			      });
		    	  $("input[data-did]").bind('blur',function(){
		    		  var checkType = $("input:radio[name='tradeMethod']:checked").val();
		    		  if(chek($(this).val())){
		    			  $(this).css('border-color','#ccc');
		    			  doblur($(this));
		    		  }else{
		    			  $(this).css('border-color','red').focus();
		    		  }
			      });

				  $("input[data-di]").bind('blur',function(){
					  var checkType = $("input:radio[name='tradeMethod']:checked").val();
					  if(chek_sell($(this).val())){
						  $(this).css('border-color','#ccc');
						  doblur($(this));
					  }else{
						  $(this).css('border-color','red').focus();
					  }
				  });
		    	  
	          }
	          

	          $("a[data-alert]").bind('click',function(){
	        	  vipPrice($(this));
		      });
	          $("input[data-did]").bind('blur',function(){
	        	  
	    		  if(chek($(this).val())){
	    			  $(this).css('border-color','#ccc');
	    			  doblur($(this));
	    		  }else{
	    			  $(this).css('border-color','red').focus();
	    		  } 
		      });
			$("input[data-di]").bind('blur',function(){

				if(chek_sell($(this).val())){
					$(this).css('border-color','#ccc');
					doblur($(this));
				}else{
					$(this).css('border-color','red').focus();
				}
			});

	          function chek_sell(val){
				  // ^\+{0,1}\d+(\.\d{1,2})?$
				  //^\d+\.\d{2}$
	        	  var checkType = $("input:radio[name='tradeMethod']:checked").val();
	        	  return (((/^\+{0,1}\d+(\.\d{1,2})?$/.test(val) &&  val != "0")&& checkType=='RMB')
	    				  || ((/^[0-9]+?$/.test(val)&&  val != "0") && checkType=='POINTS'));
	          }

			function chek(val){
				// ^\+{0,1}\d+(\.\d{1,2})?$
				//^\d+\.\d{2}$
				var checkType = $("input:radio[name='tradeMethod']:checked").val();
				return ((/^\+{0,1}\d+(\.\d{1,2})?$/.test(val)&& checkType=='RMB')
				|| ((/^[0-9]+?$/.test(val)) && checkType=='POINTS'));
			}

	          
	          $("button[data-dismiss]").click(function(){
	        	  $("#myModal").find(".modal-dialog").animate({'top':'-500px'},500,function(){
	        	    	$("#myModal").hide();
	        	    	$("#myModal").find("#jiageTB").find("input[id='discount']").each(function(){
        	        		$(this).css('border-color','#ccc').val(100);
        	        	});
	        	  });
	          });
	          $("button[data-save]").click(function(){
	        	  var _trs=$("#myModal").find("#jiageTB").find("tr");
	        	  var _cansub=true;
	        	  var plist=[];
	        	  _trs.each(function (){
	        		  var _tr=$(this);
	        		  var price={};
	        		  price['vipLevel']=_tr.find("#vipLevel").val();
	        		  
	        		  var _zhekou=_tr.find("#discount");
	        		  if(!isNaN(parseFloat(_zhekou.val())) && (1<=parseInt(_zhekou.val()) && parseInt(_zhekou.val()) <=100) && (/^[1-9]\d*$/.test(_zhekou.val()))){
	        			  _zhekou.css('border-color','#ccc').blur();
	        			  price['discount']=_zhekou.val();
	        			  //_tr.find("#discount").val(0);
	        		  }else{
	        			  _zhekou.css('border-color','red').focus();
	        			  _cansub=false;
	        		  }
	        		  
	        		  var _jiage=_tr.find("#tradePrice");
	        		  if(!isNaN(parseFloat(_jiage.val()))){
	        			  _jiage.css('border-color','#ccc');
	        			  price['tradePrice']=_jiage.val();
	        			 // _jiage.val(0);
	        		  }else{
	        			  _jiage.css('border-color','red').focus();
	        			  _cansub=false;
	        		  }
	        		  
	        		  var _jifen=_tr.find("#giftPoints");
	        		  if(!isNaN(parseFloat(_jifen.val())) && (/^[0-9]+?$/.test(_jifen.val())) ){
	        			  _jifen.css('border-color','#ccc');
	        			  price['giftPoints']=_jifen.val();
	        			  //_jifen.val(0);
	        		  }else{
	        			  _jifen.css('border-color','red').focus();
	        			  _cansub=false;
	        		  }
	        		  plist.push(price);
	        		  //((proudcts[selectIndex])["uvipPriceList"]).push(price);
	        	  });
	        	  if(_cansub){
	        		  //$("#myModal").hide();
	        		  (proudcts[selectIndex])["uvipPriceList"]=plist;
	        		  $("#myModal").find(".modal-dialog").animate({'top':'-500px'},500,function(){
	            	    	$("#myModal").hide();
	            	    	$("#myModal").find("#jiageTB").find("input[id='discount']").each(function(){
	        	        		$(this).css('border-color','#ccc').val(100);
	        	        	});
	            	  })
	        	  }
	          });
	          
	          $("input[id='discount']").blur(function(){
	        	  var _bb=$(this);
	        	  var _v=parseInt(_bb.val());
				  //^[1-9]\d*$
	        	  if(!(1<=_v && _v <=100) || !(/^[1-9]\d*$/.test(_bb.val())) ){
	        		  _bb.css('border-color','red').focus();
	        	  }else{
	        		  _bb.css('border-color','#ccc');
	        		  var _tr=_bb.parent().parent();
	        		  var checkType = $("input:radio[name='tradeMethod']:checked").val();
	        		  if(checkType=='RMB'){
	        			  _tr.find("#tradePrice").val(Math.round((selectXsjg*parseFloat(_v)/100)*100)/100);
	        		  }else{
	        			  _tr.find("#tradePrice").val(Math.ceil(selectXsjg*parseFloat(_v)/100));
	        		  }
	        	  }
	          });
	          
	          function vipPrice(object){
	        	  var _ob=$(object);
	        	  var _pr=_ob.parent().prev();
	        	  selectIndex=parseInt(_pr.attr("trindex"));
				  var checkType = $("input:radio[name='tradeMethod']:checked").val();
	        	  selectXsjg=_pr.find("#sellingPrice").val();
	        	  if(!chek_sell(selectXsjg)){
	        		  abc.layerAlert(false,'请先设置正确的销售价格!');
	        		  return ;
	        	  }
	        	  var _jgs=(proudcts[selectIndex])['uvipPriceList'];
	        	  if(_jgs.length>0){
	        		  var _trs=$("#myModal").find("#jiageTB");
	        		  for(var i=0;i<_jgs.length;i++){
	        			  var _vip=(_jgs[i])['vipLevel'];
	        			  _trs.find("tr[data-vip='"+_vip+"']").find("#tradePrice").val((_jgs[i])['tradePrice']).css('border-color','#ccc');
	        			  _trs.find("tr[data-vip='"+_vip+"']").find("#discount").val((_jgs[i])['discount']).css('border-color','#ccc');
						  _trs.find("tr[data-vip='"+_vip+"']").find("#giftPoints").val((_jgs[i])['giftPoints']).css('border-color','#ccc');

	        		  }
	        	  }
				  else{
					  $("#myModal").find("#jiageTB").find("input[id='discount']").each(function(){
						  $(this).css('border-color','#ccc').val(100);
					  });
				  }
	        	  $("#jiageTB").find("input[id='discount']").each(function(){
	        		  $(this).blur();
	        	  })
	        	  $("#myModal").show();
	     		  $("#myModal").find(".modal-dialog").css('top','-500px').animate({'top':abc.winscrollTop(100)},500);
	        	  /*$("#myModal").find(".modal-dialog").css('top',abc.winscrollTop(100));
	        	  $("#myModal").show();*/
	          }
	          
	          
	          function doblur(object){
	        	  var _bo=$(object);
	        	  var _pr=_bo.parent();
	        	  var _index=parseInt(_pr.attr("trindex"));
	        	  (proudcts[_index])[_bo.attr('id')]=_bo.val();
	          }
	          
	          
	          //表单提交
	          var myrules={};
	    	  
	    	  
	    	  var $validatorWsysVoFrom = $("form").validator({
	    		  timely: 1,
	    		  focusCleanup:true,
	              theme: 'yellow_right_effect',
	              rules: {
	            	  allint:[/^\d+$/, '请输入整数']
	              }
	          });
	          $validatorWsysVoFrom.validator().trigger("showtip");
	    	  
	          var good={};
	          $("#consumer_submit").click(function(){
	        	  var _back=false;
	        	  var _obj;
	        	  if($validatorWsysVoFrom.isValid()){
					  var checkType = $("input:radio[name='tradeMethod']:checked").val();
					  var points =  $('input[name="giftPoints"]').val();
					  
					  $("input[data-did]").each(function(){
	    	    		  if(!chek($(this).val())){
	    	    			  _back=true;
	    	    			  _obj=$(this);
	    	    			  return;
	    	    		  } 
	    		      });

					  $("input[data-di]").each(function(){
						  if(!chek_sell($(this).val())){
							  _back=true;
							  _obj=$(this);
							  return;
						  }
					  });
	        		  if(_back){
	        			  _obj.css('border-color','red').focus();
    	    			  abc.layerAlert(false,'请先设置正确的价格!');
    	    			  return;
	        		  }

					  for(var k=0;k<proudcts.length;k++) {
						  if(parseFloat((proudcts[k])['sellingPrice'])<parseFloat((proudcts[k])['costPrice'])){
							  abc.layerAlert(false,'商品销售价格不可低于商品成本价格!');
							  return;
						  }
						  if(parseFloat((proudcts[k])['sellingPrice'])<parseFloat((proudcts[k])['marketPrice'])){
							  abc.layerAlert(false,'商品销售价格不可低于商品市场价格!');
							  return;
						  }
					  }

					  if(checkType == 'POINTS'){
						  var pointflag = 0;
						  var vipflag = 0;
						  var vipflag1 = 0;
						  $("[id='sellingPrice']").each(function(){
							  if(parseInt(points)> parseInt(this.value)){
								  pointflag+=1;
							  }
						  });
						  if(pointflag>0){
							  abc.layerAlert(false,'赠送积分不可超过商品本身所值积分,请重新设置赠送积分!');
							  $('input[name="giftPoints"]').css('border-color','red').focus();
							  return;
						  }
						  var _jgs=(proudcts[selectIndex])['uvipPriceList'];
						  if(_jgs.length>0){
							  for(var i=0;i<_jgs.length;i++){
								  if(parseInt(points)> parseInt((_jgs[i])['tradePrice'])){
									  vipflag+=1;
								  }
							  }
							  for(var i=0;i<_jgs.length;i++){
								  if(parseInt((_jgs[i])['giftPoints'])> parseInt((_jgs[i])['tradePrice'])){
									  vipflag1+=1;
								  }
							  }
							  if(vipflag>0){
								  abc.layerAlert(false,'赠送积分不可超过商品本身所值积分,请重新设置赠送积分!');
								  $('input[name="giftPoints"]').css('border-color','red').focus();
								  return;
							  }
							  if(vipflag1>0){
								  abc.layerAlert(false,'赠送积分不可超过商品本身所值积分,请重新设置赠送积分!');
								  return;
							  }
						  }
					  }
	        		  good=JSON.parse($("form").serializeJson());
	                  good["details"]=editor.txt.html();
	                  if(good.goodsType=='4'){
	                	  good["memberLevel"]=$("#memberLevel").val().split("!")[0];
	                  }
	                  for(var k=0;k<proudcts.length;k++){
		    			  var _pp=proudcts[k];
		    			  var dictList=[];
		    			  for(var rule in selectBigRules){
    						  var _bg=(rule.split("!"))[0];
    						  var _dictiD={};
    						  _dictiD["id"]=_pp[_bg];
    						  dictList.push(_dictiD);
    					  }
		    			  _pp["dictList"]=dictList;
	                  }	                  
	                  good["productBOList"]=proudcts;
	                  abc.layerAjaxConfirm("POST", $("form").attr('action'),  JSON.stringify(good), document.referrer);
	              }
	          });

			$("a[name='good_open_dic']").off("click").on("click",function(){
				var url = ctx + "/system/code/list.php";

				var $parentTabNode = parent.$('#tt');
				var exist_tab = $parentTabNode.tabs('getTab', "数据字典管理");
				if (exist_tab) {
					$parentTabNode.tabs('close', "数据字典管理");
				}
				parent.addPanel("数据字典管理", url, "1200");
			});
			
			$("#goodsType").change(function(){
				var _val=$(this).val();
				if(_val=='4'){
					$("#viplevel").css('display','table-row');
					$("#name").attr('readonly','readonly').val($("#memberLevel").val().split("!")[1]);
					$("[data-alert='alertDiv']").css('display','none');
					$("#wodeguig").css('display','none');
				}else{
					$("#viplevel").css('display','none');
					$("#name").removeAttr('readonly').val('');
					$("[data-alert='alertDiv']").css('display','block');
					$("#wodeguig").css('display','table-row');
				}

				if(_val=='2'){
					$("#isShipping2").attr('checked','checked');
					$("#isShipping1").attr('disabled','disabled');
				}else{
					$("#isShipping1").removeAttr('disabled')
				}
			});
			
			$("#memberLevel").change(function(){
				$("#name").val($(this).val().split("!")[1]);
			});


			$('input[name="tradeMethod"]').on('click',function (){
				var isChecked=$(this).val();
				if(isChecked=='POINTS'){
					$("[id='sellingPrice']").val("0");
				}
			});
			
			$("input:radio[name='tradeMethod']").click(function(){
				if($(this).val()=='RMB'){
					$("[data-id='danwei']").text("元");
				}else{
					$("[data-id='danwei']").text("积分");
				}
			})
			
		});
	});
});
