<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/tagEditor/jquery.tagsinput.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
        .table>thead>tr>th,.table>tbody>tr>td{text-align:left;}
        .ny_tab_t>tbody>tr>td,.ny_tab_t>tr>td{padding: 3px 3px;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/usertrendcount/registerusercount.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间起</label>
                    <div class="layui-input-inline"  id="startDate">
                        <input type="text" class="layui-input" id="test4" value="${(BaseRq.start)!}" name="start" >
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">注册时间止</label>
                    <div class="layui-input-inline" id="endDate">
                        <input type="text" class="layui-input" id="test5" value="${(BaseRq.end)!}" name="end">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">区域</label>
                    <div class="layui-input-inline">
                        <select name="type" id="type">
                            <option <#if BaseRq.type?? && BaseRq.type == 'country'>selected</#if>  value="country">全国</option>
                            <option <#if BaseRq.type?? && BaseRq.type == 'province'>selected</#if> value="province">湖南省</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="margin-top: 10px">已选标签</label>

                    <div class="layui-input-inline" style="width:400px;margin-right: 45px">
                        <input type="text" name="tagName" id="tagnames" value="${(BaseRq.tagName)!}"
                               placeholder="请搜索选择标签" class="layui-input">
                    </div>
                </div>
                <div id="tagslect" class="layui-btn">选择标签</div>
                <div id="tagdelall" class="layui-btn layui-btn-danger">清空标签</div>
            </div>
        </div>
        <div class="statistics_tab " style="margin-top: 20px">
            <div class="statistics_t">
                <h3 class="modal-title" style="font-size: large"></h3>
            </div>
            <div class="statistics_tb">
            </div>
            <div class="tj_statis_w">
                <div class="tj_statis_n hover">
                    <div id="tjday" style="width: 100%"></div>
                </div>
            </div>
            <div class="tj_statis_w" <#if !(BaseRq.type?? && BaseRq.type == '6')>style='display:none;'</#if>>
                <div class="tj_statis_n hover">
                    <div id="tjday1" style="width: 100%"></div>
                </div>
            </div>
        </div>

        <div class="nycon_list_b" style="margin-top: 30px">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>区域</th>
                    <th>用户数（人）</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody" id="registeruser">
                </tbody>
            </table>
        </div>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/system/userarea.js" src="${ctx}/js/require.js"></script>
</body>
</html>
