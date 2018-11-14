package com.study.board.framework.paging;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.study.board.service.BoardService;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = BoardService.MAX_PAGE_SIZE;
	private SearchCriteria pcr;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcPaging();
	}

	private void calcPaging() {
		int tempEndPage = (int) (Math.ceil(pcr.getPageNum() / (double)displayPageNum) * displayPageNum);
		startPage = (tempEndPage - displayPageNum) + 1;
		
		int realEndPage = (int) (Math.ceil(totalCount / (double) pcr.getPageSize()));
		
		if(tempEndPage > realEndPage) {
			endPage = realEndPage;
		} else {
			endPage = tempEndPage;
		}
		
		prev = startPage != 1;
		next = endPage * pcr.getPageSize() < totalCount && endPage > 0;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public PageCriteria getPcr() {
		return pcr;
	}

	public void setPcr(SearchCriteria pcr) {
		this.pcr = pcr;
	}
	
	public String makeQuery(int pageNum) {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("pageNum", pageNum)
				.queryParam("pageSize", pcr.getPageSize())
				.build();
		
		return uri.toUriString();
	}
	
	public String makeSearch(int pageNum) {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("pageNum", pageNum)
				.queryParam("pageSize", pcr.getPageSize())
				.queryParam("searchType", pcr.getSearchType())
				.queryParam("keyword", encode(pcr.getKeyword()))
				.build();
		
		return uri.toUriString();
	}

	private String encode(String keyword) {
		if(keyword == null ||keyword.trim().length() == 0) {
			return "";
		}
		
		try {
			return URLEncoder.encode(keyword, "UTF-8");			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", pcr=" + pcr + "]";
	}
	
}
