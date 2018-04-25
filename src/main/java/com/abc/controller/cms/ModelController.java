package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.dto.cms.model.ModelItemDataType;
import com.abc.dto.cms.model.SystemModelItem;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.ModelItemCriteria;
import com.abc.soa.request.cms.bo.ModelItemListReq;
import com.abc.soa.response.cms.bo.*;
import com.abc.soa.response.system.bo.DictListBO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型管理Controller
 * Created by zhouzhi on 2017-05-18.
 * modify by liuqi on 2017-06-05
 */
@Controller
public class ModelController {

    private final static String CHECK_RULE = "cms_model_item_checkRule";

    /* model列表查询 */
    @GetMapping("/cms/model/list.php")
    public String list(HttpServletRequest request,Model model){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
        ModelListBO models = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL, map, ModelListBO.class);
        model.addAttribute("models",models);
        return "cms/model/list";
    }

    /* model新增-修改 GET */
    @GetMapping("/cms/model/edit.php")
    public String edit(@RequestParam(value = "id", defaultValue = "") String id, HttpServletRequest request,Model model){
        if(!CommonUtils.nullOrBlank(id)){
            ModelRs modelRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_MODEL_ID, null, ModelRs.class, id);
            model.addAttribute("model",modelRs.getData());
        }
        return "cms/model/form";
    }

    /* model新增-修改 POST */
    @PostMapping(value = "/cms/model/save.php")
    public @ResponseBody BaseResponse save(HttpServletRequest request, @RequestBody ModelBO modelBo) throws IOException {
        if(CommonUtils.nullOrBlank(modelBo.getModelId())){
            return SoaConnectionFactory.post(request, ConstantsUri.CMS_MODEL, modelBo, ModelBO.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.CMS_MODEL_ID, modelBo, ModelBO.class, modelBo.getModelId());
        }
    }

    /* model 启用 禁用 */
    @PostMapping(value = "/cms/model/enable/{id}.php")
    public @ResponseBody BaseResponse enable(HttpServletRequest request,@PathVariable("id") String id,
                                             @RequestParam(value = "isDisabled") int isDisabled) throws IOException {
        ModelBO modelBo = new ModelBO();
        modelBo.setModelId(id);
        modelBo.setIsDisabled(isDisabled);
        return SoaConnectionFactory.put(request, ConstantsUri.CMS_MODEL_DISABLE, modelBo, ModelBO.class);
    }


    /* model单个删除模型*/
    @PostMapping(value = "/cms/model/del.php")
    public @ResponseBody BaseResponse del(HttpServletRequest request, @RequestParam(value = "id") String id) throws IOException {
        return SoaConnectionFactory.delete(request,ConstantsUri.CMS_MODEL_ID, null, BaseResponse.class, id);
    }

    /* model批量删除模型*/
    @PostMapping(value = "/cms/model/delList.php")
    public @ResponseBody BaseResponse delList(HttpServletRequest request, @RequestBody String[] ids) throws IOException {
        Map map = new HashMap();
        map.put("ids",ids);
        return SoaConnectionFactory.post(request, ConstantsUri.CMS_MODEL_DEL_LIST, map, BaseResponse.class);
    }

    /* model保存排列顺序(列表保存) */
    @PostMapping(value = "/cms/model/saveList.php")
    public @ResponseBody BaseResponse saveList(HttpServletRequest request, @RequestBody List<ModelBO> list) throws IOException {
        HashMap map = new HashMap();
        map.put("modelBoList",list);
        return SoaConnectionFactory.put(request, ConstantsUri.CMS_MODEL_UPDATE_LIST, map, ModelItemBo.class);
    }

    /* 查询模型item列表(每个model下面的栏目模型 或 内容模型)isChannel 1:栏位 0:内容 */
    @GetMapping("/cms/model/item/list.php")
    public String itemList(@RequestParam(value = "modelId") String modelId, @RequestParam(value = "isChannel") int isChannel,
                           HttpServletRequest request, Model model){
        ModelItemCriteria criteria = new ModelItemCriteria().withModelId(modelId).withIsChannel(isChannel);
        ModelItemListBo modelItems = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL_ITEM, criteria, ModelItemListBo.class);

        model.addAttribute("modelItems",modelItems);
        model.addAttribute("criteria",criteria);
        model.addAttribute("itemDataTypes",ModelItemDataType.values());
        if(isChannel == 1){
            model.addAttribute("sysModelItems", SystemModelItem.CHANNEL);
        }else{
            model.addAttribute("sysModelItems", SystemModelItem.CONTENT);
        }
        model.addAttribute("fieldList",getFieldList(modelItems.getDataList()));

        ModelRs modelRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_MODEL_ID, null, ModelRs.class, modelId);
        model.addAttribute("model",modelRs.getData());
        return "cms/model/item/list";
    }

    /* (item列表保存 : 系统默认字段 和 自定义字段)  */
    @PostMapping(value = "/cms/model/item/saveList.php")
    public @ResponseBody BaseResponse saveItemList(@RequestParam(value = "addSys",defaultValue = "false") boolean addSys,
                                                   HttpServletRequest request, @RequestBody ModelItemListReq modelItemListReq) throws IOException {
        if(addSys){//添加系统字段
            return SoaConnectionFactory.post(request, ConstantsUri.CMS_MODEL_ITEM_ADD_LIST, modelItemListReq, BaseResponse.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.CMS_MODEL_ITEM_UPDATE_LIST, modelItemListReq, BaseResponse.class);
        }
    }

    /* item新增-修改 GET*/
    @GetMapping("/cms/model/item/edit.php")
    public String itemEdit(@RequestParam(value = "modelId") String modelId,
                           @RequestParam(value = "isChannel") String isChannel,
                           @RequestParam(value = "itemId",defaultValue = "") String itemId,
                           HttpServletRequest request,Model model){
        if(!CommonUtils.nullOrBlank(itemId)){
            ModelItemRs modelItemRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_MODEL_ITEM_ID, null, ModelItemRs.class, itemId);
            model.addAttribute("item",modelItemRs.getData());
        }
        model.addAttribute("modelId",modelId);
        model.addAttribute("isChannel",isChannel);
        model.addAttribute("modelItemDataTypes", ModelItemDataType.values());
        //去数据字典获取校验规则
        DictListBO rs = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_NAME_ID, null, DictListBO.class, CHECK_RULE);
        if(rs!= null && rs.getDataList()!=null && rs.getDataList().size()>0){
            model.addAttribute("checkRules",rs.getDataList());
        }
        return "cms/model/item/form";
    }

    /* item新增-修改 POST */
    @PostMapping(value = "/cms/model/item/save.php")
    public @ResponseBody BaseResponse itemSave(HttpServletRequest request, @RequestBody ModelItemBo modelItemBo) throws IOException {
        if(CommonUtils.nullOrBlank(modelItemBo.getModelitemId())){
            return SoaConnectionFactory.post(request, ConstantsUri.CMS_MODEL_ITEM, modelItemBo, ModelItemBo.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.CMS_MODEL_ITEM_ID, modelItemBo, ModelItemBo.class, modelItemBo.getModelitemId());
        }
    }

    /* item删除 */
    @PostMapping(value = "/cms/model/item/del.php")
    public @ResponseBody BaseResponse itemDel(@RequestParam(value = "itemId") String modelItemId, HttpServletRequest request) throws IOException {
        return SoaConnectionFactory.delete(request, ConstantsUri.CMS_MODEL_ITEM_ID, null, BaseResponse.class, modelItemId);
    }

    /* item 批量删除 */
    @PostMapping(value = "/cms/model/item/delList.php")
    public @ResponseBody BaseResponse itemDelList(@RequestBody String[] itemIds, HttpServletRequest request) throws IOException {
        Map map = new HashMap();
        map.put("ids",itemIds);
        return SoaConnectionFactory.post(request, ConstantsUri.CMS_MODEL_ITEM_DEL_LIST, map, BaseResponse.class);
    }

    private List<String> getFieldList(List<ModelItemBo> items) {
        List<String> list = new ArrayList<String>(items.size());
        for (ModelItemBo item : items) {
            list.add(item.getField());
        }
        return list;
    }
}
