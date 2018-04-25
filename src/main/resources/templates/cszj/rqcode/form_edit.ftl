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
    <style type="text/css">

        .textMax {
            margin-left:1px;
            width: 400px;
        }
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="rqcode_add_form" id="linkForm" action="${ctx}/cszjs/wxqrcode/list.php" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">

                <tr>

                    <td width="90">名称：</td>
                    <td colspan="3">
                        <input type="text"  name="name" class="layui-input" value="${(rqcode.name)!}" style="width:300px;">
                        <span class="color_r2">*</span>

                        <input type="hidden" name="id"  value="${(rqcode.id)!}">
                    </td>
                </tr>
                <#if rqcode??>
                    <tr>
                        <td width="90">二维码：</td>
                        <td colspan="3">
                            <img src="${rqcode.image!}" style="height: 80px;">
                        </td>
                    </tr>
                </#if>
                <tr>
                    <td width="90">描述：</td>
                    <td colspan="3">
                        <input type="text"  name="description"  class="layui-input"  value="${(rqcode.description)!}" style="width:300px;">

                    </td>
                </tr>

                <tr>
                    <td width="90">回复类型：</td>
                    <td colspan="3">
                        <div  style="width:300px;">
                         <select id="type" name="type" class="layui-input" >
                             <option value="0"   >文本</option>
                             <option value="1">图文</option>

                         </select></div>
                    </td>
                </tr>
                <tr>
                    <td width="90">回复内容：</td>
                    <td colspan="3">

                        <textarea style=" height: 120px; width: 500px; "class="layui-textarea" name="content">${(rqcode.content)!}</textarea>

                        <span class="color_r2">*</span>
                    </td>
                </tr>





                <tr>
                    <td></td>
                    <td colspan="3">
                        <button type="button" name="submit" id="submit" class="layui-btn">提交</button>
                        <a id="back" href="javascript:location.href=document.referrer" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<#if rqcode??>
    <script>
        var g_type ="${(rqcode.type)!}";
        if(g_type) document.getElementById("type").value = g_type;

   </script>
</#if>
<script data-main="${ctx}/js/abc/cszj/rqcode.js" src="${ctx}/js/require.js"></script>
</body>
</html>
