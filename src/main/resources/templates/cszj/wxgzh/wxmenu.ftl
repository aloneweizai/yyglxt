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
        table tr td:nth-child(2n+1) {
            height: 50px;
            text-align: right;
        }

        .textMax {
            margin-left: 1px;
            width: 250px;
        }

        .noDiv_0 #articleDiv0{
            display: none;
        }
        .noDiv_0 #articleDiv1{
            display ;
        }
        .noDiv_1 #articleDiv0{
            display;
        }
        .noDiv_1 #articleDiv1{
            display: none;
        }
    </style>
</head>

<body class="g_wrapper g_wrapper_full page_edit g_survey">

<div class="g_subheader">
    <div class="g_content">
        <div class="sub_nav">
            <a class="nav_item" href="${ctx}/cszjs/gzhsz/list.php">公众号设置</a>
            <a class="nav_item current" href="${ctx}/cszjs/gzmenu/list.php">公众号菜单设置</a>
            <span style="margin-left: 300px;"><input type="button" id="wxtongbu" value="同步到微信" class="layui-btn">&nbsp;</span>

        </div>
    </div>
</div>
<div style="padding-top: 60px">
    <div class="fl">
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav" id="menuList">
            </ul>
        </div>
    </div>

    <div class="fl" style="left: 400px;">
        <form id="linkForm">
            <div id="noDiv"style="display: none" class="noDiv_1">
                <table class="layui-table" lay-size="sm">

                <tr>
                    <td>
                        菜单名称：
                    </td>
                    <td>
                        <input type="hidden" name="id">
                        <input type="hidden" name="parentId">
                        <input type="hidden" name="mkey">
                        <input type="hidden" name="media_id">
                        <input type="hidden" name="appid">
                        <input type="hidden" name="pagepath">

                        <input type="text" class="layui-input" name="name"/>
                    </td>
                    <td width="100px">

                    </td>
                </tr>
                <tr>
                    <td>
                        排序号：
                    </td>
                    <td>
                        <input type="text" class="layui-input" name="sort"/>
                    </td>
                    <td width="100px">

                    </td>
                </tr>
                <tr>
                    <td width="100px">状态：</td>
                    <td colspan="3">
                        <label><input type="radio" name="wxStatus" id="wxStatus" value="1">显示</label>
                        <label><input type="radio" name="wxStatus" id="wxStatus" value="0">隐藏</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        菜单点击事件：
                    </td>
                    <td>
                        <select id="type">
                            <option value="view">地址链接</option>
                            <option value="0">显示二级菜单</option>
                        </select>
                    </td>
                </tr>
                <tbody  id="articleDiv0">
                        <tr >
                            <td>
                                链接URL地址:
                            </td>
                            <td>
                                <textarea style="width: 250px;height: 100px" name="url"></textarea>
                                <span class="color_r2">请输入完整的http路径</span>
                            </td>


                        </tr>

                </tbody>
                <tbody  id="articleDiv1">
                        <tr >
                            <td>
                                二级菜单一:
                            </td>
                            <td>
                                <input type="text" class="layui-input" name="erji1"/>
                            </td>
                        </tr>
                        <tr >
                            <td>
                                二级菜单二:
                            </td>
                            <td>
                                <input type="text" class="layui-input" name="erji2"/>
                            </td>
                        </tr>
                        <tr >
                            <td>
                                二级菜单三:
                            </td>
                            <td>
                                <input type="text" class="layui-input" name="erji3"/>
                            </td>
                        </tr>
                        <tr >
                            <td>
                                二级菜单四:
                            </td>
                            <td>
                                <input type="text" class="layui-input" name="erji4"/>
                            </td>
                        </tr>
                        <tr >
                            <td>
                                二级菜单五:
                            </td>
                            <td>
                                <input type="text" class="layui-input" name="erji5"/>
                            </td>
                        </tr>
                </tbody>

                <tr>
                    <td></td>
                    <td>
                        <input type="button" id="submit" value="保存" class="layui-btn">
                        <input type="button" id="back" value="取消" class="layui-btn layui-btn-primary">

                    </td>
                </tr>
            </table>
            </div>
        </form>
    </div>
    <div class="clear"></div>
</div>
<script data-main="${ctx}/js/abc/cszj/wxmenu.js" src="${ctx}/js/require.js"></script>
</body>
</html>
