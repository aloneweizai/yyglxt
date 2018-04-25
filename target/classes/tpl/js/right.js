
$(function(){
	//	热点问题
	if($("#hotQuestion").length > 0){
		builderHotQuestion();
	}

	//	帮邦等你回答
	if($("#hotDiscussion").length > 0){
		builderYourAnswer();
	}
	//	帮帮热议
	if($("#hotDiscussion").length > 0){
		builderHotDiscussion();
	}
	// 热门文章
	if($("#hotContent").length > 0){
		builderHotContentListHtml();
	}
	//	热词
	if($("#hotLabel").length > 0){
		builderLabelHtml();
	}
	//	热点知识
	if($("#hotknowledge").length > 0){
		buildReDianZhiShiDiv();
	}
})


function builderHotQuestion(){
	$.ajax({
		url : snsUrl+"/help/pub/hotQA",
		type : "GET",
		async : false,
		data: {'pageSize': 9},
		dataType : "JSONP	",
		success : function(data){
			var hotspotAskBOList = data.hotQAList;
			var hotHtml = "<div class='zx_liebiao_dh2'><div class='home_list_title'><i class='iconfont' style='color: #D9534F; '>&#xe634;</i><span>热点问题</span><div class='clear'></div></div><a href='MOREURL' target='_blank' class='byry-more'>更多+</a><ul class='bangyoureyi'>";
			hotHtml = hotHtml.replace("MOREURL",snsUrl+"/help/knowledge/index.html");
			var tempHtml = "<li><a href='RDWTURL'><i style='background: #D9534F;'>IDXI</i><span>HOTASK</span></a></li>";
			if(hotspotAskBOList != null && hotspotAskBOList.length>0) {
				for (var i = 0; i < hotspotAskBOList.length; i++) {
					var hotQA = hotspotAskBOList[i];
					hotHtml += tempHtml.replace("RDWTURL", snsUrl + "/help/knowledge/detail.html?id=" + hotQA.id).replace("IDXI", (i + 1)).replace("HOTASK", hotQA.subject);
				}
			}
			hotHtml +="</ul></div>"
			$("#hotQuestion").html(hotHtml);
		},
		error : function(e){
			//console.log(e);
		}
	});
}


function builderYourAnswer(){
	$.ajax({
		url : snsUrl+"/help/pub/yourAnswer",
		type : "GET",
		async : false,
		dataType : "JSONP",
		success : function(data){
			var dnhd = data.dnhd;
			if(dnhd==null){
				return;
			}
			var hotHtml = "<div class='zx_liebiao_dh2'><div class='list_title' style='margin-bottom: 0;'><i class='iconfont' style='color: #FF7800; '>&#xe634;</i><span>帮邦等你回答</span><div class='clear'></div></div><a href='MOREURL' target='_blank' class='byry-more'>更多+</a><ul class='bangyoureyi'>";
			hotHtml = hotHtml.replace("MOREURL",snsUrl+"/topic/help.html");
			var tempHtml = "<li><a href=''><i>IDXI</i><span>HOTASK</span></a></li>";
			if(dnhd != null && dnhd.length>0) {
				for (var i = 0; i < dnhd.length; i++) {
					var ask = dnhd[i];
					if (i == 0) {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i style='background: #FF3B35;'>1</i><span>HOTASK</span></a></li>";
					} else if (i == 1) {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i style='background: #FC7D30;'>2</i><span>HOTASK</span></a></li>";
					} else if (i == 2) {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i style='background: #FCBF30;'>3</i><span>HOTASK</span></a></li>";
					} else {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i>IDXI</i><span>HOTASK</span></a></li>";
					}
					hotHtml += tempHtml.replace("IDXI", (i + 1)).replace("HOTASK", ask.title).replace("BYRYURL", snsUrl + "/topic/xiangqing/" + ask.id);
				}
			}
			hotHtml+="</ul></div>"
			$("#yourAnswer").html(hotHtml);

		},
		error : function(e){
			//console.log(e);
		}
	});
}


function builderHotDiscussion(){
	$.ajax({
		url : snsUrl+"/help/pub/hotDiscussion",
		type : "GET",
		async : false,
		dataType : "JSONP",
		success : function(data){
			var byry = data.byry;
			if(byry==null){
				return;
			}
			var hotHtml = "<div class='zx_liebiao_dh2'><div class='list_title' style='margin-bottom: 0;'><i class='iconfont' style='color: #FF7800; '>&#xe634;</i><span>帮友热议</span><div class='clear'></div></div><a href='MOREURL' target='_blank' class='byry-more'>更多+</a><ul class='bangyoureyi'>";
			hotHtml = hotHtml.replace("MOREURL",snsUrl+"/topic/help.html");
			var tempHtml = "<li><a href=''><i>IDXI</i><span>HOTASK</span></a></li>";
			if(byry != null && byry.length>0) {
				for (var i = 0; i < byry.length; i++) {
					var ask = byry[i];
					if (i == 0) {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i style='background: #FF3B35;'>1</i><span>HOTASK</span></a></li>";
					} else if (i == 1) {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i style='background: #FC7D30;'>2</i><span>HOTASK</span></a></li>";
					} else if (i == 2) {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i style='background: #FCBF30;'>3</i><span>HOTASK</span></a></li>";
					} else {
						tempHtml = "<li><a href='BYRYURL' target='_blank'><i>IDXI</i><span>HOTASK</span></a></li>";
					}
					hotHtml += tempHtml.replace("IDXI", (i + 1)).replace("HOTASK", ask.title).replace("BYRYURL", snsUrl + "/topic/xiangqing/" + ask.id);
				}
			}
			hotHtml+="</ul></div>"
			$("#hotDiscussion").html(hotHtml);

		},
		error : function(e){
			//console.log(e);
		}
	});
}


