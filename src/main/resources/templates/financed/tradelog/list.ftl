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
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/tradelog/list.php" method="get" id="consumerListForm" class="layui-form" anme="consumerListForm">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">交易流水号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.orderNo)!}" name="orderNo" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <div id="tradeLog_import" class="layui-btn layui-btn-normal fr">批量导入对账</div>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>交易流水号</th>
                    <th>订单号</th>
					<th>交易类型</th>
                    <th>交易金额</th>
                    <th>交易状态</th>
                    <th>交易时间</th>
                    <th>交易方式</th>
                    <th>对账状态</th>
                    <th>对账时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if tradeLogRes?? && ( tradeLogRes?size gt 0 )>
                    <#list tradeLogRes as tradeLog>
                    <tr>
					   <td class="td_i">${BaseRq.offset + tradeLog_index + 1}</td>
                       <td>${tradeLog.tradeNo!}</td>
                        <td>${tradeLog.orderNo!}</td>
					   <td>
						<#if tradeLog.tradeType??&&tradeLog.tradeType=="1">付款<#else>退款</#if>
					   </td>
					   <td>
                           <#if tradeLog.payMethod=="POINTS">
                               ${(tradeLog.amount)!}积分
                           <#else>
                               ￥${(tradeLog.amount?string("0.00"))!}
                           </#if>
                           </td>
					   <td>
						<#if tradeLog.tradeStatus=="1"><div class="btn btn-success btn-xs ">交易成功</div>
						<#elseif tradeLog.tradeStatus=="2"><div class="btn btn-success btn-xs ">交易成功</div>
                        <#elseif tradeLog.tradeStatus=="3"><div class="btn btn-danger btn-xs ">交易失败</div>
                        <#elseif tradeLog.tradeStatus=="4"><div class="btn btn-warning btn-xs ">取消交易</div>
                        <#else><div class="btn btn-danger btn-xs ">交易关闭</div></#if>
					   </td>
					   <td>${(tradeLog.tradeTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
					   <td>
						<#if tradeLog.payMethod=="WEIXIN">微信
                        <#elseif tradeLog.payMethod=="ALIPAY">支付宝
                        <#elseif tradeLog.payMethod=="POINTS">积分
                        </#if>
					   </td>
					   <td>
						<#if tradeLog.compareStatus?? && tradeLog.compareStatus=="0"><div class="btn btn-danger btn-xs ">对账失败</div>
						<#elseif tradeLog.compareStatus?? && tradeLog.compareStatus=="1"><div class="btn btn-success btn-xs ">已对账</div>
						<#elseif tradeLog.compareStatus?? && tradeLog.compareStatus=="2"><div class="btn btn-danger btn-xs ">手工作废</div>
                        <#elseif tradeLog.compareStatus?? && tradeLog.compareStatus=="3"><div class="btn btn-success btn-xs ">手工完成</div>
                        <#else><div class="btn btn-warning btn-xs ">未对账</div></#if>
					   </td>
					   <td>${(tradeLog.compareTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                       <td>
						<#if !tradeLog.compareStatus?? || tradeLog.compareStatus=="0">
						    <a  href="javascript:void(0);" id='sgdz' data-id='${tradeLog.tradeNo!}' <#if tradeLog.payMethod=="POINTS">data-amount='${(tradeLog.amount)!}积分'<#else>data-amount='￥${(tradeLog.amount?string("0.00"))!}'</#if>
                                data-payMethod='${tradeLog.payMethod!}' data-tradeType='${tradeLog.tradeType!}' data-orderNo='${tradeLog.tradeNo!}' data-aliTrandeNo='${tradeLog.aliTrandeNo!}' data- class="pn-opt">手工对账</a>
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
				<input type="hidden" value="${ctx}/tradelog/list.php" id="currLink">
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
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>
				<div>
                                对账单CSV：<input type="file" name="uploadFile" id="uploadFile" style="width:200px;display: inline-block;">
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" value="导入" id="importbtn" name="importbtn" class="layui-btn layui-btn-normal">
                <input type="button" value="关闭" id="back" class="layui-btn layui-btn-primary">
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
			<div class="modal-header">
              <button type="button" class="close" data-dismiss="modal1" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">手工对账</h4>
            </div>
            <div class="modal-body">
                <div></div>
                <label><h2></h2></label>
				<div>
                    <table class="layui-table" lay-size="sm">
					   <tr style="font-weight:bold;"><td>交易流水号</td><td>交易类型</td><td>交易金额</td></tr>
					   <tr><td id='orderno'></td><td id='ordertype'></td><td id='orderamount'></td></tr>
					</table>
                    <table class="layui-table" lay-size="sm">
                      <caption id="title"></caption>
                      <tr style="font-weight:bold;"><td>交易流水号</td><td>交易金额</td><td>交易结果</td></tr>
					  <tr id='qresult'><td id='oorderno'></td><td id='oamount'></td><td id='oresult'></td></tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <a data-type="delSig" id='qrdz' data-confirm="确认对账?" data-okMsg="对账成功!" data-failMsg="对账失败" href="javascript:void(0);"  class="layui-btn">对账</a>&nbsp;&nbsp;
                <a data-type="delSig" id='qrzf' data-confirm="确认作废?" data-okMsg="作废成功!" data-failMsg="作废失败" href="javascript:void(0);"  	class="layui-btn layui-btn-danger">作废</a>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/financed/tradelog.js" src="${ctx}/js/require.js"></script>
</body>
</html>