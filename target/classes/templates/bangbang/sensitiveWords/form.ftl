<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
</script>

<form id="sensitiveWords_form" action="${ctx}/bangbang/sensitiveWords/save.php">
    <input type="hidden" name="status" <#if sensitiveWords?? && sensitiveWords.status>value ="${(sensitiveWords.status?c)}"<#else>value="true"</#if>/>
    <input type="hidden" name="id" value="${(sensitiveWords.id)!}"/>
    <div class="layui-container container">
        <div class="layui-row row">
            <div class="layui-col-md2 md">
                <span>敏感词汇</span>
            </div>
            <div class="layui-col-md8 md">
                <input type="text" name="keywords" value="${(sensitiveWords.keywords)!}" lay-verify="title" autocomplete="off" placeholder="请输入敏感词汇" class="layui-input">
            </div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md8 md">
                <div class="btn">
                    <button type="button" class="layui-btn js_save">保存</button>
                    <button type="button" class="layui-btn layui-btn-primary js_close">取消</button>
                </div>
            </div>
        </div>
    </div>
</form>