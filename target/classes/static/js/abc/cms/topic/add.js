require(["../../../config"], function () {
    require(["jquery.full", "wangEditor","template"], function ($, Editor, template) {

        var formNode = $("form[name='_topic_add_form']");
        var editor = new Editor("#_topic_description_area");
        editor.customConfig.uploadImgServer = ctx+'/content/content/editorFileUpload.php';
        editor.customConfig.uploadImgParams = {
            siteDomain : $("form[name='_topic_add_form'] select[name='siteId'] option:selected").data("domain"),
            fileTypeTag : "image",
            subsystem : $("form[name='_topic_add_form'] select[name='siteId'] option:selected").data("sitepath")
        }
        editor.create();

        formNode.find("button[name='_img_upload_btn']").on("click",function(){
            var targetBtn = $(this);
            var fileNode = targetBtn.siblings("input[type='file']");

            if(!fileNode.get(0).files || fileNode.get(0).files.length<=0){
                layer.msg("请选择文件");
                return;
            }

            var sitePath = $("form[name='_topic_add_form'] select[name='siteId'] option:selected").data("sitepath");
            if(!$.trim(sitePath)){
                layer.msg("请选择所属站点或该站点路径未配置");
                return;
            }

            var id = fileNode.attr("id");
            var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajaxFileUpload({
                url: ctx + '/content/content/fileUpload.php?subsystem='+sitePath+'&fileTypeTag=image', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: id, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (result){  //服务器成功响应处理函数
                    if(result.code=='2000') {
                        layer.alert(result.msg, {icon: 1},function () {
                            layer.closeAll();
                        });
                        var siteDomain = $("#siteId").find("option:selected").data("domain");
                        targetBtn.siblings("input[type='hidden']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("img").attr("src","http://" + siteDomain + result.filePath + "/" + result.fileName);
                    }else{
                        layer.alert(result.msg, {icon: 5},function () {
                            layer.closeAll();
                        });
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {icon: 5},function () {
                        layer.closeAll();
                    });
                },
                complete: function(){
                    targetBtn.siblings("input[type='file']").get(0).outerHTML=targetBtn.siblings("input[type='file']").get(0).outerHTML;
                }
            });
        });

        formNode.find("input[name='submit_btn']").on("click",function(){

            var reqParam = JSON.parse(formNode.serializeJson());
            reqParam.description = editor.txt.html();

            formNode.isValid(function(checkResult){
                if(checkResult){
                    layer.confirm("是否确认提交？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 1},function() {
                        var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
                        $.ajax({
                            url : ctx + "/cms/topic/insertOrUpdata.php",
                            type : "POST",
                            data : reqParam,
                            success : function(result){
                                if(result.code=='2000'){
                                    layer.msg(result.msg||"操作成功", {icon: 1, closeBtn:0, title: ""},function () {
                                        location.href=ctx+"/cms/topic/list.php";
                                    });
                                }else{
                                    layer.msg(result.msg||"操作失败", {icon: 5},function () {
                                        layer.close(loadIndex);
                                    });
                                }

                            },
                            error : function(err){
                                layer.msg("操作失败", {icon: 5},function () {
                                    layer.close(loadIndex);
                                });
                            }
                        });
                    });
                }
            });

        });

        formNode.find("input[name='back_btn']").on("click",function () {
            history.back();
        });

        formNode.find("select[name='siteId']").on("change",function(){
            var selectedNode = $(this);
            var siteId = $(this).val();
            var text = $(this).find("option:selected").text();
            var sitePath = $(this).find("option:selected").data("sitepath");
            // var modelId = formNode.find("input[name='modelId']").val();
            var siteDomain = $("#siteId").find("option:selected").data("domain");
            if(!$.trim(siteId)){
                return;
            }

            // if(!$.trim(modelId)){
            //     layer.msg("请先选择包含栏目");
            //     selectedNode.val("");
            //     return;
            // }

            var index = layer.confirm("该内容将归属于[" + text + "]站点，确认将不能进行修改，是否确认?", {
                btn: ['确定', '取消'],
                closeBtn: 0
            },function () {
                selectedNode.find("option:selected").siblings("option").detach();
                layer.close(index);
                $.ajax({
                    url : ctx + "/content/column/queryColumnTemplate.php",
                    type: "get",
                    data: {
                        siteId : siteId,
                        modelId : ""
                    },
                    success : function(result){
                        formNode.find("select[name='tplContent']").html(template("topic_template_list_tmpl",{list:result}));
                        editor.config.uploadImgParams = {
                            subsystem : sitePath,
                            fileTypeTag : "image",
                            siteDomain : siteDomain
                        };
                    },
                    error : function () {
                        layer.alert("查询失败", {icon: 5});
                    }
                });

            },function () {
                selectedNode.val("");
            });
        });

    });

});