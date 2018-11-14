package com.study.board.framework.paging;

public class SearchCriteria extends PageCriteria {
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keword) {
		this.keyword = keword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + " keyword=" + keyword + "]";
	}
	
}
