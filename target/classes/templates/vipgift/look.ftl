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
            <input type="hidden" name="id" value="${(vipgift.id)!}">
            <tr>
                <td width="200">礼物名称：</td>
                <td colspan="3"><span>${(vipgift.name)!}</span></td>
            </tr>
            <tr>
                <td width="200">礼物图片：</td>
                <td colspan="3"><img height='126' width='206' style='margin-left:10px;max-width:206px' src='${imgPth!}${(vipgift.imageUrl)!}' /></td>
            </tr>
            <tr>
                <td width="200">礼物简介：</td>
                <td colspan="3">
                    ${(vipgift.introduction)!}
                </td>
            </tr>
            <tr>
                <td width="200">会员等级：</td>
                <td colspan="3">
                    ${(vipgift.category)!}
                </td>
            </tr>
            <tr>
                <td width="200">排序：</td>
                <td colspan="3">
                    ${(vipgift.sort)!}
                </td>
            </tr>
            <tr>
                <td width="200">库存：</td>
                <td colspan="3">
                    ${(vipgift.stock)!}
                </td>
            </tr>
            <tr>
                <td width="200">销售价格：</td>
                <td colspan="3">
                    ￥${(vipgift.sellingPrice)!}
                </td>
            </tr>
            <tr>
                <td width="200">成本价格：</td>
                <td colspan="3">
                    ￥${(vipgift.costPrice)!}
                </td>
            </tr>
            <tr>
                <td width="200">礼物状态：</td>
                <td colspan="3">
                   <#if vipgift.status?? && vipgift.status == '0'> 
				       删除
				   <#elseif vipgift.status?? && vipgift.status == '2'>
				       上架
				   <#elseif vipgift.status?? && vipgift.status == '1'>
				       下架
				   </#if>
                </td>
            </tr>
            <tr>
                <td width="200">创建时间：</td>
                <td colspan="3">
                    ${(vipgift.createTime?string("yyyy-MM-dd HH:mm:ss"))!}
                </td>
            </tr>
            <tr>
                <td width="200">修改时间：</td>
                <td colspan="3">
                    ${(vipgift.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}
                </td>
            </tr>
            <tr>
                <td width="200">详细介绍：</td>
                <td colspan="3">
                    ${(vipgift.details)!}
                </td>
            </tr>
           
        </table>
    </form>
</div>
</body>
</html>