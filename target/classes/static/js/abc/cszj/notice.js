/**
 * Created by Administrator on 2017-06-13.
 * 评论管理
 */
require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination"], function ($, Editor) {
        var noticeListForm = $("form[name='_notice_list_form']");
        $('#queryBtn').on("click", function () {
            var title = $('#query_title').val();
            window.location.href = ctx + "/cszjs/notice/list.php?title=" + title;
        })

        //a删除
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', $("#currLink").val(), $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });

        $("select[data-type='ajax']").each(function () {
            var this_ = $(this);
            var rule = this_.attr('data-rules').split(":");
            var urls = this_.attr('data-url');
            var val = this_.attr('data-val');
            $.ajax({
                type: "GET",
                url: urls,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if (data) {
                        $.each(data, function (i, item) {
                            this_.append("<option " + (eval("item." + rule[0]) == val ? "selected" : "") + " value='" + eval("item." + rule[0]) + "'>" + eval("item." + rule[1]) + "</option>");
                        });
                    } else {
                    }
                }
            });
        });
        
        $("a[data-type='opendialog']").click(function(){
        	abc.block();
			var url=$(this).attr("data-url");
			var iframe=document.getElementById("consumer_frame");
			iframe.src=url;
			if (iframe.attachEvent){ 
				iframe.attachEvent("onload", function(){ 
					//$("#myModal3").fadeIn();
					$("#myModal3").show();
		    		$("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
					abc.unblock();
				}); 
			} else { 
				iframe.onload = function(){ 
					//$("#myModal3").fadeIn(); 
					$("#myModal3").show();
		    		$("#myModal3").find(".modal-dialog").css('top','-700px').animate({'top':abc.winscrollTop(50)},500);
					abc.unblock();
				}; 
			} 
			
		});
        
        $("button[data-dismi]").click(function(){
			 //$("#myModal3").fadeOut();
			 $("#myModal3").find(".modal-dialog").animate({'top':'-700px'},500,function(){
	      	    	$("#myModal3").hide();
	      	 });
		});
    });
})