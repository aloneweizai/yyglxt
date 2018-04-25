package com.abc.service;

import com.abc.common.exception.BusinessException;
import com.abc.dto.cms.site.ContentsListBo;
import com.abc.soa.response.cms.pub.KnowledgeBaseListResponse;
import com.abc.soa.response.cms.site.PublishSiteColumnContentListResponse;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 静态页面service
 * Created by zhouzhi on 2017-07-23.
 */
public interface StaticService {

    /**
     * 删除指定id的内容静态页
     * @param sitePath  站点路径
     * @param staticLink  cms_content_ext表中保存的 静态页地址
     * @return
     */
    public boolean delStaticContentPage(String sitePath, String staticLink) throws Exception;

    /**
     * 生成指定id的内容静态页
     * @param request
     * @param contentIds    内容ids，数组
     * @return
     * @throws Exception
     */
    public List<String> staticSomeContent(HttpServletRequest request,String[] contentIds) throws BusinessException;


    /**
     * 根据标签名称、栏目名称，获取内容列表
     * @param access_token
     * @param admin_token
     * @param tagName
     * @param channelName
     * @param size
     * @return
     * @throws Exception
     */
    public PublishSiteColumnContentListResponse getTopicChannelContentList(String access_token, String admin_token, String tagName, String channelName, String size, String page) throws Exception;

    /**
     * 根据标签名称，获取知识列表
     * @param access_token
     * @param admin_token
     * @param tagName
     * @param size
     * @return
     * @throws Exception
     */
    public KnowledgeBaseListResponse getKnowledgeWithTagContentList(String access_token, String admin_token, String tagName, String size) throws Exception;


    /**
     * 根据栏目id，获取包括子栏目的文章列表，按优先级、时间排序
     * @param access_token
     * @param admin_token
     * @param size
     * @param page
     * @return
     * @throws Exception
     */
    public PublishSiteColumnContentListResponse getContentByChannelId(String access_token, String admin_token, String channelId, String typeId, String size, String page) throws Exception;
}
