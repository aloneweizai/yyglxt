require(["../../../config"], function () {
    require(["jquery.full", "highlighter.full","barRenderer","categoryAxisRenderer"], function ($,Highcharts) {



        $("input[data-type='date']").datebox({
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
        var ticks;
        var serie;







        $(function () {
            var id=$('#id').val();
            $.ajax({
                type: "GET",
                url: ctx+"/cms/activity/bmtj/"+id,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    var option1= {
                        seriesDefaults: {
                            renderer: $.jqplot.BarRenderer, //使用柱状图表示
                            rendererOptions: {
                                barMargin: 30   //柱状体组之间间隔
                            }
                        },
                        axes: {
                            xaxis: {
                                ticks:eval(data.datas),
                                renderer: $.jqplot.CategoryAxisRenderer //x轴绘制方式
                            }
                        }
                    };
                    $.jqplot('tjday1', [eval(data.data)], option1);
                },
                error: function () {
                }
            });
        })

        $('#editor_confirm_btn').on('click',function (){
            var type=$('#type').val();
            var endtime=$('#endtime').val();
            var begintime=$('#begintime').val();
            if(type=="1"){
                var begintime1 = new Date(begintime.replace(/-/g, "/"));
                var endtime1 = new Date(endtime.replace(/-/g, "/"));
                var days = endtime1.getTime() - begintime1.getTime();
                var time = parseInt(days / (1000 * 60 * 60 * 24));
                if(time>31){
                    layer.msg("根据日查询不能超过31天",{icon:2});
                    return ;
                }
            }else if(type=="2"){
                // 拆分年月日
                var date1 = endtime.split('-');
                // 得到月数
                date1 = parseInt(date1[0]) * 12 + parseInt(date1[1]);
                // 拆分年月日
                var date2 = begintime.split('-');
                // 得到月数
                date2 = parseInt(date2[0]) * 12 + parseInt(date2[1]);
                var m = Math.abs(date1 - date2);
                if(m>24){
                    layer.msg("根据月查询不能超过24个月",{icon:2});
                    return ;
                }
            }


            var id=$(this).attr('abc-id');
            $.ajax({
                type: "GET",
                url: ctx+"/cms/activity/lltj.php?startTime="+begintime+"&endTime="+endtime+"&enventId="+id+"&type="+type,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    $('#tjday').html("");
                    ticks=eval(data.listtitle);
                    serie=eval(data.listtitles);
                    var title=data.listtitle.split(",");
                    var option={
                        title: '活动浏览量',  //标题
                        legend: { show: true, location: 'ne' }, //提示工具栏--show：是否显示,location: 显示位置 (e:东,w:西,s:南,n:北,nw:西北,ne:东北,sw:西南,se:东南)
                        //series: [{showMarker:true}],
                        axesDefaults: { //轴的刻度值，字体大小，字体类型，字体角度
                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                            //labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                            tickOptions: {
                                //fontFamily: 'Georgia',
                                angle: 40
                                //fontSize: '10pt'
                            }
                        },
                        axes: {
                            xaxis: {
                                label: '日期',
                                renderer: $.jqplot.CategoryAxisRenderer,
                                // readerer:$.jqplot.DateAxisRenderer,

                                // tickInterval: 'lday',
                                labelOptions: {
                                    //formatString:'%Y-%m-%d',
                                    fontSize: '12pt',
                                    angle:40
                                }
                            },
                            yaxis: {
                                label: '浏览数量',
                                //autoscale: true,
                                min: 0,
                                tickOptions: { formatString: '%.2f', fontSize: '10pt' }
                            }
                        },
                        series:eval(data.listtitles)
                    };
                    $.jqplot('tjday', [eval(data.title0),eval(data.title1),eval(data.title2),eval(data.title3)], option);
                },
                error: function () {
                }
            });
        })

        $('#publish_survey').click(function (){
            //window.location.href = ctx+"/cms/activity/list.php";
            // window.location.href = document.referrer;
            window.location.href =sessionStorage.getItem("activity_url");
        });



    });
});