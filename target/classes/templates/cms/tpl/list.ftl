<#--<div style="padding:5px; margin:-40px 0 15px 0; border-bottom:1px solid #ddd;">-->
<div style="padding:5px; border-bottom:1px solid #ddd;">
    <span>文件路径：</span><span type="text">
                                    <#if parentPath=="/">
                                        ${root}
                                        <#else>${root}/${parentPath}
                                    </#if>
                            </span>
    <input type="hidden" id="parentPath_list" value="${parentPath}">
    <input type="hidden" id="parentSiteId_list" value="${parentSiteId!}">
    <div style="float: right; margin-top:-20px">
        <a href="javascript:void(0)" name="addTplFile" class="btn btn-success">上传模板文件</a>
        <a href="javascript:void(0)" name="addTplDir" class="layui-btn">创建模板文件夹</a>
    </div>
</div>
<#--</div>-->
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
                <input  name="tpl_id" type="hidden" value="${item.templateId}">
                <td>${(item.templateName)!}</td>
                <td>${(item.templatePath)!}</td>
                <td>${(item.siteName)!"--"}</td>
                <td>${(item.modelName)!"--"}</td>
                <td>${(item.createTime?string('yyyy-MM-dd'))!"--"}</td>
                <td>${(item.updateTime?string('yyyy-MM-dd'))!"--"}</td>
                <td>
                <#--${item.parentPath?index_of("/")}-->
                <#--${item.templatePath}-->
                    <input type="hidden" name="templateId" value="${item.templateId}">
                    <#if item.isFolder?? && item.isFolder==1>
                        <#assign sList=(item.templatePath?split("/"))>
                        <#if item.parentPath?? && item.parentPath!="0" && item.parentPath!="/" && (sList?size>2)>
                            <#--<#if !item.templatePath?ends_with("content") && !item.templatePath?ends_with("list") && !item.templatePath?ends_with("channel")>-->
                                <#--<div class="btn btn-danger btn-xs dirDeleteBtn">删除</div>-->
                            <#--</#if>-->
                            <a class="js_del_btn pn-opt dirDeleteBtn" href="javascript:void(0)">删除</a>
                        <#else>
                                --
                        </#if>
                    <#else>
                        <#--<div class="layui-btn btn-xs fileModifyBtn">修改</div>-->
                        <#--<div class="layui-btn btn-xs fileAttriModifyBtn">修改属性</div>-->

                        <a class="js_del_btn pn-opt fileModifyBtn" href="javascript:void(0)">编辑内容</a> |
                        <a class="js_del_btn pn-opt fileAttriModifyBtn" href="javascript:void(0)">编辑属性</a> |
                        <#if item.state?? && item.state==0>
                            <#--<div class="layui-btn btn-xs start_stop_Btn">启用模板</div>-->
                            <a class="js_del_btn pn-opt start_stop_Btn" href="javascript:void(0)">启用模板</a> |
                        <#else>
                            <#--<div class="layui-btn btn-xs start_stop_Btn">停用模板</div>-->
                            <a class="js_del_btn pn-opt start_stop_Btn" href="javascript:void(0)">停用模板</a> |
                        </#if>
                        <#--<div class="btn btn-danger btn-xs fileDeleteBtn">删除</div>-->
                        <a class="js_del_btn pn-opt fileDeleteBtn" href="javascript:void(0)">删除</a>
                    </#if>
                </td>

            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>

