<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8"/>
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
    <form name="adpage_add_form" id="linkForm">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
            <#if adpage??>
                <tr>
                    <input type="hidden" name="adpage" id="adpageId" value="${adpage.id!}">
                    <td width="90">广告页名称：</td>
                    <td colspan="3">
                        <input type="text" maxlength="100" name="name" id="name" value="${adpage.name!}" data-rule="required;">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="90">外链地址</td>
                    <td colspan="3">
                        <input type="text" name="link" id="link" value="${adpage.link!}"data-rule="url;">
                    </td>
                </tr>
                <tr>
                    <td>广告页图片：</td>
                    <td>
                        <input type="file" name="FILE01" id="FILE01" style="width:200px;display: inline-block;" >
                        <#if adpage.url?exists>
                            <img id="imgHeadPhoto1" src="${adpage.url!}" style="height: 80px;" alt=""/>
                        <#else>
                            <img id="imgHeadPhoto1" src="${ctx}/images/default.jpg" style="height: 80px;" alt=""/>
                        </#if>
                        <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出1024K（jpg、png、bmp）</span>
                    </td>
                    <td>
                        <div id="imgDiv1">
                            <div id="divPreview1">

                            </div>
                        </div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td width="90">排序：</td>
                    <td colspan="3">
                        <input type="text" value="${adpage.sort!}" name="sort" id="sort" data-rule="required;digits;">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="90">样式</td>
                    <td colspan="3">
                        <input type="text" name="views" id="views" value="${adpage.style!}">
                    </td>
                </tr>
                <tr>
                    <td>显示</td>
                    <td colspan="3">
                        <label>
                            <input type="radio" name="showName" id="showName" <#if adpage.showName>checked</#if>
                                   value="1">是
                            <input type="radio" name="showName" id="showName" <#if !adpage.showName>checked</#if>
                                   value="0">否
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td colspan="3">
                        <label>
                            <input type="radio" name="status" id="status" <#if adpage.status>checked</#if> value="1">启用
                            <input type="radio" name="status" id="status" <#if !adpage.status>checked</#if> value="0">停用
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>有效期起止：</td>
                    <td colspan="3">
                        <label>
                            <input type="text" id="startTime" class="layui-input" name="startTime" style=" width:130px;" data-rule="起始时间:required;date;"
                                   value="${(adpage.startTime?string("yyyy-MM-dd"))!}">至
                            <input type="text" id="endTime" class="layui-input" name="endTime" style=" width:130px;" data-rule="结束时间:required;date;match(gt, startTime, date)"
                                   value="${(adpage.endTime?string("yyyy-MM-dd"))!}">
                        </label>
                    </td>
                </tr>
            </#if>
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
