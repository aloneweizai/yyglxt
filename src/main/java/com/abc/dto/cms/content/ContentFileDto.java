package com.abc.dto.cms.content;

/**
 * Created by relic5 on 2017/6/11.
 */
public class ContentFileDto {

    private String contentId;

    private String filePath;

    private String fileName;

    private String fileIsvalid;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

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

    public String getFileIsvalid() {
        return fileIsvalid;
    }

    public void setFileIsvalid(String fileIsvalid) {
        this.fileIsvalid = fileIsvalid;
    }
}
