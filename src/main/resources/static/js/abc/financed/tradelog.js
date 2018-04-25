require(["../../config"], function () {
    require(["jquery.full","../abc/consumer/page"], function ($) {
       $(function () {
    	   
    	   $("#tradeLog_import").click(function(){
    		   $("#myModal").show();
        	   $("#myModal").find(".modal-dialog").animate({'top':abc.winscrollTop(100)},300);
    	   });
    	   
    	   $("#back").click(function(){
        	   $("#myModal").find(".modal-dialog").animate({'top':'-200px'},300,function(){
        	    	$("#myModal").hide();
        	   });
           });
    	   
    	   $("#uploadFile").change(function() {
    		   var _val=$(this).val();
    		   var types=['csv'];
    		   var type=_val.substring(_val.lastIndexOf(".")+1);
    		   if(types.indexOf(type)<0){
    			   $(this).val('');
    			   abc.layerAlert(false,"请上传csv文件");
			   }
		   });
    	   
    	   $("#importbtn").click(function(){
    		   if($("#uploadFile").val()==''){
    			   abc.layerAlert(false,"请选择文件");
    			   return;
    		   }
    		   layer.confirm('确认导入？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                   function(){
               	     abc.block();
               	     $.ajaxFileUpload({
    					url : ctx+'/tradelog/alipay/import.php',
    					type : 'post',
    					secureuri : false, // 一般设置为false
    					fileElementId : 'uploadFile', // 上传文件的id、name属性名
    					dataType : 'application/json', // 返回值类型，一般设置为json、application/json
    					success : function(data, status) {
    						abc.unblock();
    						if(data.code=='2000'){
    							layer.msg("导入成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    window.location.href = ctx+'/tradelog/list.php';
                                });
    						}else{
    							abc.layerAlert(false,data.message);
    						}
    					},
    					error : function(data, status, e) {
    						abc.layerAlert(false,e);
    					}
    				 });    
                   }
               );
    		   
    	   });
    	   
    	   
    	   $("a[id='sgdz']").click(function(){
    		   var _obj=$(this);
    		   $("#orderno").html(_obj.attr('data-orderNo'));
    		   $("#ordertype").html((_obj.attr('data-tradeType')=='1')?'付款':'退款');
    		   $("#orderamount").html(_obj.attr('data-amount'));
    		   $('#qrdz').css('display','none').attr('data-href',ctx+'/tradelog/dovalid.php?type=valid&id='+_obj.attr('data-id'));
    		   $('#qrzf').css('display','none').attr('data-href',ctx+'/tradelog/dovalid.php?type=invalid&id='+_obj.attr('data-id'));
			   var payMethod = _obj.attr('data-payMethod');
			   if(payMethod == 'POINTS'){
				   $("#title").html("支付交易信息");
				   $("#qresult").html("<td colspan='3'><span style='color:red;'>正在查询支付信息数据......</span></td>");
				   $.ajax({
					   type: "GET",
					   url: ctx + "/tradelog/points/query.php?trade_no=" + _obj.attr('data-orderNo'),
					   async: true,
					   contentType: "application/json",
					   dataType: "JSON",
					   success: function (data) {
						   if (data.code == '2000') {
							   var _result = '';
							   if ('2' == data.tradeStatus) {
								   _result = '支付成功';
							   } else if ('1' == data.tradeStatus) {
								   _result = '等待买家付款';
							   } else if ('3' == data.tradeStatus) {
								   _result = '交易失败';
							   }
							   else if ('4' == data.tradeStatus) {
								   _result = '取消交易';
							   }
							   else {
								   _result = '交易结束';
							   }
							   $("#qresult").html("<td>" + data.tradeNo + "</td><td>" + parseInt(data.amount) + "积分</td><td>" + _result + "</td>")
						   } else {
							   $("#qresult").html("<td colspan='3'>" + data.message + "</td>");
						   }
						   $('#qrdz').css('display', 'inline-block');
						   $('#qrzf').css('display', 'inline-block');
					   }
				   });
			   }
			   else if(payMethod == 'ALIPAY'){
				   $("#title").html("第三方支付交易信息");
				   $("#qresult").html("<td colspan='3'><span style='color:red;'>正在查询第三方支付系统......</span></td>");
				   $.ajax({
					   type: "GET",
					   url: ctx + "/tradelog/alipay/query.php?out_trade_no=" + _obj.attr('data-orderNo') + "&trade_no=" + _obj.attr('data-aliTrandeNo'),
					   async: true,
					   contentType: "application/json",
					   dataType: "JSON",
					   success: function (data) {
						   if (data.code == '10000') {
							   var _result = '';
							   if ('TRADE_SUCCESS' == data.trade_status) {
								   _result = '支付成功';
							   } else if ('WAIT_BUYER_PAY' == data.trade_status) {
								   _result = '等待买家付款';
							   } else if ('TRADE_CLOSED' == data.trade_status) {
								   _result = '交易超时关闭或支付完成后全额退款';
							   } else {
								   _result = '交易结束';
							   }
							   $("#qresult").html("<td>" + data.out_trade_no + "</td><td>￥" + parseFloat(data.total_amount).toFixed(2) + "</td><td>" + _result + "</td>")
						   } else {
							   $("#qresult").html("<td colspan='3'>" + data.message + "</td>");
						   }
						   $('#qrdz').css('display', 'inline-block');
						   $('#qrzf').css('display', 'inline-block');
					   }
				   });
			   }
			   else if(payMethod == 'WEIXIN'){
				   $("#title").html("第三方支付交易信息");
				   $("#qresult").html("<td colspan='3'><span style='color:red;'>正在查询微信支付信息数据......</span></td>");
				   $.ajax({
					   type: "GET",
					   url: ctx + "/tradelog/weixin/query.php?out_trade_no=" + _obj.attr('data-orderNo') + "&transaction_id=" + _obj.attr('data-aliTrandeNo')+"&tradeType="+_obj.attr('data-tradeType'),
					   async: true,
					   contentType: "application/json",
					   dataType: "JSON",
					   success: function (data) {
						   if (data.code == '2000') {
							   if(_obj.attr('data-tradeType')=='1'){
								   $("#qresult").html("<td>" + data.out_trade_no + "</td><td>￥" + parseFloat(data.cash_fee).toFixed(2) + "</td><td>" + data.trade_state_desc + "</td>")
							   }
							   else{
								   var _result = '';
								   if ('SUCCESS' == data.refund_status_0) {
									   _result = '交易成功';
								   }
								   $("#qresult").html("<td>" + data.out_trade_no + "</td><td>￥" + parseFloat(data.cash_fee).toFixed(2) + "</td><td>" + _result + "</td>")
							   }
						   } else {
							   $("#qresult").html("<td colspan='3'>" + data.message + "</td>");
						   }
						   $('#qrdz').css('display', 'inline-block');
						   $('#qrzf').css('display', 'inline-block');
					   }
				   });
			   }
    		   
    		   $("#myModal1").show();
        	   $("#myModal1").find(".modal-dialog").animate({'top':abc.winscrollTop(100)},300);
    	   });
    	   
    	   $("[data-dismiss='modal1']").click(function(){
        	   $("#myModal1").find(".modal-dialog").animate({'top':'-200px'},300,function(){
        	    	$("#myModal1").hide();
        	   });
           });
       });
    })
});