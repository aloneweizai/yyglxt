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
    <form action="${ctx}/consumerManager/systask/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">任务名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">任务类型</label>
                    <div class="layui-input-inline">
                        <select name="type" id="type">
                            <option value=""></option>
                        <#if DictBOs?? && ( DictBOs?size gt 0 )>
                            <#list DictBOs as Dict>
                                <option
                                    <#if BaseRq.type ?? && (BaseRq.type == Dict.fieldValue)>selected</#if>
                                    value="${Dict.fieldValue}">${Dict.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a  class="layui-btn layui-btn-normal fr" href="${ctx}/consumerManager/systask/edit.php?dpType=1" style="text-decoration:none;">添加新任务</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
					<th>任务编码</th>
                    <th>任务名称</th>
                    <th>起止时间</th>
                    <th>规则名称</th>
					<th>规则代码</th>
					<th>规则奖励</th>
                    <th>任务类型</th>
					<th>任务状态</th>
                    <th>修改时间</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if sysTasks?? && ( sysTasks?size gt 0 )> 
					  <#list sysTasks as sysTask>
					  <tr>
					     <td class="td_i">${BaseRq.offset + sysTask_index + 1}</td>
						 <td>${(sysTask.code)!}</td>
						 <td>
							<#if sysTask.imageUrl?? && sysTask.imageUrl!=''>
								<img height="32" width="32" src="${imgPth}${sysTask.imageUrl!}">
							<#else>
								<img height="32" width="32" src="${ctx}/images/default.jpg">
                            </#if>&nbsp;${(sysTask.name)!}
						 </td>
						 <td>
							<#if sysTask.startTime?? && sysTask.endTime??>
							  ${(sysTask.startTime?string("yyyy-MM-dd HH:mm:ss"))!}&nbsp;&nbsp;--&nbsp;&nbsp;${(sysTask.endTime?string("yyyy-MM-dd HH:mm:ss"))!}
							<#else>
							  
                            </#if>
							 </td>
						 <td>${(sysTask.ruleName)!}</td>
						 <td>${(sysTask.ruleCode)!}</td>
						 <td>${(sysTask.award)!}</td>
						 <td>
							<#if DictBOs?? && ( DictBOs?size gt 0 )>
						    <#list DictBOs as DictBO>
							    <#if sysTask.type == DictBO.fieldValue >
									 ${DictBO.fieldKey}
								</#if>	
							</#list>
						    </#if>
                         </td>
						 <td>
							<#if sysTask.status?? && sysTask.status >
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
						 </td>
						 <td>${(sysTask.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						 <td>
							 <#if sysTask.status?? && sysTask.status >
								<a data-type="delSig" data-confirm="确认停用该任务?" data-okMsg="停用成功!" data-failMsg="停用失败"  href="javascript:void(0);" data-href="${ctx}/consumerManager/sysTask/del.php?id=${sysTask.id}" class="pn-opt">停用</a> 
							 <#else>
								<a href="${ctx}/consumerManager/systask/edit.php?id=${sysTask.id}&dpType=2">编辑</a>					
                             </#if>					 
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
				<input type="hidden" value="${ctx}/consumerManager/systask/list.php" id="currLink">	
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