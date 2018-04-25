/**
 * Created by Li Hongwei on 2015-08-21.
 * 功能菜单管理js
 */
function selectPage() {
    var options = {
        success: function () {
            $.AbcUtil.relocationPaginationBar();
            $.unblockUI();
        },
        error:function(){
            layer.msg('查询失败！', {icon: 2});
            $.unblockUI();
        },
        target: '#func-upd'
    }
    $.blockUI();
    var $search_from = $('#search-from');
    $search_from.attr('action', '/xtgl/jggl/search.html');
    $search_from.ajaxSubmit(options);
}

function doPage(pageNumber) {
    $('#pagination_currentPage').val(pageNumber);
    selectPage();
}

$(function() {
    $('#seljglx').chosen({
        width:'120px',
        disable_search : true
    });

    $('#search-btn').click(function() {
        $('#pagination_currentPage').val(1);
        $('#pagination_itemsPerPage').val(fitPageNum);
        $('#ly').val('form');
        selectPage();
    });
    $('#search-btn').trigger('click');
});
var sysFuncValidateOptupdate = {
    theme: 'simple_right',
    timely:1,
    fields: {
        "#jglx": {
            rule: "required;",
            tip: "请选择机构类型",
            //ok: "",
            msg: {required: "机构类型必选"}
        },
        "#ssdq": {
            rule: "required;",
            tip: "请选择所属地区",
            ok: "",
            msg: {required: "所属地区必选"}
        },
        "#jgmc":{
            rule: "required;length[4~50, true]",
            //tip: "请填写机构名称",
            ok: "",
            msg: {required: "请填写机构名称"}
        },
        "#jgdm":{
            rule: "required;szzm;length[4~40, true];",
            //tip: "机构代码不能为空",
            ok: "",
            msg: {required: "请填写机构代码"}
        },
        "#sjjg":{
            rule: "required;",
            //tip: "请选择机上级机构",
            ok: "",
            msg: {required: "请选择机上级机构"}
        },
        "jggl.CONTACT":{
            rule: "required;chineseEnglish;length[4~20, true]",
            //tip: "请填写联系人",
            ok: "",
            msg: {required: "请填写联系人"}
        },
        "jggl.PHONE":{
            rule: "required;telmobile;",
            //tip: "请填写联系电话",
            ok: "",
            msg: {required: "请填写联系电话"}
        },
        "jggl.ADDRESS":{
            rule: "required;;length[4~200, true]",
            //tip: "请填写联系地址",
            ok: "",
            msg: {required: "请填写联系地址"}
        },
        "jggl.REMARK":{
            rule: "length[~1000, true]",
            ok: "",
            msg: {}
        }
    }
};
var sysFuncValidateOpt = {
        theme: 'simple_right',
        timely:1,
        rules: {
            isExists: function(element) {
                return $.ajax({
                    url: ctx + '/xtgl/jggl/isExists.html',
                    type: 'post',
                    data: {code : element.value},
                    dataType: 'json',
                    success: function(d){
                    }
                });
            }
        },
        fields: {
            "#jglx": {
                rule: "required;",
                tip: "请选择机构类型",
                //ok: "",
                msg: {required: "机构类型必选"}
            },
            "#ssdq": {
                rule: "required;",
                tip: "请选择所属地区",
                ok: "",
                msg: {required: "所属地区必选"}
            },
            "#jgmc":{
                rule: "required;length[4~50, true]",
                //tip: "请填写机构名称",
                ok: "",
                msg: {required: "请填写机构名称"}
            },
            "#jgdm":{
                rule: "required;szzm;length[4~40, true];isExists;",
                //tip: "机构代码不能为空",
                ok: "",
                msg: {required: "请填写机构代码"}
            },
            "#sjjg":{
                rule: "required;",
                //tip: "请选择机上级机构",
                ok: "",
                msg: {required: "请选择机上级机构"}
            },
            "jggl.CONTACT":{
                rule: "required;chineseEnglish;length[4~20, true]",
                //tip: "请填写联系人",
                ok: "",
                msg: {required: "请填写联系人"}
            },
            "jggl.PHONE":{
                rule: "required;telmobile;",
                //tip: "请填写联系电话",
                ok: "",
                msg: {required: "请填写联系电话"}
            },
            "jggl.ADDRESS":{
                rule: "required;;length[4~200, true]",
                //tip: "请填写联系地址",
                ok: "",
                msg: {required: "请填写联系地址"}
            },
            "jggl.REMARK":{
                rule: "length[~1000, true]",
                ok: "",
                msg: {}
            }
        }
    };
