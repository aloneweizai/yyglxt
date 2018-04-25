

$(function(){
    buildNoticeDiv();
    buildBangYouReYiDiv();
    buildReDianWenTiDiv();
    buildReDianZhiShiDiv();
    buildZuiXinKeChengDiv();
    buildHuoDongDiv();
    //getLoginUser();
    $("#cszx_span").on("click",function(){
        window.open($("#tabs_ul").find(".active").attr("abc-visiturl"));
    })
});


function buildNoticeDiv(){
    $.ajax({
        url : snsUrl+"/help/pub/notices",
        type : "GET",
        async : false,
        dataType : "JSONP",
        success : function(data){
            //alert(JSON.stringify(data));
            var noticeList = data.noticesList;
            if(noticeList==null){
                return;
            }
            var noticeHtml = "";
            var tempHtml = "<a href='TOHREF' target='_blank'><li class='danhang'><i class='iconfont'>&#xe739;</i>TITLE</li></a>";
            for(var i=0; i < noticeList.length && i<6; i++){
                var content = noticeList[i];
                var href = snsUrl+"/help/notice/detail.html?id="+content.id;
                noticeHtml += tempHtml.replace("TITLE", content.title).replace("TOHREF",href);
            }
            $("#tzgg_ul").html(noticeHtml);

        },
        error : function(e){
            //console.log(e);
        }
    });
}

function buildBangYouReYiDiv(){
    $.ajax({
        url: snsUrl + "/help/pub/hotDiscussion",
        type: "GET",
        async: false,
        data: {'pageSize': 8},
        dataType: "JSONP",
        success: function (data) {
            var byry = data.byry;
            var hotHtml = "";

            for (var i = 0; i < byry.length && i < 8; i++) {
                var ask = byry[i];
                var tempHtml = "";
                if (i == 0) {
                    tempHtml = "<li><a href='BYRYURL' target='_blank'><u style='background: #FF3B35;'>1</u>HOTASK</a></li>";
                } else if (i == 1) {
                    tempHtml = "<li><a href='BYRYURL' target='_blank'><u style='background: #FC7D30;'>2</u>HOTASK</a></li>";
                } else if (i == 2) {
                    tempHtml = "<li><a href='BYRYURL' target='_blank'><u style='background: #FCBF30;'>3</u>HOTASK</a></li>";
                } else {
                    tempHtml = "<li><a href='BYRYURL' target='_blank'><u>" + (i + 1) + "</u>HOTASK</a></li>";
                }
                hotHtml += tempHtml.replace("HOTASK", ask.title.length>22?ask.title.substring(0,22)+"…":ask.title).replace("BYRYURL",snsUrl+"/topic/xiangqing/" + ask.id);
            }
            $("#byry_ul").html(hotHtml);

        },
        error: function (e) {
            //console.log(e);
        }
    });
}

//热点问题
function buildReDianWenTiDiv(){
    $.ajax({
        url: snsUrl + "/help/pub/hotQA",
        type: "GET",
        async: false,
        data: {'pageSize': 8},
        dataType: "JSONP",
        success: function (data) {
            var hotQAList = data.hotQAList;
            var hotHtml = "";

            for (var i = 0; i < hotQAList.length && i < 8; i++) {
                var hotQA = hotQAList[i];
                var tempHtml = "<li><a href='RDWTURL' target='_blank'><i class='iconfont'>&#xe739;</i>TITLE</a></li>";

                hotHtml += tempHtml.replace("RDWTURL",snsUrl+"/help/knowledge/detail.html?id="+hotQA.id).replace("TITLE", hotQA.subject.length>23?hotQA.subject.substring(0,23)+"…":hotQA.subject);
            }
            $("#rdwt_ul").html(hotHtml);

        },
        error: function (e) {
            //console.log(e);
        }
    });
}

//热点知识
function buildReDianZhiShiDiv(){
    $.ajax({
        url: snsUrl + "/help/pub/hotK",
        type: "GET",
        async: false,
        dataType: "JSONP",
        success: function (data) {
            var hotKList = data.hotKList;
            var hotHtml = "";

            for (var i = 0; i < hotKList.length && i < 10; i++) {
                var hotK = hotKList[i];
                var tempHtml = "<li><a href='RDZSURL' target='_blank'><i class='iconfont'>&#xe739;</i>TITLE</a></li>";

                hotHtml += tempHtml.replace("RDZSURL",snsUrl+"/help/knowledge/detail.html?id="+hotK.id).replace("TITLE", hotK.subject.length>23?hotK.subject.substring(0,23)+"…":hotK.subject);
            }
            $("#rdzs_ul").html(hotHtml);

        },
        error: function (e) {
            //console.log(e);
        }
    });
}


