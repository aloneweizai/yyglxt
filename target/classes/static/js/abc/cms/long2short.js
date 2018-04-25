require(["../../config"], function () {
    require(["jquery.full","../abc/util/date"], function ($) {
      $(function () {
		  $("#myspread").click(function(){
			  var url = $("#url").val();
			  var type = $("#type").val();
			  if(url == null || url == ""){
				  abc.layerAlert(false,'请输入长网址!');
				  return;
			  }
			  abc.block();
			  $.ajax({
				  type: "GET",
				  url: ctx+"/cms/long2short.php?url="+url+"&type="+type,
				  async: true,
				  contentType: "application/json",
				  dataType: "JSON",
				  success: function (data) {
					  abc.unblock();
					  $('#wx_url').val(data.data);
					  $('#tgm').show();
				  }
			  });
		  });

		  $('a[name="fz"]').on('click',function (){
			  var fzid=$(this).attr('fzid');
			  var Url2=document.getElementById(fzid);
			  Url2.select(); // 选择对象
			  document.execCommand("Copy"); // 执行浏览器复制命令
			  alert("已复制好，可贴粘。");
		  })

		  function closeDiv()
		  {
			  if(document.getElementById('loading') != null){
				  document.getElementById('loading').style.visibility='hidden';
			  }
		  }
		  closeDiv();
	  });
    })
});