function Area(_$select) {
    var arealist;
    var $select = {};
    var oArea = {
        init : function(){
            $.ajax({
                url : ctx+'/xtgl/jggl/getArea.html',
                type : 'post',
                dataType : 'json',
                success : function(data) {
                    arealist = data;
                    if(arealist.length>0) {
                        oArea.initOption();
                    } else {
                        oArea.error();
                    }
                },
                error:function() {
                    oArea.error();
                }
            });
        },
        error:function() {
            $select.find('.province, .city, .area').attr('data-placeholder','加载失败...');
            oArea.initSel();
        },
        initOption :function() {
            $select.find('.province, .city, .area').html('<option value="">请选择</option>');
            oArea.initSel();
            oArea.setProvince();
            oArea.setSel();
            oArea.bind();
        },
        setSel:function() {
            var val = $select.find('.ssdq').val();
            if(val!=null && val!="") {
                if(val.substr(-4)=='0000'){
                    $select.find('".province option[value=\"'+val+'\"]"').attr("selected",true);
                    //$select.find(".province").val(val);
                    $select.find('.province').trigger('chosen:updated');
                    oArea.provinceChange();
                } else{
                    if(val.substr(-2)=='00'){
                        $select.find('".province option[value=\"'+(val.substr(0,2)+'0000')+'\"]"').attr("selected",true);
                        $select.find('.province').trigger('chosen:updated');
                        oArea.provinceChange();
                        $select.find('".city option[value=\"'+val+'\"]"').attr("selected",true);
                        $select.find('.city').trigger('chosen:updated');
                        oArea.cityChange();
                    }else{
                        $select.find('".province option[value=\"'+(val.substr(0,2)+'0000')+'\"]"').attr("selected",true);
                        $select.find('.province').trigger('chosen:updated');
                        oArea.provinceChange();
                        $select.find('".city option[value=\"'+(val.substr(0,4)+'00')+'\"]"').attr("selected",true);
                        $select.find('.city').trigger('chosen:updated');
                        oArea.cityChange();
                        $select.find('".area option[value=\"'+val.substr(0,6)+'\"]"').attr("selected",true);
                        $select.find('.area').trigger('chosen:updated');
                    }
                }
            }
        },
        setProvince :function() {
            var html = '<option value="">请选择</option>';
            for(var i= 0,j=arealist.length; i<j;i++) {
                if(arealist[i].AREATYPE=="1") {
                    html += ('<option value="'+ arealist[i].ID +'">' + arealist[i].NAME + '</option>');
                }
            }
            $select.find('.province').html(html);
            $select.find('.province').trigger('chosen:updated');
        },
        provinceChange :function(e) {
            var fatherid = $select.find('.province option:selected').val();
            var html = '<option value="">请选择</option>';
            if (fatherid != "") {
                for (var i = 0, j = arealist.length; i < j; i++) {
                    if (arealist[i].AREATYPE == "2" && arealist[i].PARENTID == fatherid) {
                        html += ('<option value="' + arealist[i].ID + '">' + arealist[i].NAME + '</option>');
                    }
                }
            }
            $select.find('.city').html(html);
            $select.find('.city').trigger('chosen:updated');
            //oArea.cityChange();
        },
        cityChange :function() {
            var fatherid = $select.find('.city option:selected').val();
            var html = '<option value="">请选择</option>';
            if (fatherid != "") {
                for (var i = 0, j = arealist.length; i < j; i++) {
                    if (arealist[i].AREATYPE == "3" && arealist[i].PARENTID == fatherid) {
                        html += ('<option value="' + arealist[i].ID + '">' + arealist[i].NAME + '</option>');
                    }
                }
            }
            $select.find('.area').html(html);
            $select.find('.area').trigger('chosen:updated');
        },
        initSel:function() {
            var areaSel = {
                '.province' :{width:'100px'},
                '.city' :{width:'100px'},
                '.area' :{width:'100px'}
            };
            for(var s in areaSel) {
                $select.find(s).sel(areaSel[s]);
            }
        },
        bind:function() {
            $select.find('.province').chosen().change(function (e) {
                oArea.setVal(this);
                oArea.provinceChange();
            });
            $select.find('.city').chosen().change(function () {
                oArea.setVal(this);
                oArea.cityChange();
            });
            $select.find('.area').chosen().change(function () {
                oArea.setVal(this);
            });
        },
        setVal :function(_this) {
            var val = $(_this).find('option:selected').val();
            $select.find('.ssdq').val(val);
        }
    };
    $select = _$select;
    oArea.init();
}

