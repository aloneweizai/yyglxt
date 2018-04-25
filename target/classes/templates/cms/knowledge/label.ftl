<div name="panel" class="panel panel-default" style="padding: 15px;">
    <div class="form-group">
        <input type="text" id="tag_input" placeholder="标签名" style="width:180px;" class="layui-input">
        <input type="button" value="查询" id="tag_query"  class="layui-btn">
    </div>
    <div class="panel-body" style="height: 150px; padding:15px; border: 1px solid #ddd;" id="labelDIV_">
        <#if tagList?? && tagList.dataList??>
            <#list tagList.dataList as tag>
                <span><button type="button" class="layui-btn layui-btn-primary" name="tagBtn" value="${tag.id!}">${tag.name!}</button></span>&nbsp;&nbsp;
            </#list>
        </#if>
    </div>
    <div class="panel-footer" style="border: 1px solid #ddd">
        <div class="form-inline" style="width: 100%;">
            <div class="form-group">
                <label for="label">添加标签</label>
            </div>
            <div class="form-group">
                <input type="text" class="layui-input" id="label">
            </div>
            <button type="button" id="add_label_btn" class="layui-btn">保存</button>
        </div>
        <div>
            <p style="text-align: left;">
                多个标签请用逗号分隔
            </p>
        </div>
    </div>
</div>