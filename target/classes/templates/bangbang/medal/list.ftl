<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/lecturer.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <style>
        /*帮帮勋章*/
        #medal1{
            width: 100%;
            background: #fff;
        }
        #medal1 form{
            padding: 20px;
            background-color: #fff;
        }
        #medal1 tr td img{
            height: 30px;
            width: 30px;
            border-radius: 100%;
        }
        #medal1 tr th{
            text-align: center;
        }
        #medal1 td a{
            color: #3c8dbc;
        }
        #medal1 .guide-box{
            padding: 16px 20px;
            border-bottom: 1px solid #eeeeee;
        }
        #medal1 .row{
            border-bottom: 1px solid #eeeeee;
            padding: 16px 0;
        }
        #medal1 form{
            width: 100%;
            padding: 20px;
        }
        #medal1 form .container{
            width: 100%;
        }
        #medal1 form .Image img {
            width: 100px;
            height: 100px;
            border-radius:100%
        }
        #medalImage {
            width: 100px;
            height: 100px;
            border-radius:100%
        }
        #medalImage1 {
            width: 100px;
            height: 100px;
            border-radius:100%
        }
        #medal1 form .Upload-box{
            position: relative;
        }
        #medal1 form .Upload-box:after{
            content: '';
            display: block;
            clear: both;
        }
        #medal1 form .Upload-box input{
            position: absolute;
            width: 90px;
            line-height: 50px;
            z-index: 100;
            opacity: 0;
            top: 50px;
            cursor: pointer;
        }
        #medal1 form .Upload-box button{
            position: absolute;
            top: 50px;
        }
        #medal1 .md>span{
            font-weight: bold;
            color: #676767;
        }
        #medal1 ul li{
            color: #676767;
            line-height: 28px;
        }
        #medal1 ul li span{
            margin-left: 20px;
            color: #333333;
        }
    </style>
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <form class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">勋章名称</label>
                    <div class="layui-input-inline">
                        <input type="text" id="name" value="${RequestParameters["name"]!}" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn js-query" type="button">查询</button>
                </div>
                <a href="javascript:" class="layui-btn layui-btn-normal js_add fr">添加</a>
            </div>
        </div>
    </form>
    <form>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="50">序号</th>
                    <th class="text_c">勋章图片</th>
                    <th class="text_c">勋章名称</th>
                    <th class="text_c">勋章类别</th>
                    <th class="text_c">描述</th>
                    <th class="text_c">获取条件</th>
                    <th class="text_c">获取人数</th>
                    <th class="text_c">状态</th>
                    <th class="text_c">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if medals?? && (medals?size > 0) >
                    <#list medals as medal>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + medal_index + 1}</td>
                        <td align="center">
                            <div class="Image">
                                <img class="img" id="medalImage1" <#if medal?? && medal.image??>src="${imgUrl}/${medal.image}"<#else>src="${ctx}/images/default.png"</#if>>
                            </div>
                        </td>
                        <td align="center">${(medal.name)!}</td>
                        <td align="center">
                            <#if medal.type! == 'faction'>帮派勋章<#else>个人勋章</#if>
                        </td>
                        <td align="center">${(medal.description)!}</td>
                        <td align="center">${(medal.acquireCondition)!}</td>
                        <td align="center">${(medal.ownerCnt)!}</td>
                        <td align="center">
                            <#if medal.status?? && medal.status>
                                <div class="btn btn-success btn-xs">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs">无效</div>
                            </#if>
                        </td>
                        <td align="center">
                            <a data-href="${ctx}/bangbang/questionMedal/edit.php?id=${medal.id}" class="js_edit pn-opt">编辑</a> |
                            <#if medal.status>
                                <a data-id="${(medal.id)!}" data-status="false" data-href="${ctx}/bangbang/questionMedal/save.php" class="js_status pn-opt">停用</a>
                            <#else>
                                <a data-id="${(medal.id)!}" data-status="true" data-href="${ctx}/bangbang/questionMedal/save.php" class="js_status pn-opt">启用</a> |
                                <a data-href="${ctx}/bangbang/questionMedal/delete/${medal.id}.php" class="js_delete pn-opt">删除</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <table width="100%" class="yy_fanye"><tbody class='js-page-tr'>${pageHtml!}</tbody></table>
        </div>
    </form>
</div>
    <div class="teacher-popup" id="medal1" style="width: 65%">
    </div>
<div class="bg"></div>
<#--</div>-->
<script data-main="${ctx}/js/abc/bangbang/medal/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>