package com.abc.soa.response.cms.file;

/**
 * Created by Administrator on 2017-06-13.
 */
public class FileBo {

    private String filePath;//文件路径
    private String fileName;//文件名字
    private Integer fileIsvalid;//是否有效
    private String contentId;//内容id

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileIsvalid() {
        return fileIsvalid;
    }

    public void setFileIsvalid(Integer fileIsvalid) {
        this.fileIsvalid = fileIsvalid;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
