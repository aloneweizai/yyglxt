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
		<link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/page_edit.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
		<style>
             .nobord{ margin: 0 auto;font-size:medium;height:auto;positon:relative;}
			 .bt-label{margin:10px 10px;height:30px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 4px 10px;}
			 .nobord>tbody>tr>td{border-top:none;}
		     .nobord>tbody>tr>td:nth-child(odd){font-weight:bold;}
			 .bta{float: right;padding: 0 5px;height:25px;font-size: inherit;}
			 a{color:blue;}
			 .showbig{cursor:pointer;}
        </style>
		<script type="text/javascript">
            var ctx = "${ctx}";
        </script>
	</head>
	<body class="g_wrapper g_wrapper_full page_edit g_survey">
	<table class="table  table-hover">
    <tr style="background:#f5f5f5">
      <td  height="30"><!-- &nbsp;当前位置：&nbsp;<a href="${ctx}/consumerManager/trueName_list.php"><u>实名认证管理</u></a> &gt;&gt; 实名认证审核 --></td>
    </tr>
    </table>
		<div id="container" class="g_container">
			<div class="editor_container" style="display: block;">	 
				<!-- 主体 -->
				<div class="editor_main" style=" left: 0; top: 60px;">
					<div class="survey_wrap">
						<div class="survey_main" style="padding-top:0;"> 
							<div class="survey_container">
								<div class="page" data-pid="1" style="display: block;">
									<div class="toupiao-title"><h3>基本信息(<span style="color:green">${(consumerInfoRs.user.username)!}</span>)</h3><i class="glyphicon glyphicon-menu-down"></i></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">	
										<table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
										  <tr>
											<td rowspan="4" style="text-align:center;">
											 <#if consumerInfoRs.user.userPicturePath?? && consumerInfoRs.user.userPicturePath !="">
											    <img height="120" width="100" src="${imgPth}/${(consumerInfoRs.user.userPicturePath)!}">
											 <#else>
												<img height="120" width="100" src="${ctx}/images/default.jpg">
											 </#if>
											  <input type="hidden" id="userId" value="${(consumerInfoRs.user_extend.userId)!}">
											</td>
										  </tr>
                                          <tr>
                                            <td>昵称：</td><td>${(consumerInfoRs.user.nickname)!}</td>
                							<td>手机号码:</td><td>${(consumerInfoRs.user.phone)!}</td>
                							<td>用户状态：</td>
                							<td><#if (consumerInfoRs.user.status)?? && consumerInfoRs.user.status >
												<input type="hidden" id="zhengchangid">
                                                <div class="btn btn-success btn-xs " style='cursor:default'>正常</div>
                                            <#else>
                                                <div class="btn btn-danger btn-xs " style='cursor:default'>停用</div>
                                            </#if></td>
                						  </tr>
										  <tr>
                    							<td>真实姓名:</td><td>${(consumerInfoRs.user_extend.realName)!}</td>
                                                <td>性别:</td>
                    							<td>
                    							  <#if (consumerInfoRs.user_extend.sex)?? && consumerInfoRs.user_extend.sex=='0' >
                                                                                   女
                                                  <#elseif (consumerInfoRs.user_extend.sex)?? && consumerInfoRs.user_extend.sex=='1' >
                                                                                   男
                                                  </#if>
                    							</td>
                    							<td>生日:</td><td>${(consumerInfoRs.user_extend.birthday?string("yyyy-MM-dd"))!}</td>
                    						</tr> 
											<tr>
                    							<td>身份证号:</td><td>${(consumerInfoRs.user_extend.idcard)!}</td>
                    						</tr>
                                       </table>
									</div>								
									<div class="toupiao-title"><h3>实名认证情况</h3><i class="glyphicon glyphicon-menu-down"></i></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%;">
										 <#if consumerInfoRs.user_extend??>
										     <table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">	    
                            				  <tr>
                                                <td>实名认证状态:</td>
												<td>
												 <#if (consumerInfoRs.user_extend.validStatus)??> 
												    <#if consumerInfoRs.user_extend.validStatus == "0" >
                                                           <div class="btn btn-danger btn-xs " style='cursor:default'>未认证</div>
                                                    <#elseif consumerInfoRs.user_extend.validStatus == "1">
                        								   <div class="btn btn-warning btn-xs " style='cursor:default'>待认证</div>
                        							<#elseif consumerInfoRs.user_extend.validStatus == "2">
                        								   <div class="btn btn-success btn-xs " style='cursor:default'>已认证</div>
                        							<#else>
                                                        <div class="btn btn-danger btn-xs " style='cursor:default'>认证失败</div>
                                                    </#if>
												  </#if>
												</td>
                            					<td>实名认证时间:</td>
												  <td>
												 
													<#if (consumerInfoRs.user_extend.validTime)??>
													${consumerInfoRs.user_extend.validTime?string("yyyy-MM-dd HH:mm:ss")}
													</#if>
												</td>
                            					<td>创建时间:</td><td>${(consumerInfoRs.user_extend.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
											   </tr>
												 <tr>
                            					<td>上次修改时间:</td><td>${(consumerInfoRs.user_extend.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
												<td>实名认证有效期起:</td><td>
												   <#if (consumerInfoRs.user_extend.validStatus)?? && consumerInfoRs.user_extend.validStatus == "2">
													<#if (consumerInfoRs.user_extend.startTime)??>
													${consumerInfoRs.user_extend.startTime?string("yyyy-MM-dd HH:mm:ss")}
													</#if>
												   </#if>
												</td>
                    							<td>实名认证有效期止:</td><td>
												    <#if (consumerInfoRs.user_extend.validStatus)?? && consumerInfoRs.user_extend.validStatus == "2">
													<#if (consumerInfoRs.user_extend.endTime)??>
													${consumerInfoRs.user_extend.endTime?string("yyyy-MM-dd HH:mm:ss")}
													</#if>
													</#if>
												</td>
                            				  </tr>
												 <tr>
													 <td>认证渠道:</td>
													 <td>
														 <#if (consumerInfoRs.user_extend.validType)??>
															 <#if consumerInfoRs.user_extend.validType == "0" >
                                                                 自动认证
															 <#elseif consumerInfoRs.user_extend.validType == "1">
                                                                 手动认证
															 </#if>
														 </#if>
													 </td>
                                                     <td></td>
                                                     <td></td>
                                                     <td></td>
                                                     <td></td>
												 </tr>
                                           </table>
										 </#if>
									</div> 
									
									<div class="toupiao-title"><h3>身份证照片</h3><i class="glyphicon glyphicon-menu-down"></i></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%;">
										<table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
                                            <tr>
											    <td style="text-align:center;">
													<#if consumerInfoRs.user_extend.frontImage?? && consumerInfoRs.user_extend.frontImage !="">
											           <img title="点击放大图片" class="showbig" height="300" width="500" src="${imgPth}/${consumerInfoRs.user_extend.frontImage!}">
											        <#else>
												        <img height="300" width="500" src="${ctx}/images/default.jpg">
											        </#if>
													
                                                </td>
												<td style="text-align:center;">
													<#if consumerInfoRs.user_extend.backImage?? && consumerInfoRs.user_extend.backImage !="">
											            <img title="点击放大图片" class="showbig" height="300" width="500" src="${imgPth}/${consumerInfoRs.user_extend.backImage!}">
											        <#else>
												        <img height="300" width="500" src="${ctx}/images/default.jpg">
											        </#if>
													
                                                </td>
											</tr>
											<tr>
											    <td colspan="2" style="text-align:center;border-top: 1px solid #ddd;">
												  <#if consumerInfoRs.user_extend.validStatus??> 
												    <#if consumerInfoRs.user_extend.validStatus != "0" >
													 <input type="button" name="button" id="consumer_ok" value="审核通过" class="layui-btn">
													 <input type="button" name="button" style="margin-left:50px;" id="consumer_no" value="审核拒绝" class="layui-btn layui-btn-danger">
												    </#if>
												  </#if>
													<a href="${referer}" style="text-decoration: none;color:black;margin-left:50px;" class="layui-btn layui-btn-primary">返回</a>
												</td>
											</tr>
										</table>
									</div> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		

<div id="shoimg" style="position:absolute;left:50%;top:100px;width:800px;height:600px;z-index:1000;background:white;display:none;cursor:pointer;" title="点击关闭">
<img src='' style='width:100%;height:100%' />
</div>


<div class="main_modal container-fluid" id="myModal" tabindex="-1"  hidden>
<div class="modal-dialog" role="document" style="top:-400px;">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">审核拒绝原因</h4>
    </div>
    <div class="modal-body">
        <table class="layui-table" lay-size="sm">
		<tbody id="jiageTB">
           <tr>
			<td>
			  <select style="height:30px;width:300px;" data-type="ajax" data-url="${ctx}/util/jsonDictBOs.php?dictId=realVlidaRson" data-rules="fieldValue:fieldKey" id="reasonlib">
    			<option value="">快捷选择常见问题</option>
    		  </select>
			</td>
		   </tr>
		   <tr>
			<td><textarea id="refuseRson" rows="4" cols="60"></textarea><label style='color:red;'>*</label></td>
		   </tr>
		</tbody>
      </table>
    </div>
    <div class="modal-footer">
      <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
      <button type="button" class="layui-btn layui-btn-normal" data-save="modal">确定</button>
    </div>
  </div>
</div>
</div>	
		
	<script data-main="${ctx}/js/abc/consumer/validat.js" src="${ctx}/js/require.js"></script>
	</body>
</html>