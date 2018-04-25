require(["../../../config"], function () {
    require(["jquery.full","qrcode"], function ($,qrCode) {

        var url = $("#wx_url").val();

        var qrcode=new qrCode($("#event_erweima").get(0),{
            text: url,
            width: 300,
            height: 300,
            colorDark : '#58a6e7',
            colorLight : '#ffffff',
            correctLevel : qrCode.CorrectLevel.H
        });


        $('a[name="fz"]').on('click',function (){
            var fzid=$(this).attr('fzid');
            var Url2=document.getElementById(fzid);
            Url2.select(); // 选择对象
            document.execCommand("Copy"); // 执行浏览器复制命令
            alert("已复制好，可贴粘。");
        })


        $('#xiazaierweima').on('click',function (){
           var canvas=$('#event_erweima canvas');
            var ff=canvas.get(0).getContext( '2d' );
            var url = canvas.get(0).toDataURL("image/png");
            var triggerDownload = $("#xiazai").attr("href", url).attr("download", "推广二维码.png");
            triggerDownload[0].click();
        })

        $('#publish_survey').click(function (){
            //window.location.href = ctx+"/cms/activity/list.php";
            //window.location.href = document.referrer;
            window.location.href =sessionStorage.getItem("activity_url");
        })

    });
});