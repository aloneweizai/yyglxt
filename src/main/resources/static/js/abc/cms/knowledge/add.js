window.UEDITOR_HOME_URL = ctx+"/js/plugin/ueditor/";
require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/date","abc.common","ueditor", "layui","ztree","abc.ajaxfileupload","input",
        "../plugin/bootstrap-select","../abc/cms/knowledge/tag"], function ($,  abc_date,Editor,ueditor,zeroClipboard) {
        var zTreeObj = null;
        var editor = null;
        // //初始化富文本框
        // editor = new Editor("#_standard_answer_area");
        // editor.customConfig.uploadImgTimeout = 10000;
        // editor.customConfig.uploadImgServer = ctx+'/util/wangEditorUpload/knowledge.php';
        // editor.customConfig.menus =['head', 'bold', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'image', 'table', 'video', 'code', 'undo', 'redo'];
        // editor.create();
        //window.ZeroClipboard = zeroClipboard;
        var ue=null;
        $(function (){
            window.ZeroClipboard=zeroClipboard;

            ue=UE.getEditor("_standard_answer_area_baidu",{
                initialFrameWidth: 800,
                initialFrameHeight: 400,
                autoHeightEnabled: false
            });

            //var sitePath = $("#siteId option:selected").data("sitepath");

            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;

            /*提醒：关于图片的配置，在ueditor.all.js中设置了可选的图片类型，默认为 accept="image/*", 已改为 accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp"*/
            UE.Editor.prototype.getActionUrl = function(action) {
                if(action == 'config'){
                    return ctx+'/util/initSoaUeditorFileUpload.php';
                }
                else if(action == "uploadimage"){
                    return ctx+'/util/ueditorSoaFileUpload.php?subsystem=csw&fileTypeTag=image';
                }else {
                    return this._bkGetActionUrl.call(this, action);
                }
            };
        });



        function htmlDecode( html ) {
            var a = document.createElement( 'a' ); a.innerHTML = html;
            return a.textContent;
        };
        $("body").on("click",".js_page_location", function(){
            if($(this).attr("data-ajax") == "true"){
                $.ajax({
                    type: 'GET',
                    url: $(this).attr("data-href"),
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data) {
                            if(!data.bodyHtml){
                                data.bodyHtml = '';
                            }
                            $(".js-body-tr").html(htmlDecode(data.bodyHtml));
                            $(".js-page-tr").html(htmlDecode(data.pageHtml));
                        } else {}
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
                        type: 'GET',
                        url: url.replace("[:page]", page),
                        data: '',
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (data) {
                                if(!data.bodyHtml){
                                    data.bodyHtml = '';
                                }
                                $(".js-body-tr").html(htmlDecode(data.bodyHtml));
                                $(".js-page-tr").html(htmlDecode(data.pageHtml));
                            } else {}
                        }
                    });
                } else {
                    return;
                }
            }else{
                location.href = $(this).attr("data-href");
            }
        });
        //点击添加关联问题
        $("#add_standard_answer").on('click',function(){
            var value=$(this).val();
            $.ajax({
                type: "GET",
                url: ctx + "/cms/know/toaddanswer",
                success: function(result){
                    var layerIndex = layer.open({
                        title:value,
                        type: 1,
                        area: ['800px', '500px'],
                        fixed: false, //不固定
                        maxmin: false,
                        content: result,
                        success:function(){







                        }
                    });
                },
                error: function(){
                    layer.msg('获取标签信息失败', {icon: 5});
                }
            });
        });

        $(document).on('click', '.js_select_relKnow_btn', function(e){
            var id = $(this).attr("data-id");
            var subject = $(this).find("td:first").text();
            if(id == $('[name="id"]').val()){
                layer.msg('不能关联自己', {icon: 5});
                return;
            }
            var flag = true;
            $("input[name='refKnowledgeIds']").each(function(){
                if(id == $(this).val()){
                    flag = false;
                }
            });
            if(!flag){
                layer.msg('已添加', {icon: 5,time:1000});
                return;
            }
            $("#refKnowledgeIdsDIV").append("<input type='hidden' name='refKnowledgeIds' value='"+id+"' val-subject='"+subject+"'/>");
            $("#refKnowledgeIds_p").html($("input[name='refKnowledgeIds']").length);
            layer.msg('添加成功', {icon: 1,time:1000});
        });


        //点击取消按钮事件
        $(document).on('click',"#conCancelBtn", function(){
            layer.close(layerIndex);
        })

        //点击选择知识分类 弹出选择模态框
        $(document).on('click',"#conSelectKnowCateInp", function(){
            showTree("cateTree", conZTreeOnClick);
        });

        //添加关联问题框 点击搜索按钮
        $(document).on('click',"#conSearchBtn", function(){
            var seleKnowIds = "";
            $("#refKnowledgeIdsDIV input").each(function(){
                seleKnowIds +=$(this).val()+",";
            });
            $.ajax({
                url: ctx + "/cms/know/conSearchAjaxPage",
                type: "GET",
                data: {
                    "categoryCode":$("#conCateId").val(),
                    "keywords":$("#keywords").val(),
                    "seleKnowIds":seleKnowIds,
                    "status":true,
                    "isOpen":true
                },
                success: function (result) {
                    if(!result.bodyHtml){
                        result.bodyHtml = '';
                    }
                    $(".js-body-tr").empty().append(htmlDecode(result.bodyHtml));
                    $(".js-page-tr").empty().append(htmlDecode(result.pageHtml));
                },
                error: function (err) {
                    layer.msg("查询失败", {icon: 5},function () {
                        layer.closeAll();
                    });
                }
            });
        });

        //点击关联问题的数字可以删除关联问题
        $("#hasKnow_p").on('click', function(){
            if($("#refKnowledgeIdsDIV input").length == 0){
                layer.msg('请先添加关联问题', {icon: 5});
                return;
            }
            var html = "<div style='padding: 15px;'><table class='layui-table' lay-size='sm'><thead><th style='text-align: center;'>标准问法</th><th style='text-align: center;'>操作</th></thead>";
            $("#refKnowledgeIdsDIV input").each(function(){
                html += "<tr>";
                html += "<td>"+$(this).attr("val-subject")+"</td>";
                html += "<td style='text-align: center;'><a class=\"js_delete_relKnow\" data-id='"+$(this).val()+"'  href=\"javascript:void(0)\">删除</a></td>";
                    //"<button class='btn btn-list-del btn-bj-red' name='con_del_btn' value='"+$(this).val()+"'>删除</button>" +
                html += "</tr>";
            });
            //html +="<tr><td colspan='2' style='text-align: center;'><button class='btn btn-close' id='con_del__btn_close'>关闭</button></td></tr>";
            html +="</table></div>";

            var layerIndex = layer.open({
                type: 1,
                area: '700px',
                title: '已关联问题',
                closeBtn: 1,
                shadeClose: true,
                content: html,
                success:function(){
                    //点击删除关联知识
                    $(".js_delete_relKnow").on('click', function(){
                        var val = $(this).attr("data-id");
                        $("input[name='refKnowledgeIds']").each(function(){
                            if(val == $(this).val()){
                               $(this).remove();
                            }
                        });
                        $(this).parent().parent().remove();
                        $("#refKnowledgeIds_p").html($("#refKnowledgeIdsDIV input[name='refKnowledgeIds']").length);
                    });

                    $("#con_del__btn_close").on('click', function(){
                        layer.close(layerIndex);
                    });
                }
            });
        });

        //点击选择知识分类 弹出选择模态框
        $("#selectKnowCateInp").on('click', function(){
            showTree("cateTree", zTreeOnClick);
        });

        //添加按钮
        $("#subBtn").on('click', function(){
            if(!checkSubForm()){
                return false;
            }
            var data = {};
            data.knowledgeBase = {};
            data.knowledgeBase.categoryCode = $("#cateId").val();
            data.knowledgeBase.type = $("input[name='type']:checked").val();
            if(data.knowledgeBase.type == 'QA'){
                data.knowledgeBase.recommend = $("input[name='recommend']:checked", "#QA_recommend").val();
            }else{
                data.knowledgeBase.recommend = $("input[name='recommend']:checked", "#K_recommend").val();
            }
            data.knowledgeBase.subject = $("input[name='subject']").val().replace(/\s/ig,'');
            // data.knowledgeBase.content = editor.txt.html();
            data.knowledgeBase.content = ue.getContent();

            if(ue.getContent()==null || ue.getContent()==""){
                layer.msg("内容不能为空", {offset: abc.winscrollTop(300),icon: 5}, function(){
                    layer.closeAll();
                });
                return;
            }

            data.knowledgeBase.status = $("input[name='status']:checked").val();
            //if(data.knowledgeBase.status =='true'){
            var val = $("input[name='activeTime_temp']:checked").attr("val");
            if(val == "0"){
                var value=$("input[name='activeTime_date']").val();
                var timestamp2 = Date.parse(new Date(value));
                if(timestamp2 < new Date()){
                    abc.layerAlert(false, '操作失败: 生效时间需要大于当前时间');
                    return false;
                }
                data.knowledgeBase.activeTime = abc_date.getDate(value);
            }
            //}
            data.knowledgeBase.isOpen = $("input[name='isOpen']:checked").val();
            data.knowledgeBase.source = $("input[name='source']").val();
            data.knowledgeBase.id = $("input[name='id']").val();

            var attachmentList = [];
            $("tr[name='attachementInput']").each(function(i,item){
                var fileName = $(this).find("[name='fileName']").val();
                var filePath = $(this).find("[name='filePath']").val();
                attachmentList.push({fileName:fileName, filePath:filePath, sort:i});
            });
            data.knowledgeBase.attachmentList = attachmentList;


            data.tagIds = [];
            $("#labelDiv input[name='tagIds']").each(function(){
                data.tagIds.push($(this).val());
            });
            data.refKnowledgeId = [];
            $("#refKnowledgeIdsDIV input[name='refKnowledgeIds']").each(function(){
                data.refKnowledgeId.push($(this).val());
            });

            var _this = $(this);
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                function(){
                    _this.attr("disabled","true");
                    abc.block();
                    $.ajax({
                        url: ctx + "/cms/know/save",
                        type: "post",
                        data: JSON.stringify(data),
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (result) {
                            $.unblockUI();
                            if (result.code == '2000') {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function () {
                                    window.location.href = $(".js_currLink").val();
                                });
                            }else{
                                layer.alert(result.message||"操作失败", {icon: 5},function(){
                                    _this.removeAttr("disabled");
                                    layer.closeAll();
                                });
                            }
                        },
                        error: function (err) {
                            $.unblockUI();
                            layer.alert("系统异常", {icon: 5},function(){
                                _this.removeAttr("disabled");
                                layer.closeAll();
                            });
                        }
                    });
                }
            );
        });

        var proposals = [];

        $(function (){
            $.ajax({
                url: ctx + "/cms/know/cjly",
                type: "get",
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (result) {
                    var title=result.cjly.split(",");
                    proposals=title;
                },
                error: function (err) {
                }
            });
        })


        $("input[name='activeTime_temp']").on('click',function(){
            if($(this).attr("val") == "-1"){
                $("#activeTimeRadio_3").hide();
            }else{
                $("#activeTimeRadio_3").show();
            }
        });



        $(function (){
            var value=$('#search-form').attr("input-value");
            $('#search-form').autocomplete({
                hints: proposals,
                width: 300,
                height: 20,
                onSubmit: function(text){
                    $('#message').html('Selected: <b>' + text + '</b>');
                },
                value:value
            });
        })

        var x;
        var y;
        $(document).mousemove(function(e){
            x = e.pageX;
            y = e.pageY;
        });



        $('input[name="subject"]').blur(function (){
            //x的值相对于文档的左边缘。y的值相对于文档的上边缘
//x,y是全局变量;
//判断鼠标是否在某DIV中
            var div = $('#subject_bz');//获取你想要的DIV
            var y1 = div.offset().top;  //div上面两个的点的y值
            var y2 = y1 + div.height();//div下面两个点的y值
            var x1 = div.offset().left;  //div左边两个的点的x值
            var x2 = x1 + div.width();  //div右边两个点的x的值
            if( x < x1 || x > x2 || y < y1 || y > y2){
                $('#subject_bz').hide();
            }
        });

        var boolcom=false;
        $('input[name="subject"]').on('keyup',function (){
            if ($(this).prop('comStart')) return;
            var value=$(this).val();
            console.log(value)
            if(value!=''){
                var width=$(this).width();
                $.ajax({
                    url: ctx + "/cms/know/titlelist?subject="+value,
                    type: "get",
                    async: false,
                    dataType: "html",
                    success: function (result) {
                        $('#subject_bz').html(result);
                        $('#subject_bz').width(width);
                        $('#subject_bz').show();
                    },
                    error: function (err) {
                    }
                });
            }else{
                $('#subject_bz').hide();
            }
        }).on('compositionstart',function (){
            $(this).prop('comStart', true);
        }).on("compositionend",function (){
            $(this).prop('comStart', false);
        })

        // $('input[name="subject"]').keyup(function (){
        //     var keycode = event.keyCode;
        //     if(keycode!=8){
        //         var value=$(this).val();
        //         console.log(value)
        //         if(value!=''){
        //             var width=$(this).width();
        //             $.ajax({
        //                 url: ctx + "/cms/know/titlelist?subject="+value,
        //                 type: "get",
        //                 async: false,
        //                 dataType: "html",
        //                 success: function (result) {
        //                     $('#subject_bz').html(result);
        //                     $('#subject_bz').width(width);
        //                     $('#subject_bz').show();
        //                 },
        //                 error: function (err) {
        //                 }
        //             });
        //         }else{
        //             $('#subject_bz').hide();
        //         }
        //     }
        // });



        $('input[name="subject"]').click(function (){
            var value=$(this).val();
            console.log(value)
            if(value!=''){
                var width=$(this).width();
                $.ajax({
                    url: ctx + "/cms/know/titlelist?subject="+value,
                    type: "get",
                    async: false,
                    dataType: "html",
                    success: function (result) {
                        $('#subject_bz').html(result);
                        $('#subject_bz').width(width);
                        $('#subject_bz').show();
                    },
                    error: function (err) {
                    }
                });
            }else{
                $('#subject_bz').hide();
            }
        });



        //表单校验
        function checkSubForm(){
            if($("#cateId").val() ==''){
                abc.layerAlert(false, '操作失败: 请选择知识分类');
                return false;
            }
            var fileNameTag = true;
            $("table[name='attachementTable']").find("input[name='fileName']").each(function(){
                if($(this).val()==null || $(this).val()==""){
                    fileNameTag = false;
                }

            });
            if(fileNameTag==false){
                abc.layerAlert(false, '操作失败: 请填写文件名称');
                return;
            }

            var type = $("input[name='type']:checked").val();
            if(type == "QA"){
                if($("input[name='recommend']:checked", "#QA_recommend").length == 0){
                    abc.layerAlert(false, '操作失败: 请选择问答推荐');
                    return false;
                }
            }else{
                if($("input[name='recommend']:checked", "#K_recommend").length == 0){
                    abc.layerAlert(false, '操作失败: 请选择知识推荐');
                    return false;
                }
            }
            if($.trim($("input[name='subject']").val()) == ''){
                abc.layerAlert(false, '操作失败: 请输入标准问法');
                return false;
            }
            var texts=ue.getContent();
            if($.trim(texts.replace(/&nbsp;/g,""))==''){
                abc.layerAlert(false, '操作失败: 请输入标准答案');
                return false;
            }
            if($('input[name="tagIds"]').length<=0){
                abc.layerAlert(false, '操作失败: 请选择相关标签');
                return false;
            }
            if($("input[name='source']").val()==''){
                abc.layerAlert(false, '操作失败: 请输入采集来源');
                return false;
            }

            var status = $("input[name='status']:checked").val();
            if(status == "true"){
                if($("input[name='activeTime_temp']:checked").length == 0){
                    abc.layerAlert(false, '操作失败: 请选择生效时间');
                    return false;
                }else{
                    var val = $("input[name='activeTime_temp']:checked").attr("val");
                    if(val != "-1"){
                        var activeTimeStr = $("input[name='activeTime_date']").val();
                        if(!activeTimeStr){
                            abc.layerAlert(false, '操作失败: 请选择生效截止时间');
                            return false;
                        }else{
                            var timestamp2 = Date.parse(new Date(activeTimeStr));
                            if(timestamp2 < new Date()){
                                abc.layerAlert(false, '操作失败: 生效时间需要大于当前时间');
                                return false;
                            }
                            $("input[name='activeTime']").val(activeTimeStr);
                        }
                    }else{
                        $("input[name='activeTime']").val("");
                    }
                }
            }else{
                $("input[name='activeTime']").val("");
            }
            var flag = true;
            $("[name='attachementInput']").each(function(e, i){
                if($.trim($(this).find("[name='fileName']").val()) == ''){
                    flag = false;
                    abc.layerAlert(false, '操作失败: 请输入附件名称');
                    return;
                }
                if($.trim($(this).find("[name='filePath']").val()) == ''){
                    flag = false;
                    abc.layerAlert(false, '操作失败: 请上传附件');
                    return;
                }
            });
            if(!flag){
                return false;
            }
            return true;
        }
        function escape2Html(str) {
            if(str){
                var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
                return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
            }else{
                return str;
            }
        }
