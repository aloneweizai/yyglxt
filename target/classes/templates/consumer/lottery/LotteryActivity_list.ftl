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
    <style type="text/css">
        #_content_list_form th, #_content_list_form td {
            text-align: center;
        }

        .ny_tab_t > tbody > tr > td, .ny_tab_t > tr > td {
            padding: 3px 3px;
        }

        .cxtd1 {
            text-align: right;
            width: 80px;
        }

        .cxtd2 {
            text-align: left;
            width: 150px;
        }

        .cxinput {
            width: 140px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form class="layui-form" action="${ctx}/consumerManager/lottery/lotteryActivity.php" method="get" id="actListForm">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">活动名称</label>
                    <div class="layui-input-inline">
                        <input type="text"  name ="name" value="${(pagination.name)!}" id="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="queryBtn" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/consumerManager/lottery/lotteryActivityEdit.php"  class="layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>

                    <th>活动名称</th>
                    <#--<th>描述</th>-->
                    <th>抽奖次数</th>
                    <th>用户等级</th>
                    <th>模版名称</th>
                    <th>有效期</th>
                    <th>每天免费(次)</th>
                    <th>领奖有效天数</th>
                    <th>每天派奖总数</th>
                    <th>当天已派奖数</th>
                    <th>状态</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if listRs?? && ( listRs?size gt 0 )>
                    <#list listRs as list>
                    <tr>
                        <td>${(list.name)!}</td>
                       <#-- <td>
                            <label title="${(list.description)!}" style="font-weight:normal">
                                <#if list.description?? && list.description?length gt 13>
                                ${list.description?substring(0,10)}...
                                <#else>${(list.description)!}
                                </#if>
                            </label>
                        </td>-->
                        <td>${(list.count)!}</td>
                        <td><#if list.userLevel??>LV${(list.userLevel)}</#if></td>
                        <td>${(list.templateName)!}</td>
                        <td>${(list.startTime?string("yyyy-MM-dd"))!}<br/>${(list.endTime?string("yyyy-MM-dd"))!}</td>

                        <td>${(list.userFreeCount)!}</td>
                        <td>${(list.getlotteyDay)!}</td>
                        <td>${(list.timeStock)!}</td>
                        <td>${(list.timeCount)!}</td>
                        <td>
                            <#if list.status?? && list.status>
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                        </td>
                        <td>
                            <a href="${ctx}/consumerManager/lottery/lotteryActivityprize.php?activityId=${list.id}">奖品</a>
                            |&nbsp; <a href="${ctx}/consumerManager/lottery/lotteryActivityip.php?activityId=${list.id}">IP屏蔽</a>
                        <#--            |&nbsp;<a href="${ctx}/consumerManager/lottery/lotterytime.php?activityId=${list.id}">时间段</a> -->

                            <#if list.status?? && list.status>
                                |&nbsp; <a href="javascript:void(0);"data-type="edit" data-id="${list.id}">查看</a>
                                |&nbsp;       <a data-type="delSig" data-confirm="确认停用?" data-okMsg="停用成功!" data-failMsg="停用失败" href="javascript:void(0);" data-href="lotteryActivity/enableApi.php?id=${list.id}&status=false">停用</a>

                            <#else >
                                |&nbsp;       <a data-type="delSig" data-confirm="确认停用?" data-okMsg="停用成功!" data-failMsg="停用失败" href="javascript:void(0);" data-href="lotteryActivity/enableApi.php?id=${list.id}&status=true">启用</a>

                                |&nbsp;<a  href="${ctx}/consumerManager/lottery/lotteryActivityEdit.php?id=${list.id}">修改</a>
                                |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                          href="javascript:void(0);"
                                          data-href="${ctx}/consumerManager/lottery/lotteryActivityDel.php?id=${list.id}"
                                          class="pn-opt">删除</a>

                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>

</div>
<script data-main="${ctx}/js/abc/consumer/LotteryActivity.js" src="${ctx}/js/require.js"></script>
</body>
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
                <button type="button" class="btn btn-primary ok"id="model-ok">确定</button>
            </div>
        </div>
    </div>
</div><!-- modal -->
</html>
