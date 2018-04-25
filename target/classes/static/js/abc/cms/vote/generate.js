require(["../../../config"], function () {
    require(["jquery.full","qrcode"], function ($,qrCode) {

        var url = $("#generated_url").val();

        new qrCode($("#vote_qrcode_container").get(0),{
            text: url,
            width: 300,
            height: 300,
            colorDark : '#58a6e7',
            colorLight : '#ffffff',
            correctLevel : qrCode.CorrectLevel.H
        });
        $("#vote_qrcode_container").css("border","");

        $("#vote_qrcode_container").on("click",function(){
            var base64 = $(this).find("img").attr("src");
            window.open(base64);
        });

        $("#vote_baseinfo").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.msg('请先保存该投票项', {icon: 5});
                return ;
            }

            location.href = ctx + "/cms/vote/add.php?voteId="+voteId;
        });

        $("#vote_publish").on("click",function(){
            var voteId = $(this).data("voteid");
            if(!voteId){
                layer.msg('请先保存该投票项', {icon: 5});
                return ;
            }

            location.href = ctx + "/cms/vote/publish.php?voteId="+voteId;
        });

    });
});
