package com.abc.service;

import com.abc.dto.cms.tpl.TemplateBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 模板Service
 */
public interface TplManager {
    /**
     * 获得模板列表。根据前缀，用于选择模板。
     *
     * @param prefix
     *            前缀
     * @return
     */
    public List<? extends Tpl> getListByPrefix(String prefix);

    public List<String> getNameListByPrefix(String prefix);

    /**
     * 获得下级模板列表。根据路径，用于展现下级目录和文件。
     *
     * @param path
     *            路径
     * @return
     */
    public List<? extends Tpl> getChild(String path, String root) throws FileNotFoundException;


    /**
     * 获得指定类型的下级模板列表。根据路径，用于展现下级目录和文件。
     *
     * @param path
     *            路径
     * @return
     */
//    public List<? extends Tpl> getChild(String path, String type) throws FileNotFoundException;

    /**
     * 获得全部下级模板
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public List<? extends Tpl> getAllChildList(String path, String root) throws FileNotFoundException;

    /**
     * 获得指定类型全部下级模板
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public List<? extends Tpl> getAllChildList_SpeciType(String path, String type) throws FileNotFoundException;

    /**
     * 获得指定类型全部下级模板，不包括文件
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public List<? extends Tpl> getAllChildList_SpeciType_excludeDir(String path, String type) throws FileNotFoundException;

    /**
     * 获取指定类型，指定站点的所有模板
     * @param templateProperty
     * @param templateType   模板页面类型，在字典中约定，比如栏目主页模板 tpl_index；栏目列表模板 tpl_list，内容模板 tpl_content
     * @param siteId
     * @return
     */
    public List<TemplateBo> getAllTpl_Speci_tplProperty_siteId(HttpServletRequest request, String templateProperty, String templateType, String siteId);

    /**
     * 保存模板
     *
     * @param name
     *            模板名称
     * @param source
     *            模板内容
     * @param isDirecotry
     *            是否目录
     */
    public void save(String name, String source, boolean isDirectory);

    /**
     * 保存模板
     *
     * @param path
     * @param file
     */
    public void save(String path, MultipartFile file);

    /**
     * 获得模板
     *
     * @param name
     * @return
     */
    public Tpl get(String name);

    /**
     * 更新模板
     *
     * @param name
     *            模板名称
     * @param source
     *            模板内容
     */
    public void update(String name, String source);

    /**
     * 修改模板名称或路径
     *
     * @param orig
     * @param dist
     */
    public void rename(String orig, String dist);

    /**
     * 删除模板
     *
     * @param names
     *            模板名称数组
     * @return 被删除的模板数量
     */
    public int delete(String[] names);

    /**
     * 备份模板文件，并生成版本信息
     * @param sourceFile  要备份的源文件
     * @param filePathName  备份要放到的具体目录，方法内会将该具体目录放在设置的备份父文件夹中
     * @return  返回备份文件的绝对路径
     */
    public String backupFile(File sourceFile, String filePathName) throws Exception;

    /**
     * 保存模板文件内容到备份目录，并生成版本信息
     * @param filePathName 要写入的目标文件
     * @param tplContant  备份要放到的具体目录，方法内会将该具体目录放在设置的备份父文件夹中
     */
    public void save_createVersionFile(String filePathName, String tplContant) throws Exception;

    /**
     * 根据文件路径+文件名  获取最新版本的备份模板文件
     * @param filePathName  templatePathName
     * @return
     * @throws Exception
     */
    public File getNewestBakeTplFile(String filePathName) throws Exception;

    /**
     * 根据模板备份文件路径，返回其版本号，比如 v316
     * @param tplFilePath
     * @return
     */
    public String getBakeTplFileVersion(String tplFilePath);

    /**
     * 新建指定站点的模板文件夹
     * @param sitePath 站点路径 如：cms
     * @return
     */
    public boolean createSiteTplDir(HttpServletRequest request, String siteId, String sitePath, String siteName);

    /**
     * 修改站点时，修改模板文件夹名称
     * @param siteName 站点中文名称
     * @return
     */
    public boolean updateSiteTplDirName(HttpServletRequest request, String siteId, String siteName);
}