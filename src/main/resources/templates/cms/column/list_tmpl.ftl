<div name="column_tree_${type}" class="nycon_list_b">
    <table name="column_tree_table" class="table table-hover m_top_20">

        <#list siteList as site>
            <#if (site.siteStatus!)=="1">
            <tr>
                <td>
                    <table style="width:100%;">
                        <tr>
                            <td width="40">[站点]</td>
                            <td>
                                <span name="site_tree">${site.siteName!}</span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <#list columnList as col>
                <#if (col.siteId!)==(site.siteId!) && (col.parentId)=="0">
                    <@generateSubTree node=col list=columnList modelList=modelList width=3 />
                </#if>
            </#list>
            </#if>
        </#list>
    </table>
</div>

<#macro generateSubTree node list modelList width>
    <#assign modelName="">
    <#if modelList?? && modelList?size gt 0>
        <#list modelList as model>
            <#if model.modelId==node.modelId>
                <#assign modelName=model.modelName>
                <#break>
            </#if>
            <#if model_index==modelList?size-1>
                <#assign modelName="">
            </#if>
        </#list>
    <#else>
        <#assign modelName="">
    </#if>
<tr>
    <td>
        <table style="width:100%;">
            <tr>
                <td width="${width}%"></td>
                <td width="${100-width}%">
                    <table style="width:100%;">
                        <tbody>
                        <tr name="column_tree_row" data-modelid="${node.modelId!}" data-channelpath="${node.channelPath!}"
                            data-channelid="${node.channelId!}" data-channelname="${node.channelName!}"
                            data-siteid="${node.siteId!}">
                            <td width="50%">
                                <#if (node.isDisplay??) && node.isDisplay==0>
                                    <span style="color:#ff2d3c;">[当前状态:停用]</span>&nbsp;${node.channelName!}
                                <#else>
                                    <a name="column_tree_name" data-modelid="${node.modelId!}" data-channelpath="${node.channelPath!}"
                                       data-channelid="${node.channelId!}" data-channelname="${node.channelName!}"
                                       data-siteid="${node.siteId!}" href="javascript:void(0);">[${modelName!}]&nbsp;&nbsp;${node.channelName!}
                                    </a>
                                </#if>

                             <#--   <a name="column_tree_name" <#if (node.isDisplay??) && node.isDisplay==0>style="color:#ff2d3c;"</#if>
                                   data-modelid="${node.modelId!}" data-channelpath="${node.channelPath!}"
                                   data-channelid="${node.channelId!}" data-channelname="${node.channelName!}"
                                   data-siteid="${node.siteId!}"
                                   href="javascript:void(0);">[${modelName!}]&nbsp;&nbsp;<#if (node.isDisplay??) && node.isDisplay==0>
                                    [当前状态:停用]&nbsp;</#if>${node.channelName!}</a>-->
                                <#if type=="page">
                                    <#if (node.isDisplay??) && node.isDisplay==0>
                                        (文章：
                                            ${((node.cnt!)=="")?string("0",(node.cnt!))}&nbsp;&nbsp;
                                        )
                                        <#else>
                                            (文章：
                                            <a class="ljc_00bcd4" name="column_tree_open_content" href="javascript:void(0);" data-siteid="${node.siteId!}" data-channelid="${node.channelId!}">${((node.cnt!)=="")?string("0",(node.cnt!))}
                                                &nbsp;&nbsp;</a>
                                            )
                                    </#if>

                                </#if>

                            </td>

                            <#if type=="page">
                                <td width="50%" class="text_r"><a
                                        href="${ctx}/content/column/addPage.php?parentId=${node.channelId!}&&modelId=${node.modelId!}">增加子类</a>
                                    | <a
                                            href="${ctx}/content/column/addPage.php?channelId=${node.channelId!}&&modelId=${node.modelId!}">编辑</a>
                                    | <a name="btn_deleteChannel"
                                         class="pn-opt" data-columnid="${node.channelId!}"
                                         href="javascript:void(0);">删除</a>
                                </td>
                            </#if>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </td>
</tr>
    <#list list as subnode>
        <#if subnode.parentId?? && subnode.parentId==node.channelId>
            <@generateSubTree node=subnode list=list modelList=modelList width=width+3 />
        </#if>
    </#list>
</#macro>