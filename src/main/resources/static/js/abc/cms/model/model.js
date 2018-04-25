/**
 * Created by liuqi on 2017/5/22.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","nicevalidator","nicevalidator.zh_CN","abc.common","layui"], function ($) {

        $(function () {
            //form表单提交弹出框
            $(".js_save_btn").click(function (e) {
                if($validatorModelFrom.isValid()){
                    abc.layerAjaxConfirm("POST", $("form").attr('action'), $("form").serializeJson(), ctx+"/cms/model/list.php");
                }
            });

            //删去 按钮 弹出对话框
            $(".js_del_btn").click(function(){
                abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '',ctx+"/cms/model/list.php");
            });

            //批量删除
            $(".js_del_batch_btn").click(function(){
                var ids = abc.getCheckBoxIds();
                if(ids){
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href"),JSON.stringify(ids.split(",")) ,ctx+"/cms/model/list.php");
                }else{
                    abc.layerAlert(false,"请勾选复选框");
                }
            });

            //list页面 禁用开启
            $('body').on('click', '.js_enable', function() {
                abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', ctx+"/cms/model/list.php");
            });

            //点击 保存排列顺序（保存 顺序，默认，启用）
            $(".js_save_list_btn").click(function(){
                //遍历列表  获取  id，排列顺序，默认，启用
                var arr = [],reg = /^([1-9]\d{0,2}|1000)$/,valid=true;
                $(".js_model_list tr").each(function(i){
                    var tr = $(this);
                    //if(tr.find(".js_checkbox")[0].checked){
                        var obj = {};
                        var modelId = tr.find("[name='modelId']").val();
                        var priority =tr.find("[name='priority']").val();
                        if(!reg.test(priority)){
                            abc.layerAlert(false, tr.find("#modelName_td").text()+"模型的排列顺序范围需在[1-1000]");
                            valid = false;
                            return false;
                        }
                        obj.modelId = modelId;
                        obj.priority = Number(priority);
                        //obj.isDef = Number(tr.find("[name='isDef']")[0].checked);
                        //obj.isDisabled = Number(!(tr.find("[name='isDisabled']")[0].checked));
                        arr.push(obj);
                    //}
                });
                if(valid){
                    abc.layerAjaxConfirm("POST", $(this).attr("data-href"),JSON.stringify(arr),ctx+"/cms/model/list.php");
                }
            });

            //form页面 表单数据校验
            var $validatorModelFrom = $("#model_form").validator({
                theme: 'yellow_top',
                timely: 1,
                fields: {
                    'modelName': "required;length[1~32]",
                    "titleImgWidth":"required; integer; range[1~100000]",
                    "titleImgHeight":"required; integer; range[1~100000]",
                    "contentImgWidth":"required; integer; range[1~100000]",
                    "contentImgHeight":"required; integer; range[1~100000]",
                    "priority":"required; integer; range[1~1000]",
                }
            });
            $validatorModelFrom.validator().trigger("showtip");
        });
    })
});