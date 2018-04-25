package com.abc.controller.financed;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.DateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.ConsumerRq;
import com.abc.soa.request.consumer.MsgSendDetailRq;
import com.abc.soa.request.consumer.TagsRq;
import com.abc.soa.request.consumer.VipLevelRq;
import com.abc.soa.request.financed.CouponActivityRq;
import com.abc.soa.request.financed.CouponRq;
import com.abc.soa.request.financed.CouponUserRq;
import com.abc.soa.response.consumer.*;
import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.consumer.bo.Tag;
import com.abc.soa.response.consumer.bo.VipLevel;
import com.abc.soa.response.financed.CouponActivityRs;
import com.abc.soa.response.financed.CouponRs;
import com.abc.soa.response.financed.CouponUserRs;
import com.abc.soa.response.financed.bo.CouponActivity;
import com.abc.soa.response.financed.bo.CouponActivityBO;
import com.abc.soa.response.financed.bo.CouponBO;
import com.abc.soa.response.financed.bo.CouponListBO;
import com.abc.soa.response.region.City;
import com.abc.soa.response.region.Province;
import com.abc.soa.response.region.Region;
import com.abc.soa.response.region.RegionListRs;
import com.abc.soa.response.system.bo.MenuListBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuhao on 2017-05-24.
 * 数据字典管理1
 */
@Controller
@RequestMapping(value = "/financed")
public class CouponController extends BaseController {

