require(["../../config"], function () {
    require(["jquery.full","abc.common","autocomplete","../abc/consumer/page"], function ($) {
      $(function () {
		  var distParnet;
		  var $validatorWsysVoFrom = $("form").validator({
			  timely: 1,
			  focusCleanup:true,
			  theme: 'yellow_right_effect',
			  rules: {
				  allint:[/^(-|\+)?\d+$/, '请输入整数']
			  }
		  });
		  $validatorWsysVoFrom.validator().trigger("showtip");
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
		  $("#editBtn").on("click", function () {
			  if($validatorWsysVoFrom.isValid()) {
				  var tableEl = document.getElementById("fptable");
				  var rows = tableEl.rows;
				  var repoNum;
				  var applyNum;
				  var num1 = 0;
				  var num2 = 0;
				  var num3 = 0;
				  var num4 = 0;
				  var invoiceTypeCode;
				  for(var i = 1;i<rows.length;i++) {
					  var index = rows[i].getAttribute("did");
					  repoNum = document.getElementById("repoNum"+index).value;
					  applyNum = document.getElementById("applyNum"+index).value;
					  invoiceTypeCode = document.getElementById("invoiceTypeCode"+index).value;
					  if(invoiceTypeCode=='ZZSPTFP-'){
						  num1+=1;
					  }
					  if(invoiceTypeCode=='DZZZSPTFP-'){
						  num2+=1;
					  }
					  if(invoiceTypeCode=='ZZSZYFP-'){
						  num3+=1;
					  }
					  if(invoiceTypeCode=='TYJDFP-'){
						  num4+=1;
					  }
					if(parseInt(applyNum)>parseInt(repoNum)){
						layer.msg('申请数量不能大于库存数量，请重新填写第'+i+'行申请数量。', {offset: abc.winscrollTop(200), icon: 5});
						return;
					}
				  }
				  if(num1>1){
					  layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					  return;
				  }
				  if(num2>1){
					  layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					  return;
				  }
				  if(num3>1){
					  layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					  return;
				  }
				  if(num4>1){
					  layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					  return;
				  }
					  var applyUser = $('input[name="user"]').val();
					  var issueStatus = $('input[name="issueStatus"]').val();
					  var examineStatus = $('input[name="examineStatus"]').val();
					  var endTime = $('input[name="endTime"]').val();
					  var startTime = $('input[name="startTime"]').val();
				  var page = $('input[name="page"]').val();
				  layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
					  function (index) {
						  $.ajax({
							  type: "POST",
							  url: ctx + "/financed/save.php?type=3",
							  data: $('#invoiceLy').serializeJson(),
							  async: false,
							  contentType: "application/json",
							  dataType: "JSON",
							  success: function (data) {
								  if (data.result.code == '2000') {
									  layer.msg("操作成功", {
										  offset: abc.winscrollTop(200),
										  shade: 0.3,
										  icon: 1,
										  time: 1000
									  }, function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
										  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
									  });
									  setTimeout(function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
										  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
									  }, 2000);
								  } else {
									  layer.msg(data.result.message, {offset: abc.winscrollTop(200), icon: 5});
								  }
							  }
						  });
					  }
				  );
			  }
		  })
		  $(".commitBtn").on("click", function () {
			 if($validatorWsysVoFrom.isValid()) {
				 var tableEl = document.getElementById("fptable");
				 var rows = tableEl.rows;
				 var repoNum;
				 var applyNum;
				 var num1 = 0;
				 var num2 = 0;
				 var num3 = 0;
				 var num4 = 0;
				 var invoiceTypeCode;
				 for(var i = 1;i<rows.length;i++){
					 var index = rows[i].getAttribute("did");
					 repoNum = document.getElementById("repoNum"+index).value;
					 applyNum = document.getElementById("applyNum"+index).value;
					 invoiceTypeCode = document.getElementById("invoiceTypeCode"+index).value;
					 if(invoiceTypeCode=='ZZSPTFP-'){
						 num1+=1;
					 }
					 if(invoiceTypeCode=='DZZZSPTFP-'){
						 num2+=1;
					 }
					 if(invoiceTypeCode=='ZZSZYFP-'){
						 num3+=1;
					 }
					 if(invoiceTypeCode=='TYJDFP-'){
						 num4+=1;
					 }
					 if(parseInt(applyNum)>parseInt(repoNum)){
						 layer.msg('申请数量不能大于库存数量，请重新填写第'+i+'行申请数量。', {offset: abc.winscrollTop(200), icon: 5});
						 return;
					 }
				 }
				 if(num1>1){
					 layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					 return;
				 }
				 if(num2>1){
					 layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					 return;
				 }
				 if(num3>1){
					 layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					 return;
				 }
				 if(num4>1){
					 layer.msg('同一个申请里不能有多条相同发票种类申请！', {offset: abc.winscrollTop(200), icon: 5});
					 return;
				 }
					 var applyUser = $('input[name="user"]').val();
					 var issueStatus = $('input[name="issueStatus"]').val();
					 var examineStatus = $('input[name="examineStatus"]').val();
					 var endTime = $('input[name="endTime"]').val();
					 var startTime = $('input[name="startTime"]').val();
				 var page = $('input[name="page"]').val();
				  layer.confirm('是否提交？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
					  function (index) {
						  $.ajax({
							  type: "POST",
							  url: ctx + "/financed/save.php?type=0",
							  data: $('#invoiceLy').serializeJson(),
							  async: false,
							  contentType: "application/json",
							  dataType: "JSON",
							  success: function (data) {
								  if (data.result.code == '2000') {
									  layer.msg("操作成功", {
										  offset: abc.winscrollTop(200),
										  shade: 0.3,
										  icon: 1,
										  time: 1000
									  }, function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
										  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
									  });
									  setTimeout(function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
										  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
									  }, 2000);
								  } else {
									  layer.msg(data.result.message, {offset: abc.winscrollTop(200), icon: 5});
								  }
							  }
						  });
					  }
				  );
			  }
		  })

		  $('#checkBtn').click(function () {
			  $("#myModal").show();
		  });

		  $("#checkSubmit").on("click", function () {
			  var checkType = $("input:radio[name='examineStatus']:checked").val();
			  var applyUser = $('input[name="user"]').val();
			  var issueStatus = $('input[name="isStatus"]').val();
			  var examineStatus = $('input[name="exStatus"]').val();
			  var endTime = $('input[name="endTime"]').val();
			  var startTime = $('input[name="startTime"]').val();
			  var page = $('input[name="page"]').val();
			  if($validatorWsysVoFrom.isValid()) {
				  layer.confirm('是否审核？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
					  function (index) {
						  $.ajax({
							  type: "POST",
							  url: ctx + "/financed/invoiceLyCheck.php?type=" + checkType,
							  data: $('#invoiceLy_check').serializeJson(),
							  async: false,
							  contentType: "application/json",
							  dataType: "JSON",
							  success: function (data) {
								  if (data.result.code == '2000') {
									  layer.msg("操作成功", {
										  offset: abc.winscrollTop(200),
										  shade: 0.3,
										  icon: 1,
										  time: 1000
									  }, function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
										  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
									  });
									  setTimeout(function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser=" + applyUser + "&&issueStatus=" + issueStatus +
										  "&&examineStatus=" + examineStatus + "&&startTime=" + startTime + "&&endTime=" + endTime+"&&page="+page;
									  }, 2000);
								  } else {
									  layer.msg(data.result.message, {offset: abc.winscrollTop(200), icon: 5});
								  }
							  }
						  });
					  }
				  );
			  }
		  })

		  $("#signBtn").on("click", function () {
			  var applyUser = $('input[name="user"]').val();
			  var issueStatus = $('input[name="isStatus"]').val();
			  var examineStatus = $('input[name="exStatus"]').val();
			  var endTime = $('input[name="endTime"]').val();
			  var startTime = $('input[name="startTime"]').val();
			  var page = $('input[name="page"]').val();
			  layer.confirm('是否签收？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
				  function(index){
					  $.ajax({
						  type: "POST",
						  url: ctx+"/financed/invoiceLysign.php",
						  data: $('#invoiceLy_sign').serializeJson(),
						  async: false,
						  contentType: "application/json",
						  dataType: "JSON",
						  success: function (data) {
							  if (data.result.code == '2000') {
								  layer.msg("操作成功", {
									offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},
									  function () {
									  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
									  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
								  });
								  setTimeout(function () {
									  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
									  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
								  }, 2000);
							  } else {
								  layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
							  }
						  }
					  });
				  }
			  );
		  });

		  $("#distSubmit").on("click", function () {
			  var oselectall = $('input[name="realNum"]');
			  var bool=true;
			  oselectall.each(function() {
				  if(this.value == null || this.value == ''|| this.value == '0'){
					  bool = false;
				  }
			  })
			  if(bool){
				  var applyUser = $('input[name="user"]').val();
				  var issueStatus = $('input[name="isStatus"]').val();
				  var examineStatus = $('input[name="exStatus"]').val();
				  var endTime = $('input[name="endTime"]').val();
				  var startTime = $('input[name="startTime"]').val();
				  var page = $('input[name="page"]').val();
				  layer.confirm('是否分发？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					  function(index){
						  $.ajax({
							  type: "POST",
							  url: ctx+"/financed/invoiceLydist.php?type=1",
							  data: $('#invoice_dist').serializeJson(),
							  async: false,
							  contentType: "application/json",
							  dataType: "JSON",
							  success: function (data) {
								  if (data.result.code == '2000') {
									  layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},
										  function () {
											  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
											  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
										  });
									  setTimeout(function () {
										  window.location.href = ctx + "/financed/invoiceLyList.php?applyUser="+applyUser+"&&issueStatus="+issueStatus+
										  "&&examineStatus=" +examineStatus+"&&startTime="+startTime+"&&endTime="+endTime+"&&page="+page;
									  }, 2000);
								  } else {
									  layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
								  }
							  }
						  });
					  }
				  );
			  }
			  else{
				  layer.msg("请选择分发数量!", {offset: abc.winscrollTop(200),icon: 5});
			  }

		  })
		  //a删除
		  $("a[data-type='delSig']").click(function () {
			  abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
		  });

		  $('#closeBtn').click(function () {
			  var invoiceLyRsId = $("#invoiceLyRsId").val();
			  window.location.href = ctx + "/financed/invoiceLyDetail.php?id="+invoiceLyRsId;
		  });

		  $('#consumer_query').on("click", function () {
			  abc.block();
			  $('#consumerListForm').submit();
		  });
          var idNum = 0;
		  if($('.fpsize').val() != 0){
			  idNum = parseInt($('.fpsize').val())-1;
		  }
		  else{
			  idNum = parseInt($('.fpsize').val());
		  }
		  $('.mdtable').on("click",".jia",function(){
			  idNum+=1;
			  $.ajax({
				  type: "GET",
				  url: ctx+"/financed/jsonDictBOs.php?dictId=invoicetype",
				  async: false,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  if (data) {
						  var trs=$('.mdtable tr');
						  var ran=Math.random();
						  var html= '<tr did="'+idNum+'"><td class="mdtablethtd"><select class="invoiceType" name="invoiceTypeCode" data-rule="required;" style="width:200px;height:30px;" id="invoiceTypeCode'+idNum+'"><option value="">-- 请选择 --</option>';
						  $.each(data, function (i, item) {
							  html+= "<option value='" + eval("item.fieldValue") +  "'>"+ eval("item.fieldKey") + "</option>";
						  });
						  html+= '</select></td><td class="mdtablethtd"><input type="text" readonly="readonly" style="width: 100px;" class="repoNum layui-input" name="repoNum" value="" id="repoNum'+idNum+'"></td>';
						  html+= '<td class="mdtablethtd"><input type="text" readonly="readonly" style="width: 100px;" name="usableShare" value="" class="layui-input" id="usableShare'+idNum+'"></td>';
						  html+= '<td class="mdtablethtd"><input type="text" style="width: 100px;" data-rule="required;integer(+);length[0~4];" class="applyNum layui-input" name="applyNum" value="" id="applyNum'+idNum+'"></td>';
						  html+= '<td class="mdtablethtd"><input type="text" id="fpremark"  name="fpremark" value=" " class="layui-input"><input type="hidden" style="width: 100px;" name="fpmxId" value=" "></td>';
						  //html+= '<td class="mdtablethtd"><textarea name="fpremark" id="remark'+idNum+'">&nbsp;</textarea></td>';
						  html+= '<td class="mdtablethtd"><span  name="jia" class="jia">+</span><span class="jian"  name="jian" delid="">-</span></td>';
						  html+= '</tr>';
						  $('.mdtable').append(html);
						  $(".mdtable").on('change', '.invoiceType',
							  function() {
								  var _val=$(this).val();
								  var trNode = $(this).parent().parent();
								  $.ajax({
									  type: "GET",
									  url: ctx+"/financed/invoiceRepoNum.php?invoiceTypeCode="+_val,
									  async: false,
									  contentType: "application/json",
									  dataType: "JSON",
									  success: function (data) {
										  if (data) {
											  trNode.children().eq(1).children().val(data.repoNum);
											  trNode.children().eq(2).children().val(data.usableShare);
										  }
									  }
								  });
							  });
					  } else {
					  }
				  }
			  });
		  })

		  $('.mdtable').on("click",".jian",function(){

			  var tr=$(this).parent().parent();
			  var trs=$('.mdtable tr');
			  if(trs.length<=2){
				  layer.msg("已经是最少行不能删除!", {offset: abc.winscrollTop(200),icon: 5});
			  }else{
				  var delid=$(this).attr('delid');
				  if(delid==''){
					  tr.remove();
				  }else{
					  layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset:abc.winscrollTop(200),closeBtn: 0},
						  function(index) {
							  $.ajax({
								  type: "POST",
								  url: ctx+"/cms/activity/sponsor/"+delid ,
								  async: false,
								  contentType: "application/json",
								  dataType: "JSON",
								  success: function (data) {
									  if(data.result.code=='2000'){
										  tr.remove();
										  layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
									  }else{
										  layer.msg(data.result.message, {offset: abc.winscrollTop(200),icon: 5});
									  }
								  }
							  });
						  }
					  );
				  }
			  }
		  })

		 $(".invoiceType").change(function(){
			  var _val=$(this).val();
			  var trNode = $(this).parent().parent();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/financed/invoiceRepoNum.php?invoiceTypeCode="+_val,
				  async: false,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  if (data) {
						  trNode.children().eq(1).children().val(data.repoNum);
						  trNode.children().eq(2).children().val(data.usableShare);
					  }
				  }
			  });
		  });
		  $('#fprepo_query').click(function () {
			  cupageVal=1;
			  var noStart = $("#noStart").val();
			  var noEnd = $("#noEnd").val();
			  var invoiceNoStart = $("#invoiceNoStart").val();
			  var invoiceNoEnd = $("#invoiceNoEnd").val();
			  if($("#invoiceNoEnd").val()==""||$("#invoiceNoEnd").val() == null){
				  invoiceNoEnd = $("#invoiceNoStart").val();
			  }
			  if($("#invoiceNoStart").val()==""||$("#invoiceNoStart").val() == null){
				  invoiceNoStart = $("#invoiceNoEnd").val();
			  }
			  if($("#noEnd").val()==""||$("#noEnd").val() == null){
				  noEnd = $("#noStart").val();
			  }
			  if($("#noStart").val()==""||$("#noStart").val() == null){
				  noStart = $("#noEnd").val();
			  }
			  var invoiceTypeCode = $("#invoiceTypeCode").val();
			  var invoiceCode = $("#invoiceCode").val();
			  var size = $("#consumer_size").val();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/financed/invoiceLyRepoList.php?status=0&invoicetype="+invoiceTypeCode+"&invoiceCode="+invoiceCode+"&noStart="+noStart
				  +"&noEnd="+noEnd+"&invoiceNoStart="+invoiceNoStart+"&invoiceNoEnd="+invoiceNoEnd+"&page=1"+"&size="+size,
				  async: false,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  if (data) {
						  $('#fprepo').empty().append(data.bodyHtml);
						  $(".mdtable").on('click', '.nycon_sel_btn',
							  function() {
								  var oselectall = $('input[name="ids"]')
								  var bool=false;
								  oselectall.each(function() {
									  if (this.checked == false) {
										  bool=true;
									  }
								  })
								  if(bool){
									  oselectall.each(function() {
										  this.checked = true;
									  })
								  }else{
									  oselectall.each(function() {
										  this.checked = false;
									  })
								  }
							  });
					  } else {
					  }
				  }
			  });
		  });

		  var goPage= function(index) {
			  if (isNaN($("#consumer_size").val())
				  || $("#consumer_size").val() == '') {
				  $("#consumer_size").val(15);
			  }
			  var size =  $("#consumer_size").val();
			  var totalPage = parseInt($("#topageVal").val());
			  if ((index < 1 || index == cupageVal || index > totalPage) && index != -1) {
				  return;
			  } else if (index == -1) {
				  index = 1;
			  }
			  var noStart = $("#noStart").val();
			  var noEnd = $("#noEnd").val();
			  var invoiceNoStart = $("#invoiceNoStart").val();
			  var invoiceNoEnd = $("#invoiceNoEnd").val();
			  var invoiceTypeCode = $("#invoiceTypeCode").val();
			  var invoiceCode = $("#invoiceCode").val();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/financed/invoiceLyRepoList.php?status=0&invoicetype="+invoiceTypeCode+"&invoiceCode="+invoiceCode+"&noStart="+noStart
				  +"&noEnd="+noEnd+"&invoiceNoStart="+invoiceNoStart+"&invoiceNoEnd="+invoiceNoEnd+"&page="+index+"&size="+size,
				  async: false,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  if (data) {
						  $('#fprepo').empty().append(data.bodyHtml);
						  $(".mdtable").on('click', '.nycon_sel_btn',
							  function() {
								  var oselectall = $('input[name="ids"]')
								  var bool=false;
								  oselectall.each(function() {
									  if (this.checked == false) {
										  bool=true;
									  }
								  })
								  if(bool){
									  oselectall.each(function() {
										  this.checked = true;
									  })
								  }else{
									  oselectall.each(function() {
										  this.checked = false;
									  })
								  }
							  });
					  } else {
					  }
				  }
			  });
			  cupageVal = index;
			  $('#page').text(index);

		  }
		  var cupageVal=1;
		  $('[id="distBtn"]').click(function () {
			  var _val =$(this).attr('data-value');
			 distParnet = $(this).parent().parent();
			  cupageVal=1;
			  $("#modal-dialog").fadeIn();
			  document.getElementById("distlist").src=ctx+"/financed/RepoList.php?invoicetype=" +_val;
		  });
		  $("button[data-dismiss]").click(function(){
			  $("#modal-dialog").fadeOut();
		  });
		  $('.nycon_sel_btn').on('click',function (){
			  var oselectall = $('input[name="ids"]')
			  var bool=false;
			  oselectall.each(function() {
				  if (this.checked == false) {
					  bool=true;
				  }
			  })
			  if(bool){
				  oselectall.each(function() {
					  this.checked = true;
				  })
			  }else{
				  oselectall.each(function() {
					  this.checked = false;
				  })
			  }
		  });

		  $('#fpdistBtn').click(function () {
			  var checkBook =  distParnet.children().eq(5).children().val();
			  var oselectall = document.getElementById('distlist').contentWindow.document.getElementsByName("ids");
			  var ids='';
			  var num = 0;
			  for (var i = 0; i < oselectall.length; i++)
			  {
				  if(oselectall[i].checked == true)
				  {
					  ids+=oselectall[i].value+',';
					  num +=1;
				  }

			  }
			  if(num < checkBook){
				  var str = "申请"+checkBook+"本，当前分发"+num+"本，确认分发吗？"
				  layer.confirm(str, {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
					  function(index) {
						  $("#modal-dialog").fadeOut();
						  layer.close(index);
					  }
				  );
			  }
			  else if(num > checkBook){
				  var str = "申请"+checkBook+"本，当前分发"+num+"本，分发本数不可大于申请本数！"
				  layer.msg(str, {offset: abc.winscrollTop(200),icon: 5});
			  }
			  else{
				  $("#modal-dialog").fadeOut();
			  }

			  distParnet.children().eq(6).children(".invoiceRepoIds").val(ids);
			  distParnet.children().eq(5).children(".realNum").val(num);

		  });

		  $('input[name="examineStatus"]').on('click',function (){
			  var isChecked=$(this).val();
			  if(isChecked=='2'){
				  document.getElementById('remark').innerHTML="";
				  document.getElementById('remark').setAttribute("data-rule",'required;');
			  }
			  else{
				  document.getElementById('remark').innerHTML="同意";
			  }
		  });

       });
    })
});