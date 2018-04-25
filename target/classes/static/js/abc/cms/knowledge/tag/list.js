/**
 * Created by LiuQi
 */

require(["../../../../config"], function () {
    require(["jquery-3.1.0","ztree","abc.common","layui","../abc/util/page"], function ($) {

        $.ajax({
            type: "GET",
            url: initPageLink,
            async: false,
            success: function (data) {
                $(".js_page_div").html(data);
            }
        });


        var queryList = function(){
            var nodes = zTreeObj.getSelectedNodes();
            var tagType = "";
            if(nodes.length == 1){
                if(nodes[0].id != -1){
                    tagType = nodes[0].id;
                }
            }
            location.href = ctx+'/cms/knowTag/list.php?keywords='+$.trim($("#keywords").val())+'&tagType='+tagType;
        };
        $('body').on('click', '.js-query', function(e){
            e.preventDefault();
            queryList();
        });

        //list页面 删去
        $('body').on('click', '.js_delete', function(){
            var tagId = $(this).attr("data-id");
            var tagTypeName = "";
            $.ajax({
                type: "GET",
                url: ctx+"/cms/knowTag/selectedTagType/"+tagId+".php",
                async: false,
                success: function (data) {
                    var allTagTypeList = data["allTagTypeList"];
                    var tagTypeArray = data["tagTypeArray"];
                    $.each(tagTypeArray,function(index,value){
                        $.each(allTagTypeList,function(i,v){
                            if(v.fieldValue == value){
                                if(tagTypeName!=''){
                                    tagTypeName = tagTypeName+","
                                }
                                tagTypeName = tagTypeName+v.fieldKey;
                            }
                        });
                    });
                }
            });
            var del_href = $(this).attr("data-href");
            var currLink = $(".js_currLink").val();
            layer.confirm('该标签关联了系统: '+tagTypeName+'<br>  确认删除？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                function(){
                    abc.block();
                    $.ajax({
                        type: "DELETE",
                        url: del_href,
                        async: true,
                        success: function (data) {
                            abc.unblock();
                            if (data && data.code == "2000") {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    window.location.href = currLink;
                                });
                            }else {
                                abc.layerAlert(false, '操作失败: '+data.message);
                            }
                        }
                    });
                }
            );
        });

        //list页面 启用 , 禁用
        $('body').on('click', '.js_enable', function(){
            var tagId = $(this).attr("data-id");
            var tagTypeName = "";
            $.ajax({
                type: "GET",
                url: ctx+"/cms/knowTag/selectedTagType/"+tagId+".php",
                async: false,
                success: function (data) {
                    var allTagTypeList = data["allTagTypeList"];
                    var tagTypeArray = data["tagTypeArray"];
                    $.each(tagTypeArray,function(index,value){
                        $.each(allTagTypeList,function(i,v){
                            if(v.fieldValue == value){
                                if(tagTypeName!=''){
                                    tagTypeName = tagTypeName+","
                                }
                                tagTypeName = tagTypeName+v.fieldKey;
                            }
                        });
                    });
                }
            });
            var del_href = $(this).attr("data-href");
            var currLink = $(".js_currLink").val();
            layer.confirm('该标签关联了系统: '+tagTypeName+'<br>  确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                function(){
                    abc.block();
                    $.ajax({
                        type: "PUT",
                        url: del_href,
                        async: true,
                        success: function (data) {
                            abc.unblock();
                            if (data && data.code == "2000") {
                                layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                    window.location.href = currLink;
                                });
                            }else {
                                abc.layerAlert(false, '操作失败: '+data.message);
                            }
                        }
                    });
                }
            );
        });

        //list 修改按钮
        $('body').on('click', '.js_edit', function(){
            window.location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
        });

        //list批量删除
        $(".js_del_batch_btn").click(function(){
            var ids =[];
            $(".js_checkbox:checked").each(function(i){
                ids.push($(this).val());
            });
            if(ids.length==0){
                abc.layerAlert(false,"请勾选要操作的标签");
            }else{
                abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), JSON.stringify(ids), $(".js_currLink").val());
            }
        });

        var setting = {
            //view: {dblClickExpand: false,showLine: false},
            data: {simpleData: {enable: true}},
            callback: {
                onClick: function(e,treeId, treeNode) {
                    var tagType = "";
                    if(treeNode.id != -1){
                        tagType = treeNode.id;
                    }
                    $.ajax({
                        type: "GET",
                        url: ctx+"/cms/knowTag/page.php?tagType="+tagType+'&keywords='+$("#keywords").val(),
                        async: false,
                        success: function (data) {
                            $(".js_page_div").html(data);
                        }
                    });
                }
            }
        };

        var zNodes = [{ id:-1, pId: -2, name:"系统划分", open:true}];
        $(".tagType_li").each(function(){
            var obj = {};
            obj.id= $(this).attr("data-tagType");
            obj.name= $(this).attr("data-name");
            obj.pId = -1;
            obj.open = true;
            zNodes.push(obj);
        });
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        var select_node_id = $(".js_select_tagType").val();
        if(select_node_id){
            var node = zTreeObj.getNodeByParam("id",select_node_id);
            zTreeObj.selectNode(node);
        }

    });
});