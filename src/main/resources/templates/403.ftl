<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#assign ctx=request.getContextPath()>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>500</title>
    <style>
        *{margin: 0;padding: 0; font-family: "微软雅黑";}
        .photo1{ position:absolute; width: 700px; height: 400px; top: 50%; left: 50%; margin-left: -350px; margin-top: -200px; background-image: url(${ctx}/images/baocuo.jpg);}
        .photo1 .text{ position: relative; top:20px; left: 384px; color: #666;margin-top:5px;word-wrap:break-word;width:300px;}
    </style>
</head>
<body>
<div class="photo1">
    <div class="text" style="font-weight:bold;font-size:20px;margin-bottom:25px;top:150px;width: 260px">您没有访问权限，请先获取相应权限。</div>
</div>
</body>
</html>