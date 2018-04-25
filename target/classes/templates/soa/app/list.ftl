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
    <form action="${ctx}/app/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">应用名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">应用状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="cxinput">
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status>selected</#if>  value="true">启用</option>
                            <option <#if BaseRq.status?? && !BaseRq.status>selected</#if> value="false">停用</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">授权时间</label>
                    <div class="layui-input-inline">
                        <input  type="text" value="${(BaseRq.startTime)!}" id="test30" name="startTime" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input  type="text" value="${(BaseRq.endTime)!}" id="test31" name="endTime" class="layui-input">
                    </div>
                </div>
                <#--<div class="layui-inline">
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
                </div>-->
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
                <a href="${ctx}/app/new.php"  class="layui-btn layui-btn-normal fr">添加接入应用</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
					<th>应用名称</th>
                    <th>应用昵称</th>
                    <th>授权时间起止</th>
                    <th>状态</th>
					<th>描述</th>
                    <th>上次修改时间</th>
					<th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                     <#if apps?? && ( apps?size gt 0 )> 
					  <#list apps as app>
					    <tr>
					      <td width="30" class="td_i">${BaseRq.offset + app_index + 1}</td>
						  <td>${app.name!}</td>
						  <td>${app.nickname!}</td>
						  <td>${app.startTime?string("yyyy-MM-dd HH:mm:ss")}&nbsp;--&nbsp;${app.endTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						  <td>
							<#if app.status?? && app.status >
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                          </td>
						  <td>${app.remark!}</td>
						  <td>${app.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
						  <td>
							<a data-type="lookdialog" href="javascript:;" data-url="${ctx}/app/look.php?id=${app.id}">查看</a>|
							<#if app.status?? && app.status >
                                <a data-type="delSig" data-confirm='确认停用?' data-okMsg='停用成功!' data-failMsg='停用失败' href="javascript:void(0);" data-href="${ctx}/app/check.php?id=${app.id}&status=false" class="pn-opt">停用</a>
                            <#else>
                                <a data-type="delSig" data-confirm='确认启用?' data-okMsg='启用成功!' data-failMsg='启用失败' href="javascript:void(0);" data-href="${ctx}/app/check.php?id=${app.id}&status=true" class="pn-opt">启用</a>
							    |&nbsp;<a href="${ctx}/app/info.php?id=${app.id}" class="pn-opt">编辑</a>
                            </#if>
							|&nbsp;<a href="${ctx}/app/settinglist.php?appId=${app.id}" class="pn-opt">接口授权</a>
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
				<input type="hidden" value="${ctx}/app/list.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"  style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>