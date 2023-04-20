package com.category.cate;

public class Category {
	private String catId;
	private String catPid;
	private String catNm;
	private int catLv;

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatPid() {
		return catPid;
	}

	public void setCatPid(String catPid) {
		this.catPid = catPid;
	}

	public String getCatNm() {
		return catNm;
	}

	public void setCatNm(String catNm) {
		this.catNm = catNm;
	}

	public int getCatLv() {
		return catLv;
	}

	public void setCatLv(int catLv) {
		this.catLv = catLv;
	}

	@Override
	public String toString() {
		return catNm;
	}
}
