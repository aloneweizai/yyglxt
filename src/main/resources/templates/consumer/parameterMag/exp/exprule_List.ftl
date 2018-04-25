<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
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
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/exprule/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">规则名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a  class="layui-btn layui-btn-normal fr" href="${ctx}/consumerManager/exprule/edit.php?dpType=1">添加规则</a>
            </div>
        </div>


      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>规则名称</th>
                    <th>规则代码</th>
                    <th>经验值</th>
					<th>计算周期</th>
					<th>计算次数</th>
                    <th>描述</th>
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
						 <td>${(pointRule.code)!}</td>
						 <td>${(pointRule.exp gt 0)?string("+","")}${pointRule.exp?c}</td>
						 <td>
						    <#if pointRule.period?? && pointRule.period=="Y">年
							<#elseif pointRule.period?? && pointRule.period=="M">月
							<#elseif pointRule.period?? && pointRule.period=="D">日
							<#elseif pointRule.period?? && pointRule.period=="O">一次性
							<#elseif pointRule.period?? && pointRule.period=="A">无限制</#if>
						 </td>
						 <td>${pointRule.degree!}</td>
						 <td>${(pointRule.description)!}</td>
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
                             <a data-type="delSig" data-confirm="确认停用?" data-okMsg="停用成功!" data-failMsg="停用失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/enable.php?delType=2&status=false&id=${pointRule.id}" class="pn-opt">停用</a>
                         <#else>
							 <a data-type="delSig" data-confirm="确认启用?" data-okMsg="启用成功!" data-failMsg="启用失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/enable.php?delType=2&status=true&id=${pointRule.id}" class="pn-opt">启用</a> |
                             <a href="${ctx}/consumerManager/exprule/edit.php?dpType=2&id=${pointRule.id}">编辑</a> | 
							 <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/del.php?delType=2&id=${pointRule.id}" class="pn-opt">删除</a>
                         </#if>
						 <!--| <a class="pn-opt" id="settings" data-id="${pointRule.code}" data-name="${pointRule.name}">详细设置</a> -->
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
				<input type="hidden" value="${ctx}/consumerManager/exprule/list.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="top:-500px;width:80%">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">经验值规则设置(<span style="font-size:12px; color: #999;">温馨提示：累计次数填0不限制次数</span>)</h4>
		  <input type='hidden' id="expruleId" />
        </div>
        <div class="modal-body">
		<form id="ExpCodexForm">
            <table class="layui-table" lay-size="sm">
            <caption id="expruleName"></caption>
            <thead style="font-weight:bold;">
              <th width="10%">客户端类型</th>
              <th width="7%">是否享用</th>
              <th width="15%">经验值</th>
              <th width="15%">累计周期</th>
              <th width="15%">累计次数</th>
			  <th width="23%">描述</th>
            </thead>
			<tbody id="mytb">
    			<#if clienttype?? && ( clienttype?size gt 0 )> 
    			<#list clienttype as type>
                <tr id="${type.fieldValue}">
                  <td>${type.fieldKey!}<input type='hidden' id="clientType" name='clientType' value='${type.fieldValue!}'/></td>
                  <td><input id="status${type_index}" name="status${type_index}" value="true" type="checkbox" lay-skin="primary"  /></td></td>
                  <td><input readonly id="uexp${type_index}" data-rule='required(#status${type_index}:checked);integer(+);length(1~5);'  name='uexp${type_index}'  type="text" value="" style="width: 100px;" /></td>
                  <td>
					<select id="period${type_index}" name='period${type_index}' disabled>
						<option value='A'>无限制</option>
						<option value='Y'>年</option>
						<option value='M'>月</option>
						<option value='D'>日</option>
					</select>
				  </td>
                  <td><input readonly id="degree${type_index}" data-rule='required(#status${type_index}:checked);integer(+0);length(1~5);' name='degree${type_index}'  type="text" value="" style="width: 100px;" /></td>
    			  <td><textarea readonly id="description${type_index}" data-rule='required(#status${type_index}:checked);length(1~500);' name='description${type_index}' style="width: 200px;"></textarea></td>
                </tr>
    			</#list>
    			</#if>
			  </tbody>
			</table>
		</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
          <button type="button" class="layui-btn layui-btn-normal" data-dismiss="save">保存</button>
        </div>
      </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/consumer/explist.js" src="${ctx}/js/require.js"></script>
</body>
</html>
