<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list link_sort_add">
    <table class="table  table-hover">
        <tr>
            <td height="30">&nbsp;当前位置：&nbsp;<a href="${ctx}/cms/friendlink/list.php"><u>友情链接列表</u></a> &gt;&gt; 友情链接类别
            </td>
        </tr>
    </table>
    <form name="sortForm" id="sortForm">
        <div class="nycon_list_b">
            <table>
                <tr>
                    <td>名称：<input type="text" name="">排列顺序：<input type="text" name="" size="2">
                        <bottom name="" class="btn-info btn btn-xs">+添加</bottom>
                    </td>
                </tr>
            </table>

            <table class="layui-table" lay-size="sm">
                <thead>
                <th width="54">
                    <div class="nycon_sel_btn" onclick="selectall(&quot;input[name='ids']&quot;)">全选</div>
                </th>
                <th>ID</th>
                <th>名称</th>
                <th>排列顺序</th>

                <th>操作选项</th>

                </thead>
                <tbody>
                <tr>
                    <td><input name="ids" type="checkbox" lay-skin="primary"  value="67668"></td>
                    <td>1</td>
                    <td><input type="text" name="" value="1242"></td>
                    <td><input type="text" name="" size="3"></td>
                    <td><a class="pn-opt">删除</a></td>
                </tr>
                </tbody>
            </table>
            <input type='button' class="btn btn-default btn-sm pn-opt" value='批量删除'/>
            <input type='button' class="btn btn-default btn-sm pn-opt" value='保存内容'/>
        </div>
    </form>
</div>
</body>
</html>