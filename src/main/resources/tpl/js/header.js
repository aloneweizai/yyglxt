
var headerHtml = "";
headerHtml +="<div class='ny_head'>";
headerHtml +="<div class='w_1200'>";
headerHtml +="<div class='ny_logo'>";
headerHtml +="<a href='"+cswUrl+ "/index.html' title='网站首页'>";
headerHtml +="<div class='boximg'><span><img src='"+cswUrl+"/images/logo.png'></span></div>";
headerHtml +="</a>";
headerHtml +="</div>";

headerHtml +="<div class='ny-480-nav'>";
headerHtml +="<div class='ny-480-sg'>";
headerHtml +="<span></span>";
headerHtml +="<span></span>";
headerHtml +="<span></span>";
headerHtml +="</div>";
headerHtml +="<ul>";
headerHtml +="<li class='active'><a href='"+cswUrl+"/index.html'>首页</a></li>";
headerHtml +="<li><a href='"+cswUrl+"/products/index.html'>产品</a></li>";
headerHtml +="<li><a href='"+cswUrl+ "/services/index.html'>服务</a></li>";
headerHtml +="<li><a href='"+cswUrl+ "/news/index.html'>资讯</a></li>";
headerHtml +="<li><a href='"+cswUrl+ "/rjxz/index.html'>下载</a></li>";
headerHtml +="<li><a href='"+snsUrl+ "/school/index.html'>学堂</a></li>";
headerHtml +="<li><a href='"+snsUrl+"/bangbang/index.html'>帮邦</a></li>";
headerHtml +="<div class='clear'></div>";
headerHtml +="</ul>";
headerHtml +="<div class='ny-480-bg'></div>";
headerHtml +="</div>";

headerHtml +="<div class='fr'>";
headerHtml +="<div class='ny_nav'>";
headerHtml +="<ul>";
headerHtml +="<li><a href='"+cswUrl+"/index.html'>首页</a></li>";
headerHtml +="<li><a href='"+cswUrl+"/products/index.html'>产品</a></li>";
headerHtml +="<li><a href='"+cswUrl+ "/services/index.html'>服务</a></li>";
headerHtml +="<li><a href='"+cswUrl+ "/news/index.html'>资讯</a></li>";
headerHtml +="<li><a href='"+cswUrl+ "/rjxz/index.html'>下载</a></li>";
headerHtml +="<li><a href='"+snsUrl+ "/school/index.html'>学堂</a></li>";
headerHtml +="<li><a href='"+snsUrl+"/bangbang/index.html'>帮邦</a></li>";
headerHtml +="<div class='clear'></div>";
headerHtml +="</ul>";
headerHtml +="</div>";
headerHtml +="<div id='loginheaddiv' class='ny_login_nav'>";
headerHtml +="<a href='"+ucUrl+ "/login'>登录</a>|";
headerHtml +="<a href='"+ucUrl+ "/login#zhuce'>注册</a>";
headerHtml +="</div>";
headerHtml +="</div>";
headerHtml +="</div>";
headerHtml +="</div>";
headerHtml +="<div class='clear'></div>";


$(function(){
	//$("#header").html(headerHtml);
	countReadNumber();
	getLoginUser();
	$("#tiwen").on("click",function(){


		$.ajax({
			url:  snsUrl+"/getLoginUser.html",
			type: "GET",
			async: false,
			dataType: "JSONP",
			success: function (data) {
				var userName = null;
				var headPicUrl = null;
				if(data!=null && data.data!=null && data.data.data !=null && data.data.data.username!=null){
					userName = data.data.data.nickname;
				}

				if(userName!=null){
					window.location.href = snsUrl + "/quiz/quizindex.html";
				}else{
					setTimeout(function(){
						window.location.href = ucUrl + "/login?recallurl="+$.base64.btoa((snsUrl + "/quiz/quizindex.html").replace("https://",""));
					},1000);
				}

			},
			error: function (e) {
				//console.log(e);
			}
		});
	});
});


function getLoginUser(){
	//alert(getCookie("nickname"));
	var widW = $(window).width();
	if(widW<481){
		$("#loginheaddiv").hide();
		$("#loginheaddiv_loged").hide();
	}else {
		$.ajax({
			url: snsUrl + "/overtime.html",
			type: "GET",
			async: false,
			dataType: "JSONP",
			success: function (data) {
				if (data.data.code == "2000") {
					$("#loginheaddiv").hide();
					$("#loginheaddiv_loged").show();
					$("#nickname_a").text(decodeURI(getCookie("nickname")));
					$("#vip_a").text(decodeURI(getCookie("vipLevel")) + "  " + decodeURI(getCookie("vipLevelName")));
					$("#level_a").text(decodeURI(getCookie("level")) + "(" + decodeURI(getCookie("levelName")) + ")");
					var userPicPath = getCookie("userPicturePath");
					if (userPicPath == null || userPicPath == "") {
						$("#userpic_img").attr("src", "/images/icon_zx_03.png");
					} else {
						$("#userpic_img").attr("src", picUrl + getCookie("userPicturePath"));
					}

					//$("#loginheaddiv").html($("#loginheaddiv_loged").html());
				} else {
					$("#loginheaddiv").show();
					$("#loginheaddiv_loged").hide();
					//$("#loginheaddiv").html($("#loginheaddiv_beforelog").html());
				}
			},
			error: function (e) {
			}
		});
	}
}


