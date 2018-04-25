<ul>
    <#if titlelist?? && (titlelist?size>0)>
        <#list titlelist as list>
            <li><a href="${viewDomain!}?id=${list.id}" target="_blank">${list.subject!""}</a></li>
        </#list>
    <#else>
        <li>暂无记录</li>
    </#if>
</ul>