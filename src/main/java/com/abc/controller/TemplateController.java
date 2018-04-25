package com.abc.controller;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.ReadFromFile;
import com.abc.dto.cms.FileListDto;
import com.abc.dto.cms.content.ContentDto;
import com.abc.dto.cms.site.SiteManageListDto;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.service.FileTpl;
import com.abc.service.FileTplManagerImpl;
import com.abc.service.Tpl;
import com.abc.service.TplManager;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.system.BasePaginationCriteria;
import com.abc.soa.response.cms.bo.ModelListBO;
import com.abc.soa.response.cms.tpl.TplListResponse;
import com.abc.soa.response.cms.tpl.TplResponse;
import com.abc.soa.response.system.bo.DictBO;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017-06-02.
 */
@Controller
@RequestMapping(value = "/cms/template")
public class TemplateController extends BaseController{


    private static final Logger _log = LoggerFactory
            .getLogger(TemplateController.class);

    @Autowired
    private TplManager tplManager;


    //    @RequiresPermissions("template:template_main")
//    @RequestMapping("/template/template_main.php")
//    public String templateMain(ModelMap model) {
//        return "cms/tpl/template_main";
//    }

    //    @RequiresPermissions("template:v_left")
//    @RequestMapping("/template/v_left.php")
//    public String left(String path, HttpServletRequest request, ModelMap model) {
//        return "cms/tpl/left";
//    }

