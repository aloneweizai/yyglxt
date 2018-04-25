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
	<script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/api/list.php" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">接口名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">接口地址</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.url)!}" name="url" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">接口状态</label>
                    <div class="layui-input-inline">
                        <select name="status"class="cxinput">
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status>selected</#if> value="true">正常</option>
                            <option <#if BaseRq.status?? && !BaseRq.status>selected</#if> value="false">停用</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">所属系统</label>
                    <div class="layui-input-inline">
                        <select name="dictId"class="cxinput">
                            <option value=""></option>
                        <#if apps?? && ( apps?size gt 0 )>
                            <#list apps as app>
                                <option <#if BaseRq.dictId?? && BaseRq.dictId==app.id>selected</#if>  value="${app.id}" >${app.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/api/edit.php?dpType=1"  class="layui-btn layui-btn-normal fr">添加服务接口</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <th>接口名称</th>
                    <th>接口地址</th>
                    <th>调用方式</th>
					<th>所属系统</th>
                    <th>接口版本</th>
                    <#--<th>是否验证身份</th>-->
                    <th>接口状态</th>
					<th>上次修改时间</th>
					<th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                     <#if apis?? && ( apis?size gt 0 )> 
					  <#list apis as api>
					    <tr>
					      <td width="30" class="td_i">${BaseRq.offset + api_index + 1}</td>
						  <td>${api.name!}</td>
						  <td>${api.uri!}</td>
						  <td>${api.method!}</td>
						  <td>${api.dictName!}</td>
						  <td>${api.version!}</td>
						  <#--<td>
							<#if api.authentication?? && api.authentication >
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
						  </td>-->
						  <td>
							<#if api.status?? && api.status >
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
						  </td>
						  <td>${(api.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						  <td>
							 <#if api.status?? && api.status >
                                <a data-type="delSig" data-confirm="确认停用?" data-okMsg="停用成功!" data-failMsg="停用失败" href="javascript:void(0);" data-href="${ctx}/api/enableApi.php?id=${api.id}&status=false">停用</a>
                            <#else>
								<a data-type="delSig" data-confirm="确认启用?" data-okMsg="启用成功!" data-failMsg="启用失败" href="javascript:void(0);" data-href="${ctx}/api/enableApi.php?id=${api.id}&status=true">启用</a> |
                                <a href="${ctx}/api/edit.php?dpType=2&id=${api.id}">编辑</a> | 
							    <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/api/del.php?id=${api.id}" class="pn-opt">删除</a>
                            </#if>
						   </td>
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
				<input type="hidden" value="${ctx}/api/list.php" id="currLink">
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