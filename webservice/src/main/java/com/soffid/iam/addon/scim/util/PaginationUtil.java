package com.soffid.iam.addon.scim.util;

public class PaginationUtil {

	private boolean active = false;

	private int startIndex = -1;
	private int count = -1;

	private int totalResults = -1;
	private int itemsPerPage = -1;

	private int index = -1;

	public PaginationUtil(String startIndex, String count) {
		super();
		if (!startIndex.trim().isEmpty())
			this.startIndex = Integer.parseInt(startIndex) - 1;
		if (!count.trim().isEmpty())
			this.count = Integer.parseInt(count);
		if (this.startIndex < 0)
			this.startIndex = 0;
		if (this.count>0)
			active = true;
	}

	public boolean isActive() {
		return active;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getCount() {
		return count;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		if (isActive()) {
			this.totalResults = totalResults;
			this.index = 1;
			if (this.startIndex>this.totalResults) // more than total
				this.itemsPerPage = 0;
			else if ((this.startIndex+this.count)>=this.totalResults+1) // items left less than count
				this.itemsPerPage = this.totalResults+1-this.startIndex;
			else // count
				this.itemsPerPage = this.count;
		}
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public int getItem() {
		return (this.startIndex-1)+(this.index-1);
	}

	public boolean isItem() {
		return this.index<=this.itemsPerPage;
	}

	public void nextItem() {
		this.index++;
	}

	public void setCount(int i) {
		this.count = i;
	}
}
