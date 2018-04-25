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
		.alertTb>tbody>tr>td{padding:10px 5px;text-align:center;}
		.alertTb>tbody>tr>td:HOVER{border: 1px solid #c0c0c0;}
		.alertTb>tbody>tr>td>img{cursor:pointer;}
	</style>
</head>
<body>
  <div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <#--
	<table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/consumerManager/honour/list.php">
            <u>用户等级勋章设置</u>
          </a> &gt;&gt; 添加/编辑用户等级勋章</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/consumerManager/honour/save.php" next-url="${ctx}/consumerManager/honour/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		<#if pointRule??>
		<input type="hidden" name="id" value="${pointRule.id!}">
        <tr>
          <td width="150">等级名称</td>
          <td colspan="3"><input type="text" name="name" value="${pointRule.name!}" data-rule="required;"  class="layui-input" style=" width:320px;" ><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="150">经验值范围<</td>
          <td colspan="3">
			 <input type="text" name="minValue" value="${(pointRule.minValue?c)!}" data-rule="经验值范围开始值:required;integer(+0);"  style=" width:100px;"   class="layui-input"><label style='color:red;'>*</label>
			 &nbsp;&nbsp;-&nbsp;&nbsp;
			 <input type="text" name="maxValue" value="${(pointRule.maxValue?c)!}" data-rule="经验值范围结束值:required;integer(+0);match(gt, minValue);"  style=" width:100px;"   class="layui-input"><label style='color:red;'>*</label>
          </td>
        </tr>
		<tr>
          <td width="150">勋章名称</td>
          <td colspan="3">
			<input type="text" name="medal" value="${pointRule.medal!}" data-rule="required;"  style=" width:320px;"   class="layui-input"><label style='color:red;'>*</label>
          </td>
        </tr>
		<tr>
          <td width="150">勋章样式</td>
          <td  colspan="3">
			<img id="see" src="${ctx}/images/medals/${(pointRule.medalIcon)!}.png" style='cursor:pointer;'height="40" width="40"><input type="hidden" readonly name="medalIcon" value="${pointRule.medalIcon!}" data-rule="required;"  >
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
          <td width="150">等级名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;"  style=" width:300px;"  class="layui-input" ><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="150">经验值范围</td>
          <td colspan="3">
			  <input type="text" name="minValue" data-rule="经验值范围开始值:required;integer(+0);"  style=" width:100px;"   class="layui-input"><label style='color:red;'>*</label>
			  &nbsp;&nbsp;-&nbsp;&nbsp;
			  <input type="text" name="maxValue" data-rule="经验值范围结束值:required;integer(+0);match(gt, minValue);"  style=" width:100px;"   class="layui-input"><label style='color:red;'>*</label>
          </td>
          </td>
        </tr>
		<tr>
          <td width="150">勋章名称</td>
          <td colspan="3">
			<input type="text" name="medal" data-rule="required;"  style=" width:300px;"   class="layui-input"><label style='color:red;'>*</label>
          </td>
        </tr>
		<tr>
          <td width="150">勋章样式</td>
          <td colspan="3">
			<img id="see" src="${ctx}/images/medals/medal_1.png" style='cursor:pointer;'height="40" width="40"><input type="hidden" value="medal_1"  name="medalIcon" data-rule="required;" >
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
            <a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a>
          </td>
        </tr>
      </table>
    </form>
  </div>

<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-4">
        <div class="main_modal_t">图标选择
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree">
			  <center>
                <table class="alertTb">
					<tr>
                      <td><img src="${ctx}/images/medals/medal_1.png" title="medal_1"height="40" width="40"></td>
                      <td ><img src="${ctx}/images/medals/medal_2.png" title="medal_2"height="40" width="40"></td>
                      <td ><img src="${ctx}/images/medals/medal_3.png" title="medal_3"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_4.png" title="medal_4"height="40" width="40"></td>
					  <td ><img src="${ctx}/images/medals/medal_5.png" title="medal_5"height="40" width="40"></td>
					  <td ><img src="${ctx}/images/medals/medal_6.png" title="medal_6"height="40" width="40"></td>
					  <td ><img src="${ctx}/images/medals/medal_7.png" title="medal_7"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_8.png" title="medal_8"height="40" width="40"></td>
                    </tr>
					<tr>
                        <td ><img src="${ctx}/images/medals/medal_9.png" title="medal_9"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_10.png" title="medal_10"height="40" width="40"></td>
                      <td ><img src="${ctx}/images/medals/medal_11.png" title="medal_11"height="40" width="40"></td>
					  <td ><img src="${ctx}/images/medals/medal_12.png" title="medal_12"height="40" width="40"></td>
					  <td ><img src="${ctx}/images/medals/medal_13.png" title="medal_13"height="40" width="40"></td>
					  <td ><img src="${ctx}/images/medals/medal_14.png" title="medal_14"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_15.png" title="medal_15"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_16.png" title="medal_16"height="40" width="40"></td>
                    </tr>
                    <tr>
                        <td><img src="${ctx}/images/medals/medal_17.png" title="medal_17"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_18.png" title="medal_18"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_19.png" title="medal_19"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_20.png" title="medal_20"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_21.png" title="medal_21"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_22.png" title="medal_22"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_23.png" title="medal_23"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_24.png" title="medal_24"height="40" width="40"></td>
                    </tr>
                    <tr>
                        <td><img src="${ctx}/images/medals/medal_25.png" title="medal_25"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_26.png" title="medal_26"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_27.png" title="medal_27"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_28.png" title="medal_28"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_29.png" title="medal_29"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_30.png" title="medal_30"height="40" width="40"></td>
                        <td ><img src="${ctx}/images/medals/medal_31.png" title="medal_31"height="40" width="40"></td>
                        <td></td>
                    </tr>
				</table>
			  </center>
			</div>
        </div>
        <input value="确认" class="js_close btn btn-info">
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/consumer/explvl.js" src="${ctx}/js/require.js"></script>
</html>