function Panl(oper,id,$panl) {
    var _oper = oper;
    var _id = id;
    var $operation_panl = {};
    var $validatorFrom = {};
    var $queryBox = {};

    var panl = {
        initOption :function() {
            new Area($operation_panl);
            panl.initSel();
            panl.bindSel();
            //panl.initValidate();
        },
        initSel :function() {
            var sel = {
                '.jglx' :{width:'120px'}
            };
            for(var s in sel) {
                $operation_panl.find(s).sel(sel[s]);
            }

        },
        initValidate:function() {
            $validatorFrom = $operation_panl.find('.operation-from').validator(sysFuncValidateOpt);
        },
        bindSel:function() {
            panl.jglxSelChange();
            //panl.ssdqSelChange();
        },
        jglxSelChange :function() {
            $operation_panl.find('#jglx').on('change', function() {
                $('#jgdm').val('');
                if($('#jglx').val()=="0") {
                }else {
                    $('#yhhb_chosen').hide();
                }
                if(($('#jglx').val()=="0" || $('#jglx').val()=="2") && $('#ssdq').val()!="") {//税务机关 银行
                    $('#jgmc').unbind('blur');
                } else if($('#jglx').val()=="1") {//保险
                } else{
                }
            });
        },
        ssdqSelChange :function() {
            $operation_panl.find(".province, .city, .area").on('change', function() {
                $('#jgdm').val('');
                $('#jgmc').val('');
                if($('#jglx').val()=="0"|| $('#jglx').val()=="2") {//bank
                } else if($('#jglx').val()=="1") {
                }
            });
        },
        sjjgSelInit :function() {
            $.ajax({
                url : ctx+'/xtgl/jggl/getOrgSelect.html',
                data : {TYPE : $('#jglx').val()},
                type : 'post',
                dataType : 'json',
                success : function(data) {
                    var sXphms = '<option value="" selected>请选择</option><option value="0">无</option>';
                    for(var i= 0, j=data.length; i<j; i++) {
                        sXphms +='<option value="' + data[i].ID+ '">' + data[i].NAME  + '</option>'
                    }
                    $operation_panl.find('#sjjg').html(sXphms);
                    $operation_panl.find('#sjjg').trigger("chosen:updated");
                }
            });
        }
    };
    $operation_panl = $panl;
    panl.initOption();
}
var $funcTree,pid;
$(function () {
    $("#func-tree").dynatree({
        checkbox:false,
        selectMode:3,
        clickFolderMode:1,
        initAjax:{
            url:ctx + "/xtgl/jggl/getOrgTree.html"
        },
        onSelect:function (select, node) {
            //var selNodes = node.tree.getSelectedNodes();
            //if(selNodes.length > 0){
            //    var selKeys = $.map(selNodes, function(node){
            //        return node.data.key;
            //    });
            //    $('#del-funcs-inp').val(selKeys.join(";"));
            //    $('#del-sysfunc-btn').enable(true).removeClass("page-button-disabled");
            //}  else{
            //    $('#del-funcs-inp').val("");
            //    $('#del-sysfunc-btn').enable(false).addClass("page-button-disabled");
            //}
        },
        onClick:function (node, event) {
            $('#sjjgdm').val(node.data.key);
            //$('#search-from').find('[name="jggl.NAME"]').val('');
            $('#ly').val('tree');
            $('#PARENT_NAME').val(node.data.title);
            selectPage();
            //if (node.getEventTargetType(event) == "title") {
            //    if (node && "0" != node.data.key) {
            //        pid = node.data.key;
            //        $('#func-upd').block();
            //        $.ajax({
            //            url:ctx + "/xtgl/jggl/operation.html",
            //            type:'post',
            //            dataType:'html',
            //            data:{
            //                "jggl.ID": node.data.key,
            //                "operation": "edit"
            //            },
            //            success:function (data) {
            //                //if($.AbcUtil.isLoginPage(data)){
            //                //    top.location = ctx + "/login.jsp";
            //                //    return true;
            //                //};
            //
            //                $('#func-upd').html(data);
            //                new Panl('edit','',$('#func-upd'));
            //                $('#func-upd').unblock();
            //                var $updVali = $('#func-upd').find('form:first').validator(sysFuncValidateOpt);
            //
            //                $updVali.validator().trigger("showtip");
            //
            //                $('#func-upd').find('#save-btn').click(function (event) {
            //                    if ($updVali.isValid()) {
            //                        $.blockUI();
            //                        var options = {
            //                            type:'post',
            //                            url:ctx + '/xtgl/jggl/edit.html',
            //                            dataType:'JSON',
            //                            success:function (result) {
            //                                //var _result = eval("(" + result + ")");
            //                                alert(result.msg);
            //                                if (result.result) {
            //                                    if ($funcTree)
            //                                        $funcTree.reload();
            //                                }
            //                            }
            //                        };
            //
            //                        $('#func-upd').find('form:first').ajaxSubmit(options);
            //                        $.unblockUI();
            //                    }
            //                });
            //            },
            //            error:function () {
            //                alert("查询失败！");
            //                $.unblockUI();
            //            }
            //        });
                //}
            //}
        //}
        //,onLazyRead:function (node) {
        //    node.appendAjax({
        //        url:ctx + "/xtgl/jggl/getOrgTree.html",
        //        method:"POST",
        //        data:"jggl.PARENTID=" + node.data.key,
        //        debugLazyDelay:300
        //    });
        }
    });

    $funcTree = $("#func-tree").dynatree("getTree");

    var add_btn = function () {
        $('#toadd-btn').off('click.add');
        pid = '';
        var $panl_r = $("#add-panel");
        $panl_r.block();
        $.ajax({
            url:ctx + "/xtgl/jggl/operation.html",
            type:'post',
            dataType:'html',
            success:function (data) {
                //if($.AbcUtil.isLoginPage(data)){
                //    top.location = ctx + "/login.jsp";
                //    return true;
                //};
                //var html = '<div style="margin: 0px; padding: 0px; border: currentColor; border-image: none; left: 0px; top: 0px; width: 100%; height: 100%; position: absolute; z-index: 99999; cursor: wait; opacity: 0.6; background-color: rgb(0, 0, 0);"></div>' +data;
                $panl_r.html(data);
                new Panl('edit','',$panl_r);
                $panl_r.unblock();
                var $addVali = $panl_r.find('form:first').validator(sysFuncValidateOpt);
                $addVali.validator().trigger("showtip");
                var $add_panel = $panl_r.slidpanel({
                    title:'新增机构',
                    buttons: {
                        '保存' :function() {
                            if ($addVali.isValid()) {
                                var options = {
                                    type:'post',
                                    url:ctx + '/xtgl/jggl/add.html',
                                    dataType:'JSON',
                                    success:function (result) {
                                        if (result.result) {
                                            layer.msg(result.msg, {icon: 1});
                                            if ($funcTree)
                                                $funcTree.reload();
                                            selectPage();
                                        } else {
                                            layer.msg(result.msg, {icon:2});
                                        }
                                        $('#toadd-btn').on({'click.add':add_btn});
                                        $add_panel.slidpanel('close');
                                        $.unblockUI();
                                    }
                                };
                                $.blockUI();
                                $panl_r.find('form:first').ajaxSubmit(options);
                            }
                        },
                        '取消': function() {
                            //console.info(this)
                            //alert($(this).html());
                            $('#toadd-btn').on({'click.add':add_btn});
                            $add_panel.slidpanel('close');
                        }
                    }
                });
            }
        });
    };
    $('#toadd-btn').on({'click.add':add_btn});
});