//function getLoginUser(){
//	//alert(decodeURI(getCookie("nickname")));
//	//alert(getCookie("nickname"));
//	$.ajax({
//		url: snsUrl + "/getLoginUser.html",
//		type: "GET",
//		async: false,
//		dataType: "JSONP",
//		success: function (data) {
//			//alert(JSON.stringify(result));
//			//alert(data.data.data.username);
//			var userName = null;
//			var headPicUrl = null;
//			if(data.data !=null && data.data.data !=null && data.data.data.username!=null){
//				if(data.data.data.nickname!=null && data.data.data.nickname!=""){
//					userName = data.data.data.nickname;
//				}else{
//					userName = data.data.data.username;
//				}
//
//				headPicUrl = (data.data.data.userPicturePath==null || data.data.data.userPicturePath.length==0)? "/images/icon_zx_03.png" :picUrl+data.data.data.userPicturePath;
//			}
//
//			if(userName!=null){
//				$("#loginheaddiv").attr("class","ny_login_nav2");
//				var loginheadtext = "<img src='"+headPicUrl+"'>";
//				loginheadtext += "<div class='user_name'>";
//				loginheadtext += "<p>";
//				loginheadtext += "<a style='color: #fff; font-size: 12px;' href='"+ucUrl+"/index.html'>"+userName+"</a>";
//				loginheadtext += "</p>";
//				//loginheadtext += "<a style='color: #fff; font-size: 12px;' href="+ucUrl+"/index.html style='display:block;'>"+userName+"</a>";
//				loginheadtext += "<a href='javascript:void (0)' style='color: #fcff00; font-size: 12px;' >";
//				loginheadtext += (data.data.data.vipLevel==null || data.data.data.vipLevel=='')?'VIP0':data.data.data.vipLevel;
//				loginheadtext += "&nbsp;";
//				loginheadtext += (data.data.data.vipLevelName==null || data.data.data.vipLevelName=='')?'普通用户':data.data.data.vipLevelName;
//				loginheadtext += "</a>";
//				//loginheadtext += "<span style='font-size: 12px;'>｜</span>";
//				//loginheadtext += "<a href='javascript:void (0)' style='color: #fcff00; font-size: 12px;' >";
//				//loginheadtext += (data.data.data.level==null || data.data.data.level=='')?'LV1':data.data.data.level;
//				//loginheadtext += "&nbsp;";
//				//loginheadtext += (data.data.data.levelName==null || data.data.data.levelName=='')?'（实习生1级）':("（"+data.data.data.levelName+"）");
//				loginheadtext += "</a>";
//
//				loginheadtext += "</div>";
//
//				loginheadtext += "<div class='nemu-box'>";
//				loginheadtext += "<div class='center'>";
//				loginheadtext += "<a href='javascript:void (0)'>";
//				loginheadtext += (data.data.data.level==null || data.data.data.level=='')?'LV1':data.data.data.level;
//				loginheadtext += (data.data.data.levelName==null || data.data.data.levelName=='')?'（实习生1级）':("（"+data.data.data.levelName+"）");
//				loginheadtext += "</a>";
//				loginheadtext += "</div>";
//
//
//				//loginheadtext += "<a href='"+ ucUrl +"/index.html'>进入个人中心</a>";
//				//loginheadtext += "<a href='javascript:;' id='logout'>";
//				//loginheadtext += "<i class='iconfont'> &#xe636;</i>";
//				//loginheadtext += "<span >退出</span>";
//				//loginheadtext += "</a>";
//				//loginheadtext += "</div>";
//				loginheadtext += "<ul>";
//				loginheadtext += "<li name='"+ ucUrl +"/index.html' link='"+ ucUrl +"/userinfo/userinfolist.html' onclick='jbzl()'> ";
//				loginheadtext += "<a href='javascript:void(0);'>";
//				loginheadtext += "<i class='iconfont'> &#xe764;</i>";
//				loginheadtext += "<span>基本资料</span>";
//				loginheadtext += "</a>";
//				loginheadtext += "</li>";
//
//				loginheadtext += "<li name='"+ ucUrl +"/index.html?#1_1' link='"+ ucUrl +"/userinfo/userinfolist.html?#1_1' onclick='smrzClick()'>";
//				loginheadtext += "<a href='javascript:void(0);'>";
//				loginheadtext += "<i class='iconfont'> &#xe6ed;</i>";
//				loginheadtext += "<span>实名认证</span>";
//				loginheadtext += "</a>";
//				loginheadtext += "</li>";
//
//				loginheadtext += "<li name='"+ ucUrl +"/userinfo/user_message.html' link='"+ ucUrl +"/userinfo/user_message.html' onclick='wdxx();'>";
//				loginheadtext += "<a href='javascript:void(0);'>";
//				loginheadtext += "<i class='iconfont'> &#xe603;</i>";
//				loginheadtext += "<span>我的消息</span>";
//				//loginheadtext += "<em>1</em>";
//				loginheadtext += "</a>";
//				loginheadtext += "</li>";
//
//				loginheadtext += "<li name='"+ ucUrl +"/member/member_index.html' link='"+ ucUrl +"/member/member_index.html' onclick='hyzx();'>";
//				loginheadtext += "<a href='javascript:void(0);'>";
//				loginheadtext += "<i class='iconfont'> &#xe684;</i>";
//				loginheadtext += "<span>会员中心</span>";
//				loginheadtext += "</a>";
//				loginheadtext += "</li>";
//
//				loginheadtext += "<li class='tuichu'>"
//				loginheadtext += "<a  href='"+ ucUrl +"/logout_index.html'>";
//				loginheadtext += "<i class='iconfont'> &#xe636;</i>";
//				loginheadtext += "<span>退出登录</span>";
//				loginheadtext += "</a>";
//				loginheadtext += "</li>";
//				loginheadtext += "</ul>";
//				loginheadtext += "</div>";
//
//
//				$("#loginheaddiv").html(loginheadtext);
//				if($(window).width()>480){
//					$("#loginheaddiv").show();
//				}else{
//					$("#loginheaddiv").hide();
//				}
//
//			}else{
//				$("#loginheaddiv").attr("class","ny_login_nav");
//				var unloginheadtext = "<a href='" + ucUrl + "/login"+"'>登录</a>|";
//				unloginheadtext += "<a href='" + ucUrl + "/login#zhuce"+"'>注册</a>";
//				$("#loginheaddiv").html(unloginheadtext);
//			}
//
//		},
//		error: function (e) {
//
//		}
//	});
//}


