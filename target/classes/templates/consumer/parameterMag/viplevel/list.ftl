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
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/viplevel/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">等级名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.level)!}" name="level" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a  class="layui-btn layui-btn-normal fr" href="${ctx}/consumerManager/viplevel/edit.php?dpType=1">添加等级</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>等级名称</th>
					<th>等级代码</th>
					<th>成本价格</th>
					<th>市场价格</th>
					<th>销售价格</th>
					<th>兑换积分</th>
					<th>赠送积分</th>
                    <th>成长因子</th>
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
						 <td><img height="32" width="32" src="${imgPth!}${(pointRule.imgUrl)!}">${(pointRule.level)!}</td>
					     <td>${(pointRule.levelCode)!}</td>
						 <td>￥${(pointRule.costPrice?c)!}</td>
						 <td>￥${(pointRule.marketPrice?c)!}</td>
						 <td>￥${(pointRule.salePrice?c)!}</td>
						 <td>${(pointRule.pointsPrice?c)!}</td>
						 <td>${(pointRule.sendPoints?c)!}</td>
						 <td>${(pointRule.factor)!}</td>
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
                             <a data-type="delSig" data-confirm="确认停用?" data-okMsg="停用成功!" data-failMsg="停用失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/enable.php?delType=3&status=false&id=${pointRule.id}" class="pn-opt">停用</a>
                         <#else>
							 <a data-type="delSig" data-confirm="确认启用?" data-okMsg="启用成功!" data-failMsg="启用失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/enable.php?delType=3&status=true&id=${pointRule.id}" class="pn-opt">启用</a> |
                             <a href="${ctx}/consumerManager/viplevel/edit.php?dpType=2&id=${pointRule.id}">编辑</a> | 
							 <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/consumerManager/del.php?delType=3&id=${pointRule.id}" class="pn-opt">删除</a>
                         </#if>	
						 | <a class="pn-opt" id="settings" data-id="${pointRule.id!}" data-code="${pointRule.levelCode!}" data-name="${pointRule.level!}">特权设置</a>
						 </td>
					  </tr>
					  </#list>
			        </#if>
                </tbody>
            </table>
		 <table class="yy_fanye">
          <tbody>
            <tr>
			  <!--<td style="text-align: left;"><input type='button' class="btn btn-default btn-sm pn-opt" value='批量删除' /></td>-->
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
				<input type="hidden" value="${ctx}/consumerManager/viplevel/list.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document" style="top:-500px;width:80%">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">会员等级特权设置(<span style="font-size:12px; color: #999;">温馨提示：参数1，参数2，参数3，参数4不能填写中文</span>)</h4>
		  <input type='hidden' id="vipprivilegelevelid" />
        </div>
        <div class="modal-body">
		 <form id="vipPrivilegeForm">
             <table class="layui-table" lay-size="sm">
            <caption id="vipprivilegelevelname"></caption>
            <thead style="font-weight:bold;">
              <th width="10%">用户权益</th>
              <th width="7%">是否享用</th>
              <th width="15%">参数1</th>
              <th width="15%">参数2</th>
              <th width="15%">参数3</th>
              <th width="15%">参数4</th>
			  <th width="23%">描述</th>
            </thead>
			<tbody id="mytb">
			  <#if vipPrivileges?? && ( vipPrivileges?size gt 0 )> 
    			<#list vipPrivileges as vipPrivilege>
                <tr id="${vipPrivilege.id!}">
                  <td>${vipPrivilege.name!}<input type='hidden' id="privilegeId" name='privilegeId' value='${vipPrivilege.code!}'/></td>
                  <td><input id="status" name="status${vipPrivilege_index}" value="true" type="checkbox" lay-skin="primary"  /></td></td>
                  <td><input readonly id="val1" data-rule='no_CN' name='val1${vipPrivilege_index}'  type="text" value="" style="width: 100px;" /></td>
                  <td><input readonly id="val2" data-rule='no_CN' name='val2${vipPrivilege_index}'  type="text" value="" style="width: 100px;" /></td>
                  <td><input readonly id="val3" data-rule='no_CN' name='val3${vipPrivilege_index}'  type="text" value="" style="width: 100px;" /></td>
                  <td><input readonly id="val4" data-rule='no_CN' name='val4${vipPrivilege_index}'  type="text" value="" style="width: 100px;" /></td>
    			  <td><textarea readonly id="description" name='description${vipPrivilege_index}' style="width: 200px;"></textarea></td>
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
	
</div>
<script data-main="${ctx}/js/abc/consumer/viplist.js" src="${ctx}/js/require.js"></script>
</body>
</html>