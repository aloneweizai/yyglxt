<#assign ctx=request.getContextPath()>
<div id="container" class="g_container">
    <div class="survey_background_wrap" style="height: 872px; background-position: 50% 50%;"></div>
    <div class="editor_container" style="display: block;">
        <!-- 主体 -->
        <div class="editor_main" style=" left: 0; top: 60px;">
            <div class="survey_wrap">
                <div class="survey_main" style="padding-top:0;">
                    <div class="survey_container">
                        <div class="page" data-pid="1" style="display: block;">


                            <div class="toupiao-title"><h3>投票页链接地址</h3></div>
                            <div class="question question_radio required">
                                <div class="xuanxiang" style="width:90%;">
                                    <div class="xuanxiang-input">
                                        <input type="text" readonly="readonly" id="generated_url" class="toupiao-input" value="${url!}"/>
                                        <span>
                                            <a href="${url}" target="_blank" style="width:100px; background:#178FE5;color:#FFF;">打开链接</a>
                                        </span>
                                    </div>
                                    <div class="xuanxiang-a">
                                        此链接可以放到自定义菜单或微信文章的阅读原文
                                    </div>
                                </div>

                            </div>
                            <div class="toupiao-title"><h3>二维码</h3></div>
                            <div class="question question_radio required">
                                <div class="xuanxiang">
                                    <div style="width: 300px;height:300px;border:1px solid gray;" id="vote_qrcode_container"></div>
                                    <p>用微信扫描二维码</p>
                                </div>
                            </div>
                            <div class="inner" style="text-align:center; margin-top:30px">
                                <div class="row editor_control">
                                    <a href="${ctx}/cms/vote/list.php" style="background:#e9e9e9;"
                                       class="btn btn_small btn_default">返回首页</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>