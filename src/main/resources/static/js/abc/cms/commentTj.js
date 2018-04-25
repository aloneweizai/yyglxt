/**
 * Created by Administrator on 2017-06-11.
 * 评论统计
 */
require(["../../config"], function () {
    require(["jquery.full", "highlighter.full"], function ($) {
        $('.tj_statis_n>div').eq(0).show();
        $('.statistics_t ul li').click(function () {
            $(".statistics_tb ul li").eq(0).addClass('hover').siblings().removeClass('hover');
            $(".tj_statis_n >div").hide();
            var i = $(this).index();
            $('.statistics_t ul li').eq(i).addClass('hover').siblings().removeClass('hover');
            $('.tj_statis_w > div').eq(i).addClass('hover').siblings().removeClass('hover');

            $(".tj_statis_w .hover>div").eq(0).show();


        });
        $(".statistics_tb ul li").click(function () {
            var i = $(this).index();
            $(this).addClass('hover').siblings().removeClass('hover');
            $(".tj_statis_w .hover>div").eq(i).show().siblings().hide();
        });
        var option = {
            grid: {
                drawBorder: false,
                shadow: false,
                //The background color of the whole chart.
                background: '#FFFFFF'
            },
            highlighter: {show: true},
            seriesDefaults: {
                shadowAlpha: 0.1,
                shadowDepth: 2,
                fillToZero: true
            },
            series: [
                {
                    color: 'red',
                    showMarker: true,
                    showLine: true,
                    markerOptions: {
                        style: 'filledCircle',
                        size: 8
                    },
                    rendererOptions: {
                        smooth: true
                    }
                },
                {
                    color: 'yellow',
                    showMarker: true,
                    showLine: true,
                    rendererOptions: {
                        smooth: true
                    },
                    markerOptions: {
                        style: 'filledSquare',
                        size: 8
                    }
                }
            ],
            axes: {
                xaxis: {
                    pad: 1.0,
                    tickOptions: {
                        showGridline: false
                    }
                },
                yaxis: {
                    pad: 1.05
                }
            }
        };
        $(function () {
            $.ajax({
                type: "GET",
                url: ctx+"/cms/comment/getTjData.php",
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    $("#days").html(data.days);
                    $("#weeks").html(data.weeks);
                    $("#months").html(data.months);
                    $("#years").html(data.years);
                    $("#cnts").html(data.cnts);
                    $.jqplot('tjday', [eval(data.tjday)], option);
                    $.jqplot('tjday0', [eval(data.tjday1)], option);
                    $.jqplot('tjday1', [eval(data.tjday0)], option);
                    $.jqplot('tjmonth', [eval(data.tjmonth)], option);
                    $.jqplot('tjmonth0', [eval(data.tjmonth1)], option);
                    $.jqplot('tjmonth1', [eval(data.tjmonth0)], option);
                    $.jqplot('tjyear', [eval(data.tjyear)], option);
                    $.jqplot('tjyear0', [eval(data.tjyear1)], option);
                    $.jqplot('tjyear1', [eval(data.tjyear0)], option);
                },
                error: function () {
                }
            });
        })
    })
})