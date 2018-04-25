package com.abc.soa.request.cms;

import com.abc.soa.request.system.BasePaginationCriteria;

/**
 * Created by stuy on 2017/7/10.
 */
public class CommentPaginationCriteria extends BasePaginationCriteria {
    private String contentId;

    private String isChecked;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    private String isRecommend;
}
