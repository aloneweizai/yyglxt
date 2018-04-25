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
                    ${adpage.name!}
                    </td>
                </tr>
                <tr>
                    <td width="90">外链地址</td>
                    <td colspan="3">
                    ${adpage.link!}
                    </td>
                </tr>
                <tr>
                    <td>广告页图片：</td>
                    <td colspan="3">
                        <#if adpage.url!="">
                        <img src="${adpage.url}" style="height: 80px;">
                    <#else>
                        <img src="${ctx}/images/zanwu.png" style="height: 50;">
                    </#if>
                    </td>
                </tr>
                <tr>
                    <td width="90">排序：</td>
                    <td colspan="3">
                    ${adpage.sort!}
                    </td>
                </tr>
                <tr>
                    <td width="90">样式</td>
                    <td colspan="3">
                    ${adpage.style!}
                    </td>
                </tr>
                <tr>
                    <td>显示</td>
                    <td colspan="3">
                        <label>
                            <#if adpage.showName>是<#else>否</#if>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td colspan="3">
                        <label>
                            <#if adpage.status>启用<#else>停用</#if>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>有效期起止：</td>
                    <td colspan="3">
                        <label>
                        ${(adpage.startTime?string("yyyy-MM-dd HH:mm:ss"))!} 至  ${(adpage.endTime?string("yyyy-MM-dd HH:mm:ss"))!}
                        </label>
                    </td>
                </tr>
            </#if>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cszj/adpage.js" src="${ctx}/js/require.js"></script>
</body>
</html>
