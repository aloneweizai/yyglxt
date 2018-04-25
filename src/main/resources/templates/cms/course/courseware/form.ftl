<form>
    <fieldset>
    <table class="courseware_table newclassroom-table" <#if formName != "courseware">style="display:none;width: 1200px"<#else>style="width: 1200px"</#if>>
        <tbody class="last" >
            <tr>
                <td>
                    <a href="${currLink!}" class="layui-btn layui-btn-primary" style="margin-left:700px">返回课程列表</a>
                </td>
                <td class="chapter text-right">
                    <#if !readonly>
                            <button type="button" class="layui-btn add-chapter" type="button">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;添加章
                            </button>
                    </#if>
                </td>
            </tr>
            <#-- 章节 -->
            <#if course?? && course.chapterBoList?? && (course.chapterBoList?size>0)>
                <#list course.chapterBoList as chapter>
                    <tr data-chapterId="${chapter.chapterId}">
                        <td class="Introduction" data-id="${chapter.chapterId!}" date-sort="${chapter.chapterSeq!}">
                            <input type="text" readonly placeholder="${"第${chapter_index+1}章 : ${chapter.chapterName!}"}">
                            <div>
                                <ul>
                                    <li class="glyphicon glyphicon-plus-sign <#if !readonly>add-courseware</#if>"></li>
                                    <li class="glyphicon glyphicon-edit <#if !readonly>edit-chapter</#if>"></li>
                                    <li class="glyphicon glyphicon-trash <#if !readonly>remove-chapter</#if>"></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <#if chapter.coursewareList?? && (chapter.coursewareList?size>0)>
                        <#list chapter.coursewareList as courseware>
                            <tr class="courseware-son" data-id="${courseware.coursewareId}" data-chapterId="${chapter.chapterId}" data-sort="${courseware.coursewareSeq!}">
                                <td class="division">
                                    <div class="cable">
                                    </div>
                                    <div class="cable-down">
                                    </div>
                                </td>
                                <td class="courseware">
                                    <i></i>
                                    <input type="text" readonly placeholder="${"课件${courseware_index+1} : ${courseware.title}"}">
                                    <div>
                                        <ul>
                                            <li><span class="glyphicon glyphicon-pencil <#if !readonly>edit-courseware</#if>"></span><span <#if !readonly>class="edit-courseware"</#if>>编辑</span></li>
                                            <li><span class="glyphicon glyphicon-eye-open view-courseware"></span><span class="view-courseware">预览</span></li>
                                            <li class="removeli"><span class="glyphicon glyphicon-trash <#if !readonly>remove-courseware</#if>"></span><span <#if !readonly>class="remove-courseware"</#if>>删除</span></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </#list>
            </#if>
        </tbody>
        <tfoot class="foot">
            <tr>
                <td></td><td></td>
            </tr>
        </tfoot>
    </table>
    </fieldset>
</form>

<div id="chapter_div" class="Popup"  style="display:none;"></div>
<div id="popup_courseware_div"></div>