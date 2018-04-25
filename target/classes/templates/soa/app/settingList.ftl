<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">

    <form action="${ctx}/app/settinglist.php" method="get" id="consumerListForm" class="layui-form">
    <#--
    <table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/app/list.php">
            <u>接入应用设置</u>
          </a> &gt;&gt; 接入应用接口授权信息</td>
      </tr>
    </table>
    -->
        <table class="ny_tab_t">
            <tr>
                <td style="text-align:left;padding-left:30px;font-size:18px;">
                    <label>接入应用名称：</label>${(app.name)!} &nbsp;&nbsp;
                    <span>接口名称<input type="text" value="${(BaseRq.name)!}" name="name" placeholder="接口名称"
                                     style="width:200px;height:30px;"></span>
                    <span>接口地址<input type="text" value="${(BaseRq.uri)!}" name="uri" placeholder="接口地址"
                                     style="width:200px;height:30px;"></span>
                </td>
                <td>
                    <div class="nycon_l_t_btn text-right">
                        <span><input type="hidden" value="${(BaseRq.appId)!}" name="appId"></span>
                        <input type="button" value="查询" id="consumer_query" class="layui-btn">
                        <input type="button" value="单个接口授权" id="consumer_show" class="layui-btn layui-btn-normal">
                        <a href="${ctx}/app/batchSetting.php?appId=${app.id}"
                           class="layui-btn layui-btn-normal">批量接口授权</a>
                    <#if AppRq.status??&&AppRq.status>
                        <a href="${ctx}/app/list.php?page=${(AppRq.page)!}&&name=${(AppRq.name)!}&&status=true&&startTime=${(AppRq.startTime)!}&&endTime=${(AppRq.endTime)!}&&size=${(AppRq.size)!}"
                           class="layui-btn layui-btn-primary" style="text-decoration:none;color:black">返回</a>
                    <#elseif AppRq.status??&&!AppRq.status>
                        <a href="${ctx}/app/list.php?page=${(AppRq.page)!}&&name=${(AppRq.name)!}&&status=false&&startTime=${(AppRq.startTime)!}&&endTime=${(AppRq.endTime)!}&&size=${(AppRq.size)!}"
                           class="layui-btn layui-btn-primary" style="text-decoration:none;color:black">返回</a>
                    <#else>
                        <a href="${ctx}/app/list.php?page=${(AppRq.page)!}&&name=${(AppRq.name)!}&&startTime=${(AppRq.startTime)!}&&endTime=${(AppRq.endTime)!}&&size=${(AppRq.size)!}"
                           class="layui-btn layui-btn-primary" style="text-decoration:none;color:black">返回</a>
                    </#if>

                    </div>
                </td>
            </tr>
        </table>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>接口名称</th>
                    <th>接口所属系统</th>
                    <th>接口地址</th>
                    <th>调用方式</th>
                    <th>每分钟允许访问数</th>
                    <th>每小时允许访问数</th>
                    <th>每天允许访问数</th>
                <#--<th>是否验证身份</th>-->
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if appsets?? && ( appsets?size gt 0 )>
                    <#list appsets as appset>
                    <tr>
                        <td class="td_i">${BaseRq.offset + appset_index + 1}</td>
                        <td>${appset.name!}</td>
                        <td>${appset.appName!}</td>
                        <td>${appset.uri!}</td>
                        <td>${appset.method}</td>
                        <td>${appset.timesPerMinute}</td>
                        <td>${appset.timesPerHour}</td>
                        <td>${appset.timesPerDay}</td>
                    <#--<td>
                        <#if appset.isValidate?? && appset.isValidate >
                            <div class="btn btn-success btn-xs ">是</div>
                        <#else>
                            <div class="btn btn-danger btn-xs ">否</div>
                        </#if>
                    </td>-->
                        <td style='display:none;'>
                            <#if appset.status?? && appset.status >
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                        </td>
                        <td>
                            <a href="${ctx}/app/settingInfo.php?apiId=${appset.apiId}&appId=${appset.appId}&id=${appset.id}">编辑</a>
                            |
                            <a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                               href="javascript:void(0);"
                               data-href="${ctx}/app/settingDel.php?apiId=${appset.apiId}&appId=${appset.appId}&id=${appset.id}"
                               class="pn-opt">删除</a></td>
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
                        每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size"
                                 type="text">条&nbsp;&nbsp;
                        <input class="btn btn-default btn-xs" value="首 页" id="consumer_first" type="button">
                        <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up" type="button">
                        <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                        <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                        当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                        <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}"
                               type="text"> 页
                        <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                        <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                        <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                        <input type="hidden" value="${ctx}/app/settinglist.php?appId=${BaseRq.appId}" id="currLink">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加接口授权</h4>
            </div>
            <div class="modal-body">
                <form id="appapi" data-setting='true' action="${ctx}/app/setting.php"
                      next-url="${ctx}/app/settinglist.php?appId=${BaseRq.appId}">
                    <table class="layui-table" lay-size="sm">
                        <tbody id="jiageTB">
                        <tr>
                            <td width="160" style="font-weight:bold">所属系统</td>
                            <td>
                                <input type="hidden" name="appId" value="${BaseRq.appId}" id="appId"/>
                                <select style="height:30px;width:300px;" id="dictId">
                                    <option value="">可选择所属系统进行接口快捷筛选</option>
                                <#if apiSystem?? && ( apiSystem?size gt 0 )>
                                    <#list apiSystem as app>
                                        <option value="${app.id}">${app.fieldKey}</option>
                                    </#list>
                                </#if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="160" style="font-weight:bold">接口名称</td>
                            <td>
                                <select style="height:30px;width:380px;" name="apiId" id="apiName" data-rule="required;"
                                        data-target="#apiIdMsg">
                                </select><span style="color:red" id="apiIdMsg">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td width="160" style="font-weight:bold">接口地址</td>
                            <td id="appuri"></td>
                        </tr>
                        <tr>
                            <td width="160" style="font-weight:bold">调用方式</td>
                            <td id="appmethod"></td>
                        </tr>
                        <tr>
                            <td width="160" style="font-weight:bold">每分允许访问次数</td>
                            <td>
                                <input type="text" name="timesPerMinute" data-rule="required;integer;" value='0'
                                       style=" width:120px;"><label style='color:red;'>*</label>
                            </td>
                        </tr>
                        <tr>
                            <td width="160" style="font-weight:bold">每小时允许访问次数</td>
                            <td>
                                <input type="text" name="timesPerHour" data-rule="required;integer;" value='0'
                                       style=" width:120px;"><label style='color:red;'>*</label>
                            </td>
                        </tr>
                        <tr>
                            <td width="160" style="font-weight:bold">每日允许访问次数</td>
                            <td>
                                <input type="text" name="timesPerDay" data-rule="required;integer;" value='0'
                                       style=" width:120px;"><label style='color:red;'>*</label>
                            </td>
                        </tr>
                        <#--<tr>
                         <td width="160" style="font-weight:bold">是否验证身份</td>
                         <td>
                             <input name="isValidate" type="radio" value="true" checked>是
                             <input name="isValidate" style='margin-left:50px'  type="radio" value="false" >否
                         </td>
                        </tr>-->
                        <tr style='display:none;'>
                            <td width="160" style="font-weight:bold">状态</td>
                            <td>
                                <input name="status" type="radio" value="true" checked> 启用
                                <input name="status" style='margin-left:50px' type="radio" value="false"> 停用
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="layui-btn layui-btn-normal" data-save="modal">确定</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/soa/shouquan" src="${ctx}/js/require.js"></script>
</body>
</html>