    /**
     * 优惠券列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:coupon")
    @RequestMapping("/coupon/list.php")
    public String selectList(CouponRq rq, HttpServletRequest request, Model model) {
        CouponRs couponRs = SoaConnectionFactory.get(request, ConstantsUri.COUPON_LIST, rq, CouponRs.class);
            model.addAttribute("couponRs", couponRs.getDataList());
        rq.setTotalItems(couponRs.getTotal());
            rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
            rq.calculate();
            model.addAttribute("BaseRq", rq);
        model.addAttribute("couponMode", getDictBOList(request, "couponMode"));
        model.addAttribute("couponType", getDictBOList(request, "couponType"));
        model.addAttribute("couponStatus", getDictBOList(request, "couponStatus"));
            return "/financed/coupon/list";
    }

    /**
     * 优惠券列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:couponActivity")
    @RequestMapping("/couponActivity/list.php")
    public String selectActivityList(CouponActivityRq rq, HttpServletRequest request, Model model) {
        CouponActivityRs couponActivityRs = SoaConnectionFactory.get(request, ConstantsUri.COUPONACTIVITY_LIST, rq, CouponActivityRs.class);
        model.addAttribute("couponActivityRs", couponActivityRs.getDataList());
        rq.setTotalItems(couponActivityRs.getTotal());
        rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
        rq.calculate();
        model.addAttribute("BaseRq", rq);
        model.addAttribute("couponStatus", getDictBOList(request, "couponStatus"));
        return "/financed/couponActivity/list";
    }
    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("financed:coupon")
    @RequestMapping("/coupon/editform.php")
    public String editform(@RequestParam(required = false) String id,@RequestParam(required = false) String lookType, HttpServletRequest request, Model model) {
        model.addAttribute("lookType", lookType);
        if (!StringUtils.isEmpty(id)) {
            CouponRs couponRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPON_ONE, null, CouponRs.class, id);
            CouponListBO data = couponRs.getData();
            model.addAttribute("couponRs", data);
        }
        model.addAttribute("couponMode", getDictBOList(request, "couponMode"));
        model.addAttribute("couponType", getDictBOList(request, "couponType"));
        return "/financed/coupon/form_edit";
    }

    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("financed:couponActivity")
    @RequestMapping("/couponActivity/editform.php")
    public String editActivityform(@RequestParam(required = false) String id,@RequestParam(required = false) String lookType, HttpServletRequest request, Model model) {
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
        /*List<Area> areaList = regionListRs.getAreaList();
        for(Area area:areaList){
            Region region = new Region();
            region.setRegionId(area.getAreaId());
            region.setRegionName(area.getArea());
            region.setpId(area.getCityId());
            dataList.add(region);
        }*/
        model.addAttribute("regions", dataList);
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size", "0");
        MenuListBO menus = SoaConnectionFactory.get(request, ConstantsUri.SYS_MENU, map, MenuListBO.class);
        model.addAttribute("menus", dataList);
        VipLevelRq levelRq = new VipLevelRq();
        VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        List<VipLevel> levelList = levelRs.getDataList();
        if (!StringUtils.isEmpty(id)) {
            CouponActivityRs couponActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPONACTIVITY_ONE, null, CouponActivityRs.class, id);
            CouponActivity data = couponActivityRs.getData();
            CouponRs couponRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPON_ONE, null, CouponRs.class, data.getCouponId());
            data.setCouponName(couponRs.getData().getCouponName());
            if(!StringUtils.isEmpty(couponRs.getData().getValidStartTime())){
                data.setStart(DateUtil.formatDateTime(couponRs.getData().getValidStartTime(), "yyyy-MM-dd"));
            }
            if(!StringUtils.isEmpty(couponRs.getData().getValidEndTime())){
                data.setEnd(DateUtil.formatDateTime(couponRs.getData().getValidEndTime(), "yyyy-MM-dd"));
            }
            data.setValidType(couponRs.getData().getValidType());
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
            String vips = data.getVips();
            String levels = "";
            if(!StringUtils.isEmpty(vips)){
                String [] ids = vips.split(",");
                if(ids.length>0){
                    for(String vipid : ids){
                        for(int i =0;i<levelList.size();i++){
                            if(vipid.equals(levelList.get(i).getLevelCode())){
                                if(!StringUtils.isEmpty(levels)){
                                    levels = levelList.get(i).getLevel()+ ","+levels;
                                }
                                else{
                                    levels = levelList.get(i).getLevel();
                                }
                            }
                        }
                    }
                }
                data.setLevels(levels);
            }
            data.setTagName(tagName);
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
            model.addAttribute("couponActivityRs", data);
        }
        CouponRq rq = new CouponRq();
        rq.setStatus("2");
        rq.setSize(99999);
        CouponRs couponRs = SoaConnectionFactory.get(request, ConstantsUri.COUPON_LIST, rq, CouponRs.class);
        model.addAttribute("couponRs", couponRs.getDataList());
        model.addAttribute("imgPth", SpringCtxHolder.getProperty("picdomain"));
        model.addAttribute("levelRs",levelRs.getDataList());
        return "/financed/couponActivity/form_edit";
    }

    /**
     * 跳转到微信红包活动新增修改页面
     *
     * @param  couponUserRq
     * @param request
     * @return
     */
    @RequiresPermissions("financed:couponActivity")
    @RequestMapping("/couponActivity/getOrUseLog.php")
    public String consumerlook(CouponUserRq couponUserRq, HttpServletRequest request,
                               Model model) {
        CouponUserRs couponUserRs = SoaConnectionFactory.get(request,
                ConstantsUri.COUPONUSER_LIST, couponUserRq, CouponUserRs.class);
        model.addAttribute("couponUserRs", couponUserRs.getDataList());
        couponUserRq.setTotalItems(couponUserRs.getTotal());
        couponUserRq.calculate();
        model.addAttribute("BaseRq", couponUserRq);
        model.addAttribute("couponMode", getDictBOList(request, "couponMode"));
        model.addAttribute("couponType", getDictBOList(request, "couponType"));
        model.addAttribute("couponUseStatus", getDictBOList(request, "couponUseStatus"));
        return "/financed/couponActivity/consumer_look";
    }
    /**
     * 微信红包口令编辑
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("financed:coupon")
    @RequestMapping("/coupon/update.php")
    public @ResponseBody BaseResponse update(@RequestBody CouponBO update,@RequestParam(value = "status") String status,HttpServletRequest request){
        if(!StringUtils.isEmpty(update.getStartTime())){
            update.setValidStartTime(DateUtil.strToDate(update.getStartTime(), "yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(update.getEndTime())){
            update.setValidEndTime(DateUtil.strToDate(update.getEndTime(), "yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(update.getId())){
            if("2".equals(status)){
                update.setStatus(status);
            }
            return SoaConnectionFactory.put(request, ConstantsUri.COUPON_ONE, update, BaseResponse.class,update.getId());
        }
        else {
            update.setStatus(status);
            return SoaConnectionFactory.post(request, ConstantsUri.COUPON_LIST, update, BaseResponse.class);
        }
    }

    /**
     * 微信红包口令编辑
     *
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("financed:couponActivity")
    @RequestMapping("/coupon/activity/update.php")
    public @ResponseBody BaseResponse activityupdate(@RequestBody CouponActivityBO update,@RequestParam(value = "status") String status,HttpServletRequest request){
        if(!StringUtils.isEmpty(update.getStartTime())){
            update.setActivityStartTime(DateUtil.strToDate(update.getStartTime(), "yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(update.getEndTime())){
            update.setActivityEndTime(DateUtil.strToDate(update.getEndTime(), "yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(update.getRegStart())){
            update.setRegStartTime(DateUtil.strToDate(update.getRegStart(), "yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(update.getRegEnd())){
            update.setRegEndTime(DateUtil.strToDate(update.getRegEnd(), "yyyy-MM-dd"));
        }
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
        VipLevelRq levelRq = new VipLevelRq();
        VipLevelRs levelRs=SoaConnectionFactory.get(request, ConstantsUri.VIPLEVEL_LIST, levelRq, VipLevelRs.class);
        List<VipLevel> dataList = levelRs.getDataList();
        String levels = update.getLevels();
        String vips = "";
        if(!StringUtils.isEmpty(levels)){
            String [] names = levels.split(",");
            if(names.length>0){
                for(String name : names){
                    for(int i =0;i<dataList.size();i++){
                        if(name.equals(dataList.get(i).getLevel())){
                            if(!StringUtils.isEmpty(vips)){
                                vips = dataList.get(i).getLevelCode()+ ","+vips;
                            }
                            else{
                                vips = dataList.get(i).getLevelCode();
                            }
                        }
                    }
                }
            }
        }
        update.setVips(vips);
        if(!StringUtils.isEmpty(update.getId())){
            if("2".equals(status)){
                update.setStatus(status);
            }
            return SoaConnectionFactory.put(request, ConstantsUri.COUPONACTIVITY_ONE, update, BaseResponse.class,update.getId());
        }
        else {
            update.setStatus(status);
            return SoaConnectionFactory.post(request, ConstantsUri.COUPONACTIVITY_LIST, update, BaseResponse.class);
        }
    }

    /**
     * 优惠券停启用接口
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("financed:coupon")
    @PostMapping("/coupon/qyorty/{id}")
    public @ResponseBody BaseResponse check(@PathVariable(value = "id") String id, @RequestParam(value = "status") String status,HttpServletRequest request){
        CouponRs couponRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPON_ONE, null, CouponRs.class, id);
        CouponListBO bo = couponRs.getData();
        bo.setStatus(status);
        BaseResponse result=SoaConnectionFactory.put(request, ConstantsUri.COUPON_ONE, bo, BaseResponse.class, id);
        System.out.print(result);
        return result;
    }
    /**
     * 优惠券活动停启用接口
     *
     * @param
     * @param
     * @return
     */
    @RequiresPermissions("financed:couponActivity")
    @PostMapping("/coupon/activity/qyorty/{id}")
    public @ResponseBody BaseResponse activity(@PathVariable(value = "id") String id, @RequestParam(value = "status") String status,HttpServletRequest request){
        CouponActivityRs couponActivityRs = SoaConnectionFactory.getRestful(request, ConstantsUri.COUPONACTIVITY_ONE, null, CouponActivityRs.class, id);
        CouponActivity update = couponActivityRs.getData();
        update.setStatus(status);
        BaseResponse result=SoaConnectionFactory.put(request, ConstantsUri.COUPONACTIVITY_ONE, update, BaseResponse.class, id);
        System.out.print(result);
        return result;
    }
    /**
     * 微信红包活动删除
     *
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:coupon")
    @RequestMapping("/coupon/detele/{id}")
    public
    @ResponseBody
    BaseResponse del(@PathVariable(value = "id") String id, HttpServletRequest request) {
        return  SoaConnectionFactory.delete(request, ConstantsUri.COUPON_ONE, null, BaseResponse.class, id);
    }

    /**
     * 微信红包活动删除
     *
     * @param
     * @param request
     * @return
     */
    @RequiresPermissions("financed:couponActivity")
    @RequestMapping("/coupon/activity/detele/{id}")
    public
    @ResponseBody
    BaseResponse activitydel(@PathVariable(value = "id") String id, HttpServletRequest request) {
        return  SoaConnectionFactory.delete(request, ConstantsUri.COUPONACTIVITY_ONE, null, BaseResponse.class, id);
    }
}
