package com.edan.model;

import net.sf.json.JSONObject;

public class HazyUser implements IHazyUser{
	private Integer ID = 0;  //

	private String username ="";//Reviewer CN Name
	private String password ="";//Reviewer EN Name
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public JSONObject toJSON() {
		JSONObject jObj=new JSONObject();
		jObj.put("ID", this.ID);
		jObj.put("username", this.username);
		jObj.put("password", this.password);
		return jObj;
	}

	
   

}
