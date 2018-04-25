package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.PagerSpec;
import com.abc.controller.BaseController;
import com.abc.job.QuartzCmsJob;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.task.ScheduleJob;
import com.abc.soa.request.cms.task.TaskBo;
import com.abc.soa.request.cms.task.TaskDataBo;
import com.abc.soa.request.cms.task.TaskDataListBo;
import com.abc.soa.request.system.BasePaginationCriteria;
import com.abc.soa.response.system.bo.IdsBo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by stuy on 2017/6/26.
 */
@Controller
@RequestMapping(value = "/cms/time")
public class TimeController extends BaseController{

    @Autowired
    private QuartzCmsJob quartzJob;

    /**
     * 跳转至数定时任务管理列表
     *
     * @param pagerSpec
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/list.php",method = RequestMethod.GET)
    public ModelAndView list(PagerSpec pagerSpec,BasePaginationCriteria basePaginationCriteria, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/time/list");
        TaskDataListBo taskDataListBo=SoaConnectionFactory.get(request, ConstantsUri.SYS_TIME, basePaginationCriteria, TaskDataListBo.class);
        basePaginationCriteria.setTotalItems((long)taskDataListBo.getTotal());
        basePaginationCriteria.setTotalPage((int) Math.ceil((double) basePaginationCriteria.getTotalItems() / (double) basePaginationCriteria.getSize()));
        mav.addObject("taskDataListBo", taskDataListBo);
        mav.addObject("jobGroup", getDictBOList(request, "jobGroup"));
        mav.addObject("jobStatus", getDictBOList(request, "jobStatus"));
        mav.addObject("pagination", basePaginationCriteria);
        return mav;
    }

    /**
     * 跳转至定时任务管理新增页面
     *
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/add.php", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/time/form_add");
        mav.addObject("jobGroups", getDictBOList(request, "jobGroup"));
        return mav;
    }

    /**
     * 跳转至定时任务管理修改页面
     *
     * @param jobGroup
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/{jobName}/{jobGroup}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "jobName") String jobName,@PathVariable(value = "jobGroup") String jobGroup,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/time/form_edit");
        mav.addObject("jobGroups", getDictBOList(request, "jobGroup"));
        TaskDataListBo taskDataListBo=SoaConnectionFactory.get(request, ConstantsUri.SYS_TIME, null, TaskDataListBo.class);
        List<TaskBo>  dataList = taskDataListBo.getDataList();
        TaskBo data = new TaskBo();
        for(int i = 0; i < dataList.size(); i++){
            if(jobName.equals(dataList.get(i).getJobName())&&jobGroup.equals(dataList.get(i).getJobGroup())){
                data = dataList.get(i);
            }
        }
        mav.addObject("taskDataBo", data);
        return mav;
    }

    /**
     * 跳转至定时任务激活
     *
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/qi/{jobName}/{jobGroup}", method = RequestMethod.POST)
    public ModelAndView qi(@PathVariable(value = "jobName") String jobName,@PathVariable(value = "jobGroup") String jobGroup, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        TaskDataBo result = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_TIME_RESUME, null, TaskDataBo.class, jobName,jobGroup);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 跳转至定时任务暂停
     *
     * @param jobName
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/ting/{jobName}/{jobGroup}", method = RequestMethod.POST)
    public ModelAndView ting(@PathVariable(value = "jobName") String jobName,@PathVariable(value = "jobGroup") String jobGroup, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        TaskDataBo result = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_TIME_PAUSE, null, TaskDataBo.class, jobName,jobGroup);
       /* result.getData().setIsEnable("0");
        TaskDataBo results = SoaConnectionFactory.put(request, ConstantsUri.SYS_TIME_UPD, result.getData(), TaskDataBo.class, result.getData().getTaskId());
        mav.addObject("result", results);*/
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 定时任务管理保存操作
     *
     * @param request
     * @param taskBo
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/save.php",method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, @RequestBody TaskBo taskBo) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        //taskBo.setId(1);
        TaskDataBo result =  SoaConnectionFactory.post(request, ConstantsUri.SYS_TIME_SAVE, taskBo, TaskDataBo.class);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 定时任务管理删除操作
     *
     * @param jobName
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/{jobName}/{jobGroup}", method = RequestMethod.POST)
    public ModelAndView del(@PathVariable(value = "jobName") String jobName,@PathVariable(value = "jobGroup") String jobGroup, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        //TaskDataBo results = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_TIME_ONE, null, TaskDataBo.class, id);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.SYS_TIME_DELETE, null, BaseResponse.class, jobName,jobGroup);
        /*TaskBo data = results.getData();
        ScheduleJob scheduleJob = getScheduleJob(data);
        try {
            quartzJob.deleteTrigger(scheduleJob);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 定时任务管理批量删除操作
     *
     * @param ids 多个id编号用","隔开
     * @param request
     * @return
     */
    @RequiresPermissions("system:time")
    @RequestMapping(value = "/batchDel.php", method = RequestMethod.POST)
    public ModelAndView batchDel(@RequestParam(value = "ids", required = false) String ids, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            if(id.length>0){
                IdsBo idsBo=new IdsBo();
                idsBo.setIds(id);
                for(int i=0;i<id.length;i++){
                    TaskDataBo results = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_TIME_ONE, null, TaskDataBo.class, id[i]);
                    TaskBo data = results.getData();
                    ScheduleJob scheduleJob = getScheduleJob(data);
                    try {
                        quartzJob.deleteTrigger(scheduleJob);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                result=SoaConnectionFactory.post(request, ConstantsUri.SYS_TIME_DELETELIST, idsBo, BaseResponse.class);
            }
        }
        mav.addObject("result", result);
        return mav;
    }


    /**
     * 生成定时任务对象
     * @param taskBo
     * @return
     */
    private ScheduleJob getScheduleJob(TaskBo taskBo){
        ScheduleJob scheduleJob=new ScheduleJob();
        /*scheduleJob.setJobId(taskBo.getTaskId());
        scheduleJob.setJobName(taskBo.getTaskName());
        scheduleJob.setJobGroup("group_"+taskBo.getTaskId());
        switch (taskBo.getTaskIntervalUnit()){
            case 1:
                scheduleJob.setCronExpression("0 0/"+taskBo.getIntervalMinute()+" * * * ?");
                break;
            case 2:
                scheduleJob.setCronExpression("0 * 0/"+taskBo.getIntervalMinute()+" * * ?");
                break;
            case 3:
                scheduleJob.setCronExpression("0 "+taskBo.getMinute()+" "+taskBo.getHour()+" * * ?");
                break;
            case 4:
                scheduleJob.setCronExpression("0 "+taskBo.getMinute()+" "+taskBo.getHour()+" * * "+taskBo.getDayOfWeek());
                break;
            default:
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
        return scheduleJob;
    }
}
