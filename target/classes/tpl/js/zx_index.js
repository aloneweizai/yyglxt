

//资讯首页瀑布按钮js
$("a[name='nextPageButton_zxindex']").on("click",function(){
            var nextPageNum = $(this).attr("next-page-num");
            nextPageNews(nextPageNum);
        });

function nextPageNews(nextPageNum){
    if(nextPageNum=="0"){
        $("a[name='nextPageButton_zxindex']").text("没有了");
        return;
    }
    $.ajax({
        url: snsUrl +"/help/pub/nextNews.html?nextPageNum="+nextPageNum,
        type : "GET",
        dataType : "JSONP",
        success: function(result){
        	var newContentList = result.newContentList;
            if(newContentList == null){
                return;
            }
        	var contentLabelList = result.contentLabelList.dataList;
        	var pageNum = result.pageNum;
        	
        	var htmlVal = "";
        	
            for(var i=0,len = newContentList.length; i<len;i++){
                htmlVal += "<li class='clearfix'><div class='dynamic-right-li'>";
                htmlVal += "<a href='"+ cswUrl + newContentList[i].staticLink +"'" +"target='_blank'><img src='"+ cswUrl + newContentList[i].titleImg +"' class='fl clearfix'></a>";

                htmlVal += "<a href='"+ cswUrl + newContentList[i].staticLink +"'" +"target='_blank'><h4>"+newContentList[i].title+"</h4></a>";
                htmlVal += "<p class='dynamic-right-li-hello sanhang'><span><span>"+ newContentList[i].shortTitle +"</span></span></p>";
                htmlVal += "<p class='dynamic-right-li-word clearfix'><span>作者："+ newContentList[i].author +"<s>"+ formatDate(newContentList[i].releaseDate) +"</s></span>";
                htmlVal += "<span>";
                var labels = (newContentList[i].contentType).split(";");
                //alert(JSON.stringify(labels));
                //alert(JSON.stringify(contentLabelList));
                //alert(labels[0]);
                if(labels!=null && labels.length>0 && labels[0].length>1){
                    htmlVal +="<i class='iconfont'>&#xe78d;</i>"
                    for(var k=0; k<labels.length; k++){
                        //alert(contentLabelList.length);
                        for(var j=0,lent = contentLabelList.length; j<lent;j++){
                            //alert("label="+labels[k]+";contentLabelList[j]="+contentLabelList[j].tagId);
                            if(labels[k] == contentLabelList[j].tagId){
                                htmlVal += "<a href='"+snsUrl+"/help/pub/contentBelongTagList.html?tagId="+contentLabelList[j].tagId+"&page=1' target='_blank'><u>"+contentLabelList[j].tagName+"</u></a>"
                            }
                        }
                    }
                }

                htmlVal += "</span>";

                htmlVal += "</p>";
                htmlVal += "</span>";
                htmlVal += "</div>";
                htmlVal += "</li>";
            }
        		
        	
            $("ul[name='contentList_zxIndex']").append(htmlVal);
            $("a[name='nextPageButton_zxindex']").attr("next-page-num",1+parseInt(nextPageNum));
        },
        error:function(){
            layer.alert("出错了");
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
     return year+"-"+month+"-"+date; 
} 
//如果记得时间戳是毫秒级的就需要*1000 不然就错了记得转换成整型
//var d=new Date(1230999938); 
//alert(formatDate(d));