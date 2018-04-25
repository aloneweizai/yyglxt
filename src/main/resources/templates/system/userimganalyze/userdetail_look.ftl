<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .table>thead>tr>th,.table>tbody>tr>td{text-align:left;}

        .ny_tab_t>tbody>tr>td,.ny_tab_t>tr>td{padding: 3px 3px;}
        caption {
            padding-top: 8px;
            padding-bottom: 8px;
            color: #777;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/userimganalyze/userdetail/userlook.php?tagName=${BaseRq.tagName!}&startTime=${BaseRq.startTime!}&endTime=${BaseRq.endTime!}&startNum=${BaseRq.startNum!}&endNum=${BaseRq.endNum!}&doType=${BaseRq.doType!}&sex=${BaseRq.sex!}&type=${BaseRq.type!}&province=${BaseRq.province!}&menu=${BaseRq.menu!}"
          method="post" id="consumerListForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <caption>用户清单</caption>
                <thead class="pn-lthead">
                <tr>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>昵称</th>
                    <#--<th>真实姓名</th>-->
                    <th>手机号</th>
                    <th>积分</th>
                    <th>经验值</th>
                    <#--<th>用户等级</th>-->
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
                        <td>${(consumer.username)!}</td>
                        <td>${(consumer.nickname)!}</td>
                        <#--<td>${(consumer.realName)!}</td>-->
                        <td>${(consumer.phone)!}</td>
                        <td>${(consumer.points)!}
                        </td>
                        <td>${(consumer.exp)!}
                        </td>
                        <#--<td>${(consumer.medal)!}</td>-->
                        <td>
                            <#if levels?? && ( levels?size gt 0 )>
					              <#list levels as level>
                                <#if level.levelCode == consumer.vipLevel>${level.level!}</#if>
                            </#list>
			                    </#if>
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
                <#else>
                <tr>
                    <td colspan="11">--未查到用户详情信息--</td>
                </tr>
                </#if>
                </tbody>
            </table>
            <#if BaseRq.doType??&&BaseRq.doType!="1">
            <div class="button_caozuo_fenye">
                <div id='tjdownLoad' class="layui-btn"
                     data-qurl="${ctx}/userimganalyze/qexportuser.php?tagName=${BaseRq.tagName!}&startTime=${BaseRq.startTime!}&endTime=${BaseRq.endTime!}&startNum=${BaseRq.startNum!}&endNum=${BaseRq.endNum!}&doType=${BaseRq.doType!}&sex=${BaseRq.sex!}&type=${BaseRq.type!}&province=${BaseRq.province!}&menu=${BaseRq.menu!}"
                     data-durl="${ctx}/userimganalyze/consumption/exportExcel.php?tagName=${BaseRq.tagName!}&startTime=${BaseRq.startTime!}&endTime=${BaseRq.endTime!}&startNum=${BaseRq.startNum!}&endNum=${BaseRq.endNum!}&doType=${BaseRq.doType!}&sex=${BaseRq.sex!}&type=${BaseRq.type!}&province=${BaseRq.province!}&menu=${BaseRq.menu!}"
                     data-noMsg="当前条件下无可导出的用户详情信息，请重新输入条件统计" />导出用户详情</div>
        </div>
            </#if>
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

<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
