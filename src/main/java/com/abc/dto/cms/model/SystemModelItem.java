package com.abc.dto.cms.model;

import com.abc.soa.request.cms.ModelItem;

/**
 * @Author liuqi
 * @Date 2017/6/8 9:38
 * 模型管理 ：（栏目模型 和 内容模型  的系统字段）
 */
public enum SystemModelItem {

    CHANNEL("栏目模型系统字段", new ModelItem[]{

            new ModelItem("siteId", ModelItemDataType.SELECT.getCode(), "所属站点", 1, 0, 1, 1),
            new ModelItem("modelId", ModelItemDataType.SELECT.getCode(), "模型列表", 2, 0, 1, 1),
            new ModelItem("parentId", ModelItemDataType.STRING_TEXT.getCode(), "父级栏目", 3, 0, 1, 0),
            new ModelItem("channelName", ModelItemDataType.STRING_TEXT.getCode(), "栏目名称", 4, 0, 1, 1),
            new ModelItem("tplChannel", ModelItemDataType.SELECT.getCode(), "栏目首页模板", 5, 0, 1, 1),
            new ModelItem("tplContent", ModelItemDataType.SELECT.getCode(), "栏目列表模板", 5, 0, 1, 1),
            new ModelItem("channelPath", ModelItemDataType.STRING_TEXT.getCode(), "访问路径", 6, 0, 1, 1),
            new ModelItem("title", ModelItemDataType.STRING_TEXT.getCode(), "meta标题", 7, 0, 1, 0),
            new ModelItem("keywords", ModelItemDataType.STRING_TEXT.getCode(), "meta关键字", 8, 0, 1, 0),
            new ModelItem("description", ModelItemDataType.STRING_TEXT.getCode(), "meta描述", 9, 0, 1, 0),
            new ModelItem("priority", ModelItemDataType.INT_TEXT.getCode(), "排列顺序", 10, 0, 1, 1),
            new ModelItem("isDisplay", ModelItemDataType.RADIO.getCode(), "显示", 11, 0, 1, 1,"1:是;0:否"),
            new ModelItem("link", ModelItemDataType.STRING_TEXT.getCode(), "外部链接", 12, 0, 1, 0),
            new ModelItem("commentControl", ModelItemDataType.RADIO.getCode(), "评论", 13, 0, 1, "0:匿名;1:会员一次;2:关闭;3:会员多次"),
//            new ModelItem("titleImg", ModelItemDataType.IMAGE.getCode(), "标题图", 14, 0, 1, 0),
            new ModelItem("contentImg", ModelItemDataType.IMAGE.getCode(), "内容图", 15, 0, 1, 0),
            new ModelItem("txt", ModelItemDataType.TEXTAREA.getCode(), "内容", 16, 0, 1, 0),
//            new ModelItem("viewGroupIds", ModelItemDataType.CHECKBOX.getCode(), "浏览权限", 17, 0, 1,"1:会员;2:非会员")
    }),

    CONTENT("内容模型系统字段", new ModelItem[]{

        new ModelItem("siteId", ModelItemDataType.SELECT.getCode(), "所属站点", 1, 0, 1, 1),
        new ModelItem("channelId", ModelItemDataType.SELECT.getCode(), "栏目", 2, 0, 1, 1),
        new ModelItem("title", ModelItemDataType.STRING_TEXT.getCode(), "标题", 3, 0, 1, 1),
        new ModelItem("typeId", ModelItemDataType.SELECT.getCode(), "内容类型", 4, 0, 1, 1),
        new ModelItem("tplContent", ModelItemDataType.SELECT.getCode(), "指定模板", 5, 0, 1, 1),
        new ModelItem("contentType", ModelItemDataType.CHECKBOX.getCode(), "标签", 9, 0, 1, 0),
        new ModelItem("description", ModelItemDataType.STRING_TEXT.getCode(), "meta描述", 10, 0, 1, 0),
        new ModelItem("shortTitle", ModelItemDataType.TEXTAREA.getCode(), "摘要", 11, 0, 1, 0),
        new ModelItem("titleColor", ModelItemDataType.COLOR_PICKER.getCode(), "标题颜色", 12, 0, 1, 0),
        new ModelItem("txt", ModelItemDataType.TEXTAREA.getCode(), "内容", 13, 0, 1, 0),
        new ModelItem("author", ModelItemDataType.STRING_TEXT.getCode(), "作者", 14, 0, 1, 0),
        new ModelItem("origin", ModelItemDataType.STRING_TEXT.getCode(), "来源", 15, 0, 1, 0),
        new ModelItem("topLevel", ModelItemDataType.RADIO.getCode(), "固顶", 16, 0, 1, 1, "0:否;1:是"),
        new ModelItem("releaseDate", ModelItemDataType.DATE.getCode(), "发布时间", 17, 0, 1, 1),
        new ModelItem("titleImg", ModelItemDataType.IMAGE.getCode(), "标题图", 18, 0, 1, 0),
        new ModelItem("contentImg", ModelItemDataType.IMAGE.getCode(), "内容图", 19, 0, 1, 0),
        new ModelItem("mediaPath", ModelItemDataType.MEDIA.getCode(), "多媒体", 20, 0, 1, 0),
        new ModelItem("mediaType", ModelItemDataType.CHECKBOX.getCode(), "视频类型", 21, 0, 1,"历史:历史;古装:古装;都市:都市;喜剧:喜剧;悬疑:悬疑;动作:动作;谍战:谍战;伦理:伦理;战争:战争;惊悚:惊悚"),
        new ModelItem("link", ModelItemDataType.STRING_TEXT.getCode(), "相关链接", 22, 0, 1, 0),
        new ModelItem("viewGroupIds", ModelItemDataType.CHECKBOX.getCode(), "浏览权限", 23, 0, 1,"1:会员;2:非会员"),
    });

    private String description;
    private ModelItem[] sysModelItems;

    public ModelItem[] getSysModelItems(){
        return this.sysModelItems;
    }

    SystemModelItem(String description, ModelItem[] sysModelItems){
        this.description = description;
        this.sysModelItems = sysModelItems;
    }
}
