<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
	<script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/consumer/taxpayerBind_pwdlog.php" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <input type="hidden" name="nsrsbh" id="nsrsbh" value="${BaseRq.nsrsbh}" class="layui-input">
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>纳税人识别号</th>
                    <th>法人名称</th>
                    <th>法人证件号</th>
                    <th>修改结果</th>
                    <th>IP,时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if taxpayerBindRs?? && ( taxpayerBindRs?size gt 0 )> 
					  <#list taxpayerBindRs as taxpayerBind>
					  <tr>
					     <td class="td_i">${BaseRq.offset + taxpayerBind_index + 1}</td>
						 <td>${(taxpayerBind.username)!}</td>
						 <td>${(taxpayerBind.nickname)!}</td>
						 <td>${(taxpayerBind.nsrsbh)!}</td>
						 <td>${(taxpayerBind.frmc)!}</td>
						 <td>${(taxpayerBind.frzjh)!}</td>
                          <td>${(taxpayerBind.message)!}</td>
                          <td>${(taxpayerBind.ip)!}&nbsp;,&nbsp;${(taxpayerBind.createTime?string("yyyy-MM-dd HH:mm:ss"))!}&nbsp;</td>
                      </tr>
					  </#list>
			        </#if>
                </tbody>
            </table>
		 <table class=yy_fanye">
          <tbody>
            <tr>
              <td align="center">
                             共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
				每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp; 
				 当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
				<input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
			    <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/taxpayerBind.js" src="${ctx}/js/require.js"></script>
</body>
</html>