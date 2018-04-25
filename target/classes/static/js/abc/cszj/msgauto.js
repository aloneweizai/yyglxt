require(["../../config"], function () {
    require(["jquery.full", "qrcode"], function ($, qrCode) {
        $("#save_btn").click(function () {


            var url = "update_msg.php";
            var data1 ={
                "id":$("#keyId").val(),
                "msgType":$("input[name='msgType']:checked").val()

            };
            if(data1.msgType == "news"){
                data1.content = $("#articleDiv2 textarea").val().trim();
            }else   if(data1.msgType == "text"){
                data1.content = $("#articleDiv1 textarea").val().trim();
            }else {
                data1.content = $("#articleDiv0 textarea").val().trim();
            }
                data1 = JSON.stringify(data1);

            $.ajax({
                type: "POST",
                url: url,
                data: data1,
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    //console.log(data);
                    if (data.code == '2000') {
                        layer.msg("操作成功", {icon: 1, time: 1000});
                        setTimeout(function () {
                            window.location.reload();//刷新当前页面.
                        }, 2000);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            });

        });
                $('#status0').on('click',function (){
                    $('#noDiv').attr("class", "noDiv_0");
                })
                $('#status1').on('click',function (){
                    $('#noDiv').attr("class", "noDiv_text");

                })
                $('#status2').on('click',function (){
                    $('#noDiv').attr("class", "noDiv_news");
                })
        /*var url = $("#wx_url").val();

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
         */

    });
});