package com.abc.soa.response.financed.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuy on 2017/6/30.
 */
public class InvoiceUseSaveUpdBo {
    private String id;
    /**
     * 申请人
     **/
    private String applyUser;

    /**
     * 签发状态，0：未分发，1：已分发，2：已签收
     **/
    private String issueStatus;

    /**
     * 审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿
     **/
    private String examineStatus;

    private String remark;

    private String[] invoiceTypeCode;
    //库存数量
    private String[] repoNum;
    //申请数量
    private String[] applyNum;
    //实发数量
    private String[] realNum;
    private String[] fpremark;
    //可用份数
    private String[] usableShare;
    //审批数量
    private String[] checkBook;
    /**发票分发流水号**/
    private String[] invoiceRepoIds;
    //用户ID
    private String[] useId;
    //分发备注
    private String[] ffremark;
    //发票明细ID
    private String[] fpmxId;

    /**审核意见**/
    private String checkOpinion;

    private String user;
    private String startTime;
    private String endTime;

    public InvoiceUseSaveUpdBo(){}

    public String[] getInvoiceTypeCode() {
        return invoiceTypeCode;
    }

    public void setInvoiceTypeCode(String[] invoiceTypeCode) {
        this.invoiceTypeCode = invoiceTypeCode;
    }

    public String[] getRepoNum() {
        return repoNum;
    }

    public void setRepoNum(String[] repoNum) {
        this.repoNum = repoNum;
    }

    public String[] getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String[] applyNum) {
        this.applyNum = applyNum;
    }

    public String[] getRealNum() {
        return realNum;
    }

    public void setRealNum(String[] realNum) {
        this.realNum = realNum;
    }

    public String[] getFpremark() {
        return fpremark;
    }

    public void setFpremark(String[] fpremark) {
        this.fpremark = fpremark;
    }

    public String[] getUsableShare() {
        return usableShare;
    }

    public void setUsableShare(String[] usableShare) {
        this.usableShare = usableShare;
    }


    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(String examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


  public List<InvoiceUseDetailBO> getInvoiceUseDetailBO(){
        List<InvoiceUseDetailBO> list=new ArrayList<InvoiceUseDetailBO>();
        for(int i=0;i<this.invoiceTypeCode.length;i++){
            if(this.invoiceTypeCode[i]!=null&&!"".equals(this.invoiceTypeCode[i].trim())&&!"-1".equals(this.invoiceTypeCode[i].trim())){
                InvoiceUseDetailBO invoiceUseDetailBO=new InvoiceUseDetailBO();
                invoiceUseDetailBO.setInvoiceTypeCode(this.invoiceTypeCode[i]);
                if(this.repoNum[i]!=null&&!"".equals(this.repoNum[i].trim()))
                {
                    invoiceUseDetailBO.setRepoNum(Integer.parseInt(this.repoNum[i].trim()));
                }
                if(this.usableShare[i]!=null&&!"".equals(this.usableShare[i].trim())) {
                    invoiceUseDetailBO.setUsableShare(Integer.valueOf(this.usableShare[i]));
                }
                if(this.applyNum[i]!=null&&!"".equals(this.applyNum[i].trim())) {
                    invoiceUseDetailBO.setApplyNum(Integer.parseInt(this.applyNum[i]));
                }
                if(this.fpremark[i]!=null&&!"".equals(this.fpremark[i].trim())){
                    invoiceUseDetailBO.setRemark(this.fpremark[i]);
                }
                if(this.fpmxId[i]!=null&&!"".equals(this.fpmxId[i].trim())){
                    invoiceUseDetailBO.setId(this.fpmxId[i]);
                }
                list.add(invoiceUseDetailBO);
            }
        }
        return list;
    }

    public List<InvoiceUseDetailBO> getInvoiceUseCheckBO (){
        List<InvoiceUseDetailBO> list=new ArrayList<InvoiceUseDetailBO>();
        for(int i=0;i<this.invoiceTypeCode.length;i++){
            if(this.invoiceTypeCode[i]!=null&&!"".equals(this.invoiceTypeCode[i].trim())&&!"-1".equals(this.invoiceTypeCode[i].trim())){
                InvoiceUseDetailBO invoiceUseDetailBO=new InvoiceUseDetailBO();
                invoiceUseDetailBO.setInvoiceTypeCode(this.invoiceTypeCode[i]);
                if(this.useId[i]!=null&&!"".equals(this.useId[i].trim())){
                    invoiceUseDetailBO.setUseId(this.useId[i]);
                }
                if(this.fpmxId[i]!=null&&!"".equals(this.fpmxId[i].trim())){
                    invoiceUseDetailBO.setId(this.fpmxId[i]);
                }
                if(this.checkBook[i]!=null&&!"".equals(this.checkBook[i].trim())){
                    invoiceUseDetailBO.setCheckBook(Integer.parseInt(this.checkBook[i]));
                }
                list.add(invoiceUseDetailBO);
            }
        }
        return list;
    }

    public List<InvoiceUseDetailBO> getInvoiceUseDistBO (){
        List<InvoiceUseDetailBO> list=new ArrayList<InvoiceUseDetailBO>();
        for(int i=0;i<this.invoiceTypeCode.length;i++){
            if(this.invoiceTypeCode[i]!=null&&!"".equals(this.invoiceTypeCode[i].trim())&&!"-1".equals(this.invoiceTypeCode[i].trim())){
                InvoiceUseDetailBO invoiceUseDetailBO=new InvoiceUseDetailBO();
                invoiceUseDetailBO.setInvoiceTypeCode(this.invoiceTypeCode[i]);
                //realNum
                if(this.realNum[i]!=null&&!"".equals(this.realNum[i].trim())&&!"-1".equals(this.realNum[i].trim())){
                    invoiceUseDetailBO.setRealNum(Integer.parseInt(this.realNum[i]));
                }
                if(this.invoiceRepoIds[i]!=null&&!"".equals(this.invoiceRepoIds[i].trim())){
                    invoiceUseDetailBO.setInvoiceRepoIds(this.invoiceRepoIds[i].split(","));
                }
                if(this.useId[i]!=null&&!"".equals(this.useId[i].trim())){
                    invoiceUseDetailBO.setUseId(this.useId[i]);
                }
                if(this.fpmxId[i]!=null&&!"".equals(this.fpmxId[i].trim())){
                    invoiceUseDetailBO.setId(this.fpmxId[i]);
                }
                if(this.ffremark[i]!=null&&!"".equals(this.ffremark[i].trim())){
                    invoiceUseDetailBO.setRemark(this.ffremark[i]);
                }
                list.add(invoiceUseDetailBO);
            }
        }
        return list;
    }
    public String[] getCheckBook() {
        return checkBook;
    }

    public void setCheckBook(String[] checkBook) {
        this.checkBook = checkBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getInvoiceRepoIds() {
        return invoiceRepoIds;
    }

    public void setInvoiceRepoIds(String[] invoiceRepoIds) {
        this.invoiceRepoIds = invoiceRepoIds;
    }

    public String[] getUseId() {
        return useId;
    }

    public void setUseId(String[] useId) {
        this.useId = useId;
    }

    public String[] getFfremark() {
        return ffremark;
    }

    public void setFfremark(String[] ffremark) {
        this.ffremark = ffremark;
    }

    public String[] getFpmxId() {
        return fpmxId;
    }

    public void setFpmxId(String[] fpmxId) {
        this.fpmxId = fpmxId;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
