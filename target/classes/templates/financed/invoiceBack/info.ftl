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
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <table class="layui-table" lay-size="sm">
		  <caption>原始快递信息</caption>
          <tr style="font-weight:bold;">
            <td>名称</td>
            <td>发票号码</td>
            <td>发票代码</td>
            <td>发票性质</td>
            <td>发票内容</td>
            <td>开票金额</td>
          </tr>
		  <#if invoiceBack.invoiceBO??>
		   <tr>
            <td>名称</td>
            <td>${invoiceBack.invoiceBO.invoiceNo}</td>
            <td>${invoiceBack.invoiceBO.invoiceCode}</td>
            <td><#if invoiceBack.invoiceBO.property?? && invoiceBack.invoiceBO.property=="1">纸质发票<#else>电子发票</#if></td>
            <td>
			  <#if invoicecontents?? && ( invoicecontents?size gt 0 )> 
			   <#list invoicecontents as invoicecontent>
                   <#if invoiceBack.invoiceBO.content == invoicecontent.fieldValue>${invoicecontent.fieldKey}</#if>
			   </#list>
		      </#if>
			</td>
            <td>￥${invoiceBack.invoiceBO.amount?string("0.00")}</td>
          </tr>
		  </#if>
		</table>
		<form action="${ctx}/financed/doback.php?id=${invoiceBack.id}&expressId=${invoiceBack.expressId}" next-url="${ctx}/financed/invoiceBackList.php" method="post" id="consumerListForm" class="layui-form">
            <table class="layui-table" lay-size="sm">
		  <caption>提交申请信息</caption>
          <tr>
            <td style="font-weight:bold;width:30%;">退票原因</td>
            <td>
			  <#if ibackreason?? && ( ibackreason?size gt 0 )> 
			   <#list ibackreason as reason>
                   <#if invoiceBack.invoiceBO.content == reason.fieldValue>${reason.fieldKey}</#if>
			   </#list>
		      </#if>
			</td> 
          </tr>
		  <tr>
            <td style="font-weight:bold;">备注信息</td>
            <td>${invoiceBack.userRemark!}</td> 
          </tr>
		  <tr>
            <td style="font-weight:bold;">快递公司</td>
            <td>${invoiceBack.expressComp!}</td> 
          </tr>
		  <tr>
            <td style="font-weight:bold;">快递单号</td>
            <td>${invoiceBack.expressNo!}</td> 
          </tr>
		  <#if invoiceBack.status?? && invoiceBack.status=="1">
		  <tr>
            <td style="font-weight:bold;">处理</td>
            <td>
				<input name="status" type="radio" value="2"  checked>同意
				<input name="status" style='margin-left:50px' id="refuse"  type="radio" value="3" >拒绝
            </td> 
          </tr>
		  <tr>
            <td style="font-weight:bold;">处理意见</td>
            <td><textarea id="introduction" name="adminRemark" data-rule="required(#refuse:checked)" rows="3" cols="60"></textarea></td> 
          </tr>
		  <#else>
		  <tr>
            <td style="font-weight:bold;">状态</td>
            <td>
			  <#if ibackstatus?? && ( ibackstatus?size gt 0 )> 
			   <#list ibackstatus as status>
                   <#if invoiceBack.status == status.fieldValue>${status.fieldKey}</#if>
			   </#list>
		      </#if>
            </td> 
          </tr>
		  <tr>
            <td style="font-weight:bold;">处理意见</td>
            <td>${invoiceBack.adminRemark}</td> 
          </tr>
		  </#if>
		  <tr>
            <td style="font-weight:bold;"></td>
            <td style="text-align:right;padding-right:80px;">
				<#if invoiceBack.status=="1">
					<input type="button" name="button" id="consumer_submit" value="提交" class="layui-btn">
				<#elseif invoiceBack.status=="2">
					<input type="button" name="button" id="consumer_submit2" value="确认收票" class="layui-btn">
				</#if>
                <a href="${ctx}/financed/invoiceBackList.php" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
			</td> 
          </tr>
		</table>
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/myPlugs" src="${ctx}/js/require.js"></script>
</body>
</html>