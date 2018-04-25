require(["../../../config"], function () {

    require(["jquery-3.1.0","layui"], function ($) {
        $.ajax({
            type: "GET",
            url: initPageLink,
            async: false,
            success: function (data) {
                $(".js_page_div").html(data);
                layui.use('form', function () {
                    var form = layui.form;
                    form.render();
                });
            }
        });
    });

    require(["jquery-3.1.0","wangEditor","../abc/cms/knowledge/category","template","../plugin/bootstrap-select","../abc/util/page","layui","ztree","abc.common"], function ($,Editor,category) {
        zTreeObj.setting.callback.onClick = function(){
            var categoryCode = "";
            //分类
            var nodes = zTreeObj.getSelectedNodes();
            if(nodes.length == 1){
                categoryCode = nodes[0].id;
            }
            ajaxKnowList(categoryCode);
        };


        //list页面 修改按钮
        $('body').on('click', '.js_edit', function(){
            location.href = $(this).attr("data-href")+"&currLink="+encodeURIComponent($(".js_currLink").val());
        });


        $("#whereBtn").on('click',function(){
            if($("#wherePanel").is(":hidden")){
                $("#wherePanel").show();
            }else{
                $("#wherePanel").hide();
            }
        });

        //新增知识按钮
        $("#addKnowBtn").on('click',function(){
            var nodes = zTreeObj.getSelectedNodes();
            var id = "";
            var name = "";
            if(nodes.length == 1){
                id = nodes[0].id;
                name = nodes[0].name;
            }
            location.href = ctx + "/cms/know/edit?categoryCode="+id+"&categoryName="+name+"&currLink="+encodeURIComponent($(".js_currLink").val());
        });

        //点击修改按钮
        $("#updateKnowBtn").on('click', function(){
            var parameter = getChecked();
            if(parameter.length <= 0 || parameter.split("&").length != 1){
                layer.alert("请选择一条知识记录", {icon: 5});
                return;
            }
            location.href = ctx + "/cms/know/edit?"+parameter;
        });

        $("#searchClickBtn").on('click',searchClick);//加载搜搜列表页面初始化绑定方法

        $(document).on("click",'#lllorder',function (){
            var order=$(this).attr("order");
            var nodes = zTreeObj.getSelectedNodes();
            if(nodes.length == 1){
                ajaxKnowList1( nodes[0].id,"lll");
            }else if(nodes.length == 0){
                ajaxKnowList1("0","lll");
            }
        })

        $(document).on("click",'#gxsjorder',function (){
            var order=$(this).attr("order");
            var nodes = zTreeObj.getSelectedNodes();
            if(nodes.length == 1){
                ajaxKnowList1( nodes[0].id,"gxsj");
            }else if(nodes.length == 0){
                ajaxKnowList1("0","gxsj");
            }
        })

        $(document).on("click",'#quanxuan',function (){
            var oselectall = $('input[name="know_checkbox"]')
            var bool=false;
            oselectall.each(function() {
                if (this.checked == false) {
                    bool=true;
                }
            })
            if(bool){
                oselectall.each(function() {
                    this.checked = true;
                    $(this).next().addClass("layui-form-checked");
                })
            }else{
                oselectall.each(function() {
                    this.checked = false;
                    $(this).next().removeClass("layui-form-checked");
                })
            }
        })

        var form=null;
        layui.use('form', function(){
            form = layui.form;
        });


        var biaoqianindex;
        $(document).on('click','#jbq',function (){
            var parameter = getChecked();
            if(parameter.length <= 0){
                layer.msg('请勾选需要批量操作的记录', {icon: 5});
                return;
            }
            $.ajax({
                url: ctx + "/cms/know/label_list",
                type: "get",
                async: false,
                dataType: "html",
                success: function (result) {
                    biaoqianindex=layer.open({
                        title:"选择标签",
                        type: 1,
                        area: ['550px', '450px'],
                        fixed: false, //不固定
                        maxmin: false,
                        content: result,
                        zIndex:1000,
                        success:function(){
                            form.render('checkbox');
                        },
                        cancel: function(index, layero){
                            layer.close(index);
                        }
                    });
                },
                error: function (err) {
                }
            });

        })

        //标签取消
        $(document).on("click",'#quxiao_',function (){
            layer.close(biaoqianindex);
        })

        //标签保存
        $(document).on('click','#bqsave',function (){
            var value='';
            $("input:checkbox[name='updatelabel']:checked").each(function() {
                value+=$(this).val()+",";  // 每一个被选中项的值
            });
            if(value==''){
                layer.msg('请勾选标签', {icon: 5});
                return;
            }else{
                //alert('可以提交了');
                var oselectall = $('input[name="know_checkbox"]')
                var ids=''
                oselectall.each(function() {
                    if (this.checked == true) {
                        ids+=this.value+','
                    }
                })
                layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},function() {
                    $.ajax({
                        type: 'POST',
                        url: ctx + "/cms/know/modify_tag",
                        data: {ids:ids,tags:value},
                        success: function (result) {
                            if (result.data.code == "2000") {
                                layer.close(biaoqianindex);
                                layer.msg("处理成功",{icon:1});
                                //ajaxKnowList1("0","gxsj");
                            } else {
                                layer.alert(result.data.message || "操作失败", {icon: 5});
                            }
                        }
                    });
                });

            }
        })

        $(document).on('click','#gfl',function (){
            var parameter = getChecked();
            if(parameter.length <= 0){
                layer.msg('请勾选需要批量操作的记录', {icon: 5});
                return;
            }
            var html = '<div style="max-height:370px;overflow: auto;"><div name="column_tree_layer" class="nycon_list_b">' +
                '<div><ul id="cateTree_fenlei" class="ztree" style="margin-top: 0px; height: 100%;"></ul></div>' +
                //'<input value="确认" id="okSub" class="js_close btn btn-info">' +
                '</div></div>';
            html+="<div style='position:fixed;bottom:0; width: 300px; height: 30px; text-align: center; cursor: pointer; background-color: #FF6633; font-size: 16px;font-weight:bold; padding-top: 7px;' id='fenleiok'>确&nbsp;认</div>";
            layerIndex = layer.open({
                title:"选择分类",
                type: 1,
                area: ['300px', '450px'],
                fixed: false, //不固定
                maxmin: false,
                content: html,
                zIndex:1000,
                success:function(){
                    initCateTree("cateTree_fenlei", function (){
                    });
                },
                cancel: function(index, layero){
                    layer.close(index);
                }
            });
        })
        var layerIndex;

        $(document).on("click",'#fenleiok',function (){

            var selectedNodes = zTreeObj1.getSelectedNodes();
            if(selectedNodes.length==0){
                layer.msg('请选择分类', {icon: 5});
                return;
            }
            if(selectedNodes[0].isParent){
                layer.msg('请选择末级节点', {icon: 5});
                return;
            }
            var oselectall = $('input[name="know_checkbox"]')
            var ids=''
            oselectall.each(function() {
                if (this.checked == true) {
                    ids+=this.value+','
                }
            })
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},function() {
                $.ajax({
                    type: 'POST',
                    url: ctx + "/cms/know/modify_code",
                    data: {ids:ids,code:selectedNodes[0].id},
                    success: function (result) {
                        if (result.data.code == "2000") {
                            layer.close(layerIndex);
                            layer.msg("处理成功",{icon:1});
                            //ajaxKnowList1("0","gxsj");
                        } else {
                            layer.alert(result.data.message || "操作失败", {icon: 5});
                        }
                    }
                });
            });
        })

        var zTreeObj1;

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
                    zTreeObj1 = $.fn.zTree.init($("#"+bindEle), setting, zNodes);
                }
            });
        }

        function escape2Html(str) {
            if(str){
                var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
                return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
            }else{
                return str;
            }
        }

    });
});


