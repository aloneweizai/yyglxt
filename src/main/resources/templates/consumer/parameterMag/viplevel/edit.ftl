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
		var imgUrl="${imgPth}";
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
          <a href="${ctx}/consumerManager/viplevel/list.php">
            <u>会员等级设置</u>
          </a> &gt;&gt; 添加/编辑会员等级</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/consumerManager/viplevel/save.php" next-url="${ctx}/consumerManager/viplevel/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if pointRule??>
		<input type="hidden" name="id" value="${pointRule.id!}">
			
		<tr>
          <td width="180" style="height:100px;vertical-align:middle">会员图片</td>
		  <td width="100" id="imgshow" style="vertical-align:middle">
			<#if pointRule.imgUrl?? && pointRule.imgUrl!=''>
				<img height='90' width='90' style='margin-left:10px;' src='${imgPth}${pointRule.imgUrl!}' />
			<#else>
				<img height='90' width='90' style='margin-left:10px;' src="${ctx}/images/default.jpg">
            </#if>
			
		  </td>
          <td colspan="2" style="vertical-align:middle">
			<input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><label style='color:red;'>*</label>
            <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
			<button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
			<input type="hidden" name="imgUrl" class="layui-input" style="width:330px;" data-rule="required;" value="${pointRule.imgUrl!}">
			<span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200KB（jpg、png、bmp）</span>
		  </td>
        </tr>
		<tr>
          <td width="90">等级名称</td>
          <td colspan="3"><input type="text" name="level" data-rule="required;length[2~16]" value="${pointRule.level!}"  class="layui-input" style="width:330px;"/><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="90">等级代码</td>
          <td colspan="3"><input type="text" name="levelCode" data-rule="required;length[2~16]" value="${pointRule.levelCode!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">成本价格</td>
          <td colspan="3"><input type="text" name="costPrice" data-rule="required;length[1~10];price" value="${(pointRule.costPrice?c)!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
		<tr>
          <td width="90">市场价格</td>
          <td colspan="3"><input type="text" name="marketPrice" data-rule="required;length[1~10];price" value="${(pointRule.marketPrice?c)!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">销售价格</td>
          <td colspan="3"><input type="text" name="salePrice" data-rule="required;length[1~10];price" value="${(pointRule.salePrice?c)!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">兑换积分</td>
          <td colspan="3"><input type="text" name="pointsPrice" data-rule="required;length[1~10];integer(+0)" value="${(pointRule.pointsPrice?c)!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">增送积分</td>
          <td colspan="3"><input type="text" name="sendPoints" data-rule="required;length[1~10];integer(+0);" value="${(pointRule.sendPoints?c)!}"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td>成长因子</td>
          <td colspan="3"><input type="text" name="factor" data-rule="required;range(0~999.99);length(~6)" value="${pointRule.factor!}" style=" width:330px;"  class="layui-input"/><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td>状态</td>
          <td colspan="3"><input name="status" <#if pointRule.status>checked</#if> type="radio" value="true" title="启用 ">
          <input name="status" <#if !pointRule.status>checked</#if> type="radio" value="false" title="停用">
          </td>
        </tr>
		<#else>
		<tr>
          <td width="180" style="height:100px;vertical-align:middle">会员图片</td>
		  <td width="100" id="imgshow" style="vertical-align:middle"><img height='90' width='90' style='margin-left:10px;' src='${ctx}/images/default.jpg' /></td>
          <td colspan="2" style="vertical-align:middle">
			<input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><label style='color:red;'>*</label>
			<label id="uploadMsg" class="uploadMsg" style="color:red"></label>
			<button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
			<input type="hidden" name="imgUrl"  data-rule="required;"> 
			<span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200KB（jpg、png、bmp）</span>
          </td>         
        </tr>
		<tr>
          <td width="90">等级名称</td>
          <td colspan="3"><input type="text" name="level" data-rule="required;length[2~16]" style=" width:330px;" class="layui-input"/><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">等级代码</td>
          <td colspan="3"><input type="text" name="levelCode" data-rule="required;length[2~16]" style=" width:330px;" class="layui-input"/><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">成本价格</td>
          <td colspan="3"><input type="text" name="costPrice" data-rule="required;length[1~10];price"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">市场价格</td>
          <td colspan="3"><input type="text" name="marketPrice" data-rule="required;length[1~10];price"   class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">销售价格</td>
          <td colspan="3"><input type="text" name="salePrice" data-rule="required;length[1~10];price"   class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">兑换积分</td>
          <td colspan="3"><input type="text" name="pointsPrice" data-rule="required;length[1~10];integer(+0)" class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="90">增送积分</td>
          <td colspan="3"><input type="text" name="sendPoints" data-rule="required;length[1~10];integer(+0);"  class="layui-input" style="width:330px;" /><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td>成长因子</td>
          <td colspan="3"><input type="text" name="factor" data-rule="required;range(0~999.99);length(~6)" style=" width:330px;" class="layui-input"/><label style='color:red;'>*</label></td>
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
          <td colspan="3"><input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
           <a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs.js" src="${ctx}/js/require.js"></script>
</html>