function builderHotContentListHtml(){
	$.ajax({
		url : snsUrl+"/help/pub/hotContentList",
		type : "GET",
		async : false,
		data: {'pageSize': 5},
		dataType : "JSONP",
		success : function(data){
			var contentList = data.hotContentList;
			var hotHtml = "<div class='list_title'><span>热门资讯</span></div>";
			var tempHtml = "<li class='max'><a href='TOHREF'>IMGTITLE<div class='xuhao'>Top1</div> <div class='zx_right_title'> <p>TITLE</p> <span>DATATIME</span> </div></a></li>";
			if(contentList != null && contentList.length>0) {
				for (var i = 0; i < 5 && i < contentList.length; i++) {
					var content = contentList[i];
					var href = cswUrl + "/" + content.staticLink;
					if (i <= 1) {
						hotHtml += tempHtml.replace("Top1", "Top" + (i + 1)).replace("TOHREF", href).replace("IMGTITLE", (content.titleImg == null || content.titleImg == '') ? "<img src='/images/noimg_1.jpg'/>" : "<img src='" + cswUrl + content.titleImg + "'/>").replace("TITLE", content.title).replace("DATATIME", content.releaseDate);

					} else {
						hotHtml += tempHtml.replace("Top1", (i + 1)).replace("TOHREF", href).replace("max", "min").replace("IMGTITLE", (content.titleImg == null || content.titleImg == '') ? "<img src='/images/noimg_0.jpg'/>" : "<img src='" + cswUrl + content.titleImg + "'/>").replace("TITLE", content.title).replace("DATATIME", new Date(content.releaseDate).Format("yyyy-MM-dd"));
					}
				}
			}
			$("#hotContent").html(hotHtml);

		},
		error : function(e){
			//console.log(e);
		}
	});
}


function builderLabelHtml(){
	$.ajax({
		url : snsUrl+"/help/pub/hotLabel",
		type : "GET",
		async : false,
		dataType : "JSONP",
		success : function(labelList){
			labelList = labelList.contentLabelList;
			var hotHtml = "<div class='list_title'><span>热词推荐</span></div><div class='recituijian'>";
			if(labelList != null && labelList.length>0) {
				for (var i = 0; i < labelList.length; i++) {
					var label = labelList[i];
					hotHtml += "<a href='" + snsUrl + "/help/pub/contentBelongTagList.html?tagId=" + label.tagId + "&page=1' target='_blank'>" + label.tagName + "</a>";
				}
			}
			hotHtml +="</div>";
			$("#hotLabel").html(hotHtml);
		},
		error : function(e){
			//console.log(e);
		}
	});
}

//热点知识
function buildReDianZhiShiDiv(){
	$.ajax({
		url : snsUrl+"/help/pub/hotK",
		type : "GET",
		async : false,
		dataType : "JSONP	",
		success : function(data){
			//var hotspotAskBOList = data.hotQAList;
			var hotKList = data.hotKList;
			var hotHtml = "<div class='zx_liebiao_dh2'><div class='list_title' style='margin-bottom: 0;'><i class='iconfont' style='color:  #FF7800; '>&#xe634;</i><span>热点知识</span><div class='clear'></div></div><a href='MOREURL' target='_blank' class='byry-more'>更多+</a><ul class='bangyoureyi'>";
			hotHtml = hotHtml.replace("MOREURL",snsUrl+"/help/knowledge/list.html");
			var tempHtml = "<li><a href='RDZSURL'><i style='background: #D9534F;'>IDXI</i><span>HOTKNOW</span></a></li>";
			if(hotKList != null && hotKList.length>0) {
				for (var i = 0; i < hotKList.length; i++) {
					var hotK = hotKList[i];
					if (i == 0) {
						tempHtml = "<li><a href='RDZSURL'><i style='background: #FF3B35;'>IDXI</i><span>HOTKNOW</span></a></li>";
					} else if (i == 1) {
						tempHtml = "<li><a href='RDZSURL'><i style='background: #FC7D30;'>IDXI</i><span>HOTKNOW</span></a></li>";
					} else if (i == 2) {
						tempHtml = "<li><a href='RDZSURL'><i style='background: #FCBF30;'>IDXI</i><span>HOTKNOW</span></a></li>";
					} else {
						tempHtml = "<li><a href='RDZSURL'><i> IDXI</i><span>HOTKNOW</span></a></li>";
					}
					hotHtml += tempHtml.replace("RDZSURL", snsUrl + "/help/knowledge/detail.html?id=" + hotK.id).replace("IDXI", (i + 1)).replace("HOTKNOW", hotK.subject);
				}
			}
			hotHtml +="</ul></div>"
			$("#hotknowledge").html(hotHtml);
		},
		error : function(e){
			//console.log(e);
		}
	});
}


Date.prototype.Format = function(fmt)
{
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
}  