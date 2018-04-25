<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        /*input[type="text"] {
            border: none;
            readonly:readonly;
            disabled:disabled;
            width: 400px;
        }*/
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form id="linkForm">
        <div class="nycon_list_b">
        <#if obj??>
            <input type="hidden" name="id" id="id" value="${obj.id!}">

        </#if>
            <table class="layui-table" lay-size="sm">
                <tr>
                    <td width="110px">用户：</td>
                    <td colspan="3">
                         ${(obj.username)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">纳税人识别号：</td>
                    <td colspan="3">
                    ${(obj.nsrsbh)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">会话ID：</td>
                    <td colspan="3">
                         ${(obj.sessionId)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">时间：</td>
                    <td colspan="3">
                        ${(obj.createTime?string("yyyy-MM-dd HH:mm:ss"))!}
                    </td>
                </tr>

                <tr>
                    <td width="110px">页面标题：</td>
                    <td colspan="3">
                         ${(obj.pageTitle)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">页面路径：</td>
                    <td colspan="3">
                         ${(obj.pageUrl)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">Referer路径：</td>
                    <td colspan="3">
                        ${(obj.referer)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">停留时长(秒)：</td>
                    <td colspan="3">
                         ${(obj.stayLong)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">操作功能：</td>
                    <td colspan="3">
                         ${(obj.feature)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">使用系统：</td>
                    <td colspan="3">
                         ${(obj.appName)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">访问IP：</td>
                    <td colspan="3">
                    ${(obj.ip)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">访问地点：</td>
                    <td colspan="3">
                         ${(obj.location)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">浏览器类型：</td>
                    <td colspan="3">
                         ${(obj.browseType)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">浏览器版本：</td>
                    <td colspan="3">
                         ${(obj.browseVersion)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">操作系统类型：</td>
                    <td colspan="3">
                         ${(obj.os)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">设备类型：</td>
                    <td colspan="3">
                         ${(obj.device)!}
                    </td>
                </tr>
                <tr>
                    <td width="110px">备注：</td>
                    <td colspan="3">
                         ${(obj.remark)!}
                    </td>
                </tr>
             </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/Lottery.js" src="${ctx}/js/require.js"></script>
</body>
</html>
