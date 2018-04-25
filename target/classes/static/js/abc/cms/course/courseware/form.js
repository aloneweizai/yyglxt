/**
 * Created by LiuQi
 */
define(['jquery-3.1.0',"abc.common","layui"],function(){
        //授课情况
        $(".teaching").click(function(){
            $(".classroom-case").show().css({zIndex:100});
            $("div.bg").show()
        });
        $(".classroom-case s").click(function(){
            $(".classroom-case").hide();
            $("div.bg").hide();
        });
        $(".classroom-case-tr td").click(function(){
            var html=$.trim($(this).text());
            $(this).addClass("active").siblings().removeClass("active");
            if(html=="授课学习情况"){
                $(".classroom-case-learn").show().siblings("tbody").hide();
            }else if(html=="报名签到情况"){
                $(".classroom-case-sign").show().siblings("tbody").hide();
            }else{
                $(".classroom-case-order").show().siblings("tbody").hide();
            }
        });

        //取消章弹出框
        $("body").on("click","div.Popup .js_chapter_cancel, .Popup-nav span u",function(){
            $("div#chapter_div").hide();
            $("div.bg").hide();
        });
        //取消课件弹出框
        $("body").on("click",".popup-courseware thead u, .popup-courseware tfoot .js_cancel",function(){
            $(".popup-courseware").hide();
            $("div.bg").hide();
        });
        //添加课件- 文件上传/文件链接 切换
        $("body").on("click",".js_media_tbody .js_uploadWay_td input[type='radio']",function(){
            var tbody = $(this).parents(".js_media_tbody");
            var link_tr = tbody.find(".js_media_link_tr");
            var file_tr = tbody.find(".js_media_file_tr");
            if($(this).val()=="0" || $(this).val()=="2"){
                link_tr.css("display","block");
                file_tr.hide();
            }else{
                link_tr.hide();
                file_tr.show();
            }
        });
        $("body").on("click",".popup-courseware label input[type='radio']",function(){
            var html=$.trim($(this).parent().text());
            if(html=="视频"){
                $('.popup-courseware-video').show().siblings('tbody').hide();
            }else if(html=="音频"){
                $('.popup-courseware-audio').show().siblings('tbody').hide();
            }else if(html=="文档"){
                $('.popup-courseware-document').show().siblings('tbody').hide();
            }else if(html=="直播"){
                $('.popup-courseware-live').show().siblings('tbody').hide();
            }
        });


        // 上传视频/从视频素材库中选择   切换
        $("body").on("click","input[name='media_select_type']",function(){
            var selectType = $(this).val();
            if(selectType == 'upload'){
                $(".js_media_upload_div").show();
                $(".js_media_select_library_div").hide();
            }else{
                $(".js_media_upload_div").hide();
                $(".js_media_select_library_div").show();
            }
        });
        //点击 素材库 按钮
        $("body").on("click",".js_media_select_library_btn",function(){
            $.ajax({
                type: "GET",
                url: ctx + "/cms/courseware/ajaxList.php",
                async: false,
                success: function(html){
                    layer.open({
                        type: 1,
                        area: ['1000px', '550px'],
                        title: "选择视频",
                        closeBtn: 1,
                        shadeClose: true,
                        content: html,
                        success:function(){
                            $("body").on('click', '.js_select_video_tr', function(e){
                                var fileName = $(this).find("td:first").text();
                                var fileSize = $(this).find("td:eq(1)").text();
                                var fileSite = $(this).find("td:last").text();
                                $(".js_media_div").html("<span>"+fileName+"</span><span style='width: 10%'>"+fileSize+"</span><span style='width: 53%'>"+fileSite+"</span>");
                                layer.msg('添加成功', {icon: 1,time:1000});
                            });

                            $("body").on('click', '#video_query', function(e){
                                var fileName = $("#videoName_input").val();
                                $.ajax({
                                    url: ctx + "/cms/courseware/ajaxList.php?fileName="+fileName,
                                    type: "GET",
                                    success: function (html) {
                                       $(".layui-layer-content").html(html);
                                    },
                                    error: function (err) {
                                        layer.msg("查询失败", {icon: 5},function () {
                                            layer.closeAll();
                                        });
                                    }
                                });
                            });

                        }
                    });
                },
                error: function(){
                    layer.msg('获取信息失败', {icon: 5});
                }
            });
        });

    $("body").on("click",".js_page_location", function(){
        if($(this).attr("data-ajax") == "true"){
            $.ajax({
                url: $(this).attr("data-href"),
                type: "GET",
                success: function (html) {
                    $(".layui-layer-content").html(html);
                },
                error: function (err) {
                    layer.msg("查询失败", {icon: 5},function () {
                        layer.closeAll();
                    });
                }
            });
        }else{
            location.href = $(this).attr("data-href");
        }
    });
    $("body").on("keypress","#go_page", function(e){
        if(e.keyCode==13){
            $(".js_go_page").click();
            return false;
        }
    });
    $("body").on("click",".js_go_page", function(e){
        if($(this).attr("data-ajax") == "true"){
            var page = $('#go_page').val();
            var url = $(this).attr("data-href");
            if (page.match("^[1-9][0-9]*$")) {
                $.ajax({
                    url: url.replace("[:page]", page),
                    type: "GET",
                    success: function (html) {
                        $(".layui-layer-content").html(html);
                    },
                    error: function (err) {
                        layer.msg("查询失败", {icon: 5},function () {
                            layer.closeAll();
                        });
                    }
                });
            } else {
                return;
            }
        }else{
            location.href = $(this).attr("data-href");
        }
    });

    /* 删除章节 */
    $("tbody.last").on("click",".remove-chapter",function(e) {
        e.preventDefault();
        var _this = $(this);
        var chapterId = _this.parents(".Introduction").attr("data-id");
        var chapter_tr = _this.parents(".Introduction").parent();
        layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
            function() {
                abc.block();
                $.ajax({
                    type: "DELETE",
                    url: ctx + "/cms/courseware/chapter/delete/"+chapterId+".php",
                    async: false,
                    success: function(data){
                        if (data && data.code == "2000") {
                            chapter_tr.next(".courseware-son").remove();
                            chapter_tr.remove();
                            //排序
                            if($(".Introduction").length>0){
                                $(".Introduction").each(function(i,item){
                                    var val = $(this).find("input").attr("placeholder");
                                    var head = val.substring(0, val.indexOf(":"));
                                    $(this).find("input").attr("placeholder","第"+(i+1)+"章 :"+val.substring(val.indexOf(":")+1));
                                });
                            }
                            layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                        }else{
                            abc.layerAlert(false, '操作失败: '+data.message);
                        }
                        abc.unblock();
                    }
                });
            }
        );
    });

    /* 删除课件 */
    $("tbody.last").on("click",".remove-courseware",function(e) {
        e.preventDefault();
        var courseware_tr = $(this).parents(".courseware-son");
        var coursewareId = courseware_tr.attr("data-id");
        var chapterId = courseware_tr.attr("data-chapterId");
        layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
            function() {
                abc.block();
                $.ajax({
                    type: "DELETE",
                    url: ctx + "/cms/courseware/delete/"+coursewareId+".php",
                    async: false,
                    success: function(data){
                        abc.unblock();
                        if (data && data.code == "2000") {
                            courseware_tr.remove();
                            //修改排序
                            if($(".courseware-son[data-chapterId='"+chapterId+"']").length>0){
                                $(".courseware-son[data-chapterId='"+chapterId+"']").each(function(i,item){
                                    var val = $(this).find("input").attr("placeholder");
                                    var head = val.substring(0, val.indexOf(":"));
                                    $(this).find("input").attr("placeholder","课件"+(i+1)+" :"+val.substring(val.indexOf(":")+1));
                                });
                            }
                            layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                        }else{
                            abc.layerAlert(false, '操作失败: '+data.message);
                        }
                    }
                });
            }
        );
    });

    /* 章弹出框 */
    $("body").on("click",".add-chapter",function(e){
        e.preventDefault();
        var curriculumId = $("[name='curriculumId']").val();
        if(!curriculumId){
            abc.layerAlert(false, '操作失败: 请先添加课程内容');
            return;
        }
        $.ajax({
            type: "GET",
            url: ctx + "/cms/courseware/chapter/ajaxForm.php",
            async: false,
            success: function(html){
                $("#chapter_div").html(html);
                $("div#chapter_div").show().css("z-index","100");
                $("div.bg").show();
            }
        });
    });
    $("body").on("click",".edit-chapter",function(e){
        e.preventDefault();
        var chapterId = $(this).parents(".Introduction").attr("data-id");
        $.ajax({
            type: "GET",
            url: ctx + "/cms/courseware/chapter/ajaxForm.php?id="+chapterId,
            async: false,
            success: function(html){
                $("#chapter_div").html(html);
                $("div#chapter_div").show().css("z-index","100");
                $("div.bg").show();
            }
        });
    });



    /* 课件弹出框 */
    $("tbody.last").on("click",".add-courseware",function(e){
        e.preventDefault();
        var chapterId = $(this).parents(".Introduction").attr("data-id");
        $.ajax({
            type: "GET",
            url: ctx + "/cms/courseware/ajaxForm.php?chapterId="+chapterId,
            async: false,
            success: function(html){
                $("#popup_courseware_div").html(html);
                if($(".js_course_isFree").val()=='1'){
                    $("#courseware_isFree_tr").hide();
                }
                $(".popup-courseware").show().css("z-index","100");
                $("div.bg").show();
            }
        });
    });
    /* 课件编辑 */
    $("tbody.last").on("click",".edit-courseware",function(e){
        e.preventDefault();
        var coursewareId = $(this).parents(".courseware-son").attr("data-id");
        var chapterId = $(this).parents(".courseware-son").attr("data-chapterId");
        var url = ctx + "/cms/courseware/ajaxForm.php?id="+coursewareId;
        var isView = $(this).attr("class").indexOf("view-courseware");
        abc.block();
        $.ajax({
            type: "GET",
            url: url,
            async: false,
            success: function(html){
                $("#popup_courseware_div").html(html);
                if($(".js_course_isFree").val()=='1'){
                    $("#courseware_isFree_tr").hide();
                }
                $(".popup-courseware").show().css("z-index","100");
                $("div.bg").show();
                abc.unblock();
                if(isView != -1){
                    $('input,select,textarea,button',$('.popup-courseware')).attr('disabled',true);
                }

            }
        });
    });
    $("tbody.last").on("click",".view-courseware",function(e){
        var coursewareId = $(this).parents(".courseware-son").attr("data-id");
        var curriculumId = $("[name='curriculumId']").val();
        var url = ctx+'/cms/course/video/'+curriculumId+'/'+coursewareId;
        window.open(url);
    });







    /* 多媒体 上传 */
    $("body").on("click",".js_upload_media_btn",function(e){
        e.preventDefault();
        var obj = $(this);
        var media = $("#"+obj.attr("data-id")+"");
        var mediaName = media.attr("name");
        var msg = obj.siblings("label");
        var rs_div = obj.parent().parent().siblings(".js_media_div");

        var _val= media.val();
        if(_val==""){
            abc.layerAlert(false,'请选择上传');
            return;
        }
        var types= media.attr('data-type').split(';');
        var type=_val.substring(_val.lastIndexOf(".")+1);
        obj.hide();
        msg.html('正在上传.....');
        if(types.indexOf(type)<0){
            abc.layerAlert(false,'允许类型:['+types+"]");
            obj.show();
            msg.html('');
            return;
        }
        var url = ctx+'/util/mediaUpload.php?path=course&mediaName='+mediaName;
        if(type =='zip'){
            url = ctx+'/util/uploadM3u8Zip.php?path=course&mediaName='+mediaName;
        }
        $.ajaxFileUpload({
            url : url,
            type : 'POST',
            secureuri : false, // 一般设置为false
            fileElementId : obj.attr("data-id"),
            dataType : 'application/json',
            success : function(data, status) {
                obj.show();
                msg.html('');
                if(data.error){
                    abc.layerAlert(false, '操作失败:'+data.error);
                }else{
                    if(data){//上传成功
                        rs_div.html("<span>"+data.fileName+"</span><span style='width: 10%'>"+data.fileSize+"M</span><span style='width: 53%'>"+data.filePath+"</span>");
                    }
                }
            },
            error : function(data, status, e) {
            }
        });
    });


    /*章保存*/
    $('body').on('click', '.js_chapter_save', function(e){
        e.preventDefault();
        var curriculumId = $("[name='curriculumId']").val();
        if(!curriculumId){
            abc.layerAlert(false, '操作失败: 请先添加课程内容');
            return;
        }
        var obj = {};
        obj.curriculumId = curriculumId;
        obj.chapterId = $("#chapter_id").val();
        obj.chapterName = $("#chapter_content").val();
        obj.chapterSeq = $("#chapter_order").val();
        if(obj.chapterName ==''){
            abc.layerAlert(false, '操作失败: 章标题不能为空');
            return;
        }
        if(!(obj.chapterSeq).match(/^([1-9]\d{0,2})$/)){
            abc.layerAlert(false, '操作失败: 章排序范围[1-999]');
            return;
        }
        abc.layerAjaxConfirm("POST",ctx+"/cms/courseware/chapter/save.php",JSON.stringify(obj),ctx+"/cms/course/edit.php?id="+curriculumId+"&formName=courseware");
    });

    /* 添加课件 */
    $('body').on('click', '.js_courseware_save', function(e){
        e.preventDefault();
        var obj = {};
        obj.coursewareId = $("[name='courseware_Id']").val();//章id
        obj.curriculumId = $("[name='curriculumId']").val();//课程id
        obj.chapterId = $("[name='courseware_chapterId']").val();//章id
        obj.type = $("[name='coursewareType']:checked").val();//类型
        obj.title = $("[name='coursewareTitle']").val();//标题
        obj.coursewareSeq = $("[name='coursewareOrder']").val();//课件排序
        if(obj.title ==''){
            abc.layerAlert(false, '操作失败: 标题不能为空');
            return ;
        }
        if(!(obj.coursewareSeq).match(/^([1-9]\d{0,2})$/)){
            abc.layerAlert(false, '操作失败: 排序范围需在[1-999]');
            return ;
        }
        if($(".js_course_isFree").val()=='0'){
            obj.isFree = $("[name='isFree']:checked","#courseware_form").val();//是否免费
            if(!obj.isFree){
                abc.layerAlert(false, '操作失败: 请选择是否免费播放');
                return ;
            }
        }
        //根据类型 分别添加 不同的数据
        var tbody = $(".popup-courseware-"+obj.type);
        var uploadWay = tbody.find(".js_uploadWay_td");
        var duration = tbody.find(".js_duration_input");
        var isDownload = tbody.find(".js_isDownload_td");
        if(uploadWay){
            obj.uploadWay = uploadWay.find("input[type='radio']:checked").val();//1:文件上传，0:文件外链，2：第三方视频外链
        }
        if(uploadWay && obj.uploadWay =='1'){//如果 是文件上传
            var media_div = tbody.find(".js_media_div");
            obj.fileName = media_div.find("span:first").text();//文件名称
            obj.fileSize = media_div.find("span:eq(1)").text();//文件大小
            obj.fileSite = media_div.find("span:last").text();//文件地址
            if(!obj.fileName){
                abc.layerAlert(false, '操作失败: 请上传文件');
                return ;
            }
        }else{//如果 是文件外链  或者  是直播
            obj.link = tbody.find(".js_link_input").val();//链接
            if(obj.link.indexOf("http")== -1 && obj.link.indexOf("https")== -1 && obj.link.indexOf("ftp")== -1){
                abc.layerAlert(false, '操作失败: 链接必须以http|ftp|https开头');
                return ;
            }
            if(!/^(http|ftp|https):\/\/[\w]+(.[\w]+)([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])$/.test(obj.link)){
                abc.layerAlert(false, '操作失败: 链接格式不正确');
                return ;
            }
        }
        if(duration){
            obj.duration = duration.val();//时长
        }
        if(isDownload){//是否可以下载
            obj.isDownload = isDownload.find("input[type='radio']:checked").val();
        }

        abc.layerAjaxConfirm("POST",ctx+"/cms/courseware/save.php",
            JSON.stringify(obj),ctx+"/cms/course/edit.php?id="+obj.curriculumId+"&formName=courseware");
    });

});