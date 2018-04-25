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
        </style>
	</head>
	<body class="g_wrapper g_wrapper_full page_edit g_survey">

	<#--<table class="table  table-hover">
    <tr style="background:#f5f5f5">
      <td  height="30"><!-- &nbsp;当前位置：&nbsp;<a href="${ctx}/consumerManager/consumer/list.php"><u>用户管理</u></a> &gt;&gt; 用户标签管理 &ndash;&gt;</td>
    </tr>
    </table>-->

		<div id="container" class="g_container" >
			<div class="editor_container" style="display: block;">	 
				<!-- 主体 -->
				<div class="editor_main" style=" left: 0; top: 60px;">
					<div class="survey_wrap">
						<div class="survey_main" style="padding-top:0;"> 
							<div class="survey_container">
								<div class="page" data-pid="1" style="display: block;">
									<div class="toupiao-title"><h3>基本信息(<span style="color:green">${(consumerInfoRs.user.username)!}</span>)</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">	
										<table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
										  <tr>
											<td rowspan="9" style="text-align:center;">
											 <#if (consumerInfoRs.user.userPicturePath)?? && consumerInfoRs.user.userPicturePath !="">
											    <img height="120" width="100" src="${imgPth!}/${(consumerInfoRs.user.userPicturePath)!}">
											 <#else>
												<img height="120" width="100" src="${ctx}/images/default.jpg">
											 </#if>
											</td>
										  </tr>
                						  <tr>
											<td style="width: 15%">昵称：</td><td style="width: 25%">${(consumerInfoRs.user.nickname)!}</td>
                							<td style="width: 15%">可用积分:</td><td style="width: 25%"><a class="ljc_00bcd4" title="查看积分记录" href="${ctx}/consumerManager/consumer/pointsLog.php?userId=${(consumerInfoRs.user.id)!}">${(consumerInfoRs.user.points)!}</a></td>
                						  </tr>
                						  <tr>
											<td>可用经验值:</td><td><a class="ljc_00bcd4" title="查看经验值记录" href="${ctx}/consumerManager/consumer/expLog.php?userId=${(consumerInfoRs.user.id)!}">${(consumerInfoRs.user.exp)!}</a></td>
                                              <td>用户状态:</td>
                                              <td><#if (consumerInfoRs.user.status)?? && consumerInfoRs.user.status >
                                                  <div class="btn btn-success btn-xs " style='cursor:default'>启用</div>
											  <#else>
                                                  <div class="btn btn-danger btn-xs " style='cursor:default'>停用</div>
											  </#if></td>
                						  </tr>
                                          <tr>
                                            
                							<td>手机号码:</td><td>${(consumerInfoRs.user.phone)!}</td>
											<td>礼包金额:</td><td><a class="ljc_00bcd4" title="查看礼包金额" href="${ctx}/consumerManager/consumer/amountLog.php?userId=${(consumerInfoRs.user.id)!}"><#if consumerInfoRs.user.amount??>${(consumerInfoRs.user.amount)!}元</#if></a></td>
										  </tr>
                						  <tr>
                							<td>邮箱:</td><td>${(consumerInfoRs.user.regMail)!}</td>
											<td>QQ:</td><td>${(consumerInfoRs.user_extend.qq)!}</td>
                						  </tr>
                                            <tr>
                                                <td>微信openid:</td><td style="word-break: break-all">${(consumerInfoRs.user.wxopenid)!}</td>
                                                <td>微信昵称:</td><td>${(consumerInfoRs.user.wxnickname)!}</td>
                                            </tr>
                						  <tr>
                                              <td>会员等级:</td><td><a class="ljc_00bcd4" title="查看会员等级记录" href="${ctx}/consumerManager/consumer/vipLog.php?userId=${(consumerInfoRs.user.id)!}">
										  <#if levels?? && ( levels?size gt 0 )>
                    					              <#list levels as level>
											  <#if (consumerInfoRs.user.vipLevel)?? && level.levelCode == consumerInfoRs.user.vipLevel>${level.level!}</#if>
										  </#list>
                    			                    </#if>
                                          </a></td>
											<td>会员到期日期:</td>
											  <td>
										  ${(consumerInfoRs.user.vipExpireDate?string("yyyy-MM-dd HH:mm:ss"))!}
											</td>
										  </tr>
                						  <tr>
                                              <td>所属行业:</td><td>
										  <#if (consumerInfoRs.user_extend.occupation)??>
												   <#if shhy?? && ( shhy?size gt 0 )>
											  <#list shhy as shhys>
												  <#if shhys.fieldValue == consumerInfoRs.user_extend.occupation>${shhys.fieldKey!}</#if>
											  </#list>
										  </#if>
												</#if>
                                          </td>
                                              <td>从业时间:</td><td>${(consumerInfoRs.user_extend.careerDuration)!}</td>
                						  </tr>
                                            <tr>
                                                <td>注册时间:</td><td>${(consumerInfoRs.user.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                                <td>最近修改时间:</td><td>${(consumerInfoRs.user.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                            </tr>
                                       </table>
									</div>
									<div class="toupiao-title"><h3>详细信息</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">	
										<#if consumerInfoRs.user_extend??>
										 <table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">	    
                                              <tr>
                    							<td>真实姓名:</td><td>${(consumerInfoRs.user_extend.realName)!}</td>
                                                <td>性别:</td>
                    							<td>
                    							  <#if (consumerInfoRs.user_extend.sex)?? && consumerInfoRs.user_extend.sex=='0' >女
                                                  <#elseif (consumerInfoRs.user_extend.sex)?? && consumerInfoRs.user_extend.sex=='1'>男</#if>
                    							</td>
											 </tr>
                    						 <tr>
												<td>身份证号:</td><td>${(consumerInfoRs.user_extend.idcard)!}</td>
                    							<td>生日:</td><td>${(consumerInfoRs.user_extend.birthday?string("yyyy-MM-dd"))!}</td>
                    							<#--<td>婚姻状况:</td><td>${(consumerInfoRs.user_extend.marital)!}</td>-->
                    						  </tr>
                    						  <tr>
												<td>微信:</td><td>${(consumerInfoRs.user.wxnickname)!}</td>
                    							<td>教育程度:</td><td>${(consumerInfoRs.user_extend.education)!}</td>
											  </tr>
                    						  <tr style='display:none;'>
                    							<td>毕业院校:</td><td>${(consumerInfoRs.user_extend.graduate)!}</td>
											    
                    						  </tr>
                    						  <tr style='display:none;'>
                    							<td>职业:</td><td>${(consumerInfoRs.user_extend.occupation)!}</td>
                    							<td>收入水平:</td><td>${(consumerInfoRs.user_extend.income)!}</td>
												<td>身高:</td><td>${(consumerInfoRs.user_extend.height)!}</td>
											  </tr>
											  <#--
                    						  <tr>
                    							<td>体重:</td><td>${(consumerInfoRs.user_extend.weight)!}</td>
                    							<td>QQ:</td><td>${(consumerInfoRs.user_extend.qq)!}</td>
                    						  </tr>
											  -->
                    						  <tr style='display:none;'>
                                                <td>血型:</td><td>${(consumerInfoRs.user_extend.bloodType)!}</td>
                    							
                    						  </tr>
                    						  <tr>
                    							<td>省:</td><td>${(consumerInfoRs.user_extend.province)!}</td>
                    							<td>市:</td><td>${(consumerInfoRs.user_extend.city)!}</td>
                    							<td>区县:</td><td colspan="3">${(consumerInfoRs.user_extend.area)!}</td>
                    						  </tr>
                                              <tr><td >通讯地址:</td><td colspan="4">${(consumerInfoRs.user_extend.postAddress)!}</td></tr>
                    						  <tr style='display:none;'><td >签名:</td><td colspan="7">${(consumerInfoRs.user_extend.signature)!}</td></tr>
                    						  <tr><td >擅长领域:</td><td colspan="4">
											        <#if (consumerInfoRs.user_extend.tags)??>
													   <#if scly?? && ( scly?size gt 0 )> 
                        					              <#list scly as sclys>
                        								    <#if sclys.fieldValue == consumerInfoRs.user_extend.tags>${sclys.fieldKey!}</#if>
                        								  </#list>
                        			                    </#if>
													</#if>
											  </td></tr>
                                           </table>
										</#if>
									</div>									
									<div class="toupiao-title"><h3>实名认证情况</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%;">
										 <#if consumerInfoRs.user_extend??>
										     <table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">	    
                            				  <tr>
                                                <td>实名认证状态:</td>
												<td>
												  <#if (consumerInfoRs.user_extend.validStatus)??>
												   <#if consumerInfoRs.user_extend.validStatus == "0">
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
													${(consumerInfoRs.user_extend.validTime?string("yyyy-MM-dd HH:mm:ss"))!}
													</#if>
												</td>
												</tr><tr>
                            					<td>注册时间:</td>
												<td>
													<#if (consumerInfoRs.user_extend.createTime)??>
													${(consumerInfoRs.user_extend.createTime?string("yyyy-MM-dd HH:mm:ss"))!}
													</#if>
												</td>
											   
                            					<td>最近修改时间:</td>
												<td>
													<#if (consumerInfoRs.user_extend.lastUpdate)??>
													${(consumerInfoRs.user_extend.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}
													</#if>
												</td>
												</tr><tr>
												<td>实名认证有效期起:</td>
												<td>
													<#if (consumerInfoRs.user_extend.startTime)??>
													${(consumerInfoRs.user_extend.startTime?string("yyyy-MM-dd HH:mm:ss"))!}
													</#if>
												</td>
                    							<td>实名认证效期止:</td>
												<td>
													<#if (consumerInfoRs.user_extend.endTime)??>
													${(consumerInfoRs.user_extend.endTime?string("yyyy-MM-dd HH:mm:ss"))!}
													</#if>
												</td>
                            				  </tr>
                                           </table>
										 </#if>
									</div> 
									<div class="toupiao-title"><h3>已打标签</h3></div>
									<#-- <a href="${ctx}/consumerManager/consumer/userTags.php?id=${consumerInfoRs.user.id}" id="taglib" class="btn btn-warning btn-xs bta" >用户标签管理 ></a> -->
									<div class="question question_radio required" style="width:94%;margin-left:3%;">
										<#if usertags?? && ( usertags?size gt 0 )> 
                            			  <#list usertags as usertag>
                                              <div id="taglib" class="btn btn-success btn-xs bt-label" data-type="selected" data-id="${usertag.id!}">${usertag.tagName!}</div>
                            			  </#list>
                            	        </#if>
									</div>
									</br>
									<#--<center><a href="${ctx}/consumerManager/consumer/list.php" style="text-decoration: none;color:black;" class="layui-btn layui-btn-primary">返回</a></center>-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>