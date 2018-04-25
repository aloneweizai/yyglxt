package com.abc.controller.system;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.RedisGlRq;
import com.abc.soa.response.system.RedisGlQueryRs;
import com.abc.soa.response.system.RedisGlRs;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuhao on 2017-05-24.
 * 数据字典管理1
 */
@Controller
@RequestMapping(value = "/system/redis")
public class RedisGlController extends BaseController {


    /**
     * redis管理功能列表查询
     *
     * @param rq
     * @param request
     * @return
     */
    @RequiresPermissions("system:redis")
    @RequestMapping("/list.php")
    public String selectList(RedisGlRq rq, HttpServletRequest request, Model model) {
        //rq.setPattern("*_Dict");
        if(!StringUtils.isEmpty(rq.getPattern())){
            RedisGlRs redisGlRs = SoaConnectionFactory.get(request, ConstantsUri.REDISGL_LIST, rq, RedisGlRs.class);
            model.addAttribute("redisGlRs", redisGlRs.getData());
            rq.setTotalItems(redisGlRs.getTotal());
            rq.setTotalPage((int) Math.ceil((double) rq.getTotalItems() / (double) rq.getSize()));
            rq.calculate();
        }
        model.addAttribute("BaseRq", rq);
        return "system/redisgl/list";
    }

    /**
     * 删除redis键值对
     *
     * @param id
     * @param request
     * @return
     */
    @RequiresPermissions("system:redis")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView delmx(@PathVariable(value = "id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        BaseResponse result=SoaConnectionFactory.delete(request, ConstantsUri.REDISGL_ONE, null, BaseResponse.class, id);
        System.out.print(result);
        mav.addObject("result", result);
        return mav;
    }

    /**
     * 单个redis键值对查询
     * @param
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("system:redis")
    @RequestMapping(value = "/selectone/{id}")
    public ModelAndView userservicecomplist(@PathVariable(value = "id") String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        RedisGlQueryRs redisGlQueryRs = SoaConnectionFactory.getRestful(request, ConstantsUri.REDISGL_ONE, null, RedisGlQueryRs.class, id);
        mav.addObject("redisxx", redisGlQueryRs.getData());
        if(redisGlQueryRs.getData().length()>200){
            redisGlQueryRs.setData(redisGlQueryRs.getData().substring(0, 200) + "...");
        }
        mav.addObject("redisGlQueryRs", redisGlQueryRs.getData());
        return mav;
    }

    /**
     * 清空redis数据库
     * @param
     * @param request
     * @param
     * @return
     */
    @RequiresPermissions("system:redis")
    @RequestMapping("/flushdb.php")
    public
    @ResponseBody
    BaseResponse flushdb(HttpServletRequest request) {
       return SoaConnectionFactory.post(request, ConstantsUri.REDISGL_FLUSHDB, null, RedisGlQueryRs.class);
    }
}
