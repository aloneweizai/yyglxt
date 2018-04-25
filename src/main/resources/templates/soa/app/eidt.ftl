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
	<#--
    <table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/app/list.php">
            <u>接入应用设置</u>
          </a> &gt;&gt; 修改接入应用信息</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/app/save.php" next-url="${ctx}/app/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if app??>
		<input type="hidden" name="id" value="${app.id!}">
		<tr>
          <td width="90">应用名称</td>
          <td colspan="3"><input type="text" name="name" value="${app.name!}" data-rule="required;length[2~50]" style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="90">应用昵称</td>
          <td colspan="3"><input type="text" name="nickname" data-rule="required;length[2~50]" value="${app.nickname!}" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">应用秘钥</td>
          <td colspan="3">
			<input type="hidden" name="oldPassWord" data-rule="required;length[2~50]" style=" width:330px;" value="${app.password!}" class="layui-input">
			<span id="passwordsss"><input type="password" name="password" data-rule="required;length[2~50]" style=" width:330px;" class="layui-input" value="${app.password!}"></span><button type="button" name="button" id="xianshibt" class="layui-btn layui-btn-primary  xianshi">显示</button><label style='color:red;'>*</label>
              <button style='margin-left:10px;' type="button" name="button" id="shuaxin"  class="layui-btn">刷新秘钥</button>
           </td>
        </tr>
        <tr>
          <td width="90">授权时间起</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="开始时间:required;datetime;" data-target="#startMsg" value='${app.startTime?string("yyyy-MM-dd HH:mm:ss")}' name="startT" style=" width:330px;" class="layui-input" ><label style='color:red;'>*</label><span class="msg-box" id="startMsg"></span></td>
        </tr>
		<tr>
          <td width="90">授权时间止</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="结束时间:required;datetime;match(gte, startT, datetime)" data-target="#endMsg" value='${app.endTime?string("yyyy-MM-dd HH:mm:ss")}' name="endT" class="layui-input" style=" width:330px;" ><label style='color:red;'>*</label><span class="msg-box" id="endMsg"></span></td>
        </tr>
		<tr>
          <td width="90">描述</td>
          <td colspan="3">
			 <textarea name="remark" data-rule="length[~500]" rows="3" cols="60">${app.remark!}</textarea>
		  </td>
        </tr>
		<tr>
          <td>状态</td>
          <td colspan="3"><input name="status" <#if app.status>checked</#if> type="radio" value="true" title="启用"> <input name="status" <#if !app.status>checked</#if> type="radio" value="false" title="停用"></td>
        </tr>
		<#else>
		<tr>
          <td width="90">应用名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~50]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">应用昵称</td>
          <td colspan="3"><input type="text" name="nickname" data-rule="required;length[2~50]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">应用秘钥</td>
          <td colspan="3"><span id="passwordsss"><input type="password" name="password" data-rule="required;length[2~50]" style=" width:330px;" class="layui-input" ></span><button type="button" name="button" id="xianshibt" class="layui-btn layui-btn-primary xianshi">显示</button><label style='color:red;'>*</label>
              <button type="button" name="button" id="shuaxin" class="layui-btn">刷新秘钥</button>
          </td>
        </tr>
        <tr>
          <td width="90">授权时间起</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="开始时间:required;datetime;" data-target="#startMsg"  name="startT" style=" width:330px;"  class="layui-btn"><label style='color:red;'>*</label><span class="msg-box" id="startMsg"></span></td>
        </tr>
		<tr>
          <td width="90">授权时间止</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="结束时间:required;datetime;match(gte, startT, datetime)" data-target="#endMsg"  name="endT" style=" width:330px;"  class="layui-btn"><label style='color:red;'>*</label><span class="msg-box" id="endMsg"></span></td>
        </tr>
		<tr>
          <td width="90">描述</td>
          <td colspan="3">
			 <textarea name="remark" data-rule="length[~500]" rows="3" cols="60"></textarea>
		  </td>
        </tr>
		<tr>
          <td>状态</td>
          <td width="150" colspan="3"><input name="status" type="radio" value="true" title="启用"><input name="status" checked type="radio" value="false" title="停用">
          </td>
		</tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3"><button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
            <a href="javascript:location.href=document.referrer" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>