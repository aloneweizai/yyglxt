package com.abc.job;

import com.abc.application.QuartzConfig;
import com.abc.soa.request.cms.task.ScheduleJob;
import org.quartz.*;
import org.springframework.stereotype.Service;

/**
 * Created by stuy on 2017/7/7.
 */
@Service
public class QuartzCmsJob {

    QuartzConfig quartzConfig = new QuartzConfig();

    public void execute(ScheduleJob job) throws Exception {
        Scheduler scheduler = quartzConfig.scheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        //不存在，创建一个
        if (trigger == null) {
            JobDetail jobDetail = JobBuilder.newJob(job.getJobClass())
                    .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put(job.getJobGroup(), job);

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                    .getCronExpression());

            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                    .getCronExpression());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();

            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    public void deleteTrigger(ScheduleJob job) throws Exception{
        try {
            Scheduler scheduler = quartzConfig.scheduler();
            //logger.debug("delete trigger {" + triggerName + "@" + group + "}");
            //pause trigger
            scheduler.pauseTrigger(TriggerKey.triggerKey(job.getJobName(), job.getJobGroup()));
            //delete job
            scheduler.deleteJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
            scheduler.unscheduleJob(TriggerKey.triggerKey(job.getJobName(), job.getJobGroup()));
            //logger.debug("trigger {" + triggerName + "@" + group + "} resumed");
        } catch (SchedulerException e) {
            //logger.warn("delete trigger error", e);
            throw new RuntimeException(e);
        }
    }
}
