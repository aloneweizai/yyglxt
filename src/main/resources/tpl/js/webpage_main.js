





$(function(){
	
	//选项卡公共用法
	var tab = function(obj){
	    var n = obj.index();
	    var ch = obj.children();
	    var sib = obj.parent().parent().next().children();
	    if(ch[0] === undefined){
	        obj.addClass('on').siblings().removeClass('on')
	    }else{
	        ch.addClass('on').parent().siblings().children().removeClass('on');
	    }
	    sib.eq(n).show().siblings().hide();
	};
	
	//帮邦首页选项卡点击事件
	$(document).on('click','#BbHome ol li',function() {
	    tab($(this));
	});

	
	
	
	
	
	  //个人中心切换iframe src地址

	$(".grzx_main_l_b [data-url='1']").click(function(){
		var test=$(this).attr("data-name");
	    	$(".uc_iframe").attr('src',test)
	}) 
	
	
	$(".cjwt_fenlei ul li").click(function(){
		$(this).next().slideToggle(); 
		var classwt=$(this).find('i').attr('class');
		if(classwt=="iconfont icon-zuoyoujiantou-copy-copy"){
			$(this).find('i').attr("class","iconfont icon-zuoyoujiantou-copy")
		}else{
			$(this).find('i').attr("class","iconfont icon-zuoyoujiantou-copy-copy")
		}
	})


	$(".hot-box .nav li").click(function() {
	   	 var n=$(this).index();
	   	 $(this).parent().next().children("li").hide().eq(n).show();
	   	 $(this).addClass('noe').siblings().removeClass('noe');
   });
   
   $(".fankuileixing span").click(function(){
   		$(this).addClass('hover').siblings().removeClass('hover')
   })
    
   
   
   //学堂
   
   //推荐——最新-会员-免费
   $(".zuixin_tuijian li").hover(function() {
	   	 var n=$(this).index();
	   	  $(this).addClass('hover').siblings().removeClass('hover');
	   	 $(this).parent().next().children("li").hide().eq(n).show();

   });
   
   
   
   
   						
	$("#select1 dd").click(function () {
		$(this).addClass("xuetang_selected").siblings().removeClass("xuetang_selected");
		if ($(this).hasClass("xuetang_select-all")) {
			$("#selectA").remove();
		} else {
			var copyThisA = $(this).clone();
			if ($("#selectA").length > 0) {
				$("#selectA a").html($(this).text());
			} else {
				$(".xuetang_select-result dl").append(copyThisA.attr("id", "selectA"));
			}
		}
	});
	
	$("#select2 dd").click(function () {
		$(this).addClass("xuetang_selected").siblings().removeClass("xuetang_selected");
		if ($(this).hasClass("xuetang_select-all")) {
			$("#selectB").remove();
		} else {
			var copyThisB = $(this).clone();
			if ($("#selectB").length > 0) {
				$("#selectB a").html($(this).text());
			} else {
				$(".xuetang_select-result dl").append(copyThisB.attr("id", "selectB"));
			}
		}
	});
	
	$("#select3 dd").click(function () {
		$(this).addClass("xuetang_selected").siblings().removeClass("xuetang_selected");
		if ($(this).hasClass("xuetang_select-all")) {
			$("#selectC").remove();
		} else {
			var copyThisC = $(this).clone();
			if ($("#selectC").length > 0) {
				$("#selectC a").html($(this).text());
			} else {
				$(".xuetang_select-result dl").append(copyThisC.attr("id", "selectC"));
			}
		}
	});
	
	$("#selectA").click(function () {
		$(this).remove();
		$("#select1 .xuetang_select-all").addClass("xuetang_selected").siblings().removeClass("xuetang_selected");
	});
	
	$("#selectB").click(function () {
		$(this).remove();
		$("#select2 .xuetang_select-all").addClass("xuetang_selected").siblings().removeClass("xuetang_selected");
	});
	
	$("#selectC").click(function () {
		$(this).remove();
		$("#select3 .xuetang_select-all").addClass("xuetang_selected").siblings().removeClass("xuetang_selected");
	});
	
	$(".xuetang_select dd").click(function () {
		if ($(".xuetang_select-result dd").length > 1) {
			$(".xuetang_select-no").hide();
		} else {
			$(".xuetang_select-no").show();
		}
	});
	
 
//学堂订单

	//第一页的确定按钮
	$("#btn_part1").click(function() { 
		$(".part1").hide();
		$(".part2").show();
		$(".step li").eq(1).addClass("on");
	});
	//第二页的确定按钮
	$("#btn_part2").click(function() { 
		$(".part2").hide();
		$(".part3").show();
		$(".step li").eq(2).addClass("on");
	});
	//第三页的确定按钮
	$("#btn_part3").click(function() { 
		$(".part3").hide();
		$(".part4").show();
		$(".step li").eq(3).addClass("on");
		
	});
	
	
	
	//课程视频右边目录和提问
	$(".mulu_biji_tiwen span").click(function() {
	   	 var n=$(this).index();
	   	 $(this).parent().next().children("ul").hide().eq(n).show();
	   	 $(this).addClass('hover').siblings().removeClass('hover');
   });





//评价弹窗
	function byIndexLeve(index){
        var str ="";
        switch (index)
        {
            case 0:
                str="差评";
                break;
            case 1:
                str="较差";
                break;
            case 2:
                str="中等";
                break;
            case 3:
                str="一般";
                break;
            case 4:
                str="好评";
                break;
        }
        return str;
    }
    //  星星数量
    var stars = [
        ['x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png'],
    ];
    $(".block li").find("img").hover(function(e) {
        var obj = $(this);
        var index = obj.index();
        if(index < (parseInt($(".block li").attr("data-default-index")) -1)){
            return ;
        }
        var li = obj.closest("li");
        var star_area_index = li.index();
        for (var i = 0; i < 5; i++) {
            li.find("img").eq(i).attr("src", "images/" + stars[index][i]);//切换每个星星
        }
        $(".level").html(byIndexLeve(index));
    }, function() {
    })

    $(".block li").hover(function(e) {
    }, function() {
        var index = $(this).attr("data-default-index");//点击后的索引
        index = parseInt(index);
        $(".level").html(byIndexLeve(index-1));
        $(".order-evaluation ul li:eq(0)").find("img").attr("src","images/x1.png");
        for (var i=0;i<index;i++){

            $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","images/x2.png");
        }
    })
    $(".block li").find("img").click(function() {
        var obj = $(this);
        var li = obj.closest("li");
        var star_area_index = li.index();
        var index1 = obj.index();
        li.attr("data-default-index", (parseInt(index1)+1));
        var index = $(".block li").attr("data-default-index");//点击后的索引
        index = parseInt(index);
        $(".level").html(byIndexLeve(index-1));
        $(".order-evaluation ul li:eq(0)").find("img").attr("src","images/x1.png");
        for (var i=0;i<index;i++){
            $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","images/x2.png");
        }

    });
    //印象
    $(".order-evaluation-check").click(function(){
        if($(this).hasClass('checked')){
            //当前为选中状态，需要取消
            $(this).removeClass('checked');
        }else{
            //当前未选中，需要增加选中
            $(this).addClass('checked');
        }
    });
    //评价字数限制
    $("#TextArea1").keydown(function(){
    	var curLength=$(this).val().length;
    	if(curLength>140){
    		 var num=$(this).val().substr(0,140);
            $("#TextArea1").val(num);
            alert("感谢您的热情，您的输入已超出最大限度！" );
    	}else{
            $("#textCount").text(140-$("#TextArea1").val().length);
        }
    })
    
    //帮帮中心会员级别进度条
    $('.skillbar').each(function(){
		$(this).find('.skillbar-bar').animate({
			width:$(this).attr('data-percent')
		},3000);
	});
    
    
    
     
// //首页轮播 
// 	var wth=$(window).width()
// 	var i=0;
// 	var size=$(".Carousel-img>div").size()
// 	var clone=$(".Carousel-img>div").first().clone().css({width:wth})
// 	$(".Carousel-img").css({width:wth*(size+1)})
// 	$(".Carousel-img>div").css({width:wth})
// 	$(".Carousel-img").append(clone)
// 	function moveleft(){
// 		i++;

// 		if(i==size+1){
// 			$(".Carousel-img").css({left:0})
// 			i=1
// 		}
// 		$(".Carousel-img").stop().animate({left:i*-wth})
// 		 if(i==size){
//             $('.Carousel-Dot>li').eq(0).addClass('active').siblings().removeClass('active');
//         }
// 		$('.Carousel-Dot>li').eq(i).addClass('active').siblings().removeClass('active');
// 	}
// 	var t=setInterval(function(){
// 		moveleft()
// 	},5000)
// 	for(var r= 0;r<size;r++){
//         $('.Carousel-Dot').append("<li>"+"</li>")
// 	}
// 	 $('.Carousel-Dot>li').first().addClass("active")
// 	$('.Carousel-Dot li').hover(function(){
// 		clearInterval(t)
		
// 		var index=$(this).index()
// 		$(this).addClass("active").siblings().removeClass("active")
// 		$(".Carousel-img").stop().animate({left:index*-wth})
// 	},function(){
// 		t=setInterval(function(){
// 		moveleft()
// 	},5000)
// 	})
// 	$(window).resize(function(){
// 		 window.location.reload()	
// 	}) 
//财税网首页 
	$(".stipulate-ul-top li").hover(function(){
		var html=$.trim($(this).text())
		$(this).addClass("active").siblings('li').removeClass('active')
		if(html=="财税实务"){
			$(".stipulate-ul-bottom-one").show().siblings('ul').hide()
		}else if(html=="案例分析"){
			$(".stipulate-ul-bottom-two").show().siblings('ul').hide()
		}else{
			$(".stipulate-ul-bottom-three").show().siblings('ul').hide()
		}
	}) 



//问答-帮帮-内页 
	//团队任务
	$(".page-left-content ul li").hover(function(){
		$(this).find(".page-left-content-bg").show()
		$(this).children('p').css("color","#E0BB78")
	},function(){
		$(this).find(".page-left-content-bg").hide()
		$(this).children('p').css("color","#712238")
	})
	//团队动态导航
	$(".page-left-foot-title li").hover(function(){
		$(this).addClass("active").siblings().removeClass("active")
		var html=$.trim($(this).text())
		if(html=="帮派动态"){
			$(".page-one").show()
			$(".page-two").hide()
		}else{
			$(".page-one").hide()
			$(".page-two").show()
		}
	})
	//问答分享显示
	$(".change-questions-fx").hover(function(){
		$(this).parent().parent().siblings(".index-middle-div-tc").show();
	},function(){
		$(this).parent().parent().siblings(".index-middle-div-tc").hide();
	})
	//问答-帮帮-活动-我要报名
	$(".hdjsbtn01").click(function(){
		$(".hdjs-fill").stop().slideToggle()
	})
	//问答-帮帮-首页
	$(".index-middle-div>img").hover(function(){
		$(this).parent().siblings("div.index-middle-eject").show()
	},function(){
		$(this).parent().siblings("div.index-middle-eject").hide()
	})
	//帮帮-首页-排行榜
	
	$( '.index-middle-down .index-middle-right-top>p span').hover(function(){
		var idx = $(this).index();
		$('.index-middle-right-top').children('ul').hide().eq(idx).show()
	},function(){

	})
	
	
	$('.all-sort-list > .item').hover(function(){

			var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素

				h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素

				s = $(window).scrollTop(),									//获取游览器滚动了多少高度

				i = $(this).offset().top,									//当前元素滑过距离窗口多少像素

				item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度

				sort = $('.all-sort-list').height();						//父类分类列表容器的高度

			

			if ( item < sort ){												//如果子类的高度小于父类的高度

				if ( eq == 0 ){

					$(this).children('.item-list').css('top', (i-h));

				} else {

					$(this).children('.item-list').css('top', (i-h)+1);

				}

			} else {

				if ( s > h ) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度

					if ( i-s > 0 ){											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,

						$(this).children('.item-list').css('top', (s-h)+2 );

					} else {

						$(this).children('.item-list').css('top', (s-h)-(-(i-s))+2 );

					}

				} else {

					$(this).children('.item-list').css('top', 0 );

				}

			}	



			$(this).addClass('hover');

			$(this).children('.item-list').css('display','block');

		},function(){

			$(this).removeClass('hover');

			$(this).children('.item-list').css('display','none');

		});



		$('.item > .item-list > .close').click(function(){

			$(this).parent().parent().removeClass('hover');

			$(this).parent().hide();

		}); 
		
		
		
//资讯首页轮播图 
	// var i=0;
	// var dWidth=$('.zx_home_gg-lb div').width();
	// var clone=$('.zx_home_gg-lb div').first().clone();
	// $('.zx_home_gg-lb').append(clone)
	// var size=$('.zx_home_gg-lb div').size();
	// var BWidth=size*dWidth;
	// $('.zx_home_gg-lb').css({width:BWidth});
	// function moveleft(){
	// 	i++;
	// 	if(i>size-1){
	// 		$(".zx_home_gg-lb").css({left:0})
	// 		i=1;
	// 	}
	// 	$(".zx_home_gg-lb").stop().animate({left:-i*dWidth});
	// 	if(i>size-2){
	// 	$(".zx_home_gg-lb-ul li").eq(0).addClass("active").siblings().removeClass("active")
	// 	}
	// 	$(".zx_home_gg-lb-ul li").eq(i).addClass("active").siblings().removeClass("active")
	// }
	// for(var r=0;r<size-1;r++){
	// 	$(".zx_home_gg-lb-ul").append("<li>"+"</li>")
	// }
	// var t=setInterval(function(){
	// 	moveleft()
	// },2000)
	// $('.zx_home_gg-lb-ul li').first().addClass("active")
	// $('.zx_home_gg-lb-ul li').hover(function(){
	// 	clearInterval(t)
	// 	var index=$(this).index();
	// 	$(".zx_home_gg-lb").stop().animate({left:-index*dWidth});
	// 	$(this).addClass("active").siblings().removeClass("active")
	// },function(){
	// 	t=setInterval(function(){
	// 		moveleft()
	// 	},2000)
	// }) 


	$(".grzx-content-left-bottom>p span").click(function(){
		var Sinx=$(this).index();
		$(this).addClass("active").siblings('span').removeClass("active")
		$(".grzx-content-left-bottom").children('ul').eq(Sinx).show().siblings("ul").hide()
	})
	//帮帮-个人中心
	$(".grzx-right-nav li").click(function(){
		var Sinx=$(this).index();
		$(this).addClass("active").siblings().removeClass("active")
		$(".grzx-content-right").children('ul').eq(Sinx).show().siblings("ul").hide()
	}) 

//创建邦派字数限制
       $(".cjbp-gg textarea").keydown(function(){
        var tarLength=$(this).val().length;
    	if(tarLength>240){
    		 var num=$(this).val().substr(0,240);
            $(".cjbp-gg textarea ").val(num);
            alert("感谢您的热情，您的输入已超出最大限度！" );
    	}else{
            $(".cjbp-gg u").text(240-$(".cjbp-gg textarea").val().length);
        }
    })
    //创建邦派下面导航切换
     $(".wgldbp-left-foot-title li:lt(4)").hover(function(){
        $(this).addClass("active").siblings().removeClass("active")
        var tinx=$(this).index()
       $(".wgldbp-left-foot-nav").children("li").hide().eq(tinx).show()
     })
})


