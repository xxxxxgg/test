package com.study.board.framework.paging;

import com.study.board.service.BoardService;

public class PageCriteria extends Criteria {
	private int pageNum;
	private int pageSize;

	public PageCriteria() {
		this.pageNum = 1;
		this.pageSize = 10;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum >= 1) {
			this.pageNum = pageNum;
		} else {
			this.pageNum = 1;
		}
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0 && pageSize <= BoardService.MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = BoardService.DEFAULT_PAGE_SIZE;
		}
	}
	
	public int getPageStart() {
		return (this.pageNum - 1) * pageSize;
	}

	public void setPageSize1(int pageSize2) {
		this.pageSize = pageSize2;
	}
}