var $parIdTree;
function showDialogTree(selId, labelId, jcId, dlId, dlLabelId, opt) {

    var type;
    //if(opt && opt.functionId){
    //    alert($('#add-panel').find('[name="jggl.TYPE"]').val());
    //}else{
    //    type = $('#add-panel').find('[name="jggl.TYPE"]').val();
    //}
    type = $('#jglx').val();

    $("#func-parent-tree").dynatree({
        checkbox:false,
        clickFolderMode:1,
        initAjax:{
            url:ctx + "/xtgl/jggl/getOrgTree.html",
            data:{type : type}
        },
        onActivate:function (node) {
            //if(opt && opt.functionId){
            //    if(opt.functionId == node.data.key){
            //        alert("不能选取当前机构且级次低于当前机构");
            //        return false;
            //    }
            //}

            $('#' + selId).val(node.data.key);
            $('#' + labelId).text(node.data.title);
            //$('#' + jcId).val(node.data.pro.JC + 1);
            //$('#' + dlId).val(node.data.pro.DL);
            //$('#' + dlLabelId).val(node.data.pro.DL==1?"客户端功能菜单":"管理端功能菜单");
            $treeDialog.close();
        },
        onLazyRead:function (node) {
            node.appendAjax({
                url:ctx + "/xtgl/jggl/getOrgTree.html",
                method:"POST",
                data:"sysModule.PAR_ID=" + node.data.key,
                debugLazyDelay:300
            });
        }
    });
    if ($parIdTree)
        $parIdTree.reload();
    $parIdTree = $("#func-parent-tree").dynatree("getTree");

    //if(type) {
        var fatherNode = ["银行", "保险", "税务机关"];
        //for()
    //$("#func-parent-tree").dynatree("getTree").visit(function(node){
    //        alert(node.data.key);
    //});
    //    setTimeout(function(){
    //        $parIdTree.getNodeIndex("_1").remove();
    //    },0);
    //}

    //if(!(opt && opt.functionId)){//Is add true.
        if(pid != "") {
            setTimeout(function(){
                $parIdTree.getNodeByKey(pid).remove();
            },0);
        }
    //}
    var $treeDialog =  $('#sysfunction-tree-dialog-div').dialog({
        title: '机构列表',
        width: 300,
        height: 300,
        modal: true,
        resizable: false,
        buttons: {
            '确定': function () {
                $(this).dialog('close');
            }
        }
    });
    //var $treeDialog = $('#sysfunction-tree-dialog-div').abc("dialog", option, undefined);
}

