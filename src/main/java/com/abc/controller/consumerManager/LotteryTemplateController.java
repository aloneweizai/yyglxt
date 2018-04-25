package com.abc.controller.consumerManager;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.Constant;
import com.abc.dto.cms.content.ContentWrapperDto;
import com.abc.dto.cms.knowledge.KnowledgeTagList;
import com.abc.dto.cms.site.ContenttagidBo;
import com.abc.dto.cms.site.SiteManageQueryWrapper;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.service.FileTplManagerImpl;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.consumer.LotteryActivityprizeRq;
import com.abc.soa.request.consumer.LotteryLogRq;
import com.abc.soa.response.cms.knowledge.KnowledgeTag;
import com.abc.soa.response.cms.tpl.TplListResponse;
import com.abc.soa.response.consumer.LotteryActivityRs;
import com.abc.soa.response.consumer.LotteryActivityprizeRs;
import com.abc.soa.response.consumer.LotteryLogRs;
import com.abc.soa.response.consumer.bo.LotteryActivityBO;
import com.abc.soa.response.consumer.bo.LotteryActivityprizeBO;
import com.abc.soa.response.consumer.bo.LotteryTemplateExRs;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.soa.request.consumer.LotteryTemplateRq;
import com.abc.soa.response.consumer.LotteryTemplateRs;
import com.abc.soa.response.consumer.bo.LotteryTemplateBO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by relic5 on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/consumerManager/lottery")
public class LotteryTemplateController {

    protected static Logger log = LoggerFactory.getLogger(LotteryTemplateController.class);

