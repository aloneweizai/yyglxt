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
    <form action="${ctx}/apilog/appApiinfo" method="get" id="consumerListForm" class="layui-form">
        <table class="ny_tab_t">
        <tr>
		   <td style="text-align:left;padding-left:30px;font-size:18px;">
			 <label>应用名称：</label>${app.nickname!}&nbsp;&nbsp;
			 <input  type="hidden" value="${(BaseRq.startTime)!}" name="startTime" class="cxinput">
			 <input  type="hidden" value="${(BaseRq.appId)!}" name="appId" class="cxinput">
			 <input  type="hidden" value="${(BaseRq.uri)!}" name="uri" class="cxinput">
			 <input  type="hidden" value="${(BaseRq.method)!}" name="method" class="cxinput">
			 <input  type="hidden" value="${(BaseRq.version)!}" name="version" class="cxinput">
		   </td>
		   <td>
			<div class="nycon_l_t_btn text-right">
			  <a href="${ctx}/apilog/appApiCounts?appId=${(BaseRq.appId)!}&startTime=${(BaseRq.startTime)!}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a>
            </div>
		   </td>
        </tr>
      </table>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
					<th>接口</th>
                    <th>访问IP</th>
				    <th>访问时间</th>
                    <th>访问耗时(毫秒)</th>
                    <th>客户端类型</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if appApiinfoRs?? && ( appApiinfoRs?size gt 0 )> 
					  <#list appApiinfoRs as appApiinfo>
					  <tr>
					     <td class="td_i">${BaseRq.offset + appApiinfo_index + 1}</td>
						 <td>${(appApiinfo.uri)!}</td>
					     <td>${(appApiinfo.ip)!}</td>
						 <td>${(appApiinfo.bgtime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						 <td>${(appApiinfo.outTime-appApiinfo.inTime)!}</td>
						 <td>${(appApiinfo.userAgent)!}</td>
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

<script data-main="${ctx}/js/abc/consumer/page.js" src="${ctx}/js/require.js"></script>
</body>
</html>