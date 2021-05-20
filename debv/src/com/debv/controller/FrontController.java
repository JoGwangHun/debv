package com.debv.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 전체 application의 controller 등록 관리
 *  *.do , *.action 
 */
	
public class FrontController extends HttpServlet{
	HashMap<String, Controller> list;
	String charset= null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset =config.getInitParameter("charset");
		//front 요청하면 제일 처음 한	번만 실행되는 메소드
		    list = new HashMap<>();
		    System.out.println("front init()");
	    //	list.put("요청하는페이지.do" , "controller");
		    list.put("/memberInsert.do", new MemberInsertController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri=req.getRequestURI(); //  /debv/insertMember.do
		
		String contextPath= req.getContextPath(); // /debv
		// /insertMember.do
		String path = uri.substring(contextPath.length());
		System.out.println(path);
		
		Controller sub =list.get(path); //controller
		sub.execute(req, resp);
	}
}
