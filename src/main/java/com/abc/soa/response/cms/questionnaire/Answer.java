package com.abc.soa.response.cms.questionnaire;

/**
 * @Author liuqi
 * @Date 2017/7/5 15:21
 */
public class Answer {

    /**答题记录ID**/
    private String answerLogId;

    /**题目ID**/
    private String subjectsId;

    /**答案内容**/
    private String content;

    public void setAnswerLogId(String answerLogId){
        this.answerLogId = answerLogId;
    }

    public String getAnswerLogId(){
        return this.answerLogId;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }
}
