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
<@shiro.hasPermission name="uc_taxpayer:nsrjbxx">
    <#assign canNsrjbxx=true>
</@shiro.hasPermission>
<@shiro.hasPermission name="uc_taxpayer:detail">
    <#assign canDetail=true>
</@shiro.hasPermission>
<@shiro.hasPermission name="uc_taxpayer:pwdlog">
    <#assign canPwdlog=true>
</@shiro.hasPermission>
<!--查看用户信息-->
<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/consumer/taxpayerBindQuery.php" method="post" id="consumerListForm"
          class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>

                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                <#--430121595471003-->
                    <label class="layui-form-label">纳税人识别号</label>

                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.nsrsbh)!}" name="nsrsbh" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">绑定状态</label>

                    <div class="layui-input-inline">
                        <select name="status" class="cxinput" id="status">
                            <option value=""></option>
                            <option <#if BaseRq.status?? && BaseRq.status>selected</#if>  value="true">有效</option>
                            <option <#if BaseRq.status?? && !BaseRq.status>selected</#if> value="false">失效</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"> 所属系统</label>

                    <div class="layui-input-inline">
                        <select name="type" class="cxinput">
                            <option <#if BaseRq.type?? && BaseRq.type == 'dzsb'>selected</#if>  value="dzsb">电子申报
                            </option>
                            <option <#if BaseRq.type?? && BaseRq.type == 'hngs'>selected</#if> value="hngs">湖南国税
                            </option>
                            <option <#if BaseRq.type?? && BaseRq.type == 'hnds'>selected</#if> value="hnds">湖南地税
                            </option>
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
                    <th></th>
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>纳税人识别号</th>
                    <th>纳税人名称</th>
                    <th>主管税务机关</th>
                    <th>所属系统</th>
                    <th>绑定状态</th>
                    <th>绑定时间</th>
               <#if canDetail??||canPwdlog??>
                    <th>操作选项</th>
               </#if>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if taxpayerBindRs?? && ( taxpayerBindRs?size gt 0 )>
                    <#list taxpayerBindRs as taxpayerBind>
                    <tr>
                        <td class="td_i">${BaseRq.offset + taxpayerBind_index + 1}</td>
                        <td>${(taxpayerBind.username)!}</td>
                        <td>
                        <#if canQuery??>
                            <a class="ljc_00bcd4" data-type="lookdialog" data-val="4" href="javascript:;"
                               data-url="${ctx}/consumerManager/consumer/info.php?id=${taxpayerBind.userId}">${(taxpayerBind.nickname)!}</a>
                            <#else>
                            ${(taxpayerBind.nickname)!}
                        </#if>
                        </td>
                        <td>${(taxpayerBind.nsrsbh)!}</td>
                        <td>
                            <#if canNsrjbxx??>
                                <a class="ljc_00bcd4" data-type="lookdialog" data-val="4" href="javascript:;"
                                   data-url="${ctx}/consumerManager/consumer/taxpayerBind_nsrxx.php?id=${(taxpayerBind.id)!}&type=${(taxpayerBind.bindType)!}&nsrsbh=${(taxpayerBind.nsrsbh)!}">${(taxpayerBind.nsrmc)!}</a>
                            <#else>
                            ${(taxpayerBind.nsrmc)!}
                            </#if>
                        </td>
                        <td>${(taxpayerBind.swjgmc)!}</td>
                        <td>
                            <#if taxpayerBind.bindType?? && ( taxpayerBind.bindType == 'dzsb' )>
                                电子申报
                            <#elseif taxpayerBind.bindType?? && ( taxpayerBind.bindType == 'hngs' )>
                                湖南国税
                            <#else>
                                湖南地税
                            </#if>
                        </td>
                        <td>
                            <#if taxpayerBind.status?? && taxpayerBind.status >
                                <div class="btn btn-success btn-xs ">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">失效</div>
                            </#if>
                        </td>
                        <td>${(taxpayerBind.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <#if canDetail??||canPwdlog??>
                        <td>
                            <#if canDetail??>
                                <a href="javascript:void(0);" data-id="${(taxpayerBind.id)!}"
                                   data-nsrsbh="${(taxpayerBind.nsrsbh)!}" data-type="${(taxpayerBind.bindType)!}"
                                   name="a1">查看</a>
                            </#if>
                            <#if canPwdlog??>
                                | <a data-type="lookdialog" data-val="4" href="javascript:;"
                                     data-url="${ctx}/consumerManager/consumer/taxpayerBind_pwdlog.php?nsrsbh=${(taxpayerBind.nsrsbh)!}">修改密码记录</a>
                            </#if>
                        </td>
                        </#if>
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
                <iframe id="consumer_frame" style="width: 100%;height: 500px;border:0" border="0"
                        frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal(模态框) -->
<div class="modal fade" id="modal-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">详细页面</h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary ok" id="model-ok">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- modal -->
<script data-main="${ctx}/js/abc/consumer/taxpayerBind.js" src="${ctx}/js/require.js"></script>
</body>
</html>