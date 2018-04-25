/**
 * Created by Administrator on 2017-06-06.
 * 友情链接
 */
require(["../../config"], function () {
    require(["jquery.full", "swfupload.full","../abc/util/pagination"], function ($) {
        $(function () {
            $("#FILE01").on("change", function () {
                PreviewImage(this, 'imgDiv1', 'imgHeadPhoto1', 'divPreview1');
            })

            //数据校验
            var $validatorWsysVoFrom = $("form").validator({
                theme: 'simple_right',
                timely: 1,
                fields: {
                    "siteName": "required;",
                    "domain": "required;url;",
                    "priority": "required;",
                    "views": "required;digits;length[1~7]",
                    "email":"email;"
                }
            });

            $('#reset').click(function (){
                //window.location.href = ctx+"/cms/friendlink/list.php";
                window.location.href = document.referrer;
            });


            $("#submit").on("click", function () {
                if($validatorWsysVoFrom.isValid()){
                    layer.confirm('是否保存？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajaxFileUpload({
                                url: ctx+"/cms/friendlink/save.php",
                                secureuri: false,
                                fileElementId: 'FILE01',//file标签的id
                                dataType: 'json',
                                data: {
                                    siteName: $("#siteName").val(),
                                    domain: $("#domain").val(),
                                    friendlinkId: $("#friendlinkId").val(),
                                    friendlinkctgId: $("#friendlinkctgId option:selected").val(),
                                    email: $("#email").val(),
                                    description: $("#description").val(),
                                    priority: $("#priority").val(),
                                    views: $("#views").val(),
                                    isEnabled: $("input:radio[name='isEnabled']:checked").val()
                                },
                                success: function (data) {
                                    if (data.result.code == '2000') {
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            //swindow.location.href = ctx+"/cms/friendlink/list.php";
                                            window.location.href = document.referrer;
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
                window.location.href = ctx+"/cms/friendlink/list.php";
                //window.location.reload();
            })



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


        $('.nycon_sel_btn').on('click',function (){
            var oselectall = $('input[name="ids"]')
            oselectall.each(function() {
                if (this.checked == false) {
                    this.checked = true;
                    $(this).next().addClass("layui-form-checked");
                } else {
                    this.checked = false ;
                    $(this).next().removeClass("layui-form-checked");
                }
            })
        })

        $('#batch_del').on('click',function (){
            var oselectall = $('input[name="ids"]')
            var ids=''
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                }
            })
            if(ids==''){
                layer.msg("请选择需要批量删除的项!", {icon: 5});
            }else{
                layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                    function(index){
                        $.ajax({
                            type:'POST',
                            url: ctx+"/cms/friendlink/batchDel.php" ,
                            data:{ids:ids},
                            success: function (data){
                                if(data.result.code=='2000'){
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    setTimeout(function () {
                                        //window.location.href = ctx+"/cms/friendlink/list.php";
                                        window.location.reload();
                                    }, 2000);
                                }else{
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            } ,
                            dataType: "JSON"
                        });
                    }
                );

            }
        })


        $("a").on('click',function (){
            var data=$(this).attr("data");
            var url=$(this).attr("data-url");
            var type=$(this).attr('type');
            var abc_type=$(this).attr('abc-type');

            if('get'==type||'GET'==type){
                window.location.href=url;
            }else{
                if(abc_type=='delete'){
                    layer.confirm('是否删除？', {icon: 3,title:"操作提示",btn: ['确认','取消'], offset: '200px',closeBtn: 0},
                        function(index){
                            $.ajax({
                                type:type,
                                url: url ,
                                data:data,
                                success: function (data){
                                    if(data.result.code=='2000'){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        setTimeout(function () {
                                            //window.location.href = ctx+"/cms/friendlink/list.php";
                                            window.location.reload();
                                        }, 2000);
                                    }else{
                                        layer.msg(data.result.message, {icon: 5});
                                    }
                                } ,
                                dataType: "JSON"
                            });
                        }
                    );
                }else{
                    $.ajax({
                        type:type,
                        url: url ,
                        data:data,
                        success: function (data){
                            if(data.result.code=='2000'){
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                setTimeout(function () {
                                    //window.location.href = ctx+"/cms/friendlink/list.php";
                                    window.location.href = document.referrer;
                                }, 2000);
                            }else{
                                layer.msg(data.result.message, {icon: 5});
                            }
                        } ,
                        dataType: "JSON"
                    });
                }
            }
        });
    });


})
