<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/colpick.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list">
    <table class="table  table-hover">
        <tr>

        </tr>
    </table>
    <div name="content_static">
        <table class="table">
            <tbody><tr>
                <td width="90">栏目</td>
                <td width="290" name="constantSel">
                    <input type="hidden"  name="columnId" id="columnId"
                           value="" style="width:250px;margin-right:10px;">
                    <input type="text" style="width:250px;margin-right:10px; padding: 3px;" readonly="readonly" value=""
                            name="columnName" placeholder="请选择栏目" data-rule="required;"><label style='color:red;'>*</label></td>
                <td> <button style="height:26px;line-height:13px;" name="_channel_btn" type="button" class="layui-btn">
                    选择
                </button></td>
            </tr>
            <tr>
                <td>起始时间</td>
                <td><input id="startTime"  style="width:250px;" abc-type="datebox"></td>
                <td></td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <button type="button" name="staticContent" class="layui-btn">生成内容静态页</button></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>


</div>
</body>
<script data-main="${ctx}/js/abc/cms/pagestatic/contentstatic.js" src="${ctx}/js/require.js"></script>
</html>