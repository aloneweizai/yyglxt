package com.abc.service;

import com.abc.application.SpringCtxHolder;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.task.TaskDataListBo;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by stuy on 2017/7/11.
 */
@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 远程读取数据库中的定时任务
     * @return
     * @throws InterruptedException
     */
    @Async
    public TaskDataListBo findTask() throws InterruptedException {
        LOGGER.info("==========读取定时任务===========");
        String url = SpringCtxHolder.getProperty("abc.soa-url")+ ConstantsUri.SYS_TIME_LIST;
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Version", "1");
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<TaskDataListBo> taskDataListBo = restTemplate.exchange(url, HttpMethod.GET, requestEntity, TaskDataListBo.class);
        TaskDataListBo dataList = taskDataListBo.getBody();
        String json=JSONObject.fromObject(dataList).toString();
        LOGGER.info("返回定时任务结果:"+ json);
        // 模拟网络延时
        //Thread.sleep(1000L);
        return dataList;
    }


}
