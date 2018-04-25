<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/tagEditor/jquery.tagsinput.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>

</head>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <form action="${ctx}/consumerManager/operate/message/consumerlook.php" method="post" id="consumerListForm" class="layui-form">
        <input type="hidden" name="userIds" id="userIds" value="${userIds!}">
        <div class="layui-form-top">
            <div class="layui-form-item" style="width: 500px">
                <div class="layui-inline">
                    <span style="font-weight: inherit;padding: 2px 10px;float: left;width:50px;">用户名</span>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <span style="font-weight: inherit;padding: 2px 10px;float: left;width:50px;">手机号</span>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.phone)!}" name="phone" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <span style="font-weight: inherit;padding: 2px 10px;float: left;width:50px;">昵称</span>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.nickname)!}" name="nickname" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
            <div  style="width: 500px">
                <table class="layui-table">
                    <thead class="pn-lthead">
                    <tr>
                        <th>序号</th>
                        <th width="28">全选</th>
                        <th width: 10px>用户名</th>
                        <th style="width: 10px">昵称</th>
                        <th style="width: 10px">手机号</th>
                    </tr>
                    </thead>
                    <tbody class="pn-ltbody">
                    <#if consumers?? && ( consumers?size gt 0 )>
                        <#list consumers as consumer>
                        <tr>
                            <td width='30' class="td_i">${BaseRq.offset + consumer_index + 1}</td>
                            <td><input class="js_checkbox" lay-filter="ids" name="ids" type="checkbox" <#if consumer.checked??&&consumer.checked>checked</#if> data-name="${(consumer.username)!}"  value="${consumer.id}" lay-skin="primary"></td>
                            <td>${(consumer.username)!}</td>
                            <td>${(consumer.nickname)!}</td>
                            <td>${(consumer.phone)!}</td>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
                <div class="button_caozuo_fenye" style="width: 470px">
                    <div class="layui-btn nycon_sel_btn" data-check=false>全选</div>
                </div>
                <table class="yy_fanye" style="width:500px">
                    <tbody>
                    <tr>
                        <td align="center">
                            <div class="layui-inline">
                            共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                            每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size"
                                     type="text">条&nbsp;&nbsp;
                            <input class="btn btn-default btn-xs" value="首 页" id="consumer_first" type="button">
                            <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up" type="button">
                            <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                            <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                                </div>
                            <div class="layui-inline">
                            当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                            <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}"
                                   type="text"> 页
                            <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                            <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                            <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                                </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
    </form>
</div>


<div class="main_modal container-fluid" id="myModal" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <label><h3>标签</h3></label>
                <select style="height:30px;margin-left:30px;" id="seletablib">
                    <option value="">所有标签类型</option>
                <#if taglib?? && ( taglib?size gt 0 )>
                    <#list taglib as lib>
                        <option value="${lib.fieldValue}">${lib.fieldKey}</option>
                    </#list>
                </#if>
                </select>
            </div>
            <div class="modal-body">
                        <#if taglib?? && ( taglib?size gt 0 )>
                            <#list taglib as lib>
                                <div class="tag-list__itemWraper itemWraper1" id="tag_${lib.fieldValue}">
                                    <h3 class="h5 tag-list__itemheader">${lib.fieldKey}</h3>
                                    <ul class="tag-list__itembody taglist--inline multi">
                                        <#if tags?? && ( tags?size gt 0 )>
                                            <#list tags as tag>
                                                <#if lib.fieldValue == tag.category  && tag.status>
                                                    <li><a id="taglib" class="btn tag1" data-type="unselect"
                                                           data-id="${tag.tagName!}">${tag.tagName!}</a></li>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>

                                <#if (lib_index+1)%3==0>
                                    <div style="clear: both;height:0px;"></div>
                                </#if>
                            </#list>
                        </#if>
                            <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/consumer/userlook.js" src="${ctx}/js/require.js"></script>
</body>
</html>
