<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn" class="ua-windows_nt ua-windows_nt-10 ua-windows_nt-10-0 ua-chrome ua-chrome-49 ua-chrome-49-0 ua-chrome-49-0-2623 ua-chrome-49-0-2623-221 ua-se ua-se-2 ua-se-2-x ua-metasr ua-metasr-1 ua-metasr-1-0 ua-desktop ua-desktop-windows ua-webkit ua-webkit-537 ua-webkit-537-36 js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- 指定多核浏览器用webkit模式 -->
		<meta name="renderer" content="webkit">
		<title></title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/page_edit.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/css/select2.min.css">
		<script type="text/javascript">
            var ctx = "${ctx}";
        </script>
		<style>
             .nobord{ margin: 0 auto;font-size:medium;height:auto;positon:relative;}
			 .bt-label{margin:10px 10px;height:30px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 4px 10px;}
			 .nobord>tbody>tr>td{border-top:none;}
		     .nobord>tbody>tr>td:nth-child(odd){font-weight:bold;}
			 .bta{float: right;padding: 0 5px;height:25px;font-size: inherit;}
			 a{color:blue;}

             .checkbox {
                 position: relative;
                 height: 30px;
				 display: inline-block;
				 margin-left: -20px;
				 width: 25%;
             }
             .checkbox input[type='checkbox'] {
                 position: absolute;
                 left: 0;
                 top: 0;
                 width: 20px;
                 height: 20px;
                 opacity: 0;
             }
             .checkbox label {
                 position: absolute;
                 left: 30px;
                 top: 0;
                 height: 20px;
                 line-height: 20px;
				 padding-left: 0px;
             }
             .checkbox label:before {
                 content: '';
                 position: absolute;
                 left: -30px;
                 top: 0;
                 width: 20px;
                 height: 20px;
                 border: 1px solid #ddd;
                 border-radius: 50%;
                 transition: all 0.3s ease;
                 -webkit-transition: all 0.3s ease;
                 -moz-transition: all 0.3s ease;
             }

             .checkbox label:after {
                 content: '';
                 position: absolute;
                 left: -22px;
                 top: 3px;
                 width: 6px;
                 height: 12px;
                 border: 0;
                 border-right: 1px solid #fff;
                 border-bottom: 1px solid #fff;
                 background: #fff;
                 transform: rotate(45deg);
                 -webkit-transform: rotate(45deg);
                 -moz-transform: rotate(45deg);
                 -ms-transform: rotate(45deg);
                 transition: all 0.3s ease;
                 -webkit-transition: all 0.3s ease;
                 -moz-transition: all 0.3s ease;
             }

             .checkbox input[type='checkbox']:checked + label:before {
                 background: #4cd764;
                 border-color: #4cd764;
             }
             .checkbox input[type='checkbox']:checked + label:after {
                 background: #4cd764;
             }
        </style>
	</head>
	<body class="g_wrapper g_wrapper_full page_edit g_survey">

		<div id="container" class="g_container" >
			<div class="editor_container" style="display: block;">
				<!-- 主体 -->
				<div class="editor_main" style=" left: 0; top: 60px;">
					<div class="survey_wrap">
						<div class="survey_main" style="padding-top:0;">
							<div class="survey_container">
								<div class="page" data-pid="1" style="display: block;">
                                    <div><h1 style="text-align: center;font-size: large;font-weight: bold;">个人产品代客下单</h1></div>
                                    <div class="layui-form-top" style="margin-top: 20px">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label">用户名</label>
                                                <div class="layui-input-inline" style="width:170px;">
                                                    <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" style="width:170px;" id="username">
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label">手机号</label>
                                                <div class="layui-input-inline" style="width:170px;">
                                                    <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" style="width:170px;" id="phone">
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <div class="layui-input-inline" style="width:180px;">
                                                    <div id="query" class="layui-btn">查询</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
									<div class="toupiao-title"><h3 style="font-weight: bold;">客户信息</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">
										<table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
                						  <tr>
											<td style="width: 20%">用户名：<input id="userId" type="hidden"/></td><td style="width: 30%" id="cusername"></td>
                							<td style="width: 20%">用户昵称:<input  id="vipLevel" type="hidden"/></td><td style="width: 30%" id="nickname"></td>
                						  </tr>
                						  <tr>
											<td>手机号:</td>
											  <td id="cphone"></td>
                                              <td>用户姓名:</td>
                                              <td id="realName"></td>
                						  </tr>
                                       </table>
									</div>
									<div class="toupiao-title"><h3 style="font-weight: bold;">选购产品</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">
										 <table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%" class="layui-table" >
                                              <tr>
                    							<td style="width: 20%">产品类型:</td>
												  <td style="width: 30%">
                                                      <select id="prodcutslect" lay-filter="tradingChannels" style="width: 100%;height: 35px">
													  <#if channels?? && ( channels?size gt 0 )>
														  <#list channels as channel>
															  <#if channel.fieldValue != 'ALL'>
                                                                  <option value="${channel.fieldValue}">${channel.fieldKey}</option>
															  </#if>
														  </#list>
													  </#if>
                                                      </select>
												  </td>
                                                <td style="width: 20%">产品规格:</td>
                    							<td style="width: 30%">
                                                    <select id="smallGoodrule" style="width: 100%;height: 35px">
														<option value="">选择产品规格</option>
													<#if levels?? && ( levels?size gt 0 )>
														<#list levels as level>
                                                            <option
																<#if "VIP0" != level.levelCode>
                                                                value="${level.levelCode}">${level.level}</option>
																</#if>
														</#list>
													</#if>
													</select>
                    							</td>
											 </tr>
                    						 <tr>
												<td>推荐人工号:<input id="goodsId"  type="hidden"><input id="goodname" type="hidden"><input id="specInfo" type="hidden"></td><td id="recommendUser">${username!}</td>
                    							<td>产品价格:<input id="totalPrice" type="hidden"><input id="marketPrice" type="hidden"></td><td id="price"></td>
                    						  </tr>
                    						  <tr>
												<td>购买优惠赠送:<input id="browseUrl"  type="hidden"><input id="imageUrl"  type="hidden"><input id="giftPoints"  type="hidden"></td>
                    							<td colspan="3">
													<div class="ddxx-fpxx-content">
														<div id="gift">
													    </div>
													</div>
												</td>
											  </tr>
                                             <tr>
                                                 <td>订单备注:</td>
                                                 <td colspan="3"><textarea style="width: 90%;height: 80px" id="remark"></textarea></td>
                                             </tr>
                                           </table>
									</div>
									</br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

        <script data-main="${ctx}/js/abc/financed/selling.js" src="${ctx}/js/require.js"></script>
	</body>
</html>