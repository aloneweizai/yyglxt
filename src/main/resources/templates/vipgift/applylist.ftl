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
    <form action="${ctx}/vipgift/applylist.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">申请人</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">申请单号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.applyId)!}" name="applyId" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">申请状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="cxinput" >
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status=="0">selected</#if> value="0">已拒绝</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="1">selected</#if> value="1">待处理</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="2">selected</#if> value="2">已审批</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="3">selected</#if> value="3">已发货</option>
                            <option <#if BaseRq.status?? && BaseRq.status=="4">selected</#if> value="4">已完成</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
					<th>申请单号</th>
                    <th>申请人</th>
                    <th>用户名</th>
					<th>手机号码</th>
					<th>申请时间</th>
					<th>兑换礼物</th>
					<th>申请状态</th>
					<th>运单号</th>
					<th>备注</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if applylists?? && ( applylists?size gt 0 )> 
					  <#list applylists as apply>
					   <tr>
					     <td class="td_i">${BaseRq.offset + apply_index + 1}</td>
					     <td>${(apply.applyId)!}</td>
						 <td>${(apply.name)!}</td>
                           <td>
                          <#if canQuery??>
                               <a class="ljc_00bcd4" data-type="opendialog" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${apply.userId}">${(apply.username)!}</a>
                           <#else>
                              ${(apply.username)!}
                          </#if>
                          </td>
						 <td>${(apply.phone)!}</td>
						 <td>${(apply.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
						 <td style="text-align:left;padding-left:20px;">
						    <#if apply.giftApplyBOList?? && (apply.giftApplyBOList?size gt 0)> 
						       <#list apply.giftApplyBOList as gift>
						           ${(gift.giftName)!}&nbsp;&nbsp;&nbsp;￥${(gift.giftAmount)!}&nbsp;&nbsp;&nbsp;数量：${(gift.giftNum)!}</br>
						       </#list>
						    </#if>
						 </td>
						 <td>
						    <#if apply.status?? && apply.status=="0">已拒绝
						    <#elseif apply.status?? && apply.status=="1">待处理
						    <#elseif apply.status?? && apply.status=="2">已审批
						    <#elseif apply.status?? && apply.status=="3">已发货
						    <#elseif apply.status?? && apply.status=="4">已完成
                            </#if>
						 </td>
						 <td>${(apply.expressNo)!}</td>
						 <td>${(apply.remark)!}</td>
						 <td>
						   <#if apply.status?? && apply.status=="1">
						     <a data-do="applay_detail" data-info="${(apply.applyId)!}" data-val="5" href="javascript:;" class="pn-opt">审核</a>
						   <#elseif apply.status?? && apply.status=="2">
						     <a data-do="applay_detail" data-info="${(apply.applyId)!}" data-val="5" href="javascript:;" class="pn-opt">发货</a>
						   <#else>
						     <a data-do="applay_detail" data-info="${(apply.applyId)!}" data-val="5" href="javascript:;" class="pn-opt">查看</a>
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
				<input type="hidden" value="${ctx}/vipgift/applylist.php" id="currLink">
              </td>
            </tr>
          </tbody>
        </table>
        </div>		
    </form>
</div>

<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">会员礼包申请信息</h4>
            </div>
            <div class="modal-body">
                <form id="appapi" action="${ctx}/app/setting.php" class="layui-form">
                    <table class="layui-table" lay-size="sm">
                       <tr><td width="200">申请单号：</td><td colspan="3"><span id='ap_sqid'></span></td></tr>
                       <tr><td width="200">申请人：</td><td colspan="3"><span id='ap_sqr'></span></td></tr>
                       <tr><td width="200">电话号码：</td><td colspan="3"><span id='ap_dhhm'></span></td></tr>
                       <tr><td width="200">收件地址：</td><td colspan="3"><span id='ap_sjdz'></span></td></tr>
                       <tr><td width="200">申请时间：</td><td colspan="3"><span id='ap_sqsj'></span></td></tr>
                       <tr><td width="200">礼物名称：</td>
                          <td colspan="3"><span id='ap_lwmc'></span></td>
                       </tr>
                       <tr><td width="200">状态：</td><td colspan="3"><span id='ap_zt'></span></td></tr>
                       <tr data-hide id='kuaiditr' style='display:none;'><td width="200">快递公司：</td><td colspan="3"><span id='ap_kdgs'></span></td></tr>
                       <tr data-hide id='kuaidinotr'  style='display:none;'><td width="200">快递号码：</td><td colspan="3"><span id='ap_kdhm'></span></td></tr>
                       <tr data-hide id='kuaiditr1' style='display:none;'>
                         <td width="200">快递公司：</td>
                         <td colspan="3"><input type="text" name="expressComp" style=" width:200px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;"><label style='color:red;'>*</label></td>
                       </tr>
                       <tr data-hide id='kuaidinotr1'  style='display:none;'>
                          <td width="200">快递号码：</td>
                          <td colspan="3"><input type="text" name="expressNo" style=" width:200px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;"><label style='color:red;'>*</label></td>
                       </tr>
                       <tr data-hide id='shenhetr' style='display:none;'>
                           <td width="200">审核结果：</td>
                           <td colspan="3">
                             <input type="radio" name="status" value="1" title="通过" checked>
                             <input type="radio" name="status" value="0" title="拒绝">
                           </td>
                       </tr>
                       <tr data-hide id='remarktr2' style='display:none;'>
                           <td width="200">备注：</td>
                           <td colspan="3"><span id='ap_remark'></span></td>
                       </tr>
                       <tr data-hide id='remarktr' style='display:none;'>
                           <td width="200">备注：</td>
                           <td colspan="3">
                              <textarea id="remark" rows="3" class="layui-textarea" style="width: 95%"></textarea>
                           </td>
                       </tr>
                    </table>
                    <fieldset>
                       <legend style='font-size:100%'>会员礼包申请操作日志</legend>
                       <table class="layui-table" lay-size="sm">
                          <thead><tr><td>操作时间</td><td>操作内容</td><td>操作人</td><td>备注</td></tr></thead>
                          <tbody id='sqrzcx'></tbody>
                       </table>
                    </fieldset>
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="layui-btn layui-btn-normal" data-save="modal">确定</button>
            </div>
        </div>
    </div>
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
<script data-main="${ctx}/js/abc/gift/applylist" src="${ctx}/js/require.js"></script>
</body>
</html>