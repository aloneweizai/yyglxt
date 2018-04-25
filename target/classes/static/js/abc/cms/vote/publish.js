require(["../../../config"], function () {
    require(["jquery.full", "wangEditor"], function ($, Editor) {


        $("#vote_publish_upload_btn").on("click",function(){
            var targetNode = $(this);
            targetNode.siblings("input[type='file']").detach();
            var nodeId = "file"+(new Date()).getTime();
            var node = $("<input type='file' id='"+nodeId+"' name='vote_publish_img_upload' style='display:none;' accept='image/jpeg,image/gif,image/png'>");
            targetNode.after(node);
            targetNode.siblings("input[type='file']").on("change",function(){
                $.ajaxFileUpload({
                    url: ctx + '/cms/vote/fileUpload.php', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: nodeId, //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (result){  //服务器成功响应处理函数
                        if(result.code=='2000') {
                            targetNode.siblings("input[name='header']").val(result.filePath);
                            targetNode.siblings("img[name='vote_publish_header']").attr("src",result.hostUrl + result.filePath);
                        }else{
                            layer.alert(result.msg, {icon: 5});
                        }
                    },
                    error: function (){//服务器响应失败处理函数
                        layer.alert("上传文件失败", {icon: 5});
                    },
                    complete:function(){
                        targetNode.siblings("input[type='file']").detach();
                    }
                });

            }).trigger("click");
        });

        var serializeObject = function(){

            var votePublishDto = JSON.parse($("#vote_publish_form").serializeJson());

            return votePublishDto;

        }

        $("#vote_publish_confirm_btn").on("click",function(){
            layer.confirm("是否确认提交？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                var reqParam = serializeObject();
                $.ajax({
                    url: ctx + "/cms/vote/update.php",
                    contentType: 'application/json',
                    type: "POST",
                    data: JSON.stringify(reqParam),
                    dataType: "json",
                    success: function (result) {
                        if (result.code == "2000") {
                            layer.msg('投票发布设置成功', {icon: 1, closeBtn: 0, shade: [0.6, "#393D49"]}, function () {
                                location.href = ctx + "/cms/vote/generate.php?voteId=" + result.data.id;
                            });
                        } else {
                            layer.msg(result.message || "投票发布设置失败", {icon: 5});
                        }

                    },
                    error: function () {
                        layer.msg("操作失败", {icon: 5});
                    }
                });
            });

        });

        $("#vote_publish_back_btn").on("click",function(){
            location.href = ctx + "/cms/vote/list.php";
            //window.location.href = document.referrer;
        });

        $("#vote_baseinfo").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.msg('请先保存该投票项', {icon: 5});
                return ;
            }

            location.href = ctx + "/cms/vote/add.php?voteId="+voteId;
        });

        $("#vote_generate").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.msg('请先保存该投票项', {icon: 5});
                return ;
            }

            location.href = ctx + "/cms/vote/generate.php?voteId="+voteId;
        });

        $("input[name='hiddenResult']").on("change",function(){
            var index = layer.confirm("该选项涉及用户投票完成后是否可以查看投票结果，请确认",{
                btn : ["启用","禁用"]
            },function(){
                $("input[name='hiddenResult'][value='true']").prop("checked","checked");
                layer.close(index);
            },function(){
                $("input[name='hiddenResult'][value='false']").prop("checked","checked");
            });
        });

    });
});