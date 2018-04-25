<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/course/lecturer.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <style>
        /*财税大侠*/
        #expert1{
            width: 100%;
            background: #fff;
        }
        #expert form{
            padding: 20px;
            background-color: #fff;
        }
        #expert tr td img{
            height: 30px;
            width: 30px;
            border-radius: 100%;
        }
        #expert tr th{
            text-align: center;
        }
        #expert td a{
            color: #3c8dbc;
        }
        #expert1 .guide-box{
            padding: 16px 20px;
            border-bottom: 1px solid #eeeeee;
        }
        #expert1 .row{
            border-bottom: 1px solid #eeeeee;
            padding: 16px 0;
            margin-right: 10px;
        }
        #expert1 form{
            width: 100%;
            padding: 20px;
        }
        #expert1 form .container{
            width: 100%;
        }
        #expert1 form .Image img {
            width: 100px;
            height: 100px;
            border-radius:100%
        }
        #expert1 form .Upload-box{
            position: relative;
        }
        #expert1 form .Upload-box:after{
            content: '';
            display: block;
            clear: both;
        }
        #expert1 form .Upload-box input{
            position: absolute;
            width: 90px;
            line-height: 50px;
            z-index: 100;
            opacity: 0;
            top: 50px;
            cursor: pointer;
        }
        #expert1 form .Upload-box button{
            position: absolute;
            top: 50px;
        }
        #expert1 .md>span{
            font-weight: bold;
            color: #676767;
        }
        #expert1 ul li{
            color: #676767;
            line-height: 28px;
        }
        #expert1 ul li span{
            margin-left: 20px;
            color: #333333;
        }
        /*遮罩层*/
        .bg{
            height: 1000px;
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
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" value="${RequestParameters["username"]!}" placeholder="用户名" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="realName" value="${RequestParameters["realName"]!}" placeholder="真实姓名" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-inline">
                        <input type="text" id="phone" value="${RequestParameters["phone"]!}" placeholder="联系电话" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专家类型</label>
                    <div class="layui-input-inline">
                        <select id="type" style="width:130px;height:35.5px;margin-left:5px;">
                            <option value="">--专家类型--</option>
                        <#list expertTypes as expertType>
                            <option value="${expertType.fieldValue}"
                                    <#if RequestParameters["type"]?? && RequestParameters["type"] == expertType.fieldValue>selected</#if>
                                    >${expertType.fieldKey}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button type="button" class="layui-btn js-query">查询</button>
                    </div>
                </div>
                <a href="javascript:;" class="js_add layui-btn layui-btn-normal fr">添加</a>
            </div>
        </div>
    </form>
    <form>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th style="text-align: center" width="50">序号</th>
                    <th style="text-align: center">用户名</th>
                    <th style="text-align: center">用户昵称</th>
                    <th style="text-align: center">真实姓名</th>
                    <th style="text-align: center">联系电话</th>
                    <th style="text-align: center">专家类型</th>
                    <th style="text-align: center">状态</th>
                    <th style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if experts?? && (experts?size > 0) >
                    <#list experts as expert>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + expert_index + 1}</td>
                        <td align="center">${(expert.username)!}</td>
                        <td align="center">${(expert.nickname)!}</td>
                        <td align="center">${(expert.realName)!}</td>
                        <td align="center">${(expert.phone)!}</td>
                        <td align="center">
                            <#list expertTypes as expertType>
                                <#if expert?? && expert.type == expertType.fieldValue!>${expertType.fieldKey}</#if>
                            </#list>
                        </td>
                        <td align="center">
                            <#if expert.status?? && expert.status=="1">
                                <div class="btn btn-success btn-xs ">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">无效</div>
                            </#if>
                        </td>
                        <td align="center">
                            <a data-href="${ctx}/bangbang/questionExpert/edit.php?id=${expert.id}" class="js_edit pn-opt">编辑</a> |
                            <#if expert.status?? && expert.status=="1">
                                <a data-id="${(expert.id)!}" data-status="0" data-href="${ctx}/bangbang/questionExpert/save.php" class="js_status pn-opt">停用</a>
                            <#else>
                                <a data-id="${(expert.id)!}" data-status="1" data-href="${ctx}/bangbang/questionExpert/save.php" class="js_status pn-opt">启用</a> |
                                <a data-href="${ctx}/bangbang/questionExpert/delete/${expert.id}.php" class="js_delete pn-opt">删除</a>
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
    <div class="teacher-popup" id="expert1" style="width: 75%">
    </div>
<div class="bg"></div>
<#--</div>-->
<script data-main="${ctx}/js/abc/bangbang/expert/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>