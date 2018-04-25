/**
 * Created by liuqi on 2017/5/22.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","nicevalidator","nicevalidator.zh_CN","layui","abc.common","jquery.validate"], function ($) {

        $(function () {
            //form表单页面提交弹出框
            $(".js_save_btn").click(function (e) {
                if($validatorSaveFrom.isValid()){
                    var modelId = $("[name='modelId']").val();
                    var isChannel = $("[name='isChannel']").val();
                    abc.layerAjaxConfirm("POST", $("form").attr('action'), $("form").serializeJson(), ctx+"/cms/model/item/list.php?modelId="+modelId+"&isChannel="+isChannel);
                }
            });

            //form表单页面 字段dataType select下拉列表 change变化
            $("select[name='dataType']").change(function (e) {
                if($(this).val() == '10'){
                    $(".js_img_set_tr").show();
                }else{
                    $(".js_img_set_tr").hide();
                }
                //可选项：  只有下拉，复选，单选 才会显示出来
                if($.inArray($(this).val(), ['6', '7', '8'] ) >= 0){
                    $(".js_optValue_tr").show();
                }else{
                    $(".js_optValue_tr").hide();
                }
                //默认值
                if($.inArray($(this).val(), [ '1', '2', '3']) >= 0){
                    $(".js_defValue_tr").show();
                }else{
                    $(".js_defValue_tr").hide();
                }
                if($(".js_checkRule_tr").length>0){
                    if($.inArray($(this).val(), ['1', '2', '3'] ) >= 0){
                        $(".js_checkRule_tr").show();
                    }else{
                        $(".js_checkRule_tr").hide();
                    }
                }
            });

            //list页面 (保存系统默认字段,保存内容)
            $(".js_save_list_btn").click(function(){
                var modelId = $("[name='modelId']").val();
                var isChannel = $("[name='isChannel']").val();
                var arr = [],reg = /^([1-9]\d{0,2}|1000)$/,valid=true;
                var trClass = $(this).attr("data-tr-class");
                var checkboxClass = $(this).attr("data-checkbox-class");
                $(trClass).each(function(){
                    var tr = $(this);
                    var flag = false;
                    if(trClass == ".js_save_tr"){
                        flag = true;
                    }else{
                        if(tr.find(checkboxClass)[0].checked){
                            flag = true;
                        }
                    }
                    if(flag){
                        var obj = {};
                        obj.modelitemId = tr.find("[name='modelitemId']").val();
                        obj.field = tr.find("[name='field']").val();
                        obj.dataType = tr.find("[name='dataType']").val();
                        obj.itemLabel = tr.find("[name='itemLabel']").val();
                        obj.priority = tr.find("[name='priority']").val();
                        if(obj.itemLabel.length == 0){
                            abc.layerAlert(false,obj.field+"字段名称不能为空");
                            valid = false;
                            return false;
                        }
                        if(obj.itemLabel.length > 100){
                            abc.layerAlert(false,obj.field+"字段名称长度不能超过100");
                            valid = false;
                            return false;
                        }
                        if(!reg.test(obj.priority)){
                            abc.layerAlert(false,obj.field+"字段的排列顺序范围需在[1-1000]");
                            valid = false;
                            return false;
                        }
                        obj.isDisplay = Number(tr.find("[name='isDisplay']")[0].checked);
                        obj.modelId = modelId;
                        obj.isChannel = isChannel;
                        obj.isSingle = 0;
                        obj.isCustom = tr.find("[name='isCustom']").val();
                        obj.optValue = tr.find("[name='optValue']").val();
                        var isRequired = tr.find("[name='isRequired']").val();
                        if(isRequired == ''){
                            obj.isRequired = 0;
                        }else{
                            obj.isRequired = isRequired;
                        }
                        arr.push(obj);
                    }
                });
                if(valid && arr.length==0){
                    abc.layerAlert(false,"请勾选要操作的字段");
                    return;
                }
                if(valid){
                    var req = {};
                    req.modelItemBoList = arr;
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href"), JSON.stringify(req), ctx+"/cms/model/item/list.php?modelId="+modelId+"&isChannel="+isChannel);
                }
            });

            //list 页面全选checkbox
            $(".js_sys_selectAll").click(function(){
                if($(this).attr("data-check")=='true'){
                    $(this).attr("data-check",false);
                    $.each($(".js_sys_checkbox"),function (){
                        this.checked=false;
                    });
                }else{
                    $(this).attr("data-check",true);
                    $.each($(".js_sys_checkbox"),function (){
                        this.checked="checked";
                    });
                }
            });

            //list页面 删去item 字段
            $(".jd_item_del").click(function(e){
                var modelId = $("[name='modelId']").val();
                var isChannel = $("[name='isChannel']").val();
                abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', ctx+"/cms/model/item/list.php?modelId="+modelId+"&isChannel="+isChannel);
            });
            //list批量删除
            $(".js_del_batch_btn").click(function(){
                var modelId = $("[name='modelId']").val();
                var isChannel = $("[name='isChannel']").val();
                var ids =[];
                $(".js_save_tr .js_checkbox:checked").each(function(i){
                    ids.push($(this).val());
                });
                if(ids.length==0){
                    abc.layerAlert(false,"请勾选要操作的字段");
                }else{
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href"), JSON.stringify(ids) ,ctx+"/cms/model/item/list.php?modelId="+modelId+"&isChannel="+isChannel);
                }
            });

            //item form页面 表单数据校验
            var $validatorSaveFrom = $("[name='item_form']").validator({
                ignore: ":hidden",
                theme: 'yellow_top',
                timely: 1,
                rules: {
                    //myRule:[/^(\d*:[^;]*)+(;\d*:.[^;]*)*$/, '不符合例子0:香蕉;1:苹果;2:橙子']
                    myRule:[/^([^;]*:[^;]*)+(;[^;]*:.[^;]*)*$/, '不符合例子0:香蕉;1:苹果;2:橙子']
                },
                fields: {
                    'field':"required,length[~50]",
                    'itemLabel': "required;length[~100]",
                    "priority":"required; integer; range[1~1000]",
                    'help':"length[~255]",
                    'helpPosition':{rule: "range[1~4];", msgClass: 'n-bottom'},
                    'optValue':{rule:"required,myRule,length[~255]", msgClass: 'n-bottom'},
                    'defValue':"length[~255]",
                    'textSize':"integer;range[0~1000000]",
                    'areaRows':"integer;range[0~999]",
                    'areaCols':"integer;range[0~999]",
                    'imageWidth':"integer,range[0~1000000]",
                    'imageHeight':"integer,range[0~1000000]"
                }
            });
            $validatorSaveFrom.validator().trigger("showtip");

        });
    })
});