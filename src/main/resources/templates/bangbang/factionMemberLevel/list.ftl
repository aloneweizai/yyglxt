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
        #factionMember1{
            width: 100%;
            background: #fff;
        }
        #factionMember1 form{
            padding: 20px;
            background-color: #fff;
        }
        #factionMember1 tr td img{
            height: 30px;
            width: 30px;
            border-radius: 100%;
        }
        #factionMember1 tr th{
            text-align: center;
        }
        #factionMember1 td a{
            color: #3c8dbc;
        }
        #factionMember1 .guide-box{
            padding: 16px 20px;
            border-bottom: 1px solid #eeeeee;
        }
        #factionMember1 .row{
            border-bottom: 1px solid #eeeeee;
            padding: 16px 0;
        }
        #factionMember1 form{
            width: 100%;
            padding: 20px;
        }
        #factionMember1 form .container{
            width: 100%;
        }
        #factionMember1 form .Image img {
            width: 100px;
            height: 100px;
            border-radius:100%
        }
        #factionMember1 form .Upload-box{
            position: relative;
        }
        #factionMember1 form .Upload-box:after{
            content: '';
            display: block;
            clear: both;
        }
        #factionMember1 form .Upload-box input{
            position: absolute;
            width: 90px;
            line-height: 50px;
            z-index: 100;
            opacity: 0;
            top: 50px;
            cursor: pointer;
        }
        #factionMember1 form .Upload-box button{
            position: absolute;
            top: 50px;
        }
        #factionMember1 .md>span{
            font-weight: bold;
            color: #676767;
        }
        #factionMember1 ul li{
            color: #676767;
            line-height: 28px;
        }
        #factionMember1 ul li span{
            margin-left: 20px;
            color: #333333;
        }
        #factionMember1 .layui-layer-title {
            background: #009f95;
            color: #fff;
            border: none;
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
                    <th class="text_c">帮手等级</th>
                    <th class="text_c">帮手名称</th>
                    <th class="text_c">回答数</th>
                    <th class="text_c">讨论数</th>
                    <th class="text_c">采纳回答</th>
                    <th class="text_c">状态</th>
                    <th class="text_c">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if factionMembers?? && (factionMembers?size > 0) >
                    <#list factionMembers as factionMember>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + factionMember_index + 1}</td>
                        <td align="center">${(factionMember.code)!}</td>
                        <td align="center">${(factionMember.name)!}</td>
                        <td align="center">
                            <#if factionMember.answers == 0>无限制<#else>${(factionMember.answers?c)!}</#if>
                        </td>
                        <td align="center">
                            <#if factionMember.discussions == 0>无限制<#else>${(factionMember.discussions?c)!}</#if>
                        <td align="center">
                            <#if factionMember.adoptions == 0>无限制<#else>${(factionMember.adoptions?c)!}</#if>
                        </td>
                        <td align="center">
                            <#if factionMember.status?? && factionMember.status>
                                <div class="btn btn-success btn-xs">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs">无效</div>
                            </#if>
                        </td>
                        <td align="center">
                            <a data-href="${ctx}/bangbang/factionMemberLevel/edit.php?id=${factionMember.id}" class="js_edit pn-opt">编辑</a> |
                            <#if factionMember.status>
                                <a data-id="${(factionMember.id)!}" data-status="false" data-href="${ctx}/bangbang/factionMemberLevel/save.php" class="js_status pn-opt">停用</a>
                            <#else>
                                <a data-id="${(factionMember.id)!}" data-status="true" data-href="${ctx}/bangbang/factionMemberLevel/save.php" class="js_status pn-opt">启用</a> |
                                <a data-href="${ctx}/bangbang/factionMemberLevel/delete/${factionMember.id}.php" class="js_delete pn-opt">删除</a>
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
    <div class="teacher-popup" id="factionMember1" style="width: 50%">
    </div>
<div class="bg"></div>
<#--</div>-->
<script data-main="${ctx}/js/abc/bangbang/factionMemberLevel/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>