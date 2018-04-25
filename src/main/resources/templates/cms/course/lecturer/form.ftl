<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<form id="form" action="${ctx}/cms/course/lecturer/save.php">
    <input type="hidden" name="lecturerId" value="${(lecturer.lecturerId)!}"/>
    <input type="hidden" name="userId" value="${(lecturer.userId)!}"/>
    <div style="background: #00bcd4; height: 40px; line-height: 40px; padding:0 10px; font-size: 16px; color: #fff;">编辑讲师信息<span class="glyphicon glyphicon-remove teacher-close fr" style="margin-top: 10px;"></span></div>
    <table class="layui-table" lay-size="sm" style="margin:2%; width: 96%;">
        <tr>
            <td style="width: 120px;">
                讲师用户名
            </td>
            <td>
                <input type="text" name="username" value="${(lecturer.username)!}" class="layui-input" style="width: 330px;">
                &nbsp;&nbsp;<a style="margin-right: -50px" class="layui-btn btn-xs js_valid_username">验证</a>
            </td>
        </tr>
        <tr>
            <td>
                讲师姓名
            </td>
            <td>
                <input type="text" name="lecturerName" value="${(lecturer.lecturerName)!}" class="layui-input" style="width: 330px;">
            </td>
        </tr>
        <tr>
            <td>
                联系电话
            </td>
            <td>
                <input type="text" name="phone" value="${(lecturer.phone)!}" class="layui-input" style="width: 330px;">
            </td>
        </tr>
        <tr>
            <td>
                讲师QQ
            </td>
            <td>
                <input type="text" name="lecturerQQ" value="${(lecturer.lecturerQQ)!}" class="layui-input" style="width: 330px;">
            </td>
        </tr>
        <tr>
            <td>
                所在单位
            </td>
            <td>
                <input type="text" name="company" value="${(lecturer.company)!}" class="layui-input" style="width: 330px;">
            </td>
        </tr>
        <tr>
            <td>
                讲师Email
            </td>
            <td>
                <input type="text" name="lecturerEmail" value="${(lecturer.lecturerEmail)!}" class="layui-input" style="width: 330px;">
            </td>
        </tr>
        <tr>
            <td>讲师简介</td>
            <td>
                <textarea style="width: 100%; height: 200px" name="intro" placeholder="讲师介绍需控制在200字以内" class="layui-textarea">${(lecturer.intro)!}</textarea>
                <#--<div class="jd_editor">${(lecturer.intro)!}</div>-->
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <input type="button" class="layui-btn js_save" value="保存"/>
                <input type="button" value="关闭" id="boxCancelBtn" class="layui-btn layui-btn-primary"/>
            </td>
        </tr>
    </table>
</form>