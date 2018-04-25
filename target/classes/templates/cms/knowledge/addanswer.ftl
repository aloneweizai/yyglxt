<div name="panel" class="panel panel-default" style="padding: 15px;">
    <table class="layui-table" lay-size="sm">
        <tr>
            <td style="width: 150px;">显示名称</td>
            <td>
                <input class="layui-input" name="keywords" id="keywords" placeholder="请输入该问题关键字"/>
            </td>
        </tr>
        <tr>
            <td>筛选条件</td>
            <td>
                <input readonly value="${name!"选择知识分类"}" id="conSelectKnowCateInp" class="layui-input"/>
                <input id="conCateId" name="categoryCode" type="hidden" value="${id!''}"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                    <button type="button" id="conSearchBtn"  class="layui-btn">搜索</button>
            </td>
        </tr>
    </table>
    <table class="layui-table" lay-size="sm">
       <thead>
        <tr>
            <#--<th></th>-->
            <th>标准问法</th>
            <th>更新时间</th>
            <th>启用状态</th>
            <th>有效期</th>
        </tr>
       </thead>
       <tbody class="js-body-tr"></tbody>
    </table>
    <table width="100%" class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>

</div>