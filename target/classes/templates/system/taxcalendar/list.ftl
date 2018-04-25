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
    <form action="${ctx}/taxcalendar/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">申报年份</label>
                    <div class="layui-input-inline">
                        <input type="text" id='queryY' value="${(BaseRq.sbnf)!}" name="sbnf" class="layui-input" readonly>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">申报年月</label>
                    <div class="layui-input-inline">
                        <input type="text" id='queryM' value="${(BaseRq.sbyf)!}" name="sbyf" class="layui-input" readonly>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button id="consumer_query" class="layui-btn">查询</button>
                    </div>
                </div>
                <a href="javascript:;"  class="layui-btn layui-btn-normal fr" id='addbsrl'>添加办税日历</a>
            </div>
        </div>
      <div class="nycon_list_b">
          <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
					<th width="30"></th>
                    <th>申报年月</th>
                    <th>日期范围</th>               
					<th>修改时间</th>
					<th>描述</th>
					<th>操作选项</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                    <#if taxCalendars?? && ( taxCalendars?size gt 0 )> 
					  <#list taxCalendars as taxCalendar>
					   <tr>
					     <td class="td_i">${BaseRq.offset + taxCalendar_index + 1}</td>
					     <td>${(taxCalendar.sbyf)!}</td>
					     <td>${(taxCalendar.sbrq)!}</td>
					     <td>${(taxCalendar.xgsj)!}</td>
					     <td>${(taxCalendar.description)!}</td>
					     <td>
					      <a data-do="applay_detail" data-info="${(taxCalendar.calId)!}|${(taxCalendar.sbyf)!}|${(taxCalendar.sbrq)!}|${(taxCalendar.description)!}" data-val="5" href="javascript:;" class="pn-opt">编辑</a>
					      |&nbsp;<a data-type="delSig" data-confirm="确认删除改办税日历?" data-okMsg="办税日历删除成功!" data-failMsg="办税日历删除失败" href="javascript:void(0);" data-href="${ctx}/taxcalendar/del.php?calId=${(taxCalendar.calId)!}" class="pn-opt">删除</a>
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
                             共&nbsp;${BaseRq.totalItems!}&nbsp;条&nbsp;&nbsp;
				每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size" type="text">条&nbsp;&nbsp;
                <input class="btn btn-default btn-xs"  value="首 页" id="consumer_first" type="button" >
                <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"  type="button">
                <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down" type="button">
                <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" type="button">&nbsp;&nbsp; 
				 当前&nbsp;${BaseRq.page!}/${BaseRq.totalPage!}&nbsp;页&nbsp;&nbsp;转到第
                <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}" type="text"> 页
                <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo" type="button">
				<input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
			    <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
				<input type="hidden" value="${ctx}/vipgift/list.php" id="currLink">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">办税日历编辑</h4>
            </div>
            <div class="modal-body">
                <form id="appapi" class="layui-form">
                    <table class="layui-table" lay-size="sm">
                       <tr>
                         <td width="100">申报年月：</td>
                         <td colspan="3">
                           <input type="hidden" name="calId">
                           <input type="text" id='sbnftime' readonly name="sbyfA" style=" width:200px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;"><label style='color:red;'>*</label>
                         </td>
                       </tr>
                       <tr>
                         <td width="100">日期范围：</td>
                         <td colspan="3">
                           <input type="text" maxlength ='5' name="sbrqA" style=" width:200px;font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;"><label style='color:red;'>*</label>
                           <br/>
                           <span style="font-size:12px; color: #999;">示例：申报年月为2018-01，申报开始日期为1号，结束日期为15号，则日期范围是：1,15(请用英文逗号)。</span>
                         </td>
                       </tr>
                       <tr>
                           <td width="100">备注：</td>
                           <td colspan="3">
                              <textarea name="remark" rows="3" class="layui-textarea" style="width: 95%"></textarea>
                           </td>
                       </tr>
                    </table>                 
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="layui-btn layui-btn-normal" data-save="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<script data-main="${ctx}/js/abc/system/taxcalendar" src="${ctx}/js/require.js"></script>
</body>
</html>