var open = 1;
function operation(oper, id, isvalide,type) {
    if(open) {
        open= 0;
    if(oper == 'disable') {
        var sTilte = isvalide == "N" ? "启用" : "禁用";
        isvalide = isvalide == "N" ? "Y" : "N";
        layer.confirm('是否确认' + sTilte + '？', {
            btn: [sTilte,'取消']
        }, function(){
            $.ajax({
                type: "post",
                url: ctx + '/xtgl/jggl/disable.html',
                data: {"jggl.ID": id, "jggl.ISVALIDE": isvalide,"jggl.TYPE": type},
                dataType: "json",
                success: function (data) {
                    if (data.result) {
                        layer.msg(data.msg, {icon: 1});
                        if ($funcTree)
                            $funcTree.reload();
                        selectPage();
                    } else{
                        layer.msg(data.msg, {icon: 2});
                    }
                },
                error: function () {
                    layer.msg('操作失败!', {icon: 2});
                }
            });
                open = 1;
        }
        ,function() {
                open = 1;
        });
    } else if(oper == 'edit') {
        //setTimeout(function() {
            pid = id;
        //},0);
        $.ajax({
            url:ctx + "/xtgl/jggl/operation.html",
            type:'post',
            dataType:'html',
            data:{
                "jggl.ID": id,
                "operation": "edit"
            },
            success:function (data) {
                //if($.AbcUtil.isLoginPage(data)){
                //    top.location = ctx + "/login.jsp";
                //    return true;
                //};

                $('#operation-panl').html(data);
                new Panl('edit','',$('#operation-panl'));
                $('#operation-panl').unblock();
                var $updVali = $('#operation-panl').find('form:first').validator(sysFuncValidateOptupdate);

                $updVali.validator().trigger("showtip");

                //$('#operation-panl').find('#save-btn').click(function (event) {
                        var $add_panel = $('#operation-panl').slidpanel({
                            title:'编辑机构',
                            buttons: {
                                '保存': function () {
                                    if ($updVali.isValid()) {
                                        $.blockUI();
                                        var options = {
                                            type: 'post',
                                            url: ctx + '/xtgl/jggl/edit.html',
                                            dataType: 'JSON',
                                            success: function (result) {
                                                //var _result = eval("(" + result + ")");
                                                if (result.result) {
                                                    layer.msg(result.msg, {icon: 1});
                                                    if ($funcTree)
                                                        $funcTree.reload();
                                                    selectPage();
                                                } else{
                                                    layer.msg(result.msg, {icon:2});
                                                }
                                                $add_panel.slidpanel('close');
                                                $.unblockUI();
                                            }
                                        };

                                        $('#operation-panl').find('form:first').ajaxSubmit(options);
                                    }
                                },
                                '取消': function() {
                                    //console.info(this)
                                    //alert($(this).html());
                                    $add_panel.slidpanel('close');
                                }
                            }
                });
                //});
                open = 1;
            },
            error:function () {
                layer.msg('查询失败！', {icon: 2});
                $.unblockUI();
                open = 1;
            }
        });
    }
    }
}