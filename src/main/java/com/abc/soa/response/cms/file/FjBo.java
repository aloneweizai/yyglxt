package com.abc.soa.response.cms.file;

import java.util.List;

/**
 * Created by Administrator on 2017-06-13.
 */
public class FjBo {
    private String fileName;

    private List<Byte> content;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Byte> getContent() {
        return content;
    }

    public void setContent(List<Byte> content) {
        this.content = content;
    }
}
