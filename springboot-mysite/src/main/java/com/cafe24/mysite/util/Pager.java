package com.cafe24.mysite.util;

import org.springframework.stereotype.Component;

@Component
public class Pager {
	private int countPerPage;
	private int countPerBlock;

	private int totalCount;
	private int totalPageCount;
	private int totalBlockCount;
	private int currentPage;
	private int currentBlock;
	private int nextPage;
	private int previewPage;
	
	public int getCountPerPage() {
		return countPerPage;
	}
	public int getCountPerBlock() {
		return countPerBlock;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	private void setTotalPageCount() {
		this.totalPageCount = (totalCount + countPerPage - 1) / countPerPage;
	}
	public int getTotalBlockCount() {
		return totalBlockCount;
	}
	private void setTotalBlockCount() {
		this.totalBlockCount = (totalPageCount + countPerBlock - 1) / countPerBlock;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentBlock() {
		return currentBlock;
	}
	private void setCurrentBlock() {
		this.currentBlock = currentPage / countPerBlock;
	}
	public int getNextPage() {
		return nextPage;
	}
	private void setNextPage() {
		this.nextPage = (((currentBlock + 1) * countPerBlock) + 1 > totalPageCount) ? totalPageCount - 1 : ((currentBlock + 1) * countPerBlock);
	}
	public int getPreviewPage() {
		return previewPage;
	}
	private void setPreviewPage() {
		this.previewPage = (currentBlock == 0) ? 0 : (currentBlock - 1) * countPerBlock;
	}
	
	public void init(int countPerPage, int countPerBlock, int totalCount, int currentPage) {
		this.countPerPage = countPerPage;
		this.countPerBlock = countPerBlock;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		
		setTotalPageCount();
		setTotalBlockCount();
		setCurrentBlock();
		setPreviewPage();
		setNextPage();
	}
}
