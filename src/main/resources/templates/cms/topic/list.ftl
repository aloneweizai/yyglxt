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
<div class="container-fluid m_top_30 nycol_list">
    <form action=" " method="post">
        <table class="ny_tab_t">
            <tr>
                <td>
                    <div class="nycon_l_t_btn text-right">
                        <div class="btn btn-md btn-info"><a href="${ctx}/cms/topic/addPage.php">创建专题</a></div>

                    </div>
                </td>
            </tr>
        </table>
    </form>

    <form action="" name="_topic_list_form" method="post">
        <div class=" nycon_list_b">

            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="54">
                        <div name="nycon_sel_btn" class="nycon_sel_btn">全选</div>
                    </th>
                    <th>序号</th>
                    <th>标题</th>
                    <th>站点名称</th>
                    <th>排列顺序</th>
                    <th>推荐</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#list topicList as topic>
                <tr>
                    <td><input name="ids" type="checkbox" lay-skin="primary"  data-siteid="${topic.siteId!}" value="${topic.topicId!}"></td>
                    <td>${(pagination.size!)?number*((pagination.page!)?number-1)+topic_index+1}</td>
                    <td><a  class="ljc_00bcd4" href="#点击跳转到前台预览" target="_blank">${topic.topicName!}</a></td>
                    <td>${topic.siteName!}</td>
                    <td><input name="_list_toplevel" value="${topic.priority!}" required="true" maxlength="3" style="width:40px" type="text"></td>
                    <td>${((topic.isRecommend)=="0")?string("否","是")}</td>

                    <td><a href="#点击跳转到前台预览" target="_blank">查看</a> | <a href="${ctx}/cms/topic/addPage.php?topicId=${topic.topicId!}">编辑</a> | <a
                            href="javascript:void(0);" data-topicid="${topic.topicId!}" name="_topic_list_delete" class="pn-opt">删除</a></td>
                </tr>
                </#list>
                </tbody>
            </table>
            <input name="_topic_delete_batch" type='button' class="btn btn-default btn-sm pn-opt" value='批量删除'/>
            <input name="_topic_update_priority" type='button' class="btn btn-default btn-sm pn-opt" value='保存排列顺序'/>
        </div>
    </form>
<#include "../../common/pagination.ftl">

</div>
</body>
<script data-main="${ctx}/js/abc/cms/topic/list.js" src="${ctx}/js/require.js"></script>
</html>