//最新课程
function buildZuiXinKeChengDiv(){
    $.ajax({
        url: snsUrl + "/help/pub/newestcurr",
        type: "GET",
        async: false,
        dataType: "JSONP",
        success: function (data) {
            var curriculumnList = data.curriculumnList;
            var hotHtml = "";

            for (var i = 0; i < curriculumnList.length && i < 6; i++) {
                var curriculumn = curriculumnList[i];
                var dafaultPicUrl = snsUrl+"/images/noimg_0.jpg";
                var tempHtml = "<li class='clearfix'>"
                    +  "<a href='CURRURL' target='_blank'>"
                    +  "<div class='newest-li-left'>"
                    +  "<img src='"+dafaultPicUrl+"' alt=''>"
                    +  "</div>"
                    +  "<div class='newest-li-right'>"
                    +  "<p class='newest-li-right-p'>TITLE</p>"
                    +   "</a>"
                    +  "<p>"
                    +  "<span>"
                    +  "<i class='iconfont'>&#xe735;</i>"
                    +  "<s>HOUR</s>课时"
                    +  "</span>"
                    +  "<span>"
                    +  "<i class='iconfont'>&#xe713;</i>"
                    +  "<s>TIMES</s>次学习"
                    +  "</span>"
                    +  "</p>"
                    +  "<p>"
                    +  "FREE"
                    +  "</p>"
                    +  "</div>"
                    +  "</li>";

                hotHtml += tempHtml.replace("CURRURL",snsUrl+"/school/details/"+curriculumn.curriculumId+"?page=1").replace(dafaultPicUrl,picUrl+curriculumn.cover).replace("TITLE",  curriculumn.title.length>17?curriculumn.title.substring(0,17)+"…":curriculumn.title).replace("HOUR", curriculumn.coursewareNum).replace("TIMES", curriculumn.watchNum).replace("FREE", curriculumn.isFree=="0"?"<s style='color:#f29941'>会员免费</s>":"<s>免费</s>");
            }
            $("#zxkc_ul").html(hotHtml);

        },
        error: function (e) {
            //console.log(e);
        }
    });


}


//财税活动
function buildHuoDongDiv(){
    $.ajax({
        url: snsUrl + "/pub/taxEvent",
        type: "GET",
        dataType: "JSONP",
        success: function (data) {

            var event = data.event;
            if(event==null || event==''){
                return;
            }
            //console.log(JSON.stringify(event));
            var dizhi = data.dizhi;
            //console.log(JSON.stringify(dizhi));
            var eventHtml = "";
            
            eventHtml +=     "<img src='"+picUrl+event.picture+"' onerror=\"this.src=\'/images/noimg_0.jpg\'\" class='help-activity-right-firstimg'  onclick='openEvent()'>";
            eventHtml +=     "<div class='firstimg-bg'>";
            eventHtml +=     "<p class='duohang'>";
            eventHtml +=     event.title;
            eventHtml +=     "</p>";
            eventHtml += "<span><i class='iconfont'>&#xe7f7;</i>"+formatDate(event.begintime)+"</span>";
            var eventId = "\'"+event.eventId+"\'";
            eventHtml +="<input type='hidden' value="+eventId+" id='eventIdHidden'>";
            eventHtml +=     "<a href='javascript:void(0)' onclick='openEvent()'>我要报名</a>";
            eventHtml +=     "</div>";


            //console.log(eventHtml);
            $("#huodongDiv").html(eventHtml);

        },
        error: function (e) {
            //console.log(e);
        }
    });
}


//格式化时间戳
function formatDate(now) {
    now = new Date(now);
    var year=now.getFullYear();
    var month=now.getMonth()+1;
    var date=now.getDate();
    var hour=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    return year+"年"+month+"月"+date+"日"+hour+":"+minute;
}
//如果记得时间戳是毫秒级的就需要*1000 不然就错了记得转换成整型
//var d=new Date(1230999938);
//alert(formatDate(d));


//打开活动详情页
function openEvent(){
    var eventId = $('#eventIdHidden').val();
    window.location.href = snsUrl + "/pub/event/detail/" + eventId;
}

//打开活动列表页
function openEventList(){
    window.location.href = snsUrl + "/pub/event/list.html";
}

//首页服务续费图片链接
function getFwxfUrl_home_pic(){
    $.ajax({
        url: snsUrl + "/pub/toPayServiceCharge?random=" + Math.random(),
        type: "GET",
        async:false,
        success: function (result) {
            if (result.toUrl != null) {


                $("#home_fwxf_pic").attr("href",result.toUrl);

            } else {


                $("#home_fwxf_pic").attr("href","http://pay.abc12366.cn");

            }
        },
        error: function () {

            $("#home_fwxf_pic").attr("href","http://pay.abc12366.cn");
        },
        dataType: "JSONP"
    })
}