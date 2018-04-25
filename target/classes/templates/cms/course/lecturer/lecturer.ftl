<!-- 选择讲师页面 -->
<div>
<table class="layui-table" lay-size="sm">
    <thead class="pn-lthead">
    <tr>
        <th width="50">序号</th>
        <th>讲师姓名</th>
        <#--<th>好评度</th>-->
        <th>讲师简介</th>
        <th>创建时间</th>
        <th>联系电话</th>
        <th>所在单位</th>
        <th>操作</th>
    </tr>
    </thead>
    <#if lecturers?? && (lecturers?size>0)>
        <#list lecturers as lecturer>
            <tr>
                <td class="td_i">${pagerSpec.offset + lecturer_index + 1}</td>
                <td>${(lecturer.lecturerName)!}</td>
                <td>${(lecturer.intro)!}</td>
                <td>
                <#--${lecturer.createTime?string("yyyy-MM-dd HH:mm:ss")}-->
                </td>
                <td>${(lecturer.phone)!}</td>
                <td>${(lecturer.company)!}</td>
                <td>
                    <a data-id="${(lecturer.lecturerId)!}"
                       data-name="${(lecturer.lecturerName)!}"
                       data-img="${(lecturer.lecturerPicture)!}"
                       href="javascript:void(0);" class="js_select_lecturer_tr pn-opt">选择</a>
                </td>
            </tr>
        </#list>
    </#if>
</table>
</div>