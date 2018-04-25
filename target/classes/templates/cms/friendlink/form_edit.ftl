<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8" />
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <!--友情链接修改-->
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form name="linkForm" id="linkForm" class="layui-form">
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <input type="hidden" name="friendlinkId" id="friendlinkId" value="${link.friendlinkId!}">
                <tr>
                    <td width="90">网址名称</td>
                    <td width="850">
                        <input type="text" maxlength="100" name="siteName" id="siteName" value="${link.siteName!}" class="layui-input" style="width:300px">
                        <span class="color_r2">*</span>
                    </td>
                    <td width="90">网址地址</td>
                    <td>
                        <input type="text" name="domain" id="domain" value="${link.domain!}" class="layui-input"  style="width:300px">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td>网址类别</td>
                    <td><div style="width:300px">
                        <select name="friendlinkctgId" id="friendlinkctgId">
                            <option value="1" <#if link?? && link.friendlinkctgId=="1">selected </#if>>文字链接</option>
                            <option value="2" <#if link?? && link.friendlinkctgId=="2">selected</#if>>图片链接</option>
                        </select></div>
                    </td>
                    <td>站长邮箱</td>
                    <td>
                        <input name="email" id="email" type="text" value="${link.email!}" class="layui-input" style="width:300px">
                    </td>
                </tr>
                <tr>
                    <td>网站LOGO</td>
                    <td>
                        <input type="file" name="FILE01" id="FILE01" style="width:200px;display: inline-block;" class="layui-input">
                        <#if link.logoPath?exists>
                            <img id="imgHeadPhoto1" src="${link.logoPath!}" style="height: 80px;" alt="">
                        <#else>
                            <img id="imgHeadPhoto1" src="${ctx}/images/default.jpg" style="height: 80px;" alt=""/>
                        </#if>
                        <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200K（jpg、png、bmp）</span>
                    </td>
                    <td>
                        <div id="imgDiv1">
                            <div id="divPreview1">

                            </div>
                        </div>
                    </td>
                    <td></td>
                </tr>

                <tr>
                    <td>网站简介</td>
                    <td colspan="3">
                        <textarea name="description" id="description" rows="3"  class="layui-textarea">${link.description!}</textarea>
                    </td>
                </tr>
                <tr>
                    <td width="90">网站排序</td>
                    <td>
                        <input type="text" name="priority" id="priority" value="${link.priority!}" class="layui-input" style="width:300px">
                        <span class="color_r2">*</span>
                    </td>
                    <td width="90"></td>
                    <td>
                        <input type="hidden" name="views" id="views" value="${link.views!}">
                        <span class="color_r2"></span>
                    </td>
                </tr>
                <tr>
                    <td>显示</td>
                    <td colspan="3">
                        <label>
                            <input type="radio" name="isEnabled" id="isEnabled" value="1"
                                   <#if link?? && link.isEnabled=="1">checked</#if> title="是">
                            <input type="radio" name="isEnabled" id="isEnabled" value="0"
                                   <#if link?? && link.isEnabled=="0">checked</#if> title="否">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button" name="commitBtn" id="submit" value="提交" class="layui-btn">
                        <input type="button" name="reset" id="reset" value="返回" class="layui-btn layui-btn-primary">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script data-main="${ctx}/js/abc/cms/friendlink" src="${ctx}/js/require.js"></script>
</body>
</html>
