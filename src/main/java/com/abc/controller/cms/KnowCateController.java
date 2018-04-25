package com.abc.controller.cms;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.dto.cms.knowledge.*;
import com.abc.soa.ConstantsUri;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 知识库Controller
 * Created by Arvin on 2017/8/3 0003.
 */
@Controller
public class KnowCateController extends BaseController {

    /**
     * ajax查询所有分类
     * @param request
     * @return
     */
    @GetMapping("/cms/knowcate/ajaxList")
    @ResponseBody
    public KnowledgeCategoryBOList ajaxCateList(HttpServletRequest request){
        KnowledgeCategoryBOList contentListResponse = SoaConnectionFactory.get(request, ConstantsUri.KNOW_CATE_LIST, null, KnowledgeCategoryBOList.class);
        return contentListResponse;
    }

    /**
     * 进入新增知识分类页面
     * @return
     */
    @GetMapping("/cms/knowcate/add")
    public String toAddCate(HttpServletRequest request,Model model, KnowledgeCategoryBO cate){
        model.addAttribute("cate", cate);
        return "cms/knowledge/addcate";
    }

    /**
     * 新增分类
     * @return
     */
    @PostMapping("/cms/knowcate/save")
    @ResponseBody
    public BaseResponse addCate(HttpServletRequest request, KnowledgeCategoryBO cate){
        if(StringUtils.isEmpty(cate.getId())){
            return SoaConnectionFactory.post(request, ConstantsUri.KNOW_CATE_ADD, cate, BaseResponse.class);
        }else{
            return SoaConnectionFactory.put(request, ConstantsUri.KNOW_CATE_MODIFY, cate, BaseResponse.class);
        }
    }

    /**
     * @param1  name
     * @param1  id
     * 修改分类名称
     */
    @PutMapping(path = "/cms/knowcate/modifyName/{id}")
    @ResponseBody
    public KnowledgeCategoryBOList modifyCateName(HttpServletRequest request, KnowledgeCategoryBO cate){
        Map<String, String> parameter = new HashMap<>();
        parameter.put("name", cate.getName());
        KnowledgeCategoryBOList categoryBO = SoaConnectionFactory.putRestful(request, ConstantsUri.KNOW_CATE_MODIFYNAME, parameter, KnowledgeCategoryBOList.class, cate.getId());
        return categoryBO;
    }

    /**
     * @param  id
     * 根据分类Id 删除分类
     */

    @DeleteMapping(path = "/cms/knowcate/delete/{id}")
    @ResponseBody
    public BaseResponse modifyCateName(HttpServletRequest request,@PathVariable String id){
        return SoaConnectionFactory.deleteRestful(request, ConstantsUri.KNOW_CATE_DELETE, null, BaseResponse.class, id);
    }

    /**
     * 跳转知识分类构建树
     * @return
     */
    @GetMapping("/cms/knowcate/ajaxtree")
    public String ajaxTree(HttpServletRequest request, Model model){
        return "cms/knowledge/catetree";
    }


}
