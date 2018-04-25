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
    <form class="layui-form" action="${ctx}/consumerManager/lottery/lottery.php" method="get" id="lotteryListForm">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">奖品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name ="name" value="${(pagination.name)!}" id="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline js-query" style="width:180px;">
                        <button id="queryBtn" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/consumerManager/lottery/lotteryEdit.php"  class="layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>

        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>奖品名称</th>
                    <th>图片</th>
                    <th>描述</th>
                    <th>类别</th>
                    <th>成本价</th>
                    <th>创建时间</th>
                    <th>是否中奖</th>
                    <th>邮寄</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if listRs?? && ( listRs?size gt 0 )>
                    <#list listRs as list>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+list_index+1}</td>
                        <td>${(list.name)!}</td>

                        <td>
                            <#if list.image!="">
                                <img src="${list.image}" style="height: 80px;">
                            <#else>
                                <img src="${ctx}/images/zanwu.png" style="height: 50;">
                            </#if>
                        </td>
                        <td>
                            <label title="${(list.description)!}" style="font-weight:normal">
                                <#if list.description?? && list.description?length gt 13>
                                ${list.description?substring(0,10)}...
                                <#else>${(list.description)!}
                                </#if>
                            </label>
                        </td>

                        <td>${(list.types)!}</td>
                        <td>${(list.cost)!}</td>

                        <td>${(list.createTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                            <#if list.noluck?? && list.noluck>
                                <div class="btn btn-danger btn-xs ">否</div>
                            <#else>
                                <div class="btn btn-success btn-xs ">是</div>
                            </#if>
                        </td>
                        <td>
                            <#if list.send?? && list.send>
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
                        </td>
                        <td>
                            <a href="${ctx}/consumerManager/lottery/lotteryEdit.php?id=${list.id}">修改</a>
                            |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                      href="javascript:void(0);"
                                      data-href="${ctx}/consumerManager/lottery/lotteryDel.php?id=${list.id}"
                                      class="pn-opt">删除</a>

                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>

</div>
<script data-main="${ctx}/js/abc/consumer/Lottery.js" src="${ctx}/js/require.js"></script>
</body>
</html>
