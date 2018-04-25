<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<style type="text/css">
    .tip_ico{
        display: inline-block;
        vertical-align: bottom;
        margin-left: 4px;
        cursor: pointer;
        background-image: url(${ctx}/images/sprites_ico.png?v=1.9.100);
        background-position: -242px -660px;
        width: 16px;
        height: 16px;
    }
</style>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">

<div class="container-fluid m_top_30 nycol_list_edit">
      <form class="layui-form">
            <div class="layui-form-top">
                <div class="layui-form-item">
                    <#if feedbackTypes?? && (feedbackTypes?size > 0) >
                        <div class="layui-inline">
                            <label class="layui-form-label">反馈类型</label>
                            <div class="layui-input-inline">
                                <select name="feedbackType">
                                    <option value=""></option>
                                    <#list feedbackTypes as feedbackType>
                                        <option value="${feedbackType.fieldValue}" <#if RequestParameters["feedbackType"]?? && RequestParameters["feedbackType"] == feedbackType.fieldValue>selected</#if>>${feedbackType.fieldKey}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </#if>
                    <#if sourceTypes?? && (sourceTypes?size > 0) >
                        <div class="layui-inline">
                            <label class="layui-form-label">来源途径</label>
                            <div class="layui-input-inline">
                                <select name="sourceType" >
                                    <option value=""></option>
                                    <#list sourceTypes as sourceType>
                                        <option value="${sourceType.code}" <#if RequestParameters["sourceType"]?? && RequestParameters["sourceType"] == sourceType.code>selected</#if>>${sourceType.description}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </#if>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline">
                                <select name="isReply" >
                                    <option value=""></option>
                                    <option value="true" <#if RequestParameters["isReply"]?? && (RequestParameters["isReply"]=="true")>selected</#if>>已回复</option>
                                    <option value="false" <#if RequestParameters["isReply"]?? && (RequestParameters["isReply"]=="false")>selected</#if>>未回复</option>
                                </select>
                            </div>
                        </div>
                    <div class="layui-inline">
                            <button class="layui-btn js-query">查询</button>
                    </div>
                </div>
            </div>
        </form>
        <form class="layui-form">
            <div class="nycon_list_b">
                <table class="layui-table" lay-size="sm">
                    <thead class="pn-lthead">
                    <tr>
                        <th width="28" style="text-align: center">全选</th>
                        <th width="28" style="text-align: center">序号</th>
                        <th style="text-align: center">来源途径</th>
                        <th style="text-align: center">反馈人</th>
                        <th style="text-align: center">反馈类型</th>
                        <th style="text-align: center" width="30%">意见描述</th>
                        <th style="text-align: center">反馈时间</th>
                        <th style="text-align: center">联系电话</th>
                        <th style="text-align: center">操作</th>
                    </tr>
                    </thead>
                    <tbody class="js-body-tr pn-ltbody">
                    <#if feedbacks?? && (feedbacks?size > 0) >
                        <#list feedbacks as feedback>
                            <tr>
                                <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${feedback.id}"></td>
                                <td class="td_i">${pagerSpec.offset + feedback_index + 1}</td>
                                <td align="center">
                                    <#list sourceTypes as sourceType>
                                        <#if sourceType.code == feedback.sourceType>${sourceType.description}</#if>
                                    </#list>
                                </td>
                                <td align="center">
                                    <a href="javascript:;" class="js_open_userInfo ljc_00bcd4" data-url="${ctx}/consumerManager/consumer/info.php?id=${feedback.feedbackUserId}">${(feedback.feedbackUserName)!}</a>
                                </td>
                                <td align="center">
                                    <#if feedbackTypes??>
                                        <#list feedbackTypes as feedbackType>
                                            <#if feedback.feedbackType == feedbackType.fieldValue>
                                            ${feedbackType.fieldKey}
                                            </#if>
                                        </#list>
                                    </#if>
                                </td>
                                <td align="center" class="js_opinionDesc">${(feedback.opinionDesc)!}</td>
                                <td align="center" class="js_feedbackTime">${feedback.feedbackTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td align="center">${(feedback.contactNumber)!}</td>
                                <td align="center">
                                    <a data-href="${ctx}/cms/feedback/delete/${feedback.id}.php" class="js_delete pn-opt">删除</a>
                                    <#if !feedback.replyMsg??>
                                        | <a data-id="${(feedback.id)!}" data-feedbackUserId="${(feedback.feedbackUserId)!}" class="js_answer pn-opt">回复</a>
                                    <#else>
                                        <a title="回复信息" data-container="body" data-toggle="popover" data-placement="left" data-content="${feedback.replyMsg}">
                                            <i class="tip_ico"></i>
                                        </a>
                                    </#if>

                                </td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
                <div class="button_caozuo_fenye">
                    <div class="layui-btn js_selectAll" data-check=false>全选</div>
                    <#if feedbacks?? && (feedbacks?size > 0) >
                        <div  data-href="${ctx}/cms/feedback/delete.php" class="js_del_batch_btn layui-btn layui-btn-danger" >批量删除</div>
                    </#if>
                </div>
                <table width="100%" class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
            </div>
        </form>
</div>
<div class="main_modal " id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/cms/feedback/list.js?v=v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>