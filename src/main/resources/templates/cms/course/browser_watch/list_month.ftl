<br>
<script type="text/javascript">
    <#assign ctx=request.getContextPath()>
    var ctx = "${ctx}";
</script>

<div id="form1">
    <div style="display: block" class="tatle_box">
        <form class="layui-form" >
            <div>
                <div class="layui-row row">
                    <div  class="layui-inline inline">
                        <label style="width: 80px;" class="layui-form-label">时间</label>
                        <div style="float: left" class="layui-form">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <input id="month" value="${RequestParameters["month"]!}" type="text" class="layui-input date-month">
                                </div>
                                <div class="layui-btn js_query_month">查找</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="layui-table tables">
                <colgroup>
                    <col width="5%">
                    <col width="35%">
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                    <col width="20%">
                    <col width="10%">
                </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>课程标题</th>
                    <th>状态</th>
                    <th>浏览</th>
                    <th>观看</th>
                    <th>课程分类</th>
                    <th>月份</th>
                </tr>
                </thead>
                <tbody align="center" class="js-body-tr">
                <#if list?? && (list?size > 0) >
                    <#list list as item>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + item_index + 1}</td>
                        <td align="center">
                            <a class="ljc_00bcd4" href="${SNS_PATH}/school/details/${item.curriculumId!}" target="_blank">${item.title!}</a></td>
                        </td>
                        <td>
                            <#if item.status??&&item.status == 0>
                                <div class="btn btn-info btn-xs">未发布</div>
                            <#elseif item.status??&&item.status == 1>
                                <div class="btn btn-success btn-xs">已发布</div>
                            <#elseif item.status??&&item.status == 2>
                                <div class="btn btn-danger btn-xs">已撤销</div>
                            </#if>
                        </td>
                        <td align="center">${(item.browseNum)!}</td>
                        <td align="center">
                            <a class="ljc_00bcd4 opendialog"  data-val="4" href="javascript:;"
                               data-url="/cms/courseBrowseWatch/watch_userlist.php?initModule=month&begintime=${(item.updateTime?string("yyyy-MM"))!}&endtime=${(item.updateTime?string("yyyy-MM"))!}&curriculumId=${item.curriculumId!}">${(item.watchNum)!}</a>
                        </td>
                        <td align="center">${(item.classify)!}</td>
                        <td align="center">${(item.updateTime?string("yyyy-MM"))!}</td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </form>
    </div>
<#if list?? && (list?size > 0) >
    <table width="100%" class="table table-hover .table-condensed"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
</#if>

