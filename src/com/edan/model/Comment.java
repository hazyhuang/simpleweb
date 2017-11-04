package com.edan.model;

import net.sf.json.JSONObject;

public class Comment implements IComment {
	String comment;
	Integer ID;
	Integer userID;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public JSONObject toJSON() {
		JSONObject jObj=new JSONObject();
		jObj.put("ID", this.ID);
		jObj.put("comment", this.comment);
		jObj.put("userID", this.userID);
		return jObj;
	}
	
}
