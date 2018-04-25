/**
 * Created by LiuQi
 * 知识库  分类管理
 */
define(['jquery-3.1.0','ztree', 'ztree.excheck','blockUI','layui'],function(){
    var ajaxList_url, modifyName_url, delete_url, save_url;
    function initUrl(){
        ajaxList_url = "/cms/knowcate/ajaxList";
        modifyName_url = "/cms/knowcate/modifyName/";
        save_url= "/cms/knowcate/save";
        delete_url = "/cms/knowcate/delete/";
        init();
    }

    function escape2Html(str) {
        if(str){
            var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
            return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
        }else{
            return str;
        }
    }

    //初始化知识分类 ajaxList_url:分类列表查询url,  modifyName_url:分类名称修改url
    function init(){
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            view: {},
            callback: { //点击回调
                beforeRename   :function(treeId, treeNode, newName, isCancel){
                    var oldName = treeNode.name;
                    var id = treeNode.idx;
                    if(oldName != newName){
                        $.ajax({
                            type:'PUT',
                            url:ctx +modifyName_url+id,
                            data:{"name":newName},
                            success : function(result){
                                if (result.code == "2000") {
                                    layer.msg("操作成功", {icon: 1});
                                } else {
                                    layer.alert("操作失败", {icon: 5});
                                }
                            }
                        });
                    }
                }
            },
            data: {
                simpleData: {enable: true}
            },
            edit:{
                enable: false,  //启用编辑
                editNameSelectAll:  false
            }
        };

        $.ajax({
            type:'GET',
            url:ctx +ajaxList_url,
            async: false,
            dataType:'json',
            success : function(data){
                var zNodes =  [{ id:0, pId:'', name:"全部分类", open:true}];
                data = data.dataList;
                $("#category_ul").empty();
                $.each(data,function(key, val){
                    var obj = {};
                    obj.idx=val.id;
                    obj.id = val.code;
                    obj.pId = val.parentCode;
                    obj.pName = escape2Html(val.parentName);
                    obj.name = escape2Html(val.name);
                    obj.open = true;
                    if(val.parentCode==0){
                        obj.isParent = 1;
                        obj.open = false;
                    }
                    obj.sort = val.sort;
                    zNodes.push(obj);
                    var li = '<li class="category_li" data-id="'+val.id+'" data-code="'+val.code+'" data-pCode="'+val.parentCode+'" data-name="'+val.name+'" data-open="'+obj.open+'"></li>';
                    $("#category_ul").append(li);
                });
                zTreeObj = $.fn.zTree.init($("#cateTree"), setting, zNodes);
                var select_node_id = $(".js_select_cate").val();
                if(select_node_id){
                    var node = zTreeObj.getNodeByParam("id",select_node_id);
                    zTreeObj.selectNode(node);
                }

            }
        });
    }

    /* 新增 */
    $("#addCateBtn").on('click',function(){
        var nodes = zTreeObj.getSelectedNodes();
        if(nodes.length > 1){
            layer.alert("只能选择一个分类", {icon: 5});
            return;
        }else if(nodes.length == 0){
            layer.alert("请选择上级分类", {icon: 5});
            return;
        }else{
            showAddCate(nodes);
        }
    });

    /* 编辑 */
    $("#editCateBtn").on('click',function(){
        var nodes = zTreeObj.getSelectedNodes();
        if(nodes.length > 1){
            layer.alert("只能选择一个分类", {icon: 5});
            return;
        }
        if(nodes.length == 0) {
            layer.alert("请选择分类", {icon: 5});
            return;
        }
        var node = nodes[0];
        if(node.id == 0){
            layer.alert("请选择分类", {icon: 5});
            return;
        }
        var pName = node.pName;
        if(!node.pName){
            pName = '全部分类';
        }
        var parameter = { "id":node.idx, name:node.name, code:node.id, parentCode:node.pId, parentName:pName, "sort":node.sort};
        $.ajax({
            type: "GET",
            url: ctx + "/cms/knowcate/add",
            data:parameter,
            success: function(result){
                var layerIndex =layer.open({
                    title:"编辑分类",
                    type: 1,
                    area: ['600px', '250px'],
                    fixed: false, //不固定
                    maxmin: false,
                    zIndex: 200,
                    content: result,
                    success:function(){
                        //关闭分类弹框
                        $(".js_cancel_cate").on('click',function(){
                            layer.close(layerIndex);
                        });
                        $(".js_save_cate").on('click',function(){
                            var name = $("#addKnowForm [name='name']").val();
                            var sort = $("#addKnowForm [name='sort']").val();
                            if(name ==''){
                                layer.msg('请输入分类名称', {icon: 5});
                                return;
                            }
                            if(name.length > 30){
                                layer.msg('分类名称长度不能大于30', {icon: 5});
                                return;
                            }
                            if(!sort.match(/^([1-9]\d{0,2})$/)){
                                layer.msg('排列顺序限制范围[1-999]', {icon: 5});
                                return ;
                            }
                            $.ajax({
                                type:'POST',
                                url:ctx +save_url,
                                data:$("#addKnowForm").serialize(),
                                dataType:'json',
                                success : function(result){
                                    console.log(result);
                                    if(result.code=='2000') {

                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function (){
                                            window.location.href=$('.js_currLink').val();
                                        });
                                    }else{
                                        layer.alert(result.message||"保存失败", {icon: 5});
                                    }
                                }
                            });
                        });
                    }
                });
            },
            error: function(){
                layer.msg('失败', {icon: 5});
            }
        });
    });

    $("#removeCateBtn").on('click',function(){
        var nodes = zTreeObj.getSelectedNodes();
        if(nodes.length == 1){
            if(nodes[0].id == 0){
                layer.alert("请选择需要删除的分类", {icon: 5});
                return;
            }
            layer.confirm("是否确认删除分类？", {title:'操作提示',btn: ['确认','取消'], icon: 3, closeBtn: 0},function() {
                $.ajax({
                    type:'DELETE',
                    url:ctx +delete_url+nodes[0].idx,
                    success : function(result){
                        if (result.code == "2000") {
                            layer.alert(result.message || "删除成功", {icon: 1});
                            zTreeObj.removeNode(nodes[0]);
                        } else {
                            layer.alert(result.message || "删除失败", {icon: 5});
                        }
                    }
                });
            });
        }else{
            layer.alert("请选择需要删除的分类", {icon: 5});
        }
    });

    //弹出新增知识分类
    function showAddCate(nodes){
        var id ="";
        var pid = "";
        var pname = "";
        if(nodes.length == 1){
            id = nodes[0].id;
            pid = nodes[0].idx;
            pname = nodes[0].name;
        }
        if(id.length==18){
            layer.alert("已经是最末级分类不能添加子分类", {icon: 5});
            return;
        }
        var parameter = { "parentCode":id,  "parentName":pname};
        $.ajax({
            type: "GET",
            url: ctx + "/cms/knowcate/add",
            data:parameter,
            success: function(result){
                var layerIndex =layer.open({
                    title:"新增分类",
                    type: 1,
                    area: ['600px', '300px'],
                    fixed: false, //不固定
                    maxmin: false,
                    content: result,
                    success:function(){
                        //关闭新增分类弹框
                        $(".js_cancel_cate").on('click',function(){
                            layer.close(layerIndex);
                        });
                        $(".js_save_cate").on('click',function(){
                            var name = $("#addKnowForm [name='name']").val();
                            var sort = $("#addKnowForm [name='sort']").val();
                            if(name ==''){
                                layer.msg('请输入分类名称', {icon: 5});
                                return;
                            }
                            if(name.length > 30){
                                layer.msg('分类名称长度不能大于30', {icon: 5});
                                return;
                            }
                            if(!sort.match(/^([1-9]\d{0,2})$/)){
                                layer.msg('排列顺序限制范围[1-999]', {icon: 5});
                                return ;
                            }
                            $.ajax({
                                type:'POST',
                                url:ctx +save_url,
                                data:$("#addKnowForm").serialize(),
                                dataType:'json',
                                success : function(result){
                                    console.log(result);
                                    if(result.code=='2000') {
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                        location.reload();
                                    }else{
                                        layer.alert(result.message||"保存失败", {icon: 5});
                                    }
                                }
                            });
                        });
                    }
                });
            },
            error: function(){
                layer.msg('失败', {icon: 5});
            }
        });
    };


    var setting2 = {
        view: {dblClickExpand: false,showLine: false},
        data: {simpleData: {enable: true}},
        callback: {
            onClick: function(e,treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);
                //点击给上级菜单栏位赋值；
                $(".js_selectParentCate").val(treeNode.name);
                $("[name='parentCode']").val(treeNode.id);
            }
        }
    };
    /* 选择父分类 */
    $('body').on('click', '.js_selectParentCate', function(){
        var code = $(this).attr("date-code");
        var parentCode = $("[name='parentCode']").val();
        var zNodes =  [{ id:0, pId:'', name:"全部分类", open:true}];
        $(".category_li").each(function(){
            var obj = {};
            obj.idx= $(this).attr("data-id");
            obj.id = $(this).attr("data-code");
            if(obj.id.indexOf(code)==0){
                return true;
            }
            obj.pId = $(this).attr("data-pCode");
            obj.name = $(this).attr("data-name");
            obj.open = $(this).attr("data-open");
            zNodes.push(obj);
        });
        var zTree = $.fn.zTree.init($("#treeDemo"), setting2, zNodes);
        $(".js_pop_menu").show();
    });
    //点击取消 隐藏对话框
    $('body').on('click', '.js_close', function(){
        $(".main_modal").hide();
    });





    initUrl();
});