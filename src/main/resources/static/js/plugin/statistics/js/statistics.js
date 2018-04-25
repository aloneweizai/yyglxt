
$(function(){
//日统计-全部评论
if($("div").hasClass("container_line11")){
Highcharts.chart('container_line11', {

    title: {
        text: '日统计-全部评论'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
 enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
 
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }]

});

}

//日统计-已回复

if($("div").hasClass("container_line12")){
Highcharts.chart('container_line12', {

    title: {
        text: '总浏览数'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '投票统计表'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
         enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: { 
    },
    
    xAxis: {
   		categories: ['10-10', '10-11', '10-12', '10-13', '10-14', '10-15', '10-16', '10-17']
	},


    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

//日统计-未回复

if($("div").hasClass("container_line13")){
Highcharts.chart('container_line13', {

    title: {
        text: '日统计-未回复'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}


//月统计-全部评论
if($("div").hasClass("container_line21")){
Highcharts.chart('container_line21', {

    title: {
        text: '月统计-全部评论'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

//月统计-已回复

if($("div").hasClass("container_line22")){
Highcharts.chart('container_line22', {

    title: {
        text: '月统计-已回复'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

//月统计-未回复

if($("div").hasClass("container_line23")){
Highcharts.chart('container_line23', {

    title: {
        text: '月统计-未回复'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

//年统计-全部评论
if($("div").hasClass("container_line31")){
Highcharts.chart('container_line31', {

    title: {
        text: '年统计-全部评论'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

//年统计-已回复

if($("div").hasClass("container_line32")){
Highcharts.chart('container_line32', {

    title: {
        text: '年统计-已回复'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

//年统计-未回复

if($("div").hasClass("container_line33")){
Highcharts.chart('container_line33', {

    title: {
        text: '年统计-未回复'
    },

    subtitle: {
        text: '说明'
    },

    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        enabled: false,
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: '分类一',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: '分类二',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: '分类三',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: '分类四',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: '其他',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }]

});

}

 



//条形图
if($("div").hasClass("container_col")){
Highcharts.chart('container_col', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Monthly Average Rainfall'
    },
    subtitle: {
        text: 'Source: WorldClimate.com'
    },
    xAxis: {
        categories: [
            '税务管理',
            '财务管理',
            '企业管理',
            '财税管理',
            '电脑数码',
            '休闲娱乐',
            '生活百科',
            'Sep',
            'Oct',
            'Nov',
            'Dec'
        ],
        crosshair: true
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Rainfall (mm)'
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
        name: 'Tokyo',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6]

    }, {
        name: 'New York',
        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5]

    }, {
        name: 'London',
        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0]

    }, {
        name: 'Berlin',
        data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4]

    }]
});
}


//饼型图
if($("div").hasClass("container_pei")){

Highcharts.chart('container_pei', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Browser market shares January, 2015 to May, 2015'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                }
            }
        }
    },
    series: [{
        name: 'Brands',
        colorByPoint: true,
        data: [{
            name: 'Microsoft Internet Explorer',
            y: 56.33
        }, {
            name: 'Chrome',
            y: 24.03,
            sliced: true,
            selected: true
        }, {
            name: 'Firefox',
            y: 10.38
        }, {
            name: 'Safari',
            y: 4.77
        }, {
            name: 'Opera',
            y: 0.91
        }, {
            name: 'Proprietary or Undetectable',
            y: 0.2
        }]
    }]
});
}






if($("div").hasClass("container_line_wjtongji")){

Highcharts.chart('container_line_wjtongji', {

    title: {
        text: '活动综合浏览数：3215'
    },

    subtitle: {
        text: '说明'
    },
    xAxis: {
   		categories: ['10-10', '10-11', '10-12', '10-13', '10-14', '10-15', '10-16', '10-17']
	},
    yAxis: {
        title: {
            text: '问卷统计分析表'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
          
        }
    },

    series: [{
        name: '问卷浏览量',
        data: [80, 88, 102, 221, 80, 87, 145, 98]
    }, {
        name: '问卷回收量',
        data: [40, 50, 55, 58, 59, 66, 68,70]
    }]

});

}








if($("div").hasClass("container_line")){

Highcharts.chart('container_line', {

    title: {
        text: '活动综合浏览数：3215'
    },

    subtitle: {
        text: '说明'
    },
    xAxis: {
   		categories: ['10-10', '10-11', '10-12', '10-13', '10-14', '10-15', '10-16', '10-17']
	},
    yAxis: {
        title: {
            text: '左侧标题'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
          
        }
    },

    series: [{
        name: 'PC',
        data: [20, 40, 20, 40, 20, 40, 20, 40]
    }, {
        name: 'MobileWeb',
        data: [30, 30, 30, 30, 30, 30, 30, 30]
    }, {
        name: 'other',
        data: [40, 20, 40, 20, 40, 20, 40, 20]
    }]

});

}

//条形图
if($("div").hasClass("container_col_hdtongji")){
Highcharts.chart('container_col_hdtongji', {
    chart: {
        type: 'column'
    },
    title: {
        text: '活动详细统计'
    },
    subtitle: {
        text: ''
    },  
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2 
        }
    },
    xAxis: {
   		categories: ['活动计划人数', '实际参与人数', '审核通过人数']
	},
    series: [{
        name: '活动参与比例：',
        data: [234, 221, 109]

    }]
});
}






if($("div").hasClass("container_line_tptongji")){

Highcharts.chart('container_line_tptongji', {

    title: {
        text: '浏览总数：2553'
    },

    subtitle: {
        text: '参与投票人数：1254'
    },
    xAxis: {
   		categories: ['10-10', '10-11', '10-12', '10-13', '10-14', '10-15', '10-16', '10-17']
	},
    yAxis: {
        title: {
            text: '投票综合统计表'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
          
        }
    },

    series: [{
        name: '浏览次数',
        data: [20, 40, 60, 40, 55, 77, 70, 95]
    }, {
        name: '投票次数',
        data: [12, 18, 40, 29, 38, 62, 55, 82]
    }]

});

}










//条形图
if($("div").hasClass("container_col_tptongji")){
Highcharts.chart('container_col_tptongji', {
    chart: {
        type: 'column'
    },
    title: {
        text: '投票详细统计'
    },
    subtitle: {
        text: ''
    },  
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2 
        }
    },
    xAxis: {
   		categories: ['张三', '李四', '肖亮', '刘放敏', '罗玉凤',]
	},
    series: [{
        name: '投票数',
        data: [234, 221, 109, 319, 156]

    }]
});
}









})




