<div class="Popup-nav">
    <span>添加章<u class="glyphicon glyphicon-remove"></u></span>
</div>
<div class="Popup-content">
    <input type="hidden" id="chapter_id" value="${(chapter.chapterId)!}">
    <p>   章标题：<input type="text" id="chapter_content" value="${(chapter.chapterName)!}"></p>
    <p>   章排序：<input type="text" id="chapter_order" value="${(chapter.chapterSeq)!}"></p>
</div>
<div class="Popup-foot">
    <button type="button"  class="btn btn-danger js_chapter_cancel">取消</button>
    <button type="button" class="btn btn-success js_chapter_save">保存</button>
</div>