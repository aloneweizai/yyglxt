<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
	<script type="text/javascript">
            var ctx = "${ctx}";
        </script>
    <style>
		input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
	</style>
</head>
<body>
  <div class="container-fluid m_top_30 nycon_list sys_mod_add">
	<#--
    <table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/goodscategory/list.php">
            <u>商品分类管理</u>
          </a> &gt;&gt; 添加/编辑商品分类</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/goodscategory/save.php" next-url="${ctx}/goodscategory/list.php" method="post">
      <table class="layui-table" lay-size="sm">
		<#if pointRule??>
		<input type="hidden" id='categoryId' name="id" value="${pointRule.id!}">
		<tr>
          <td width="120">分类名称</td>
          <td colspan="3"><input type="text" name="category" data-rule="required;length[2~16]" value="${pointRule.category!}"  class="layui-input" style="width:300px;"><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
        <tr>
          <td width="120">上级分类</td>
          <td colspan="3">
			<input type="text" id="parentName" name="parentName" readonly  class="layui-input" style="width:300px;" ><label style='color:red;'>*</label>
		    <input type="hidden" id="parentId" name="parentId" data-rule="required;length[2~50]" value="${pointRule.parentId!}"  class="layui-input" style="width:300px;" >
          </td>
          <td></td>
        </tr>
		<tr>
          <td width="120">排序</td>
          <td colspan="3"><input type="text" name="sort" data-rule="required;integer(+);" value="${pointRule.sort}" style=" width:300px;"  class="layui-input"><label style='color:red;'>*</label></td>
          <td>
          </td>
        </tr>
		<#else>
		<tr>
          <td width="120">分类名称</td>
          <td colspan="3"><input type="text" name="category" data-rule="required;length[2~16]"  class="layui-input" style="width:300px;"><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
        <tr>
          <td width="120">上级分类</td>
          <td colspan="3">
			<input type="text" id="parentName" name="parentName" readonly  class="layui-input" style="width:300px;" ><label style='color:red;'>*</label>
		    <input type="hidden" id="parentId" name="parentId" data-rule="required;length[2~50]" class="layui-input" style="width:330px;" >
          </td>
          <td></td>
        </tr>
		<tr>
          <td width="120">排序</td>
          <td colspan="3"><input type="text" name="sort" data-rule="required;integer(+);" style=" width:300px;"  class="layui-input"><label style='color:red;'>*</label></td>
          <td>
          </td>
        </tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3"><button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
            <a  href="${referer}" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>


	
<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">规则选择
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <button type="button" class="js_close layui-btn">确认</button>
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/financed/goodedit" src="${ctx}/js/require.js"></script>
</html>