<#assign ctx=request.getContextPath()>
<html>
<head>
    <meta charset="utf-8">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/tagEditor/jquery.tagsinput.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>

</head>
<!--查看用户信息-->
<@shiro.hasPermission name="consumer:query">
    <#assign canQuery=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <form action="${ctx}/consumerManager/consumer/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.username)!}" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.realName)!}" name="realName" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.phone)!}" name="phone" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query"  data-val="0"  class="layui-btn">查询</div>
                        <div id="consumer_more" class="layui-btn layui-btn-normal">更多条件</div>
                    </div>
                </div>

                <div class='moretj' style='display:none;'>
                    <div class="layui-inline">
                        <label class="layui-form-label">注册时间</label>
                        <div class="layui-input-inline">
                        <#--<input type="text" id="test3" value="${(BaseRq.createTime)!}" name="createTime" class="layui-input">-->
                            <select name="datetype"  id="datetype"lay-filter="datetype">
                                <option value=""></option>
                                <option <#if BaseRq.datetype?? && BaseRq.datetype == '1'>selected</#if>  value="1">今天</option>
                                <option <#if BaseRq.datetype?? && BaseRq.datetype == '2'>selected</#if> value="2">昨天</option>
                                <option <#if BaseRq.datetype?? && BaseRq.datetype == '3'>selected</#if> value="3">最近7天</option>
                                <option <#if BaseRq.datetype?? && BaseRq.datetype == '4'>selected</#if> value="4">最近30天</option>
                                <option <#if BaseRq.datetype?? && BaseRq.datetype == '5'>selected</#if> value="5">时间段</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">注册时间起</label>
                        <div class="layui-input-inline"  id="startDate">
                            <input type="text" class="layui-input" id="test30" value="${(BaseRq.startDate)!}" name="startDate" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">注册时间止</label>
                        <div class="layui-input-inline" id="endDate">
                            <input type="text" class="layui-input" id="test31" value="${(BaseRq.endDate)!}" name="endDate">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">昵称</label>
                        <div class="layui-input-inline">
                            <input type="text" value="${(BaseRq.nickname)!}" name="nickname" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">积分</label>
                        <div class="layui-input-inline" style="width: 90px">
                            <select name="pointsOper"  id="pointsOper"lay-filter="pointsOper" style="width: 10px">
                                <option value=""></option>
                                <option <#if BaseRq.pointsOper?? && BaseRq.pointsOper == 'lte'>selected</#if>  value="lte">小于等于</option>
                                <option <#if BaseRq.pointsOper?? && BaseRq.pointsOper == 'equals'>selected</#if> value="equals">等于</option>
                                <option <#if BaseRq.pointsOper?? && BaseRq.pointsOper == 'gte'>selected</#if> value="gte">大于等于</option>
                            </select>
                        </div>
                        <div class="layui-input-inline"style="width: 50px">
                            <input type="text" value="${(BaseRq.points)!}" name="points" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">经验值</label>
                        <div class="layui-input-inline"style="width: 90px">
                            <select name="expOper"  id="expOper"lay-filter="expOper">
                                <option value=""></option>
                                <option <#if BaseRq.expOper?? && BaseRq.expOper == 'lte'>selected</#if>  value="lte">小于等于</option>
                                <option <#if BaseRq.expOper?? && BaseRq.expOper == 'equals'>selected</#if> value="equals">等于</option>
                                <option <#if BaseRq.expOper?? && BaseRq.expOper == 'gte'>selected</#if> value="gte">大于等于</option>
                            </select>
                        </div>
                        <div class="layui-input-inline"style="width: 50px">
                            <input type="text" value="${(BaseRq.exp)!}" name="exp" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">会员等级</label>
                        <div class="layui-input-inline">
                            <select name="vipLevel" class="cxinput" id="vipLevel">
                                <option value=""></option>
                            <#if levels?? && ( levels?size gt 0 )>
                                <#list levels as level>
                                    <option
                                        <#if BaseRq.vipLevel ?? && (BaseRq.vipLevel == level.levelCode)>selected</#if>
                                        value="${level.levelCode}">${level.level}</option>
                                </#list>
                            </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">用户状态</label>
                        <div class="layui-input-inline">
                            <select name="status" class="cxinput" id="status">
                                <option value=""></option>
                                <option <#if BaseRq.status?? && BaseRq.status>selected</#if>  value="true">启用</option>
                                <option <#if BaseRq.status?? && !BaseRq.status>selected</#if> value="false">停用</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">已选标签</label>
                        <div class="layui-input-inline" style="width:440px;">
                            <input type="text" name="tagName" id="tagnames" value="${(BaseRq.tagName)!}"
                                   placeholder="请搜索选择标签" class="layui-input">
                        </div>

                    </div>
                    <div id="tagslect" class="layui-btn">选择标签</div>
                    <div id="tagdelall" class="layui-btn layui-btn-danger">清空标签</div>

                </div>
            </div>
            <div class="nycon_list_b">
                <table class="layui-table" lay-size="sm">
                    <thead class="pn-lthead">
                    <tr>
                        <th>序号</th>
                        <th width="28">全选</th>
                        <th>用户名</th>
                        <th>昵称</th>
                        <th>真实姓名</th>
                        <th>手机号</th>
                        <th>积分</th>
                        <th>经验值</th>
                        <th>礼包金额(元)</th>
                        <th>用户等级</th>
                        <th>会员等级</th>
                        <th>用户状态</th>
                        <th>注册时间</th>
                        <th>操作选项</th>
                    </tr>
                    </thead>
                    <tbody class="pn-ltbody">
                    <#if consumers?? && ( consumers?size gt 0 )>
                        <#list consumers as consumer>
                        <tr>
                            <td width='30' class="td_i">${BaseRq.offset + consumer_index + 1}</td>
                            <td><input class="js_checkbox" name="ids" type="checkbox" lay-skin="primary"  value="${consumer.id}" lay-skin="primary"></td>
                            <td>
                            <#if canQuery??>
                                <a class="ljc_00bcd4" data-type="opendialog" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${consumer.id}">${(consumer.username)!}</a>
                            <#else>
                                ${(consumer.username)!}
                            </#if>
                            </td>
                            <td>${(consumer.nickname)!}&nbsp;<i class="glyphicon  glyphicon-info-sign" id="sjlog" data-url="${ctx}/consumerManager/consumer/sjLog.php?userId=${consumer.id}"></i></td>
                            <td>${(consumer.realName)!}</td>
                            <td>${(consumer.phone)!}</td>
                            <td><a class="ljc_00bcd4" title="查看积分记录" id="pointsLog" data-type="opendialog" href="javascript:;" data-url="${ctx}/consumerManager/consumer/pointsLog.php?userId=${consumer.id}"
                                   data-val="1"      <#--href="${ctx}/consumerManager/consumer/pointsLog.php?userId=${consumer.id}"-->>${(consumer.points)!}</a>
                            </td>
                            <td><a class="ljc_00bcd4" title="查看经验值记录" data-type="opendialog" data-val="2" href="javascript:;" data-url="${ctx}/consumerManager/consumer/expLog.php?userId=${consumer.id}"
                            <#--href="${ctx}/consumerManager/consumer/expLog.php?userId=${consumer.id}"-->>${(consumer.exp)!}</a>
                            </td>
                            <td><a class="ljc_00bcd4" title="查看礼包金额" data-type="opendialog" data-val="5" href="javascript:;" data-url="${ctx}/consumerManager/consumer/amountLog.php?userId=${consumer.id}"
                            >${(consumer.amount)!}</a>
                            </td>
                            <td>${(consumer.medal)!}</td>
                            <td>
                                <a class="ljc_00bcd4" title="查看会员记录" data-type="opendialog" data-val="3" href="javascript:;" data-url="${ctx}/consumerManager/consumer/vipLog.php?userId=${consumer.id}"
                                <#-- href="${ctx}/consumerManager/consumer/vipLog.php?userId=${consumer.id}"-->>
                                    <#if levels?? && ( levels?size gt 0 )>
					              <#list levels as level>
                                        <#if level.levelCode == consumer.vipLevel>${level.level!}</#if>
                                    </#list>
			                    </#if>
                                </a>
                            </td>
                            <td>
                                <#if consumer.status?? && consumer.status >
                                    <div class="btn btn-success btn-xs ">启用</div>

                                <#else>
                                    <div class="btn btn-danger btn-xs ">停用</div>
                                </#if>
                            </td>
                            <td>${(consumer.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>

                            <td>
                                <@shiro.hasPermission name="uc_user:edit">
                                    <#if consumer.status?? && consumer.status>
                                        <a data-url="${ctx}/consumerManager/consumer/${consumer.id}?status=false"
                                           type="PATCH" abc-type="是否停用?" class="pn-opt">停用</a>
                                    <#else>
                                        <a data-url="${ctx}/consumerManager/consumer/${consumer.id}?status=true"
                                           type="PATCH" abc-type="是否启用?" class="pn-opt">启用</a>
                                    </#if>|
                                    <a class="pn-opt xiugaishouji" data-userid="${(consumer.id)!}" data-nickname='${(consumer.username)!}' data-ylphone='${(consumer.phone)!}'>修改</a>
                                </@shiro.hasPermission>
                                <#--<a data-type="opendialog" data-val="4" href="javascript:;" data-url="${ctx}/consumerManager/consumer/info.php?id=${consumer.id}"
                                &lt;#&ndash;href="${ctx}/consumerManager/consumer/info.php?id=${consumer.id}"&ndash;&gt;>详细</a>-->
                                <#--<#if consumer.status?? && consumer.status >-->
                                    |&nbsp;<a data-type="opendialog" data-val="5" href="javascript:;" data-url="${ctx}/consumerManager/consumer/userTags.php?id=${consumer.id}"
                                <#--href="${ctx}/consumerManager/consumer/userTags.php?id=${consumer.id}"-->
                                        class="pn-opt">标签</a>
                                <@shiro.hasPermission name="uc_user:resetpwd">
                                    |&nbsp;<a data-url="${ctx}/consumerManager/consumer/${consumer.id}?status=false" data-userid="${(consumer.id)!}"
                                              data-type="resetpwd" abc-type="是否重置密码,默认密码是?" class="pn-opt">重置密码</a>
                                </@shiro.hasPermission>
                                <#--</#if>-->
                            </td>


                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
                <div class="button_caozuo_fenye">
                <@shiro.hasPermission name="uc_user:edit">
                    <div class="layui-btn js_selectAll" data-check=false>全选</div>
                    <a href="javascript:void(0);" class="layui-btn" id="batchTags">批量打标签</a>
                </@shiro.hasPermission>
                </div>
                <table class="yy_fanye">
                    <tbody>
                    <tr>
                        <td align="center">
                            共&nbsp;${BaseRq.totalItems}&nbsp;条&nbsp;&nbsp;
                            每页<input maxlength="2" style="width:40px" name="size" value="${BaseRq.size}" id="consumer_size"data-val="0"
                                     type="text">条&nbsp;&nbsp;
                            <input class="btn btn-default btn-xs" value="首 页" id="consumer_first" data-val="0" type="button">
                            <input class=" btn btn-default btn-xs" value="上一页" id="consumer_up"data-val="0"  type="button">
                            <input class=" btn btn-default btn-xs" value="下一页" id="consumer_down"data-val="0"  type="button">
                            <input class=" btn btn-default btn-xs" value="尾 页" id="consumer_last" data-val="0" type="button">&nbsp;&nbsp;
                            当前&nbsp;${(BaseRq.page?c)!}/${(BaseRq.totalPage?c)!}&nbsp;页&nbsp;&nbsp;转到第
                            <input style="width:50px" onfocus="this.select();" id="consumer_go" value="${(BaseRq.page?c)!}"
                                   type="text"> 页
                            <input class=" btn btn-default btn-xs" value="转" id="consumer_gogo"data-val="0"  type="button">
                            <input type="hidden" name="page" id="cupageVal" value="${(BaseRq.page?c)!}">
                            <input type="hidden" name="tpage" id="topageVal" value="${(BaseRq.totalPage?c)!}">
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
                <label><h3>标签</h3></label>
                <select style="height:30px;margin-left:30px;" id="seletablib">
                    <option value="">所有标签类型</option>
                <#if taglib?? && ( taglib?size gt 0 )>
                    <#list taglib as lib>
                        <option value="${lib.fieldValue}">${lib.fieldKey}</option>
                    </#list>
                </#if>
                </select>
            </div>
            <div class="modal-body">
                        <#if taglib?? && ( taglib?size gt 0 )>
                            <#list taglib as lib>
                                <div class="tag-list__itemWraper itemWraper1" id="tag_${lib.fieldValue}">
                                    <h3 class="h5 tag-list__itemheader">${lib.fieldKey}</h3>
                                    <ul class="tag-list__itembody taglist--inline multi">
                                        <#if tags?? && ( tags?size gt 0 )>
                                            <#list tags as tag>
                                                <#if lib.fieldValue == tag.category  && tag.status>
                                                    <li><a id="taglib" class="btn tag1" data-type="unselect"
                                                           data-id="${tag.tagName!}">${tag.tagName!}</a></li>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>

                                <#if (lib_index+1)%3==0>
                                    <div style="clear: both;height:0px;"></div>
                                </#if>
                            </#list>
                        </#if>
                            <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal2" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <label><h3>标签</h3></label>
                <select style="height:30px;margin-left:30px;" id="seletablib2">
                    <option value="">所有标签类型</option>
                <#if taglib?? && ( taglib?size gt 0 )>
                    <#list taglib as lib>
                        <option value="${lib.fieldValue}">${lib.fieldKey}</option>
                    </#list>
                </#if>
                </select>
            </div>
            <div class="modal-body">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <td>
                            <div class="tag-list__itemWraper itemWraper3">
                                <h3 class="h5 tag-list__itemheader">已选择标签</h3>
                                <textarea id="taged-s" rows="4" cols="60"></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <#if taglib?? && ( taglib?size gt 0 )>
                            <#list taglib as lib>
                                <div class="tag-list__itemWraper itemWraper2" id="tag2_${lib.fieldValue}">
                                    <h3 class="h5 tag-list__itemheader">${lib.fieldKey}</h3>
                                    <ul class="tag-list__itembody taglist--inline multi" style="padding: 10px 0;">
                                        <#if tags?? && ( tags?size gt 0 )>
                                            <#list tags as tag>
                                                <#if lib.fieldValue == tag.category && tag.status>
                                                    <li><a id="taglib2" class="btn tag1" data-taged
                                                           data-lib="${lib.fieldValue}" data-id="${tag.id!}"
                                                           data-name="${tag.tagName!}">${tag.tagName!}</a></li>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                                <#if (lib_index+1)%3==0>
                                    <div style="clear: both;height:0px;"></div>
                                </#if>
                            </#list>
                        </#if>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-del="modal" class="layui-btn layui-btn-normal">清空</button>
                <button type="button" class="layui-btn" data-yes="modal">保存</button>
                <button type="button" class="layui-btn layui-btn-primary" data-dis="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <iframe id="consumer_frame"   style="width: 100%;height: 500px;border:0" border="0" frameboder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismi="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal4" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-disxgsj="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改手机号码和用户名</h4>
            </div>
            <div class="modal-body">
                <table class="layui-table" lay-size="sm">
                    <form name="xiugaihm" id="xiugaihm"  action="${ctx}/financed/goodeditsave.php"  enctype="multipart/form-data" method="post">
                        <tbody>
                        <tr>
                            <td>用户名</td>
                            <td>
                                <input type="text" id='xghmnc'>
                            </td>
                        </tr>
                        <tr>
                            <td>原手机号码</td>
                            <td id='xghmyhm'></td>
                        </tr>
                        <tr>
                            <td>新手机号码</td>
                            <td>
                                <input type="hidden" id="xghmyuserid">
                                <input type="text" id="newphone" name="newphone" data-rule="length[11]">
                                <label style="color:orange">不填默认清空号码</label>
                            </td>
                        </tr>
                        <tr>
                            <td>修改备注</td>
                            <td>
                                <textarea name="reason" id="reason" data-rule="required;" rows="3" cols="60" placeholder="请录入修改原因和用户身份核实情况" ></textarea><label style='color:red;'>*</label>
                                <#--<input type="text" id="reason" name="reason" placeholder="请录入修改原因和用户身份核实情况" data-rule="required;length[11]">-->
                            </td>
                        </tr>
                        </tbody>
                    </form>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-normal" data-xgsj="modal">保存</button>
                <button type="button" class="layui-btn layui-btn-primary" data-disxgsj="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main_modal container-fluid" id="myModal5" tabindex="-1" hidden>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-resetpwd="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">重置密码</h4>
            </div>
            <div class="modal-body">
                <table class="layui-table" lay-size="sm">
                    <form name="xiugaihm" id="xiugaihm"  action="${ctx}/financed/goodeditsave.php"  enctype="multipart/form-data" method="post">
                        <tbody>
                        <tr>
                            <td>修改备注</td>
                            <td>
                                <input type="hidden" id="userid">
                                <textarea name="reason" id="pwdreason" data-rule="required;" rows="3" cols="60" placeholder="请录入重置原因" ></textarea><label style='color:red;'>*</label>
                            <#--<input type="text" id="reason" name="reason" placeholder="请录入修改原因和用户身份核实情况" data-rule="required;length[11]">-->
                            </td>
                        </tr>
                        </tbody>
                    </form>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-normal" data-reset="modal">保存</button>
                <button type="button" class="layui-btn layui-btn-primary" data-resetpwd="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/userpage" src="${ctx}/js/require.js"></script>
</body>
</html>
