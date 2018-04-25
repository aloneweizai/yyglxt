package com.abc.service;

import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.staticpage.ContentPreNextResponse;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by zhouzhi on 2017-07-12.
 */
@Service
public class ContentPreNextService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentPreNextService.class);

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 远程调取数据库中的 据内容ID获取前后记录信息
     * @return
     * @throws InterruptedException
     */
    public ContentPreNextResponse findContentPreNext(String channelId, String releaseDate) throws InterruptedException {
        String url ="http://test.chabc.net"+ ConstantsUri.CMS_CONTENT_PRE_NEXT +"?channelId="+channelId+"&releaseDate="+releaseDate;
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Version", "1");
//        httpHeaders.add("Access-Token", "5477d958602849d43766c21f71802f5f");

//        HashMap<String,String> requestParam = new HashMap<>();
//        requestParam.put("channelId",channelId);
//        requestParam.put("releaseDate",releaseDate);

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, httpHeaders);
        ResponseEntity<ContentPreNextResponse> contentPreNextResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ContentPreNextResponse.class);
        ContentPreNextResponse contentPreNextResponse = contentPreNextResponseEntity.getBody();
//        String json= JSONObject.fromObject(dataList).toString();
//        LOGGER.info("返回结果:"+ json);
        return contentPreNextResponse;
    }
}
