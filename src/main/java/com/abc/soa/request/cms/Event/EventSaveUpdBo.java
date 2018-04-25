package com.abc.soa.request.cms.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stuy on 2017/6/30.
 */
public class EventSaveUpdBo {
    private String category;
    private String shape;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    private String eventId;

    private String bmendtime;

    public String getBmendtime() {
        return bmendtime;
    }

    public void setBmendtime(String bmendtime) {
        this.bmendtime = bmendtime;
    }

    private String title;

    public int getZbfindex() {
        return zbfindex;
    }

    public void setZbfindex(int zbfindex) {
        this.zbfindex = zbfindex;
    }

    private String province;
    private String city;
    private String address;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String time;
    private String tag;
    private String summary;

    private String status;

    private int zbfindex;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeoppleNum() {
        return peoppleNum;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public int getIsUserGrade() {
        return isUserGrade;
    }

    public void setIsUserGrade(int isUserGrade) {
        this.isUserGrade = isUserGrade;
    }

    public void setPeoppleNum(String peoppleNum) {
        this.peoppleNum = peoppleNum;
    }



    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }




    private String description;
    private String peoppleNum;
    private int isCheck;
    private int isUserGrade;

    public String getFieldsf() {
        return fieldsf;
    }

    public void setFieldsf(String fieldsf) {
        this.fieldsf = fieldsf;
    }

    private String userGrade;
    private String[] sponsorName;
    private String[] sponsorLxr;
    private String[] sponsorTel;
    private String[] sponsorEmail;
    private String[] sponsorId;
    private String[] sponsorIntro;
    //private String[] fields;
    private String fieldsf;
    private String picture;

    private String field;

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    private String begintime;

    private String endtime;

    public EventSaveUpdBo(){}

    public String[] getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String[] sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String[] getSponsorLxr() {
        return sponsorLxr;
    }

    public void setSponsorLxr(String[] sponsorLxr) {
        this.sponsorLxr = sponsorLxr;
    }

    public String[] getSponsorTel() {
        return sponsorTel;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setSponsorTel(String[] sponsorTel) {
        this.sponsorTel = sponsorTel;
    }

    public String[] getSponsorEmail() {
        return sponsorEmail;
    }

    public void setSponsorEmail(String[] sponsorEmail) {
        this.sponsorEmail = sponsorEmail;
    }

    public String[] getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String[] sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String[] getSponsorIntro() {
        return sponsorIntro;
    }

    public void setSponsorIntro(String[] sponsorIntro) {
        this.sponsorIntro = sponsorIntro;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public EventBo getEventBo(EventSaveUpdBo eventSaveUpdBo){
        EventBo eventBo=new EventBo();
        eventBo.setAddress(eventSaveUpdBo.address);
        eventBo.setCategory(eventSaveUpdBo.category);
        eventBo.setCity(eventSaveUpdBo.city);
        eventBo.setCreatetime(new Date());
        eventBo.setIsCheck(eventSaveUpdBo.isCheck);
        eventBo.setIsUserGrade(eventSaveUpdBo.isUserGrade);
        eventBo.setDescription(eventSaveUpdBo.description);
        eventBo.setPeoppleNum(eventSaveUpdBo.peoppleNum);
        eventBo.setProvince(eventSaveUpdBo.province);
        eventBo.setShape(eventSaveUpdBo.shape);
        eventBo.setSummary(eventSaveUpdBo.summary);
        eventBo.setTag(eventSaveUpdBo.tag);
        eventBo.setTitle(eventSaveUpdBo.title);
        eventBo.setUserGrade(eventSaveUpdBo.userGrade);
        eventBo.setPicture(eventSaveUpdBo.picture);
        eventBo.setEventId(eventSaveUpdBo.eventId);
        try {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(eventSaveUpdBo.begintime!=null&&!"".equals(eventSaveUpdBo.begintime)){
            Date date=formatter.parse(eventSaveUpdBo.begintime);
            eventBo.setBegintime(date);
        }
        if(eventSaveUpdBo.endtime!=null&&!"".equals(eventSaveUpdBo.endtime)){
            Date date= formatter.parse(eventSaveUpdBo.endtime);
            eventBo.setEndtime(date);
        }
        if(eventSaveUpdBo.bmendtime!=null&&!"".equals(eventSaveUpdBo.bmendtime)){
            Date date=formatter.parse(eventSaveUpdBo.bmendtime);
            eventBo.setBmendtime(date);
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return eventBo;
    }

    public List<EventSponsorBo> getEventSponsorBo(EventSaveUpdBo eventSaveUpdBo){
        List<EventSponsorBo> list=new ArrayList<EventSponsorBo>();
        for(int i=0;i<eventSaveUpdBo.sponsorName.length;i++){
            if((eventSaveUpdBo.sponsorEmail[i]!=null&&!"".equals(eventSaveUpdBo.sponsorEmail[i].trim()))||
                    (eventSaveUpdBo.sponsorLxr[i]!=null&&!"".equals(eventSaveUpdBo.sponsorLxr[i].trim()))||
                    (eventSaveUpdBo.sponsorName[i]!=null&&!"".equals(eventSaveUpdBo.sponsorName[i].trim()))||
                    (eventSaveUpdBo.sponsorTel[i]!=null&&!"".equals(eventSaveUpdBo.sponsorTel[i].trim()))||
                    (eventSaveUpdBo.sponsorId[i]!=null&&!"".equals(eventSaveUpdBo.sponsorId[i].trim()))){
                EventSponsorBo eventSponsorBo=new EventSponsorBo();
                eventSponsorBo.setSponsorEmail(eventSaveUpdBo.sponsorEmail[i]);
                eventSponsorBo.setSponsorIntro(eventSaveUpdBo.sponsorIntro[i].trim());
                eventSponsorBo.setSponsorLxr(eventSaveUpdBo.sponsorLxr[i]);
                eventSponsorBo.setSponsorName(eventSaveUpdBo.sponsorName[i]);
                eventSponsorBo.setSponsorTel(eventSaveUpdBo.sponsorTel[i]);
                eventSponsorBo.setSponsorId(eventSaveUpdBo.sponsorId[i]);
                list.add(eventSponsorBo);
            }
        }
        return list;
    }

    public List<EventModelItemBo> getEventModelItemBo(EventSaveUpdBo eventSaveUpdBo){
        List<EventModelItemBo> list=new ArrayList<EventModelItemBo>();
        if(eventSaveUpdBo.field!=null&&!"".equals(eventSaveUpdBo.field)){
            String [] fields=eventSaveUpdBo.field.split("#");
            if(fields!=null&&fields.length>0){
                for(int i=0;i<fields.length;i++){
                    EventModelItemBo eventModelItemBo=new EventModelItemBo();
                    String [] field=fields[i].split(",");
                    eventModelItemBo.setField(field[0]);
                    eventModelItemBo.setItemLabel(field[1]);
                    if(eventSaveUpdBo.getFieldsf().indexOf(fields[i])>-1){
                        eventModelItemBo.setIsRequired(1);
                    }else{
                        eventModelItemBo.setIsRequired(0);
                    }
                    list.add(eventModelItemBo);
                }
            }
        }
        return list;
    }
}
