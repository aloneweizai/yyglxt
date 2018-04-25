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
    <form action="${ctx}/vipgift/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">礼物名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">会员等级</label>
                    <div class="layui-input-inline">
                       <select name="category" style="width:200px;height:30px;">
                            <option value=""></option>
	                        <option <#if BaseRq.category?? && BaseRq.category=='VIP1'>selected</#if> value="VIP1">VIP1</option>
	                        <option <#if BaseRq.category?? && BaseRq.category=='VIP2'>selected</#if> value="VIP2">VIP2</option>
	                        <option <#if BaseRq.category?? && BaseRq.category=='VIP3'>selected</#if> value="VIP3">VIP3</option>
	                        <option <#if BaseRq.category?? && BaseRq.category=='VIP4'>selected</#if> value="VIP4">VIP4</option>
                       </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">礼物状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="cxinput"  id="validStatus">
                            <option value=""></option>
                            <#-- <option <#if BaseRq.status?? && BaseRq.status=="0">selected</#if> value="0">删除</option> -->
                            <option <#if BaseRq.status?? && BaseRq.status=="2">selected</#if> value="2">上架</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="1">selected</#if> value="1">下架</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/vipgift/giftedit.php"  class="layui-btn layui-btn-normal fr">添加礼物</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>礼物名称</th>
                    <th>礼物简介</th>               
					<th>会员等级</th>
					<th>排序</th>
					<th>库存</th>
                    <th>销售价格</th>
					<th>成本价格</th>
					<th>礼物状态</th>
					<th>最新修改时间</th>
					<th>造作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if gifts?? && ( gifts?size gt 0 )> 
					  <#list gifts as gift>
					   <tr>
					     <td class="td_i">${BaseRq.offset + gift_index + 1}</td>
						 <td style="text-align:left;" title='${(gift.name)!}'><a class="ljc_00bcd4" <#if gift.detailUrl??>href="${(gift.detailUrl)!}"</#if> target="_blank"><img height="40" width="40" src="${imgPth!}${(gift.imageUrl)!}">&nbsp;${(gift.name)!}</a></td>
						 <td title='${(gift.introduction)!}'>
						   <#if gift.introduction?? && (gift.introduction?length gt 30)>
							${gift.introduction?substring(0,30)}... 
							<#else>
							${gift.introduction!}
							</#if>
						 </td>
						 <td>
                            <#if gift?? && gift.category?? && gift.category=='VIP1'>银卡会员</#if>
                             <#if gift?? && gift.category?? && gift.category=='VIP2'>金卡会员</#if>
                             <#if gift?? && gift.category?? && gift.category=='VIP3'>钻石会员</#if>
                             <#if gift?? && gift.category?? && gift.category=='VIP4'>超级会员</#if>
                         </td>
						 <td>${(gift.sort?c)!}</td>
						 <td>${(gift.stock?c)!}</td>
						 <td>￥${(gift.sellingPrice?c)!}</td>
						 <td>￥${(gift.costPrice?c)!}</td>
						 <td>
						   <#if gift.status?? && gift.status == '0'> 
						       删除
						   <#elseif gift.status?? && gift.status == '2'>
                               <div class="btn btn-success btn-xs ">上架</div>
						   <#elseif gift.status?? && gift.status == '1'>
                               <div class="btn btn-danger btn-xs ">下架</div>
						   </#if>
						 </td>
						 <td>${(gift.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						 <td>
                             <#if gift.status?? && gift.status == '2'>
                                 <a data-type="delSig" data-confirm="确认下架礼物?" data-okMsg="礼物下架成功!" data-failMsg="礼物下架失败" href="javascript:void(0);" data-href="${ctx}/vipgift/giftsxj.php?giftId=${gift.id}&&status=1" class="pn-opt">下架</a>
                                 | <a data-type="opendialog" data-val="5" href="javascript:;" data-url="${ctx}/vipgift/giftlook.php?giftId=${gift.id}" class="pn-opt">查看</a>
                             <#elseif gift.status?? && gift.status == '1'>
                                 <a href="${ctx}/vipgift/giftedit.php?giftId=${gift.id}" class="pn-opt">编辑</a>&nbsp;|
                                 <a data-type="delSig" data-confirm="确认上架礼物?" data-okMsg="礼物上架成功!" data-failMsg="礼物上架失败" href="javascript:void(0);" data-href="${ctx}/vipgift/giftsxj.php?giftId=${gift.id}&&status=2" class="pn-opt">上架</a>&nbsp;|
                                 <a data-type="delSig" data-confirm="确认删除礼物?" data-okMsg="礼物删除成功!" data-failMsg="礼物删除失败" href="javascript:void(0);" data-href="${ctx}/vipgift/giftdel.php?giftId=${gift.id}" class="pn-opt">删除</a>
                                 | <a data-type="opendialog" data-val="5" href="javascript:;" data-url="${ctx}/vipgift/giftlook.php?giftId=${gift.id}" class="pn-opt">查看</a>

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
                             共&nbsp;${BaseRq.totalItems!}&nbsp;条&nbsp;&nbsp;
				每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp; 
				 当前&nbsp;${BaseRq.page!}/${BaseRq.totalPage!}&nbsp;页&nbsp;&nbsp;转到第
                <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
				<input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
			    <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
				<input type="hidden" value="${ctx}/vipgift/list.php" id="currLink">
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

<script data-main="${ctx}/js/abc/consumer/userpage" src="${ctx}/js/require.js"></script>
</body>
</html>