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
    <style>
		input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .contents{width: 80%;border: 1px solid #c0c0c0; margin: 0 auto;font-size:medium;height:auto;positon:relative;}
        .contents .header{background: #ccc;color: #000;height:35px;padding: 8px;}
        .contents .header>span{display: inline-block;margin-left: 20px;}
        .imgs{ float: left;width: 100px;height: 140px;border: 1px solid #ccc;margin: 20px 0 0 20px;}
        .cont_right{float: left;}
        ul{list-style: none;}
        li{float: left; margin-right: 100px;}
        .cont_first>span{display: inline-block;margin-left: 30px;margin-top: 20px;font-weight:bold;font-size:medium;}
        .cont_second{margin-top: 0px;margin-left: 20px;}
        .informs_one{margin-bottom: 10px;}
        hr{display: inline-block;width: 98%;border: 1px solid #ccc;margin-top: 20px;margin-left: 9px;}
        .cont_list{padding-bottom: 20px;}
		.nobord>tbody>tr>td{border-top:none;}
		.tablea>tbody>tr>td:nth-child(odd){font-weight:bold;text-align:right;padding-right:20px;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
  <#--
  <table class="table  table-hover">
    <tr style="background:#f5f5f5">
      <td  height="30">&nbsp;当前位置：&nbsp;<a href="${ctx}/consumerManager/consumer/taxpayerBind_list.php"><u>纳税人绑定查询</u></a> &gt;&gt; 详细信息</td>
    </tr>
  </table>
  -->
  <table class="table tablea">
		<#if userDzsb??>
        <tr><td width="190">登记序号</td><td>${(userDzsb.djxh)!}</td><td></td></tr>
        <tr><td width="190">纳税人识别号</td><td>${(userDzsb.nsrsbh)!}</td><td></td></tr>
		<tr><td width="190">纳税人名称</td><td>${(userDzsb.nsrmc)!}</td><td></td></tr>
		<tr><td width="190">社会信用代码</td><td>${(userDzsb.shxydm)!}</td><td></td></tr>
		<tr><td width="190">税务机关名称</td><td>${(userDzsb.swjgMc)!}</td><td></td></tr>
        <tr><td width="190">税务机关代码</td><td>${(userDzsb.swjgDm)!}</td><td></td></tr>
		<tr><td width="190">绑定状态</td><td>
		  <#if userDzsb.status?? && userDzsb.status >
            <div class="btn btn-success btn-xs ">有效</div>
            <#else>
            <div class="btn btn-danger btn-xs ">失效</div>
          </#if>
		</td><td></td></tr>
		<tr><td width="190">创建时间</td><td>${userDzsb.createTime?string("yyyy-MM-dd HH:mm:ss")}</td><td></td></tr>
		<tr><td width="190">修改时间</td><td>${userDzsb.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td><td></td></tr>
		</#if>
		<#if userHngs??>
        <tr><td width="190">登记序号</td><td>${(userHngs.djxh)!}</td><td></td></tr>
        <tr><td width="190">纳税人识别号</td><td>${(userHngs.nsrsbh)!}</td><td></td></tr>
		<tr><td width="190">纳税人名称</td><td>${(userHngs.nsrmc)!}</td><td></td></tr>
		<tr><td width="190">社会信用代码</td><td>${(userHngs.shxydm)!}</td><td></td></tr>
		<tr><td width="190">税务机关名称</td><td>${(userHngs.swjgMc)!}</td><td></td></tr>
        <tr><td width="190">税务机关代码</td><td>${(userHngs.swjgDm)!}</td><td></td></tr>
		<tr><td width="190">办税员</td><td>${(userHngs.bsy)!}</td><td></td></tr>
		<tr><td width="190">实名认证状态</td>
			<td>${userHngs.smrzzt!}</td>
		</tr>
		<tr><td width="190">绑定状态</td><td>
		  <#if userHngs.status?? && userHngs.status >
            <div class="btn btn-success btn-xs ">有效</div>
            <#else>
            <div class="btn btn-danger btn-xs ">失效</div>
          </#if>
		</td><td></td></tr>
		<tr><td width="190">创建时间</td><td>${userHngs.createTime?string("yyyy-MM-dd HH:mm:ss")}</td><td></td></tr>
		<tr><td width="190">修改时间</td><td>${userHngs.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td><td></td></tr>
		</#if>
		<#if userHnds??>
        <tr><td width="190">登记序号</td><td>${(userHnds.djxh)!}</td><td></td></tr>
        <tr><td width="190">纳税人识别号</td><td>${(userHnds.nsrsbh)!}</td><td></td></tr>
		<tr><td width="190">纳税人名称</td><td>${(userHnds.nsrmc)!}</td><td></td></tr>
		<tr><td width="190">社会信用代码</td><td>${(userHnds.shxydm)!}</td><td></td></tr>
		<tr><td width="190">税务机关名称</td><td>${(userHnds.swjgMc)!}</td><td></td></tr>
        <tr><td width="190">税务机关代码</td><td>${(userHnds.swjgDm)!}</td><td></td></tr>
		<tr><td width="190">主用户</td><td>${(userHnds.username)!}</td><td></td></tr>
		<tr><td width="190">子用户</td><td>${(userHnds.subuser)!}</td><td></td></tr>
		<tr><td width="190">绑定状态</td><td>
		  <#if userHnds.status?? && userHnds.status >
            <div class="btn btn-success btn-xs ">有效</div>
            <#else>
            <div class="btn btn-danger btn-xs ">失效</div>
          </#if>
		</td><td></td></tr>
		<tr><td width="190">创建时间</td><td>${userHnds.createTime?string("yyyy-MM-dd HH:mm:ss")}</td><td></td></tr>
		<tr><td width="190">修改时间</td><td>${userHnds.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td><td></td></tr>
		</#if>
		<tr>
          <td colspan="3" style='text-align:center;'>
			<a href="${referer}" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a>
          </td>
        </tr>
      </table>
</div>
</body>
</html>