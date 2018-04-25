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
          <a href="${ctx}/financed/logisticsList.php">
            <u>物流公司管理</u>
          </a> &gt;&gt; 添加/编辑物流公司</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/financed/logisticsSave.php" next-url="${ctx}/financed/logisticsList.php?compName=${(LogisticsRq.compName)!}" method="post">
      <table class="table">
		<#if pointRule??>
		<input type="hidden" name="id" id="compNameId" value="${pointRule.id!}">
		<tr>
          <td style="width:200px;">物流公司名称</td>
          <td><input type="text" name="compName" id="compName" data-rule="required;length[2~10]" value="${pointRule.compName!}"  class="layui-input" style="width:330px;"><label style='color:red;'>*</label> </td>
        </tr>
        <tr>
          <td>物流公司代码</td>
          <td><input type="text" name="compCode" id="compCode" data-rule="required;length[2~50]" value="${pointRule.compCode!}"  class="layui-input" style="width:330px;" ><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td>网址</td>
          <td><input type="text" name="compUrl" id="compUrl" data-rule="required;length[~100];url;" value="${pointRule.compUrl!}" style=" width:330px;" ><label style='color:red;'>*</label></td>
        </tr>
        <tr>
            <td>物流公司快递模板</td>
            <td>
                <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择快递模板，快递模板文件类型（.xls、.xlsx）</span><br/>
                <input type="file" name="FILE01" id="FILE01" style="width:200px;display: inline-block;" value="${pointRule.templateUrl!}">
                <#if path??>
                    <a data-type="download" href="javascript:void(0);" data-val="${path!}" class="pn-opt">模板下载</a>
             </#if>
            </td>
        </tr>
		<tr>
          <td>排序</td>
          <td><input type="text" name="sort" id="sort" data-rule="required;integer(+);" value="${pointRule.sort}" style=" width:330px;" ><label style='color:red;'>*</label></td>
        </tr>
		<#else>
		<tr>
          <td style="width:200px;">物流公司名称</td>
          <td>
              <input type="hidden" name="id" id="compNameId" value="">
              <input type="text" name="compName" id="compName" data-rule="required;length[2~10]"   class="layui-input" style="width:330px;"><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td>物流公司代码</td>
          <td><input type="text" name="compCode" id="compCode" data-rule="required;length[2~50]"   class="layui-input" style="width:330px;" ><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="120">网址</td>
          <td colspan="3"><input type="text" name="compUrl" id="compUrl" value="http://" data-rule="required;length[~100];url;" style=" width:330px;" ><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
        <tr>
            <td>物流公司快递模板</td>
            <td>
                <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择快递模板，快递模板文件类型（.xls、.xlsx）</span><br/>
                <input type="file" name="FILE01" id="FILE01" style="width:200px;display: inline-block;">
            </td>
        </tr>
		<tr>
          <td>排序</td>
          <td><input type="text" name="sort" id="sort" data-rule="required;integer(+);"  style=" width:330px;" ><label style='color:red;'>*</label></td>
        </tr>
		</#if>
        <tr>
          <td></td>
          <td>
              <input type="hidden" style="width: 100px;" id="comp" value="${(LogisticsRq.compName)!}">
              <input type="hidden" style="width: 100px;" id="page" value="${(LogisticsRq.page)!}">
              <input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
              <a href="${ctx}/financed/logisticsList.php?compName=${(LogisticsRq.compName)!}&page=${(LogisticsRq.page)!}"
                 style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/financed/logistics.js" src="${ctx}/js/require.js"></script>
</html>