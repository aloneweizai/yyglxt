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

<div class="nycon_list_b">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">详情页面</h4>
            </div>
            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#home" data-toggle="tab">绑定详情</a></li>
                <li><a href="#jbxx" data-toggle="tab">纳税人基本信息</a></li>
                <li class="dropdown"><a href="#profile" data-toggle="tab">投资方信息</a></li>
                <li><a href="#ios" data-toggle="tab">扣款银行信息</a></li>
                <li><a href="#jmeter" data-toggle="tab">登记注册资本投资总额</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="home">
                    <table class="table tablea">
                    <#if userDzsb??>
                        <tr><td width="190">登记序号</td><td>${(userDzsb.djxh)!}</td><td></td></tr>
                        <tr><td width="190">纳税人识别号</td><td>${(userDzsb.nsrsbh)!}</td><td></td></tr>
                        <tr><td width="190">纳税人名称</td><td>${(userDzsb.nsrmc)!}</td><td></td></tr>
                        <tr><td width="190">纳税人类型</td><td>
                            <#if userDzsb.nsrlx?? && userDzsb.nsrlx =='1'>增值税小规模
                            <#elseif userDzsb.nsrlx?? && userDzsb.nsrlx =='2'>增值税一般
                            <#elseif userDzsb.nsrlx?? && userDzsb.nsrlx =='3'>纯所得税
                            <#else>未核定</#if>
                        </td><td></td></tr>
                        <tr><td width="190">社会信用代码</td><td>${(userDzsb.shxydm)!}</td><td></td></tr>
                        <tr><td width="190">税务机关名称</td><td>${(userDzsb.swjgMc)!}</td><td></td></tr>
                        <tr><td width="190">税务机关代码</td><td>${(userDzsb.swjgDm)!}</td><td></td></tr>
                        <tr><td width="190">税务登记日期</td><td>${(userDzsb.djrq)!}</td><td></td></tr>
                        <tr><td width="190">软件到期日</td><td>${(userDzsb.expireTime?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                        <tr><td width="190">延长到期日</td><td>${(userDzsb.expandExpireTime?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                        <tr><td width="190">绑定状态</td><td>
                            <#if userDzsb.status?? && userDzsb.status >
                                <div class="btn btn-success btn-xs ">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">失效</div>
                            </#if>
                        </td><td></td></tr>
                        <tr><td width="190">创建时间</td><td>${(userDzsb.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                        <tr><td width="190">修改时间</td><td>${(userDzsb.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                    </#if>
                    <#if userHngs??>
                        <tr><td width="190">登记序号</td><td>${(userHngs.djxh)!}</td><td></td></tr>
                        <tr><td width="190">纳税人识别号</td><td>${(userHngs.nsrsbh)!}</td><td></td></tr>
                        <tr><td width="190">纳税人名称</td><td>${(userHngs.nsrmc)!}</td><td></td></tr>
                        <tr><td width="190">社会信用代码</td><td>${(userHngs.shxydm)!}</td><td></td></tr>
                        <tr><td width="190">税务机关名称</td><td>${(userHngs.swjgMc)!}</td><td></td></tr>
                        <tr><td width="190">税务机关代码</td><td>${(userHngs.swjgDm)!}</td><td></td></tr>
                        <tr><td width="190">办税员</td><td>${(userHngs.bsy)!}</td><td></td></tr>
                        <tr><td width="190">实名认证状态</td>
                            <td>${userHngs.smrzzt!}</td>
                        </tr>
                        <tr><td width="190">绑定状态</td><td>
                            <#if userHngs.status?? && userHngs.status >
                                <div class="btn btn-success btn-xs ">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">失效</div>
                            </#if>
                        </td><td></td></tr>
                        <tr><td width="190">创建时间</td><td>${(userHngs.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                        <tr><td width="190">修改时间</td><td>${(userHngs.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                    </#if>
                    <#if userHnds??>
                        <tr><td width="190">登记序号</td><td>${(userHnds.djxh)!}</td><td></td></tr>
                        <tr><td width="190">纳税人识别号</td><td>${(userHnds.nsrsbh)!}</td><td></td></tr>
                        <tr><td width="190">纳税人名称</td><td>${(userHnds.nsrmc)!}</td><td></td></tr>
                        <tr><td width="190">社会信用代码</td><td>${(userHnds.shxydm)!}</td><td></td></tr>
                        <tr><td width="190">税务机关名称</td><td>${(userHnds.swjgMc)!}</td><td></td></tr>
                        <tr><td width="190">税务机关代码</td><td>${(userHnds.swjgDm)!}</td><td></td></tr>
                        <tr><td width="190">主用户</td><td>${(userHnds.username)!}</td><td></td></tr>
                        <tr><td width="190">子用户</td><td>${(userHnds.subuser)!}</td><td></td></tr>
                        <tr><td width="190">绑定状态</td><td>
                            <#if userHnds.status?? && userHnds.status >
                                <div class="btn btn-success btn-xs ">有效</div>
                            <#else>
                                <div class="btn btn-danger btn-xs ">失效</div>
                            </#if>
                        </td><td></td></tr>
                        <tr><td width="190">创建时间</td><td>${(userHnds.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                        <tr><td width="190">修改时间</td><td>${(userHnds.lastUpdate?string("yyyy-MM-dd HH:mm:ss"))!}</td><td></td></tr>
                    </#if>
                        <tr>
                            <td colspan="3" style='text-align:center;'>

                            </td>
                        </tr>
                    </table>
                </div>
            <#if szhdxxRs??&&szhdxxRs.nsrjcxxvo??>
                <div class="tab-pane fade" id="jbxx">
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td nowrap="nowrap">纳税人识别号</td>
                            <td>${(szhdxxRs.nsrjcxxvo.nsrsbh)!}</td>
                            <td>纳税人名称</td>
                            <td>${(szhdxxRs.nsrjcxxvo.nsrmc)!}</td>
                        </tr>
                        <tr>
                            <td>登记注册类型</td>
                            <td>${(szhdxxRs.nsrjcxxvo.djzclxmc)!}</td>
                            <td>生产经营地址</td>
                            <td>${(szhdxxRs.nsrjcxxvo.scjydz)!}</td>
                        </tr>
                        <tr>
                            <td>生产经营地邮编</td>
                            <td>${(szhdxxRs.nsrjcxxvo.scjydYb)!}</td>
                            <td>电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.dhhm)!}</td>
                        </tr>
                        <tr>
                            <td>行业名称</td>
                            <td>${(szhdxxRs.nsrjcxxvo.hymc)!}</td>
                            <td>法定代表人</td>
                            <td>${(szhdxxRs.nsrjcxxvo.fddbrmc)!}</td>
                        </tr>
                        <tr>
                            <td>法人证件类型</td>
                            <td>${(szhdxxRs.nsrjcxxvo.frzjlxmc)!}</td>
                            <td>证件号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zjhm)!}</td>
                        </tr>
                        <tr>
                            <td>办税员</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bsrmc)!}</td>
                            <td>税务机构</td>
                            <td>${(szhdxxRs.nsrjcxxvo.swjgmc)!}</td>
                        </tr>
                        <tr>
                            <td>工商机关</td>
                            <td>${(szhdxxRs.nsrjcxxvo.gszgswjgDm)!}</td>
                            <td>纳税人税务机构代码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.nsrSwjgDm)!}</td>
                        </tr>
                        <tr>
                            <td>街道乡镇名称</td>
                            <td>${(szhdxxRs.nsrjcxxvo.jdxzmc)!}</td>
                            <td>开业设立日期</td>
                            <td>${(szhdxxRs.nsrjcxxvo.kyslrq)!}</td>
                        </tr>
                        <tr>
                            <td>变化日期</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bhrq)!}</td>
                            <td>单位隶属关系</td>
                            <td>${(szhdxxRs.nsrjcxxvo.dwlsgxmc)!}</td>
                        </tr>
                        <tr>
                            <td>批准机关</td>
                            <td>${(szhdxxRs.nsrjcxxvo.pzjg)!}</td>
                            <td>批准文号</td>
                            <td>${(szhdxxRs.nsrjcxxvo.pzwh)!}</td>
                        </tr>
                        <tr>
                            <td>工商开业日期</td>
                            <td>${(szhdxxRs.nsrjcxxvo.kyrq)!}</td>
                            <td>生产经营期限起</td>
                            <td>${(szhdxxRs.nsrjcxxvo.scjyqxQ)!}</td>
                        </tr>
                        <tr>
                            <td>生产经营期限止</td>
                            <td>${(szhdxxRs.nsrjcxxvo.scjyqxZ)!}</td>
                            <td>工商登记证字号</td>
                            <td>${(szhdxxRs.nsrjcxxvo.gsdjzzh)!}</td>
                        </tr>
                        <tr>
                            <td>注册地址</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zcdz)!}</td>
                            <td>注册地邮编</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zcdYb)!}</td>
                        </tr>
                        <tr>
                            <td>注册地电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zcdDhhm)!}</td>
                            <td>核算方式</td>
                            <td>${(szhdxxRs.nsrjcxxvo.hsfsmc)!}</td>
                        </tr>
                        <tr>
                            <td>从业人数</td>
                            <td>${(szhdxxRs.nsrjcxxvo.cyrs)!}</td>
                            <td>外籍人数</td>
                            <td>${(szhdxxRs.nsrjcxxvo.wjrs)!}</td>
                        </tr>
                        <tr>
                            <td>网站网址</td>
                            <td>${(szhdxxRs.nsrjcxxvo.wzwz)!}</td>
                            <td>会计制度准则</td>
                            <td>${(szhdxxRs.nsrjcxxvo.kjzdzzmc)!}</td>
                        </tr>
                        <tr>
                            <td>税收管理员</td>
                            <td>${(szhdxxRs.nsrjcxxvo.ssglymc)!}</td>
                            <td>法人电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.frDhhm)!}</td>
                        </tr>
                        <tr>
                            <td>法人移动电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.frYddhhm)!}</td>
                            <td>法人电子邮箱</td>
                            <td>${(szhdxxRs.nsrjcxxvo.frDydz)!}</td>
                        </tr>
                        <tr>
                            <td>财务负责人名称</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zcdz)!}</td>
                            <td>财务负责人证件类型</td>
                            <td>${(szhdxxRs.nsrjcxxvo.cwffzrzjlxmc)!}</td>
                        </tr>
                        <tr>
                            <td>财务负责人证件号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.cwfzrZjhm)!}</td>
                            <td>财务负责人电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.cwfzrDhhm)!}</td>
                        </tr>
                        <tr>
                            <td>财务负责人移动电话</td>
                            <td>${(szhdxxRs.nsrjcxxvo.cwfzrYddhhm)!}</td>
                            <td>财务负责人电子邮箱</td>
                            <td>${(szhdxxRs.nsrjcxxvo.cwfzrDydz)!}</td>
                        </tr>
                        <tr>
                            <td>办税人员证件类型</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bsrsfzjzlmc)!}</td>
                            <td>办税人证件号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bsrZjhm)!}</td>
                        </tr>
                        <tr>
                            <td>办税人电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bsrDhhm)!}</td>
                            <td>办税人移动电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bsrYddhhm)!}</td>
                        </tr>
                        <tr>
                            <td>办税人电子邮箱</td>
                            <td>${(szhdxxRs.nsrjcxxvo.bsrDydz)!}</td>
                            <td>税务代理人名称</td>
                            <td>${(szhdxxRs.nsrjcxxvo.swdlrmc)!}</td>
                        </tr>
                        <tr>
                            <td>税务代理人纳税人识别号</td>
                            <td>${(szhdxxRs.nsrjcxxvo.swdlrNsrsbh)!}</td>
                            <td>税务代理人电话号码</td>
                            <td>${(szhdxxRs.nsrjcxxvo.swdlrDhhm)!}</td>
                        </tr>
                        <tr>
                            <td>税务代理人电子邮箱</td>
                            <td>${(szhdxxRs.nsrjcxxvo.swdlrDydz)!}</td>
                            <td>注册资本</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zczb)!}</td>
                        </tr>
                        <tr>
                            <td>自然人投资比例</td>
                            <td>${(szhdxxRs.nsrjcxxvo.zrrtzbl)!}</td>
                            <td>外资投资比例</td>
                            <td>${(szhdxxRs.nsrjcxxvo.wztzbl)!}</td>
                        </tr>
                        <tr>
                            <td>国有投资比例</td>
                            <td>${(szhdxxRs.nsrjcxxvo.gytzbl)!}</td>
                            <td>工商登记发照日期</td>
                            <td>${(szhdxxRs.nsrjcxxvo.gsfzrq)!}</td>
                        </tr>
                        <tr>
                            <td>有效期起</td>
                            <td>${(szhdxxRs.nsrjcxxvo.yxqQ)!}</td>
                            <td>有效期止</td>
                            <td>${(szhdxxRs.nsrjcxxvo.yxqZ)!}</td>
                        </tr>
                        <tr>
                            <td>纳税人英文名称</td>
                            <td>${(szhdxxRs.nsrjcxxvo.nsrmcYw)!}</td>
                            <td>合伙人数</td>
                            <td>${(szhdxxRs.nsrjcxxvo.hhrs)!}</td>
                        </tr>
                        <tr>
                            <td>固定人数</td>
                            <td>${(szhdxxRs.nsrjcxxvo.gdrs)!}</td>
                            <td>工商户标志</td>
                            <td>${(szhdxxRs.nsrjcxxvo.gshbz)!}</td>
                        </tr>
                        <tr>
                            <td>发证日期</td>
                            <td>${(szhdxxRs.nsrjcxxvo.fzrq)!}</td>
                            <td>填表日期</td>
                            <td>${(szhdxxRs.nsrjcxxvo.tbrq)!}</td>
                        </tr>
                        <tr>
                            <td>经营范围</td>
                            <td colspan="3">${(szhdxxRs.nsrjcxxvo.jyfw)!}</td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="profile">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <th>投资方名称</th>
                        <th>投资比例</th>
                        <th>证件类型</th>
                        <th>证件号码</th>
                        <th nowrap="nowrap">国籍</th>
                        <th nowrap="nowrap">注册地址</th>
                    </tr>
                    <#if szhdxxRs.nsrjcxxvo.tzfList??>
                        <#if szhdxxRs.nsrjcxxvo.tzfList?? && ( szhdxxRs.nsrjcxxvo.tzfList?size gt 0 )>
                            <#list szhdxxRs.nsrjcxxvo.tzfList as tzfList>
                                <tr>
                                    <td>${tzfList.tzfmc!}</td>
                                    <td>${tzfList.tzbl!}</td>
                                    <td>${tzfList.zjlxmc!}</td>
                                    <td>${tzfList.zjhm!}</td>
                                    <td nowrap="nowrap">${tzfList.gjmc!}</td>
                                    <td nowrap="nowrap">${tzfList.zcdz!}</td>
                                </tr>
                            </#list>
                        </#if>
                    </#if>
                    </table>
                </div>
                <div class="tab-pane fade" id="ios">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <th width="50%">扣款银行名称</th>
                        <th width="50%">扣款银行账号</th>
                    </tr>
                    <#if szhdxxRs.nsrjcxxvo.kkyhList??>
                        <#if szhdxxRs.nsrjcxxvo.kkyhList?? && ( szhdxxRs.nsrjcxxvo.kkyhList?size gt 0 )>
                            <#list szhdxxRs.nsrjcxxvo.kkyhList as kkyhList>
                                <tr>
                                    <td>${kkyhList.kkyhmc!}</td>
                                    <td>${kkyhList.kkyhzh!}</td>
                                </tr>
                            </#list>
                        </#if>
                    </#if>
                    </table>
                </div>
                <div class="tab-pane fade" id="jmeter">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <th width="50%">币种</th>
                        <th width="50%">金额</th>
                    </tr>
                    <#if szhdxxRs.nsrjcxxvo.djZczbtzzeList??>
                        <#if szhdxxRs.nsrjcxxvo.djZczbtzzeList?? && ( szhdxxRs.nsrjcxxvo.djZczbtzzeList?size gt 0 )>
                            <#list szhdxxRs.nsrjcxxvo.djZczbtzzeList as djZczbtzzeList>
                                <tr>
                                    <td>${djZczbtzzeList.zcbzmc!}</td>
                                    <td>${djZczbtzzeList.je!}</td>
                                </tr>
                            </#list>
                        </#if>
                    </#if>
                    </table>
                </div>
            <#else>
                <div class="tab-pane fade" id="jbxx">
                    <table class="layui-table" lay-size="sm">
                        <tr>
                            <td align="center">--未查到相关信息--</td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="profile">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <td align="center">--未查到相关信息--</td>
                    </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="ios">
                <table class="layui-table" lay-size="sm">
                    <tr>
                        <td align="center">--未查到相关信息--</td>
                    </tr>
                    </table>
                </div>
            </#if>
            </div>
</div>
<script data-main="${ctx}/js/abc/consumer/nsqygl.js" src="${ctx}/js/require.js"></script>
</body>
</html>