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
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<form name="order_detail_form" action="" enctype="multipart/form-data" method="post"
      next-url="">
    <div class="container-fluid m_top_30 nycol_list  sys_mod">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <table class="layui-table" lay-size="sm">
                    <caption>商品信息</caption>
                    <tr style="font-weight:bold;">
                        <td>商品名称</td>
                        <td>规格</td>
                        <td>会员等级</td>
                        <td>销售价格</td>
                        <td>折扣</td>
                        <td>交易价格</td>
                        <td>商品数量</td>
                        <td>小计</td>
                        <td>赠送积分</td>
                    </tr>
                </table>
                <table class="layui-table" lay-size="sm">
                    <caption>买家信息</caption>
                    <tr style="font-weight:bold;">
                        <td width="20%">用户名</td>
                        <td width="20%">昵称</td>
                        <td width="20%">手机号</td>
                        <td>当前会员等级</td>
                    </tr>
                </table>
                <table class="layui-table" lay-size="sm">
                    <caption>订单信息</caption>
                    <tr style="font-weight:bold;">
                        <td width="20%">订单号</td>
                        <td width="20%">订单状态</td>
                        <td width="20%">支付方式</td>
                        <td width="20%">是否需要寄送</td>
                        <td>下单时间</td>
                    </tr>
                </table>
                <table class="layui-table" lay-size="sm">
                    <caption>发票信息</caption>
                    <tr style="font-weight:bold;">
                        <td>发票抬头</td>
                        <td>公司名称</td>
                        <td>发票号码</td>
                        <td>发票代码</td>
                        <td>发票名称</td>
                    </tr>
                </table>
            </div>
            <div class="tab-pane fade" id="jmeter">
                <table class="layui-table" lay-size="sm">
                    <caption>订单日志</caption>
                    <tr style="font-weight:bold;">
                        <td>订单号</td>
                        <td>动作</td>
                        <td>操作人</td>
                        <td>完成时间</td>
                    </tr>
               
                </table>
            </div>
        </div>
        <table>
            <tr>
			  <td colspan="2" style="text-align:right;padding-right:150px;">
                 <input type="button" name="button" id="application_ok" value="审核" class="layui-btn">
				 <input type="button" name="button" id="application_next" value="退款" class="layui-btn">
				 <a href="${ctx}/orderback/applications.php" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a>
			  </td>
			</tr>
        </table>
    </div>
</form>
<div class="main_modal container-fluid" id="myModal" tabindex="-1"  hidden>
<div class="modal-dialog" role="document" style="top:-400px;">
<form id="orderbackCharge">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">退单审核</h4>
    </div>
    <div class="modal-body">
        <table class="layui-table" lay-size="sm">
		<tbody id="jiageTB">
		   <tr>
            <td>审核:</td>
			<td>
			   <input type="radio" name="status" value="1" id="isOk" checked>审核通过
			   <input type="radio" name="status" value="2" id="isNO" style="margin-left:60px;">审核拒绝
            </td>
		   </tr>
		   <tr>
            <td>备注:</td>
			<td>
				<textarea id="refuseRson" rows="6" cols="60" data-rule="required;"></textarea><label style='color:red;'>*</label>
			    </br><span style="font-size:12px; color: #999;">温馨提示：审核通过时填写发货地址等信息，审核拒绝时填写拒绝原因</span>
			</td>
		   </tr>
		</tbody>
      </table>
    </div>
    <div class="modal-footer">
      <button tyclass="layui-btn layui-btn-primary"  data-dismiss="modal">关闭</button>
      <button type="button" class="layui-btn layui-btn-normal" data-save="modal">确定</button>
    </div>
  </div>
</form>
</div>
</div>	
<script data-main="${ctx}/js/abc/financed/orderback.js" src="${ctx}/js/require.js"></script>
</body>
</html>