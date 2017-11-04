package com.edan.model;

import net.sf.json.JSONObject;

public interface IComment {
	public String getComment();
	public void setComment(String comment);
	public Integer getID();
	public void setID(Integer iD);
	public Integer getUserID();
	public void setUserID(Integer userID);
	public JSONObject toJSON() ;
}
