package es.caib.bpm.vo;

import java.io.Serializable;

public class DocumentReference implements Serializable {
	public String id;
	public String hash;
	public DocumentReference(String id, String hash) {
		super();
		this.id = id;
		this.hash = hash;
	}
	public String getId() {
		return id;
	}
	public String getHash() {
		return hash;
	}
	public String toString() {
		return id+":"+hash;
	}
	public DocumentReference (String tag) throws IllegalArgumentException
	{
		String [] split = tag.split(":");
		if (split.length != 0)
			throw new IllegalArgumentException ("Wrong tag value "+tag);
		id = split[0];
		hash = split[1];
	}
}
