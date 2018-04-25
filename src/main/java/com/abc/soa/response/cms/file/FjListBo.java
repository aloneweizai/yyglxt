package com.abc.soa.response.cms.file;

import java.util.List;

/**
 * Created by Administrator on 2017-06-13.
 */
public class FjListBo {

    private String directory;

    private List<FjBo> fjBo;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public List<FjBo> getFjBo() {
        return fjBo;
    }

    public void setFjBo(List<FjBo> fjBo) {
        this.fjBo = fjBo;
    }
}
