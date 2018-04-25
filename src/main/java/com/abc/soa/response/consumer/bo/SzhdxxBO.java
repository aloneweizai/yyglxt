package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SzhdxxBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nsrsbh; //纳税人识别号
	private String nsrmc; //纳税人名称
	private String djzclxDm;  //登记注册类型
	private String zzjgdm;	//组织机构代码
	private String scjydz;	//生产经营地址
	private String scjydYb;	//生产经营地邮编
	private String dhhm;	//电话号码
	private String dwxzDm;	//单位性质代码
	private String hy_dm;	//行业代码
	private String fddbrmc;	//法定代表人名称
	private String frzjlxDm;	//法人证件类型代码
	private String zjhm;	//证件号码
	private String bsrmc;	//办税员名称
	private String lsswdjlxDm	; //临时税务登记类型代码
	private String swdjblxDm;	//税务登记表类型代码
	private String swjgDm;	//税务机构代码
	private String swjgmc;	//税务机构名称
	private String gszgswjgDm;	//工商机关代码
	private String nsrSwjgDm;	//纳税人税务机构代码
	private String nsrSwjgmc;	//纳税人税务机构名称
	private String jdxzDm;	//街道乡镇代码
	private String kyslrq;	//开业设立日期
	private String bhrq;	//变化日期
	private String lsgxDm;	//隶属关系代码
	private String pzjg;	//批准机关
	private String pzwh;	//批准文号
	private String kyrq;	//工商开业日期
	private String scjyqxQ;	//生产经营期限起
	private String scjyqxZ;	//生产经营期限止
	private String zzlbDm;	//证照类别代码
	private String gsdjzzh;	//工商登记证字号
	private String zcdz;	//注册地址
	private String zcdYb;	//注册地邮编
	private String zcdDhhm;	//注册地电话号码
	private String hsfsDm;	//核算方式代码
	private String cyrs;	//从业人数
	private String wjrs;	//外籍人数
	private String wzwz;	//网站网址
	private String sykjzdDm;	//使用会计制度代码
	private String jyfw;	//经营范围
	private String frDhhm;	//法人电话号码
	private String frYddhhm;	//法人移动电话号码
	private String frDydz;	//法人电子邮箱
	private String cwfzrmc;	//财务负责人名称
	private String cwfzrZjlxDm;	//财务负责人证件类型代码
	private String cwfzrZjhm;	//财务负责人证件号码
	private String cwfzrDhhm;	//财务负责人电话号码
	private String cwfzrYddhhm;	//财务负责人移动电话
	private String cwfzrDydz;//	财务负责人电子邮箱
	private String bsrZjlxDm;	//办税人员证件类型代码
	private String bsrZjhm;	//办税人证件号码
	private String bsrDhhm;//办税人电话号码
	private String bsrYddhhm;	//办税人移动电话号码
	private String bsrDydz;	////办税人电子邮箱
	private String swdlrmc;	//税务代理人名称
	private String swdlrNsrsbh;	//税务代理人纳税人识别号
	private String swdlrDhhm;	//税务代理人电话号码
	private String swdlrDydz;	//税务代理人电子邮箱
	private String zczb;	//注册资本
	private String zrrtzbl;	//自然人投资比例
	private String wztzbl;	//外资投资比例
	private String gytzbl;	//国有投资比例
	private String gsfzrq;	//工商登记发照日期
	private String fshy1Dm;	//附属行业代码1
	private String fshy2Dm;	//附属行业代码2
	private String fshy3Dm;	//附属行业代码3
	private String yxqQ;	//有效期起
	private String yxqZ;	//有效期止
	private String nsrmcYw;	//纳税人名称英文
	private String hhrs;	//合伙人数
	private String gdrs;	//固定人数
	private String gshbz;	//工商户标志
	private String fzrq;	//发证日期
	private String tbrq;	//填表日期
	private String djzclxmc;	//登记注册类型名称
	private String hymc;	//行业名称
	private String frzjlxmc;	//法人证件类型名称
	private String jdxzmc;	//街道乡镇名称
	private String dwlsgxmc;	//单位隶属关系名称
	private String hsfsmc;	//核算方式名称
	private String kjzdzzmc;	//会计制度准则名称
	private String cwffzrzjlxmc;	//财务负责人证件类型名称
	private String bsrsfzjzlmc;	//办税人证件类型名称
	private String gsdjjgmc;	//工商登记机关名称
	private String ssglyDm;	//税收管理员代码
	private String ssglymc;	//税收管理员名称
	private List<tzfBO> tzfList;	//投资方List
	private List<djZczbtzzeBO> djZczbtzzeList;	//登记注册资本投资总额
	private List<kkyhBO> kkyhList;	//扣款银行信息List
	private List<Szhdxx> szhdxxList;

	public String getBhrq() {
		return bhrq;
	}

	public void setBhrq(String bhrq) {
		this.bhrq = bhrq;
	}

	public String getBsrDhhm() {
		return bsrDhhm;
	}

	public void setBsrDhhm(String bsrDhhm) {
		this.bsrDhhm = bsrDhhm;
	}

	public String getBsrDydz() {
		return bsrDydz;
	}

	public void setBsrDydz(String bsrDydz) {
		this.bsrDydz = bsrDydz;
	}

	public String getBsrmc() {
		return bsrmc;
	}

	public void setBsrmc(String bsrmc) {
		this.bsrmc = bsrmc;
	}

	public String getBsrsfzjzlmc() {
		return bsrsfzjzlmc;
	}

	public void setBsrsfzjzlmc(String bsrsfzjzlmc) {
		this.bsrsfzjzlmc = bsrsfzjzlmc;
	}

	public String getBsrYddhhm() {
		return bsrYddhhm;
	}

	public void setBsrYddhhm(String bsrYddhhm) {
		this.bsrYddhhm = bsrYddhhm;
	}

	public String getBsrZjhm() {
		return bsrZjhm;
	}

	public void setBsrZjhm(String bsrZjhm) {
		this.bsrZjhm = bsrZjhm;
	}

	public String getBsrZjlxDm() {
		return bsrZjlxDm;
	}

	public void setBsrZjlxDm(String bsrZjlxDm) {
		this.bsrZjlxDm = bsrZjlxDm;
	}

	public String getCwffzrzjlxmc() {
		return cwffzrzjlxmc;
	}

	public void setCwffzrzjlxmc(String cwffzrzjlxmc) {
		this.cwffzrzjlxmc = cwffzrzjlxmc;
	}

	public String getCwfzrDhhm() {
		return cwfzrDhhm;
	}

	public void setCwfzrDhhm(String cwfzrDhhm) {
		this.cwfzrDhhm = cwfzrDhhm;
	}

	public String getCwfzrDydz() {
		return cwfzrDydz;
	}

	public void setCwfzrDydz(String cwfzrDydz) {
		this.cwfzrDydz = cwfzrDydz;
	}

	public String getCwfzrmc() {
		return cwfzrmc;
	}

	public void setCwfzrmc(String cwfzrmc) {
		this.cwfzrmc = cwfzrmc;
	}

	public String getCwfzrYddhhm() {
		return cwfzrYddhhm;
	}

	public void setCwfzrYddhhm(String cwfzrYddhhm) {
		this.cwfzrYddhhm = cwfzrYddhhm;
	}

	public String getCwfzrZjhm() {
		return cwfzrZjhm;
	}

	public void setCwfzrZjhm(String cwfzrZjhm) {
		this.cwfzrZjhm = cwfzrZjhm;
	}

	public String getCwfzrZjlxDm() {
		return cwfzrZjlxDm;
	}

	public void setCwfzrZjlxDm(String cwfzrZjlxDm) {
		this.cwfzrZjlxDm = cwfzrZjlxDm;
	}

	public String getCyrs() {
		return cyrs;
	}

	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}

	public String getDhhm() {
		return dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}

	public String getDjzclxDm() {
		return djzclxDm;
	}

	public void setDjzclxDm(String djzclxDm) {
		this.djzclxDm = djzclxDm;
	}

	public String getDjzclxmc() {
		return djzclxmc;
	}

	public void setDjzclxmc(String djzclxmc) {
		this.djzclxmc = djzclxmc;
	}

	public List<djZczbtzzeBO> getDjZczbtzzeList() {
		return djZczbtzzeList;
	}

	public void setDjZczbtzzeList(List<djZczbtzzeBO> djZczbtzzeList) {
		this.djZczbtzzeList = djZczbtzzeList;
	}

	public String getDwlsgxmc() {
		return dwlsgxmc;
	}

	public void setDwlsgxmc(String dwlsgxmc) {
		this.dwlsgxmc = dwlsgxmc;
	}

	public String getDwxzDm() {
		return dwxzDm;
	}

	public void setDwxzDm(String dwxzDm) {
		this.dwxzDm = dwxzDm;
	}

	public String getFddbrmc() {
		return fddbrmc;
	}

	public void setFddbrmc(String fddbrmc) {
		this.fddbrmc = fddbrmc;
	}

	public String getFrDhhm() {
		return frDhhm;
	}

	public void setFrDhhm(String frDhhm) {
		this.frDhhm = frDhhm;
	}

	public String getFrDydz() {
		return frDydz;
	}

	public void setFrDydz(String frDydz) {
		this.frDydz = frDydz;
	}

	public String getFrYddhhm() {
		return frYddhhm;
	}

	public void setFrYddhhm(String frYddhhm) {
		this.frYddhhm = frYddhhm;
	}

	public String getFrzjlxDm() {
		return frzjlxDm;
	}

	public void setFrzjlxDm(String frzjlxDm) {
		this.frzjlxDm = frzjlxDm;
	}

	public String getFrzjlxmc() {
		return frzjlxmc;
	}

	public void setFrzjlxmc(String frzjlxmc) {
		this.frzjlxmc = frzjlxmc;
	}

	public String getFshy1Dm() {
		return fshy1Dm;
	}

	public void setFshy1Dm(String fshy1Dm) {
		this.fshy1Dm = fshy1Dm;
	}

	public String getFshy2Dm() {
		return fshy2Dm;
	}

	public void setFshy2Dm(String fshy2Dm) {
		this.fshy2Dm = fshy2Dm;
	}

	public String getFshy3Dm() {
		return fshy3Dm;
	}

	public void setFshy3Dm(String fshy3Dm) {
		this.fshy3Dm = fshy3Dm;
	}

	public String getFzrq() {
		return fzrq;
	}

	public void setFzrq(String fzrq) {
		this.fzrq = fzrq;
	}

	public String getGdrs() {
		return gdrs;
	}

	public void setGdrs(String gdrs) {
		this.gdrs = gdrs;
	}

	public String getGsdjjgmc() {
		return gsdjjgmc;
	}

	public void setGsdjjgmc(String gsdjjgmc) {
		this.gsdjjgmc = gsdjjgmc;
	}

	public String getGsdjzzh() {
		return gsdjzzh;
	}

	public void setGsdjzzh(String gsdjzzh) {
		this.gsdjzzh = gsdjzzh;
	}

	public String getGsfzrq() {
		return gsfzrq;
	}

	public void setGsfzrq(String gsfzrq) {
		this.gsfzrq = gsfzrq;
	}

	public String getGshbz() {
		return gshbz;
	}

	public void setGshbz(String gshbz) {
		this.gshbz = gshbz;
	}

	public String getGszgswjgDm() {
		return gszgswjgDm;
	}

	public void setGszgswjgDm(String gszgswjgDm) {
		this.gszgswjgDm = gszgswjgDm;
	}

	public String getGytzbl() {
		return gytzbl;
	}

	public void setGytzbl(String gytzbl) {
		this.gytzbl = gytzbl;
	}

	public String getHhrs() {
		return hhrs;
	}

	public void setHhrs(String hhrs) {
		this.hhrs = hhrs;
	}

	public String getHsfsDm() {
		return hsfsDm;
	}

	public void setHsfsDm(String hsfsDm) {
		this.hsfsDm = hsfsDm;
	}

	public String getHsfsmc() {
		return hsfsmc;
	}

	public void setHsfsmc(String hsfsmc) {
		this.hsfsmc = hsfsmc;
	}

	public String getHy_dm() {
		return hy_dm;
	}

	public void setHy_dm(String hy_dm) {
		this.hy_dm = hy_dm;
	}

	public String getHymc() {
		return hymc;
	}

	public void setHymc(String hymc) {
		this.hymc = hymc;
	}

	public String getJdxzDm() {
		return jdxzDm;
	}

	public void setJdxzDm(String jdxzDm) {
		this.jdxzDm = jdxzDm;
	}

	public String getJdxzmc() {
		return jdxzmc;
	}

	public void setJdxzmc(String jdxzmc) {
		this.jdxzmc = jdxzmc;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public String getKjzdzzmc() {
		return kjzdzzmc;
	}

	public void setKjzdzzmc(String kjzdzzmc) {
		this.kjzdzzmc = kjzdzzmc;
	}

	public List<kkyhBO> getKkyhList() {
		return kkyhList;
	}

	public void setKkyhList(List<kkyhBO> kkyhList) {
		this.kkyhList = kkyhList;
	}

	public String getKyrq() {
		return kyrq;
	}

	public void setKyrq(String kyrq) {
		this.kyrq = kyrq;
	}

	public String getKyslrq() {
		return kyslrq;
	}

	public void setKyslrq(String kyslrq) {
		this.kyslrq = kyslrq;
	}

	public String getLsgxDm() {
		return lsgxDm;
	}

	public void setLsgxDm(String lsgxDm) {
		this.lsgxDm = lsgxDm;
	}

	public String getLsswdjlxDm() {
		return lsswdjlxDm;
	}

	public void setLsswdjlxDm(String lsswdjlxDm) {
		this.lsswdjlxDm = lsswdjlxDm;
	}

	public String getNsrmcYw() {
		return nsrmcYw;
	}

	public void setNsrmcYw(String nsrmcYw) {
		this.nsrmcYw = nsrmcYw;
	}

	public String getNsrSwjgDm() {
		return nsrSwjgDm;
	}

	public void setNsrSwjgDm(String nsrSwjgDm) {
		this.nsrSwjgDm = nsrSwjgDm;
	}

	public String getNsrSwjgmc() {
		return nsrSwjgmc;
	}

	public void setNsrSwjgmc(String nsrSwjgmc) {
		this.nsrSwjgmc = nsrSwjgmc;
	}

	public String getPzjg() {
		return pzjg;
	}

	public void setPzjg(String pzjg) {
		this.pzjg = pzjg;
	}

	public String getPzwh() {
		return pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}

	public String getScjydYb() {
		return scjydYb;
	}

	public void setScjydYb(String scjydYb) {
		this.scjydYb = scjydYb;
	}

	public String getScjydz() {
		return scjydz;
	}

	public void setScjydz(String scjydz) {
		this.scjydz = scjydz;
	}

	public String getScjyqxQ() {
		return scjyqxQ;
	}

	public void setScjyqxQ(String scjyqxQ) {
		this.scjyqxQ = scjyqxQ;
	}

	public String getScjyqxZ() {
		return scjyqxZ;
	}

	public void setScjyqxZ(String scjyqxZ) {
		this.scjyqxZ = scjyqxZ;
	}


	public String getSsglyDm() {
		return ssglyDm;
	}

	public void setSsglyDm(String ssglyDm) {
		this.ssglyDm = ssglyDm;
	}

	public String getSsglymc() {
		return ssglymc;
	}

	public void setSsglymc(String ssglymc) {
		this.ssglymc = ssglymc;
	}

	public String getSwdjblxDm() {
		return swdjblxDm;
	}

	public void setSwdjblxDm(String swdjblxDm) {
		this.swdjblxDm = swdjblxDm;
	}

	public String getSwdlrDhhm() {
		return swdlrDhhm;
	}

	public void setSwdlrDhhm(String swdlrDhhm) {
		this.swdlrDhhm = swdlrDhhm;
	}

	public String getSwdlrDydz() {
		return swdlrDydz;
	}

	public void setSwdlrDydz(String swdlrDydz) {
		this.swdlrDydz = swdlrDydz;
	}

	public String getSwdlrmc() {
		return swdlrmc;
	}

	public void setSwdlrmc(String swdlrmc) {
		this.swdlrmc = swdlrmc;
	}

	public String getSwdlrNsrsbh() {
		return swdlrNsrsbh;
	}

	public void setSwdlrNsrsbh(String swdlrNsrsbh) {
		this.swdlrNsrsbh = swdlrNsrsbh;
	}

	public String getSwjgDm() {
		return swjgDm;
	}

	public void setSwjgDm(String swjgDm) {
		this.swjgDm = swjgDm;
	}

	public String getSwjgmc() {
		return swjgmc;
	}

	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}

	public String getSykjzdDm() {
		return sykjzdDm;
	}

	public void setSykjzdDm(String sykjzdDm) {
		this.sykjzdDm = sykjzdDm;
	}

	public String getTbrq() {
		return tbrq;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public List<tzfBO> getTzfList() {
		return tzfList;
	}

	public void setTzfList(List<tzfBO> tzfList) {
		this.tzfList = tzfList;
	}

	public String getWjrs() {
		return wjrs;
	}

	public void setWjrs(String wjrs) {
		this.wjrs = wjrs;
	}

	public String getWztzbl() {
		return wztzbl;
	}

	public void setWztzbl(String wztzbl) {
		this.wztzbl = wztzbl;
	}

	public String getWzwz() {
		return wzwz;
	}

	public void setWzwz(String wzwz) {
		this.wzwz = wzwz;
	}

	public String getYxqQ() {
		return yxqQ;
	}

	public void setYxqQ(String yxqQ) {
		this.yxqQ = yxqQ;
	}

	public String getYxqZ() {
		return yxqZ;
	}

	public void setYxqZ(String yxqZ) {
		this.yxqZ = yxqZ;
	}

	public String getZcdDhhm() {
		return zcdDhhm;
	}

	public void setZcdDhhm(String zcdDhhm) {
		this.zcdDhhm = zcdDhhm;
	}

	public String getZcdYb() {
		return zcdYb;
	}

	public void setZcdYb(String zcdYb) {
		this.zcdYb = zcdYb;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getZczb() {
		return zczb;
	}

	public void setZczb(String zczb) {
		this.zczb = zczb;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getZrrtzbl() {
		return zrrtzbl;
	}

	public void setZrrtzbl(String zrrtzbl) {
		this.zrrtzbl = zrrtzbl;
	}

	public String getZzjgdm() {
		return zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getZzlbDm() {
		return zzlbDm;
	}

	public void setZzlbDm(String zzlbDm) {
		this.zzlbDm = zzlbDm;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}


	public List<Szhdxx> getSzhdxxList() {
		return szhdxxList;
	}

	public void setSzhdxxList(List<Szhdxx> szhdxxList) {
		this.szhdxxList = szhdxxList;
	}
}
