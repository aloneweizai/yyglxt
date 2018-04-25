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
    <style>
        input, textarea, select {
            font-family: inherit;
            font-size: inherit;
            font-weight: inherit;
            padding: 2px 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > td {
            text-align: left;
        }
    </style>
    <!--评论管理-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list operator">
    <form action="${ctx}/cszjs/wxuser/list.php" method="post" id="wxuserListForm">
        <table class="ny_tab_t">
            <tr>
                <td style="text-align:left">
                    <span><input type="text" style="height: 30px" id="openid" name="openid" value="${(BaseRq.openid)!}"
                                 placeholder="OpenID" style="width:130px;"></span>
                    <span><input type="text" style="height: 30px" id="nickname" name="nickname"
                                 value="${(BaseRq.nickname)!}" placeholder="昵称" style="width:130px;"></span>
                    <span> 关注开始时间:
          <input type="text" id="startTime" data-type="datebox" name="startTime" style=" width:130px;"
                 value="${(startTime)!}">
          </span>
                    <span> 关注结束时间:
          <input type="text" id="endTime" data-type="datebox" name="endTime" style=" width:130px;"
                 value="${(endTime)!}">
          </span>
                </td>
                <td>
                    <div class="nycon_l_t_btn text-right">
                        <input type="button" value="查询" id="queryBtn" class="layui-btn">

                        <div class="btn btn-md btn-info">
                            <a data-type="delSig" data-confirm="确认同步微信用户到本地?" data-okMsg="同步微信用户成功!"
                               data-failMsg="同步微信用户失败" href="javascript:void(0);"
                               data-href="${ctx}/cszjs/wxuser/synchro.php" class="pn-opt">同步</a>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
    <form action="" name="_wxuser_list_form" method="post">
        <div class=" nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>OpenID</th>
                    <th>昵称</th>
                    <th>性别</th>
                    <th>所在城市</th>
                    <th>关注时间</th>
                    <th>用户名</th>
                    <th>手机号</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if wxuserList?? && ( wxuserList?size gt 0 )>
                    <#list wxuserList as wxuser>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+wxuser_index+1}</td>
                        <td>${wxuser.openid!}</td>
                        <td>${wxuser.nickname!}</td>
                        <td>${wxuser.sex!}</td>
                        <td>${wxuser.city!}</td>
                        <td>${(wxuser.subscribe_time?string("yyyy-MM-dd HH:mm:ss"))!}
                        <td>
                        </td>
                        <td></td>
                        <td><a href="${ctx}/cszjs/wxuser/selectone.php?openid=${wxuser.openid}">详细信息</a>
                            |&nbsp;<a data-type="delSig" data-confirm="确认重新获取微信资料 ?" data-okMsg="获取微信资料成功!"
                                      data-failMsg="获取微信资料失败" href="javascript:void(0);"
                                      data-href="${ctx}/cszjs/wxuser/synchroone.php?openid=${wxuser.openid}"
                                      class="pn-opt">重新获取微信资料</a>&nbsp;</td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">
    <input type="hidden" value="${ctx}/cszjs/wxuser/list.php" id="currLink">
</div>
<script data-main="${ctx}/js/abc/cszj/wxuser.js" src="${ctx}/js/require.js"></script>
</body>
</html>