    /**
     * 抽奖活动模板
     * @param request
     * @return
     */
    public static TplListResponse getTemplates(HttpServletRequest request,LotteryTemplateRq lotteryTemplateRq) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("parentPath", "csw/template/lottery");
        if(lotteryTemplateRq != null) {
            String templateName = lotteryTemplateRq.getName();
            if (templateName != null && !templateName.isEmpty()) {
                queryMap.put("templateName", templateName);
            }
        }
        try{
            TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);
            return tplList;

        }catch (Exception e){
            return null;
        }

    }

    /**
     * 模板列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/lotteryTemplate.php")
    public String lotteryTemplateList(LotteryTemplateRq lotteryTemplateRq, HttpServletRequest request, Model model) {
        TplListResponse tplList = LotteryTemplateController.getTemplates(request,lotteryTemplateRq);
        model.addAttribute("listRs", tplList.getDataList());
        model.addAttribute("pagination", lotteryTemplateRq);
        return "consumer/lottery/LotteryTemplate_list";
    }

    @RequestMapping("/lotteryTemplateEdit.php")
    public String lotteryTemplateEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            LotteryTemplateRs lotteryTemplateRs = SoaConnectionFactory.getRestful(request, ConstantsUri.LOTTERYTEMPLATE_INFO, null, LotteryTemplateRs.class, id);

            model.addAttribute("obj", lotteryTemplateRs.getData());

        }
        return "consumer/lottery/LotteryTemplate_edit";
    }

    @PostMapping("/lotteryTemplateSave.php")
    public @ResponseBody
    BaseResponse lotteryTemplateSave(@RequestBody LotteryTemplateBO lotteryTemplateBO, HttpServletRequest request) {
        String id = lotteryTemplateBO.getId();
        BaseResponse returnObj = null;
        if (id == null || id.isEmpty()) {
            //新增
            returnObj = SoaConnectionFactory.post(request, ConstantsUri.LOTTERYTEMPLATE_LIST, lotteryTemplateBO, LotteryTemplateRs.class);

        } else {
            returnObj = SoaConnectionFactory.put(request, ConstantsUri.LOTTERYTEMPLATE_INFO, lotteryTemplateBO, LotteryTemplateRs.class, id);

        }

        return returnObj;

    }

    /**
     * 删除
     */
    @RequestMapping("/lotteryTemplateDel.php")
    public
    @ResponseBody
    BaseResponse delVipPrivilege(@RequestParam(required = true) String id, HttpServletRequest request) {
        return SoaConnectionFactory.delete(request, ConstantsUri.LOTTERYTEMPLATE_INFO, null, BaseResponse.class, id);
    }

    /**
     * 生成内容预览页
     *
     * @param request
     * @param response
     */
    @GetMapping("/lotteryTemplateDo.php")
    @ResponseBody
    public void renderContent(HttpServletRequest request, HttpServletResponse response, String id) {
        Map<String, Object> root = new HashMap<>();
        LotteryTemplateExRs tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_VIEW, null, LotteryTemplateExRs.class, id);
        String uri = BaseObject.getConfig("cswdomain") + "/template/lottery";
        root.put("uri", uri);

        TemplateBo templateBo = tplList.getData();
        //通过 模版 名字 去查找 对应的活动

        LotteryActivityBO lotteryActivityBO = null;
        LotteryActivityRs lotteryActivityRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITY_LIST, null, LotteryActivityRs.class);
        for (LotteryActivityBO obj : lotteryActivityRs.getDataList()) {
            if (id.equals(obj.getTemplateId())) {
                lotteryActivityBO = obj;
                root.put("lotteryActivityBO",lotteryActivityBO);
                break;
            }
        }
        LotteryActivityprizeRq lotteryActivityprizeRq = new LotteryActivityprizeRq();
        lotteryActivityprizeRq.setActivityId(lotteryActivityBO.getId());
        LotteryActivityprizeRs lotteryActivityprizeRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYACTIVITYPRIZE_LIST, lotteryActivityprizeRq, LotteryActivityprizeRs.class);
        //这里获得 奖品
        List<LotteryActivityprizeBO> list = lotteryActivityprizeRs.getDataList();
    int tmpi = 0;
        int size = list.size();
        for (LotteryActivityprizeBO obj : list) {
            String img = obj.getLotteryImage();
            if(img != null && !img.isEmpty()){
                obj.setLotteryImage(BaseObject.getConfig("picdomain") + img);
            }

        }
        //加个没中奖
        LotteryActivityprizeBO lotteryActivityprizeBO = new LotteryActivityprizeBO();
        lotteryActivityprizeBO.setLotteryName("谢谢参与");
        lotteryActivityprizeBO.setLotteryImage(uri + "/images/3.png");
        lotteryActivityprizeBO.setId("noluck");
        list.add(lotteryActivityprizeBO);
        size++;
        for (int i = size; i < 12; i++) {
            list.add(list.get(tmpi));
            tmpi++;
            if (tmpi >= size) {
                tmpi = 0;
            }
        }
        root.put("listRs", list);

        LotteryLogRq lotteryLogRq = new LotteryLogRq();
        lotteryLogRq.setIsluck(1);
        LotteryLogRs lotteryLogRs = SoaConnectionFactory.get(request, ConstantsUri.LOTTERYLOG_LIST, lotteryLogRq, LotteryLogRs.class);
        root.put("logRs",lotteryLogRs.getDataList());

        templateDo(request, response, templateBo, root);
    }

    public void templateDo(HttpServletRequest request, HttpServletResponse response, TemplateBo templateBo, Map root) {
        root.put("snsUrl", SpringCtxHolder.getProperty("snsdomain"));
        root.put("picUrl", SpringCtxHolder.getProperty("picdomain"));
        root.put("cswUrl", SpringCtxHolder.getProperty("cswdomain"));
        String ucUrl = SpringCtxHolder.getProperty("ucdomain");
        root.put("ucUrl", ucUrl);

        String uri = ucUrl + "/moban";
        root.put("uri", uri);

        Configuration config = new Configuration(Configuration.VERSION_2_3_24);
        List<String> generatedContentIdArray = new ArrayList<String>();

        String tplPathName = templateBo.getTemplatePath();
        String tplPathNameWithRoot = FileTplManagerImpl.addRootToPath(tplPathName);
        String tplPath = tplPathNameWithRoot.substring(0, tplPathNameWithRoot.lastIndexOf("/"));
        String tplName = tplPathNameWithRoot.substring(tplPathNameWithRoot.lastIndexOf("/") + 1);

        try {
            File file = ResourceUtils.getFile(tplPath);
            config.setDirectoryForTemplateLoading(file);//设置模板路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDefaultEncoding("UTF-8");//编码
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Writer out = null;
        try {
            out = response.getWriter();
            Template temple = config.getTemplate(tplName);
            temple.process(root, out);//处理
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
