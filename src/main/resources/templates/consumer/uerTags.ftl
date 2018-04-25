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
			 input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
             .nobord{ margin: 0 auto;font-size:medium;height:auto;positon:relative;}
			 .bt-label{margin:10px 10px;height:30px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 4px 10px;}
			 .nobord>tbody>tr>td{border-top:none;}
		     .nobord>tbody>tr>td:nth-child(odd){font-weight:bold;}
			 a{color:blue;}
			 ul, ol {margin-top: 0;margin-bottom: 10px;}
			 .tag-list__itemheader{ margin: 0; line-height: 32px; font-weight: bold; font-size: 14px; color: #333;  border-bottom: 1px solid #EEE;}
            .tag-list__itembody {padding: 20px 0;margin-bottom: 0;}
            .taglist--inline, .taglist--block {list-style: none;padding: 0;font-size: 0;}
			 .tag {display: inline-block;padding: 0 8px;color: #017E66;background-color: rgba(1,126,102,0.08);height: 24px;line-height: 24px;font-weight: normal;font-size: 16px;text-align: center;}
             a, a:hover, a:active, a:focus {outline: 0;}
			 .tag-list__itemWraper {margin-bottom: 5px;width:30%;float:left;margin-right:2%;height:auto;}
			 .tag:hover, .tag:focus {background-color: #017E66;color: #fff;text-decoration: none;}
			 .taglist--inline.multi>li { margin-bottom: 5px;}.taglist--inline>li {display: inline-block;margin-right: 5px;}
             .taglist--inline li, .taglist--block li {padding: 0;font-size: 13px;}
        </style>
	</head>
	<body class="g_wrapper g_wrapper_full page_edit g_survey">
	<#--<table class="table  table-hover">
    <tr style="background:#f5f5f5">
      <td  height="30"><!-- &nbsp;当前位置：&nbsp;<a href="${ctx}/consumerManager/consumer/list.php"><u>用户管理</u></a> &gt;&gt; 用户标签管理 &ndash;&gt;</td>
    </tr>
    </table>-->
		<div id="container" class="g_container" style="margin-top:20px;">
			<input type="hidden" id="userId" value="${(consumerInfoRs.user.id)!}"/>
			<div class="editor_container" style="display: block;">	 
				<!-- 主体 -->
				<div class="editor_main" style=" left: 0; top: 60px;">
					<div class="survey_wrap">
						<div class="survey_main" style="padding-top:0;"> 
							<div class="survey_container">
								<div class="page" data-pid="1" style="display: block;">
                                    <div class="toupiao-title"><h3>基本信息</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">	
										<table class="table nobord" cellspacing="0" cellpadding="0" border="0" width="100%">
										  <tr>
                                            <td colspan="4">${(consumerInfoRs.user.username)!}</td>
                						  </tr>
                						  <tr>
                							<td>可用积分:</td><td><a class="ljc_00bcd4" title="查看积分记录" href="${ctx}/consumerManager/consumer/pointsLog.php?userId=${(consumerInfoRs.user.id)!}">${(consumerInfoRs.user.points)!}</a></td>
                							<td>可用经验值:</td><td><a class="ljc_00bcd4" title="查看经验值记录" href="${ctx}/consumerManager/consumer/expLog.php?userId=${(consumerInfoRs.user.id)!}">${(consumerInfoRs.user.exp)!}</a></td>
                							<td>会员等级:</td><td><a class="ljc_00bcd4" title="查看会员等级记录" href="${ctx}/consumerManager/consumer/vipLog.php?userId=${(consumerInfoRs.user.id)!}">
													<#if levels?? && ( levels?size gt 0 )> 
                    					              <#list levels as level>
                    								    <#if (consumerInfoRs.user.vipLevel)?? && level.levelCode == consumerInfoRs.user.vipLevel>${level.level!}</#if>
                    								  </#list>
                    			                    </#if>
											</a></td>
										  </tr>
                						  <tr>
                							<td>用户状态：</td>
                							<td><#if (consumerInfoRs.user.status)?? && consumerInfoRs.user.status >
                                                <div class="btn btn-success btn-xs " style='cursor:default'>启用</div>
                                            <#else>
                                                <div class="btn btn-danger btn-xs " style='cursor:default'>停用</div>
                                            </#if></td>
                						  
                                            <td>昵称：</td><td>${(consumerInfoRs.user.nickname)!}</td>
                							<td>手机号码:</td><td>${(consumerInfoRs.user.phone)!}</td>
										   </tr>
                                           <tr>
                							<td>注册邮箱:</td><td>${(consumerInfoRs.user.regMail)!}</td>
                							<td>上次修改时间:</td><td>${(consumerInfoRs.user.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                						  </tr>
                                       </table>
									</div>
									<div class="toupiao-title"><h3>已打标签</h3></div>
									<div class="question question_radio required" style="width:94%;margin-left:3%">	
										<#if usertags?? && ( usertags?size gt 0 )> 
                    					  <#list usertags as usertag>
                                              <a   ${ (usertag.status) ?string('','style="background:#CCCCCC"')} id="taglib" data-confirm="确认移除该标签?" data-okMsg="移除标签成功!" data-failMsg="移除标签失败" class="btn btn-success btn-xs bt-label" data-type="selected" data-id="${usertag.id!}">


											  ${usertag.tagName!}
											  </a>
                    					  </#list>
                    			        </#if>
									</div>									
									<div class="toupiao-title">
										<label><h3>未打标签</h3></label>
										<select style="height:30px;margin-left:30px;" id="seletablib">
											<option value="">所有标签类型</option>
											<#if taglib?? && ( taglib?size gt 0 )> 
                        					  <#list taglib as lib>
                                                  <option value="${lib.fieldValue!}">${lib.fieldKey!}</option>
                        					  </#list>
                        			        </#if>
										</select>
									</div>
									<div class="question question_radio required" style="width:94%;margin-left:3%;">
										<#if taglib?? && ( taglib?size gt 0 )> 
                        					<#list taglib as lib>
                                               <div class="tag-list__itemWraper" id="tag_${lib.fieldValue!}">
                                                 <h3 class="h5 tag-list__itemheader">${lib.fieldKey!}</h3>
                                                 <ul class="tag-list__itembody taglist--inline multi" style="padding: 10px 0;">
                                                    <#if tags?? && ( tags?size gt 0 )> 
                                					  <#list tags as tag>
													    <#if lib.fieldValue == tag.category && tag.status>
                                                          <li><a id="taglib" data-confirm="确认标记该标签?" data-okMsg="标签标记成功!" data-failMsg="标签标记失败" class="btn tag" data-type="unselect" data-id="${tag.id!}">${tag.tagName!}</a></li>
                                					    </#if> 
                                                      </#list>
                                			        </#if> 
                                                 </ul>
                                               </div> 
											   <#if (lib_index+1)%3==0>
											   <div style="clear: both;height:0px;"></div>
											   </#if>
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
	<script data-main="${ctx}/js/abc/consumer/taglib" src="${ctx}/js/require.js"></script>
	</body>
</html>