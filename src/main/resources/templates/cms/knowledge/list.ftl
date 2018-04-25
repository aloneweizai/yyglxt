<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
</head>
<body>
<#assign canAdd=false canEdit=false canDel=false>
<@shiro.hasPermission name="cms_know:add"><#assign canAdd=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_know:edit"><#assign canEdit=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_know:delete"><#assign canDel=true></@shiro.hasPermission>

<input type="hidden" class="js_select_cate" value="${RequestParameters["categoryCode"]!}">
<div class="container-fluid m_top_30 nycol_list_edit layui-form">
    <div class="layui-form-top">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">关键字</label>
                <div class="layui-input-inline">
                    <input type="text" id="keywords" value="${RequestParameters["keywords"]!}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select name="status">
                        <option value="">状态</option>
                        <option value="1" <#if RequestParameters["status"]?? && RequestParameters["status"] =='1'>selected</#if>   >启用</option>
                        <option value="0" <#if RequestParameters["status"]?? && RequestParameters["status"] =='0'>selected</#if> >停用</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">类别</label>
                <div class="layui-input-inline">
                    <select name="type" style="width: 100px">
                        <option value="">类别</option>
                        <option value="QA" <#if RequestParameters["type"]?? && RequestParameters["type"] =='QA'>selected</#if>>问答</option>
                        <option value="K" <#if RequestParameters["type"]?? && RequestParameters["type"] =='K'>selected</#if>>知识</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">知识推荐</label>
                <div class="layui-input-inline">
                    <select name="recommend">
                        <option value="">知识推荐</option>
                        <option value="common" <#if RequestParameters["recommend"]?? && RequestParameters["recommend"] =='common'>selected</#if>>普通问题</option>
                        <option value="QA_hot" <#if RequestParameters["recommend"]?? && RequestParameters["recommend"] =='QA_hot'>selected</#if>>推荐问题</option>
                        <option value="top"    <#if RequestParameters["recommend"]?? && RequestParameters["recommend"] =='top'>selected</#if>>普通知识</option>
                        <option value="K_hot"  <#if RequestParameters["recommend"]?? && RequestParameters["recommend"] =='K_hot'>selected</#if>>推荐知识</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                    <button id="searchClickBtn" class="layui-btn">搜索</button>
            </div>
            <#if canAdd>
                <button class="layui-btn layui-btn-normal fr" id="addKnowBtn">添加知识</button>
            </#if>
        </div>
    </div>
    <div class="qazhishiku">
        <div class="qaleft">
            <div class="layui-btn-group" style="margin-top: 8px; width: 100%">
                <#if canAdd>
                    <button class="layui-btn layui-btn-primary layui-btn-sm" id="addCateBtn" style="width: 33%;">
                        <i class="layui-icon">&#xe654;</i> 增加
                    </button>
                </#if>
                <#if canEdit>
                    <button class="layui-btn layui-btn-primary layui-btn-sm" id="editCateBtn" style="width: 33%;">
                        <i class="layui-icon">&#xe642;</i> 修改
                    </button>
                </#if>
                <#if canDel>
                    <button class="layui-btn layui-btn-primary layui-btn-sm" id="removeCateBtn" style="width: 33%;">
                        <i class="layui-icon">&#xe640;</i> 删除
                    </button>
                </#if>
            </div>

            <div>
                <ul id="cateTree" class="ztree"></ul>
            </div>
        </div>
        <div class="qaright">
            <div  class="js_page_div" id="knowHtml">
            <#--<#include "search.ftl">-->
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="main_modal container-fluid row js_pop_menu" style="z-index: 1000"  hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">请选择上级分类
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <div>
            <button type="button" class="js_close layui-btn">确认</button>
        </div>
    </div>
</div>
<ul style="display: none" id="category_ul">

</ul>
</body>
<script data-main="${ctx}/js/abc/cms/knowledge/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</html>