<script type="text/javascript"> <#assign ctx=request.getContextPath()> </script>

    <div class="layui-layer-title">添加帮派等级</div>
        <form id="factionLevel_form" class="layui-form" action="${ctx}/bangbang/factionLevel/save.php">
            <input type="hidden" name="status" <#if faction?? && faction.status>value ="${(faction.status?c)}"<#else>value="true"</#if>/>
            <input type="hidden" name="id" value="${(faction.id)!}"/>
                <div class="layui-container container">
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>帮派名称</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="name" value="${(faction.name)!}" lay-verify="title" autocomplete="off" placeholder="请输入帮派名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>帮派等级</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="code" value="${(faction.code)!}" lay-verify="title" autocomplete="off" placeholder="请输入帮派等级" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>帮派荣誉值</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="honorValue" value="${(faction.honorValue?c)!}" lay-verify="title" autocomplete="off" placeholder="请输入帮派荣誉值" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>帮派头像</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/sl.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/sl.png" title="少林"
                                <#if !faction?? || (faction?? && faction.image =='/images/bangbang/sl.png')>checked</#if> >
                            </div>
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/wd.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/wd.png" title="武当"
                                       <#if (faction?? && faction.image =='/images/bangbang/wd.png')>checked</#if> >
                            </div>
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/gb.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/gb.png" title="丐帮"
                                       <#if (faction?? && faction.image =='/images/bangbang/gb.png')>checked</#if> >
                            </div>
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/hs.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/hs.png" title="华山"
                                       <#if (faction?? && faction.image =='/images/bangbang/hs.png')>checked</#if> >
                            </div>
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/kd.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/kd.png" title="崆峒"
                                       <#if (faction?? && faction.image =='/images/bangbang/kd.png')>checked</#if> >
                            </div>
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/em.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/em.png" title="峨眉"
                                       <#if (faction?? && faction.image =='/images/bangbang/em.png')>checked</#if> >
                            </div>
                            <div class="mp">
                                <img src="${ctx}/images/bangbang/kl.png" alt="">
                                <input type="radio" name="image" value="/images/bangbang/kl.png" title="昆仑"
                                       <#if (faction?? && faction.image =='/images/bangbang/kl.png')>checked</#if> >
                            </div>
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>人数限制</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="text" name="peopleLimit" value="${(faction.peopleLimit?c)!}" lay-verify="title" autocomplete="off" placeholder="请输入人数限制" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-row row">
                        <div class="layui-col-md2 md">
                            <span>任务限制</span>
                        </div>
                        <div class="layui-col-md8 md">
                            <input type="radio" name="taskLimit" value="1" title="1级任务" lay-skin="primary" <#if !faction?? || (faction?? && faction.taskLimit == 1)>checked</#if> >
                            <input type="radio" name="taskLimit" value="2" title="2级任务" lay-skin="primary" <#if (faction?? && faction.taskLimit == 2)>checked</#if> >
                            <input type="radio" name="taskLimit" value="3" title="3级任务" lay-skin="primary" <#if (faction?? && faction.taskLimit == 3)>checked</#if> >
                            <input type="radio" name="taskLimit" value="4" title="4级任务" lay-skin="primary" <#if (faction?? && faction.taskLimit == 4)>checked</#if>>
                            <input type="radio" name="taskLimit" value="5" title="5级任务" lay-skin="primary" <#if (faction?? && faction.taskLimit == 5)>checked</#if>>
                            <input type="radio" name="taskLimit" value="6" title="6级任务" lay-skin="primary" <#if (faction?? && faction.taskLimit == 6)>checked</#if>>
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