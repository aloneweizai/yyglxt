/**
 * Created by liuqi on 2017/5/24.
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","abc.common"], function ($) {
        $(function () {
            //list页面 修改按钮
            $('body').on('click', '.js_edit', function(){
                location.href = $(this).attr("data-href")+"?currLink="+encodeURIComponent($(".js_currLink").val());
            });

            //list页面 禁用开启
            $('body').on('click', '.js_enable', function(){
                abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '', $(".js_currLink").val());
            });

            //list页面 删除
            $('body').on('click', '.js_delete', function(){
                abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '', $(".js_currLink").val());
            });
        });
    })
});