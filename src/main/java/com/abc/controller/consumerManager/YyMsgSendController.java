package com.abc.controller.consumerManager;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.*;
import com.abc.soa.response.consumer.*;
import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.consumer.bo.Tag;
import com.abc.soa.response.consumer.bo.YyMsgSendBO;
import com.abc.soa.response.region.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhao on 2017-05-24.
 * 数据字典管理1
 */
@Controller
@RequestMapping(value = "/consumerManager/operate/message")
public class YyMsgSendController extends BaseController {

    /**
     * redis管理功能列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping("/list.php")
    public String selectList(YyMsgSendRq rq, HttpServletRequest request, Model model) {
            YyMsgSendRs yyMsgSendRs = SoaConnectionFactory.get(request, ConstantsUri.YYMSGSEND_LIST, rq, YyMsgSendRs.class);
            model.addAttribute("yyMsgSendRs", yyMsgSendRs.getDataList());
            rq.setTotalItems(yyMsgSendRs.getTotal());
            rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
            rq.calculate();
            model.addAttribute("BaseRq", rq);
            return "consumer/yymsgsend/list";
    }

    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping("/editform.php")
    public String editform(@RequestParam(required = false) String id,@RequestParam(required = false) String lookType, HttpServletRequest request, Model model) {
        model.addAttribute("lookType", lookType);
        TagsRq tagsRq = new TagsRq();
        tagsRq.setPage(0);
        tagsRq.setSize(0);
        tagsRq.setType("user");
        TagsRs tagsRs = SoaConnectionFactory.get(request,
                ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
        List<Tag> tags = tagsRs.getDataList();
        model.addAttribute("tags", tags);
        model.addAttribute("taglib", getDictBOList(request, "taglib"));
        RegionListRs regionListRs = SoaConnectionFactory.get(request,
                ConstantsUri.REGION_LIST, "", RegionListRs.class);
        List<Region> dataList = new ArrayList<>();
        List<Province> provinceList = regionListRs.getProvinceList();
        for(Province province:provinceList){
            Region region = new Region();
            region.setRegionId(province.getProvinceId());
            region.setRegionName(province.getProvince());
            region.setpId("");
            dataList.add(region);
        }
        List<City> cityList = regionListRs.getCityList();
        for(City city:cityList){
            Region region = new Region();
            region.setRegionId(city.getCityId());
            region.setRegionName(city.getCity());
            region.setpId(city.getProvinceId());
            dataList.add(region);
        }
        List<Area> areaList = regionListRs.getAreaList();
        for(Area area:areaList){
            Region region = new Region();
            region.setRegionId(area.getAreaId());
            region.setRegionName(area.getArea());
            region.setpId(area.getCityId());
            dataList.add(region);
        }
        model.addAttribute("menus",dataList);
        if (!StringUtils.isEmpty(id)) {
            YyMsgSendRs yyMsgSendRs = SoaConnectionFactory.getRestful(request, ConstantsUri.YYMSGSEND_ONE, null, YyMsgSendRs.class, id);
            YyMsgSendBO data = yyMsgSendRs.getData();
            String tagIds = data.getTagIds();
            String tagName = "";
            if(!StringUtils.isEmpty(tagIds)){
                String [] ids = tagIds.split(",");
                if(ids.length>0){
                    for(String tagid : ids){
                        for(int i =0;i<tags.size();i++){
                            if(tagid.equals(tags.get(i).getId())){
                                if(!StringUtils.isEmpty(tagName)){
                                    tagName = tags.get(i).getTagName()+ ","+tagName;
                                }
                                else{
                                    tagName = tags.get(i).getTagName();
                                }
                            }
                        }
                    }
                }
                data.setTagnum(ids.length);
            }
            data.setTagName(tagName);
            String url = data.getUrl();
            if(!StringUtils.isEmpty(url)){
                data.setUrltitle(url.substring(url.indexOf(">")+1, url.lastIndexOf("<")));
                data.setUrlhref(url.substring(url.indexOf("href='") + 6, url.lastIndexOf("'>")));
            }
            data.setNum(data.getContent().length());
            String userIds = data.getUserIds();
            String username = "";
            if(!StringUtils.isEmpty(userIds)){
                String [] ids = userIds.split(",");
                if(ids.length>0){
                    for(String userId : ids){
                        ConsumerInfoRs consumerInfoRs = SoaConnectionFactory.getRestful(
                                request, ConstantsUri.CONSUMER_INFO, null,
                                ConsumerInfoRs.class, userId);
                        if(!StringUtils.isEmpty(username)){
                            username = consumerInfoRs.getUser().getUsername()+ ","+username;
                        }
                        else{
                            username = consumerInfoRs.getUser().getUsername();
                        }
                    }
                    data.setUsernum(ids.length);
                }
                data.setUserName(username);
            }
            model.addAttribute("yyMsgSendRs", data);
        }
        return "consumer/yymsgsend/form_edit";
    }


    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param  rq
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping("/look.php")
    public String look(MsgSendDetailRq rq,
                       HttpServletRequest request, Model model) {
        YyMsgSendRs yyMsgSendRs = SoaConnectionFactory.getRestful(request, ConstantsUri.YYMSGSEND_ONE, null, YyMsgSendRs.class, rq.getId());
        rq.setMessageId(rq.getId());
        MsgSendDetailRs msgSendDetailRs = SoaConnectionFactory.get(request, ConstantsUri.YYMSGSEND_SEND_LIST, rq, MsgSendDetailRs.class);
        model.addAttribute("msgSendDetailRs", msgSendDetailRs.getDataList());
        model.addAttribute("yyMsgSend", yyMsgSendRs.getData());
        rq.setTotalItems(msgSendDetailRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);
        return "consumer/yymsgsend/look";
    }

    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param  consumerRq
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping("/consumerlook.php")
    public String consumerlook(ConsumerRq consumerRq, @RequestParam(required = false) String userIds, HttpServletRequest request,
                               Model model) {
        ConsumerRs consumerRs = SoaConnectionFactory.get(request,
                ConstantsUri.CONSUMER_LIST, consumerRq, ConsumerRs.class);
        if(!StringUtils.isEmpty(userIds)){
            String [] names = userIds.split(",");
            if(names.length>0){
                for(String name : names){
                    for(int i =0;i<consumerRs.getDataList().size();i++){
                        if(name.equals(consumerRs.getDataList().get(i).getUsername())){
                            consumerRs.getDataList().get(i).setChecked(true);
                        }
                    }
                }
            }
        }
        model.addAttribute("userIds", userIds);
        model.addAttribute("consumers", consumerRs.getDataList());
        VipLevelRq levelRq=new VipLevelRq();
        levelRq.setStatus(true);
        levelRq.setPage(0);
        levelRq.setSize(0);
        VipLevelRs level=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        model.addAttribute("levels", level.getDataList());
        consumerRq.setTotalItems(consumerRs.getTotal());
        consumerRq.calculate();
        model.addAttribute("BaseRq", consumerRq);
        return "consumer/yymsgsend/consumer_look";
    }
    /**
     * 微信红包口令编辑
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping("/update.php")
    public @ResponseBody BaseResponse update(@RequestBody YyMsgSendBO update,HttpServletRequest request){
        TagsRq tagsRq = new TagsRq();
        tagsRq.setPage(0);
        tagsRq.setSize(0);
        tagsRq.setType("user");
        TagsRs tagsRs = SoaConnectionFactory.get(request,
                ConstantsUri.TAG_LIST, tagsRq, TagsRs.class);
        List<Tag> tags = tagsRs.getDataList();
        String tagName = update.getTagName();
        String tagIds = "";
        if(!StringUtils.isEmpty(tagName)){
            String [] names = tagName.split(",");
            if(names.length>0){
                for(String name : names){
                    for(int i =0;i<tags.size();i++){
                        if(name.equals(tags.get(i).getTagName())){
                            if(!StringUtils.isEmpty(tagIds)){
                                tagIds = tags.get(i).getId()+ ","+tagIds;
                            }
                            else{
                                tagIds = tags.get(i).getId();
                            }
                        }
                    }
                }
            }
        }
        update.setTagIds(tagIds);
        String userNames = update.getUserName();
        String userId = "";
        ConsumerRq consumerRq = new ConsumerRq();
        if(!StringUtils.isEmpty(userNames)){
            String [] names = userNames.split(",");
            if(names.length>0){
                for(String name : names){
                    consumerRq.setUsername(name);
                    ConsumerRs consumerRs = SoaConnectionFactory.get(request,
                            ConstantsUri.CONSUMER_LIST, consumerRq, ConsumerRs.class);
                    List<Consumer> dataList = consumerRs.getDataList();
                    if(dataList.size()>0){
                        Consumer consumer = dataList.get(0);
                        if(!StringUtils.isEmpty(userId)){
                            userId = consumer.getId() + ","+userId;
                        }
                        else{
                            userId = consumer.getId();
                        }
                    }
                }
            }
            update.setUserIds(userId);
        }
        if(!StringUtils.isEmpty(update.getId())){
            update.setStatus("1");
            return SoaConnectionFactory.put(request, ConstantsUri.YYMSGSEND_LIST, update, BaseResponse.class);
        }
        else {
            update.setStatus("1");
            return SoaConnectionFactory.post(request, ConstantsUri.YYMSGSEND_LIST, update, BaseResponse.class);
        }
    }
    /**
     * 删除
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView delmx(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.YYMSGSEND_ONE, null, BaseResponse.class, id);
        System.out.print(result);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 复用
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping(value = "/reuse/{id}", method = RequestMethod.POST)
    public ModelAndView reuse(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.post(request, ConstantsUri.YYMSGSEND_REUSE, null, BaseResponse.class, id);
        System.out.print(result);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 撤销
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("consumer:message")
    @RequestMapping(value = "/nuse/{id}", method = RequestMethod.POST)
    public ModelAndView nuse(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        YyMsgSendBO update = new YyMsgSendBO();
        update.setId(id);
        update.setStatus("0");
        BaseResponse result=SoaConnectionFactory.put(request, ConstantsUri.YYMSGSEND_LIST, update, BaseResponse.class);
        System.out.print(result);
        mav.addObject("result", result);
        return mav;
    }

}
