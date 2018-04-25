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
          <a href="${ctx}/consumerManager/tag/list.php">
            <u>用户标签设置</u>
          </a> &gt;&gt; 添加/编辑用户标签</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/consumerManager/tag/save.php" next-url="${ctx}/consumerManager/tag/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if pointRule??>
		<input type="hidden" name="id" value="${pointRule.id!}">
        <tr>
          <td width="90">标签名称</td>
          <td colspan="3"><input type="text" name="tagName" data-rule="required;length[2~50]" value="${pointRule.tagName!}" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		   <input name="type" type="hidden" value="${pointRule.type!}"  >
        <tr>
          <td>标签分类</td>
          <td colspan="3">
              <div style=" width:330px;">
                  <select name="category" style="height:30px;" data-rule="required;">
                      <option value="">请选择</option>
                    <#if taglib?? && ( taglib?size gt 0 )>
                      <#list taglib as lib>
                          <option <#if pointRule.category??&&pointRule.category == lib.fieldValue>selected</#if> value="${lib.fieldValue}">${lib.fieldKey}</option>
                      </#list>
                    </#if>
                  </select>
			</div>
          </td>
        </tr>
		<tr>
          <td>权重</td>
          <td colspan="3">
			<input type="text" name="weight" data-rule="required;range(0~)" value="${pointRule.weight!}" style=" width:330px;"   class="layui-input"><label style='color:red;'>*</label>
          </td>
        </tr>
		<tr>
          <td>描述</td>
          <td colspan="3">
			<textarea name="description" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea">${pointRule.description!}</textarea>
          </td>
        </tr>
		<tr>
          <td>规则</td>
          <td colspan="3">
			<textarea name="rule" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea">${pointRule.rule!}</textarea>
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
          <td width="90">标签名称</td>
          <td colspan="3"><input type="text" name="tagName" data-rule="required;length[2~50]"  style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<input name="type" type="hidden" value="user"  >
        <tr>
          <td>标签分类</td>
          <td colspan="3">
              <div style=" width:330px;">
                  <select name="category" style="height:30px;" data-rule="required;">
                      <option value="">请选择</option>
                    <#if taglib?? && ( taglib?size gt 0 )>
                      <#list taglib as lib>
                          <option value="${lib.fieldValue}">${lib.fieldKey}</option>
                      </#list>
                    </#if>
                  </select>
                  </div>
          </td>
        </tr>
		<tr>
          <td>权重</td>
          <td colspan="3">
			<input type="text" name="weight" data-rule="required;range(0~)"  style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label>
          </td>
        </tr>
		<tr>
          <td>描述</td>
          <td colspan="3">
			<textarea name="description" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea"></textarea>
          </td>
        </tr>
		<tr>
          <td>规则</td>
          <td colspan="3">
			<textarea name="rule" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea"></textarea>
          </td>
        </tr>
		<tr>
          <td>状态</td>
          <td colspan="3"><input name="status" type="radio" value="true" title="启用">
          <input name="status" checked type="radio" value="false" title="停用">
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
<script data-main="${ctx}/js/abc/consumer/page.js" src="${ctx}/js/require.js"></script>
</html>