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
	<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">	
	<script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
		input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 5px;}
		.table>thead>tr>th,.table>tbody>tr>td{text-align:left;}
		.apisets{width:70px;}
	</style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
<table class="ny_tab_t">
  <tr>
  <td style="text-align:left;padding-left:30px;font-size:18px;">
	 <label style="font-weight:bold;">接入应用名称：</label>${app.name!} &nbsp;&nbsp;
  </td>
  <td>
	<div class="nycon_l_t_btn text-right">
		 <input type="button" value="保存" id="consumer_show"  class="btn btn-sm btn-info">
         <a href="javascript:location.href=document.referrer"  style="text-decoration:none;color:black" class="btn  btn-sm btn-default">返回</a>
    </div>
  </td>
 </tr>
</table>
<span><input type="hidden" value="${app.id!}" name="appId"></span>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <div class="depart_l" style="margin-left:0px;">
		<input type="text" id='guanjianzi' placeholder="授权树筛选关键字" style="display:none;"/>
        <div id="treeDemo" class="ztree"></div>
    </div>
    <form action="" method="get">
        <div class="nycon_list_b depart_r">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>接口名称</th>
                    <th>接口地址</th>
                    <th>调用方式</th>
                    <th><input id='timesPerMinute' placeholder="允许次数/分" style="width:100px" title="允许访问次数/分"/></th>
                    <th><input id='timesPerHour' placeholder="允许次数/时" style="width:100px" title="允许访问次数/时"/></th>
                    <th><input id='timesPerDay' placeholder="允许次数/天" style="width:100px" title="允许访问次数/天"/></th>
					<#--<th>是否验证身份&nbsp;&nbsp;<input name="gender" type="radio" value="true">是<input name="gender" type="radio" value="false">否</th>-->
					<th><!--状态--></th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody" id="apisets">
                </tbody>
            </table>
        </div>
    </form>
</div>
</div>
<div >
 <ul>
		<#if apiSystem?? && ( apiSystem?size gt 0 )> 
			<#list apiSystem as app>
			    <li class="org_li" data-tp="P" data-id="${app.id}" data-pid="" data-name="${app.fieldKey!}" data-uri='${app.fieldKey!}'></li>
			</#list>
		</#if>
		<#if apis?? && ( apis?size gt 0 )> 
			<#list apis as api>
			    <li class="org_li" data-tp="C" data-id="${api.id!}" data-authentication='${api.authentication?string('true','false')}' data-uri='${api.uri!}' data-method='${api.method!}' data-pid="${api.dictId!}" data-name="${api.name!}" ></li>
		    </#list>
		</#if>			  
    </ul>   
</div>

<div id="maskses" style='position:absolute;width:100%;height:100%;background: hsla(0,0%,7%,.7);text-align:center;top:0;left:0;padding-top:10%;'><img src="${ctx}/images/loading1.gif" ></div>

<script data-main="${ctx}/js/abc/soa/batchSetting" src="${ctx}/js/require.js"></script>
</body>
</html>