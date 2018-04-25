<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/layui.css">
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
                    <label class="layui-form-label">敏感词汇</label>
                    <div class="layui-input-inline">
                        <input type="text" id="keywords" value="${RequestParameters["keywords"]!}" placeholder="请输入敏感词汇" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="js-query layui-btn" type="button">查询</button>
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
                    <th width="50">序号</th>
                    <th class="text_c">敏感词汇</th>
                    <th class="text_c">状态</th>
                    <th class="text_c">操作</th>
                </tr>
                </thead>
                <tbody class="js-body-tr pn-ltbody">
                <#if sensitiveWords?? && (sensitiveWords?size > 0) >
                    <#list sensitiveWords as sensitive>
                    <tr>
                        <td class="td_i">${pagerSpec.offset + sensitive_index + 1}</td>
                        <td align="center">${(sensitive.keywords)!}</td>
                        <td align="center">
                            <#if sensitive.status?? && sensitive.status>
                                <div class="btn btn-success btn-xs">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs">无效</div>
                            </#if>
                        </td>
                        <td align="center">
                            <a data-href="${ctx}/bangbang/sensitiveWords/edit.php?id=${sensitive.id}" class="js_edit pn-opt">编辑</a> |
                            <#if sensitive.status>
                                <a data-id="${(sensitive.id)!}" data-status="false" data-href="${ctx}/bangbang/sensitiveWords/save.php" class="js_status pn-opt">停用</a>
                            <#else>
                                <a data-id="${(sensitive.id)!}" data-status="true" data-href="${ctx}/bangbang/sensitiveWords/save.php" class="js_status pn-opt">启用</a> |
                                <a data-href="${ctx}/bangbang/sensitiveWords/delete/${sensitive.id}.php" class="js_delete pn-opt">删除</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        ${pageHtml!}
        </div>
    </form>
</div>
    <div class="teacher-popup" id="medal1" style="width: 65%">
    </div>
<div class="bg"></div>
<#--</div>-->
<script data-main="${ctx}/js/abc/bangbang/sensitiveWords/list.js?v=${.now?string("SSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>