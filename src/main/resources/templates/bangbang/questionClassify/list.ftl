<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()> var ctx = "${ctx}";
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
        .selectTag span{font-weight: bolder;margin-right: 10px;}
        .layui-form-checkbox{padding-bottom: 12px;}
    </style>
</head>
<body>
<@shiro.hasPermission name="cms_course:add"><#assign canAdd=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:edit"><#assign canEdit=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:statistics"><#assign canStatistics=true></@shiro.hasPermission>

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
        <div class="qaright js_tag_div" hidden>
            <form class="layui-form">
                <div class="layui-form-top">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">标签名称</label>
                            <div class="layui-input-inline">
                                <input  autocomplete="off" type="text" id="keywords" placeholder="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="button" class="layui-btn js_select_tag">查询</button>
                        </div>
                        <button type="button" class="layui-btn layui-btn-normal js_add_tag fr">添加标签</button>
                    </div>
                </div>
                <div class="layui-row row">
                    <div style="padding: 15px;">
                        <table class="layui-table" lay-skin="line">
                            <tr>
                                <td><span style="font-weight: bold;" id="categoryName" data-categoryId="" data-categoryName="">分类名称：</span></td>
                            </tr>
                            <tr>
                                <td><div class="checked selectTag">
                                    <span class="selectTag_title">已选标签：</span>
                                </div></td>
                            </tr>
                            <tr>
                                <td><div class="checkbox js_tag_checkbox" >
                                <#if (tagList?? && tagList.dataList?? && tagList.dataList?size > 0)>
                                    <#list tagList.dataList as tag>
                                        <input name="tagIds" lay-filter="tag" data-id="${tag.id}" data-name="${tag.name}" type="checkbox" title="${tag.name}" lay-skin="primary">
                                    </#list>
                                </#if>
                                </div></td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="layui-row row js_tag_div" hidden>
                                        <button class="layui-btn js_save_categoryTag_btn" style="margin-left: 10px;">提交</button>
                                    <#--<input name="button" value="提交" class="js_save_categoryTag_btn btn btn-info btn-sm w_55" date-href="/ABCOMS/system/role/operator/save.php">-->
                                    </div>
                                </td>
                            </tr>
                        </table>



                    </div>
                </div>

            </form>
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
            <input value="确认" class="js_close layui-btn">
        </div>
    </div>
</div>
<ul style="display: none" id="category_ul">
</ul>
<div style="display: none;padding: 30px;" id="addTag_pop">
    <p>请输入标签名</p>
    <p style="margin-top: 20px;">
        <input type="text" id="label" value="" lay-verify="title" autocomplete="off" placeholder="多个标签请用逗号分隔" class="layui-input">
    </p>
    <div class="layui-input-block btns" style="margin-top: 20px;margin-left: 0px">
        <button class="layui-btn js_submit_tag">立即提交</button>
        <button class="layui-btn layui-btn-primary" name="close-layer-btn">关闭</button>
    </div>
</div>

<script data-main="${ctx}/js/abc/bangbang/questionClassify/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>