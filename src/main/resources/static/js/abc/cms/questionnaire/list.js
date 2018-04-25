require(["../../../config"], function () {
    require(["jquery-3.1.0","qrcode","abc.common","layui"], function ($, qr_code) {
        $(function () {
            //复制,回收
            $(".js_copy_question,.js_recycle_question").click(function(){
                abc.layerAjaxConfirm("POST", $(this).attr("data-href"), '',$(".js_currLink").val());
            });
            //删除
            $(".js_del_question").click(function(){
                abc.layerAjaxConfirm("DELETE", $(this).attr("data-href"), '',$(".js_currLink").val());
            });

            $(".js_edit").click(function(){
                location.href = $(this).attr("data-href")+"?currLink="+encodeURIComponent($(".js_currLink").val());
            });

            ///*分享*/
            //$(".js_share_link").click(function() {
            //    $("#share_div").show();
            //    $("#share_link").val($(this).attr("data-href"));
            //});
            //$(".ui-dialog-close, .ui-dialog-autofocus").click(function() {
            //    $("#share_div").hide();
            //});
            //$('.copy_link').on('click',function (){
            //    $("#share_link").select(); // 选择对象
            //    document.execCommand("Copy"); // 执行浏览器复制命令
            //    layer.alert("已复制好，可贴粘。", {icon: 1,closeBtn: 0});
            //});
            //var qrcode = new qr_code($("#qrcode").get(0), {
            //    width : 120,//设置宽高
            //    height : 120
            //});
            //qrcode.makeCode("http://www.baidu.com");
        });

    })
});