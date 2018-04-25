require(["../../config"], function () {
    require(["jquery.full","../abc/util/pagination"], function ($) {
      $(function () {
    	  var myrules={};	  
    	  
    	  var $validatorWsysVoFrom = $("form").validator({
    		  timely: 1,
    		  focusCleanup:true,
              theme: 'yellow_right_effect',
              rules: {
            	  allint:[/^(-|\+)?\d+$/, '请输入整数'],
                  tests:function (element,invoiceNoStart){
                      var v1=$("#invoiceNoStart").val();
                      var v2=element.value;
                      return v1.length  == v2.length ?true:'发票号码起长度必需与发票号码止长度一致';
                  },
                  testslen:function (element,idStart){
                      var v1=$("#idStart").val();
                      var v2=element.value;
                      return v1.length  == v2.length ?true:'发票编号止长度必须与发票编号起长度一致!';
                  }
              }
          });
          $validatorWsysVoFrom.validator().trigger("showtip");

          $(".invoiceNoStart").blur(function(){
              if($(".invoiceNoEnd").val()==""||$(".invoiceNoEnd").val() == null){
                  $(".invoiceNoEnd").val($(".invoiceNoStart").val());
              }
          });

          $(".invoiceNoEnd").blur(function(){
              if($(".invoiceNoStart").val()==""||$(".invoiceNoStart").val() == null){
                  $(".invoiceNoStart").val($(".invoiceNoEnd").val());
              }
          });
          $(".noStart").blur(function(){
              if($(".noEnd").val()==""||$(".noEnd").val() == null){
                  $(".noEnd").val($(".noStart").val());
              }
          });

          $(".noEnd").blur(function(){
              if($(".noStart").val()==""||$(".noStart").val() == null){
                  $(".noStart").val($(".noEnd").val());
              }
          });
          $('#consumer_query').on("click", function () {
              abc.block();
              $('#consumerListForm').submit();
          });

          //a删除
          $("a[data-type='delSig']").click(function () {
              abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
          });

          $("#consumer_submit").click(function(){
        	  if($validatorWsysVoFrom.isValid()){
                  var invoiceCode = $("#invoiceCode").val();
                  var invoiceNoStart = $("#invoiceNoStart").val();
                  var invoiceNoEnd = $("#invoiceNoEnd").val();
                  var id = $("#id").val();
                  layer.confirm('是否提交？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                      function (index) {
                          abc.block();
                          $.ajax({
                              type: "GET",
                              url: ctx+"/financed/validate.php?invoiceCode="+invoiceCode+"&invoiceNoStart="+invoiceNoStart+"&invoiceNoEnd="+invoiceNoEnd+"&id="+id,
                              dataType: 'json',
                              success: function (data) {
                                  abc.unblock();
                                  if (data) {
                                      dosave(document.referrer);
                                      //abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(),document.referrer);
                                  }
                                  else{
                                      layer.msg("发票代码和发票号码必须唯一，请重新输入！", {icon: 5});
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

              }
          });

          function dosave(back_url){
              abc.block();
              $.ajax({
                  type: "POST",
                  url: $("form").attr('action'),
                  data: $("form").serializeJson(),
                  async: true,
                  contentType: "application/json",
                  dataType: "JSON",
                  success: function (data) {
                      abc.unblock();
                      if (data && data.code == "2000") {
                          if(back_url){
                              layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                  window.location.href = back_url;
                              });
                          }else{
                              layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                          }
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
          $("#submit").click(function(){
              $("#booktips").hide();
              goPage(-1);
          });

          //首页
          $("#consumer_first").click(function(){
              goPage(1);
          });
          //前一页
          $("#consumer_up").click(function(){
              goPage(parseInt($("#cupageVal").val())-1);
          });
          //下一页
          $("#consumer_down").click(function(){
              goPage(parseInt($("#cupageVal").val())+1);
          });
          //最后一页
          $("#consumer_last").click(function(){
              goPage(parseInt($("#topageVal").val()));
          });
          //跳转
          $("#consumer_go").keypress(function(e) {
              if(e.which == 13) {
                  goPage(isNaN($("#consumer_go").val())?1:$("#consumer_go").val());
              }
          });
          //跳转匡
          $("#consumer_gogo").click(function(e) {
              goPage(parseInt(isNaN($("#consumer_go").val())?1:$("#consumer_go").val()));
          });

          var goPage= function(index){
              if(isNaN($("#consumer_size").val())
                  ||$("#consumer_size").val()==''){
                  $("#consumer_size").val(10);
              }
              var curtPage=parseInt($("#cupageVal").val());
              var totalPage=parseInt($("#topageVal").val());
              if((index<1||index==curtPage||index>totalPage)&&index!=-1){
                  return;
              }else if(index==-1){
                  index=1;
              }
              $('#cupageVal').val(index);
              if($validatorWsysVoFrom.isValid()) {
                  var invoiceTypeCode = $("#invoiceTypeCode").val();
                  var share = parseInt($("#share").val());
                  var invoiceTypeName = '';
                  var invoiceNoStart = $("#invoiceNoStart").val();
                  var invoiceNoStart = $("#invoiceNoStart").val();
                  var invoicestart = '';
                  var invoiceend = '';
                  if(index == 1){
                      invoicestart = parseInt(invoiceNoStart);
                      invoiceend = parseInt(invoiceNoStart) + share*index - 1;
                  }
                  else{
                      invoicestart = parseInt(invoiceNoStart)+ share*(index-1)*10;
                      invoiceend = parseInt(invoiceNoStart) +share+ share*(index-1)*10 - 1;
                  }
                  var total = parseInt($("#invoiceNoEnd").val())-parseInt($("#invoiceNoStart").val())+1;
                  var book = total/share;
                  if(Math.floor(book) != book){
                      layer.msg("根据发票号码起、止及每本发票份数计算得不到完整本数,请重新输入! ", {
                          offset: abc.winscrollTop(200),
                          shade: 0.3,
                          icon: 5,
                          time: 2000
                      });
                      return;
                  }
                  var num = parseInt($("#idEnd").val()) - parseInt($("#idStart").val()) + 1;
                  if (num != book) {
                      layer.msg("根据发票编号起/止计算出本数与发票号码起/止计算出的本数不相等,请重新输入! ", {
                          offset: abc.winscrollTop(200),
                          shade: 0.3,
                          icon: 5,
                          time: 2000
                      });
                      return;
                  }
              }
              if($validatorWsysVoFrom.isValid()){
                  var html ='';
                  var id = $("#id").val();
                  var invoiceCode = $("#invoiceCode").val();
                  $('#tjBtn').hide();
                  $("#tips").hide();
                  $('#plfpmx').show();
                  $.ajax({
                      type: "GET",
                      url: ctx+"/financed/jsonDictBOs.php?dictId=invoicetype",
                      async: false,
                      contentType: "application/json",
                      dataType: "JSON",
                      success: function (data) {
                          if (data) {
                              $.each(data, function (i, item) {
                                  if(invoiceTypeCode == item.fieldValue){
                                      invoiceTypeName = item.fieldKey;
                                  }
                              });
                              if(book<10){
                                  for(var i=1;i<=book;i++){
                                      var idStart = $("#idStart").val();
                                      var start = (parseInt(idStart)+i-1).toString();
                                      for(var j=start.length;j<idStart.length;j++){
                                          start = "0"+start;
                                      }
                                      var nostart = invoicestart.toString();
                                      var noend = invoiceend.toString();
                                      for(var k=noend.length;k<invoiceNoStart.length;k++){
                                          noend = "0"+noend;
                                      }
                                      for(var k=nostart.length;k<invoiceNoStart.length;k++){
                                          nostart = "0"+nostart;
                                      }
                                      html+= "<tr><td width='4%'>"+i+"</td>";
                                      html+= "<td class='mdtablethtd'>"+id+start +"</td>";
                                      html+= "<td class='mdtablethtd'>"+invoiceTypeName +"</td>";
                                      html+= "<td class='mdtablethtd'>"+invoiceCode+"</td>";
                                      html+= "<td class='mdtablethtd'>"+nostart+"</td>";
                                      html+= "<td class='mdtablethtd'>"+noend+"</td>";
                                      html+= "<td class='mdtablethtd'>"+share+"</td>";
                                      html+= "<td class='mdtablethtd'>1</td>";
                                      html+= '</tr>';
                                      invoicestart = invoicestart+share;
                                      invoiceend = invoiceend +share;
                                  }
                              }
                              else{
                                  if(index ==1){
                                      var currNum = index;
                                  }
                                  else{
                                      var currNum = (index-1)*10+1;
                                  }
                                  if(index<=(Math.ceil(book/10)-1)){
                                      for(var i=currNum;i<=index*10;i++){
                                          var idStart = $("#idStart").val();
                                          var start = (parseInt(idStart)+i-1).toString();
                                          for(var j=start.length;j<idStart.length;j++){
                                              start = "0"+start;
                                          }
                                          var nostart = invoicestart.toString();
                                          var noend = invoiceend.toString();
                                          for(var k=noend.length;k<invoiceNoStart.length;k++){
                                              noend = "0"+noend;
                                          }
                                          for(var k=nostart.length;k<invoiceNoStart.length;k++){
                                              nostart = "0"+nostart;
                                          }
                                          html+= "<tr><td width='4%'>"+i+"</td>";
                                          html+= "<td class='mdtablethtd'>"+id+start +"</td>";
                                          html+= "<td class='mdtablethtd'>"+invoiceTypeName +"</td>";
                                          html+= "<td class='mdtablethtd'>"+invoiceCode+"</td>";
                                          html+= "<td class='mdtablethtd'>"+nostart+"</td>";
                                          html+= "<td class='mdtablethtd'>"+noend+"</td>";
                                          html+= "<td class='mdtablethtd'>"+share+"</td>";
                                          html+= "<td class='mdtablethtd'>1</td>";
                                          html+= '</tr>';
                                          invoicestart = invoicestart+share;
                                          invoiceend = invoiceend +share;
                                      }
                                  }
                                  else{
                                      for(var i=currNum;i<=book;i++){
                                          var idStart = $("#idStart").val();
                                          var start = (parseInt(idStart)+i-1).toString();
                                          for(var j=start.length;j<idStart.length;j++){
                                              start = "0"+start;
                                          }
                                          var nostart = invoicestart.toString();
                                          var noend = invoiceend.toString();
                                          for(var k=noend.length;k<invoiceNoStart.length;k++){
                                              noend = "0"+noend;
                                          }
                                          for(var k=nostart.length;k<invoiceNoStart.length;k++){
                                              nostart = "0"+nostart;
                                          }
                                          html+= "<tr><td width='4%'>"+i+"</td>";
                                          html+= "<td class='mdtablethtd'>"+id+start +"</td>";
                                          html+= "<td class='mdtablethtd'>"+invoiceTypeName +"</td>";
                                          html+= "<td class='mdtablethtd'>"+invoiceCode+"</td>";
                                          html+= "<td class='mdtablethtd'>"+nostart+"</td>";
                                          html+= "<td class='mdtablethtd'>"+noend+"</td>";
                                          html+= "<td class='mdtablethtd'>"+share+"</td>";
                                          html+= "<td class='mdtablethtd'>1</td>";
                                          html+= '</tr>';
                                          invoicestart = invoicestart+share;
                                          invoiceend = invoiceend +share;
                                      }
                                  }

                              }
                              $('#fpmxbody').empty().append(html);
                              $('#totalItems').text(book);
                              $('#page').text(index);
                              //Math.ceil(5/2)
                              $('#totalPage').text(Math.ceil(book/10));
                              $("#topageVal").val(Math.ceil(book/10));
                          } else {
                          }
                      }
                  });
                  //abc.layerAjaxConfirm("POST", $("form").attr('action'),  $("form").serializeJson(), $("form").attr('next-url'));
              }
          }

          $("#idEnd").keyup(function () {
              var v1=$("#idStart").val();
              var v2=$("#idEnd").val();
              if(v1.length  == v2.length){
                  $("#tips").hide();
              }
          });
         $(".share").keyup(function () {
             var invoiceNoStart = $("#invoiceNoStart").val();
             var invoiceNoEnd = $("#invoiceNoEnd").val();

             if(($("#invoiceNoEnd").val()!=""||$("#invoiceNoStart").val()!="")
                 &&(invoiceNoStart.length == invoiceNoEnd.length)&&($("#invoiceNoEnd").val()>=$("#invoiceNoStart").val())) {
                 $("#share").val(parseInt($("#invoiceNoEnd").val()) - parseInt($("#invoiceNoStart").val()) + 1);
             }
             else{
                 $("#share").val("");
             }
          });


          $(".book").blur(function(){
              if($("#invoiceNoStart").val()==""||$("#invoiceNoStart").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#invoiceNoEnd").val()==""||$("#invoiceNoEnd").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#idStart").val()==""||$("#idStart").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#idEnd").val()==""||$("#idEnd").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#share").val()==""||$("#share").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#invoiceNoStart").val()!=""&&$("#invoiceNoStart").val() != null&&$("#invoiceNoEnd").val()!=""&&$("#invoiceNoEnd").val() != null&&$("#share").val()!=""&&$("#share").val() != null){
                  var num = parseInt($("#invoiceNoEnd").val())-parseInt($("#invoiceNoStart").val())+1;
                  var share  = parseInt($("#share").val());
                  var book = num/share;
                  if(Math.floor(book) == book){
                      $("#book").val(book);
                      $("#booktips").hide();
                      $("#booktips1").hide();
                  }
                  else{
                      $('#tjBtn').show();
                      //$("#tips").hide();
                      $('#plfpmx').hide();
                      $("#book").val("");
                      $("#booktips").show();
                      $("#booktips1").hide();
                  }
              }
              if($("#idStart").val()!=""&&$("#idStart").val() != null&&$("#idEnd").val()!=""&&$("#idEnd").val() != null&&$("#book").val()!=""&&$("#book").val() != null) {
                  var noNum = parseInt($("#idEnd").val()) - parseInt($("#idStart").val()) + 1;
                  var bookNum = parseInt($("#book").val());
                  if (noNum != bookNum) {
                      $("#booktips1").show();
                      $('#tjBtn').show();
                      //$("#tips").hide();
                      $('#plfpmx').hide();
                  }
                  else {
                      $("#booktips1").hide();
                      if (Math.floor(book) == book) {
                          goPage(-1);
                      }
                  }
              }
          });

          $(".invoiceType").blur(function(){
              if($("#invoiceNoStart").val()==""||$("#invoiceNoStart").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#invoiceNoEnd").val()==""||$("#invoiceNoEnd").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#idStart").val()==""||$("#idStart").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#idEnd").val()==""||$("#idEnd").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#share").val()==""||$("#share").val() == null){
                  $('#tjBtn').show();
                  //$("#tips").hide();
                  $('#plfpmx').hide();
              }
              if($("#invoiceNoStart").val()!=""&&$("#invoiceNoStart").val() != null&&$("#invoiceNoEnd").val()!=""&&$("#invoiceNoEnd").val() != null&&$("#share").val()!=""&&$("#share").val() != null){
                  var num = parseInt($("#invoiceNoEnd").val())-parseInt($("#invoiceNoStart").val())+1;
                  var share  = parseInt($("#share").val());
                  var book = num/share;
                  if(Math.floor(book) == book){
                      $("#book").val(book);
                      $("#booktips").hide();
                      $("#booktips1").hide();
                  }
                  else{
                      $('#tjBtn').show();
                      //$("#tips").hide();
                      $('#plfpmx').hide();
                      $("#book").val("");
                      $("#booktips").show();
                      $("#booktips1").hide();
                  }
              }
              if($("#idStart").val()!=""&&$("#idStart").val() != null&&$("#idEnd").val()!=""&&$("#idEnd").val() != null&&$("#book").val()!=""&&$("#book").val() != null) {
                  var noNum = parseInt($("#idEnd").val()) - parseInt($("#idStart").val()) + 1;
                  var bookNum = parseInt($("#book").val());
                  if (noNum != bookNum) {
                      $("#booktips1").show();
                      $('#tjBtn').show();
                      //$("#tips").hide();
                      $('#plfpmx').hide();
                  }
                  else {
                      $("#booktips1").hide();
                      if (Math.floor(book) == book) {
                          goPage(-1);
                      }
                  }
              }
          });
          $(".invoiceType").on('change',function(){
              var _val=$(this).val();
              if(_val == "DZZZSPTFP-"){
                  $("input[name='property']").eq(0).removeAttr("checked");
                  $("input[name='property']").eq(1).attr("checked","checked");
                  $("input[name='property']").eq(1).attr("disabled",false);
                  $("input[name='property']").eq(0).attr("disabled","disabled");
                  $("input[name='property']").eq(1).click();
              }
              else{
                  $("input[name='property']").eq(0).attr("checked","checked");
                  $("input[name='property']").eq(1).removeAttr("checked");
                  $("input[name='property']").eq(0).attr("disabled",false);
                  $("input[name='property']").eq(1).attr("disabled","disabled");
                  $("input[name='property']").eq(0).click();
              }
              $.ajax({
                  type: "GET",
                  url: ctx+"/financed/invoiceRepoId.php?code="+_val,
                  async: false,
                  contentType: "application/json",
                  dataType: "JSON",
                  success: function (data) {
                      if (data != null) {
                          var ids = data.id.split("-");
                          var len = ids[1].length;
                          var idstart = (parseInt(ids[1])+1).toString();
                          for(var i=idstart.length;i<len;i++){
                              idstart = "0"+idstart;
                          }
                          $("#id").val(data.invoiceTypeCode);
                          $("#idStart").val(idstart);
                          var v1=$("#idStart").val();
                          var v2=$("#idEnd").val();
                          if(v2 != undefined){
                              if(v1.length  == v2.length){
                                  $("#tips").hide();
                              }
                              else{
                                  $("#tips").show();
                                  $("#booktips1").hide();
                              }
                          }

                      }
                      else{
                          $("#id").val(_val);
                          $("#idStart").val("000000001");
                      }

                      //}
                  },
                  error: function () {
                      /*var cObj = document.getElementById("idStart");
                      cObj.removeAttribute("readOnly");*/
                      $("#id").val(_val);
                      $("#idStart").val("000000001");
                  }
              });
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
          
          
       });
    })
});