<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8"/>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">

    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form id="linkForm" class="layui-form">
        <div class="nycon_list_b">
        <#if obj??>
            <input type="hidden" name="id" id="id" value="${obj.id!}">
        </#if>

            <table class="layui-table" lay-size="sm">
                <tr>
                    <td width="130px">奖品名称：</td>
                    <td colspan="3">
                        <input type="text" name="name" id="name" value="${(obj.name)!}" maxlength="20" dataEvent="nonull" class="layui-input" style="width: 300px"><span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="130px">描述：</td>
                    <td colspan="3">
                        <textarea name="description" id="description" class="layui-textarea">${(obj.description)!}</textarea>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td>图片：</td>
                    <td>
                        <input type="file" name="FILE01" id="FILE01" style="width:200px;display: inline-block;">
                    <#if obj?? && obj.image!="">
                        <img id="imgHeadPhoto1" src="${(obj.image)!}" style="height: 80px;" alt="">
                    <#else>
                        <img id="imgHeadPhoto1" src="${ctx}/images/default.jpg" style="height: 80px;" alt=""/>
                    </#if>
                        <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200K（jpg、png、bmp）</span><span class="color_r2">*</span>
                    </td>
                    <td colspan="2">
                        <div id="imgDiv1">
                            <div id="divPreview1">

                            </div>
                        </div>
                    </td>
                </tr>
                </tr>
                <tr>
                    <td width="130px">成本价：</td>
                    <td colspan="3">
                        <input type="text" name="cost" id="cost" maxlength="10" class="layui-input" style="width: 300px" value="${(obj.cost)!}" onkeyup="value=this.value.replace(/\D+/g,'')"><span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="130px">奖品类别：</td>
                    <td colspan="3">
                        <div style="width: 300px; float: left">
                        <select name="types" id="types" required="required">
                            <option value="">请选择</option>
                        <#if lotteryTypes?? && (lotteryTypes?size>0)>
                        <#list lotteryTypes as typeObj>
                            <option  value="${(typeObj.fieldKey)!}" <#if obj??&&obj.types??&&typeObj.fieldKey??&&obj.types==typeObj.fieldKey>selected</#if> > ${(typeObj.fieldKey)!}</option>
                        </#list>
                        </#if>
                        </select>
        </div><span class="color_r2">*</span>

                    </td>
                </tr

                <tr>
                    <td width="130px">所属活动：</td>
                    <td colspan="3"><div style="width: 300px; float: left">
                        <select name="activityId" id="activityId" required="required">
                            <option value="">请选择</option>
                        <#if lotteryActivityRs?? && (lotteryActivityRs?size>0)>
                        <#list lotteryActivityRs as act>
                            <option  value="${(act.id)!}"> ${(act.name)!}</option>
                        </#list>
                        </#if>
                        </select></div><span class="color_r2">*</span>
                    </td>
                </tr

                <tr>
                    <td width="130px">是否中奖：</td>
                    <td colspan="3">
                        <label><input type="radio" name="noluck" id="noluck" checked value="true" title="是"></label>
                        <label><input type="radio" name="noluck" id="noluck" <#if obj?? && !obj.noluck>checked</#if>
                                      value="false" title="否"></label>
                    </td>
                </tr>
                <tr>
                    <td width="130px">是否邮寄：</td>
                    <td colspan="3">
                        <label><input type="radio" name="send" id="send" checked value="true" title="是"></label>
                        <label><input type="radio" name="send" id="send1" <#if obj?? && !obj.send>checked</#if>
                                      value="false" title="否"></label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="3">
                        <input type="button" name="submit" id="submit" value="提交" class="layui-btn">
                        <input type="button" name="back" id="back" value="返回" class="layui-btn layui-btn-primary">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/Lottery.js" src="${ctx}/js/require.js"></script>
</body>
</html>
