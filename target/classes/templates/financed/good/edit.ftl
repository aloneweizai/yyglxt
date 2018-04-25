<#assign ctx=request.getContextPath()>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>htweb</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
  <script type="text/javascript">
        var ctx = "${ctx}";
		var imgurl="${imgPth}";
		var look="${look}";
    </script>
  <style>
	 input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
	 input[type='text'], select{height:30px;}
	 .table>tbody>tr>td{vertical-align:middle}
  </style>
</head>
<body scoll=no>
  <div class="container-fluid m_top_30 nycon_list sys_mod_add">
	<#--
    <table class="table  table-hover">
      <tr style="background:#f5f5f5">
        <td height="30">&nbsp;当前位置：&nbsp;
          <a href="${ctx}/financed/goodList.php">
            <u>商品管理</u>
          </a> &gt;&gt; 修改商品</td>
      </tr>
    </table>
	-->
    <form name="form1"  action="${ctx}/financed/goodeditsave.php" next-url="${ctx}/financed/goodList.php" enctype="multipart/form-data" method="post">
        <table class="layui-table" lay-size="sm">
		<#--<tr>
          <td width="120">商品性质</td>
          <td colspan="3" >
			  <select id="goodsType"  name="goodsType" style="width:200px;height:30px;">
				<#if goodsType?? && (goodsType?size > 0) >
        		  <#list goodsType as types>
        			 <option value="${types.fieldValue}" <#if good.goodsType==types.fieldValue>selected</#if> >${types.fieldKey}</option>
        		  </#list>
        		</#if>
			  </select>
          </td>
          <td></td>
        </tr>-->
		<#--<tr id='viplevel' <#if good.goodsType !="4"> style='display:none;' </#if> >
          <td width="120">会员等级</td>
          <td colspan="3" >
			  <select id="memberLevel" name="memberLevel" style="width:200px;height:30px;">
                <#if levelRs?? && (levelRs?size > 0) >
                  <#list levelRs as viplevel>
        			 <option value="${viplevel.levelCode}!${viplevel.level}" <#if good.memberLevel?? && good.memberLevel == viplevel.levelCode>selected</#if>>${viplevel.level}</option>
        		  </#list>
        		</#if>
			  </select>
          </td>
          <td></td>
        </tr>-->
        <tr>
          <td width="120">商品名称<input type="hidden" name="id" id="goodId" value="${good.id!}"></td>
          <td colspan="3"><input type="text" id="name" name="name" value="${good.name!}" data-good data-rule="required;length[~200]" style="width:600px;"><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
        <tr>
          <td width="90" style="height:100px;">商品图片</td>
		  <td width="100" id="imgshow"><img height='90' width='90' style='margin-left:10px;' src='${imgPth}${good.imageUrl!}' /></td>
          <td colspan="3" id="uploadFiletd">
			<input type="file" id="uploadFile" name="uploadFile" data-type="jpg;jpeg;png;gif;bmp"><label style='color:red;'>*</label>
			<button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
			<label id="uploadMsg" class="uploadMsg" style="color:red"></label>
			<input type="hidden" id="imageUrl" name="imageUrl" value="${good.imageUrl!}" data-good  data-rule="required;length[~200]">
			<span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200KB（jpg、png、bmp）</span>
          </td>
          
        </tr>
        <tr>
          <td width="120">简单介绍</td>
          <td colspan="3"><textarea id="introduction" name="introduction" data-good  data-rule="required;length[~2000]" rows="3" cols="60">${good.introduction!}</textarea><label style='color:red;'>*</label></td>
          <td></td>
        </tr>
		
        <tr>
          <td width="120">所属分类</td>
          <td colspan="3">
             <input type="text" class="layui-input" style="width:330px;" readonly name="categoryName" value="${good.categoryName!}"><label style='color:red;'>*</label>
			 <input type="hidden" class="layui-input" style="width:330px;" name="categoryId" data-rule="required;" data-good  value="${good.categoryId!}">
          </td>
          <td></td>
        </tr>
		<tr>
          <td width="120">商品开票内容</td>
          <td colspan="3" >
			  <select name="invoiceContent" style="width:200px;height:30px;">
                <#if invoicecontent?? && (invoicecontent?size > 0) >
        		  <#list invoicecontent as content>
        			 <option value="${content.fieldValue}" <#if good.invoiceContent??&&good.invoiceContent == content.fieldValue>selected</#if>>${content.fieldKey}</option>
        		  </#list>
        		</#if>
			  </select>
          </td>
          <td></td>
        </tr>
        <tr>
          <td width="120">是否上架</td>
          <td width="150"><input type="radio" name="status" value="true" title="是" <#if good.status??&& good.status>checked</#if> >是
          </td>
          <td width="150"><input type="radio" name="status" value="false" title="否" <#if good.status??&& !good.status>checked</#if>>否
          </td>
		  <td></td>
		  <td></td>
        </tr>
        <tr>
          <td width="120">是否需要寄送</td>
          <td width="150"><input type="radio" name="isShipping" value="1" id="isShipping1"title="是" <#if good.isShipping??&& good.isShipping==1>checked</#if>>是
          </td>
          <td width="150"><input type="radio" name="isShipping" value="2" id="isShipping2"title="否" <#if good.isShipping??&& good.isShipping==2>checked</#if>>否
          </td>
		  <td></td>
		  <td></td>
        </tr>
        <tr>
          <td width="120">是否免运费</td>
          <td width="150"><input type="radio" name="isFreeShipping" value="1" title="是" <#if good.isFreeShipping??&& good.isFreeShipping==1>checked</#if>>是
          </td>
          <td width="150"><input type="radio" name="isFreeShipping" value="2" title="否" <#if good.isFreeShipping??&& good.isFreeShipping==2>checked</#if>>否
          </td>
		  <td></td>
		  <td></td>
        </tr>
          <tr>
              <td width="120">是否可以退货</td>
              <td width="150"><input type="radio" name="isReturn" value="0" title="是"<#if good.isReturn??&& good.isReturn=="0">checked</#if>>是
              </td>
              <td width="150"><input type="radio" name="isReturn" value="1" title="否"<#if !(good.isReturn??&& good.isReturn=="0")>checked</#if>>否
              </td>
              <td></td>
              <td></td>
          </tr>
          <tr>
              <td width="120">是否可以换货</td>
              <td width="150"><input type="radio" name="isExchange" value="0" title="是"<#if good.isExchange??&& good.isExchange=="0">checked</#if>>是
              </td>
              <td width="150"><input type="radio" name="isExchange" value="1" title="否"<#if !(good.isExchange??&& good.isExchange=="0")>checked</#if>>否
              </td>
              <td></td>
              <td></td>
          </tr>
        <tr>
          <td width="120">交易方式</td>
          <td width="150"><input type="radio" name="tradeMethod" value="RMB" title="人民币" <#if good.tradeMethod??&& good.tradeMethod=="RMB">checked</#if>>人民币
          </td>
          <td width="150"><input type="radio" name="tradeMethod" value="POINTS" title="积分" <#if good.tradeMethod??&& good.tradeMethod=="POINTS">checked</#if>>积分
          </td>
		  <td></td>
		  <td></td>
        </tr>
		<tr>
          <td width="120">购买成功赠送积分</td>
          <td width="150"><input type="text" data-good  data-rule="required;integer;" name="giftPoints" value="${good.giftPoints?c}"  style=" width:100px;"><label style='color:red;'>*</label>
          </td>
          <td width="150">
          </td>
		  <td></td>
		  <td></td>
        </tr>
		<tr>
          <td width="120">排序</td>
          <td width="150"><input type="text" data-good  data-rule="required;allint;" name="sort" value='${good.sort!}' style="width:100px;"><label style='color:red;'>*</label>
          </td>
          <td width="150">
          </td>
		  <td></td>
		  <td></td>
        </tr>
        
        <tr>
          <td width="120">基本数据</td>
          <td colspan="4">
            <table class="table table-bordered" style="width:auto" id="guigeTB">
              <tr id="guigeTH">
				<td width="250">成本价格</td>
                <td width="250">市场价格</td>
                <td width="250">销售价格</td>
				<td>操作</td>
				<td width="100" style="display:none;">库存</td>
				<td width="100" style="display:none;">重量</td>
              </tr>
			  <tbody id="guigeTY">
			  <tr trindex='0'>
				<td trindex='0' width="100"><input type="text" data-did id="costPrice" value='0' style="width:90px;"> </td>
                <td trindex='0' width="100"><input type="text" data-did id="marketPrice" value='0' style="width:90px;"> </td>
                <td trindex='0' width="240"><input type="text" data-di id="sellingPrice" value='0' style="width:90px;"></td><td> <a class="btn btn-primary btn-sm" data-alert='alertDiv'><i class="glyphicon glyphicon-plus">会员组价格</i></a></td>
				<td trindex='0' style="display:none;" width="100"><input type="text" id="stock" value='0' style="width:90px;"> </td>
				<td trindex='0' style="display:none;" width="100"><input type="text"  id="weight" value='0' style="width:90px;"> </td>
              </tr>
              </tbody>
            </table>
           </td>
        </tr>
        <tr id='guigetr'>
          <td width="120">规格</td>
          <td colspan="4">
            <select id="bigGoodrule">
			   <option value="">选择规格</option>
               <#if goodrule?? && (goodrule?size > 0) >
        		  <#list goodrule as rule>
        			 <option value="${rule.fieldValue}">${rule.fieldKey}</option>
        		  </#list>
        		</#if>
            </select>
            <select id="smallGoodrule"><option value="">选择规格数据</option></select>
            <a name="good_open_dic" href="javascript:void(0)"><i class="glyphicon glyphicon-plus btn btn-success" style='cursor:pointer;' id="addSysCode">新增规格</i></a>
          </td>
        </tr>
        <tr>
          <td width="120">商品推荐类型</td>
          <td colspan="4">
			<#if recommendType?? && (recommendType?size > 0) >
    		  <#list recommendType as type>
    			 <input name="recommendType" <#if good.recommendType??&& good.recommendType==type.fieldValue>checked</#if> <#if type_index gt 0 >style="margin-left:50px"</#if> type="radio" value="${type.fieldValue}">${type.fieldKey}
    		  </#list>
    		</#if> 
          </td>
        </tr>
        <tr>
          <td width="120">详细介绍</td>
          <td colspan="4">
            <div id="_topic_description_area" name="details"><p>${good.details!}</p></div>
          </td>
        </tr>
        <tr>
          <td width="120"></td>
          <td colspan="3"><input type="button" id="consumer_submit" name="button" value="提交" class="layui-btn">
            <a href="${referer}" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
        </tr>
      </table>
    </form>
  </div>
  
