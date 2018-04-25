<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <#--<link rel="stylesheet" type="text/css" href="${ctx}/css/layui.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/lecturer.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">

    <style>
        /*帮帮勋章*/
        #faction1{
            width: 100%;
            background: #fff;
        }
        #faction1 form{
            padding: 20px;
            background-color: #fff;
        }
        #faction1 tr td img{
            height: 30px;
            width: 30px;
            border-radius: 100%;
        }
        #faction1 tr th{
            text-align: center;
        }
        #faction1 td a{
            color: #3c8dbc;
        }
        #faction1 .guide-box{
            padding: 16px 20px;
            border-bottom: 1px solid #eeeeee;
        }
        #faction1 .row{
            border-bottom: 1px solid #eeeeee;
            padding: 16px 0;
        }
        #faction1 form{
            width: 100%;
            padding: 20px;
        }
        #faction1 form .container{
            width: 100%;
        }
        #faction1 form .Image img {
            width: 100px;
            height: 100px;
            border-radius:100%
        }
        #faction1 form .Upload-box{
            position: relative;
        }
        #faction1 form .Upload-box:after{
            content: '';
            display: block;
            clear: both;
        }
        #faction1 form .Upload-box input{
            position: absolute;
            width: 90px;
            line-height: 50px;
            z-index: 100;
            opacity: 0;
            top: 50px;
            cursor: pointer;
        }
        #faction1 form .Upload-box button{
            position: absolute;
            top: 50px;
        }
        #faction1 .md>span{
            font-weight: bold;
            color: #676767;
        }
        #faction1 ul li{
            color: #676767;
            line-height: 28px;
        }
        #faction1 ul li span{
            margin-left: 20px;
            color: #333333;
        }
        #faction1 .layui-layer-title {
            background: #009f95;
            color: #fff;
            border: none;
        }
        #faction1 .mp {
            float: left;
            margin-right: 8px;
        }
        #faction1 .mp img {
            width: 26px;
        }
        .js-body-tr a{
            color: #337ab7;
        }
    </style>
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink}">
<div class="container-fluid m_top_30 nycol_list character_no_span">
    <form class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <a href="javascript:;" class="js_add layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>
    </form>
    <form>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="50">序号</th>
                    <th class="text_c">帮派等级</th>
                    <th class="text_c">帮派名称</th>
                    <th class="text_c">帮派头像</th>
                    <th class="text_c">帮派荣誉值</th>
                    <th class="text_c">人数限制</th>
                    <th class="text_c">任务限制</th>
                    <th class="text_c">状态</th>
                    <th class="text_c">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if factions?? && (factions?size > 0) >
                    <#list factions as faction>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + faction_index + 1}</td>
                        <td align="center">${(faction.code)!}</td>
                        <td align="center">${(faction.name)!}</td>
                        <td align="center">
                            <img src="${ctx}${(faction.image)!}" alt="" width="26px">
                        </td>
                        <td align="center">${(faction.honorValue?c)!}</td>
                        <td align="center">${(faction.peopleLimit?c)!}</td>
                        <td align="center">${(faction.taskLimit?c)!}级</td>
                        <td align="center">
                            <#if faction.status?? && faction.status>
                                <div class="btn btn-success btn-xs">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs">无效</div>
                            </#if>
                        </td>
                        <td align="center">
                            <a data-href="${ctx}/bangbang/factionLevel/edit.php?id=${faction.id}" class="js_edit pn-opt">编辑</a> |
                            <#if faction.status>
                                <a data-id="${(faction.id)!}" data-status="false" data-href="${ctx}/bangbang/factionLevel/save.php" class="js_status pn-opt">停用</a>
                            <#else>
                                <a data-id="${(faction.id)!}" data-status="true" data-href="${ctx}/bangbang/factionLevel/save.php" class="js_status pn-opt">启用</a> |
                                <a data-href="${ctx}/bangbang/factionLevel/delete/${faction.id}.php" class="js_delete pn-opt">删除</a>
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
    <div class="teacher-popup" id="faction1" style="width: 50%">
    </div>
<div class="bg"></div>
<#--</div>-->
<script data-main="${ctx}/js/abc/bangbang/factionLevel/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>