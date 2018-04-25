package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventModelItemBo extends BaseResponse {

    private String modelitemId;

    private String eventId;

    private String field;

    private String itemLabel;

    private String priority;

    private String defValue;

    private String optValue;

    private String textSize;

    private int areaRows;

    private int areaCols;

    private String help;

    private String helpPosition;

    public String getModelitemId() {
        return modelitemId;
    }

    public void setModelitemId(String modelitemId) {
        this.modelitemId = modelitemId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
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

    public int getAreaRows() {
        return areaRows;
    }

    public void setAreaRows(int areaRows) {
        this.areaRows = areaRows;
    }

    public int getAreaCols() {
        return areaCols;
    }

    public void setAreaCols(int areaCols) {
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

    public int getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }

    public int getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(int isCustom) {
        this.isCustom = isCustom;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public int getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getCheckRule() {
        return checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }

    private String dataType;

    private int isSingle;

    private int isCustom;

    private int isDisplay;

    private int isRequired;
    private int imageWidth;
    private int imageHeight;
    private String checkRule;
}
