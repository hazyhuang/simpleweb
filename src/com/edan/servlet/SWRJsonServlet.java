package com.edan.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.edan.handler.CommentDAO;
import com.edan.model.IComment;

import net.sf.json.JSONArray;

public class SWRJsonServlet extends HttpServlet{
	 private static Logger logger = Logger.getLogger(SWRJsonServlet.class);
		

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	  
		String userID = request.getSession().getAttribute("USERID").toString();
	
		JSONArray jsonArray=getCommentJSON(userID);
	    logger.info("JSON:"+jsonArray);
		response.setContentType("text/json;charset=UTF-8"); 
		response.getWriter().write(jsonArray.toString());
		
		
	}
	


	public JSONArray getCommentJSON(String DARNumber)  {
	    JSONArray arry=new JSONArray();
		List<IComment> RecordList = CommentDAO.load(DARNumber);
		for (int i = 0; i < RecordList.size(); i++) {
			arry.add(RecordList.get(i).toJSON());
		}
		return arry;
	}

	
}
