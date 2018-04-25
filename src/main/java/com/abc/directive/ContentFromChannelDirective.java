package com.abc.directive;

import com.abc.service.StaticService;
import com.abc.service.StaticServiceImpl;
import com.abc.soa.response.cms.pub.KnowledgeBaseListResponse;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-21.
 */
public class ContentFromChannelDirective implements TemplateDirectiveModel {
    @Autowired
    private StaticService staticService = new StaticServiceImpl();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String access_token = params.get("access_token").toString();
        String admin_token = params.get("admin_token").toString();
        String channelId = params.get("channelId").toString();
        String typeId = params.get("typeId")==null? "" : params.get("typeId").toString();
        String size = params.get("ps").toString();
        String page = params.get("page").toString();




        PublishSiteColumnContentListResponse contentListResponse = null;
        try {
            contentListResponse = staticService.getContentByChannelId(access_token, admin_token, channelId, typeId, size, page);
        } catch (Exception e) {
            e.printStackTrace();
        }


        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        env.setVariable("contentListResponse", builder.build().wrap(contentListResponse));
        body.render(env.getOut());
    }
}
