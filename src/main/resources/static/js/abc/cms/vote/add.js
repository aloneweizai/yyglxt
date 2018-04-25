window.UEDITOR_HOME_URL = ctx+"/js/plugin/ueditor/";
require(["../../../config"], function () {
    require(["jquery.full", "wangEditor","template","ueditor","zeroClipboard"], function ($, Editor,template,ueditor,zeroClipboard) {

        var voteIntroduceEdtior;
        var voteIntroduceEdtiorConfig = {
            initialFrameHeight:400,serverUrl: ctx + '/cms/vote/fileUpload.php',maximumWords:800,
            //工具栏上的所有的功能按钮和下拉框，可以在new编辑器的实例时选择自己需要的重新定义
            toolbars: [[
                'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                /*'directionalityltr', 'directionalityrtl',*/ 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', /*'touppercase', 'tolowercase', '|',*/
                'link', 'unlink', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload', /*'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template',*/ 'background', '|',
                'horizontal', /* 'date', 'time', 'spechars', 'snapscreen', 'wordimage',*/ '|',
                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                /*'print',*/ 'preview', 'searchreplace'/*, 'drafts', 'help'*/
            ]]
        };

        $(".toupiao-title").on("click",function () {
            var target = $(this).find(".glyphicon");
            target.parent().next().toggle();
            var classjt = target.attr('class');
            if (classjt == "glyphicon glyphicon-menu-up") {
                target.attr("class", "glyphicon glyphicon-menu-down")
            } else {
                target.attr("class", "glyphicon glyphicon-menu-up")
            }
        });

        window.ZeroClipboard = zeroClipboard;
        var startIntroEditor = UE.getEditor('_vote_subject_intro',{initialFrameHeight:600,serverUrl: ctx + '/cms/vote/fileUpload.php',maximumWords:4000});

        // var startIntroEditor = new Editor($("#_vote_subject_intro").get(0));
        // startIntroEditor.customConfig.uploadImgServer = ctx+'/cms/vote/fileUpload.php';
        // startIntroEditor.customConfig.customAlert = function (info) {
        //     layer.alert(info, {icon: 5});
        // };
        // startIntroEditor.create();

        var endIntroEditor = UE.getEditor('_voted_words',{initialFrameHeight:600,serverUrl: ctx + '/cms/vote/fileUpload.php',maximumWords:4000});
        // var endIntroEditor = new Editor($("#_voted_words").get(0));
        // endIntroEditor.customConfig.uploadImgServer = ctx + '/cms/vote/fileUpload.php';
        // endIntroEditor.customConfig.customAlert = function (info) {
        //     layer.alert(info, {icon: 5});
        // };
        // endIntroEditor.create();

        var resortItemOrderNormal = function(){
            var num = 1;
            $("#vote_item_normal_group .xuanxiang").each(function(){
                $(this).find("span[name='order']").text((num)+".");
                $(this).find("input[vote-field='vote_item_name']").attr("name","vote_item_name_"+num);
                $(this).find("input[name='vote_subject_item_sort']").val(num++);
            });
        }

        ~function(){

            var setProxy = function(name, date){
                $("#vote_base_info input[name='"+name+"-vote-proxy']").val(date);
            }

            $("#container").find("[abc-type='datetimebox']").each(function(){
                var targetNode = $(this);
                var now = new Date();
                targetNode.datetimebox({
                    showSeconds: false,
                    spinnerWidth: 172,
                    editable: false,
                    formatter: function (date) {
                        var y = date.getFullYear();
                        var m = date.getMonth() + 1;
                        var d = date.getDate();
                        var h = date.getHours();
                        var min = date.getMinutes();
                        var sec = date.getSeconds();
                        return  y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec);
                    },
                    parser: function (s) {
                        if (!s) return new Date();
                        var y = s.substring(0,4);
                        var m =s.substring(5,7);
                        var d = s.substring(8,10);
                        var h = s.substring(11,13);
                        var min = s.substring(14,16);
                        var sec = s.substring(17,19);
                        if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)){
                            return new Date(y,m-1,d,h,min,sec);
                        } else {
                            return new Date();
                        }
                    }
                }).combo({
                    onChange : function (newVal,oldVal) {
                        setProxy(targetNode.attr('textboxname'),newVal);
                    }
                }).datebox("calendar").calendar({
                    validator: function(date){
                        var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
                        return d1<=date;
                    },
                });
            });

            $(".shoujixinxi a[name='vote_collection_btn']").on("click",function(){

                var targetVal = $(this).data("value");
                var exist = false;

                var targetObj = {
                    value : targetVal,
                    text : $(this).text()
                };

                $("#vote_collect_list").find("div[name='vote_collect_item']").each(function(){
                    if($(this).data("selectedval")==targetVal){
                        exist = true;
                        return false;
                    }
                });

                if(!exist){
                    var html = template("vote_collect_item_tmpl",targetObj);
                    if($("#vote_collect_list div[name='vote_collect_item']").length>0) {
                        $("#vote_collect_list div[name='vote_collect_item']:last").after(html);
                    }else{
                        $("#vote_collect_list").html(html);
                    }

                }
            });
        }();

        //投票项事件
        ~function(){
            $(document).on("click",".xuanxiang a[name='vote_add_normal_item']",function(){
                var lastNode = $("#vote_item_normal_group .xuanxiang:last");
                var html = template("vote_item_tmpl",{});
                lastNode.after(html);
                resortItemOrderNormal();
            });

            $(document).on("click",".xuanxiang a[name='vote_uploadimg_normal_item']",function(){
                var targetNode = $(this);
                var parentNode = targetNode.parents(".xuanxiang");
                var status = parentNode.find("input[name='vote_subject_item_status']").val();
                if(status=="0"){
                    layer.alert("该投票项当前为禁用状态", {icon: 5});
                    return;
                }

                targetNode.siblings("input[type='file']").detach();
                var nodeId = "file"+(new Date()).getTime();
                var node = $("<input type='file' id='"+nodeId+"' name='vote_img_upload' style='display:none;' accept='image/jpeg,image/gif,image/png'>");
                targetNode.after(node);
                targetNode.siblings("input[type='file']").on("change",function(){
                    var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});
                    $.ajaxFileUpload({
                        url: ctx + '/cms/vote/fileUpload.php', //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: nodeId, //文件上传域的ID
                        dataType: 'json', //返回值类型 一般设置为json
                        before: function(){
                            parentNode.find("div[name='vote_normal_item_pic']").html("");
                        },
                        success: function (result){  //服务器成功响应处理函数
                            if(result.code=='2000') {
                                parentNode.find("input[name='vote_subject_item_img']").val(result.filePath);
                                parentNode.find("div[name='vote_normal_item_pic']").html($('<img src="'+result.hostUrl + result.filePath+'" height="60">'));
                            }else{
                                layer.alert(result.msg||"图片上传失败", {icon: 5});
                            }
                        },
                        error: function (){//服务器响应失败处理函数
                            layer.alert("上传文件失败", {icon: 5});
                        },
                        complete:function(){
                            layer.close(loadIndex);
                            targetNode.siblings("input[type='file']").detach();
                        }
                    });

                }).trigger("click");

            });

            $(document).on("click",".xuanxiang a[name='vote_delete_normal_item']",function(){
                var targetNode = $(this);
                targetNode.parents(".xuanxiang").detach();
                resortItemOrderNormal();
            });

            $(document).on("click",".xuanxiang a[name='vote_intro_normal_item']",function(){

                var parentNode = $(this).parents(".xuanxiang");
                var status = parentNode.find("input[name='vote_subject_item_status']").val();
                if(status=="0"){
                    layer.alert("该投票项当前为禁用状态", {icon: 5});
                    return;
                }
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['1100px', '600px'], //宽高
                    content: '<div style="margin:0 5px;"><textarea id="vote_intro_layer"></textarea></div><div style="text-align:center"><button id="vote_intro_layer_btn" class="btn btn-success" type="button">确认</button></div>',
                    success: function(){

                        $("#vote_intro_layer_btn").on("click",function(){
                            // var textLength = voteIntroduceEdtior.getContent().length;
                            parentNode.find("div[name='vote_intro_html']").html(voteIntroduceEdtior.getContent());
                            layer.closeAll();
                        });

                        var existWords = parentNode.find("div[name='vote_intro_html']").html();
                        $("#vote_intro_layer").html(existWords);

                        voteIntroduceEdtior = UE.getEditor('vote_intro_layer',voteIntroduceEdtiorConfig);
                        // introEditor = new Editor($("#vote_intro_layer").get(0));
                        // introEditor.customConfig.uploadImgServer = ctx+'/cms/vote/fileUpload.php'
                        // introEditor.customConfig.customAlert = function (info) {
                        //     layer.alert(info, {icon: 5});
                        // };
                        // introEditor.customConfig.height = 550;
                        // introEditor.create();
                    },
                    end: function(){
                        voteIntroduceEdtior.destroy();
                        voteIntroduceEdtior = null;
                    }

                });
            });

        }();

        function serializeObject(){

            var baseVoteInfo = JSON.parse($("form[name='vote_base_info']").serializeJson());

            var voteItemList = [],voteItem;
            var type = $("#vote_item_btn_group button.btn-success").data("type");

            $("#vote_item_normal_group div.xuanxiang").each(function(){
                voteItem = {
                    id : $(this).find("input[name='vote_subject_item_id']").val()||"",
                    subjectId : $("#vote_subjectId").val()||"",
                    type : type||"",
                    item : $(this).find("input[vote-field='vote_item_name']").val()||"",
                    image : $(this).find("input[name='vote_subject_item_img']").val()||"",
                    detail : $(this).find("div[name='vote_intro_html']").html()||"",
                    sort : $(this).find("input[name='vote_subject_item_sort']").val()||"",
                    nop : $(this).find("input[name='vote_subject_item_nop']").val()||"",
                    status : $(this).find("input[name='vote_subject_item_status']").val()||""
                }
                voteItemList.push(voteItem);
            });

            baseVoteInfo.startIntro = startIntroEditor.getContent();
            baseVoteInfo.endIntro = endIntroEditor.getContent();

            var voteCollectList = [];
            $("#vote_collect_list").find("div[name='vote_collect_item']").each(function(){
                var id = $(this).data("id");
                var voteId = $("#id").val();
                var dictId = $(this).data("selectedval");
                var text = $(this).find("span[name='vote_collect_name']").text();
                var isChecked = $(this).find("input[name='vote_collect_item_checked']").prop("checked")?true:false;
                var param = {
                    id : id||"",
                    voteId : voteId||"",
                    dictId : dictId||"",
                    content : text||"",
                    required : isChecked||false
                };
                voteCollectList.push(param);
            });


            baseVoteInfo.voteItemListStr = JSON.stringify(voteItemList);
            baseVoteInfo.voteCollectListStr = JSON.stringify(voteCollectList);

            return baseVoteInfo;

        }

        $("#vote_editor_confirm_btn").on("click",function(){

            var loadIndex = layer.load(1,{shade: [0.6,"#393D49"]});

            var reqParam = serializeObject();

            if(reqParam.name.length < 2){
                layer.msg("活动标题不能低于2个字符", {icon: 5,offset: abc.winscrollTop(200)},function(){
                    layer.closeAll();
                });
                return;
            }

            if(reqParam.startIntro.length>4000){
                layer.msg("活动介绍内容（包含格式）的长度不能超过4000,当前长度：" + reqParam.startIntro.length, {icon: 5,offset: abc.winscrollTop(200)},function(){
                    layer.closeAll();
                });
                return;
            }

            if(reqParam.endIntro.length>4000){
                layer.msg("投票后显示的内容（包含格式）的长度不能超过4000,当前长度：" + reqParam.endIntro.length, {icon: 5,offset: abc.winscrollTop(200)},function(){
                    layer.closeAll();
                });
                return;
            }

            $("#vote_base_info").isValid(function(baseCheck){
                $("#vote_item_info").isValid(function (itemCheck) {
                    if(baseCheck&&itemCheck){
                        if(!validFields(reqParam)) {
                            layer.close(loadIndex);
                            return;
                        }

                        layer.confirm("是否确认提交？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0,offset: abc.winscrollTop(200)},function() {
                            $.ajax({
                                url: ctx + "/cms/vote/inserOrUpdate.php",
                                type: "POST",
                                data: reqParam,
                                success: function (result) {

                                    if (result.code == "2000") {
                                        layer.msg('操作成功', {
                                            title: "",
                                            icon: 1,
                                            closeBtn: 0,
                                            shade: [0.6, "#393D49"],
                                            offset: abc.winscrollTop(200)
                                        }, function () {
                                            location.href = ctx + "/cms/vote/publish.php?voteId=" + result.data.id;
                                        });
                                    } else {
                                        layer.msg(result.message || "投票基本设置失败", {icon: 5,title: "",offset: abc.winscrollTop(200)});
                                    }

                                },
                                error: function () {
                                    layer.msg("操作失败", {icon: 5,title: "", closeBtn:0, offset: abc.winscrollTop(200)});
                                },
                                complete: function () {
                                    layer.close(loadIndex);
                                }
                            });
                        },function () {
                            layer.close(loadIndex);
                        });
                    }else{
                        layer.close(loadIndex);
                    }
                })
            });

        });

        $("#vote_editor_back_btn").on("click",function(){
            location.href = ctx + "/cms/vote/list.php";
            //window.location.href = document.referrer;
        });

        var validFields = function(param){

            var type = $("#vote_item_btn_group button.btn-success").data("type");
            var itemNum = $("#vote_item_normal_group div.xuanxiang").length;
            if(itemNum<2){
                layer.msg("请至少设置两个投票选项",{offset: abc.winscrollTop(200)});
                return false;
            }

            var itemList = JSON.parse(param.voteItemListStr);
            for(var i in itemList){
                if(!$.trim(itemList[i].item)){
                    layer.msg("投票选项名称不能为空",{offset: abc.winscrollTop(200)});
                    return false;
                }
            }

            return true;
        }

        $("#vote_publish").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.msg('请先保存该投票项', {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            location.href = ctx + "/cms/vote/publish.php?voteId="+voteId;
        });

        $("#vote_generate").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.msg('请先保存该投票项', {icon: 5,offset: abc.winscrollTop(200)});
                return ;
            }

            location.href = ctx + "/cms/vote/generate.php?voteId="+voteId;
        });

        $(document).on("click","a[name='vote_remove_label']",function(){
            $(this).parents("div[name='vote_collect_item']").detach();
        })

    });
});