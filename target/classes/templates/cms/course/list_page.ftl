
<@shiro.hasPermission name="cms_course:add"><#assign canAdd=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:edit"><#assign canEdit=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:delete"><#assign canDelete=true></@shiro.hasPermission>
<@shiro.hasPermission name="cms_course:statistics"><#assign canStatistics=true></@shiro.hasPermission>

<input type="hidden" class="js_currLink" value="${currLink!}">
<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<div style="padding: 15px;">
    <table class="layui-table" lay-skin="sm">
        <thead>
            <#--<th width="54"><div class="nycon_sel_btn js_selectAll" data-check=false>全选</div></th>-->
            <th width="50">序号</th>
            <th width="250">课程标题</th>
            <th nowrap="nowrap">
                <span class="js_sort" data-name="browseNum" style="cursor: pointer;">浏览</span>
                <span class="layui-table-sort layui-inline"
                              <#if RequestParameters["sortFieldName"]?? && RequestParameters["sortName"]??
                              && RequestParameters["sortFieldName"]=='browseNum'>lay-sort="${RequestParameters["sortName"]!}"</#if>>
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                </span>
            </th>
            <th nowrap="nowrap">
                <span class="js_sort" data-name="watchNum" style="cursor: pointer;">观看</span>
                <span class="layui-table-sort layui-inline"
                      <#if RequestParameters["sortFieldName"]?? && RequestParameters["sortName"]??
                      && RequestParameters["sortFieldName"]=='watchNum'>lay-sort="${RequestParameters["sortName"]!}"</#if>>
                            <i class="layui-edge layui-table-sort-asc"></i>
                            <i class="layui-edge layui-table-sort-desc"></i>
                </span>
            </th>
            <th>课程分类</th>
            <th>状态</th>
            <th>授课方式</th>
            <th>是否收费</th>
            <th>推荐方式</th>
            <th>创建者</th>
            <th>更新时间</th>
            <#if canAdd?? ||canEdit?? || canDelete?? || canStatistics??>
            <th>操作</th>
            </#if>
        </thead>
        <tbody class="js-body-tr">
        <#if (courses?? && courses?size > 0)>
            <#list courses as course>
            <tr>
                <#--<td><input class="js_checkbox" name="ids" class="checkbox" type="checkbox" lay-skin="primary"  value="${course.curriculumId}"></td>-->
                <td>${pagerSpec.offset + course_index + 1}</td>
                <td>
                <#if course.status??&&course.status == 1>
                    <a class="ljc_00bcd4" href="${SNS_PATH}/school/details/${course.curriculumId}" target="_blank">${course.title!}</a>
                    <#else>
                    ${course.title!}
                    </#if>
                    </td>
                <td>${course.browseNum!}</td>
                <td>${course.watchNum!}</td>
                <td>${course.classify!}</td>
                <td>
                    <#if course.status??&&course.status == 0>
                        <div class="btn btn-info btn-xs">未发布</div>
                    <#elseif course.status??&&course.status == 1>
                        <div class="btn btn-success btn-xs">已发布</div>
                    <#elseif course.status??&&course.status == 2>
                        <div class="btn btn-danger btn-xs">已撤销</div>
                    </#if>
                </td>
                <td>
                    <#list teachMethodTypes as teachMethodType>
                        <#if teachMethodType.name() == course.teachingMethod>
                            ${teachMethodType.description}
                        </#if>
                    </#list>
                </td>
                <td>${(course.isFree==1)?string("免费","收费")}</td>
                    <td>${(course.recommend??&&course.recommend=="recommend")?string("推荐","普通")}</td>
                <td>${course.createrName!}</td>
                <td>${(course.updateTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                <#if canAdd?? ||canEdit?? || canDelete?? || canStatistics??>
                <td>
                    <a class="js_view" href="javascript:void(0)" data-href="${ctx}/cms/course/edit.php?id=${(course.curriculumId)!}&readonly=true">查看</a> |
                    <#if course.status == 0><!-- 未发布状态 -->
                        <#if canEdit??><a class="js_edit" href="javascript:void(0)" data-href="${ctx}/cms/course/edit.php?id=${(course.curriculumId)!}">修改</a> |</#if>
                        <#if canDelete??><a class="js_delete" href="javascript:void(0)" data-href="${ctx}/cms/course/delete/${(course.curriculumId)!}.php">删除</a> |</#if>
                        <#if canEdit??><a class="js_change_status" href="javascript:void(0)" data-href="${ctx}/cms/course/updateStatus/${(course.curriculumId)!}.php?status=1">发布</a></#if>
                    </#if>
                    <#if course.status == 1><!-- 已发布状态 -->
                        <#if canEdit??><a class="js_change_status" href="javascript:void(0)" data-href="${ctx}/cms/course/updateStatus/${(course.curriculumId)!}.php?status=2">撤销发布</a> |</#if>
                        <#if canStatistics??><a class="js_view_statistics" href="javascript:void(0)" data-href="${ctx}/cms/course/statistics/${(course.curriculumId)!}.php?">授课情况</a></#if>
                    </#if>
                    <#if course.status == 2><!-- 已撤销状态 -->
                        <#if canEdit??>
                            <a class="js_edit" href="javascript:void(0)" data-href="${ctx}/cms/course/edit.php?id=${(course.curriculumId)!}">修改</a> |
                            <a class="js_change_status" href="javascript:void(0)" data-href="${ctx}/cms/course/updateStatus/${(course.curriculumId)!}.php?status=1">发布</a> |
                        </#if>
                        <#if canStatistics??><a class="js_view_statistics" href="javascript:void(0)" data-href="${ctx}/cms/course/statistics/${(course.curriculumId)!}.php?">授课情况</a></#if>
                    </#if>
                </td>
                </#if>
            </tr>
            </#list>
        <#else>
        <tr>
            <td colspan="8">
                <p style="text-align: center;"></p>
            </td>
        </tr>
        </#if>
        </tbody>
    </table>
    <table class="yy_fanye" style="width: 100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</div>