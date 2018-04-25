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
    <form action="${ctx}/consumerManager/consumer/vipLog.php" method="get" id="consumerListForm" class="layui-form">
        <table class="ny_tab_t">
        <tr>
            <td style="text-align:left">
              <input type="hidden" name="userId" id="userId" value="${BaseRq.userId}">
            </td>
        	<#--<td>
              <div class="nycon_l_t_btn text-right">
        		 <a href="${ctx}/consumerManager/consumer/list.php" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a>
              </div>
            </td>-->
		 </tr>
        </table>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>时间</th>
                    <th>会员等级</th>
                    <th>会员有效期</th>
                    <th>来源</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if vipLogs?? && ( vipLogs?size gt 0 )> 
					  <#list vipLogs as vipLog>
					  <tr>
					    <td class="td_i">${BaseRq.offset + vipLog_index + 1}</td>
                          <td>${vipLog.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>

						 <td>${(vipLog.levelId)!}</td>
                          <td>${vipLog.vipExpireDate!?string("yyyy-MM-dd HH:mm:ss")}</td>
                          <td>${(vipLog.source)!}</td>
					  </tr>
					  </#list>
			        </#if>
                </tbody>
            </table>
		 <table class="yy_fanye">
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
<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>