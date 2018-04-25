<div class="video_list_div">
    <table class="ny_tab_t ">
        <tbody>
        <tr>
            <td style="text-align:left" width="400">
                <span><input type="text" value="${RequestParameters["fileName"]!}" id="videoName_input" placeholder="文件名" style="width:130px;height:30px;"></span>
                <input type="button" value="查询" id="video_query"  class="layui-btn">
            </td>
        </tr>
        </tbody>
    </table>
    <div class="js_video_div">
        <table class="layui-table" lay-size="sm">
            <thead class="pn-lthead">
            <tr><th>文件名</th><th>大小</th><th>地址</th></tr>
            </thead>
            <tbody class="pn-ltbody">
                <#if (mediaFiles?? && mediaFiles?size > 0)>
                    <#list mediaFiles as file>
                        <tr style="cursor:pointer" class="js_select_video_tr">
                            <td>${file.fileName!}</td>
                            <td>${file.fileSize!}</td>
                            <td>${file.fileSite!}</td>
                        </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
        <table width="100%"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
    </div>
</div>