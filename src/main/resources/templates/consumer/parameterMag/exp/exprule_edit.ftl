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
          <a href="${ctx}/consumerManager/exprule/list.php">
            <u>经验值规则设置</u>
          </a> &gt;&gt; 添加/编辑经验值规则</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/consumerManager/exprule/save.php" next-url="${ctx}/consumerManager/exprule/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if pointRule??>
		<input type="hidden" name="id" value="${pointRule.id!}">
        <tr>
          <td width="90">规则名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~16]" value="${pointRule.name!}"  class="layui-input" style="width:330px;" ><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td>规则代码</td>
          <td colspan="3">
			<input type="text" name="codeHead" value="E" readonly  style="width:30px;"  class="layui-input">-
			<input type="text" name="code" data-rule="required;length[2~10]" value="${pointRule.code?substring(2)}"  style="width:290px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td>经验值</td>
          <td colspan="3"><input type="text" name="exp" data-rule="required;allint;" value="${pointRule.exp?c}" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>c
		<tr>
          <td>计算周期</td>
          <td colspan="3">
              <div style=" width:330px;height:30px; float: left" >
		   <select  name='period'>
				<option <#if pointRule.period?? && pointRule.period=="A">selected</#if>  value='A'>无限制</option>
				<option <#if pointRule.period?? && pointRule.period=="O">selected</#if>  value='O'>一次性</option>
				<option <#if pointRule.period?? && pointRule.period=="Y">selected</#if>  value='Y'>年</option>
				<option <#if pointRule.period?? && pointRule.period=="M">selected</#if>  value='M'>月</option>
				<option <#if pointRule.period?? && pointRule.period=="D">selected</#if>  value='D'>日</option>
		   </select></div>
           <label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td>次数</td>
          <td colspan="3">
			<input data-rule='integer(+0);length(1~5);' name='degree' value="${pointRule.degree!}" type="text" style="width: 100px;" class="layui-input"/>
			<label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td>描述</td>
          <td colspan="3">
			<textarea name="description" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea">${pointRule.description!}</textarea>
          </td>
        </tr>
        <tr>
          <td>状态</td>
          <td colspan="3"><input name="status" <#if pointRule.status>checked</#if> type="radio" value="true" title="启用">
              <input name="status" <#if !pointRule.status>checked</#if> type="radio" value="false" title="停用">
          </td>
        </tr>
		<#else>
		<tr>
          <td width="90">规则名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~16]" style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td>规则代码</td>
          <td colspan="3">
			<input type="text" name="codeHead" value="E" readonly  style="width:30px;" class="layui-input" >-
			<input type="text" name="code" data-rule="required;length[2~10]" style=" width:290px;" class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td>经验值</td>
          <td colspan="3"><input type="text" name="exp" data-rule="required;allint;" style=" width:330px;" value='0' class="layui-input"><label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td>计算周期</td>
          <td colspan="3">
              <div style=" width:330px;height:30px; float: left">
		   <select  name='period' >
				<option value='A'>无限制</option>
				<option value='O'>一次性</option>
				<option value='Y'>年</option>
				<option value='M'>月</option>
				<option value='D'>日</option>
		   </select></div>
           <label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td>次数</td>
          <td colspan="3">
			<input data-rule='required;integer(+0);length(1~5);' name='degree'  type="text" style="width: 100px;"  class="layui-input"/>
			<label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td>描述</td>
          <td colspan="3"><textarea name="description" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea"></textarea></td>
        </tr>
        <tr>
          <td>状态</td>
		  <td colspan="3"><input name="status" type="radio" value="true" title="启用">
              <input name="status"  type="radio" value="false" title="停用" checked>
          </td>

        </tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3">
              <button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
			<a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a>
            </td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>