//批量启用 禁用
//flag  true or false
function betchEff(flag){
    var statusStr = flag?'<div class="btn btn-success btn-xs btn_nocursor">启用</div>':'<div class="btn btn-danger btn-xs btn_nocursor">停用</div>';
    var parameter = getChecked();
    if(parameter.length <= 0){
        layer.msg('请勾选需要批量操作的记录', {icon: 5});
        return;
    }
    parameter += "&flag="+flag;
    layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},function() {
        $.ajax({
            type: 'POST',
            url: ctx + "/cms/know/status",
            data: parameter,
            success: function (result) {
                if (result.code == "2000") {
                    layer.msg(result.message || "操作成功", {icon: 1});
                    $("input[name='know_checkbox']").each(function () {
                        if ($(this).is(":checked")) {
                            $(this).parents("tr").find(".js_show_status").html(statusStr);
                        }
                    });
                } else {
                    layer.alert(result.message || "操作失败", {icon: 5});
                }
            }
        });
    });
}

//获取选择的checkbox
function getChecked(){
    var parameter = "";
    $("input[name='know_checkbox']").each(function(){
        if($(this).is(":checked")){
            parameter += "knowId="+$(this).val()+"&";
        }
    });
    if(parameter.length > 0){
        parameter = parameter.substring(0, parameter.length-1)
    }
    return parameter;
}

