require(["../../../config"], function () {
    require(["jquery.full","../lib/template","highlighter.full","jqplot","barRenderer","categoryAxisRenderer","dateAxisRenderer","canvasAxisLabelRenderer"], function ($,template) {

        //错误提示函数
        var errorMsg = function (content) {
            layer.open({
                time: 3000,
                btn : null,
                title: false,
                closeBtn: 0,
                content: content,
                icon: 5,
                anim: 6
            });
        }

        //请求统计数据函数
        var requestData = function (reqParam) {

            $.ajax({
                url : ctx+"/cms/vote/summaryVote.php",
                type: "post",
                data: reqParam,
                success : function(data){

                    if(data.code=="2000") {
                        packAndRenderData(data,reqParam.statisticsScope);
                    }else{

                    }

                },
                error : function(err){
                    console.log(err)
                }
            });
        };

        //组装图表数据并渲染统计图方法
        var packAndRenderData = function (respData,type) {

            //投票综合统计数据拼凑
            var dataArray = [];
            var fieldNames = [];
            if (respData.mobileLine && respData.mobileLine.length > 0) {
                dataArray.push(respData.mobileLine);
                fieldNames.push("手机浏览次数");
            } else {

            }
            if (respData.pcLine && respData.pcLine.length > 0) {
                dataArray.push(respData.pcLine);
                fieldNames.push("PC浏览次数");
            }
            if (respData.sumVoteLine && respData.sumVoteLine.length > 0) {
                dataArray.push(respData.sumVoteLine);
                fieldNames.push("投票次数");
            }

            var foratStr,tickInter;
            if("day"==type){
                foratStr = '%#d日';
                tickInter = "1 day";
            }else if("month"==type){
                foratStr = '%m月';
                tickInter = "month";
            }else{
                foratStr = '%Y年';
                tickInter = "year";
            }

            $("#container_line_tptongji").html("");
            $.jqplot('container_line_tptongji', dataArray, {
                animate: !$.jqplot.use_excanvas,
                title: '<div><span style="color:#333;font-size:18px;">浏览总人数:' + respData.totalView + '</span></div><div><span style="color:#666;font-size:12px;">参与投票人数:' + respData.totalVote + '</span></div>',
                axes: {
                    xaxis: {
                        renderer: $.jqplot.DateAxisRenderer,
                        tickOptions: {
                            formatString: foratStr
                        },
                        tickInterval: tickInter
                    },
                    yaxis: {
                        label: '投票综合统计表',
                        labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                        labelOptions: {
                            fontSize: '12pt'
                        }
                    }
                },
                legend: {
                    show: true,
                    location: 'e',
                    renderer: $.jqplot.TableLegendRenderer,
                    rendererOptions: {
                        labels: fieldNames
                    }
                },
                series: [
                    {
                        lineWidth: 1
                    }
                ]
            });

            //投票详细信息数据拼凑
            var detailData = [];
            var detailFields = [];
            for (var i in respData.voteDetail) {
                detailFields.push(respData.voteDetail[i].tj);
                detailData.push(respData.voteDetail[i].cnt);
            }

            $("#container_col_tptongji").html("");
            $.jqplot('container_col_tptongji', [detailData], {
                // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
                animate: !$.jqplot.use_excanvas,
                title: "投票详细统计",
                seriesDefaults: {
                    renderer: $.jqplot.BarRenderer,
                    pointLabels: {show: true},
                    rendererOptions: {
                        barWidth: 60
                    }
                },
                axes: {
                    xaxis: {
                        renderer: $.jqplot.CategoryAxisRenderer,
                        ticks: detailFields
                    }
                },
                legend: {
                    show: true,
                    location: 'nw',
                    renderer: $.jqplot.TableLegendRenderer,
                    rendererOptions: {
                        labels: ["投票次数"]
                    }
                }
            });

            //投票项投票量表
            $("#vote_item_list_title").html(template("vote_item_list_tmpl", {
                list: respData.voteItemList,
                totalView: respData.totalView,
                totalVote: respData.totalVote
            }));

            $('.skillbar').each(function () {
                $(this).find('.skillbar-bar').animate({
                    width: $(this).attr('data-percent')
                }, 3000);
            });
        };

        //事件函数汇总
        var eventFunctions = function () {

            //结果与日志按钮事件
            $(document).on("click","#vote-result",function () {
                var voteId = $(this).data("voteid");
                location.href = ctx + "/cms/vote/statistics.php?voteId=" + voteId;
            });

            //投票地址按钮事件
            $(document).on("click","#vote-url",function () {
                var voteId = $(this).data("voteid");
                location.href = ctx + "/cms/vote/view.php?voteId=" + voteId;
            });

            //返回按钮事件
            $(document).on("click","#vote-index",function () {
                location.href = ctx + "/cms/vote/list.php";
            });

            //侧边栏按钮事件
            $('.sidebar_item').on('click', function () {
                $(this).addClass('current').siblings('.current').removeClass('current');
                var test = "#" + $(this).attr("test");
                var weui_tab__panel = $('.sidebar_item').length;
                for (var i = 1; i <= weui_tab__panel; i++) {
                    if ("#n" + i == test) {
                        $("#n" + i).css("display", "block")
                    } else {
                        $("#n" + i).css("display", "none")
                    }
                }
            });

            //统计查询按钮事件
            layui.form.on("submit(do-statistics-form)",function (formData) {

                var formField = formData.field;
                var start = new Date(formField.startDate + " 00:00:00");
                var end = new Date(formField.endDate + " 23:59:59");

                if(start.getTime()>=end.getTime()){
                    errorMsg("终止日期不能早于起始日期");
                    return false;
                }


                if(Math.floor((end.getTime()/24/60/60/1000-start.getTime()/24/60/60/1000))>31){
                    errorMsg("日期间隔不得多于31天");
                    return false;
                }

                requestData(formField);
                return false;
            });
        };

        ~function(){

            var laydate = layui.laydate;
            laydate.render({
                elem: '#startDate'
            });
            laydate.render({
                elem: '#endDate'
            });
            $.jqplot.config.enablePlugins = true;
            // requestData();
            eventFunctions();

            $("#search-btn").trigger("click");
        }();

    });
});