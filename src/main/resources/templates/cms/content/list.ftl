<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <form action="${ctx}/content/content/list.php" id="content_query_condition_form" method="get" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input name="title" value="${pagination.title!}" type="text" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">操作员</label>
                    <div class="layui-input-inline">
                        <input name="username" value="${pagination.username!}" type="text" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">内容类型</label>
                    <div class="layui-input-inline">
                        <select class="cxinput" name="typeId">
                            <option value=""></option>
                        <#list typeMap?keys as dict>
                            <option ${(dict==(pagination.typeId!))?string("selected='selected'","")}
                                    value="${dict!}">${typeMap[dict]}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button type="submit" class="layui-btn">查询</button>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>
                <bottom id="content_create_btn" class="layui-btn layui-btn-normal fr">创建内容</bottom>
                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">固顶</label>
                        <div class="layui-input-inline">
                            <input name="topLevel" value="1" ${((pagination.topLevel!)=="1")?string("checked='checked'","")} type="checkbox" lay-skin="primary"  class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-inline">
                            <select  class="cxinput" name="status">
                                <option value=""></option>
                            <#list contentStatusList as contentStatus>
                                <option ${((contentStatus.fieldKey!)==(pagination.status!))?string("selected='selected'","")}
                                        value="${contentStatus.fieldKey!}">${contentStatus.fieldValue!}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">站点选择</label>
                        <div class="layui-input-inline">
                            <select  class="cxinput" name="siteId">
                                <option value=""></option>
                            <#list siteList as site>
                                <#if (site.siteStatus!)=="1">
                                    <option  ${((site.siteId!)==(pagination.siteId!))?string("selected='selected'","")}
                                            value="${site.siteId}">${site.siteName}</option>
                                </#if>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属栏目</label>
                        <div class="layui-input-inline">
                            <input id="input_channelId" placeholder="点击选择" value="${channelName!}" readonly="readonly"  type="text" class="layui-input">
                            <input type="hidden" name="channelId" value="${pagination.channelId!}">
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </form>

    <form action="" id="_content_list_form" name="_content_list_form" method="post" class="layui-form">
        <div class=" nycon_list_b">

            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="28">序号</th>
                    <th width="28">全选</th>
                    <th>标题</th>
                    <th width="28">固顶</th>
                    <th width="75">内容类型</th>
                    <th width="60">操作员</th>
                    <th width="45">点击</th>
                    <th width="150">发布时间</th>
                    <th width="115">是否生成静态页</th>
                    <th width="80">状态</th>
                    <th width="150">操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#assign statusMap={"0":"草稿","2":"已发布","3":"已撤销"} >
                <#assign statusColorMap={"0":"#bd9c30","2":"#397b3e","3":"#a94141"} >
                <#list contentList as content>
                <tr>
                    <td>${(pagination.size!)?number*((pagination.page!)?number-1)+content_index+1}</td>
                    <td>
                        <input name="ids" type="checkbox" lay-skin="primary" data-status="${content.status!}"
                               data-generated="${content.needRegenerate!}" data-siteid="${content.siteId!}" lay-filter="ids-filter"
                               data-sitestatus="${content.siteStatus!}" value="${content.contentId!}">
                    </td>
                    <td style="text-align:left;">
                        <strong>${((content.siteName!)!="")?string("["+(content.siteName!)+"]","[空站点]")}
                            [${content.channelName!}]</strong>
                        <#if (content.needRegenerate!)=="1">
                            <#if staticHtmlUrl?? && (content.contentId)??>
                            <#--<a name="content_list_item" href="javascript:void(0);" data-id="${content.contentId!}"
                               data-url="${(staticHtmlUrl!)+(content.contentId!)+".html"}"
                               style="font-weight:600;">${content.title!}</a>-->
                                <a class="ljc_00bcd4" href="http://${(content.domain!)}${(content.staticLink!)}"
                                   target="_blank">${content.title!}</a>
                            <#else>
                                <span style="color:#337ab7;font-weight:300;">${content.title!}</span>
                            </#if>
                        <#else>
                            <span style="font-weight:300;">${content.title!}</span>
                        </#if>
                    </td>
                    <td><input name="list_topLevel" type="checkbox" lay-skin="primary"  style="width:30px" value="1"
                               <#if (content.topLevel!)=="1">checked="checked"</#if>></td>
                    <td>${typeMap[(content.typeId!)]!}</td>
                    <td>${content.username!}</td>
                    <td>${content.views!}</td>
                    <td>${(content.releaseDate?string("yyyy-MM-dd HH:mm"))!}</td>
                    <td>
                        <span style="color:${((content.needRegenerate!)=="1")?string("#397b3e","#a94141")}">${((content.needRegenerate!)=="1")?string("已生成","未生成")}</span>
                    </td>
                    <td>
                        <span style="color:${statusColorMap[(content.status!)]!}">${statusMap[(content.status!)]!}</span>
                    </td>
                    <td>
                        <#if (content.needRegenerate!)=="1">
                        <#--<a name="content_show_site" data-generated="${content.needRegenerate!}" data-url="http://${(content.domain!)}${(content.staticLink!)}"-->
                        <#--data-id="${content.contentId!}" href="javascript:void(0);">查看-->
                        <#--</a>-->
                            <a href="${ctx}/content/content/addPage.php?contentId=${content.contentId!}&view=view">查看</a>
                        <#else>
                            <a href="${ctx}/cms/render/renderContent.php?contentId=${content.contentId!}"
                               target="_blank">预览</a>
                        </#if>
                        <#if (content.needRegenerate!)!="1" && ((content.siteStatus!)=="1")>|
                            <a href="${ctx}/content/content/addPage.php?contentId=${content.contentId!}">编辑</a>
                        </#if>
                        <#if (content.needRegenerate!)!="1" && ((content.siteStatus!)=="1")>|
                            <a name="content_issue_btn" data-contentid="${content.contentId!}" href="javascript:void(0);"
                               class="pn-opt">发布</a>
                        </#if>
                        <#if (content.needRegenerate!)!="1">|
                            <a name="content_del_btn" data-contentid="${content.contentId!}" href="javascript:void(0);"
                               class="pn-opt">删除</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="button_caozuo_fenye">
                <button type="button" id="btn-select-all" data-selectall="0" class="nycon_sel_btn layui-btn">全选</button>
                <button name="content_delete_batch" type="button" id="content_delete_batch" class="layui-btn layui-btn-danger">批量删除</button>
                <button name="content_send_back" id="content_send_back" type="button"  class="layui-btn layui-btn-normal">撤销发布</button>
                <button name="generate_static_site" id="generate_static_site" type="button"  class="layui-btn">生成静态页</button>
                <button name="content_set_toplevel" id="content_set_toplevel" type="button"  class="layui-btn layui-btn-normal">保存固顶</button>
            </div>
        <#--<input name="content_set_topic" type='button' class="btn btn-default btn-sm pn-opt" value='推送至专题'/>-->

        <#--<table>-->
        <#--<tbody>-->
        <#--<tr>-->
        <#--<td align="center">-->
        <#--共 377 条 每页-->
        <#--<input value="20" maxlength="3" style="width:30px" onfocus="this.select();"-->
        <#--onblur="refresh(this.value)"-->
        <#--onkeypress="if(event.keyCode==13){$(this).blur();return false;}" type="text">-->
        <#--条-->
        <#--<input class=" btn btn-default btn-xs" value="首 页" onclick="_gotoPage('1');" type="button">-->
        <#--<input class=" btn btn-default btn-xs" value="上一页" onclick="_gotoPage('2');" type="button">-->
        <#--<input class=" btn btn-default btn-xs" value="下一页" onclick="_gotoPage('4');" type="button">-->
        <#--<input class=" btn btn-default btn-xs" value="尾 页" onclick="_gotoPage('19');" type="button">-->
        <#--当前 3/19 页 转到第-->
        <#--<input id="_goPs" style="width:50px" onfocus="this.select();"-->
        <#--onkeypress="if(event.keyCode==13){$('#_goPage').click();return false;}" type="text">-->
        <#--页-->
        <#--<input id="_goPage" class=" btn btn-default btn-xs" value="转"-->
        <#--onclick="_gotoPage($('#_goPs').val());" type="button">-->
        <#--</td>-->
        <#--</tr>-->
        <#--</tbody>-->
        <#--</table>-->
        </div>
    </form>
<#include "../../common/pagination.ftl">
</div>
</body>
<script data-main="${ctx}/js/abc/cms/content/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}"
        src="${ctx}/js/require.js"></script>
</html>