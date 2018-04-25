<#assign ctx=request.getContextPath()>
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
	  <link rel="stylesheet" type="text/css" href="${ctx}/css/wangEditor.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
        var imgurl="${imgPth!}";
    </script>
    <style>
        input, textarea, select {font-family: inherit;font-size: inherit;font-weight: inherit;padding: 2px 10px;}
    </style>
</head>
<body>
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
    <form name="form1" action="${ctx}/vipgift/giftsave.php"  method="post"  class="layui-form">
        <table class="layui-table" lay-size="sm">
            <input type="hidden" name="id" value="${(vipgift.id)!}">
            <tr>
                <td width="200">礼物名称：</td>
                <td colspan="3">
                  <input type="text" data-rule='required;length[~200]'  style=" width:400px;" name="name" value="${(vipgift.name)!}"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="200">礼物图片：</td>
                <td colspan="3">
                   <img id="imgshow" height='126' width='206' style='margin-left:10px;max-width:206px' onerror="javascript:this.src='${ctx}/images/default.jpg';"
                        <#if vipgift??>
                        src='${imgPth!}${(vipgift.imageUrl)!}'
                        <#else>
                        src='${ctx}/images/default.jpg'
                        </#if> />
                   <input type="file" id="uploadFile" name="uploadFile" data-type="jpg;jpeg;png;gif;bmp"><label style='color:red;'>*</label>
                   <button style="height:26px;line-height:13px;" id="upload_btn" type="button" class="layui-btn">上传</button>
                   <label id="uploadMsg" class="uploadMsg" style="color:red"></label>
			       <input type="hidden" id="imageUrl" name="imageUrl"  data-rule="required;length[~200]" value='${(vipgift.imageUrl)!}'>
			       <span style="font-size:12px; color: #999;">温馨提示：可以点击上传选择图片，图片大小不能超出200KB（jpg、png、bmp）</span>
                </td>
            </tr>
            <tr>
                <td width="200">礼物简介：</td>
                <td colspan="3">
                    <textarea id="introduction" name="introduction"  data-rule="required;length[~300]" rows="3" class="layui-textarea" style="width: 60%">${(vipgift.introduction)!}</textarea><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="200">会员等级：</td>
                <td colspan="3">
                  <div style="width:330px;">
                    <select name="category" style="width:200px;height:30px;">
                        <option <#if vipgift?? && vipgift.category?? && vipgift.category=='VIP1'>selected</#if> value="VIP1">银卡会员</option>
                        <option <#if vipgift?? && vipgift.category?? && vipgift.category=='VIP2'>selected</#if> value="VIP2">金卡会员</option>
                        <option <#if vipgift?? && vipgift.category?? && vipgift.category=='VIP3'>selected</#if> value="VIP3">钻石会员</option>
                        <option <#if vipgift?? && vipgift.category?? && vipgift.category=='VIP4'>selected</#if> value="VIP4">超级会员</option>
                    </select>
                  </div>
                </td>
            </tr>
            <tr>
                <td width="200">排序：</td>
                <td colspan="3">
                    <input type="text" data-rule="required;allint" name="sort" value="${(vipgift.sort?c)!}" style=" width:200px;"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="200">库存：</td>
                <td colspan="3">
                    <input type="text" data-rule="required;allint" name="stock" value="${(vipgift.stock?c)!}" style=" width:200px;"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="200">销售价格：</td>
                <td colspan="3">
                    <input type="text" data-rule="required;amount" name="sellingPrice" value="${(vipgift.sellingPrice?c)!}" style=" width:200px;"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="200">成本价格：</td>
                <td colspan="3">
                    <input type="text" data-rule="required;amount" name="costPrice" value="${(vipgift.costPrice?c)!}" style=" width:200px;"><label style='color:red;'>*</label>
                </td>
            </tr>
            <tr>
                <td width="200">礼物状态：</td>
                <td colspan="3">
                   <input type="radio" name="status" value="2" title="上架" <#if vipgift?? && vipgift.status?? && vipgift.status == '2'>checked</#if> >
                   <input type="radio" name="status" value="1" title="下架" <#if !vipgift?? || (vipgift.status?? && vipgift.status == '1')>checked</#if> >
                   <#-- <input type="radio" name="status" value="0" title="删除" <#if vipgift?? && vipgift.status?? && vipgift.status == '0'>checked</#if> > -->
                </td>
            </tr>
            <tr>
                <td width="200">第三方产品详情：</td>
                <td colspan="3">
                    <input type="text" name="detailUrl" data-rule="url;" style=" width:400px;" value="${(vipgift.detailUrl)!}">
                </td>
            </tr>
            <tr>
                <td width="200">详细介绍：</td>
                <td colspan="3">
                    <div id="_topic_description_area" name="details" ><p>${(vipgift.details)!}</p></div>
                </td>
            </tr>
            <tr>
	          <td width="120"></td>
	          <td colspan="3"><input type="button" id="consumer_submit" name="button" value="提交" class="layui-btn">
	            <a href="javascript:location.href=document.referrer" style="text-decoration:none;" class="layui-btn layui-btn-primary">返回</a></td>
	        </tr>
        </table>
    </form>
</div>
<script data-main="${ctx}/js/abc/gift/gift" src="${ctx}/js/require.js"></script>
</body>
</html>