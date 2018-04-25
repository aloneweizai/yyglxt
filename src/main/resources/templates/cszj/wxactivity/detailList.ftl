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
<!--导入红包口令-->
<@shiro.hasPermission name="cszj_wxactivity:drSecret">
    <#assign canDrSecret=true>
</@shiro.hasPermission>
<!--同步-->
<@shiro.hasPermission name="cszj_wxactivity:queryinfo">
    <#assign canQueryinfo=true>
</@shiro.hasPermission>
<!--重新发生-->
<@shiro.hasPermission name="cszj_wxactivity:resend">
    <#assign canSesend=true>
</@shiro.hasPermission>
<!--删除红包口令-->
<@shiro.hasPermission name="cszj_wxactivity:delHbkl">
    <#assign canDelHbkl=true>
</@shiro.hasPermission>
<#--<!--修改红包口令&ndash;&gt;
<@shiro.hasPermission name="cszj_wxactivity:updHbkl">
    <#assign canUpdHbkl=true>
</@shiro.hasPermission>-->
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
<#--
<table class="table  table-hover">
  <tr style="background:#f5f5f5">
    <td height="30">&nbsp;当前位置：&nbsp;
      <a href="${ctx}/financed/invoiceRepoList.php">
        <u>发票仓库管理</u>
      </a> &gt;&gt; 发票仓库明细</td>
  </tr>
