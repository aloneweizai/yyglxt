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
    <link rel="stylesheet" type="text/css" href="${ctx}/css/tongji/page_edit.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style type="text/css">
        table tr td:nth-child(2n+1){
            height:50px;
            text-align:right;
        }
        .textMax {
            margin-left:1px;
            width: 90%;
        }
    </style>
</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">
<div class="container-fluid m_top_30 nycon_list" >
<div class="g_subheader">
    <div class="g_content">
            <div class="sub_nav">
                <a class="nav_item current" href="${ctx}/cszjs/gzhsz/list.php">公众号设置</a>
                <a class="nav_item" href="${ctx}/cszjs/gzmenu/list.php">公众号菜单设置</a>
            </div>
    </div>
</div>


    <form   id="linkForm">
        <div class="nycon_list_b" style="margin-top: 70px;">
            <table class="layui-table" lay-size="sm">
                <tbody>
                <tr>
                    <td style="width: 200px;">
                        公众号名称：
                    </td>
                    <td>
                        <input type="text" class="layui-input" name="gzhName" value="${(gzhInfo.gzhName)!}" /><input type="hidden" name="id" value="${(gzhInfo.id)!}">
                    </td>
                </tr>

                <tr>
                    <td>
                        AppId：
                    </td>
                    <td>
                        <input type="text"name="appid"  class="layui-input" value="${(gzhInfo.appid)!}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        AppSecret:
                    </td>
                    <td  >
                        <input type="text" class="layui-input" name="secret" value="${(gzhInfo.secret)!}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        TOKEN:
                    </td>
                    <td  >
                        <input type="text" class="layui-input" name="tokenStr" value="${(gzhInfo.tokenStr)!}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        URL:
                    </td>
                    <td  >
                        <input type="text" class="layui-input" name="serverUrl" value="${(gzhInfo.serverUrl)!}" />
                    </td>
                </tr>
                </tbody>
                <tr>
                    <td></td>
                    <td  >
                        <button type="button"   id="submit" class="layui-btn">保存</button>
                        <button type="button" id="back" class="layui-btn layui-btn-primary">取消</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cszj/wxgzh.js" src="${ctx}/js/require.js"></script>
</body>
</html>
