package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;

public class ExpLevel implements Serializable{
	    /**
	 * 
	 */
	    private static final long serialVersionUID = 1L;
		private String id;
	    private String name;
	    private String value;
	    private int topPerDay;
	    private Boolean status;
	    private Date lastUpdate;
	    private Date createTime;
	    private String medal;
	    private String medalIcon;
	    private Integer minValue;
	    private Integer maxValue;
	    
	    
	    
		public Integer getMinValue() {
			return minValue;
		}
		public void setMinValue(Integer minValue) {
			this.minValue = minValue;
		}
		public Integer getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(Integer maxValue) {
			this.maxValue = maxValue;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public int getTopPerDay() {
			return topPerDay;
		}
		public void setTopPerDay(int topPerDay) {
			this.topPerDay = topPerDay;
		}
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public Date getLastUpdate() {
			return lastUpdate;
		}
		public void setLastUpdate(Date lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getMedal() {
			return medal;
		}
		public void setMedal(String medal) {
			this.medal = medal;
		}
		public String getMedalIcon() {
			return medalIcon;
		}
		public void setMedalIcon(String medalIcon) {
			this.medalIcon = medalIcon;
		}
	    
	    
}
