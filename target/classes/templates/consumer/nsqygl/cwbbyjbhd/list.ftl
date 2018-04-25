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
    <form action="${ctx}/consumerManager/consumer/nsqygl/cwbbyjbhd/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">纳税人识别号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.nsrsbh)!}" name="nsrsbh" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>

      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <th>纳税人识别号</th>
                    <th>纳税人名称</th>
                    <th>报表报送期限名称</th>
                    <th>有效期起止</th>
					<th>有效标志</th>
                  <#--  <@shiro.hasPermission name="uc_taxpayer:detail">
                    <th>操作选项</th>
                    </@shiro.hasPermission>-->
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if cwbbyjbhdRs??&&cwbbyjbhdRs.cwbbyjbhdList?? && ( cwbbyjbhdRs.cwbbyjbhdList?size gt 0 )>
					  <#list cwbbyjbhdRs.cwbbyjbhdList as cwbbyjbhd>
					  <tr>
					     <td class="td_i">${BaseRq.offset + cwbbyjbhd_index + 1}</td>
						 <td>${(cwbbyjbhd.nsrsbh)!}</td>
						 <td>${(cwbbyjbhd.nsrmc)!}</td>
                          <td>${(cwbbyjbhd.bbbsqmc)!}</td>
                          <td>${(cwbbyjbhd.yxqq)!}~${(cwbbyjbhd.yxqz)!}</td>
                          <td>
                              <#if cwbbyjbhd.yxbz?? && cwbbyjbhd.yxbz =="Y"  >
                                  <div class="btn btn-success btn-xs ">是</div>
                              <#else>
                                  <div class="btn btn-danger btn-xs ">否</div>
                              </#if>
                          </td>
                      </tr>
					  </#list>
                    <#elseif cwbbyjbhdRs??&&cwbbyjbhdRs.cwbbyjbhdList??&& ( cwbbyjbhdRs.cwbbyjbhdList?size == 0 ) >
                    <tr>
                        <td colspan="6" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                    </tr>
                    <#elseif cwbbyjbhdRs??&&(!cwbbyjbhdRs.cwbbyjbhdList??)>
                    <tr>
                        <td colspan="6" style="font-size:15px; color: #999;text-align: center">--${(cwbbyjbhdRs.msg)!}--</td>
                    </tr>
			        </#if>
                </tbody>
            </table>
		 <table>
          <#--<tbody>
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
          </tbody>-->
        </table>
        </div>		
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/nsqygl.js" src="${ctx}/js/require.js"></script>
</body>
</html>