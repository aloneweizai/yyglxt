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
            <#if wxuser??>
                <tr>
                    <td width="90">用户昵称：</td>
                    <td>${wxuser.nickname!}</td>
                </tr>
                <tr>
                    <td width="90">OPENID：</td>
                    <td>
                    ${wxuser.openid!}
                    </td>
                </tr>
                <tr>
                    <td width="90">关注时间：</td>
                    <td>
                    ${(wxuser.subscribe_time?string("yyyy-MM-dd HH:mm:ss"))!}
                    </td>
                </tr>
                <tr>
                    <td width="90">所在国家：</td>
                    <td>
                    ${wxuser.country!}
                    </td>
                </tr>
                <tr>
                    <td width="90">所在省份：</td>
                    <td>
                    ${wxuser.sort!}
                    </td>
                </tr>
                <tr>
                    <td width="90">所在城市：</td>
                    <td>
                    ${wxuser.city!}
                    </td>
                </tr>
            </#if>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" name="back" id="back" value="返回" class="layui-btn layui-btn-primary">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cszj/wxuser.js" src="${ctx}/js/require.js"></script>
</body>
</html>
