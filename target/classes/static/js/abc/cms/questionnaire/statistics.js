/**
 * @Author liuqi
 * @Date 2017/7/5 15:58
 * 问卷调查统计
 */
require(["../../../config"], function () {
    require(["jquery-3.1.0","../abc/util/date","highlighter","barRenderer","categoryAxisRenderer","layui","abc.common"], function ($, abc_date, highlighter) {
        $(function () {
            $('.sidebar_item').on('click', function () {
                $(this).addClass('current').siblings('.current').removeClass('current');
                $(".js_sidebar_div").hide();  $("#"+$(this).attr("test")).show();
            });
            var option = function(title, ylabel,max){
                var op = {
                    title: title,  //标题
                    series:[{"color":"#FE3718","label":"total"},{"color":"#18FE6D","label":"PC"},{"color":"#FAF806","label":"MobileWeb"}],
                    seriesDefaults: {
                        label: '', //分类名称
                        showLine: true, //是否显示图表中的折线（折线图中的折线）
                        markerOptions: {
                            show:true
                        },
                        showMarker: true, //是否显示节点
                        pointLabels: {
                            show: true,//数据点标签
                        }
                    },
                    axes: {
                        xaxis: {
                            renderer: $.jqplot.CategoryAxisRenderer,
                            labelOptions: {
                                fontSize: '12pt'
                            }
                        },
                        yaxis: {
                            label: ylabel,
                            min: 0,
                            max:max
                        }
                    },
                    highlighter: {
                        show: true,
                        sizeAdjust: 7.5
                    },
                    legend:{        // 图例属性
                        show:true
                    },
                }
                return op;
            }
            var initAccessstatistics = function(){
                var max = 0;
                //问卷浏览量统计图
                var accessStatistics = [];
                $(".js_access_statistics").each(function(i, item){
                    if(parseInt($(this).attr("data-counts"))> parseInt(max) ){
                        max = $(this).attr("data-counts");
                    }
                    accessStatistics.push([$(this).attr("data-days"),$(this).attr("data-counts")]);
                });
                //accessStatistics.push([20170102,1]);accessStatistics.push([20170103,3]);accessStatistics.push([20170104,13]);accessStatistics.push([20170105,2]);accessStatistics.push([20170106,8]);
                var accessPcStatistics = [];
                $(".js_access_pc_statistics").each(function(i, item){
                    if(parseInt($(this).attr("data-counts"))> parseInt(max) ){
                        max = $(this).attr("data-counts");
                    }
                    accessPcStatistics.push([$(this).attr("data-days"),$(this).attr("data-counts")]);
                });
                //accessPcStatistics.push([20170102,3]);accessPcStatistics.push([20170103,13]);accessPcStatistics.push([20170104,3]);accessPcStatistics.push([20170105,12]);accessPcStatistics.push([20170106,4]);
                var accessMobileStatistics = [];
                $(".js_access_mobile_statistics").each(function(i, item){
                    if(parseInt($(this).attr("data-counts"))> parseInt(max) ){
                        max = $(this).attr("data-counts");
                    }
                    accessMobileStatistics.push([$(this).attr("data-days"),$(this).attr("data-counts")]);
                });
                //accessMobileStatistics.push([20170102,4]);accessMobileStatistics.push([20170103,23]);accessMobileStatistics.push([20170104,13]);accessMobileStatistics.push([20170105,2]);accessMobileStatistics.push([20170106,5]);
                if(accessStatistics.length==0 && accessPcStatistics.length==0 && accessMobileStatistics.length==0){
                    accessStatistics.push([0]); accessMobileStatistics.push([0]); accessPcStatistics.push([0]);
                }
                var m = parseInt(max.substr(0,1)) +parseInt(1);
                var len = max.length-1;
                var num = parseInt(m)*(Math.pow(10,len));
                $.jqplot('access_statistics', [accessStatistics, accessPcStatistics, accessMobileStatistics],option('问卷浏览量综合数：'+$("[name='accessCounts']").val(), '问卷浏览量',num));
            }
            var initAnswerStatistics = function () {
                var max = 0;
                //回收量统计图
                var answerStatistics = [];
                $(".js_answer_statistics").each(function(i, item){
                    if(parseInt($(this).attr("data-counts"))> parseInt(max) ){
                        max = $(this).attr("data-counts");
                    }
                    answerStatistics.push([$(this).attr("data-days"),$(this).attr("data-counts")]);
                });
                var answerPcStatistics = [];
                $(".js_answer_pc_statistics").each(function(i, item){
                    if(parseInt($(this).attr("data-counts"))> parseInt(max) ){
                        max = $(this).attr("data-counts");
                    }
                    answerPcStatistics.push([$(this).attr("data-days"),$(this).attr("data-counts")]);
                });
                var answerMobileStatistics = [];
                $(".js_answer_mobile_statistics").each(function(i, item){
                    if(parseInt($(this).attr("data-counts"))> parseInt(max) ){
                        max = $(this).attr("data-counts");
                    }
                    answerMobileStatistics.push([$(this).attr("data-days"),$(this).attr("data-counts")]);
                });
                if(answerStatistics.length==0 && answerPcStatistics.length==0 && answerMobileStatistics.length==0){
                    answerStatistics.push([0]); answerMobileStatistics.push([0]); answerPcStatistics.push([0]);
                }
                var m = parseInt(max.substr(0,1)) +parseInt(1);
                var len = max.length-1;
                var num = parseInt(m)*(Math.pow(10,len));
                $.jqplot('answer_statistics', [answerStatistics, answerPcStatistics, answerMobileStatistics],option('问卷回收量综合数：'+$("[name='answerCounts']").val(), '问卷回收量',num));
            }
            initAccessstatistics();
            initAnswerStatistics();
            function closeDiv()
            {
                document.getElementById('loading').style.visibility='hidden';
            }
            closeDiv();
            //点击查询时间按钮
            $(".js_btn_access_statistics_query").click(function(){
                var questionId = $("[name='questionId']").val()
                var st = $("#access_statistics_starttime").val();
                var et = $("#access_statistics_endtime").val();
                abc.block();
                $.ajax({
                    type: "GET",
                    url: ctx+"/cms/questionnaire/ajaxAccessStatistics/"+questionId+".php?startTime="+st+"&endTime="+et,
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        $.unblockUI();
                        if (data && data.success) {
                            var accessLogtjListBo = data.data;
                            if(accessLogtjListBo){
                                $(".js_access_statistics,.js_access_pc_statistics,.js_access_mobile_statistics").remove();
                                var list = accessLogtjListBo.list;
                                var pclist = accessLogtjListBo.pclist;
                                if(accessLogtjListBo.llcnt != null);{
                                    $("[name='accessCounts']").val(accessLogtjListBo.llcnt);
                                }
                                var mobileWeblist = accessLogtjListBo.mobileWeblist;
                                for(var i = 0; i<list.length; i++){
                                    var access = list[i];
                                    var input = '<input type="hidden" class="js_access_statistics" data-days="'+access.tj+'" data-counts="'+access.cnt+'">';
                                    $(".js_access_statistics_div").append(input);
                                }
                                for(var i = 0; i<pclist.length; i++){
                                    var access = pclist[i];
                                    var input = '<input type="hidden" class="js_access_pc_statistics" data-days="'+access.tj+'" data-counts="'+access.cnt+'">';
                                    $(".js_access_statistics_div").append(input);
                                }
                                for(var i = 0; i<mobileWeblist.length; i++){
                                    var access = mobileWeblist[i];
                                    var input = '<input type="hidden" class="js_access_mobile_statistics" data-days="'+access.tj+'" data-counts="'+access.cnt+'">';
                                    $(".js_access_statistics_div").append(input);
                                }
                                $("#access_statistics").empty();
                                initAccessstatistics();
                            }
                        }
                    }
                });
            });
            //点击查询时间按钮
            $(".js_btn_answer_statistics_query").click(function(){
                var questionId = $("[name='questionId']").val()
                var st = $("#answer_statistics_starttime").val();
                var et = $("#answer_statistics_endtime").val();
                abc.block();
                $.ajax({
                    type: "GET",
                    url: ctx+"/cms/questionnaire/ajaxAnswerStatistics/"+questionId+".php?startTime="+st+"&endTime="+et,
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        $.unblockUI();
                        if (data && data.success) {
                            var answerLogtjListBo = data.data;
                            if(answerLogtjListBo){
                                $(".js_answer_statistics,.js_answer_pc_statistics,.js_answer_mobile_statistics").remove();
                                var list = answerLogtjListBo.list;
                                var pclist = answerLogtjListBo.pclist;
                                var mobileWeblist = answerLogtjListBo.mobileWeblist;
                                if(answerLogtjListBo.llcnt != null){
                                    $("[name='answerCounts']").val(answerLogtjListBo.llcnt);
                                }
                                for(var i = 0; i<list.length; i++){
                                    var access = list[i];
                                    var input = '<input type="hidden" class="js_answer_statistics" data-days="'+access.tj+'" data-counts="'+access.cnt+'">';
                                    $(".js_answer_statistics_div").append(input);
                                }
                                for(var i = 0; i<pclist.length; i++){
                                    var access = pclist[i];
                                    var input = '<input type="hidden" class="js_answer_pc_statistics" data-days="'+access.tj+'" data-counts="'+access.cnt+'">';
                                    $(".js_answer_statistics_div").append(input);
                                }
                                for(var i = 0; i<mobileWeblist.length; i++){
                                    var access = mobileWeblist[i];
                                    var input = '<input type="hidden" class="js_answer_mobile_statistics" data-days="'+access.tj+'" data-counts="'+access.cnt+'">';
                                    $(".js_answer_statistics_div").append(input);
                                }
                                $("#answer_statistics").empty();
                                initAnswerStatistics();
                            }
                        }
                    }
                });

            });

//样本数据页面
            function htmlDecode( html ) {
                var a = document.createElement( 'a' ); a.innerHTML = html;
                return a.textContent;
            };

            var queryAnswerData = function(){
                var startTime = $("#answer_start_time").val();
                var endTime = $("#answer_end_time").val();
                var perPageNum = $(".js_change_perpagenum").val();
                var questionId = $("[name='questionId']").val()
                $.ajax({
                    type: "GET",
                    url: ctx+"/cms/questionnaire/statistics/answerList/"+questionId+".php?startTime="+startTime+"&endTime="+endTime+"&perPageNum="+perPageNum,
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        if (data && data.success) {
                            if(!data.bodyHtml){
                                data.bodyHtml = '';
                            }
                            $(".js-body-tr").html(htmlDecode(data.bodyHtml));
                            $(".js-page-tr").html(htmlDecode(data.pageHtml));
                        } else {}
                    }
                });
            }
            $(".js_btn_export_answer").click(function(){
                var startTime = $("#answer_start_time").val();
                var endTime = $("#answer_end_time").val();
                window.location.href = $(this).attr("data-href")+"?startTime="+startTime+"&endTime="+endTime;
            });
            $(".js_btn_query_answer").click(function(){queryAnswerData();});//根据时间查询
            $(".js_change_perpagenum").change(function(){queryAnswerData();});//页数切换时
            $(".js_del_answer").click(function(){
                //删除回答记录
                var questionId = $("[name='questionId']").val();
                //查询勾选的checkbox
                var ids = abc.getCheckBoxIds();
                if(ids){
                    var url = ctx+"/cms/questionnaire/statistics/delAnswer/"+questionId+".php?ids="+ids;
                    layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0},
                        function(){
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: '',
                                async: false,
                                contentType: "application/json",
                                dataType: "JSON",
                                success: function (data) {
                                    if (data && data.code == "2000") {
                                        queryAnswerData();
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    } else {
                                        abc.layerAlert(false, '操作失败: '+data.message);
                                    }
                                }
                            });
                        }
                    );
                }else{
                    abc.layerAlert(false,"请勾选复选框");
                }
            });