</table>
-->
    <form action="${ctx}/cszjs/wxactivity/detailList.php?isFirst=no" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">红包口令</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.activityId)!}" name="activityId" id="activityId" hidden>
                        <input type="text" value="${(BaseRq.secret)!}" name="secret" class="layui-input">
                        <input type="hidden" value="${backUrl!}" name="backUrl" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发送状态</label>
                    <div class="layui-input-inline">
                        <select name="sendStatus" class="cxinput" id="sendStatus">
                            <option value=""></option>
                        <#if sendStatus?? && ( sendStatus?size gt 0 )>
                            <#list sendStatus as sendSta>
                                <option
                                    <#if BaseRq.sendStatus ?? && (BaseRq.sendStatus == sendSta.fieldValue)>selected</#if>
                                    value="${sendSta.fieldValue}">${sendSta.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">领取状态</label>
                    <div class="layui-input-inline">
                        <select name="receiveStatus" class="cxinput" id="receiveStatus">
                            <option value=""></option>
                        <#if receiveStatus?? && ( receiveStatus?size gt 0 )>
                            <#list receiveStatus as receiveSt>
                                <option
                                    <#if BaseRq.receiveStatus ?? && (BaseRq.receiveStatus == receiveSt.fieldValue)>selected</#if>
                                    value="${receiveSt.fieldValue}">${receiveSt.fieldKey}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>
                <a href="${ctx}/cszjs/wxactivity/list.php?name=${(wxActivityRq.name)!}" class="layui-btn  layui-btn-primary fr" style="text-decoration:none;">返回</a>
            <#if canDrSecret??>
                <a data-url='/cszjs/wxactivity/import.php' id="consumer_import"   class="layui-btn layui-btn-normal fr">导入红包口令</a>
            </#if>
                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">OpenId</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.openId)!}" name="openId" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">业务ID</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.businessId)!}" name="businessId" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>
                        <div>全选</div>
                    </th>
                    <th>活动名称</th>
                    <th>金额(元)</th>
                    <th>中奖概率</th>
                    <th>红包口令</th>
                    <th>口令产生时间</th>
                    <th>红包金额(元)</th>
                    <th>发送状态</th>
                    <th>发送时间</th>
                    <th>领取状态</th>
                    <th>用户OPENID</th>
                    <th>昵称</th>
                    <th>手机号码</th>
                    <th>业务ID</th>
                    <th>备注</th>
                <#if canQueryinfo?? || canSesend?? || canDelHbkl??>
                    <th nowrap="nowrap">操作选项</th>
                </#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if repackRs?? && ( repackRs?size gt 0 )>
                    <#list repackRs as repack>
                    <tr>
                        <td width="30" class="td_i">${BaseRq.offset + repack_index + 1}</td>
                        <td>
                            <div class="layui-input-inline">
                                <input class="js_checkbox" type="checkbox" name="ids" lay-skin="primary" value="${repack.id}"
                                       status="<#if repack.sendStatus??||repack.receiveStatus??>1<#else>0</#if>">
                            </div>
                        </td>

                        <td>${(repack.name)!}</td>
                    <#-- <td>
                         <#if repack.amountType?? && repack.amountType=="1">固定金额
                         <#elseif repack.amountType?? && repack.amountType=="2">随机金额
                         </#if>
                     </td>-->
                        <td>
                            <#if repack.amountType?? && repack.amountType=="2"&&repack.minAmount??>
                            ${(repack.minAmount)!} -
                            </#if>
                        ${(repack.amount)!}</td>
                        <td>${(repack.probability)!}</td>
                        <td>${(repack.secret)!}</td>
                        <td>${(repack.createTime)!}</td>
                        <td>${(repack.sendAmount)!}</td>
                        <#if repack.sendStatus??>
                            <td>
                                <#if sendStatus?? && ( sendStatus?size gt 0 )>
            				   <#list sendStatus as sendSta>
                                    <#if repack.sendStatus == sendSta.fieldValue>${sendSta.fieldKey}</#if>
                                </#list>
            			    </#if>
                            </td>
                        <#else>
                            <td>
                            </td>
                        </#if>
                        <td><#if repack.sendTime??>${repack.sendTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                        <#if repack.receiveStatus??>
                            <td>
                                <#if receiveStatus?? && ( receiveStatus?size gt 0 )>
            				   <#list receiveStatus as receiveSta>
                                    <#if repack.receiveStatus == receiveSta.fieldValue>${receiveSta.fieldKey}</#if>
                                </#list>
            			    </#if>
                            </td>
                        <#else>
                            <td>
                            </td>
                        </#if>
                        <td>${(repack.openId)!}</td>
                        <td>${(repack.wxnickname)!}</td>
                        <td>${(repack.phone)!}</td>
                        <td>${(repack.businessId)!}</td>
                        <td>${(repack.remark)!}</td>
                        <#if canQueryinfo?? || canSesend?? || canDelHbkl??>
                            <td nowrap="nowrap">
                                <#--<#if (repack.sendStatus??||repack.receiveStatus??)>
                                    <#if canUpdHbkl??>
                                        <a data-type="editdialog" href="javascript:;"
                                           data-url="${ctx}/cszjs/wxactivity/edithbkl.php?id=${repack.id}">修改</a>&nbsp;
                                        |
                                    </#if>
                                </#if>-->
                                <!--发送状态:已发送-->
                                <#if repack.sendStatus??>
                                <#--  <#if !(repack.receiveStatus??&&(repack.receiveStatus=="RECEIVED"||repack.receiveStatus=="NOT_WINNING"))>
                                      <#if canQueryinfo??>
                                           | <a data-type="delSig" data-confirm='确认同步?' data-okMsg='同步成功!'
                                             data-failMsg='同步失败' href="javascript:void(0);"
                                             data-href="${ctx}/cszjs/wxactivity/queryinfo.php?id=${repack.id}"
                                             class="pn-opt">同步</a>
                                      </#if>
                                  </#if>
                                  <!--发送状态:已中奖未发送or发送失败&ndash;&gt;
                              <#elseif repack.sendStatus??&&(repack.sendStatus == '0' || repack.sendStatus == '2')>-->
                                    <#if canSesend??>
                                        <a data-type="delSig" data-confirm='确认发送?' data-okMsg='发送成功!'
                                           data-failMsg='发送失败' href="javascript:void(0);"
                                           data-href="${ctx}/cszjs/wxactivity/resend.php?id=${repack.id}"
                                           class="pn-opt">重新发送</a> |
                                    </#if>
                                    <#if canQueryinfo??>
                                        <a data-type="delSig" data-confirm='确认同步?' data-okMsg='同步成功!'
                                           data-failMsg='同步失败' href="javascript:void(0);"
                                           data-href="${ctx}/cszjs/wxactivity/queryinfo.php?id=${repack.id}"
                                           class="pn-opt">同步</a>
                                    </#if>
                                <#else>
                                <#--<#if !(repack.sendStatus??||repack.receiveStatus??)>-->
                                    <#if canDelHbkl??>
                                        <a data-url="${ctx}/cszjs/wxactivity/${repack.id}" type="POST"
                                           data-type="detele" id="delbtn" abc-type="是否删除?" class="pn-opt">删除</a>
                                    </#if>
                                <#-- </#if>-->
                                </#if>
                            </td>
                        </#if>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <div class="button_caozuo_fenye">
                <div class="layui-btn nycon_sel_btn" data-check=false>全选</div>
                <a href="javascript:void(0);" class="layui-btn layui-btn-danger" id="batch_del">批量删除</a>
            </div>
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
                        <input type="hidden"
                               value="${ctx}/cszjs/wxactivity/detailList.php?isFirst=no&&activityId=${(BaseRq.activityId)!}&&secret=${(BaseRq.secret)!}&&sendStatus=${(BaseRq.sendStatus)!}&&receiveStatus=${(BaseRq.receiveStatus)!}&&openId=${(BaseRq.openId)!}"
                               id="currLink">

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
                    Excel文件：<input type="file" class="uploadFile" name="uploadFile" id="uploadFile"
                                   style="width:200px;display: inline-block;">
                    <a data-type="download" href="javascript:void(0);" class="pn-opt">导入模板下载</a>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="importbtn" name="importbtn" class="layui-btn layui-btn-normal" data-val="1">导入</button>
                <button type="button" id="back" class="layui-btn layui-btn-primary" data-val="1">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="hbkl_frame" style="width: 100%;height: 300px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" id="editbtn" name="editbtn" class="layui-btn"
                       data-val="1">确定</button>
                <button type="button" class="layui-btn layui-btn-primary" data-dism="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cszj/wxactivity.js" src="${ctx}/js/require.js"></script>
</body>
</html>
