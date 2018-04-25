<#macro model_tmpl_macro channelItem>
    <#if channelItem.isDisplay==1>
        <#if queryColumn??>
            <#if channelItem.isCustom==0>
                <#assign fieldVal=(queryColumn.channel[channelItem.field])???string((queryColumn.channel[channelItem.field])!,(queryColumn.channelExt[channelItem.field])!)/>
            <#else>
                <#if queryColumn.channelAttrList??>
                    <#list queryColumn.channelAttrList as columnAttr>
                        <#if columnAttr.attrName==channelItem.field>
                            <#assign fieldVal=columnAttr.attrValue>
                            <#break>
                        </#if>
                        <#if columnAttr_index==queryColumn.channelAttrList?size-1>
                            <#assign fieldVal="">
                        </#if>
                    </#list>
                    <#if !(queryColumn.channelAttrList??) ||queryColumn.channelAttrList?size==0 >
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
        <#if channelItem.field=='siteId'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <select style="width: 250px;" name="${channelItem.field}" id="siteId" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#if (fieldVal!)=="" && (parentChannelName!)=="">
                        <option value="">请选择</option>
                    </#if>
                    <#list siteList as site>
                        <#if (site.siteStatus!)=="1" >
                            <#if (fieldVal!)!="">
                                <#if (fieldVal!)==(site.siteId!)>
                                    <option <#if (site.siteId!)==(fieldVal!)>selected="selected"</#if> data-sitepath="${site.sitePath!}" data-domain="${site.domain!}" value="${(site.siteId)!}">${(site.siteName)!}</option>
                                </#if>
                            <#else>
                                <option data-sitepath="${site.sitePath!}" value="${(site.siteId)!}" data-domain="${site.domain!}">${(site.siteName)!}</option>
                            </#if>
                        </#if>
                    </#list>
                </select>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='channelPath'>
        <#if (fieldVal!)!="">
            <#assign fieldVal = fieldVal?replace((parentChannelPath!),"")>
        </#if>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <input type="text" class="layui-input" id="parentPath" name="parentPath" readonly="readonly" placeholder="根级栏目" value="${parentChannelPath!}" style="width:80px;">

                <input type="text" id="${channelItem.field}" class="layui-input" name="${channelItem.field}" style="width:165px;"
                       value="${fieldVal!}" abc-custom="${channelItem.isCustom}" maxlength="${(channelItem.textSize)!}"
                       data-rule="path;${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"><#if (channelItem.isRequired!)==1>
                <span class="color_r2">*</span></#if></td>
        </tr>
        <#elseif channelItem.field=='modelId'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <select style="width: 250px;" name="${channelItem.field}" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#--<option value="">请选择</option>-->
                    <#list modelList as model>
                        <#--<option <#if (model.modelId!)==(modelId!)>selected="selected"</#if> value="${(model.modelId)!}">${(model.modelName)!}</option>-->
                        <#if (model.modelId!)==(modelId!)>
                            <option <#if (model.modelId!)==(modelId!)>selected="selected"</#if> value="${(model.modelId)!}">${(model.modelName)!}</option>
                        </#if>
                    </#list>
                </select>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='parentId'>
        <tr>
            <td width="150">父级栏目：</td>
            <td>
                <input type="hidden" abc-custom="0" name="parentId" value="${(fieldVal??&&fieldVal!="")?string(fieldVal,parentId!)}" style="width:250px;margin-right:10px;">
                <input type="text" style="width:250px;margin-right:10px;" readonly="readonly" value="${parentChannelName!}" data-rule="${(channelItem.isRequired==1)?string("required","")}" placeholder="请选择栏目"><button style="height:26px;line-height:13px;" name="_channel_btn" type="button" class="layui-btn">选择</button>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='tplChannel'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <select style="width: 250px;" name="${channelItem.field}" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#if templateList??>
                        <option value="">请选择</option>
                        <#list templateList as templateItem>
                            <option <#if (templateItem.templatePath!)==(fieldVal!)>selected="selected"</#if> value="${templateItem.templatePath!}">${templateItem.templateName!}</option>
                        </#list>
                    <#else>
                        <option value="">请先选择所属站点</option>
                    </#if>
                </select>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='tplContent'>
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <select style="width: 250px;" name="${channelItem.field}" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <#if templateList??>
                        <option value="">请选择</option>
                        <#list templateList as templateItem>
                            <option <#if (templateItem.templatePath!)==(fieldVal!)>selected="selected"</#if> value="${templateItem.templatePath!}">${templateItem.templateName!}</option>
                        </#list>
                    <#else>
                        <option value="">请先选择所属站点</option>
                    </#if>
                </select>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.field=='viewGroupIds'>
            <#assign fieldVal=queryColumn.groupList>
            <#assign selectFlag=false>
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
                    <input class="np" name="${channelItem.field}" value="${subVal[0]!}" data-rule="${(channelItem.isRequired==1)?string("checked","")}" <#if selectFlag >checked="checked"</#if> type="checkbox" lay-skin="primary"  abc-custom="${channelItem.isCustom}">&nbsp;${subVal[1]!}&nbsp;&nbsp;
                </#list>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="1"><#-- 文本输入框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><input class="layui-input" name="${channelItem.field}" style="width: 250px;" value="${((fieldVal!)!="")?string((fieldVal!),(channelItem.defValue!))}" type="text" abc-custom="${channelItem.isCustom}"
                       maxlength="${(channelItem.textSize)!}"
                       data-rule="${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
        </tr>
        <#elseif channelItem.dataType=="2"><#-- 整型文本框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><input class="layui-input" name="${channelItem.field}" style="width: 250px;" value="${((fieldVal!)!="")?string((fieldVal!),(channelItem.defValue!))}" type="text" abc-custom="${channelItem.isCustom}"
                       maxlength="${(channelItem.textSize)!}"
                       data-rule="integer(+0);${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
        </tr>
        <#elseif channelItem.dataType=="3"><#-- 浮点文本框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td><input class="layui-input" style="width: 250px;" name="${channelItem.field}" value="${((fieldVal!)!="")?string((fieldVal!),(channelItem.defValue!))}" type="text" abc-custom="${channelItem.isCustom}"
                       maxlength="${(channelItem.textSize)!}"
                       data-rule="numeric;${(channelItem.isRequired==1)?string("required;","")}${channelItem.checkRule!}"
                       <#--<#if (channelItem.isRequired!)==1>data-rule="required"</#if>-->
                    ><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
        </tr>
        <#elseif channelItem.dataType=="4"><#-- 区域框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：<#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if></td>
            <td style="height:350px;">
                <div id="${channelItem.modelitemId!}" style="width: 100%;height:300px;" abc-type="textarea"
                     abc-custom="${channelItem.isCustom}" data-labelname="${channelItem.itemLabel!}"
                     <#if (channelItem.isRequired!)==1>data-rule="required"</#if>
                     name="${channelItem.field}">${fieldVal!}</div>
            </td>
        </tr>
        <#elseif channelItem.dataType=="5"><#-- 日期 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <input id="${channelItem.field}" name="${channelItem.field}" style="width:250px;" abc-type="datebox"
                       abc-custom="${channelItem.isCustom}"
                       data-rule="${(channelItem.isRequired==1)?string("required","")}"
                       value="${((fieldVal!)!="")?string((fieldVal!),(channelItem.defValue!))}"><#if (channelItem.isRequired!)==1>
                <span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="6"><#-- 下拉列表 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
                <select style="width: 250px;" name="${channelItem.field}" abc-custom="${channelItem.isCustom}" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                    <option value="">请选择</option>
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
                </select>
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
                    <input class="np" name="${channelItem.field}" value="${subVal[0]!}" data-rule="${(channelItem.isRequired==1)?string("checked","")}" <#if checkFlag >checked="checked"</#if> type="checkbox" lay-skin="primary"  abc-custom="${channelItem.isCustom}">&nbsp;${subVal[1]!}&nbsp;&nbsp;
                </#list>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="8"><#-- 单选框 -->
        <tr>
            <td width="150">${channelItem.itemLabel}：</td>
            <td>
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
                    <input name="${channelItem.field}" class="np" value="${(subVal[0])!}" data-rule="${(channelItem.isRequired==1)?string("checked","")}" <#if checkFlag>checked="checked"</#if> type="radio" abc-custom="${channelItem.isCustom}">&nbsp;${(subVal[1])!}&nbsp;&nbsp;
                </#list>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="9"><#-- 附件 -->
        <tr>
            <td width="150"><span style="vertical-align:middle;">${channelItem.itemLabel}：</span></td>
            <td>
                <#--<input type="hidden" readonly value="" name="${channelItem.field}_name" style="width:250px;">-->

                <input type="text" readonly value="${fieldVal!}" name="${channelItem.field}_path" data-rule="${(channelItem.isRequired==1)?string("required","")}" placeholder="请先上传文件" style="width:250px;">
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
                <input id="file_${channelItem.modelitemId}" name="file_${channelItem.field}" data-field="${channelItem.field}" data-type="file" class="layui-input" style="width: 250px;display:inline-block;margin-right:10px;" value="" abc-custom="${channelItem.isCustom}"
                       type="file"><button style="height:26px;line-height:13px;" name="_file_upload_btn" type="button" class="layui-btn">上传</button><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="10"><#-- 图片 -->
        <tr>
            <td width="150"><span style="line-height: 80px;">${channelItem.itemLabel}：</span></td>
            <td>
                <#--<input type="hidden" readonly value="" name="${channelItem.field}_name" style="width:250px;">-->
                <img data-action="zoom" abc-type="image" src="${((fieldVal??)&&fieldVal!="")?string("http://" + (currentSiteDomain!) + fieldVal,ctx+"/images/default.jpg")}" height="80" style="margin-right:20px;">
                <input type="hidden" readonly value="${fieldVal!}" name="${channelItem.field}_path" style="width:250px;" data-rule="${(channelItem.isRequired==1)?string("required","")}">
                <input id="file_${channelItem.modelitemId}" name="img_${channelItem.field}" data-field="${channelItem.field}" class="layui-input" data-type="img" style="width: 250px;display:inline-block;margin-right:10px;" value="" abc-custom="${channelItem.isCustom}"
                       type="file"><button style="height:26px;line-height:13px;" name="_img_upload_btn" type="button" class="layui-btn">上传</button><#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        <#elseif channelItem.dataType=="11"><#-- 颜色选择器 -->
        <tr>
            <td width="150"><span>${channelItem.itemLabel}：</span></td>
            <td>
                <input type="text" style="width:250px;margin-right:10px;" value="${fieldVal!}" readonly="readonly" data-rule="${(channelItem.isRequired==1)?string("required","")}" abc-custom="${channelItem.isCustom}" placeholder="请选择颜色">
                <button style="height:26px;line-height:13px;" name="_color_btn" type="button" class="layui-btn">选择</button>
                <#if (channelItem.isRequired!)==1><span class="color_r2">*</span></#if>
            </td>
        </tr>
        </#if>
    </#if>
</#macro>




