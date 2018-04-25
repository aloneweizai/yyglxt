package com.abc.application;

import com.abc.job.*;
import com.abc.service.TaskService;
import com.abc.soa.request.cms.task.ScheduleJob;
import com.abc.soa.request.cms.task.TaskBo;
import com.abc.soa.request.cms.task.TaskDataListBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by stuy on 2017/7/7.
 */
@Configuration
public class QuartzListener implements CommandLineRunner {

    @Autowired
    public QuartzCmsJob quartzJob;

    private static final Logger logger = LoggerFactory.getLogger(QuartzListener.class);

    private final TaskService taskService;

    public QuartzListener(TaskService taskServices) {
        this.taskService = taskServices;
    }


    /**
     * 定时任务初始化
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
//        TaskDataListBo datalist = taskService.findTask();
//        if(datalist!=null){
//            List<TaskBo> list=datalist.getDataList();
//            if(list!=null&&list.size()>0){
//                for(TaskBo taskBo : list){
//                    if("1".equals(taskBo.getIsEnable())){
//                        ScheduleJob scheduleJob = getScheduleJob(taskBo);
//                        try {
//                            if(scheduleJob!=null){
//                                logger.info("启动定时任务：[定时任务名称："+scheduleJob.getJobName()+" | 定时任务分组编号: "+scheduleJob.getJobGroup()+"]");
//                                quartzJob.execute(scheduleJob);
//                            }else{
//                                logger.info("启动定时任务失败，缺少必要参数");
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
    }

    private ScheduleJob getScheduleJob(TaskBo taskBo){
        /*if(taskBo.getTaskId()==null||taskBo.getTaskName()==null||"".equals(taskBo.getTaskName())){
            return null;
        }*/
        ScheduleJob scheduleJob=new ScheduleJob();
        /*scheduleJob.setJobId(taskBo.getTaskId());
        scheduleJob.setJobName(taskBo.getTaskName());
        scheduleJob.setJobGroup("group_"+taskBo.getTaskId());
        switch (taskBo.getTaskIntervalUnit()){
            case 1:
                if(taskBo.getIntervalMinute()!=0){
                    scheduleJob.setCronExpression("0 0/"+taskBo.getIntervalMinute()+" * * * ?");
                }
                break;
            case 2:
                if(taskBo.getIntervalMinute()!=0)
                scheduleJob.setCronExpression("0 * 0/"+taskBo.getIntervalMinute()+" * * ?");
                break;
            case 3:
                if(taskBo.getMinute()!=0&&taskBo.getHour()!=0)
                scheduleJob.setCronExpression("0 "+taskBo.getMinute()+" "+taskBo.getHour()+" * * ?");
                break;
            case 4:
                if(taskBo.getMinute()!=0&&taskBo.getHour()!=0&&taskBo.getDayOfWeek()!=0)
                scheduleJob.setCronExpression("0 "+taskBo.getMinute()+" "+taskBo.getHour()+" * * "+taskBo.getDayOfWeek());
                break;
            default:
                if(taskBo.getMinute()!=0&&taskBo.getHour()!=0&&taskBo.getDayOfMonth()!=0)
                scheduleJob.setCronExpression("0 "+taskBo.getMinute()+" "+taskBo.getHour()+" "+taskBo.getDayOfMonth()+" * ?");
        }
        switch (taskBo.getTaskType()){
            case "1":
                scheduleJob.setJobClass(QuartzCmsIndexFactory.class);
                break;
            case "2":
                scheduleJob.setJobClass(QuartzCmsColumnFactory.class);
                break;
            case "3":
                scheduleJob.setJobClass(QuartzCmsContentFactory.class);
                break;
            case "4":
                scheduleJob.setJobClass(QuartzCmsCollectionFactory.class);
                break;
            default:
                scheduleJob.setJobClass(QuartzCmsDistributeFactory.class);
        }
        scheduleJob.setJobStatus(taskBo.getIsEnable());*/
        if(scheduleJob.getCronExpression()!=null&&!"".equals(scheduleJob.getCronExpression()))
        return scheduleJob;
        else
            return null;
    }
}