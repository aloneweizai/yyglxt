require(["../../../config"], function () {
    require(["jquery.full","wangEditor","template"], function ($,Editor,template) {

        var formNode = $("form[name='model_add_form']");
        var editorMap = {};
        var editor;

        var siteId = $("#siteId").val();
        var modelId = formNode.find("select[name='modelId']").val();
        var sitePath = $("#siteId").find("option:selected").data("sitepath");
        var siteDomain = $("#siteId").find("option:selected").data("domain");
        if ($.trim(siteId) && $.trim(modelId)) {
            formNode.find("[abc-type='textarea']").each(function () {
                editor = new Editor($(this).get(0));
                editor.customConfig.uploadImgServer = ctx + '/content/content/editorFileUpload.php';
                editor.customConfig.uploadImgParams = {
                    subsystem: sitePath,
                    fileTypeTag: "image",
                    siteDomain: siteDomain
                };
                editor.customConfig.uploadImgHooks = {
                    fail: function (xhr, editor, result) {
                        if (result.errno == "-2") {
                            layer.alert("请选择所属站点或该站点未配置访问路径", {icon: 5});
                        } else if (result.errno == "-1") {
                            layer.alert("上传图片失败", {icon: 5});
                        } else {
                            layer.alert("上传图片失败", {icon: 5});
                        }
                    }
                };

                editor.create();
                editorMap[$(this).attr('name')] = editor;
            });
        } else {
            formNode.find("[abc-type='textarea']").each(function () {
                editor = new Editor($(this).get(0));
                editor.customConfig.uploadImgServer = ctx + '/content/content/editorFileUpload.php';
                editor.customConfig.uploadImgParams = {
                    subsystem: "",
                    fileTypeTag: "image",
                    siteDomain: ""
                };
                editor.customConfig.uploadImgHooks = {
                    fail: function (xhr, editor, result) {
                        if (result.errno == "-2") {
                            layer.alert("请选择所属站点或该站点未配置访问路径", {icon: 5});
                        } else if (result.errno == "-1") {
                            layer.alert("上传图片失败", {icon: 5});
                        } else {
                            layer.alert("上传图片失败", {icon: 5});
                        }
                    }
                };

                editor.create();
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
        });

        formNode.find("input[name='model_submit_btn']").off("click").on("click",function(){

            layer.load(1,{shade: [0.6,"#393D49"]});

            var channelId = formNode.find("input[name='channelId']").val();

            var customAttr = [];
            var picArr = [];
            var fileArr = [];

            var param = {},customTmp;
            formNode.find("input[type='text'],input[type='number'],input[type='hidden'],input.easyui-datebox,select,input[type='radio']:checked").each(function () {

                if($(this).attr("abc-custom")=="0"){
                    if($(this).attr("name")=='channelPath'){
                        param[$(this).attr("name")] = $(this).siblings("input[name='parentPath']").val() + $(this).val();
                    }else{
                        param[$(this).attr("name")] = $(this).val();
                    }
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

            formNode.find("[abc-type='textarea']").each(function(){
                if($(this).attr("abc-custom")=="0") {
                    param[$(this).attr("name")] = editorMap[$(this).attr("name")].txt.html();
                }else if($(this).attr("abc-custom")=="1"){
                    customTmp = {};
                    customTmp.channelId = channelId;
                    customTmp.attrName = $(this).attr("name");
                    customTmp.attrValue = editorMap[$(this).attr("name")].txt.html();;
                    customAttr.push(customTmp);
                }
                return true;
            });

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
                    }
                } else if(custom == 0){
                    param[fieldName] = targetNode.siblings("input[name='" + fieldName + "_path']").val();
                }
            });

            param.columnAttrStr = JSON.stringify(customAttr);
            param.columnPictureStr = JSON.stringify(picArr);
            param.columnFileStr = JSON.stringify(fileArr);

            $("form[name='model_add_form']").isValid(function(checkResult) {
                if (checkResult) {
                    for(var i in editorMap){
                        if(editorMap.hasOwnProperty(i)){
                            if($(editorMap[i].toolbarSelector).data("rule")=="required" && !editorMap[i].txt.text()){
                                layer.msg($(editorMap[i].toolbarSelector).data("labelname")+"不能为空", {icon: 5}, function(){
                                    layer.closeAll();
                                });
                                return false;
                            }
                        }
                    }
                    layer.confirm("是否确认提交？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                        $.ajax({
                            url: ctx + "/content/column/insertOrUpdate.php",
                            type: "post",
                            data: param,
                            success: function (result) {
                                //console.log(result);
                                if (result.code == '2000') {
                                    layer.msg("操作成功", {icon: 1,closeBtn:0, title: ""},function () {
                                        location.href = ctx + "/content/column/list.php";
                                    });
                                } else {
                                    layer.msg(result.message || "操作失败", {icon: 5},function () {
                                        layer.closeAll();
                                    });
                                }

                            },
                            error: function (err) {
                                layer.msg("操作失败", {icon: 5},function () {
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

        formNode.find("input[name='model_reset_btn']").off("click").on("click",function(){
            // formNode.get(0).reset();
            // for(var i in editorMap){
            //     if(editorMap.hasOwnProperty(i)){
            //         editorMap[i].txt.clear();
            //     }
            // }

            history.back();

        });

        formNode.find("button[name='_channel_btn']").on("click",function(){

            var siteId = $("#siteId").val();
            if(!$.trim(siteId)){
                layer.msg("请选择所属站点");
                return;
            }

            var targetNode = $(this);
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
                                targetNode.siblings("input[type='hidden']").val($(this).data("channelid"));
                                targetNode.siblings("input[type='text']").val($(this).data("channelname"));
                                $("#parentPath").val($(this).data("channelpath")).attr("readonly","readonly");
                                layer.closeAll();
                            });
                        }
                    });
                },
                error: function () {
                    layer.msg('查询栏目信息失败', {icon: 5});
                }
            });
        });

        formNode.find("button[name='_img_upload_btn']").on("click",function(){
            var targetBtn = $(this);
            var fileNode = targetBtn.siblings("input[type='file']");

            if(!fileNode.get(0).files || fileNode.get(0).files.length<=0){
                layer.msg("请选择文件");
                return;
            }

            var sitePath = $("#siteId option:selected").data("sitepath");
            if(!$.trim(sitePath)){
                layer.msg("请选择所属站点或该站点路径未配置");
                return;
            }

            var id = fileNode.attr("id");
            var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajaxFileUpload({
                url: ctx+'/content/content/fileUpload.php?subsystem='+sitePath+'&fileTypeTag=image', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: id, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (result){  //服务器成功响应处理函数
                    //console.log(result);
                    if(result.code=='2000') {
                        layer.alert(result.msg, {icon: 1});
                        var siteNode = $("select[name='siteId'] option:selected");
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_name']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_path']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("img").attr("src","http://" + siteNode.data("domain") + result.filePath + "/" + result.fileName);
                    }else{
                        layer.alert(result.msg, {icon: 5});
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {icon: 5});
                },
                complete: function(){
                    layer.close(loadIndex);
                    targetBtn.siblings("input[type='file']").get(0).outerHTML=targetBtn.siblings("input[type='file']").get(0).outerHTML;
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
                layer.msg("请选择文件");
                return;
            }

            var sitePath = $("#siteId option:selected").data("sitepath");
            if(!$.trim(sitePath)){
                layer.msg("请选择所属站点或该站点路径未配置");
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
                        layer.alert(result.msg, {icon: 1});
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_name']").val(result.filePath + "/" + result.fileName);
                        targetBtn.siblings("input[name='" + fileNode.data("field") + "_path']").val(result.filePath + "/" + result.fileName);
                    }else{
                        layer.alert(result.msg, {icon: 5});
                    }
                },
                error: function (){//服务器响应失败处理函数
                    layer.alert("上传文件失败", {icon: 5});
                },
                complete: function(){
                    layer.close(loadIndex);
                    targetBtn.siblings("input[type='file']").get(0).outerHTML=targetBtn.siblings("input[type='file']").get(0).outerHTML;
                }
            });
        });

        formNode.find("select[name='siteId']").on("change",function(){
            var selectedNode = $(this);
            var siteId = $(this).val();
            var text = $(this).find("option:selected").text();
            var sitePath = $(this).find("option:selected").data("sitepath");
            var modelId = formNode.find("select[name='modelId']").val();
            var siteDomain = $("#siteId").find("option:selected").data("domain");

            if(!$.trim(siteId)){
                return;
            }

            if(!$.trim(modelId)){
                layer.msg("请选择模型");
                return;
            }

            var index = layer.confirm("该栏目将归属于[" + text + "]站点，确认将不能进行修改，是否确认?", {
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
                        modelId : modelId
                    },
                    success : function(result){
                        formNode.find("select[name='tplChannel']").html(template("column_template_list_tmpl",{list:result}));
                        formNode.find("select[name='tplContent']").html(template("column_template_list_tmpl",{list:result}));
                        for(var i in editorMap){
                            if(editorMap.hasOwnProperty(i)){
                                editorMap[i].config.uploadImgParams = {
                                    subsystem : sitePath,
                                    fileTypeTag : "image",
                                    siteDomain: siteDomain
                                };
                            }
                        }
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