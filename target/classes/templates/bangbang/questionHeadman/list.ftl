<html lang="en">
<head>
    <script type="text/javascript">
    <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
    </script>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bangbang/help.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <title></title>
    <style>
        a{
        color: #337ab7;
        text-decoration: none;
        cursor: pointer;
        }
        a:hover,a:focus {
        color: #23527c;
        }
    </style>
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="table-responsive responsive">
    <div class="table">
        <table class="layui-table" id="table-box" lay-size="sm">
            <colgroup>
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="40%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
            </colgroup>
            <thead>
            <tr>
                <th class="text-center">管理话题分类</th>
                <th class="text-center">用户姓名</th>
                <th class="text-center">联系电话</th>
                <th class="text-center">申请理由</th>
                <th class="text-center">申请时间</th>
                <th class="text-center">申请状态</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <#if headmanList?? && (headmanList?size > 0) >
                <#list headmanList as headman>
                    <tr class="text-center">
                        <td>${(headman.classifyName)!}</td>
                        <td>${(headman.name)!}</td>
                        <td>${(headman.phone)!}</td>
                        <td><p class="webkit">${(headman.applyReason)!}</p></td>
                        <td>${(headman.createTime?string("yyyy-MM-dd"))!}</td>
                        <td>
                        <#list headmanStatus as status>
                        <#if headman.status == status.name()>${status.description}</#if>
                        </#list>
                        </td>
                        <td align="center">
                        <a class="js_view" data-href="${ctx}/bangbang/questionHeadman/view/${headman.id}.php">查看</a>
                            <#if headman.status == "apply">
                            | <a class="js_status" data-href="${ctx}/bangbang/questionHeadman/modifyStatus/${headman.id}.php">审批</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>

    </div><table width="100%" class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</div>

</body>
<script data-main="${ctx}/js/abc/bangbang/questionHeadman/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</html>