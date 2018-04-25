<!doctype html>
<html>
<head>
    <title>CHABC运营管理系统</title>
    <script type="text/javascript"> var imgUrl="${imgUrl!}"; <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/input.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">

    <style type="text/css">
        .ztree{border: none}
    </style>
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink!}">
<div class="container-fluid m_top_30 nycol_list_edit">
    <div class="panel panel-default">
        <div class="">
            <form id="subForm">
                <input type="hidden" value="${(know.id)!}" name="id" id="id"/>
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <td style="width:15%" class="text-center"><span class="color_r2">*</span><label>选择分类：</label></td>
                        <td>
                            <div class="select" style="width: 30%;">
                               <input value="${(know.categoryName?html)!""}" id="selectKnowCateInp" class="form-control" placeholder="选择知识分类" readonly/>
                               <input id="cateId" name="categoryCode" type="hidden" value="${(know.categoryCode)!''}"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label>知识类别：</label></td>
                        <td>
                            <div class="checkbox">
                                <label>
                                    <input type="radio" id="QACheckbox" <#if (know.type)??><#if know.type=="QA">checked</#if><#else>checked</#if>  name="type" value="QA">问答
                                </label>
                                <label>
                                    <input type="radio" id="KCheckbox" <#if (know.type)?? && know.type=="K">checked</#if>  name="type" value="K">知识
                                </label>
                            </div>
                        </td>
                    </tr>

                    <tr id="QA_recommend_tr"  <#if (know.type)?? && know.type=="K">style="display: none;"</#if>  >
                        <td class="text-center"><label>问答推荐：</label></td>
                        <td>
                            <div class="checkbox" id="QA_recommend">
                                <label>
                                    <input type="radio" <#if (know.recommend)??><#if know.recommend=="common">checked</#if><#else>checked</#if> name="recommend" value="common">普通问题
                                </label>
                                <label>
                                    <input type="radio" name="recommend" <#if (know.recommend)?? && know.recommend=="hot" && know.type=="QA">checked</#if> value="hot">推荐问题
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr id="K_recommend_tr" <#if !(know.type)?? || know.type=="QA">style="display: none;"</#if>>
                        <td class="text-center"><label>知识推荐：</label></td>
                        <td>
                            <div class="checkbox" id="K_recommend">
                                <label>
                                    <input type="radio" name="recommend" <#if (know.recommend)?? && know.recommend=="top">checked</#if> value="top">普通知识
                                </label>
                                <label>
                                    <input type="radio" name="recommend" <#if (know.recommend)?? && know.recommend=="hot" && know.type=="K">checked</#if> value="hot">推荐知识
                                </label>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="text-center"><span class="color_r2">*</span><label id="bzwf">
                            <#if (know.type)?? && know.type=="K">
                                知识标题:
                            <#else>
                                标准问法：
                            </#if>
                        </label></td>
                        <td>
                            <div class="form-group"  style="width: 50%;">
                                <input type="text" name="subject" value="${(know.subject)!}" class="form-control">
                                <div id="subject_bz" style="position: absolute; z-index: 1000; background-color: #ffffff; display: none;border: 1px solid #999999; padding-left: 12px; padding-right: 12px;">
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><span class="color_r2">*</span><label>相关标签：</label></td>
                        <td>
                            <div class="btn-group" role="group" style="float: left;" id="labelDiv" >
                                <#if tags??&&(tags?size>0)>
                                    <#list tags as t>
                                        <#if t??>
                                            <span name='labelSpan' onclick='$(this).remove();'>
                                                <input name='tagIds' type='hidden' value='${t.id!""}'/>
                                                <button type='button' class='btn btn-default'>${t.name!""}<i class='glyphicon glyphicon-remove'></i></button>
                                            </span>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                            <div style="float:left; padding-left: 20px;">
                                <button onkeydown="return false" data-type="xthf_knowledge" class="btn btn-default js_add_tag">＋</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><span class="color_r2">*</span><label id="bzda">
                            <#if (know.type)?? && know.type=="K">
                                知识内容:
                            <#else>
                                标准答案：
                            </#if>
                        </label></td>
                        <td>
                            <#--<div id="_standard_answer_area">${(know.content)!}</div>-->
                                <script id="_standard_answer_area_baidu" name="txt" data-labelname="内容" type="text/plain" abc-type="ueditArea" abc-custom="0">
                                    ${(know.content)!""}
                                </script>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center">
                            <input type="button" id="add_standard_answer" class="layui-btn layui-btn-primary" style=" font-size:16px;font-weight:700" value="
                            <#if (know.type)?? && know.type=="K">
                                    添加关联知识
                                <#else>
                                    添加关联问题
                                </#if>
                        "/>
                        </td>
                        <td style="vertical-align: middle;">
                            <div id="refKnowledgeIdsDIV">
                                <#if refKnows??>
                                    <#list refKnows as conKnow>
                                        <input type='hidden' name='refKnowledgeIds' value='${conKnow.id}' val-subject='${conKnow.subject}'/>
                                    </#list>
                                </#if>
                            </div>
                            <p id="hasKnow_p"><span id="hastext">
                                <#if (know.type)?? && know.type=="K">
                                    已关联知识
                                <#else>
                                    已关联问题
                                </#if>
                            </span><b id="refKnowledgeIds_p">
                                <#if refKnows??>${refKnows?size}<#else >0</#if>
                            </b>个</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label>生效状态：</label></td>
                        <td>
                            <div class="checkbox">
                                <label>
                                    <input type="radio" name="status" <#if (know.status)??><#if know.status>checked</#if><#else>checked</#if> value="true">启用
                                </label>
                                <label>
                                    <input type="radio" name="status" <#if (know.status)??><#if !know.status>checked</#if></#if> value="false">停用
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label>生效时间：</label></td>
                        <td>
                            <div class="checkbox">
                                <input name="activeTime" value="" type="hidden"/>
                                <label>
                                    <input type="radio" <#if (know.activeTime)??><#else>checked</#if> name="activeTime_temp" value="" val="-1">永久
                                </label>
                                <label>
                                    <input type="radio" <#if (know.activeTime)??>checked</#if> name="activeTime_temp" value="" val="0">自定义
                                </label>
                                    <label id="activeTimeRadio_3" <#if (know.activeTime)??><#else>hidden="hidden"</#if>>
                                    截止时间：<input type="text" value="<#if (know.activeTime)??>${((know.activeTime)?string("yyyy-MM-dd HH:mm:ss"))!}</#if>" data-options="editable:false" data-target="#startMsg" data-type="datetimebox" name="activeTime_date" style=" width:150px;" >
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><span class="color_r2">*</span><label>采集来源：</label></td>
                        <td>
                            <div class="form-group"  style="width: 40%;">
                                <#--<input type="text" name="source" value="${(know.source)!}" class="form-control">-->
                                    <div id="search-form" input-value="${(know.source)!}"></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center"><label>查询限制：</label></td>
                        <td>
                            <div class="checkbox">
                                <label>
                                    <input type="radio" name="isOpen" <#if (know.isOpen)?? && !(know.isOpen)>checked</#if> value="false">仅内部查询
                                </label>
                                <label>
                                    <input type="radio" name="isOpen" <#if !(know.isOpen)?? || know.isOpen>checked</#if> value="true">可对外开放
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center">
                            <label>附件：</label>
                        </td>
                        <td>
                            <table name="attachementTable" cellspacing="10px" cellpadding="3px">
                                <button name="addAttachementInput" type="button">增加附件输入栏</button>上传格式支持：.rar;.zip;最大为3M
                                <br>
                                <#if know?? && know.attachmentList?? && know.attachmentList?size gt 0>
                                    <#list know.attachmentList as afile>
                                        <tr name="attachementInput">
                                            <td style="padding:5px;">文件名称：<input type="text" name="fileName" value="${afile.fileName!}" data-rule="required;"></td>
                                            <td style="padding:5px;" hidden>文件路径：<input type="text" name="filePath" value="${afile.filePath!}" readonly></td>
                                            <td style="padding:5px;"><input type="file" id="uploadFile${afile_index!}" name="uploadFile" data-type="rar;zip;"></td>
                                            <td style="padding:5px;"><button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button></td>
                                            <td style="padding:5px;"><button name="_attachement_delete_btn" type="button" class="layui-btn">删除</button></td>
                                            <td style="padding:5px;"><a href="${imgUrl}${afile.filePath!}">${afile.filePath!}</a></td>
                                        </tr>
                                    </#list>
                                <#else>
                                </#if>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="checkbox" style="text-align: center;">
                                <label>
                                    <input type="button" id="subBtn" class="layui-btn btn-default" value="保存"/>
                                </label>
                                <label>
                                    <a href="${currLink!}" class="btn btn-default js_back">返回</a>
                                </label>
                            </div>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>


<table hidden>
    <tr id="attachementInputTpl" hidden>
        <td style="padding:5px;">文件名称：<input type="text" name="fileName" data-rule="required;"></td>
        <td style="padding:5px;" hidden>文件路径：<input type="text" name="filePath" readonly></td>
        <td style="padding:5px;"><input type="file" id="" name="uploadFile" data-type="rar;zip;"></td>
        <td style="padding:5px;"><button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button></td>
        <td style="padding:5px;"><button name="_attachement_delete_btn" type="button" class="layui-btn">删除</button></td>
        <td style="padding:5px;"><a href="javascript:;"></a></td>
        <td><label class="uploadMsg" style="color:red"></label></td>
    </tr>
</table>

</body>
<script data-main="${ctx}/js/abc/cms/knowledge/add.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</html>