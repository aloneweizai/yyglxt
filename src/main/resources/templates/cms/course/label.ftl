<div name="panel" class="panel panel-default">
    <div class="panel-heading">
        <input type="text" id="tag_input" placeholder="标签名" style="width:130px;height:30px;">
        <input type="button" value="查询" id="tag_query"  class="layui-btn">
    </div>
    <div class="panel-body" style="height: 200px;" id="labelDIV_">
        <#if tagList?? && tagList.dataList??>
            <#list tagList.dataList as tag>
                <span><button type="button" class="layui-btn layui-btn-primary" name="tagBtn" value="${tag.tagId!}">${tag.tagName!}</button></span>&nbsp;&nbsp;
            </#list>
        </#if>
    </div>
    <div class="panel-footer">
        <div class="form-inline" style="width: 100%;">
            <div class="form-group">
                <label for="label">添加标签</label>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="label">
            </div>
            <button type="button" id="add_label_btn" class="layui-btn layui-btn-primary">保存</button>
        </div>
        <div>
            <p style="text-align: left;">
                多个标签请用逗号分隔
            </p>
        </div>
    </div>
</div>