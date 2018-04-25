require(["../../../config"], function () {
    require(["jquery.full"], function ($) {

        $("#_file_upload_btn").on("click",function(){

            var $that = $(this);

            var domain = $("input[name='domain']").val();
            if(!$.trim(domain)){
                layer.alert("请填写域名", {icon: 5});
                return ;
            }

            var sitePath = $("input[name='sitePath']").val();
            if(!$.trim(sitePath)){
                layer.alert("请填写站点路径", {icon: 5});
                return ;
            }

            if(!$.trim($("#siteLogo_ipt").val())){
                layer.alert("请选择上传文件", {icon: 5});
                return ;
            };

            var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajaxFileUpload({
                url: ctx+'/content/content/fileUpload.php?subsystem='+sitePath+'&fileTypeTag=image', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: "siteLogo_ipt", //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (result){  //服务器成功响应处理函数
                    console.log(result);
                    if(result.code=='2000') {
                        layer.alert(result.msg, {icon: 1});
                        $that.siblings("input[name='siteLogo']").val(result.filePath + "/" + result.fileName);
                        $that.siblings("img").attr("src","http://" + domain + result.filePath + "/" + result.fileName);
                    }else{
                        layer.alert(result.msg, {icon: 5});
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {icon: 5});
                },
                complete: function(){
                    layer.close(loadIndex);
                    $that.siblings("input[type='file']").get(0).outerHTML=$that.siblings("input[type='file']").get(0).outerHTML;
                }
            });

        });

        $("#_site_checkurl").on("click",function(){

            var host = $(this).siblings("input[name='domain']").val();

            if(!host){
                layer.alert("请填写域名", {icon: 5});
                return ;
            }

            if(host.indexOf("http://")<=0){
                window.open("http://"+host);
            }else{
                window.open(host);
            }

        });

        $("#submit_btn").on("click",function () {

            layer.load(1,{shade: [0.6,"#393D49"]});

            $("#site_manage_add_form").isValid(function(checkResult){
                if(checkResult){
                    var confirmIndex = layer.confirm("是否确认提交？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                        $.ajax({
                            type : "post",
                            url : ctx + "/content/site/insertOrUpdate.php",
                            data : JSON.parse($("#site_manage_add_form").serializeJson()),
                            success:function(data){
                                if(data && data.code=='2000'){
                                    layer.msg("操作成功", {icon: 1,closeBtn:0, title: ""},function(){
                                        location.href = ctx + "/content/site/sitelist.php";
                                    });
                                }else{
                                    layer.msg(data.message||"创建站点失败", {icon: 5}, function () {
                                        layer.closeAll();
                                    });
                                }
                            },
                            error:function () {
                                layer.msg("创建站点失败", {icon: 5}, function () {
                                    layer.closeAll();
                                });
                            }
                        });
                        layer.close(confirmIndex);
                    },function(){
                        layer.closeAll();
                    });
                }else{
                    layer.closeAll();
                }
            });


        });

        $("#back_btn").on("click",function () {
            history.back();
        });

    });
});