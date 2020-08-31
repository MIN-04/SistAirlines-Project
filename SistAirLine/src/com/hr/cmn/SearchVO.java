package com.hr.cmn;


public class SearchVO extends DTO {
	
	private String searchDiv;//�˻�����
	private String searchWord;//�˻���
	private String searchWord02;//�˻���
	
	public SearchVO() {}

	public SearchVO(String searchDiv, String searchWord, String searchWord02) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.searchWord02 = searchWord02;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchWord02() {
		return searchWord02;
	}

	public void setSearchWord02(String searchWord02) {
		this.searchWord02 = searchWord02;
	}

	@Override
	public String toString() {
		return "SearchVO [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", searchWord02=" + searchWord02
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
