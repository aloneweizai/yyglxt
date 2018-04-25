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
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/cszjs/wxqrcode/list.php" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">二维码名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(rq.name)!}" name="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="queryBtn" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="${ctx}/cszjs/wxqrcode/rqcodeEdit.php"  class="layui-btn layui-btn-normal fr">添加二维码</a>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>二维码名称</th>
                    <th>二维码</th>
                    <th>二维码描述</th>
                    <th>回复类型</th>
                    <th>回复内容</th>
                    <th>创建时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if rqcodeRs?? && ( rqcodeRs?size gt 0 )>
                    <#list rqcodeRs as rqcode>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+rqcode_index+1}</td>
                        <td>${(rqcode.name)!}</td>
                        <td>
                            <#if rqcode.image!="">
                                <img src="${rqcode.image}" style="height: 80px;">
                            <#else>
                                <img src="${ctx}/images/zanwu.png" style="height: 50;">
                            </#if>
                        </td>
                        <td>${(rqcode.description)!}</td>
                        <td>
                            <#if rqcode.type??>
                                <#if rqcode.type == "0">
                                    文本
                                <#else >
                                    图文
                                </#if>
                            </#if>
                        </td>
                        <td>${(rqcode.content)!}</td>

                        

                        <td>${(rqcode.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td>

                                <a href="${ctx}/cszjs/wxqrcode/rqcodeEdit.php?id=${rqcode.id!}">修改</a>
                                |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                          href="javascript:void(0);"
                                          data-href="${ctx}/cszjs/wxqrcode/rqcodeDel.php?id=${rqcode.id}" class="pn-opt">删除</a>&nbsp;


                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">

</div>
<script data-main="${ctx}/js/abc/cszj/rqcode.js" src="${ctx}/js/require.js"></script>
</body>
</html>