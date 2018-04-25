package com.abc.controller.cms;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.*;
import com.abc.controller.BaseController;
import com.abc.dto.cms.CmsFileUploadDto;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.FjDto;
import com.abc.service.ExportUtil;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.ActivityPaginationCriteria;
import com.abc.soa.request.cms.Event.*;
import com.abc.soa.response.cms.businessMsg.MessageSendBo;
import com.abc.soa.response.consumer.ExpLevelRs;
import com.abc.soa.response.system.bo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by stuy on 2017/6/29.
 */
@Controller
@RequestMapping(value = "/cms/activity")
public class ActivityController extends BaseController{


    /**
     * 跳转至活动管理列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list.php",method = RequestMethod.GET)
    public ModelAndView list(ActivityPaginationCriteria activityCriteria, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/list");
        Map<String,String> map=new HashMap<String,String>();
        if(!"".equals(activityCriteria.getTitle()))
            map.put("title",activityCriteria.getTitle());
        if(!"".equals(activityCriteria.getCategory()))
            map.put("category",activityCriteria.getCategory());
        if(!"".equals(activityCriteria.getStatus()))
            map.put("status",activityCriteria.getStatus());
        if(!"".equals(activityCriteria.getBegintime()))
            map.put("begintime",activityCriteria.getBegintime());
        if(!"".equals(activityCriteria.getEndtime()))
            map.put("endtime",activityCriteria.getEndtime());
        map.put("page",activityCriteria.getPage()+"");
        map.put("size",activityCriteria.getSize()+"");
        EventListsBo event = SoaConnectionFactory.get(request, ConstantsUri.CMS_ACTIVITY, map, EventListsBo.class);
        activityCriteria.setTotalItems((long)event.getTotal());
        activityCriteria.setTotalPage((int) Math.ceil((double) activityCriteria.getTotalItems() / (double) activityCriteria.getSize()));
        List<DictBO> listfl=getDictBOList(request,"hdfl");
        List<DictBO> listxs=getDictBOList(request,"hdxs");
        List<DictBO> listhdzt=getDictBOList(request,"activity");
        mav.addObject("event", event);
        mav.addObject("dict",listfl);
        mav.addObject("dictxs",listxs);
        mav.addObject("hdzt",listhdzt);
        mav.addObject("pagination", activityCriteria);
        mav.addObject("title", activityCriteria.getTitle());
        mav.addObject("begintime", activityCriteria.getBegintime());
        mav.addObject("category", activityCriteria.getCategory());
        mav.addObject("status", activityCriteria.getStatus());
        mav.addObject("endtime", activityCriteria.getEndtime());

        return mav;
    }


    /**
     * 跳转至活动管理新增页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add.php", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/form_add");
        ExpLevelRs expleve=SoaConnectionFactory.get(request,ConstantsUri.UEXPLEVEL_LIST,null, ExpLevelRs.class);
        ProvinceListRs list = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
        EventSponsorItemBo eventSponsorItemBo=SoaConnectionFactory.get(request,ConstantsUri.CMS_EVENTSPONSOR,null,EventSponsorItemBo.class);

        List<DictBO> listfl=getDictBOList(request,"hdfl");
        List<DictBO> listxs=getDictBOList(request,"hdxs");
        List<DictBO> listbq=getDictBOList(request,"tjbq");
        List<DictBO> listbm=getDictBOList(request,"bmbtzl");
        mav.addObject("expleve", expleve);
        mav.addObject("province",list);
        mav.addObject("dict",listfl);
        mav.addObject("dictxs",listxs);
        mav.addObject("dictbq",listbq);
        mav.addObject("dictbm",listbm);
        mav.addObject("eventSponsor",eventSponsorItemBo);
        return mav;
    }

    /**
     * 跳转至活动管理修改页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView upd(@PathVariable(value = "id") String id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/form_upd");
        ExpLevelRs expleve=SoaConnectionFactory.get(request,ConstantsUri.UEXPLEVEL_LIST,null, ExpLevelRs.class);
        ProvinceListRs list = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
        EventSponsorItemBo eventSponsorItemBo=SoaConnectionFactory.get(request,ConstantsUri.CMS_EVENTSPONSOR,null,EventSponsorItemBo.class);
        List<DictBO> listfl=getDictBOList(request,"hdfl");
        List<DictBO> listxs=getDictBOList(request,"hdxs");
        List<DictBO> listbq=getDictBOList(request,"tjbq");
        List<DictBO> listbm=getDictBOList(request,"bmbtzl");

        EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,id);
        if(eventSaveBo.getData().getEvent().getProvince()!=null&&!"".equals(eventSaveBo.getData().getEvent().getProvince())){
            CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, eventSaveBo.getData().getEvent().getProvince());
            mav.addObject("city",cityListRs);
        }
        mav.addObject("expleve", expleve);
        mav.addObject("province",list);
        mav.addObject("dict",listfl);
        mav.addObject("dictxs",listxs);
        mav.addObject("dictbq",listbq);
        mav.addObject("dictbm",listbm);
        mav.addObject("event",eventSaveBo.getData().getEvent());
        mav.addObject("modelItemList",eventSaveBo.getData().getModelItemList());
        mav.addObject("eventSponsor",eventSponsorItemBo);
        mav.addObject("path",SpringCtxHolder.getProperty("picdomain"));

        return mav;
    }


    /**
     * 跳转至活动名单页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/hd_md_list.php", method = RequestMethod.GET)
    public ModelAndView hd_md_list(@RequestParam(value = "status", required = false,defaultValue = "") String status,
                                   @RequestParam(value = "name", required = false,defaultValue = "") String name,
                                   @RequestParam(value = "id", required = false) String id,PagerSpec pagerSpec,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_md_list");
        Map map=new HashMap();
        map.put("eventId",id);
        map.put("page","1");
        map.put("size","1000");
        map.put("name",name);
        map.put("status",status);
        EventApplyItemBo eventApplyItemBo = SoaConnectionFactory.get(request, ConstantsUri.CMS_EVENTAPPLY, map, EventApplyItemBo.class);
        mav.addObject("eventApplyItemBo",eventApplyItemBo);
        mav.addObject("id",id);
        mav.addObject("status",status);
        mav.addObject("name",name);
        return mav;
    }





    /**
     * 跳转至活动统计页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/hd_tj.php", method = RequestMethod.GET)
    public ModelAndView hd_tj(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_tj");
        return mav;
    }

    /**
     * 跳转至活动推广页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/hd_tg.php", method = RequestMethod.GET)
    public ModelAndView hd_tg(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_tg");
        return mav;
    }



    /**
     * 报名名单批量删除
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchDel", method = RequestMethod.POST)
    public ModelAndView batchDel(@RequestParam(value = "ids") String ids, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            if(id.length>0){
                IdsBo idsbo=new IdsBo();
                idsbo.setIds(id);
                result=SoaConnectionFactory.post(request,ConstantsUri.CMS_EVENTAPPLY_LIST_DEL,idsbo,BaseResponse.class);
            }
        }
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 报名名单批量审核
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchSh", method = RequestMethod.POST)
    public ModelAndView batchSh(@RequestParam(value = "ids") String ids,
            @RequestParam(value = "eventid") String eventid,
            @RequestParam(value = "userids") String userids
            , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        List<String> list=new ArrayList<String>();
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            String []userid=userids.split(",");
            if(id.length>0){
                IdsBo idsbo=new IdsBo();
                idsbo.setIds(id);
                result=SoaConnectionFactory.put(request,ConstantsUri.CMS_EVENTAPPLY_LIST_SH,idsbo,BaseResponse.class);
                for(int i=0;i<userid.length;i++){
                    list.add(userid[i]);
                }
            }

        }
        mav.addObject("result", result);
        if("2000".equals(result.getCode())){
            EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,eventid);
            MessageSendBo messageSendBo=new MessageSendBo();
            messageSendBo.setUserIds(list);
            String shi="";
            ProvinceListRs lists = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
            if(eventSaveBo.getData().getEvent().getProvince()!=null&&!"".equals(eventSaveBo.getData().getEvent().getProvince())){
                CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, eventSaveBo.getData().getEvent().getProvince());
                //mav.addObject("city",cityListRs);
                for(int i=0;i<cityListRs.getDataList().size();i++){
                    if(cityListRs.getDataList().get(i).getCityId().equals(eventSaveBo.getData().getEvent().getCity())){
                        shi=cityListRs.getDataList().get(i).getCity();
                        break;
                    }
                }
            }
            String shen="";
            for(int i=0;i<lists.getDataList().size();i++){
                if(lists.getDataList().get(i).getProvinceId().equals(eventSaveBo.getData().getEvent().getProvince())){
                    shen=lists.getDataList().get(i).getProvince();
                    break;
                }
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String msg="您好,"+eventSaveBo.getData().getEvent().getTitle()+"活动已审核通过，请于"+sdf.format(eventSaveBo.getData().getEvent().getBegintime())+
                    "到"+sdf.format(eventSaveBo.getData().getEvent().getEndtime())+",地点:"+shen+shi+eventSaveBo.getData().getEvent().getAddress()+"准时参加，感谢您的参与！";
            messageSendBo.setWebMsg(msg);
            messageSendBo.setTemplateid("W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc");
            Map map=new HashMap();
            map.put("first",eventSaveBo.getData().getEvent().getEventId());
            map.put("keyword1","您好,"+""+eventSaveBo.getData().getEvent().getTitle()+"活动已审核通过，请于"+sdf.format(eventSaveBo.getData().getEvent().getBegintime())+
                    "到"+sdf.format(eventSaveBo.getData().getEvent().getEndtime())+",地点"+shen+shi+eventSaveBo.getData().getEvent().getAddress()+"准时参加，感谢您的参与！");
            messageSendBo.setDataList(map);
            SoaConnectionFactory.post(request, ConstantsUri.BUSINESS_MSG_SEND_BYQDBATCH, messageSendBo, BaseResponse.class);
        }
        return mav;
    }

    /**
     * 消息提醒
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/eventMsg", method = RequestMethod.POST)
    public ModelAndView eventMsg(
                                @RequestParam(value = "eventid") String eventid,
                                @RequestParam(value = "userids") String userids
            , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        List<String> list=new ArrayList<String>();
        if(userids!=null&&!"".equals(userids)){
            String []userid=userids.split(",");
            if(userid.length>0){
                for(int i=0;i<userid.length;i++){
                    list.add(userid[i]);
                }
            }

        }
        EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,eventid);
        MessageSendBo messageSendBo=new MessageSendBo();
        messageSendBo.setUserIds(list);
        String shi="";
        ProvinceListRs lists = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
        if(eventSaveBo.getData().getEvent().getProvince()!=null&&!"".equals(eventSaveBo.getData().getEvent().getProvince())){
            CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, eventSaveBo.getData().getEvent().getProvince());
            //mav.addObject("city",cityListRs);
            for(int i=0;i<cityListRs.getDataList().size();i++){
                if(cityListRs.getDataList().get(i).getCityId().equals(eventSaveBo.getData().getEvent().getCity())){
                    shi=cityListRs.getDataList().get(i).getCity();
                    break;
                }
            }
        }
        String shen="";
        for(int i=0;i<lists.getDataList().size();i++){
            if(lists.getDataList().get(i).getProvinceId().equals(eventSaveBo.getData().getEvent().getProvince())){
                shen=lists.getDataList().get(i).getProvince();
                break;
            }
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String msg="您好,"+eventSaveBo.getData().getEvent().getTitle()+"活动已审核通过，请于"+sdf.format(eventSaveBo.getData().getEvent().getBegintime())+
                "到"+sdf.format(eventSaveBo.getData().getEvent().getEndtime())+",地点:"+shen+shi+eventSaveBo.getData().getEvent().getAddress()+"准时参加，感谢您的参与！";
        messageSendBo.setWebMsg(msg);
        messageSendBo.setTemplateid("W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc");
        Map map=new HashMap();
        map.put("first","审核通过");
        map.put("keyword1",eventSaveBo.getData().getEvent().getTitle());
        map.put("keyword2","您好,"+""+eventSaveBo.getData().getEvent().getTitle()+"活动已审核通过，请于"+sdf.format(eventSaveBo.getData().getEvent().getBegintime())+
                "到"+sdf.format(eventSaveBo.getData().getEvent().getEndtime())+",地点"+shen+shi+eventSaveBo.getData().getEvent().getAddress()+"准时参加，感谢您的参与！");
        map.put("remark","");
        messageSendBo.setDataList(map);
        result= SoaConnectionFactory.post(request, ConstantsUri.BUSINESS_MSG_SEND_BYQDBATCH, messageSendBo, BaseResponse.class);
        mav.addObject("result", result);
        return mav;
    }


    /**
     * 报名名单批量拒绝审核
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchNoSh", method = RequestMethod.POST)
    public ModelAndView batchNoSh(@RequestParam(value = "ids") String ids,
                                  @RequestParam(value = "eventid") String eventid,
                                  @RequestParam(value = "userids") String userids,
            @RequestParam(value = "text") String text
            , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        List<String> list=new ArrayList<String>();
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            String []userid=userids.split(",");
            if(id.length>0){
                IdsBo idsbo=new IdsBo();
                idsbo.setIds(id);
                idsbo.setText(text);
                result=SoaConnectionFactory.put(request,ConstantsUri.CMS_EVENTAPPLY_NO_LIST_SH,idsbo,BaseResponse.class);
                for(int i=0;i<userid.length;i++){
                    list.add(userid[i]);
                }
            }
        }
        mav.addObject("result", result);
        if("2000".equals(result.getCode())){
            EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,eventid);
            MessageSendBo messageSendBo=new MessageSendBo();
            messageSendBo.setUserIds(list);
            String shi="";
            ProvinceListRs lists = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
            if(eventSaveBo.getData().getEvent().getProvince()!=null&&!"".equals(eventSaveBo.getData().getEvent().getProvince())){
                CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, eventSaveBo.getData().getEvent().getProvince());
                //mav.addObject("city",cityListRs);
                for(int i=0;i<cityListRs.getDataList().size();i++){
                    if(cityListRs.getDataList().get(i).getCityId().equals(eventSaveBo.getData().getEvent().getCity())){
                        shi=cityListRs.getDataList().get(i).getCity();
                        break;
                    }
                }
            }
            String shen="";
            for(int i=0;i<lists.getDataList().size();i++){
                if(lists.getDataList().get(i).getProvinceId().equals(eventSaveBo.getData().getEvent().getProvince())){
                    shen=lists.getDataList().get(i).getProvince();
                    break;
                }
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String msg="您报名的["+eventSaveBo.getData().getEvent().getTitle()+"]活动，已被拒绝,理由:"+text;
            messageSendBo.setWebMsg(msg);
            messageSendBo.setTemplateid("W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc");
            Map map=new HashMap();
            map.put("first","审核不通过");
            map.put("keyword1",eventSaveBo.getData().getEvent().getTitle());
            map.put("keyword2","您报名的["+eventSaveBo.getData().getEvent().getTitle()+"]活动，已被拒绝,理由:"+text);
            map.put("remark","");
            messageSendBo.setDataList(map);
            SoaConnectionFactory.post(request, ConstantsUri.BUSINESS_MSG_SEND_BYQDBATCH, messageSendBo, BaseResponse.class);
        }
        return mav;
    }





    /**
     * 报名名单导出
     *
     */
    @RequestMapping(value = "/download.php", method = RequestMethod.GET)
    public void findBuyCSV(@RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "id", required = false) String id,HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, String>> dataList=null;
        ActivityMdCriteria criteria = new ActivityMdCriteria();
        criteria.setName(name);
        criteria.setStatus(status);
        criteria.setEventId(id);
        EventApplyItemBo eventApplyItemBo = SoaConnectionFactory.get(request, ConstantsUri.CMS_EVENTAPPLY, criteria, EventApplyItemBo.class);
        String sTitle = "序号,名称,手机,邮箱,状态,报名时间";
        String fName = "buywater_";
        String mapKey = "applyId,name,tel,email,status,applytime";
        dataList = new ArrayList<>();
        Map<String, String> map = null;
        int index=1;
        for (EventApplyBo order : eventApplyItemBo.getDataList()) {
            map = new HashMap<String, String>();
            map.put("applyId", index+"");
            map.put("name", order.getName());
            map.put("tel", order.getTel());
            map.put("email", order.getEmail());
            map.put("status", (order.getStatus()!=null&&!"".equals(order.getStatus()))?"1".equals(order.getStatus())?"已审核":"2".equals(order.getStatus())?"拒绝审核":"待审核":"待处理");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("applytime", (order.getApplytime()!=null&&!"".equals(order.getApplytime()))?sdf.format(order.getApplytime()):"");
            dataList.add(map);
            index++;
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fName, response);
            ExportUtil.doExport(dataList, sTitle, mapKey, os);

        } catch (Exception e) {
            //logger.error("购买CSV失败", e);
        }
    }


    /**
     * 活动管理保存操作
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/save.php",method = RequestMethod.POST)
    public ModelAndView add( @RequestBody EventSaveUpdBo eventSaveUpdBo,HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        EventSponsorDataBo result = null;
        EventSaveBo results = null;
        List<EventModelItemBo> eventModelItemBo=eventSaveUpdBo.getEventModelItemBo(eventSaveUpdBo);
        EventBo eventBo=eventSaveUpdBo.getEventBo(eventSaveUpdBo);
        List<EventSponsorBo> eventSponsorBo=eventSaveUpdBo.getEventSponsorBo(eventSaveUpdBo);
        EventSponsorBo zbf=null;
        if (CommonUtils.nullOrBlank(eventSaveUpdBo.getEventId().trim())) {
            for(int i=0;i<eventSponsorBo.size();i++){
                EventSponsorBo ev=eventSponsorBo.get(i);
                if(ev.getSponsorId()!=null&&!"".equals(ev.getSponsorId().trim())){
                    result = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENTSPONSOR_UPD, ev, EventSponsorDataBo.class,ev.getSponsorId().trim());
                }else{
                    result = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENTSPONSOR_SAVE, ev, EventSponsorDataBo.class);
                }
                if(zbf==null){
                    if(eventSaveUpdBo.getZbfindex()==i){
                        eventBo.setSponsorId(result.getData().getSponsorId().trim());
                    }
                }
            }
            EventSaveBo esb=new EventSaveBo();
            eventBo.setStatus(eventSaveUpdBo.getStatus());
            esb.setEvent(eventBo);
            esb.setModelItemList(eventModelItemBo);
            results = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENT_SAVE, esb, EventSaveBo.class);
        } else {
            for(int i=0;i<eventSponsorBo.size();i++){
                EventSponsorBo ev=eventSponsorBo.get(i);
                if(ev.getSponsorId()!=null&&!"".equals(ev.getSponsorId().trim())){
                    result = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENTSPONSOR_UPD, ev, EventSponsorDataBo.class,ev.getSponsorId());
                }else{
                    result = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENTSPONSOR_SAVE, ev, EventSponsorDataBo.class);
                }
                if(zbf==null){
                    if(eventSaveUpdBo.getZbfindex()==i){
                        eventBo.setSponsorId(result.getData().getSponsorId().trim());
                    }
                }
            }
            EventSaveBo esb=new EventSaveBo();
            eventBo.setStatus(eventSaveUpdBo.getStatus());
            esb.setEvent(eventBo);
            esb.setModelItemList(eventModelItemBo);
            results = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENT_UPD, esb, EventSaveBo.class,eventSaveUpdBo.getEventId());
        }
        mav.addObject("result", results);
        return mav;
    }


    /**
     * 活动管理复制操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.POST)
    public ModelAndView copy(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,id);
        EventSaveBo data = eventSaveBo.getData();
        data.getEvent().setStatus("1");
        EventSaveBo results = SoaConnectionFactory.post(request, ConstantsUri.CMS_EVENT_SAVE, data, EventSaveBo.class);
        mav.addObject("result", results);
        return mav;
    }

    /**
     * 活动管理撤销发布操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/revoke/{id}", method = RequestMethod.POST)
    public ModelAndView Revoke(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,id);
        EventSaveBo data = eventSaveBo.getData();
        data.getEvent().setStatus("4");
        EventSaveBo results = SoaConnectionFactory.put(request, ConstantsUri.CMS_EVENT_UPD, data, EventSaveBo.class,id);
        mav.addObject("result", results);
        return mav;
    }

    /**
     * 活动管理删除操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView del(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.CMS_EVENT_UPD, null, BaseResponse.class, id);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 主办方删除操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/sponsor/{id}", method = RequestMethod.POST)
    public ModelAndView sponsorDel(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.CMS_EVENTSPONSOR_DEL, null, BaseResponse.class, id);
        mav.addObject("result", result);
        return mav;
    }


    /**
     * 活动管理上传图片操作
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/upload.php",method = RequestMethod.POST)
    public @ResponseBody BaseResponse add(MultipartHttpServletRequest multipartRequest,String path, MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session,String fileTypeTag) {
        MultipartFile file =  multipartRequest.getFile("filep");
        CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
        getPicFileType();
        FjDto fjDto = new FjDto();
        fjDto.setFileName(file.getOriginalFilename());
        try {
            byte[] img = file.getBytes();
            if(file.getSize()>1024*200){
                return new BaseResponse("300", "文件大小不能大于200k");
            }
            if (getFileTypeByStream(img) == null) {
                return new BaseResponse("300", "上传文件类型错误!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
            fjDto.setFileContent(new BASE64Encoder().encode(file.getBytes()));
            cmsFileUploadDto.setDirectory(path.replaceAll("!", "/"));
            cmsFileUploadDto.getFjBo().add(fjDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.FILEUPBASE64, cmsFileUploadDto, FileListDto.class);
        if(fileListDto.getDataList().size()>0){
            return new BaseResponse("200",  "/images" + fileListDto.getDataList().get(0).getFilePath());
        }else{
            return new BaseResponse("300", "上传失败");
        }
    }
    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    public String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        System.out.println(filetypeHex);
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP
                .entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue().toUpperCase();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp","424D");
    }

    public String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 活动统计操作
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/lltj", method = RequestMethod.GET)
    public ModelAndView eventtj(@RequestParam(value = "startTime", required = false)String startTime
            ,@RequestParam(value = "endTime", required = false) String endTime
            ,@RequestParam(value = "type", required = false) String type
            ,@RequestParam(value = "enventId", required = false) String enventId, HttpServletRequest request) {
        //ModelAndView mav = new ModelAndView("cms/activity/hd_tj");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        //SoaConnectionFactory.get
        EventBmBo bm=new EventBmBo();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        bm.setEndTime(endTime==null?formatter.format(new Date()):endTime);
        bm.setStartTime(startTime==null?formatter.format(new Date()):startTime);
        bm.setEventId(enventId);
        bm.setType(type);
        EventlltjListItemBo eventlltjListItemBo=SoaConnectionFactory.get(request,ConstantsUri.CMS_EVENT_APPLY_SELECTLLTJ,bm,EventlltjListItemBo.class);

        //Array li=new Array();
        if(eventlltjListItemBo!=null&&eventlltjListItemBo.getData().getLltj().size()>0){
            Map<String, List<EventlltjBo>> lltj = eventlltjListItemBo.getData().getLltj();
            int count=0;
            List<String> listtitle=new ArrayList<String>();
            List<Map<String,String>> listtitles=new ArrayList<Map<String,String>>();
            for (String key : lltj.keySet()) {
                List<List<String>> lists=new ArrayList<List<String>>();
                List<EventlltjBo> llsjs = lltj.get(key);
                Map<String,String> map=new HashMap<>();
                map.put("label",key);
                map.put("color",getColor());
                listtitles.add(map);
                String llsj_str="";
                for(int j=0;j<llsjs.size();j++){
                    List<String> list=new ArrayList<String>();
                    llsj_str+=llsjs.get(j).getLlsj()+",";
                    list.add(llsjs.get(j).getLlsj());
                    list.add(llsjs.get(j).getCnt()+"");
                    if(llsjs.size()!=listtitle.size()){
                        if(j==listtitle.size()){
                            listtitle.add(llsjs.get(j).getLlsj());
                        }
                    }
                    lists.add(list);
                }
                if(lists.size()!=listtitle.size()){
                    if("".equals(llsj_str)){
                        for(String str : listtitle){
                            List<String> list=new ArrayList<String>();
                            list.add(str);
                            list.add("0");
                            lists.add(list);
                        }
                    }else{
                        for(String str : listtitle){
                            if(llsj_str.indexOf(str)<=-1){
                                List<String> list=new ArrayList<String>();
                                list.add(str);
                                list.add("0");
                                lists.add(list);
                            }
                        }
                    }
                }
                mav.addObject("title"+count,Arrays.toString(lists.toArray()) );
                count++;
            }
            StringBuffer sb=new StringBuffer();
            for (int i=0;i<listtitle.size();i++) {
                if(i!=0){
                    sb.append(",");
                }
                sb.append(listtitle.get(i));
            }
            mav.addObject("listtitle",sb.toString());
            String json= JSONArray.fromObject(listtitles).toString();
            mav.addObject("listtitles",json );
        }
        return mav;
    }




    private String getColor(){
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length()==1 ? "0" + red : red ;
        //判断绿色代码的位数
        green = green.length()==1 ? "0" + green : green ;
        //判断蓝色代码的位数
        blue = blue.length()==1 ? "0" + blue : blue ;
        //生成十六进制颜色值
        String color = "#"+red+green+blue;
        return color;
    }

    /**
     * 报名统计
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/bmtj/{id}", method = RequestMethod.GET)
    public ModelAndView bmtj(@PathVariable(value = "id") String id, HttpServletRequest request) {
        //ModelAndView mav = new ModelAndView("cms/activity/hd_tj");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        //SoaConnectionFactory.get
        EventBmTjItemBo bm=new EventBmTjItemBo();
        bm.setEventId(id);
        EventbmtjDataBo eventbmtjDataBo=SoaConnectionFactory.get(request,ConstantsUri.CMS_EVENT_APPLY_SELECTLLTJ_BM,bm,EventbmtjDataBo.class);
        //EventbmtjDataBo eventbmtjDataBo=SoaConnectionFactory.getRestfulWithoutToken(request,ConstantsUri.CMS_EVENT_APPLY_SELECTLLTJ_BM,bm,EventbmtjDataBo.class);
        //Array li=new Array();
        List<Integer> list=new ArrayList<Integer>();
        list.add(eventbmtjDataBo.getData().getPeoppleNum());
        list.add(eventbmtjDataBo.getData().getCnt1());
        list.add(eventbmtjDataBo.getData().getCnt2());
        mav.addObject("data",Arrays.toString(list.toArray()));
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) eventbmtjDataBo.getData().getCnt1() / (float) eventbmtjDataBo.getData().getPeoppleNum() * 100);
        String result1 = numberFormat.format((float) eventbmtjDataBo.getData().getCnt2() / (float) eventbmtjDataBo.getData().getPeoppleNum() * 100);
        List<String> lists=new ArrayList<String>();
        lists.add("'活动计划人数'");
        lists.add("'实际参与人数("+result+"%)'");
        lists.add("'审核通过人数("+result1+"%)'");
        mav.addObject("datas",Arrays.toString(lists.toArray()));
        return mav;


    }


    /**
     * 活动统计页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/tj/{id}", method = RequestMethod.GET)
    public ModelAndView eventtjym(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_tj");
        mav.addObject("id", id);
        return mav;
    }

    /**
     * 活动推广页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/tg/{id}", method = RequestMethod.GET)
    public ModelAndView eventtgym(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/activity/hd_tg");
        EventDataBo eventSaveBo=SoaConnectionFactory.getRestful(request,ConstantsUri.CMS_EVENT_UPD,null,EventDataBo.class,id);
        StringBuffer sb=new StringBuffer();
        sb.append("http://www.baidu.com?eventid=").append(id);
        StringBuffer sbbm=new StringBuffer();
        sbbm.append("http://www.baidu.com?eventid=").append(id);
        StringBuffer sban=new StringBuffer();
        sban.append("<a href=\"\" style=\"display:inline-block; padding:0px 5px; background: #009900; color: #fff;\">立即报名</a>");
        List<EventModelItemBo> modelItemList = eventSaveBo.getData().getModelItemList();
        StringBuffer bmform=new StringBuffer();
        if(modelItemList!=null&&modelItemList.size()>0){
            bmform.append("<form id='bmform'>");
            for( EventModelItemBo eventModelBo:modelItemList){
                bmform.append(eventModelBo.getItemLabel());
                bmform.append("<input name='").append(eventModelBo.getField()).append("' id='").append(eventModelBo.getField()).append("'>");
            }
            bmform.append("</form>");
        }
        mav.addObject("url",sb.toString());
        mav.addObject("bmurl",sbbm.toString());
        mav.addObject("bman",sban.toString());
        mav.addObject("bmform",bmform.toString());
        mav.addObject("id",id);
        return mav;
    }
}
