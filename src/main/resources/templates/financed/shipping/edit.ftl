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
          <a href="${ctx}/financed/shippingList.php">
            <u>配送方式管理</u>
          </a> &gt;&gt; 添加/编辑配送方式</td>
      </tr>
    </table>
	-->
    <form name="consumer_edit" action="${ctx}/financed/shippingSave.php" next-url="${ctx}/financed/shippingList.php?name=${(shippingRq.name)!}&page=${(shippingRq.page)!}" method="post">
      <table class="table">
		<#if pointRule??>
		<input type="hidden" name="id" value="${pointRule.id!}">
		<tr>
          <td width="120">配送方式名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~10]" value="${pointRule.name!}"  class="layui-input" style="width:330px;"><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
		<tr>
          <td>类型</td>
          <td width="150"><input name="type" <#if pointRule.type=="1">checked</#if> type="radio" value="1" >先收款后发货 
          </td>
          <td width="150"><input name="type" <#if pointRule.type=="2">checked</#if> type="radio" value="2"> 货到付款
          </td>
        </tr>
		<tr>
          <td width="120">重量设置</td>
          <td colspan="4">
			<select name="firstWeight" style="height:30px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=firstWeight" data-val="${pointRule.firstWeight!}" data-rules="fieldValue:fieldKey" >
            </select> 首重费用
            <input type="text" style=" width:110px;" name="firstWeightFee" data-rule="required;range(0~)" value="${pointRule.firstWeightFee!}"> 续重重量
			<select name="addedWeight" style="height:30px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=addedWeight" data-val="${pointRule.addedWeight!}" data-rules="fieldValue:fieldKey" >
            </select>
                     续重费用
            <input type="text" style=" width:110px;" name="addedWeightFee" data-rule="required;range(0~)" value="${pointRule.addedWeightFee!}">
            <p class="text-muted">根据重量来计算运费，当物品不足《首重重量》时，按照《首重费用》计算，超过部分按照《续重重量》和《续重费用》乘积来计算</p>
          </td>
        </tr>
		<tr>
          <td width="90">支持保价</td>
          <td colspan="4">
            <input type="checkbox" lay-skin="primary"  name="isInsured" value="1" <#if pointRule.isInsured??&&pointRule.isInsured==1>checked</#if> id="isInsured"> 支持物流保价 费率
            <#if pointRule.isInsured??&&pointRule.isInsured==1>
                <input type="text" style=" width:110px;" name="rate"  value="${pointRule.rate!}" data-rule="required(#isInsured:checked);range(0~100);integer;"> % 最低保价费
                <input type="text" style=" width:110px;" name="minInsuredFee" value="${pointRule.minInsuredFee!}" data-rule="required(#isInsured:checked);range(0~)">元
            <#else>
                <input type="text" style=" width:110px;" name="rate" readonly="readonly"  value="${pointRule.rate!}" data-rule="required(#isInsured:checked);range(0~100);integer;"> % 最低保价费
                <input type="text" style=" width:110px;" name="minInsuredFee" readonly="readonly" value="${pointRule.minInsuredFee!}" data-rule="required(#isInsured:checked);range(0~)">元

            </#if>

            <p class="text-muted">当用户需要保价后，一般是按照货物总金额的《费率》计算，但是保价金额最低不低于《最低保价费》</p>
          </td>
        </tr>
        <tr>
          <td width="90">设置地区运费</td>
          <td colspan="4">
            <input name="areaFeeType" type="radio" value="1" <#if pointRule.areaFeeType=="1">checked</#if> > 统一地区运费
            <input name="areaFeeType" type="radio" value="2" <#if pointRule.areaFeeType=="2">checked</#if> disabled> 指定地区运费（暂不支持）
            <p class="text-muted">统一地区运费》：全部的地区都使用默认的《重量设置》中的计费方式。《指定地区运费》：单独指定部分地区的运费</p>
          </td>
        </tr>
        <tr>
          <td width="120">描述</td>
          <td colspan="3"><textarea name="description" data-rule="length[~200]" rows="3" cols="60">${pointRule.description!}</textarea></td>
          <td></td>
        </tr>
		<tr>
          <td width="120">排序</td>
          <td colspan="3"><input type="text" name="sort" data-rule="required;integer(+);" value="${pointRule.sort}" style=" width:330px;" ><label style='color:red;'>*</label></td>
          <td>
          </td>
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
          <td width="120">配送方式名称</td>
          <td colspan="3"><input type="text" name="name" data-rule="required;length[2~10]" class="layui-input" style="width:330px;"><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
		<tr>
          <td>类型</td>
          <td width="150"><input name="type" checked type="radio" value="1" >先收款后发货 
          </td>
          <td width="150"><input name="type" type="radio" value="2"> 货到付款
          </td>
        </tr>
		<tr>
          <td width="120">重量设置</td>
          <td colspan="4">
			<select name="firstWeight" style="height:30px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=firstWeight" data-val="" data-rules="fieldValue:fieldKey" >
            </select> 首重费用
            <input type="text" style=" width:110px;" name="firstWeightFee" data-rule="required;range(0~)" value="0"> 续重重量
			<select name="addedWeight" style="height:30px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=addedWeight" data-val="" data-rules="fieldValue:fieldKey" >
            </select>
                     续重费用
            <input type="text" style=" width:110px;" name="addedWeightFee" data-rule="required;range(0~)" value="0">
            <p class="text-muted">根据重量来计算运费，当物品不足《首重重量》时，按照《首重费用》计算，超过部分按照《续重重量》和《续重费用》乘积来计算</p>
          </td>
        </tr>
		<tr>
          <td width="90">支持保价</td>
          <td colspan="4">
            <input type="checkbox" lay-skin="primary"  name="isInsured" value="1" id="isInsured"> 支持物流保价 费率
			<input type="text" style=" width:110px;" name="rate" data-rule="required(#isInsured:checked);range(0~100);integer;" readonly="readonly"> % 最低保价费
			<input type="text" style=" width:110px;" name="minInsuredFee" data-rule="required(#isInsured:checked);range(0~)" readonly="readonly">元
            <p class="text-muted">当用户需要保价后，一般是按照货物总金额的《费率》计算，但是保价金额最低不低于《最低保价费》</p>
          </td>
        </tr>
        <tr>
          <td width="90">设置地区运费</td>
          <td colspan="4">
            <input name="areaFeeType" type="radio" value="1" checked> 统一地区运费
            <input name="areaFeeType" style="margin-left:30px;" type="radio" value="2" disabled> 指定地区运费（暂不支持）
            <p class="text-muted">统一地区运费》：全部的地区都使用默认的《重量设置》中的计费方式。《指定地区运费》：单独指定部分地区的运费</p>
          </td>
        </tr>
        <tr>
          <td width="120">描述</td>
          <td colspan="3"><textarea name="description" data-rule="length[~200]" rows="3" cols="60"></textarea></td>
          <td></td>
        </tr>
		<tr>
          <td width="120">排序</td>
          <td colspan="3"><input type="text" name="sort" data-rule="required;integer(+);"  style=" width:330px;" ><label style='color:red;'>*</label></td>
          <td>
          </td>
        </tr>
		<tr>
          <td>状态</td>
          <td width="150"><input name="status"  type="radio" value="true" >启用 
          </td>
          <td width="150"><input name="status" checked type="radio" value="false">停用
          </td>
        </tr>
		</#if>
        <tr>
          <td></td>
          <td colspan="3"><input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
            <a href="${ctx}/financed/shippingList.php?name=${(shippingRq.name)!}&page=${(shippingRq.page)!}" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>
</body>
<script data-main="${ctx}/js/abc/consumer/myPlugs" src="${ctx}/js/require.js"></script>
</html>