package com.abc.directive;

import com.abc.service.StaticService;
import com.abc.service.StaticServiceImpl;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * 根据标签名称、栏目名称，获取内容列表
 * Created by Administrator on 2017-09-08.
 */
public class TopicDirective implements TemplateDirectiveModel {

    @Autowired
    private StaticService staticService = new StaticServiceImpl();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String access_token = params.get("access_token").toString();
        String admin_token = params.get("admin_token").toString();
        String channelName = params.get("channelName").toString();
        String tagName = params.get("tagName").toString();
        String size = params.get("ps").toString();
        String page = params.get("page").toString();




        PublishSiteColumnContentListResponse contentListResponse = null;
        try {
            contentListResponse = staticService.getTopicChannelContentList(access_token, admin_token, tagName, channelName, size, page);
        } catch (Exception e) {
            e.printStackTrace();
        }


        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        env.setVariable("contentListResponse", builder.build().wrap(contentListResponse));
        body.render(env.getOut());
    }
}