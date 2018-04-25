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
    <form action="${ctx}/financed/goodList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name"  class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">上下架</label>
                    <div class="layui-input-inline">
                        <select name="status"  class="cxinput">
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status >selected</#if> value="true">上架</option>
                            <option <#if BaseRq.status?? && !BaseRq.status >selected</#if> value="false">下架</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">推荐类型</label>
                    <div class="layui-input-inline">
                        <select name="recommendType"  id="recommendType">
                            <option value=""></option>
                        <#if recommendType?? && ( recommendType?size gt 0 )>
                            <#list recommendType as type>
                                <option
                                    <#if BaseRq.recommendType ?? && (BaseRq.recommendType == type.fieldValue)>selected</#if>
                                    value="${type.fieldValue}">${type.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/financed/goodAdd.php"  class="layui-btn layui-btn-normal fr">添加商品</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th></th>
                    <th style="text-align:left;">商品名称</th>
                    <th>分类</th>
                    <th>销售价</th>
                    <th>赠送积分</th>
                    <th>状态</th>
                    <th>是否需要寄送</th>
					<th>是否免运费</th>
					<th>排序</th>
					<th>修改时间</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if productRs?? && ( productRs?size gt 0 )> 
					  <#list productRs as product>
					    <tr>
					     <td class="td_i">${BaseRq.offset + product_index + 1}</td>
						 <td style="text-align:left;" title='${(product.name)!}'><img height="32" width="32" src="${imgPth}${(product.imageUrl)!}">&nbsp;${(product.name)!}</td>
						 <td>${(product.categoryName)!}</td>
						 <td>
							${(product.sellingPrice)!}
                            <#if product.tradeMethod?? && product.tradeMethod =="RMB" >元
                            <#else>分
                            </#if>
						 </td>
						 <td>${product.giftPoints?c}</td>
						 <td>
							<#if product.status?? && product.status >
                                <div class="btn btn-success btn-xs ">上架</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">下架</div>
                            </#if>
                         </td>
						 <td>
							<#if product.isShipping?? && product.isShipping==1 >
								<div class="btn btn-success btn-xs ">是</div>
                            <#else>
								<div class="btn btn-danger btn-xs ">否</div>
                            </#if>
						 </td>
						 <td>
							<#if product.isFreeShipping?? && product.isFreeShipping == 1 >
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
						 </td>
						 <td>${product.sort!}</td>
						 <td>${product.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td>
							<#if product.status?? && product.status >
                               <a data-type="delSig" data-confirm="确认下架该商品?" data-okMsg="商品下架成功!" data-failMsg="商品下架失败" href="javascript:void(0);" data-href="${ctx}/financed/goodCheck.php?goodsIds=${product.id}&status=false" class="pn-opt">下架</a>
                            <#else>
							   <a data-type="delSig" data-confirm="确认上架该商品?" data-okMsg="商品上架成功!" data-failMsg="商品上架失败" href="javascript:void(0);" data-href="${ctx}/financed/goodCheck.php?goodsIds=${product.id}&status=true" class="pn-opt">上架</a>&nbsp;|
                               <a href="${ctx}/financed/goodEdit.php?id=${product.id}&look=no" class="pn-opt">编辑</a>&nbsp;|
							   <a data-type="delSig" data-confirm="删除商品，库存会一并删除，确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败" href="javascript:void(0);" data-href="${ctx}/financed/delgood.php?goodId=${product.id}" class="pn-opt">删除</a>
							   
                            </#if>
							   | <a href="${ctx}/financed/goodEdit.php?id=${product.id}&look=yes" class="pn-opt">查看</a>&nbsp;
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
				<input type="hidden" value="${ctx}/financed/goodList.php" id="currLink">
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