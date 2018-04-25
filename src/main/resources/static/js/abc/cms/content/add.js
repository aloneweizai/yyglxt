window.UEDITOR_HOME_URL = ctx+"/js/plugin/ueditor/";

require(["../../../config"], function () {
    require(["jquery.full","wangEditor","template","ueditor","zeroClipboard","../plugin/bootstrap-select","../abc/cms/content/tag"], function ($,Editor,template,ueditor,zeroClipboard) {



        $(function(){
            // 初始化ZeroClipboard，此处一定要有，不然会报错
            window.ZeroClipboard = zeroClipboard;


            //96dd8f78bfe741ed9413910d65c3b527 对应的是内容字段  channelItem.modelitemId
            contentInputId = formNode.find("[data-labelname='内容']").attr("id");
            var ue = UE.getEditor(contentInputId,{
                initialFrameWidth: 1000,
                initialFrameHeight: 600,
                autoHeightEnabled: false
            });

            var sitePath = $("#siteId option:selected").data("sitepath");

            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;

            /*提醒：关于图片的配置，在ueditor.all.js中设置了可选的图片类型，默认为 accept="image/*", 已改为 accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp"*/
            UE.Editor.prototype.getActionUrl = function(action) {
                if(action == 'config'){
                    return ctx+'/util/initUeditorFileUpload.php';
                }
                else if(action == "uploadimage"){
                    return ctx+'/util/ueditorFileUpload.php?subsystem='+sitePath+'&fileTypeTag=image';
                }else {
                    return this._bkGetActionUrl.call(this, action);
                }
            };

            $("form[name='content_add_form']").validator({
                theme: 'yellow_right',
                timely: 0
            });

            //如果是已发布内容的查看，则设置所有输入项均不可编辑，只可查看
            if($("#view_input").val()=='view'){
                formNode.find("input,select,button,textarea").prop('disabled','disabled');
                formNode.find("div.w-e-text").attr("contenteditable","false");
                formNode.find("input[name='content_back_btn']").prop("disabled",false);

                //设置ueditor不可编辑
                ue.ready( function( editor ) {
                    ue.setDisabled();
                } );
            }



        });

        $(function(){
            var tplType =  $("form[name='content_add_form']").find("select[name='tplType']").val();
            var tplContent =  $("form[name='content_add_form']").find("select[name='tplContent']").val();
            var siteId = $("#siteId").val();
            var modelId =  $("form[name='content_add_form']").find("input[name='modelId']").val();

            if(tplType!=null && tplType!=""){
                $.ajax({
                    url : ctx + "/content/column/queryColumnTemplate.php",
                    type: "get",

                    data: {
                        siteId : siteId,
                        modelId : modelId,
                        tplType : tplType
                    },
                    success : function(result){
                        $("form[name='content_add_form']").find("select[name='tplContent']").html(template("content_template_list_tmpl",{list:result}));
                        $("form[name='content_add_form']").find("select[name='tplContent']").val(tplContent)
                    },
                    error : function () {
                        layer.alert("查询失败", {icon: 5});
                    }
                });
            }




            var form= layui.form;

            $("form[name='content_add_form']").find("select[name='tplType']").attr("lay-filter","tplType");
            form.on("select(tplType)",function(data){
                var siteId = $("#siteId").val();
                var modelId = formNode.find("input[name='modelId']").val();
                var tplType = data.value;
                //alert("siteId="+siteId+",modelId="+modelId+"tplType="+tplType);
                $.ajax({
                     url : ctx + "/content/column/queryColumnTemplate.php",
                     type: "get",
                     data: {
                         siteId : siteId,
                         modelId : modelId,
                         tplType : tplType
                     },
                     success : function(result){
                         formNode.find("select[name='tplContent']").html(template("content_template_list_tmpl",{list:result}));
                         form.render('select');
                         //for(var i in editorMap){
                         //    if(editorMap.hasOwnProperty(i)){
                         //        editorMap[i].config.uploadImgParams = {
                         //            subsystem : sitePath,
                         //            fileTypeTag : "image",
                         //            siteDomain : siteDomain
                         //        };
                         //    }
                         //}
                     },
                     error : function () {
                         layer.alert("查询失败", {icon: 5});
                     }
                 });
            });
        });


        var formNode = $("form[name='content_add_form']");
        var editorMap = {};
        var editor;


        var siteId = $("#siteId").val();
        var modelId = formNode.find("input[name='modelId']").val();
        var sitePath = $("#siteId").find("option:selected").data("sitepath");
        var siteDomain = $("#siteId").find("option:selected").data("domain");

        if ($.trim(siteId) && $.trim(modelId)) {

            var gathterAndEditRadio = formNode.find("input[name='gatherAndEdit']");
            gathterAndEditRadio.on("click",function(){
                if($(this).val()=='0'){
                    formNode.find("input[name='origin']").val("ABC财税网");
                }else{
                    formNode.find("input[name='origin']").val("");
                }
            })


            formNode.find("[abc-type='textarea']").each(function(){

                editor = new Editor($(this).get(0));
                editor.customConfig.uploadImgServer = ctx+'/content/content/editorFileUpload.php';
                editor.customConfig.uploadImgParams = {
                    subsystem : sitePath,
                    fileTypeTag : "image",
                    siteDomain : siteDomain
                };
                editor.customConfig.customAlert = function (info) {
                    layer.alert(info, {offset: abc.winscrollTop(300),icon: 5});
                };
                editor.customConfig.uploadImgHooks = {
                    fail: function (xhr, editor, result) {
                        if(result.errno=="-2"){
                            layer.alert("请选择所属站点或该站点未配置访问路径", {offset: abc.winscrollTop(300),icon: 5});
                        }else if(result.errno=="-1"){
                            layer.alert("上传图片失败", {offset: abc.winscrollTop(300),icon: 5});
                        }else if(result.errno == "-3"){
                            layer.alert(result.errMsg, {offset: abc.winscrollTop(300),icon: 5});
                        }else{
                            layer.alert("上传图片失败", {offset: abc.winscrollTop(300),icon: 5});
                        }
                    }
                };


                //特别设置摘要输入框的菜单
                if($(this).attr("name")=='shortTitle'){
                    editor.customConfig.menus = [

                    ];
                }

                editor.create();

                //特别设置摘要输入框的大小
                if($(this).attr("name")=='shortTitle'){
                    editor.$textContainerElem.css('height', '100px');
                    $(this).attr("style","width: 100%;");
                }

                editorMap[$(this).attr('name')] = editor;
            });
        }else{
            formNode.find("[abc-type='textarea']").each(function(){

                editor = new Editor($(this).get(0));
                editor.customConfig.uploadImgServer = ctx+'/content/content/editorFileUpload.php';
                editor.customConfig.uploadImgParams = {
                    subsystem : "",
                    fileTypeTag : "image",
                    siteDomain : ""
                };
                editor.customConfig.uploadImgHooks = {
                    fail: function (xhr, editor, result) {
                        if(result.errno=="-2"){
                            layer.alert("请选择所属站点或该站点未配置访问路径", {offset: abc.winscrollTop(300),icon: 5});
                        }else if(result.errno=="-1"){
                            layer.alert("上传图片失败", {offset: abc.winscrollTop(300),icon: 5});
                        }else{
                            layer.alert("上传图片失败", {offset: abc.winscrollTop(300),icon: 5});
                        }
                    }
                };

                if($(this).attr("name")=='shortTitle'){
                    editor.customConfig.menus = [

                    ];
                }

                editor.create();
                if($(this).attr("name")=='shortTitle'){
                    editor.$textContainerElem.css('height', '100px')
                    $(this).attr("style","width: 100%");
                }
                editorMap[$(this).attr('name')] = editor;
            });
        }

        formNode.find("[abc-type='datebox']").each(function(){
            var targetNode = $(this);
            targetNode.datebox({
                editable: false,
                formatter: function (date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d);
                },
                parser: function (str) {
                    if (!str) {
                        return new Date();
                    }
                    var ss = str.split("-");
                    var y = parseInt(ss[0], 10);
                    var m = parseInt(ss[1], 10);
                    var d = parseInt(ss[2], 10);
                    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                        return new Date(y, m - 1, d);
                    } else {
                        return new Date();
                    }
                }
            });

            if(targetNode.attr("textboxname")=="releaseDate"){
                //easyui日期框默认加载日历时只能加载今天以前的日期
                targetNode.datebox().datebox('calendar').calendar({
                    validator:function(date){
                        var  now = new Date();
                        var mDate = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                        return date <= mDate;
                    }
                });
            }
        });

        formNode.find("button[name='_channel_btn']").on("click",function(){
            var targetNode = $(this);

            var siteId = $("#siteId").val();
            if(!$.trim(siteId)){
                layer.msg("请选择所属站点",{offset: abc.winscrollTop(300)});
                return;
            }

            $.ajax({
                type: "GET",
                url: ctx + "/content/column/ajaxColTree.php?channelId=&siteId=" + siteId,
                success: function (result) {
                    layer.open({
                        area: ['600px', '700px'],
                        type: 1,
                        title: "请选择栏目",
                        content: result,
                        success: function(){
                            $("div[name='column_tree_layer'] a[name='column_tree_name']").off("click").on("click",function(){
                                // targetNode.siblings("input[type='hidden']").val($(this).data("channelid"));
                                // targetNode.siblings("input[type='text']").val($(this).data("channelname"));
                                // layer.closeAll();
                                var channelId = $(this).data("channelid");

                                layer.confirm("重新选择栏目将重新加载本页面，是否确认？", {
                                    btn: ['确定', '取消'],
                                    closeBtn: 0
                                },function () {/**/
                                    location.href = ctx + "/content/content/addPage.php?channelId=" + channelId;
                                },function(){
                                    layer.closeAll();
                                });
                            });
                        }
                    });
                },
                error: function () {
                    layer.msg('查询栏目信息失败', {offset: abc.winscrollTop(300),icon: 5});
                }
            });
        });

        formNode.find("button[name='_color_btn']").each(function(){
            var targetNode = $(this);
            targetNode.colpick({
                layout:'hex',
                onSubmit:function(hbs,hex,rgb){
                    targetNode.siblings("input[type='text']").val("#"+hex);
                    targetNode.colpickHide();
                },
                onChange:function(hbs,hex,rgb){
                    targetNode.siblings("input[type='text']").val("#"+hex);
                }
            });
        });

        formNode.find("button[name='_file_upload_btn']").on("click",function(){
            var targetBtn = $(this);
            var fileNode = targetBtn.siblings("input[type='file']");

            if(!fileNode.get(0).files || fileNode.get(0).files.length<=0){
                layer.msg("请选择文件",{offset: abc.winscrollTop(300)});
                return;
            }

            var sitePath = $("#siteId option:selected").data("sitepath");
            if(!$.trim(sitePath)){
                layer.msg("请选择所属站点或该站点路径未配置",{offset: abc.winscrollTop(300)});
                return;
            }

            var id = fileNode.attr("id");
            var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajaxFileUpload({
                url: ctx + '/content/content/fileUpload.php?subsystem='+sitePath+'&fileTypeTag=file', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: id, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (result){  //服务器成功响应处理函数
                    //console.log(result);
                    if(result.code=='2000') {
                        layer.alert(result.msg, {offset: abc.winscrollTop(300),icon: 1});
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_name']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_path']").val(result.filePath + "/" + result.fileName);
                    }else{
                        layer.alert(result.msg, {offset: abc.winscrollTop(300),icon: 5});
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {offset: abc.winscrollTop(300),icon: 5});
                },
                complete: function(){
                    layer.close(loadIndex);
                    targetBtn.siblings("input[type='file']").get(0).outerHTML=targetBtn.siblings("input[type='file']").get(0).outerHTML;
                }
            });
        });

        formNode.find("button[name='_attachement_upload_btn']").on("click",function(){
            uploadAttachement($(this));
        });

        formNode.find("button[name='_img_upload_btn']").on("click",function(){
            var targetBtn = $(this);
            var fileNode = targetBtn.siblings("input[type='file']");

            if(!fileNode.get(0).files || fileNode.get(0).files.length<=0){
                layer.msg("请选择文件",{offset: abc.winscrollTop(300)});
                return;
            }

            var sitePath = $("#siteId option:selected").data("sitepath");
            if(!$.trim(sitePath)){
                layer.msg("请选择所属站点或该站点路径未配置",{offset: abc.winscrollTop(300)});
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
                    //console.log(result);
                    if(result.code=='2000') {
                        layer.alert(result.msg, {offset: abc.winscrollTop(300),icon: 1});

                        var siteNode = $("select[name='siteId'] option:selected");
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_name']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_path']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("img").attr("src","http://" + siteNode.data("domain") + result.filePath + "/" + result.fileName);
                    }else{
                        layer.alert(result.msg, {offset: abc.winscrollTop(300),icon: 5});
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {offset: abc.winscrollTop(300),icon: 5});
                },
                complete: function(){
                    layer.close(loadIndex);
                    targetBtn.siblings("input[type='file']").get(0).outerHTML=targetBtn.siblings("input[type='file']").get(0).outerHTML;
                }
            });
        });

        formNode.find("input[name='content_submit_btn']").off("click").on("click",function(){

            //layer.load(1,{shade: [0.6,"#393D49"]});

            var contentId = formNode.find("input[name='contentId']").val();
            var customAttr = [];
            var picArr = [];
            var fileArr = [];
            var status = "";

            var param = {},customTmp;

            param.status=status=$(this).attr("status-val");

            formNode.find("input[type='text'],input[type='number'],input[type='hidden'],input.easyui-datebox,select,input[type='radio']:checked,textarea").each(function () {
                if($(this).attr("abc-custom")=="0"){
                    param[$(this).attr("name")] = $(this).val();
                }else if($(this).attr("abc-custom")=="1"){
                    customTmp = {};
                    customTmp.attrName = $(this).attr("name");
                    customTmp.attrValue = $(this).val();
                    customAttr.push(customTmp);
                }
            });

            formNode.find("[abc-type='datebox']").each(function(){
                if($(this).attr("abc-custom")=="0"){
                    param[$(this).attr("textboxname")] = $(this).val();
                }else if($(this).attr("abc-custom")=="1"){
                    customTmp = {};
                    customTmp.attrName = $(this).attr("textboxname");
                    customTmp.attrValue = $(this).val();
                    customAttr.push(customTmp);
                }
            });

            //formNode.find("[abc-type='ueditArea']").each(function(){
            //    alert(123);
            //    if($(this).attr("abc-custom")=="0"){
            //contentInputId = formNode.find("[data-labelname='内容']").attr("id");
            var ue = UE.getEditor(contentInputId);


            param["txt"] = ue.getContent();
            //    }else if($(this).attr("abc-custom")=="1"){
            //        customTmp = {};
            //        customTmp.attrName = $(this).attr("name");
            //        const ue = UE.getEditor("96dd8f78bfe741ed9413910d65c3b527");
            //        customTmp.attrValue = ue.getContent();
            //        customAttr.push(customTmp);
            //    }
            //});


            formNode.find("input[type='checkbox']:checked").each(function(){

                if($(this).attr("abc-custom")=="0"){
                    if(param[$(this).attr("name")]){
                        param[$(this).attr("name")] += ";"+$(this).val();
                    }else{
                        param[$(this).attr("name")] = $(this).val();
                    }
                }else if($(this).attr("abc-custom")=="1"){
                    var hasProperty = false;
                    for(var i in customAttr){
                        if(customAttr.hasOwnProperty(i)){
                            if(customAttr[i].attrName==$(this).attr("name")){
                                hasProperty = true;
                                customAttr[i].attrValue += ";"+$(this).val();
                                break;
                            }
                        }
                    }
                    if(!hasProperty){
                        customTmp = {};
                        customTmp.attrName = $(this).attr("name");
                        customTmp.attrValue = $(this).val();
                        customAttr.push(customTmp);
                    }
                }
            });
            var title;
            formNode.find("[abc-type='textarea']").each(function(){
                if($(this).attr("abc-custom")=="0") {
                    if($(this).attr("name")=="shortTitle"){
                        param[$(this).attr("name")] = editorMap[$(this).attr("name")].txt.text();
                        title = editorMap[$(this).attr("name")].txt.text();
                    }else{
                        param[$(this).attr("name")] = editorMap[$(this).attr("name")].txt.html();
                    }

                }else if($(this).attr("abc-custom")=="1"){
                    customTmp = {};
                    customTmp.channelId = channelId;
                    customTmp.attrName = $(this).attr("name");
                    if($(this).attr("name")=="shortTitle"){
                        customTmp.attrValue = editorMap[$(this).attr("name")].txt.text();
                        title = editorMap[$(this).attr("name")].txt.text();
                    }else{
                        customTmp.attrValue = editorMap[$(this).attr("name")].txt.html();
                    }

                    customAttr.push(customTmp);
                }
                return true;
            });

            if(title!=null && title.length>330){
                layer.msg("摘要最大长度为330", {offset: abc.winscrollTop(300),icon: 5}, function(){
                    layer.closeAll();
                });
                return;
            }
            if(ue.getContent()==null || ue.getContent()==""){
                layer.msg("内容不能为空", {offset: abc.winscrollTop(300),icon: 5}, function(){
                    layer.closeAll();
                });
                return;
            }

            formNode.find("input[type='file']").each(function(){
                var targetNode = $(this);
                var fieldName = targetNode.data("field");
                var type = targetNode.data("type");
                var custom = targetNode.attr("abc-custom");
                customTmp = {};

                if (custom == 1) {
                    customTmp.fieldName = fieldName;
                    if (type === "img") {
                        customTmp.description = targetNode.siblings("input[name='" + fieldName + "_name']").val();
                        customTmp.imgPath = targetNode.siblings("input[name='" + fieldName + "_path']").val();
                        customTmp.priority = "1";
                        picArr.push(customTmp);

                    } else if (type == 'file') {
                        customTmp.fileName = targetNode.siblings("input[name='" + fieldName + "_name']").val();
                        customTmp.filePath = targetNode.siblings("input[name='" + fieldName + "_path']").val();
                        customTmp.fileIsvalid = "1";
                        fileArr.push(customTmp);
                    }else if (type == 'attachement') {
                        customTmp.fileName = targetNode.parent().parent().find("input[name='fileName']").val();
                        customTmp.filePath = targetNode.parent().parent().find("input[name='filePath']").val();
                        customTmp.fileIsvalid = "1";
                        fileArr.push(customTmp);
                    }
                } else {
                    param[fieldName] = targetNode.siblings("input[name='" + fieldName + "_path']").val();
                }
            });

            var contentType = "";
            //var selectedTxtArr = $('#contentType').selectpicker('val');
            //
            //if($('#contentType').data("isrequired")=="1" && selectedTxtArr.length<=0){
            //    layer.msg(result.msg||"请选择内容标签", {offset: abc.winscrollTop(300),icon: 5}, function(){
            //        layer.closeAll();
            //    });
            //    return;
            //}



            //for(var index in selectedTxtArr){
            //    if(contentType){
            //        contentType += ";";
            //    }
            //    contentType += selectedTxtArr[index];
            //}
            $("#labelDiv input[name='tagIds']").each(function(){
                if(contentType){
                    contentType += ";";
                }
                contentType += $(this).val();
            });
            param.contentTagStr = contentType;

            param.contentAttrStr = JSON.stringify(customAttr);
            param.contentPictureStr = JSON.stringify(picArr);
            param.contentFileStr = JSON.stringify(fileArr);



            //清空验证
            $("form[name='content_add_form']").validator( "cleanUp" );

            $("form[name='content_add_form']").isValid(function(checkResult) {
                if (checkResult) {

                    for(var i in editorMap){
                        if(editorMap.hasOwnProperty(i)){
                            if($(editorMap[i].toolbarSelector).data("rule")=="required" && !editorMap[i].txt.text()){
                                layer.msg($(editorMap[i].toolbarSelector).data("labelname")+"不能为空", {offset: abc.winscrollTop(300),icon: 5}, function(){
                                    layer.closeAll();
                                });
                                return false;
                            }
                        }
                    }
                    var msg;
                    if(status=="2"){
                        msg =  "是否确认发布？"
                    }
                    else{
                        msg =  "是否确认保存？"
                    }
                    var confirmIndex = layer.confirm(msg, {offset: abc.winscrollTop(300),title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                        layer.close(confirmIndex);
                        var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});

                        $.ajax({
                            url: ctx + "/content/content/insertOrUpdate.php",
                            type: "post",
                            data: param,
                            success: function (result) {
                                if (result.code == '2000') {
                                    var ids = [];
                                    ids.push(result.contentId);
                                    if(status=="2"){
                                        $.ajax({
                                            url : ctx + "/content/sysmaintain/static_some_content.php",
                                            type : "get",
                                            data : {
                                                "ids[]" : ids
                                            },
                                            success : function (generateResult) {
                                                if(generateResult.code=="2000"){
                                                    layer.msg("操作成功", {offset: abc.winscrollTop(300),icon: 1,closeBtn:0,title:""},function () {
                                                        layer.closeAll();
                                                        //location.href = ctx + "/content/content/list.php";
                                                        window.location.href = document.referrer;
                                                    });
                                                }else{
                                                    layer.msg(generateResult.message||"操作失败", {offset: abc.winscrollTop(300),icon: 5},function () {
                                                        layer.closeAll();
                                                        //location.href = ctx + "/content/content/list.php";
                                                        window.location.href = document.referrer;
                                                    });
                                                }
                                            },
                                            error : function () {
                                                layer.msg("操作失败", {offset: abc.winscrollTop(300),icon: 5},function () {
                                                    layer.closeAll();
                                                });
                                            }
                                        });
                                    }else{
                                        layer.msg("操作成功", {offset: abc.winscrollTop(300),icon: 1,closeBtn:0,title:""},function () {
                                            layer.closeAll();
                                            //location.href = ctx + "/content/content/list.php";
                                            window.location.href = document.referrer;
                                        });
                                    }
                                } else {
                                    layer.msg(result.msg || "操作失败", {offset: abc.winscrollTop(300),icon: 5},function () {
                                        layer.closeAll();
                                    });
                                }

                            },
                            error: function (err) {
                                layer.msg("操作失败", {offset: abc.winscrollTop(300),icon: 5},function () {
                                    layer.closeAll();
                                });
                            }
                        });
                    },function () {
                        layer.closeAll();
                    });
                }else{
                    layer.closeAll();
                }
            });
        });

        formNode.find("input[name='content_back_btn']").on("click",function(){
            history.back();
        });

        formNode.find("input[name='content_reset_btn']").off("click").on("click",function(){
            formNode.get(0).reset();
            for(var i in editorMap){
                if(editorMap.hasOwnProperty(i)){
                    editorMap[i].txt.clear();
                }
            }
        });

        //alert(234)
        //formNode.find("select[name='tplType']").on("change",function(){
        //    alert(123)
        //    var siteId = $("#siteId").val();
        //    alert(siteId)
        //    var modelId = formNode.find("input[name='modelId']").val();
        //    var tplType = $(this).val();
        //    $.ajax({
        //         url : ctx + "/content/column/queryColumnTemplate.php",
        //         type: "get",
        //         data: {
        //             siteId : siteId,
        //             modelId : modelId,
        //             tplType : tplType
        //         },
        //         success : function(result){
        //             formNode.find("select[name='tplContent']").html(template("content_template_list_tmpl",{list:result}));
        //             //for(var i in editorMap){
        //             //    if(editorMap.hasOwnProperty(i)){
        //             //        editorMap[i].config.uploadImgParams = {
        //             //            subsystem : sitePath,
        //             //            fileTypeTag : "image",
        //             //            siteDomain : siteDomain
        //             //        };
        //             //    }
        //             //}
        //         },
        //         error : function () {
        //             layer.alert("查询失败", {icon: 5});
        //         }
        //     });
        //});

        // formNode.find("select[name='siteId']").on("change",function(){
        //     var selectedNode = $(this);
        //     var siteId = $(this).val();
        //     var text = $(this).find("option:selected").text();
        //     var sitePath = $(this).find("option:selected").data("sitepath");
        //     var modelId = formNode.find("input[name='modelId']").val();
        //     var siteDomain = $("#siteId").find("option:selected").data("domain");
        //     if(!$.trim(siteId)){
        //         return;
        //     }
        //
        //     if(!$.trim(modelId)){
        //         layer.msg("请选择模型");
        //         return;
        //     }
        //
        //
        //     var index = layer.confirm("该内容将归属于[" + text + "]站点，确认将不能进行修改，是否确认?", {
        //         btn: ['确定', '取消'],
        //         closeBtn: 0
        //     },function () {
        //         selectedNode.find("option:selected").siblings("option").detach();
        //         layer.close(index);
        //         $.ajax({
        //             url : ctx + "/content/column/queryColumnTemplate.php",
        //             type: "get",
        //             data: {
        //                 siteId : siteId,
        //                 modelId : modelId
        //             },
        //             success : function(result){
        //                 formNode.find("select[name='tplContent']").html(template("content_template_list_tmpl",{list:result}));
        //                 for(var i in editorMap){
        //                     if(editorMap.hasOwnProperty(i)){
        //                         editorMap[i].config.uploadImgParams = {
        //                             subsystem : sitePath,
        //                             fileTypeTag : "image",
        //                             siteDomain : siteDomain
        //                         };
        //                     }
        //                 }
        //             },
        //             error : function () {
        //                 layer.alert("查询失败", {icon: 5});
        //             }
        //         });
        //     },function () {
        //         selectedNode.val("");
        //     });
        //
        // });

        $("#contentType").selectpicker({});



        //添加附件输入行
        formNode.find("button[name='addAttachementInput']").on("click",function(){
            //获取最后一个附件输入行
            var tdTpl = $("#attachementInputTpl").html();
            //var lastTdHtml = formNode.find("table[name='attachementTable'] tbody").find("tr:last-child").html();

            //如果获取table 中的 tbody，或者tbody中没有，直接table下的输入行id
            var lastTdId = formNode.find("table[name='attachementTable'] tbody").find("tr:last-child").find("input[name='file']").attr("id");
            if(lastTdId == null){
                lastTdId = formNode.find("table[name='attachementTable']").find("tr:last-child").find("input[name='file']").attr("id");
            }
            var newTdHtml = "";
            if(lastTdId==null){
                newTdHtml =  tdTpl;
            }else{
                var lastTdIndex = 0;
                if(lastTdId!=null){
                    lastTdIndex = parseInt(lastTdId.split("_")[1]);
                }
                //生成新的一个附件输入行
                newTdHtml =  tdTpl.replace("file_0","file_"+(lastTdIndex+1));

            }
            formNode.find("table[name='attachementTable']").append("<tr>"+newTdHtml+"</tr>");


            formNode.find("button[name='_attachement_delete_btn']").unbind('click').on("click",function(){
                deleteAttachementTd($(this));
            });
            formNode.find("button[name='_attachement_upload_btn']").unbind('click').on("click",function(){
                uploadAttachement($(this));
            });
        });

        //删除附件输入行
        formNode.find("button[name='_attachement_delete_btn']").unbind('click').on("click",function(){
            deleteAttachementTd($(this));
        });

        function deleteAttachementTd($delBtn){
            $delBtn.parent().parent().remove();
        }

        function uploadAttachement($uploadBtn){
            var targetBtn = $uploadBtn;
            var fileNode = targetBtn.parent().parent().find("input[type='file']");

            if(!fileNode.get(0).files || fileNode.get(0).files.length<=0){
                layer.msg("请选择文件",{offset: abc.winscrollTop(300)});
                return;
            }

            var sitePath = $("#siteId option:selected").data("sitepath");
            if(!$.trim(sitePath)){
                layer.msg("请选择所属站点或该站点路径未配置",{offset: abc.winscrollTop(300)});
                return;
            }

            var id = fileNode.attr("id");
            var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajaxFileUpload({
                url: ctx + '/content/content/fileUpload.php?subsystem='+sitePath+'&fileTypeTag=attachement', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: id, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (result){  //服务器成功响应处理函数
                    //console.log(result);
                    if(result.code=='2000') {
                        var siteNode = $("select[name='siteId'] option:selected");
                        layer.alert(result.msg, {offset: abc.winscrollTop(300),icon: 1});
                        //targetBtn.siblings("input[name='fileName']").val(result.filePath + "/" + result.fileName);
                        targetBtn.parent().parent().find("input[name='filePath']").val(result.filePath + "/" + result.fileName);
                        targetBtn.parent().parent().find("a[name='fileUrl']").attr("href","http://" + siteNode.data("domain") + result.filePath + "/" + result.fileName).text(result.fileName);
                        targetBtn.parent().parent().find("a[name='fileUrl']").parent().show();
                    }else{
                        layer.alert(result.msg, {offset: abc.winscrollTop(300),icon: 5});
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {offset: abc.winscrollTop(300),icon: 5});
                },
                complete: function(){
                    layer.close(loadIndex);
                    targetBtn.parent().parent().find("input[type='file']").get(0).outerHTML=targetBtn.parent().parent().find("input[type='file']").get(0).outerHTML;
                }
            });
        }

    });
    //var ue =  UE.getEditor('96dd8f78bfe741ed9413910d65c3b527');

});