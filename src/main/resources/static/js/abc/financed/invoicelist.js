require(["../../config"], function () {
    require(["jquery.full","../abc/consumer/page"], function ($) {
       $(function () {
		   layui.use('laydate', function() {
			   var laydate = layui.laydate;
			   laydate.render({
				   elem: '#test30',
				   theme: '#14b9d5'
			   });
			   laydate.render({
				   elem: '#test31',
				   theme: '#14b9d5'
			   });
		   })
    	   $("[id='downLoad']").click(function(){
      		 var qurl=$(this).attr('data-qurl');
      		 var durl=$(this).attr('data-durl');
      		 var noMsg=$(this).attr('data-noMsg');
			   var val=$(this).attr('data-val');
			   if(val=="1") {
				   layer.confirm('是否导出？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
					   function (index) {
						   $.ajax({
							   url: qurl,
							   dataType: 'json',
							   success: function (data) {
								   if (data) {
									   layer.msg("导出成功", {
										   offset: abc.winscrollTop(200),
										   shade: 0.3,
										   icon: 1,
										   time: 1000
									   }, function () {
										   //window.open(durl);
										   window.location.href = durl;
									   });
								   } else {
									   layer.msg(noMsg, {
										   offset: abc.winscrollTop(200),
										   shade: 0.3,
										   icon: 5,
										   time: 2000
									   });
								   }
							   }
						   });
					   }
				   );
			   }
			   else{
				   $("#myModal2").show();
				   $("#myModal2").find(".modal-dialog").animate({'top': abc.winscrollTop(100)}, 300);
				   $(".compId").val("");
			   }
           });
		   var compId;
		   $(".compId").change(function() {
			   compId = $(this).val();
		   });
		   var type;
		   $(".type").change(function() {
			   type = $(this).val();
		   });
		   $("[id='exportbtn']").click(function(){
			   var qurl=$(this).attr('data-qurl');
			   var noMsg=$(this).attr('data-noMsg');
			   if(compId == ""||compId ==undefined){
				   abc.layerAlert(false,"请选择快递公司");
				   return;
			   }
			   var durl = ctx+"/financed/invoicekd/export.php?compId="+compId;
			   layer.confirm('是否导出？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
				   function (index) {
					   $.ajax({
						   url: qurl,
						   dataType: 'json',
						   success: function (data) {
							   if (data) {
								   layer.msg("导出成功", {
									   offset: abc.winscrollTop(200),
									   shade: 0.3,
									   icon: 1,
									   time: 1000
								   }, function () {
									   //window.open(durl);
									   window.location.href = durl;
									   $("#myModal2").hide();
								   });
							   } else {
								   layer.msg(noMsg, {
									   offset: abc.winscrollTop(200),
									   shade: 0.3,
									   icon: 5,
									   time: 2000
								   });
							   }
						   }
					   });
				   }
			   );
		   });
    	   var uploadurl='';
    	   $("[id='consumer_import']").click(function(){
			   var val=$(this).attr('data-val');
			   if(val=="1"){
				   uploadurl = $(this).attr('data-url');
				   layer.confirm('确认已导出发票信息且正确填写发票信息？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					   function(index) {
						   layer.close(index);
						   $("#myModal").show();
						   $("#myModal").find(".modal-dialog").animate({'top': abc.winscrollTop(100)}, 300);
						   $("#uploadFile").val("");
					   });
			   }
			   else{
				   uploadurl = $(this).attr('data-url');
				   layer.confirm('确认已导出快递信息且正确填写快递信息？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					   function(index) {
						   layer.close(index);
						   $("#myModal1").show();
						   $("#myModal1").find(".modal-dialog").animate({'top': abc.winscrollTop(100)}, 300);
						   $(".compId").val("");
						   $("#uploadFile1").val("");
					   });
			   }

    	   });
		   var invoice={};
		   $("[id='invoice_invalid']").click(function(){
			   //发票性质：1.纸质发票 2.电子发票
			   var val=$(this).attr('data-val');
			   if(val=="2"){
				   uploadurl = $(this).attr('data-url');
				   $("#invoiceId").val($(this).attr('data-id'));
				   layer.confirm('确认作废该发票申请或发票？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					   function(index) {
						   layer.close(index);
						   $("#myModal4").show();
						   $("#myModal4").find(".modal-dialog").animate({'top': abc.winscrollTop(100)}, 300);
					   });
			   }
			   else{
				   invoice["id"]=$(this).attr('data-id');
				   invoice['type']="0";
				   invoice['property']=val;
				   uploadurl = $(this).attr('data-url');
				   layer.confirm('可能已开票，确认作废该发票申请？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					   function(index) {
						   $.ajax({
							   type: "POST",
							   url: uploadurl,
							   data: JSON.stringify(invoice),
							   async: true,
							   contentType: "application/json",
							   dataType: "JSON",
							   success: function (data) {
								   abc.unblock();
								   if (data && data.code == "2000") {
									   layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
										   window.location.href = location.href;
									   });
								   } else {
									   abc.layerAlert(false, '操作失败: '+data.message);
								   }
							   },
							   error: function (data) {
								   abc.unblock();
								   layer.msg("操作失败", {icon: 5,title: "", closeBtn:0, offset: abc.winscrollTop(200)});
								   //abc.layerAlert(false, data.message);
							   }
						   });
					   });
			   }

		   });

		   $("[id='invalidbtn']").click(function(){
				   if(type==''){
					   abc.layerAlert(false,"请选择作废类型");
					   return;
				   }
			   invoice["id"]=$("#invoiceId").val();
			   invoice['type']=type;
			   invoice['property']="2";
				   layer.confirm('确认操作？', {
						   title: '操作提示',
						   btn: ['确认', '取消'],
						   icon: 3,
						   offset: abc.winscrollTop(200),
						   closeBtn: 0,
						   zIndex: 90000
					   },
					   function () {
						   abc.block();
						   $.ajax({
							   type: "POST",
							   url: ctx + "/financed/invoiceInvalid.php",
							   data: JSON.stringify(invoice),
							   async: true,
							   contentType: "application/json",
							   dataType: "JSON",
							   success: function (data) {
								   abc.unblock();
								   if (data && data.code == "2000") {
									   layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
										   window.location.href = location.href;
									   });
								   } else {
									   abc.layerAlert(false, '操作失败: '+data.message);
								   }
							   },
							   error: function (data) {
								   abc.unblock();
								   layer.msg("操作失败", {icon: 5,title: "", closeBtn:0, offset: abc.winscrollTop(200)});
								   //abc.layerAlert(false, data.message);
							   }
						   });
					   }
				   );
		   });
    	   $("[id='back']").click(function(){
			   var val=$(this).attr('data-val');
			   if(val=="1") {
				   $("#myModal").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
					   $("#myModal").hide();
				   });
			   }
			   else if(val=="2"){
				   $("#myModal1").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
					   $("#myModal1").hide();
				   });
			   }
			   else if(val=="4"){
				   $("#myModal4").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
					   $("#myModal4").hide();
				   });
			   }
			   else{
				   $("#myModal2").find(".modal-dialog").animate({'top': '-200px'}, 300, function () {
					   $("#myModal2").hide();
				   });
			   }
           });

    	   $(".uploadFile").change(function() {
    		   var _val=$(this).val();
    		   var types=['xls','xlsx'];
    		   var type=_val.substring(_val.lastIndexOf(".")+1);
    		   if(types.indexOf(type)<0){
    			   $(this).val('');
    			   abc.layerAlert(false,"请上传excel文件");
			   }
		   });

    	   $("[id='importbtn']").click(function(){
			   var val=$(this).attr('data-val');
			   if(val=="1") {
				   if($("#uploadFile").val()==''){
					   abc.layerAlert(false,"请选择文件");
					   return;
				   }
				   layer.confirm('确认导入？', {
						   title: '操作提示',
						   btn: ['确认', '取消'],
						   icon: 3,
						   offset: abc.winscrollTop(200),
						   closeBtn: 0,
						   zIndex: 90000
					   },
					   function () {
						   abc.block();
						   $.ajaxFileUpload({
							   url: ctx + uploadurl,
							   type: 'post',
							   secureuri: false, // 一般设置为false
							   fileElementId: 'uploadFile', // 上传文件的id、name属性名
							   dataType: 'application/json', // 返回值类型，一般设置为json、application/json
							   success: function (data, status) {
								   abc.unblock();
								   if (data.code == '2000') {
									   layer.msg("导入成功", {
										   offset: abc.winscrollTop(200),
										   shade: 0.3,
										   icon: 1,
										   time: 1000
									   }, function () {
										   window.location.href = ctx + '/financed/invoiceList.php';
									   });
								   } else {
									   abc.layerAlert(false, data.message);
								   }
							   },
							   error: function (data) {
								   abc.unblock();
								   abc.layerAlert(false, data.message);
							   }
						   });
					   }
				   );
			   }
			   else{
				   if(compId == ""||compId ==undefined){
					   abc.layerAlert(false,"请选择快递公司");
					   return;
				   }
				   if($("#uploadFile1").val()==''){
					   abc.layerAlert(false,"请选择文件");
					   return;
				   }
				   layer.confirm('确认导入？', {
						   title: '操作提示',
						   btn: ['确认', '取消'],
						   icon: 3,
						   offset: abc.winscrollTop(200),
						   closeBtn: 0,
						   zIndex: 90000
					   },
					   function () {
						   abc.block();
						   $.ajaxFileUpload({
							   url: ctx + uploadurl,
							   type: 'post',
							   secureuri: false, // 一般设置为false
							   fileElementId: 'uploadFile1', // 上传文件的id、name属性名
							   dataType: 'application/json', // 返回值类型，一般设置为json、application/json
							   data: {
								   compId: compId
							   },
							   success: function (data, status) {
								   abc.unblock();
								   if (data.code == '2000') {
									   layer.msg("导入成功", {
										   offset: abc.winscrollTop(200),
										   shade: 0.3,
										   icon: 1,
										   time: 1000
									   }, function () {
										   window.location.href = ctx + '/financed/invoiceList.php';
									   });
								   } else {
									   abc.layerAlert(false, data.message);
								   }
							   },
							   error: function (data) {
								   abc.unblock();
								   abc.layerAlert(false, data.message);
							   }
						   });
					   }
				   );
			   }
    	   });

		   $("[data-dismiss='myModal2']").click(function(){
			   $("#myModal1").find(".modal-dialog").animate({'top':'-200px'},300,function(){
				   $("#myModal1").hide();
			   });
		   });

		   $("a[data-type='lookModal']").click(function(){
			   abc.block();
			   var url=$(this).attr("data-url");
			   var iframe=document.getElementById("look_frame");
			   iframe.src=url;
			   if (iframe.attachEvent){
				   iframe.attachEvent("onload", function(){
					   //$("#myModal3").fadeIn();
					   $("#lookModal").show();
					   $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
					   abc.unblock();
				   });
			   } else {
				   iframe.onload = function(){
					   //$("#myModal3").fadeIn();
					   $("#lookModal").show();
					   $("#lookModal").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
					   abc.unblock();
				   };
			   }
		   });

		   $("[data-dismiss='lookModal']").click(function(){
			   $("#lookModal").find(".modal-dialog").animate({'top':'-700px'},500,function(){
				   $("#lookModal").hide();
			   });
		   });

		   $("a[name='fpxq']").on("click", function () {
			   var _type =($(this).attr("data-type"));
			   var _code =($(this).attr("data-code"));
			   var _no =($(this).attr("data-no"));
			   var property =($(this).attr("data-pro"));
			   $("#type").html(getInvoiceType(_type));
			   $("#property").html(getProperty(property));
			   $("#invoiceCode").html(_code);
			   $("#invoiceNo").html(_no);
			   $("#myModal3").show();
			   $("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
		   });

		   $("button[data-dismi]").click(function(){
			   //$("#myModal3").fadeOut();
			   $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
				   $("#myModal3").hide();
			   });
		   });

		   function getInvoiceType(type){
			   var invoiceTypeName;
			   $.ajax({
				   type: "GET",
				   url: ctx+"/financed/jsonDictBOs.php?dictId=invoicetype",
				   async: false,
				   contentType: "application/json",
				   dataType: "JSON",
				   success: function (data) {
					   if (data) {
						   $.each(data, function (i, item) {
							   if(type == item.fieldValue){
								   invoiceTypeName = item.fieldKey;
							   }
						   });
					   }
				   }
			   });
			   return invoiceTypeName;
		   }

		   function getProperty(obj){
			   if(obj == '1'){
				   return "纸质发票";
			   }
			   else if(obj == '2'){
				   return "电子发票";
			   }
			   else{
				   return "";
			   }
		   }
       });
    })
});