<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
	<style>
	body{background: #fff;}
	.zx_wenzhang{background: #fff;padding: 50px 30px 30px;}
	.zx_wenzhang_title{text-align: center;border-bottom: 1px dashed #ddd;margin-bottom: 20px;}
	.detatime{line-height: 20px;font-size: 12px;color: #999;margin-top: 20px;}
	.clear{clear: both;}
	.zx_zhengwen{line-height: 34px;margin-bottom: 20px;border-bottom: 1px dashed #ddd;padding-bottom: 20px;text-indent: 2em;font-size: 15px;}
	</style>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="zx_liebiao_left2">
        <div class="zx_wenzhang">
            <div class="zx_wenzhang_title">
                <h3>${noticeDto.title!}</h3>
                <div class="detatime " style="margin: 20px 0; height: 10px;">
                    <span style="margin-right: 10px;">作者：${noticeDto.author!}</span>
                    <span>发布时间：${noticeDto.createTime!}</span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="zx_zhengwen">
                <p>${noticeDto.content!}</p>
            </div>
        </div>
    </div>
</body>
</html>