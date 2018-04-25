<script type="text/javascript"> <#assign ctx=request.getContextPath()> </script>

    <div class="layui-layer-title">添加帮手等级</div>
        <form id="factionMemberLevel_form" action="${ctx}/bangbang/factionMemberLevel/save.php">
            <input type="hidden" name="status" <#if factionMember?? && factionMember.status>value ="${(factionMember.status?c)}"<#else>value="true"</#if>/>
            <input type="hidden" name="id" value="${(factionMember.id)!}"/>
                <div class="layui-container container">
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>帮手等级</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="code" value="${(factionMember.code)!}" lay-verify="title" autocomplete="off" placeholder="请输入帮手等级" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>帮手名称</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="name" value="${(factionMember.name)!}" lay-verify="title" autocomplete="off" placeholder="请输入帮手名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>回答数</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="answers" value="${(factionMember.answers?c)!}" lay-verify="title" autocomplete="off" placeholder="请输入回答数（0表示无限制）" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>讨论数</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="discussions" value="${(factionMember.discussions?c)!}" lay-verify="title" autocomplete="off" placeholder="请输入讨论数（0表示无限制）" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>采纳数</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="adoptions" value="${(factionMember.adoptions?c)!}" lay-verify="title" autocomplete="off" placeholder="请输入采纳数（0表示无限制）" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md8 md">
                            <div class="btn">
                                <button type="button" class="layui-btn js_save">保存</button>
                                <button type="button" class="layui-btn layui-btn-primary js_close">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
        </form>