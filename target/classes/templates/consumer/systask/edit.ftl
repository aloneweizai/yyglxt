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
	<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <style>
		input, textarea, select,.uploadMsg {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
	</style>
	<script type="text/javascript">
        var ctx = "${ctx}";
		var imgUrl="${imgPth}";
    </script>
</head>
<body>
  <div class="container-fluid m_top_30 nycon_list sys_mod_add">
	
    <form name="consumer_edit" action="${ctx}/consumerManager/sysTask/save.php" next-url="${ctx}/consumerManager/systask/list.php" method="post" class="layui-form">
      <table class="layui-table" lay-size="sm">
		
		<#if sysTask??>
		<input type="hidden" name="id" value="${sysTask.id!}">
		<input type="hidden" name="awardType" value="${sysTask.awardType!}">
		<input type="hidden" name="ruleId" value="${sysTask.ruleId!}">
		<tr>
          <td>任务类型</td>
          <td colspan="3"><div style="width:330px; float: left">
			<select name="type" id='renwutype' style="height:30px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=tasktype" data-val="${sysTask.type!}" data-rules="fieldValue:fieldKey" >
			</select></div>
          </td>
        </tr>
		<tr>
          <td width="180">任务编码</td>
          <td colspan="3"><input type="text" name="code" value="${sysTask.code!}" data-rule="required;length[2~10]" style=" width:330px;" class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
        <tr>
          <td width="180">任务名称</td>
          <td colspan="3"><input type="text" name="name" value="${sysTask.name!}" data-rule="required;length[2~50]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180" style="height:100px;vertical-align:middle">主题图片</td>
		  <td width="100" id="imgshow" style="vertical-align:middle">
			<#if sysTask.imageUrl!=''>
				<img height='90' width='90' style='margin-left:10px;' src='${imgPth}${sysTask.imageUrl!}' />
			<#else>
				<img height='90' width='90' style='margin-left:10px;' src="${ctx}/images/default.jpg">
            </#if>
			
		  </td>
          <td style="vertical-align:middle" colspan="2">
			<input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><label style='color:red;'>*</label>
            <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
			<button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
			<input type="hidden" name="imageUrl" class="layui-input" style="width:330px;" data-rule="required;" value="${sysTask.imageUrl!}">
			<span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200KB（jpg、png、bmp）</span>
		  </td>
          
        </tr>
		<#if sysTask.startTime?? && sysTask.endTime??>
		<tr>
          <td width="180">开始时间</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="开始时间:required(#endTime:filled);datetime;" id="startTime" data-target="#startMsg" value="${sysTask.startTime?string("yyyy-MM-dd HH:mm:ss")}" name="startT" style=" width:330px;"  class="layui-input"><span class="msg-box" id="startMsg"></span></td>
        </tr>
		<tr>
          <td width="180">结束时间</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="结束时间:required(#startTime:filled);datetime;match(gte, startT, datetime);" id="endTime" data-target="#endMsg" value="${sysTask.endTime?string("yyyy-MM-dd HH:mm:ss")}" name="endT" style=" width:330px;"  class="layui-input"><span class="msg-box" id="endMsg"></span></td>
        </tr>
		<#else>
		<tr>
          <td width="180">开始时间</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="开始时间:required(#endTime:filled);datetime;" id="startTime" data-target="#startMsg"  name="startT" style=" width:330px;"  class="layui-input"><span class="msg-box" id="startMsg"></span></td>
        </tr>
		<tr>
          <td width="180">结束时间</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="结束时间:required(#startTime:filled);datetime;match(gte, startT, datetime);" id="endTime" data-target="#endMsg"  name="endT" style=" width:330px;" class="layui-input" ><span class="msg-box" id="endMsg"></span></td>
        </tr>
        </#if>
		<tr>
          <td width="180">规则名称</td>
          <td colspan="3"><input type="text" clickDIV id="ruleName" name="ruleName" value="${sysTask.ruleName!}" readonly data-rule="required;" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180">规则代码</td>
          <td colspan="3"><input type="text" clickDIV id="ruleCode" name="ruleCode" value="${sysTask.ruleCode!}" readonly data-rule="required;" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180">完成积分/经验值</td>
          <td colspan="3"><input type="text" clickDIV id="award" name="award" value="${sysTask.award!}" readonly data-rule="required;" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td>任务周期</td>
          <td colspan="3"><div style="width: 330px; float: left">
		   <select  name='dateType' style=" width:330px;height:30px;" >
				<option <#if sysTask.dateType?? && sysTask.dateType=="A">selected</#if>  value='A'>无限制</option>
				<option <#if sysTask.dateType?? && sysTask.dateType=="O">selected</#if>  value='O'>一次性</option>
				<option <#if sysTask.dateType?? && sysTask.dateType=="Y">selected</#if>  value='Y'>年</option>
				<option <#if sysTask.dateType?? && sysTask.dateType=="M">selected</#if>  value='M'>月</option>
				<option <#if sysTask.dateType?? && sysTask.dateType=="D">selected</#if>  value='D'>日</option>
		   </select></div>
           <label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td width="180">数量</td>
          <td colspan="3"><input type="text" name="count" data-rule="integer" value="${(sysTask.count?c)!}" style=" width:100px;"  class="layui-input"> </td>
        </tr>
		<tr>
          <td width="180">跳转链接</td>
          <td colspan="3"><input type="text" name="skipURL" data-rule="length[2~200]" value="${sysTask.skipURL!}" style=" width:500px;" class="layui-input" > </td>
        </tr>
		<tr>
          <td>任务规则</td>
          <td colspan="3">
			<textarea name="rule" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea">${sysTask.rule!}</textarea>
          </td>
        </tr>
		<tr>
          <td>状态</td>
          <td width="150" colspan="2">
			<input name="status" <#if sysTask.status>checked</#if> type="radio" value="true" title="启用 ">
			<input name="status" style='margin-left:50px' <#if !sysTask.status>checked</#if> type="radio" value="false" title="停用">
          </td>
        </tr>
		<#else>
		<input type="hidden" name="awardType">
		<input type="hidden" name="ruleId">
		<tr>
          <td>任务类型</td>
          <td colspan="3"><div style="width:330px; float: left">
			<select name="type" style="height:30px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=tasktype" data-val="" data-rules="fieldValue:fieldKey" >
            </select></div>
          </td>
        </tr>
		<tr>
          <td width="180">任务编码</td>
          <td colspan="3"><input type="text" name="code"  data-rule="required;length[2~10]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180">任务名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~50]" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180" style="height:100px;vertical-align:middle">主题图片</td>
		  <td width="100" id="imgshow" style="vertical-align:middle"><img height='90' width='90' style='margin-left:10px;' src='${ctx}/images/default.jpg' /></td>
          <td width="400" colspan="2" style="vertical-align:middle">
			<input type="file" id="uploadFile" name="uploadFile" data-type="jpg;png;bmp"><label style='color:red;'>*</label>
			<label id="uploadMsg" class="uploadMsg" style="color:red"></label>
			<button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
			<input type="hidden" name="imageUrl"  data-rule="required;"> 
			<span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200KB（jpg、png、bmp）</span>
          </td>         
        </tr>
		<tr>
          <td width="180">开始时间</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="开始时间:required(#endTime:filled);datetime;" data-target="#startMsg" id="startTime" name="startT"  style=" width:330px;"  class="layui-input"><span class="msg-box" id="startMsg"></span></td>
        </tr>
		<tr>
          <td width="180">结束时间</td>
          <td colspan="3"><input type="text" data-type="datetimebox" data-rule="结束时间:required(#startTime:filled);datetime;match(gte, startT, datetime);" data-target="#endMsg" id="endTime" name="endT"  style=" width:330px;"  class="layui-input"><span class="msg-box" id="endMsg"></span></td>
        </tr>
		<tr>
          <td width="180">规则名称</td>
          <td colspan="3"><input type="text" clickDIV id="ruleName" name="ruleName" readonly data-rule="required;" style=" width:330px;" class="layui-input" ><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180">规则代码</td>
          <td colspan="3"><input type="text" clickDIV id="ruleCode" name="ruleCode" readonly data-rule="required;" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td width="180">完成积分/经验值</td>
          <td colspan="3"><input type="text" clickDIV id="award" name="award" value="" readonly data-rule="required;" style=" width:330px;"  class="layui-input"><label style='color:red;'>*</label></td>
        </tr>
		<tr>
          <td>任务周期</td>
          <td colspan="3"><div style="width: 330px; float:left">
		   <select  name='dateType' style=" width:330px;height:30px;" >
				<option value='A'>无限制</option>
				<option value='O'>一次性</option>
				<option value='Y'>年</option>
				<option value='M'>月</option>
				<option value='D'>日</option>
		   </select></div>
           <label style='color:red;' >*</label></td>
        </tr>
		<tr>
          <td width="180">数量</td>
          <td colspan="3"><input type="text" name="count" data-rule="integer" style=" width:100px;"  class="layui-input"> </td>
        </tr>
		<tr>
          <td width="180">跳转链接</td>
          <td colspan="3"><input type="text" name="skipURL" data-rule="length[2~200]" style=" width:500px;"  class="layui-input"> </td>
        </tr>
		<tr>
          <td>任务规则</td>
          <td colspan="3">
			<textarea name="rule" data-rule="length[~500]" rows="3" cols="60" class="layui-textarea"></textarea>
          </td>
        </tr>
		<tr>
          <td>状态</td>
		  <td colspan="3" width="150"><input name="status" type="radio" value="true" title="启用">
				<input name="status" style='margin-left:50px'  type="radio" value="false" title="停用" checked>
          </td>
        </tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3"><button type="button" name="button" id="consumer_submit"  class="layui-btn">提交</button>
            <a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>

<div >
    <ul>
		<li class="org_li" data-id="1" data-pid="" data-name="积分奖励" ></li>
		<li class="org_li" data-id="2" data-pid="" data-name="经验值奖励" ></li>
		<#if points?? && (points?size > 0) >
        <#list points as point>
            <li class="org_li" data-rid="${point.id!}" data-id="${point.code!}" data-pid="1" data-name="${point.name!}" data-val="${(point.points?c)!}" data-type="1"></li>
        </#list>
		</#if>
		<#if exps?? && (exps?size > 0) >
        <#list exps as exp>
            <li class="org_li" data-rid="${exp.id!}" data-id="${exp.code!}" data-pid="2" data-name="${exp.name!}" data-val="${(exp.exp?c)!}" data-type="0"></li>
        </#list>
		</#if>
    </ul>
</div>
	
<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">规则选择
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <input value="确认" class="js_close btn btn-info">
    </div>
</div>
</body>
<script data-main="${ctx}/js/abc/consumer/systask" src="${ctx}/js/require.js"></script>
</html>