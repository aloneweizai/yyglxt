require(["../../config"], function () {
    require(["jquery-3.1.0","jquery.easyui.min","abc.common","layui","../abc/util/date"], function ($) {

        var queryList = function(){
            var nsrsbh = $.trim($("[name='nsrsbh']").val());//
            var name = $.trim($("[name='name']").val());//
            var phone = $.trim($("[name='phone']").val());//
            var date = $.trim($("[name='date']").val());//
            location.href = ctx+'/consumerManager/customerServiceOrder/list.php?nsrsbh='+nsrsbh+"&name="+name+'&phone='+phone+'&date='+date;
        };
        $('body').on('click', '.js-query', function(){queryList();});

    })
});
