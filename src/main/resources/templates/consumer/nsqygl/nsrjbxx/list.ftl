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
<!--查看-->
<@shiro.hasPermission name="nsrjbxx:look">
    <#assign canLook=true>
</@shiro.hasPermission>
<body>
<div class="container-fluid m_top_30 nycol_list  sys_mod">
    <form action="${ctx}/consumerManager/consumer/nsqygl/nsrjbxx/list.php" method="get" id="consumerListForm" class="layui-form">
        <div class="layui-form-top">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">纳税人识别号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${(BaseRq.nsrsbh)!}" name="nsrsbh" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:180px;">
                        <div id="consumer_query" class="layui-btn">查询</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nycon_list_b">
            <table class="layui-table" lay-size="sm">
                <thead class="pn-lthead">
                <tr>
                    <th></th>
                    <th>纳税人识别号</th>
                    <th>纳税人名称</th>
                    <th>法定代表人</th>
                    <th>法人电话号码</th>
                    <th>财务负责人</th>
                    <th>财务负责人电话号码</th>
                    <th>生产经营地址</th>
                    <th>生产经营期限起止</th>
                    <th>税务机构名称</th>
                </tr>
                </thead>
                <tbody class="pn-ltbody">
                <#if szhdxxRs??&&szhdxxRs.nsrjcxxvo??>
                <tr>
                    <td class="td_i">1</td>
                    <td>
                    <#if canLook??>
                        <a class="ljc_00bcd4"  id="opendialog" data-type="opendialog" href="javascript:;"> ${(szhdxxRs.nsrjcxxvo.nsrsbh)!}</a>
                    <#else>
                        ${(szhdxxRs.nsrjcxxvo.nsrsbh)!}
                    </#if>
                    </td>
                    <td>${(szhdxxRs.nsrjcxxvo.nsrmc)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.fddbrmc)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.frDhhm)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.cwfzrmc)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.cwfzrDhhm)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.scjydz)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.scjyqxQ)!}~${(szhdxxRs.nsrjcxxvo.scjyqxZ)!}</td>
                    <td>${(szhdxxRs.nsrjcxxvo.swjgmc)!}</td>
                </tr>
                <#elseif szhdxxRs??&&!(szhdxxRs.nsrjcxxvo??)>
                <tr>
                    <td colspan="10" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </form>
</div>

<div class="main_modal container-fluid" id="myModal1" tabindex="-1" hidden>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">纳税人基本信息</h4>
            </div>
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#home" data-toggle="tab">基本信息</a>
                </li>
                <li class="dropdown"><a href="#profile" data-toggle="tab">投资方信息</a></li>
                <li><a href="#ios" data-toggle="tab">扣款银行信息</a></li>
                <li><a href="#jmeter" data-toggle="tab">登记注册资本投资总额</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
            <#if szhdxxRs??&&szhdxxRs.nsrjcxxvo??>
                <div class="tab-pane fade in active" id="home">
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
                    <#if szhdxxRs.nsrjcxxvo.tzfList??>
                        <tr>
                            <th>投资方名称</th>
                            <th>投资比例</th>
                            <th>证件类型</th>
                            <th>证件号码</th>
                            <th nowrap="nowrap">国籍</th>
                            <th nowrap="nowrap">注册地址</th>
                        </tr>
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
                    <#else>
                        <tr>
                            <td colspan="6" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                        </tr>
                    </#if>
                </table>
                </div>
                <div class="tab-pane fade" id="ios">
                <table class="layui-table" lay-size="sm">
                    <#if szhdxxRs.nsrjcxxvo.kkyhList??>
                        <#if szhdxxRs.nsrjcxxvo.kkyhList?? && ( szhdxxRs.nsrjcxxvo.kkyhList?size gt 0 )>
                            <tr>
                                <th width="50%">扣款银行名称</th>
                                <th width="50%">扣款银行账号</th>
                            </tr>
                            <#list szhdxxRs.nsrjcxxvo.kkyhList as kkyhList>
                                <tr>
                                    <td>${kkyhList.kkyhmc!}</td>
                                    <td>${kkyhList.kkyhzh!}</td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="2" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                            </tr>
                        </#if>
                    <#else>
                        <tr>
                            <td colspan="2" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                        </tr>
                    </#if>
                </table>
                </div>
                <div class="tab-pane fade" id="jmeter">
                <table class="layui-table" lay-size="sm">
                    <#if szhdxxRs.nsrjcxxvo.djZczbtzzeList??>
                        <tr>
                            <th width="50%">币种</th>
                            <th width="50%">金额</th>
                        </tr>
                        <#if szhdxxRs.nsrjcxxvo.djZczbtzzeList?? && ( szhdxxRs.nsrjcxxvo.djZczbtzzeList?size gt 0 )>
                            <#list szhdxxRs.nsrjcxxvo.djZczbtzzeList as djZczbtzzeList>
                                <tr>
                                    <td>${djZczbtzzeList.zcbzmc!}</td>
                                    <td>${djZczbtzzeList.je!}</td>
                                </tr>
                            </#list>
                        </#if>
                    <#else>
                        <tr>
                            <td colspan="2" style="font-size:15px; color: #999;text-align: center">--未查到相关信息--</td>
                        </tr>
                    </#if>
                </table>
                </div>
            </#if>
            </div>
            <div class="modal-footer">
                <button type="button" class="layui-btn layui-btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script data-main="${ctx}/js/abc/consumer/nsqygl.js" src="${ctx}/js/require.js"></script>
</body>
</html>