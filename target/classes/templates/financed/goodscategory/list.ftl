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
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
	<form action="${ctx}/goodscategory/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">分类名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.category)!}" name="category"  class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
                <a href="${ctx}/goodscategory/edit.php"  class="layui-btn layui-btn-normal fr">添加分类</a>
            </div>
        </div>
	  <div class="nycon_list_b">
          <table class="layui-table" id="treeDataTb" lay-size="sm">
               <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th width="500">分类名称</th>
					<th>上级分类</th>
                    <th>排序</th>
                    <th>修改时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
				<tbody class="pn-ltbody">
					
                </tbody>

      </table>
      </div>
	  </form>
</div>
<input type="hidden" value="${ctx}/goodscategory/list.php" id="currLink">
<script data-main="${ctx}/js/abc/financed/goods" src="${ctx}/js/require.js"></script>
</body>
</html>