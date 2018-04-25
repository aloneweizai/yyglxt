<#assign ctx=request.getContextPath()>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="财税专家，ABC4000，智能申报，AI企易税，e企惠税，易企惠税，ABC4000惠报税，电子申报，电子税务局，电子税局,掌上办税厅,营改增,互联网+税务,帮邦,">
    <meta name="description" content="由艾博克公司打造的财税云服务平台，提供在线涉税业务办理、信息查询、在线培训、知识库学习等财税综合服务，为税务机关提供财税信息化解决方案" />
    <title>课堂视频</title>
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/helpcenter/bootstrap.css">-->
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/iconfont.css">-->
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/webpage_main.css">-->
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/public.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/video-js.css">
    <script type="text/javascript" src="${ctx}/js/abc/cms/course/sewise.player.min.js"></script>

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?a6abbd47e203dea0e025df6e6476c084";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<style>
    .bjys{
        color: #ffffff;
    }
</style>
<body style="background: #ececec;">

<#if type=='video'||type=='audio'>
<div id="player" style="width: 840px;height: 500px;">
    <#if ploadWay??&&ploadWay==0>
        <#if link??&&link?index_of('m3u8')!=-1>
            <script>
                SewisePlayer.setup({
                    server: "vod",
                    type: "m3u8",
                    autostart: "true",
                    poster: "${path!""}${data.cover!""}",
                    videourl: "${link!""}",
                    skin: "vodWhite",
                    title: "${data.title!""}",
                    lang: "zh_CN",
                    claritybutton: 'disable'
                }, "player");
            </script>
        <#else>
            <script>
                SewisePlayer.setup({
                    server: "vod",
                    type: "mp4",
                    autostart: "true",
                    poster: "${path!""}${data.cover!""}",
                    videourl: "${link!""}",
                    skin: "vodWhite",
                    title: "${data.title!""}",
                    lang: "zh_CN",
                    claritybutton: 'disable'
                }, "player");
            </script>
        </#if>
    <#else>
        <#if fileSite??&&fileSite?index_of('m3u8')!=-1>
            <script>
                SewisePlayer.setup({
                    server: "vod",
                    type: "m3u8",
                    autostart: "true",
                    poster: "${path!""}${data.cover!""}",
                    videourl: "${path!}${fileSite!""}",
                    skin: "vodWhite",
                    title: "${data.title!""}",
                    lang: "zh_CN",
                    claritybutton: 'disable'
                }, "player");
            </script>
        <#else>
            <script>
                SewisePlayer.setup({
                    server: "vod",
                    type: "mp4",
                    autostart: "true",
                    poster: "${path!""}${data.cover!""}",
                    videourl: "${path!}${fileSite!""}",
                    skin: "vodWhite",
                    title: "${data.title!""}",
                    lang: "zh_CN",
                    claritybutton: 'disable'
                }, "player");
            </script>
        </#if>
    </#if>

</div>

<#else>
<div class="h500">
    <div class="lightbox" id="lightbox"></div>
    <div id="pop" class="pop">
        <iframe src="${ctx}/js/lib/generic/web/viewer.html?name=${path!}${fileSite!""}" frameborder="0" id="pdfContainer" name="pdfContainer" ></iframe>
    </div>
</div>
</#if>


<!-- main end -->
<!-- footer  -->
<!-- footer end -->

</body>
</html>