package com.vil.pjt.domain;

import java.util.Date;

public class SampleVO {
	private String mid;
	private String mpw;
	private String mname;
	private String memail;
	private Date regdate;
	private Date updatedate;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "SampleVO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", memail=" + memail + ", regdate="
				+ regdate + ", updatedate=" + updatedate + "]";
	}
}