//统计图表
            $(".page_tab").click(function(){
                var pid = $(this).attr("data-pid");
                $(this).addClass("active").siblings().removeClass("active");
                $(".page_panels").find(".page_panel[data-pid='"+pid+"']").addClass("active").siblings().removeClass("active");
            });



            $("body").on("click",".js_page_location", function(){
                if($(this).attr("data-ajax") == "true"){
                    $.ajax({
                        type: 'GET',
                        url: $(this).attr("data-href"),
                        data: '',
                        async: false,
                        contentType: "application/json",
                        dataType: "JSON",
                        success: function (data) {
                            if (data && data.success) {
                                if(!data.bodyHtml){
                                    data.bodyHtml = '';
                                }
                                $(".js-body-tr").html(htmlDecode(data.bodyHtml));
                                $(".js-page-tr").html(htmlDecode(data.pageHtml));
                            } else {}
                        }
                    });
                }else{
                    location.href = $(this).attr("data-href");
                }
            });
            $("body").on("keypress","#go_page", function(e){
                if(e.keyCode==13){
                    $(".js_go_page").click();
                    return false;
                }
            });
            $("body").on("click",".js_go_page", function(e){
                if($(this).attr("data-ajax") == "true"){
                    var page = $('#go_page').val();
                    var url = $(this).attr("data-href");
                    if (page.match("^[1-9][0-9]*$")) {
                        $.ajax({
                            type: 'GET',
                            url: url.replace("[:page]", page),
                            data: '',
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data && data.success) {
                                    if(!data.bodyHtml){
                                        data.bodyHtml = '';
                                    }
                                    $(".js-body-tr").html(htmlDecode(data.bodyHtml));
                                    $(".js-page-tr").html(htmlDecode(data.pageHtml));
                                } else {}
                            }
                        });
                    } else {
                        return;
                    }
                }else{
                    location.href = $(this).attr("data-href");
                }
            });
        })
    })
})