function countReadNumber(){
	var contentPath = window.location.pathname;
	var contentId = contentPath.substring(contentPath.lastIndexOf("/")+1,contentPath.lastIndexOf(".html"));
	if(contentId.length == 32){
		$.ajax({
			url: snsUrl + "/pub/browseCount.html?random="+Math.random(),
			type: "GET",
			dataType: "JSONP",
			data:{id:contentId},
			//success: function (data) {
			//}

		});
		//$.get(snsUrl + "/pub/browseCount.html?id="+contentId+"&random="+Math.random(),function(){});
	}
}

$(function(){
	//财税网首页手机版导航伸缩
	var widH = $(window).height();
	var widW = $(window).width();
	$(".ny-480-bg").width(widW);
	$(".ny-480-bg").height(widH);
	$(".ny-480-nav").click(function(){
		$(this).children('ul').stop().slideToggle(500);
		if($(".ny-480-bg").css("display")=='none'){
			$(this).find(".ny-480-bg").stop().show();
			$('body').css({ "overflow-x":"hidden","overflow-y":"hidden"});
			$("body").on("touchmove",function(event){event.preventDefault;}, false)
		}else{
			$(this).find(".ny-480-bg").stop().hide();
			$('body').css({ "overflow-x":"auto","overflow-y":"auto"});
			$("body").off("touchmove")
		}
	})
	$(".ny-480-nav ul li").hover(function(){
		$(this).addClass("active").siblings('li').removeClass("active")
	})
})

//function getCookie(sName) {
//	var aCookie = document.cookie.split("; ");
//	for (var j = 0; j < aCookie.length; j++) {
//		var aCrumb = aCookie[j].split("=");
//		if (escape(sName) == aCrumb[0])
//			return unescape(aCrumb[1]);
//	}
//	return null;
//}

//function getCookie(name)
//{
//	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
//	if(arr=document.cookie.match(reg))
//		return unescape(arr[2]);
//	else
//		return null;
//}

function getCookie(name){
	var strcookie = document.cookie;//获取cookie字符串
	var arrcookie = strcookie.split("; ");//分割
//遍历匹配
	for ( var i = 0; i < arrcookie.length; i++) {
		var arr = arrcookie[i].split("=");
		if (arr[0] == name){
			return arr[1];
		}
	}
	return "";
}