<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/financed/invoicekjlog/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">请求流水号</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.fpqqlsh)!}" name="fpqqlsh">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发票号码</label>
                    <div class="layui-input-inline">
                        <input type="text" lay-verify="required|number" autocomplete="off" class="layui-input" value="${(BaseRq.fp_hm)!}" name="fp_hm" >
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a data-type="delSig" data-confirm="确定更新库存状态?" data-okMsg="更新库存状态成功!"
                   data-failMsg="更新库存状态失败" href="javascript:void(0);"
                   data-href="${ctx}/financed/invoicekjlog/check.php" class="layui-btn layui-btn-normal fr">更新库存状态</a>
            </div>
        </div>



        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th width="30">序号</th>
                    <th>发票请求流水号</th>
                    <th>发票代码</th>
                    <th>发票号码</th>
                    <th>发票校验码</th>
                    <th>开票日期</th>
                    <th>pdf下载地址</th>
                   <#-- <th>收票地址</th>-->
                    <th>同步状态</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if invoicekjlogRs??>
                    <#list invoicekjlogRs as invoicekjlog>
                    <tr>
                        <td>${(pagination.size!)?number*((pagination.page!)?number-1)+invoicekjlog_index+1}</td>
                        <td>${(invoicekjlog.FPQQLSH)!}</td>
                        <td>${(invoicekjlog.FP_DM)!}</td>
                        <td>${(invoicekjlog.FP_HM)!}</td>
                        <td>${(invoicekjlog.JYM)!}</td>
                        <td>${(invoicekjlog.KPRQ)!}</td>
                        <td><a class="ljc_00bcd4" href="${(invoicekjlog.PDF_URL)!}">pdf下载地址</a></td>
                        <#--<td>${(invoicekjlog.SP_URL)!}</td>-->
                        <td>
                            <#if invoicekjlog.TBSTATUS??&&invoicekjlog.TBSTATUS == "0">
                                未同步
                            <#elseif invoicekjlog.TBSTATUS??&&invoicekjlog.TBSTATUS == "1" >
                                已同步
                            </#if>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <table class="yy_fanye">
                <tbody>
                <tr>
                    <td align="center">
                        共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                        每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                        <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                        <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                        <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                        <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp;
                        当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                        <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                        <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
                        <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                        <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
                        <input type="hidden" value="${ctx}/orderchange/applications.php" id="currLink">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script data-main="${ctx}/js/abc/consumer/page" src="${ctx}/js/require.js"></script>
</body>
</html>
