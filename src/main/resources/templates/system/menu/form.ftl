<html>
<head>
<meta charset="utf-8">
<title></title>
<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
</head>
<body>
<input type="hidden" class="js_currLink" value="${currLink!}">
<div class="container-fluid m_top_30 nycon_list sys_mod_add">
  <form name="form" action="${ctx}/system/menu/save.php" method="post">
      <table class="layui-table" lay-size="sm">
      <input type="hidden" name="menuId" value="${(menu.menuId)!}">
      <input type="hidden" name="status" value="true">
      <br>
      <tr>
        <td width="90">类型</td>
        <td width="150">
            <input id="contentRadio" name="type" type="radio" value="1" <#if menu??><#if menu.type=='1'>checked</#if><#else>checked</#if>>
            <label for="contentRadio">目录</label>
        </td>
        <td width="150">
            <input id="menuRadio" name="type" type="radio" value="2" <#if menu?? && menu.type=='2'>checked</#if>>
            <label for="menuRadio">菜单</label>
        </td>
        <td width="150">
            <input id="btnRadio" name="type" type="radio" value="3" <#if menu?? && menu.type=='3'>checked</#if>>
            <label for="btnRadio">按钮</label>
        </td>
        <td></td>
      </tr>


      <tr id="td_menu_name">
        <td width="90">菜单名称</td>
        <td colspan="3"><input type="text" name="menuName" style=" width:330px;" value="${(menu.menuName)!}" required><span class="color_r2">*</span></td>
        <td></td>
      </tr>
      <tr id="td_parent_menu">
        <td width="90">上级菜单</td>
        <td colspan="3">
            <input id="parentName" type="text" style=" width:330px;" value="${(menu.parentName)!}"  readonly="readonly">
        </td>
        <td></td>
        <td hidden>
            <input name="parentId" type="text" style=" width:330px;"  value="${(menu.parentId)!}" hidden>
            <input name="parentType" type="text" style=" width:330px;"  hidden>
        </td>
      </tr>





      <!-- 如果是目录 不需要菜单URL -->
      <tr id="td_menu_url"  <#if !menu?? || menu.type=='1'>hidden</#if>>
        <td width="90">菜单URL</td>
        <td colspan="3"><input name="menuUrl" type="text" style=" width:330px;" value="${(menu.menuUrl)!}"></td>
        <td></td>
      </tr>
      <!-- 如果是目录 不需要授权标识-->
      <tr id="td_perms" <#if !menu?? || menu.type=='1'>hidden</#if>>
        <td width="90">授权标识</td>
        <td colspan="3"><input name="perms" type="text" style=" width:330px;" value="${(menu.perms)!}"></td>
        <td></td>
      </tr>
      <!-- 如果是按钮 不需要排序号-->
      <tr id="td_sort" <#if menu?? && menu.type=='3'>hidden</#if>>
        <td width="90">排序号</td>
        <td colspan="3"><input name="sort"  type="text" style=" width:330px;" value="${(menu.sort?c)!}"></td>
        <td></td>
      </tr>
      <!-- 如果是菜单和按钮 不需要图标-->
      <tr id="td_icon" <#if menu?? && "2,3"?contains(menu.type)>hidden</#if>>
        <td width="90">图标</td>
        <td colspan="3">
            <input name="icon" type="text" style=" width:330px;" value="${(menu.icon)!}">
            <a target="_blank" href="http://v3.bootcss.com/components/">可用的图标</a>
        </td>
        <td></td>
      </tr>






      <tr>
        <td></td>
        <td colspan="3">
            <input  name="button"  value="提交" class="js_save_btn btn btn-info btn-sm w_55">
            <a href="javascript:;" class="btn btn-default js_back">返回</a>
        </td>
        <td></td>
      </tr>
    </table>
  </form>
</div>
<#if menus?? && (menus?size > 0) >
<div hidden>
    <ul>
      <#list menus as menu>
          <#if menu.type != '3'>
            <li class="menu_li" data-menu-id="${menu.menuId!}" data-menu-pid="${menu.parentId!}" data-menu-name="${menu.menuName!}" data-menu-type="${menu.type!}"></li>
          </#if>
    </#list>
    </ul>
</div>
</#if>
<div class="main_modal container-fluid row js_pop_menu" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">请选择上级菜单
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <div id="treeDemo" class="ztree"></div>
        </div>
        <div>
            <input value="确认" class="js_close btn btn-info">
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/system/menu/form" src="${ctx}/js/require.js"></script>
</body>
</html>