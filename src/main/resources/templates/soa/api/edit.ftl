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
</head>
<body>
  <div class="container-fluid m_top_30 nycon_list sys_mod_add">
	<#--
    <table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/api/list.php">
            <u>服务接口设置</u>
          </a> &gt;&gt; 添加/编辑服务接口</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/api/save.php" next-url="${ctx}/api/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if api??>
		<input type="hidden" name="id" value="${api.id!}">
		<tr>
          <td  width="120">接口名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[4~50]" value="${api.name!}"  class="layui-input" style="width:330px;"/><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td  width="120">接口地址</td>
          <td colspan="3"><input type="text" name="uri" data-rule="required;length[1~128]" value="${api.uri!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td  width="120">调用方式</td>
          <td colspan="3">
              <div style="width: 330px; float: left">
			 <select name="method" style="height:30px;width:150px;">
				<option <#if api.method=="GET">selected</#if> value="GET" >GET</option>
				<option <#if api.method=="POST">selected</#if> value="POST" >POST</option>
				<option <#if api.method=="PUT">selected</#if> value="PUT" >PUT</option>
				<option <#if api.method=="DELETE">selected</#if> value="DELETE" >DELETE</option>
             </select></div>
             <label style='color:red;'>*</label>
		  </td>
        </tr>
		<tr>
          <td  width="120">所属系统</td>
          <td colspan="3">
              <div style="width: 330px; float: left">
			 <select name="dictId" style="height:30px;width:150px;">
				<#if apps?? && ( apps?size gt 0 )> 
				 <#list apps as app>
				 <option <#if api.dictId==app.id>selected</#if>  value="${app.id}" >${app.fieldKey}</option>
				 </#list>
			   </#if>
             </select>
                  </div>
             <label style='color:red;'>*</label>
		  </td>
        </tr>
		<tr>
          <td width="120">接口版本</td>
          <td colspan="3"><input type="text" name="version" data-rule="required;integer(+0);" value="${api.version!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<#--<tr>
          <td  width="120">是否验证身份</td>
          <td colspan="3"><input name="authentication" <#if api.authentication>checked</#if> type="radio" value="true" >是&nbsp;&nbsp;&nbsp;&nbsp; 
            <input name="authentication" style="margin-left:120px"  <#if !api.authentication>checked</#if> type="radio" value="false">否
          </td>
		  <td></td>
        </tr>-->
		<tr>
          <td  width="120">状态</td>
          <td colspan="3"><input name="status" <#if api.status>checked</#if> type="radio" value="true" title="启用">
          <input name="status" <#if !api.status>checked</#if> type="radio" value="false" title="停用">
          </td>
        </tr>
		<#else>
		<tr>
          <td width="120">接口名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[4~50]" class="layui-input" style="width:330px;"/><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="120">接口地址</td>
          <td colspan="3"><input type="text" name="uri" data-rule="required;length[1~128]" class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="120">调用方式</td>
          <td colspan="3">
              <div style="width: 330px; float: left">
			 <select name="method" style="height:30px;width:150px;">
				<option  value="GET" >GET</option>
				<option  value="POST" >POST</option>
				<option  value="PUT" >PUT</option>
				<option  value="DELETE" >DELETE</option>
             </select>
                  </div>
             <label style='color:red;'>*</label>
		  </td>
        </tr>
		<tr>
          <td  width="120">所属系统</td>
          <td colspan="3">
              <div style="width: 330px; float: left">
			 <select name="dictId" style="height:30px;width:150px;">
			   <#if apps?? && ( apps?size gt 0 )>
				 <#list apps as app>
				 <option value="${app.id}" >${app.fieldKey}</option>
				 </#list>
			   </#if>
             </select>
              </div>
             <label style='color:red;'>*</label>
		  </td>
        </tr>
		<tr>
          <td width="120">接口版本</td>
          <td colspan="3"><input type="text" name="version" data-rule="required;integer(+0);" class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<#--<tr>
          <td width="120">是否验证身份</td>
          <td colspan="2" >
			<input name="authentication"  checked type="radio" value="true" >是&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="authentication" style="margin-left:120px" type="radio" value="false">否
          </td>

        </tr>-->
		<tr>
          <td width="120">状态</td>
          <td colspan="3">
			  <input name="status"  type="radio" value="true" title="启用">
              <input name="status" checked style="margin-left:120px"  type="radio" value="false" title="停用">
          </td>
        </tr>
		</#if>
        <tr>
          <td width="120"></td>
          <td colspan="3"><button type="button" name="button" id="consumer_submit" class="layui-btn">提交</button>
            <a href="javascript:location.href=document.referrer" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>