<!--会员价格弹出层-->
<div class="main_modal container-fluid" id="myModal" tabindex="-1"  hidden>
<div class="modal-dialog" role="document" style="z-index:20000;">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <h4 class="modal-title" id="myModalLabel">会员组价格(<span style="font-size:12px; color: #999;">温馨提示:会员折扣仅允许1~100的整数.</span>)</h4>
    </div>
    <div class="modal-body">
        <table class="layui-table" lay-size="sm">
        <tr>
          <td>会员名称</td>
          <td>会员折扣(%)</td>
          <td>会员价格</td>
          <td>赠送积分</td>
        </tr>
		<tbody id="jiageTB">
		<#if levelRs?? && (levelRs?size > 0) >
		  <#list levelRs as viplevel>
            <#if viplevel.levelCode??&&viplevel.levelCode != "VIP0">
			 <tr data-vip='${viplevel.levelCode!}'>
              <td>${viplevel.level!}<input type="hidden" value="${viplevel.levelCode!}" style="width:100px;" id="vipLevel"></td>
              <td><input type="text" size="3" maxlength="3" style="width:100px;" id="discount" value="0"> </td>
              <td><input type="text"  readonly   style="width:100px;" id="tradePrice" value="0"> </td>
              <td><input type="text" size="6" maxlength="6" style="width:100px;" id="giftPoints" value="0"> </td>
            </tr>
            </#if>
		  </#list>
		</#if>
		</tbody>
      </table>
    </div>
    <div class="modal-footer">
      <button tyclass="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
      <button type="button" class="layui-btn layui-btn-normal" data-save="modal">保存</button>
    </div>
  </div>
</div>
</div>
<!--会员价格弹出层 end-->

<!--商品分类树-->

<div class="main_modal container-fluid row js_pop_ztree" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">规则选择
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <input value="确认" class="js_close btn btn-info">
    </div>
</div>
<!--商品分类树 end-->
<script data-main="${ctx}/js/abc/financed/productEdit.js" src="${ctx}/js/require.js"></script>
</body>
</html>