function ajaxKnowList1(cateId,order){
    if(cateId == "0"){
        cateId ="";
    }
    var type = $("[name='type']").val();
    var recommend = $("[name='recommend']").val();
    if(type =='QA' && (recommend == 'top'||recommend=='K_hot')){
        layer.msg("类别和知识推荐查询条件冲突", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:2000});
        return;
    }
    if(type =='K' && (recommend == 'common'||recommend=='QA_hot')){
        layer.msg("类别和知识推荐查询条件冲突", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:2000});
        return;
    }
    if(recommend =='QA_hot' && type== ''){
        type = 'QA';
    }
    if(recommend =='K_hot' && type== ''){
        type = 'K';
    }
    if(recommend =='QA_hot' || recommend=='K_hot'){
        recommend = 'hot';
    }

    $.ajax({
        type:'GET',
        url:ctx +"/cms/know/page.php?keywords="+$("#keywords").val()
        +'&status='+$("[name='status']").val()+'&type='+type+'&recommend='+recommend+'&categoryCode='+cateId+"&order="+order,
        success : function(data){
            if (data) {
                $(".js_page_div").html(data);
                layui.use('form', function() {
                    var form = layui.form;
                    form.render();
                });
            } else {
                abc.layerAlert(false, '操作失败: '+data.message);
            }
        }
    });
}

// 批量删除
function betchDel(){
    var parameter = getChecked();
    if(parameter.length <= 0){
        layer.msg('请勾选需要删除的记录', {icon: 5});
        return;
    }
    layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},function() {
        $.ajax({
            type:'POST',
            url:ctx +"/cms/know/delete",
            data :parameter,
            success : function(result){
                if (result.code == "2000") {
                    layer.msg(result.message || "操作成功", {icon: 1});
                    $("input[name='know_checkbox']").each(function(){
                        if($(this).is(":checked")){
                            $(this).parent().parent().remove();
                        }
                    });
                } else {
                    layer.alert(result.message || "操作失败", {icon: 5});
                }
            }
        });
    });
}

/**
 * 点击搜索button
 */
function searchClick(){
    var nodes = zTreeObj.getSelectedNodes();
    if(nodes.length == 1){
        ajaxKnowList( nodes[0].id);
    }else if(nodes.length == 0){
        ajaxKnowList("0");
    }
}

function ajaxKnowList(cateId){
    if(cateId == "0"){
        cateId ="";
    }
    var type = $("[name='type']").val();
    var recommend = $("[name='recommend']").val();
    if(type =='QA' && (recommend == 'top'||recommend=='K_hot')){
        layer.msg("类别和知识推荐查询条件冲突", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:2000});
        return;
    }
    if(type =='K' && (recommend == 'common'||recommend=='QA_hot')){
        layer.msg("类别和知识推荐查询条件冲突", {offset: abc.winscrollTop(200),shade:0.3,icon: 5,time:2000});
        return;
    }
    if(recommend =='QA_hot' && type== ''){
        type = 'QA';
    }
    if(recommend =='K_hot' && type== ''){
        type = 'K';
    }
    if(recommend =='QA_hot' || recommend=='K_hot'){
        recommend = 'hot';
    }

    $.ajax({
        type:'GET',
        url:ctx +"/cms/know/page.php?keywords="+$("#keywords").val()
        +'&status='+$("[name='status']").val()+'&type='+type+'&recommend='+recommend+'&categoryCode='+cateId,
        success : function(data){
            if (data) {
                $(".js_page_div").html(data);
                layui.use('form', function() {
                    var form = layui.form;
                    form.render();
                });
            } else {
                abc.layerAlert(false, '操作失败: '+data.message);
            }
        }
    });
}




Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}