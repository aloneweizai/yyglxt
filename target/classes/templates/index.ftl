<#assign ctx=request.getContextPath()>
<html>
<head>
    <title>ABC财税平台运营管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/htcss.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/nicevalidator/jquery.validator.css">
    <link rel="icon" type="image/x-icon" href="${ctx}/favicon.ico">
    <script type="text/javascript">
        var ctx = "${ctx}";
		var isInitPassword = "${(currentUser.isInitPassword?c)!}";
    </script>
</head>
<body class="bg_blue">
<div class="mian_icen">
    <div id="main_itop_l">欢迎 ${Session.currentUser.username}</div><div id="main_itop_r">
    <div id="main_itop_l_ny">
        <div class="fl">
            <div id="main_itop_an">
                <button type="button"><span></span><span></span><span></span></button>
            </div>
        </div>
        <div class="logoname"><img src="${ctx}/images/cslogo.png"> ABC财税平台运营管理系统</div>
        <div class="fr">
            <ul class="main_ileft_li"><li><i class="glyphicon glyphicon-edit"></i><a class="js_alter_pw" href="javascript:void(0);">修改密码</a></li><li><i class="glyphicon glyphicon-log-out"></i><a class="js_loginout" href="javascript:void(0);">退出系统</a></li> </ul>
        </div>
        <div class="clear"></div>
    </div>
</div> <div class="clear"></div>
    <div id="main_ileft" class="leftbg">
        <div class="main_inav">
            <ul>
                <!-- menuNodes是顶级菜单集合 -->
                <#if menuNodes?? && (menuNodes?size > 0) >
                    <#list menuNodes as menuNode>
                        <#assign menu=menuNode.menu>
                        <#assign childNode = menuNode.childNode>
                        <#if menu?? && menu.type=="2"><!-- 如果1级 并且 是菜单类型 -->
                            <li>
                                <div class="main_inav_one clear">
                                    <a href="javascript:void(0)" onclick='addPanel("${menu.menuName}","${ctx}${menu.menuUrl}","1425"),1' >
                                        <samp class="${menu.icon}"></samp><samp class="main_inav_tit">${menu.menuName}</samp>
                                        <span class="fr glyphicon font_12 glyphicon-menu-right"></span>
                                    </a>
                                </div>
                            </li>
                        </#if>
                        <#if menu?? && menu.type=="1"><!-- 如果1级 并且 是目录类型 -->
                            <li>
                                <div class="main_inav_one clear">
                                    <a href="javascript:void(0)">
                                        <samp class="${menu.icon}"></samp>
                                        <ss>${menu.menuName}</ss>
                                        <span class="fr glyphicon glyphicon-menu-left glyphicon-menu-down font_12"></span>
                                    </a>
                                </div>
                                <#if childNode?? && (childNode?size > 0)>
                                    <dl>
                                        <#list childNode as node>
                                            <#assign childMenu = node.menu>
                                            <#if childMenu?? && childMenu.status==true && childMenu.type=="2">
                                                <dd><!-- 如果2级 并且 是菜单类型 -->
                                                    <a href="javascript:void(0)" onclick='addPanel("${childMenu.menuName}","${ctx}${childMenu.menuUrl}","1425"),1'>
                                                        <div class="yuan fl"></div>${childMenu.menuName}
                                                    </a>
                                                </dd>
                                            </#if>
                                            <#if childMenu??  && childMenu.status==true && childMenu.type=="1">
                                                <dd><!-- 如果2级 并且 是目录类型 -->
                                                    <div class="main_inav_add_t"><div class="yuan fl"></div>${childMenu.menuName}</div>
                                                    <#assign child2Nodes = node.childNode>
                                                    <#if child2Nodes?? && (child2Nodes?size > 0)>
                                                        <div class="main_inav_add_d">
                                                            <#list child2Nodes as child2Node>
                                                                <#assign child2Menu = child2Node.menu>
                                                                <#if child2Menu?? && child2Menu.status==true && child2Menu.type=="2">
                                                                    <div>
                                                                        <a href="javascript:void(0)" onclick='addPanel("${child2Menu.menuName}","${ctx}${child2Menu.menuUrl}","1200"),1'>
                                                                            <div class="nav_er fl"></div>${child2Menu.menuName}
                                                                        </a>
                                                                    </div>
                                                                </#if>
                                                            </#list>
                                                        </div>
                                                    </#if>
                                                </dd>
                                            </#if>
                                        </#list>
                                    </dl>
                                </#if>
                            </li>
                        </#if>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="main_ileft_tit">换肤</div>
        <div class="main_ileft_skin">
            <ul>
                <li class="bg_blue_x" data-value="bg_blue"></li>
                <li class="bg_yel_x" data-value="bg_yel"></li>
                <li class="bg_red_x" data-value="bg_red"></li>

                <li class="bg_gre_x" data-value="bg_gre"></li>
                <li class="bg_zi_x" data-value="bg_zi"></li>
            </ul>
        </div>
    </div>
    <div id="main_iright">
        <div id="main_iright_ny">
            <div id="tt" class="easyui-tabs">
                <div title="首页" data-options="closeable:true" >
                    <div class="main_idx"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>

<div class="main_modal container-fluid row js_pop_pw" hidden>
    <div class="main_modal_tc col-sm-3">
        <div class="main_modal_t">修改密码
            <div class="close fr js_close">&times;</div>
            <div class="clear"></div>
        </div>
        <div class="main_modal_tit">
            <form id="form_pw_alter" action="/login/alterPassword">
                <table class="table" cellspacing="0" cellpadding="0" border="0" >
                    <tr style="height: 50px">
                        <td align="right"><span class="color_r2">*</span>旧密码：</td>
                        <td><input name="oldPw" type="password"></td>
                    </tr>
                    <tr style="height: 60px">
                        <td align="right"><span class="color_r2">*</span>新密码：</td>
                        <td>
                            <input name="newPw" type="password">
                            <div style="">
                                <div id="pwd_Weak" class="pwd_cs pwd_c "></div>
                                <div id="pwd_Medium" class="pwd_cs pwd_c "></div>
                                <div id="pwd_Strong" class="pwd_cs pwd_c pwd_c_r "></div>
                                <div class="clear"></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right"><span class="color_r2">*</span>新密码确认：</td>
                        <td><input name="newPwConfirm" type="password" ></td>
                    </tr>
                </table class="table">
            </form>
        </div>
        <button class="btn btn-sm btn-info fr js_close" type="button" role="button" aria-disabled="false">
            <span>取消</span></button>
        <button class="btn btn-sm btn-info fr js_alter_pw_save" type="button" role="button" aria-disabled="false">
            <span>确认</span></button><div class="clear"></div>
    </div>
</div>
<script data-main="${ctx}/js/abc/index.js?v=4" src="${ctx}/js/require.js"></script>
</body>
</html>
