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
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .table>thead>tr>th,.table>tbody>tr>td{text-align:left;}

        .ny_tab_t>tbody>tr>td,.ny_tab_t>tr>td{padding: 3px 3px;}
        .cxtd1{text-align:right;width:80px;}
        .cxtd2{text-align:left;width:150px;}
        .cxinput{width:140px;height:30px;}
    </style>
</head>
<!--查看用户信息-->
<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/financed/clientList.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top" style="margin-top: -10px;">
            <table width="100%" style="border-bottom:1px solid #ccc;height: 30px"><tr><td style="width: 50%;text-align: left">我的客户</td><td style="width: 50%;text-align: right">我的推荐人代码:${(username)!}</td></tr></table>
        </div>
        <div class="layui-form-top">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" value="${(BaseRq.realname)!}" name="realname" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                    <input type="text" value="${(BaseRq.nickname)!}" name="nickname" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline" style="width:180px;">
                    <div id="consumer_query"  class="layui-btn">查询</div>
                    <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                </div>
            </div>

            <div class='moretj' style='display:none;'>
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间</label>
                    <div class="layui-input-inline">
                    <#--<input type="text" id="test3" value="${(BaseRq.createTime)!}" name="createTime" class="layui-input">-->
                        <select name="datetype"  id="datetype"lay-filter="datechange">
                            <option value=""></option>
                            <option <#if BaseRq.datetype?? && BaseRq.datetype == '1'>selected</#if>  value="1">今天</option>
                            <option <#if BaseRq.datetype?? && BaseRq.datetype == '2'>selected</#if> value="2">昨天</option>
                            <option <#if BaseRq.datetype?? && BaseRq.datetype == '3'>selected</#if> value="3">最近7天</option>
                            <option <#if BaseRq.datetype?? && BaseRq.datetype == '4'>selected</#if> value="4">最近30天</option>
                            <option <#if BaseRq.datetype?? && BaseRq.datetype == '5'>selected</#if> value="5">时间段</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间起</label>
                    <div class="layui-input-inline"  id="createTimeB">
                        <input type="text" class="layui-input" id="test30" value="${(BaseRq.createTimeB)!}" name="createTimeB" >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间止</label>
                    <div class="layui-input-inline" id="createTimeE">
                        <input type="text" class="layui-input" id="test31" value="${(BaseRq.createTimeE)!}" name="createTimeE">
                    </div>
                </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机号</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.phone)!}" name="phone" class="layui-input">
                        </div>
                    </div>
                <#--<div class="layui-inline">
                    <label class="layui-form-label">注册时间</label>
                    <div class="layui-input-inline"  id="createTime">
                        <input type="text" class="layui-input"   id="test30"  value="${(BaseRq.createTime)!}" name="createTime">
                    </div>
                </div>-->
            </div>
        </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>真实姓名</th>
                    <th>积分</th>
                    <th>经验值</th>
                    <th>礼包金额(元)</th>
                    <th>用户等级</th>
                    <th>会员等级</th>
                    <th>用户状态</th>
                    <th>注册时间</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if consumers?? && ( consumers?size gt 0 )>
                    <#list consumers as consumer>
                    <tr>
                        <td width='30' class="td_i">${BaseRq.offset + consumer_index + 1}</td>
                        <td>
                        <#if canQuery??>
                            <a class="ljc_00bcd4" data-type="opendialog" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${consumer.userid}">
                            ${(consumer.username)!}</a>
                        <#else>
                            ${(consumer.username)!}
                        </#if>
                        </td>
                        <td>${(consumer.nickname)!}</td>
                        <td>${(consumer.realName)!}</td>
                        <td><a class="ljc_00bcd4" title="查看积分记录" id="pointsLog" data-type="opendialog" href="javascript:;" data-url="${ctx}/consumerManager/consumer/pointsLog.php?userId=${consumer.userid}"
                               data-val="1"      <#--href="${ctx}/consumerManager/consumer/pointsLog.php?userId=${consumer.id}"-->>${consumer.points}</a>
                        </td>
                        <td><a class="ljc_00bcd4" title="查看经验值记录" data-type="opendialog" data-val="2" href="javascript:;" data-url="${ctx}/consumerManager/consumer/expLog.php?userId=${consumer.userid}"
                        <#--href="${ctx}/consumerManager/consumer/expLog.php?userId=${consumer.id}"-->>${consumer.exp}</a>
                        </td>
                        <td><a class="ljc_00bcd4" title="查看礼包金额" data-type="opendialog" data-val="5" href="javascript:;" data-url="${ctx}/consumerManager/consumer/amountLog.php?userId=${consumer.userid}"
                                >${(consumer.amount)!}</a>
                        <td>${(consumer.levelName)!}</td>
                        <td>
                            <a class="ljc_00bcd4" title="查看会员记录" data-type="opendialog" data-val="3" href="javascript:;" data-url="${ctx}/consumerManager/consumer/vipLog.php?userId=${consumer.userid}"
                            <#-- href="${ctx}/consumerManager/consumer/vipLog.php?userId=${consumer.id}"-->>
                            ${(consumer.vipLevelName)!}
                            </a>
                        </td>
                        <td>
                            <#if consumer.status?? && consumer.status >
                                <div class="btn btn-success btn-xs ">启用</div>

                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                        </td>
                        <td>${(consumer.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>

                    </tr>
                    </#list>
                <#elseif consumers?? && ( consumers?size == 0 )>
                <tr>
                    <td colspan="11" style="font-size:15px; color: #999;text-align: center">--未查到客户信息--</td>
                </tr>
                </#if>
                </tbody>
            </table>
        <#if consumers??>
            <table class="yy_fanye">
                <tbody>
                <tr>
                    <td align="center">
                        共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                        每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size"d type="text">条&nbsp;&nbsp;
                        <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                        <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                        <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                        <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                        当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                        <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                        <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo"type="button">
                        <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                        <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                        <input type="hidden" value="${ctx}/orderchange/applications.php" id="currLink">
                    </td>
                </tr>
                </tbody>
            </table>
        </#if>
        </div>
    </form>
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
<script data-main="${ctx}/js/abc/consumer/userpage" src="${ctx}/js/require.js"></script>
</body>
</html>
