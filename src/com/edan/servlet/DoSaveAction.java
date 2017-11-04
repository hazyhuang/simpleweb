package com.edan.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import com.edan.handler.CommentDAO;
import com.edan.model.Comment;
import com.edan.model.IComment;

public class DoSaveAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2555056766299229459L;
	static Logger logger = Logger.getLogger(DoSaveAction.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ret=execute(request,response);
		//String DARNumber = request.getSession().getAttribute(EdanWebKeys.ChangeNumber).toString();
		//JSONArray jsonArray=getCommentJSON(DARNumber);
	   // logger.info("JSON:"+jsonArray);
		response.setContentType("text/json;charset=UTF-8"); 
		response.getWriter().write(ret);
		
		
	}
	
	public void saveDocumentApprovalRecord(String dataline,String userid) throws SQLException{
		CommentDAO.addUpdate(createDALineSave(dataline), userid);
		
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = "";
			if (request.getParameter("type") != null) {
				type = request.getParameter("type");
			}

			if (type.equalsIgnoreCase("delete")) {

				String ID = "";
				if (request.getParameter("ID") != null) {
					ID = request.getParameter("ID");
				}
				logger.debug("[delete]" + ID);
				if (ID.length() > 0)
					CommentDAO.delete(ID);
			}

			if (type.equalsIgnoreCase("save")) {
				String dataline = "";
				if (request.getParameter("dataline") != null) {
					dataline = request.getParameter("dataline").toString();
					dataline = URLDecoder.decode(dataline, "UTF-8");
					dataline = dataline.replaceAll("%26", "&");
					dataline = dataline.replaceAll("%2b", "+");
					logger.debug("[dataline]" + dataline);
					String userid = request.getSession().getAttribute("USERID").toString();
				
					if (userid != null) {
					
						saveDocumentApprovalRecord(dataline, userid);
					}
				}
			}
	

		} catch (Exception e) {
			e.printStackTrace();
		}
         return "";
	
	}
	


	IComment createDALineSave(String dataline){
		
		String[] datalinearr = dataline.split(";");
		String ID=datalinearr[0];
		String comment=datalinearr[1];
		String userID=datalinearr[2];
		IComment line=new Comment();
		line.setID(Integer.valueOf(ID));
		line.setComment(replaceStrAll(comment));
		line.setUserID(Integer.valueOf(userID));
		return line;
	}
	public static String replaceStrAll(String str) {
		if (str.indexOf('\'') > -1) {
			str = str.replaceAll("'", "''");
		}
		str = str.replaceAll("%3b", ";");
		return str;
	}
}