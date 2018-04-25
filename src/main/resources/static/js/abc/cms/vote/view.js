require(["../../../config"], function () {
    require(["jquery.full", "qrcode"], function ($, qrCode) {

        var url = $("#generated_url").val();
        new qrCode($("#vote_qrcode_container").get(0),{
            text: url,
            width: 300,
            height: 300,
            colorDark : '#58a6e7',
            colorLight : '#ffffff',
            correctLevel : qrCode.CorrectLevel.H
        });

        $(document).on("click","#vote-result",function () {
            var voteId = $(this).data("voteid");
            location.href = ctx + "/cms/vote/statistics.php?voteId=" + voteId;
        });

        $(document).on("click","#vote-url",function () {
            var voteId = $(this).data("voteid");
            location.href = ctx + "/cms/vote/view.php?voteId=" + voteId;
        });

        $(document).on("click","#vote-index",function () {
            location.href = ctx + "/cms/vote/list.php";
        });

    });
});