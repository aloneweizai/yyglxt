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
          <a href="${ctx}/consumerManager/vipPrivilege/list.php">
            <u>会员特权设置</u>
          </a> &gt;&gt; 添加/编辑会员特权</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/consumerManager/vipPrivilege/save.php" next-url="${ctx}/consumerManager/vipPrivilege/list.php" method="post">
      <table class="table">
		<#if pointRule??>
		<input type="hidden" name="id" value="${pointRule.id!}">
        <tr>
          <td width="90">特权名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~50]" value="${pointRule.name!}" style=" width:330px;" ><label style='color:red;'>*</label> </td>
          <td></td>
        </tr>
            <tr>
                <td width="90">特权代码</td>
                <td colspan="3"><input type="text" name="code" data-rule="required;" value="${pointRule.code!}" style=" width:330px;" ><label style='color:red;'>*</label> </td>
                <td></td>
            </tr>
        <tr>
          <td>图标</td>
          <td colspan="3">
			<input type="text" name="icon" data-rule="required;length[2~50]" value="${pointRule.icon!}" style=" width:330px;" ><label style='color:red;'>*</label>
			</br><a target="_blank" href="http://iconfont.cn/">可用的图标</a>
          </td>
          <td></td>
        </tr>
		<tr>
          <td>序号</td>
          <td colspan="3">
			<input type="text" name="sort" data-rule="required;integer(+)" value="${pointRule.sort!}" style=" width:330px;" ><label style='color:red;'>*</label>
          </td>
          <td></td>
        </tr>
		<tr>
          <td>描述</td>
          <td colspan="3">
			<textarea name="description" data-rule="length[~500]" rows="3" cols="60">${pointRule.description!}</textarea>
          </td>
          <td></td>
        </tr>
		<tr>
          <td>状态</td>
          <td width="150"><input name="status" <#if pointRule.status>checked</#if> type="radio" value="true" >启用 
          </td>
          <td width="150"><input name="status" <#if !pointRule.status>checked</#if> type="radio" value="false">停用
          </td>
        </tr>
		<#else>
		<tr>
          <td width="90">特权名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~50]" style=" width:330px;" ><label style='color:red;'>*</label> </td>
          <td></td>
        </tr>
            <tr>
                <td width="90">特权代码</td>
                <td colspan="3"><input type="text" name="code" data-rule="required;" style=" width:330px;" ><label style='color:red;'>*</label> </td>
                <td></td>
            </tr>
        <tr>
          <td>图标</td>
          <td colspan="3">
			<input type="text" name="icon" data-rule="required;length[2~50]"  style=" width:330px;" ><label style='color:red;'>*</label>
			</br><a target="_blank" href="http://iconfont.cn/">可用的图标</a>
          </td>
          <td></td>
        </tr>
		<tr>
          <td>序号</td>
          <td colspan="3">
			<input type="text" name="sort" data-rule="required;integer(+)"  style=" width:330px;" ><label style='color:red;'>*</label>
          </td>
          <td></td>
        </tr>
		<tr>
          <td>描述</td>
          <td colspan="3">
			<textarea name="description" data-rule="length[~500]" rows="3" cols="60"></textarea>
          </td>
          <td></td>
        </tr>
        <tr>
          <td>状态</td>
		  <td width="150"><input name="status" type="radio" value="true"> 启用
          </td>
          <td width="150"><input name="status"  type="radio" value="false" checked>停用
          </td>
          
        </tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3"><input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
            <a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>