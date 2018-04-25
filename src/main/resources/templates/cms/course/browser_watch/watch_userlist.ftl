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
<!--查看用户信息-->
<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/cms/courseBrowseWatch/watch_userlist.php?begintime=${BaseRq.begintime}&endtime=${BaseRq.endtime}&curriculumId=${BaseRq.curriculumId}"
          method="post"  id="consumerListForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                <tr>
                    <th width="45">序号</th>
                    <th style="text-align: center">用户名</th>
                    <th style="text-align: center">会员等级</th>
                    <th style="text-align: center">学习课件</th>
                    <th style="text-align: center">学习时间</th>
                    <th style="text-align: center">学习进度</th>
                    <th style="text-align: center">访问IP</th>
                    <th style="text-align: center">访问地点</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if list?? && (list?size > 0) >
                    <#list list as item>
                    <tr>
                        <td class="td_i">${BaseRq.offset + item_index + 1}</td>
                        <td align="center">
                        <#if canQuery??>
                            <a class="ljc_00bcd4" data-type="lookdialog"  data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${item.userId}">${(item.username)!}</a>
                            <#else>
                            ${(item.username)!}
                        </#if>
                        </td>
                        <td align="center">${(item.memberGrade)!}</td>
                        <td align="center">${(item.coursewareName)!}</td>
                        <td align="center">${(item.studyTime?string("yyyy-MM-dd"))!}</td>
                        <td align="center">${(item.studyDuration)!}</td>
                        <td align="center">${(item.visitIP)!}</td>
                        <td align="center">${(item.visitSite)!}</td>
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
    <div class="modal-dialog modal-lg" role="document" style="width: 95%;top: 10;">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame" style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button class="layui-btn layui-btn-primary" data-dismi='modal'>关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