    //    @RequiresPermissions("template:v_tree")
    @RequestMapping(value = "/tree.php", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> tree(HttpServletRequest request,
                       HttpServletResponse response) {

        String path = request.getParameter("path");

        path = FileTplManagerImpl.standardizePath(path);
        String root = FileTplManagerImpl.standardizePath("");

        ArrayList<FileTpl> tplList = null;
        Map<String, Object> returnMap = new HashMap<String,Object>();
        try {
//            tplList = (ArrayList<FileTpl>)tplManager.getChild(path);
            tplList = (ArrayList<FileTpl>)tplManager.getAllChildList(path, root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }

        returnMap.put("rootPath",path);
        returnMap.put("tplList",tplList);
//        response.setHeader("Cache-Control", "no-cache");
//        response.setContentType("text/json;charset=UTF-8");
        return returnMap;
    }

    /**
     * 获取指定类型的模板，返回树
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/specifiedType_tree.php", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSpecifiedTypetree(String type, HttpServletRequest request,
                            HttpServletResponse response) {

        String path = request.getParameter("path");
        if(!StringUtils.isEmpty(type)) {
            type = "_" + type;
        }
        path = FileTplManagerImpl.standardizePath(path);


        ArrayList<FileTpl> tplList = null;
        Map<String, Object> returnMap = new HashMap<String,Object>();
        try {
//            tplList = (ArrayList<FileTpl>)tplManager.getChild(path);
            tplList = (ArrayList<FileTpl>)tplManager.getAllChildList_SpeciType(path,type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }
//        response.setHeader("Cache-Control", "no-cache");
//        response.setContentType("text/json;charset=UTF-8");
        returnMap.put("rootPath",path);
        returnMap.put("tplList",tplList);

        return returnMap;
    }


    /**
     * 获取指定类型的模板，返回下拉菜单
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/specifiedType_select.php", method = RequestMethod.GET)
    public @ResponseBody ArrayList<FileTpl> getSpecifiedTypeSelect(String type, HttpServletRequest request,
                                                                 HttpServletResponse response) {

        String path = request.getParameter("path");
        type="_"+type;

        path = FileTplManagerImpl.standardizePath(path);

        ArrayList<FileTpl> tplList = null;
        Map<String, List<? extends Tpl>> fileListMap = null;
        try {
//            tplList = (ArrayList<FileTpl>)tplManager.getChild(path);
            tplList = (ArrayList<FileTpl>)tplManager.getAllChildList_SpeciType_excludeDir(path,type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }
//        response.setHeader("Cache-Control", "no-cache");
//        response.setContentType("text/json;charset=UTF-8");
        return tplList;
    }


    // 直接调用方法需要把root参数保存至model中
//    @RequiresPermissions("template:v_list")
    @RequestMapping(value = "/list.php", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap model) {
//        String rel = "";
//        if (rel.length() == 0) {
//            rel = "/";
//        }

        model.addAttribute("root", FileTplManagerImpl.standardizePath(""));
//        model.addAttribute("rel", rel);

        model.addAttribute("parentPath", FileTplManagerImpl.standardizePath(""));
        model.addAttribute("parentSiteId", "");
        try {
            model.addAttribute("filelist", (ArrayList<FileTpl>) tplManager.getChild(FileTplManagerImpl.standardizePath(""),FileTplManagerImpl.standardizePath("")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }
        return "cms/tpl/template_main";
    }

    @RequestMapping(value = "/update_list.php", method = RequestMethod.GET)
    public String updatelist(HttpServletRequest request, ModelMap model) {
        String path = request.getParameter("path");

        path = FileTplManagerImpl.standardizePath(path);

        String root = FileTplManagerImpl.standardizePath("");



        model.addAttribute("parentPath", path);
        try {
            model.addAttribute("filelist", (ArrayList<FileTpl>) tplManager.getChild(path, root));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }
        return "cms/tpl/list";
    }

/*
//    @RequiresPermissions("template:o_create_dir")
    @RequestMapping(value = "/template/o_create_dir.do")
    public String createDir(String root, String dirName,
                            HttpServletRequest request, ModelMap model) {
        // TODO 检查dirName是否存在
        tplManager.save(root + "/" + dirName, null, true);
        model.addAttribute("root", root);
        return list(request, model);
    }

//    @RequiresPermissions("template:v_add")
    @RequestMapping(value = "/template/v_add.do", method = RequestMethod.GET)
    public String add(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        String root = RequestUtils.getQueryParam(request, "root");
        WebErrors errors = validateAdd(root, site.getTplPath(), request);
        if (errors.hasErrors()) {
            return errors.showErrorPage(model);
        }
        String style = handerStyle(RequestUtils.getQueryParam(request, "style"));
        model.addAttribute("directives", directiveTplMng.getList(Integer.MAX_VALUE));
        model.addAttribute("root", root);
        return "template/add_" + style;
    }
*/

//    @RequiresPermissions("template:v_edit")
    @RequestMapping("/edit.php")
    public ModelAndView edit(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cms/tpl/edit");
        String filePathName = request.getParameter("filePathName");
        String filePathNameWithRoot =  FileTplManagerImpl.standardizePath("")+"/"+filePathName;


//        String encoding="UTF-8";
        String fullText = null;
        try {
            File file = ResourceUtils.getFile(filePathNameWithRoot);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                fullText = ReadFromFile.readFileByChars(file.getPath()).toString();
            }else{
                System.out.println("找不到指定的文件");
                _log.error("找不到指定的模板文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }
        mav.addObject("filePathName",filePathName);
        mav.addObject("fullText",fullText);
        mav.addObject("root", FileTplManagerImpl.standardizePath(""));
        return mav;
    }


    /**
     * 获取指定文件内容
     * @param request
     * @return
     */
    @RequestMapping("/getFileContent.php")
    public ModelAndView getFileContent(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        String absoluteFilePathName = request.getParameter("filePathName");


//        String encoding="UTF-8";
        String fullText = null;
        try {
            File file = ResourceUtils.getFile(absoluteFilePathName);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                fullText = ReadFromFile.readFileByChars(file.getPath()).toString();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }
        mav.addObject("absoluteFilePathName", absoluteFilePathName);
        mav.addObject("fullText", fullText);
        return mav;
    }


    /**
     * 上传模板文件
      * @param root
     * @param filename
     * @param source
     * @param request
     * @param model
     * @return
     */
//    @RequiresPermissions("template:o_save")
    @RequestMapping("/save.php")
    public @ResponseBody String save(String root, String filename, String source,
                       HttpServletRequest request, ModelMap model) {

        String parentPath = request.getParameter("parentPath");

        parentPath = FileTplManagerImpl.standardizePath(parentPath);

        InputStream stream = null;
        OutputStream bos = null;
        try{
        // 设置临时文件存储位置
//        String base = "d:/uploadFiles";
        File uploadDir = null;
        uploadDir =  ResourceUtils.getFile(parentPath);

        if(uploadDir==null || !uploadDir.exists()){
            uploadDir.mkdirs();
        }
//        File uploadDir  = new File(base);
//        if(!uploadDir .exists())
//            uploadDir.mkdirs();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file =  multipartRequest.getFile("Filedata");//这里是表单的名字，在swfupload.js中this.ensureDefault("file_post_name", "filedata");


        stream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        fileName = new String(fileName.getBytes(),"utf-8");
        String fileNameFull = uploadDir + "/" + fileName;
        bos = new FileOutputStream(fileNameFull);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.close();
        // close the stream
        stream.close();
        }catch(Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
            return "Failed";
        }finally{
            try {
                if (bos != null) {
                    bos.close();
                }
                if (stream != null) {
                    stream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
                _log.error(e.getMessage());
            }
        }
        return "Success";
    }


    /**
     * update模板文件
     * @param name
     * @param source
     * @param request
     * @param response
     * @param model
     * @return
     */

    // AJAX请求，不返回页面
//    @RequiresPermissions("template:o_ajaxUpdate")
    @RequestMapping("/ajaxUpdate.php")
    @ResponseBody
    public BaseResponse ajaxUpdate(String tplContent, String name, String source,
                           HttpServletRequest request, HttpServletResponse response,
                           ModelMap model) {
        String filePathName = request.getParameter("filePathName");
        String filePathNameWithRoot = FileTplManagerImpl.addRootToPath(filePathName);



//        String fullText = null;
        try {
            File file = ResourceUtils.getFile(filePathNameWithRoot);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                ReadFromFile.writeText(file, tplContent, "UTF-8");
            }else{
                return new BaseResponse("4000","找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4001","修改模板文件失败");
        }

        return new BaseResponse("2000","修改模板文件成功");
    }

    /**
     * update模板文件，并记录版本
     * @param name
     * @param source
     * @param request
     * @param response
     * @param model
     * @return
     */

    // AJAX请求，不返回页面，如果文件有改动，则进行备份，生成新版本号
    @RequestMapping("/ajaxUpdate_recordVersion.php")
    public @ResponseBody BaseResponse ajaxUpdate_recordVersion(String tplContent, String name, String source,
                                           HttpServletRequest request, HttpServletResponse response,
                                           ModelMap model) {
        String filePathName = request.getParameter("filePathName");
        String filePathNameWithRoot = FileTplManagerImpl.addRootToPath(filePathName);     //filePathNameWithRoot： 模板文件根路径 TEMPLATE_ROOT_PATH ~ 文件名称，例如为：templates/t/cms_sys_defined/content/abcd1234_news.ftl


        String filePath = filePathNameWithRoot.substring(0, filePathNameWithRoot.lastIndexOf("/"));
        String fileName = filePathNameWithRoot.substring(filePathNameWithRoot.lastIndexOf("/") + 1);



//        String fullText = null;
        try {
            File file = ResourceUtils.getFile(filePathNameWithRoot);

            if(file.isFile() && file.exists()){ //判断文件是否存在
                ReadFromFile.writeText(file, tplContent, "UTF-8");
            }else{
                return new BaseResponse("4000","找不到指定的文件");
            }
//            String filePathName = filePathNameWithRoot.replace(FileTplManagerImpl.standardizePath(""),"");  //文件路径，不包括模板文件根路径，例如为： /content/abcd1234_news.ftl
            File newestBackTplFile = tplManager.getNewestBakeTplFile(filePathName);
            String newestBackTplFile_FullText = "";
            if(newestBackTplFile!=null) {
                newestBackTplFile_FullText = ReadFromFile.readFileByChars(newestBackTplFile.getPath()).toString();
            }
//            tplManager.backupFile(file,filePathNameWithRoot);
            //清除前后文空格后，比较页面传来的模板内容 与 从最近备份文件里取到的模板内容 是否相同，不相同则进行备份。
            if(!tplContent.trim().equals(newestBackTplFile_FullText.trim())){
                tplManager.save_createVersionFile(filePathName, tplContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4001","备份模板文件失败");
        }
        return new BaseResponse("2000","备份模板文件成功");
    }


    /**
     * 删除模板文件
     * @param request
     * @return
     */
    @RequestMapping("/del.php")
    @ResponseBody
    public BaseResponse del(HttpServletRequest request, String templateId) {
        String filePathName = request.getParameter("filePathName");


//        String encoding="UTF-8";
        try {
            File file = ResourceUtils.getFile(FileTplManagerImpl.addRootToPath(filePathName));
//            String filePathName = filePathNameWithRoot.replace(FileTplManagerImpl.standardizePath(""), "");
            //删除前，备份一个版本
//            tplManager.backupFile(file,filePathName);

            if(file.isFile() && file.exists()){ //判断文件是否存在
                file.delete();
            }else{
                return new BaseResponse("4000","找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4001","删除文件失败");
        }
        BaseResponse delResp = SoaConnectionFactory.delete(request, ConstantsUri.CMS_TEMPLATE_DEL, null, BaseResponse.class, templateId);
        if(!delResp.isSuccess()){
            return new BaseResponse("4002","删除文件成功，删除文件信息失败");
        }
        return new BaseResponse("2000","删除文件成功");
    }

    @RequestMapping("/getTags.php")
    public @ResponseBody Map<String, Object> getTags(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<String,Object>();
        String path = BaseObject.getConfig("TAG_ROOT_PATH");

        ArrayList<FileTpl> tagList = null;

        try {
            tagList = (ArrayList<FileTpl>)tplManager.getAllChildList(path, path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            _log.error(e.getMessage());
        }

        returnMap.put("rootPath",path);
        returnMap.put("tagList", tagList);
        return returnMap;
    }






    /**
     * 上传单个模板文件页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAddTplFile.php", method = RequestMethod.GET)
    public String toAddTplFile(HttpServletRequest request, ModelMap model, String siteId) {
        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        model.addAttribute("cmsSiteList", siteManageListDto.getDataList());

        ModelListBO models = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL, new BasePaginationCriteria(0,0), ModelListBO.class);
        model.addAttribute("models",models.getDataList());

        List<DictBO> tplTypeList = this.getDictBOList(request, "tpl_page_type");
        model.addAttribute("tplType",tplTypeList);

        model.addAttribute("siteId",siteId);
        return "cms/tpl/toAddFile";
    }

    /**
     * 上传单个模板文件
     * @param request
     * @return
     */
//    @PostMapping("/")
    @RequestMapping(value = "/addTplFile.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addTplFile(MultipartHttpServletRequest request,TemplateBo tplBo){
//        String parentPath = request.getParameter("parentPath");

        String parentPath = FileTplManagerImpl.standardizePath(tplBo.getParentPath());

        InputStream stream = null;
        OutputStream bos = null;
        try{
            File uploadDir = null;
            uploadDir =  ResourceUtils.getFile(FileTplManagerImpl.addRootToPath(parentPath));

            if(uploadDir==null || !uploadDir.exists()){
                uploadDir.mkdirs();
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file =  multipartRequest.getFile("tplFile");//这里是表单的名字，在swfupload.js中this.ensureDefault("file_post_name", "filedata");


            stream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            fileName = new String(fileName.getBytes(),"utf-8");
            String fileNameFull = uploadDir + "/" + fileName;
            bos = new FileOutputStream(fileNameFull);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.close();
            // close the stream
            stream.close();
        }catch(Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4000","添加文件失败");
        }finally{
            try {
                if (bos != null) {
                    bos.close();
                }
                if (stream != null) {
                    stream.close();
                }
            }catch(Exception e){
                _log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        tplBo.setTemplatePath(parentPath+"/"+ request.getFile("tplFile").getOriginalFilename());
        tplBo.setIsFolder(0);
        tplBo.setState(1);
        BaseResponse addResp = SoaConnectionFactory.post(request, ConstantsUri.CMS_TEMPLATE_ADD, tplBo, BaseResponse.class);
        if(!addResp.isSuccess()){
            return new BaseResponse("4002","添加文件成功，记录文件信息失败");
        }
        return new BaseResponse("4000","添加文件成功");

    }

    /**建文件夹页面*/
    @RequestMapping(value = "/toAddTplDir.php", method = RequestMethod.GET)
    public String toAddTplDir(HttpServletRequest request, ModelMap model) {
        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        model.addAttribute("cmsSiteList", siteManageListDto.getDataList());

        List<DictBO> tplTypeList = this.getDictBOList(request, "tpl_page_type");
        model.addAttribute("tplType",tplTypeList);

        return "cms/tpl/toAddDir";
    }

    /**
     *新建文件夹
     */
    @PostMapping("/addTplDir.php")
    @ResponseBody
    public BaseResponse addTplDir(HttpServletRequest request,String parentPath, String tplDirName, String siteId, String tplDirChineseName){
        String parentPathWithRoot = FileTplManagerImpl.addRootToPath(parentPath);
//        parentPath = FileTplManagerImpl.standardizePath(parentPath);

        try{

            File uploadDir = ResourceUtils.getFile(parentPathWithRoot);

            File newDir = new File(uploadDir.getPath() +"/"+ tplDirName);
            if(newDir == null || !newDir.exists()){
                newDir.mkdirs();
            }

        }catch(Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4000","新建"+ tplDirName +"文件夹失败");
        }
        TemplateBo tplBo = new TemplateBo();
        tplBo.setTemplateName(tplDirChineseName);
        tplBo.setParentPath(parentPath);
        tplBo.setTemplatePath("/".equals(parentPath)?tplDirName:(parentPath+"/"+ tplDirName));
        tplBo.setIsFolder(1);
        tplBo.setState(1);
        tplBo.setSiteId(siteId);
        BaseResponse addResp = SoaConnectionFactory.post(request, ConstantsUri.CMS_TEMPLATE_ADD, tplBo, BaseResponse.class);
        if(!addResp.isSuccess()){
            return new BaseResponse("4002","添加文件成功，记录文件信息失败");
        }

        return new BaseResponse("2000","新建"+ tplDirName +"文件夹成功");

    }

    /**
     * 删除模板文件夹
     * @param request
     * @return
     */
    @RequestMapping("/delDir.php")
    @ResponseBody
    public BaseResponse delDir(HttpServletRequest request, String dirPathName, String templateId) {

        try{

            File delDir = ResourceUtils.getFile(FileTplManagerImpl.addRootToPath(dirPathName));

            if (delDir.isDirectory()) {
                String[] files = delDir.list();
                if (files.length > 0) {
                    return new BaseResponse("4001","目录不为空！");
                }
            }

            if(delDir != null && delDir.exists()){
                delDir.delete();
            }

        }catch(Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
            return new BaseResponse("4000","删除"+ dirPathName +"文件夹失败");
        }

        BaseResponse delResp = SoaConnectionFactory.delete(request, ConstantsUri.CMS_TEMPLATE_DEL, null, BaseResponse.class, templateId);
        if(!delResp.isSuccess()){
            return new BaseResponse("4002","删除文件夹成功，删除文件夹信息失败");
        }
        return new BaseResponse("2000","删除"+ dirPathName +"文件夹成功");
    }


    /**修改模板文件属性页面*/
    @RequestMapping(value = "/toEditTpl.php", method = RequestMethod.GET)
    public String toEditTpl(HttpServletRequest request, ModelMap model, String templateId) {
        SiteManageListDto siteManageListDto = SoaConnectionFactory.get(request, ConstantsUri.CMS_SITE, new BasePaginationCriteria(0,0), SiteManageListDto.class);
        model.addAttribute("cmsSiteList", siteManageListDto.getDataList());

        ModelListBO models = SoaConnectionFactory.get(request, ConstantsUri.CMS_MODEL, new BasePaginationCriteria(0,0), ModelListBO.class);
        model.addAttribute("models",models.getDataList());

        List<DictBO> tplTypeList = this.getDictBOList(request, "tpl_page_type");
        model.addAttribute("tplType",tplTypeList);

        TplResponse tplResponse = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_TEMPLATE_VIEW, null, TplResponse.class, templateId);

        if(tplResponse!=null && tplResponse.getData()!=null) {
            model.addAttribute("tplBo", tplResponse.getData());
        }

        return "cms/tpl/toEditFileAttri";
    }

    /**
     * 根据数据库模板数据生成tree
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/treeFromDb.php", method = RequestMethod.GET)
    public @ResponseBody
    String treeFromDb(HttpServletRequest request,
                             HttpServletResponse response) {

        String path = request.getParameter("id");
        if(path==null || "".equals(path)){
            path = "0";
        }

        path = FileTplManagerImpl.standardizePath(path);
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("parentPath", path);
        TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);;

        JSONArray jsonArray=new JSONArray();
        for(TemplateBo tpl:tplList.getDataList())
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",tpl.getTemplatePath());
            jsonObject.put("pid",tpl.getParentPath());
            jsonObject.put("name",tpl.getTemplateName());
            jsonObject.put("siteId",tpl.getSiteId());

            //判断所选择节点是否是父节点，如果是设置isParent属性为true,不是设置为false
            jsonObject.put("isParent",tpl.getIsFolder()==1?"true":"false");

            //目录，要显示目录中文名和英文名
            if(tpl.getIsFolder()==1){
                String name_en = tpl.getTemplatePath().substring(tpl.getTemplatePath().lastIndexOf("/") + 1);
                if("".equals(name_en)){
                    jsonObject.put("name",tpl.getTemplateName()+"(/)");
                }else {
                    jsonObject.put("name", tpl.getTemplateName() + "(" + name_en + ")");
                }
            }

            jsonObject.put("open","false");
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }

    /**
     * 根据数据库模板数据生成每个目录下的列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/tplListFromDb.php", method = RequestMethod.GET)
    public String tplListFromDb(HttpServletRequest request,
                      HttpServletResponse response, ModelMap model) {

        String path = request.getParameter("id");

        path = FileTplManagerImpl.standardizePath(path);
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("parentPath",path);
        TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);;

        model.addAttribute("filelist", tplList.getDataList());
        model.addAttribute("root", FileTplManagerImpl.standardizePath(""));
        model.addAttribute("parentPath", "/");
        return "cms/tpl/template_main";
    }

    /**
     * 根据数据库数据刷新模板列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/update_list_formDb.php", method = RequestMethod.GET)
    public String updatelistFromDb(HttpServletRequest request, ModelMap model, String siteId) {
        String path = request.getParameter("path");

        path = FileTplManagerImpl.standardizePath(path);

        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("parentPath",path);


        TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);;
        model.addAttribute("filelist", tplList.getDataList());
        model.addAttribute("parentPath",path);
        model.addAttribute("root",FileTplManagerImpl.standardizePath(""));

        model.addAttribute("parentSiteId",siteId);

        return "cms/tpl/list";
    }


    /**
     *修改模板属性
     */
    @PostMapping("/uploadTplAttri.php")
    @ResponseBody
    public BaseResponse uploadTplAttri(HttpServletRequest request,TemplateBo tplBo){

        TemplateBo tplBoDb = null;
        TplResponse tplResponse = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_TEMPLATE_VIEW, null, TplResponse.class, tplBo.getTemplateId());

        if(tplResponse.isSuccess() && tplResponse.getData()!=null) {
            tplBoDb = tplResponse.getData();
        }else{
            return new BaseResponse("4002","修改文件信息失败");
        }

        tplBoDb.setTemplateName(tplBo.getTemplateName());
//        tplBoDb.setSiteId(tplBo.getSiteId());
        tplBoDb.setTemplateProperty(tplBo.getTemplateProperty());
        tplBoDb.setPriority(tplBo.getPriority());
        tplBoDb.setTemplateType(tplBo.getTemplateType());
        BaseResponse addResp = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_TEMPLATE_MOD, tplBoDb, BaseResponse.class, tplBo.getTemplateId());

        if(!addResp.isSuccess()){
            return new BaseResponse("4002","修改文件信息失败");
        }

        return new BaseResponse("2000","修改文件信息成功");

    }


    /**
     *启停模板
     */
    @PostMapping("/start_stop_Tpl.php")
    @ResponseBody
    public BaseResponse start_stop_Tpl(HttpServletRequest request,String templateId){

        TemplateBo tplBoDb = null;
        TplResponse tplResponse = SoaConnectionFactory.getRestful(request, ConstantsUri.CMS_TEMPLATE_VIEW, null, TplResponse.class, templateId);

        if(tplResponse.isSuccess() && tplResponse.getData()!=null) {
            tplBoDb = tplResponse.getData();
        }else{
            return new BaseResponse("4002","切换模板文件有效状态失败");
        }

        tplBoDb.setState(tplBoDb.getState() == 0 ? 1 : 0);

        BaseResponse addResp = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_TEMPLATE_MOD, tplBoDb, BaseResponse.class, templateId);

        if(!addResp.isSuccess()){
            return new BaseResponse("4002","切换模板文件有效状态失败");
        }

        return new BaseResponse("2000","切换模板文件有效状态成功");

    }

}