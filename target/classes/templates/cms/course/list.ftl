<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <style type="text/css">
        .ztree{border: none}
    </style>
</head>
<body>
<@shiro.hasPermission name="cms_course:add"><#assign canAdd=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:edit"><#assign canEdit=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:statistics"><#assign canStatistics=true></@shiro.hasPermission>
<input type="hidden" name="sortFieldName" value="">
<input type="hidden" name="sortName" value="">
<div class="container-fluid m_top_30 nycol_list_edit">
    <div class="qazhishiku">
        <div class="qaleft">
            <#if canAdd?? ||canEdit??>
                <div class="layui-btn-group" style="margin-top: 8px; width: 100%">
                    <button class="layui-btn layui-btn-primary layui-btn-sm" id="addCateBtn" style="width: 33%;">
                        <i class="layui-icon">&#xe654;</i> 增加
                    </button>
                    <button class="layui-btn layui-btn-primary layui-btn-sm" id="editCateBtn" style="width: 33%;">
                        <i class="layui-icon">&#xe642;</i> 修改
                    </button>
                    <button class="layui-btn layui-btn-primary layui-btn-sm" id="removeCateBtn" style="width: 33%;">
                        <i class="layui-icon">&#xe640;</i> 删除
                    </button>
                </div>
            </#if>
            <div>
                <ul id="cateTree" class="ztree"></ul>
            </div>
        </div>
        <div class="qaright">
            <form class="layui-form">
                <div class="layui-form-top">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">课程名称</label>
                            <div class="layui-input-inline">
                                <input type="text" id="keywords" value="${RequestParameters["title"]!}" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline">
                                <select name="status" style="width:110px;height:35.5px;margin-left:5px;">
                                    <option value="">--状态--</option>
                                <#list courseStatuses as courseStatus>
                                    <option value="${courseStatus.code}"
                                            <#if RequestParameters["status"]?? && RequestParameters["status"]== courseStatus.code>selected</#if>
                                            >${courseStatus.description}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">推荐类型</label>
                            <div class="layui-input-inline">
                                <select name="recommend" style="width:110px;height:35.5px;margin-left:5px;">
                                    <option value="">--请选择--</option>
                                    <option value="putong">普通</option>
                                    <option value="recommend">推荐</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">是否收费</label>
                            <div class="layui-input-inline">
                                <select name="isFree" style="width:110px;height:35.5px;margin-left:5px;">
                                    <option value="">--请选择--</option>
                                    <option value="1">免费</option>
                                    <option value="0">收费</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn js_query" type="button">查询</button>
                        </div>
                        <#if canAdd??>
                            <a href="${ctx}/cms/course/edit.php" class="layui-btn layui-btn-normal fr">创建课程</a>
                        </#if>
                    </div>
                </div>
            </form>
            <div class="row js_page_div">
                <#--<#include "list_page.ftl">-->
            </div>
        </div>
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
            <button type="button"  class="js_close layui-btn">确认</button>
        </div>
    </div>
</div>
<ul style="display: none" id="category_ul">

</ul>
<script data-main="${ctx}/js/abc/cms/course/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>