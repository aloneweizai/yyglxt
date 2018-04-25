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
    <form action="${ctx}/consumerManager/honour/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">等级名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/consumerManager/honour/edit.php?dpType=1"  class="layui-btn layui-btn-normal fr">添加等级勋章</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>等级名称</th>
                    <th>经验值范围</th>
					<th>勋章</th>                  
					<th>状态</th>
					<th>修改时间</th>
					<th>相关操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if pointRules?? && ( pointRules?size gt 0 )> 
					  <#list pointRules as pointRule>
					  <tr>
					    <td class="td_i">${BaseRq.offset + pointRule_index + 1}</td>
					     <td>${(pointRule.name)!}</td>
						 <td>${(pointRule.minValue)!}&nbsp;~&nbsp;${(pointRule.maxValue)!}</td>
						 <td>${(pointRule.medal)!}&nbsp;<img src="${ctx}/images/medals/${(pointRule.medalIcon)!}.png" height="40" width="40"></td>
						 <td>
							<#if pointRule.status?? && pointRule.status >
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
						 </td>
						 <td>${pointRule.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td>
						 <#if pointRule.status?? && pointRule.status >
                             <a data-type="delSig" data-confirm="确认停用?" data-okMsg="停用成功!" data-failMsg="停用失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/enable.php?delType=6&status=false&id=${pointRule.id}" class="pn-opt">停用</a>
                         <#else>
							 <a data-type="delSig" data-confirm="确认启用?" data-okMsg="启用成功!" data-failMsg="启用失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/enable.php?delType=6&status=true&id=${pointRule.id}" class="pn-opt">启用</a> |
                             <a href="${ctx}/consumerManager/honour/edit.php?dpType=2&id=${pointRule.id}">编辑</a> | 
							 <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/del.php?delType=6&id=${pointRule.id}" class="pn-opt">删除</a>
                         </#if>		
							</td>
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
				<input type="hidden" value="${ctx}/consumerManager/honour/list.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/page.js" src="${ctx}/js/require.js"></script>
</body>
</html>