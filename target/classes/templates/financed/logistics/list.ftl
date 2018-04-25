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
    <form action="${ctx}/financed/logisticsList.php?compName=${(BaseRq.compName)!}" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">物流公司名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.compName)!}" name="compName" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/financed/logisticsEdit.php" class="layui-btn layui-btn-normal fr">添加物流公司</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th>序号</th>
                    <th>物流公司名称</th>
                    <th>公司代码</th>
                    <th>网址</th>
                    <th>排序</th>
                    <th>修改时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if logisticsRs?? && ( logisticsRs?size gt 0 )> 
					  <#list logisticsRs as logistics>
					  <tr>
					     <td width="30" class="td_i">${BaseRq.offset + logistics_index + 1}</td>
						 <td>${(logistics.compName)!}</td>
						 <td>${(logistics.compCode)!}</td>
						 <td>${(logistics.compUrl)!}</td>
						 <td>${(logistics.sort)!}</td>
						 <td>${logistics.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td><a href="${ctx}/financed/logisticsEdit.php?id=${logistics.id}&dpType=2">编辑</a>
							|<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/financed/logisticsDel.php?id=${logistics.id}" class="pn-opt">删除</a>
						 </td>
					  </tr>
					  </#list>
			        </#if>
                </tbody>
            </table>
		 <table>
          <tbody class="yy_fanye">
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
				<input type="hidden" value="${ctx}/financed/logisticsList.php" id="currLink">
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