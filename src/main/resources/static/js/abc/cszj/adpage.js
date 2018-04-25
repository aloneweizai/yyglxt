/**
 * Created by Administrator on 2017-06-06.
 * 友情链接
 */
require(["../../config"], function () {
    require(["jquery.full", "swfupload.full","../abc/util/pagination"], function ($) {
        layui.use(['laydate'], function(){
            var laydate = layui.laydate; //获得laydate模块
            laydate.render({
                elem: '#startTime',
                theme: '#14b9d5'
            });
            laydate.render({
                elem: '#endTime',
                theme: '#14b9d5'
            });
            //使用模块
        });
        $(function () {
            $("#FILE01").on("change", function () {
                PreviewImage(this, 'imgDiv1', 'imgHeadPhoto1', 'divPreview1');
            })

            //数据校验
            var $validatorWsysVoFrom = $("form").validator({
                theme: 'simple_right',
                timely: 1
            });

            $('#reset').click(function (){
                window.location.href = ctx + "/cszjs/adpage/list.php";
            });


            $("#submit").on("click", function () {
                if($validatorWsysVoFrom.isValid()){
                    var name =  $("#adpageName").val();
                    var page =  $("#page").val();
                    layer.confirm('是否保存？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajaxFileUpload({
                                url: ctx + "/cszjs/adpage/adpageSave.php",
                                secureuri: false,
                                fileElementId: 'FILE01',//file标签的id
                                dataType: 'json',
                                data: {
                                    name: $("#name").val(),
                                    link: $("#link").val(),
                                    adpageId: $("#adpageId").val(),
                                    sort: $("#sort").val(),
                                    showName: $("input:radio[name='showName']:checked").val(),
                                    status: $("input:radio[name='status']:checked").val(),
                                    startTime: $("#startTime").val(),
                                    endTime: $("#endTime").val(),
                                    views: $("#views").val()
                                },
                                success: function (data) {
                                    //console.log(data);
                                    if (data.result.code == '2000') {
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000}, function () {
                                            window.location.href = ctx + "/cszjs/adpage/list.php?name="+name+"&page="+page;
                                        });
                                        setTimeout(function () {
                                            window.location.href = ctx + "/cszjs/adpage/list.php";
                                        }, 2000);
                                    } else {
                                        layer.msg(data.result.message, {icon: 5});
                                    }
                                }
                            });
                        }
                    );
                }
            })

            $("#queryBtn").on("click", function () {
                var name = $('#name').val();
                window.location.href = ctx + "/cszjs/adpage/list.php?name=" + name;
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
                        $("#myModal3").find(".modal-dialog").css('top','-400px').animate({'top':abc.winscrollTop(50)},500);
                        abc.unblock();
                    });
                } else {
                    iframe.onload = function(){
                        //$("#myModal3").fadeIn();
                        $("#myModal3").show();
                        $("#myModal3").find(".modal-dialog").css('top','-400px').animate({'top':abc.winscrollTop(50)},500);
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

            //js本地图片预览，兼容ie[6-9]、火狐、Chrome17+、Opera11+、Maxthon3
            function PreviewImage(fileObj, imgDiv, imgPreviewId, divPreviewId) {
                $('#' + imgDiv).show();
                var allowExtention = ".jpg.png.bmp";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
                var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
                var browserVersion = window.navigator.userAgent.toUpperCase();
                if (allowExtention.indexOf(extention) > -1) {
                    if (fileObj.files) {//HTML5实现预览，兼容chrome、火狐7+等
                        if (window.FileReader) {
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                document.getElementById(imgPreviewId).setAttribute("src", e.target.result);

                            }
                            reader.readAsDataURL(fileObj.files[0]);
                        } else if (browserVersion.indexOf("SAFARI") > -1) {
                            abc.layerAlert(false,"不支持Safari6.0以下浏览器的图片预览!");
                        }
                    } else if (browserVersion.indexOf("MSIE") > -1) {
                        if (browserVersion.indexOf("MSIE 6") > -1) {//ie6
                            document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);

                        } else {//ie[7-9]
                            fileObj.select();
                            if (browserVersion.indexOf("MSIE 9") > -1)
                                fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问
                            var newPreview = document.getElementById(divPreviewId + "New");


                            if (newPreview == null) {
                                newPreview = document.createElement("div");
                                newPreview.setAttribute("id", divPreviewId + "New");
                                newPreview.style.width = document.getElementById(imgPreviewId).width + "px";
                                newPreview.style.height = document.getElementById(imgPreviewId).height + "px";
                                newPreview.style.border = "solid 1px #d2e2e2";
                            }
                            newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";


                            var tempDivPreview = document.getElementById(divPreviewId);
                            tempDivPreview.parentNode.insertBefore(newPreview, tempDivPreview);
                            tempDivPreview.style.display = "none";

                        }
                    } else if (browserVersion.indexOf("FIREFOX") > -1) {//firefox
                        var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
                        if (firefoxVersion < 7) {//firefox7以下版本
                            document.getElementById(imgPreviewId).setAttribute("src", fileObj.files[0].getAsDataURL());
                        } else {//firefox7.0+
                            document.getElementById(imgPreviewId).setAttribute("src", window.URL.createObjectURL(fileObj.files[0]));
                        }
                    } else {
                        document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
                    }
                } else {
                    layer.msg("仅支持" + allowExtention + "为后缀名的文件!", {icon: 5});
                    fileObj.value = "";//清空选中文件
                    document.getElementById(imgPreviewId).setAttribute("src", "");

                }
            }
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

    });


})
