/**
 * Created by LiuQi
 */
require(["../../../../config"], function () {
    require(["jquery.full","abc.common","nicevalidator","nicevalidator.zh_CN","layui","../plugin/bootstrap-select"], function ($) {
        $(function () {

            //点击返回
            $('body').on('click', '.js_back', function(){
                window.location.href = $(".js_currLink").val();
            });

            //form表单
            $(".js_save_btn").click(function (e) {
                if($validatorFrom.isValid()){
                    var tagType = "";
                    var selectedTxtArr = $('#tagType').selectpicker('val');

                    for(var index in selectedTxtArr){
                        if(tagType){
                            tagType += ";";
                        }
                        tagType += selectedTxtArr[index];
                    }
                    var param = {};
                    param["id"]=$("input[name='id']").val();
                    param["status"]=$("input[name='status']").val();
                    param["tagType"]=tagType;
                    param["name"] = $.trim($("input[name='name']").val());
                    param["description"] = $.trim($("textarea[name='description']").val());
                    abc.layerAjaxConfirm("POST", $("form").attr('action'),  JSON.stringify(param), $(".js_currLink").val());
                }
            });

            //数据校验
            var $validatorFrom = $("form").validator({
                theme: 'yellow_top',
                timely: 1,
                fields: {
                    "name": "required;length[1~32];",
                    "description":"length[~256];",
                    "tagType":"required"
                }
            });
            $validatorFrom.validator().trigger("showtip");

            $('.selectpicker').selectpicker({
            });


        });

    });
});
