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
	<#if appset?? && api??>
    <form name="consumer_edit" action="${ctx}/app/settingEdit.php" next-url="${ctx}/app/settinglist.php?appId=${appset.appId!}" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		
		<input type="hidden" name="id" value="${appset.id!}">
		<input type="hidden" name="appId" value="${appset.appId!}">
		<input type="hidden" name="apiId" value="${appset.apiId!}">
         <tr>
			<td width="160" style="font-weight:bold">接口名称</td>   
			<td>${api.name!}</td>
		   </tr>
		   <tr>
			<td width="160" style="font-weight:bold">接口地址</td>
			<td id="appuri">${api.uri!}</td>
		   </tr>
		   <tr>
			<td width="160" style="font-weight:bold">调用方式</td>
			<td id="appmethod">${api.method!}</td>
		   </tr>
		   <tr>
			<td width="160" style="font-weight:bold">每分允许访问次数</td>
			<td>
			   <input type="text" value="${appset.timesPerMinute!}" name="timesPerMinute" data-rule="required;integer"  style=" width:120px;" ><label style='color:red;'>*</label>
			</td>
		   </tr>
		   <tr>
			<td width="160" style="font-weight:bold">每小时允许访问次数</td>
			<td>
			  <input type="text" value="${appset.timesPerHour!}" name="timesPerHour" data-rule="required;integer" style=" width:120px;" ><label style='color:red;'>*</label>
			</td>
		   </tr>
		   <tr>
			<td width="160" style="font-weight:bold">每日允许访问次数</td>
			<td>
			  <input type="text" value="${appset.timesPerDay!}" name="timesPerDay"  data-rule="required;integer" style=" width:120px;" ><label style='color:red;'>*</label>
			</td>
		   </tr>
		   <#--<tr>
			<td width="160" style="font-weight:bold">是否验证身份</td>
			<td>
			    <input name="isValidate" <#if appset.isValidate>checked</#if> type="radio" value="true">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="isValidate" <#if !appset.isValidate>checked</#if>  style='margin-left:50px'  type="radio" value="false">否
			</td>
		   </tr>-->
		   <tr style='display:none;'>
			<td width="160" style="font-weight:bold">状态</td>
			<td>
			    <input name="status" type="radio" value="true" title="启用" checked>
				<input name="status"  type="radio" value="false" title="停用">
			</td>
		   </tr>
		
        <tr>
          <td></td>
          <td colspan="3"><button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
            <a href="javascript:location.href=document.referrer"  style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
	</#if>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>