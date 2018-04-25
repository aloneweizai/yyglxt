<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<div class="container-fluid m_top_30 nycol_list_edit">
    <div class="panel panel-default">
        <div class="">
           <form name="addKnowForm" id="addKnowForm" method="POST">
               <input name="id" value="${(cate.id)!}" type="hidden"/>
               <table class="layui-table" lay-size="sm">
                   <tr>
                       <td>分类名称</td>
                       <td><input name="name" value="${(cate.name?html)!}" id="name" class="layui-input" style="width: 300px;"/><span class="color_r2">*</span></td>
                   </tr>
                        <tr>
                            <td>上级分类</td>
                            <td>
                                <input class="js_selectParentCate layui-input" value="${(cate.parentName?html)!}" date-code="${(cate.code)!}" <#if (cate?? && !cate.id??)>disabled</#if> readonly style="width: 300px;"/><span class="color_r2">*</span>
                            <#--<input name="id" value="${pId!''}" type="hidden"/>-->
                                <input name="parentCode" value="${(cate.parentCode)!}" type="hidden"/>
                            </td>
                        </tr>
                   <tr>
                       <td>排列顺序</td>
                       <td><input name="sort" id="sort" value="${(cate.sort)!}" class="layui-input" style="width: 300px;"/><span class="color_r2">*</span></td>
                   </tr>
                   <tr>
                        <td colspan="2" style="text-align: center;">
                            <button type="button" class="js_save_cate layui-btn">提交</button>
                            <button type="button" class="js_cancel_cate layui-btn layui-btn-primary">关闭</button>
                        </td>
                   </tr>
               </table>
           </form>
        </div>
    </div>
</div>
