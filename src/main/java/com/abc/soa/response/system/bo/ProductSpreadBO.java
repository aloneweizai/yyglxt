package com.abc.soa.response.system.bo;

import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 18:19
 */
public class ProductSpreadBO {

    private String id;

    private String name; // 宣传名称

    /**
     * 产品链接
     */
    private String url;

    private Date createTime; // 创建时间

    private Date lastUpdate; // 修改时间

    private String updateUserId; // 修改人ID

    private String updateName; // 修改人

    private List<ProductImg> productImgs; // 图片地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<ProductImg> getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(List<ProductImg> productImgs) {
        this.productImgs = productImgs;
    }
}
