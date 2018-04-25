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
    <!--评论管理-->
</head>

<body>
<div class="container-fluid m_top_30 nycol_list">
    <form name="" id="">
        <table class="ny_tab_t">
            <tr>
                <td>
                    <span> 审核:</span>
                    <span>
                        <select name="isChecked" id="isChecked">
                            <option value=""
                                    <#if isChecked??>
                                        selected="selected"
                                    </#if>
                                    >--所有--</option>
                            <option value="1"
                            <#if isChecked=="1">
                                    selected="selected"
                            </#if>
                            >是</option>
                            <option value="0"
                            <#if isChecked=="0">
                                    selected="selected"
                            </#if>
                            >否</option>
                        </select>
                    </span>
                    <span>推荐：</span>
                    <span>
                        <select name="isRecommend" id="isRecommend">
                            <option value=""
                                    <#if isRecommend??>
                                        selected="selected"
                                    </#if>
                                    >--所有--</option>
                            <option value="1"
                            <#if isRecommend=="1">
                                    selected="selected"
                            </#if>>是</option>
                            <option value="0"
                            <#if isRecommend=="0">
                                    selected="selected"
                            </#if>>否</option>
                        </select>
                    </span>
                    <input name="cid" value="" type="hidden">
                </td>
                <td>
                    <div class=" nycon_list_h">


                    </div>
                </td>
                <td>
                    <div class="nycon_l_t_btn text-right">
                        <div >
                            <input id="queryBtn" name="queryBtn" class="btn btn-md btn-bj-blue" value="查询" style="border:none; color:#fff;"     type="button">

                    </div>
                </td>
            </tr>
        </table>
    </form>

    <div class=" nycon_list_b">
        <table class="layui-table" lay-size="sm">
            <thead class="pn-lthead">
            <tr>
                <th width="54">
                    <div class="nycon_sel_btn">全选</div>
                </th>
                <th>序号</th>
                <#--<th>评论</th>-->
                <th>标题</th>
                <th>推荐</th>
                <th>审核</th>
                <th>评论时间</th>
                <th>用户名</th>
                <th>操作选项</th>
            </tr>
            </thead>
            <tbody class="pn-ltbody">
            <#if comments.dataList?? && (comments.dataList?size > 0) >
                <#list comments.dataList as comment>
                <tr>
                    <td><input name="ids" type="checkbox" lay-skin="primary"  status="${comment.isChecked!}" value="${comment.commentId!}"></td>
                    <td>${comment_index+1+(pagination.page-1)*pagination.size}</td>
                    <#--<td>-->
                        <#--<#if comment.text?exists>-->
                            <#--${comment.text}-->
                        <#--</#if>-->
                    <#--</td>-->
                    <td>
                        <#if comment.title?exists>
                            ${comment.title}
                        </#if>
                    </td>
                    <td>
                        <#if comment.isRecommend?exists>
                            <#if comment.isRecommend==1>

                                <div class="btn btn-success btn-xs ">是</div>
                                <#else >
                                    <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
                        </#if>
                    </td>
                    <td>
                        <#if comment.isChecked?exists>
                            <#if comment.isChecked==1>
                                <div class="btn btn-success btn-xs ">是</div>
                            <#else >
                                <div class="btn btn-danger btn-xs ">否</div>
                            </#if>
                        </#if>
                    </td>
                    <td>
                        <#if comment.createTime??>
                            ${comment.createTime?string("yyyy-MM-dd HH:mm:ss")}
                        </#if>
                    </td>
                    <td>
                        <#if comment.commentUserId?exists>
                            ${comment.commentUserId}
                        </#if>
                    </td>
                    <td>
                        <a href="${ctx}/cms/comment/${comment.commentId}">修改</a> |
                        <a data-url="${ctx}/cms/comment/${comment.commentId}" type="POST" abc-type="delete"  class="pn-opt">删除</a>
                    </td>
                </tr>
                </#list>
            <#else>
            </#if>
            </tbody>
        </table>
        <input type='button' class="btn btn-default btn-sm pn-opt" value='批量删除'  id="batch_del"/>
        <input type='button' class="btn btn-default btn-sm pn-opt" value='批量审核'  id="batch_review"/>
       <!-- <input type='button' class="btn btn-default btn-sm pn-opt" value='保存排列顺序'/>-->
    <#--${pagination}-->
    <#include "../../common/pagination.ftl">
    </div>
</div>
<script data-main="${ctx}/js/abc/cms/comment" src="${ctx}/js/require.js"></script>
</body>
</html>