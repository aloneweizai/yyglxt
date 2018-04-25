/**
 * Created by LiuQi
 */
define(['jquery.easyui.min'],function(){
    var init = function(){
        if($("input[data-type='datetimebox']").length>0){
            $("input[data-type='datetimebox']").datetimebox({
                spinnerWidth: 172,
                editable: false,
                formatter:function(date){
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    var h = date.getHours();
                    var min = date.getMinutes();
                    var sec = date.getSeconds();
                    return  y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec);
                },
                parser: function (s) {
                    if (!s) return new Date();
                    var y = s.substring(0,4);
                    var m =s.substring(5,7);
                    var d = s.substring(8,10);
                    var h = s.substring(11,13);
                    var min = s.substring(14,16);
                    var sec = s.substring(17,19);
                    if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)){
                        return new Date(y,m-1,d,h,min,sec);
                    } else {
                        return new Date();
                    }
                }
            });
        }

        if($("input[data-type='datebox']").length>0){
            $("input[data-type='datebox']").datebox({
                editable: false,
                formatter:function(date){
                    var y = date.getFullYear();
                    var m = date.getMonth()+1;
                    var d = date.getDate();
                    var h = date.getHours();
                    var M = date.getMinutes();
                    var s = date.getSeconds();
                    function formatNumber(value){
                        return (value < 10 ? '0' : '') + value;
                    }
                    return y+'-'+formatNumber(m)+'-'+formatNumber(d);
                },
                parser:function(s){
                    var t = Date.parse(s);
                    if (!isNaN(t)){
                        return new Date(t);
                    } else {
                        return new Date();
                    }
                }
            });
        };

    };


    var format = function(date,fmt){
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

    var getDate = function(s){
        if (!s) return new Date();
        var y = s.substring(0,4);
        var m =s.substring(5,7);
        var d = s.substring(8,10);
        var h = s.substring(11,13);
        var min = s.substring(14,16);
        var sec = s.substring(17,19);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)){
            return new Date(y,m-1,d,h,min,sec);
        } else {
            return new Date();
        }
    };

    init();

    return{
        format: format,
        getDate: getDate,
        init : init
    }
})