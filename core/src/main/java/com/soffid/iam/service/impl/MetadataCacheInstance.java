package com.soffid.iam.service.impl;

import java.util.List;

import com.soffid.iam.api.DataType;

public class MetadataCacheInstance {
	List<DataType> data = null;
	long timestamp = 0;
	public List<DataType> getData() {
		return data;
	}
	public void setData(List<DataType> data) {
		this.data = data;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
