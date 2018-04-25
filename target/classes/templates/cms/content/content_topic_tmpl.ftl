<div class=" nycon_list_b">
    <table name="_content_topic_table" class="layui-table" lay-size="sm">
        <thead class="pn-lthead">
        <tr>
            <th>标题</th>
            <th>排列顺序</th>
            <th>推荐</th>
        </tr>
        </thead>
        <tbody class="pn-ltbody">
        <#list topicList as topic>
        <tr>
            <td><a class="ljc_00bcd4" href="javascript:void(0);" data-topicid="${topic.topicId!}" name="_content_a_topic">${topic.topicName!}</a></td>
            <td><span>${topic.priority!}</span></td>
            <td>${((topic.isRecommend)=="0")?string("否","是")}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>