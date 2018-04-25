/**
 * Created by zhouhi on 2017/6/6.
 */
require(["../../config"], function () {
    require(["jquery.full","uploadify","nicevalidator","nicevalidator.zh_CN","abc.common","layui"], function ($) {

        $(function () {

            genereateFileTree();




            //$('.fileModifyBtn').bind('click',function(){
            //    showFileContext($(this).parent().parent().find('input[name="templatePath_list"]').val());
            //});

            //$('.fileAttriModifyBtn').bind('click',function(){
            //    showFileAttri($(this).parent().parent().find('input[name="templatePath_list"]').val());
            //});

            $('.fileDeleteBtn').bind('click',function(){
                var fileDeleteBtn = $(this);
                layer.confirm('是否删除该模板文件？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                    function(index){
                        deleteFile(fileDeleteBtn);
                    }
                );

            });

            $('.dirDeleteBtn').bind('click',function(){
                var dirDeleteBtn = $(this);
                layer.confirm('是否删除该模板文件夹？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                    function(index){
                        deleteDir(dirDeleteBtn);
                    }
                );

            })

            $('.start_stop_Btn').bind('click',function(){
                start_stop_tpl($(this));
            });

            //新上传模板文件链接
            //$("a[name='addTplFile']").click(function(){
            //    showUploadTplFileDiv();
            //});
            $("a[name='addTplFile']").hide();


            //新建文件夹链接
            //$("a[name='addTplDir']").click(function(){
            //    showAddTplDirDiv();
            //});
            $("a[name='addTplDir']").hide();


        });


        function genereateFileTree(){
            var setting = {
                async: {
                    enable: true,//异步处理
                    type : "get",

                    //contentType: "application/json",//提交参数方式，这里 JSON 格式，默认form格式

                    url: ctx +"/cms/template/treeFromDb.php"+'?random='+Math.random(),//异步获取json格式数据的路径

                    autoParam: ["id"]//异步加载时需要提交的参数，多个用逗号分隔


                },
                data: {
                    simpleData: {
                        enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式
                        idKey: "id",
                        pIdKey: "pid",
                        title: "name",
                        rootPId: 0
                    }
                },
                callback: {
                    onClick: function(e,treeId, treeNode) {
                        if(treeNode.isParent){
                            refreshList(treeNode.pid, treeNode.id, treeNode.siteId);
                        }else{
                            showFileContext(treeNode.pid, treeNode.id, treeNode.siteId);
                        }

                    },
                    onAsyncSuccess: zTreeOnAsyncSuccess
                }

            };

            var zNodes=[];
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            //var zTree = $.fn.zTree.getZTreeObj("treeDemo");//获取ztree对象
            //var nodes = zTree.getNodes();
            //alert(nodes.length);
            //var node = zTree.getNodeByParam('id', "/",null);//获取id为1的点
            //alert(node);
            //zTree.expandNode(node);
            //$.ajax({
            //    type:'GET',
            //    url:ctx +"/cms/template/treeFromDb.php"+'?random='+Math.random(),
            //    dataType:'json',
            //    success : function(data){
            //        //var zNodes =  [{ id:"/"+data.rootPath, pId:0, name:"模板根目录", open:true}];
            //        //$.each(data.tplList,function(key, val){
            //        //    var obj = new Object();
            //        //    obj.id = val.name;
            //        //    obj.pId = val.path;
            //        //    obj.name = val.filename;
            //        //    obj.open = true;
            //        //    obj.path = val.name;
            //        //    obj.isParent = val.directory;
            //        //    zNodes.push(obj);
            //        //});
            //        $.fn.zTree.init($("#treeDemo"), setting, data);
            //
            //        $('.fileModifyBtn').bind('click',function(){
            //            showFileContext($(this).parent().parent().find('input[type=checkbox]').val());
            //        });
            //
            //        $('.fileDeleteBtn').bind('click',function(){
            //            deleteFile($(this));
            //        });
            //        $('.dirDeleteBtn').bind('click',function(){
            //            deleteDir($(this));
            //        })
            //    }
            //});
        }


        /**
         * 刷新当前节点
         */
        function refreshNode() {
            /*根据 treeId 获取 zTree 对象*/
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                type = "refresh",
                silent = false,
            /*获取 zTree 当前被选中的节点数据集合*/
                nodes = zTree.getSelectedNodes();
            /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/
            zTree.reAsyncChildNodes(nodes[0], type, silent);
        }


        //加载tree成功后展开第一级
        function zTreeOnAsyncSuccess(event, treeId, msg) {

            var zTree = $.fn.zTree.getZTreeObj("treeDemo");//获取ztree对象
            var nodes = zTree.getNodes();
            try {
                //如果第一个节点未展开，调用默认展开第一个结点
                var selectedNode = zTree.getSelectedNodes();
                var nodes = zTree.getNodes();
                if (!nodes[0].open) {
                    zTree.expandNode(nodes[0], true);
                    refreshList(nodes[0].pid, nodes[0].id, nodes[0].siteId);
                }

            } catch (err) {

            }


        }

        function refreshList(pid, treeNodePath, siteId){
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/update_list_formDb.php"+'?random='+Math.random(),
                dataType:'html',
                data : {
                    path : treeNodePath,
                    siteId: siteId},
                success : function(data){
                    $('#tableForm').html(data);

                    $('.fileModifyBtn').bind('click',function(){
                        showFileContext(pid, $(this).parent().parent().find('input[name="templatePath_list"]').val(),siteId);
                    });

                    $('.fileAttriModifyBtn').bind('click',function(){
                        showFileAttri(pid, $(this),siteId);
                    });

                    $('.fileDeleteBtn').bind('click',function(){
                        var fileDeleteBtn = $(this);
                        layer.confirm('是否删除该模板文件？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                            function(index){
                                deleteFile(pid,fileDeleteBtn,siteId);
                            }
                        );
                    });
                    $('.dirDeleteBtn').bind('click',function(){
                        var dirDeleteBtn = $(this);
                        layer.confirm('是否删除该模板文件夹？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                            function(index){
                                deleteDir(pid, dirDeleteBtn, siteId);
                            }
                        );
                    });

                    $('.start_stop_Btn').bind('click',function(){
                        start_stop_tpl(pid, $(this), siteId);
                    })

                    //新上传模板文件链接
                    $("a[name='addTplFile']").click(function(){
                        showUploadTplFileDiv(pid,siteId);
                    });

                    //新建文件夹链接
                    $("a[name='addTplDir']").click(function(){
                        showAddTplDirDiv(pid, siteId);
                    });
                    if(pid!='/' && pid!='0'){
                        $("a[name='addTplFile']").show()
                        $("a[name='addTplDir']").show();
                    }else{
                        $("a[name='addTplFile']").hide()
                        $("a[name='addTplDir']").hide();
                    }


                }
            });


        }



        function showFileContext(pid, filePathName,siteId){
            //var filePathName = btn.parent().parent().find('input[type=checkbox]').val();
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/edit.php"+'?random='+Math.random(),
                dataType:'html',
                data : {"filePathName" : filePathName},
                success : function(data){
                    //$('#tableForm').html('文件名称：<input type="text" id="tplPathName_mod" value=\"'+ filePathName +'\" readonly=\"readonly\"/><br>'+
                    //    '<textarea id="editor" style="width:500px;height:300px;">'+ data.toString() +'</textarea>'
                    //    +'<input type="button" id="updateTplBtn" value="保存模板"">'
                    //    +'<input type="button" id="recordVersionTplBtn" value="保存并记录版本">'
                    //);
                    $('#tableForm').html(data);

                    $('#updateTplBtn').bind('click',function(){
                        $('#updateTplBtn').blur();
                        layer.confirm('确认保存对模板文件所做修改？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                            function(index){
                                updateTpl();
                            }
                        );
                    });

                    $('#recordVersionTplBtn').bind('click',function(){
                        $('#recordVersionTplBtn').blur();
                        layer.confirm('确认保存并备份模板文件？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                            function(index){
                                recordVersion();
                            }
                        );
                    });

                    $('#selectTagBtn').bind('click',function(){
                        getTags();
                    });

                    //返回文件列表页面
                    $('#backToFileListBtn').bind('click',function(){
                        refreshList(pid, filePathName.substring(0,filePathName.lastIndexOf('/')),siteId);
                    });
                }
            });
        }

        //更新文件属性页面
        function showFileAttri(pid, btn, siteId){
            var templateId = btn.parent().parent().find('input[name="tpl_id"]').val();
            var templatePath = btn.parent().parent().find('input[name="templatePath_list"]').val();
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/toEditTpl.php"+'?random='+Math.random(),
                dataType:'html',
                data : {"templateId" : templateId
                },
                success : function(data){


                    $('.nycon_list_b').html(data);

                    $('#tplFile_edit_btn').bind('click',function(){
                        updateTplAttri(pid, siteId);
                    });

                    //返回文件列表页面
                    $('#backToFileListBtn').bind('click',function(){
                        refreshList(pid, $('#parentPath_list').val(), siteId);
                    });
                }
            });
        }

        function deleteFile(pid, btn, siteId){
            var filePathName = btn.parent().parent().find('input[name="templatePath_list"]').val();
            var templateId = btn.parent().find("input[name='templateId']").val()
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/del.php"+'?random='+Math.random(),
                dataType:'json',
                data : {
                    "filePathName" : filePathName,
                    "templateId": templateId
                },
                success : function(data){
                    if(data.code!=2000){
                        layer.msg(data.message, {icon: 2});
                    }else{
                        layer.msg('删除模板文件成功', {icon: 1});
                        refreshList(pid, filePathName.substring(0,filePathName.lastIndexOf('/')),siteId);
                        refreshNode();
                    }
                }
            });
        }

        function deleteDir(pid, btn, siteId){
            var dirPathName = btn.parent().parent().find('input[name="templatePath_list"]').val();
            var templateId = btn.parent().find("input[name='templateId']").val();
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/delDir.php"+'?random='+Math.random(),
                dataType:'json',
                data : {
                    "dirPathName" : dirPathName,
                    "templateId": templateId
                },
                success : function(data){
                    if(data.code!=2000){
                        layer.msg(data.message, {icon: 2});
                    }else{
                        layer.msg('删除模板文件夹成功', {icon: 1});
                        refreshList(pid, dirPathName.substring(0,dirPathName.lastIndexOf('/')), siteId);
                        //genereateFileTree();
                        refreshNode();
                    }
                },
                error:function(){
                    layer.msg("操作失败", {icon: 2});
                }
            });
        }


        function updateTpl(){
            var tplContent = $('#editor').val();
            layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajax({
                type:'POST',
                url: ctx +"/cms/template/ajaxUpdate.php",
                dataType:'json',
                data : {"filePathName" : $('#tplPathName_mod').val(),
                    "tplContent":tplContent},
                success : function(data){
                    if(data.code!=2000){
                        layer.msg(data.message, {icon: 2,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                        //layer.msg(data.message, {icon: 2});
                    }else{
                        layer.msg(data.message, {icon: 1,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                    }
                },
                error: function(){
                    layer.msg("操作失败", {icon: 2,closeBtn:0,title:""},function () {
                        layer.closeAll();
                    });
                }
            });
        }

        function recordVersion(){
            var tplContent = $('#editor').val();
            layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajax({
                type:'POST',
                url: ctx +"/cms/template/ajaxUpdate_recordVersion.php",
                dataType:'json',
                data : {"filePathName" : $('#tplPathName_mod').val(),
                    "tplContent":tplContent},
                success : function(data){
                    if(data.code!=2000){
                        layer.msg(data.message, {icon: 2,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                    }else{
                        layer.msg(data.message, {icon: 1,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                    }
                },
                error: function(){
                    layer.msg("操作失败", {icon: 2,closeBtn:0,title:""},function () {
                        layer.closeAll();
                    });
                }
            });
        }

        function getTags(){
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['800px', '600px'], //宽高
                content: "<div class='depart_l' style='margin-top:-10px; width:30%;'><div id='tagTree' class='ztree'></div></div>" +
                "<div class='depart_r' style='width:66%;'><textarea rows='30' style='width:100%' id='tagContent' readonly='readonly'></textarea></div>" +
                "<div style='margin:5px; text-align: right;'><input type='button' id='useTagBtn'  class='btn btn-success' value='确定'></div>",
                success:function(layero, index){

                    var setting = {

                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onClick: function(e,treeId, treeNode) {
                                //alert(treeNode.path);
                                //获取标签文件内容
                                getTagsContent(treeNode.path);
                            }
                        }

                    };

                    $.ajax({
                        type:'GET',
                        url: ctx +"/cms/template/getTags.php",
                        dataType:'json',
                        success : function(data){
                            var zNodes =  [{ id:"/"+data.rootPath, pId:0, name:"标签根目录", open:true}];
                            $.each(data.tagList,function(key, val){
                                var obj = new Object();
                                obj.id = val.name;
                                obj.pId = val.path;
                                obj.name = val.filename;
                                obj.open = true;
                                obj.path = val.path;
                                obj.isParent = val.directory;
                                zNodes.push(obj);
                            });
                            $.fn.zTree.init($("#tagTree"), setting, zNodes);
                        }
                    });

                    $('#useTagBtn').bind('click',function(){
                        useTag(index);
                    });
                }
            });
        }

        //获取指定tag文件的内容
        function getTagsContent(tagsAbsoluteFilePath){
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/getFileContent.php?random="+Math.random(),
                dataType:'json',
                data:{
                    "filePathName" : tagsAbsoluteFilePath
                },
                success : function(data){
                    //alert(data.fullText);
                    $('#tagContent').text(data.fullText);
                }
            });
        }

        //插入指定tag
        function useTag(index){
            var str = $('#tagContent').text();

            layer.close(index);

            var obj = $("#editor").get(0);
            if (document.selection) {
                obj.focus();
                var sel = document.selection.createRange();
                sel.text = str;
            } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
                var startPos = obj.selectionStart;
                var endPos = obj.selectionEnd;
                var tmpStr = obj.value;
                obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
            } else {
                obj.value += str;
            }


        }

        //显示上传模板文件页面
        function showUploadTplFileDiv(pid, siteId){
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/toAddTplFile.php?random="+Math.random(),
                dataType:'html',
                data:{
                    "siteId":siteId
                },

                success : function(data){
                    //alert(data.fullText);
                    $('.nycon_list_b').html(data);
                    $('.nycon_list_b').find("input[name='parentPath']").val($('#parentPath_list').val());
                    $("input[name='tplFile_submit_btn']").bind('click',function(){
                        uploadTplFile(pid, siteId);
                    });
                    //返回文件列表页面
                    $('#backToFileListBtn').bind('click',function(){
                        refreshList(pid, $('#parentPath_list').val(),siteId);
                    });
                }
            });

        }



        //上传模板文件
        function uploadTplFile(pid, siteId){
            var $validatorFrom1 = $('.nycon_list_b').find("form[name='addFileForm']").validator({
                timely: 1,
                //focusCleanup:true,
                theme: 'simple_right',
                fields: {
                    "tplFile":"required;betplfile[ftl,html]",
                    "templateName":"required;chinese",
                    "templateProperty":"required",
                    //"siteId":"required;",
                    "priority":"integer"
                }
            });
            $validatorFrom1.validator().trigger("showtip");


            if($validatorFrom1.isValid()) {
                layer.load(1,{shade: [0.6,"#393D49"]});
                $.ajaxFileUpload({
                    url: ctx + '/cms/template/addTplFile.php', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'tplFile', //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    //data : {
                    //    'parentPath' : $("input[name='parentPath']").val(),
                    //    'tplBo' : JSON.parse($("form[name='addFileForm']").serializeJson())
                    //},
                    data: JSON.parse($("form[name='addFileForm']").serializeJson()),
                    success: function (result) {  //服务器成功响应处理函数+
                        layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                            layer.closeAll();
                            refreshList(pid, $("input[name='parentPath']").val(),siteId);
                            refreshNode();
                        });
                    },
                    error:function(){
                        layer.msg("操作失败", {icon: 2,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                    }
                });
            }
        }


        //显示新增文件夹页面
        function showAddTplDirDiv(pid, siteId){
            $.ajax({
                type:'GET',
                url: ctx +"/cms/template/toAddTplDir.php?random="+Math.random(),
                dataType:'html',
                success : function(data){
                    $('.nycon_list_b').html(data);
                    $('.nycon_list_b').find("input[name='parentPath']").val($('#parentPath_list').val());
                    $('.nycon_list_b').find("input[name='parentSiteId']").val($('#parentSiteId_list').val());
                    $("input[name='dir_submit_btn']").bind('click',function(){
                        addTplDir(pid, siteId);
                    });
                    //返回文件列表页面
                    $('#backToFileListBtn').bind('click',function(){
                        refreshList(pid, $('#parentPath_list').val(),siteId);
                    });
                }
            });

        }

        //新增文件夹
        function addTplDir(pid, siteId){
            var $validatorFrom =  $('.nycon_list_b').find("form[name='addDirForm']").validator({
                timely: 1,
                theme: 'simple_right',
                fields: {
                    tplDirName:"required;szzm",
                    //siteId:"required",
                    tplDirChineseName:"required;chinese"
                }
            });
            $validatorFrom.validator().trigger("validate");
            if($validatorFrom.isValid()){
                layer.load(1,{shade: [0.6,"#393D49"]});
                $.ajax({
                    type:'POST',
                    url: ctx +"/cms/template/addTplDir.php",
                    data : {
                        'tplDirName': $("input[name='tplDirName']").val(),
                        'parentPath' :  $("input[name='parentPath']").val(),
                        'tplDirChineseName' : $("input[name='tplDirChineseName']").val(),
                        'siteId' : $("input[name='parentSiteId']").val()
                    },
                    dataType:'json',
                    success : function(data){
                        layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                            layer.closeAll();
                            refreshList(pid, $("input[name='parentPath']").val(), siteId);
                            refreshNode();
                        });
                    },
                    error : function(){
                        layer.msg("操作失败", {icon: 2,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                    }
                });
            }
        }

        //更新模板属性
        function updateTplAttri(pid, siteId){

            var $validatorFrom = $('.nycon_list_b').find("form[name='eidtFileAttriForm']").validator({
                timely: 1,
                theme: 'simple_right',
                fields: {
                    "tplFile":"required;",
                    "templateName":"required;chinese",
                    "templateProperty":"required",
                    //"siteId":"required;",
                    "priority":"integer",
                    //"templateType":"required"
                }
            });
            $validatorFrom.validator().trigger("showtip");

            if($validatorFrom.isValid()) {
                $('.nycon_list_b').find("input[name='parentPath']").val($('#parentPath_list').val());
                layer.load(1,{shade: [0.6,"#393D49"]});
                $.ajax({
                    type: 'POST',
                    url: ctx + "/cms/template/uploadTplAttri.php",
                    data: JSON.parse($("form[name='eidtFileAttriForm']").serializeJson()),
                    dataType: 'json',
                    success: function (data) {
                        layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                            layer.closeAll();
                            refreshList(pid, $("input[name='parentPath']").val(), siteId);
                            refreshNode();
                        });

                    },
                    error:function(){
                        layer.msg("操作失败", {icon: 2,closeBtn:0,title:""},function () {
                            layer.closeAll();
                        });
                    }
                });
            }
        }

        //启停模板
        //更新模板属性
        function start_stop_tpl(pid, btn, siteId){
            //$('.nycon_list_b').find("input[name='parentPath']").val($('#parentPath_list').html());
            var templateId = btn.parent().find("input[name='templateId']").val();
            layer.load(1,{shade: [0.6,"#393D49"]});
            $.ajax({
                type:'POST',
                url: ctx +"/cms/template/start_stop_Tpl.php",
                data:{
                    "templateId" : templateId
                },

                dataType:'json',
                success : function(data){
                    layer.msg("操作成功", {icon: 1,closeBtn:0,title:""},function () {
                        layer.closeAll();
                        refreshList(pid, $('#parentPath_list').val(), siteId);
                        refreshNode();
                    });

                },
                error:function(){
                    layer.msg("操作失败", {icon: 2,closeBtn:0,title:""},function () {
                        layer.closeAll();
                    });
                }
            });
        }

    });
});
