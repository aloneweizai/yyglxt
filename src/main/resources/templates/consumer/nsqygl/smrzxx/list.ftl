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
    <form action="${ctx}/consumerManager/consumer/nsqygl/smrz/list.php" method="get" id="consumerListForm" class="layui-form">
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
                    <th>办税人员类型名称</th>
                    <th>姓名</th>
                    <th>身份证件类型名称</th>
                    <th>身份证件号码</th>
                    <th>固定电话</th>
                    <th>移动电话</th>
                    <th>登记序号</th>
                    <th>认证有效期起止</th>
                    <th>认证状态</th>
                    <th>认证类型</th>
                    <th>审核状态</th>
                    <th>审核不通过原因</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if smrzxxRs?? &&smrzxxRs.dataList??&& ( smrzxxRs.dataList?size gt 0 )>
					  <#list smrzxxRs.dataList as smrzxx>
                      <tr>
                          <td class="td_i">${BaseRq.offset + smrzxx_index + 1}</td>
                          <td>${(smrzxx.gxrlxmc)!}</td>
                          <td>${(smrzxx.xm)!}</td>
                          <td>${(smrzxx.sfzjlxmc)!}</td>
                          <td>${(smrzxx.sfzjhm)!}</td>
                          <td>${(smrzxx.gddh)!}</td>
                          <td>${(smrzxx.yddh)!}</td>
                          <td>${(smrzxx.djxh)!}</td>
                          <td>${(smrzxx.rzyxqq)!}~${(smrzxx.rzyxqz)!}</td>
                          <td>${(smrzxx.rzztmc)!}</td>
                          <td>${(smrzxx.rzlxmc)!}</td>
                          <td>${(smrzxx.shztmc)!}</td>
                          <td>${(smrzxx.shbtgyy)!}</td>
                      </tr>
					  </#list>
                    <#elseif smrzxxRs??&&smrzxxRs.dataList??&& ( smrzxxRs.dataList?size == 0 ) >
                    <tr>
                        <td colspan="9" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                    </tr>
                    <#elseif smrzxxRs??&&(!smrzxxRs.dataList??)>
                    <tr>
                        <td colspan="13" style="font-size:15px; color: #999;text-align: center">--${(smrzxxRs.msg)!}--</td>
                    </tr>
			        </#if>
                </tbody>
            </table>
        </div>		
    </form>
</div>
<script data-main="${ctx}/js/abc/consumer/nsqygl.js" src="${ctx}/js/require.js"></script>
</body>
</html>