package com.edan.model;

import net.sf.json.JSONObject;

public interface IHazyUser {
	public Integer getID();
	public void setID(Integer ID) ;


	public void setUsername(String username) ;
	public String getUsername() ;

	public void setPassword(String password) ;

	public String getPassword();
	public JSONObject toJSON() ;

}