//初始化知识分类
        function initCateTree(bindEle, clickBack){
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                callback:{
                    onClick:clickBack
                },
                data: {
                    simpleData: {enable: true}
                }
            };
            $.ajax({
                type:'GET',
                url:ctx +"/cms/knowcate/ajaxList",
                dataType:'json',
                success : function(data){
                    var zNodes =  [{ id:0, pId:"-1", name:"全部分类", open:true}];
                    data = data.dataList;
                    $.each(data,function(key, val){
                        var obj = new Object();
                        obj.idx=val.id;
                        obj.id = val.code;
                        obj.pId = val.parentCode;
                        obj.name = escape2Html(val.name);
                        obj.open = true;
                        if(val.parentCode==0){
                            obj.isParent = 1;
                            obj.open = false;
                        }
                        zNodes.push(obj);
                    });
                    zTreeObj = $.fn.zTree.init($("#"+bindEle), setting, zNodes);
                }
            });
        }

//弹出分类框点击事件
        function zTreeOnClick(event, treeId, treeNode) {
            if(treeNode.pId == null){
                return;
            }
            $("#selectKnowCateInp").val(treeNode.name);
            $("#cateId").val(treeNode.id);
        };

//添加关联问题弹出分类框点击事件
        function conZTreeOnClick(event, treeId, treeNode) {
            if(treeNode.pId == null){
                $("#conSelectKnowCateInp").val('');
                $("#conCateId").val('');
                return;
            }
            $("#conSelectKnowCateInp").val(treeNode.name);
            $("#conCateId").val(treeNode.id);
        };