function showPdf(isShow) {
    var state = "";
    if (isShow) {
        state = "block";
    } else {
        state = "none";
    }
    var pop = document.getElementById("pop");
    pop.style.display = state;
    var lightbox = document.getElementById("lightbox");
    lightbox.style.display = state;
}
$(function(){
	//公共选项卡
	var tab = function(obj){
    var n = obj.index();
    var ch = obj.children();
    var sib = obj.parent().parent().next().children();
    if(ch[0] === undefined){
        obj.addClass('on').siblings().removeClass('on')
    }else{
        ch.addClass('on').parent().siblings().children().removeClass('on');
    }
    sib.eq(n).show().siblings().hide();
};
//我的问他选项卡功能
$(document).on('click','#tat ul li',function(){
	tab($(this));
	$(this).addClass("hover").siblings().removeClass("hover");
});
})
//帮帮中心-我管理的邦派-帮派设置-自动加入切换
//$(function(){
//	$(".cjbp-xz input").click(function(){
//		var check = $(this).is(":checked");
//		if(check == false){
//			$(this).siblings().hide();
//		}else{
//			$(this).siblings().show();
//		}
//	})
//})
//帮帮问答-分享插件
$(function(){
	window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
})
//帮帮问答列表 弹窗控制
$(function(){
    $(".change-questions-tc").click(function(){
		$(".questions-model").show();
		$(".new-bb-bg").css("overflow-y","hidden")
		$(".questions-model").scrollTop(0);
	})
	$(".questions-model-gb").click(function(){
		$(".questions-model").hide();
		$(".new-bb-bg").css("overflow-y","scroll")
	})
	$(".questions-model-bg").click(function(){
		$(".questions-model").hide();
		$(".new-bb-bg").css("overflow-y","scroll")
	})
	$(".questions-model-zd").click(function(){
		$(".questions-model").scrollTop(0);
	})
})
//$(function(){
//	//财税网首页手机版导航伸缩
//	var widH = $(window).height();
//	var widW = $(window).width();
//	$(".ny-480-bg").width(widW);
//	$(".ny-480-bg").height(widH);
//	$(".ny-480-nav").click(function(){
//		$(this).children('ul').stop().slideToggle();
//		if($(".ny-480-bg").css("display")=='none'){
//			$(this).find(".ny-480-bg").stop().show();
//			$('body').css({ "overflow-x":"hidden","overflow-y":"hidden"});
//			 $("body").on("touchmove",function(event){event.preventDefault;}, false)
//		}else{
//			$(this).find(".ny-480-bg").stop().hide();
//			$('body').css({ "overflow-x":"auto","overflow-y":"auto"});
//			$("body").off("touchmove")
//		}
//	})
//})
$(function(){
	//财税网首页活动悬浮效果
	$('.middle .help-activity-right-top').hover(function(){
		$(this).children('.firstimg-bg').show();
	},function(){
		$(this).children('.firstimg-bg').hide();
	})
	var widH = $(window).height();
	//财税网动态添加侧边栏
	var html ='<div class="top-div">';


	html+='<ul class="top-div-ul">';
	html+='<li class="top-div-top"><a href=""></a><i class="iconfont">&#xeb88;</i><p>返回顶部</p></li>';
	html+='<li >';
	html+='<a target="_blank" href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODA2MzIwM180NzY5ODJfNDAwODg3MzEzM18yXw">';
	html+='<i class="iconfont">&#xe80a;</i>';
	html+='<p>在线客服</p>';
	html+='</a>';
	html+='</li>';
	html+='<li class="top-div-ewm2"><a href=""></a><i class="iconfont">&#xe80b;</i>';
	html+='<div class="top-div-ewm">';
	html+= '<img src="/images/code.jpg" alt="">';
	//html +='<p style="color:#ccc">及时掌握财税动态和业务提醒</p>';
	html +='</div>';
	html +='</li>';

	html +='<li>';
	html+='<a target="_blank" id="cbl_fwxf_a">';
	html+='<i class="iconfont">&#xe7e1;</i>';
	html+='<p>服务续费</p>';
	html+='</a>';
	html+='</li>';
	html+='<li class="clearfix"><a href="'+snsUrl+'/help/knowledge/index.html" target="_blank"><i class="iconfont icon-dengpao"></i><p>帮助中心</p></a></li>';
	html+='<li class="clearfix"><a id="cbl_yjfk_a" target="_blank"><i class="iconfont icon-xiugai"></i><p>意见反馈</p></a></li>';
	html+='<li class="clearfix top-div-ss"><a ><i class="iconfont icon-guanbi2"></i></a></li>';
	html+='</ul>';
	html+='</div>';
	$('body').append(html)
	//财税网首页侧边栏
	$('.top-div-ewm2').hover(function(){
		$('.top-div-ewm').show();
	},function(){
		$('.top-div-ewm').hide();
	});
	$('.top-div ul li').hover(function(){
		var Pwid = $(this).find('p').outerWidth();
		$(this).find('p').css("left",-Pwid);

	},function(){
		var Pwid = $(this).find('p').outerWidth();
		$(this).find('p').css("left",Pwid);
	});
	$(document).scroll(function(){
		var WinST = $(this).scrollTop();
		if(WinST > widH){
			$('.top-div-top').css({opacity:1});
		}
		else{
			$('.top-div-top').css({opacity:0});
		}
	});
	var LSiz = $('.top-div-ul li').size();
	var LHig = $('.top-div ul li').outerHeight();
	var SSdiv = $('.top-div-ss').outerHeight();
	$('.top-div-ul').css({height:LSiz*LHig});
	$('.top-div-ss').click(function(){
		var ParH = $(this).parent('ul').outerHeight();
		if(ParH == (LSiz*LHig)){
			$(this).parent('.top-div-ul').css('height',SSdiv);
			$(this).find('i').addClass('icon-zuidahua1').removeClass(' icon-guanbi2')
		}else{
			$(this).parent('.top-div-ul').css('height',LSiz*LHig);
			$(this).find('i').addClass('icon-guanbi2').removeClass('icon-zuidahua1')
		}
		// $(this).siblings('li').animate({top:(LSiz - Ind) * Lhg})
	});
	$('.top-div-ewm').hover(function(){
		$(this).show();
	},function(){
		$(this).hide();
	})

	$('.top-div-top').click(function(){
		$(window).scrollTop(0)
	})

	$('#cbl_yjfk_a').mousemove(function(){
		$.ajax({
			url: snsUrl +"/testLogin.html?random="+Math.random(),
			type : "GET",
			async:false,
			success: function(result){
				if(result.data.code==2000){
					//window.open(snsUrl +"/help/feedback/toAdd.html");

					$('#cbl_yjfk_a').attr("href",snsUrl +"/help/feedback/toAdd.html");
				}else{
					//layer.alert("请先登录");
					//setTimeout(function(){
						//window.open(ucUrl + "/login?recallurl="+$.base64.btoa((snsUrl +"/help/feedback/toAdd.html").replace("https://","")));
						$('#cbl_yjfk_a').attr("href",ucUrl + "/login?recallurl="+$.base64.btoa((snsUrl +"/help/feedback/toAdd.html").replace("https://","")));
					//},1000);
				}
			},
			error:function(){
				//layer.alert("出错了");
			},
			dataType : "JSONP"
		});
	});

	$('#cbl_fwxf_a').mousemove(function(){
		$.ajax({
			url: snsUrl + "/pub/toPayServiceCharge?random=" + Math.random(),
			type: "GET",
			async:false,
			success: function (result) {
				if (result.toUrl != null) {


					$("#cbl_fwxf_a").attr("href",result.toUrl);

				} else {


					$("#cbl_fwxf_a").attr("href","http://pay.abc12366.cn");

				}
			},
			error: function () {

				$("#cbl_fwxf_a").attr("href","http://pay.abc12366.cn");
			},
			dataType: "JSONP"
		})
	});
})