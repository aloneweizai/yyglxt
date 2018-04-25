<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/swfupload/css/default.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/swfupload/css/button.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/uploadify/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="box-positon">
    <form class="ropt">
        <input type="hidden" name="root" value="${root}"/>

    </form>
    <div class="clear"></div>
</div>
<div class="body-box" style="margin-top:14px;">
    <form style="margin-bottom:10px;">
        <input type="hidden" name="root" value="${root}"/>
        <table border="0">


            <tr>
                <td colspan="4">
                    <div id="fsUploadProgress"></div>
                </td>
            </tr>
        </table>
    </form>

<#--<input type="file" name="file_upload" id="file_upload" />-->

    <div class="depart_l">
        <div id="treeDemo" class="ztree"></div>
    </div>

    <div id="tableForm" class="depart_r">
        <div style="padding:5px; border-bottom:1px solid #ddd;">
            <span>文件路径：</span><span type="text">${root}</span>
            <input type="hidden" id="parentPath_list" value="${parentPath}">
            <div style="float: right; margin-top:-20px">
                <a href="javascript:void(0)" name="addTplFile" class="btn btn-success" style="display:none">上传模板文件</a>
                <a href="javascript:void(0)" name="addTplDir" class="layui-btn" style="display:none">创建模板文件夹</a>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30"></th>

                    <th>名称</th>
                    <th>路径</th>
                    <th>站点名称</th>
                    <th>模型名称</th>
                    <th>创建时间</th>
                    <th>最后修改时间</th>
                    <th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if (filelist)?? && (filelist?size > 0)>
                    <#list filelist as item>
                    <tr>
                        <td class="td_i"></td>
                        <input class="js_checkbox" name="templatePath_list" type="hidden" value="${item.templatePath}">
                        <td>${(item.templateName)!}</td>
                        <td>${(item.templatePath)!}</td>
                        <td>${(item.siteName)!"--"}</td>
                        <td>${(item.modelName)!"--"}</td>
                        <td>${(item.createTime?string('yyyy-MM-dd'))!"--"}</td>
                        <td>${(item.updateTime?string('yyyy-MM-dd'))!"--"}</td>
                        <td>
                            <input type="hidden" name="templateId" value="${item.templateId}">
                            <#if item.isFolder?? && item.isFolder==1>
                                <div class="btn btn-danger btn-xs dirDeleteBtn">删除</div>
                            <#else>
                                <div class="layui-btn btn-xs fileModifyBtn">编辑内容</div>
                                <div class="layui-btn btn-xs fileAttriModifyBtn">编辑属性</div>
                                <#if item.state?? && item.state==0>
                                    <div class="layui-btn btn-xs start_stop_Btn">启用模板</div>
                                <#else>
                                    <div class="layui-btn btn-xs start_stop_Btn">停用模板</div>
                                </#if>
                                <div class="btn btn-danger btn-xs fileDeleteBtn">删除</div>
                            </#if>
                        </td>

                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>


    </div>


</div>


<div class="clear"></div>


<script data-main="${ctx}/js/abc/cms/tpl" src="${ctx}/js/require.js"></script>
</body>
</html>