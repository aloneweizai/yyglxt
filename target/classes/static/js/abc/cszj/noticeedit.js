/**
 * Created by Administrator on 2017-06-13.
 * 评论管理
 */
require(["../../config"], function () {
    require(["jquery.full", "wangEditor", "../abc/util/pagination"], function ($, Editor) {
        var editor = new Editor("#_topic_description_area");
        editor.customConfig.uploadImgServer = ctx + '/util/wangEditorUpload.php';
        abc.block();
        if(editor.create() !="undefined"){
            $("#notice_submit_cg").removeClass("layui-btn-disabled");
            $("#notice_submit_fb").removeClass("layui-btn-disabled");
            $("#back").removeClass("layui-btn-disabled");
            $("#notice_submit_cg").removeAttr("disabled");
            $("#notice_submit_fb").removeAttr("disabled");
            $("#back").removeAttr("disabled");
            $.unblockUI();
        }

        var noticeListForm = $("form[name='notice_add_form']");

        $('#back').click(function () {
            window.location.href = ctx + "/cszjs/notice/list.php?title="+$("#noticeTitle").val()+"&status="+$("#status").val()+"&page="+$("#page").val()+"&size="+$("#size").val();
        });
        /*noticeListForm.find("input[name='content_reset_btn']").off("click").on("click",function(){
         noticeListForm.get(0).reset();
         for(var i in editorMap){
         if(editorMap.hasOwnProperty(i)){
         editorMap[i].txt.clear();
         }
         }
         editor.txt.html("");
         });
         */
        noticeListForm.find("input[name='notice_submit_btn']").off("click").on("click", function () {
            var status = $(this).attr("status-val");
            var noticeId = $("#noticeId").val();
            if (noticeId == 'undefined' || noticeId == null) {
                noticeId = "";
            }
            if ($("form[name='notice_add_form']").isValid()) {
                var reqParam = JSON.parse(noticeListForm.serializeJson());
                reqParam.content = editor.txt.html();
                reqParam.status = status;
                reqParam.id = noticeId;
                var title =  $("#noticeTitle").val();
                var page =  $("#page").val();
                var noticeStatus =  $("#status").val();
                layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                    function (index) {
                        abc.block();
                        $("input[name='notice_submit_btn']").attr("disabled","disabled");
                        $.ajax({
                            url: ctx + "/cszjs/notice/noticeSave.php",
                            type : "POST",
                            secureuri: false,
                            dataType: 'json',
                            data : reqParam,
                            success: function (data) {
                                //console.log(data);
                                $.unblockUI();
                                if (data.result.code == '2000') {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000}, function () {
                                        window.location.href = ctx + "/cszjs/notice/list.php?title="+title+"&status="+noticeStatus+"&page="+page;
                                    });
                                    setTimeout(function () {
                                        window.location.href = ctx + "/cszjs/notice/list.php";
                                    }, 2000);
                                } else {
                                    $("input[name='notice_submit_btn']").attr("disabled",false);
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            },
                            error:function(){
                                $.unblockUI();
                                layer.msg("操作失败", {icon: 2});
                                $("input[name='notice_submit_btn']").attr("disabled",false);
                            }
                        });
                    }
                );
            }
        })

    });
})