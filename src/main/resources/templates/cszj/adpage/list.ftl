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
    <form action="${ctx}/adpage/adpageList.php" method="post" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.name)!}" name="name" id="name" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="queryBtn" class="layui-btn" type="button">查询</button>
                    </div>
                </div>
                <a href="${ctx}/cszjs/adpage/adpageEdit.php"  class="layui-btn layui-btn-normal fr">添加广告页</a>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>名称</th>
                    <th>图片</th>
                    <th>外链地址</th>
                    <th>显示名称</th>
                    <th>访问量</th>
                    <th>排序</th>
                    <th>状态</th>
                    <th>有效期起止</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if adpageRs?? && ( adpageRs?size gt 0 )>
                    <#list adpageRs as adpage>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+adpage_index+1}</td>
                        <td>${(adpage.name)!}</td>
                        <td>
                            <#if adpage.url!="">
                                <img src="${adpage.url}" style="height: 80px;">
                            <#else>
                                <img src="${ctx}/images/zanwu.png" style="height: 50;">
                            </#if>
                        </td>
                        <td>${(adpage.link)!}</td>
                        <td>
                            <#if adpage.showName?? && adpage.showName>
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
                        </td>
                        <td>
                        <#if adpage.openCount?? && adpage.openCount gt 0>
                            <a href="${ctx}/cszjs/adpagelog/list.php?adpageid=${adpage.id}">${(adpage.openCount)!}</a>
                        <#else>
                            ${(adpage.openCount)!}
                        </#if>

                        </td>
                        <td>${(adpage.sort)!}</td>
                        <td>
                            <#if adpage.status?? && adpage.status>
                                <div class="btn btn-success btn-xs ">启用</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">停用</div>
                            </#if>
                        </td>
                        <td>

                        ${(adpage.startTime?string("yyyy-MM-dd"))!}
                            ~
                        ${(adpage.endTime?string("yyyy-MM-dd"))!}
                        </td>

                        <td>
                            <#if adpage.status?? && adpage.status>
                                <a data-type="opendialog" href="javascript:;" data-url="${ctx}/cszjs/adpage/addlook.php?id=${adpage.id}">查看</a>|
                                <a data-type="delSig" data-confirm="确认停用该广告图片?" data-okMsg="停用成功!" data-failMsg="停用失败"
                                   href="javascript:void(0);"
                                   data-href="${ctx}/cszjs/adpage/adpageDisable.php?id=${adpage.id}"
                                   class="pn-opt">停用</a>
                            <#else>
                                <a href="${ctx}/cszjs/adpage/adpageEdit.php?id=${adpage.id}">修改</a>
                                |&nbsp;<a data-type="delSig" data-confirm="确认删除?" data-okMsg="删除成功!" data-failMsg="删除失败"
                                          href="javascript:void(0);"
                                          data-href="${ctx}/cszjs/adpage/adpageDel.php?id=${adpage.id}" class="pn-opt">删除</a>&nbsp;|
                                <a data-type="delSig" data-confirm="确认启用该广告图片?" data-okMsg="启用成功!" data-failMsg="启用失败"
                                   href="javascript:void(0);"
                                   data-href="${ctx}/cszjs/adpage/adpageEnable.php?id=${adpage.id}"
                                   class="pn-opt">启用</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
<#include "../../common/pagination.ftl">
    <input type="hidden" value="${ctx}/cszjs/adpage/list.php" id="currLink">
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"  style="width: 100%;height: 650px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cszj/adpage.js" src="${ctx}/js/require.js"></script>
</body>
</html>