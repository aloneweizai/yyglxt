<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
        var imgurl="${imgPth!}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/system/productspread/downloadproduct.php" method="get" id="consumerListForm"
          class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <h1 style="font-size: large;margin-top: 20px;margin-bottom: 10px">请输入长网址：</h1>
                <div class="layui-inline">
                    <div class="layui-input-inline"  style="width:80px;margin-left: 30px">
                        <select  name="type"  id="type" >
                            <option value="2">微信</option>
                            <option value="1">tiny</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline"style="width:500px ">
                        <input type="text" value="${(url)!}" name="url" class="layui-input" id="url">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="myspread" class="layui-btn">压缩一下</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b" id="tgm" style="display: none">
            <div class="layui-inline">
                <table style="width: 320px">
                    <tr>
                        <td style="height: 30px">
                            您生成的短网址：
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-input-inline">
                          <textarea style="width: 200px;height: 200px" id="wx_url" value=""></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a class="tuiguangfuzhi layui-btn layui-btn-normal layui-btn-mini" style="margin-top: 5px" href="javascript:;" fzid="wx_url" name="fz">复制</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="nycon_list_b" id="spreadImg">
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/cms/long2short.js" src="${ctx}/js/require.js"></script>
</body>
</html>