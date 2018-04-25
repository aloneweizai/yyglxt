<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8" />
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!-- 友情链接新增-->
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="linkForm" id="linkForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <tr>
                    <input type="hidden" name="adpage" id="adpageId" value="">
                    <td width="90">广告页名称</td>
                    <td colspan="3">
                        <input type="text" maxlength="100" name="name" id="name" data-rule="required;" class="layui-input" style="width: 300px;">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="90">外链地址</td>
                    <td colspan="3">
                        <input type="text" name="link" id="link" value="http://" data-rule="url;" class="layui-input" style="width: 300px;">
                    </td>
                </tr>
                <tr>
                    <td>广告页图片：</td>
                    <td colspan="3">
                        <input type="file" name="FILE01" id="FILE01" style="width:200px;display: inline-block;" class="layui-input" data-rule="required;"  >
                        <img id="imgHeadPhoto1" src="${ctx}/images/default.jpg" style="height: 80px; width: 80px;" alt=""/>
                        <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出1024K（jpg、png、bmp）</span>
                        <div id="imgDiv1" hidden="hidden">
                            <div id="divPreview1">

                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="90">排序：</td>
                    <td colspan="3">
                        <input type="text" name="sort" id="sort" class="layui-input" style="width: 300px;" data-rule="required;digits;">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="90">样式</td>
                    <td colspan="3">
                        <input type="text" name="views" id="views" class="layui-input" style="width: 300px;">
                    </td>
                </tr>
                <tr>
                    <td>显示</td>
                    <td colspan="3">
                        <label>
                            <input type="radio" name="showName" id="showName" checked="checked" value="1" title="是" >
                            <input type="radio" name="showName" id="showName" value="0" title="否">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td colspan="3">
                        <label>
                            <input type="radio" name="status" id="status" checked="checked" value="1" title="启用">
                            <input type="radio" name="status" id="status" value="0" title="停用">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>有效期起止：</td>
                    <td colspan="3">
                        <label>
                            <input type="text" class="layui-input" id="startTime" value="${(startTime)!}"  name="startTime" style="width: 130px" data-rule="起始时间:required;date;">
                            至
                            <input type="text" class="layui-input" id="endTime" value="${(endTime)!}"name="endTime" style="width: 130px" data-rule="结束时间:required;date;match(gt, startTime, date)">
                        </label>
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="hidden" style="width: 100px;" id="adpageName" value="${(adpageRq.name)!}">
                        <input type="hidden" style="width: 100px;" id="page" value="${(adpageRq.page)!}">
                        <input type="button" name="submit" id="submit" value="提交" class="layui-btn">
                        <a  href="${referer}" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cszj/adpage.js" src="${ctx}/js/require.js"></script>
</body>
</html>
