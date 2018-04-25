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
    <style>
         input[type="text"] {
            width: 350px;
        }
         select{
             width: 350px;
         }
         textarea
         {
             width: 350px;
             height:100px;
             font-size: 12px;
         }
        .td111{
            width: 140px;
            text-align: right;
        }
         <#if chakan??>
            #btntr{
                  display: none;

              }
         input[type="text"] {
             border: none;
             readonly:readonly;
         }
         </#if>
    </style>
</head>

<body>
<div class="container-fluid m_top_30 nycon_list">
    <form id="linkForm" class="layui-form">
        <div class="nycon_list_b">
        <#if obj??>
            <input  type="hidden" name="id" id="id" value="${obj.id!}">

        </#if>
            <table class="layui-table" lay-size="sm">

                <tr>
                    <td   class="td111">活动名称：</td>
                    <td colspan="3">
                        <input type="text" name="name" id="name" value="${(obj.name)!}" maxlength="20"dataEvent="nonull" class="layui-input">
                        <label title="名称不能为空"  class="color_r2">*</label>
                    </td>
                </tr>
                <tr>
                    <td   class="td111">模版名称：</td>
                    <td colspan="3">
                        <div style="width:350px; float: left">
                        <select name="templateId" required="required">
                            <option value="">请选择</option>
                            <#if lotteryTemplateRs??>
                        <#list lotteryTemplateRs as lotteryTemplate>
                            <option value="${(lotteryTemplate.templateId)!}"
                                    <#if obj??&&obj.templateId??&&lotteryTemplate.templateId??&&obj.templateId==lotteryTemplate.templateId>selected</#if>> ${(lotteryTemplate.templateName)!}</option>
                        </#list>
                            </#if>
                        </select></div>
                        <span class="color_r2">*</span>
                    </td>
                </tr>

                <tr>
                    <td    class="td111">描述：</td>
                    <td colspan="3">
                        <textarea name="description" id="description" class="layui-textarea">${(obj.description)!}</textarea>

                    </td>
                </tr>


                <tr>
                    <td   class="td111">用户等级：</td>
                    <td colspan="3">
                        <div style="width:350px; float: left">
                        <select name="userLevel" required="required">
                            <option value="">请选择</option>
                        <#if userLevelRs??>
                            <#list userLevelRs as userLevel>
                                <option value="${(userLevel)!}"
                                        <#if obj??&&obj.userLevel?? &&obj.userLevel==userLevel>selected</#if>> LV${(userLevel)!}</option>
                            </#list>
                        </#if>
                        </select></div>
                        <span class="color_r2">*</span>
                        <span>参与活动用户最低等级限制</span>
                    </td>
                </tr>
                <tr>
                    <td   class="td111">每天免费次数：</td>
                    <td colspan="3">
                        <input type="text" name="userFreeCount" id="userFreeCount" value="${(obj.userFreeCount)!}" onkeyup="value=this.value.replace(/\D+/g,'')" class="layui-input">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td    class="td111">领奖有效天数：</td>
                    <td colspan="3">
                        <input type="text" name="getlotteyDay" id="getlotteyDay" value="${(obj.getlotteyDay)!}" onkeyup="value=this.value.replace(/\D+/g,'')" class="layui-input">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <#--<tr>-->
                    <#--<td   class="td111">用户中奖次数限制：</td>-->
                    <#--<td colspan="3">-->
                        <#--<input type="text" name="userLotteryMax" id="userLotteryMax" value="${(obj.userLotteryMax)!}"onkeyup="value=this.value.replace(/\D+/g,'')">-->

                    <#--</td>-->

                <#--</tr>-->
                <tr>
                    <td   class="td111">用户每天中奖次数：</td>
                    <td colspan="3">
                        <input type="text" name="userLotteryMaxDay" id="userLotteryMaxDay" value="${(obj.userLotteryMaxDay)!}"onkeyup="value=this.value.replace(/\D+/g,'')" class="layui-input">
                        <span class="color_r2">*</span>
                    </td>

                </tr>
                <tr>
                    <td width="100px" class="td111">每天派奖总数：</td>
                    <td colspan="3">
                        <input type="text" name="timeStock" id="timeStock" value="${(obj.timeStock)!}" onkeyup="value=this.value.replace(/\D+/g,'')" class="layui-input">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="100px" class="td111">当天已派奖数：</td>
                    <td colspan="3">
                        <input type="text" readonly  name="timeCount"  value="${(obj.timeCount)!}" class="layui-input">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td   class="td111">开始时间：</td>
                    <td colspan="3">
                        <input type="text" id="startTime" data-type="datebox" name="startTime"
                               value="${(obj.startTime?string("yyyy-MM-dd"))!}" class="layui-input"><span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td   class="td111">结束时间：</td>
                    <td colspan="3">
                        <input type="text" id="endTime" data-type="datebox" name="endTime"
                               value="${(obj.endTime?string("yyyy-MM-dd"))!}" class="layui-input">
                        <span class="color_r2">*</span>
                    </td>
                </tr>
                <tr>
                    <td   class="td111">状态：</td>
                    <td colspan="3">
                        <input type="radio" name="status" id="status" checked value="true" title="启用">
                        <input type="radio" name="status" id="status" <#if obj?? && !obj.status>checked</#if>
                                      value="false" title="停用">
                    </td>
                </tr>
                <tr id="btntr">
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

<script data-main="${ctx}/js/abc/consumer/LotteryActivity.js" src="${ctx}/js/require.js"></script>
</body>
</html>
