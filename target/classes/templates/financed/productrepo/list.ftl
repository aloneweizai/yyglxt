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
    <form action="${ctx}/financed/productrepoList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.goodsName)!}" name="goodsName" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">库存范围</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${BaseRq.startRepo!}" name="startRepo" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" value="${BaseRq.endRepo!}" name="endRepo" class="layui-input">
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
                    <th>商品名称</th>
					<th>规格</th>
					<th>库存</th>
					<th>上次出入库时间</th>
					<th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if productRs?? && ( productRs?size gt 0 )> 
					  <#list productRs as product>
					  <tr>
					     <td width="30"  class="td_i">${BaseRq.offset + product_index + 1}</td>
						 <td>${product.goodsName!}</td>
						 <td>
							<#assign guige="">
							<#if product.dictList?? && ( product.dictList?size gt 0 )> 
					          <#list product.dictList as dict>
							      ${dict.dictName}:[${dict.fieldKey}]&nbsp;
								  <#assign guige =guige+ "${dict.dictName}:[${dict.fieldKey}]"/> 
							  </#list>
			                </#if>
						 </td>
						 <td>${product.stock!}</td>
						 <td>${product.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td><a href="${ctx}/financed/productrepoInfo.php?productId=${product.id!}&goodsId=${product.goodsId!}&goodsName=${product.goodsName!}&guige=${guige!}">出入库管理</a></td>
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
				<input type="hidden" value="${ctx}/financed/productrepoList.ph" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>
<script data-main="${ctx}/js/abc/financed/productrepo" src="${ctx}/js/require.js"></script>
</body>
</html>