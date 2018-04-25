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
    <form name="consumer_edit" action="${ctx}/app/save.php" next-url="${ctx}/app/list.php" method="post">
      <table class="table">
		<input type="hidden" name="id" value="${app.id!}">
		<tr>
          <td width="130">应用名称</td>
          <td colspan="3">${app.name!}</td>
          <td></td>
        </tr>
        <tr>
          <td width="130">应用昵称:</td>
          <td colspan="3">${app.nickname!}</td>
          <td></td>
        </tr>
		<tr>
          <td width="130">应用秘钥:</td>
          <td colspan="3">
			${app.password!}

           </td>
          <td></td>
        </tr>
        <tr>
          <td width="130">授权时间起:</td>
          <td colspan="3">${(app.startTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
          <td></td>
        </tr>
		<tr>
          <td width="130">授权时间止:</td>
          <td colspan="3">${(app.endTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
          <td></td>
        </tr>
		<tr>
          <td width="130">描述:</td>
          <td colspan="3">
			 ${app.remark!}
		  </td>
          <td></td>
        </tr>
		<tr>
          <td>状态:</td>
		  <td>
          <#if app.status>启用 </#if><#if !app.status>停用</#if>
          </td>
          <td width="150">
          </td>
		  <td></td>
		  <td></td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>