//弹出的标签框 点击标签事件
        function selectLabelClick(e){
            var flag = true;
            var val = $(this).val();
            $("#labelDiv input").each(function(e, i){
                if($(this).val() == val){
                    flag = false;
                    return;
                }
            });
            if(flag){
                $("#labelDiv").append("<span name='labelSpan' onclick='$(this).remove();'><input name='tagIds' type='hidden' value='"+$(this).val()+"'/><button type='button' class='btn btn-default'>"+$(this).text()+"<i class='glyphicon glyphicon-remove'></i></button></span>");
            }
        }

        var layerIndex;

//弹出知识分类选择框  如果选择较多可以直接传配置参数
//bindEle 树绑定标签
//节点点击会掉方法
        function showTree(bindEle, callback){
            var html = '<div style="max-height:370px;overflow: auto; padding: 10px;"><div name="column_tree_layer" class="nycon_list_b">' +
                            '<div><ul id="cateTree" class="ztree" style="margin-top: 0px; height: 100%;"></ul></div>' +
                            //'<input value="确认" id="okSub" class="js_close btn btn-info">' +
                        '</div></div>';
            html+="<div style='position:fixed;bottom:0; width: 300px; height: 30px; text-align: center; cursor: pointer; background-color: #00bcd4; color:#fff; font-size: 16px;font-weight:bold; padding-top: 7px;' id='fenleiok'>确&nbsp;认</div>";
            $('body').css({
                width:"100%",
                position:"fixed"
            })
            layerIndex = layer.open({
                title:"选择分类",
                type: 1,
                area: ['300px', '350px'],
                fixed: false, //不固定
                maxmin: false,
                content: html,
                success:function(){
                    initCateTree(bindEle, callback);
                    $("#okSub").on("click", function(){
                        var nodes = zTreeObj.getSelectedNodes();
                        if(nodes.length == 1){
                            callback(null,null, nodes[0]);
                            $('body').removeAttr("style");
                            layer.close(layerIndex);

                        }else{
                            //alert("请选择一个分类");
                        }
                    });
                },
                cancel: function(index, layero){
                    $('body').removeAttr("style");
                    layer.close(index);
                }
            });
        }


        $(document).on("click",'#fenleiok',function (){
            $('body').removeAttr("style");
            layer.close(layerIndex);
        })

        $('#KCheckbox').click(function (){
            $('#K_recommend_tr').show();
            $('#QA_recommend_tr').hide();

            $('input[name="recommend"]').each(function (){
                $(this).removeAttr("checked")
            })
            $("input[type='radio'][name='recommend'][value='top']").prop("checked",true);

            $('#bzwf').html("知识标题");
            $('#bzda').html("知识内容");
            $('#add_standard_answer').val("添加关联知识");
            $('#hastext').html("已关联知识");
        });
        $('#QACheckbox').click(function (){
            $('#QA_recommend_tr').show();
            $('#K_recommend_tr').hide();
            $('input[name="recommend"]').each(function (){
                $(this).removeAttr("checked")
            })
            $("input[type='radio'][name='recommend'][value='common']").prop("checked",true);
            $('#bzwf').html("标准问法");
            $('#bzda').html("标准答案");
            $('#add_standard_answer').val("添加关联问题");
            $('#hastext').html("已关联问题");
        });




        /*添加附件*/
        //添加附件输入行
        $("[name='addAttachementInput']").on("click",function(){
            $("[name='attachementTable']").append("<tr name='attachementInput'>"+$("#attachementInputTpl").html()+"</tr>");
            $("[name='attachementTable']").find("tr").each(function(i,item){
                $(this).find("[name='uploadFile']").attr("id", "uploadFile"+i);
            });
        });
        //删除附件输入行
        $('body').on('click', '[name="_attachement_delete_btn"]', function(){
            $(this).parent().parent().remove();
        });
        //上传附件
        $('body').on('click', '[name="_attachement_upload_btn"]', function(){
            var obj = $(this).parent().parent();
            var file = obj.find("[name='uploadFile']");
            var fileId = file.attr("id");
            if(!file.val()){
                abc.layerAlert(false,'请选择上传文件');
                return;
            }
            if(file[0].files[0].size>3145728){
                file.val('');
                abc.layerAlert(false,'文件大小不能超过3M');
                return;
            }
            var types= file.attr('data-type').split(';');
            var type=file.val().substring(file.val().lastIndexOf(".")+1);
            if(types.indexOf(type)<0){
                abc.layerAlert(false,'允许类型:['+types+"]");
                return;
            }
            var uploadMsg = obj.find(".uploadMsg");
            obj.find("[name='_attachement_upload_btn']").hide();
            obj.find("[name='_attachement_delete_btn']").hide();
            uploadMsg.html('正在上传.....');
            $.ajaxFileUpload({
                url : ctx+'/util/doFileupload.php?path=knowledge',
                type : 'post',
                secureuri : false, // 一般设置为false
                fileElementId : fileId, // 上传文件的id、name属性名
                dataType : 'application/json', // 返回值类型，一般设置为json、application/json
                success : function(data, status) {
                    uploadMsg.html('');
                    obj.find("[name='_attachement_upload_btn']").show();
                    obj.find("[name='_attachement_delete_btn']").show();
                    if(data.code=='200'){
                        obj.find("[name='filePath']").val(data.message);
                        //obj.find("[name='fileName']").val(data.message);
                        obj.find("a").text(data.message).attr("href",imgUrl+data.message);;
                    }else{
                        abc.layerAlert(false, data.message);
                    }
                },
                error : function(data, status, e) {
                    uploadMsg.html('');
                    abc.layerAlert(false, '上传异常');
                }
            });
        });


       // ue = UE.getEditor("_standard_answer_area_baidu");




    });
});



