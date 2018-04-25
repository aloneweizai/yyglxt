package com.abc.dto.cms.column;

/**
 * Created by relic5 on 2017/6/7.
 */
public class ChannelItemDto {

    private String modelitemId;

    private String modelId;

    /**
     * 字段**varchar(50)
     **/
    private String field;
    /**
     * 名称**varchar(100)
     **/
    private String itemLabel;
    /**
     * 默认值**varchar(255)
     **/
    private String defValue;
    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;
    /**
     * 可选项**varchar(255)
     **/
    private String optValue;

    /**
     * 长度**varchar(20)
     **/
    private String textSize;

    /**
     * 文本行数**varchar(3)
     **/
    private String areaRows;

    /**
     * 文本列数**varchar(3)
     **/
    private String areaCols;
    /**
     * 帮助信息**varchar(255)
     **/
    private String help;
    /**
     * 帮助位置**varchar(1)
     **/
    private String helpPosition;
    /**
     * 数据类型**int(11)
     **/
    private String dataType;
    /**
     * 是否独占一行**tinyint(1)
     **/
    private Integer isSingle;
    /**
     * 是否栏目模型项**tinyint(1)
     **/
    private Integer isChannel;
    /**
     * 是否自定义**tinyint(1)
     **/
    private Integer isCustom;
    /**
     * 是否显示**tinyint(1)
     **/
    private Integer isDisplay;
    /**
     * 是否必填项**tinyint(1)
     **/
    private Integer isRequired;
    /**
     * 校验规则**varchar(500)
     **/
    private String checkRule;

    public String getModelitemId() {
        return modelitemId;
    }

    public void setModelitemId(String modelitemId) {
        this.modelitemId = modelitemId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    public String getAreaRows() {
        return areaRows;
    }

    public void setAreaRows(String areaRows) {
        this.areaRows = areaRows;
    }

    public String getAreaCols() {
        return areaCols;
    }

    public void setAreaCols(String areaCols) {
        this.areaCols = areaCols;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getHelpPosition() {
        return helpPosition;
    }

    public void setHelpPosition(String helpPosition) {
        this.helpPosition = helpPosition;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getIsChannel() {
        return isChannel;
    }

    public void setIsChannel(Integer isChannel) {
        this.isChannel = isChannel;
    }

    public Integer getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Integer isCustom) {
        this.isCustom = isCustom;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public String getCheckRule() {
        return checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }
}
