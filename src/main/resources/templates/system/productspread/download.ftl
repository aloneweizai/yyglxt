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
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">公司产品</label>
                    <div class="layui-input-inline">
                        <select name="productSpreadId">
                            <option>--请选择--</option>
                        <#if productSpreadRs?? && ( productSpreadRs?size gt 0 )>
                            <#list productSpreadRs as productSpread>
                                <option <#if productSpreadId??&&productSpreadId == productSpread.id> selected </#if> value="${(productSpread.id)!}">${(productSpread.name)!}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b">
        <#if SpreadRs??&&SpreadRs.productImgs?? && (SpreadRs.productImgs?size > 0) >
            <#list SpreadRs.productImgs as productImg>
                <div class="layui-inline">
                    <table style="width: 320px">
                        <tr>
                            <td>
                            ${(productImg.description)!}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-input-inline">
                                    <#if productImg.imageUrl??&&productImg.imageUrl != "">
                                        <img title="点击放大图片" class="showbig" height="200" width="200"
                                             src="${imgPth}/${productImg.imageUrl!}">
                                    <#else>
                                        <img height="300" width="500" src="${ctx}/images/default.jpg">
                                    </#if>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="${imgPth}/${productImg.imageUrl!}" download="图片">下载</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </#list>
        </#if>
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