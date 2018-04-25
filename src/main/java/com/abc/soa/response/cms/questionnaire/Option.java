package com.abc.soa.response.cms.questionnaire;
import java.io.Serializable;


/**
 * 
 * 选项表
 * 
 **/
public class Option{

	/**选项ID**/
	private String id;

	/**题目ID**/
	private String subjectsId;

	/**答案内容**/
	private String optionString;

    /**选项状态，true:正常，false:删除**/
    private Boolean status;

	/*排序号*/
	private Integer sequence;


	public Integer getSequence() {
		return sequence;
	}

	public Option setSequence(Integer sequence) {
		this.sequence = sequence;
		return this;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setOptionString(String optionString){
		this.optionString = optionString;
	}

	public String getOptionString(){
		return this.optionString;
	}

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }
}
