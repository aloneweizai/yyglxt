<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="consumer_edit">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="id" value="${(productSpreadRs.id)!}">
            <tr>
                <td width="30%">产品名称</td>
                <td colspan="3"><span>${(productSpreadRs.name)!}</span></td>
            </tr>
            <tr>
                <td colspan="4">
                    <table width="100%" class="mdtable" id="producttable" cellspacing="1" cellpadding="5">
                        <tr>
                            <th width="70%" class="mdtablethtd">图片</th>
                            <th width="30%" class="mdtablethtd">图片描述</th>
                        </tr>
                    <#if productSpreadRs??&&productSpreadRs.productImgs?? && (productSpreadRs.productImgs?size > 0) >
                        <#list productSpreadRs.productImgs as productImg>
                            <tr did="">
                                <td  width="70%" >
                                    <img height='126' width='206' style='margin-left:10px;max-width:206px' src='${imgPth!}${(productImg.imageUrl)!}' />
                                </td>
                                <td  width="30%"  class="mdtablethtd">
                                ${(productImg.description)!}
                                </td>
                            </tr>
                        </#list>
                    </#if>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>