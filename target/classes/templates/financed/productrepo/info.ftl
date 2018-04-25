<#assign ctx=request.getContextPath()>
<html>
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
	<#--
	<table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/financed/productrepoList.php">
            <u>商品库存管理</u>
          </a> &gt;&gt; 库存详细记录查看</td>
      </tr>
    </table>
	-->
    <form action="${ctx}/financed/productrepoInfo.php" method="post" id="consumerListForm" class="layui-form">
        <table class="ny_tab_t">
        <tr>
          <td style="text-align:left;padding-left:30px;font-size:18px;">
			 <label>商品名：</label>${BaseRq.goodsName!} &nbsp;&nbsp;<label>规格：</label>(&nbsp;${BaseRq.guige!}&nbsp;)
			 <input type="hidden" id="goodsName" name="goodsName" value="${BaseRq.goodsName!}">
			 <input type="hidden" id="guige" name="guige" value="${BaseRq.guige!}">
			 <input type="hidden" id="goodsId" name="goodsId" value="${BaseRq.goodsId!}">
			 <input type="hidden" id="productId" name="productId" value="${BaseRq.productId!}">
              <input type="hidden" id="page" name="page" value="${BaseRq.page!}">
          </td>
		  <td>
			
            <div class="nycon_l_t_btn text-right">
			  <a class="btn btn-md btn-info" href="javascript:void(0);" id="consumer_query">刷新</a>
			  <a href="javascript:void(0);" class="btn btn-md btn-info" id="showmyModal">商品出入库</a>
			  <a href="${ctx}/financed/productrepoList.php?goodsName=${(ProductrepoRq.goodsName)!}&&startRepo=${(ProductrepoRq.startRepo)!}&&endRepo=${(ProductrepoRq.endRepo)!}&&page=${(ProductrepoRq.page)!}"
                 class="btn btn-md btn-default" style="text-decoration:none;color:black">返回</a>
              
            </div>
          </td>
        </tr>

      </table>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>时间</th>
                    <th>入库</th>
					<th>出库</th>
					<th>库存</th>
					<th>备注</th>

                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if productRs?? && ( productRs?size gt 0 )> 
					  <#list productRs as product>
					  <tr>
					     <td class="td_i">${BaseRq.offset + product_index + 1}</td>
                          <td>${product.lastUpdate?string("yyyy-MM-dd HH:mm:ss")}</td>
						 <td>${product.income!}</td>
						 <td>${product.outcome!}</td>
						 <td>${product.stock!}</td>
						 <td>${product.remark!}</td>

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


<!--会员价格弹出层-->
<div class="main_modal container-fluid" id="myModal" tabindex="-1"  hidden>
<div class="modal-dialog" role="document" style="top:10%;">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">商品出入库</h4>
    </div>
    <div class="modal-body">
	  <form data-type="A">
          <table class="layui-table" lay-size="sm">
        <tr>
          <td>类型</td>
          <td>
            <select id="type" style="width:100px;height:30px;">
                <option value="income">入库</option>
				<option value="outcome">出库</option>
			</select>
          </td>
        </tr>
		<tr>
          <td>数量</td>
          <td>
			 <input type="text" data-rule="required;integer(+)" id="goodsNum" style="width:100px;height:30px;"><label style='color:red;'>*</label>
		  </td>
        </tr>
		<tr>
          <td>备注</td>
          <td>
			 <textarea id="remark" rows="3" cols="60"></textarea>
          </td>
        </tr>
      </table>
      </form>
    </div>
    <div class="modal-footer">
      <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
      <button type="button" class="layui-btn layui-btn-normal" data-save="modal">保存</button>
    </div>
  </div>
</div>
</div>
<!--会员价格弹出层 end-->
<script data-main="${ctx}/js/abc/financed/productrepo" src="${ctx}/js/require.js"></script>
</body>
</html>