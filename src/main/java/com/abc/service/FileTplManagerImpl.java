package com.abc.service;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.BaseObject;
import com.abc.common.util.ReadFromFile;
import com.abc.controller.BaseController;
import com.abc.dto.cms.tpl.TemplateBo;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.cms.tpl.TplListResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


@Component
public class FileTplManagerImpl implements TplManager {
    private static Logger log = LoggerFactory
            .getLogger(FileTplManagerImpl.class);

    public int delete(String[] names) {
        File f;
        int count = 0;
        for (String name : names) {
            f = new File(realPathResolver.get(name));
            if (f.isDirectory()) {
                if (FileUtils.deleteQuietly(f)) {
                    count++;
                }
            } else {
                if (f.delete()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Tpl get(String name) {
        File f = new File(realPathResolver.get(name));
        if (f.exists()) {
            return new FileTpl(f, root);
        } else {
            return null;
        }
    }

    public List<Tpl> getListByPrefix(String prefix) {
        File f = new File(realPathResolver.get(prefix));
        String name = prefix.substring(prefix.lastIndexOf("/") + 1);
        File parent;
        if (prefix.endsWith("/")) {
            name = "";
            parent = f;
        } else {
            parent = f.getParentFile();
        }
        if (parent.exists()) {
            File[] files = parent.listFiles(new PrefixFilter(name));
            if (files != null) {
                List<Tpl> list = new ArrayList<Tpl>();
                for (File file : files) {
                    list.add(new FileTpl(file, root));
                }
                return list;
            } else {
                return new ArrayList<Tpl>(0);
            }
        } else {
            return new ArrayList<Tpl>(0);
        }
    }

    public List<String> getNameListByPrefix(String prefix) {
        List<Tpl> list = getListByPrefix(prefix);
        List<String> result = new ArrayList<String>(list.size());
        for (Tpl tpl : list) {
            result.add(tpl.getName());
        }
        return result;
    }

    public List<Tpl> getChild(String path ,String root) throws FileNotFoundException {
//        File file = new File(realPathResolver.get(path));

        File file =  ResourceUtils.getFile(path);
//        this.root = root;
//        realPathResolver.get(path);
        File[] child = file.listFiles();
        if (child != null) {
            List<Tpl> list = new ArrayList<Tpl>(child.length);
            for (File f : child) {
                list.add(new FileTpl(f, root));
            }
            return list;
        } else {
            return new ArrayList<Tpl>(0);
        }
    }

//    @Override
//    public List<Tpl> getChild(String path, String type) throws FileNotFoundException {
//        path = this.standardizePath(path);
//
//        File file =  ResourceUtils.getFile(path);
//        root = ClassLoader.getSystemResource("").getPath().substring(1);
////        realPathResolver.get(path);
//        File[] child = file.listFiles();
//        if (child != null) {
//            List<Tpl> list = new ArrayList<Tpl>();
//            if(StringUtils.isEmpty(type)){
//                for (File f : child) {
//                    if(f.isDirectory()){
//                        list.add(new FileTpl(f, root));
//                    }else if(f.getName()!=null) {
//                        list.add(new FileTpl(f, root));
//                    }
//                }
//            }else {
//                for (File f : child) {
//                    if (f.isDirectory()) {
//                        list.add(new FileTpl(f, root));
//                    } else if (f.getName() != null && f.getName().contains(type)) {
//                        list.add(new FileTpl(f, root));
//                    }
//                }
//            }
//            return list;
//        } else {
//            return new ArrayList<Tpl>(0);
//        }
//    }

    @Override
    public List<Tpl> getAllChildList(String path, String root) throws FileNotFoundException {
        path = this.standardizePath(path);

        List<Tpl> returnFileList = new ArrayList<Tpl>();
        List<Tpl> childFileList = this.getChild(path, root);
        returnFileList.addAll(childFileList);

        //获取子文件夹的下级元素
        for(Tpl tpl: childFileList){
            if(tpl.isDirectory()){
                String childPath = tpl.getName().substring(1);
                List<? extends Tpl> childFileList1 = this.getAllChildList(childPath, root);
                if(childFileList1!=null){
                    returnFileList.addAll(childFileList1);
                }
            }
        }
        return returnFileList;
    }

    @Override
    public List<? extends Tpl> getAllChildList_SpeciType(String path, String type) throws FileNotFoundException {
        path = this.standardizePath(path);

        List<Tpl> returnFileList = new ArrayList<Tpl>();
        List<Tpl> childFileList = this.getChild(path, type);
        returnFileList.addAll(childFileList);

        //获取子文件夹的下级元素
        for(Tpl tpl: childFileList){
            if(tpl.isDirectory()){
                String childPath = tpl.getName().substring(1);
                List<? extends Tpl> childFileList1 = this.getAllChildList_SpeciType(childPath, type);
                if(childFileList1!=null){
                    returnFileList.addAll(childFileList1);
                }
            }
        }
        return returnFileList;
    }

    @Override
    public List<TemplateBo> getAllTpl_Speci_tplProperty_siteId(HttpServletRequest request, String templateProperty, String templateType, String siteId) {
        HashMap<String,String> queryMap = new HashMap<>();
        if(templateProperty==null){
            templateProperty="";
        }
        if(templateType==null){
            templateType="";
        }
        queryMap.put("templateProperty",templateProperty);
        queryMap.put("siteId",siteId);
        queryMap.put("templateType",templateType);
        queryMap.put("isFolder","0");
        queryMap.put("state","1");
        TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);;

        return tplList.getDataList();
    }

    @Override
    public List<? extends Tpl> getAllChildList_SpeciType_excludeDir(String path, String type) throws FileNotFoundException {
        path = this.standardizePath(path);

        List<FileTpl> allChildList = (ArrayList<FileTpl>)getAllChildList_SpeciType(path, type);

        Iterator<FileTpl> it = allChildList.iterator();
        while(it.hasNext()){
            FileTpl x = it.next();
            if(x.isDirectory()){
                it.remove();
            }
        }
        return allChildList;
    }

    public void rename(String orig, String dist) {
        String os = realPathResolver.get(orig);
        File of = new File(os);
        String ds = realPathResolver.get(dist);
        File df = new File(ds);
        try {
            if (of.isDirectory()) {
                FileUtils.moveDirectory(of, df);
            } else {
                FileUtils.moveFile(of, df);
            }
        } catch (IOException e) {
            log.error("Move template error: " + orig + " to " + dist, e);
        }
    }

    public void save(String name, String source, boolean isDirectory) {
        String real = realPathResolver.get(name);
        File f = new File(real);
        if (isDirectory) {
            f.mkdirs();
        } else {
            try {
                FileUtils.writeStringToFile(f, source, "UTF-8");
            } catch (IOException e) {
                log.error("Save template error: " + name, e);
                throw new RuntimeException(e);
            }
        }
    }

    public void save(String path, MultipartFile file) {
        File f = new File(realPathResolver.get(path), file
                .getOriginalFilename());
        try {
            file.transferTo(f);
        } catch (IllegalStateException e) {
            log.error("upload template error!", e);
        } catch (IOException e) {
            log.error("upload template error!", e);
        }
    }

    public void update(String name, String source) {
        String real = realPathResolver.get(name);
        File f = new File(real);
        try {
            FileUtils.writeStringToFile(f, source, "UTF-8");
        } catch (IOException e) {
            log.error("Save template error: " + name, e);
            throw new RuntimeException(e);
        }
    }

    private String root;

    @Autowired
    private RealPathResolver realPathResolver;


    public void setRealPathResolver(RealPathResolver realPathResolver) {
        this.realPathResolver = realPathResolver;
        root = realPathResolver.get("");
    }

    private static class PrefixFilter implements FileFilter {
        private String prefix;

        public PrefixFilter(String prefix) {
            this.prefix = prefix;
        }

        public boolean accept(File file) {
            String name = file.getName();
            return file.isFile() && name.startsWith(prefix);
        }
    }

    /**
     * 对path进行标准化，入参为空时，返回配置文件配置的 TEMPLATE_ROOT_PATH，同样是去掉了最前面的"/"
     * @param path
     * @return
     */
    public static String standardizePath(String path){
        if(path==null || "".equals(path)){
            path = BaseObject.getConfig("TEMPLATE_ROOT_PATH");
        }
//        if(path.startsWith("/")){
//            path = path.substring(1);
//        }
        return path;
    }

    public static String addRootToPath(String relativePath){
        return BaseObject.getConfig("TEMPLATE_ROOT_PATH") +"/"+ relativePath;
    }

    @Override
    public String backupFile(File sourceFile, String filePathName) throws Exception{
        String filePath = filePathName.substring(0, filePathName.lastIndexOf("/"));
        String fileName = filePathName.substring(filePathName.lastIndexOf("/") + 1);

        String lastVersionName = genrateNewVersionName(BaseObject.getConfig("TEMPLATE_BACK_PATH")+"/"+ filePath, fileName);
        File filebake = new File(BaseObject.getConfig("TEMPLATE_BACK_PATH")+"/"+ filePath+"/"+ lastVersionName);
        FileUtils.copyFile(sourceFile, filebake);
        return filebake.getAbsolutePath();
    }

    @Override
    public void save_createVersionFile(String filePathName, String tplContent) throws Exception {
        String filePath = filePathName.substring(0, filePathName.lastIndexOf("/"));
        String fileName = filePathName.substring(filePathName.lastIndexOf("/") + 1);

        String lastVersionName = genrateNewVersionName(BaseObject.getConfig("TEMPLATE_BACK_PATH")+"/" + filePath, fileName);
        File targetFile = new File(BaseObject.getConfig("TEMPLATE_BACK_PATH")+"/"+ filePath+"/"+ lastVersionName);
        if(targetFile.isFile() && targetFile.exists()){ //判断文件是否存在
            ReadFromFile.writeText(targetFile, tplContent, "UTF-8");
        }else{
            File targetDir = new File(BaseObject.getConfig("TEMPLATE_BACK_PATH")+"/"+ filePath);
            if(!targetDir.exists()){
                targetDir.mkdirs();
            }
            targetFile.createNewFile();
            ReadFromFile.writeText(targetFile, tplContent, "UTF-8");
        }

    }


    @Override
    public File getNewestBakeTplFile(String filePathName) throws Exception {
        String filePath = filePathName.substring(0, filePathName.lastIndexOf("/"));
        String fileName = filePathName.substring(filePathName.lastIndexOf("/") + 1);

        String lastVersionName = getNewestVersionName(BaseObject.getConfig("TEMPLATE_BACK_PATH")  + filePath, fileName);
        if(lastVersionName==null){
            return null;
        }else {
            File newestTplFile = new File(BaseObject.getConfig("TEMPLATE_BACK_PATH") + filePath + "/" + lastVersionName);
            return  newestTplFile;
        }
    }

    @Override
    public String getBakeTplFileVersion(String tplFilePath) {
        return tplFilePath.substring(tplFilePath.lastIndexOf("_v")+1, tplFilePath.lastIndexOf("."));
    }

    /**
     * 在指定文件夹，生成指定文件名的最新版本名称， 比如，给 fileName=style_1-3_lm.html ，生成 style_1-3_lm_v1.html
     * @param filePath  文件路径
     * @param fileName  文件名 style_1-3_lm.html
     * @return
     */
    private String genrateNewVersionName(String filePath, String fileName){
        String fileNamePre = fileName.substring(0, fileName.lastIndexOf("."));
        String fileNamePost = fileName.substring(fileName.lastIndexOf("."));


        File file = new File(filePath);
        String[] strArray = file.list();

        if(strArray==null || strArray.length==0){
            return fileNamePre + "_v" + 1 + fileNamePost;
        }


        String version = "0";
        int lastVersionInt = 0;
        for (String s : strArray) {
            if(s.lastIndexOf("_v")!=-1 && s.substring(0,s.lastIndexOf("_v")).equals(fileNamePre)) {
                version = s.substring(s.indexOf("_v") + 2, s.indexOf("."));
                int versionInt = Integer.parseInt(version);
                if (versionInt > lastVersionInt) {
                    lastVersionInt = versionInt;
                }
            }
        }
        if(version.equals("0")){
            return fileNamePre + "_v" + 1  + fileNamePost;
        }else {
            return fileNamePre + "_v"  + (lastVersionInt + 1) + fileNamePost;
        }
    }


    private String getNewestVersionName(String filePath, String fileName){
        String fileNamePre = fileName.substring(0, fileName.lastIndexOf("."));
        String fileNamePost = fileName.substring(fileName.lastIndexOf("."));


        File file = new File(filePath);
        String[] strArray = file.list();

        if(strArray==null || strArray.length==0){
            return null;
        }


        String lastVersion = "0";
        int lastVersionInt = 0;
        for (String s : strArray) {
            if(s.substring(0,s.lastIndexOf("_v")).equals(fileNamePre)) {
                lastVersion = s.substring(s.indexOf("_v") + 2, s.indexOf("."));
                int versionInt = Integer.parseInt(lastVersion);
                if (versionInt > lastVersionInt) {
                    lastVersionInt = versionInt;
                }
            }
        }

        return fileNamePre + "_v"  + lastVersionInt + fileNamePost;
    }

    @Override
    public boolean createSiteTplDir(HttpServletRequest request, String siteId, String sitePath, String siteName) {
        //判断是否要建根节点
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("parentPath", "0");
        TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);;
        if(tplList.getDataList().size()<1){
            TemplateBo allRootBo = new TemplateBo();
            allRootBo.setTemplateName("根目录");
            allRootBo.setParentPath("0");
            allRootBo.setTemplatePath("/");
            allRootBo.setIsFolder(1);
            allRootBo.setState(1);
            SoaConnectionFactory.post(request, ConstantsUri.CMS_TEMPLATE_ADD, allRootBo, BaseResponse.class);
        }


        String siteFullPath = BaseObject.getConfig("TEMPLATE_ROOT_PATH")+"/"+sitePath;
        File siteTplDir = new File(siteFullPath+"/"+"template");
        if(!siteTplDir.exists()){
            siteTplDir.mkdirs();
        }

        TemplateBo siteRootBo = new TemplateBo();
        siteRootBo.setTemplateName(siteName);
        siteRootBo.setParentPath("/");
        siteRootBo.setTemplatePath(sitePath);
        siteRootBo.setIsFolder(1);
        siteRootBo.setState(1);
        siteRootBo.setSiteId(siteId);
        SoaConnectionFactory.post(request, ConstantsUri.CMS_TEMPLATE_ADD, siteRootBo, BaseResponse.class);

        TemplateBo siteTplBo = new TemplateBo();
        siteTplBo.setTemplateName("模板");
        siteTplBo.setParentPath(sitePath);
        siteTplBo.setTemplatePath(sitePath+"/"+"template");
        siteTplBo.setIsFolder(1);
        siteTplBo.setState(1);
        siteTplBo.setSiteId(siteId);
        SoaConnectionFactory.post(request, ConstantsUri.CMS_TEMPLATE_ADD, siteTplBo, BaseResponse.class);

        String[] subDirNames_en = {"content","list","channel"};
        String[] subDirNames_cn = {"内容","列表","栏目"};
        for(int i=0;i<subDirNames_en.length;i++) {
            String subDirEnName = subDirNames_en[i];
            File siteSubFolder = new File(siteTplDir.getPath() + "/" + subDirEnName);
            if(!siteSubFolder.exists()){
                siteSubFolder.mkdirs();
            }
            String subDirCnName = subDirNames_cn[i];

            TemplateBo tplBo = new TemplateBo();
            tplBo.setTemplateName(subDirCnName);
            tplBo.setParentPath(sitePath+"/"+"template");
            tplBo.setTemplatePath(sitePath+"/"+"template" + "/" + subDirEnName);
            tplBo.setIsFolder(1);
            tplBo.setState(1);
            tplBo.setSiteId(siteId);
            SoaConnectionFactory.post(request, ConstantsUri.CMS_TEMPLATE_ADD, tplBo, BaseResponse.class);
        }

        return true;
    }

    @Override
    public boolean updateSiteTplDirName(HttpServletRequest request, String siteId, String siteName) {
        //判断是否要建根节点
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("siteId", siteId);
        queryMap.put("parentPath","/");
        TplListResponse tplList = SoaConnectionFactory.get(request, ConstantsUri.CMS_TEMPLATE_LIST, queryMap, TplListResponse.class);;
        if(tplList.getDataList().size()==1){
            TemplateBo tplBo = tplList.getDataList().get(0);
            tplBo.setTemplateName(siteName);
            BaseResponse baseResponse = SoaConnectionFactory.putRestful(request, ConstantsUri.CMS_TEMPLATE_MOD, tplBo, BaseResponse.class, tplBo.getTemplateId());
            if(baseResponse.isSuccess()){
                return true;
            }
        }
        return false;
    }
}