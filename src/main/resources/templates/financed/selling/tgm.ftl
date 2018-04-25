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
    <style>
        .showbig {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/system/productspread/downloadproduct.php" method="get" id="consumerListForm"
          class="layui-form">
        <div class="layui-form-top" style="margin-top: -10px;">
            <table width="100%" style="border-bottom:1px solid #ccc;height: 30px"><tr><td style="width: 50%;text-align: left">创建我的推广码</td><td style="width: 50%;text-align: right">我的推荐人代码:${(username)!}</td></tr></table>
        </div>
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">产品名称</label>
                    <div class="layui-input-inline" style="width:300px ">
                        <select name="productSpreadId" id="productSpreadId">
                            <option value="">--请选择--</option>
                        <#if productSpreadRs?? && ( productSpreadRs?size gt 0 )>
                            <#list productSpreadRs as productSpread>
                                <option <#if productSpreadId??&&productSpreadId == productSpread.id> selected </#if>
                                        value="${(productSpread.id)!}"
                                        url="${productSpread.url!}">${(productSpread.name)!}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: auto;">
                        <div id="myspread" class="layui-btn">生成推广码</div>
                        <div id="productspread" class="layui-btn">下载宣传页</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b" id="tgm" style="display: none; text-align: center;">
            <div class="layui-inline">
                <table style="width: 320px">
                    <tr>
                        <td>
                            推广二维码：
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-input-inline">
                                <img style="width: 100%;" id="weixin_ewm"></img>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a class="tuiguangfuzhi" href="javascript:;" id="xiazaierweima">下载二维码</a>
                            <a id="tttt"></a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="layui-inline">
                <table style="width: 320px">
                    <tr>
                        <td>
                            推广链接：
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
                            <a class="tuiguangfuzhi" href="javascript:;" fzid="wx_url" name="fz">复制</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="nycon_list_b" id="spreadImg" style="padding-top: 20px; text-align: center;">
        </div>
    </form>
</div>

<div id="shoimg"
     style="position:absolute;left:50%;top:100px;width:800px;height:600px;z-index:1000;background:white;display:none;cursor:pointer;"
     title="点击关闭">
    <img src='' style='width:100%;height:100%'/>
</div>
<script data-main="${ctx}/js/abc/system/productspread.js" src="${ctx}/js/require.js"></script>
</body>
</html>