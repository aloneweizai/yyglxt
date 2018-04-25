function validLogin_linkto(url){

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
                window.location.href = url;
            }else{
                setTimeout(function(){
                    window.location.href = ucUrl + "/login?recallurl="+$.base64.btoa(url.replace("https://",""));
                },1000);
            }

        },
        error: function (e) {
            //console.log(e);
        }
    });

}

//更换绑定手机
function ghbdsjClick(){
    validLogin_linkto( ucUrl+ "/index.html?url="+$.base64.btoa(ucUrl+"/userinfo/userinfolist.html#ghbdsj"));
}

//实名认证
function smrzClick(){
    validLogin_linkto(ucUrl + "/index.html?url="+$.base64.btoa(ucUrl+"/userinfo/userinfolist.html#smrz"));
}

//积分充值
function jfczClick(){
    validLogin_linkto(ucUrl + "/member/member_index.html?url="+$.base64.btoa(ucUrl+"/pointsExchange/points.php#jfcz"));
}

//我的积分
function wdjfClick(){
    validLogin_linkto(ucUrl + "/member/member_index.html?url="+$.base64.btoa(ucUrl+"/pointsExchange/points.php"));
}

//索取发票
function sqfpClick(){
    validLogin_linkto(ucUrl + "/index.html?url="+$.base64.btoa(ucUrl+"/userinfo/userinvoice.html"));
}

//忘记申报密码
function wjmmClick(){
    validLogin_linkto(ucUrl + "/index.html?url=" + $.base64.btoa(ucUrl + "/userinfo/enterprise.html#wjmm"));
}

//申报服务续费
function sbfwxfClick(){
    $.ajax({
        url: snsUrl + "/pub/toPayServiceCharge?random=" + Math.random(),
        type: "GET",
        success: function (result) {
            if (result.toUrl != null) {
                window.open(result.toUrl);
            } else {
                window.open("http://pay.abc12366.cn");
            }
        },
        error: function () {
            //layer.alert("出错了");
            window.open("http://pay.abc12366.cn");
        },
        dataType: "JSONP"
    })
}

//申报服务续费_服务静态页面
function sbfwxfClick_servicePage(obj){
    $.ajax({
        url: snsUrl + "/pub/toPayServiceCharge?random=" + Math.random(),
        type: "GET",
        success: function (result) {
            if (result.toUrl != null) {
                //window.open(result.toUrl);
                $(obj).attr("href",result.toUrl);
            } else {
                //window.open("http://pay.abc12366.cn");
                $(obj).attr("href","http://pay.abc12366.cn")
            }
        },
        error: function () {
            //layer.alert("出错了");
            //window.open("http://pay.abc12366.cn");
            $(obj).attr("href","http://pay.abc12366.cn")
        },
        dataType: "JSONP"
    })
}


//我的消息
function wdxx(){
    validLogin_linkto(ucUrl + "/index.html?url="+$.base64.btoa(ucUrl+"/userinfo/user_message.html"));
}

//基本资料
function jbzl(){
    validLogin_linkto(ucUrl + "/index.html?url="+$.base64.btoa(ucUrl+"/userinfo/userinfolist.html"));
}

//会员中心
function hyzx(){
    validLogin_linkto(ucUrl + "/member/member_index.html?url="+$.base64.btoa(ucUrl+"/member/my_index.html"));
}