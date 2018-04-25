package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.cms.CodePaginationCriteria;
import com.abc.soa.request.system.CodeCriteria;
import com.abc.soa.response.system.Dict;
import com.abc.soa.response.system.bo.DictBO;
import com.abc.soa.response.system.bo.DictDetailBo;
import com.abc.soa.response.system.bo.DictListBO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuhao on 2017-05-24.
 * 数据字典管理1
 */
@Controller
@RequestMapping(value = "/system/code")
public class CodeController extends BaseController {

    /**
     * 跳转至数据字典管理列表
     *
     * @param request
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/list.php",method = RequestMethod.GET)
    public ModelAndView list( CodePaginationCriteria codeBasePaginationCriteria, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("system/code/list");
        CodeCriteria codeCriteria=new CodeCriteria();
        String status="";
        if (codeBasePaginationCriteria.getStatus() != null) {
            if (codeBasePaginationCriteria.getStatus().equals("true")) {
                status="true";
                codeCriteria.setStatus(true);
            } else if (codeBasePaginationCriteria.getStatus().equals("false")) {
                status="false";
                codeCriteria.setStatus(false);
            }
        }
        codeCriteria.setDictName(codeBasePaginationCriteria.getDictName());
        codeCriteria.setPage(codeBasePaginationCriteria.getPage());
        codeCriteria.setSize(codeBasePaginationCriteria.getSize());
        DictListBO dicts = SoaConnectionFactory.get(request, ConstantsUri.SYS_CODE, codeCriteria, DictListBO.class);
        DictListBO dictst = SoaConnectionFactory.get(request, ConstantsUri.SYS_CODE_NAME, null, DictListBO.class);
        codeBasePaginationCriteria.setTotalItems((long)dicts.getTotal());
        codeBasePaginationCriteria.setTotalPage((int) Math.ceil((double) codeBasePaginationCriteria.getTotalItems() / (double) codeBasePaginationCriteria.getSize()));
        mav.addObject("pagination",codeBasePaginationCriteria);
        mav.addObject("dicts", dicts);
        mav.addObject("total", dicts.getTotal());
        mav.addObject("status", status);
        mav.addObject("dictName", codeBasePaginationCriteria.getDictName());
        mav.addObject("dictst",dictst);
        return mav;
    }

    /**
     * 跳转至数据字典管理新增页面
     *
     * @param request
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/add.php", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("system/code/form_add");
        return mav;
    }

    /**
     * 跳转至数据字典管理修改页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("system/code/form_edit");
        DictDetailBo result = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_ONE, null, DictDetailBo.class, id);
        mav.addObject("dict", result.getData());
        return mav;
    }

    /**
     * 数据字典管理保存操作
     *
     * @param request
     * @param dictBO
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/save.php",method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, @RequestBody DictBO dictBO) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        Dict result = null;
        if (CommonUtils.nullOrBlank(dictBO.getId())) {
            result = SoaConnectionFactory.post(request, ConstantsUri.SYS_CODE, dictBO, Dict.class);
        } else {
            result = SoaConnectionFactory.put(request, ConstantsUri.SYS_CODE_ONE, dictBO, Dict.class, dictBO.getId());
        }
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 数据字典管理删除操作
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView del(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.SYS_CODE_ONE, null, BaseResponse.class, id);
        System.out.print(result);
        //return "redirect:/system/code/list.php";
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 数据字典管理启用和禁用
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ModelAndView updateStatus(@PathVariable(value = "id") String id,@RequestParam(value = "status") String status, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        DictDetailBo dictBO = SoaConnectionFactory.getRestful(request, ConstantsUri.SYS_CODE_ONE, null, DictDetailBo.class, id);
        dictBO.getData().setStatus(Boolean.valueOf(status));
        BaseResponse result = null;
        result = SoaConnectionFactory.put(request, ConstantsUri.SYS_CODE_ONE, dictBO.getData(), BaseResponse.class, dictBO.getData().getId());
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 数据字典管理批量删除操作
     *
     * @param ids 多个id编号用","隔开
     * @param request
     * @return
     */
    @RequiresPermissions("system:code")
    @RequestMapping(value = "/batchDel.php", method = RequestMethod.POST)
    public ModelAndView batchDel(@RequestParam(value = "ids", required = false) String ids, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=null;
        if(ids!=null&&!"".equals(ids)){
            String []id=ids.split(",");
            if(id.length>0){
                for(String idcode : id){
                    if(idcode!=null&&!"".equals(idcode))
                        result=SoaConnectionFactory.delete(request, ConstantsUri.SYS_CODE_ONE, null, BaseResponse.class, idcode);
                }
            }
        }
        mav.addObject("result", result);
        return mav;
    }

}
