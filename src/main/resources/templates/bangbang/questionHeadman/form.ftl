<form id="form3" class="layui-form" action="">
    <div class="layui-container container">
        <div class="layui-row row">
            <div class="layui-col-md2">用户名称：</div>
            <div class="layui-col-md10">${headman.name!}</div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2">联系电话：</div>
            <div class="layui-col-md10">${headman.phone!}</div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2">提问数：</div>
            <div class="layui-col-md10">${headman.questionNum!}</div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2">回答数：</div>
            <div class="layui-col-md10">${headman.answerNum!}</div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2">评论数：</div>
            <div class="layui-col-md10">${headman.commentNum!}</div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2">申请理由</div>
            <div class="layui-col-md10">${headman.applyReason!}</div>
        </div>
        <div class="layui-row row">
            <div class="layui-col-md2">加入的帮派</div>
            <div class="layui-col-md10"><dfn>${headman.factionName!}<span></span></dfn></div>
        </div>
        <#if isChangeStatus??>
            <div class="layui-row row">
                <div class="layui-col-md2">备注</div>
                <div class="layui-col-md10">
                    <textarea id="headman_remark" class="layui-textarea" placeholder="拒绝审核请填写原因"></textarea>
                </div>
            </div>
            <div class="layui-row row">
                <div class="layui-col-md2">操作</div>
                <div class="layui-col-md8">
                    <button data-id="${headman.id!}" data-status="success" data-href="/bangbang/questionHeadman/modify.php" class="layui-btn js_change_status">通过</button>
                    <button data-id="${headman.id!}" data-status="refuse"  data-href="/bangbang/questionHeadman/modify.php" class="layui-btn layui-btn-primary btn1 js_change_status">拒绝</button>
                </div>
            </div>
        <#else>
            <#if headman.remark?? && headman.remark!=''>
                <div class="layui-row row">
                    <div class="layui-col-md2">备注</div>
                    <div class="layui-col-md10">${headman.remark!}</div>
                </div>
            </#if>
        </#if>
    </div>
</form>