<#macro model_tmpl_macro channelItem>
    <#if channelItem.isDisplay==1>
        <#if contentDto??>
            <#if channelItem.isCustom==0 && ((contentDto.content[channelItem.field])?? || (contentDto.contentExt[channelItem.field])?? ||(contentDto.contentTxt[channelItem.field])??)>
                <#assign  fieldVal="">
                <#if channelItem.field=="releaseDate">
                    <#assign fieldVal=(contentDto.contentExt[channelItem.field])?string("yyyy-MM-dd HH:mm")>
                <#else>
                    <#assign fieldVal=(contentDto.content[channelItem.field])???string((contentDto.content[channelItem.field])!,((contentDto.contentExt[channelItem.field])???string((contentDto.contentExt[channelItem.field])!,contentDto.contentTxt[channelItem.field]!))!)/>
               </#if>

            <#else>
                <#if contentDto.contentAttrList??>
                    <#list contentDto.contentAttrList as contentAttr>
                        <#if contentAttr.attrName==channelItem.field>
                            <#assign fieldVal=contentAttr.attrValue>
                            <#break>
                        </#if>
                        <#if contentAttr_index==contentDto.contentAttrList?size-1>
                            <#assign fieldVal="">
                        </#if>
                    </#list>
                    <#if !(contentDto.contentAttrList??) ||contentDto.contentAttrList?size==0 >
                        <#assign fieldVal="">
                    </#if>
                <#else>
                    <#assign fieldVal="">
                </#if>
            </#if>
        <#else>
            <#assign fieldVal="">
        </#if>

        <#assign checkFlag=false>
        <#if channelItem.field=='contentType'>
            <#if (contentDto.tagList??) && (contentDto.tagList)?size !=0>
                <#assign tagList=contentDto.tagList>
            </#if>
        <tr>
            <td width="150" style="vertical-align:middle;">${channelItem.itemLabel!}：</td>
            <td>
                <#--<div style="width: 330px; float: left">-->
                <#--<select id="contentType" name="${channelItem.itemLabel!}" data-width="250px"-->
                        <#--data-none-selected-text="请选择标签" class="selectpicker" data-live-search="true" multiple-->
                        <#--data-multiple-separator=";">-->
                    <#--<#list contentLabelList as contentLabel>-->
                        <#--<#assign checkFlag=false>-->
                        <#--<#if tagList??>-->
                            <#--<#list tagList as opt>-->
                                <#--<#if (contentLabel.id!) == opt.tagId>-->
                                    <#--<#assign checkFlag=true>-->
                                    <#--<#break >-->
                                <#--</#if>-->
                            <#--</#list>-->
                        <#--</#if>-->
                        <#--<option <#if checkFlag>selected="selected"</#if> value="${contentLabel.id!}">${contentLabel.name!}</option>-->
                    <#--</#list>-->
                <#--</select></div>-->

                <div class="btn-group" role="group" style="float: left;" id="labelDiv" >
                <#if contentLabelList??&&(contentLabelList?size>0)>
                    <#list contentLabelList as t>
                        <#if t??>
                            <#if tagList??>
                                <#list tagList as opt>
                                    <#if (t.id!) == opt.tagId>
                                        <span name='labelSpan' onclick='$(this).remove();'>
                                                <input name='tagIds' type='hidden' value='${t.id!""}'/>
                                                <button type='button' class='btn btn-default'>${t.name!""}<i class='glyphicon glyphicon-remove'></i></button>
                                            </span>
                                    </#if>
                                </#list>
                            </#if>
                        </#if>
                    </#list>
                </#if>
            </div>
                <div style="float:left; padding-left: 20px;">
                    <button onkeydown="return false" data-type="xthf_csw" class="btn btn-default js_add_tag">＋</button>
                </div>

                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='siteId'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><div style="width: 330px; float: left" class="layui-form">
                <select style="width: 250px;" name="${channelItem.field}" id="siteId" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#if showFirstNodeOfSite>
                        <option value="">请选择</option>
                    </#if>
                    <#list siteList as site>
                        <#if (site.siteStatus!)=="1">
                        <option <#if (site.siteId!)==(fieldVal!)>selected="selected"</#if> data-sitepath="${site.sitePath!}" data-domain="${site.domain!}" value="${(site.siteId)!}">${(site.siteName)!}</option>
                        </#if>
                    </#list>
                </select></div>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='channelId'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <input type="hidden" abc-custom="${channelItem.isCustom!}" name="${channelItem.field!}"
                       value="${((fieldVal!)!="")?string((fieldVal!),(channelId!))}" style="width:250px;margin-right:10px;" class="layui-btn">
                <input class="layui-input" type="text" style="width:250px;margin-right:10px;" readonly="readonly" value="${channelName!}" name="text_content_${channelItem.field!}"
                       placeholder="请选择栏目" <#if (channelItem.isRequired!)==1>data-rule="required"</#if>>
                <button style="height:26px;line-height:13px;" name="_channel_btn" <#if (contentId!)!="">disabled="disabled"</#if> type="button" class="layui-btn">
                    选择
                </button>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>

        <#elseif channelItem.field=='typeId'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><div style="width: 330px; float: left" class="layui-form">
                <select style="width: 250px;" name="${channelItem.field}" abc-custom="${channelItem.isCustom}"
                        data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#list contentTypeList as contentType>
                        <option <#if (fieldVal!)==(contentType.fieldValue!)>selected="selected"</#if> value="${contentType.fieldValue!}">${contentType.fieldKey!}</option>
                    </#list>
                </select></div>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>

        <#elseif channelItem.field=='tplContent'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><div style="width: 330px; float: left" class="layui-form">
                <select style="width: 250px;"  name="${channelItem.field}" abc-custom="${channelItem.isCustom}"
                        data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#if templateList??>
                        <option value="">请选择</option>
                        <#list templateList as templateItem>
                            <option <#if (templateItem.templatePath!)==(fieldVal!)>selected="selected"</#if> value="${templateItem.templatePath!}">${templateItem.templateName!}</option>
                        </#list>
                    <#else>
                        <option value="">请先选择所属站点</option>
                    </#if>
                </select></div>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>

        <#elseif channelItem.field=='viewGroupIds'>
        <#assign fieldVal=contentDto.groupList>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <#list (channelItem.optValue!)?split(";") as opt>
                    <#assign subVal=opt?split(":")>
                    <#list fieldVal as groupDto>
                        <#if groupDto.groupId == subVal[0]>
                            <#assign selectFlag=true>
                            <#break >
                        </#if>
                        <#if groupDto_index == (fieldVal?size-1)>
                            <#assign selectFlag=false>
                        </#if>
                    </#list>
                    <input class="np" name="${channelItem.field}" value="${subVal[0]!}"
                           data-rule="${(channelItem.isRequired==1)?string("checked","")}"
                           <#if selectFlag??&&selectFlag >checked="checked"</#if> type="checkbox" lay-skin="primary"  abc-custom="${channelItem.isCustom!}">&nbsp;${subVal[1]!}
                    &nbsp;&nbsp;
                </#list>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='description'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <textarea  name="${channelItem.field}" style="width:100%;height:100px;"
                           data-rule="${(channelItem.isRequired==1)?string("checked","")}"
                           abc-custom="${channelItem.isCustom!}" maxlength="85" onchange="this.value=this.value.substring(0, 85)"
                           onkeydown="this.value=this.value.substring(0, 85)" onkeyup="this.value=this.value.substring(0, 85)">${fieldVal!}</textarea>

            </td>
        </tr>
        <#elseif channelItem.field=='shortTitle'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <textarea  name="${channelItem.field}" style="width:100%;height:100px;"
                           data-rule="${(channelItem.isRequired==1)?string("checked","")}"
                           abc-custom="${channelItem.isCustom!}" maxlength="330" onchange="this.value=this.value.substring(0, 330)"
                           onkeydown="this.value=this.value.substring(0, 330)" onkeyup="this.value=this.value.substring(0, 330)">${fieldVal!}</textarea>

            </td>
        </tr>
        <#elseif channelItem.field=='title'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><input class="layui-input" style="width:600px;" name="${channelItem.field}" style="width: 250px;" value="${fieldVal!}" abc-custom="${channelItem.isCustom}"
                       maxlength="200" type="text"
                       data-rule="${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
            <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
        </tr>

        <#elseif channelItem.dataType=="1"><#-- 文本输入框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <input class="layui-input" name="${channelItem.field}" style="width: 250px;" value="${fieldVal!}" abc-custom="${channelItem.isCustom}"
            <#if channelItem.field=="author"||channelItem.field=="origin">
                       maxlength="30"
            <#elseif channelItem.field=="link">
                       maxlength="100"
            <#else>
                       maxlength="${(channelItem.textSize)!}"
            </#if>
                       type="text" data-rule="${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="2"><#-- 整型文本框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><input class="layui-input" style="width: 250px;" name="${channelItem.field}" value="${fieldVal!}" type="number" abc-custom="${channelItem.isCustom}"
                       maxlength="${(channelItem.textSize)!}"
                       data-rule="integer(+0);${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
        </tr>
        <#elseif channelItem.dataType=="3"><#-- 浮点文本框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><input class="layui-input" style="width: 250px;" name="${channelItem.field}" value="${fieldVal!}" type="number" abc-custom="${channelItem.isCustom}"
                       maxlength="${(channelItem.textSize)!}"
                       data-rule="numeric;${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
        </tr>
        <#elseif channelItem.dataType=="4"><#-- 区域框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：<#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
            <td>
                <#if channelItem.itemLabel!="内容">
                <div id="${channelItem.modelitemId!}" style="width: 100%;" abc-type="textarea"
                     abc-custom="${channelItem.isCustom}" data-labelname="${channelItem.itemLabel!}"
                     <#if (channelItem.isRequired!)==1>data-rule="required"</#if>
                     name="${channelItem.field}"><span>${fieldVal!}</span></div>
                <div class="clearfix"></div>
                <#else>
                    <script id="${channelItem.modelitemId!}" name="${channelItem.field}" data-labelname="${channelItem.itemLabel!}" type="text/plain" abc-type="ueditArea" abc-custom="${channelItem.isCustom}">
                           ${fieldVal!}
                    </script>

                    <div class="clearfix"></div>
                </#if>

            </td>
        </tr>
        <#elseif channelItem.dataType=="5"><#-- 日期 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
            <input id="${channelItem.field}" name="${channelItem.field}" style="width:250px;" abc-type="datebox"
                   abc-custom="${channelItem.isCustom}" value="${fieldVal!}" data-rule="${(channelItem.isRequired==1)?string("required","")}"
                   <#if (channelItem.isRequired!)==1>data-rule="required"</#if>><#if (channelItem.isRequired!)==1>
            <span class="color_r2">*</span></#if>
            <#--<input class="easyui-datetimebox" name="${channelItem.field}" abc-custom="${channelItem.isCustom}" value="${fieldVal!}"-->
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                       <#--data-options="sharedCalendar:'#cc'">-->
            </td>
        </tr>
        <#elseif channelItem.dataType=="6"><#-- 下拉列表 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><div style="width: 330px; float: left" class="layui-form">
                <select style="width: 250px;" name="${channelItem.field}" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <option value="">请选择</option>
                    <#if (channelItem.optValue!)!="">
                    <#list (channelItem.optValue!"")?split(";") as opt>
                        <#assign subVal=opt?split(":")>

                        <#list fieldVal?split(";") as fieldNode>
                            <#if fieldNode == subVal[0]>
                                <#assign selectFlag=true>
                                <#break >
                            </#if>
                            <#if fieldNode_index == (fieldVal?split(";")?size-1)>
                                <#assign selectFlag=false>
                            </#if>
                        </#list>
                        <option <#if selectFlag>selected="selected"</#if> value="${(subVal[0])!}">${(subVal[1])!}</option>
                    </#list>
                    </#if>
                </select></div>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="7"><#-- 复选框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <#list (channelItem.optValue!)?split(";") as opt>
                    <#assign subVal=opt?split(":")>
                    <#list fieldVal?split(";") as fieldNode>
                        <#if fieldNode == subVal[0]>
                            <#assign checkFlag=true>
                            <#break >
                        </#if>
                        <#if fieldNode_index == (fieldVal?split(";")?size-1)>
                            <#assign checkFlag=false>
                        </#if>
                    </#list>
                    <input class="np" name="${channelItem.field}" value="${subVal[0]!}"
                           data-rule="${(channelItem.isRequired==1)?string("checked","")}"
                           <#if checkFlag >checked=checked</#if> type="checkbox" lay-skin="primary"  abc-custom="${channelItem.isCustom}">&nbsp;${subVal[1]!}
                    &nbsp;&nbsp;
                </#list>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="8"><#-- 单选框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td class="layui-form">
                <#list (channelItem.optValue!"")?split(";") as opt>
                    <#assign subVal=opt?split(":")>
                    <#list fieldVal?split(";") as fieldNode>
                        <#if fieldNode == subVal[0]>
                            <#assign checkFlag=true>
                            <#break >
                        </#if>
                        <#if fieldNode_index == (fieldVal?split(";")?size-1)>
                            <#assign checkFlag=false>
                        </#if>
                    </#list>
                    <input name="${channelItem.field}" class="np" value="${(subVal[0])!}"
                           <#if checkFlag || ((fieldVal!)==""&&opt_index==0)>checked=checked</#if> type="radio" title="${(subVal[1])!}"
                           abc-custom="${channelItem.isCustom}">
                </#list>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="9"><#-- 附件 -->
        <tr>
            <td width="150"><span style="vertical-align:middle;">${channelItem.itemLabel}：</span></td>
            <td>
                <input type="hidden" readonly value="" name="${channelItem.field}_name" style="width:250px;">
                <input type="text" readonly value="${fieldVal!}" name="${channelItem.field}_path" data-rule="${(channelItem.isRequired==1)?string("required","")}" placeholder="请先上传文件" style="width:250px;">
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
                <input id="file_${channelItem.modelitemId}" name="file_${channelItem.field}" data-field="${channelItem.field}" data-type="file" class="layui-input" style="width: 250px;display:inline-block;margin-right:10px;" value="" abc-custom="${channelItem.isCustom}"
                       type="file"><button style="height:26px;line-height:13px;" name="_file_upload_btn" type="button" class="layui-btn">上传</button></td>
        </tr>
        <#elseif channelItem.dataType=="10"><#-- 图片 -->
        <tr>
            <td width="150"><span style="line-height: 80px;">${channelItem.itemLabel}：</span></td>
            <td>
                <img data-action="zoom" src="${((fieldVal??)&&fieldVal!="")?string("http://" + (currentSiteDomain!) +fieldVal,ctx+"/images/default.jpg")}" height="80" style="margin-right:20px;"><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
                <input type="hidden" readonly value="${fieldVal!}" name="${channelItem.field}_path" style="width:250px;">
                <input type="hidden" readonly value="${fieldVal!}" name="${channelItem.field}_name" data-rule="${(channelItem.isRequired==1)?string("required","")}" style="width:250px;">
                <input id="file_${channelItem.modelitemId}" name="img_${channelItem.field}" data-field="${channelItem.field}" class="layui-input" data-type="img" style="width: 250px;display:inline-block;margin-right:10px;" value="" abc-custom="${channelItem.isCustom}"
                       type="file"><button style="height:26px;line-height:13px;" name="_img_upload_btn" type="button" class="layui-btn">上传</button>
                <span style="line-height: 80px;">上传格式支持.jpg;.jpeg;.png;.bmp;图片最大为200K;宽高不得小于225×110</span>
            </td>
        </tr>
        <#elseif channelItem.dataType=="11"><#-- 颜色选择器 -->
        <tr>
            <td width="150"><span>${channelItem.itemLabel}：</span></td>
            <td>
                <input type="text" style="width:250px;margin-right:10px;" value="${fieldVal!}" readonly="readonly" name="${channelItem.field}" abc-custom="${channelItem.isCustom}"
                       data-rule="${(channelItem.isRequired==1)?string("required","")}" placeholder="请选择颜色">
                <button style="height:26px;line-height:13px;" name="_color_btn" type="button" class="layui-btn">选择</button>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="12"><#-- 多媒体 -->
        <tr>
            <td width="150"><span style="vertical-align:middle;">${channelItem.itemLabel}：</span></td>
            <td>
                <input type="hidden" readonly value="" name="${channelItem.field}_name" style="width:250px;">
                <input type="text" readonly value="${fieldVal!}" name="${channelItem.field}_path" data-rule="${(channelItem.isRequired==1)?string("required","")}" placeholder="请先上传多媒体文件" style="width:250px;" class="layui-input">
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
                <input id="file_${channelItem.modelitemId}" name="file_${channelItem.field}" data-field="${channelItem.field}" data-type="file" class="layui-input" style="width: 250px;display:inline-block;margin-right:10px;" value="" abc-custom="${channelItem.isCustom}"
                       type="file"><button style="height:26px;line-height:13px;" name="_media_upload_btn" type="button" class="layui-btn">上传</button></td>
        </tr>
        <#elseif channelItem.dataType=="13"><#-- 多附件 -->
        <tr>
            <td width="150"><span style="vertical-align:middle;">${channelItem.itemLabel}：</span>
                <button name="addAttachementInput" type="button">增加附件输入栏</button>
            </td>

            <td>
                <table name="attachementTable" cellspacing="10px" cellpadding="3px">
                    上传格式支持：.zip;.rar;最大为3M
                    <br>
                    <#if contentDto.fileList?? && contentDto.fileList?size gt 0>
                        <#list contentDto.fileList as afile>
                            <tr name="attachementInput">
                                <td style="padding:5px;">文件名称：<input type="text" name="fileName" value="${afile.fileName!}" data-rule="required;"></td>
                                <td style="padding:5px;" hidden>文件路径：<input type="text" name="filePath" value="${afile.filePath!}" readonly></td>
                                <td style="padding:5px;"><input type="file" name="file" id="file_${afile_index}" data-type="attachement" abc-custom="${channelItem.isCustom}"></td>
                                <td style="padding:5px;"><button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button></td>
                                <td style="padding:5px;"><button name="_attachement_delete_btn" type="button" class="layui-btn">删除</button></td>
                                <td style="padding:5px;"><a name="fileUrl" href="${((afile.filePath??)&&afile.filePath!='')?string('http://' + (currentSiteDomain!) +afile.filePath,'')}">${afile.fileName!}</a></td>
                            </tr>
                        </#list>
                    <#else>
                            <#--<tr name="attachementInput">-->
                                <#--<td style="padding:5px;">文件名称：<input type="text" name="fileName" value="" data-rule="required;"></td>-->
                                <#--<td style="padding:5px;" hidden>文件路径：<input type="text" name="filePath" value="" readonly></td>-->
                                <#--<td style="padding:5px;"><input type="file" name="file" id="file_0" data-type="attachement" abc-custom="${channelItem.isCustom}"></td>-->
                                <#--<td style="padding:5px;"><button name="_attachement_upload_btn" type="button" class="layui-btn">上传</button></td>-->
                                <#--<td style="padding:5px;"><button name="_attachement_delete_btn" type="button" class="layui-btn">删除</button></td>-->
                                <#--<td style="padding:5px;" hidden><a name="fileUrl" href="javascript:void();"></a></td>-->
                            <#--</tr>-->
                    </#if>
                </table>
            </td>
        </tr>
        </#if>
    </#if>
</#macro>




