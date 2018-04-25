package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class ProductrepoRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String goodsName;
    private String repoType;
    private String goodsId;
    private String productId;
    private String guige;
    private String startRepo;
    private String endRepo;
    
    
    
    
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getRepoType() {
		return repoType;
	}
	public void setRepoType(String repoType) {
		this.repoType = repoType;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getStartRepo() {
		return startRepo;
	}
	public void setStartRepo(String startRepo) {
		this.startRepo = startRepo;
	}
	public String getEndRepo() {
		return endRepo;
	}
	public void setEndRepo(String endRepo) {
		this.endRepo = endRepo;
	}
    
}
