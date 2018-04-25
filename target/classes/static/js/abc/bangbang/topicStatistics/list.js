/**
 * Created by LiuQi
 */

//问答内容审核
require(["../../../config"], function () {
    require(["jquery-3.1.0",  "layui"], function ($) {
            $(function () {
                var init_layUi = function(){
                    layui.use('form', function(){
                        var form = layui.form;
                        form.render();
                        var laydate = layui.laydate;
                        lay('.date-item').each(function () {
                            laydate.render({
                                elem: this
                            })
                        });
                    });
                };
                init_layUi();


                /*查询按钮*/
                $(".js_query").click(function () {

                    var parentCode = $(".js_select_classify.on").attr("data-code");
                    var url = ctx + "/bangbang/topicStatistics/ajaxList.php?parentCode=" + parentCode;
                    var beginTime = $("#beginTime").val();
                    var endTime = $("#endTime").val();
                    if (beginTime) {
                        url = url + "&beginTime=" + beginTime.replace(/-/g, "");
                    }
                    if (endTime) {
                        url = url + "&endTime=" + endTime.replace(/-/g, "");
                    }
                    $.ajax({
                        url: url,
                        type: "GET",
                        async: false,
                        success: function (data) {
                            $(".analysis tbody").empty();
                            var list = data.dataList;
                            $.each(list, function (k, v) {
                                var tr = '<tr class="js_statistics_tr"><td>' + v.classifyName + '</td><td>' + v.answerNum + '</td><td>' +
                                    v.commentNum + '</td><td>' + v.likeNum + '</td><td>' + v.questionNum + '</td><td></tr>';
                                $(".analysis tbody").append(tr);
                            });
                            initHighcharts();
                        }
                    });
                });

                //话题统计分析单选
                $(document).on('click', '#statistics-box .radio-title span', function () {
                    var img = $(this).siblings().find('img').remove();
                    $(this).append(img).children(":first").addClass('on').parents().siblings().children().removeClass('on');
                });

                var initHighcharts = function () {
                    var categories = [], answers = [], likes = [], comments = [], questions = [];
                    $(".js_statistics_tr").each(function () {
                        categories.push($(this).find("td:eq(0)").html());
                        answers.push(parseInt($(this).find("td:eq(1)").html()));
                        comments.push(parseInt($(this).find("td:eq(2)").html()));
                        likes.push(parseInt($(this).find("td:eq(3)").html()));
                        questions.push(parseInt($(this).find("td:eq(4)").html()));
                    });
                    if ($("div").hasClass("container_col")) {
                        Highcharts.chart('container_col', {
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: ''
                            },
                            subtitle: {
                                text: ''
                            },
                            xAxis: {
                                categories: categories,
                                crosshair: true
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '数量'
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:1f}</b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            },
                            series: [{
                                name: '回答数',
                                data: answers
                            }, {
                                name: '评论数',
                                data: comments
                            }, {
                                name: '点赞数',
                                data: likes
                            }, {
                                name: '提问数',
                                data: questions
                            }]
                        });
                    }
                };
                //条形图
                initHighcharts();
            });


        })
    //})
});