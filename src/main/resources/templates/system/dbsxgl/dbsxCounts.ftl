<#assign ctx=request.getContextPath()>
<meta http-equiv="Content-Type" content="text/html ;charset=utf-8" />
<html>
<head>
    <meta charset="utf-8" http-equiv="refresh" content="120">
    <title>htweb</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layui/css/layui.css">
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <style>
        .col-xs-4{width:55%;font-size: 20px;font-weight:bold;margin-top:0;line-height: 80px;height:80px;white-space: nowrap;overflow:hidden;text-overflow:ellipsis;}
        .col-xs-8{width:45%;text-align: center;background: #fff;overflow: hidden;}
        body, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, code, form, fieldset, legend, input, textarea, p, blockquote, th, td, dd, dt, dl {
            margin: 0;
            padding: 0;
        }
        .ccd{line-height: 80px;font-size: 25px;margin-top:0;font-weight:bold; background: #f2f2f2}
        .sta_icon_l {
            line-height: 80px;
            font-size: 30px;
            color: #fff;
            text-align: center;
        }

        .sta_icon_r {
            text-align: center;
            background: #fff;
            color: #333;
            line-height: 30px;
            height: 80px;
            overflow: hidden;
        }

        .sta_icon_r h3 {
            line-height: 34px;
            margin-top: 10px;
        }

        .sta_icon2 .sta_icon_l {
            font-size: 12px;
        }

        .sta_icon2 ul li {
            width: 258px;
            margin: 0 0 15px 15px;
        }

        .sta_icon2.sta_icon_l {
            font-size: 12px;
        }

        @media (max-width: 768px) {
            .sta_icon2 ul li {
                width: 100%;
            }
        }

        .statistics_tb{ margin-bottom: 20px;  }
        .tj_statis_n>div{ display: none; }
        .statistics_t ul li{ float: left;
            color: #333333;
            cursor: pointer;
            display: inline-block;
            font-size: 14px;
            line-height: 34px;
            margin-right: 20px;
            position: relative;
            text-align: center;
            width: 60px;
        }

        .statistics_t {
            border-top: 1px solid #ddd;
            width: 100%; margin: 30px 0 10px;
        }
        .statistics_t ul .hover{ border-top: 1px solid #3694d7 }

        .statistics_tb ul li {
            background-color: #fff;
            border: 1px solid #d5d5d5;
            border-radius: 0;
            box-shadow: none;
            color: #858585;
            cursor: pointer;
            font-family: "微软雅黑";
            font-size: 12px;
            height: 28px;
            line-height: 26px;
            margin-right: 6px;
            padding: 0 10px;
            transition-duration: 0.1s; float: left;
        }
        .statistics_tb ul .hover {
            border-color: #f7815d;
            color: #f7815d;
            outline: 0 none;
        }
        .ttd{
            text-align:center;
            border-right-style:solid;
            border-right-color:#999;
            border-right-width: 1px;
            border-left-style:solid;
            border-left-color:#999;
            border-left-width: 1px;
            height: 50px;
        }
        .numtd{
            text-align:center;
            border-right-style:solid;
            border-right-color:#999;
            border-right-width: 1px;
            border-left-style:solid;
            border-left-color:#999;
            border-left-width: 1px;
            height: 10px;
        }
        .circle{
            width: 10px;
            height: 10px;
            background-color: red;
            color: white;
            text-align: center;
            border-radius: 100%;
            /*-webkit-border-radius: 100px;*/
            -moz-border-radius: 100px;
            display: block;
        }
    </style>
    <!--评论统计查询-->
</head>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <div class=" container-fluid">
        <div class="row sta_icon2" style="margin-top: 15px;">
            <ul style="cursor:hand">
                <li class=" fl" data-id='1'>
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">实名认证审核</div>
                    <div class="col-xs-8"><h3 id="num1" class="ccd" style="color: #F56954;">${(dbsxRs.num1)!}</h3></div>
                </li>
                <li class=" fl" data-id='2' >
                    <div class=" col-xs-4 sta_icon_l btn-bj-red2">待处理订单</div>
                    <div class="col-xs-8"><h3 id="num2" class="ccd" style="color: #F56954;">${(dbsxRs.num2)!}</h3></div>
                </li>
                <li class=" fl" data-id='3' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">退换货申请</div>
                    <div class="col-xs-8"><h3 id="num3" class="ccd" style="color: #F56954;">${(dbsxRs.num3)!}</h3></div>
                </li>
                <li class=" fl" data-id='4' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">退款申请</div>
                    <div class="col-xs-8"><h3 id="num4" class="ccd" style="color: #F56954;">${(dbsxRs.num5)!}</h3></div>
                </li>
                <li class=" fl" data-id='5' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">发票寄送</div>
                    <div class="col-xs-8"><h3 id="num5" class="ccd" style="color: #F56954;">${(dbsxRs.num4)!}</h3></div>
                </li>
                <li class=" fl" data-id='6' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">发票领用</div>
                    <div class="col-xs-8"><h3 id="num6" class="ccd" style="color: #F56954;">${(dbsxRs.num6)!}</h3></div>
                </li>
                <li class=" fl" data-id='7'>
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">问答内容审核</div>
                    <div class="col-xs-8"><h3 id="num1" class="ccd" style="color: #F56954;">${(dbsxRs1.num1)!}</h3></div>
                </li>
                <li class=" fl" data-id='8' >
                    <div class=" col-xs-4 sta_icon_l btn-bj-red2">举报内容审核</div>
                    <div class="col-xs-8"><h3 id="num2" class="ccd" style="color: #F56954;">${(dbsxRs1.num2)!}</h3></div>
                </li>
                <li class=" fl" data-id='9' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">意见反馈回复</div>
                    <div class="col-xs-8"><h3 id="num3" class="ccd" style="color: #F56954;">${(dbsxRs1.num3)!}</h3></div>
                </li>
                <li class=" fl" data-id='18' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">会员礼包申请</div>
                    <div class="col-xs-8"><h3 id="num7" class="ccd" style="color: #F56954;">${(dbsxRs.giftCount)!}</h3></div>
                </li>
                <li class=" fl" data-id='19' >
                    <div class="col-xs-4 sta_icon_l btn-bj-red2">会员礼包发货</div>
                    <div class="col-xs-8"><h3 id="num8" class="ccd" style="color: #F56954;">${(dbsxRs.giftSend)!}</h3></div>
                </li>

            </ul>
        </div>
        <div class="row sta_icon2" style="margin-top: 15px;">
            <ul style="cursor:hand">
                <li class=" fl" data-id='10' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue ">今日课程浏览数</div>
                    <div class="col-xs-8"><h3 id="num4" class=" color_b ccd">${(dbsxRs1.num4)!}</h3></div>
                </li>
                <li class=" fl" data-id='11' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue ">今日课程观看数</div>
                    <div class="col-xs-8"><h3 id="num5" class="  color_b ccd">${(dbsxRs1.num5)!}</h3></div>
                </li>
                <li class=" fl" data-id='12' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue ">本月课程浏览数</div>
                    <div class="col-xs-8"><h3 id="num6" class="  color_b ccd">${(dbsxRs1.num6)!}</h3></div>
                </li>
                <li class=" fl" data-id='13' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue ">本月课程观看数</div>
                    <div class="col-xs-8"><h3 id="num7" class="  color_b ccd">${(dbsxRs1.num7)!}</h3></div>
                </li>
                <li class=" fl" data-id='14' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue ">今日用户注册数</div>
                    <div class="col-xs-8"><h3 id="num7" class="  color_b ccd">${(dbsxRs.regsDay)!}</h3></div>
                </li>
                <li class=" fl" data-id='15' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue ">本月用户注册数</div>
                    <div class="col-xs-8"><h3 id="num7" class="  color_b ccd">${(dbsxRs.regsMonth)!}</h3></div>
                </li>
                <li class=" fl" data-id='16' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue " style="cursor:auto">今日纳税人登录数</div>
                    <div class="col-xs-8"><h3 id="num7" class="  color_b ccd" style="cursor:auto">${(dbsxRs.dzsbLoginsDay)!}</h3></div>
                </li>
                <li class=" fl" data-id='17' >
                    <div class="col-xs-4 sta_icon_l btn-bj-blue "  style="cursor:auto">本月纳税人登录数</div>
                    <div class="col-xs-8"><h3 id="num7" class="  color_b ccd"  style="cursor:auto">${(dbsxRs.nsrLoginsMonth)!}</h3></div>
                </li>

            </ul>
        </div>
        <div class="statistics_tab " style="margin-top: 20px">
            <div class="statistics_t">
                <h3 class="modal-title" style="font-size: large"></h3>
            </div>
            <div class="statistics_tb">
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width:auto;max-width: 40%">
                        <h1 class="modal-title" style="margin-top: 30px;font-size: large">订单统计</h1>
                        <table lay-size="sm" id="sextable" style="width: 500px; height: 200px;margin-top: 30px ">
                            <thead class="pn-lthead">
                            <tr>
                                <td class="numtd"><div><h3 id="fkz" value="${(orderCountRs.orderStatus)!}"></h3><a data-type='lookdialog' data-status="" class="ljc_00bcd4">${(orderCountRs.orderStatus)!}</a></div></td>
                                <td class="numtd"><div><h3 id="fkz" value="${(orderCountRs.orderStatus2)!}"></h3><a data-type='lookdialog' data-status="2"   class="ljc_00bcd4">${(orderCountRs.orderStatus2)!}</a></div></td>
                                <td class="numtd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus4)!}"></h3><a data-type='lookdialog' data-status="4" class="ljc_00bcd4">${(orderCountRs.orderStatus4)!}</a></div></td>
                                </tr>
                            <tr>
                                <td class="ttd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus)!}"></h3>订单总数</div></td>
                                <td class="ttd"><div><h3 id="fkz" value="${(orderCountRs.orderStatus3)!}"></h3>待付款订单数</div></td>
                                <td class="ttd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus4)!}"></h3>付款成功订单数</div></td>
                                </tr>
                            <tr><td colspan="3" style="height:10px;"></td></tr>
                            <tr>
                                <td class="numtd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus6)!}"></h3><a data-type='lookdialog' data-status="6" class="ljc_00bcd4">${(orderCountRs.orderStatus6)!}</a></div></td>
                                <td class="numtd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus7)!}"></h3><a data-type='lookdialog' data-status="7"  class="ljc_00bcd4">${(orderCountRs.orderStatus7)!}</a></div></td>
                                <td class="numtd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus9)!}"></h3><a data-type='lookdialog' data-status="9" class="ljc_00bcd4">${(orderCountRs.orderStatus9)!}</a></div></td>
                            </tr>
                            <tr>
                                <td class="ttd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus6)!}"></h3>订单完成订单数</div></td>
                                <td class="ttd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus7)!}"></h3>订单作废订单数</div></td>
                                <td class="ttd"><div><h3 id="fkz"value="${(orderCountRs.orderStatus9)!}"></h3>已退款订单数</div></td>
                            </tr>
                            </thead>
                            <tbody class="pn-ltbody" id="registeruser">
                            </tbody>
                        </table>
                    </div>
                    <div class="layui-input-inline" style="width:auto;max-width: 50%">
                        <div id="tjday" style="width: 500px; display: block; position: relative; height: 300px;margin-left: 120px">
                        </div>
                        </div>
                </div>
            </div>
        </div>
    </div>

    <div class="main_modal container-fluid" id="myModal3" tabindex="-1" hidden>
        <div class="modal-dialog modal-lg" role="document"style="width: 95%;top: 0px;z-index: 1000000">
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
    <script data-main="${ctx}/js/abc/system/dbsxconut" src="${ctx}/js/require.js"></script>
</body>
</html>