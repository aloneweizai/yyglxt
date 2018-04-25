<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<input type="hidden" class="js_currLink" value="${currLink!}">
<form  class="layui-form">
<table  class="layui-table" lay-size="sm">
    <thead>
        <th><div class="nycon_sel_btn js_selectAll" data-check=false>全选</div></th>
        <th>类别</th>
        <th style="width: 40%; overflow: hidden; line-height: 20px;">知识标题</th>
        <th><a href="javascript:;" id="lllorder">浏览量</a></th>
        <th>状态</th>
        <th>所属分类</th>
        <th>更新人</th>
        <th><a href="javascript:;" id="gxsjorder">更新时间</a></th>
        <th>有效期</th>
        <@shiro.hasPermission name="cms_know:edit"><th>操作</th></@shiro.hasPermission>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if (knowBaseList?? && knowBaseList?size > 0)>
        <#list knowBaseList as knowBase>
        <tr>
            <td><input class="js_checkbox checkbox" type="checkbox" lay-skin="primary"  name="know_checkbox" id="${knowBase.id}" value="${knowBase.id}"></td>
            <td><input id="query" class="layui-btn layui-btn-primary" value="${(knowBase.type=='QA')?string("问答","知识")}" style="border:none; " type="button"></td>
            <td  style="width: 40%; overflow:hidden; line-height: 20px;"><a class="ljc_00bcd4" href="${viewDomain!}?id=${knowBase.id}" target="_blank">${knowBase.subject!}</a></td>
            <td>${knowBase.pv!'0'}</td>
            <td class="js_show_status">
                <#if knowBase.status?? && knowBase.status>
                    <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                <#else>
                    <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                </#if>
            </td>
            <td>${knowBase.categoryName!}</td>
            <td>${knowBase.updateUserName!}</td>
            <td>${(knowBase.updateTime?string("yyyy-MM-dd"))!}</td>
            <td>
            ${(knowBase.activeTime?string("yyyy-MM-dd"))!'永久'}
            </td>
            <@shiro.hasPermission name="cms_know:edit">
            <td>
                <a class="js_edit" href="javascript:void(0)" data-href="${ctx}/cms/know/edit.php?knowId=${(knowBase.id)!}">编辑</a>
            </td>
            </@shiro.hasPermission>
        </tr>
        </#list>
        <#else>
        <tr>
            <td colspan="8">
                <p style="text-align: center;">暂无内容</p>
            </td>
        </tr>
    </#if>
    </tbody>
</table>
</form >
<#if (knowBaseList?? && knowBaseList?size > 0)>
    <#assign  canEdit=false canDel=false>
    <@shiro.hasPermission name="cms_know:edit"><#assign canEdit=true></@shiro.hasPermission>
    <@shiro.hasPermission name="cms_know:delete"><#assign canDel=true></@shiro.hasPermission>
    <#--<#if canDel||canEdit>-->
        <div class="button_caozuo_fenye">
            <a href="javascript:;" id="quanxuan" class="layui-btn">全选</a>
            <#if canEdit>
                <a href="javascript:betchEff(true)" class="layui-btn">启用</a>
                <a href="javascript:betchEff(false)" class="layui-btn">禁用</a>
                <a href="javascript:;" id="jbq" class="layui-btn">加标签</a>
                <a href="javascript:;" id="gfl" class="layui-btn">改分类</a>
            </#if>
            <#if canDel>
                <a href="javascript:betchDel();" class="layui-btn">删除</a>
            </#if>
        </div>
    <#--</#if>-->
    <table class="yy_fanye" style="width: 100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>
