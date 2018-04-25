package com.abc.job;

import com.abc.soa.request.cms.task.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stuy on 2017/7/7.
 * 分发JOB
 */
public class QuartzCmsDistributeFactory implements Job {

    protected Logger _log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        JobKey jobkey = jobExecutionContext.getTrigger().getJobKey();
        ScheduleJob scheduleJob = (ScheduleJob)jobExecutionContext.getMergedJobDataMap().get( jobkey.getGroup());
        _log.info("名称 = [分发] 任务名称 = [ " + scheduleJob.getJobName() + " ] 任务时间 = [ "+dateString+" ]");
    }
}
