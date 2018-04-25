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
<!--查看用户信息-->
<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/trueName_list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.realName)!}" name="realName" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.phone)!}" name="phone" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">认证状态</label>
                    <div class="layui-input-inline">
                        <select name="validStatus" class="cxinput"  id="validStatus">
                            <option value=""></option>
                            <option <#if BaseRq.validStatus?? && BaseRq.validStatus=="0">selected</#if> value="0">未认证</option>
                            <option <#if BaseRq.validStatus?? && BaseRq.validStatus=="1">selected</#if> value="1">待认证</option>
                            <option <#if BaseRq.validStatus?? && BaseRq.validStatus=="2">selected</#if> value="2">已认证</option>
                            <option <#if BaseRq.validStatus?? && BaseRq.validStatus=="3">selected</#if> value="3">认证失败</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>用户名</th>
                    <th>昵称</th>
					<th>真实姓名</th>                  
					<th>手机号</th>
					<th>用户状态</th>
					<th>认证状态</th>
                    <th>认证渠道</th>
					<th>提交时间</th>
					<th>审核时间</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if consumers?? && ( consumers?size gt 0 )> 
					  <#list consumers as consumer>
					   <tr>
					     <td class="td_i">${BaseRq.offset + consumer_index + 1}</td>
						 <td>
                          <#if canQuery??>
                             <a class="ljc_00bcd4" data-type="lookdialog" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${consumer.userId}">${(consumer.username)!}</a>
                              <#else>
                              ${(consumer.username)!}
                          </#if>
                         </td>
						 <td>${(consumer.nickname)!}</td>
						 <td>${(consumer.realName)!}</td>
						 <td>${(consumer.phone)!}</td>
						 <td>
							<#if consumer.status?? && consumer.status ><div class="btn btn-success btn-xs ">正常</div>
                            <#else><div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                         </td>
						 <td>
						  <#if consumer.validStatus??>
							<#if consumer.validStatus == "0">
                                   <div class="btn btn-danger btn-xs ">未认证</div>
                            <#elseif consumer.validStatus == "1">
								   <div class="btn btn-warning btn-xs ">待认证</div>
							<#elseif consumer.validStatus == "2">
								   <div class="btn btn-success btn-xs ">已认证</div>
							<#else>
                                <div class="btn btn-danger btn-xs ">认证失败</div>
                            </#if>
						   </#if>
                         </td>
                           <td>
                               <#if consumer.validType??>
                                   <#if consumer.validType == "0">
                                       自动认证
                                   <#elseif consumer.validType == "1">
                                      手动认证
                                   </#if>
                               </#if>
                           </td>
						 <td>${(consumer.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						 <td><#if consumer.validTime??>${(consumer.validTime?string("yyyy-MM-dd HH:mm:ss"))!}</#if></td>
						 <td>
							<#if consumer.validStatus?? && consumer.validStatus == "0">
								 <a href="${ctx}/consumerManager/trueName_query.php?userId=${consumer.userId}">查看</a>
							<#elseif consumer.validStatus?? && (consumer.validStatus == "2"||consumer.validStatus == "3")>
                                 <a href="${ctx}/consumerManager/trueName_query.php?userId=${consumer.userId}">重新审核</a>
							<#else>
                                 <a href="${ctx}/consumerManager/trueName_query.php?userId=${consumer.userId}">审核</a>
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

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
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