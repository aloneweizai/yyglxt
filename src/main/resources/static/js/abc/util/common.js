/**
 * Created by liuqi on 2017/6/1.
 */
;!function(window, $){
	
	/*$("a[href^='"+ctx+"']").click(function(){
		abc.block();
	});
	$("input[type='button'][value='查询']").click(function(){
		abc.block();
	});*/

    window.abc = {
    	
    	winscrollTop:function (offset){
    			return window.parent?($(window.parent.document).scrollTop()+offset)+'px':
    				($(document).scrollTop()+offset)+'px';
    	},	
    	
    	block:function(){
    		$.blockUI({ message: '<img src="'+ctx+'/images/loading1.gif" >',css: { top :abc.winscrollTop(300)},baseZ:100000000000000});
    	},
    	unblock:function(){
    		$.unblockUI();
    	},
    		
        layerAlert :function(success, msg, go_url){
            if(success){
                if(go_url){
                    layer.alert(msg, {offset: abc.winscrollTop(200),icon: 1,closeBtn: 0}, function(){
                        window.location.href = go_url;
                    });
                }else{
                    layer.alert(msg, {offset: abc.winscrollTop(200),icon: 1,closeBtn: 0});
                }
            }else{
                layer.alert(msg, {offset: abc.winscrollTop(200),icon: 5,closeBtn: 0});
            }
        },

        layerAjaxConfirm : function(req_type, req_url, req_json, back_url){
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                    function(){
            	        abc.block();
                        $.ajax({
                            type: req_type,
                            url: req_url,
                            data: req_json,
                            async: true,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                            	abc.unblock();
                                if (data && data.code == "2000") {
                                    if(back_url){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                            window.location.href = back_url;
                                        });
                                    }else{
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    }
                                } else {
                                    abc.layerAlert(false, '操作失败: '+data.message);
                                }
                            },
                            error: function (data) {
                                abc.unblock();
                                layer.msg("操作失败", {icon: 5,title: "", closeBtn:0, offset: abc.winscrollTop(200)});
                                //abc.layerAlert(false, data.message);
                            }
                        });
                    }
            );
        },
        
        //自定义显示内容ajax提交确认框
        ajaxConfirm: function(req_type, req_url, req_json, back_url,confirmMsg,okmsg,failmsg,sec){
        	var _sec=sec?sec:1000;
            layer.confirm(confirmMsg, {title:'操作提示',btn: ['确认','取消'], icon: 3,offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                    function(){
            	        abc.block();
                        $.ajax({
                            type: req_type,
                            url: req_url,
                            data: req_json,
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                            	abc.unblock();
                                if (data && data.code == "2000") {
                                	layer.msg(okmsg, {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:_sec},function(){
                                		abc.block();
                                		window.location.reload();
                                		//window.location.href = back_url;
                                		//goPage(parseInt($("#cupageVal").val()));
                                	});
                                } else {
                                    abc.layerAlert(false, failmsg+":"+data.message);
                                }
                            },
                            error: function (data) {
                                abc.unblock();
                                layer.msg("操作失败", {icon: 5,title: "", closeBtn:0, offset: abc.winscrollTop(200)});
                                //abc.layerAlert(false, data.message);
                            }
                        });
                    }
            );
        },
        

        goPage : function(){
            var page = $('#_goPs').val();
            var url = $('#_goPage').attr("data-url");
            if (page.match("^[1-9][0-9]*$")) {
                window.location.href = url.replace("[:page]", page);
            } else {
                return;
            }
        },

        getCheckBoxIds : function(){
            var ids;
            $(".js_checkbox:checked").each(function(i){
                if(0==i){
                    ids = $(this).val();
                }else{
                    ids += (","+$(this).val());
                }
            });
            return ids;
        },


        ajaxPage: function(url){
            $.ajax({
                type: 'GET',
                url: url,
                data: '',
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    $(".js-body-tr").empty().append(data.bodyHtml);
                    $(".js-page-tr").empty().append(data.pageHtml);
                    if($(".js_currLink").length > 0){
                        $(".js_currLink").val(data.currLink);
                    }
                }
            });
        },

        ajaxGoPage: function(){
            var page = $('#_goPs').val();
            var url = $('#_goPage').attr("data-url");
            if (page.match("^[1-9][0-9]*$")) {
                $.ajax({
                    type: 'GET',
                    url: url.replace("[:page]", page),
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        $(".js-body-tr").empty().append(data.bodyHtml);
                        $(".js-page-tr").empty().append(data.pageHtml);
                        if($(".js_currLink").length > 0){
                            $(".js_currLink").val(data.currLink);
                        }
                    }
                });
            } else {
                return;
            }
        },
       getCurrentWeek: function(){
            //起止日期数组
            var startStop=new Array();
            //获取当前时间
            var currentDate= new Date();
            //返回date是一周中的某一天
            var week=currentDate.getDay();
            //一天的毫秒数
            var millisecond=1000*60*60*24;
            //减去的天数
            var minusDay=week!=0?week-1:6;
            //alert(minusDay);
            //本周 周一
            var monday=new Date(currentDate.getTime()-(minusDay*millisecond));
            //本周 周日
            var sunday=new Date(monday.getTime()+(6*millisecond));
            //添加本周时间
            startStop.push(abc.format(monday));//本周起始时间
            //添加本周最后一天时间
            startStop.push(abc.format(sunday));//本周终止时间
            //返回
            return startStop;
        },

    //格式化时间
        format :function (date){
        // 拼接时间
        var time1 = date;
        var Y1 = time1.getFullYear()
        var M1 = ((time1.getMonth() + 1) > 10 ? (time1.getMonth() + 1) : '0' + (time1.getMonth() + 1))
        var D1 = (time1.getDate() >= 10 ? time1.getDate() : '0' + time1.getDate())
        var timer1 = Y1 + '-' + M1 + '-' + D1 //
        return timer1;
    },

    /***
     * 获得本月的起止时间
     */
     getCurrentMonth:function(){
        //起止日期数组
        var startStop=new Array();
        //获取当前时间
        var currentDate=new Date();
        //获得当前月份0-11
        var currentMonth=currentDate.getMonth();
        //获得当前年份4位年
        var currentYear=currentDate.getFullYear();
        //求出本月第一天
        var firstDay=new Date(currentYear,currentMonth,1);


        //当为12月的时候年份需要加1
        //月份需要更新为0 也就是下一年的第一个月
        if(currentMonth==11){
            currentYear++;
            currentMonth=0;//就为
        }else{
            //否则只是月份增加,以便求的下一月的第一天
            currentMonth++;
        }


        //一天的毫秒数
        var millisecond=1000*60*60*24;
        //下月的第一天
        var nextMonthDayOne=new Date(currentYear,currentMonth,1);
        //求出上月的最后一天
        var lastDay=new Date(nextMonthDayOne.getTime()-millisecond);

        //添加至数组中返回
        startStop.push(abc.format(firstDay));
        startStop.push(abc.format(lastDay));
        //返回
        return startStop;
    },

    /***
     * 得到本年的起止日期
     *
     */
     getCurrentYear:function(){
        //起止日期数组
        var startStop=new Array();
        //获取当前时间
        var currentDate=new Date();
        //获得当前年份4位年
        var currentYear=currentDate.getFullYear();

        //本年第一天
        var currentYearFirstDate=new Date(currentYear,0,1);
        //本年最后一天
        var currentYearLastDate=new Date(currentYear,11,31);
        //添加至数组
        startStop.push(abc.format(currentYearFirstDate));
        startStop.push(abc.format(currentYearLastDate));
        //返回
        return startStop;
    },

    /**
     * 获得上一月的起止日期
     * ***/
     getPreviousMonth:function(){
        //起止日期数组
        var startStop=new Array();
        //获取当前时间
        var currentDate= new Date();
        //获得当前月份0-11
        var currentMonth=currentDate.getMonth();
        //获得当前年份4位年
        var currentYear=currentDate.getFullYear();
        //获得上一个月的第一天
        var priorMonthFirstDay=abc.getPriorMonthFirstDay(currentYear,currentMonth);
        //获得上一月的最后一天
        var priorMonthLastDay=new Date(priorMonthFirstDay.getFullYear(),priorMonthFirstDay.getMonth(),abc.getMonthDays(priorMonthFirstDay.getFullYear(), priorMonthFirstDay.getMonth()));
        //添加至数组
        startStop.push(abc.format(priorMonthFirstDay));
        startStop.push(abc.format(priorMonthLastDay));
        //返回
        return startStop;
    },
    /**
     * 获得该月的天数
     * @param year年份
     * @param month月份
     * */
     getMonthDays:function(year,month){
        //本月第一天 1-31
        var relativeDate=new Date(year,month,1);
        //获得当前月份0-11
        var relativeMonth=relativeDate.getMonth();
        //获得当前年份4位年
        var relativeYear=relativeDate.getFullYear();

        //当为12月的时候年份需要加1
        //月份需要更新为0 也就是下一年的第一个月
        if(relativeMonth==11){
            relativeYear++;
            relativeMonth=0;
        }else{
            //否则只是月份增加,以便求的下一月的第一天
            relativeMonth++;
        }
        //一天的毫秒数
        var millisecond=1000*60*60*24;
        //下月的第一天
        var nextMonthDayOne=new Date(relativeYear,relativeMonth,1);
        //返回得到上月的最后一天,也就是本月总天数
        return new Date(nextMonthDayOne.getTime()-millisecond).getDate();
    },

    /**
     * 返回上一个月的第一天Date类型
     * @param year 年
     * @param month 月
     **/
     getPriorMonthFirstDay:function(year,month){
        //年份为0代表,是本年的第一月,所以不能减
        if(month==0){
            month=11;//月份为上年的最后月份
            year--;//年份减1
            return new Date(year,month,1);
        }
        //否则,只减去月份
        month--;
        return new Date(year,month,1);;
    },
    /**
     * 获得上一周的起止日期
     * **/
     getPreviousWeek:function(){
        //起止日期数组
        var startStop=new Array();
        //获取当前时间
        var currentDate= new Date();
        //返回date是一周中的某一天
        var week=currentDate.getDay();
        //返回date是一个月中的某一天
        var month=currentDate.getDate();
        //一天的毫秒数
        var millisecond=1000*60*60*24;
        //减去的天数
        var minusDay=week!=0?week-1:6;
        //获得当前周的第一天
        var currentWeekDayOne=new Date(currentDate.getTime()-(millisecond*minusDay));
        //上周最后一天即本周开始的前一天
        var priorWeekLastDay=new Date(currentWeekDayOne.getTime()-millisecond);
        //上周的第一天
        var priorWeekFirstDay=new Date(priorWeekLastDay.getTime()-(millisecond*6));

        //添加至数组
        startStop.push(abc.format(priorWeekFirstDay));
        startStop.push(abc.format(priorWeekLastDay));

        return startStop;
    },
        /**
         * 0-1之间的最多两位小数
         * **/
        checkzerotoOne:function(val){
            return ((/^0(\.[\d]{1,2})$/i.test(val)));
        }
        ,
        /**
         * //金额设置 (8位整数加两位小数）/^\d{1,14}$|^\d{1,14}\.\d{1,2}$/
         * **/
        checkje:function(val){
            return ((/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/i.test(val)));
        }
        ,
        /**
         * //金额设置 (金额应为14位数字两位小数
         * **/
        checkje14:function(val){
            return ((/^\d{1,14}$|^\d{1,14}\.\d{1,2}$/i.test(val)));
        }
        ,
        /**
         * 非负整数 (非负整数）
         * **/
        check_ffzs:function(val){
            return ((/^\d+$/i.test(val)));
        }
        ,
        /**
         * 正整数
         * **/
        check_zzs:function(val){
            return ((/^[1-9]\d*$/i.test(val)));
        }
        ,
        /**
         * 网址格式
         * **/
        check_url:function(val){
            return ((/^(https?|ftp):\/\/[^\s]+$/i.test(val)));
        }
    };

    //全选checkbox
    $("body").on("click",".js_selectAll",function(){
        if($(this).attr("data-check")=='true'){
            $(this).attr("data-check",false);
            $.each($(".js_checkbox"),function (){
                this.checked=false;
                $(this).next().removeClass("layui-form-checked");
            });
        }else{
            $(this).attr("data-check",true);
            $.each($(".js_checkbox"),function (){
                this.checked="checked";
                $(this).next().addClass("layui-form-checked");
            });
        }
    });

}(window, jQuery);