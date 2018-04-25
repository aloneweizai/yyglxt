require(["../../config"], function () {
    require(["jquery.full","../abc/util/date"], function ($) {
      $(function () {

         //查询
    	 $("#consumer_query").click(function(){
			 var nsrsbh = $("input[name='nsrsbh']").val();
			 if(nsrsbh == null || nsrsbh == ""){
				 abc.layerAlert(false,'请输入纳税人识别号！');
				 return;
			 }
			 abc.block();
			 $('#consumerListForm').submit();
    	 });

		  $("a[data-type='opendialog']").click(function(){
				  $("#myModal1").fadeIn();
		  });
		  $("[data-dismiss='modal']").click(function(){
			  $("#myModal1").fadeOut();
		  });


       });
    })
});
